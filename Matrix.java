package com.eonsahead.swing;

/**
 * Model a matrix.
 *
 * @author Leon Tabak
 * @version 1 April 2020
 */
public class Matrix {

    private final double[][] elements;

    public Matrix() {
        this.elements = new double[4][4];
        this.identity();
    } // Matrix()

    public double get(int row, int column) {
        return this.elements[row][column];
    } // get( int, int )
    // used to get a specified value in the matrix

    public void set(int row, int column, double value) {
        this.elements[row][column] = value;
    } // set( int, int, double )

    public final void identity() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j) {
                    this.set(i, j, 1.0);
                } // if
                else {
                    this.set(i, j, 0.0);
                } // else
            } // for
        } // for
    } // identity()

    /**
     * rotationX creates a matrix that when multiplied by a vector will rotate it around the x axis
     * 
     * @param angle angle of rotation
     */
    public void rotationX(double angle) {
        this.identity();
        this.set(1, 1, Math.cos(angle));
        this.set(1, 2, -Math.sin(angle));
        this.set(2, 1, Math.sin(angle));
        this.set(2, 2, Math.cos(angle));
    } // rotationX( double )

    /**
     * rotationY creates a matrix that when multiplied by a vector will rotate it around the y axis
     * 
     * @param angle angle of rotation
     */
    public void rotationY(double angle) {
        this.identity();
        this.set(0, 0, Math.cos(angle));
        this.set(0, 2, Math.sin(angle));
        this.set(2, 0, -Math.sin(angle));
        this.set(2, 2, Math.cos(angle));
    } // rotationY( double )    
    
    /**
     * rotationZ creates a matrix that when multiplied by a vector will rotate it around the z axis
     * 
     * @param angle angle of rotation
     */
    public void rotationZ(double angle) {
        this.identity();
        this.set(0, 0, Math.cos(angle));
        this.set(0, 1, -Math.sin(angle));
        this.set(1, 0, Math.sin(angle));
        this.set(1, 1, Math.cos(angle));
    } // rotationZ( double )

    /**
     * scale creates a matrix that will change the size of the polygon
     * 
     * @param xFactor the increase amount on the x axis
     * @param yFactor the increase amount on the y axis
     * @param zFactor the increase amount on the z axis
     */
    public void scale(double xFactor, double yFactor, double zFactor) {
        this.identity();
        this.set(0, 0, xFactor);
        this.set(1, 1, yFactor);
        this.set(2, 2, zFactor);
    } // scale( double, double, double )

    /**
     * translate creates a vector that will move the polygon
     * @param deltaX amount of movement in the x axis
     * @param deltaY amount of movement in the y axis
     * @param deltaZ amount of movement in the z axis
     */
    public void translate(double deltaX, double deltaY, double deltaZ) {
        this.identity();
        this.set(0, 3, deltaX);
        this.set(1, 3, deltaY);
        this.set(2, 3, deltaZ);
    } // translate( double, double, double )

    /**
     * multiply a matrix with another matrix
     * 
     * @param otherMatrix the second matrix
     * @return Matrix product
     */
    public Matrix multiply(Matrix otherMatrix) {
        Matrix product = new Matrix();
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                double sum = 0.0;
                for (int k = 0; k < 4; k++) {
                    sum += this.elements[row][k]
                            * otherMatrix.elements[k][column];
                } // for
                product.set(row, column, sum);
            } // for
        } // for
        return product;
    } // multiply( Matrix )

    /**
     * multiply a vector with another vector
     * @param v other vector
     * @return new Vector
     */
    public Vector multiply(Vector v) {
        double x = 0.0;
        for (int i = 0; i < 3; i++) {
            x += this.get(0, i) * v.get(i);
        } // for

        double y = 0.0;
        for (int i = 0; i < 3; i++) {
            y += this.get(1, i) * v.get(i);
        } // for

        double z = 0.0;
        for (int i = 0; i < 3; i++) {
            z += this.get(2, i) * v.get(i);
        } // for

        return new Vector(x, y, z);
    } // multiply( Vector )

    /**
     * rowToString turns a row of the matrix into a string
     * 
     * @param row the row number
     * @return string
     */
    private String rowToString(int row) {
        StringBuilder result = new StringBuilder();
        result.append("( ");
        for (int i = 0; i < 3; i++) {
            result.append(this.get(row, i));
            result.append(",");
        } // for
        result.append(this.get(row, 3));
        result.append(" )");
        return result.toString();
    } // rowToString( int )

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[ ");
        for (int i = 0; i < 4; i++) {
            result.append(rowToString(i));
        } //
        result.append(" ]");
        return result.toString();
    } // toString()

    public static void main(String[] args) {
        Matrix identity = new Matrix();
        System.out.println("identity = " + identity);

        Matrix product = identity.multiply(identity);
        System.out.println("product = " + product);

        Matrix ccw = new Matrix();
        ccw.rotationZ(Math.PI / 4);
        System.out.println("counter-clockwise rotation = " + ccw);

        Matrix cw = new Matrix();
        cw.rotationZ(-Math.PI / 4);
        System.out.println("clockwise rotation = " + cw);

        Matrix netRotation = ccw.multiply(cw);
        System.out.println("net rotation = " + netRotation);
    } // main( String [] )

} // Matrix
