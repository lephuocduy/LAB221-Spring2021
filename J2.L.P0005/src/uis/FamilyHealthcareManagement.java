/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uis;

import dtos.RegistrationDTO;
import implement.RegistrationInterface;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import validation.checkValue;

/**
 *
 * @author Le Phuoc Duy
 */
public class FamilyHealthcareManagement extends javax.swing.JFrame {

    /**
     * Creates new form FamilyHealthcareManagement
     */
    private final String serviceName = "rmi://127.0.0.1:1098/FamilyHealthcareService";
    private RegistrationInterface stub;
    private DefaultTableModel model;
    private Vector<String> header;
    private Vector<Vector> data = null;
    private boolean addNew = false;

    public FamilyHealthcareManagement() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        header = new Vector<>();
        header.add("ID");
        header.add("Full Name");
        header.add("Age");
        header.add("Gender");
        header.add("Phone");
        header.add("Address");
        model = new DefaultTableModel(data, header);
        tbl.setModel(model);
        beforeGet();
    }

    void beforeGet() {
        txtId.setText("");
        txtName.setText("");
        txtAge.setText("");
        rbMale.setSelected(true);
        rbFemale.setSelected(false);
        txtEmail.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtNumOfMem.setText("");
        txtChildren.setText("");
        txtAdults.setText("");

        txtId.setEnabled(false);
        txtName.setEnabled(false);
        txtAge.setEnabled(false);
        rbMale.setEnabled(false);
        rbFemale.setEnabled(false);
        txtEmail.setEnabled(false);
        txtPhone.setEnabled(false);
        txtAddress.setEnabled(false);
        txtNumOfMem.setEnabled(false);
        txtChildren.setEnabled(false);
        txtAdults.setEnabled(false);

        tbl.setEnabled(false);
        btnFindById.setEnabled(false);
        btnGetAllData.setEnabled(true);
        cbSort.setEnabled(false);
        btnSearchByName.setEnabled(false);
        txtSearchByName.setEnabled(false);
        btnAddNew.setEnabled(true);
        btnSave.setEnabled(false);
        btnRemove.setEnabled(false);
    }

    void afterGet() {
        txtId.setText("");
        txtName.setText("");
        txtAge.setText("");
        rbMale.setSelected(true);
        rbFemale.setSelected(false);
        txtEmail.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtNumOfMem.setText("");
        txtChildren.setText("");
        txtAdults.setText("");

        txtId.setEnabled(true);
        txtName.setEnabled(false);
        txtAge.setEnabled(false);
        rbMale.setEnabled(false);
        rbFemale.setEnabled(false);
        txtEmail.setEnabled(false);
        txtPhone.setEnabled(false);
        txtAddress.setEnabled(false);
        txtNumOfMem.setEnabled(false);
        txtChildren.setEnabled(false);
        txtAdults.setEnabled(false);

        tbl.setEnabled(true);
        btnFindById.setEnabled(true);
        btnGetAllData.setEnabled(true);
        cbSort.setEnabled(true);
        btnSearchByName.setEnabled(true);
        txtSearchByName.setEnabled(true);
        btnAddNew.setEnabled(true);
        btnSave.setEnabled(false);
        btnRemove.setEnabled(false);
    }

    void whenAddNew() {
        txtId.setText("");
        txtName.setText("");
        txtAge.setText("");
        rbMale.setSelected(true);
        rbFemale.setSelected(false);
        txtEmail.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtNumOfMem.setText("");
        txtChildren.setText("");
        txtAdults.setText("");

        txtId.setEnabled(true);
        txtName.setEnabled(true);
        txtAge.setEnabled(true);
        rbMale.setEnabled(true);
        rbFemale.setEnabled(true);
        txtEmail.setEnabled(true);
        txtPhone.setEnabled(true);
        txtAddress.setEnabled(true);
        txtNumOfMem.setEnabled(true);
        txtChildren.setEnabled(true);
        txtAdults.setEnabled(true);

        tbl.setEnabled(false);
        tbl.clearSelection();
        btnFindById.setEnabled(false);
        btnGetAllData.setEnabled(false);
        btnSave.setEnabled(true);
        btnRemove.setEnabled(false);
        cbSort.setEnabled(false);
        btnSearchByName.setEnabled(false);
        txtSearchByName.setEnabled(false);
        btnAddNew.setEnabled(true);
    }

    void closeAddNew() {
        txtId.setText("");
        txtName.setText("");
        txtAge.setText("");
        rbMale.setSelected(true);
        rbFemale.setSelected(false);
        txtEmail.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtNumOfMem.setText("");
        txtChildren.setText("");
        txtAdults.setText("");
        btnAddNew.setText("Add new");
        addNew = false;

        txtId.setEnabled(true);
        txtName.setEnabled(false);
        txtAge.setEnabled(false);
        rbMale.setEnabled(false);
        rbFemale.setEnabled(false);
        txtEmail.setEnabled(false);
        txtPhone.setEnabled(false);
        txtAddress.setEnabled(false);
        txtNumOfMem.setEnabled(false);
        txtChildren.setEnabled(false);
        txtAdults.setEnabled(false);

        tbl.setEnabled(true);
        tbl.clearSelection();
        btnFindById.setEnabled(true);
        btnGetAllData.setEnabled(true);
        btnSave.setEnabled(true);
        btnRemove.setEnabled(true);
        cbSort.setEnabled(true);
        btnSearchByName.setEnabled(true);
        txtSearchByName.setEnabled(true);
        btnAddNew.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        btngGender = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        cbSort = new javax.swing.JComboBox<>();
        btnSearchByName = new javax.swing.JButton();
        txtSearchByName = new javax.swing.JTextField();
        btnGetAllData = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnAddNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        btnFindById = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        txtAge = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        rbMale = new javax.swing.JRadioButton();
        rbFemale = new javax.swing.JRadioButton();
        txtEmail = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtNumOfMem = new javax.swing.JTextField();
        txtChildren = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtAdults = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Main part:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl);

        jLabel13.setText("Sort by name:");

        cbSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascending", "Descending" }));
        cbSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSortActionPerformed(evt);
            }
        });

        btnSearchByName.setText("Search by name:");
        btnSearchByName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchByNameActionPerformed(evt);
            }
        });

        btnGetAllData.setText("Get all data");
        btnGetAllData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetAllDataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(cbSort, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearchByName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchByName))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(btnGetAllData)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cbSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchByName)
                    .addComponent(txtSearchByName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnGetAllData)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detailed part:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Registration ID:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanel2.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Full name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanel2.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Age:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanel2.add(jLabel3, gridBagConstraints);

        jLabel4.setText("Email:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanel2.add(jLabel4, gridBagConstraints);

        jLabel5.setText("Phone:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanel2.add(jLabel5, gridBagConstraints);

        jLabel6.setText("Address:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanel2.add(jLabel6, gridBagConstraints);

        jLabel7.setText("Include:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanel2.add(jLabel7, gridBagConstraints);

        jLabel8.setText("Number of member:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanel2.add(jLabel8, gridBagConstraints);

        btnAddNew.setText("Add new");
        btnAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        jPanel2.add(btnAddNew, gridBagConstraints);

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        jPanel2.add(btnSave, gridBagConstraints);

        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        jPanel2.add(btnRemove, gridBagConstraints);

        txtId.setPreferredSize(new java.awt.Dimension(150, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 0);
        jPanel2.add(txtId, gridBagConstraints);

        btnFindById.setText("Find by ID");
        btnFindById.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindByIdActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 0);
        jPanel2.add(btnFindById, gridBagConstraints);

        txtName.setPreferredSize(new java.awt.Dimension(200, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 0);
        jPanel2.add(txtName, gridBagConstraints);

        txtAge.setPreferredSize(new java.awt.Dimension(60, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 0);
        jPanel2.add(txtAge, gridBagConstraints);

        jLabel9.setText("Sex:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanel2.add(jLabel9, gridBagConstraints);

        btngGender.add(rbMale);
        rbMale.setText("Male");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 0);
        jPanel2.add(rbMale, gridBagConstraints);

        btngGender.add(rbFemale);
        rbFemale.setText("Female");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 0);
        jPanel2.add(rbFemale, gridBagConstraints);

        txtEmail.setPreferredSize(new java.awt.Dimension(200, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 0);
        jPanel2.add(txtEmail, gridBagConstraints);

        txtPhone.setPreferredSize(new java.awt.Dimension(200, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 0);
        jPanel2.add(txtPhone, gridBagConstraints);

        txtNumOfMem.setPreferredSize(new java.awt.Dimension(60, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 0);
        jPanel2.add(txtNumOfMem, gridBagConstraints);

        txtChildren.setPreferredSize(new java.awt.Dimension(60, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 0);
        jPanel2.add(txtChildren, gridBagConstraints);

        jLabel10.setText("Children");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 0);
        jPanel2.add(jLabel10, gridBagConstraints);

        jLabel11.setText("Adults");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 0);
        jPanel2.add(jLabel11, gridBagConstraints);

        txtAdults.setPreferredSize(new java.awt.Dimension(60, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 0);
        jPanel2.add(txtAdults, gridBagConstraints);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(200, 117));

        txtAddress.setColumns(20);
        txtAddress.setRows(5);
        txtAddress.setPreferredSize(new java.awt.Dimension(200, 94));
        jScrollPane2.setViewportView(txtAddress);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jScrollPane2, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        jLabel12.setText("Family Healthcare Management");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(234, 234, 234)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private boolean connectToServer() {
        try {
            stub = (RegistrationInterface) Naming.lookup(serviceName);
            if (stub == null) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void btnGetAllDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetAllDataActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.getDataVector().removeAllElements();
        if (connectToServer()) {
            ArrayList<RegistrationDTO> listArr = null;
            try {
                listArr = stub.findAllRegistrations();
                if (listArr.isEmpty() || listArr == null) {
                    tbl.updateUI();
                    JOptionPane.showMessageDialog(null, "FILE 'RegistrationData.txt' has no data");
                    beforeGet();
                } else {
                    data = loadData(listArr);
                    model = new DefaultTableModel(data, header);
                    tbl.setModel(model);
                    afterGet();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Can not connect to server");
        }
    }//GEN-LAST:event_btnGetAllDataActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if (!connectToServer()) {
            JOptionPane.showMessageDialog(this, "Can not connect to server");
        }
        String id = txtId.getText().trim(),
                fullName = txtName.getText().trim(),
                age = txtAge.getText().trim(),
                email = txtEmail.getText().trim(),
                phone = txtPhone.getText().trim(),
                add = txtAddress.getText().replaceAll("\\s\\s+", " ").trim(),
                numOfmem = txtNumOfMem.getText().trim(),
                children = txtChildren.getText().trim(),
                adults = txtAdults.getText().trim();
        boolean sex = rbMale.isSelected() ? true : false;
        boolean check = checkValue.checkRegistrationID(id)
                && checkValue.checkfullName(fullName)
                && checkValue.checkNumber(age)
                && checkValue.checkEmail(email)
                && checkValue.checkPhone(phone)
                && checkValue.checkNumber(numOfmem)
                && checkValue.checkNumber(children)
                && checkValue.checkNumber(adults);
        if (addNew) {
            try {
                if (id.isEmpty() || fullName.isEmpty() || age.isEmpty() || email.isEmpty() || phone.isEmpty() || add.isEmpty() || numOfmem.isEmpty() || children.isEmpty() || adults.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "have not entered enough information");
                    return;
                } else if (!check) {
                    if (!checkValue.checkRegistrationID(id)) {
                        JOptionPane.showMessageDialog(null, "RegistrationID max length is 10, not contains special characters");
                        txtId.setText("");
                    } else if (!checkValue.checkfullName(fullName)) {
                        JOptionPane.showMessageDialog(null, "FullName max length is 50 and not a number");
                        txtName.setText("");
                    } else if (!checkValue.checkNumber(age)) {
                        JOptionPane.showMessageDialog(null, "Age must be >= 0");
                        txtAge.setText("");
                    } else if (!checkValue.checkEmail(email)) {
                        JOptionPane.showMessageDialog(null, "Email max length is 30 and format a@b.c or a@b.c.d");
                        txtEmail.setText("");
                    } else if (!checkValue.checkPhone(phone)) {
                        JOptionPane.showMessageDialog(null, "Phone max length is 15, contain numeric characters only (0-9)");
                        txtPhone.setText("");
                    } else if (!checkValue.checkNumber(numOfmem)) {
                        JOptionPane.showMessageDialog(null, "Number of member must be >= 0");
                        txtNumOfMem.setText("");
                    } else if (!checkValue.checkNumber(children)) {
                        JOptionPane.showMessageDialog(null, "Number of children must be >= 0");
                        txtChildren.setText("");
                    } else if (!checkValue.checkNumber(adults)) {
                        JOptionPane.showMessageDialog(null, "Number of adults must be >=0");
                        txtAdults.setText("");
                    }
                    return;
                } else if (stub.findByRegistrationID(id) != null) {
                    JOptionPane.showMessageDialog(null, "id already exists");
                    txtId.setText("");
                    return;
                } else {
                    stub.createRegistration(new RegistrationDTO(id, fullName, email, phone, add, Integer.parseInt(age), Integer.parseInt(numOfmem), Integer.parseInt(children), Integer.parseInt(adults), sex));
                    JOptionPane.showMessageDialog(null, "Add new successfully");

                    txtId.setEnabled(true);
                    txtId.setText("");
                    txtName.setText("");
                    txtAge.setText("");
                    rbMale.setSelected(true);
                    rbFemale.setSelected(false);
                    txtEmail.setText("");
                    txtPhone.setText("");
                    txtAddress.setText("");
                    txtNumOfMem.setText("");
                    txtChildren.setText("");
                    txtAdults.setText("");
                    btnAddNew.setText("Add new");
                }
            } catch (RemoteException ex) {
                Logger.getLogger(FamilyHealthcareManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeAddNew();
            btnGetAllDataActionPerformed(null);
        } else {
            try {
                if (fullName.isEmpty() || age.isEmpty() || email.isEmpty() || phone.isEmpty() || add.isEmpty() || numOfmem.isEmpty() || children.isEmpty() || adults.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "have not entered enough information");
                    return;
                } else if (!check) {
                    if (!checkValue.checkfullName(fullName)) {
                        JOptionPane.showMessageDialog(null, "FullName max length is 50 and not a number");
                        txtName.setText("");
                    } else if (!checkValue.checkNumber(age)) {
                        JOptionPane.showMessageDialog(null, "Age must be >= 0");
                        txtAge.setText("");
                    } else if (!checkValue.checkEmail(email)) {
                        JOptionPane.showMessageDialog(null, "Email max length is 30 and format a@b.c or a@b.c.d");
                        txtEmail.setText("");
                    } else if (!checkValue.checkPhone(phone)) {
                        JOptionPane.showMessageDialog(null, "Phone max length is 15, contain numeric characters only (0-9)");
                        txtPhone.setText("");
                    } else if (!checkValue.checkNumber(numOfmem)) {
                        JOptionPane.showMessageDialog(null, "Number of member must be >= 0");
                        txtNumOfMem.setText("");
                    } else if (!checkValue.checkNumber(children)) {
                        JOptionPane.showMessageDialog(null, "Number of children must be >= 0");
                        txtChildren.setText("");
                    } else if (!checkValue.checkNumber(adults)) {
                        JOptionPane.showMessageDialog(null, "Number of adults must be >=0");
                        txtAdults.setText("");
                    }
                    return;
                } else {
                    stub.updateRegistration(new RegistrationDTO(id, fullName, email, phone, add, Integer.parseInt(age), Integer.parseInt(numOfmem), Integer.parseInt(children), Integer.parseInt(adults), sex));
                    JOptionPane.showMessageDialog(null, "Update successfully");
                    btnGetAllDataActionPerformed(null);

                    tbl.clearSelection();
                    txtId.setText("");
                    txtName.setText("");
                    txtAge.setText("");
                    rbMale.setSelected(true);
                    rbFemale.setSelected(false);
                    txtEmail.setText("");
                    txtPhone.setText("");
                    txtAddress.setText("");
                    txtNumOfMem.setText("");
                    txtChildren.setText("");
                    txtAdults.setText("");

                    txtId.setEnabled(true);
                    txtName.setEnabled(false);
                    txtAge.setEnabled(false);
                    rbMale.setEnabled(false);
                    rbFemale.setEnabled(false);
                    txtEmail.setEnabled(false);
                    txtPhone.setEnabled(false);
                    txtAddress.setEnabled(false);
                    txtNumOfMem.setEnabled(false);
                    txtChildren.setEnabled(false);
                    txtAdults.setEnabled(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewActionPerformed
        // TODO add your handling code here:
        if (addNew == false) {
            addNew = true;
            whenAddNew();
            btnAddNew.setText("Cancel");
        } else if (addNew) {
            addNew = false;
            closeAddNew();
        }
    }//GEN-LAST:event_btnAddNewActionPerformed

    private void btnFindByIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindByIdActionPerformed
        // TODO add your handling code here:
        if (!connectToServer()) {
            JOptionPane.showMessageDialog(this, "Can not connect to server");
        }
        try {
            String id = txtId.getText();
            if (checkValue.checkRegistrationID(id)) {
                RegistrationDTO dto = stub.findByRegistrationID(id);
                if (dto == null) {
                    JOptionPane.showMessageDialog(null, "id does not exist");
                    txtId.setText("");
                    txtId.requestFocus();
                } else {
                    System.out.println(dto.isGender());
                    addNew = false;
                    txtId.setEnabled(false);
                    txtName.setText(dto.getFullName());
                    txtAge.setText(dto.getAge() + "");
                    if (dto.isGender()) {
                        rbMale.setSelected(true);
                        rbFemale.setSelected(false);
                    } else {
                        rbMale.setSelected(false);
                        rbFemale.setSelected(true);
                    }
                    txtEmail.setText(dto.getEmail());
                    txtPhone.setText(dto.getPhone());
                    txtAddress.setText(dto.getAddress());
                    txtNumOfMem.setText(dto.getNumberOfMember() + "");
                    txtChildren.setText(dto.getNumberOfChildren() + "");
                    txtAdults.setText(dto.getNumberOfAdults() + "");

                    txtId.setEnabled(false);
                    txtName.setEnabled(true);
                    txtAge.setEnabled(true);
                    rbMale.setEnabled(true);
                    rbFemale.setEnabled(true);
                    txtEmail.setEnabled(true);
                    txtPhone.setEnabled(true);
                    txtAddress.setEnabled(true);
                    txtNumOfMem.setEnabled(true);
                    txtChildren.setEnabled(true);
                    txtAdults.setEnabled(true);
                    btnSave.setEnabled(true);
                    btnRemove.setEnabled(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, " max length is 10 and not contains special characters (@, #, $)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnFindByIdActionPerformed

    private void tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseClicked
        // TODO add your handling code here:
        int row = tbl.getSelectedRow();
        if (!connectToServer()) {
            JOptionPane.showMessageDialog(this, "Can not connect to server");
        }
        try {
            RegistrationDTO dto;
            if ((dto = stub.findByRegistrationID(tbl.getValueAt(row, 0).toString())) != null) {
                addNew = false;
                txtId.setEnabled(false);
                txtId.setText(dto.getRegistrationID());
                txtName.setText(dto.getFullName());
                txtAge.setText(dto.getAge() + "");
                if (dto.isGender()) {
                    rbMale.setSelected(true);
                    rbFemale.setSelected(false);
                } else {
                    rbMale.setSelected(false);
                    rbFemale.setSelected(true);
                }
                txtEmail.setText(dto.getEmail());
                txtPhone.setText(dto.getPhone());
                txtAddress.setText(dto.getAddress());
                txtNumOfMem.setText(dto.getNumberOfMember() + "");
                txtChildren.setText(dto.getNumberOfChildren() + "");
                txtAdults.setText(dto.getNumberOfAdults() + "");

                txtId.setEnabled(false);
                txtName.setEnabled(true);
                txtAge.setEnabled(true);
                rbMale.setEnabled(true);
                rbFemale.setEnabled(true);
                txtEmail.setEnabled(true);
                txtPhone.setEnabled(true);
                txtAddress.setEnabled(true);
                txtNumOfMem.setEnabled(true);
                txtChildren.setEnabled(true);
                txtAdults.setEnabled(true);
                btnSave.setEnabled(true);
                btnRemove.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "Not find id, maybe data is changed");
                btnFindByIdActionPerformed(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblMouseClicked

    private void btnSearchByNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchByNameActionPerformed
        // TODO add your handling code here:
        String name = txtSearchByName.getText();
        if (connectToServer()) {
            ArrayList<RegistrationDTO> listArr = new ArrayList();
            try {
                for (RegistrationDTO obj : stub.findAllRegistrations()) {
                    if (obj.getFullName().indexOf(name) >= 0) {
                        listArr.add(obj);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (listArr.size() == 0) {
                JOptionPane.showMessageDialog(this, "Not found");
                txtSearchByName.setText("");
                return;
            }
            Vector<Vector> list = new Vector<>();
            Vector obj = null;
            for (RegistrationDTO dto : listArr) {
                obj = new Vector<>();
                String gender = dto.isGender() ? "Male" : "Female";
                obj.add(dto.getRegistrationID());
                obj.add(dto.getFullName());
                obj.add(dto.getAge() + "");
                obj.add(gender);
                obj.add(dto.getPhone());
                obj.add(dto.getAddress());
                list.add(obj);
            }

            model = new DefaultTableModel(list, header);
            tbl.setModel(model);
            txtSearchByName.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Can not connect to server");
        }
    }//GEN-LAST:event_btnSearchByNameActionPerformed

    private void cbSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSortActionPerformed
        // TODO add your handling code here:
        int pos = cbSort.getSelectedIndex();
        if (connectToServer()) {
            ArrayList<RegistrationDTO> listArr = null;
            try {
                listArr = stub.findAllRegistrations();
                Collections.sort(listArr);
                if (pos == 0) {
                    Collections.reverse(listArr);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            data = null;
            data = loadData(listArr);
            model = new DefaultTableModel(data, header);
            tbl.setModel(model);
        } else {
            JOptionPane.showMessageDialog(this, "Can not connect to server");
        }
    }//GEN-LAST:event_cbSortActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // TODO add your handling code here:
        if (connectToServer()) {
            try {
                int ans = JOptionPane.showConfirmDialog(null, "Do you want to REMOVE?", "REMOVE ARMOR", JOptionPane.YES_NO_OPTION);
                if (ans == JOptionPane.YES_OPTION) {
                    stub.removeRegistration(txtId.getText());
                    btnGetAllDataActionPerformed(null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Can not connect to server");
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private Vector loadData(ArrayList<RegistrationDTO> listArr) {

        Vector list = new Vector();
        Vector<String> obj = null;

        for (RegistrationDTO dto : listArr) {
            obj = new Vector<>();
            String gender = dto.isGender() ? "Male" : "Female";
            obj.add(dto.getRegistrationID());
            obj.add(dto.getFullName());
            obj.add(dto.getAge() + "");
            obj.add(gender);
            obj.add(dto.getPhone());
            obj.add(dto.getAddress());
            list.add(obj);
        }
        return list;
    }

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FamilyHealthcareManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FamilyHealthcareManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FamilyHealthcareManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FamilyHealthcareManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FamilyHealthcareManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNew;
    private javax.swing.JButton btnFindById;
    private javax.swing.JButton btnGetAllData;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearchByName;
    private javax.swing.ButtonGroup btngGender;
    private javax.swing.JComboBox<String> cbSort;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rbFemale;
    private javax.swing.JRadioButton rbMale;
    private javax.swing.JTable tbl;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JTextField txtAdults;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtChildren;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNumOfMem;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSearchByName;
    // End of variables declaration//GEN-END:variables
}
