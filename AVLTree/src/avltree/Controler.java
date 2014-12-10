package avltree;

import javax.swing.ImageIcon;

/**
 *
 * @author Robin
 */
public class Controler {

    private Gui gui;
    private GraphCreator creator;
    private AVLTree tree = null;
    private static Controler Instance = null;

    /**
     * Initialisierung der Komponenten
     */
    public Controler() {
        creator = new GraphCreator();
    }

    /**
     * Die Oberflache des Programms wird erstellt und gestartet
     */
    public void startGui() {
        gui = new Gui();
        gui.setVisible(true);
    }

    /**
     * Singleton-Pattern fuer ein einzieges Objekt dieser Klasse waehrend der
     * Programmlaufzeit
     *
     * @return Instanz dieser Klasse
     */
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

    /**
     * Ein neuer Baum wird erzeugt
     *
     * @param root der erste Knoten des Baums
     */
    public void newTree(int root) {
        tree = new AVLTree(new AVLNode(root));
        //Der neue aktuelle Graph wird in der Oberflaeche angezeigt
        this.showCurrentGraph();
    }

    /**
     * Ein Graph wird geladen
     *
     * @param path Pfad, an dem sich der Graph befindet
     */
    public void loadGraph(String path) {
        //Der Graph, welcher sich am uebergebenen Pfad befindet wird geladen
        creator.loadGraph(path);
        //Der geladene Graph wird in der Oberflaeche angezeigt
        gui.showGraph(new ImageIcon(creator.getGraph()));
    }

    /**
     * Der aktuelle Baum wird als JPEG-Datei gespeichert
     *
     * @param path Pfad, an dem sich die Datei befindet
     */
    public void saveTreeAsJpeg(String path) {
        creator.saveGraphAsJpeg(path);
    }

    /**
     * Der aktuelle Baum wird als txt-Datei gespeichert
     *
     * @param path Pfad, an dem sich die Datei befindet
     */
    public void saveTreeAsTxt(String path) {
        creator.saveGraphAsTxt(path);
    }

    /**
     * Der aktuelle Baum wird als Grafik in die Oberflaeche geladen
     */
    private void showCurrentGraph() {
        try {
            //Wenn ein Baum existiert...
            if (tree != null) {
                //wird der Graph des aktuellen Baums erzeugt
                creator.createGraph(tree);
            } else {

            }
            ImageIcon img = new ImageIcon(creator.getGraph());
            //Der Graph wird als Grafik in der Oberflaeche angezeigt
            gui.showGraph(img);
        } catch (Exception e) {
            gui.showHint("Keine Werte enthalten!");
        }

    }

    /**
     * Dem aktuellen Baum wird ein neuer Wert hinzugefuegt
     *
     * @param node Der neue Wert
     * @return Bestaetigung, ob die Aktion erfolgreich war
     */
    public boolean addNode(int node) {
        //Fals kein Baum existiert, wird ein neuer Baum erzeugt
        if (tree == null) {
            newTree(node);
        } else {
            try {
                // sonst wird dem aktuellen Baum der neue Knoten hinzugefuegt
                tree.insertNode(node);
                //
            } catch (Exception e) {
                return false;
            }
            //Der veraenderte oder neue Baum wird als Grafik in der Oberflaeche angezeigt
            showCurrentGraph();

        }
        return true;

    }

    /**
     * Dem aktuellen Baum wird ein Wert entfernt
     *
     * @param node Der zu entfernende Wert
     */
    public void delNode(int node) {
        //Wenn kein Baum existiert wird es dem User mitgeteilt
        if (tree == null) {
            gui.showHint("Kein Baum geladen");
        } else {
            try {
                //Wenn ein Baum existiert, wird geprueft ob der zuloeschende Wert existiert und geloescht
                if (tree.findNode(node) == null) {
                    gui.showHint("Node nicht enthalten");
                }
                ///////////////////////////////////////////////////////////////////////////////
//            tree.deleteNode(node);
                //////////////////////////////////////////////////////////////////////////////
                showCurrentGraph();
            } catch (Exception ex) {
            }
        }
    }

}
