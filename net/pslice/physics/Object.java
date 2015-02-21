package net.pslice.physics;

import java.util.HashSet;

public class Object implements Comparable<Object> {

    private String name;
    private double mass;
    private DataSet position, velocity;
    private HashSet<Force> forces = new HashSet<>();

    public Object(String name, double mass) {
        this(name, mass, 0, 0, 0);
    }

    public Object(String name, double mass, double x, double y, double z) {
        if (mass <= 0)
            mass = 1;
        this.name = name;
        this.mass = mass;
        this.setInitialPosition(x, y, z);
        this.setInitialVelocity(0, 0, 0);
    }

    public void add(Force force) {
        forces.add(force);
    }

    public void clearForces() {
        forces.clear();
    }

    public HashSet<Force> getForces() {
        return forces;
    }

    public DataSet getInitialPosition() {
        return position;
    }

    public DataSet getInitialVelocity() {
        return velocity;
    }

    public double getMass() {
        return mass;
    }

    public String getName() {
        return name;
    }

    public boolean isActedOnBy(Force force) {
        return forces.contains(force);
    }

    public void remove(Force force) {
        if (forces.contains(force))
            forces.remove(force);
    }

    public void setInitialPosition(double x, double y, double z) {
        position = new DataSet(x, y, z);
    }

    public void setInitialVelocity(double x, double y, double z) {
        velocity = new DataSet(x, y, z);
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " (Mass: " + mass + ")";
    }

    @Override
    public int compareTo(Object object) {
        return name.compareToIgnoreCase(object.name);
    }
}
