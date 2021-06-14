package net.word.count;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MyComparator  implements Comparator<Map.Entry<String,Integer>> {
    public int compare(HashMap.Entry<String,Integer> he1, HashMap.Entry<String,Integer> he2) {
        return he2.getValue()-he1.getValue();
    }
}
