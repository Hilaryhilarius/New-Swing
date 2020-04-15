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
    public final List<Shape> rectangles = new ArrayList<>();

    public Polygon3D(int numberOfSides, double radius, double height) {
            for (int i = 0; i < numberOfSides; i++) {
                double fraction = ((double) i) / numberOfSides;
                double angle = fraction * 2.0 * Math.PI;
                double x = radius * Math.cos(angle);
                double y = radius * Math.sin(angle);
                double z = height / 2;
                Vector v = new Vector(x, y, z);
                Vector w = new Vector(x, y, -z);
                this.vertices.add(v);
                this.bottom.add(w);
            } // for
    } // Polygon3D( int, double )

    public void transform(Matrix m) {
        for (Vector u : this.vertices) {
            u.set( m.multiply(u) );
        } // for 
        for (Vector u : this.bottom) {
            u.set( m.multiply(u) );
        } // for 
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
    public Shape getShape2() {
        GeneralPath path = new GeneralPath();

        Vector w = this.bottom.get(0);
        double x = w.get(0);
        double y = w.get(1);
        path.moveTo(x, y);

        for (int i = 1; i < this.bottom.size(); i++) {
            w = this.bottom.get(i);
            x = w.get(0);
            y = w.get(1);
            path.lineTo(x, y);
        } // for

        path.closePath();

        return path;

    } // Polygon3D
    public List<Shape> getShape3() {
        for (int i = 0; i < this.vertices.size(); i++){
            GeneralPath path1 = new GeneralPath();
            
            Vector v = this.vertices.get(i);
            Vector v1 = this.vertices.get(i+1);
            Vector w = this.bottom.get(i);
            Vector w1 = this.bottom.get(i+1);
            double x0 = v.get(0);
            double y0 = v.get(1);
            path1.moveTo(x0, y0);
            
            double x1 = w.get(0);
            double y1 = w.get(1);
            path1.moveTo(x1, y1);
            
            double x2 = w1.get(0);
            double y2 = w1.get(1);
            path1.moveTo(x2, y2);
            
            double x3 = v1.get(0);
            double y3 = v1.get(1);
            path1.moveTo(x3, y3);
            
            path1.moveTo(x0, y0);
            path1.closePath();
            rectangles.add(path1);
        }
        return rectangles;

    } // Polygon3D
}
