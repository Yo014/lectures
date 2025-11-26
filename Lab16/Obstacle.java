// Author= Santo Mukiza , 101324696
// Author= Charlotte Di Bert , 101334204

import java.util.ArrayList;
import java.awt.Point;
import java.awt.Polygon;

public class Obstacle {
  private ArrayList<Point> vertices;   // An obstacle is just its vertices as points stored in CCW order
  private Polygon asPolygon;  // A polygonal representation of the obstacle for display.
  private boolean polygonWasComputed;  // true if a polygonal version of this obstacle was computed

  public Obstacle() {
    vertices = new ArrayList<Point>();
    polygonWasComputed = false;
  }

  public ArrayList<Point> getVertices() {
    return vertices;
  }


  public void addVertex(int x, int y) {
    vertices.add(new Point(x, y));
    polygonWasComputed = false;
    computePolygon();
  }

  public Point getVertex(int i) {
    return vertices.get(i);
  }

  public int numVertices() {
    return vertices.size();
  }

  // Return a polygonal version of the obstacle
  public Polygon asPolygon() {
    if (polygonWasComputed)
      return asPolygon;
    computePolygon();
    return asPolygon;
  }

  // Compute and store a polygon version of this obstacle, which makes things easier 
  // for displaying and for checking point inclusion
  public void computePolygon() {
    if (polygonWasComputed)
      return;
    asPolygon = new Polygon();
    for (Point v : vertices)
      asPolygon.addPoint(v.x, v.y);
    polygonWasComputed = true;
  }

  // Return true if the given point lies on or within the obstacle boundary.
  public boolean contains(Point p) {
    if (asPolygon == null)
      return false;
    return asPolygon.contains(p);
  }

  // Determines whether or not the given point is on the boundary of the obstacle
  public boolean pointOnBoundary(Point p) {
    boolean onBoundary = false;
    for (int i = 0; i < vertices.size(); i++) {
      Point v1 = vertices.get(i);
      Point v2 = vertices.get((i + 1) % vertices.size());
      if (java.awt.geom.Line2D.Double.linesIntersect(p.x, p.y, p.x, p.y, v1.x, v1.y, v2.x, v2.y))
        onBoundary = true;
    }
    return onBoundary;
  }


  // Check if the obstacle is convex
  public boolean isConvex() {
    int n = vertices.size();
    if (n < 3) return true;
    boolean hasLeftTurn = false;
    boolean hasRightTurn = false;

    for (int i = 0; i < n; i++) {

      Point v0 = vertices.get((i - 1 + n) % n);
      Point v1 = vertices.get(i);
      Point v2 = vertices.get((i + 1) % n);
      double crossProduct = (v1.x - v0.x) * (v2.y - v0.y) - (v1.y - v0.y) * (v2.x - v0.x);

      if (crossProduct > 0) {
        hasLeftTurn = true;
      } else if (crossProduct < 0) {
        hasRightTurn = true;
      }

      if (hasLeftTurn && hasRightTurn) {
        return false;
      }
    }

    return true;


  }

  // Decompose an obstacle into triangles.  Return an arraylist of Obstacles where
  // each obstacle is a triangle.
  public ArrayList<Obstacle> splitIntoTriangles() {
    ArrayList<Obstacle> triangles = new ArrayList<Obstacle>();
    Obstacle ear = null;

    int n = vertices.size();

    if (n < 3) {
      return triangles;
    }
    if (n == 3) {
      triangles.add(this);
      return triangles;
    }

    ArrayList<Point> Q = new ArrayList<>();

    for (Point v : vertices) {
      Q.add(new Point(v.x, v.y));
    }

    while (Q.size() > 3) {
      int earIndex = -1;
      for (int i = 0; i < Q.size(); i++) {
        int s = Q.size();
        Point prev = Q.get((i - 1 + s) % s);
        Point curr = Q.get(i);
        Point next = Q.get((i + 1) % s);


        double turn = ((curr.x - prev.x) * (next.y - prev.y))
                - ((curr.y - prev.y) * (next.x - prev.x));

        if (turn > 0) {
          ear = new Obstacle();
          ear.addVertex(prev.x, prev.y);
          ear.addVertex(curr.x, curr.y);
          ear.addVertex(next.x, next.y);
          earIndex = i;

          for (int k = 0; k < Q.size(); k++) {

            if (k == (i - 1 + s) % s || k == i || k == (i + 1) % s) {
              continue;
            }
            Point currentPoint = Q.get(k);
            if (ear.contains(currentPoint) || ear.pointOnBoundary(currentPoint)) {
              earIndex = -1;
            }
          }

          if (earIndex != -1) {
            break;
          }

        }
      }

      if (earIndex == -1) {
        return triangles;
      }
      Q.remove(earIndex);
      triangles.add(ear);

    }

    Obstacle lastObstacle = new Obstacle();
    lastObstacle.addVertex(Q.getFirst().x, Q.getFirst().y);
    lastObstacle.addVertex(Q.get(1).x, Q.get(1).y);
    lastObstacle.addVertex(Q.get(2).x, Q.get(2).y);

    triangles.add(lastObstacle);

    return triangles;
  }
}