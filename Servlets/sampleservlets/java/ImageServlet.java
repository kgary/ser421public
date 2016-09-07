//javac -d \apps\jsdk2.0\examples

import javax.servlet.*;
import javax.servlet.http.*;
import java.awt.*;
import java.io.*;

import Acme.JPM.Encoders.GifEncoder;

@SuppressWarnings("serial")
public class ImageServlet extends HttpServlet {

    public void doGet (HttpServletRequest req, HttpServletResponse res)
                        throws ServletException, IOException {

        ServletOutputStream out = res.getOutputStream();
        String              text = req.getParameter("DISPLAY_TEXT");
        Frame               frame = null;
        Graphics            g = null;

        try {
            frame = new Frame();
            frame.addNotify();

/***
            Image image = Toolkit.getDefaultToolkit().
                                  getImage("javalogo52x88.gif");
            res.setContentType("image/gif");
            out.
***/
            Image image = frame.createImage(400, 400);
            g = image.getGraphics();


            g.setColor(Color.cyan);
            g.fill3DRect(100, 100, 50, 50, true);
            g.setColor(Color.blue);
            g.draw3DRect(125, 120, 50, 50, false);

            Toolkit tk = Toolkit.getDefaultToolkit();
            Image pic = tk.getImage( "javalogo52x88.gif");
            g.drawImage(pic, 10, 30, Color.gray, frame);

            g.setColor(Color.magenta);
            g.setFont(new Font("Dialog", Font.ITALIC + Font.BOLD, 20));
            g.drawString("Hello There", 200, 100);

            if (text != null)
                g.drawString(text, 10, 30);
            else
                g.drawString("Text argument missing", 10, 30);


            res.setContentType("image/gif");
            GifEncoder encoder = new GifEncoder(image, out);
            encoder.encode();
        }
        finally {
            if (g != null)    g.dispose();
            if (frame != null)        frame.removeNotify();
        }
    }
}
