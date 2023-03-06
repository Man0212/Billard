package listener;
import composant.*;
import affichage.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Listner_NewMatch implements MouseListener 
{
    Fenetre C;
    Player[] LP;
    Game[] LG;
    String A,B,D,E,F=new String();

	 public Listner_NewMatch(Fenetre C,Player[] LP, Game[] LG)
	 {
         this.C=C;
         this.LP=LP;
         this.LG=LG;
     }
	 
    public void mouseClicked(MouseEvent e)
	{
        JTextField CH=C.getCH();
        JTextField P1=C.getP1();
        JTextField P2=C.getP2();
        JTextField IDmatch=C.getIDM();
        JTextField IDChampionnat=C.getIDC();
        JButton button=new JButton();
		button=(JButton)e.getSource();

		if (button.getText()=="confirmer")
		{
            A=P1.getText();
            B=P2.getText();
            D=IDmatch.getText();
            E=IDChampionnat.getText();
            
            try{
                
            Player.PlayerExeption(Integer.valueOf(A),Integer.valueOf(B),LP);
            Player.MatchExeption(Integer.valueOf(D),LG);
            Player.ChampionnatExeption(Integer.valueOf(E));
            C.setJ( Player.DetectPlayer(Integer.valueOf(A),LP) , Player.DetectPlayer(Integer.valueOf(B),LP),Integer.valueOf(D),Integer.valueOf(E) );
            C.startGame(); 
            }
            catch(Exception se){
                JFrame J=new JFrame();
                J.setSize(200,100);
                J.setDefaultCloseOperation(J.DISPOSE_ON_CLOSE);
                J.setVisible(true);
                JLabel A=new JLabel(se.getMessage());
                J.add(A);
            }
            
		}

        if(button.getText()=="voir")
        {
            F=CH.getText();
            JFrame J=new JFrame();
            J.setSize(400,100);
            J.setDefaultCloseOperation(J.DISPOSE_ON_CLOSE);
            J.setVisible(true);
            JLabel A=new JLabel("Vainqueur Championnat " + Integer.valueOf(F) + " : " + Core.getWinnerParChampionnat(Integer.valueOf(F),LG,LP).getnom());
            J.add(A);
        }
	}

        public void mouseEntered(MouseEvent e)
        {
            
        }
        public void mouseExited(MouseEvent e){}
        public void mousePressed(MouseEvent e){}
        public void mouseReleased(MouseEvent e){}
    }