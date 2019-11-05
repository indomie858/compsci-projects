/**
 * @author Gaven Grantz
 * October 26, 2018
 * CS282 Project #2 - 234 Tress and B Trees
 * Source - Tree234.java
 * Description: This java file contains the algorithm to build a 234 Tree structure.
 */
package Tree234B;

class Tree234 {

    protected Node root = new Node();            // make root node

// -------------------------------------------------------------
    public int find(long key) {

        Node curNode = root;

        int childNumber;

        while (true) {

            if ((childNumber = curNode.findItem(key)) != -1) {
                return childNumber;               // found it
            } else if (curNode.isLeaf()) {
                return -1;                        // can't find it
            } else // search deeper
            {
                curNode = getNextChild(curNode, key);
            }

        }  // end while

    }

// -------------------------------------------------------------
    // insert a DataItem
    public void insert(long dValue) {

        Node curNode = root;

        DataItem tempItem = new DataItem(dValue);

        while (true) {

            if (curNode.isFull()) // if node full,
            {

                split(curNode);                   // split it

                curNode = curNode.getParent();    // back up

                // search once
                curNode = getNextChild(curNode, dValue);

            } // end if(node is full)
            else if (curNode.isLeaf()) // if node is leaf,
            {
                break;                            // go insert
            } // node is not full, not a leaf; so go to lower level
            else {
                curNode = getNextChild(curNode, dValue);
            }

        }  // end while

        curNode.insertItem(tempItem);       // insert new DataItem

    }  // end insert()

// -------------------------------------------------------------
    public void split(Node thisNode) // split the node
    {

        // assumes node is full
        DataItem itemB, itemC;

        Node parent, child2, child3;

        int itemIndex;

        itemC = thisNode.removeItem();    // remove items from

        itemB = thisNode.removeItem();    // this node

        child2 = thisNode.disconnectChild(2); // remove children

        child3 = thisNode.disconnectChild(3); // from this node

        Node newRight = new Node();       // make new node

        if (thisNode == root) // if this is the root,
        {

            root = new Node();                // make new root

            parent = root;                    // root is our parent

            root.connectChild(0, thisNode);   // connect to parent

        } else // this node not the root
        {
            parent = thisNode.getParent();    // get parent
        }

        // deal with parent
        itemIndex = parent.insertItem(itemB); // item B to parent

        int n = parent.getNumItems();         // total items?

        for (int j = n - 1; j > itemIndex; j--) // move parent's
        {                                      // connections

            Node temp = parent.disconnectChild(j); // one child

            parent.connectChild(j + 1, temp);        // to the right

        }

        // connect newRight to parent
        parent.connectChild(itemIndex + 1, newRight);

        // deal with newRight
        newRight.insertItem(itemC);       // item C to newRight

        newRight.connectChild(0, child2); // connect to 0 and 1

        newRight.connectChild(1, child3); // on newRight

    }  // end split()

// -------------------------------------------------------------
    // gets appropriate child of node during search for value
    public Node getNextChild(Node theNode, long theValue) {

        int j;

        // assumes node is not empty, not full, not a leaf
        int numItems = theNode.getNumItems();

        for (j = 0; j < numItems; j++) // for each item in node
        {                               // are we less?

            if (theValue < theNode.getItem(j).dData) {
                return theNode.getChild(j);  // return left child
            }
        }  // end for                   // we're greater, so

        return theNode.getChild(j);        // return right child

    }

// -------------------------------------------------------------
    public void displayTree() {

        recDisplayTree(root, 0, 0);

    }

// -------------------------------------------------------------
    private void recDisplayTree(Node thisNode, int level,
            int childNumber) {

        System.out.print("level=" + level + " child=" + childNumber + " ");

        thisNode.displayNode();               // display this node

        // call ourselves for each child of this node
        int numItems = thisNode.getNumItems();

        for (int j = 0; j < numItems + 1; j++) {

            Node nextNode = thisNode.getChild(j);

            if (nextNode != null) {
                recDisplayTree(nextNode, level + 1, j);
            } else {
                return;
            }

        }

    }  // end recDisplayTree()

// -------------------------------------------------------------\
    public void insert(long dValue, String record) {  //overloaded insert method for data from txt file

        Node curNode = root;

        DataItem tempItem = new DataItem(record, dValue);

        while (true) {

            if (curNode.isFull()) // if node full,
            {

                split(curNode);                   // split it

                curNode = curNode.getParent();    // back up

                // search once
                curNode = getNextChild(curNode, dValue);

            } // end if(node is full)
            else if (curNode.isLeaf()) // if node is leaf,
            {
                break;                            // go insert
            } // node is not full, not a leaf; so go to lower level
            else {
                curNode = getNextChild(curNode, dValue);
            }

        }  // end while

        curNode.insertItem(tempItem);       // insert new DataItem

    }  // end insert()

}  // end class Tree234
