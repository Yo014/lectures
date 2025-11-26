import java.util.*;
import java.io.*;
import com.cyberbotics.webots.controller.Display;
import com.cyberbotics.webots.controller.ImageRef;

public class Map {
  
  private short[][]  occupancyGrid;
  private int[]      gridImage; // For display purposes only
  private int        width;
  private int        height;
  private double     resolution; 
  private boolean    isBinary;
  private short      maxValue = 0;
  private short      minValue = 0;
    
  private int        DISPLAY_COUNTER = 0; // This is used to limit the number of times to display

  // Create a mao with the given with, height,  resolution and whether or not it is binary (otherwise gray scale)
  public Map(int w, int h, double res, boolean binary) {
    width = w;
    height = h;
    resolution = res;
    occupancyGrid = new short[width][height];
    gridImage = new int[width*height];
    isBinary = binary;
  }

  // Returns true if the given point is within the occupancy grid boundaries and false otherwise.
  boolean isInsideGrid(double x, double y) {
    return (((int)(x/resolution+width/2) >= 0) && ((int)(y/resolution+height/2) >= 0) &&
            ((int)(x/resolution+width/2) < width) && ((int)(y/resolution+height/2) < height));
  }
  
  // Increase the value at the grid by one
  void increaseGridValue(double x, double y) {
    short value = occupancyGrid[(int)(x/resolution+width/2)][(int)(y/resolution+height/2)];
    occupancyGrid[(int)(x/resolution+width/2)][(int)(y/resolution+height/2)] = ++value;
    if ((value > maxValue) && (value <= 255)) maxValue = value;
    if (value < minValue) minValue = value;
  }
  
  // Decrease the value at the grid by one
  void decreaseGridValue(double x, double y) {
    short value = occupancyGrid[(int)(x/resolution+width/2)][(int)(y/resolution+height/2)];
    occupancyGrid[(int)(x/resolution+width/2)][(int)(y/resolution+height/2)] = --value;
    if ((value > maxValue) && (value <= 255)) maxValue = value;
    if (value < minValue) minValue = value;
  }
  
  // Set the value at the grid to the given value
  void setGridValue(double x, double y, short value) {
    occupancyGrid[(int)(x/resolution+width/2)][(int)(y/resolution+height/2)] = value;
  }
  
  
  
  /**************************************************************************************************************/
  /* Apply the sensor model */
  /**************************************************************************************************************/
  public void applySensorModelReading(double sensorX, double sensorY, int sensorAngle, double distance, double beamWidthInDegrees, double distanceErrorAsPercent) {
    // YOU WRITE CODE HERE
   
    double sigma = Math.toRadians(beamWidthInDegrees);
    double phi = Math.toRadians(sensorAngle);
    double epsilon = distanceErrorAsPercent;
    double INC = 0.25;
    
    double objX, objY;
    
    double rMin = distance*(1-epsilon);
    double rMax = distance*(1+epsilon);
    double aMin = -sigma/2;
    double aMax = sigma/2;
    
    
    for(double r = rMin; r <= rMax; r += INC){
      double xa = sensorX + (r * Math.cos(phi+sigma/2));
      double ya = sensorY + (r * Math.sin(phi+sigma/2));
      double xb = sensorX + (r * Math.cos(phi-sigma/2));
      double yb = sensorY + (r * Math.sin(phi-sigma/2));
      
      double omega = (sigma / Math.sqrt(Math.pow((xa-xb), 2) + Math.pow((ya-yb), 2))) / 2;
      
      for(double a = aMin; a <= aMax; a += omega){
        objX = sensorX + (r * Math.cos(phi+a));
        objY = sensorY + (r * Math.sin(phi+a));
        if(isInsideGrid(objX, objY)){
          if(isBinary){
            setGridValue(objX, objY, (short)1);
          } else {
            increaseGridValue(objX, objY);
          }
          
        }
      }
    }
    
  }
  
  
  
  
  
  
  
  // Set one point in the grid to 1 or increase by 1
  public void setObjectPoint(float x, float y) {
    if (isInsideGrid(x, y)) {
      if (isBinary)
        setGridValue(x, y, (short)1);
      else {
        increaseGridValue(x, y);
      }
    }
  }
  
  // Set one point in the grid to 0 or decrease by 1
  public void resetObjectPoint(int x, int y) {
    if (isInsideGrid(x, y)) {
      if (isBinary)
        setGridValue(x, y, (short)0);
      else {
        decreaseGridValue(x, y);
      }
    }
  }

  // Draw the Occupancy Grid.   Do Not Change This Code.
  public void draw(Display aPen, int magnification) {
    if (DISPLAY_COUNTER++ %250 != 0)
      return;
    if (isBinary)
      aPen.setColor(0x000000); // black
    try {
      // copy grid into image
        for (int y=0; y<height; y++) {
         for (int x=0; x<width; x++) {
            if (occupancyGrid[x][height - 1 - y] != 0) {
              if (!isBinary) {
                double darkenFactor = 255;
                if (maxValue != minValue)
                  darkenFactor /= (maxValue - minValue);
                short shade = (short)(255-occupancyGrid[x][height - 1 - y]*darkenFactor);
                if (shade < 0) shade = 0;
                gridImage[y*width + x] = 0xFF000000 + shade*0x010000 + shade*0x0100 + shade;
              }
              else 
                gridImage[y*width + x] = 0xFF000000;
            }
            else
              gridImage[y*width + x] = 0xFFFFFFFF;
        }
      }
      ImageRef imageOfGrid = aPen.imageNew(width, height, gridImage, Display.ARGB);
      aPen.imagePaste(imageOfGrid, 0, 0, true);
    } catch (java.util.ConcurrentModificationException ex) {
      // Do nothing please
    }
  }

}