/* Filename: Chromakey.java
* Created by: Judy Chun, cs8afug
* Date: 11/12/2017
* 
* 1. What would happen if the tolerance for distinguishing the background and you is set too high? Too low? 
* If the tolerance for distinguisihing the background and me is too high, a lot of pixels will be replaced. For example, if I set my tolerance
* as 900 for the chromakeyShirtChange method, the shirt's new background would appear as a solid-colored box. Whereas, if I set my tolerance
* too low, barely any pixels will be replaced. Thus, you have to explore to find out the optimal points and set the tolerance neither too high
* nor too low. 
* 
* 2. Why should the program make comparisons to the original picture with the solid green background when replacing the shirt's color with
* the shirt's new background instead of the resulting picture from part A (with the green background replaced)?
* The program should make a comparison to the original picture with the solid green background instead of the resulting picture from part A
* because the original picture assist the mehtod with determining which Pixels of the shirt color are part of the shirt, rather than the
* changed background, since the background may now have many Pixels similiar to the color of your shirt, which should not be changed. 
* For example, let's say the modified green screen background is now an image of the sky, but you're also wearing a sky-patterned shirt. In this 
* case, the program would not be able to differentiate between the background and the shirt. Thus, the program should compare back to the
* original picture when replacing the shirt's color with a new shirt pattern. 
* 
*/
import java.awt.Color;

public class Chromakey
{
    //The line below is magic, you don't have to understand it (yet)
    public static void main (String[] args)
    {
      String fileName1 = "pic0.jpg";
      Picture picture1 = new Picture(fileName1);  //original image (myselfSourceImage) with green-screen 
      String fileName2 = "pic1.jpg";
      Picture picture2 = new Picture(fileName2); //background picture of Tahiti Islands  
      Color replaceColor = new Color(1,255,0);   //the color of the green background that we wanted to change 
      Picture copy = picture1.chromakeyBackgroundChange(picture2,replaceColor, 197); //method call to chromakeyBackgroundChange to change the background (part A); 
      //creating a copy of the calling object and modifying the copy, not the calling object itself 
      picture1.explore();  //Picture object of the original image (myselfSourceImage) displayed with explore()
      copy.show();  //resulting Picture is displayed 
      
      String fileName3 = "pic2.jpg";   //the source picture myShirtSourceImage used to obtain the new shirt Pixel values 
      Picture shirt = new Picture(fileName3); 
      Color oldShirtColor = new Color(224,69,67); //the color of the old shirt that we wanted to change
      Picture shirtChange = copy.chromakeyShirtChange(shirt, picture1, oldShirtColor, 141, 173, 94, 82, 95);    //method call to chromakeyShirtChange to change T-shirt color
      //since the methods return a modified copy, it is a new object, and can be stored into a new reference variable 
      shirtChange.show();  //display the final Picture
 
      
    }
}
