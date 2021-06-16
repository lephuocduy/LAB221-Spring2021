/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.supplierDAO;
import DTO.Suppliers;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import GUI.ItemPanel;

/**
 *
 * @author truon
 */
public class SupplierPanel extends javax.swing.JPanel {

    /**
     * Creates new form SupplierPanel
     */
    ArrayList<Suppliers> listSupplier;
    boolean addNew = false;
    int index = -1;

    public SupplierPanel() {
        initComponents();
        loadSupplierPanel();
    }

    void loadSupplierPanel() {
        index = -1;
        try {
            listSupplier = supplierDAO.getsSupplier();
            DefaultTableModel model = (DefaultTableModel) tblSupplier.getModel();
            model.getDataVector().removeAllElements();
            for (Suppliers suppliers : listSupplier) {
                Vector row = new Vector();
                row.add(suppliers.getSupCode());
                row.add(suppliers.getSupName());
                row.add(suppliers.getAddress());
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error loadSP");
        }
    }

    void whenClick() {
        txtName.setEnabled(true);
        txtAddress.setEnabled(true);
        rcbCollaborating.setEnabled(true);
        btnDelete.setEnabled(true);
        btnSave.setEnabled(true);
    }

    void whenAddNew() {
        //xoa du lieu hien thi
        txtCode.setText("");
        txtName.setText("");
        txtAddress.setText("");
        rcbCollaborating.setSelected(false);
        //tat bat
        txtCode.setEnabled(true);
        txtName.setEnabled(true);
        txtAddress.setEnabled(true);
        rcbCollaborating.setEnabled(true);
        btnSave.setEnabled(true);
        btnDelete.setEnabled(false);
        tblSupplier.setEnabled(false);
        tblSupplier.clearSelection();
    }

    void resetStatus() {
        //xoa
        txtCode.setText("");
        txtName.setText("");
        txtAddress.setText("");
        rcbCollaborating.setSelected(false);
        tblSupplier.clearSelection();
        //
        txtCode.setEnabled(false);
        txtName.setEnabled(false);
        txtAddress.setEnabled(false);
        rcbCollaborating.setEnabled(false);
        btnAddNew.setEnabled(true);
        btnDelete.setEnabled(false);
        btnSave.setEnabled(false);
        tblSupplier.setEnabled(true);
        //
        loadSupplierPanel();
    }

    void saveNew() {
        if (txtAddress.getText().trim().isEmpty() || txtCode.getText().trim().isEmpty() || txtName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "have not entered enough information");
            return;
        } else {
            String supCode = txtCode.getText().trim();
            boolean checkID = true;
            for (int i = 0; i < listSupplier.size(); i++) {
                if (supCode.equals(listSupplier.get(i).getSupCode())) {
                    checkID = false;
                }
            }
            if (checkID) {
                String supName = txtName.getText().trim();
                String address = txtAddress.getText().trim();
                boolean collaborating = rcbCollaborating.isSelected();
                try {
                    if (supCode.length() > 10 || supName.length() > 50 || address.length() > 50) {
                        if (supCode.length() > 10) {
                            JOptionPane.showMessageDialog(null, "id max length is 10 ");
                        } else if (supName.length() > 50) {
                            JOptionPane.showMessageDialog(null, "name max length is 50");
                        } else if (address.length() > 50) {
                            JOptionPane.showMessageDialog(null, "unit max length is 50");
                        }
                    } else {
                        supplierDAO.insertSupplier(supCode, supName, address, collaborating);
                        addNew = false;
                        btnAddNew.setText("Add New");
                        resetStatus();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, " error save data");
                }
            } else {
                JOptionPane.showMessageDialog(null, "code used");
                txtCode.setText("");
            }
        }
    }

