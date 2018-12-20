/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generadordeparticulas;

import generadordeparticulas.controller.Mouse;
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
    private Mouse mouseController;
    
    public RenderingPanel(){
        this.setSize(WIDTH, HEIGHT);
        paintingBox = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        fillInBlack(paintingBox.getGraphics());
        particleContainerList = new ArrayList<>();
        mouseController = new Mouse();
        this.addMouseListener(mouseController);
    }
    
    private void fillParticleContainerList(){
        if(this.getMousePosition() != null)
            try{
                particleContainerList.add(new ParticleContainer(this.getMousePosition().x, this.getMousePosition().y));
            } catch(Exception e){}
            
    }
    
    private void emptyParticleContainerList(){
        particleContainerList.clear();
    }
    
    public void render(){
        Graphics graphicsFromPanel = getGraphics();
        if(graphicsFromPanel != null){
            Graphics graphicsFromBox = paintingBox.getGraphics();
            if(graphicsFromBox != null){
                if(mouseController.clicked){
                    fillParticleContainerList();
                    //mouseController.clicked = false;
                }
                fillInBlack(graphicsFromBox);
                int i = 0;
                while(i < particleContainerList.size()){
                    //System.out.println(particleContainerList.get(i).particles.size());
                    if(particleContainerList.get(i).particles.isEmpty()){
                        //System.out.println("Removing particles...");
                        particleContainerList.remove(i);
                    }
                    if(i == particleContainerList.size()) break;
                    ArrayList<Particle> particlesToBeRemoved = new ArrayList<>();
                    for (Particle p : particleContainerList.get(i).particles) {
                        particleFluxCalculator(p);
                        graphicsFromBox.setColor(p.color);
                        graphicsFromBox.fillOval(p.xPosition, p.yPosition, p.size, p.size);
                        if(p.outOfBounds) particlesToBeRemoved.add(p);
                    }
                    for(Particle p : particlesToBeRemoved){
                        particleContainerList.get(i).particles.remove(p);
                    }
                    i++;
                }
                graphicsFromBox.dispose();
            }
            graphicsFromPanel.drawImage(paintingBox, 0, 0, null);
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
