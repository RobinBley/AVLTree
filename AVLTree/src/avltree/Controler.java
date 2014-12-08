/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avltree;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Robin
 */
public class Controler {

    private Gui gui;
    private GraphCreator creator;
    private AVLTree tree = null;
    private static Controler Instance = null;

    public void Controler() {
        gui = new Gui();
        creator = new GraphCreator();
    }

    public static Controler getInstance() {
        if (Instance == null) {
            Instance = new Controler();
        }
        return Instance;

    }

    public AVLTree getTree() {
        return tree;
    }

    public Gui getGui() {
        return gui;
    }

    public void newTree(int root) {
        tree = new AVLTree(new AVLNode(root));
        this.showCurrentGraph();
    }

    public void loadGraph(String path) {
        creator.loadGraph(path);

        //Image der Gui uebergeben
        gui.showGraph(null);
    }

    public void saveTreeAsJpeg(String path) {
        creator.saveGraphAsJpeg(path);
    }

    public void saveTreeAsTxt(String path) {
        creator.saveGraphAsTxt(path);
    }

    private void showCurrentGraph() {
        try {
            creator.createGraph(tree);
            ImageIcon img = new ImageIcon(creator.getGraph());
            JLabel l = new JLabel(img);
            l.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
            gui.showGraph(l);
        } catch (Exception e) {
        }

    }

    public boolean addNode(int node) {
        if (tree == null) {
            newTree(node);
        } else {
            try {
                tree.insertNode(node);
                showCurrentGraph();
            } catch (Exception ex) {
                return false;
            }

        }
        return true;

    }

    public void delNode(int node) {
        if (tree == null) {
            gui.showHint("Kein Baum geladen");
        } else {
            //tree.deleteNode(node);
        }
    }

}
