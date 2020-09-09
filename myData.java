
            //holds the word and the frequency together

import java.util.Comparator;
public class myData implements Comparable<myData> {
    private int frequency;
    private String word;

    public myData(DNode<String> x) {
        frequency = x.getCounter();
        word = x.getElement();
    }

    public int getFreq() {
        return frequency;
    }

    public String getWord() {
        return word;
    }

    public int compareTo(myData y) {
        if (frequency < y.frequency) {
            return 1;
        }
        if (frequency > y.frequency) {
            return -1;
        }
        return 0;
    }
}

