package com.example;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel {
    private JLabel dotsLabel;
    private JTextField dotsField;
    private JButton newGameBtn;
    private DotConnectGame gameFrame;
    
    public ConfigPanel(DotConnectGame gameFrame) {
        this.gameFrame = gameFrame;
        init();
    }
    
    private void init() {
        // Set up layout
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(BorderFactory.createTitledBorder("Game Configuration"));
        
        // Create components
        dotsLabel = new JLabel("Number of Dots:");
        dotsField = new JTextField(5);
        dotsField.setText("10"); // Default value
        newGameBtn = new JButton("New Game");
        
        // Add action listener
        newGameBtn.addActionListener(e -> {
            try {
                int dots = Integer.parseInt(dotsField.getText());
                if (dots < 3) {
                    JOptionPane.showMessageDialog(gameFrame, 
                        "Please enter at least 3 dots", 
                        "Invalid Input", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                gameFrame.setNumberOfDots(dots);
                gameFrame.newGame();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(gameFrame, 
                    "Please enter a valid number", 
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Add components
        add(dotsLabel);
        add(dotsField);
        add(newGameBtn);
    }
    
    public void updateDotsField(int dots) {
        dotsField.setText(String.valueOf(dots));
    }
}