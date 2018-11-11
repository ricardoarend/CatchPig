/* Ambiente do Tabuleiro */

package Main;

import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Image;
import javax.swing.ImageIcon;
 
public class Tabuleiro extends JPanel{     

int numLinhas=5;
int numColunas=5;
int matriz[][]= new int[numLinhas][numColunas];
int tickMaximo=80;
int numTick=0;
int round=3;
int faz1=1;
int faz2=2;
int porco=3;
    
    public void MontarTabuleiro() {
       int x,y;
        // eixo [x][y] 4 blocos
        matriz[1][1]=8;
        matriz[3][1]=8;
        matriz[1][3]=8;
        matriz[3][3]=8; 
        
        //pos inicial dos agentes
        //matriz[1][0]=faz1;
        //matriz[1][4]=faz2;
        //matriz[0][2]=porco;
        ///*
        do{
            x = (int )(Math.random() * 4 + 0);
            y = (int )(Math.random() * 4 + 0);  
        }while(matriz[x][y]!=0);
        matriz[x][y]=faz1;
        do{
            x = (int )(Math.random() * 4 + 0);
            y = (int )(Math.random() * 4 + 0);  
        }while(matriz[x][y]!=0);        
        matriz[x][y]=faz2;
        do{
            x = (int )(Math.random() * 4 + 0);
            y = (int )(Math.random() * 4 + 0);  
        }while(matriz[x][y]!=0);        
        matriz[x][y]=porco;  
        //*/
    }
    public double calculaDistancia(int x, int x2, int y, int y2){
        double dist, p1, p2;    
        p1= Math.pow(x2-x, 2);
        p2= Math.pow(y2-y, 2);
        dist= Math.sqrt(p1+p2);
        return dist;
    }
    public void moveUp(int x,int y,int agentId){
        if(matriz[x][y-1]==0 || matriz[x][y-1]==3 ){
        matriz[x][y-1]=agentId; 
        matriz[x][y]=0;      
        }
    }
    public void moveDown(int x,int y, int agentId){

        if(matriz[x][y+1]==0 || matriz[x][y+1]==3 ){
            matriz[x][y+1]=agentId; 
            matriz[x][y]=0;                
        }     
    }
    public void moveLeft(int x,int y, int agentId){
        
        if(matriz[x-1][y]==0 || matriz[x-1][y]==3 ){
            matriz[x-1][y]=agentId;
            matriz[x][y]=0;        
        }
    }
    public void moveRight(int x,int y, int agentId){
        if(matriz[x+1][y]==0 || matriz[x+1][y]==3 ){
            matriz[x+1][y]=agentId;
            matriz[x][y]=0;         
        }
    }

