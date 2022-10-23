package Servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    /*
    用户将用户名密码传送到后端，后端解密后经过hash存入数据库
     */
    @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse resp){
        System.out.println("into post method");//提示进入post方法
        String pwd = req.getParameter("password");
        String username=req.getParameter("username");
        String email=req.getParameter("email");
        String name=req.getParameter("name");
        String tele=req.getParameter("telephone");
        String age=req.getParameter("age");
        String address=req.getParameter("address");
        System.out.println("username"+username);
        System.out.println("pwd:"+pwd);
        System.out.println(email);
        System.out.println(address);
        System.out.println(age);
        System.out.println(tele);
        //RSA解密：


        //
        //:数据库
        
    }



}


