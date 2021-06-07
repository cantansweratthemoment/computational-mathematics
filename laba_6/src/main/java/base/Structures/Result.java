package base.Structures;

import lombok.Data;

import java.util.List;

@Data
public class Result {
    private Table table;
    private List<String> steps;

    public Result(Table table, List<String> steps) {
        this.table = table;
        this.steps = steps;
    }
}
