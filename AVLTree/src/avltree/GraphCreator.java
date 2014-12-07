package avltree;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Robin
 */
public class GraphCreator {

    private GraphViz graph;
    private static GraphCreator instance = null;

    /**
     *
     * @author Robin
     */
    public static GraphCreator getInstance() {
        if (instance == null) {
            instance = new GraphCreator();
        }
        return instance;
    }

    public GraphCreator() {

        graph = new GraphViz();
    }

    public void createGraph() {
        graph.addln(graph.start_graph());
        Node n = tree.getRoot();

        ArrayList<Node> nextNodes = new ArrayList<Node>();
        nextNodes.add(n);
        while (!nextNodes.isEmpty()) {
            n = nextNodes.get(0);
            if (n.hasLeft) {
                addNode(n.getValue(), n.getLeft().getValue());
                nextNodes.add(n.getLeft());
            }
            if (n.hasRight) {
                addNode(n.getValue(), n.getRight().getValue());
                nextNodes.add(n.getRight());
            }
            nextNodes.remove(0);

        }

        graph.addln(graph.end_graph());
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
     * Der Graph wird als jpeg gespeichert
     *
     * @param path Der Pfad, wo der Graph gespeichert wird
     */
    public void saveGraph(String path) {
        try {
            graph.writeGraphToFile(graph.getGraph(graph.getDotSource(), "jpeg"), path);

        } catch (Exception e) {
            e.printStackTrace();
            Gui.getInstance().showHint("Graph konnte nicht gespeichert werden!");
        }
    }

    /**
     * Ein Graph wird geladen
     *
     * @param path Der Pfad, wo sich die Datei befindet
     */
    public void loadGraph(String path) {
        try {
            graph.readSource(path);

        } catch (Exception e) {
            e.printStackTrace();
            Gui.getInstance().showHint("Graph konnte nicht geladen werden!");
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
    /// MUSS NOCH ANGEPAST WERDEN
    public Image getGraph() {
        try {
            return ImageIO.read(new ByteArrayInputStream(graph.getGraph(graph.getDotSource(), "jpeg")));
        } catch (Exception e) {
            e.printStackTrace();
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
