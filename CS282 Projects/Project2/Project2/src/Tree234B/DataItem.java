/**
 * @author Gaven Grantz
 * October 26, 2018
 * CS282 Project #2 - 234 Tress and B Trees
 * Source - DataItem.java
 * Description: DataItem.java contains fields for items in a node.
 */

package Tree234B;

class DataItem {

    private String record;

    public long dData;          // one data item

//--------------------------------------------------------------
    public DataItem(long dd) // constructor
    {
        dData = dd;
    }

    public DataItem(String record, long dData) {  //new constructor for data in txt file
        this.record = record;
        this.dData = dData;
    }
    

//--------------------------------------------------------------
    public void displayItem() // display item, format "/27"
    {
        System.out.print("/" + dData);
    }

//--------------------------------------------------------------
    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

}  // end class DataItem
