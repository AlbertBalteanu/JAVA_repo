package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.List;

public class DrawingPanel extends JPanel {
    private static final int DOT_RADIUS = 15; // Increased size for better visibility
    private DotConnectGame gameFrame;
    private Point2D dragStart = null;
    
    public DrawingPanel(DotConnectGame gameFrame) {
        this.gameFrame = gameFrame;
        init();
    }
    
    private void init() {
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        // Set a preferred size to ensure the panel is visible
        setPreferredSize(new Dimension(700, 500));
        
        // Add mouse listener for click-based dot selection
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point2D mousePoint = new Point2D.Double(e.getX(), e.getY());
                for (Point2D dot : gameFrame.getDots()) {
                    if (mousePoint.distance(dot) <= DOT_RADIUS) {
                        gameFrame.selectDot(dot);
                        break;
                    }
                }
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                Point2D mousePoint = new Point2D.Double(e.getX(), e.getY());
                for (Point2D dot : gameFrame.getDots()) {
                    if (mousePoint.distance(dot) <= DOT_RADIUS) {
                        dragStart = dot;
                        repaint();
                        break;
                    }
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                if (dragStart != null) {
                    Point2D mousePoint = new Point2D.Double(e.getX(), e.getY());
                    for (Point2D dot : gameFrame.getDots()) {
                        if (mousePoint.distance(dot) <= DOT_RADIUS && !dot.equals(dragStart)) {
                            // Use selectDot twice to simulate the two selections
                            gameFrame.selectDot(dragStart);
                            gameFrame.selectDot(dot);
                            break;
                        }
                    }
                    dragStart = null;
                    repaint();
                }
            }
        });
        
        // Add mouse motion listener for drag preview
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragStart != null) {
                    repaint();
                }
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Enable anti-aliasing for smoother drawing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw all lines (retained mode)
        List<Line> lines = gameFrame.getLines();
        if (lines != null) {
            for (Line line : lines) {
                g2d.setColor(line.getColor());
                g2d.setStroke(new BasicStroke(2f));
                g2d.draw(new Line2D.Double(line.getStart(), line.getEnd()));
            }
        }
        
        // Draw drag line if dragging
        if (dragStart != null) {
            Point mousePoint = getMousePosition();
            if (mousePoint != null) {
                g2d.setColor(Color.GRAY);
                g2d.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{5}, 0));
                g2d.draw(new Line2D.Double(dragStart.getX(), dragStart.getY(), mousePoint.x, mousePoint.y));
            }
        }
        
        // Draw all dots
        List<Point2D> dots = gameFrame.getDots();
        if (dots != null) {
            Point2D selectedDot = gameFrame.getSelectedDot();
            
            g2d.setFont(new Font("SansSerif", Font.BOLD, 10));
            
            int dotIndex = 0;
            for (Point2D dot : dots) {
                if (dot.equals(selectedDot) || dot.equals(dragStart)) {
                    // Highlight selected/dragged dot
                    g2d.setColor(Color.GREEN);
                    g2d.fill(new Ellipse2D.Double(dot.getX() - DOT_RADIUS - 2, 
                                                dot.getY() - DOT_RADIUS - 2, 
                                                (DOT_RADIUS + 2) * 2, 
                                                (DOT_RADIUS + 2) * 2));
                }
                
                // Fill dot with black
                g2d.setColor(Color.BLACK);
                g2d.fill(new Ellipse2D.Double(dot.getX() - DOT_RADIUS, 
                                            dot.getY() - DOT_RADIUS, 
                                            DOT_RADIUS * 2, 
                                            DOT_RADIUS * 2));
                
                // Add white border for better visibility
                g2d.setColor(Color.WHITE);
                g2d.setStroke(new BasicStroke(1.5f));
                g2d.draw(new Ellipse2D.Double(dot.getX() - DOT_RADIUS, 
                                            dot.getY() - DOT_RADIUS, 
                                            DOT_RADIUS * 2, 
                                            DOT_RADIUS * 2));
                
                // Add dot index number for better visibility
                g2d.setColor(Color.WHITE);
                String indexText = String.valueOf(dotIndex++);
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(indexText);
                int textHeight = fm.getAscent();
                g2d.drawString(indexText, 
                              (float)(dot.getX() - textWidth/2), 
                              (float)(dot.getY() + textHeight/2));
            }
        }
        
        // Display best possible score information
        double bestScore = gameFrame.getBestPossibleScore();
        if (bestScore > 0) {
            g2d.setColor(Color.DARK_GRAY);
            g2d.setFont(new Font("SansSerif", Font.PLAIN, 12));
            g2d.drawString("Best possible score: " + String.format("%.2f", bestScore), 10, 20);
        }
        
        // Debug information
        g2d.setColor(Color.RED);
        g2d.drawString("Panel size: " + getWidth() + "x" + getHeight(), 10, 40);
        g2d.drawString("Dots: " + (dots != null ? dots.size() : 0), 10, 60);
    }
}