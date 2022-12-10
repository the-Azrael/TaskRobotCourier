import java.util.*;

public class Main {
    public static final String COMMANDS = "RLRFR";
    public static final int ROUTE_LENGTH = 100;
    public static final int ROUTE_COUNT = 1000;
    public static final char ANALYZED_CHAR = 'R';
    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    public static void main(String[] args) {
        generateRoutes();
        System.out.println("Другие размеры: ");
        for (Map.Entry<Integer, Integer> entry : sizeToFreq.entrySet()) {
            System.out.println(" - " + entry.getKey() + " (" + entry.getValue() + " раз)");
        }
    }

    private static void generateRoutes() {
        for (int i = 0; i < ROUTE_COUNT; i++) {
            synchronized (sizeToFreq) {
                Maximum m = new Maximum(sizeToFreq);
                Route r = new Route(COMMANDS, ROUTE_LENGTH, ANALYZED_CHAR);
                r.setMap(sizeToFreq);
                r.run();
                m.run();
            }
        }
    }
}
