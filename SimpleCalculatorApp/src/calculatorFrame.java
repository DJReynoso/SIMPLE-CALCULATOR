import javax.swing.*;
import java.awt.*;

public class calculatorFrame extends JFrame {
    public calculatorFrame() {
        content();
    }
    public void content() {
        this.setTitle("Simple Calculator By Daniel Jess Reynoso");
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        fieldAndButtonPanel fieldAndButtonPanel = new fieldAndButtonPanel();
        container.add(fieldAndButtonPanel);

        this.setVisible(true);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new calculatorFrame();
    }
}
