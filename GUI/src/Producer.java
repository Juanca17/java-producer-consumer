


import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Producer extends Thread {
    int id;
    Buffer buffer;
    int waitTime;
    DefaultTableModel table;
    
    Producer(int id, Buffer buffer, DefaultTableModel table) {
        this.id = id;
        this.buffer = buffer;
        this.table = table;
        this.waitTime = 1000;
    }
    
    Producer(int id, Buffer buffer, DefaultTableModel table, int waitTime) {
        this.id = id;
        this.buffer = buffer;
        this.table = table;
        this.waitTime = waitTime;
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
        String operands = "+-*/";
        Random r = new Random(System.currentTimeMillis());
        String product;
        
        for(int i=0 ; i<5 ; i++) {
            product = String.format("(%s %d %d)", operands.charAt(r.nextInt(4)), r.nextInt(9), r.nextInt(9));
            this.buffer.produce(product);
            this.table.addRow(new Object[]{"Producer " + this.id, product});
            Buffer.addOperationsCount();
            // Buffer.print("Producer " + this.id + " produced: " + product);
            
            try {
                Thread.sleep(this.waitTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
