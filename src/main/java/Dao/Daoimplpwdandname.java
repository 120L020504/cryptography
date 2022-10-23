package Dao;

import ADT.User;
import JDBCUtils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Daoimplpwdandname {//存储用户名和密码
    public List<User> findbyusername(String name){//通过输入username寻找所有username和pwd并存放到list中返回
        Connection conn =null;
        PreparedStatement pstmt = null;
        User usr =null;
        List<User> result = new ArrayList<>();//作为存放结果的用户list
        try{
            conn = JDBCUtils.getConnection();
            String sql = "SELECT * from user where username=?";
            pstmt= conn.prepareStatement(sql);
            pstmt.setString(1,name);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next())
            {
                String username=rs.getString("username");
                String password=rs.getString("password");
                System.out.println("所查询的用户名为：");
                System.out.println(username);
                System.out.println("所查询的密码为：");
                usr=new User();//将查询到的username和pwd构造为一个User对象存放到result列表中
                usr.setUsername(username);
                usr.setPassword(password);
                result.add(usr);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.close(pstmt,conn);
        }
        return result;

    }
    public boolean save(User usr){//输入一个User对象，将其存放到数据库中
        String uname= usr.getUsername();
        String pwd=usr.getPassword();
        Connection conn =null;
        PreparedStatement pstmt = null;
        int count=0;
        try{
            conn = JDBCUtils.getConnection();
            String sql = "insert into user values(?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,uname);
            pstmt.setString(2,pwd);
            count=pstmt.executeUpdate();
            System.out.println("有"+count+"行记录被修改");
        }catch(SQLException e){
            e.printStackTrace();

        }
        finally {
            JDBCUtils.close(pstmt,conn);
        }
        if(count==0){
            return false;
        }
        else{
            return true;
        }

    }
    public boolean updatepwd(String name,String newpwd){//修改某用户的密码
        Connection conn =null;
        PreparedStatement pstmt=null;
        int result =666;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "UPDATE user SET password=? where username=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,newpwd);
            pstmt.setString(2,name);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.close(pstmt,conn);
        }
        if(result==0){
            return false;
        }
        else{
            return true;
        }
    }

}
