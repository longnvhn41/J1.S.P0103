/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBConnect;
import entity.Request;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nguyen Van Long
 */
public class RequestDao {

    Connection conn = null;

    DBConnect dbConn = null;

    public RequestDao(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbConn = dbconn;
    }

    static Connection con;
    static PreparedStatement ps;
    static ResultSet rs;

    public int getMaxRequestId() {
        String query = "SELECT MAX(id)FROM Request";
        int output = 0;
        try {
            conn = new DBConnect().con;
            PreparedStatement ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                output = rs.getInt(1);
            }
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                con.close();
            } catch (Exception e) {
            }
        } catch (Exception e) {
            System.out.println(e);
            output = -2;
        }
        return output;
    }

    public void createRequest(Request request) {
        String query = "insert into request values (?, null, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = new DBConnect().con;
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, request.getMentee_id());
            ps.setString(2, request.getMess());
            ps.setDate(3, request.getDeadline() == null ? null : new java.sql.Date(request.getDeadline().getTime()));
            ps.setDate(4, request.getCreationDate() == null ? null : new java.sql.Date(request.getCreationDate().getTime()));
            ps.setDate(5, request.getFinishDate() == null ? null : new java.sql.Date(request.getFinishDate().getTime()));
            ps.setInt(6, request.getStatus());
            ps.setFloat(7, request.getDeadlineHour());
            ps.setString(8, request.getTitle());
            ps.executeUpdate();

            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                con.close();
            } catch (Exception e) {
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
