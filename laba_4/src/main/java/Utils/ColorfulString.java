package Utils;

public class ColorfulString {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void println(String q) {
        System.out.println(ANSI_CYAN + q + ANSI_RESET);
    }

    public static void aggressivelyPrintln(String q) {
        System.out.println(ANSI_RED + q + ANSI_RESET);
    }
}