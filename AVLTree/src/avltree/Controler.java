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

    /**
     * Initialisierung der Komponenten
     */
    public Controler() {
        creator = new GraphCreator();
    }

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
        creator.loadGraph(path);

        //////////////////////Image der Gui uebergeben
        showCurrentGraph();
    }

    /**
     * Der Baum wird als JPEG-Datei gespeichert
     *
     * @param path Pfad, an dem sich die Datei befindet
     */
    public void saveTreeAsJpeg(String path) {
        creator.saveGraphAsJpeg(path);
    }

    /**
     * Der Baum wird als txt-Datei gespeichert
     *
     * @param path Pfad, an dem sich die Datei befindet
     */
    public void saveTreeAsTxt(String path) {
        creator.saveGraphAsTxt(path);
    }

    /**
     * Der aktuelle Baum wird das Graph in die Oberflaeche geladen
     */
    private void showCurrentGraph() {
        try {
            //Ein Graph des aktuellen Baums wird erzeugt
            creator.createGraph(tree);
            //Der Graph wird als Grafik in ein Label geladen, welches der Oberflaeche uebergeben wird
            ImageIcon img = new ImageIcon(creator.getGraph());
            JLabel l = new JLabel(img);
            l.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
            gui.showGraph(l);
        } catch (Exception e) {
            e.printStackTrace();;
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
                tree.insertNode(node);
                //
            } catch (Exception ex) {
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
        if (tree == null) {
            gui.showHint("Kein Baum geladen");
        } else {
            ////////////////tree.deleteNode(node);
            showCurrentGraph();
        }
    }

}
