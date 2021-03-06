/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.it.tri.shell.gui.occ;

import di.uniba.it.tri.api.occ.OccAPI;
import di.uniba.it.tri.api.occ.Word;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 *
 * @author pierpaolo
 */
public class OccSearchGUI extends javax.swing.JFrame {

    /**
     * Creates new form OccSearchGUI
     */
    public OccSearchGUI() {
        initComponents();
        myInit();
    }

    private void myInit() {
        fileChooser = new JFileChooser();
        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        fileChooser.setDialogTitle("Open main directory");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setCurrentDirectory(new File("./"));

        saveFileChooser = new JFileChooser();
        fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooser.setDialogTitle("Export...");
        fileChooser.setCurrentDirectory(new File("./"));
    }

    private void initAPI(File file) throws IOException {
        api = new OccAPI(file);
        Map<Integer, File> fileMap = api.getFileMap();
        List<Integer> years = new ArrayList<>(fileMap.keySet());
        Collections.sort(years);
        DefaultComboBoxModel<Integer> modelStart = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<Integer> modelEnd = new DefaultComboBoxModel<>();
        for (Integer year : years) {
            modelStart.addElement(year);
            modelEnd.addElement(year);
        }
        comboBoxStart.setModel(modelStart);
        if (modelStart.getSize() > 0) {
            comboBoxStart.setSelectedIndex(0);
        }
        comboBoxEnd.setModel(modelEnd);
        if (modelEnd.getSize() > 0) {
            comboBoxEnd.setSelectedIndex(0);
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

        jToolBar1 = new javax.swing.JToolBar();
        searchTextField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        comboBoxStart = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        comboBoxEnd = new javax.swing.JComboBox<>();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTextArea = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        buttonResultPrev = new javax.swing.JButton();
        buttonResultNext = new javax.swing.JButton();
        comboYearResult = new javax.swing.JComboBox<>();
        mainMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        fileOpenMenu = new javax.swing.JMenuItem();
        fileExportMenu = new javax.swing.JMenuItem();
        fileExportAllMenu = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        fileExitMenu = new javax.swing.JMenuItem();
        optionMenu = new javax.swing.JMenu();
        optionsPreferencesMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Google N-gram co-occurrences ");

        jToolBar1.setRollover(true);

        searchTextField.setColumns(20);
        jToolBar1.add(searchTextField);

        jButton1.setText("Search");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);
        jToolBar1.add(jSeparator2);

        comboBoxStart.setPreferredSize(new java.awt.Dimension(90, 27));
        jToolBar1.add(comboBoxStart);

        jLabel1.setText("TO");
        jToolBar1.add(jLabel1);

        comboBoxEnd.setPreferredSize(new java.awt.Dimension(90, 27));
        jToolBar1.add(comboBoxEnd);
        jToolBar1.add(jSeparator3);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.NORTH);

