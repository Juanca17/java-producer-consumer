


import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    private String buffer;
    GUIFrame frame;
    
    Buffer(GUIFrame frame) {
        this.buffer = "";
        this.frame = frame;
    }
    
    synchronized String consume() {
        String product = "";
        
        if("".equals(this.buffer)) {
            try {
                wait(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        product = this.buffer;
        this.buffer = "";
        notify();
        
        return product;
    }
    
    synchronized void produce(String product) {
        if(!"".equals(this.buffer)) {
            try {
                wait(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.buffer = product;
        
        notify();
    }
    
    static int count = 1;
    synchronized static void addOperationsCount() {
        System.out.println(count++);
    }    
}
