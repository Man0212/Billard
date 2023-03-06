
package affichage;
import composant.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
        import java.time.format.*;
        import java.time.LocalTime;
        import static java.time.temporal.ChronoUnit.MINUTES;
        import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.Timer;
import javax.imageio.ImageIO;

public class Core extends JPanel {

public static final int height = 500;
 public static final int width = 800;
 public static final double friction = 0.0019;

    But[] HOLE;
    Boule[] BALL;
    Graphics g;
    boolean move;
    int tour=1;
    JLabel one,two;
    int TJ1=0;
    int TJ2=0;
    Player Player1,Player2;
    Player[] LP;
    Game[] LG;
    int scoreJ1 , scoreJ2;
    int BouleMaty;
    int IDChampionnat;
    int IDMatch;
    boolean ExistWinner=false;


    public void setmove(boolean p){this.move=p;}
	public boolean getmove(){return this.move;}

    public void setBALL(Boule[] p){this.BALL=p;}
	public Boule[] getBALL(){return this.BALL;}

    public void settour(int p){this.tour=p;}
	public int gettour(){return this.tour;}

    public void setTJ1(int p){this.TJ1=p;}
	public int getTJ1(){return this.TJ1;}

    public void setTJ2(int p){this.TJ2=p;}
	public int getTJ2(){return this.TJ2;}

    public void setScoreJ1(int p){this.scoreJ1=p;}
	public int getScoreJ1(){return this.scoreJ1;}

    public void setScoreJ2(int p){this.scoreJ2=p;}
	public int getScoreJ2(){return this.scoreJ2;}
    
    public void setPlayer1(Player p){this.Player1=p;}
	public Player getPlayer1(){return this.Player1;}

    public void setPlayer2(Player p){this.Player2=p;}
	public Player getPlayer2(){return this.Player2;}

    public void setIDMatch(int p){this.IDMatch=p;}
	public int getIDMatch(){return this.IDMatch;}
    
    public void setChampionnat(int p){this.IDChampionnat=p;}
	public int getChampionnat(){return this.IDChampionnat;}


    public Core(But[] HOLE,Boule[] BALL,Player[] LP,Game[] LG)
    {
        this.HOLE=HOLE;
        this.BALL=BALL;
        this.LP=LP;
        this.LG=LG;
    }

    
    
	public void paint(Graphics g) {
    super.paint(g);		

    g.setColor(new Color(0,80, 0));
    g.fillRect(0,0,width,height);

    g.setColor(Color.RED);
    g.drawRect(10,10,width-43,height-65);
    g.drawRect(9,9,width-41,height-63);
    g.drawRect(3,3,width-28,height-50);

    if(TJ1!=0 && TJ2!=0)
    {
    g.drawString("Stat J1 :" + this.GetStat(scoreJ1,TJ1),230,80);
    g.drawString("Stat J2 :" + this.GetStat(scoreJ2,TJ2),width-250,80);
    }
    g.setColor(Color.WHITE);
    g.fillOval((int)BALL[0].getx(),(int)BALL[0].gety(),Boule.taille,Boule.taille);

    g.drawString("J1 : " + getPlayer1().getnom() +" Score : "+ scoreJ1  ,200,20);
    g.drawString("NB tir J1 :" + TJ1,100,40);
    g.drawString("J2 : " + getPlayer2().getnom() +" Score : "+ scoreJ2  ,width-220,20);
    g.drawString("NB tir J2 :" + TJ2 ,width-120,40);

    /*BALL[0].InsertTemp(this);
    for(int i=0;i<BALL[0].getTemp1().size();i++)
    {
        System.out.println(BALL[0].getTemp1().get(i).getavant());
        System.out.println(BALL[0].getTemp1().get(i).getapres());
    }*/

    int T=1;
    if(tour%2==0)
    {
        T=2;
    }
    g.drawString("Tour de J" + T,width/2,70);

    shoot(BALL[0]);
    CheckGagnant(g);

    try {Thread.sleep(10);
    } catch (Exception e) {}

    for(int i=0;i<HOLE.length;i++)
    {
        g.setColor(Color.BLACK);
        g.fillOval(HOLE[i].getx(),HOLE[i].gety(),But.taille,But.taille);
    }


    for(int i = 0; i < this.BALL.length; i++) {
        shoot(this.BALL[i]); 
        WallCollision(this.BALL[i]);
        for(int j = 0; j < this.BALL.length; j++) {
            if(i != j) {
                this.BALL[j].SimpleCollision(this.BALL[i]);               
            }
        }
    }

       for(int i=1;i<BALL.length;i++)
    {
        g.setColor(new Color(0,i*20, 204));
        g.fillOval((int)BALL[i].getx(),(int)BALL[i].gety(),Boule.taille,Boule.taille);
    }

        if(!ExistWinner)
        {
            repaint();
        }

	}

