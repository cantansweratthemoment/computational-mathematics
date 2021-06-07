package base.Utils;

public class FormatResult {
    public static String formatResult(int i, double x, double y, double f, double exact) {
        return String.format("i: %d \nX: %f \nY: %f \nF(x,y): %f \nТочное значение: %f", i, x, y, f, exact);
    }
}
