/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avltree;

/**
 *
 * @author Robin
 */
public class GraphCreater {

    private GraphViz graph;

    /**
     *
     * @author Robin
     */
    public GraphCreater() {
        graph = new GraphViz();
    }

    /**
     *
     * @param parentNode Der Wert des Knotens, von dem aus ein Pfeil gerichtet
     * wird
     * @param childNode Der Wert des Knotens welche hinzugfuegt wird. Der Wert
     * eines Knotens wird dem Graphen hinzugefuegt
     */
    public void addNode(int parentNode, int childNode) {
        graph.addln(graph.start_graph());
        graph.addln(String.valueOf(parentNode) + " -> " + String.valueOf(childNode));
        graph.addln(graph.end_graph());

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
    public void getGraph() {
        graph.getGraph(graph.getDotSource(), "jpeg");
    }

    /**
     * Die Informationen des Graphs werden geladen
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
