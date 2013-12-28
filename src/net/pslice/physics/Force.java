package net.pslice.physics;

public class Force {

    /*
    ** Variables:
     */
    private String name;

    private double xMagnitude;
    private double yMagnitude;

    /*
    ** Initializers:
    ** Name - The name the force can be referred to as.
    ** xMagnitude - The original x magnitude of the force (in Newtons).
    ** yMagnitude - The original y magnitude of the force (in Newtons).
     */
    public Force(String name, double xMagnitude, double yMagnitude) {
        this.name = name;
        this.xMagnitude = xMagnitude;
        this.yMagnitude = yMagnitude;
    }

    /*
    ** Setter and getter for force name:
     */
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    /*
    ** Setter and getter for force X magnitude:
     */
    public void setXMagnitude(double magnitude) {
        xMagnitude = magnitude;
    }
    public double getXMagnitude() {
        return xMagnitude;
    }
    public void increaseXMagnitude(double i) {
        xMagnitude += i;
    }
    public void decreaseXMagnitude(double i) {
        xMagnitude -= i;
    }

    /*
    ** Setter and getter for force Y magnitude:
     */
    public void setYMagnitude(double magnitude) {
        yMagnitude = magnitude;
    }
    public double getYMagnitude() {
        return yMagnitude;
    }
    public void increaseYMagnitude(double i) {
        yMagnitude += i;
    }
    public void decreaseYMagnitude(double i) {
        yMagnitude -= i;
    }
}
