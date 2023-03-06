package composant;

import java.awt.Color;
import java.awt.*;
import javax.swing.*;

public class But{
    public static int taille=40;
    int x;
    int y;
    static Color color=Color.BLACK;

    public int getTaille(){return this.taille;}

    public void setx(int p){this.x=p;}
	public int getx(){return this.x;}

    public void sety(int p){this.y=p;}
	public int gety(){return this.y;}

   public But(int x,int y)
   {
       this.x=x;
       this.y=y;
   }
}