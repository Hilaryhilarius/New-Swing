package com.eonsahead.swing;

import java.util.ArrayList;
import java.util.List;

public class Vector {

    private double[] elements = new double[4];

    public Vector() {
        for (int i = 0; i < 4; i++) {
            this.elements[i] = 0.0;
        } // for
    } // Vector()

    public Vector(double x, double y, double z) {
        this.elements[0] = x;
        this.elements[1] = y;
        this.elements[2] = z;
        this.elements[3] = 1.0;
    } // Vector( double, double, double )

    public Vector(double x, double y, double z, double h) {
        this.elements[0] = x;
        this.elements[1] = y;
        this.elements[2] = z;
        this.elements[3] = h;
    } // Vector( double, double, double, double )

    public double get( int index ) {
        return this.elements[index];
    } // get( int )
    
    public void set( int index, double value ) {
        this.elements[index] = value;
    } // set( int, double )
    
    public void set( Vector v ) {
        this.elements[0] = v.elements[0];
        this.elements[1] = v.elements[1];
        this.elements[2] = v.elements[2];
        this.elements[3] = v.elements[3];
    } // set( Vector )
    
    public Vector add( Vector v ) {
        double x = this.get(0) + v.get(0);
        double y = this.get(1) + v.get(1);
        double z = this.get(2) + v.get(2);        
        return new Vector( x, y, z );
    } // add( Vector )
    
    public Vector subtract( Vector v ) {
        double x = this.get(0) - v.get(0);
        double y = this.get(1) - v.get(1);
        double z = this.get(2) - v.get(2);        
        return new Vector( x, y, z );
    } // subtract( Vector )
    
    public double dot( Vector v ) {
        double xProduct = this.get(0) * v.get(0);
        double yProduct = this.get(1) * v.get(1);
        double zProduct = this.get(2) * v.get(2);
        return xProduct + yProduct + zProduct;
    } // dot( Vector )
    
    public double magnitude() {
        return Math.sqrt( this.dot( this ) );
    } // magnitude()
    
    public Vector normalize() {
        double length = this.magnitude();
        double x = this.get(0) / length;
        double y = this.get(1) / length;
        double z = this.get(2) / length;
        return new Vector( x, y, z );
    } // normalize()
    
    public Vector cross( Vector v ) {
        double x = this.get(1) * v.get(2) - this.get(2) * v.get(1);
        double y = this.get(2) * v.get(0) - this.get(0) * v.get(2);
        double z = this.get(0) * v.get(1) - this.get(1) * v.get(0);
        return new Vector( x, y, z );
    } // cross( Vector )
    
    public static void main( String [] args ) {
        System.out.println( "hi");
        List<List<Vector>> surface = new ArrayList<>();
        
        List<Vector> poly0 = new ArrayList<>();
        poly0.add(new Vector());
        poly0.add(new Vector());
        
        List<Vector> poly1 = new ArrayList<>();
        poly1.add(new Vector());
        poly1.add(new Vector());
        
        surface.add( poly0 );
        surface.add( poly1 );
        
        System.out.println( "size = " + surface.size() );
    } // main( String [] )
} // Vector
