package Aufgabe5;

public class Interior<P> extends Space<P> {
    private final double area;

    public Interior(String description, double area) {
        super(description);
        this.area = area;
    }

    public double area() {
        return area;
    }

    @Override
    public String toString() {
        return super.toString() + " (Area: " + area + " m²)";
    }
}
