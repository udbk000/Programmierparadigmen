public class Szenario {

    private String name;

    private float co2Emission;

    private String material;

    private float waste;

    //per m2
    private float costs;

    //possible landscapes
    enum Landscape{
        CITY, FOREST, DESERT, COAST, MOUNTAIN
    }

    private Landscape landscape;

    public Szenario(){
        //switch for costs, lifespan, catastrophe coefficients
    }

    public String getName(){
        return name;
    }

    public float getCo2Emission() {
        return co2Emission;
    }

    public String getMaterial(){
        return material;
    }

    public float getWaste(){
        return waste;
    }

    public Landscape getLandscape(){
        return landscape;
    }


}
