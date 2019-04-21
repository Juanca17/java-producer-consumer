


import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Producer extends Thread {
    int id;
    Buffer buffer;
    DefaultTableModel table;
    int waitTime;
    int lowerBound;
    int upperBound;
    
    Producer(int id, Buffer buffer, DefaultTableModel table, int waitTime, int lowerBound, int upperBound) {
        this.id = id;
        this.buffer = buffer;
        this.table = table;
        this.waitTime = waitTime;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound + 1;
    }
    
    @Override
    public void run() {
        String operands = "+-*/";
        Random r = new Random(System.currentTimeMillis());
        String product;
        
        while (this.buffer.isAvailable()) {
            int operand1 = r.nextInt(this.upperBound-this.lowerBound) + this.lowerBound;
            int operand2 = r.nextInt(this.upperBound-this.lowerBound) + this.lowerBound;
            product = String.format("(%s %d %d)", operands.charAt(r.nextInt(4)), operand1, operand2);
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
