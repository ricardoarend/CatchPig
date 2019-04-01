/*
 Agente Fazendeiro3
 */
package Main;

import static Main.GenericAgent.t;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FarmerThree extends GenericAgent{
    @Override
    protected void setup() 
    {
        t=new Tabuleiro();
        t.posInicial();
        
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
            if(t.round==t.faz3){                               
                Thread.sleep(1000);
                col=t.retornaColuna(t.faz3);
                lin=t.retornaLinha(t.faz3);
                t.moveFarmer(col,lin,t.faz3);
                t.round=t.pig;
            }
        } 
        catch (InterruptedException ex){
            Logger.getLogger(FarmerTwo.class.getName()).log(Level.SEVERE, null, ex);
        }
        }                      
    }
}
    