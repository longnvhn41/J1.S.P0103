/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBConnect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

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

    public int addRequest(int menteeID, String mess, int status) {
        int n = 0;
        //String sql="insert into request(mentee_id, [message],request_date,[status]) values (?,?,?,?)";
        String sql = "insert into request(mentee_id, [message],[status]) values (?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, menteeID);
            pre.setString(2, mess);
            pre.setInt(3, status);
            n = pre.executeUpdate();
        } catch (Exception e) {
        }
        return n;
    }
}
