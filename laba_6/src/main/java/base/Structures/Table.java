package base.Structures;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Table {
    private ArrayList<Double> xs, ys;

    public Table(ArrayList<Double> xs, ArrayList<Double> ys) {
        this.xs = xs;
        this.ys = ys;
    }
}
