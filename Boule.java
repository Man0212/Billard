package composant;
import affichage.*;

import java.awt.Color;
import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import java.lang.Math;
        import java.time.format.*;
        import java.time.LocalTime;
        import static java.time.temporal.ChronoUnit.MINUTES;

public class Boule{
    public static int taille=30;
    public static int masse=1;
    float x;
    float y;
    float Vx;
    float Vy;
    Color color;
 double Vitesse = 1.5;
  double friction=0.0001;
      Vector<Temp> Temp1;
    Vector<Temp> Temp2;

    public void setTemp1(Vector<Temp> p){this.Temp1=p;}
	public Vector<Temp> getTemp1(){return this.Temp1;}

    public void setTemp2(Vector<Temp> p){this.Temp2=p;}
	public Vector<Temp> getTemp2(){return this.Temp2;}

   public Boule(float x,float y)
   {
       color=Color.RED;
       this.x=x;
       this.y=y;
   }

    public void setx(float p){this.x=p;}
	public float getx(){return this.x;}

    public void sety(float p){this.y=p;}
	public float gety(){return this.y;}

    public void setVx(float p){this.Vx=p;}
	public float getVx(){return this.Vx;}

    public void setVy(float p){this.Vy=p;}
	public float getVy(){return this.Vy;}

    public void setVitesse(double p){this.Vitesse=p;}
	public double getVitesse(){return this.Vitesse;}

    public double distance(Boule A, Boule B) {
        Double distance = Math.sqrt(Math.pow(B.getx() - A.getx(), 2) + Math.pow(B.gety() - A.gety(), 2));
        return distance;
    }


    public void SimpleCollision(Boule ball) {
           ball.Vitesse -= friction;
    if(distance(this,ball)<=Boule.taille+5 && ball.Vitesse>0){

     
            Double teta = Math.atan2(ball.getx() - this.getx(), ball.gety() - this.gety());
            Double sinTeta = Math.sin(teta); 
            Double cosTeta = Math.cos(teta); 
            Double vx = sinTeta * -4*ball.Vitesse;
            Double vy = cosTeta * -4*ball.Vitesse;
            this.setVx(vx.floatValue());
            this.setVy(vy.floatValue());

  
            Double teta2 = 180 - teta;
            Double v2x = Math.sin(teta2)*-ball.Vitesse;
            Double v2y = Math.cos(teta2)*-ball.Vitesse;
            ball.setVx(v2x.floatValue());
            ball.setVy(v2y.floatValue());
            
        }
    }


    public void InsertTemp(Core C)
    {
        Temp1=new Vector<Temp>();
        Temp2=new Vector<Temp>();
        Temp T=new Temp();
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.now();
        if(C.gettour()%2==0)
        {
            T.setapres(localTime);
            if(this.getVitesse()==1.5)
            {
                T.setavant(localTime);
            }
            Temp1.add(T);

        }else{
            T.setapres(localTime);
            if(this.getVitesse()==1.5)
            {
                T.setavant(localTime);
            }
             Temp1.add(T);
        }
    }

}