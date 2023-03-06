package composant;
import affichage.*;

import java.awt.Color;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import connect.Connex;

public class Game{
    int IDChampionnat;
    int idGame;
    int idPlayer1;
    int idPlayer2;
    int idWinner;

    public void setIDChampionnat(int p){this.IDChampionnat=p;}
	public int getIDChampionnat(){return this.IDChampionnat;}

    public void setidGame(int p){this.idGame=p;}
	public int getidGame(){return this.idGame;}

    public void setidPlayer1(int p){this.idPlayer1=p;}
	public int getidPlayer1(){return this.idPlayer1;}

    public void setidPlayer2(int p){this.idPlayer2=p;}
	public int getidPlayer2(){return this.idPlayer2;}

    public void setidWinner(int p){this.idWinner=p;}
	public int getidWinner(){return this.idWinner;}

    public static Game[] ListGame(ResultSet resultat) throws Exception
   {
        Object[] tab=Main.List(resultat,Game.class);
        Game[] E=new Game[tab.length];
        for(int i=0;i<tab.length;i++){
            E[i]=(Game) tab[i];
        }
        return E;
   }
}