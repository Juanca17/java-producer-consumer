


import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {
    Buffer buffer;
    int waitTime;
    
    Consumer(Buffer buffer) {
        this.buffer = buffer;
        this.waitTime = 1000;
    }
    
    Consumer(Buffer buffer, int waitTime) {
        this.buffer = buffer;
        this.waitTime = waitTime;
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        char product;
        
        for(int i=0 ; i<5 ; i++) {
            product = this.buffer.consume();
            //System.out.println("Consumer consumed: " + product);
            Buffer.print("Consumer consumed: " + product);
            
            try {
                Thread.sleep(this.waitTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
