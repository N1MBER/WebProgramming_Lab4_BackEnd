package main.web.service;


import main.web.entity.Point;
import main.web.mbeans.PointCounter;
import main.web.mbeans.Square;
import org.springframework.stereotype.Service;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

@Service
public class PointService {
    private PointCounter pointCounter;
    private Square square;

    PointService(PointCounter pointCounter, Square square){
        this.pointCounter = pointCounter;
        this.square = square;
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        try {
            mBeanServer.registerMBean(pointCounter, new ObjectName("main.web.mbeans:type=PointCounter"));
            mBeanServer.registerMBean(square, new ObjectName("main.web.mbeans:type=Square"));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void count(Point point){
        pointCounter.setPoint(point);
    } 

    public void getSquare(double r){
        square.setSquareOfFigure(r);
    }
}
