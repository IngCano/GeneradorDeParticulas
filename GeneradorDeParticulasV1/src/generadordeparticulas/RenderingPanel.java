/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generadordeparticulas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author jhonedercano@gmail.com
 */
public class RenderingPanel extends JPanel{
    
    public static final int HEIGHT = RenderingWindow.HEIGHT;
    public static final int WIDTH = RenderingWindow.WIDTH;
    
    private ArrayList<ParticleContainer> particleContainerList;
    private BufferedImage paintingBox;
    
    private int counter;
    private int limit;
    
    public RenderingPanel(){
        this.setSize(WIDTH, HEIGHT);
        paintingBox = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        fillInBlack(paintingBox.getGraphics());
        particleContainerList = new ArrayList<>();
        fillParticleContainerList();
        counter = 0;
        limit = 1;
    }
    
    private void fillParticleContainerList(){
        int i = 0;
        while(i < 10){
            particleContainerList.add(new ParticleContainer());
            i++;
        }
    }
    
    private void emptyParticleContainerList(){
        particleContainerList.clear();
    }
    
    public void render(){
        Graphics graphicsFromPanel = getGraphics();
        if(graphicsFromPanel != null){
            Graphics graphicsFromBox = paintingBox.getGraphics();
            if(graphicsFromBox != null){
                fillInBlack(graphicsFromBox);
                
                int i = 0;
                while(i < limit){
                    particleContainerList.get(i).particles.forEach((p) -> {
                        particleFluxCalculator(p);
                        graphicsFromBox.setColor(p.color);
                        graphicsFromBox.fillRect(p.xPosition, p.yPosition, p.size, p.size);
                    });
                    i++;
                }
                graphicsFromBox.dispose();
            }
            graphicsFromPanel.drawImage(paintingBox, 0, 0, null);
        }
        counter++;
        switch(counter){
            case 50: limit = 2; break;
            case 100: limit = 3; break;
            case 150: limit = 4; break;
            case 200: limit = 5; break;
            case 250: limit = 6; break;
            case 300: limit = 7; break;
            case 350: limit = 8; break;
            case 400: limit = 9; break;
            case 450: limit = 10; break;
            case 950:
                limit = 1;
                counter = 0;
                emptyParticleContainerList();
                fillParticleContainerList();
                break;
            
        }
    }
    
    public void fillInBlack(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }
    
    private void particleFluxCalculator(Particle p){
        
        //System.out.println("xPos = "+p.xPosition+"\nyPos = "+p.yPosition+"\n\n");
        //System.out.println("xMov = "+p.xMovementCounter+"\nyMov = "+p.yMovementCounter+"\n\n");
        
        if((p.xMovementCounter < 1 && p.xMovementCounter > -1) &&
                (p.yMovementCounter > -1 && p.yMovementCounter < 1))
                p.movementCalculated = false;
        
        if(!p.movementCalculated) p.calculateParticleMovement();
        
        if(p.xMovementCounter >= 1){
            p.xMovementCounter--;
            p.xPosition--;
        } else if(p.xMovementCounter <= -1){
            p.xMovementCounter++;
            p.xPosition++;
        }
        if(p.yMovementCounter >= 1){
            p.yMovementCounter--;
            p.yPosition--;
        } else if(p.yMovementCounter <= -1){
            p.yMovementCounter++;
            p.yPosition++;
        }

    }
    
}
