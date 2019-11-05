/**
 * @author Gaven Grantz
 * October 26, 2018
 * CS282 Project #2 - 234 Tress and B Trees
 * Source - Node.java
 * Description: Node.java contains methods that are used to inserting, searching,
 * and deleting items from a node in a 234 Tree and B Tree
 */
package Tree234B;

class Node {

    private static int order = 4;

    private int numItems;

    private Node parent;

    private Node childArray[] = new Node[order];

    private DataItem itemArray[] = new DataItem[order - 1];

// -------------------------------------------------------------
    // connect child to this node
    public void connectChild(int childNum, Node child) {

        childArray[childNum] = child;

        if (child != null) {
            child.parent = this;
        }

    }

// -------------------------------------------------------------
    // disconnect child from this node, return it
    public Node disconnectChild(int childNum) {

        Node tempNode = childArray[childNum];

        childArray[childNum] = null;

        return tempNode;

    }

// -------------------------------------------------------------
    public Node getChild(int childNum) {
        return childArray[childNum];
    }

// -------------------------------------------------------------
    public Node getParent() {
        return parent;
    }

// -------------------------------------------------------------
    public boolean isLeaf() {
        return (childArray[0] == null) ? true : false;
    }

// -------------------------------------------------------------
    public int getNumItems() {
        return numItems;
    }

// -------------------------------------------------------------
    public DataItem getItem(int index) // get DataItem at index
    {
        return itemArray[index];
    }

// -------------------------------------------------------------
    public boolean isFull() {
        return (numItems == order - 1) ? true : false;
    }

// -------------------------------------------------------------
    public int findItem(long key) // return index of
    {                                    // item (within node)

        for (int j = 0; j < order - 1; j++) // if found,
        {                                 // otherwise,

            if (itemArray[j] == null) // return -1
            {
                break;
            } else if (itemArray[j].dData == key) {
                return j;
            }

        }

        return -1;

    }  // end findItem

// -------------------------------------------------------------
    public int insertItem(DataItem newItem) {

        // assumes node is not full
        numItems++;                          // will add new item

        long newKey = newItem.dData;         // key of new item

        for (int j = order - 2; j >= 0; j--) // start on right,
        {                                 //    examine items

            if (itemArray[j] == null) // if item null,
            {
                continue;                      // go left one cell
            } else // not null,
            {                              // get its key

                long itsKey = itemArray[j].dData;

                if (newKey < itsKey) // if it's bigger
                {
                    itemArray[j + 1] = itemArray[j]; // shift it right
                } else {

                    itemArray[j + 1] = newItem;   // insert new item

                    return j + 1;                 // return index to

                }                           //    new item

            }  // end else (not null)

        }  // end for                     // shifted all items,

        itemArray[0] = newItem;              // insert new item

        return 0;

    }  // end insertItem()

// -------------------------------------------------------------
    public DataItem removeItem() // remove largest item
    {

        // assumes node not empty
        DataItem temp = itemArray[numItems - 1];  // save item

        itemArray[numItems - 1] = null;           // disconnect it

        numItems--;                             // one less item

        return temp;                            // return item

    }

// -------------------------------------------------------------
    public void displayNode() // format "/24/56/74/"
    {

        for (int j = 0; j < numItems; j++) {
            itemArray[j].displayItem();   // "/56"
        }
        System.out.println("/");         // final "/"

    }

// -------------------------------------------------------------
    public static void setOrder(int orderbtree) {
        order = orderbtree;
    }

    public static int getOrder() {
        return order;
    }

}  // end class Node

