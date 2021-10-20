/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;



import context.DBConnect;
import entity.Skill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SkillDao {
   static Connection conn;

    DBConnect dbConn = null;

    public SkillDao(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbConn = dbconn;
    }
     static Connection con;
     static PreparedStatement ps;
    static ResultSet rs;
//----------------------------------------------------------------------SKILL----------------------------------------------------------
    public int getHighestSkillID(){
        String query = "SELECT MAX(id) FROM skill;";
        try {
            
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
                   
            try{rs.close();} catch(Exception e){}    
            try{ps.close();} catch(Exception e){}   
            try{con.close();} catch(Exception e){}            
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    // Lấy skill theo id
    public Skill getSkill(int id) {
        String query = "select * from skill where id = ? ";
        try {
         
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new Skill(rs.getInt("id"), rs.getString("skill_name"), rs.getString("status"));
            }
                    
            try{rs.close();} catch(Exception e){}    
            try{ps.close();} catch(Exception e){}   
            try{con.close();} catch(Exception e){}           
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    // Lấy danh sách Skill
    public ArrayList<Skill> getSkillList() {

        ArrayList<Skill> skillList = new ArrayList<>();
        String query = "select * from skill";
        try {
            conn = new DBConnect().con;
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                skillList.add(new Skill(rs.getInt("id"), rs.getString("name"), rs.getString("description")));
            }
            try{rs.close();} catch(Exception e){}    
            try{ps.close();} catch(Exception e){}   
            try{con.close();} catch(Exception e){}      
        } catch (SQLException e) {
            System.out.println(e);
        }
        return skillList;
    }
    
   
  
    
    // Đưa Skill mới vào database
    public void createSkill(Skill skill) {
        String query = "insert into skill values (?,?,?)";

        try {
             PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, skill.getId());
            ps.setString(2, skill.getName());
            ps.setString(3, skill.getStatus());
            ps.executeUpdate();
                    
            try{rs.close();} catch(Exception e){}    
            try{ps.close();} catch(Exception e){}   
            try{con.close();} catch(Exception e){}    
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    // Cập nhật Skill theo id
    public void updateSkill(Skill skill) {
        String query = "update skill set skill_name = ? ,status = ? where id = ? ";

        try {
       
            ps = con.prepareStatement(query);
            ps.setString(1, skill.getName());
            ps.setString(2, skill.getStatus());
            ps.setInt(3, skill.getId());
            ps.executeUpdate();

            try{rs.close();} catch(Exception e){}    
            try{ps.close();} catch(Exception e){}   
            try{con.close();} catch(Exception e){}    
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public ArrayList<Skill> getSkillRequest(int id){
        ArrayList<Skill> skillList = new ArrayList<>();
        String query = "SELECT s.id, s.name, s.description FROM skill s " 
                    + "inner join request_skill rs  " 
                    + "on s.id = rs.skill_id " 
                    + "inner join request r  " 
                    + "on rs.request_id = r.id " 
                    + "where r.id = (?) ";
        try {
            conn = new DBConnect().con;
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                skillList.add(new Skill(rs.getInt("id"), rs.getString("name"), rs.getString("description")));
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
        } catch (SQLException e) {
            System.out.println(e);
        }
        return skillList;
    }

    /*public static void main(String[] args) throws SQLException {
        DBConnect dconn = new DBConnect();
        SkillDao s = new SkillDao(dconn);
        ArrayList<Skill> a = s.getSkillRequest(25);     
        for (Skill skill : a) {
            System.out.println(skill.toString());
        }
    }*/
     
}
