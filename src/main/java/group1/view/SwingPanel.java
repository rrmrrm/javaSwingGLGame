package group1.view;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
class SwingPanel extends JPanel implements IVIewPanel{
    Map<EntView, Point> ents;
    public SwingPanel(){
        ents = new HashMap<EntView,Point>();
    }
    ///draws a heart near aPoint
    private void drawHeart(Point aPoint, Graphics g){
        int steps = 100;
        double lastx = 0;
        double lasty = 0;
        double scale = 8;
        double steapSize = 2*Math.PI / (double)steps;
        boolean first = true;
        for(double t = 0 ; t < steps+1 ; t += steapSize){
            double x = 16*Math.pow(Math.sin(t), 3);
            double y = -13*Math.cos(t) + 5*Math.cos(2*t) + 2*Math.cos(3*t) + Math.cos(4*t);
            x *= scale;
            y *= scale;
            x += aPoint.getX();
            y += aPoint.getY();
            if(first)
                first = false;
            else
                g.drawLine((int)lastx, (int)lasty, (int)x, (int)y);
            lastx = x;
            lasty = y;
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        /// draw heart
        g.translate(getWidth()/2, getHeight()/2);
        g.setColor(Color.RED);
        for (Point posIt : ents.values()) {
            drawHeart(posIt, g);
        }
    }
    @Override
    public void updateSpritePos(EntView aEnt, Point aPoint){
        ents.put(aEnt, aPoint);
        repaint();
    }
}