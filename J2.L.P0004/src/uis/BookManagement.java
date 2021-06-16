/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uis;

import dtos.BookDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import validation.checkValue;

/**
 *
 * @author Le Phuoc Duy
 */
public class BookManagement extends javax.swing.JFrame {

    /**
     * Creates new form BookManagement
     */
    private DefaultTableModel model;
    private Vector<String> header;
    private Vector<Vector> data = null;
    private ArrayList<BookDTO> listArr = new ArrayList<>();
    private boolean addNew = false;

    public BookManagement() {
        initComponents();
        this.setSize(990, 550);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        header = new Vector<>();
        header.add("Book ID");
        header.add("Book Name");
        header.add("Author");
        header.add("Publisher");
        header.add("Published year");
        header.add("For rent");
        model = new DefaultTableModel(data, header);
        BookDTO book1 = new BookDTO("1", "1", "1", "1", 2020, true);
        BookDTO book2 = new BookDTO("2", "2", "2", "2", 2019, false);
        listArr.add(book1);
        listArr.add(book2);
        tbl.setModel(model);
        beforeGet();
    }

    void beforeGet() {
        txtId.setText("");
        txtName.setText("");
        txtAuthor.setText("");
        txtPublisher.setText("");
        cbxPY.setSelectedIndex(0);
        cbForRent.setSelected(false);

        txtId.setEnabled(false);
        txtName.setEnabled(false);
        txtAuthor.setEnabled(false);
        txtPublisher.setEnabled(false);
        cbxPY.setEnabled(false);
        cbForRent.setEnabled(false);

        tbl.setEnabled(false);
        btnFindById.setEnabled(false);
        btnGetAllBook.setEnabled(true);
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
        txtAuthor.setText("");
        txtPublisher.setText("");
        cbxPY.setSelectedIndex(0);
        cbForRent.setSelected(false);

        txtId.setEnabled(true);
        txtName.setEnabled(false);
        txtAuthor.setEnabled(false);
        txtPublisher.setEnabled(false);
        cbxPY.setEnabled(false);
        cbForRent.setEnabled(false);

        tbl.setEnabled(true);
        btnFindById.setEnabled(true);
        btnGetAllBook.setEnabled(true);
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
        txtAuthor.setText("");
        txtPublisher.setText("");
        cbxPY.setSelectedIndex(0);
        cbForRent.setSelected(false);

        txtId.setEnabled(true);
        txtName.setEnabled(true);
        txtAuthor.setEnabled(true);
        txtPublisher.setEnabled(true);
        cbxPY.setEnabled(true);
        cbForRent.setEnabled(true);

        tbl.setEnabled(false);
        tbl.clearSelection();
        btnFindById.setEnabled(false);
        btnGetAllBook.setEnabled(false);
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
        txtAuthor.setText("");
        txtPublisher.setText("");
        cbxPY.setSelectedIndex(0);
        cbForRent.setSelected(false);
        btnAddNew.setText("Add new");
        addNew = false;

        txtId.setEnabled(true);
        txtName.setEnabled(false);
        txtAuthor.setEnabled(false);
        txtPublisher.setEnabled(false);
        cbxPY.setEnabled(false);
        cbForRent.setEnabled(false);

        tbl.setEnabled(true);
        tbl.clearSelection();
        btnFindById.setEnabled(true);
        btnGetAllBook.setEnabled(true);
        btnSave.setEnabled(true);
        btnRemove.setEnabled(true);
        cbSort.setEnabled(true);
        btnSearchByName.setEnabled(true);
        txtSearchByName.setEnabled(true);
        btnAddNew.setEnabled(true);
    }

