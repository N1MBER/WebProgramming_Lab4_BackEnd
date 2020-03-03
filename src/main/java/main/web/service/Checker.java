package main.web.service;

import main.web.entity.Point;

public class Checker {

    public static void hit(Point point) {
        double x = point.getX();
        double y = point.getY();
        double r = point.getR();
        if(x>=0 && y>=0){
            point.setInArea(y<=r&&x<=r/2);
            return;
        }
        if(x>=0 && y<=0){
            point.setInArea(!(y<=x-r));
            return;
        }
        if(x<0 && y<=0){
            point.setInArea(Math.pow(x, 2) + Math.pow(y,2) <= Math.pow(r/2, 2));
            return;
        }
        point.setInArea(false);
    }
}