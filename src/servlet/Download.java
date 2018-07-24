package servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(name = "Download")
public class Download extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Servlet实现下载
        String path = this.getServletContext().getRealPath("/download/testDownload.jpg");
        FileInputStream fileInputStream = new FileInputStream(path);
        String fileName = path.substring(path.lastIndexOf("\\")+1);
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        int len = 0;
        byte[] bytes = new byte[1024];
        ServletOutputStream servletOutputStream = response.getOutputStream();
        while ((len = fileInputStream.read(bytes)) > 0){
            servletOutputStream.write(bytes,0,len);
        }
        servletOutputStream.close();
        fileInputStream.close();
    }
}
