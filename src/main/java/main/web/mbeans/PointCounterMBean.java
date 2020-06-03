package main.web.mbeans;

import javax.management.NotificationEmitter;

public interface PointCounterMBean {
    int getCountOfPoints();
    int getCountInArea();
    int getCountNotInArea();
}
