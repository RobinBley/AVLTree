package avltree;

import java.awt.Color;
import java.awt.HeadlessException;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Robin
 */
public class Gui extends javax.swing.JFrame {

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
    private javax.swing.JPanel panel;

    /**
     * Kmponenten der Oberflaeche werden Initialisiert.
     */
    public Gui() {
        initComponents();
        jScrollPane1.setWheelScrollingEnabled(true);
        setLocationRelativeTo(null);

    }

    private void initComponents() {
        //Initialisierung der Komponenten
        fileChooser = new javax.swing.JFileChooser();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        //Hintergrund des Panels der Oberflache wird Weiß gesetzt
        panel.setBackground(Color.WHITE);
        //Dem Scrollpane wird das Panel hinzugefuegt und Autoscroll wird aktiviert
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setViewportView(panel);
        //Bei einem Klick auf das Kreuz der Oberflaeche wird das Programm beendet
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //Den Komponenten des Menus werden ActionListener und Namen hinzugefuegt
        fileMenu.setText("File");
        openItem.setText("Open");
        openItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openItemActionPerformed(evt);
            }
        });
        fileMenu.add(openItem);
        saveMenu.setText("Save as");
        txtItem.setText("Txt");
        txtItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtItemActionPerformed(evt);
            }
        });
        saveMenu.add(txtItem);
        jpegItem.setText("jpeg");
        jpegItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpegItemActionPerformed(evt);
            }
        });
        saveMenu.add(jpegItem);
        fileMenu.add(saveMenu);
        newTreeItem.setText("New Tree");
        newTreeItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newTreeItemActionPerformed(evt);
            }
        });
        fileMenu.add(newTreeItem);
        jMenuBar1.add(fileMenu);
        nodeMenu.setText("Node");
        addNodeItem.setText("add Node");
        addNodeItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNodeItemActionPerformed(evt);
            }
        });
        nodeMenu.add(addNodeItem);
        delNodeItem.setText("delete Node");
        delNodeItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delNodeItemActionPerformed(evt);
            }
        });
        nodeMenu.add(delNodeItem);
        jMenuBar1.add(nodeMenu);
        setJMenuBar(jMenuBar1);
        //Die Menuitems zum speichern und bearbeiten des Baums werden ausgegraut
        nodeMenu.setEnabled(false);
        saveMenu.setEnabled(false);
        //Das Scrollpane wird der Oberflaeche hinzugefuegt
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
    private void addNodeItemActionPerformed(java.awt.event.ActionEvent evt) {

        //Ein seperates Fenster wird gestartet, in dem der User ein Wert eingeben soll
        String Value = JOptionPane.showInputDialog("Wert des Knotens");
        try {
            //Der eingegebene Wert wird dem binaeren Baum hinzugefuegt
            Controler.getInstance().addNode(Integer.valueOf(Value));

        } catch (Exception e) {
            showHint("Falsche Eingabe! Bitte nur Integer");
        }

    }

    /**
     * Eine Grafik wird in der Oberflache angezeigt
     *
     * @param img Ein Image, welches den Graphen als Grafik enthaelt
     */
    public void showGraph(ImageIcon img) {
        JLabel l = new JLabel(img);
        //Alle Komponenten des Panels werden entfernt
        panel.removeAll();
        //Dem Panel wird ein Laben hinzugefuegt, welches dem Baum als Grafik enthalt
        panel.add(l);
        panel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
    }

    /**
     * Der User wird aufgefordert ein Wert einzugeben, welcher aus dem Binaeren
     * Baum entfernt werden soll
     */
    private void delNodeItemActionPerformed(java.awt.event.ActionEvent evt) {
        //Ein seperates Fenster wird gestartet, in dem der User ein Wert eingeben soll
        String Value = JOptionPane.showInputDialog("Wert des Knotens");
        try {
            //Der eingegebene Wert wird aus dem Baum entfernt
            Controler.getInstance().delNode(Integer.valueOf(Value));

        } catch (Exception e) {
            showHint("Falsche Eingabe! Bitte nur Integer");
        }
    }

    /**
     * Ein Filebrowser wird gestartet, in dem der User eine Datei auswaehlen
     * kann, welches einen Graphen enthaelt. Dieser Graph wird je nach Dateityp
     * geladen und ist nun veraenderbar oder nicht.
     *
     */
    private void openItemActionPerformed(java.awt.event.ActionEvent evt) {
        //Ein Filebrower wird geoeffnet und die ausgewaehlte Datei wird erstellt.
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try {
                //Wenn die ausgewaehlte datei von typ JPEG ist...
                if (!file.getPath().endsWith(".txt")) {
                    //wird die geladene Datei als Grafik in das Panel geladen
                    showGraph(new ImageIcon(file.getAbsolutePath()));

                    //Wenn die ausgewaehlte datei von typ txt ist..
                } else {
                    //werden die Daten eines Binaeren Baums geladen. Aus diesen Daten wird eine Grafik generiert. 
                    Controler.getInstance().loadGraph(file.getAbsolutePath());
                    repaint();

                }
                nodeMenu.setEnabled(false);
                saveMenu.setEnabled(false);

            } catch (Exception ex) {
                showHint("Falscher Dateityp");
            }

        }
    }

    /**
     * Der aktuell geladene binaere Baum wird in eine Datei als JPEG-Format
     * gespeichert
     */
    private void jpegItemActionPerformed(java.awt.event.ActionEvent evt) {
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == fileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            //Daten des Graphes holen und in file laden und speichern
            Controler.getInstance().saveTreeAsJpeg(file.getAbsolutePath());

        } else {
            showHint("Problem beim speichern der Datei!");
        }
    }

    /**
     * Der aktuell geladene binaere Baum wird in eine Datei als txt-Format
     * gespeichert
     */
    private void txtItemActionPerformed(java.awt.event.ActionEvent evt) {
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            //Daten des Graphes holen und in file laden und speichern
            Controler.getInstance().saveTreeAsTxt(file.getAbsolutePath());

        } else {
            showHint("Problem beim speichern der Datei!");
        }
    }

    /**
     * Der aktuel geladene binaere Baum wird geloescht und ein neuer wird
     * erzeugt.
     */
    private void newTreeItemActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            //Der User wird in einem seperaten Fenster aufgefordert den ersten Wert des neuen Baums einzugeben
            String Value = JOptionPane.showInputDialog("Wert des ersten Knotens");
            //Ein neuer Baum wird erzeugt, welcher den vom User eingegebenen Wert enthaelt
            Controler.getInstance().newTree(Integer.valueOf(Value));
            //Die Menuitems zum speichern und bearbeiten des Baums werden aktiviert
            saveMenu.setEnabled(true);
            nodeMenu.setEnabled(true);
        } catch (HeadlessException | NumberFormatException e) {
            showHint("Falsche Eingabe! Bitte nur Integer");
        }
    }

}
