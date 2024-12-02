package Aufgabe5;

public class Counter<T> implements Approvable<Counter<T>, T> {
    private int count = 0;
    private T value;

    public Counter(T value) {
        this.value = value;
    }

    @Override
    public T approved(Counter<T> p) {
        count += 1000;
        if (p != null) {
            p.count += 1;
        }
        return value;
    }

    @Override
    public void approve(Counter<T> p, T t) {
        this.value = t;
    }

    @Override
    public String toString() {
        return "Count: " + count;
    }
}
