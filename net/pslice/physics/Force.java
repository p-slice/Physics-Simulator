package net.pslice.physics;

public class Force {

    private String name;
    private DataSet magnitude;

    public Force(String name) {
        this(name, 0, 0, 0);
    }

    public Force(String name, double x, double y, double z) {
        this.name = name;
        magnitude = new DataSet(x, y ,z);
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return magnitude.getX();
    }

    public double getY() {
        return magnitude.getY();
    }

    public double getZ() {
        return magnitude.getZ();
    }

    public void set(double x, double y, double z) {
        magnitude = new DataSet(x, y, z);
    }

    public void setName(String name) {
        this.name = name;
    }
}
