/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.ItemDAO;
import DAO.supplierDAO;
import DTO.Item;
import DTO.Suppliers;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author truon
 */
public class ItemPanel extends javax.swing.JPanel {

    /**
     * Creates new form ItemPanel
     */
    ArrayList<Item> list;
    public ArrayList<Suppliers> listSupplier;
    boolean addNew = false;
    int index = -1;
    DefaultComboBoxModel comboBoxSupplier = new DefaultComboBoxModel();

    public ItemPanel() {
        initComponents();
        loadItemPanel();
    }

    void loadItemPanel() {
        index = -1;
        list = new ArrayList<>();
        listSupplier = new ArrayList<>();
        comboBoxSupplier.removeAllElements();
        comboBoxSupplier.addElement("--Seletion Supplier--");
        try {
            listSupplier = supplierDAO.getsSupplier();
            for (Suppliers supplier : listSupplier) {
                comboBoxSupplier.addElement(supplier.getSupCode() + "-" + supplier.getSupName());
            }
            cbxSupplier.setModel(comboBoxSupplier);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "loi load supplier");
        }
        try {
            list = ItemDAO.getsItem();
            DefaultTableModel model = (DefaultTableModel) tblItem.getModel();
            model.getDataVector().removeAllElements();
            for (Item item : list) {
                Vector row = new Vector();
                row.add(item.getItemCode());
                row.add(item.getItemName());
                row.add(item.getSupCode() + "-" + item.getSupName());
                row.add(item.getUnit());
                row.add(item.getPrice());
                row.add(item.isSupplying());
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "loi load item ");
        }
    }

    void loadItemPanell() {

        list = new ArrayList<>();

        listSupplier = new ArrayList<>();
        comboBoxSupplier.removeAllElements();
        comboBoxSupplier.addElement("--Seletion Supplier--");
        try {
            list = ItemDAO.getsItem();
            listSupplier = supplierDAO.getsSupplier();
            for (Suppliers supplier : listSupplier) {
                comboBoxSupplier.addElement(supplier.getSupCode() + "-" + supplier.getSupName());
            }
            cbxSupplier.setModel(comboBoxSupplier);
            if (index >= 0) {
                System.out.println("index " + index);
                comboBoxSupplier.setSelectedItem(list.get(index).getSupCode() + "-" + list.get(index).getSupName());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "loi load supplier");
        }
        try {
            list = ItemDAO.getsItem();
            DefaultTableModel model = (DefaultTableModel) tblItem.getModel();
            model.getDataVector().removeAllElements();
            for (Item item : list) {
                Vector row = new Vector();
                row.add(item.getItemCode());
                row.add(item.getItemName());
                row.add(item.getSupCode() + "-" + item.getSupName());
                row.add(item.getUnit());
                row.add(item.getPrice());
                row.add(item.isSupplying());
                model.addRow(row);
            }

            if (index >= 0) {
                tblItem.setRowSelectionInterval(index, index);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "loi load item ");
        }
    }

    void whenClick() {
        txtItemName.setEnabled(true);
        cbxSupplier.setEnabled(true);
        txtUnit.setEnabled(true);
        txtPrice.setEnabled(true);
        rcbSupplying.setEnabled(true);
        btnDelete.setEnabled(true);
        btnSave.setEnabled(true);
    }

    void whenAddNew() {
        //xoa du lieu hien thi
        txtItemCode.setText("");
        txtItemName.setText("");
        cbxSupplier.setSelectedIndex(0);
        txtUnit.setText("");
        txtPrice.setText("");
        rcbSupplying.setSelected(false);
        //tat bat
        txtItemCode.setEnabled(true);
        txtItemName.setEnabled(true);
        cbxSupplier.setEnabled(true);
        txtUnit.setEnabled(true);
        txtPrice.setEnabled(true);
        rcbSupplying.setEnabled(true);
        tblItem.setEnabled(false);
        tblItem.clearSelection();
        btnSave.setEnabled(true);
        btnDelete.setEnabled(false);
    }

    public void resetStatuss() {
        //xoa 
        txtItemCode.setText("");
        txtItemName.setText("");
        cbxSupplier.setSelectedIndex(0);
        txtUnit.setText("");
        txtPrice.setText("");
        rcbSupplying.setSelected(false);
        //tat bat
        txtItemCode.setEnabled(false);
        txtItemName.setEnabled(false);
        cbxSupplier.setEnabled(false);
        txtUnit.setEnabled(false);
        txtPrice.setEnabled(false);
        rcbSupplying.setEnabled(false);
        tblItem.setEnabled(true);
        tblItem.clearSelection();
        btnSave.setEnabled(false);
        btnDelete.setEnabled(false);
        loadItemPanel();
    }

    void saveNew() {
        if (txtItemCode.getText().trim().isEmpty() || txtItemName.getText().trim().isEmpty() || txtPrice.getText().trim().isEmpty() || txtUnit.getText().trim().isEmpty() || cbxSupplier.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "chua nhap du thong tin");
            return;
        } else {
            String itemCode = txtItemCode.getText().trim();
            boolean checkID = true;
            for (int i = 0; i < list.size(); i++) {
                if (itemCode.equals(list.get(i).getItemCode())) {
                    checkID = false;
                }
            }
            if (checkID) {
                String itemName = txtItemName.getText().trim();
                String unit = txtUnit.getText().trim();
                boolean match = txtPrice.getText().matches("\\d+(\\.\\d+)?");
                if (match && Float.parseFloat(txtPrice.getText()) > 0) {
                    float price = Float.parseFloat(txtPrice.getText());
                    boolean supplying = rcbSupplying.isSelected();
                    String supCode = null;
                    for (int i = 0; i < listSupplier.size(); i++) {
                        String checkCBX = listSupplier.get(i).getSupCode() + "-" + listSupplier.get(i).getSupName();
                        if (checkCBX.equals(cbxSupplier.getSelectedItem())) {
                            supCode = listSupplier.get(i).getSupCode();
                        }
                    }
                    try {
                        if (itemCode.length() > 10 || itemName.length() > 50 || unit.length() > 50) {
                            if (itemCode.length() > 10) {
                                JOptionPane.showMessageDialog(null, "id max length is 10 ");
                            } else if (itemName.length() > 50) {
                                JOptionPane.showMessageDialog(null, "name max length is 50");
                            } else if (unit.length() > 50) {
                                JOptionPane.showMessageDialog(null, "unit max length is 50");
                            }

                        } else {
                            ItemDAO.insertItem(itemCode, itemName, unit, price, supplying, supCode);
                            addNew = false;
                            btnAddNew.setText("Add New");
                            resetStatuss();
                        }
                    } catch (Exception re) {
                        JOptionPane.showMessageDialog(null, " loi add new");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "price nhap sai ");
                    txtPrice.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "itemCode da duoc su dung");
                txtItemCode.setText("");
            }
        }
    }

    boolean saveNewLogin() {
        if (txtItemCode.getText().trim().isEmpty() || txtItemName.getText().trim().isEmpty() || txtPrice.getText().trim().isEmpty() || txtUnit.getText().trim().isEmpty() || cbxSupplier.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "chua nhap du thong tin");
            return true;
        } else {
            String itemCode = txtItemCode.getText().trim();
            boolean checkID = true;
            for (int i = 0; i < list.size(); i++) {
                if (itemCode.equals(list.get(i).getItemCode())) {
                    checkID = false;
                }
            }
            if (checkID) {
                String itemName = txtItemName.getText().trim();
                String unit = txtUnit.getText().trim();
                boolean match = txtPrice.getText().matches("\\d+(\\.\\d+)?");
                if (match && Float.parseFloat(txtPrice.getText()) > 0) {
                    float price = Float.parseFloat(txtPrice.getText());
                    boolean supplying = rcbSupplying.isSelected();
                    String supCode = null;
                    for (int i = 0; i < listSupplier.size(); i++) {
                        String checkCBX = listSupplier.get(i).getSupCode() + "-" + listSupplier.get(i).getSupName();
                        if (checkCBX.equals(cbxSupplier.getSelectedItem())) {
                            supCode = listSupplier.get(i).getSupCode();
                        }
                    }
                    try {
                        if (itemCode.length() > 10 || itemName.length() > 50 || unit.length() > 50) {
                            if (itemCode.length() > 10) {
                                JOptionPane.showMessageDialog(null, "id max length is 10 ");
                            } else if (itemName.length() > 50) {
                                JOptionPane.showMessageDialog(null, "name max length is 50");
                            } else if (unit.length() > 50) {
                                JOptionPane.showMessageDialog(null, "unit max length is 50");
                            }
                            return true;
                        } else {
                           return false;
                            
                        }
                    } catch (Exception re) {
                        JOptionPane.showMessageDialog(null, " loi add new");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "price nhap sai ");
                    txtPrice.setText("");
                    return true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "itemCode da duoc su dung");
                txtItemCode.setText("");
                return true;
            }
        }
        return false;
    }

    void update() {
        if (txtItemCode.getText().trim().isEmpty() || txtItemName.getText().trim().isEmpty() || txtPrice.getText().trim().isEmpty() || txtUnit.getText().trim().isEmpty() || cbxSupplier.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "chua nhap du thong tin");
            return;
        } else {
            String itemCode = txtItemCode.getText().trim();
            String itemName = txtItemName.getText().trim();
            String unit = txtUnit.getText().trim();
            boolean match = txtPrice.getText().matches("\\d+(\\.\\d+)?");
            if (match && Float.parseFloat(txtPrice.getText()) > 0) {
                float price = Float.parseFloat(txtPrice.getText());
                boolean supplying = rcbSupplying.isSelected();
                String supCode = null;
                for (int i = 0; i < listSupplier.size(); i++) {
                    String checkCBX = listSupplier.get(i).getSupCode() + "-" + listSupplier.get(i).getSupName();
                    if (checkCBX.equals(cbxSupplier.getSelectedItem())) {
                        supCode = listSupplier.get(i).getSupCode();
                    }
                }
                try {
                    if (itemCode.length() > 10 || itemName.length() > 50 || unit.length() > 50) {
                        if (itemCode.length() > 10) {
                            JOptionPane.showMessageDialog(null, "id max length is 10 ");
                        } else if (itemName.length() > 50) {
                            JOptionPane.showMessageDialog(null, "name max length is 50");
                        } else if (unit.length() > 50) {
                            JOptionPane.showMessageDialog(null, "unit max length is 50");
                        }

                    } else {
                        ItemDAO.updateItem(itemCode, itemName, unit, price, supplying, supCode);
                        resetStatuss();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, " loi update");
                }
            } else {
                JOptionPane.showMessageDialog(null, "price nhap sai ");
                txtPrice.setText("");
                return;
            }
        }
    }

    boolean updateLogin() {
        if (txtItemCode.getText().trim().isEmpty() || txtItemName.getText().trim().isEmpty() || txtPrice.getText().trim().isEmpty() || txtUnit.getText().trim().isEmpty() || cbxSupplier.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "chua nhap du thong tin");
            return true;
        } else {
            String itemCode = txtItemCode.getText().trim();
            String itemName = txtItemName.getText().trim();
            String unit = txtUnit.getText().trim();
            boolean match = txtPrice.getText().matches("\\d+(\\.\\d+)?");
            if (match && Float.parseFloat(txtPrice.getText()) > 0) {
                float price = Float.parseFloat(txtPrice.getText());
                boolean supplying = rcbSupplying.isSelected();
                String supCode = null;
                for (int i = 0; i < listSupplier.size(); i++) {
                    String checkCBX = listSupplier.get(i).getSupCode() + "-" + listSupplier.get(i).getSupName();
                    if (checkCBX.equals(cbxSupplier.getSelectedItem())) {
                        supCode = listSupplier.get(i).getSupCode();
                    }
                }
                try {
                    if (itemCode.length() > 10 || itemName.length() > 50 || unit.length() > 50) {
                        if (itemCode.length() > 10) {
                            JOptionPane.showMessageDialog(null, "id max length is 10 ");
                        } else if (itemName.length() > 50) {
                            JOptionPane.showMessageDialog(null, "name max length is 50");
                        } else if (unit.length() > 50) {
                            JOptionPane.showMessageDialog(null, "unit max length is 50");
                        }
                        return true;
                    } else {
                        return false;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, " loi update");
                }
            } else {
                JOptionPane.showMessageDialog(null, "price nhap sai ");
                txtPrice.setText("");
                return true;
            }
        }
        return false;
    }

    boolean checkUpdateStatus() {
        boolean result = true;
        //System.out.println("253 :"+index);
        if (index >= 0) {
            String nameCheck = txtItemName.getText();
            String suppCheck = cbxSupplier.getSelectedItem() + "";
            String unitCheck = txtUnit.getText();
            boolean match = txtPrice.getText().matches("\\d+(\\.\\d+)?");

            if (!txtPrice.getText().isEmpty() && match && Float.parseFloat(txtPrice.getText()) > 0) {
                float priceCheck = Float.parseFloat(txtPrice.getText());
                boolean supplyCheck = rcbSupplying.isSelected();
                if (!nameCheck.equals(list.get(index).getItemName())
                        || !suppCheck.equals((list.get(index).getSupCode() + "-" + list.get(index).getSupName()))
                        || !unitCheck.equals(list.get(index).getUnit())
                        || !(priceCheck == list.get(index).getPrice())
                        || !(supplyCheck == list.get(index).isSupplying())) {
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

    public void mainCheck1() {
        addNew = false;
        btnAddNew.setText("Add New");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblItem = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtItemCode = new javax.swing.JTextField();
        txtItemName = new javax.swing.JTextField();
        txtUnit = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        cbxSupplier = new javax.swing.JComboBox<>();
        btnAddNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        rcbSupplying = new javax.swing.JCheckBox();

        tblItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Name", "Supplier", "Unit", "Price", " Supply"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblItem.getTableHeader().setReorderingAllowed(false);
        tblItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblItemMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblItemMouseReleased(evt);
            }
        });
        tblItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblItemKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblItem);

        jLabel1.setText("Item Code");

        jLabel2.setText("Item Name");

        jLabel3.setText("Supplier");

        jLabel4.setText("Unit");

        jLabel5.setText("Price");

        jLabel6.setText("Supplying");

        txtItemCode.setEnabled(false);

        txtItemName.setEnabled(false);

        txtUnit.setEnabled(false);

        txtPrice.setEnabled(false);

        cbxSupplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxSupplier.setEnabled(false);

        btnAddNew.setText("Add New");
        btnAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.setEnabled(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.setEnabled(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        rcbSupplying.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAddNew)
                                .addGap(48, 48, 48)
                                .addComponent(btnSave))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(rcbSupplying))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtItemCode, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(txtItemName)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(197, 197, 197)
                                .addComponent(btnDelete))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(142, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 96, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbxSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(rcbSupplying))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddNew)
                    .addComponent(btnSave)
                    .addComponent(btnDelete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItemMouseClicked
        // TODO add your handling code here:         
        if (addNew) {
            JOptionPane.showMessageDialog(null, "Vui long hoan thanh chuc nang ADD NEW");
            return;
        } else if (!checkUpdateStatus()) {
            //System.out.println(index);
            tblItem.setRowSelectionInterval(index, index);
            JOptionPane.showMessageDialog(null, "Vui long hoan thanh chuc nang UPDATE");

            return;
        } else {
            whenClick();
            index = tblItem.getSelectedRow();
            if (index >= 0) {
                txtItemCode.setText(list.get(index).getItemCode());
                txtItemName.setText(list.get(index).getItemName());
                txtUnit.setText(list.get(index).getUnit());
                txtPrice.setText("" + list.get(index).getPrice());
                if (list.get(index).isSupplying()) {
                    rcbSupplying.setSelected(true);
                } else {
                    rcbSupplying.setSelected(false);
                }
                comboBoxSupplier.setSelectedItem(list.get(index).getSupCode() + "-" + list.get(index).getSupName());
            } else {
                return;
            }
        }
    }//GEN-LAST:event_tblItemMouseClicked

    private void btnAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewActionPerformed
        // TODO add your handling code here:      
        if (!checkUpdateStatus() && addNew == false) {
            JOptionPane.showMessageDialog(null, "finish UPDATE");
            return;
        } else {
            if (addNew == false) {
                addNew = true;
                whenAddNew();
                btnAddNew.setText("Cancel");
            } else if (addNew) {
                Object[] options = {"Yes", "No"};
                int n = JOptionPane.showOptionDialog(null, "Do you want to  SAVE?", "CANCEL SAVE", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if (n == JOptionPane.YES_OPTION) {
                    saveNew();
                } else if (n == JOptionPane.NO_OPTION) {
                    addNew = false;
                    btnAddNew.setText("Add New");
                    resetStatuss();
                }
            }
        }
    }//GEN-LAST:event_btnAddNewActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if (!checkUpdateStatus()) {
            JOptionPane.showMessageDialog(null, "finish UPDATE");
            return;
        } else {
            index = tblItem.getSelectedRow();
            try {
                Object[] options = {"Yes", "No"};
                int n = JOptionPane.showOptionDialog(null, "Do you want to DELETE data?", "DELETE DATA", JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if (n == JOptionPane.YES_OPTION) {
                    ItemDAO.deleteItem(list.get(index).getItemCode());
                    resetStatuss();
                    tblItem.updateUI();
                } else if (n == JOptionPane.NO_OPTION) {
                    resetStatuss();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "error delete data");
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if (addNew) {
            saveNew();
        } else if (!addNew) {
            Object[] options = {"Yes", "No"};
            int n = JOptionPane.showOptionDialog(null, "Do you want to UPDATE data?", "UPDATE DATA", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if (n == JOptionPane.YES_OPTION) {
                update();
            } else if (n == JOptionPane.NO_OPTION) {
                resetStatuss();
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void tblItemMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItemMouseReleased
        // TODO add your handling code here:
        int iindexx = tblItem.getSelectedRow();
        if (iindexx >= 0) {
            tblItem.setRowSelectionInterval(iindexx, iindexx);
            tblItemMouseClicked(null);
        }
    }//GEN-LAST:event_tblItemMouseReleased

    private void tblItemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblItemKeyReleased
        // TODO add your handling code here:
        int iindexx = tblItem.getSelectedRow();
        if (iindexx >= 0) {
            tblItem.setRowSelectionInterval(iindexx, iindexx);
            tblItemMouseClicked(null);
        }
    }//GEN-LAST:event_tblItemKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNew;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbxSupplier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox rcbSupplying;
    private javax.swing.JTable tblItem;
    private javax.swing.JTextField txtItemCode;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtUnit;
    // End of variables declaration//GEN-END:variables
}
