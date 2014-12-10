package avltree;

import java.awt.Image;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Robin
 */
public class GraphCreator {

    private GraphViz graph;

    /**
     *
     * @author Robin
     */
    public GraphCreator() {

        graph = new GraphViz();
    }

    public void createGraph(AVLTree tree) {
        if (tree.root != null) {
            graph = new GraphViz();
            graph.addln(graph.start_graph());
            graph.addln(String.valueOf(tree.root.getKey()));
            AVLNode n = tree.root;
            ArrayList<AVLNode> nextNodes = new ArrayList<>();
            nextNodes.add(n);
            while (!nextNodes.isEmpty()) {
                n = nextNodes.get(0);
                if (n.getLeftChild() != null) {
                    addNode(n.getKey(), n.getLeftChild().getKey());
                    nextNodes.add(n.getLeftChild());
                }
                if (n.getRightChild() != null) {
                    addNode(n.getKey(), n.getRightChild().getKey());
                    nextNodes.add(n.getRightChild());
                }
                nextNodes.remove(0);

            }

            graph.addln(graph.end_graph());
        }
    }

    /**
     * @param parentNode Der Wert des Knotens, von dem aus ein Pfeil gerichtet
     * wird
     * @param childNode Der Wert des Knotens welche hinzugfuegt wird. Der Wert
     * eines Knotens wird dem Graphen hinzugefuegt
     */
    private void addNode(int parentNode, int childNode) {

        graph.addln(String.valueOf(parentNode) + " -> " + String.valueOf(childNode));

    }

    /**
     * Der aktuell geldene binaere Baum wird als JPEG-Datei gespeichert
     *
     * @param path Pfad, an dem die Datei gespeichert werden soll
     * @return eine Bestaetigung ob die Dateioperation erfolgreich war
     */
    public boolean saveGraphAsJpeg(String path) {
        try {
            if (!path.endsWith(".jpeg")) {
                path = path + ".jpeg";
            }
            graph.writeGraphToFile(graph.getGraph(graph.getDotSource(), "jpeg"), path);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Der aktuell geldene binaere Baum wird als txt-Datei gespeichert
     *
     * @param path Pfad, an dem die Datei gespeichert werden soll
     * @return eine Bestaetigung ob die Dateioperation erfolgreich war
     */
    public boolean saveGraphAsTxt(String path) {
        try {
            if (!path.endsWith(".txt")) {
                path = path + ".txt";
            }
            PrintWriter out = new PrintWriter(path);

            out.println(graph.getDotSource());
            out.close();
//            graph.writeGraphToFile(graph.getGraph(graph.getDotSource(), "txt"), path);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Ein Graph wird geladen
     *
     * @param path Der Pfad, wo sich die Datei befindet
     * @return Bestaetigung, ob das Laden des Graphs erfolgreich war
     */
    public void loadGraph(String path) {
        try {
            graph.readSource(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @return Die Informationen des Graphs werden als DotSource zurueckgegeben
     */
    public String getDotSource() {
        return graph.getDotSource();
    }

    /**
     * @return Der Graph wird das jpeg zurueckgegeben
     */
    public Image getGraph() {
        try {
            return new ImageIcon(graph.getGraph(graph.getDotSource(), "jpeg")).getImage();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Die Informationen des Graphs werden geladen
     *
     * @param source Die Informationen des Graphs als DotSource
     * @return Wahr oder Falsch, ob der Graph erfolgreich geladen werden konnte
     */
    public boolean setGraphByDotSource(String source) {
        try {
            graph.readSource(source);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
