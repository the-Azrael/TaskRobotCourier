import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

public class Route extends Thread {
    private String letters;
    private int length;
    private String route;
    private char analyzedChar;
    private int countOfAnalyzedSymbols;
    private Map<Integer, Integer> map;

    public Route(String letters, int length, char analyzedChar) {
        this.letters = letters;
        this.length = length;
        this.analyzedChar = analyzedChar;
    }

    public void setMap(Map<Integer, Integer> map) {
        this.map = map;
    }

    private String generateRoute() {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < this.length; i++) {
            route.append(this.letters.charAt(random.nextInt(this.letters.length())));
        }
        return route.toString();
    }

    private int countSymbol () {
        IntStream is = route.chars();
        return (int) is.filter(c -> c == analyzedChar).count();
    }

    public String getRoute() {
        return route;
    }

    private synchronized void addOrUpdateMap() {
        map.computeIfPresent(this.countOfAnalyzedSymbols, (k, v) -> v + 1);
        map.putIfAbsent(this.countOfAnalyzedSymbols, 1);
    }

    public int getCountOfAnalyzedSymbols() {
        return countOfAnalyzedSymbols;
    }

    @Override
    public void run() {
        this.route = generateRoute();
        this.countOfAnalyzedSymbols = countSymbol();
        addOrUpdateMap();
    }
}
