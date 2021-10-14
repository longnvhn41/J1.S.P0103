/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBConnect;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Nguyen Van Long
 */
public class UserDao {

    Connection conn = null;

    DBConnect dbConn = null;

    public UserDao(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbConn = dbconn;
    }

    public String getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public static int randomNumber(int min, int max) {
        Random rnd = new Random();
        return rnd.nextInt((max - min) + 1) + min;
    }

    public String getRandom2(int numberOfCharactor) {
        String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
        String alphaUpperCase = alpha.toUpperCase(); // A-Z
        String digits = "0123456789"; // 0-9
        String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfCharactor; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }

    public static void send(String to, String sub,
            String msg, final String user, final String pass) {
        //Tạo 1 Properties(key-value)
        Properties props = new Properties();

        //Thông số kết nối tới Smtp Server--> đăng nhập email
        props.put("mail.smtp.host", "smtp.gmail.com");
        //below mentioned mail.smtp.port is optional
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        /* Pass Properties object(props) and Authenticator object   
           for authentication to Session instance 
         */
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {

            /* Create an instance of MimeMessage, 
 	      it accept MIME types and headers 
             */
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);

            /* Transport class is used to deliver the message to the recipients */
            Transport.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int addUser(String cname, String account, String password, String email,
            String phone, String dob, int gender, String address, int role, String ava) {

        int n = 0;
        String sql = "insert into [user] values(?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cname);
            pre.setString(2, account);
            pre.setString(3, password);
            pre.setString(4, email);
            pre.setString(5, phone);
            pre.setString(6, dob);
            pre.setInt(7, gender);
            pre.setString(8, address);
            pre.setInt(9, role);
            pre.setString(10, ava);

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public User checkUserExitsAccount(String user) {
        try {
            String sql = "select * from [User] where account=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getInt(10));
            }
        } catch (Exception e) {

        }
        return null;
    }

    public User checkUser(String user, String pass) {
        try {
            String sql = "select * from [user] where account=? and password=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, user);
            pre.setString(2, pass);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getInt(10), rs.getString(11));
                return u;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public User checkExitsEmail(String email) {
        try {
            String sql = "select * from [User] where email=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getInt(10));
            }
        } catch (Exception e) {

        }
        return null;
    }

    public User showUserProfile(String account) {
        try {
            String sql = "select * from [User] where account=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, account);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getInt(8), rs.getString(9));
                return u;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public void updateUser(String id, String name, String acc, String pass, String email,
            String phone, String dob, String sex, String address) {
        String sql = "update [user] set full_name=?, account=?, "
                + "[password]=?,email=?,phone=?, "
                + "DOB=?, gender=?, [address]=? where id=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, name);
            pre.setString(2, acc);
            pre.setString(3, pass);
            pre.setString(4, email);
            pre.setString(5, phone);
            pre.setString(6, dob);
            pre.setString(7, sex);
            pre.setString(8, address);
            pre.setString(9, id);
            pre.executeUpdate();

        } catch (SQLException ex) {

        }
    }

    public void updatePassUser(String email, String password) {
        String sql = "Update [User] SET password=? WHERE email=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, password);
            pre.setString(2, email);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean changePass(String account, String password) {
        try {
            String sql = "  UPDATE [HappyProgramming].[dbo].[user]\n"
                    + "SET [password] = ? WHERE [account] =?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, account);
            ps.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
