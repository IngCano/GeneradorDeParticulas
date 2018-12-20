
package generadordeparticulas;

import generadordeparticulas.controller.Mouse;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author jhonedercano@gmail.com
 */
public class RenderingWindow extends JFrame implements Runnable{
    //
    public static final int HEIGHT = 600;
    public static final int WIDTH = 800;
    
    private RenderingPanel panel;
    
    private Thread renderingThread;
    
    public RenderingWindow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationByPlatform(true);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setTitle("Generador de Partículas");

        panel = new RenderingPanel();
        this.getContentPane().add(panel);
        
        renderingThread = new Thread(this);
        renderingThread.start();
        
        this.setVisible(true);
    }

    @Override
    public void run() {
        while(true){
            panel.render();
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(RenderingWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
}
