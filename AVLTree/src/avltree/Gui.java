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
     * Die Oberflache wird sichtbar
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

    /**
     * Der User wird aufgefordert ein Wert einzugeben, welcher dem Binaeren Baum
     * hinzugefuegt werden soll
     */
    private void addNodeItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNodeItemActionPerformed

        try {
            //Ein seperates Fenster wird gestartet, in dem der User ein Wert eingeben soll
            String Value = JOptionPane.showInputDialog("Wert des Knotens");
            //Der eingegebene Wert wird dem binaeren Baum hinzugefuegt
            controler.addNode(Integer.valueOf(Value));

        } catch (Exception e) {
            showHint("Falsche Eingabe!");
        }


    }//GEN-LAST:event_addNodeItemActionPerformed

    /**
     * Eine Grafik wird in der Oberflache angezeigt
     *
     * @param l Ein Label, welches den Graphen als Grafik enthaelt
     */
    public void showGraph(JLabel l) {
        jScrollPane1.removeAll();
        jScrollPane1.add(l);
        repaint();
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
    /**
     *
     * @deprecated
     */
    private void fileMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileMenuActionPerformed
    }//GEN-LAST:event_fileMenuActionPerformed

    /**
     * Der User wird aufgefordert ein Wert einzugeben, welcher aus dem Binaeren
     * Baum entfernt werden soll
     */
    private void delNodeItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delNodeItemActionPerformed
        try {
            //Ein seperates Fenster wird gestartet, in dem der User ein Wert eingeben soll
            String Value = JOptionPane.showInputDialog("Wert des Knotens");
            //Der eingegebene Wert wird aus dem Baum entfernt
            controler.delNode(Integer.valueOf(Value));

        } catch (Exception e) {
            showHint("Falsche Eingabe!");
        }
    }//GEN-LAST:event_delNodeItemActionPerformed

    /**
     * Ein Filebrowser wird gestartet, in dem der User eine Datei auswaehlen
     * kann, welches einen Graphen enthaelt. Dieser Graph wird je nach Dateityp
     * geladen und ist nun veraenderbar oder nicht.
     *
     */
    private void openItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openItemActionPerformed
        //Ein Filebrower wird geoeffnet und die ausgewaehlte Datei wird erstellt.
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == fileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try {
                //Aktuelle Inhalte des Panels werden entfernt
                jScrollPane1.removeAll();
                //Wenn die ausgewaehlte datei von typ JPEG ist...
                if (file.getPath().endsWith(".jpeg")) {
                    //wird die geladene Datei als Grafik in das Panel geladen
                    ImageIcon img = new ImageIcon(file.getName());
                    JLabel l = new JLabel(img);
                    l.setBounds(jScrollPane1.getBounds());
                    jScrollPane1.add(l);
                    nodeMenu.setEnabled(false);
                    //Wenn die ausgewaehlte datei von typ txt ist..
                } else if (file.getPath().endsWith(".txt")) {
                    //werden die Daten eines Binaeren Baums geladen. Aus diesen Daten wird eine Grafik generiert. 
                    controler.loadGraph(file.getAbsolutePath());
                    nodeMenu.setEnabled(true);
                } else {
                    showHint("Falscher Dateityp");
                }
                repaint();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }//GEN-LAST:event_openItemActionPerformed

    
    /**
     * Der aktuell geladene binaere Baum wird in eine Datei als JPEG-Format gespeichert
     */
    private void jpegItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpegItemActionPerformed
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == fileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            controler.saveTreeAsJpeg(file.getAbsolutePath());

            //Daten des Graphes holen und in file laden
            //file speichern
        } else {
            showHint("Problem beim speichern der Datei!");
        }
    }//GEN-LAST:event_jpegItemActionPerformed

      /**
     * Der aktuell geladene binaere Baum wird in eine Datei als txt-Format gespeichert
     */
    private void txtItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtItemActionPerformed
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == fileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            controler.saveTreeAsTxt(file.getAbsolutePath());

            //Daten des Graphes holen und in file laden
            //file speichern
        } else {
            showHint("Problem beim speichern der Datei!");
        }
    }//GEN-LAST:event_txtItemActionPerformed

    /**
     * Der aktuel geladene binaere Baum wird geloescht und ein neuer wird erzeugt.
     */
    private void newTreeItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newTreeItemActionPerformed
        try {
            String Value = JOptionPane.showInputDialog("Wert des ersten Knotens");
            controler.newTree(Integer.valueOf(Value));

        } catch (Exception e) {
            showHint("Falsche Eingabe!");
        }
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
    private Controler controler = Controler.getInstance();
}
