/**
 * @author Gaven Grantz
 * May 22, 2018
 * CS182 Project #6 - Hash Crash
 * Source - HashTable.java
 * Description: HashTable.java contains the variables and methods used to create
 * and manipulate a hash table.
 */
package project6;

////////////////////////////////////////////////////////////////
class HashTable
   {
    private DataItem[] hashArray;    // array holds hash table
    private int arraySize;
    private DataItem nonItem;        // for deleted items
    private int crashCount;
// -------------------------------------------------------------
    public HashTable(int size)       // constructor
       {
       crashCount = 0;
       arraySize = size;
       hashArray = new DataItem[arraySize];
       nonItem = new DataItem(-1);   // deleted item key is -1
       }
// -------------------------------------------------------------
    public void displayTable()
       {
       System.out.print("Table: ");
       for(int j=0; j<arraySize; j++)
         {
         if(hashArray[j] != null)
            System.out.print(hashArray[j].getKeyInt() + " ");
         else
            System.out.print("** ");
         }
       System.out.println("");
      }
// -------------------------------------------------------------
   public int hashFunc(int key)
      {
      return key % arraySize;       // hash function
      }
// -------------------------------------------------------------
   public void insert(DataItem item) // insert a DataItem
   // (assumes table not full)
      {
      int key = item.getKeyInt();      // extract key
      int hashVal = hashFunc(key);  // hash the key
                                    // until empty cell or -1,
      while(hashArray[hashVal] != null &&
                      hashArray[hashVal].getKeyInt() != -1)
         {
         ++hashVal;                 // go to next cell
         hashVal %= arraySize;      // wraparound if necessary
         }
      hashArray[hashVal] = item;    // insert item
      }  // end insert()
// -------------------------------------------------------------
   public DataItem delete(int key)  // delete a DataItem
      {
      int hashVal = hashFunc(key);  // hash the key

      while(hashArray[hashVal] != null)  // until empty cell,
         {                               // found the key?
         if(hashArray[hashVal].getKeyInt() == key)
            {
            DataItem temp = hashArray[hashVal]; // save item
            hashArray[hashVal] = nonItem;       // delete item
            return temp;                        // return item
            }
         ++hashVal;                 // go to next cell
         hashVal %= arraySize;      // wraparound if necessary
         }
      return null;                  // can't find item
      }  // end delete()
// -------------------------------------------------------------
   public DataItem find(int key)    // find item with key
      {
      int hashVal = hashFunc(key);  // hash the key

      while(hashArray[hashVal] != null)  // until empty cell,
         {                               // found the key?
         if(hashArray[hashVal].getKeyInt() == key)
            return hashArray[hashVal];   // yes, return item
         ++hashVal;                 // go to next cell
         hashVal %= arraySize;      // wraparound if necessary
         }
      return null;                  // can't find item
      }
// -------------------------------------------------------------
     // end class HashTable
////////////////////////////////////////////////////////////////


    //Place this in HashTable!  So we can hash Strings NOT int's
    ////////////////////////////////////////////////////////////////  
    public int hashFunc3(String key)
       {
       int hashVal = 0;
       for(int j=0; j<key.length(); j++)    // left to right
          {
          int letter = key.charAt(j) - 96;  // get char code
          hashVal = (hashVal * 27 + letter) % arraySize; // mod
          }
       return hashVal;                      // no mod
       }  // end hashFunc3()
    ////////////////////////////////////////////////////////////////

    public void displayTableStringsConsole()   //displays dataitem names from hashtable
          {
          System.out.println("Table: " + arraySize);
          System.out.println("Number of Crashes: " + crashCount);
          for(int j=0; j<arraySize; j++)
             {
             if(hashArray[j] != null)
                System.out.println(hashArray[j].getKeyString() + " ");
             //else
                //System.out.println("** ");
             }
          System.out.println("");
          }


    public void insertString(DataItem item) // insert a DataItem
       // (assumes table not full)
            {
            String key = item.getKeyString();      // extract key
            int hashVal = hashFunc3(key);  // hash the key
                                        // until empty cell or -1,

            while(hashArray[hashVal] != null)
                {
                ++hashVal;                 // go to next cell
                hashVal %= arraySize;      // wraparound if necessary
                }

            if (hashArray[hashVal] == null){
                item.setActualPosition(hashVal);
                hashArray[hashVal] = item;    // insert item
            } 
          }  // end insert()


    public DataItem findString(String key)    // find item with key
      {
      int hashVal = hashFunc3(key);  // hash the key

      while(hashArray[hashVal] != null)  // until empty cell,
         {                               // found the key?
         if(hashArray[hashVal].getKeyString().equals(key))
            return hashArray[hashVal];   // yes, return item
         ++hashVal;                 // go to next cell
         hashVal %= arraySize;      // wraparound if necessary
         }
      return null;                  // can't find item
      }


    public void populateHashTable(String[] strArray){  //creates DataItem objects and inserts into hashtable
        //DataItem names = null;
        for (int i = 0; i<strArray.length;i++){
            DataItem names = new DataItem(strArray[i]);
            //if (names != null)
            insertString(names);
        }
    }


    public DataItem checkCollisionObj(String key){ //takes in string key and returns dataitem obj if collision occurs
        int calculatedHash = hashFunc3(key);
        int position = 0;
        DataItem obj = findString(key);

        if (obj != null){
            position = obj.getActualPosition();

            if (position != calculatedHash){
                crashCount++;
                return obj;
            }
        }
        return null;
    }

    private int collision(String strVar){   //takes in string key and returns the calculated hash. Hash is calculated by calling hashFuc3();
        DataItem obj = checkCollisionObj(strVar);
        int calculatedHash = -1;
        if (obj != null){
            calculatedHash = hashFunc3(obj.getKeyString());  //Hash is calculated by calling hashFuc3();
            return calculatedHash;
        }
        return calculatedHash;
    }


    public void collisionPrintToConsole(String[] strArray){    //prints collisions to console. Used for debugging purposes
        System.out.println("Debug Crash Table: " + arraySize);
        //System.out.println("Number of Crashes: " + crashCount);
        int j = 0;
        for (int i = 0; i < strArray.length; i++){
            DataItem obj = checkCollisionObj(strArray[i]);
            if (obj != null){
                j++;
                String name = obj.getKeyString();
                int calculatedHash = hashFunc3(name);
                int actualPosition = obj.getActualPosition();
                System.out.println(j + ".Name: " + name + " --- Calculated Location: " + calculatedHash + " --- Actual Location: " + actualPosition);
            }
        }
        System.out.println("Number of Crashes: " + crashCount);
    }






}