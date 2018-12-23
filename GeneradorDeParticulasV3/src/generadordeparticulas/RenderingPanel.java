/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generadordeparticulas;

import generadordeparticulas.controller.Mouse;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
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
    private Rectangle scene;
    
    public RenderingPanel(){
        this.setSize(WIDTH, HEIGHT);
        paintingBox = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        fillInBlack(paintingBox.getGraphics());
        particleContainerList = new ArrayList<>();
        mouseController = new Mouse();
        this.addMouseListener(mouseController);
        scene = new Rectangle(WIDTH-150, HEIGHT-150);
        scene.x = 150;
        scene.y = 150;
    }
    
    private void fillParticleContainerList(){
        if(this.getMousePosition() != null)
            try{
                particleContainerList.add(new ParticleContainer(this.getMousePosition().x, this.getMousePosition().y));
            } catch(Exception e){}
            
    }
    
    public void render(){
        Graphics graphicsFromPanel = getGraphics();
        if(graphicsFromPanel != null){
            Graphics graphicsFromBox = paintingBox.getGraphics();
            if(graphicsFromBox != null){
                if(mouseController.clicked){
                    fillParticleContainerList(); //mouseController.clicked = false;
                }
                fillInBlack(graphicsFromBox);
                paintScene(graphicsFromBox, Color.green);
                int i = 0;
                while(i < particleContainerList.size()){
                    if(particleContainerList.get(i).particles.isEmpty()){
                        particleContainerList.remove(i);
                    }
                    if(i == particleContainerList.size()) break;
                    ArrayList<Particle> particlesToBeRemoved = new ArrayList<>();
                    for (Particle p : particleContainerList.get(i).particles) {
                        particleFluxCalculator(p);
                        graphicsFromBox.setColor(p.color);
                        graphicsFromBox.fillOval(p.xPosition, p.yPosition, p.size, p.size);
                        if(p.outOfBounds) particlesToBeRemoved.add(p);
                        if(p.bouncingTimes == 0) particlesToBeRemoved.add(p);
                    }
                    for(Particle p : particlesToBeRemoved){
                        System.out.println("Particle eliminated at position x = "+p.xPosition+", y = "+p.yPosition+", direction = "+p.direction);
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
    
    public void paintScene(Graphics g, Color c){
        g.setColor(c);
        g.drawRect(scene.x, scene.y, scene.width-150, scene.height-150);
    }
    
    private void particleFluxCalculator(Particle p){
        
        p.determineParticleDirectionByProximity(scene);

        if((p.xMovementCounter < 1 && p.xMovementCounter > -1) &&
                (p.yMovementCounter > -1 && p.yMovementCounter < 1))
                p.movementCalculated = false;
        
        if(!p.movementCalculated) p.calculateParticleMovement(scene);
        
        if(p.xPosition > scene.x && p.xPosition < scene.width){
            if(p.xMovementCounter >= 1){
                p.xMovementCounter--;
                p.xPosition--;
            } else if(p.xMovementCounter <= -1){
                p.xMovementCounter++;
                p.xPosition++;
            }
        } else if(p.xPosition == scene.x){
            p.xPosition++;
        } else if(p.xPosition == scene.width){
            p.xPosition--;
        } else p.outOfBounds = true;
            
        if(p.yPosition > scene.y && p.yPosition < scene.height){
            if(p.yMovementCounter >= 1){
                p.yMovementCounter--;
                p.yPosition--;
            } else if(p.yMovementCounter <= -1){
                p.yMovementCounter++;
                p.yPosition++;
            }
        } else if(p.yPosition == scene.y){
            p.yPosition++;
        } else if(p.yPosition == scene.height){
            p.yPosition--;
        } else p.outOfBounds = true;
    }
    
}
