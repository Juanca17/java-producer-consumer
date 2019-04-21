


import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    private final Stack<String> buffer;
    private int capacity;
    private boolean available;
    GUIFrame frame;
    
    Buffer(int capacity, GUIFrame frame) {
        this.buffer = new Stack<>();
        this.capacity = capacity;
        this.available = true;
        this.frame = frame;
    }
    
    synchronized String consume() {
        String product;
        
        if(this.buffer.isEmpty()) {
            try {
                wait(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        product = this.buffer.pop();
        
        try {
                this.frame.updateToDoProgressBar(this.buffer.size());
                wait(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        notify();
        
        return product;
    }
    
    synchronized void produce(String product) {
        if(this.buffer.size() >= this.capacity) {
            try {
                wait(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.buffer.push(product);
        
        try {
                this.frame.updateToDoProgressBar(this.buffer.size());
                wait(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        notify();
    }
    
    public int getBufferSize () {
        return this.buffer.size();
    }
    
    public boolean isAvailable () {
        return this.available;
    }
    
    public void turnOff () {
        this.available = false;
    }
}
