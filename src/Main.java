import estimator.Estimator;
import graphics.Canvas;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * University of Rochester
 * Author: Shengqi Suizhu
 * Date: 2016/3/21
 * PACKAGE_NAME/Main
 */

public class Main {
    public static void main(String[] args) {
        boolean Graphic = true;
        if (Graphic) {
            toGraph();
        }
        else {
            toFile();
        }
    }

    public static void toGraph(){
        JFrame frame = new JFrame("MonteCarlo Piestimator");
        frame.setSize(850,900);
        frame.add(new Canvas());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static void toFile() {
        BufferedWriter bf;
        try {
            bf = new BufferedWriter(new FileWriter(new File("data.txt")));
            Estimator est = new Estimator();
            for (int i = 0; i <= 1000000000; i++) {
                est.addPoint();
                if(i%100000==0){
                    double error = est.getError().doubleValue();
                    bf.write(i+"\t"+error);
                    bf.newLine();
                }
            }
            bf.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}