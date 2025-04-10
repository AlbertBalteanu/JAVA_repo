package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.Queue;


public class DotConnectGame extends JFrame {
    private ConfigPanel configPanel;
    private ControlPanel controlPanel;
    private DrawingPanel canvas;
    
    private int numberOfDots = 10; // Default number of dots
    private List<Point2D> dots = new ArrayList<>();
    private List<Line> lines = new ArrayList<>();
    private boolean isPlayerOneTurn = true; // true = Blue player, false = Red player
    private String currentPlayer = "Blue";
    private Point2D selectedDot = null;
    private double blueScore = 0;
    private double redScore = 0;
    
    public DotConnectGame() {
        super("Dot Connect Game");
        init();
    }
    
    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        
        // Create the component panels
        configPanel = new ConfigPanel(this);
        canvas = new DrawingPanel(this);
        controlPanel = new ControlPanel(this);
        
        // Set up the layout
        setLayout(new BorderLayout());
        add(configPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        
        // Pack and display
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        // Initialize the game after the frame is visible
        SwingUtilities.invokeLater(this::newGame);
    }
    
    public void newGame() {
        dots.clear();
        lines.clear();
        isPlayerOneTurn = true;
        currentPlayer = "Blue";
        blueScore = 0;
        redScore = 0;
        selectedDot = null;
        
        // Generate random dots
        Random rand = new Random();
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        
        // Ensure we have enough padding from edges
        int padding = 50;
        
        for (int i = 0; i < numberOfDots; i++) {
            int x = rand.nextInt(canvasWidth - 2 * padding) + padding;
            int y = rand.nextInt(canvasHeight - 2 * padding) + padding;
            dots.add(new Point2D.Double(x, y));
        }
        
        canvas.repaint();
        updateStatusMessage();
    }
    
    public void setNumberOfDots(int dots) {
        this.numberOfDots = dots;
    }
    
    public void selectDot(Point2D dot) {
        if (selectedDot == null) {
            selectedDot = dot;
        } else {
            // Check if the second dot is different from the first
            if (!selectedDot.equals(dot)) {
                // Create a new line
                Color lineColor = isPlayerOneTurn ? Color.BLUE : Color.RED;
                Line newLine = new Line(selectedDot, dot, lineColor);
                
                // Check if this line already exists
                boolean lineExists = false;
                for (Line line : lines) {
                    if ((line.getStart().equals(selectedDot) && line.getEnd().equals(dot)) || 
                        (line.getStart().equals(dot) && line.getEnd().equals(selectedDot))) {
                        lineExists = true;
                        break;
                    }
                }
                
                if (!lineExists) {
                    lines.add(newLine);
                    
                    // Calculate and update score
                    double lineLength = calculateDistance(selectedDot, dot);
                    if (isPlayerOneTurn) {
                        blueScore += lineLength;
                    } else {
                        redScore += lineLength;
                    }
                    
                    // Check if game is over
                    if (isGameOver()) {
                        String winner = (blueScore <= redScore) ? "Blue" : "Red";
                        JOptionPane.showMessageDialog(this, 
                            winner + " player wins with a score of " + 
                            (winner.equals("Blue") ? String.format("%.2f", blueScore) : String.format("%.2f", redScore)));
                        newGame();
                    } else {
                        // Switch turns
                        isPlayerOneTurn = !isPlayerOneTurn;
                        currentPlayer = isPlayerOneTurn ? "Blue" : "Red";
                    }
                }
            }
            
            selectedDot = null;
            updateStatusMessage();
            canvas.repaint();
        }
    }
    
    private boolean isGameOver() {
        // Check if all dots are connected (form a single connected component)
        if (dots.isEmpty() || lines.isEmpty()) return false;
        
        // Use BFS to check connectivity
        Set<Point2D> visited = new HashSet<>();
        Queue<Point2D> queue = new LinkedList<>();
        queue.add(dots.get(0));
        visited.add(dots.get(0));
        
        while (!queue.isEmpty()) {
            Point2D current = queue.poll();
            
            // Find all connected dots through lines
            for (Line line : lines) {
                Point2D nextDot = null;
                
                if (line.getStart().equals(current)) {
                    nextDot = line.getEnd();
                } else if (line.getEnd().equals(current)) {
                    nextDot = line.getStart();
                }
                
                if (nextDot != null && !visited.contains(nextDot)) {
                    visited.add(nextDot);
                    queue.add(nextDot);
                }
            }
        }
        
        // If all dots are visited, the game is over
        return visited.size() == dots.size();
    }
    
    private double calculateDistance(Point2D p1, Point2D p2) {
        return p1.distance(p2);
    }
    
    private void updateStatusMessage() {
        String message = currentPlayer + " player's turn";
        if (selectedDot != null) {
            message += " - Dot selected, choose second dot";
        }
        message += " | Blue Score: " + String.format("%.2f", blueScore) + 
                  " | Red Score: " + String.format("%.2f", redScore);
        controlPanel.updateStatusLabel(message);
    }
    
    public void saveGame() {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("game_save.dat"))) {
            // Save game state
            out.writeInt(numberOfDots);
            out.writeObject(dots);
            out.writeObject(lines);
            out.writeBoolean(isPlayerOneTurn);
            out.writeDouble(blueScore);
            out.writeDouble(redScore);
            JOptionPane.showMessageDialog(this, "Game saved successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "Error saving game: " + e.getMessage(), 
                "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @SuppressWarnings("unchecked")
    public void loadGame() {
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("game_save.dat"))) {
            // Load game state
            numberOfDots = in.readInt();
            dots = (List<Point2D>) in.readObject();
            lines = (List<Line>) in.readObject();
            isPlayerOneTurn = in.readBoolean();
            blueScore = in.readDouble();
            redScore = in.readDouble();
            currentPlayer = isPlayerOneTurn ? "Blue" : "Red";
            selectedDot = null;
            configPanel.updateDotsField(numberOfDots);
            updateStatusMessage();
            canvas.repaint();
            JOptionPane.showMessageDialog(this, "Game loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, 
                "Error loading game: " + e.getMessage(), 
                "Load Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Getters for access from other classes
    public List<Point2D> getDots() {
        return dots;
    }
    
    public List<Line> getLines() {
        return lines;
    }
    
    public Point2D getSelectedDot() {
        return selectedDot;
    }

    
    
    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DotConnectGame());
    }
}