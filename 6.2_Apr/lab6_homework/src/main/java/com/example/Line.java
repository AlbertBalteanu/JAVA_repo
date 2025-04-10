package com.example;


import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.Serializable;

public class Line implements Serializable {
    private Point2D start;
    private Point2D end;
    private Color color;
    
    public Line(Point2D start, Point2D end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }
    
    public Point2D getStart() {
        return start;
    }
    
    public Point2D getEnd() {
        return end;
    }
    
    public Color getColor() {
        return color;
    }
}