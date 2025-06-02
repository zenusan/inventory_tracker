package views;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import models.Category;
import models.Product;

public class Home extends JFrame {
    
    // Components ----------------------------------------------------
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JToolBar toolbar;
    private JScrollPane scrollPane;
    private JPanel categoriesPanel;
    private JPanel productsPanel;

    private JButton addRecordButton;
    private JButton addProductButton;
    private JButton addCategoryButton;
    private JButton viewToggleButton;

    // Data 
    private List<Category> categories = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private boolean showingRecords = true; // Start showing records (categories)

    // Constructor ----------------------------------------------------
    public Home() {
        setTitle("Inventory Tracker");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        loadDummyData();
        updateView();
        setVisible(true);
    }

    // Initialize Components ------------------------------------------
    private void initComponents() {
        mainPanel = new JPanel(new BorderLayout());

        toolbar = new JToolBar();
        toolbar.setFloatable(false);

        addRecordButton = new JButton("Add Record");
        addProductButton = new JButton("Add Product");
        addCategoryButton = new JButton("Add Category");

        toolbar.add(addRecordButton);
        toolbar.add(addProductButton);
        toolbar.add(addCategoryButton);

        toolbar.add(Box.createHorizontalGlue());
        viewToggleButton = new JButton("View Records");
        viewToggleButton.addActionListener(e -> {
            if ("View Records".equals(viewToggleButton.getText())) {
                viewToggleButton.setText("View Products");
                showingRecords = true;
            } else {
                viewToggleButton.setText("View Records");
                showingRecords = false;
            }
            updateView();
        });
        toolbar.add(viewToggleButton);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        
        // Create categories panel
        categoriesPanel = new JPanel();
        categoriesPanel.setLayout(new BoxLayout(categoriesPanel, BoxLayout.Y_AXIS));
        categoriesPanel.setBackground(Color.WHITE);
        
        // Create products panel
        productsPanel = new JPanel();
        productsPanel.setLayout(new BorderLayout());
        productsPanel.setBackground(Color.WHITE);
        
        // Create scroll pane
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        
        mainPanel.add(toolbar, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }
    
    private void loadDummyData() {
        // Create categories
        Category noCategory = new Category("No Category", "Default category for uncategorized products");
        Category electronics = new Category("Electronics", "Electronic devices and accessories");
        Category groceries = new Category("Groceries", "Food and household items");
        Category clothing = new Category("Clothing", "Apparel and accessories");
        
        categories.add(noCategory);
        categories.add(electronics);
        categories.add(groceries);
        categories.add(clothing);
        
        // Create products
        Product laptop = new Product("Laptop", 5, 999.99, 0.1, "Electronics");
        Product phone = new Product("Smartphone", 10, 599.99, 0.05, "Electronics");
        Product tablet = new Product("Tablet", 8, 399.99, 0.15, "Electronics");
        
        Product bread = new Product("Bread", 20, 3.99, 0, "Groceries");
        Product milk = new Product("Milk", 15, 2.49, 0, "Groceries");
        Product eggs = new Product("Eggs", 30, 4.99, 0.1, "Groceries");
        
        Product tshirt = new Product("T-Shirt", 50, 19.99, 0.2, "Clothing");
        Product jeans = new Product("Jeans", 25, 39.99, 0.1, "Clothing");
        
        Product misc = new Product("Miscellaneous Item", 3, 9.99, 0, "No Category");
        
        // Add products to categories
        electronics.addProduct(laptop);
        electronics.addProduct(phone);
        electronics.addProduct(tablet);
        
        groceries.addProduct(bread);
        groceries.addProduct(milk);
        groceries.addProduct(eggs);
        
        clothing.addProduct(tshirt);
        clothing.addProduct(jeans);
        
        noCategory.addProduct(misc);
        
        // Add products to master list
        products.add(laptop);
        products.add(phone);
        products.add(tablet);
        products.add(bread);
        products.add(milk);
        products.add(eggs);
        products.add(tshirt);
        products.add(jeans);
        products.add(misc);
    }
    
    private void updateView() {
        if (showingRecords) {
            // Show categories with their products
            updateCategoriesView();
            scrollPane.setViewportView(categoriesPanel);
        } else {
            // Show flat list of all products (not implemented yet)
            JLabel notImplemented = new JLabel("Products view not implemented yet", SwingConstants.CENTER);
            notImplemented.setFont(new Font("Arial", Font.BOLD, 16));
            scrollPane.setViewportView(notImplemented);
        }
    }
    
    private void updateCategoriesView() {
        categoriesPanel.removeAll();
        
        // Add each category panel
        for (Category category : categories) {
            CategoryPanel categoryPanel = new CategoryPanel(category);
            
            // Set the maximum width while allowing height to adjust as needed
            categoryPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, categoryPanel.getPreferredSize().height));
            
            categoriesPanel.add(categoryPanel);
            categoriesPanel.add(Box.createVerticalStrut(10)); // Spacing between categories
        }
        
        // Add empty space at the bottom to push everything up
        categoriesPanel.add(Box.createVerticalGlue());
        
        categoriesPanel.revalidate();
        categoriesPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Home();
        });
    }
}