import java.util.PriorityQueue;

public class HashTable {

    private int loadFactor;                    // the Load Factor
    private int numElements = 0;           //number of Elements in the hashtable not including the replicates
    private int tableSize;                     //number of buckets
    private DLList<String>[] hashTable;   // the hashtable array

    private void LoadFactor() { //this is the load factor calculator
        if (numElements == 0) { //this is the empty hash table
            return;
        }
        loadFactor = numElements / tableSize;
        if (loadFactor > 1.8) {     //rebuilds when the load factor gets too  big
            Rearrange();
        }
    }

    public HashTable() {   //hashtable constructor
        numElements = 0;      //intiliazes the number of elements to zero
        tableSize = 37;       //sets the table size to be 37
        hashTable = new DLList[tableSize]; //intialize the hashtable array
        for (int i = 0; i < tableSize; i++) {
            hashTable[i] = new DLList();
        }
        LoadFactor(); //update  the load factor
    }

    public HashTable(int x) {   //this is the overload constructor
        tableSize = x;
        numElements = 0;        //intializes the number of elements to zero
        hashTable = new DLList[x];
        for (int i = 0; i < x; i++) {
            hashTable[i] = new DLList();
        }
        LoadFactor();     // the loadfactor
    }

    private void Rearrange() {
        int newSize = 0;
        int tempInt;
        int[] primes = {37, 89, 199, 443, 977, 1193, 2389, 5897, 11491, 21269, 40013, 80107};   // the prime numbers grow exponentially

        for (int i = 0; i < 9; i++) {   //updates the size
            if (tableSize < primes[i]) {
                newSize = primes[i];
                break;
            }
        }

        HashTable tempTable = new HashTable(newSize);  //creates a new temporary table object

        for (int i = 0; i < tableSize; i++) {   //changes the  buckets
            for (DNode<String> j = hashTable[i].getFront(); j != null; j = j.getNext()) { //change nodes
            tempInt = j.getCounter();
            for (int k = 0; k < tempInt; k++) {  //changes the frequency
                tempTable.Hash(j.getElement());   //hashes  in  the new object
                Remove(j.getElement());     //removes the word occurence  the frequency or the node
            }
        }
    }

    hashTable =tempTable.hashTable;  //copies the information from the original object
        tableSize =tempTable.tableSize;
        loadFactor=tempTable.loadFactor;
    numElements =tempTable.numElements;

}
public void Hash(String x) {   //hashing function
    int charSum = 0;  //intitilizes the charSum to zero
    int index;

    for (int i = 0; i < x.length(); i++) {  //the summation of  the characters value
        charSum += x.charAt(i);
    }
    index = charSum % tableSize;  // the bucket number

    if (hashTable[index].find(x) != null) {    //if the element was already hashed before, this increases the  frequency of  the node
        hashTable[index].find(x).incCounter();
        return;
    } else {
        hashTable[index].insertAtRear(x);    //adds the new node
        numElements++;                   //increments the number of elements
        LoadFactor();                      //and this updates the load factor
        return;
    }
}
public void Remove(String x) {  //removes one of the occurances of the element and keeps the other
    int charSum = 0;  //intiliazes char sum to zero
    int index;
    DNode temp;
    for (int i = 0; i < x.length(); i++) { //summation of  the characters' value
        charSum += x.charAt(i);
    }
    index = charSum % tableSize;    //the bucket number

    if (hashTable[index].find(x) != null) {
        temp = hashTable[index].find(x);

        if (temp.getCounter() > 1) {
            temp.decCounter();
            return;
        }
        hashTable[index].delete(x);    //removes the node
        numElements--;                //decrements the number of elements
        LoadFactor();                   //updates the load factor
        return;
    }
}

//new class
public void mostFreq(int x) {
    PriorityQueue<myData> PQ = new PriorityQueue<myData>();
    myData current;

    for (int i = 0; i < tableSize; i++) { //going through buckets
        for (DNode<String> j = hashTable[i].getFront(); j != null; j = j.getNext()) {   //going through every linked list
            current = new myData(j); //j is a node, trying to convert the data in the node to the new class (myData)
            PQ.add(current); //put current into the priority queue
        }
        if (PQ.size() >= x) {               //if the size is bigger than the number of words I need, it will output
                                           // the root and then delete the root //put this back in with a j
            while(PQ.size() > 0) {
                System.out.println(PQ.peek().getWord() + " " + PQ.peek().getFreq());
                PQ.remove();

            }
        }
        PQ.clear();
    }

}



}















