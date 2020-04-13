package com.eonsahead.swing;

import com.eonsahead.swing.Matrix;
import com.eonsahead.swing.Vector;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

public class Polygon3D {

    private final List<Vector> vertices = new ArrayList<>();
    private final List<Vector> bottom = new ArrayList<>();

    public Polygon3D(int numberOfSides, double radius, double height) {
        for (int i = 0; i < numberOfSides; i++) {
            double fraction = ((double) i) / numberOfSides;
            double angle = fraction * 2.0 * Math.PI;
            double x = radius * Math.cos(angle);
            double y = radius * Math.sin(angle);
            double z = height;
            Vector v = new Vector(x, y, 0.0);
            Vector w = new Vector(x,y,-1 * z);
            this.vertices.add(v);
            this.bottom.add(w);
        } // for
    } // Polygon3D( int, double )

    public void transform(Matrix m) {
        for (Vector u : this.vertices) {
            u.set( m.multiply(u) );
        } // for 
        for (Vector f : this.bottom) {
            f.set( m.multiply(f) );
        }
    } // transform( Matrix )

    public Shape getShape() {
        GeneralPath path = new GeneralPath();

        Vector v = this.vertices.get(0);
        double x = v.get(0);
        double y = v.get(1);
        path.moveTo(x, y);

        for (int i = 1; i < this.vertices.size(); i++) {
            v = this.vertices.get(i);
            x = v.get(0);
            y = v.get(1);
            path.lineTo(x, y);
        } // for

        path.closePath();

        return path;
    } // getShape()

} // Polygon3D
