/*
 Agente Porco
 */
package Porco;

import static Porco.GenericAgent.t;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.JOptionPane.showMessageDialog;


public class PigAgent extends GenericAgent{
    @Override
    protected void setup() 
    {
        t=new Tabuleiro();
        t.MontarTabuleiro();
        
        addBehaviour( new walk( this ) ); 
        
        frame.setSize(500,500);
        frame.getContentPane().add(t);
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);        
        frame.repaint();
    }   
    class walk extends CyclicBehaviour 
    {   
        public walk(Agent c) { 
            super(c); 
        }            
        @Override
        public void action() 
        { 
        int col,lin;       
        boolean porcoVivo;
        
        porcoVivo=t.checaPorco();
        
        if(porcoVivo==false){ 
            showMessageDialog(null, "Porco Capturado "+t.numTick); 
            doDelete();
            takeDown();
        }
        if(t.tickMaximo==0){
            showMessageDialog(null, "Numero máximo de rodadas efetuadas"); 
            doDelete();
            takeDown();            
        }
        try {
            frame.repaint();  
            if(t.round==3){ 
                Thread.sleep(1000);                             
                col=t.retornaColuna(t.porco);
                lin=t.retornaLinha(t.porco);                
                t.movePorco(col,lin,t.porco);
                t.tickMaximo--;
                t.numTick++;
                t.round=t.faz1;
            }
        } 
        catch(InterruptedException ex) {
            Logger.getLogger(PigAgent.class.getName()).log(Level.SEVERE, null, ex);
        } 
        }        
    }
}
    

