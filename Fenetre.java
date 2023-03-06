package affichage;
import listener.*;
import composant.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color; 
import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.JFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Fenetre extends JFrame
{
    Core Table;
    Boule[] BALL;
    But[] HOLE;
    JTextField P1,P2,IDM,IDC,CH;
    Player Player1,Player2;
    Player[] LP;
    Game[] LG;
    int IDChampionnat;
    int IDMatch;

    public void setIDMatch(int p){this.IDMatch=p;}
	public int getIDMatch(){return this.IDMatch;}
    
    public void setChampionnat(int p){this.IDChampionnat=p;}
	public int getChampionnat(){return this.IDChampionnat;}

    public void setPlayer1(Player p){this.Player1=p;}
	public Player getPlayer1(){return this.Player1;}

    public void setPlayer2(Player p){this.Player2=p;}
	public Player getPlayer2(){return this.Player2;}

    public void setLP(Player[] p){this.LP=p;}
	public Player[] getLP(){return this.LP;}

    public JTextField getP1() {return P1;}

    public JTextField getP2() {return P2;}

    public JTextField getCH() {return CH;}

    public JTextField getIDM() {return IDM;}

    public JTextField getIDC() {return IDC;}
    
    public Fenetre(Boule[] BALL ,But[] HOLE,Player[] LP,Game[] LG)
    {
        this.Table=new Core(HOLE,BALL,LP,LG);  
        this.Table.setPlayer1(this.getPlayer1());
        this.Table.setPlayer2(this.getPlayer2());
        this.Table.setChampionnat(this.getChampionnat());
        this.Table.setIDMatch(this.getIDMatch());
        
        this.setSize(800,100);
        this.setLayout(new GridLayout(1,11));
        this.setVisible(true);

            JLabel IDc=new JLabel("ID CHAMP:");
            JTextField IDcT=new JTextField();
            JLabel ID=new JLabel("ID GAME:");
            JTextField IDT=new JTextField();
            JLabel J1=new JLabel("ID J1:" );
            JTextField J1T=new JTextField();
            JLabel J2=new JLabel("ID J2:" );
            JTextField J2T=new JTextField();
            JButton button=new JButton("confirmer");


            JLabel Ch=new JLabel("VOIR CHAMPION:");
            JTextField ChT=new JTextField();
            JButton btn=new JButton("voir");
            this.P1=J1T;
            this.P2=J2T;
            this.IDM=IDT;
            this.IDC=IDcT;
            this.CH=ChT;
            button.addMouseListener(new Listner_NewMatch(this,LP,LG));
            btn.addMouseListener(new Listner_NewMatch(this,LP,LG));
            J1T.setPreferredSize(new Dimension(150,25));
            J2T.setPreferredSize(new Dimension(150,25));
        this.add(IDc);
        this.add(IDcT);
        this.add(ID);
        this.add(IDT);
        this.add(J1);
        this.add(J1T);
        this.add(J2);
        this.add(J2T);
        this.add(button);

        this.add(Ch);
        this.add(ChT);
        this.add(btn);
    }

    public void startGame() {
        this.Table.addMouseListener(new Trajectoir_Listener(this.Table));
        JFrame window = new JFrame();
            window.setSize(Table.width, Table.height);
            window.add(this.Table);
            window.setLocationRelativeTo(null);
            window.setResizable(false);
            window.setVisible(true);
            this.Table.setPlayer1(this.getPlayer1());
            this.Table.setPlayer2(this.getPlayer2());
            this.Table.setChampionnat(this.getChampionnat());
            this.Table.setIDMatch(this.getIDMatch());
    }

    public void setJ(Player A,Player B,int IDMatch, int IDChampionnat){
        this.Player1=A;
        this.Player2=B;  
        this.IDChampionnat=IDChampionnat;
        this.IDMatch=IDMatch;
    }
}




