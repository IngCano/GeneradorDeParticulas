/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generadordeparticulas;

import java.awt.Color;

/**
 *
 * @author jhonedercano@gmail.com
 */
public class Particle {
    
    public int size;
    public Color color;
    public int direccion;
    public int xPosition;
    public int yPosition;
    public float xMovementCounter;
    public float yMovementCounter;
    public boolean movementCalculated;
    
    public Particle(){
        size = 1;
        color = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
        direccion = (int)(Math.random() * 360);
        xPosition = 400;
        yPosition = 300;
        xMovementCounter = 0f;
        yMovementCounter = 0f;
        movementCalculated = false;
    }
    
    public Particle(byte color, int direccion){
        size = 1;
        this.color = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
        this.direccion = direccion;
        xPosition = 400;
        yPosition = 300;
        xMovementCounter = 0f;
        yMovementCounter = 0f;
        movementCalculated = false;
    }
    
    public Particle(int size, byte color, int direccion){
        this.size = size;
        this.color = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
        this.direccion = direccion;
        xPosition = 400;
        yPosition = 300;
        xMovementCounter = 0f;
        yMovementCounter = 0f;
        movementCalculated = false;
    }
    
    public void calculateParticleMovement(){
        movementCalculated = true;
        if(direccion >= 0 && direccion < 90){
            int n90 = 90 - direccion;
            int n0 = direccion;

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
        
        else if(direccion >= 90 && direccion < 180){
            int n90 = 180 - direccion;
            int n0 = direccion - 90;

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
        
        else if(direccion >= 180 && direccion < 270){
            int n90 = 270 - direccion;
            int n0 = direccion - 180;

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
        
        else if(direccion >= 270 && direccion < 360){
            int n90 = 360 - direccion;
            int n0 = direccion - 270;
            
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
    }
    
}