    public void moveFarmer(int x, int y, int agentId){

    double left=99,right=99,up=99,down=99;
    int x_porco,y_porco;

    x_porco=retornaColuna(porco);
    y_porco=retornaLinha(porco);  

        if(x!=0 && y!=1 && y!=3 ){
            left=calculaDistancia(x-1,x_porco,y,y_porco);        
        }
        if(x!=4 && y!=1 && y!=3){
            right=calculaDistancia(x+1,x_porco,y,y_porco);
        }
        if(x!=1 && x!=3 && y!=0){
            up=calculaDistancia(x,x_porco,y-1,y_porco);
        }
        if(y!=4 && x!=1 && x!=3){
            down=calculaDistancia(x,x_porco,y+1,y_porco);
        }
        if(agentId==faz1){ 
            if(down<=up && down<=right && down<=left){
                if((x==0 && y==2) || ((x==2 && y==2) && x_porco>2)){
                    if(matriz[x][y+1]==faz2||(matriz[x][y+2]==faz2 && matriz[x][y+1]!=porco )){
                        moveRight(x,y,agentId);
                    }
                    else moveDown(x,y,agentId);
                }else{
                    if((x==4 && y==2)||((x==2 && y==2) && x_porco<2)){
                        if((matriz[x][y+1]==faz2 ||( matriz[x][y+2]==faz2 && matriz[x][y+1]!=porco))){                          
                            moveLeft(x,y,agentId);
                        }else moveDown(x,y,agentId);
                    }else moveDown(x,y,agentId);                    
                }
            }
            else{
                if(up<=right && up<=left){
                    if((x==0 && y==2) || ((x==2 && y==2) && x_porco>2)){
                        if(matriz[x][y-1]==faz2||(matriz[x][y-2]==faz2 && matriz[x][y-1]!=porco )){
                            moveRight(x,y,agentId);
                        }
                        else moveUp(x,y,agentId);
                    }else{
                        if((x==4 && y==2)||((x==2 && y==2) && x_porco<2)){
                            if(matriz[x][y-1]==faz2 ||( matriz[x][y-2]==faz2 && matriz[x][y-1]!=porco)){                          
                                moveLeft(x,y,agentId);
                            }else moveUp(x,y,agentId);
                        }else moveUp(x,y,agentId);
                        
                    }    
                }
                else{
                    if(right<=left){
                        if((x==2 && y==0) || ((x==2 && y==2) && y_porco>2)){
                            if(matriz[x+1][y]==faz2||(matriz[x+2][y]==faz2 && matriz[x+1][y]!=porco )){
                                moveDown(x,y,agentId);
                            }
                            else moveRight(x,y,agentId);
                        }else{
                            if((x==2 && y==4)||((x==2 && y==2) && y_porco<2)){
                                if(matriz[x+1][y]==faz2 ||( matriz[x+2][y]==faz2 && matriz[x+1][y]!=porco)){                          
                                    moveUp(x,y,agentId);
                                }else moveRight(x,y,agentId);
                            }else moveRight(x,y,agentId);
                        }    
                    }//left<right
                    else{
                        if((x==2 && y==0) || ((x==2 && y==2) && y_porco>2)){
                            if(matriz[x-1][y]==faz2||(matriz[x-2][y]==faz2 && matriz[x-1][y]!=porco )){
                                moveDown(x,y,agentId);
                            }
                            else moveLeft(x,y,agentId);
                        }else{
                            if((x==2 && y==4)||((x==2 && y==2) && y_porco<2)){
                                if(matriz[x-1][y]==faz2 ||( matriz[x-2][y]==faz2 && matriz[x-1][y]!=porco)){                          
                                    moveUp(x,y,agentId);
                                }else moveLeft(x,y,agentId);
                            }else moveLeft(x,y,agentId);
                        }                       
                    }
                }
            }            
        }        
        if(agentId==faz2){
            if(down<=up && down<=right && down<=left){
                if((x==0 && y==2) || ((x==2 && y==2) && x_porco>2)){
                    if(matriz[x][y+1]==faz1||(matriz[x][y+2]==faz1 && matriz[x][y+1]!=porco )){
                        moveRight(x,y,agentId);
                    }
                    else moveDown(x,y,agentId);
                }else{
                    if((x==4 && y==2)||((x==2 && y==2) && x_porco<2)){
                        if((matriz[x][y+1]==faz1 ||( matriz[x][y+2]==faz1 && matriz[x][y+1]!=porco))){                          
                            moveLeft(x,y,agentId);
                        }else moveDown(x,y,agentId);
                    }else moveDown(x,y,agentId);                    
                }
            }
            else{
                if(up<=right && up<=left){
                    if((x==0 && y==2) || ((x==2 && y==2) && x_porco>2)){
                        if(matriz[x][y-1]==faz1||(matriz[x][y-2]==faz1 && matriz[x][y-1]!=porco )){
                            moveRight(x,y,agentId);
                        }
                        else moveUp(x,y,agentId);
                    }else{
                        if((x==4 && y==2)||((x==2 && y==2) && x_porco<2)){
                            if(matriz[x][y-1]==faz1 ||( matriz[x][y-2]==faz1 && matriz[x][y-1]!=porco)){                          
                                moveLeft(x,y,agentId);
                            }else moveUp(x,y,agentId);
                        }else moveUp(x,y,agentId);
                        
                    }    
                }
                else{
                    if(right<=left){
                        if((x==2 && y==0) || ((x==2 && y==2) && y_porco>2)){
                            if(matriz[x+1][y]==faz1||(matriz[x+2][y]==faz1 && matriz[x+1][y]!=porco )){
                                moveDown(x,y,agentId);
                            }
                            else moveRight(x,y,agentId);
                        }else{
                            if((x==2 && y==4)||((x==2 && y==2) && y_porco<2)){
                                if(matriz[x+1][y]==faz1 ||( matriz[x+2][y]==faz1 && matriz[x+1][y]!=porco)){                          
                                    moveUp(x,y,agentId);
                                }else moveRight(x,y,agentId);
                            }else moveRight(x,y,agentId);
                        }    
                    }//left<right
                    else{
                        if((x==2 && y==0) || ((x==2 && y==2) && y_porco>2)){
                            if(matriz[x-1][y]==faz1||(matriz[x-2][y]==faz1 && matriz[x-1][y]!=porco )){
                                moveDown(x,y,agentId);
                            }
                            else moveLeft(x,y,agentId);
                        }else{
                            if((x==2 && y==4)||((x==2 && y==2) && y_porco<2)){
                                if(matriz[x-1][y]==faz1 ||( matriz[x-2][y]==faz1 && matriz[x-1][y]!=porco)){                          
                                    moveUp(x,y,agentId);
                                }else moveLeft(x,y,agentId);
                            }else moveLeft(x,y,agentId);
                        }                       
                    }
                }
            }  
        }
    }
   public void movePorco(int x, int y, int agentId){

    double left=0,right=0,up=0,down=0,left2=0,right2=0,up2=0,down2=0;
    int x_faz1,y_faz1,x_faz2,y_faz2;
    
    //busca as coordenadas dos fazendeiros
    x_faz1=retornaColuna(faz1);
    y_faz1=retornaLinha(faz1);
    x_faz2=retornaColuna(faz2);
    y_faz2=retornaLinha(faz2);      
                      
        if(x!=0 && y!=1 && y!=3  ){    
            left=calculaDistancia(x-1,x_faz1,y,y_faz1);
            left2=calculaDistancia(x-1,x_faz2,y,y_faz2);
            left=left+left2;
        }
        if(x!=4 && y!=1 && y!=3  ){    
            right=calculaDistancia(x+1,x_faz1,y,y_faz1);
            right2=calculaDistancia(x+1,x_faz2,y,y_faz2);
            right=right+right2;
        }
        if(x!=1 && x!=3 && y!=0 ){
            up=calculaDistancia(x,x_faz1,y-1,y_faz1);
            up2=calculaDistancia(x,x_faz2,y-1,y_faz2);
            up=up+up2;
        }
        if(y!=4 && x!=1 && x!=3 ){
            down=calculaDistancia(x,x_faz1,y+1,y_faz1);
            down2=calculaDistancia(x,x_faz2,y+1,y_faz2); 
            down=down+down2;
        } 
        if(down>=up && down>right && down>left){
            moveDown(x,y,agentId);
        }
        else{
            if(up>right && up>left){
                moveUp(x,y,agentId);
            }
            else{
                if(right>left ){
                    moveRight(x,y,agentId);
                }
                else{
                    if(left>0){
                        moveLeft(x,y,agentId);
                    }
                }                             
            }
        }   
    }
    public boolean checaPorco(){
        for (int lin=0;lin<numLinhas;lin++){
            for (int col=0;col<numColunas;col++){
                if(matriz[col][lin]==3){
                    return true;
                }
            }
        }
    return false;
    }
    public void printTabuleiro() {
        for (int lin=0;lin<numLinhas;lin++){
            for (int col=0;col<numColunas;col++)
                System.out.print(" "+matriz[col][lin]);
            System.out.print("\n");
        }
    }
    public int retornaLinha(int n) {
        for (int lin=0;lin<numLinhas;lin++){
            for (int col=0;col<numColunas;col++)
                if(n==matriz[col][lin]){
                    return(lin);
                }            
        }
        return 0;
    }
    public int retornaColuna(int n) {
        for (int lin=0;lin<numLinhas;lin++){
            for (int col=0;col<numColunas;col++)
                if(n==matriz[col][lin]){
                    return(col);
                }            
        }
        return 0;
    }

