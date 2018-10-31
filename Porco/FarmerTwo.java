/*
 Agente Fazendeiro2
 */
package Porco;

import static Porco.GenericAgent.t;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FarmerTwo extends GenericAgent{
    @Override
    protected void setup() 
    {
        t=new Tabuleiro();
        t.MontarTabuleiro();
        addBehaviour( new walk( this ) );  
        
    }    
    class walk extends CyclicBehaviour 
    {   
        public walk(Agent b) { 
            super(b); 
        }
        @Override
        public void action(){
            
        int col,lin;                            
        boolean porcoVivo=true;
        
        try {         
            frame.repaint();
            if(t.round==t.faz2){                               
                Thread.sleep(1000);
                col=t.retornaColuna(t.faz2);
                lin=t.retornaLinha(t.faz2);
                t.moveFarmer(col,lin,2);
                t.tickMaximo--;
                t.numTick++;
                t.round=t.porco;
            }
        } 
        catch (InterruptedException ex){
            Logger.getLogger(FarmerTwo.class.getName()).log(Level.SEVERE, null, ex);
        }
        }                      
    }
}
    