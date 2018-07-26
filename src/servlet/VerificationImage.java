package servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "VerificationImage")
public class VerificationImage extends HttpServlet {

    //生成的图片的宽度
    public static final int WIDTH = 120;
    //生成的图片的高度
    public static final int HEIGHT = 30;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String creatTypeFlag = request.getParameter("creatTypeFlag");
        //1.在内存中生成一张图片
        BufferedImage bufferedImage =new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        //2.得到这张图片
        Graphics graphics = bufferedImage.getGraphics();
        //3.设置图片背景颜色
        setBackGround(graphics);
        //4.设置图片边框
        setBorder(graphics);
        //5.在图片上画干扰线
        drawRandomLines(graphics);
        //6.在图片上加随机数
        String random = creatRandomNum((Graphics2D) graphics);
        //7.将随机数存到session中
        request.getSession().setAttribute("checkcode",random);
        //8.设置响应头通知浏览器以图片形式打开
        response.setContentType("image/jpeg");
        //9.设置响应头控制浏览器不要缓存
        response.setDateHeader("expries",-1);
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Pragma","no-cache");
        //10.将图片写给浏览器
        ImageIO.write(bufferedImage,"jpg",response.getOutputStream());
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    /*
    * 设置背景色
    * */

    private void setBackGround(Graphics graphics){
        //设置颜色
        int r = new Random().nextInt(255);
        int g = new Random().nextInt(255);
        int b = new Random().nextInt(255);
        graphics.setColor(new Color(r,g,b));
        //设置填充区域
        graphics.fillRect(0,0,WIDTH,HEIGHT);
    }

    /*
    * 设置边框
    * */

    private void setBorder(Graphics graphics){
        //设置颜色
        graphics.setColor(Color.BLUE);
        //设置边框区域
        graphics.drawRect(1,1,WIDTH-2,HEIGHT-2);
    }

    /*
    * 在图片上画干扰线
    * */

    private void drawRandomLines(Graphics graphics){
        //设置颜色
        //graphics.setColor(Color.MAGENTA);
        //设置干扰线数量
        Integer lineNum = new Random().nextInt(10);
        for (int i = 0 ; i < lineNum ; i ++){
            int x1 = new Random().nextInt(WIDTH);
            int y1 = new Random().nextInt(HEIGHT);
            int x2 = new Random().nextInt(WIDTH);
            int y2 = new Random().nextInt(HEIGHT);
            //设置颜色
            int r = new Random().nextInt(255);
            int g = new Random().nextInt(255);
            int b = new Random().nextInt(255);
            graphics.setColor(new Color(r,g,b));
            //画线
            graphics.drawLine(x1,y1,x2,y2);
        }
    }

    /*
    * 在图片上加随机数
    * */

    private String creatRandomNum(Graphics2D graphics2D){
        graphics2D.setFont(new Font(null,Font.BOLD,20));
        StringBuffer stringBuffer = new StringBuffer();
        String baseNum = "0123456789";
        int x = 5;
        String ch = "";
        for ( int i = 0 ; i < 4 ;i ++){
            //设置字体旋转方向
            int degree = new Random().nextInt() % 30;
            ch = baseNum.charAt(new Random().nextInt(baseNum.length())) + "";
            stringBuffer.append(ch);
            //正向角度
            graphics2D.rotate(degree * Math.PI / 180 ,x,20);
            graphics2D.drawString(ch,x,20);
            //反向角度
            graphics2D.rotate(-degree * Math.PI / 180 ,x,20);
            x += 30 ;
        }
        return stringBuffer.toString();
    }
}
