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
        int v = getMaxFreqValue();
        try {
            int k = getKeyByValue(v);
            System.out.println("Самое частое количество повторений " + k + " (встретилось " + v + " раз)");
        } catch (NullPointerException ex) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private int getMaxFreqValue() {
        Collection<Integer> collectionOfValues = map.values();
        return collectionOfValues.stream().max((e1, e2) -> e1 - e2).get();
    }

    private synchronized int getKeyByValue(int value) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return 0;
    }
}
