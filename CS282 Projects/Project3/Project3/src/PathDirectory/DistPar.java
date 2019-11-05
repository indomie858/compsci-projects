/**
 * @author Gaven Grantz
 * November 19, 2018
 * CS282 Project #3 - Direct Weighted Graphs
 * Source - DistPar.java
 * Description: This class keeps track of the parent vertices
 */
package PathDirectory;

////////////////////////////////////////////////////////////////
public class DistPar implements java.io.Serializable // distance and parent
{ // items stored in sPath array

    public int distance; // distance from start to this vertex
    public int parentVert; // current parent of this vertex
// -------------------------------------------------------------

    public DistPar(int pv, int d) // constructor
    {
        distance = d;
        parentVert = pv;
    }
} // end class DistPar
///////////////////////////////////////////////////////////////
