/**
 * Copyright (c) 2014, the Temporal Random Indexing AUTHORS.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the University of Bari nor the names of its contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * GNU GENERAL PUBLIC LICENSE - Version 3, 29 June 2007
 *
 */
package di.uniba.it.tri.shell.gui;

import di.uniba.it.tri.api.Tri;
import di.uniba.it.tri.api.TriResultObject;
import di.uniba.it.tri.ir.SearchResult;
import di.uniba.it.tri.ir.Searcher;
import di.uniba.it.tri.shell.gui.data.ChartUtils;
import di.uniba.it.tri.shell.gui.data.Options;
import di.uniba.it.tri.shell.gui.data.TimePeriod;
import di.uniba.it.tri.shell.gui.data.WordEntry;
import di.uniba.it.tri.vectors.ObjectVector;
import di.uniba.it.tri.vectors.VectorReader;
import java.awt.Cursor;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.SwingWorker;
import org.apache.lucene.queryparser.classic.ParseException;
import org.jfree.chart.ChartPanel;

/**
 *
 * @author pierpaolo
 */
public class TriShellGUI extends javax.swing.JFrame {

    /**
     * Creates new form TriShellGUI
     */
    public TriShellGUI() {
        initComponents();
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

        mainTabbedPanel = new javax.swing.JTabbedPane();
        searchPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfQuery = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        topnSpinner = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultsTable = new javax.swing.JTable();
        tablePrev = new javax.swing.JButton();
        tableNext = new javax.swing.JButton();
        timeCheck = new javax.swing.JCheckBox();
        timeComboBox = new javax.swing.JComboBox<>();
        openb = new javax.swing.JButton();
        semanticCheck = new javax.swing.JCheckBox();
        triPanel = new javax.swing.JPanel();
        triToolbar = new javax.swing.JToolBar();
        getb = new javax.swing.JButton();
        simb = new javax.swing.JButton();
        nearb = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        simsb = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        plotb = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        wordJList = new javax.swing.JList<>();
        mainMenubar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuItemClose = new javax.swing.JMenuItem();
        menuClose = new javax.swing.JMenu();
        menuitemOptions = new javax.swing.JMenuItem();
        menuItemTimeSetting = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Temporal RI - (GUI ver. 0.10b)");
        setPreferredSize(new java.awt.Dimension(800, 600));

        mainTabbedPanel.setPreferredSize(new java.awt.Dimension(640, 480));

        searchPanel.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Query");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        searchPanel.add(jLabel1, gridBagConstraints);

        tfQuery.setColumns(25);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        searchPanel.add(tfQuery, gridBagConstraints);

        searchButton.setText("Search...");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        searchPanel.add(searchButton, gridBagConstraints);

        topnSpinner.setModel(new javax.swing.SpinnerNumberModel(25, 1, 1000, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        searchPanel.add(topnSpinner, gridBagConstraints);

        resultsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Content", "Score"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        resultsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resultsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(resultsTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        searchPanel.add(jScrollPane1, gridBagConstraints);

        tablePrev.setText("<-");
        tablePrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tablePrevActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 13;
        searchPanel.add(tablePrev, gridBagConstraints);

        tableNext.setText("->");
        tableNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tableNextActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 13;
        searchPanel.add(tableNext, gridBagConstraints);

        timeCheck.setText("Enable time search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        searchPanel.add(timeCheck, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 13;
        searchPanel.add(timeComboBox, gridBagConstraints);

        openb.setText("Open doc...");
        openb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openbActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        searchPanel.add(openb, gridBagConstraints);

        semanticCheck.setText("Semantic Search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        searchPanel.add(semanticCheck, gridBagConstraints);

        mainTabbedPanel.addTab("Search", searchPanel);

        triPanel.setLayout(new java.awt.BorderLayout());

        triToolbar.setRollover(true);

        getb.setText("Get");
        getb.setFocusable(false);
        getb.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getb.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        getb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getbActionPerformed(evt);
            }
        });
        triToolbar.add(getb);

        simb.setText("Sim");
        simb.setFocusable(false);
        simb.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        simb.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        simb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simbActionPerformed(evt);
            }
        });
        triToolbar.add(simb);

        nearb.setText("Near");
        nearb.setFocusable(false);
        nearb.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nearb.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        nearb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nearbActionPerformed(evt);
            }
        });
        triToolbar.add(nearb);
        triToolbar.add(jSeparator1);

        simsb.setText("Analyze");
        simsb.setFocusable(false);
        simsb.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        simsb.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        simsb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simsbActionPerformed(evt);
            }
        });
        triToolbar.add(simsb);
        triToolbar.add(jSeparator2);

        plotb.setText("Plot");
        plotb.setFocusable(false);
        plotb.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        plotb.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        plotb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotbActionPerformed(evt);
            }
        });
        triToolbar.add(plotb);

        triPanel.add(triToolbar, java.awt.BorderLayout.NORTH);

        wordJList.setModel((ListModel) this.wordListModel);
        jScrollPane2.setViewportView(wordJList);

        triPanel.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        mainTabbedPanel.addTab("Temporal RI", triPanel);

        getContentPane().add(mainTabbedPanel, java.awt.BorderLayout.CENTER);

        menuFile.setText("File");

        menuItemClose.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        menuItemClose.setText("Close");
        menuItemClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCloseActionPerformed(evt);
            }
        });
        menuFile.add(menuItemClose);

        mainMenubar.add(menuFile);

        menuClose.setText("Edit");

        menuitemOptions.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menuitemOptions.setText("Options...");
        menuitemOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemOptionsActionPerformed(evt);
            }
        });
        menuClose.add(menuitemOptions);

        menuItemTimeSetting.setText("Time setting...");
        menuItemTimeSetting.setEnabled(false);
        menuItemTimeSetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemTimeSettingActionPerformed(evt);
            }
        });
        menuClose.add(menuItemTimeSetting);

        mainMenubar.add(menuClose);

        setJMenuBar(mainMenubar);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuitemOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemOptionsActionPerformed
        try {
            optionsDialog.setVisible(true);
            Options options = optionsDialog.getOptions();
            if (options != null) {
                if (options.getIndex() != null) {
                    File newIndexFile = new File(options.getIndex());
                    if (!newIndexFile.equals(indexDir)) {
                        indexDir = newIndexFile;
                        searcher = new Searcher(indexDir);
                    }
                }
                if (options.getTriFolder() != null && !options.getTriFolder().equals(triFolder)) {
                    triApi.close();
                    triApi.setMaindir(options.getTriFolder());
                    List<String> years = triApi.year(0, Integer.MAX_VALUE);
                    Collections.sort(years);
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    LoadTriTask task = new LoadTriTask(years);
                    task.execute();
                    progressDialog.getLabel().setText("TRI loading...");
                    progressDialog.setVisible(true);
                    getDialog.getYearComboBox().setModel(yearListmodel);
                    //init time period dialog
                    DefaultComboBoxModel<String> tsModel = new DefaultComboBoxModel<>();
                    DefaultComboBoxModel<String> ysModel = new DefaultComboBoxModel<>();
                    for (int i = 0; i < yearListmodel.getSize(); i++) {
                        tsModel.addElement(yearListmodel.getElementAt(i));
                        ysModel.addElement(yearListmodel.getElementAt(i));
                    }
                    timeComboBox.setModel(ysModel);
                    timeSetupDialog = new TimeSetupDialog(this, true, tsModel);
                    menuItemTimeSetting.setEnabled(true);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error to set new options\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_menuitemOptionsActionPerformed

    private void menuItemCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_menuItemCloseActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        if (searcher == null) {
            JOptionPane.showMessageDialog(this, "You need to load a search engine. See options.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            String queryText = tfQuery.getText();
            if (queryText != null && queryText.length() > 0) {
                try {
                    if (timeCheck.isSelected()) {
                        Object item = timeComboBox.getSelectedItem();
                        if (item != null && timeSetupDialog.getPeriodMap().containsKey(item.toString())) {
                            if (semanticCheck.isSelected()) {
                                TimePeriod tp = timeSetupDialog.getPeriodMap().get(item.toString());
                                VectorReader vr = triApi.getStores().get(tp.getKey());
                                results = searcher.search(queryText, (Integer) topnSpinner.getModel().getValue(), tp.getStart(), tp.getEnd(), vr);
                            } else {
                                TimePeriod tp = timeSetupDialog.getPeriodMap().get(item.toString());
                                results = searcher.search(queryText, (Integer) topnSpinner.getModel().getValue(), tp.getStart(), tp.getEnd());
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "No valid time period", "Warning", JOptionPane.WARNING_MESSAGE);
                            results = searcher.search(queryText, ((Integer) topnSpinner.getModel().getValue()));
                        }
                    } else {
                        results = searcher.search(queryText, ((Integer) topnSpinner.getModel().getValue()));
                    }
                    refreshTable();
                } catch (ParseException | IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error to search\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void tablePrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tablePrevActionPerformed
        if (results != null) {
            if (offset - 10 >= 0) {
                offset -= 10;
                refreshTable();
            }
        }
    }//GEN-LAST:event_tablePrevActionPerformed

    private void tableNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tableNextActionPerformed
        if (results != null) {
            if (offset + 10 < results.size()) {
                offset += 10;
                refreshTable();
            }
        }
    }//GEN-LAST:event_tableNextActionPerformed

    private void resultsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultsTableMouseClicked
        if (evt.getClickCount() == 2) {
            int row = resultsTable.getSelectedRow();
            if (row >= 0) {
                JOptionPane.showInputDialog(this, resultsTable.getModel().getValueAt(row, 1), resultsTable.getModel().getValueAt(row, 0).toString(), JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_resultsTableMouseClicked

    private void getbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getbActionPerformed
        // TODO add your handling code here:
        getDialog.setVisible(true);
        if (getDialog.getWord() != null && getDialog.getYear() != null) {
            try {
                triApi.get(getDialog.getYear(), getDialog.getWord() + "_" + getDialog.getYear(), getDialog.getWord());
                this.wordListModel.addElement(new WordEntry(getDialog.getWord(), getDialog.getYear()));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error to get vector\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_getbActionPerformed

    private void simbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simbActionPerformed
        int[] selectedIndices = wordJList.getSelectedIndices();
        if (selectedIndices.length > 1) {
            try {
                WordEntry w1 = wordListModel.get(selectedIndices[0]);
                WordEntry w2 = wordListModel.get(selectedIndices[1]);
                double sim = triApi.sim(w1.getKeyLabel(), w2.getKeyLabel());
                JOptionPane.showMessageDialog(this, "Similarity between " + w1 + " and " + w2 + " = " + sim, "Similarity", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error to compute similarity\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_simbActionPerformed

    private void nearbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nearbActionPerformed
        int[] selectedIndices = wordJList.getSelectedIndices();
        if (selectedIndices.length > 0) {
            //ask year
            WordEntry we = wordListModel.get(selectedIndices[0]);
            String ti = JOptionPane.showInputDialog(this, "Please, insert temporal interval", we.getYear());
            try {
                List<ObjectVector> near = triApi.near(ti, we.getKeyLabel(), topnear);
                WordListDialog rsd = new WordListDialog(this, true, near);
                rsd.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error to compute neighborhood\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_nearbActionPerformed

    private void simsbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simsbActionPerformed
        if (yearListmodel.getSize() > 0) {
            DefaultComboBoxModel<String> cym1 = new DefaultComboBoxModel<>();
            DefaultComboBoxModel<String> cym2 = new DefaultComboBoxModel<>();
            for (int i = 0; i < yearListmodel.getSize(); i++) {
                cym1.addElement(yearListmodel.getElementAt(i));
                cym2.addElement(yearListmodel.getElementAt(i));
            }
            SimsDialog simsd = new SimsDialog(this, true);
            simsd.getComboBox1().setModel(cym1);
            simsd.getComboBox1().setSelectedIndex(0);
            simsd.getComboBox2().setModel(cym2);
            simsd.getComboBox2().setSelectedIndex(0);
            simsd.setVisible(true);
            String v1 = simsd.getComboBox1().getSelectedItem().toString();
            String v2 = simsd.getComboBox2().getSelectedItem().toString();
            double t1 = (double) simsd.getSliderMin().getValue() / 100d;
            double t2 = (double) simsd.getSliderMax().getValue() / 100d;
            try {
                List<ObjectVector> sims = triApi.sims(v1, v2, topsims, t1, t2);
                WordListDialog rsd = new WordListDialog(this, true, sims);
                rsd.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error to analyze TRI (sims)\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_simsbActionPerformed

    private void plotbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotbActionPerformed
        String firstTerm = JOptionPane.showInputDialog("Insert first term");
        String secondTerm = JOptionPane.showInputDialog("Insert second term");
        try {
            List<TriResultObject> plotWords = triApi.plotWords(firstTerm, secondTerm);
            ChartPanel chartPanel = ChartUtils.plotWords(plotWords, "Plot words over time", firstTerm + "-" + secondTerm);
            ChartDialog chartDialog = new ChartDialog(this, true, chartPanel);
            chartDialog.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error to plot words\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_plotbActionPerformed

    private void openbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openbActionPerformed
        int selectedRow = resultsTable.getSelectedRow();
        if (selectedRow > -1) {
            String id = resultsTable.getModel().getValueAt(selectedRow, 0).toString();
            String content = resultsTable.getModel().getValueAt(selectedRow, 1).toString();
            docDialog.setTitle("Document " + id);
            docDialog.setText(content);
            docDialog.setVisible(true);
        }
    }//GEN-LAST:event_openbActionPerformed

    private void menuItemTimeSettingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemTimeSettingActionPerformed
        timeSetupDialog.setVisible(true);
    }//GEN-LAST:event_menuItemTimeSettingActionPerformed

    private void refreshTable() {
        if (results != null) {
            int limit = Math.min(results.size(), offset + 10);
            int k = 0;
            for (int i = offset; i < limit; i++) {
                resultsTable.getModel().setValueAt(results.get(i).getId(), k, 0);
                resultsTable.getModel().setValueAt(results.get(i).getText(), k, 1);
                resultsTable.getModel().setValueAt(results.get(i).getScore(), k, 2);
                k++;
            }
            for (int i = k; i < 10; i++) {
                resultsTable.getModel().setValueAt(null, k, 0);
                resultsTable.getModel().setValueAt(null, k, 1);
                resultsTable.getModel().setValueAt(null, k, 2);
                k++;
            }
        }
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
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TriShellGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TriShellGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TriShellGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TriShellGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TriShellGUI().setVisible(true);
            }
        });
    }

    //Task
    class LoadTriTask extends SwingWorker<Void, Void> {

        final List<String> years;

        public LoadTriTask(List<String> years) {
            this.years = years;
        }

        @Override
        protected Void doInBackground() throws Exception {
            yearListmodel.removeAllElements();
            for (String year : years) {
                yearListmodel.addElement(year);
                triApi.load("mem", year, year);
            }
            return null;
        }

        @Override
        protected void done() {
            setCursor(null);
            progressDialog.dispose();
        }

    }
    // Start options

    private int topnear = 25;

    private int topsims = 500;

    // End options
    private DocDialog docDialog = new DocDialog(this, false);

    private TimeSetupDialog timeSetupDialog;

    private ProgressDialog progressDialog = new ProgressDialog(this, true);

    private GetDialog getDialog = new GetDialog(this, true);

    private DefaultComboBoxModel<String> yearListmodel = new DefaultComboBoxModel<>();

    private DefaultListModel<WordEntry> wordListModel = new DefaultListModel<>();

    private int offset = 0;

    private List<SearchResult> results = null;

    private Searcher searcher = null;

    private File indexDir = null;

    private Tri triApi = new Tri();

    private String triFolder = null;

    private OptionsDialog optionsDialog = new OptionsDialog(this, true);

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton getb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JMenuBar mainMenubar;
    private javax.swing.JTabbedPane mainTabbedPanel;
    private javax.swing.JMenu menuClose;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenuItem menuItemClose;
    private javax.swing.JMenuItem menuItemTimeSetting;
    private javax.swing.JMenuItem menuitemOptions;
    private javax.swing.JButton nearb;
    private javax.swing.JButton openb;
    private javax.swing.JButton plotb;
    private javax.swing.JTable resultsTable;
    private javax.swing.JButton searchButton;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JCheckBox semanticCheck;
    private javax.swing.JButton simb;
    private javax.swing.JButton simsb;
    private javax.swing.JButton tableNext;
    private javax.swing.JButton tablePrev;
    private javax.swing.JTextField tfQuery;
    private javax.swing.JCheckBox timeCheck;
    private javax.swing.JComboBox<String> timeComboBox;
    private javax.swing.JSpinner topnSpinner;
    private javax.swing.JPanel triPanel;
    private javax.swing.JToolBar triToolbar;
    private javax.swing.JList<String> wordJList;
    // End of variables declaration//GEN-END:variables

}
