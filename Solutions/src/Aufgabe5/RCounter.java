package Aufgabe5;

public class RCounter implements Approvable<RCounter, Path<RCounter>> {
    private int count = 0;
    private Path<RCounter> approvedPath;

    public RCounter() {
        this.approvedPath = null;
    }

    @Override
    public Path<RCounter> approved(RCounter p) {
        count += 1000;
        if (p != null) {
            p.count += 1;
        }
        return approvedPath;
    }

    @Override
    public void approve(RCounter p, Path<RCounter> t) {
        this.approvedPath = t;
    }

    @Override
    public String toString() {
        return "RCounter Count: " + count;
    }
}
