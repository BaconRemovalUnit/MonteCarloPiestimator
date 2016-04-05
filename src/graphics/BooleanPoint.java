package graphics;

import java.awt.*;

/**
 * University of Rochester
 * Author: Shengqi Suizhu
 * Date: 2016/3/21
 * graphics/BooleanPoint
 */
public class BooleanPoint extends Point {
    private boolean hit;

    public BooleanPoint(){
    }

    public BooleanPoint(boolean hit){
        this.hit = hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public boolean isHit(){
        return hit;
    }
}