    int findByBookID(String id) {
        int findID = -1;
        for (int i = 0; i < listArr.size(); i++) {
            if (id.equals(listArr.get(i).getBookID())) {
                findID = i;
            }
        }
        return findID;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        cbSort = new javax.swing.JComboBox<>();
        btnSearchByName = new javax.swing.JButton();
        txtSearchByName = new javax.swing.JTextField();
        btnGetAllBook = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnAddNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        btnFindById = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        txtAuthor = new javax.swing.JTextField();
        txtPublisher = new javax.swing.JTextField();
        cbxPY = new javax.swing.JComboBox<>();
        cbForRent = new javax.swing.JCheckBox();
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

        btnGetAllBook.setText("Get all Book");
        btnGetAllBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetAllBookActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbSort, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnSearchByName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearchByName)
                .addGap(20, 20, 20))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addComponent(btnGetAllBook)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cbSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchByName)
                    .addComponent(txtSearchByName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnGetAllBook)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detailed part:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel2.setLayout(null);

        jLabel1.setText("Book ID:");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(57, 80, 41, 14);

        jLabel2.setText("Bookl name:");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(57, 123, 58, 14);

        jLabel4.setText("Author:");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(57, 165, 37, 14);

        jLabel5.setText("Publisher:");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(57, 207, 47, 14);

        jLabel6.setText("Published year:");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(57, 245, 74, 14);

        btnAddNew.setText("Add new");
        btnAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewActionPerformed(evt);
            }
        });
        jPanel2.add(btnAddNew);
        btnAddNew.setBounds(131, 380, 75, 23);

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel2.add(btnSave);
        btnSave.setBounds(217, 380, 57, 23);

        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });
        jPanel2.add(btnRemove);
        btnRemove.setBounds(293, 380, 71, 23);

        txtId.setPreferredSize(new java.awt.Dimension(150, 22));
        jPanel2.add(txtId);
        txtId.setBounds(136, 76, 150, 22);

        btnFindById.setText("Find by ID");
        btnFindById.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindByIdActionPerformed(evt);
            }
        });
        jPanel2.add(btnFindById);
        btnFindById.setBounds(291, 76, 81, 23);

        txtName.setPreferredSize(new java.awt.Dimension(200, 22));
        jPanel2.add(txtName);
        txtName.setBounds(136, 119, 200, 22);

        txtAuthor.setPreferredSize(new java.awt.Dimension(200, 22));
        jPanel2.add(txtAuthor);
        txtAuthor.setBounds(136, 161, 200, 22);

        txtPublisher.setPreferredSize(new java.awt.Dimension(200, 22));
        jPanel2.add(txtPublisher);
        txtPublisher.setBounds(136, 203, 200, 22);

        cbxPY.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021", "2020", "2019", "2018" }));
        jPanel2.add(cbxPY);
        cbxPY.setBounds(135, 245, 49, 20);

        cbForRent.setText("For rent");
        jPanel2.add(cbForRent);
        cbForRent.setBounds(130, 280, 65, 23);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        jLabel12.setText("Book Management");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(360, 360, 360))
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

    private void btnGetAllBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetAllBookActionPerformed
        // TODO add your handling code here:
        tbl.clearSelection();
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.getDataVector().removeAllElements();
        if (listArr.isEmpty() || listArr == null) {
            tbl.updateUI();
            JOptionPane.showMessageDialog(null, "The list is empty");
            beforeGet();
        } else {
            data = loadData(listArr);
            model = new DefaultTableModel(data, header);
            tbl.setModel(model);
            afterGet();
        }
    }//GEN-LAST:event_btnGetAllBookActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        String id = txtId.getText().trim(),
                name = txtName.getText().trim(),
                author = txtAuthor.getText().trim(),
                publisher = txtPublisher.getText().trim();
        int pYindex = cbxPY.getSelectedIndex();
        String pY = null;
        if (pYindex >= 0) {
            pY = cbxPY.getItemAt(pYindex);
        }
        boolean forRent = cbForRent.isSelected() ? true : false;
        boolean check = checkValue.checkBookID(id)
                && checkValue.checkString(name)
                && checkValue.checkString(author)
                && checkValue.checkString(publisher);
        if (addNew) {
            if (id.isEmpty() || name.isEmpty() || author.isEmpty() || publisher.isEmpty()) {
                JOptionPane.showMessageDialog(null, "have not entered enough information");
                return;
            } else if (!check) {
                if (!checkValue.checkBookID(id)) {
                    JOptionPane.showMessageDialog(null, "BookID max length is 10, not contains special characters");
                    txtId.setText("");
                } else if (!checkValue.checkString(name)) {
                    JOptionPane.showMessageDialog(null, "Book Name max length is 50");
                    txtName.setText("");
                } else if (!checkValue.checkString(author)) {
                    JOptionPane.showMessageDialog(null, "Author max length is 50");
                    txtAuthor.setText("");
                } else if (!checkValue.checkString(publisher)) {
                    JOptionPane.showMessageDialog(null, "Publisher max length is 50");
                    txtPublisher.setText("");
                }
                return;
            } else if (findByBookID(id) != -1) {
                JOptionPane.showMessageDialog(null, "id already exists");
                txtId.setText("");
                return;
            } else {
                BookDTO dto = new BookDTO(id, name, author, publisher, Integer.parseInt(pY), forRent);
                listArr.add(dto);
                JOptionPane.showMessageDialog(null, "Add new successfully");

                txtId.setEnabled(true);
                txtId.setText("");
                txtName.setText("");
                txtAuthor.setText("");
                txtPublisher.setText("");
                cbxPY.setSelectedIndex(0);
                cbForRent.setSelected(false);
                btnAddNew.setText("Add new");
            }
            closeAddNew();
            btnGetAllBookActionPerformed(null);
        } else {
            try {
                if (name.isEmpty() || author.isEmpty() || publisher.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "have not entered enough information");
                    return;
                } else if (!check) {
                    if (!checkValue.checkString(name)) {
                        JOptionPane.showMessageDialog(null, "Book Name max length is 50");
                        txtName.setText("");
                    } else if (!checkValue.checkString(author)) {
                        JOptionPane.showMessageDialog(null, "Author max length is 50");
                        txtAuthor.setText("");
                    } else if (!checkValue.checkString(publisher)) {
                        JOptionPane.showMessageDialog(null, "Publisher max length is 50");
                        txtPublisher.setText("");
                    }
                    return;
                } else {
                    for (BookDTO dto : listArr) {
                        if (id.equals(dto.getBookID())) {
                            dto.setBookName(name);
                            dto.setAuthor(author);
                            dto.setPublisher(publisher);
                            dto.setPublishedYear(Integer.parseInt(pY));
                            dto.setForRent(forRent);
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Update successfully");
                    btnGetAllBookActionPerformed(null);

                    tbl.clearSelection();
                    txtId.setText("");
                    txtName.setText("");
                    txtAuthor.setText("");
                    txtPublisher.setText("");
                    cbxPY.setSelectedIndex(0);
                    cbForRent.setSelected(false);

                    txtId.setEnabled(true);
                    txtName.setEnabled(false);
                    txtAuthor.setEnabled(false);
                    txtPublisher.setEnabled(false);
                    cbxPY.setEnabled(false);
                    cbForRent.setEnabled(false);
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
        String id = txtId.getText();
        if (checkValue.checkBookID(id)) {
            int dto = findByBookID(id);
            if (dto == -1) {
                JOptionPane.showMessageDialog(null, "id does not exist");
                txtId.setText("");
                txtId.requestFocus();
            } else {
                addNew = false;
                txtId.setEnabled(false);
                txtName.setText(listArr.get(dto).getBookName());
                txtAuthor.setText(listArr.get(dto).getAuthor());
                txtPublisher.setText(listArr.get(dto).getPublisher());
                for (int i = 0; i < cbxPY.getItemCount(); i++) {
                    if (cbxPY.getItemAt(i).equals(String.valueOf(listArr.get(dto).getPublishedYear()))) {
                        cbxPY.setSelectedIndex(i);
                    }
                }
                if (listArr.get(dto).isForRent()) {
                    cbForRent.setSelected(true);
                } else {
                    cbForRent.setSelected(false);
                }

                txtId.setEnabled(false);
                txtName.setEnabled(true);
                txtAuthor.setEnabled(true);
                txtPublisher.setEnabled(true);
                cbxPY.setEnabled(true);
                cbForRent.setEnabled(true);
                btnSave.setEnabled(true);
                btnRemove.setEnabled(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, " max length is 10 and not contains special characters (@, #, $)");
        }

    }//GEN-LAST:event_btnFindByIdActionPerformed

    private void tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseClicked
        // TODO add your handling code here:
        int row = tbl.getSelectedRow();
        int dto;
        if ((dto = findByBookID(tbl.getValueAt(row, 0).toString())) != -1) {
            addNew = false;
            txtId.setEnabled(false);
            txtId.setText(listArr.get(dto).getBookID());
            txtName.setText(listArr.get(dto).getBookName());
            txtAuthor.setText(listArr.get(dto).getAuthor());
            txtPublisher.setText(listArr.get(dto).getPublisher());
            for (int i = 0; i < cbxPY.getItemCount(); i++) {
                if (cbxPY.getItemAt(i).equals(String.valueOf(listArr.get(dto).getPublishedYear()))) {
                    cbxPY.setSelectedIndex(i);
                }
            }
            if (listArr.get(dto).isForRent()) {
                cbForRent.setSelected(true);
            } else {
                cbForRent.setSelected(false);
            }

            txtId.setEnabled(false);
            txtName.setEnabled(true);
            txtAuthor.setEnabled(true);
            txtPublisher.setEnabled(true);
            cbxPY.setEnabled(true);
            cbForRent.setEnabled(true);
            btnSave.setEnabled(true);
            btnRemove.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Not find id, maybe data is changed");
            btnFindByIdActionPerformed(null);
        }
    }//GEN-LAST:event_tblMouseClicked

    private void btnSearchByNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchByNameActionPerformed
        // TODO add your handling code here:
        String name = txtSearchByName.getText();
        ArrayList<BookDTO> listArr2 = new ArrayList();
        try {
            for (BookDTO obj : listArr) {
                if (obj.getBookName().indexOf(name) >= 0) {
                    listArr2.add(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (listArr2.size() == 0) {
            JOptionPane.showMessageDialog(this, "Not found");
            txtSearchByName.setText("");
            return;
        }
        Vector<Vector> list = new Vector<>();
        Vector obj = null;
        for (BookDTO dto : listArr2) {
            obj = new Vector<>();
            String forRent = dto.isForRent() ? "True" : "False";
            obj.add(dto.getBookID());
            obj.add(dto.getBookName());
            obj.add(dto.getAuthor());
            obj.add(dto.getPublisher());
            obj.add(dto.getPublishedYear());
            obj.add(forRent);
            list.add(obj);
        }

        model = new DefaultTableModel(list, header);
        tbl.setModel(model);
        txtSearchByName.setText("");
    }//GEN-LAST:event_btnSearchByNameActionPerformed

    private void cbSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSortActionPerformed
        // TODO add your handling code here:
        int pos = cbSort.getSelectedIndex();
        try {
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

    }//GEN-LAST:event_cbSortActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // TODO add your handling code here:
        try {
            String id = txtId.getText().trim();
            int ans = JOptionPane.showConfirmDialog(null, "Do you want to REMOVE?", "REMOVE ARMOR", JOptionPane.YES_NO_OPTION);
            if (ans == JOptionPane.YES_OPTION) {
                if (findByBookID(id) != -1) {
                    listArr.remove(findByBookID(id));
                    JOptionPane.showMessageDialog(null, "deleted successfully");
                }
                btnGetAllBookActionPerformed(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private Vector loadData(ArrayList<BookDTO> listArr) {

        Vector list = new Vector();
        Vector<String> obj = null;

        for (BookDTO dto : listArr) {
            obj = new Vector<>();
            obj.add(dto.getBookID());
            obj.add(dto.getBookName());
            obj.add(dto.getAuthor());
            obj.add(dto.getPublisher());
            obj.add(dto.getPublishedYear() + "");
            obj.add(dto.isForRent() + "");
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
            java.util.logging.Logger.getLogger(BookManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNew;
    private javax.swing.JButton btnFindById;
    private javax.swing.JButton btnGetAllBook;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearchByName;
    private javax.swing.JCheckBox cbForRent;
    private javax.swing.JComboBox<String> cbSort;
    private javax.swing.JComboBox<String> cbxPY;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl;
    private javax.swing.JTextField txtAuthor;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPublisher;
    private javax.swing.JTextField txtSearchByName;
    // End of variables declaration//GEN-END:variables
}
