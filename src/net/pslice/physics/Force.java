package net.pslice.physics;

public class Force {

    /*
    * ======================================================================
    * Variables, Objects, Lists, Sets and Maps:
    * ======================================================================
     */

    // The name of the force
    private String name;

    // The magnitude of the force
    private double magnitude;
    // The angle of the force
    private double angle;





    /*
    * ======================================================================
    * Initializer:
    * Name - The name the force can be referred to as.
    * Magnitude - The magnitude of the force (in Newtons).
    * yMagnitude - The angle of the force (in degrees, with 0 being East).
    * ======================================================================
     */

    // Initializer requiring the name, magnitude and angle of the force
    public Force(String name, double magnitude, double angle)
    {
        this.name = name;
        this.magnitude = magnitude;
        this.angle = angle;
    }





    /*
    * ======================================================================
    * Setter and getter for the name of the force:
    * ======================================================================
     */

    // Setter for the name of the force
    public void setName(String name)
    {
        this.name = name;
    }

    // Getter for the name of the force
    public String getName()
    {
        return name;
    }





    /*
    * ======================================================================
    * Methods involving the magnitude of the force
    * ======================================================================
     */

    // Setter for the magnitude of the force
    public void setMagnitude(double magnitude)
    {
        this.magnitude = magnitude;
    }

    // Getter for the magnitude of the force
    public double getMagnitude()
    {
        return magnitude;
    }

    // Method to increase the magnitude of the force
    public void increaseMagnitude(double i)
    {
        magnitude += i;
    }

    // Method to decrease the magnitude of the force
    public void decreaseMagnitude(double i)
    {
        magnitude -= i;
    }

    // Getter for the magnitude of the force on the X plane
    public double getXMagnitude()
    {
        return magnitude * Math.cos(Math.toRadians(angle));
    }

    // Getter for the magnitude of the force on the Y plane
    public double getYMagnitude()
    {
        return magnitude * Math.sin(Math.toRadians(angle));
    }





    /*
    * ======================================================================
    * Setter and getter for the angle of the force:
    * ======================================================================
     */

    // Setter for the angle of the force
    public void setAngle(double angle)
    {
        this.angle = angle;
    }

    // Getter for the angle of the force
    public double getAngle()
    {
        return angle;
    }
}
