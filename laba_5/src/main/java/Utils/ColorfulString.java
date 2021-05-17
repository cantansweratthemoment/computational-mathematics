package Utils;

public class ColorfulString {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void colorfulPrint(String q) {
        System.out.print(ANSI_CYAN + q + ANSI_RESET);
    }

    public static void aggressivelyPrint(String q) {
        System.out.print(ANSI_RED + q + ANSI_RESET);
    }
}