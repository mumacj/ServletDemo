package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@WebServlet(name = "Gzip")
public class Gzip extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-Encoding","gzip");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("gbk");
        String testGzip = "测试测试测试测试测试" +
                    "测试测试测试测试测试测试" +
                    "测试测试测试测试测试测试" +
                    "测试测试测试测试测试测试" +
                    "测试测试测试测试测试测试" +
                    "测试测试测试测试测试测试";

        /*
        * response.getWriter()和response.getOutputStream()互斥，用了一个就不能用另一个
        *
        * */
        //String testGzip = "wqqqqqqqqqqqjkjkalskajfkjkasljfkljkxkjkalskajfkjkasljfkljkxzjckajskkjkalskajfkjkasljfkljkxzjckajskzjckajsk";

//        response.getWriter().write("原来的长度是:"+testGzip.getBytes().length+"<br/>");
//        response.getWriter().write(testGzip);
        System.out.println("原来的长度是:"+testGzip.getBytes().length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gzipOutputStream.write(testGzip.getBytes());
        gzipOutputStream.close();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        System.out.println("压缩后的长度是:"+bytes.length);
        response.getOutputStream().write(bytes);
    }
}
