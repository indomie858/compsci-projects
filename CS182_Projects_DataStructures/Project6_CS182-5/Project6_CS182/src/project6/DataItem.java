/**
 * @author Gaven Grantz
 * May 22, 2018
 * CS182 Project #6 - Hash Crash
 * Source - DataItem.java
 * Description: DataItem.java contains the data to be stored in a hash table.
 */
package project6;

////////////////////////////////////////////////////////////////
class DataItem
   {                                // (could have more data)
   private int iData;               // data item (key)
   private String nameData;
   //protected int calculatedHash;
   private int actualPosition;
//--------------------------------------------------------------
   public DataItem(int ii)          // constructor
      { iData = ii; }

    public DataItem(String nameData) {
        this.nameData = nameData;
    }

    public int getActualPosition() {
        return actualPosition;
    }

    public void setActualPosition(int actualPosition) {
        this.actualPosition = actualPosition;
    }
   
   
    public String getKeyString(){
        return nameData;
    }
//--------------------------------------------------------------
   public int getKeyInt()
      { return iData; }
//--------------------------------------------------------------
   }  // end class DataItem
////////////////////////////////////////////////////////////////
