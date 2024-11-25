package com.example;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Define the Node class for each node in the tree
class BookNode {
    int isbn;
    String bookTitle;
    public int x = 0;
    public int y = 0;
    BookNode left;
    BookNode right;

    public BookNode(int isbn, String bookTitle) {
        this.isbn = isbn;
        this.bookTitle = bookTitle;
        this.left = null;
        this.right = null;
    }
}

public class BinaryTree {
    // Root of the binary tree
    private BookNode root;
    public JPanel panel;

    // Constructor to create a new binary tree with no nodes
    public BinaryTree(JPanel panel) {
        this.root = null;
        this.panel = panel;
    }

    // Method to insert a node into the binary tree
    public void insert(int index, String value) {
        if (root == null) {
            root = new BookNode(index, value);
            insertUI(value);
        } else {
            _insert(index, value, root);
        }
    }

    // Helper method for inserting a node recursively
    private void _insert(int data, String value, BookNode currentNode) {
        if (data < currentNode.isbn) {
            if (currentNode.left == null) {
                currentNode.left = new BookNode(data, value);
            } else {
                _insert(data, value, currentNode.left);
                insertUI(value);
            }
        } else if (data > currentNode.isbn) {
            if (currentNode.right == null) {
                currentNode.right = new BookNode(data, value);
            } else {
                _insert(data, value, currentNode.right);
                insertUI(value);
            }
        }
    }
    
    public void insertUI(String bookName){
        
        JPanel pan = new JPanel();
        
        pan.add(new JLabel(bookName));

        JButton rmButton = new JButton("remove");
        rmButton.addActionListener((ActionEvent arg0) -> {
            panel.remove(pan);
        });

        pan.add(rmButton);
        panel.add(pan);
    
        panel.revalidate();
        panel.repaint();
        
        System.out.println("si");
    }

    // Method to print the binary tree in-order
    public String printInOrder() {
        String text = "";
        if (root != null) {
            text = text + _printInOrder(root);
        }
        return text;
    }

    // Helper method for printing the binary tree in-order recursively
    private String _printInOrder(BookNode currentNode) {
        String text = "";
        if (currentNode != null) {
            text = text + _printInOrder(currentNode.left);
            // System.out.print(currentNode.value + " ");
            text = text + currentNode.bookTitle + " ";
            text = text + _printInOrder(currentNode.right);
        }
        return text;
    }

    // Method to print the binary tree pre-order
    public String printPreOrder() {
        String text = "";
        if (root != null) {
            text = text + _printPreOrder(root);
        }

        return text;
    }

    // Helper method for printing the binary tree pre-order recursively
    private String _printPreOrder(BookNode currentNode) {
        String text = "";
        if (currentNode != null) {
            // System.out.print(currentNode.value + " ");
            text = text + currentNode.bookTitle;
            text = text + _printPreOrder(currentNode.left);
            text = text + _printPreOrder(currentNode.right);
        }
        return text;
    }

    // Method to print the binary tree post-order
    public String printPostOrder() {
        String text = "";
        if (root != null) {
            text = text + _printPostOrder(root);
        }
        return text;
    }

    // Helper method for printing the binary tree post-order recursively
    private String _printPostOrder(BookNode currentNode) {
        String text = "";
        if (currentNode != null) {
            text = text + _printPostOrder(currentNode.left);
            text = text + _printPostOrder(currentNode.right);
            System.out.print(currentNode.bookTitle + " ");
            text = text + currentNode.bookTitle;
        }
        return text;
    }

    // Method to get the height of the binary tree
    public int getHeight() {
        return _getHeight(root);
    }

    // Helper method for getting the height of the binary tree recursively
    private int _getHeight(BookNode currentNode) {
        if (currentNode == null) {
            return 0;
        } else {
            int leftHeight = _getHeight(currentNode.left);
            int rightHeight = _getHeight(currentNode.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    // Method to check if the binary tree is balanced
    public boolean isBalanced() {
        return _isBalanced(root) != -1;
    }

    // Helper method for checking if the binary tree is balanced recursively
    private int _isBalanced(BookNode currentNode) {
        if (currentNode == null) {
            return 0;
        } else {
            int leftHeight = _getHeight(currentNode.left);
            int rightHeight = _getHeight(currentNode.right);
            if (Math.abs(leftHeight - rightHeight) > 1) {
                return -1; // Unbalanced
            }
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    // Method to perform Breadth-First Search (BFS)
    public String amplitud() {
        String text = "";
        if (root != null) {
            Queue<BookNode> queue = new LinkedList<>();
            queue.add(root);
            // System.out.println(root.index);
            text = text + root.bookTitle;
            while (!queue.isEmpty()) {
                BookNode currentNode = queue.poll();
                // System.out.println(currentNode.data + " "); ????????????????????
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                    // System.out.print(currentNode.left.value);
                    text = text + currentNode.left.bookTitle;
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                    System.out.println(currentNode.right.bookTitle);
                    text = text + currentNode.right.bookTitle;
                }
            }
        }
        return text;
    }

    // Method to remove a node from the binary tree
    public void remove(int data) {
        amplitud();
        root = _removeNode(root, data);
        amplitud();
    }

    // Helper method for removing a node recursively
    private BookNode _removeNode(BookNode currentNode, int data) {
        if (currentNode == null)
            return currentNode;

        if (data < currentNode.isbn)
            currentNode.left = _removeNode(currentNode.left, data);

        else if (data > currentNode.isbn)
            currentNode.right = _removeNode(currentNode.right, data);

        else { // Node with the given data is found
            if (currentNode.left == null && currentNode.right == null) {
                return null; // No children to remove, set current node as null
            } else if (currentNode.left == null) {
                return currentNode.right; // Only right child exists, return it
            } else if (currentNode.right == null) {
                return currentNode.left; // Only left child exists, return it
            }

            // Node with two children, find its replacement
            BookNode replacement = _findMin(currentNode.right);
            currentNode.isbn = replacement.isbn;
            currentNode.right = _removeNode(replacement, currentNode.isbn); // Recursively remove replacement
        }
        return currentNode;
    }

        // Helper method to find the node with minimum value in a subtree
        private BookNode _findMin(BookNode node) {
            while (node.left != null)
                node = node.left;  // Move left until we reach the smallest value
            return node;
        }


        

            // Method to find a node in the binary tree by its data value
    public BookNode findNode(int targetData) {
        return _findNode(root, targetData);  // Call the helper method
    }

    // Helper method for finding a node recursively
    private BookNode _findNode(BookNode currentNode, int targetData) {
        if (currentNode == null) {  // Base case: empty tree or reached leaf node
            return null;
        }
        if (targetData < currentNode.isbn) {  // Search in the left subtree
            return _findNode(currentNode.left, targetData);
        } else if (targetData > currentNode.isbn) {  // Search in the right subtree
            return _findNode(currentNode.right, targetData);
        } else {
            return currentNode;  // Found the node with matching data value
        }
    }
}
