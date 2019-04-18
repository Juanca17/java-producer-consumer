


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Consumer extends Thread {
    int id;
    Buffer buffer;
    DefaultTableModel table;
    int waitTime;
    
    Consumer(int id, Buffer buffer, DefaultTableModel table) {
        this.id = id;
        this.buffer = buffer;
        this.table = table;
        this.waitTime = 1000;
    }
    
    Consumer(int id, Buffer buffer, DefaultTableModel table, int waitTime) {
        this.id = id;
        this.buffer = buffer;
        this.table = table;
        this.waitTime = waitTime;
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        String product;
        
        for(int i=0 ; i<5 ; i++) {
            product = this.buffer.consume();
            this.table.addRow(new Object[]{"Consumer " + this.id, product, calculate(product)});
            Buffer.addOperationsCount();
            // Buffer.print("Consumer " + this.id + " consumed: " + calculate(product));
            
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
