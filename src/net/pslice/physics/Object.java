package net.pslice.physics;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Object {

    /*
    ** Variables:
     */
    private String name;

    private double mass;

    private final float initialXPosition;
    private final float initialYPosition;

    private final double initialXVelocity;
    private final double initialYVelocity;

    private float xPosition;
    private float yPosition;

    private double xVelocity;
    private double yVelocity;

    /*
    ** Lists:
     */
    private final Set<Force> forces;

    private final HashMap<Double, Float> timeXPositions;
    private final HashMap<Double, Float> timeYPositions;

    private final HashMap<Double, Double> timeXVelocities;
    private final HashMap<Double, Double> timeYVelocities;

    /*
    ** Initializers:
    ** Name - The name the object can be referred to as.
    ** Mass - The mass of the object (in kilograms).
    ** xPosition - The first x coordinate the object is found at (in meters).
    ** yPosition - The first y coordinate the object is found at (in meters).
    ** xVelocity - The first x velocity the object is travelling at (in meters/second).
    ** yVelocity - The first y velocity the object is travelling at (in meters/second).
     */
    public Object(String name, double mass) {
        this(name, mass, 0, 0, 0, 0);
    }
    public Object(String name, double mass, float xPosition, float yPosition) {
        this(name, mass, xPosition, yPosition, 0, 0);
    }
    public Object(String name, double mass, double xVelocity, double yVelocity) {
        this(name, mass, 0, 0, xVelocity, yVelocity);
    }
    public Object(String name, double mass, float xPosition, float yPosition, double xVelocity, double yVelocity) {
        forces = new HashSet<>();

        timeXPositions = new HashMap<>();
        timeYPositions = new HashMap<>();

        timeXVelocities = new HashMap<>();
        timeYVelocities = new HashMap<>();

        this.name = name;
        this.mass = mass;
        this.xPosition = xPosition;
            initialXPosition = xPosition;
        this.yPosition = yPosition;
            initialYPosition = yPosition;
        this.xVelocity = xVelocity;
            initialXVelocity = xVelocity;
        this.yVelocity = yVelocity;
            initialYVelocity = yVelocity;
    }

    /*
    ** Setter and getter for object name:
     */
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    /*
    ** Getters for initial X and Y positions:
     */
    public float getInitialXPosition() {
        return initialXPosition;
    }
    public float getInitialYPosition() {
        return initialYPosition;
    }

    /*
    ** Getters for initial X and Y velocities:
     */
    public double getInitialXVelocity() {
        return initialXVelocity;
    }
    public double getInitialYVelocity() {
        return initialYVelocity;
    }

    /*
    ** Setter and getter for object mass:
     */
    public void setMass(double mass) {
        this.mass = mass;
    }
    public double getMass() {
        return mass;
    }
    public void increaseMass(double i) {
        mass += i;
    }
    public void decreaseMass(double i) {
        mass -= i;
    }

    /*
    ** Setter and getter for object X position:
     */
    public void setXPosition(float position) {
        xPosition = position;
    }
    public float getXPosition() {
        return xPosition;
    }
    public void increaseXPosition(float i) {
        xPosition += i;
    }
    public void decreaseXPosition(float i) {
        xPosition -= i;
    }

    /*
    ** Setter and getter for object Y position:
     */
    public void setYPosition(float position) {
        yPosition = position;
    }
    public float getYPosition() {
        return yPosition;
    }
    public void increaseYPosition(float i) {
        yPosition += i;
    }
    public void decreaseYPosition(float i) {
        yPosition -= i;
    }

    /*
    ** Setter and getter for object X velocity:
     */
    public void setXVelocity(double velocity) {
        xVelocity = velocity;
    }
    public double getXVelocity() {
        return xVelocity;
    }
    public void increaseXVelocity(double i) {
        xVelocity += i;
    }
    public void decreaseXVelocity(double i) {
        xVelocity -= i;
    }

    /*
    ** Setter and getter for object X position:
     */
    public void setYVelocity(double velocity) {
        yVelocity = velocity;
    }
    public double getYVelocity() {
        return yVelocity;
    }
    public void increaseYVelocity(double i) {
        yVelocity += i;
    }
    public void decreaseYVelocity(double i) {
        yVelocity -= i;
    }

    /*
    ** Method to add a force:
     */
    public void addForce(Force force) {
        forces.add(force);
    }

    /*
    ** Method to remove a force:
     */
    public void removeForce(Force force) {
        if (forces.contains(force))
            forces.remove(force);
    }

    /*
    ** Method to check if a force is acting on the object:
     */
    public boolean workedOnByForce(Force force) {
        return forces.contains(force);
    }

    /*
    ** Method to get all forces acting on the object:
     */
    public Set<Force> getForces() {
        return forces;
    }

    /*
    ** Method to check if a force is acting on the object, by name:
     */
    public boolean workedOnByForce(String forceName) {
        for (Force force : forces)
            if (force.getName().equals(forceName))
                return true;
        return false;
    }

    /*
    ** Method to get a force by name:
     */
    public Force getForceByName(String forceName) {
        for (Force force : forces)
            if (force.getName().equals(forceName))
                return force;
        return null;
    }

    /*
    ** Setters for X and Y positions at a given time:
     */
    public void setXPositionAt(double time, float xPosition) {
        setXPosition(xPosition);
        timeXPositions.put(time, xPosition);
    }
    public void setYPositionAt(double time, float yPosition) {
        setYPosition(yPosition);
        timeYPositions.put(time, yPosition);
    }

    /*
    ** Setters for X and Y velocities at a given time:
     */
    public void setXVelocityAt(double time, double xVelocity) {
        setXVelocity(xVelocity);
        timeXVelocities.put(time, xVelocity);
    }
    public void setYVelocityAt(double time, double yVelocity) {
        setYVelocity(yVelocity);
        timeYVelocities.put(time, yVelocity);
    }

    /*
    ** Getters for X and Y positions at a given time:
     */
    public float getXPositionAt(double time) {
        return timeXPositions.get(time);
    }
    public float getYPositionAt(double time) {
        return timeYPositions.get(time);
    }

    /*
    ** Getters for X and Y velocities at a given time:
     */
    public double getXVelocityAt(double time) {
        return timeXVelocities.get(time);
    }
    public double getYVelocityAt(double time) {
        return timeYVelocities.get(time);
    }
}
