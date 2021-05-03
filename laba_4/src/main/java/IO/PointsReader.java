package IO;

import Structures.Point;

import java.io.BufferedReader;
import java.util.ArrayList;

public interface PointsReader {
    ArrayList<Point> read(BufferedReader sc);
}
