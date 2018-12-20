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
    
    public ParticleContainer(int x, int y){
        particles = new ArrayList<>();
        fillParticleContainer(x, y);
    }
    
    private void fillParticleContainer(int x, int y){
        int i = 0;
        byte color = (byte)(Math.random()*128);
        int direccion = 0;
        while(i < 5){
            direccion = ((int)(Math.random()*360));
            particles.add(new Particle(x, y, 2, color, direccion));
            //System.out.println(direccion);
            i++;
        }
    }
}
