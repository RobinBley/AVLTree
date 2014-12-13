package avltree;

import java.util.Stack;

/**
 *
 * @author Kevin
 */
public class AvlTree extends BinarySearchTree {
    /**
     * Liefert den BalanceIndex für einen Teilbaum zurück, mit diesem kann
     * bestimmt werden ob der Teilbaum ausgeglichen, Innen oder Außenlastig ist.
     *
     */
    private RebalanceIndex getRebalanceIndex(Node n) {
        //Index der angibt wie die Rebalancierung durchzuführen ist
        RebalanceIndex rebalanceIndex = null;

        //Balancierungsindex des Root Knotens berechnen
        int balanceIndex = getBalanceIndex(n);

        if (balanceIndex >= -1 && balanceIndex <= 1) {
            //Wenn der balance index zwischen -1 und 1 liegt ist der Subtree der am Knoten hängt ausbalanciert
            rebalanceIndex = RebalanceIndex.BALANCED;
        } else {
            if (balanceIndex == 2) {
                //BalanceIndex liegt bei 2. Der Baum benötigt eine Rechtsrotation oder Linksrechtsrotation um ausbalanciert zu werden
                if (getBalanceIndex(n.getLft()) == 1 || getBalanceIndex(n.getLft()) == 0) {
                    //Baum benötigt eine Rechtsrotation zum ausbalancieren
                    rebalanceIndex = RebalanceIndex.RIGHTRIGHT;
                } else {
                    //Baum benötigt eine Links-Rechtsrotation zum ausbalancieren
                    rebalanceIndex = RebalanceIndex.LEFTRIGHT;
                }
            } else {
                //BalanceIndex liegt bei -2. Der Baum benötigt eine Linksrotation oder Rechtslinksrotation um ausbalanciert zu werden
                if (getBalanceIndex(n.getRgt()) == -1 || getBalanceIndex(n.getRgt()) == 0) {
                    //Baum benötigt eine Linksrotation 
                    rebalanceIndex = RebalanceIndex.LEFTLEFT;
                } else {
                    //Baum benötigt eine Rechtslinksrotation 
                    rebalanceIndex = RebalanceIndex.RIGHTLEFT;
                }
            }
        }

        //Rebalancierungsindex zurückgeben
        return rebalanceIndex;
    }

    private int getBalanceIndex(Node n) {
        if (n == null) {
            return 0;
        } else {
            Node rgt = n.getRgt();
            Node lft = n.getLft();
            int rgtDepth, lftDepth;

            if (rgt != null) {
                rgtDepth = getDepth(rgt, 0);
            } else {
                rgtDepth = 0;
            }

            if (lft != null) {
                lftDepth = getDepth(lft, 0);
            } else {
                lftDepth = 0;
            }

            return lftDepth - rgtDepth;
        }
    }

    @Override
    /**
     * Fügt dem Baum einen Wert hinzu
     *
     */
    public void addValue(Integer value) throws BinarySearchTreeException {
        if (root == null) {
            //Wenn es noch keinen root Knoten gibt, erzeuge diesen mit dem hinzuzufügenen Wert
            root = new Node(value);

            //Rebalancing muss hier nicht durchgeführt werden, weil der Wert als einziger Knoten daherkommt
        } else {
            //Der Anfangsknoten zum suchen nach einer Einfügeposition ist immer root
            Node n = root;

            //Stack für das nachverfolgen des weges über die Nodes
            Stack nodeTrace = new Stack();

            nodeTrace.add(root);

            Node newNode = new Node(value);

            //Solange dem Baum folgen, bis ein freier Platz gefunden wird
            while (true) {
                if (value < n.getValue()) { //Wenn der Wert kleiner als der des aktuellen Knotens ist, suche links nach einem freien Platz
                    if (n.getLft() == null) {
                        //Freier Platz gefunden, Knoten einfügen
                        n.setLft(newNode);
                        break;
                    } else {
                        //Nächste Ebene durchsuchen
                        n = n.getLft();

                        //Knoten zum Stack hinzufügen
                        nodeTrace.add(n);
                    }

                } else if (value > n.getValue()) { //Wenn der Wert größer als der des aktuellen Knotens ist, suche rechts nach einem freien Platz
                    if (n.getRgt() == null) {
                        //Freier Platz gefunden, Knoten einfügen
                        n.setRgt(newNode);
                        break;
                    } else {
                        //Nächste Ebene durchsuchen 
                        n = n.getRgt();

                        //Knoten zum Stack hinzufügen
                        nodeTrace.add(n);
                    }
                } else {
                    //Knoten bereits vorhanden, Exception werfen
                    throw new BinarySearchTreeException();
                }
            }

            nodeTrace.add(newNode);

            //Baum rebalancieren
            performRebalance(nodeTrace);
        }
    }

