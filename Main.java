package affichage;
import composant.*;
import connect.Connex;

import java.lang.reflect.*;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

public class Main
{
	public static void main(String[] args) throws Exception
	{	
	Connex C=new Connex();
	Connection conn=C.IConnex();
	Statement stmt = conn.createStatement();
    Statement stamt = conn.createStatement();
	ResultSet resultat=stmt.executeQuery("SELECT * FROM player");
    ResultSet result=stamt.executeQuery("SELECT * FROM Game");


    Player[] LP=Player.ListPlayer(resultat);
	/*for(int i=0;i<LP.length;i++){
		System.out.println(LP[i].getIDPlayer() + "  " + LP[i].getnom());
	}*/

    Game[] LG=Game.ListGame(result);
	for(int i=0;i<Core.ListGameParChampionnat(2022,LG).length;i++){
		System.out.println(Core.ListGameParChampionnat(2022,LG)[i].getidWinner());
	}
    
    

	int H=(int)(Core.height-Boule.taille-Boule.taille-15);
	int W=Core.width-Boule.taille-Boule.taille/2;
	Boule[] Ball=new Boule[4];
    Ball[0]=new Boule(100,(H/2));
	Ball[1]=new Boule(400,H/2);
    Ball[2]=new Boule(450,180);
    Ball[3]=new Boule(450,250);
	/*Ball[4]=new Boule(500,160);
    Ball[5]=new Boule(500,230);
	Ball[6]=new Boule(500,300);
	Ball[7]=new Boule(550,130);
    Ball[8]=new Boule(550,200);
	Ball[9]=new Boule(550,270);
	Ball[10]=new Boule(550,340);*/
    

	But[] Hole=new But[8];
    Hole[0]=new But(0,0);
    Hole[1]=new But(W/2,0);
	Hole[2]=new But(W-5,0);
	Hole[3]=new But(W-5,H/2);
	Hole[4]=new But(0,H/2);
	Hole[5]=new But(0,H);
    Hole[6]=new But(W/2,H);
	Hole[7]=new But(W-5,H);
	
	Fenetre match = new Fenetre( Ball , Hole , LP , LG);
	match.setLP(LP);

	
	}	

	 public static Object[] List(ResultSet result,Class<?> obj)throws Exception{             
        Field[] tableauAttribut = obj.getDeclaredFields();
        Vector<Object> resulttab=new Vector<>();
        int taille=0;
        while(result.next()){
            Object temp= obj.getConstructor().newInstance();
            for(int i=0; i<tableauAttribut.length; i++){
                Method setterEmpno = obj.getDeclaredMethod("set"+ tableauAttribut[i].getName(), tableauAttribut[i].getType());
                if(tableauAttribut[i].getType()==Integer.class){
                    setterEmpno.invoke(temp,result.getInt(i+1));
                }
                if(tableauAttribut[i].getType().getSimpleName().equals("String"))setterEmpno.invoke(temp,result.getString(i+1));
                if(tableauAttribut[i].getType().getSimpleName().equals("Date"))setterEmpno.invoke(temp,result.getDate(i+1));
                if(tableauAttribut[i].getType().getSimpleName().equals("int"))setterEmpno.invoke(temp,result.getInt(i+1));
            }
            taille++;
            resulttab.add(temp);
        }
        Object[] tab=new Object[taille];
        for (int i = 0; i < tab.length; i++) {
            tab[i]=resulttab.get(i);
        }
        return tab;
    }
}