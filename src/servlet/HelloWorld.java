package servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "HelloWorld")
public class HelloWorld extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("HelloWorld!!!");

        //ServletConfig 获取初始值
        ServletConfig config = this.getServletConfig();
        String myName = config.getInitParameter("myName");
        response.getWriter().write("<br/>1.Test Servlet Config : Myname = "+myName);

        //ServletContext 获取初始值
        ServletContext context = this.getServletContext();
        String myNo = context.getInitParameter("myNo");
        response.getWriter().write("<br/>2.Test Servlet Context : myNo = " + myNo);

        //测试中文输出
        //修改返回数据编码以及浏览器编码
        response.getWriter().write("<br/>测试中文输出");

        if(request.getParameter("flag").equals("1")){
            response.getWriter().write("<br/>我是跳转过来的！！");
            response.getWriter().write("Time is : " + new Date());
        }

    }
}
