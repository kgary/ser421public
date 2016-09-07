import javax.servlet.*;
import javax.servlet.http.*;
import java.awt.*;
import java.io.*;
import java.util.*;

import Acme.JPM.Encoders.GifEncoder;
import javachart.chart.BarChart;

@SuppressWarnings("serial")
public class ChartServlet extends HttpServlet {

    public void doGet (HttpServletRequest req, HttpServletResponse res)
                        throws ServletException, IOException {

        Frame       frame = null;
        Graphics    g = null;
        try {
            String    title = req.getParameter("TITLE").replace('_', ' ');
            String    barsS = req.getParameter("BARS");

            ArrayList<String> barList = new ArrayList<String>();
            StringTokenizer barValues = new StringTokenizer(barsS, ",_ ");
            while (barValues.hasMoreTokens())
                barList.add(barValues.nextToken());

            double    data[] = new double[barList.size()];
            for (int i=0; i < barList.size(); i++)
                data[i] = Double.parseDouble((String)barList.get(i));

            BarChart chart = new BarChart();
            chart.getBackground().setTitleString(title);
            chart.addDataSet("Data", data);
            chart.getDatasets()[0].getGc().setFillColor(Color.magenta);
            chart.resize(400,400);

            frame = new Frame();
            frame.addNotify();
            Image image = frame.createImage(400,400);
            g = image.getGraphics();
            chart.drawGraph(g);

            res.setContentType("image/gif");
            GifEncoder encoder = new GifEncoder(image, res.getOutputStream());
            encoder.encode();
        }
        finally {
            if (g != null)            g.dispose();
            if (frame != null)        frame.removeNotify();
        }
    }
}
