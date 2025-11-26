//Eric Desrosiers   sn: 101265942
// Santo Mukiza  sn: 101324696


import java.util.ArrayList;
import java.awt.*;
import java.awt.geom.Line2D;
public class PathPlanner {
  private static final Color   OBSTACLE_COLOR = new Color(150, 200, 255); // light pale blue
  private static final Color   OBSTACLE_ID_COLOR = new Color(150, 0, 0);  // Dark red

  private Point 	    start;            // Start point of robot
  private Point	            end;              // End point of robot
  private VectorMap         vmap;             // Needs to be loaded
  private ArrayList<Point>  supportLines;     // Needs to be computed.  Support lines from start location only
  private Graph             visibilityGraph;  // Needs to be computed
  
  public static boolean     ShowObstacles = true;
  public static boolean     ShowStartDest = true;
  public static boolean     ShowSupportLines = false;
  public static boolean     ShowGraph = false;
  public static boolean     ShowObstacleLabels = false;
  
  // A Path Planner also requires a starting and end location, but these will be set later
  public PathPlanner(VectorMap  vm) {
    start = null;  // Needs to be provided later
    end = null;    // Needs to be provided later
    vmap = vm;
    supportLines = null;    //Needs to be computed
    visibilityGraph = null; // Needs to be computed
  }

  // These are used to refresh the start or end points or the map
  public void setStart(int x, int y) { start = new Point(x, y); }
  public void setEnd(int x, int y) { end = new Point(x,y); }
  public void setVectorMap(VectorMap m) { vmap = m; }
  public Graph getVisibilityGraph() { return visibilityGraph; }


  // Returns true if a support line from (x,y) to (supportX, supportY) intersects an obstacle
  // at a point other than at a vertex.
  public boolean supportLineIntersectsObstacle(int x, int y, int supportX, int supportY, ArrayList<Obstacle> allObstacles) {

    for (Obstacle ob : allObstacles) {
      ArrayList<Point> vertices = ob.getVertices();
      int numVertices = vertices.size();

      for (int i = 0; i < numVertices; i++) {
        Point v = vertices.get(i);
        Point va = vertices.get((i + 1) % numVertices);

        // Check if the support line intersects the obstacle edge v->va
        boolean intersects = Line2D.Double.linesIntersect(
                x, y, supportX, supportY,
                v.x, v.y, va.x, va.y
        );

        //Special case: if intersection occurs exactly at endpoints, it's allowed
        if (intersects) {
          boolean atStart = (x == v.x && y == v.y) || (x == va.x && y == va.y);
          boolean atEnd = (supportX == v.x && supportY == v.y) || (supportX == va.x && supportY == va.y);

          if (!atStart && !atEnd) {
            return true;
          }
        }
      }
    }


    return false; // no intersection
  }



  public ArrayList<Point> computeSupportPointsFrom(ArrayList<Obstacle> allObstacles, int x, int y) {
    ArrayList<Point>      supports = new ArrayList<Point>();

    for(Obstacle ob: allObstacles){
      int numVertices= ob.numVertices();

      for(int i = 0; i<numVertices;++i){
        Point v = ob.getVertex(i);
        Point vPrev= ob.getVertex((i-1+numVertices)% numVertices);
        Point vNext= ob.getVertex(((i+1)%numVertices));

        if (x==v.x && y==v.y){
          if(!supports.contains(vPrev))supports.add(vPrev);
          if(!supports.contains(vNext))supports.add(vNext);
          continue;

        }

        double t1 = (v.x-x)*(vNext.y-y)-(v.y-y)*(vNext.x-x);
        double t2 = (v.x-x)*(vPrev.y-y)-(v.y-y)*(vPrev.x-x);


        if (t1 <= 0 && t2 <= 0) {
          // Check visibility before adding
          if (!supportLineIntersectsObstacle(x, y, v.x, v.y, allObstacles)) {
            if (!supports.contains(v)) supports.add(v);
          }
        }
        // Check for right support vertex (both turns are left turns)
        else if (t1 >= 0 && t2 >= 0) {
          // Check visibility before adding
          if (!supportLineIntersectsObstacle(x, y, v.x, v.y, allObstacles)) {
            if (!supports.contains(v)) supports.add(v);
          }
        }
      }
    }

    // Add the end point if visible
    if (end != null && !supportLineIntersectsObstacle(x, y, end.x, end.y, allObstacles)) {
      if (!supports.contains(end)) supports.add(end);

      }
    
    return supports;
  }


