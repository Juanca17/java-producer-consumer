


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Consumer extends Thread {
    int id;
    Buffer buffer;
    DefaultTableModel table;
    GUIFrame frame;
    int waitTime;
    
    Consumer(int id, Buffer buffer, DefaultTableModel table, GUIFrame frame, int waitTime) {
        this.id = id;
        this.buffer = buffer;
        this.table = table;
        this.frame = frame;
        this.waitTime = waitTime;
    }
    
    @Override
    public void run() {
        String product;
        
        while (this.buffer.isAvailable()) {
            product = this.buffer.consume();
            this.table.addRow(new Object[]{"Consumer " + this.id, product, calculate(product)});
            this.frame.updateCountLabel();
                        
            try {
                Thread.sleep(this.waitTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private String calculate (String operation) {
        String sResult;
        switch(operation.charAt(1)) {
            case '+':
                sResult = Integer.toString(
                        Character.getNumericValue(operation.charAt(3)) + 
                        Character.getNumericValue(operation.charAt(5)));
                break;
            case '-':
                sResult = Integer.toString(
                        Character.getNumericValue(operation.charAt(3)) - 
                        Character.getNumericValue(operation.charAt(5)));
                break;
            case '*':
                sResult = Integer.toString(
                        Character.getNumericValue(operation.charAt(3)) * 
                        Character.getNumericValue(operation.charAt(5)));
                break;
            case '/':
                int a = Character.getNumericValue(operation.charAt(3));
                int b = Character.getNumericValue(operation.charAt(5));
                if (b == 0) {
                    sResult = "/ by zero";
                } else if (a % b == 0) {
                    sResult = Integer.toString(a / b);
                }else {
                    sResult = String.format("%d/%d",a,b);
                }
                
                break;
            default:
                sResult = " ";
        }
        return sResult;
    }
}
