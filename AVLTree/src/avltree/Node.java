package avltree;

/**
 * @author Till
 */
public class Node {
    private Node lft = null;
    private Node rgt = null;

    private int value;

    /**
     * Konstruktor fuer die Nodeklasse, die den Wert dieser schon setzt.
     */
    public Node(int value) {
        this.value = value;
    }

    /**
     * Liefert das linke Kind.
     */
    public Node getLft() {
        return lft;
    }

    /**
     * Setzt das linke Kind.
     */
    public void setLft(Node lft) {
        this.lft = lft;
    }

    /**
     * Gibt das rechte Kind zurueck.
     */
    public Node getRgt() {
        return rgt;
    }

    /**
     * Setzt das rechte Kind.
     */
    public void setRgt(Node rgt) {
        this.rgt = rgt;
    }

    /**
     * Liefert den eigenen Wert zurueck.
     */
    public int getValue() {
        return value;
    }

    /**
     * Setzt den eigenen Wert.
     */
    public void setValue(int value) {
        this.value = value;
    }
}
