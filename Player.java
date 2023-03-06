package composant;
import affichage.*;

import java.awt.Color;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import connect.Connex;
import java.util.Calendar;
import java.util.Date;

public class Player{
    int IDPlayer;
    String nom;
    
    

    public void setIDPlayer(int p){this.IDPlayer=p;}
	public int getIDPlayer(){return this.IDPlayer;}

    public void setnom(String p){this.nom=p;}
	public String getnom(){return this.nom;}


   public Player(int IDPlayer,String nom)
   {
       this.IDPlayer=IDPlayer;
       this.nom=nom;
   }
    
    public Player()
   {

   }

   public static Player DetectPlayer(int IDPlayer,Player[] list)
   {
       Player J=new Player();
       for(int i=0;i<list.length;i++)
       {
           if(list[i].getIDPlayer()==IDPlayer)
           {
               J=list[i];
           }
       }
       return J;
   }

   public static Player[] ListPlayer(ResultSet resultat) throws Exception
   {
        Object[] tab=Main.List(resultat,Player.class);
        Player[] E=new Player[tab.length];
        for(int i=0;i<tab.length;i++){
            E[i]=(Player) tab[i];
        }
        return E;
   }

   public static boolean IfPlayerExist(int ID,Player[] LP)
    {
        boolean A=false;
        for(int i=0;i<LP.length;i++)
        {
            if(ID==LP[i].getIDPlayer()){A=true;}
        }
        return A;
    }

    public static void PlayerExeption(int ID1,int ID2,Player[] LP) throws Exception
    {
        if(IfPlayerExist(ID1,LP) && IfPlayerExist(ID2,LP)){

        }
        else{
            throw new Exception("Tsy miexiste le Joueur");
        }


        if(ID1 == ID2)
        {
            throw new Exception("mitovy le Joueur micontre");
        }
    }

    public static void ChampionnatExeption(int IDChampionnat) throws Exception
    {
        int year=Calendar.getInstance().get(Calendar.YEAR);
        if(IDChampionnat!=year){
            throw new Exception("Championnat "+ year +" zao fa tsy io");
        }
    }


    public static boolean IfIDMatchExist(int ID,Game[] LG)
    {
        boolean A=false;
        for(int i=0;i<LG.length;i++)
        {
            if(ID==LG[i].getidGame()){A=true;}
        }
        return A;
    }

    public static void MatchExeption(int ID,Game[] LG) throws Exception
    {
        if(!IfIDMatchExist(ID,LG)){

        }
        else{
            throw new Exception("Efa Miexiste le IDMatch");
        }
    }


    public static void Ending(int IDChampionnat , int IDGagnant,int IDJoueur1 , int IDJoueur2 ,int IDGame ,int NBTirJ1 , int NBTirJ2 ,int ScoreJ1 , int ScoreJ2) throws Exception
    {
        Connex C=new Connex();
        Connection conn=C.IConnex();
        conn.setAutoCommit(false);
        Statement stmt = conn.createStatement();
        try
        {			 
            String NewMatch="INSERT INTO GAME VALUES("+ IDChampionnat + "," + IDGame + "," + IDJoueur1 +"  , "+ IDJoueur2 + "," + IDGagnant + ")";
            stmt.executeQuery(NewMatch);
            String ShootJ1="INSERT INTO SHOOT VALUES(" + IDGame + " ," + IDJoueur1 +"  , "+ NBTirJ1 + "," + ScoreJ1 + ")";
            stmt.executeQuery(ShootJ1);
            String ShootJ2="INSERT INTO SHOOT VALUES(" + IDGame + " ," + IDJoueur2 +"  , "+ NBTirJ2 + "," + ScoreJ2 + ")";
            stmt.executeQuery(ShootJ2);
            conn.commit();
        }
        catch(Exception e){
            conn.rollback();	
            JFrame J=new JFrame();
            J.setSize(200,100);
            J.setDefaultCloseOperation(J.DISPOSE_ON_CLOSE);
            J.setVisible(true);
            JLabel A=new JLabel(e.getMessage());

            J.add(A);
        }
        finally
        {
            stmt.close();
            conn.close();
        }
    }


}