    boolean saveNewLogout() {
        if (txtAddress.getText().trim().isEmpty() || txtCode.getText().trim().isEmpty() || txtName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "have not entered enough information");
            return true;
        } else {
            String supCode = txtCode.getText().trim();
            boolean checkID = true;
            for (int i = 0; i < listSupplier.size(); i++) {
                if (supCode.equals(listSupplier.get(i).getSupCode())) {
                    checkID = false;
                }
            }
            if (checkID) {
                String supName = txtName.getText().trim();
                String address = txtAddress.getText().trim();
                boolean collaborating = rcbCollaborating.isSelected();
                try {
                    if (supCode.length() > 10 || supName.length() > 50 || address.length() > 50) {
                        if (supCode.length() > 10) {
                            JOptionPane.showMessageDialog(null, "id max length is 10 ");
                        } else if (supName.length() > 50) {
                            JOptionPane.showMessageDialog(null, "name max length is 50");
                        } else if (address.length() > 50) {
                            JOptionPane.showMessageDialog(null, "unit max length is 50");
                        }
                        return true;
                    } else {
                        return false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, " error save data");
                }

            } else {
                JOptionPane.showMessageDialog(null, "code used");
                txtCode.setText("");
                return true;
            }

        }
        return false;

    }

    public void mainCheck2() {
        addNew = false;
        btnAddNew.setText("Add New");
    }

    void updateData() {
        if (txtAddress.getText().trim().isEmpty() || txtName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "have not entered enough information");
            return;
        } else {
            String supCode = txtCode.getText().trim();
            String supName = txtName.getText().trim();
            String address = txtAddress.getText().trim();
            boolean collaborating = rcbCollaborating.isSelected();
            try {
                if (supCode.length() > 10 || supName.length() > 50 || address.length() > 50) {
                    if (supCode.length() > 10) {
                        JOptionPane.showMessageDialog(null, "id max length is 10 ");
                    } else if (supName.length() > 50) {
                        JOptionPane.showMessageDialog(null, "name max length is 50");
                    } else if (address.length() > 50) {
                        JOptionPane.showMessageDialog(null, "unit max length is 50");
                    }
                } else {
                    supplierDAO.updateSupplier(supCode, supName, address, collaborating);
                    if (supplierDAO.findUseSP(supCode) != null) {
                        ItemPanel main = new ItemPanel();
                        add(main);
                        getComponents();
                        main.resetStatuss();
                        main.setVisible(true);
                        SwingUtilities.updateComponentTreeUI(main);
                        JOptionPane.showMessageDialog(null, "Fixed item ");
                    }
                    resetStatus();
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, " error update data");
            }
        }
    }

    boolean updateDataLogout() {
        if (txtAddress.getText().trim().isEmpty() || txtName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "have not entered enough information");
            return true;
        } else {
            String supCode = txtCode.getText().trim();
            String supName = txtName.getText().trim();
            String address = txtAddress.getText().trim();
            boolean collaborating = rcbCollaborating.isSelected();
            try {
                if (supCode.length() > 10 || supName.length() > 50 || address.length() > 50) {
                    if (supCode.length() > 10) {
                        JOptionPane.showMessageDialog(null, "id max length is 10 ");
                    } else if (supName.length() > 50) {
                        JOptionPane.showMessageDialog(null, "name max length is 50");
                    } else if (address.length() > 50) {
                        JOptionPane.showMessageDialog(null, "unit max length is 50");
                    }
                    return true;
                } else {
                    return false;
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, " error update data");
               
            }
        }
        return false;
    }

    boolean checkUpdateStatus() {
        boolean result = true;
        if (index >= 0) {
            String nameCheck = txtName.getText();
            String addressCheck = txtAddress.getText();
            boolean collaborating = rcbCollaborating.isSelected();
            if (!nameCheck.equals(listSupplier.get(index).getSupName())
                    || !addressCheck.equals(listSupplier.get(index).getAddress())
                    || !(collaborating == listSupplier.get(index).isCollaborating())) {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblSupplier = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnAddNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        rcbCollaborating = new javax.swing.JCheckBox();

        tblSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Name", "Address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSupplier.getTableHeader().setReorderingAllowed(false);
        tblSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSupplierMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblSupplierMouseReleased(evt);
            }
        });
        tblSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblSupplierKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblSupplier);

        jLabel1.setText("Code");

        txtCode.setEnabled(false);

        jLabel2.setText("Name");

        txtName.setEnabled(false);

        jLabel3.setText("Address");

        txtAddress.setEnabled(false);

        jLabel4.setText("Collaborating");

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

        rcbCollaborating.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(66, 66, 66)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rcbCollaborating)
                                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAddNew)
                                .addGap(29, 29, 29)
                                .addComponent(btnSave))
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(45, 45, 45)
                        .addComponent(btnDelete)))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 63, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rcbCollaborating))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddNew)
                    .addComponent(btnSave)
                    .addComponent(btnDelete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSupplierMouseClicked
        // TODO add your handling code here:
        if (addNew) {
            JOptionPane.showMessageDialog(null, "Please complete the ADD NEW function");
            return;
        } else if (!checkUpdateStatus()) {
            tblSupplier.setRowSelectionInterval(index, index);
            JOptionPane.showMessageDialog(null, "Please complete the update function");
            return;
        } else {
            whenClick();
            index = tblSupplier.getSelectedRow();
            if (index >= 0) {
                txtCode.setText(listSupplier.get(index).getSupCode());
                txtName.setText(listSupplier.get(index).getSupName());
                txtAddress.setText(listSupplier.get(index).getAddress());
                if (listSupplier.get(index).isCollaborating()) {
                    rcbCollaborating.setSelected(true);
                } else {
                    rcbCollaborating.setSelected(false);
                }

            } else {
                return;
            }
        }

    }//GEN-LAST:event_tblSupplierMouseClicked

    private void btnAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewActionPerformed
        // TODO add your handling code here:
        if (!checkUpdateStatus() && addNew == false) {
            JOptionPane.showMessageDialog(null, "Please complete the update function");
            return;
        } else {
            if (addNew == false) {
                addNew = true;
                whenAddNew();
                btnAddNew.setText("Cancel");
            } else if (addNew) {
                Object[] options = {"Yes", "No"};
                int n = JOptionPane.showOptionDialog(null, "Do you want to ADD NEW?", "CANCEL SAVE", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if (n == JOptionPane.YES_OPTION) {
                    saveNew();
                } else if (n == JOptionPane.NO_OPTION) {
                    addNew = false;
                    btnAddNew.setText("Add New");
                    resetStatus();
                }
            }
        }
    }//GEN-LAST:event_btnAddNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if (addNew) {
            saveNew();
        } else if (!addNew) {
            Object[] options = {"Yes", "No"};
            int n = JOptionPane.showOptionDialog(null, "Do you want to UPDATE data?", "UPDATE DATA", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if (n == JOptionPane.YES_OPTION) {
                updateData();
            } else if (n == JOptionPane.NO_OPTION) {
                resetStatus();
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if (!checkUpdateStatus()) {
            JOptionPane.showMessageDialog(null, "Please complete the update function");
            return;
        } else {
            index = tblSupplier.getSelectedRow();
            try {
                Object[] options = {"Yes", "No"};
                int n = JOptionPane.showOptionDialog(null, "Do you want to DELETE data?", "DELETE DATA", JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if (n == JOptionPane.YES_OPTION) {
                    if (supplierDAO.findUseSP(listSupplier.get(index).getSupCode()) == null) {
                        supplierDAO.deleteSupplier(listSupplier.get(index).getSupCode());
                        resetStatus();
                        tblSupplier.updateUI();
                    } else {
                        JOptionPane.showMessageDialog(null, "the used supplier cannot be deleted");
                        resetStatus();
                    }
                } else if (n == JOptionPane.NO_OPTION) {
                    resetStatus();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "error delete data");
            }
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tblSupplierMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSupplierMouseReleased
        // TODO add your handling code here:
        int iindexx = tblSupplier.getSelectedRow();
        if (iindexx >= 0) {
            tblSupplier.setRowSelectionInterval(iindexx, iindexx);
            tblSupplierMouseClicked(null);
        }
    }//GEN-LAST:event_tblSupplierMouseReleased

    private void tblSupplierKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblSupplierKeyReleased
        // TODO add your handling code here:
        int iindexx = tblSupplier.getSelectedRow();
        if (iindexx >= 0) {
            tblSupplier.setRowSelectionInterval(iindexx, iindexx);
            tblSupplierMouseClicked(null);
        }
    }//GEN-LAST:event_tblSupplierKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNew;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox rcbCollaborating;
    private javax.swing.JTable tblSupplier;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