    /**
     * Führt die Rebalancierung des AVL Trees durch
     *
     */
    private void performRebalance(Stack nodeTrace) {
        Node n = (Node) nodeTrace.pop();
        Node parent;

        do {
            if (nodeTrace.size() == 0) {
                parent = null;
            } else {
                parent = (Node) nodeTrace.pop();
            }

            //RebalanceIndex berechnen
            RebalanceIndex rebalanceIndex = getRebalanceIndex(n);

            if (rebalanceIndex != RebalanceIndex.BALANCED) {
                //Baum rebalancieren, wenn er nicht balanciert ist
                Node rebalanced = null;

                switch (rebalanceIndex) {
                    case LEFTLEFT:
                        rebalanced = rotateLeftLeft(n);
                        break;
                    case RIGHTRIGHT:
                        rebalanced = rotateRightRight(n);
                        break;
                    case LEFTRIGHT:
                        rebalanced = rotateLeftRight(n);
                        break;
                    case RIGHTLEFT:
                        rebalanced = rotateRightLeft(n);
                        break;
                }

                //Subtree durch neuen rebalancierten Subtree ersetzen
                if (parent == null) {
                    root = rebalanced;
                } else if (n == parent.getLft()) {
                    parent.setLft(rebalanced);
                } else {
                    parent.setRgt(rebalanced);
                }
            }

            n = parent;
        } while (parent != null);
    }

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

            Stack nodeTrace = new Stack();
            nodeTrace.push(n);

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
                    nodeTrace.push(next);
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

                //Letzten Knoten aus dem trace entfernen
            } else if (lft == null || rgt == null) {//Löschvorgang für einen Knoten der ein Kind hat
                if (n == root) {
                    //Wenn der Rootknoten gelöscht werden soll, tausche das Kind des Root Knotens mit dem Root Knoten
                    if (lft == null) {
                        root = rgt;
                    } else {
                        root = lft;
                    }
                } else {
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

                    //Letzten Knoten aus dem trace entfernen
                    nodeTrace.pop();

                    //...und mit Austauschknoten ersetzen
                    nodeTrace.push(replaceNode);
                }
            } else {//Löschvorgang für einen Knoten der zwei Kinder hat
                Node minParent = n;
                Node min = n.getRgt();

                nodeTrace.push(min);

                //Finde den kleinsten Knoten im Rechten Teilbaum des zu löschenden Knotens
                while (min.getLft() != null) {
                    minParent = min;
                    min = min.getLft();
                    nodeTrace.push(min);
                }

                //Kleinsten knoten aus dem rechten Teilbaum wieder entfernen, da dieser gelöscht wird
                nodeTrace.pop();

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

            //Baum ausbalancieren
            performRebalance(nodeTrace);
        }
    }

    private Node rotateLeftLeft(Node a) {
        Node b = a.getRgt();

        //Verbindung von a zu b lösen
        a.setRgt(b.getLft());

        //A als neues linkes Kind von b einfügen
        b.setLft(a);

        //Neuen root zurückgeben 
        return b;
    }

    private Node rotateRightRight(Node a) {
        Node b = a.getLft();

        //Verbindung von a zu b lösen
        a.setLft(b.getRgt());

        //a als neues rechtes Kind von b einfügen
        b.setRgt(a);

        //Neuen root zurückgeben
        return b;
    }

    private Node rotateRightLeft(Node a) {
        //Rechtsrotation mit Rechtem Teilbaum durchführen um Ausgangslage für eine Linksrotation zu schaffen
        a.setRgt(rotateRightRight(a.getRgt()));

        //Linksrotation durchführen um Balancierung abzuschließen
        return rotateLeftLeft(a);
    }

    private Node rotateLeftRight(Node a) {
        //Linksrotation mit Linken Teilbaum durchführen um Ausgangslage für eine Rechtsrotation zu schaffen
        a.setLft(rotateLeftLeft(a.getLft()));

        //Rechtsrotation durchführen um Balancierung abzuschließen
        return rotateRightRight(a);
    }
}
