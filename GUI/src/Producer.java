


import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Producer extends Thread {
    int id;
    Buffer buffer;
    DefaultTableModel table;
    int waitTime;
    
    Producer(int id, Buffer buffer, DefaultTableModel table, int waitTime) {
        this.id = id;
        this.buffer = buffer;
        this.table = table;
        this.waitTime = waitTime;
    }
    
    @Override
    public void run() {
        String operands = "+-*/";
        Random r = new Random(System.currentTimeMillis());
        String product;
        
        while (this.buffer.isAvailable()) {
            product = String.format("(%s %d %d)", operands.charAt(r.nextInt(4)), r.nextInt(9), r.nextInt(9));
            this.buffer.produce(product);
            this.table.addRow(new Object[]{"Producer " + this.id, product});
            
            try {
                Thread.sleep(this.waitTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
