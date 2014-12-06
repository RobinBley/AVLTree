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

    private static Gui instance = null;

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
        jPanel2 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openItem = new javax.swing.JMenuItem();
        saveItem = new javax.swing.JMenuItem();
        nodeMenu = new javax.swing.JMenu();
        addNodeItem = new javax.swing.JMenuItem();
        delNodeItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 614, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
        );

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

        saveItem.setText("Save");
        saveItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveItem);

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
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Das Singletonpattern wird angewand
     */
    public static Gui getInstance() {
        if (instance == null) {
            instance = new Gui();
        }
        return instance;
    }

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

        String Value = JOptionPane.showInputDialog("Wert des Knotens");

        //TEST
        loadGraph();


    }//GEN-LAST:event_addNodeItemActionPerformed

    private void loadGraph() {
        try {
            jPanel2.removeAll();
            ImageIcon img = new ImageIcon(GraphCreator.getInstance().getGraph());
            JLabel l = new JLabel(img);
            l.setBounds(jPanel2.getBounds());
            jPanel2.add(l);
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
            showHint("Fehler beim laden!");
        }

    }


    private void fileMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileMenuActionPerformed
    }//GEN-LAST:event_fileMenuActionPerformed

    private void delNodeItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delNodeItemActionPerformed
        String Value = JOptionPane.showInputDialog("Wert des Knotens");
    }//GEN-LAST:event_delNodeItemActionPerformed

    private void openItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openItemActionPerformed
        //Ein Filebrower wird geoeffnet und die ausgewaehlte Datei wird erstellt.
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == fileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try {
                //Aktuelle Images werden entfernt
                jPanel2.removeAll();
                //Die geladene Datei wird als Image in das Panel
                ImageIcon img = new ImageIcon(file.getName());
                JLabel l = new JLabel(img);
                l.setBounds(jPanel2.getBounds());
                jPanel2.add(l);
                repaint();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }//GEN-LAST:event_openItemActionPerformed

    private void saveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveItemActionPerformed
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == fileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            GraphCreator.getInstance().saveGraph(file.getAbsolutePath());

            //Daten des Graphes holen und in file laden
            //file speichern
        } else {
            showHint("Problem beim speichern der Datei!");
        }
    }//GEN-LAST:event_saveItemActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addNodeItem;
    private javax.swing.JMenuItem delNodeItem;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JMenu nodeMenu;
    private javax.swing.JMenuItem openItem;
    private javax.swing.JMenuItem saveItem;
    // End of variables declaration//GEN-END:variables
}
