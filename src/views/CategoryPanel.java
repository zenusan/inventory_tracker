package views;

import models.Category;
import models.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CategoryPanel extends JPanel {
    // Components ----------------------------------------------------
    private Category category;
    private JPanel productListPanel;
    private boolean isExpanded = false;
    private JButton expandButton;
    private JLabel nameLabel;
    private JLabel descriptionLabel;
    private JLabel productCountLabel;

    // Constructor ----------------------------------------------------
    public CategoryPanel(Category category) {
        this.category = category;
        setLayout(new BorderLayout()); // Add this line to set the layout
        initComponents();
    }

    // Initialize Components ------------------------------------------
    public void initComponents() {
        // Main Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.LIGHT_GRAY);

        // Information Panel
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);

        nameLabel = new JLabel(category.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        descriptionLabel = new JLabel(category.getDescription());
        descriptionLabel.setFont(new Font("Arial", Font.ITALIC, 12));

        infoPanel.add(nameLabel);
        infoPanel.add(descriptionLabel);

        // Control Panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        controlPanel.setOpaque(false);

        productCountLabel = new JLabel("Products: " + category.getProducts().size());
        expandButton = new JButton("▼");
        expandButton.setFocusPainted(false);

        expandButton.addActionListener(e -> toggleExpand());

        controlPanel.add(productCountLabel);
        controlPanel.add(expandButton);

        // Assemble Header Panel
        headerPanel.add(infoPanel, BorderLayout.WEST);
        headerPanel.add(controlPanel, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);

        // Product List Panel
        productListPanel = new JPanel();
        productListPanel.setLayout(new BoxLayout(productListPanel, BoxLayout.Y_AXIS));
        productListPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 10)); // Add padding
        productListPanel.setVisible(false);

        // Add a header row for products
        JPanel headerRow = createProductHeaderRow();
        productListPanel.add(headerRow);

        updateProductList();
        add(productListPanel, BorderLayout.CENTER);
    }

    // Added a header row for products
    private JPanel createProductHeaderRow() {
        JPanel headerRow = new JPanel(new GridLayout(1, 5, 10, 0));
        headerRow.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        Font headerFont = new Font("Arial", Font.BOLD, 12);
        
        JLabel nameHeader = new JLabel("Name");
        JLabel qtyHeader = new JLabel("Quantity");
        JLabel priceHeader = new JLabel("Price");
        JLabel discountHeader = new JLabel("Discount");
        JLabel finalHeader = new JLabel("Final Price");
        
        nameHeader.setFont(headerFont);
        qtyHeader.setFont(headerFont);
        priceHeader.setFont(headerFont);
        discountHeader.setFont(headerFont);
        finalHeader.setFont(headerFont);
        
        headerRow.add(nameHeader);
        headerRow.add(qtyHeader);
        headerRow.add(priceHeader);
        headerRow.add(discountHeader);
        headerRow.add(finalHeader);
        
        return headerRow;
    }

    // Helpers -----------------------------------------------------
    private void toggleExpand() {
        isExpanded = !isExpanded;
        expandButton.setText(isExpanded ? "▲" : "▼");
        productListPanel.setVisible(isExpanded);
        revalidate();
        repaint();
    }

    public void updateProductList() {
        // Remove all but the header row
        while (productListPanel.getComponentCount() > 1) {
            productListPanel.remove(1);
        }
        
        List<Product> products = category.getProducts();

        if (products.isEmpty()) {
            JLabel emptyLabel = new JLabel("No products in this category.");
            emptyLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            productListPanel.add(emptyLabel);
        } else {
            for (Product product : products) {
                productListPanel.add(new ProductPanel(product));
            }
        }

        productCountLabel.setText("Products: " + products.size());
        revalidate();
        repaint();
    }

    public void updateCategory(Category updatedCategory) {
        this.category = updatedCategory;
        nameLabel.setText(category.getName());
        descriptionLabel.setText(category.getDescription());
        updateProductList();
    }
}