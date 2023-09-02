package group1.view;

import group1.game.Game;
import org.lwjgl.opengl.awt.*;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.opengl.GL11.*;
import java.awt.Dimension;

//import javax.swing.JPanel;
import group1.common.GameObserver;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.BorderLayout;



class MyRunnable  implements Runnable {
    AWTGLCanvas mCanvas;
    MyRunnable(AWTGLCanvas aCanvas){
        mCanvas = aCanvas;
    }
    @Override
    public void run() {
        if (!mCanvas.isValid()) {
            GL.setCapabilities(null);
            return;
        }
        mCanvas.render();
        SwingUtilities.invokeLater(this);
    }
};

public class View extends JFrame implements KeyListener, GameObserver
{
    public IVIewPanel mPanel;
    private Game mGame;
    public View(){
        super("AWT + swing test");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 600));

        GLData data = new GLData();
        mPanel = new AwtGLPanel(data);
        ((AwtGLPanel)mPanel).setFocusable(false);
        add((AwtGLPanel)mPanel, BorderLayout.CENTER);

        mGame = new Game();
        mGame.setGameObserver(this);
        addKeyListener(this);

        pack();
        //view.transferFocus();
        setVisible(true);

        Runnable renderLoop = new MyRunnable((AwtGLPanel)mPanel);
		SwingUtilities.invokeLater(renderLoop);
    }
    public static void main(String[] args) {
        System.out.println("Hello there!");
        
        View view = new View();
        //view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //view.setLayout(new BorderLayout());
        //view.setPreferredSize(new Dimension(600, 600));
        
        //GLData data = new GLData();
        //AwtGLPanel panel = new AwtGLPanel(data);
        //panel.setFocusable(false);
        //view.add((AwtGLPanel)panel, BorderLayout.CENTER);
        
        //view.pack();
        ////view.transferFocus();
        //view.setVisible(true);

    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("key pressed: " + e.getKeyCode());
        switch(e.getKeyCode()){
            case KeyEvent.VK_A : {
                mGame.leftPressed();
                break;
            }
            case KeyEvent.VK_D : {
                mGame.rightPressed();
                break;
            }
            case KeyEvent.VK_W : {
                mGame.upPressed();
                break;
            }
            case KeyEvent.VK_S : {
                mGame.downPressed();
                break;
            }
            default :
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("key released: " + e.getKeyCode());
        switch(e.getKeyCode()){
            case KeyEvent.VK_A : {
                mGame.leftReleased();
                break;
            }
            case KeyEvent.VK_D : {
                mGame.rightReleased();
                break;
            }
            case KeyEvent.VK_W : {
                mGame.upReleased();
                break;
            }
            case KeyEvent.VK_S : {
                mGame.downReleased();
                break;
            }
            default :
                break;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("key typed: " + e.getKeyCode());
    }
    @Override
    public void tickElapsed() {
        System.out.println("tickElapsed(): creature pos:" + mGame.getCreaturePos().getX() + ", y:" + mGame.getCreaturePos().getY());
        mPanel.updateSpritePos(EntView.Creature, mGame.getCreaturePos());
    }
    
}
