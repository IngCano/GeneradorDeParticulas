/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generadordeparticulas;

import java.util.ArrayList;

/**
 *
 * @author jhonedercano@gmail.com
 */
public class ParticleContainer {
    
    public ArrayList<Particle> particles;
    
    public ParticleContainer(){
        particles = new ArrayList<>();
        
        fillParticleContainer();
    }
    
    private void fillParticleContainer(){
        int i = 0;
        byte color = (byte)(Math.random()*128);
        int direccion = 0;
        while(i < 100){
            direccion = ((int)(Math.random()*360));
            particles.add(new Particle(2, color, direccion));
            System.out.println(direccion);
            i++;
        }
    }
    
    private void emptyParticleContainer(){
        particles.clear();
    }
}
