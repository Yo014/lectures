// Author= Santo Mukiza SN(101324696)
// Author= Shivam Karavadra SN(101258404)


import java.awt.*;
import java.util.ArrayList;

public class AreaCoverage {
  public static final float  ROBOT_RADIUS = 6.8f;              // in cm
  public static final float  ROBOT_DIAMETER = ROBOT_RADIUS*2;  // in cm

  private Point 	        start;            // Start point of robot
  private Point	            end;              // End point of robot
  private VectorMap         vmap;             // Needs to be loaded
  private Graph             gridGraph;        // Needs to be computed
  private ArrayList<Point>  path;             // Path in the spanning tree
  private int               edgeCount;        // the number of new edges traversed on the path
  private Node              startNode;        // starting Node of the Spanning Tree
  
  
  // An Area Coverage will allow the robot to perform area coverage
  public AreaCoverage(VectorMap  vm) {
    start = null;  // Needs to be provided later
    end = null;    // Needs to be provided later
    vmap = vm;
    gridGraph = null;    // Needs to be computed
    path = new ArrayList<Point>();
    startNode = null;
  }

  // These are used to refresh the start or end points or the map
  public void setStart(int x, int y) { start = new Point(x, y); }
  public void setEnd(int x, int y) { end = new Point(x,y); }
  
  public VectorMap getVectorMap() { return vmap; }
  public void setVectorMap(VectorMap m) { vmap = m; }
  public Graph getGridGraph() { return gridGraph; }

  // Get the covergae path as a set of points
  public ArrayList<Point> getCoveragePath() { return path; }
  
  

  // Create and store a new grid graph for the set of convex obstacles
  public void computeGridGraph() {
    ArrayList<Obstacle> obstacles = vmap.getObstacles();   // environment obstacles
    double width = vmap.getWidth()*vmap.getResolution();   // environment width in cm
    double height = vmap.getHeight()*vmap.getResolution(); // environment height in cm
    
    // Create and store the graph for display access later
    gridGraph = new Graph();

    int numCols= (int)(width/ROBOT_DIAMETER);
    int numRows= (int)(height/ROBOT_DIAMETER);

    Node [][] nodes= new Node[numRows][numCols];
    //make nodes
    for (int i= 0; i<numRows;i++){
      for(int j = 0; j< numCols;j++){
        double x = ROBOT_RADIUS + j* ROBOT_DIAMETER;
        double y = ROBOT_RADIUS + i* ROBOT_DIAMETER;

        //display nodes
        Node node= new Node(new Point((int)x,(int)y));
        gridGraph.addNode(node);
        nodes[i][j]=node;

      }
    }

    //horizontal edges
    for (int i= 0; i<numRows;i++){
      for (int j= 0; j<numCols-1;j++){
        gridGraph.addEdge(nodes[i][j],nodes[i][j+1]);
      }
    }

    //vertical edges
    for (int i= 0; i < numRows-1;i++){
      for (int j= 0; j < numCols;j++){
        gridGraph.addEdge(nodes[i][j],nodes[i+1][j]);
      }
    }
    ArrayList<Node> badNodes= new ArrayList<Node>();
    for (Node n: gridGraph.getNodes()){
      Point nodeLocation = n.getLocation();
      boolean isInvalid= false;

      for(Obstacle obj: obstacles){
        if(obj.contains(nodeLocation)){
          isInvalid = true;
          break;
        }

        ArrayList<Point> vertices= obj.getVertices();
        for(int i =0 ; i< vertices.size();i++){
          Point v1= vertices.get(i);
          Point v2= vertices.get((i+1)% vertices.size());

          double distanceToEdge= java.awt.geom.Line2D.ptSegDist(v1.x, v1.y, v2.x, v2.y, nodeLocation.x,nodeLocation.y);

          if (distanceToEdge <=ROBOT_RADIUS){
            isInvalid = true;
            break;
          }
        }
        if(isInvalid){
          break;
        }

      }
      if(isInvalid){
        badNodes.add(n);
      }

    }
    for (Node badNode :badNodes){
      gridGraph.deleteNode(badNode);
    }



  }


  // Recursively compute the depth-first order spanning tree from the grid graph starting at node n, coming from edge e
  private void computeSpanningTreeFrom(Node n, Edge e) {
    if (n.hasBeenVisited()) {
      return;
    }
    //visited
    n.setVisited(true);

    if (e != null && e.getStartNode() != e.getEndNode()) {
      e.setSelected(true);
    }
    ArrayList<Edge> edges = n.incidentEdges();
    for (Edge edge : edges) {
      Node otherNode= edge.otherEndFrom(n);

     computeSpanningTreeFrom(otherNode,edge);
    }
  }
  
  
  
  // Recursively compute the path in the spanning tree from the starting location having come in from edge e
  private void computeCoveragePathFrom(Node n, Edge e) {
    
    // ADD YOUR CODE HERE
    
  }



  // Recursively compute the heights of ther nodes in the spanning tree from the starting root node
  private void computeNodeHeightsFrom(Node n) {
    n.setVisited(true);
    	
    ArrayList<Edge> edges = n.incidentEdges();
    if (edges.size() == 1) 
      n.setDistance(0);
    else
      n.setDistance(1);
      
    int max = 0;
    for (Edge e: edges) {
      Node n2 = e.otherEndFrom(n);
      if (!n2.hasBeenVisited()) {
		computeNodeHeightsFrom(n2);
      	if (n2.getDistance() > max)
        	max = (int)n2.getDistance();
      }
    }
    n.setDistance(n.getDistance() + max);
  }

  
  // Compute the depth-first order spanning tree from the grid graph with the starting node closest to the start point
  public void computeSpanningTree() {    
    // If there are no nodes, then stop now
    if ((gridGraph == null) || (gridGraph.getNodes().isEmpty()))
      return;
      
    // Reset all the previous pointers
    for (Node n: gridGraph.getNodes()) {
      n.setVisited(false);
      n.setDistance(0);
    }
      
    // Find the Node closest to (x,y)
    startNode = gridGraph.getNodes().get(0);
    for (Node n: gridGraph.getNodes()) {
      if (n.getLocation().distanceSq(start.x, start.y) < startNode.getLocation().distanceSq(start.x, start.y))
        startNode = n;
    }
    System.out.println("Start Node = " + startNode);
    // Recursively compute the spanning tree
    // We pass in a "dummy edge" that is not part of the tree just so that we don't have to check for null
    Edge dummyEdge = new Edge(startNode, startNode);
    computeSpanningTreeFrom(startNode, dummyEdge);
   
    // Now remove all edges that are not selected
    ArrayList<Edge>  allEdges = gridGraph.getEdges();
    for (Edge e: allEdges)
      if (!e.isSelected())
        gridGraph.deleteEdge(e);
        
    // Now compute the node heights
    for (Node n: gridGraph.getNodes()) { 
      n.setVisited(false);
      n.setDistance(0); // Reset all the distances to 0
    }
    computeNodeHeightsFrom(startNode);
    startNode.setDistance(startNode.getDistance()+1);
    
    // Prepare all node labels
    for (Node n: gridGraph.getNodes()) n.setLabel(""+(int)n.getDistance());

    // Now compute the coverage path
    path.clear();                        // Clear the previously-computed path, if there was one
    edgeCount = gridGraph.getEdges().size();
    System.out.println("Spanning Tree has " + edgeCount + " edges");
    for (Node n: gridGraph.getNodes())  // Reset all the previous pointers
      n.setPrevious(null);
    // We pass in a "dummy edge" that is not part of the tree just so that we don't have to check for null
    computeCoveragePathFrom(startNode, dummyEdge);
  }
}