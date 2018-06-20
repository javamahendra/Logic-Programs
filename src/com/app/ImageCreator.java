package com.app;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.sun.image.codec.jpeg.JPEGCodec;
 

public class ImageCreator extends HttpServlet {
    private static final long serialVersionUID = -1761346889117186607L;
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // set mime type as jpg image
        response.setContentType("image/jpg");
        ServletOutputStream out = response.getOutputStream();
 
        BufferedImage image = new BufferedImage(200, 40, BufferedImage.TYPE_BYTE_INDEXED);
 
        Graphics2D graphics = image.createGraphics();
         
        // Set back ground of the generated image to white
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 200, 40);
 
        // set gradient font of text to be converted to image
        GradientPaint gradientPaint = new GradientPaint(10, 5, Color.BLUE, 20, 10, Color.LIGHT_GRAY, true);
        graphics.setPaint(gradientPaint);
        Font font = new Font("Comic Sans MS", Font.BOLD, 30);
        graphics.setFont(font);
 
        // write 'Hello World!' string in the image
        graphics.drawString("Hello World!", 5, 30);
 
        // release resources used by graphics context
        graphics.dispose();
 
        // encode the image as a JPEG data stream and write the same to servlet output stream   
        JPEGCodec.createJPEGEncoder(out).encode(image);
 
        // close the stream
        out.close();
    }
}