package net.pslice.physics;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Object {

    /*
    * ======================================================================
    * Variables, Objects, Lists, Sets and Maps:
    * ======================================================================
     */

    // Name of the object
    private String name;

    // Mass of the object (In kg)
    private double mass;

    // Initial X coordinate of the object
    private final float initialXPosition;
    // Initial Y coordinate of the object
    private final float initialYPosition;
    // Initial velocity of the object
    private final double initialVelocity;
    // Initial X velocity of the object
    private final double initialXVelocity;
    // Initial Y velocity of the object
    private final double initialYVelocity;

    // Set of all forces working on the object
    private final Set<Force> forces;

    // Map of relevant times to X coordinates of the object
    private final HashMap<Integer, Float> xPositions;
    // Map of relevant times to Y coordinates of the object
    private final HashMap<Integer, Float> yPositions;
    // Map of relevant times to velocities of the object
    private final HashMap<Integer, Double> velocities;
    // Map of relevant times to X velocities of the object
    private final HashMap<Integer, Double> xVelocities;
    // Map of relevant times to Y velocities of the object
    private final HashMap<Integer, Double> yVelocities;





    /*
    * ======================================================================
    * Initializers:
    * Name - The name the object can be referred to as.
    * Mass - The mass of the object (in kilograms).
    * xPosition - The first x coordinate the object is found at (in meters).
    * yPosition - The first y coordinate the object is found at (in meters).
    * Velocity - The first velocity the object is travelling at (in meters/second).
    * Angle - The first angle the object is travelling at (in degrees, with 0 being East).
    * ======================================================================
     */

    // Initializer requiring name and mass
    public Object(String name, double mass)
    {
        this(name, mass, 0, 0, 0, 0);
    }

    // Initializer requiring name, mass, initial X coordinate and initial Y coordinate
    public Object(String name, double mass, float xPosition, float yPosition)
    {
        this(name, mass, xPosition, yPosition, 0, 0);
    }

    // Initializer requiring name, mass, initial velocity and initial angle
    public Object(String name, double mass, double velocity, double angle)
    {
        this(name, mass, 0, 0, velocity, angle);
    }

    // Initializer requiring name, mass, initial X coordinate, initial Y coordinate, initial velocity and initial angle
    public Object(String name, double mass, float xPosition, float yPosition, double velocity, double angle)
    {
        forces = new HashSet<>();

        xPositions = new HashMap<>();
        yPositions = new HashMap<>();

        velocities = new HashMap<>();
        xVelocities = new HashMap<>();
        yVelocities = new HashMap<>();

        this.name = name;
        this.mass = mass;

        initialXPosition = xPosition;
        initialYPosition = yPosition;
        initialVelocity = velocity;
        initialXVelocity = velocity * Math.cos(Math.toRadians(angle));
        initialYVelocity = velocity * Math.sin(Math.toRadians(angle));

        velocities.put(0, initialVelocity);
        xVelocities.put(0, initialXVelocity);
        yVelocities.put(0, initialYVelocity);
    }





    /*
    * ======================================================================
    * Setters and getters for generic object info:
    * ======================================================================
     */

    // Setter for the name of the object
    public void setName(String name)
    {
        this.name = name;
    }

    // Getter for the name of the object
    public String getName()
    {
        return name;
    }

    // Setter for the mass of the object
    public void setMass(double mass)
    {
        this.mass = mass;
    }

    // Getter for the mass of the object
    public double getMass()
    {
        return mass;
    }

    // Method to increase the mass of the object
    public void increaseMass(double i)
    {
        mass += i;
    }

    // Method to decrease the mass of the object
    public void decreaseMass(double i)
    {
        mass -= i;
    }





    /*
    * ======================================================================
    * Getters for all initial info about the object:
    * ======================================================================
    */

    // Getter for the initial X coordinate of the object
    public float getInitialXPosition()
    {
        return initialXPosition;
    }

    // Getter for the initial Y coordinate of the object
    public float getInitialYPosition()
    {
        return initialYPosition;
    }

    // Getter for the initial velocity of the object
    public double getInitialVelocity()
    {
        return initialVelocity;
    }





    /*
    * ======================================================================
    * Setters and getters for the X and Y coordinates of the object:
    * ======================================================================
     */

    // Setter for the X coordinate of the object
    public void setXPosition(int time, float xPosition)
    {
        xPositions.put(time, xPosition);
    }

    // Setter for the Y coordinate of the object
    public void setYPosition(int time, float yPosition)
    {
        yPositions.put(time, yPosition);
    }

    // Setter for both coordinates of the object
    public void setPosition(int time, float xPosition, float yPosition)
    {
        xPositions.put(time, xPosition);
        yPositions.put(time, yPosition);
    }

    // Getter for the X coordinate of the object
    public float getXPosition(int time)
    {
        return xPositions.get(time);
    }

    // Getter for the Y coordinate of the object
    public float getYPosition(int time)
    {
        return yPositions.get(time);
    }

    // Getter for both coordinates of the object
    public float[] getPosition(int time)
    {
        float[] position = new float[2];
        position[0] = xPositions.get(time);
        position[1] = yPositions.get(time);
        return position;
    }





    /*
    * ======================================================================
    * Setters and getters for the velocity and angle of the object:
    * ======================================================================
     */

    public void setVelocity(int time, double xVelocity, double yVelocity)
    {
        xVelocities.put(time, xVelocity);
        yVelocities.put(time, yVelocity);
        velocities.put(time, Math.hypot(xVelocity, yVelocity));
    }

    // Getter for the velocity of the object
    public double getVelocity(int time)
    {
        return velocities.get(time);
    }

    public double getXVelocity(int time)
    {
        return xVelocities.get(time);
    }

    public double getYVelocity(int time)
    {
        return yVelocities.get(time);
    }

    // Getter for the initial X Velocity of the angle
    public double getInitialXVelocity()
    {
        return initialXVelocity;
    }

    // Getter for the initial Y Velocity of the angle
    public double getInitialYVelocity()
    {
        return initialYVelocity;
    }





    /*
    * ======================================================================
    * Methods concerning the forces acting on the object:
    * ======================================================================
     */

    // Getter for all the forces acting on the object
    public Set<Force> getForces()
    {
        return forces;
    }

    // Method to add a force to the object
    public void addForce(Force force)
    {
        forces.add(force);
    }

    // Method to remove a force from the object
    public void removeForce(Force force)
    {
        if (forces.contains(force))
            forces.remove(force);
    }

    // Method to remove all forces from the object
    public void removeAllForces()
    {
        forces.clear();
    }

    // Method to check if a force is working on the object
    public boolean isWorkedOnByForce(Force force)
    {
        return forces.contains(force);
    }

    // Method to check if a force is working on the object, by name
    public boolean isWorkedOnByForce(String forceName)
    {
        for (Force force : forces)
            if (force.getName().equals(forceName))
                return true;
        return false;
    }

    // Method to get a force by its name
    public Force getForceByName(String forceName)
    {
        for (Force force : forces)
            if (force.getName().equals(forceName))
                return force;
        return null;
    }
}
