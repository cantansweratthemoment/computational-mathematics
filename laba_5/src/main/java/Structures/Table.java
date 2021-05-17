package Structures;

import java.util.*;

public class Table {
    private SortedMap<Double, Double> table;
    private ArrayList<Double> xs, ys;

    public Table(SortedMap<Double, Double> table) {
        this.table = table;
        xs=new ArrayList<>();
        ys=new ArrayList<>();
        for (Map.Entry<Double, Double> entry : table.entrySet()) {
            xs.add(entry.getKey());
            ys.add(entry.getValue());
        }
    }

    public SortedMap<Double, Double> getTable() {
        return table;
    }

    public ArrayList<Double> getXs() {
        return xs;
    }

    public ArrayList<Double> getYs() {
        return ys;
    }
}
