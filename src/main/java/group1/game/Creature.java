package group1.game;

import java.awt.Point;
public class Creature {
    private Point mPos = new Point(0, 0);
    public Point pos(){
        return mPos;
    }
    public void setPos(Point aPos){
        mPos = aPos;
    }


}
