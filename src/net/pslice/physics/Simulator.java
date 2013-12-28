package net.pslice.physics;

import java.util.HashSet;
import java.util.Set;

public class Simulator {

    /*
    ** Main initializing method:
     */
    public static void main(String[] args) {
        Simulator simulator = new Simulator();

        Object mass1 = new Object("Mass 1", 4);
        mass1.addForce(new Force("Force 1", 10, 0));
        mass1.addForce(new Force("Force 2", -3, -6));

        simulator.addObject(mass1);
        simulator.addObject(new Object("Mass 2", 5, 0, 3, 7, 2));
        simulator.run(0, 5, 0.5);
    }

    /*
    ** Lists:
     */
    private final Set<Object> objects;

    /*
    ** Initializers:
     */
    public Simulator() {
        objects = new HashSet<>();
    }

    /*
    ** Method to add an object:
     */
    public void addObject(Object object) {
        objects.add(object);
    }

    /*
    ** Method to remove an object:
     */
    public void removeObject(Object object) {
        if (objects.contains(object))
            objects.remove(object);
    }

    /*
    ** Method to get all objects:
     */
    public Set<Object> getObjects() {
        return objects;
    }

    /*
    ** Method to check if the simulator is using an object:
     */
    public boolean isUsingObject(Object object) {
        return objects.contains(object);
    }

    /*
    ** Method to get an object by its name:
     */
    public Object getObjectByName(String objectName) {
        for (Object object : objects)
            if (object.getName().equals(objectName))
                return object;
        return null;
    }

    /*
    ** Method to check if the simulator is using an object by name:
     */
    public boolean isUsingObject(String objectName) {
        for (Object object : objects)
            if (object.getName().equals(objectName))
                return true;
        return false;
    }

    /*
    ** Methods to run the simulation:
     */
    public void run (double endTime) {
        run(0, endTime, 1);
    }
    public void run(double startTime, double endTime) {
        run(startTime, endTime, 1);
    }
    public void run(double startTime, double endTime, double interval) {
        System.out.println("Preparing to simulate...");
        for (double a = 0; a < startTime; a+=interval)
            for (Object object : objects) {
                calculateVelocity(a, object);
                calculatePosition(a, object);
            }
        for (Object object : objects) {
            printObjectInfo(object);
            for (double i = startTime; i <= endTime; i += interval) {
                calculateVelocity(i, object);
                calculatePosition(i, object);
                System.out.println("["
                        + object.getName() + " at "
                        + String.valueOf(i).replaceAll("\\.0\\b", "") + " seconds] Location: ("
                        + String.valueOf(object.getXPosition()).replaceAll("\\.0\\b", "") + ", "
                        + String.valueOf(object.getYPosition()).replaceAll("\\.0\\b", "") + "), Velocity: "
                        + String.valueOf(Math.hypot(object.getXVelocity(), object.getYVelocity())).replaceAll("\\.0\\b", "") + " m/s "
                        + getObjectAngle(object));
            }
        }
        System.out.println("Finished simulating!");
    }

    /*
    ** Method to print general info about the object:
     */
    public static void printObjectInfo(Object object) {
        System.out.println("=========================");
        System.out.println("Object: " + object.getName());
        System.out.println("Mass: " + String.valueOf(object.getMass()).replaceAll("\\.0\\b", "") + " kg");
        System.out.println("Initial X Coordinate: " + String.valueOf(object.getInitialXPosition()).replaceAll("\\.0\\b", ""));
        System.out.println("Initial Y Coordinate: " + String.valueOf(object.getInitialYPosition()).replaceAll("\\.0\\b", ""));
        System.out.println("Initial X Velocity: " + String.valueOf(object.getInitialXVelocity()).replaceAll("\\.0\\b", "") + " m/s");
        System.out.println("Initial Y Velocity: " + String.valueOf(object.getInitialYVelocity()).replaceAll("\\.0\\b", "") + " m/s");
        System.out.println("=========================");
    }

    /*
    ** Method to run all position calculations:
     */
    private static void calculatePosition(double time, Object object) {
        object.setXPositionAt(time, object.getInitialXPosition() + (float)(object.getXVelocityAt(time) * time));
        object.setYPositionAt(time, object.getInitialYPosition() + (float)(object.getYVelocityAt(time) * time));
    }

    /*
    ** Method to run all velocity calculations:
     */
    private static void calculateVelocity(double time, Object object) {
        double totalXMagnitude = 0;
        double totalYMagnitude = 0;

        for (Force force : object.getForces()) {
            totalXMagnitude += force.getXMagnitude();
            totalYMagnitude += force.getYMagnitude();
        }

        object.setXVelocityAt(time, object.getInitialXVelocity() + ((totalXMagnitude / object.getMass()) * time));
        object.setYVelocityAt(time, object.getInitialYVelocity() + ((totalYMagnitude / object.getMass()) * time));
    }

    /*
    ** Method to calculate the angle of the object:
     */
    private static String getObjectAngle(Object object) {
        if (object.getXVelocity() == 0
                && object.getYVelocity() == 0)
            return "(No movement)";

        if (object.getXVelocity() == 0) {
            if (object.getYVelocity() >= 0)
                return "North";
            return "South";
        }

        if (object.getYVelocity() == 0) {
            if (object.getXVelocity() >= 0)
                return "East";
            return "West";
        }

        double angle =  Math.toDegrees(Math.atan(object.getYVelocity() / object.getXVelocity()));

        if (angle > 0 && angle < 90 && object.getXVelocity() > 0)
            return "at " + String.valueOf(angle).substring(0, 6).replaceAll("\\.0\\b", "") + " degrees N of E";
        if (angle > 0 && angle < 90 && object.getXVelocity() < 0)
            return "at " + String.valueOf(angle).substring(0, 6).replaceAll("\\.0\\b", "") + " degrees S of W";
        if (angle > -90 && angle < 0 && object.getXVelocity() > 0)
            return "at " + String.valueOf(-angle).substring(0, 6).replaceAll("\\.0\\b", "") + " degrees S of E";
        if (angle > -90 && angle < 0 && object.getXVelocity() < 0)
            return "at " + String.valueOf(-angle).substring(0, 6).replaceAll("\\.0\\b", "") + " degrees N of W";
        return "[Error calculating angle]";
    }
}
