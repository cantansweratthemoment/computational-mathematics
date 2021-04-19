package Methods;

import Utils.Functions;

public abstract class Method {
    private Functions functions;
    private double left;
    private double right;
    private double accuracy;

    public Method(Functions functions, double left, double right, double accuracy) {
        this.functions = functions;
        this.left = left;
        this.right = right;
        this.accuracy = accuracy;
    }

    public abstract void solve();

    public abstract void output();
}