  // Create and store a new visibility graph for the given vector map

  public void computeVisibilityGraph() {
    ArrayList<Obstacle> allObstacles = vmap.getObstacles();  // all obstacles in environment

    // Create and store the graph for display access later
    visibilityGraph = new Graph();

    // WRITE YOUR CODE HERE
    visibilityGraph.addNode(new Node(new Point(start.x,start.y)));
    visibilityGraph.addNode(new Node(new Point(end.x,end.y)));

    //add obstacles vertices as nodes
    for(Obstacle ob: allObstacles){
      for(int i=0; i<ob.numVertices(); i++){
        Point v = ob.getVertex(i);

        if(visibilityGraph.node(v.x,v.y) == null){
          visibilityGraph.addNode(new Node(new Point(v.x,v.y)));
        }
      }
    }
    //add edges between visible nodes
    for (Node n: visibilityGraph.getNodes()){
      ArrayList<Point> visiblePoints = computeSupportPointsFrom(allObstacles,n.getLocation().x,n.getLocation().y);

      for(Point p: visiblePoints){
        Node m = visibilityGraph.node(p.x,p.y);
        if(m != null && n != m){
          visibilityGraph.addEdge(n,m);
        }
      }
    }

  }

  
  
  // This procedure displays the results of the PathPlanning algorithm which includes, the obstacles, support lines and visibility graph
  public void displayResults(Graphics aPen, double resolution, Point mousePosition) {
    int magnification= (int)(1/resolution);
    try {
      if (ShowObstacles) {
        // Convert obstacles to polygons, then display the polygons
        int count = 0;
        for (Obstacle ob: vmap.getObstacles()) {
          Polygon p = new Polygon();
          Point avg = new Point();
          for (int i=0; i<ob.numVertices(); i++) {
            int x = MapperApp.MARGIN_X/2 + ob.getVertex(i).x*magnification;
            int y = MapperApp.MARGIN_Y/2 + vmap.getHeight() - ob.getVertex(i).y*magnification;
            p.addPoint(x, y);
            avg.x += x;
            avg.y += y;
          }
        
          aPen.setColor(OBSTACLE_COLOR);
          aPen.fillPolygon(p);
          aPen.setColor(Color.black);
          aPen.drawPolygon(p);
          
          // Show the obstacle number at its center
          if (ShowObstacleLabels) {
            aPen.setColor(OBSTACLE_ID_COLOR);
            aPen.drawString(""+count++, (avg.x/p.npoints)-5, (avg.y/p.npoints)+5);
          }
        }
      }
      if (ShowStartDest) {
        // Now draw the start/end points
        Node s = new Node(new Point(start.x, start.y));
        Node e = new Node(new Point(end.x, end.y));
        s.draw(aPen, vmap.getHeight(), magnification);
        e.draw(aPen, vmap.getHeight(), magnification);
      }
      if (ShowSupportLines) {
        aPen.setColor(Color.red);
        Point visPoint = new Point((int)((mousePosition.x-MapperApp.MARGIN_X/2)*resolution), (int)(vmap.getHeight()*resolution - (mousePosition.y-MapperApp.MARGIN_Y/2)*resolution));
        supportLines = computeSupportPointsFrom(vmap.getObstacles(), visPoint.x, visPoint.y);
        for (Point p: supportLines) {
          if (p != null)
            aPen.drawLine(MapperApp.MARGIN_X/2 + visPoint.x*magnification, MapperApp.MARGIN_Y/2 + vmap.getHeight() - visPoint.y*magnification,
                          MapperApp.MARGIN_X/2 + p.x*magnification, MapperApp.MARGIN_Y/2 + vmap.getHeight() - p.y*magnification);
        }
      }
      if (ShowGraph && (visibilityGraph != null)) {
        visibilityGraph.draw(aPen, vmap.getHeight(), magnification);
      }
    } catch (java.util.ConcurrentModificationException ex) {
      // Do nothing please
    }
  }
}