package net.pslice.physics;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class Simulator {

    private static final double G = 6.67E-11;
    private final TreeSet<Object> objects = new TreeSet<>();
    private boolean gravityEnabled = true;

    public void add(Object object) {
        objects.add(object);
    }

    public void remove(Object object) {
        if (objects.contains(object))
            objects.remove(object);
    }

    public HashMap<Long, Snapshot> run(long milliseconds) {
        if (milliseconds < 0)
            throw new IllegalArgumentException("Illegal time value: " + milliseconds);
        HashMap<Long, Snapshot> snapshots = new HashMap<>();
        snapshots.put(0l, new Snapshot());
        for (long time = 1l; time <= milliseconds; time++) {
            snapshots.put(time, new Snapshot(snapshots.get(time - 1)));
        }
        return snapshots;
    }

    public void setGravityEnabled(boolean enabled) {
        gravityEnabled = enabled;
    }

    public boolean uses(Object object) {
        return objects.contains(object);
    }

    public static void print(HashMap<Long, Snapshot> snapshots) {
        print(snapshots, 1);
    }

    public static void print(HashMap<Long, Snapshot> snapshots, long interval) {
        print(snapshots, 0, interval);
    }

    public static void print(HashMap<Long, Snapshot> snapshots, long start, long interval) {
        print(snapshots, start, snapshots.size() - 1, interval);
    }

    public static void print(HashMap<Long, Snapshot> snapshots, long start, long end, long interval) {
        System.out.println("Displaying simulation:");
        System.out.println("  - Start time: " + start);
        System.out.println("  - End time: " + end);
        System.out.println("  - Interval: " + interval);
        for (long time = start; time <= end; time += interval) {
            System.out.println("Time: " + time + "s");
            Snapshot snapshot = snapshots.get(time);
            for (Object object : snapshot.getSimulator().objects)
                System.out.println("  - " + object + " | Velocity: " + snapshot.getVelocity(object) + " | Position: " + snapshot.getPosition(object));
        }
    }

    public class Snapshot {

        private final TreeMap<Object, DataSet> velocities = new TreeMap<>(),
                positions = new TreeMap<>();

        private Snapshot() {
            for (Object object : objects) {
                velocities.put(object, object.getInitialVelocity());
                positions.put(object, object.getInitialPosition());
            }
        }

        private Snapshot(Snapshot lastSnapshot) {
            for (Object object : objects) {
                double xMagnitude = 0,
                        yMagnitude = 0,
                        zMagnitude = 0;
                for (Force force : object.getForces()) {
                    xMagnitude += force.getX();
                    yMagnitude += force.getY();
                    zMagnitude += force.getZ();
                }

                if (gravityEnabled) {
                    for (Object gObject : objects) {
                        if (object == gObject) continue;
                        double mass = object.getMass() * gObject.getMass();
                        DataSet pos = lastSnapshot.getPosition(object),
                                gPos = lastSnapshot.getPosition(gObject);
                        double x = gPos.getX() - pos.getX(),
                                y = gPos.getY() - pos.getY(),
                                z = gPos.getZ() - pos.getZ();
                        if (x > 0) xMagnitude += G * (mass / (x * x));
                        else if (x < 0) xMagnitude -= G * (mass / (x * x));
                        if (y > 0) yMagnitude += G * (mass / (y * y));
                        else if (y < 0) yMagnitude -= G * (mass / (y * y));
                        if (z > 0) zMagnitude += G * (mass / (z * z));
                        else if (z < 0) zMagnitude -= G * (mass / (z * z));
                    }
                }

                double xVelocity = lastSnapshot.velocities.get(object).getX() + (xMagnitude / object.getMass()),
                        yVelocity = lastSnapshot.velocities.get(object).getY() + (yMagnitude / object.getMass()),
                        zVelocity = lastSnapshot.velocities.get(object).getZ() + (zMagnitude / object.getMass()),
                        xPosition = lastSnapshot.positions.get(object).getX() + xVelocity,
                        yPosition = lastSnapshot.positions.get(object).getY() + yVelocity,
                        zPosition = lastSnapshot.positions.get(object).getZ() + zVelocity;

                velocities.put(object, new DataSet(xVelocity, yVelocity, zVelocity));
                positions.put(object, new DataSet(xPosition, yPosition, zPosition));
            }
        }

        public DataSet getVelocity(Object object) {
            return uses(object) ? velocities.get(object) : object.getInitialVelocity();
        }

        public DataSet getPosition(Object object) {
            return uses(object) ? positions.get(object) : object.getInitialPosition();
        }

        public Simulator getSimulator() {
            return Simulator.this;
        }
    }
}
