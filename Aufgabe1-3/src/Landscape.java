enum Landscape{
    CITY(1.5f, new float[]{0.3f, 0.2f, 0.1f}, "Fire", "Flood", "Earthquake"),
    FOREST(1.2f,new float[]{0.6f, 0.4f}, "Forest Fire", "Flood"),
    DESERT(0.7f,new float[]{0.5f, 0.3f}, "Sandstorm", "Drought"),
    COAST(0.9f, new float[]{0.4f, 0.5f}, "Hurricane", "Flood"),
    MOUNTAIN(0.85f,new float[]{0.7f, 0.4f}, "Avalanche", "Landslide");

    /**
     * This array holds the catastrophe factors, which define the probability of specific disasters occurring in this landscape.
     * The order of the factors should match the order of the disaster names.
     */
    private final float[] catastropheFactor;

    /**
     * An array of disaster names associated with each landscape.
     */
    private final String[] disasters;

    private final float ageFactor;

    Landscape(float ageFactor, float[] catastropheFactor, String... disasters) {
        this.ageFactor = ageFactor;
        this.catastropheFactor = catastropheFactor;
        this.disasters = disasters;
    }

    public float[] getCatastropheFactor() {
        return catastropheFactor;
    }

    public float getAgeFactor(){ return ageFactor; }

    public String[] getDisasters() {
        return disasters;
    }

    /**
     * Returns a catastrophe with its associated probability factor, based on a random selection.
     */
    public String triggerRandomDisaster() {
        int index = new java.util.Random().nextInt(disasters.length);
        return "Disaster: " + disasters[index] + ", Probability Factor: " + catastropheFactor[index];
    }
}