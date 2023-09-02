package group1.game;

import group1.common.GameObserver;

import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private boolean leftActive = false;
    private boolean rightActive = false;
    private boolean upActive = false;
    private boolean downActive = false;
    private GameObserver mObserver= null;
    private Creature mCreature;
    // how many pixels the creture moves in 1 millisecond
    private float mCreatureMoveRate = 0.1f;
    Timer mStepper;
    long prevTime = 0;
    long tickLen = 100;
    public Game(){
        mCreature = new Creature();
        // timer will call step() periodically
        mStepper = new Timer("game stepper");
        mStepper.schedule(new TimerTask() {
            @Override
            public void run() {
                step();
            }
            
        }, 0, tickLen);
    }

    /// set view for game instance
    public void setGameObserver(GameObserver aGO){
        mObserver = aGO;
    }
    private void step(){
        long newTime = System.nanoTime();
        double dTimeMilli = (newTime-prevTime)/1000000;
        //System.out.println("Game: step() called. milliSeconds spent since last step: " + dTimeMilli);
        prevTime = newTime;
        if(leftActive){
            mCreature.pos().translate(-(int)(mCreatureMoveRate*dTimeMilli), 0);
        }
        if(rightActive){
            mCreature.pos().translate((int)(mCreatureMoveRate*dTimeMilli), 0);
        }
        if(upActive){
            mCreature.pos().translate(0, -(int)(mCreatureMoveRate*dTimeMilli));
        }
        if(downActive){
            mCreature.pos().translate(0, (int)(mCreatureMoveRate*dTimeMilli));
        }
        mObserver.tickElapsed();
    }

    public Point getCreaturePos(){
        return mCreature.pos();
    }
    public void leftPressed(){
        leftActive = true;
    }
    public void leftReleased(){
        leftActive = false;
    }
    public void rightPressed(){
        rightActive = true;
    }
    public void rightReleased(){
        rightActive = false;
    }
    public void upPressed(){
        upActive = true;
    }
    public void upReleased(){
        upActive = false;
    }
    public void downPressed(){
        downActive = true;
    }
    public void downReleased(){
        downActive = false;
    }
}
