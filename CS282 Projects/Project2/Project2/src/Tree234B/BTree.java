/**
 * @author Gaven Grantz
 * October 26, 2018
 * CS282 Project #2 - 234 Tress and B Trees
 * Source - BTree.java
 * Description: BTree.java inherits from Tree234. BTree.java contains a modified
 * split() method that moves multiple items to a new node instead of one item.
 */
package Tree234B;

/**
 *
 * @author gafaa
 */
public class BTree extends Tree234 {

    // -------------------------------------------------------------
    public void split(Node thisNode) // split the node
    {

        int order = thisNode.getOrder();    //order of tree

        int numItems = thisNode.getNumItems();   //number of items in a node

        int numberOfAs, numberOfCs, numberOfChildren;

        if (numItems % 2 == 1) {         //if there are an odd number of items
            numberOfAs = numItems / 2;
            numberOfCs = numberOfAs;
        } else {                        //if there are an even number of items
            numberOfAs = numItems / 2;
            numberOfCs = numberOfAs - 1;
        }

        numberOfChildren = numberOfCs + 1;

        DataItem[] itemCarray = new DataItem[numberOfCs];   //array for items to be moved to new node

        for (int i = numberOfCs - 1; i >= 0; i--) {
            itemCarray[i] = thisNode.removeItem();    //removes one or multiple items from this node
        }

        DataItem itemBGaven = thisNode.removeItem();  //removes one item from this node to be sent to parent

        Node childArr[] = new Node[numItems];  //array for child references to be moved to new node

        int k = 0;
        for (int i = order - 1; i > numberOfAs; i--) {
            childArr[k] = thisNode.disconnectChild(i);   //removes child references from this node
            k++;
        }

        ///////////////////Unused Code from textbook//////////////////////////
        /*
        DataItem itemB, itemC;

        Node parent, child2, child3;               
       
       
        itemC = thisNode.removeItem();    // remove items from

        itemB = thisNode.removeItem();    // this node

        child2 = thisNode.disconnectChild(2); // remove children

        child3 = thisNode.disconnectChild(3); // from this node
         */
        ////////////////////////////////////////////////////////////////////////
        // assumes node is full
        Node parent;
        int itemIndex;

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
        itemIndex = parent.insertItem(itemBGaven); // item B to parent

        int n = parent.getNumItems();         // total items?

        for (int j = n - 1; j > itemIndex; j--) // move parent's
        {                                      // connections

            Node temp = parent.disconnectChild(j); // one child

            parent.connectChild(j + 1, temp);        // to the right

        }

        // connect newRight to parent
        parent.connectChild(itemIndex + 1, newRight);

        // deal with newRight
        for (int i = 0; i < numberOfCs; i++) {
            newRight.insertItem(itemCarray[i]);   //inserts one or multiple item C to new right
        }

        for (int i = 0; i < numberOfCs + 1; i++) {
            newRight.connectChild(i, childArr[i]);  //connects children to new right node
        }

        System.out.print("Now moving " + numberOfCs + " items and ");

        System.out.println(numberOfChildren + " child references to a new node.");

        ///////////////////////Unused Code from textbook///////////////////////
        //newRight.insertItem(itemC);       // item C to newRight
        //newRight.connectChild(0, child2); // connect to 0 and 1
        //newRight.connectChild(1, child3); // on newRight
        ///////////////////////////////////////////////////////////////////////
    }  // end split()

    // -------------------------------------------------------------
}