    public void shoot(Boule B)
    {
        GetPoint();
        if(move && B.getVitesse()>0){
        double A=B.getVitesse() ;
        A-=friction;
        B.setVitesse(A);

        float x = B.getx();
        float y = B.gety();
        
        x+=B.getVx() * B.getVitesse();
        y+=B.getVy() * B.getVitesse();

        B.setx(x);
        B.sety(y);

        }
        else{
            B.setVitesse(1.5);
            move=false;
            for(int i=1;i<BALL.length;i++)
            {
                BALL[i].setVx(0);
                BALL[i].setVy(0);
            }
        }
    }

    public void WallCollision(Boule B) {
        if(B.getx() >= width - B.taille * 1.5 || B.getx() <= -1 * B.taille ) {
            B.setVx(-1 * B.getVx());
        }
        if(B.gety() >= height - B.taille * 2 || B.gety() <= 0) {
            B.setVy(-1 * B.getVy());
        }
    }

    public double distance(float x1,float y1,float x2,float y2)
    {
        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        return distance;
    }

    public void GetPoint()
    {
        for(int i=0;i<BALL.length;i++)
        {
            for(int j=0;j<HOLE.length;j++)
            {
                if(distance(BALL[i].getx(),BALL[i].gety(),HOLE[j].getx(),HOLE[j].gety())<=But.taille/2)
                {
                    BALL[i].setx(-1000);
                    BALL[i].sety(-1000);
                    if(i!=0)
                    {
                        if(tour%2==0)
                        {
                        scoreJ1+=1;
                        BouleMaty++;
                        }else{
                        scoreJ2+=1;
                        BouleMaty++;
                        }
                    }
                    
                    if(i==0)
                    {
                        BALL[i].setx(150);
                        BALL[i].sety((height/2)-30);
                        BALL[i].setVx(0);
                        BALL[i].setVy(0);    
                        BALL[i].setVitesse(0);
                    }
                }
            }
        }
    }

    public void CheckGagnant(Graphics g)
    {
        int Autreboule=BALL.length-1;
        g.setFont(new Font("Serif",Font.BOLD,40));
        Player Winner=new Player();
        if(BouleMaty==Autreboule)
        {
            if(scoreJ2>scoreJ1)
            {
                g.drawString("VICTOIRE de J2",width/2,height/2);
                Winner = this.getPlayer2();
            }
            if(scoreJ1>scoreJ2)
            {
                g.drawString("VICTOIRE de J1",width/2,height/2);
                Winner = this.getPlayer1();
                
            }
            if(scoreJ1==scoreJ2)
            {
                if(this.GetStat(scoreJ1,TJ1)<this.GetStat(scoreJ2,TJ2))g.drawString("VICTOIRE de J2",width/2,height/2);Winner = this.getPlayer2();
                if(this.GetStat(scoreJ1,TJ1)>this.GetStat(scoreJ2,TJ2))g.drawString("VICTOIRE de J1",width/2,height/2);Winner = this.getPlayer1();
            }
            try{    
                if( (scoreJ1+scoreJ2)==BouleMaty){ExistWinner=true;}
                
                Player.Ending(getChampionnat() , Winner.getIDPlayer(),getPlayer1().getIDPlayer() , getPlayer2().getIDPlayer() , getIDMatch(),getTJ1() , getTJ2() ,getScoreJ1() , getScoreJ2());   
            }
            catch(Exception e){

            }
        }
    }

    public static double GetStat(int Score, int NBTir)
    {
        double resultat=Score*100/NBTir;
        return resultat;
    }



    public static void AverrageTime(Vector<LocalTime> Temp1)
    {
          if(Temp1.size()>=2)
            {
                System.out.println(MINUTES.between(Temp1.get(0) , Temp1.get(1)));
            }
        
    }

    public static int getTotalPointParJoueur(int IDPlayer,Game[] LG)
    {
        int a=0;
        for(int i=0;i<LG.length;i++)
        {
            if(IDPlayer==LG[i].getidWinner())
            {
                a++;
            }
        }
        return a;
    }

    public static Player getWinnerParChampionnat(int IDChampionnat,Game[] lg,Player[] LP)
    {
        Game[] LG=ListGameParChampionnat(IDChampionnat,lg);  

        int J= getTotalPointParJoueur(LP[0].getIDPlayer(),LG);
        for(int i=1;i<LP.length;i++)
        {
            for(int j=0;j<LG.length;j++)
            {
                if(getTotalPointParJoueur(LP[i].getIDPlayer(),LG)>J)
                {
                    J=getTotalPointParJoueur(LP[i].getIDPlayer(),LG);
                }
            }
        }

        Player P=new Player();
        for(int i=1;i<LP.length;i++)
        {
            for(int j=0;j<LG.length;j++)
            {
                if(getTotalPointParJoueur(LP[i].getIDPlayer(),LG)==J)
                {
                    P=LP[i];
                }
            }
        }
        return P;
    }

    public static Game[] ListGameParChampionnat(int IDChampionnat,Game[] LG)
    {
        Vector<Game> A=new Vector<Game>();
        int a=0;
        for(int i=0;i<LG.length;i++)
        {
            if(LG[i].getIDChampionnat()==IDChampionnat)
            {
                A.add(LG[i]);
            }
        }
        Game[] G=new Game[A.size()];
        for(int i=0;i<G.length;i++)
        {
            G[i]=A.get(i);
        }
        return G;
    }

}
