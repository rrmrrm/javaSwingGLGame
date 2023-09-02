package group1.view;

import org.lwjgl.opengl.awt.AWTGLCanvas;
import org.lwjgl.opengl.awt.*;
import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.opengl.GL11.*;


import java.awt.Point;
import java.util.HashMap;
import java.util.Map;


public class AwtGLPanel extends AWTGLCanvas implements IVIewPanel {
    private static final long serialVersionUID = 1L;
    
    Map<EntView, Point> mEnts;
    AwtGLPanel(GLData aGlData){
        super(aGlData);
        mEnts = new HashMap<EntView,Point>();
    }
    @Override
    public void initGL() {
        System.out.println("OpenGL version: " + effective.majorVersion + "." + effective.minorVersion + " (Profile: " + effective.profile + ")");
        createCapabilities();
        glClearColor(0.3f, 0.4f, 0.5f, 1);
    }
    @Override
    public void paintGL() {
        if(!mEnts.containsKey(EntView.Creature)){
            return;
        }
        Point pos= mEnts.get(EntView.Creature);
        // if lwjglx-awt 0.2.0 will be available in maven central(now some CI/CD tests are failing for this release), getwidth height can be changes to getFramebufferWidth() getFramebufferHeight();
        // the importance of framebufferhweight/width in contrast to getheight/width is the former handles desktop/graphics configuration scaling.
        int w = getWidth();
        int h = getHeight();
        float xScaled = (float)(pos.getX()/(float)w);
        float yScaled = (float)(pos.getY()/(float)h);
        float creatureScale = 0.1f;
        float aspect = (float) w / h;
        double now = System.currentTimeMillis() * 0.001;
        float cWidth = creatureScale * (float) Math.abs(Math.sin(now * 0.3));
        float cHeight = creatureScale;
        glClear(GL_COLOR_BUFFER_BIT);
        glViewport(0, 0, w, h);
        glBegin(GL_QUADS);
        glColor3f(0.4f, 0.6f, 0.8f);
        glVertex2f(xScaled - 0.75f * cWidth / aspect, yScaled + 0.0f);
        glVertex2f(xScaled + 0, yScaled - 0.75f * cHeight);
        glVertex2f(xScaled + 0.75f * cWidth/ aspect, yScaled + 0);
        glVertex2f(xScaled + 0, yScaled + 0.75f * cHeight);
        glEnd();
        swapBuffers();
    }
    @Override
    public void updateSpritePos(EntView aEnt, Point aPoint) {
        mEnts.put(aEnt, aPoint);
        //repaint();
    }
}
