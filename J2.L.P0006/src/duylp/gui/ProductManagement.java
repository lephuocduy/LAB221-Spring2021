/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duylp.gui;

import duylp.dao.CategoryDAO;
import duylp.dao.ProductDAO;
import duylp.dtos.Category;
import duylp.dtos.Product;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LP D
 */
public class ProductManagement extends javax.swing.JDialog {

    /**
     * Creates new form ProductManagement
     */
    ArrayList<Category> listCategory;
    ArrayList<Product> listProduct;
    boolean addNewCategory = false;
    boolean addNewProduct = false;
    int indexCategory = -1;
    int indexProduct = -1;
    DefaultComboBoxModel comboBoxCategoryName = new DefaultComboBoxModel();
    UsersLogin ul;

    public ProductManagement(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ul = (UsersLogin) parent;
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        loadCategory();
        resetStatusCategory();
        loadProduct();
        resetStatusProduct();
    }

    void loadCategory() {
        indexCategory = -1;
        try {
            listCategory = CategoryDAO.getsCategory();
            DefaultTableModel modelCategory = (DefaultTableModel) tblCategory.getModel();
            modelCategory.getDataVector().removeAllElements();
            for (Category category : listCategory) {
                Vector row = new Vector();
                row.add(category.getCategoryID());
                row.add(category.getCategoryName());
                row.add(category.getDescription());
                modelCategory.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error load Category");
        }
    }

    void loadProduct() {
        indexProduct = -1;
        listProduct = new ArrayList<>();
        listCategory = new ArrayList<>();
        comboBoxCategoryName.removeAllElements();
        comboBoxCategoryName.addElement("--Seletion Category Name--");
        try {
            listCategory = CategoryDAO.getsCategory();
            for (Category category : listCategory) {
                comboBoxCategoryName.addElement(category.getCategoryName());
            }
            cbxCategoryName.setModel(comboBoxCategoryName);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error load product");
        }
        try {
            listProduct = ProductDAO.getsProduct();
            DefaultTableModel modelProduct = (DefaultTableModel) tblProduct.getModel();
            modelProduct.getDataVector().removeAllElements();
            for (Product product : listProduct) {
                Vector row = new Vector();
                row.add(product.getProductID());
                row.add(product.getProductName());
                row.add(product.getUnit());
                row.add(product.getQuantity());
                row.add(product.getPrice());
                row.add(product.getCategoryID());
                modelProduct.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error load Product");
        }
    }

    void whenClickCategory() {
        txtCategoryName.setEnabled(true);
        txtDescription.setEnabled(true);
        btnSaveCategory.setEnabled(true);
        btnDeleteCategory.setEnabled(true);
    }

    void whenClickProduct() {
        txtProductName.setEnabled(true);
        cbxCategoryName.setEnabled(true);
        txtUnit.setEnabled(true);
        txtQuantity.setEnabled(true);
        txtPrice.setEnabled(true);
        btnSaveProduct.setEnabled(true);
        btnDeleteProduct.setEnabled(true);
    }

    void whenAddNewCategory() {
        txtCategoryID.setText("");
        txtCategoryName.setText("");
        txtDescription.setText("");
        txtCategoryID.setEnabled(true);
        txtCategoryName.setEnabled(true);
        txtDescription.setEnabled(true);
        btnSaveCategory.setEnabled(true);
        btnDeleteCategory.setEnabled(false);
        tblCategory.setEnabled(false);
        tblCategory.clearSelection();
    }

    void whenAddNewProduct() {
        txtProductID.setText("");
        txtProductName.setText("");
        cbxCategoryName.setSelectedIndex(0);
        txtUnit.setText("");
        txtQuantity.setText("");
        txtPrice.setText("");
        txtProductID.setEnabled(true);
        txtProductName.setEnabled(true);
        cbxCategoryName.setEnabled(true);
        txtUnit.setEnabled(true);
        txtQuantity.setEnabled(true);
        txtPrice.setEnabled(true);
        btnSaveProduct.setEnabled(true);
        btnDeleteProduct.setEnabled(false);
        tblProduct.setEnabled(false);
        tblProduct.clearSelection();
    }

    void resetStatusCategory() {
        txtCategoryID.setText("");
        txtCategoryName.setText("");
        txtDescription.setText("");
        tblCategory.clearSelection();
        txtCategoryID.setEnabled(false);
        txtCategoryName.setEnabled(false);
        txtDescription.setEnabled(false);
        btnAddNewCategory.setEnabled(true);
        btnSaveCategory.setEnabled(false);
        btnDeleteCategory.setEnabled(false);
        tblCategory.setEnabled(true);
        loadCategory();
    }

    void resetStatusProduct() {
        txtProductID.setText("");
        txtProductName.setText("");
        cbxCategoryName.setSelectedIndex(0);
        txtUnit.setText("");
        txtQuantity.setText("");
        txtPrice.setText("");
        txtProductID.setEnabled(false);
        txtProductName.setEnabled(false);
        cbxCategoryName.setEnabled(false);
        txtUnit.setEnabled(false);
        txtQuantity.setEnabled(false);
        txtPrice.setEnabled(false);
        btnAddNewProduct.setEnabled(true);
        btnSaveProduct.setEnabled(false);
        btnDeleteProduct.setEnabled(false);
        tblProduct.setEnabled(true);
        loadProduct();
    }

    void saveNewCategory() {
        if (txtCategoryID.getText().trim().isEmpty()
                || txtCategoryName.getText().trim().isEmpty()
                || txtDescription.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Have not entered enough information");
            return;
        } else {
            boolean categoryIDCheck = txtCategoryID.getText().matches("[a-z0-9_-]{1,10}");
            if (categoryIDCheck) {
                String categoryID = txtCategoryID.getText().trim();
                boolean checkCategoryID = true;
                for (int i = 0; i < listCategory.size(); i++) {
                    if (categoryID.equals(listCategory.get(i).getCategoryID())) {
                        checkCategoryID = false;
                    }
                }
                if (checkCategoryID) {
                    String categoryName = txtCategoryName.getText().trim();
                    boolean checkCategoryName = true;
                    for (int i = 0; i < listCategory.size(); i++) {
                        if (categoryName.equals(listCategory.get(i).getCategoryName())) {
                            checkCategoryName = false;
                        }
                    }
                    if (checkCategoryName) {
                        String description = txtDescription.getText().trim();
                        try {
                            if (categoryID.length() > 10 || categoryName.length() > 50 || description.length() > 50) {
                                if (categoryID.length() > 10) {
                                    JOptionPane.showMessageDialog(null, "CategoryID max length is 10 ");
                                } else if (categoryName.length() > 50) {
                                    JOptionPane.showMessageDialog(null, "CategoryName max length is 50");
                                } else if (description.length() > 50) {
                                    JOptionPane.showMessageDialog(null, "Description max length is 50");
                                }
                            } else {
                                CategoryDAO.insertCategory(categoryID, categoryName, description);
                                addNewCategory = false;
                                btnAddNewCategory.setText("Add New");
                                resetStatusCategory();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, " Error save data");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Category Name already exists");
                        txtCategoryName.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ID already exists");
                    txtCategoryID.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "The ID cannot contain special characters");
                txtCategoryID.setText("");
            }
        }
    }

    void saveNewProduct() {
        if (txtProductID.getText().trim().isEmpty()
                || txtProductName.getText().trim().isEmpty()
                || cbxCategoryName.getSelectedIndex() == 0
                || txtUnit.getText().trim().isEmpty()
                || txtQuantity.getText().trim().isEmpty()
                || txtPrice.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Have not entered enough information");
            return;
        } else {
            boolean productIDCheck = txtProductID.getText().matches("[a-z0-9_-]{1,10}");
            if (productIDCheck) {
                String productID = txtProductID.getText().trim();
                boolean checkProductID = true;
                for (int i = 0; i < listProduct.size(); i++) {
                    if (productID.equals(listProduct.get(i).getProductID())) {
                        checkProductID = false;
                    }
                }
                if (checkProductID) {
                    String productName = txtProductName.getText().trim();
                    String unit = txtUnit.getText().trim();
                    boolean matchQuantity = txtQuantity.getText().matches("\\d{1,2}");
                    if (matchQuantity && Integer.parseInt(txtQuantity.getText()) > 0) {
                        int quantity = Integer.parseInt(txtQuantity.getText());
                        boolean matchPrice = txtPrice.getText().matches("\\d+(\\.\\d+)?");
                        if (matchPrice && Float.parseFloat(txtPrice.getText()) > 0) {
                            float price = Float.parseFloat(txtPrice.getText());
                            String categoryID = null;
                            for (int i = 0; i < listCategory.size(); i++) {
                                String checkCBX = listCategory.get(i).getCategoryName();
                                if (checkCBX.equals(cbxCategoryName.getSelectedItem())) {
                                    categoryID = listCategory.get(i).getCategoryID();
                                }
                            }
                            try {
                                if (productID.length() > 10
                                        || productName.length() > 50
                                        || unit.length() > 50) {
                                    if (productID.length() > 10) {
                                        JOptionPane.showMessageDialog(null, "ProductID max length is 10 ");
                                    } else if (productName.length() > 50) {
                                        JOptionPane.showMessageDialog(null, "ProductName max length is 50");
                                    } else if (unit.length() > 50) {
                                        JOptionPane.showMessageDialog(null, "Unit max length is 50");
                                    }

                                } else {
                                    ProductDAO.insertProduct(productID, productName, unit, price, quantity, categoryID);
                                    addNewProduct = false;
                                    btnAddNewProduct.setText("Add New");
                                    resetStatusProduct();
                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, " Error add new");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Price incorrect value entered");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Quantity incorrect value entered");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ID already exists");
                    txtProductID.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "The ID cannot contain special characters");
                txtProductID.setText("");
            }
        }
    }

    void updateDataCategory() {
        if (txtCategoryName.getText().trim().isEmpty()
                || txtDescription.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Have not entered enough information");
            return;
        } else {
            String categoryID = txtCategoryID.getText().trim();
            String categoryName = txtCategoryName.getText().trim();
            String description = txtDescription.getText().trim();
            try {
                if (categoryID.length() > 10 || categoryName.length() > 50 || description.length() > 50) {
                    if (categoryID.length() > 10) {
                        JOptionPane.showMessageDialog(null, "CategoryID max length is 10 ");
                    } else if (categoryName.length() > 50) {
                        JOptionPane.showMessageDialog(null, "CategoryName max length is 50");
                    } else if (description.length() > 50) {
                        JOptionPane.showMessageDialog(null, "Description max length is 50");
                    }
                } else {
                    if (CategoryDAO.updateCategory(categoryID, categoryName, description) != 0) {
                        JOptionPane.showMessageDialog(null, "Fixed category");
                        if (CategoryDAO.findUseSP(categoryID) != null) {
                            loadProduct();
                            resetStatusProduct();
                        }
                        resetStatusCategory();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, " Error update data");
            }
        }
    }

    void updateDataProduct() {
        if (txtProductName.getText().trim().isEmpty()
                || cbxCategoryName.getSelectedIndex() == 0
                || txtUnit.getText().trim().isEmpty()
                || txtQuantity.getText().trim().isEmpty()
                || txtPrice.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Have not entered enough information");
            return;
        } else {
            String productID = txtProductID.getText().trim();
            String productName = txtProductName.getText().trim();
            String unit = txtUnit.getText().trim();
            boolean matchQuantity = txtQuantity.getText().matches("\\d{1,2}");
            if (matchQuantity && Integer.parseInt(txtQuantity.getText()) > 0) {
                int quantity = Integer.parseInt(txtQuantity.getText());
                boolean matchPrice = txtPrice.getText().matches("\\d+(\\.\\d+)?");
                if (matchPrice && Float.parseFloat(txtPrice.getText()) > 0) {
                    float price = Float.parseFloat(txtPrice.getText());
                    String categoryID = null;
                    for (int i = 0; i < listCategory.size(); i++) {
                        String checkCBX = listCategory.get(i).getCategoryName();
                        if (checkCBX.equals(cbxCategoryName.getSelectedItem())) {
                            categoryID = listCategory.get(i).getCategoryID();
                        }
                    }
                    try {
                        if (productID.length() > 10
                                || productName.length() > 50
                                || unit.length() > 50) {
                            if (productID.length() > 10) {
                                JOptionPane.showMessageDialog(null, "ProductID max length is 10 ");
                            } else if (productName.length() > 50) {
                                JOptionPane.showMessageDialog(null, "ProductName max length is 50");
                            } else if (unit.length() > 50) {
                                JOptionPane.showMessageDialog(null, "Unit max length is 50");
                            }
                        } else {
                            ProductDAO.updateProduct(productID, productName, unit, price, quantity, categoryID);
                            resetStatusProduct();
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, " Error update");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Price incorrect value entered");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Quantity incorrect value entered");
                return;
            }
        }
    }

    boolean checkUpdateStatusCategory() {
        boolean result = true;
        if (indexCategory >= 0) {
            String categoryNameCheck = txtCategoryName.getText();
            String descriptionCheck = txtDescription.getText();
            if (!categoryNameCheck.equals(listCategory.get(indexCategory).getCategoryName())
                    || !descriptionCheck.equals(listCategory.get(indexCategory).getDescription())) {
                result = false;
            }
        } else {
            result = true;
        }
        return result;
    }

    boolean checkUpdateStatusProduct() {
        boolean result = true;
        if (indexProduct >= 0) {
            String productNameCheck = txtProductName.getText();
            String cbxCategoryNameCheck = cbxCategoryName.getSelectedItem() + "";
            String unitCheck = txtUnit.getText();
            boolean matchQuantity = txtQuantity.getText().matches("\\d{1,2}");
            if (!txtQuantity.getText().isEmpty() && matchQuantity && Integer.parseInt(txtQuantity.getText()) > 0) {
                int quantityCheck = Integer.parseInt(txtQuantity.getText());
                boolean matchPrice = txtPrice.getText().matches("\\d+(\\.\\d+)?");
                if (!txtPrice.getText().isEmpty() && matchPrice && Float.parseFloat(txtPrice.getText()) > 0) {
                    float priceCheck = Float.parseFloat(txtPrice.getText());
                    if (!productNameCheck.equals(listProduct.get(indexProduct).getProductName())
                            || !cbxCategoryNameCheck.equals(listProduct.get(indexProduct).getCategoryName())
                            || !unitCheck.equals(listProduct.get(indexProduct).getUnit())
                            || !(quantityCheck == listProduct.get(indexProduct).getQuantity())
                            || !(priceCheck == listProduct.get(indexProduct).getPrice())) {
                        result = false;
                    }
                } else {
                    result = false;
                }
            } else {
                result = false;
            }
        } else {
            result = true;
        }
        return result;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCategory = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCategoryID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCategoryName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();
        btnAddNewCategory = new javax.swing.JButton();
        btnSaveCategory = new javax.swing.JButton();
        btnDeleteCategory = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtProductID = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtProductName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbxCategoryName = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtUnit = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        btnAddNewProduct = new javax.swing.JButton();
        btnSaveProduct = new javax.swing.JButton();
        btnDeleteProduct = new javax.swing.JButton();
        btnLogOut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Product Management");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(900, 500));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Main part:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 255))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(502, 45));

        tblCategory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Catergory ID", "Category Name", "Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoryMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCategory);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detailed part:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 255))); // NOI18N
        jPanel4.setLayout(null);

        jLabel1.setText("Category ID:");
        jLabel1.setPreferredSize(new java.awt.Dimension(40, 20));
        jPanel4.add(jLabel1);
        jLabel1.setBounds(20, 40, 70, 40);
        jPanel4.add(txtCategoryID);
        txtCategoryID.setBounds(105, 40, 190, 40);

        jLabel2.setText("Category Name:");
        jPanel4.add(jLabel2);
        jLabel2.setBounds(20, 120, 80, 40);
        jPanel4.add(txtCategoryName);
        txtCategoryName.setBounds(105, 120, 190, 40);

        jLabel3.setText("Description:");
        jPanel4.add(jLabel3);
        jLabel3.setBounds(20, 200, 70, 40);
        jPanel4.add(txtDescription);
        txtDescription.setBounds(105, 200, 190, 40);

        btnAddNewCategory.setText("Add New");
        btnAddNewCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewCategoryActionPerformed(evt);
            }
        });
        jPanel4.add(btnAddNewCategory);
        btnAddNewCategory.setBounds(20, 290, 90, 40);

        btnSaveCategory.setText("Save");
        btnSaveCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveCategoryActionPerformed(evt);
            }
        });
        jPanel4.add(btnSaveCategory);
        btnSaveCategory.setBounds(140, 290, 70, 40);

        btnDeleteCategory.setText("Delete");
        btnDeleteCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCategoryActionPerformed(evt);
            }
        });
        jPanel4.add(btnDeleteCategory);
        btnDeleteCategory.setBounds(240, 290, 70, 40);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Category", jPanel1);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Main part:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 255))); // NOI18N

        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product Name", "Unit", "Quantity", "Price", "Category ID"
            }
        ));
        tblProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblProduct);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detailed part:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 255))); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(113, 193));
        jPanel6.setLayout(new java.awt.GridLayout(6, 2, -60, 10));

        jLabel4.setText("Product ID:");
        jPanel6.add(jLabel4);
        jPanel6.add(txtProductID);

        jLabel5.setText("Product Name:");
        jPanel6.add(jLabel5);
        jPanel6.add(txtProductName);

        jLabel6.setText("Category Name:");
        jPanel6.add(jLabel6);

        cbxCategoryName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10" }));
        jPanel6.add(cbxCategoryName);

        jLabel7.setText("Unit:");
        jPanel6.add(jLabel7);
        jPanel6.add(txtUnit);

        jLabel8.setText("Quantity:");
        jPanel6.add(jLabel8);
        jPanel6.add(txtQuantity);

        jLabel9.setText("Price:");
        jPanel6.add(jLabel9);
        jPanel6.add(txtPrice);

        btnAddNewProduct.setText("Add New");
        btnAddNewProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewProductActionPerformed(evt);
            }
        });

        btnSaveProduct.setText("Save");
        btnSaveProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveProductActionPerformed(evt);
            }
        });

        btnDeleteProduct.setText("Delete");
        btnDeleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAddNewProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnSaveProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnDeleteProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddNewProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSaveProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDeleteProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 3, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Product", jPanel2);

        getContentPane().add(jTabbedPane1);
        jTabbedPane1.setBounds(10, 60, 870, 395);

        btnLogOut.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnLogOut.setForeground(new java.awt.Color(255, 0, 0));
        btnLogOut.setText("Log Out");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogOut);
        btnLogOut.setBounds(780, 20, 100, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoryMouseClicked
        // TODO add your handling code here:
        if (addNewCategory) {
            JOptionPane.showMessageDialog(null, "Please complete the add new function");
            return;
        } else if (!checkUpdateStatusCategory()) {
            tblCategory.setRowSelectionInterval(indexCategory, indexCategory);
            JOptionPane.showMessageDialog(null, "Please complete the update function");
            return;
        } else {
            whenClickCategory();
            indexCategory = tblCategory.getSelectedRow();
            if (indexCategory >= 0) {
                txtCategoryID.setText(listCategory.get(indexCategory).getCategoryID());
                txtCategoryName.setText(listCategory.get(indexCategory).getCategoryName());
                txtDescription.setText(listCategory.get(indexCategory).getDescription());
            } else {
                return;
            }
        }
    }//GEN-LAST:event_tblCategoryMouseClicked

    private void btnAddNewCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewCategoryActionPerformed
        // TODO add your handling code here:
        if (!checkUpdateStatusCategory() && addNewCategory == false) {
            JOptionPane.showMessageDialog(null, "Please complete the update function");
            return;
        } else {
            if (addNewCategory == false) {
                addNewCategory = true;
                whenAddNewCategory();
                btnAddNewCategory.setText("Cancel");
            } else if (addNewCategory) {
                addNewCategory = false;
                btnAddNewCategory.setText("Add New");
                resetStatusCategory();
            }
        }
    }//GEN-LAST:event_btnAddNewCategoryActionPerformed

    private void btnSaveCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveCategoryActionPerformed
        // TODO add your handling code here:
        if (addNewCategory) {
            saveNewCategory();
            loadProduct();
        } else if (!addNewCategory) {
            Object[] options = {"Yes", "No"};
            int n = JOptionPane.showOptionDialog(null, "Do you want to UPDATE data?", "UPDATE DATA", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if (n == JOptionPane.YES_OPTION) {
                updateDataCategory();
                loadProduct();
            } else if (n == JOptionPane.NO_OPTION) {
                resetStatusCategory();
            }
        }
    }//GEN-LAST:event_btnSaveCategoryActionPerformed

    private void btnDeleteCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCategoryActionPerformed
        // TODO add your handling code here:
        if (!checkUpdateStatusCategory()) {
            JOptionPane.showMessageDialog(null, "Please complete the update function");
            return;
        } else {
            indexCategory = tblCategory.getSelectedRow();
            try {
                Object[] options = {"Yes", "No"};
                int n = JOptionPane.showOptionDialog(null, "Do you want to DELETE data?", "DELETE DATA", JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if (n == JOptionPane.YES_OPTION) {
                    if (CategoryDAO.findUseSP(listCategory.get(indexCategory).getCategoryID()) == null) {
                        CategoryDAO.deleteCategory(listCategory.get(indexCategory).getCategoryID());
                        resetStatusCategory();
                        tblCategory.updateUI();
                        resetStatusProduct();
                        loadProduct();
                    } else {
                        JOptionPane.showMessageDialog(null, "the used category cannot be deleted");
                        resetStatusCategory();
                    }
                } else if (n == JOptionPane.NO_OPTION) {
                    resetStatusCategory();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "error delete data");
            }
        }
    }//GEN-LAST:event_btnDeleteCategoryActionPerformed

    private void tblProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductMouseClicked
        // TODO add your handling code here:
        if (addNewProduct) {
            JOptionPane.showMessageDialog(null, "Add New function is not completed");
            return;
        } else if (!checkUpdateStatusProduct()) {
            tblProduct.setRowSelectionInterval(indexProduct, indexProduct);
            JOptionPane.showMessageDialog(null, "UPDATE function is not completed");
            return;
        } else {
            whenClickProduct();
            indexProduct = tblProduct.getSelectedRow();
            if (indexProduct >= 0) {
                txtProductID.setText(listProduct.get(indexProduct).getProductID());
                txtProductName.setText(listProduct.get(indexProduct).getProductName());
                txtUnit.setText(listProduct.get(indexProduct).getUnit());
                txtQuantity.setText("" + listProduct.get(indexProduct).getQuantity());
                txtPrice.setText("" + listProduct.get(indexProduct).getPrice());
                comboBoxCategoryName.setSelectedItem(listProduct.get(indexProduct).getCategoryName());
            } else {
                return;
            }
        }
    }//GEN-LAST:event_tblProductMouseClicked

    private void btnAddNewProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewProductActionPerformed
        // TODO add your handling code here:
        loadProduct();
        if (!checkUpdateStatusProduct() && addNewProduct == false) {
            JOptionPane.showMessageDialog(null, "finish UPDATE");
            return;
        } else {
            if (addNewProduct == false) {
                addNewProduct = true;
                whenAddNewProduct();
                btnAddNewProduct.setText("Cancel");
            } else if (addNewProduct) {
                addNewProduct = false;
                btnAddNewProduct.setText("Add New");
                resetStatusProduct();
            }
        }
    }//GEN-LAST:event_btnAddNewProductActionPerformed

    private void btnSaveProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveProductActionPerformed
        // TODO add your handling code here:
        if (addNewProduct) {
            saveNewProduct();
        } else if (!addNewProduct) {
            Object[] options = {"Yes", "No"};
            int n = JOptionPane.showOptionDialog(null, "Do you want to UPDATE data?", "UPDATE DATA", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if (n == JOptionPane.YES_OPTION) {
                updateDataProduct();
            } else if (n == JOptionPane.NO_OPTION) {
                resetStatusProduct();
            }
        }
    }//GEN-LAST:event_btnSaveProductActionPerformed

    private void btnDeleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProductActionPerformed
        // TODO add your handling code here:
        if (!checkUpdateStatusProduct()) {
            JOptionPane.showMessageDialog(null, "finish UPDATE");
            return;
        } else {
            indexProduct = tblProduct.getSelectedRow();
            try {
                Object[] options = {"Yes", "No"};
                int n = JOptionPane.showOptionDialog(null, "Do you want to DELETE data?", "DELETE DATA", JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if (n == JOptionPane.YES_OPTION) {
                    ProductDAO.deleteProduct(listProduct.get(indexProduct).getProductID());
                    resetStatusProduct();
                    tblProduct.updateUI();
                } else if (n == JOptionPane.NO_OPTION) {
                    resetStatusProduct();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "error delete data");
            }
        }
    }//GEN-LAST:event_btnDeleteProductActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        // TODO add your handling code here:
        ul.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogOutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProductManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ProductManagement dialog = new ProductManagement(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNewCategory;
    private javax.swing.JButton btnAddNewProduct;
    private javax.swing.JButton btnDeleteCategory;
    private javax.swing.JButton btnDeleteProduct;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnSaveCategory;
    private javax.swing.JButton btnSaveProduct;
    private javax.swing.JComboBox<String> cbxCategoryName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblCategory;
    private javax.swing.JTable tblProduct;
    private javax.swing.JTextField txtCategoryID;
    private javax.swing.JTextField txtCategoryName;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtProductID;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtUnit;
    // End of variables declaration//GEN-END:variables
}
