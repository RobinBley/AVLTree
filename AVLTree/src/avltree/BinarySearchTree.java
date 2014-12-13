package avltree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Till
 */
public class BinarySearchTree implements AbstractBinarySearchTree {

    protected Node root;

    @Override
    /**
     * Fügt dem Baum einen Wert hinzu
     */
    public void addValue(Integer value) throws BinarySearchTreeException {
        if (root == null) {
            //Wenn es noch keinen root Knoten gibt, erzeuge diesen mit dem hinzuzufügenen Wert
            root = new Node(value);
        } else {
            //Der Anfangsknoten zum durchsuchen ist immer root
            Node n = root;

            //Solange dem Baum folgen, bis ein freier Platz gefunden wird
            while (true) {
                if (value < n.getValue()) { //Wenn der Wert kleiner als der des aktuellen Knotens ist, suche links nach einem freien Platz
                    if (n.getLft() == null) {
                        //Freier Platz gefunden, Knoten einfügen
                        n.setLft(new Node(value));
                        break;
                    } else {
                        //Nächste Ebene durchsuchen
                        n = n.getLft();
                    }

                } else if (value > n.getValue()) { //Wenn der Wert größer als der des aktuellen Knotens ist, suche rechts nach einem freien Platz
                    if (n.getRgt() == null) {
                        //Freier Platz gefunden, Knoten einfügen
                        n.setRgt(new Node(value));
                        break;
                    } else {
                        //Nächste Ebene durchsuchen 
                        n = n.getRgt();
                    }
                } else {
                    //Knoten bereits vorhanden, Exception werfen
                    throw new BinarySearchTreeException();
                }
            }
        }
    }
    
    /**
     * Löscht einen Wert wieder aus dem Baum
     * 
     */
    @Override
    public void delValue(Integer value) throws BinarySearchTreeException {
        if (root == null) {
            //Es gibt einen Root knoten, also kann das Element was gelöscht werden soll nicht vorhanden sein
            throw new BinarySearchTreeException();
        } else {
            //Normaler Löschvorgang, der nicht den root Knoten löscht
            Node n = root;
            Node parent = null;
            Node next;

            //Den zu löschenden Knoten lokalisieren
            while (value != n.getValue()) {
                if (value > n.getValue()) {
                    next = n.getRgt();
                } else {
                    next = n.getLft();
                }

                if (next == null) {
                    //Wenn der Knoten nicht gefunden wird, Exception werfen
                    throw new BinarySearchTreeException();
                } else {
                    parent = n;
                    n = next;
                }
            }

            //Ermittle den Linken und Rechten Teilknoten
            Node lft = n.getLft();
            Node rgt = n.getRgt();

            //Löschvorgang für einen Knoten der keine Kinder hat
            if (lft == null && rgt == null) {
                if (n == root) {
                    //Wenn der zu löschende Knoten der Root Knoten ist und dieser keine Kinder hat, einfach null setzen
                    root = null;
                } else {
                    if (value > parent.getValue()) {
                        //Wenn der zu löschende Knoten einen größeren Wert als der Elternknoten hat, rechten Ast den Elternknotens null setzen
                        parent.setRgt(null);
                    } else {
                        //Wenn der zu löschende Knoten einen klineren Wert als der Elternknoten hat, linken Ast den Elternknotens null setzen
                        parent.setLft(null);
                    }
                }
            } else if (lft == null || rgt == null) {//Löschvorgang für einen Knoten der ein Kind hat
                if(n == root){
                    //Wenn der Rootknoten gelöscht werden soll, tausche das Kind des Root Knotens mit dem Root Knoten
                    if(lft == null){
                        root = rgt;
                    } else{
                        root = lft;
                    }
                } else{
                    //Ast zum ersetzen
                    Node replaceNode;

                    //Mit dem Ast ersetzen der vorhanden ist
                    if (lft == null) {
                        replaceNode = rgt;
                    } else {
                        replaceNode = lft;
                    }

                    if (value > parent.getValue()) {
                        //Knoten liegt auf dem rechten ast des elternknotens, also einzigen ast des knotens mit der position des knotens tauschen
                        parent.setRgt(replaceNode);
                    } else {
                        //Knoten liegt auf dem linken ast des elternknotens, also einzigen ast des knotens mit der position des knotens tauschen
                        parent.setLft(replaceNode);
                    }
                }
            } else {//Löschvorgang für einen Knoten der zwei Kinder hat
                Node minParent = n;
                Node min = n.getRgt();

                //Finde den kleinsten Knoten im Rechten Teilbaum des zu löschenden Knotens
                while (min.getLft() != null) {
                    minParent = min;
                    min = min.getLft();
                }

                //Wert sichern, falls n = minParent
                Integer minParentValue = minParent.getValue();

                //Tausche den Wert des zu löschenden Knotens mit dem Wert aus dem kleinsten Knoten des Rechten teilbaums
                n.setValue(min.getValue());

                //Lösche den Knoten vom Elternknoten aus
                if (min.getValue() > minParentValue) {
                    minParent.setRgt(null);
                } else {
                    minParent.setLft(null);
                }
            }
        }
    }

