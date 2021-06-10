package base.Utils;

public class FormatResult {
    public static String formatResult(int i, double x, double y, double exact, double runge) {
        //return String.format("i: %d \nX: %f \nY: %f \nF(x,y): %f \nТочное значение: %f", i, x, y, f, exact);
        return String.format("i: %d \nX: %f \nY: %f \nТочное значение: %f \nКонтроль точности: %f", i, x, y, exact, runge);
    }
}