        resultTextArea.setEditable(false);
        resultTextArea.setColumns(20);
        resultTextArea.setRows(5);
        jScrollPane1.setViewportView(resultTextArea);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        buttonResultPrev.setText("<<");
        buttonResultPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonResultPrevActionPerformed(evt);
            }
        });
        jPanel1.add(buttonResultPrev);

        buttonResultNext.setText(">>");
        buttonResultNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonResultNextActionPerformed(evt);
            }
        });
        jPanel1.add(buttonResultNext);

        comboYearResult.setPreferredSize(new java.awt.Dimension(90, 27));
        comboYearResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboYearResultActionPerformed(evt);
            }
        });
        jPanel1.add(comboYearResult);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        fileMenu.setText("File");

        fileOpenMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        fileOpenMenu.setText("Open...");
        fileOpenMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileOpenMenuActionPerformed(evt);
            }
        });
        fileMenu.add(fileOpenMenu);

        fileExportMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        fileExportMenu.setText("Export...");
        fileExportMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileExportMenuActionPerformed(evt);
            }
        });
        fileMenu.add(fileExportMenu);

        fileExportAllMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        fileExportAllMenu.setText("Export all...");
        fileExportAllMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileExportAllMenuActionPerformed(evt);
            }
        });
        fileMenu.add(fileExportAllMenu);
        fileMenu.add(jSeparator1);

        fileExitMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        fileExitMenu.setText("Exit");
        fileExitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileExitMenuActionPerformed(evt);
            }
        });
        fileMenu.add(fileExitMenu);

        mainMenuBar.add(fileMenu);

        optionMenu.setText("Options");

        optionsPreferencesMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        optionsPreferencesMenu.setText("Preferences....");
        optionsPreferencesMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionsPreferencesMenuActionPerformed(evt);
            }
        });
        optionMenu.add(optionsPreferencesMenu);

        mainMenuBar.add(optionMenu);

        setJMenuBar(mainMenuBar);

        setSize(new java.awt.Dimension(800, 600));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void fileOpenMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileOpenMenuActionPerformed
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                initAPI(fileChooser.getSelectedFile());
            } catch (IOException ex) {
                Logger.getLogger(OccSearchGUI.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_fileOpenMenuActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (api != null) {
            queryWord = searchTextField.getText().trim();
            int startInt = comboBoxStart.getSelectedIndex();
            int endInt = comboBoxEnd.getSelectedIndex();
            if (startInt >= 0 && startInt <= endInt) {
                queryYears = new int[endInt - startInt + 1];
                for (int k = startInt; k <= endInt; k++) {
                    queryYears[k - startInt] = comboBoxStart.getModel().getElementAt(k);
                }
                SearchTask task = new SearchTask();
                task.execute();
                pd.setVisible(true);
                initComboResults();
                showResult();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please, set the main directory.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void buttonResultPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonResultPrevActionPerformed
        // TODO add your handling code here:
        if (comboYearResult.getSelectedIndex() > 0) {
            comboYearResult.setSelectedIndex(comboYearResult.getSelectedIndex() - 1);
            showResult();
        }
    }//GEN-LAST:event_buttonResultPrevActionPerformed

    private void buttonResultNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonResultNextActionPerformed
        // TODO add your handling code here:
        if (comboYearResult.getSelectedIndex() < (comboYearResult.getModel().getSize() - 1)) {
            comboYearResult.setSelectedIndex(comboYearResult.getSelectedIndex() + 1);
            showResult();
        }
    }//GEN-LAST:event_buttonResultNextActionPerformed

    private void fileExitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileExitMenuActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_fileExitMenuActionPerformed

    private void comboYearResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboYearResultActionPerformed
        showResult();
    }//GEN-LAST:event_comboYearResultActionPerformed

    private void fileExportAllMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileExportAllMenuActionPerformed
        if (occurrences != null && occurrences.size() > 0) {
            if (saveFileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = saveFileChooser.getSelectedFile();
                try {
                    Appendable out = new FileWriter(file);
                    CSVPrinter printer = CSVFormat.EXCEL.withHeader("year", "word", "score").withDelimiter('\t').print(out);
                    List<Integer> years = new ArrayList<>(occurrences.keySet());
                    Collections.sort(years);
                    for (Integer year : years) {
                        List<Word> list = occurrences.get(year);
                        for (Word word : list) {
                            printer.printRecord(year, word.getWord(), word.getScore());
                        }
                    }
                    printer.close();
                } catch (IOException ex) {
                    Logger.getLogger(OccSearchGUI.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_fileExportAllMenuActionPerformed

    private void fileExportMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileExportMenuActionPerformed
        Object selectedItem = comboYearResult.getSelectedItem();
        if (selectedItem != null) {
            if (saveFileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = saveFileChooser.getSelectedFile();
                try {
                    Appendable out = new FileWriter(file);
                    CSVPrinter printer = CSVFormat.EXCEL.withHeader("year", "word", "score").withDelimiter('\t').print(out);
                    List<Word> list = occurrences.get((Integer) selectedItem);
                    for (Word word : list) {
                        printer.printRecord((Integer) selectedItem, word.getWord(), word.getScore());
                    }
                    printer.close();
                } catch (IOException ex) {
                    Logger.getLogger(OccSearchGUI.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_fileExportMenuActionPerformed

    private void optionsPreferencesMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionsPreferencesMenuActionPerformed
        optionsDialog.setVisible(true);
        this.searchSize = optionsDialog.getRusutsSize();
    }//GEN-LAST:event_optionsPreferencesMenuActionPerformed

    private void initComboResults() {
        if (occurrences != null) {
            DefaultComboBoxModel<Integer> modelYearResult = new DefaultComboBoxModel<>();
            List<Integer> years = new ArrayList<>(occurrences.keySet());
            Collections.sort(years);
            for (Integer year : years) {
                modelYearResult.addElement(year);
            }
            comboYearResult.setModel(modelYearResult);
            if (modelYearResult.getSize() > 0) {
                comboYearResult.setSelectedIndex(0);
            }
        }
    }

    private void showResult() {
        Object selectedItem = comboYearResult.getSelectedItem();
        if (selectedItem != null) {
            List<Word> list = occurrences.get((Integer) selectedItem);
            if (list != null) {
                StringBuilder sb = new StringBuilder();
                for (Word word : list) {
                    sb.append(word.getWord()).append("\t").append(String.valueOf(word.getScore())).append("\n");
                }
                resultTextArea.setText(sb.toString());
                resultTextArea.setCaretPosition(0);
            } else {
                resultTextArea.setText("No results!");
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OccSearchGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OccSearchGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OccSearchGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OccSearchGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OccSearchGUI().setVisible(true);
            }
        });
    }

    private OccAPI api = null;
    private JFileChooser fileChooser;
    private JFileChooser saveFileChooser;
    private Map<Integer, List<Word>> occurrences;
    private String queryWord;
    private int[] queryYears;
    private final ProgressSearchDialog pd = new ProgressSearchDialog(this, true);
    private int searchSize = 1000;
    private final OptionsDialog optionsDialog = new OptionsDialog(this, true, searchSize);

    class SearchTask extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {
            if (queryWord != null && queryYears != null) {
                try {
                    occurrences = api.getOccurrences(queryWord, queryYears, searchSize);
                } catch (IOException ex) {
                    Logger.getLogger(OccSearchGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return null;
        }

        @Override
        protected void done() {
            pd.setVisible(false);
            pd.dispose();
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonResultNext;
    private javax.swing.JButton buttonResultPrev;
    private javax.swing.JComboBox<Integer> comboBoxEnd;
    private javax.swing.JComboBox<Integer> comboBoxStart;
    private javax.swing.JComboBox<Integer> comboYearResult;
    private javax.swing.JMenuItem fileExitMenu;
    private javax.swing.JMenuItem fileExportAllMenu;
    private javax.swing.JMenuItem fileExportMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem fileOpenMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JMenu optionMenu;
    private javax.swing.JMenuItem optionsPreferencesMenu;
    private javax.swing.JTextArea resultTextArea;
    private javax.swing.JTextField searchTextField;
    // End of variables declaration//GEN-END:variables
}
