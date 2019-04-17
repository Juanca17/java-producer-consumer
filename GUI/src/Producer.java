


import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    Buffer buffer;
    int waitTime;
    
    Producer(Buffer buffer) {
        this.buffer = buffer;
        this.waitTime = 1000;
    }
    
    Producer(Buffer buffer, int waitTime) {
        this.buffer = buffer;
        this.waitTime = waitTime;
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
        String products = "AEIOU";
        Random r = new Random(System.currentTimeMillis());
        char product;
        
        for(int i=0 ; i<5 ; i++) {
            product = products.charAt(r.nextInt(5));
            this.buffer.produce(product);
            //System.out.println("Producer produced: " + product);
            Buffer.print("Producer produced: " + product);
            
            try {
                Thread.sleep(this.waitTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
