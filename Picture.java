/* Filename: Picture.java
* Created by: Judy Chun, cs8afug 
* Date: 11/12/2017
*/

/*----------- Program Description: ------------
 * Picture.java will make a copy of the original picture of myself in front of a green-screen background, and modify the green color in the green-screen background
 * to be replaced by the new background Tahiti Island Picture. The program will compare to the original picture with the solid green background when replacing
 * the shirt's color with the shirt's new flower background. A copy of the picture with changed background will be made and the shirt change will be performed
 * on that copy.
 * 
 * Chromakey.java displays the original image of me in front of the green-screen background and modifies a copy of this image by replacing the green color in the 
 * background of the image as the Tahiti Island. A copy of the picture that has its background changed will be displayed by replacing the old shirt color as the 
 * new flower T-shirt background. 
 *
 */

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List
import java.awt.Color;


/**
 * A class that represents a picture.  This class inherits from
 * SimplePicture and allows the student to add functionality to
 * the Picture class.
 *
 * Copyright Georgia Institute of Technology 2004-2005
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture
{
  ///////////////////// constructors //////////////////////////////////

  /**
   * Constructor that takes no arguments
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor
     */
    super();
  }

  /**
   * Constructor that takes a file name and creates the picture
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }

  /**
   * Constructor that takes the width and height
   * @param width the width of the desired picture
   * @param height the height of the desired picture
   */
  public Picture(int width, int height)
  {
    // let the parent class handle this width and height
    super(width,height);
  }

  /**
   * Constructor that takes a picture and creates a
   * copy of that picture
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }

  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }

  ////////////////////// methods ///////////////////////////////////////

  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() +
      " height " + getHeight()
      + " width " + getWidth();
    return output;
  }
  
  /**
   * Copy the calling object and replace all of the Pixels in the copy (within a set tolerance) with Pixels
   * from the background Picture 
   * Input: background: the Picture you will be using to obtain new background pixel values. 
            replaceColor: the Color on your source Picture to be replaced by the background Picture.
            tolerance: determines whether the Color in the source Picture is close enough to replaceColor
   * Result: returns the modified copy 
   */
  public Picture chromakeyBackgroundChange(Picture background,
  Color replaceColor, double tolerance)   //replaces most of the green pixels in the background of the image 
  { 
    int x = 0;  
    int y = 0;
    Pixel sourcePixel; 
    Picture copy = new Picture(this);  //copy of the calling object 
    for(x=0; x<this.getWidth(); x++){   //looping through width and height 
      for(y=0; y<this.getHeight(); y++){
        if(copy.getPixel(x,y).colorDistance(replaceColor) < tolerance)   //using colorDistance() to see how far away a color is from another one and using the tolerance
          //to determine whether the Color in the copy of calling object Picture is close enough to replaceColor 
        {
          sourcePixel = background.getPixel(x,y);  //sourcePixel refers to the pixel of the Tahiti Island background 
          copy.getPixel(x,y).setColor(sourcePixel.getColor());  //setting the pixel of the copy of calling object as the color of sourcePixel 
        }
      }
    }
    return copy;   //returning the modified copy 
  }

  /**
   * Copy the calling object (picture with changed background) and perform the shirt change on that copy 
   * Input:  Shirt: the source picture myShirtSourceImage, or pic2.jpg, used to obtain the new shirt Pixel values.
             Original: the original picture (myselfSourceImage), assist the method with determining which Pixels of the shirt color are part of the shirt, rather than the 
                       changed background, since the background may now have many Pixels similar to the color of your shirt, which should not be changed.
             oldShirtColor: the color of the old shirt that we wanted to change (from myShirtSourceImage)
             startX, startY: top left of where the shirt starts
             width, height: the width and height of the region with the shirt. (the size of the box this creates should only be slightly bigger than the size of the shirt)
             tolerance: determines whether the Color in the calling object Picture is close enough to oldShirtColor

   * Result: new image will be returned by the method 
   */
   public Picture chromakeyShirtChange(Picture shirt, Picture original,
   Color oldShirtColor, int startX, int startY, int width, int height, double tolerance)
  {
    Picture copy = new Picture(this); //making a copy of the calling object (Picture object that has its background changed by the chromakeyBackgroundChange method) 
    Pixel shirtPixel, designPixel, originalPixel;  //shirtPixel refers to the pixel of the copy of calling object, designPixel refers to the pixel of shirt pattern background
    //originalPixel refers to the pixel of original green screen 
    for(int x = startX; x<width+startX; x++){     //looping through width and height starting from the top left of where the shirt starts 
      for(int y = startY; y<height+startY;y++){
        shirtPixel = copy.getPixel(x,y);   //getting pixel from the copy of the calling object 
        designPixel = shirt.getPixel(x-startX,y-startY); //getting pixel from the new shirt pattern background, starting at 0 to avoid out of bound errors
        originalPixel = original.getPixel(x,y);  //getting pixel from original picture (myselfSourceImage) 
        if(originalPixel.colorDistance(oldShirtColor) < tolerance)   //using colorDistance() to see how far away a color is from another one and using the tolerance
          //to determine whether the Color in the calling object Picture is close enough to oldShirtColor 
        {
          shirtPixel.setColor(designPixel.getColor());  //setting the color of shirt in copy of the calling object as the new shirt Pixel values 
        }
      }
    }
    return copy; //this new image will be returned by the method 
   }

} // this } is the end of class Picture, put all new methods before this
