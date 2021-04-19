package Utils;

public enum MethodType {
    rectangleLeft("Метод левых прямоугольников"),
    rectangleMiddle("Метод средних прямоугольников"),
    rectangleRight("Метод правых прямоугольников"),
    simpsons("Метод Симпсона"),
    trapezium("Метод трапеций");
    private final String name;

    MethodType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
