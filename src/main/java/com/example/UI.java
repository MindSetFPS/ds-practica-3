package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UI {
    JScrollPane scrollPane = new JScrollPane();
    JPanel verticalPanel = new JPanel();
    BinaryTree bt = new BinaryTree(verticalPanel);

    public void create() {
        // Create the frame (window)
        JFrame frame = new JFrame("My GUI Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold other components
        JPanel panel = new JPanel();
        JPanel searchPanel = new JPanel();
        JPanel inputPanel = new JPanel();
        verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, 
              verticalPanel, inputPanel); // This example creates a horizontal split

        panel.setLayout(new FlowLayout());

        // Create two text fields
        JLabel bookNameLabel = new JLabel("Name:");
        JTextField bookNameTextField = new JTextField(8);
        JLabel ISBNLabel = new JLabel("ISBN:");
        JTextField ISBNTextField = new JTextField(13);

        scrollPane.getViewport().add(panel);

        // Add the text fields to the panel
        inputPanel.add(bookNameLabel);
        inputPanel.add(bookNameTextField);
        inputPanel.add(ISBNLabel);
        inputPanel.add(ISBNTextField);

        // Create a button and add it to the panel
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener((ActionEvent arg0) -> {
            bt.insert(
                    Integer.parseInt(ISBNTextField.getText()),
                    bookNameTextField.getText());
            ISBNTextField.setText("");
            bookNameTextField.setText("");
        });
        inputPanel.add(btnSubmit);
        
        JTextField searchField = new JTextField(12);
        JButton searchButton = new JButton("buscar");
        JLabel searchResult = new JLabel();

        searchButton.addActionListener((ActionEvent arg0) ->{
            BookNode node = bt.findNode(Integer.parseInt(searchField.getText()));
            searchResult.setText(node.bookTitle);
        });
        


        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(searchResult);
        
        inputPanel.add(searchPanel);
        
        // Add the panel to the frame
        frame.getContentPane().add(splitPane);

        // Set the size of the window and center it on screen
        frame.setSize(400, 100);
        frame.setLocationRelativeTo(null);

        // Make the window visible
        frame.setVisible(true);
    }
}