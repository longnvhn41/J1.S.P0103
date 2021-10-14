/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import context.DBConnect;
import dao.UserDao;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author GHC
 */
public class ForgetPassController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ForgetPassController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgetPassController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String service = request.getParameter("service");
            DBConnect dBConnect = new DBConnect();
            if(service.equals("forget")){
                UserDao d = new UserDao(dBConnect);
                String email = request.getParameter("email");
                User u = d.checkExitsEmail(email);
                if(u==null){
                    request.setAttribute("mess", "The email you entered did not exist, please try again!");
                    request.getRequestDispatcher("forgetPass.jsp").forward(request, response);
                }else{
                    String userfrom = "longnvhn41@gmail.com";
                    String passfrom = "nguyenvanlong98";
                    String subject = "Reset Password";
                    String code = d.getRandom2(6);
                    String message = ("This is your code: " + code);
                    UserDao.send(email, subject, message, userfrom, passfrom);
                    HttpSession session1 = request.getSession();
                    session1.setAttribute("rsUser", u);
                    session1.setAttribute("rsCode", code);
                    session1.setMaxInactiveInterval(10 * 60);
                    response.sendRedirect("verifyResetPass.jsp");
                }
            }
            if(service.equals("checkCode")){
                HttpSession session = request.getSession(); 
                User user = (User) session.getAttribute("rsUser");
                String verifyCode = session.getAttribute("rsCode").toString();
                UserDao dao = new UserDao(dBConnect);
                String code = request.getParameter("authcode");
                String pass = request.getParameter("newPass");
                if (!verifyCode.equalsIgnoreCase(code)) {
                    request.setAttribute("error", "sai mã xác minh");
                    request.setAttribute("tempPass", pass);
                    request.getRequestDispatcher("verifyResetPass.jsp").forward(request, response);
                } else {
                    dao.changePass(user.getAccount(), pass);
                    session.setAttribute("verifyCode", null);
                    response.sendRedirect("HomeP.jsp");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ForgetPassController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
