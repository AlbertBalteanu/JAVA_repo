package com.example;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private JButton saveBtn;
    private JButton loadBtn;
    private JButton exitBtn;
    private JLabel statusLabel;
    private DotConnectGame gameFrame;
    
    public ControlPanel(DotConnectGame gameFrame) {
        this.gameFrame = gameFrame;
        init();
    }
    
    private void init() {
        // Set up layout
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Game Controls"));
        
        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        // Create components
        saveBtn = new JButton("Save");
        loadBtn = new JButton("Load");
        exitBtn = new JButton("Exit");
        statusLabel = new JLabel("Start a new game to begin", JLabel.CENTER);
        
        // Add action listeners
        saveBtn.addActionListener(e -> gameFrame.saveGame());
        loadBtn.addActionListener(e -> gameFrame.loadGame());
        exitBtn.addActionListener(e -> System.exit(0));
        
        // Add components to button panel
        buttonPanel.add(saveBtn);
        buttonPanel.add(loadBtn);
        buttonPanel.add(exitBtn);
        
        // Add components to control panel
        add(buttonPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }
    
    public void updateStatusLabel(String message) {
        statusLabel.setText(message);
    }
}