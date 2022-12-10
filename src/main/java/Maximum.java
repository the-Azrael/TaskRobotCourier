import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

public class Maximum extends Thread {
    private Map<Integer, Integer> map;

    public Maximum(Map<Integer, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        int k = getMaxLengthKeyInMap();
        try {
            int s = map.get(k);
            System.out.println(LocalDateTime.now() + " Максимально: " + k + " встречается " + s + " раз");
        } catch (NullPointerException e) {
            try {
                wait();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private int getMaxLengthKeyInMap() {
        Collection<Integer> values = this.map.values();
        return values.stream().max((c1, c2) -> c1 - c2).get();
    }

}
