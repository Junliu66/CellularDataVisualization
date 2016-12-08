package cellularData;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.Graphics;

import java.util.LinkedList;


 
public class MainFrame extends JPanel{
   LinkedList<Country> countries; 
   int width;
   int height;
   private Font font;
 
   public MainFrame(LinkedList<Country> countries) {
      this.countries = countries;
      font = new Font("Serif", Font.PLAIN, 11);
   }
}
 

 
   