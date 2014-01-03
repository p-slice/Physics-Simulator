package net.pslice.physics;

import java.util.LinkedHashSet;
import java.util.Set;

public class Simulator {

    /*
    ** Main initializing method:
     */
    public static void main(String[] args)
    {
        Simulator simulator = new Simulator();

        Object mass1 = new Object("Mass 1", 10);
        mass1.addForce(new Force("Force 1", 5, 45));

        simulator.addObject(mass1);
        simulator.run(0, 5, 0.25);
    }

    /*
    * ======================================================================
    * Variables, Objects, Lists, Sets and Maps:
    * ======================================================================
     */

    // Set of all objects being simulated
    private final Set<Object> objects;

    // Number used as the bottom interval in simulating
    // (Warning: Making this too big could cause the simulation to get very slow)
    private static final int baseInterval = 100;





    /*
    * ======================================================================
    * Initializers:
    * ======================================================================
     */

    // Basic initializer
    public Simulator()
    {
        objects = new LinkedHashSet<>();
    }





    /*
    * ======================================================================
    * Methods concerning the objects being simulated:
    * ======================================================================
     */

    // Method to add an object
    public void addObject(Object object)
    {
        objects.add(object);
    }

    // Method to remove an object
    public void removeObject(Object object)
    {
        if (objects.contains(object))
            objects.remove(object);
    }

    // Getter for the set of all objects being simulated
    public Set<Object> getObjects()
    {
        return objects;
    }

    // Method to check if the simulator is using a certain object
    public boolean isUsingObject(Object object)
    {
        return objects.contains(object);
    }

    // Method to check if the simulator is using a certain object, by name
    public boolean isUsingObject(String objectName)
    {
        for (Object object : objects)
            if (object.getName().equals(objectName))
                return true;
        return false;
    }

    // Method to get an object by its name
    public Object getObjectByName(String objectName)
    {
        for (Object object : objects)
            if (object.getName().equals(objectName))
                return object;
        return null;
    }





    /*
    * ======================================================================
    * Methods to run the simulation:
    * ======================================================================
     */

    // Method requiring the end time of the simulation
    public void run (double end)
    {
        run(0, end, 1);
    }

    // Method requiring the start and end times of the simulation
    public void run(double start, double end) {
        run(start, end, 1);
    }

    // Method requiring the start and end times, and interval of the simulation
    public void run(double start, double end, double interval) {

        if (interval < 0.01)
        {
            System.out.println("Error: The interval must be greater than 0.01 seconds!");
            return;
        }

        int startTime = (int) (start * baseInterval);
        int endTime = (int) (end * baseInterval);
        int intervalTime = (int) (interval * baseInterval);

        System.out.println("Preparing to simulate...");
        for (Object object : objects)
        {
            printObjectInfo(object);
            for (int time = 0; time <= endTime; time++)
            {
                updateObject(time, object);
                if (time >= startTime
                        && time%intervalTime==0)
                    printObjectInfoAtTime(time, object);
            }
        }
        System.out.println("Finished simulating!");
    }





    /*
    * ======================================================================
    * Static methods to print out info about the object and simulation:
    * ======================================================================
     */

    // Method to print out generic info about the object
    private static void printObjectInfo(Object object)
    {
        System.out.println("=========================");
        System.out.println("Object: " + object.getName());
        System.out.println("Mass: " + cleanDecimal(object.getMass()) + " kg");
        System.out.println("Initial X Coordinate: " + cleanDecimal(object.getInitialXPosition()));
        System.out.println("Initial Y Coordinate: " + cleanDecimal(object.getInitialYPosition()));
        System.out.println("Initial Velocity: " + cleanDecimal(object.getInitialVelocity()) + " m/s");
        System.out.println("Initial Direction: " + getObjectAngle(0, object));
        System.out.println("Forces:");
        for (Force force : object.getForces())
        {
            System.out.print("    [");
            System.out.print(force.getName());
            System.out.print("] Magnitude: ");
            System.out.print(cleanDecimal(force.getMagnitude()));
            System.out.print(" Newtons at an angle of ");
            System.out.print(cleanDecimal(force.getAngle()));
            System.out.print(" degrees");
            System.out.println();
        }
        System.out.println("=========================");
    }

    // Method to print out object statistics at a given time
    private static void printObjectInfoAtTime(int time, Object object)
    {
        System.out.print("[");
        System.out.print(object.getName());
        System.out.print(" at ");
        System.out.print(cleanDecimal((double) time / baseInterval));
        System.out.print(" seconds] Location: (");
        System.out.print(cleanDecimal(object.getXPosition(time)));
        System.out.print(", ");
        System.out.print(cleanDecimal(object.getYPosition(time)));
        System.out.print("), Velocity: ");
        System.out.print(cleanDecimal(object.getVelocity(time)));
        System.out.print(" m/s ");
        System.out.print(getObjectAngle(time, object));
        System.out.println();
    }





    /*
    * ======================================================================
    * Static methods concerning the calculations of positions, velocities and angles of an object
    * ======================================================================
     */

    // Method to update the velocity and position of an object
    private static void updateObject(int time, Object object)
    {
        double totalXMagnitude = 0;
        double totalYMagnitude = 0;

        for (Force force : object.getForces())
        {
            totalXMagnitude += force.getXMagnitude();
            totalYMagnitude += force.getYMagnitude();
        }

        double xVelocity = object.getInitialXVelocity() + ((totalXMagnitude / object.getMass()) * ((double)time/baseInterval));
        double yVelocity = object.getInitialYVelocity() + ((totalYMagnitude / object.getMass()) * ((double)time/baseInterval));

        object.setVelocity(time, xVelocity, yVelocity);

        object.setXPosition(time, object.getInitialXPosition() + (float) (xVelocity * ((double)time/baseInterval)));
        object.setYPosition(time, object.getInitialYPosition() + (float) (yVelocity * ((double)time/baseInterval)));
    }

    // Method to convert the angle of the object
    private static String getObjectAngle(int time, Object object)
    {
        double xVelocity = object.getXVelocity(time);
        double yVelocity = object.getYVelocity(time);

        if (xVelocity < 0.01)
            xVelocity = 0;
        if (yVelocity < 0.01)
            yVelocity = 0;

        if (object.getVelocity(time) == 0)
            return "(No movement)";
        if (xVelocity == 0
                && yVelocity > 0)
            return "North";
        if (xVelocity == 0
                && yVelocity < 0)
            return "South";
        if (xVelocity > 0
                && yVelocity == 0)
            return "East";
        if (xVelocity < 0
                && yVelocity == 0)
            return "West";

        double angle = Math.toDegrees(Math.atan2(yVelocity, xVelocity));

        if (angle > 0 && angle < 90 && xVelocity > 0)
            return "at " + cleanDecimal(angle) + " degrees N of E";
        if (angle > 0 && angle < 90 && xVelocity < 0)
            return "at " + cleanDecimal(angle) + " degrees S of W";
        if (angle > -90 && angle < 0 && xVelocity > 0)
            return "at " + cleanDecimal(-angle) + " degrees S of E";
        if (angle > -90 && angle < 0 && xVelocity < 0)
            return "at " + cleanDecimal(-angle) + " degrees N of W";
        return "[Error calculating angle]";
    }

    // Method to clean up decimals in doubles
    private static String cleanDecimal(double number)
    {
        if (Double.toString(number).toCharArray().length > 4)
            number = Double.parseDouble(Double.toString(number).substring(0, 4));
        return String.valueOf(number).replaceAll("\\.0\\b", "");
    }
}
