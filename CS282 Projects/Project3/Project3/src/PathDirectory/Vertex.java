/**
 * @author Gaven Grantz
 * November 19, 2018
 * CS282 Project #3 - Direct Weighted Graphs
 * Source - Vertex.java
 * Description: This class keeps track of what vertices are in the tree.
 */
package PathDirectory;

///////////////////////////////////////////////////////////////
public class Vertex implements java.io.Serializable {

    public char label; // label (e.g. ‘A’)
    public boolean isInTree;
// -------------------------------------------------------------

    public Vertex(char lab) // constructor
    {
        label = lab;
        isInTree = false;
    }
// -------------------------------------------------------------
} // end class Vertex
////////////////////////////////////////////////////////////////
