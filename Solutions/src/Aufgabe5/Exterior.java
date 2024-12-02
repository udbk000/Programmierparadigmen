package Aufgabe5;

public class Exterior<P> extends Space<P> {
    private final boolean isPublic;

    public Exterior(String description, boolean isPublic) {
        super(description);
        this.isPublic = isPublic;
    }

    public boolean isPublic() {
        return isPublic;
    }

    @Override
    public String toString() {
        return super.toString() + " (Public: " + isPublic + ")";
    }
}
