/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generadordeparticulas;

import java.awt.Color;
import java.awt.Rectangle;

/**
 *
 * @author jhonedercano@gmail.com
 */
public class Particle {
    
    public int size;
    public Color color;
    public int direction;
    public int xPosition;
    public int yPosition;
    public int bouncingTimes;
    public float xMovementCounter;
    public float yMovementCounter;
    public boolean movementCalculated;
    public boolean outOfBounds;
    
    public Particle(int x, int y){
        size = 1;
        color = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
        direction = (int)(Math.random() * 360);
        xPosition = x;
        yPosition = y;
        xMovementCounter = 0f;
        yMovementCounter = 0f;
        movementCalculated = false;
        outOfBounds = false;
        bouncingTimes = 5;
    }
    
    public Particle(int x, int y, Color color, int direccion){
        size = 1;
        this.color = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
        this.direction = direccion;
        xPosition = x;
        yPosition = y;
        xMovementCounter = 0f;
        yMovementCounter = 0f;
        movementCalculated = false;
        outOfBounds = false;
        bouncingTimes = 5;
    }
    
    public Particle(int x, int y, int size, Color color, int direccion){
        this.size = size + (int)(Math.random()*6);
        this.color = color;
        this.direction = direccion;
        xPosition = x;
        yPosition = y;
        xMovementCounter = 0f;
        yMovementCounter = 0f;
        movementCalculated = false;
        outOfBounds = false;
        bouncingTimes = 5;
    }
    
    public void calculateParticleMovement(Rectangle scene){
        movementCalculated = true;
        if(direction >= 0 && direction < 90){
            int n90 = 89 - direction;
            int n0 = direction;

            if(n90 == 0){
                yMovementCounter++;
            } else {
                if(n0 == 0){
                    xMovementCounter--;
                } else {
                    yMovementCounter += (float)n0/n90;
                    xMovementCounter -= (float)n90/n0;
                }
            }
        }
        
        else if(direction >= 90 && direction < 180){
            int n90 = 179 - direction;
            int n0 = direction - 90;

            if(n90 == 0){
                xMovementCounter++;
            } else {
                if(n0 == 0){
                    yMovementCounter++;
                } else {
                    yMovementCounter += (float)n0/n90;
                    xMovementCounter += (float)n90/n0;
                }
            }
        }
        
        else if(direction >= 180 && direction < 270){
            int n90 = 269 - direction;
            int n0 = direction - 180;

            if(n90 == 0){
                yMovementCounter--;
            } else {
                if(n0 == 0){
                    xMovementCounter++;
                } else {
                    yMovementCounter -= (float)n0/n90;
                    xMovementCounter += (float)n90/n0;
                }
            }
        }
        
        else if(direction >= 270 && direction < 360){
            int n90 = 359 - direction;
            int n0 = direction - 270;
            
            if(n90 == 0){
                xMovementCounter--;
            } else {
                if(n0 == 0){
                    yMovementCounter--;
                } else {
                    yMovementCounter -= (float)n0/n90;
                    xMovementCounter -= (float)n90/n0;
                }
            }
        }
        
        if((xPosition > scene.width || xPosition < scene.x)
                || (yPosition > scene.height || yPosition < scene.y)){
            outOfBounds = true;
        }
    }
    
    public void determineParticleDirectionByProximity(Rectangle scene){
        boolean directionChanged = false;
        if(xPosition >= scene.width || xPosition <= scene.x){
            if(direction >= 0 && direction < 90){
                direction += 90;
                directionChanged = true;
            } else if(direction >= 90 && direction < 180){
                direction -= 90;
                directionChanged = true;
            } else if(direction >= 180 && direction < 270){
                direction += 90;
                directionChanged = true;
            } else if(direction >= 270 && direction < 360){
                direction -= 90;
                directionChanged = true;
            }
            if(xPosition == scene.width - 150 || xPosition == scene.x){
                xMovementCounter = 0;
                yMovementCounter = 0;
            }
        }
        if(yPosition >= scene.height || yPosition <= scene.y){
            if(direction >= 0 && direction < 90){
                direction += 270;
                directionChanged = true;
            } else if(direction >= 90 && direction < 180){
                direction += 90;
                directionChanged = true;
            } else if(direction >= 180 && direction < 270){
                direction -= 90;
                directionChanged = true;
            } else if(direction >= 270 && direction < 360){
                direction -= 270;
                directionChanged = true;
            }
            //System.out.println("direction = " + direction + ", ballObjectHashCode = "+this.hashCode());
            if(yPosition == scene.height || yPosition == scene.y){
                //System.out.println("Movement anulated");
                xMovementCounter = 0;
                yMovementCounter = 0;
            }
        }
        if(directionChanged){
            movementCalculated = false;
            bouncingTimes--;
            color = new Color(color.getRed()/2, color.getGreen()/2, color.getBlue()/2);
        }
    }
    
}
