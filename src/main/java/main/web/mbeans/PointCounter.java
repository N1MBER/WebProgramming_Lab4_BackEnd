package main.web.mbeans;

import main.web.entity.Point;
import org.springframework.stereotype.Component;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

@Component
public class PointCounter  extends NotificationBroadcasterSupport implements PointCounterMBean{
    private int countOfPoints;
    private int countInArea;
    private int countNotInArea;
    private int outsider;

    public void setPoint(Point point){
        countOfPoints++;
        if(point.getInArea()) countInArea++;
        else countNotInArea++;
        if(Math.abs(point.getX()) > 5.9 || Math.abs(point.getY()) > 5.9)
            sendNotification(new Notification("Point outside area",
                    this, ++outsider,"That's point have coordinates " +
                    "(" + point.getX() + ";" + point.getY() + ";" + point.getR() + ")" +
                    " outside the canvas area"));
    }

    @Override
    public int getCountInArea() {
        return countInArea;
    }

    @Override
    public int getCountOfPoints() {
        return countOfPoints;
    }

    @Override
    public int getCountNotInArea() {
        return countNotInArea;
    }
}
