package avltree;

import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Robin
 */
public class Gui extends javax.swing.JFrame {

    /**
     * Kmponenten der Oberflaeche werden Initialisiert.
     */
    public Gui() {
        initComponents();
        setLocationRelativeTo(null);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openItem = new javax.swing.JMenuItem();
        saveMenu = new javax.swing.JMenu();
        txtItem = new javax.swing.JMenuItem();
        jpegItem = new javax.swing.JMenuItem();
        newTreeItem = new javax.swing.JMenuItem();
        nodeMenu = new javax.swing.JMenu();
        addNodeItem = new javax.swing.JMenuItem();
        delNodeItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fileMenu.setText("File");
        fileMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileMenuActionPerformed(evt);
            }
        });

        openItem.setText("Open");
        openItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openItemActionPerformed(evt);
            }
        });
        fileMenu.add(openItem);

        saveMenu.setText("Save as");

        txtItem.setText("Txt");
        txtItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtItemActionPerformed(evt);
            }
        });
        saveMenu.add(txtItem);

        jpegItem.setText("jpeg");
        jpegItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpegItemActionPerformed(evt);
            }
        });
        saveMenu.add(jpegItem);

        fileMenu.add(saveMenu);

        newTreeItem.setText("New Tree");
        newTreeItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newTreeItemActionPerformed(evt);
            }
        });
        fileMenu.add(newTreeItem);

        jMenuBar1.add(fileMenu);

        nodeMenu.setText("Node");

        addNodeItem.setText("add Node");
        addNodeItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNodeItemActionPerformed(evt);
            }
        });
        nodeMenu.add(addNodeItem);

        delNodeItem.setText("delete Node");
        delNodeItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delNodeItemActionPerformed(evt);
            }
        });
        nodeMenu.add(delNodeItem);

        jMenuBar1.add(nodeMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Die Oberflache wird sichtbar gemacht
     */
    public void showGui() {
        this.setVisible(true);
    }

    /**
     * @param message Die auszugebene Nachricht Eine Nachricht wird dem User in
     * einem Seperaten Fenster angezeigt
     */
    public void showHint(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void addNodeItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNodeItemActionPerformed

        try {
            String Value = JOptionPane.showInputDialog("Wert des Knotens");
            Controler.getInstance().addNode(Integer.valueOf(Value));

        } catch (Exception e) {
            showHint("Falsche Eingabe!");
        }


    }//GEN-LAST:event_addNodeItemActionPerformed

    public void showGraph(JLabel l) {
        jScrollPane1.removeAll();
        jScrollPane1.add(l);
    }

//    private void loadGraph() {
//        try {
//            jScrollPane1.removeAll();
//            ImageIcon img = new ImageIcon(GraphCreator.getInstance().getGraph());
//            JLabel l = new JLabel(img);
//            l.setBounds(jScrollPane1.getBounds());
//            jScrollPane1.add(l);
//            repaint();
//        } catch (Exception e) {
//            e.printStackTrace();
//            showHint("Fehler beim laden!");
//        }
//
//    }

    private void fileMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileMenuActionPerformed
    }//GEN-LAST:event_fileMenuActionPerformed

    private void delNodeItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delNodeItemActionPerformed
        try {
            String Value = JOptionPane.showInputDialog("Wert des Knotens");
            Controler.getInstance().delNode(Integer.valueOf(Value));

        } catch (Exception e) {
            showHint("Falsche Eingabe!");
        }
    }//GEN-LAST:event_delNodeItemActionPerformed

    private void openItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openItemActionPerformed
        //Ein Filebrower wird geoeffnet und die ausgewaehlte Datei wird erstellt.
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == fileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try {
                //Aktuelle Images werden entfernt
                jScrollPane1.removeAll();
                if (file.getPath().endsWith(".jpeg")) {

                    //Die geladene Datei wird als Image in das Panel
                    ImageIcon img = new ImageIcon(file.getName());
                    JLabel l = new JLabel(img);
                    l.setBounds(jScrollPane1.getBounds());
                    jScrollPane1.add(l);
                } else if (file.getPath().endsWith(".txt")) {
                    Controler.getInstance().loadGraph(file.getAbsolutePath());
                } else {
                    showHint("Falscher Dateityp");
                }
                repaint();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }//GEN-LAST:event_openItemActionPerformed

    private void jpegItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpegItemActionPerformed
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == fileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            Controler.getInstance().saveTreeAsJpeg(file.getAbsolutePath());


            //Daten des Graphes holen und in file laden
            //file speichern
        } else {
            showHint("Problem beim speichern der Datei!");
        }
    }//GEN-LAST:event_jpegItemActionPerformed

    private void txtItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtItemActionPerformed
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == fileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            Controler.getInstance().saveTreeAsTxt(file.getAbsolutePath());

            //Daten des Graphes holen und in file laden
            //file speichern
        } else {
            showHint("Problem beim speichern der Datei!");
        }
    }//GEN-LAST:event_txtItemActionPerformed

    private void newTreeItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newTreeItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newTreeItemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addNodeItem;
    private javax.swing.JMenuItem delNodeItem;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem jpegItem;
    private javax.swing.JMenuItem newTreeItem;
    private javax.swing.JMenu nodeMenu;
    private javax.swing.JMenuItem openItem;
    private javax.swing.JMenu saveMenu;
    private javax.swing.JMenuItem txtItem;
    // End of variables declaration//GEN-END:variables
}
