package listener;
import composant.*;
import affichage.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Trajectoir_Listener implements MouseListener 
{
   
     Core C;
	 public Trajectoir_Listener(Core C)
	 {
         this.C=C;
     }
	 
    public void mouseClicked(MouseEvent e)
	{
        float x=(float) (1/(Math.sqrt((Math.pow(e.getX(),2)+Math.pow(e.getY(),2)) )));
        float xdist=(float)e.getX()-C.getBALL()[0].getx();
        float ydist=(float)e.getY()-C.getBALL()[0].gety();
        float rad=(float) Math.atan2(ydist,xdist);
        float X=(float)Math.cos(rad);
        float Y=(float)Math.sin(rad);
        this.C.getBALL()[0].setVx(X);
        this.C.getBALL()[0].setVy(Y);
        C.setmove(true);

        C.settour(C.gettour()+1);
      

            if(C.gettour()%2==0)
            {
                C.setTJ1(C.getTJ1()+1);
            }else{
                C.setTJ2(C.getTJ2()+1);
            }
	}

        public void mouseEntered(MouseEvent e)
        {
            
        }
        public void mouseExited(MouseEvent e){}
        public void mousePressed(MouseEvent e){}
        public void mouseReleased(MouseEvent e){}
    }