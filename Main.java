import java.util.*;
import javax.swing.*;

public class Main extends JFrame {
	public static double overlay(double v1, double v3, double alpha) {
        double val = 0;
        if (v1 < 128)
            val = 255 * (1 - (1 - v3 / (2 * v1)) / alpha);
        else
            val = 255 * (((v3 - 255) + (1 - alpha) * (v3 - v1) / alpha) / (2 * (255 - v1)) + 1);    
        // val = 255 * ((v3 - v1) / (alpha * (255 - v1)) + 1);      
        return val;
    }
	
	public static void main(String[] args) {
        //JFrame app = new Main();
        //app.show();
        
        ArrayList<Double> arr = new ArrayList<Double>();
        Scanner scan = new Scanner(System.in);
        String type = scan.nextLine();
        double val;
        double r2 = 0;
        double g2 = 0;
        double b2 = 0;
        for (int i = 0; i < 7; i++) {
            val = Double.valueOf(scan.nextLine());
            arr.add(val);
        }
        
        double r1 = arr.get(0);
        double g1 = arr.get(1);
        double b1 = arr.get(2);
        double r3 = arr.get(3);
        double g3 = arr.get(4);
        double b3 = arr.get(5);
        double alpha = arr.get(6);
        
        if (type.equals("n")) {
            r2 = r1 + (r3-r1)/alpha;
            g2 = g1 + (g3-g1)/alpha;
            b2 = b1 + (b3-b1)/alpha;
        }
        else if (type.equals("m")) {
            r2 = 255 * (1 - (1 - r3 / r1) / alpha);
            g2 = 255 * (1 - (1 - g3 / g1) / alpha);
            b2 = 255 * (1 - (1 - b3 / b1) / alpha);
            // v3 = v1 * (255 - (alpha * (255 - v2)))/255;
            // 255 - ((255 - (255 * b3 / b1)) / alpha);
        }
        else if (type.equals("o")) {
            r2 = overlay(r1, r3, alpha);
            g2 = overlay(g1, g3, alpha);
            b2 = overlay(b1, b3, alpha);
        }
        else if (type.equals("s")) {
            if (r3 == 0 || g3 == 0 || b3 == 0)
                System.out.println("Error: Values below 0");
            r2 = (255 * alpha - r1 + r3) / alpha;
            g2 = (255 * alpha - g1 + g3) / alpha;
            b2 = (255 * alpha - b1 + b3) / alpha;
            // v2 = 255 - v1 + v3;
            // (255 - 255 * (1 - alpha) - b1 + b3) / alpha;
        }
        System.out.println(String.format("RGB: %f, %f, %f (%f)", r2, g2, b2, alpha));
    }
}

