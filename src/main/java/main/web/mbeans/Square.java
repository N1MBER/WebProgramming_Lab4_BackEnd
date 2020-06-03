package main.web.mbeans;

import org.springframework.stereotype.Component;

@Component
public class Square implements SquareMBean {
    private double squareOfFigure;

    public void setSquareOfFigure(double r) {
        this.squareOfFigure = (r / 2) * r + Math.pow(r / 2, 2) +
                0.25 * r * Math.PI / 4;
    }

    @Override
    public double getSquareOfFigure() {
        return squareOfFigure;
    }
}