    public void paint(Graphics g){

    int x, y;    

    g.clearRect(100, 100, 250,250); 
    g.drawLine(100, 150, 350, 150);
    g.drawLine(100, 200, 350, 200);
    g.drawLine(100, 250, 350, 250);
    g.drawLine(100, 300, 350, 300);

    g.drawLine(150, 100, 150, 350 );
    g.drawLine(250, 100, 250, 350 );
    g.drawLine(200, 100, 200, 350 );
    g.drawLine(300, 100, 300, 350 );

    g.fillRect(250, 150, 50, 50);
    g.fillRect(150, 150, 50, 50);
    g.fillRect(150, 250, 50, 50);
    g.fillRect(250, 250, 50, 50);

    Image pig = new ImageIcon(this.getClass().getResource("/images/porco.png")).getImage();
    Image faz = new ImageIcon(this.getClass().getResource("/images/ze.png")).getImage();
    Image faz2 = new ImageIcon(this.getClass().getResource("/images/ze2.png")).getImage();

        for (int col=0;col<numColunas;col++){
            for (int lin=0;lin<numLinhas;lin++){
                if(matriz[col][lin]==1){
                    x=col*50;
                    y=lin*50;
                    g.drawImage(faz,105+x,102+y,null);                                  
                }
                if(matriz[col][lin]==2){
                    x=col*50;
                    y=lin*50;
                    g.drawImage(faz2,105+x,102+y,null);
                }
                if(matriz[col][lin]==3){
                    x=col*50;
                    y=lin*50;
                    g.drawImage(pig,105+x,107+y,null);                   
                }
            }
        }
    }    
}