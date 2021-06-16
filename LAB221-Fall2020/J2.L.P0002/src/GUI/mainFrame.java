package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DTO.ArmorDTO;
import GUI.ArmorInterface;
import Validation.checkValue;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author truon
 */
public class mainFrame extends javax.swing.JFrame {

    /**
     * Creates new form mainFrame
     */
    ArmorInterface armorInter;
    List<ArmorDTO> listArmor = new ArrayList<>();
    String serviceName = "rmi://127.0.0.1:8888/now";
    SimpleDateFormat formaT = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    int indexTBL = -1;
    boolean createNew = false;
    boolean findByID = false;

    public mainFrame() {
        initComponents();
        checkSeverConnect();
    }

    void checkSeverConnect() {
        try {
            armorInter = (ArmorInterface) Naming.lookup(serviceName);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not running the server yet, please run ArmorServer");
            System.exit(0);
        }
    }

    boolean checkUpdateStatus() {
        boolean result = true;
        try {
            listArmor = armorInter.findAllArmor();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "60");
        }
        if (indexTBL >= 0) {
            String armorIDCheck = txtArmorID.getText();
            String classificationCheck = txtClassification.getText();
            String descriptionCheck = txtDescription.getText();
            String statusCheck = txtStatus.getText();
            boolean checkDe = txtDefense.getText().matches("\\d+");
            if (!checkDe) {
                result = false;
            } else if (!txtDefense.getText().isEmpty()) {
                int defenseCheck = Integer.parseInt(txtDefense.getText());
                for (ArmorDTO armorDTO : listArmor) {
                    if (armorDTO.getArmorId().equals(armorIDCheck)) {
                        if (!classificationCheck.equals(armorDTO.getClassification())
                                || !descriptionCheck.equals(armorDTO.getDescription())
                                || !(defenseCheck == armorDTO.getDefense())
                                || !statusCheck.equals(armorDTO.getStatus())) {
                            result = false;
                        }
                    }

                }

            } else {
                result = false;
            }
        } else {
            result = true;
        }
        return result;
    }

    void whenAddNew() {
        txtArmorID.setText("");
        txtClassification.setText("");
        txtTimeOfCreate.setText("");
        txtDefense.setText("");
        txtDescription.setText("");
        txtStatus.setText("");

        txtArmorID.setEnabled(true);
        txtClassification.setEnabled(true);
        txtDefense.setEnabled(true);
        txtDescription.setEnabled(true);
        txtStatus.setEnabled(true);

        tblArmor.setEnabled(false);
        tblArmor.clearSelection();
        btnFIndByArmorID.setEnabled(false);
        btnGetAll.setEnabled(false);
        btnUpdateArmor.setEnabled(false);
        btnRemoveArmor.setEnabled(false);
    }

    void closeCreate() {
        txtArmorID.setText("");
        txtClassification.setText("");
        txtTimeOfCreate.setText("");
        txtDefense.setText("");
        txtDescription.setText("");
        txtStatus.setText("");
        btnCreateArmor.setText("Create");
        createNew = false;
        tblArmor.setEnabled(true);
        tblArmor.clearSelection();
        btnFIndByArmorID.setEnabled(true);
        btnGetAll.setEnabled(true);
        btnUpdateArmor.setEnabled(true);
        btnRemoveArmor.setEnabled(true);

        txtClassification.setEnabled(false);
        txtDefense.setEnabled(false);
        txtDescription.setEnabled(false);
        txtStatus.setEnabled(false);
        indexTBL = -1;
    }

    void checkClick() {
        int checkClickRow = tblArmor.getSelectedRow();
        if (checkClickRow >= 0) {
            ArmorDTO armor;
            try {
                System.out.println("");
                String id = (String) tblArmor.getValueAt(checkClickRow, 0);
                armor = armorInter.findByArmorID(id);
                DefaultTableModel model = (DefaultTableModel) tblArmor.getModel();
                if (armor == null) {
                    model.removeRow(checkClickRow);
                } else {
                    model.setValueAt(armor.getClassification(), checkClickRow, 1);
                    model.setValueAt(formaT.format(armor.getTimeOfCreate()), checkClickRow, 2);
                    model.setValueAt(armor.getDefense(), checkClickRow, 3);
                }

            } catch (RemoteException ex) {
                System.out.println("32");
            }

        }
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
        tblArmor = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtArmorID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtClassification = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDefense = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        btnCreateArmor = new javax.swing.JButton();
        btnUpdateArmor = new javax.swing.JButton();
        btnRemoveArmor = new javax.swing.JButton();
        btnFIndByArmorID = new javax.swing.JButton();
        btnGetAll = new javax.swing.JButton();
        txtTimeOfCreate = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tblArmor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Classification", "TimeOfCreate", "Defense"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblArmor.getTableHeader().setReorderingAllowed(false);
        tblArmor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblArmorMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblArmorMouseReleased(evt);
            }
        });
        tblArmor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblArmorKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblArmor);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Armor Client");

        jLabel2.setText("Armor's Detail:");

        jLabel3.setText("ArmorID:");

        jLabel4.setText("CLassification:");

        txtClassification.setEnabled(false);

        jLabel5.setText("TimeOfCreate:");

        jLabel6.setText("Defense:");

        txtDefense.setEnabled(false);

        jLabel7.setText("Description:");

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        txtDescription.setEnabled(false);
        jScrollPane2.setViewportView(txtDescription);

        jLabel8.setText("Status:");

        txtStatus.setEnabled(false);

        btnCreateArmor.setText("Create");
        btnCreateArmor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateArmorActionPerformed(evt);
            }
        });

        btnUpdateArmor.setText("Update");
        btnUpdateArmor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateArmorActionPerformed(evt);
            }
        });

        btnRemoveArmor.setText("Remove");
        btnRemoveArmor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveArmorActionPerformed(evt);
            }
        });

        btnFIndByArmorID.setText("Find By ArmorID");
        btnFIndByArmorID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFIndByArmorIDActionPerformed(evt);
            }
        });

        btnGetAll.setText("Get All");
        btnGetAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetAllActionPerformed(evt);
            }
        });

        txtTimeOfCreate.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(btnGetAll)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCreateArmor)
                                .addGap(82, 82, 82)
                                .addComponent(btnUpdateArmor)
                                .addGap(74, 74, 74)
                                .addComponent(btnRemoveArmor))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                    .addComponent(txtStatus)
                                    .addComponent(txtArmorID)
                                    .addComponent(txtClassification)
                                    .addComponent(txtDefense)
                                    .addComponent(txtTimeOfCreate))
                                .addGap(27, 27, 27)
                                .addComponent(btnFIndByArmorID)))))
                .addGap(50, 50, 50))
            .addGroup(layout.createSequentialGroup()
                .addGap(374, 374, 374)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtArmorID, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnFIndByArmorID))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtClassification, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtTimeOfCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtDefense, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addComponent(jLabel7))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnGetAll)
                        .addGap(67, 67, 67)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateArmor)
                    .addComponent(btnUpdateArmor)
                    .addComponent(btnRemoveArmor))
                .addContainerGap(104, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGetAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetAllActionPerformed
        // TODO add your handling code here:
        checkSeverConnect();
        if (!checkUpdateStatus()) {
            JOptionPane.showMessageDialog(null, "don't finish UPDATE");
            return;
        } else {
            indexTBL = -1;
            tblArmor.clearSelection();
            try {
                listArmor = armorInter.findAllArmor();
                if (listArmor == null || listArmor.isEmpty()) {
                    DefaultTableModel model = (DefaultTableModel) tblArmor.getModel();
                    model.getDataVector().removeAllElements();
                    txtArmorID.setText("");
                    txtClassification.setText("");
                    txtTimeOfCreate.setText("");
                    txtDefense.setText("");
                    txtDescription.setText("");
                    txtStatus.setText("");
                    txtArmorID.setEnabled(true);
                    txtClassification.setEnabled(false);
                    txtDefense.setEnabled(false);
                    txtDescription.setEnabled(false);
                    txtStatus.setEnabled(false);
                    txtTimeOfCreate.setEnabled(false);

                    tblArmor.updateUI();
                    JOptionPane.showMessageDialog(null, "FILE 'ArmorData.txt' has no data");

                } else {
                    DefaultTableModel model = (DefaultTableModel) tblArmor.getModel();
                    model.getDataVector().removeAllElements();
                    for (ArmorDTO armorDTO : listArmor) {
                        Vector row = new Vector();
                        row.add(armorDTO.getArmorId());
                        row.add(armorDTO.getClassification());
                        row.add(formaT.format(armorDTO.getTimeOfCreate()));
                        row.add(armorDTO.getDefense());
                        model.addRow(row);

                        findByID = false;
                        txtArmorID.setEnabled(true);
                        btnFIndByArmorID.setText("Find By ArmorID");
                        txtArmorID.setText("");
                        txtClassification.setText("");
                        txtTimeOfCreate.setText("");
                        txtDefense.setText("");
                        txtDescription.setText("");
                        txtStatus.setText("");
                        txtArmorID.setEnabled(true);
                        txtClassification.setEnabled(false);
                        txtDefense.setEnabled(false);
                        txtDescription.setEnabled(false);
                        txtStatus.setEnabled(false);
                    }

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "error load get all ");
            }
        }
    }//GEN-LAST:event_btnGetAllActionPerformed

    private void tblArmorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblArmorMouseClicked
        // TODO add your handling code here:
        checkSeverConnect();
        checkClick();
        if (!checkUpdateStatus() && !createNew) {
            JOptionPane.showMessageDialog(null, "don't finish UPDATE");
            return;
        } else {

            indexTBL = tblArmor.getSelectedRow();
            //JOptionPane.showMessageDialog(null, indexTBL);
            if (indexTBL >= 0) {
                try {
                    String idGet = (String) tblArmor.getValueAt(indexTBL, 0);
                    //JOptionPane.showMessageDialog(null, idGet);
                    ArmorDTO armorGet = armorInter.findByArmorID(idGet);
                    //JOptionPane.showMessageDialog(null, armorGet);
                    txtArmorID.setText(armorGet.getArmorId());
                    txtClassification.setText(armorGet.getClassification());
                    txtTimeOfCreate.setText(formaT.format(armorGet.getTimeOfCreate()));
                    txtDefense.setText(armorGet.getDefense() + "");
                    txtDescription.setText(armorGet.getDescription());
                    txtStatus.setText(armorGet.getStatus());

                    txtClassification.setEnabled(true);
                    txtDefense.setEnabled(true);
                    txtDescription.setEnabled(true);
                    txtStatus.setEnabled(true);
                    txtArmorID.setEnabled(false);
                    //txtTimeOfCreate.setText("");
                    findByID = false;
                    btnFIndByArmorID.setText("Find By ArmorID");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }//GEN-LAST:event_tblArmorMouseClicked

    private void btnCreateArmorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateArmorActionPerformed
        // TODO add your handling code here: 
        checkSeverConnect();
        if (!checkUpdateStatus() && createNew == false) {
            JOptionPane.showMessageDialog(null, " don't finish UPDATE");
            indexTBL = tblArmor.getSelectedRow();
            return;
        } else {
            //neu dang chon 
            if (createNew == false) {
                createNew = true;
                whenAddNew();
                btnCreateArmor.setText("Save");

                findByID = false;
                txtArmorID.setEnabled(true);
                btnFIndByArmorID.setText("Find By ArmorID");
                txtArmorID.setText("");
                txtClassification.setText("");
                txtTimeOfCreate.setText("");
                txtDefense.setText("");
                txtDescription.setText("");
                txtStatus.setText("");
            } else if (createNew) {
                int ans = JOptionPane.showConfirmDialog(null, "Do you want to SAVE?", "SAVE ARMOR", JOptionPane.YES_NO_OPTION);
                if (ans == JOptionPane.YES_OPTION) {
                    try {
                        String armorID = txtArmorID.getText().trim();
                        String classification = txtClassification.getText().trim();
                        String defense = txtDefense.getText().trim();
                        String description = txtDescription.getText().trim();
                        Date time = Calendar.getInstance().getTime();
                        String status = txtStatus.getText().trim();
                        boolean check = checkValue.checkArmorId(armorID) && checkValue.checkClassification(classification)
                                && checkValue.checkDescription(description) && checkValue.checkDefense(defense)
                                && description.matches(".{0,30}");
                        if (armorID.isEmpty() || classification.isEmpty() || defense.isEmpty() || description.isEmpty() || status.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "have not entered enough information");
                            return;
                        } else if (!check) {
                            if (!checkValue.checkArmorId(armorID)) {
                                JOptionPane.showMessageDialog(null, " max length is 10 and not contains special characters (@, #, $)");
                            } else if (!checkValue.checkClassification(classification)) {
                                JOptionPane.showMessageDialog(null, "max length is 30");
                            } else if (!checkValue.checkDescription(description)) {
                                JOptionPane.showMessageDialog(null, "Max Desciption'S length is 30 character");
                            } else if (!checkValue.checkDefense(defense)) {
                                JOptionPane.showMessageDialog(null, "Defense must be integer number greater than 0");
                            } else if (!description.matches(".{0,30}")) {
                                JOptionPane.showMessageDialog(null, "please leave enter");
                            }
                            return;
                        } else if (armorInter.findByArmorID(armorID) != null) {
                            JOptionPane.showMessageDialog(null, "id already exists");
                            return;
                        } else {
                            ArmorDTO armorGet = new ArmorDTO(armorID, classification, description, status,
                                    time, Integer.parseInt(defense));
                            armorInter.createArmor(armorGet);
                            JOptionPane.showMessageDialog(null, "created successfully");

                            DefaultTableModel model = (DefaultTableModel) tblArmor.getModel();

                            Vector row = new Vector();
                            row.add(armorID);
                            row.add(classification);
                            row.add(formaT.format(time));
                            row.add(defense);
                            model.addRow(row);

                            txtArmorID.setEnabled(true);
                            findByID = false;
                            btnFIndByArmorID.setText("Find By ArmorID");
                            txtArmorID.setText("");
                            txtClassification.setText("");
                            txtTimeOfCreate.setText("");
                            txtDefense.setText("");
                            txtDescription.setText("");
                            txtStatus.setText("");

                            // btnGetAllActionPerformed(null);
                        }
                        closeCreate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (ans == JOptionPane.NO_OPTION) {
                    findByID = false;
                    btnFIndByArmorID.setText("Find By ArmorID");
                    closeCreate();
                }
            }
        }

    }//GEN-LAST:event_btnCreateArmorActionPerformed

    private void btnUpdateArmorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateArmorActionPerformed
        // TODO add your handling code here:
        checkSeverConnect();
        indexTBL = tblArmor.getSelectedRow();
        if (indexTBL < 0) {
            JOptionPane.showMessageDialog(null, "have not selected a row to update");
        } else {
            try {
                String armorID = txtArmorID.getText().trim();
                String classification = txtClassification.getText().trim();
                String defense = txtDefense.getText().trim();
                String description = txtDescription.getText().trim();
                Date time = Calendar.getInstance().getTime();
                String status = txtStatus.getText().trim();
                boolean check = checkValue.checkClassification(classification)
                        && checkValue.checkDescription(description) && checkValue.checkDefense(defense)
                        && description.matches(".{0,30}");
                if (classification.isEmpty() || defense.isEmpty() || description.isEmpty() || status.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "have not entered enough information");
                    return;
                } else if (!check) {
                    if (!checkValue.checkClassification(classification)) {
                        JOptionPane.showMessageDialog(null, "max length is 30");
                    } else if (!checkValue.checkDescription(description)) {
                        JOptionPane.showMessageDialog(null, "Max Desciption'S length is 30 character");
                    } else if (!checkValue.checkDefense(defense)) {
                        JOptionPane.showMessageDialog(null, "Defense must be integer number greater than 0");
                    } else if (!description.matches(".{0,30}")) {
                        JOptionPane.showMessageDialog(null, "please leave enter");
                    }
                } else {
                    ArmorDTO armorGet = new ArmorDTO(armorID, classification, description, status,
                            time, Integer.parseInt(defense));
                    armorInter.updateArmor(armorGet);
                    DefaultTableModel model = (DefaultTableModel) tblArmor.getModel();
                    model.setValueAt(classification, indexTBL, 1);
                    model.setValueAt(formaT.format(time), indexTBL, 2);
                    model.setValueAt(Integer.parseInt(defense), indexTBL, 3);

                    indexTBL = -1;
                    JOptionPane.showMessageDialog(null, "update successfully");
                    tblArmor.clearSelection();
                    findByID = false;
                    txtArmorID.setEnabled(true);
                    btnFIndByArmorID.setText("Find By ArmorID");
                    txtArmorID.setText("");
                    txtClassification.setText("");
                    txtTimeOfCreate.setText("");
                    txtDefense.setText("");
                    txtDescription.setText("");
                    txtStatus.setText("");
                    txtClassification.setEnabled(false);
                    txtDefense.setEnabled(false);
                    txtDescription.setEnabled(false);
                    txtStatus.setEnabled(false);
                    txtTimeOfCreate.setEnabled(false);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_btnUpdateArmorActionPerformed

    private void btnRemoveArmorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveArmorActionPerformed
        // TODO add your handling code here:
        checkSeverConnect();
        int indexTBL = tblArmor.getSelectedRow();
        if (indexTBL < 0) {
            JOptionPane.showMessageDialog(null, "have not selected a row to delete");
        } else if (!checkUpdateStatus()) {
            JOptionPane.showMessageDialog(null, " don't finish UPDATE");
            return;
        } else {
            try {
                String idGet = txtArmorID.getText();
                int ans = JOptionPane.showConfirmDialog(null, "Do you want to REMOVE?", "REMOVE ARMOR", JOptionPane.YES_NO_OPTION);
                if (ans == JOptionPane.YES_OPTION) {
                    boolean check = armorInter.removeArmor(idGet);
                    JOptionPane.showMessageDialog(null, "deleted successfully");

                    DefaultTableModel model = (DefaultTableModel) tblArmor.getModel();
                    model.removeRow(indexTBL);
                    indexTBL = -1;
                    tblArmor.clearSelection();
                    tblArmor.updateUI();
                    closeCreate();
                    //btnGetAllActionPerformed(null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnRemoveArmorActionPerformed

    private void btnFIndByArmorIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFIndByArmorIDActionPerformed
        // TODO add your handling code here:
        checkSeverConnect();
        if (tblArmor.getSelectedRow() >= 0) {
            return;
        } else if (findByID == false) {
            try {
                String idArmor = txtArmorID.getText();
                if (checkValue.checkArmorId(idArmor)) {
                    ArmorDTO armor1Click = armorInter.findByArmorID(idArmor);
                    if (idArmor.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "have not entered id");
                    } else if (armor1Click == null) {
                        JOptionPane.showMessageDialog(null, "id does not exist");
                        txtArmorID.setText("");
                        txtArmorID.requestFocus();
                    } else {
                        txtArmorID.setText(armor1Click.getArmorId());
                        txtClassification.setText(armor1Click.getClassification());
                        txtTimeOfCreate.setText(formaT.format(armor1Click.getTimeOfCreate()));
                        txtDefense.setText(armor1Click.getDefense() + "");
                        txtDescription.setText(armor1Click.getDescription());
                        txtStatus.setText(armor1Click.getStatus());
                        findByID = true;
                        txtArmorID.setEnabled(false);
                        btnFIndByArmorID.setText("Clean detail");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, " max length is 10 and not contains special characters (@, #, $)");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (findByID) {
            findByID = false;
            txtArmorID.setEnabled(true);
            btnFIndByArmorID.setText("Find By ArmorID");
            txtArmorID.setText("");
            txtClassification.setText("");
            // txtTimeOfCreateChoose.setText("");
            txtDefense.setText("");
            txtDescription.setText("");
            txtStatus.setText("");
        }
    }//GEN-LAST:event_btnFIndByArmorIDActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if (!checkUpdateStatus() || createNew) {
            JOptionPane.showMessageDialog(null, "Please complete the function before closing the program");
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        } else {
            System.exit(0);
        }

    }//GEN-LAST:event_formWindowClosing

    private void tblArmorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblArmorKeyReleased
        // TODO add your handling code here:
        int iindexTBL = tblArmor.getSelectedRow();
        if (iindexTBL >= 0) {
            tblArmor.setRowSelectionInterval(iindexTBL, iindexTBL);
            tblArmorMouseClicked(null);
        }
    }//GEN-LAST:event_tblArmorKeyReleased

    private void tblArmorMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblArmorMouseReleased
        // TODO add your handling code here:
        int iindexTBL = tblArmor.getSelectedRow();
        if (iindexTBL >= 0) {
            tblArmor.setRowSelectionInterval(iindexTBL, iindexTBL);
            tblArmorMouseClicked(null);
        }
    }//GEN-LAST:event_tblArmorMouseReleased

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
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateArmor;
    private javax.swing.JButton btnFIndByArmorID;
    private javax.swing.JButton btnGetAll;
    private javax.swing.JButton btnRemoveArmor;
    private javax.swing.JButton btnUpdateArmor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblArmor;
    private javax.swing.JTextField txtArmorID;
    private javax.swing.JTextField txtClassification;
    private javax.swing.JTextField txtDefense;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtTimeOfCreate;
    // End of variables declaration//GEN-END:variables
}