    /**
     * Funktion die überprüft ob ein Wert bereits im Baum vorhanden ist
     * 
     */
    @Override
    public boolean hasValue(Integer value) {
        if (root == null) {
            //Wenn es keinen root knoten gibt, dann kann der wert nicht im baum sein
            return false;
        } else {
            //Erster zu durchsuchender Knoten ist immer root
            Node n = root;

            //Nach unten iterieren
            while (n != null) {
                if (value > n.getValue()) {
                    //Wert ist größer als der des aktuellen Knotens, nach rechts weiter suchen
                    n = n.getRgt();
                } else if (value < n.getValue()) {
                    //Wert ist kleiner als der des aktuellen Knotens, nach links weiter suchen
                    n = n.getLft();
                } else {
                    //Wert gefunden
                    return true;
                }
            }
            //Kompletter Baum abgesucht, aber nichts gefunden
            return false;
        }
    }

    /**
     * Funktion die die Maximale Tiefe des Baumes bestimmt
     * 
     */
    @Override
    public Integer getDepth() {
        if (root == null) {
            //Wenn es keinen Root gibt dann ist die Tiefe = 0
            return 0;
        } else {
            //Rekursive Tiefensuche starten
            return getDepth(root, 0);
        }
    }

    /**
     * Funktion die Rekursiv die länge des längsten Astes eines Knotens bestimmen kann
     * 
     */
    protected Integer getDepth(Node n, int depth) {
        //Linken und Rechten Kindknoten von n ermitteln
        Node lft = n.getLft();
        Node rgt = n.getRgt();

        if (lft == null && rgt == null) {
            //Wenn der Knoten ein Blatt ist, bisherige Tiefe + 1 zurückgeben
            return depth + 1;
        } else {
            //Ansonsten nach links und rechts weitersuchen
            Integer depthLft = depth + 1;
            Integer depthRgt = depth + 1;

            if (lft != null) {
                //Wenn es noch einen linken ast gibt, bestimme die Tiefe des astes ( + die bisherige Tiefe = Gesamttiefe)
                depthLft = getDepth(lft, depth + 1);
            }
            if (rgt != null) {
                //Wenn es noch einen rechten ast gibt, bestimme die Tiefe des astes ( + die bisherige Tiefe = Gesamttiefe)
                depthRgt = getDepth(rgt, depth + 1);
            }

            //Gib nur die größte Tiefe zurück, weil nur diese gesucht wird
            return depthLft > depthRgt ? depthLft : depthRgt;
        }
    }

    /**
     * Funktion die den Baum in einer übergebenen Reihenfolge durchläuft und automatisch ihre Rekursive
     * Version aufruft, wenn dies erforderlich ist.
     * 
     */
    @Override
    public List<Integer> traverse(Order o) {
        if (root == null) {
            //Wenn es keinen root knoten gibt, gibt es auch keine Elemente
            return new ArrayList<>();
        } else {
            //Rekursive Traversion starten
            return traverse(root, o);
        }
    }

    /**
     * Rekursive Funktion die den Baum in einer übergebenen Reihenfolge durchläuft.
     * 
     */
    private List<Integer> traverse(Node n, Order o) {
        //Linkes und Rechtes Kind des Knotens bestimmen
        Node lft = n.getLft();
        Node rgt = n.getRgt();

        ArrayList<Integer> stor = new ArrayList<>();

        if (o == Order.POSTORDER) { //LRW
            //Erst den Linken Teilbaum bestimmen (Wenn vorhanden)
            if (lft != null) {
                stor.addAll(traverse(lft, o));
            }
            
            //Dann den Rechten Teilbaum bestimmen (Wenn vorhanden)
            if (rgt != null) {
                stor.addAll(traverse(rgt, o));
            }

            //Dann die Wurzel hinzufügen
            stor.add(n.getValue());
        } else if (o == Order.PREORDER) { // WLR
            //Erst die Wurzel hinzufügen
            stor.add(n.getValue());

            //Linken Teilbaum hinzufügen (Wenn vorhanden)
            if (lft != null) {
                stor.addAll(traverse(lft, o));
            }
            
            //Rechten Teilbaum hinzufügen (Wenn vorhanden)
            if (rgt != null) {
                stor.addAll(traverse(rgt, o));
            }
        } else { //LWR
            //Linken Teilbaum hinzufügen
            if (lft != null) {
                stor.addAll(traverse(lft, o));
            }

            //Wurzel hinzufügen
            stor.add(n.getValue());

            //Rechten Teilbaum hinzufügen
            if (rgt != null) {
                stor.addAll(traverse(rgt, o));
            }
        }

        //Alle erfassten Elemente zurückgeben
        return stor;
    }
}
