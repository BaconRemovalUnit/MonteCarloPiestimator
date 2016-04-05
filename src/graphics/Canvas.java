package graphics;
import estimator.Estimator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * University of Rochester
 * Author: Shengqi Suizhu
 * Date: 2016/3/21
 * graphics/Canvas
 */

public class Canvas extends JPanel implements ActionListener, MouseListener, MouseWheelListener {
    private final int radius = 400;
    private final int DELAY = 30;
    private int NUM_DOTS = 150;
    private final int x_shift = 20;
    private final int y_shift = 20;
    private final Estimator estimator= new Estimator(radius);
    private Timer timer;
    BufferedImage image;

    public Canvas(){
        setBackground(Color.black);
        timer = new Timer(DELAY,this);
        addMouseListener(this);
        addMouseWheelListener(this);
        timer.start();
        image = new BufferedImage(radius*2+10,radius*2+10,BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.white);
        g.drawString("Number of dots: "+estimator.getTotal(),x_shift+500,y_shift+40+radius*2);
        g.drawString("Pi: "+estimator.getPi(),x_shift,y_shift+40+radius*2);
        g.drawString("Author: ShengqiSuizhu", x_shift+600,y_shift-5);
        g.drawString("Error percentage:"+estimator.getError().doubleValue()*100.0,x_shift,y_shift+20+radius*2);
        g.drawString("Dots per second: "+(int)(NUM_DOTS*1000.0/DELAY),x_shift+500,y_shift+20+radius*2);
        g.drawImage(image,x_shift,y_shift,null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < NUM_DOTS; i++) {
            BooleanPoint temp = estimator.addPoint();
            image.setRGB(temp.x,temp.y,temp.isHit()?-16711936:-65536);
        }

        timer.setDelay(DELAY);
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if (timer.isRunning()) {
            timer.stop();
            estimator.printResults();
        }
        else {
            timer.start();
            System.out.println("-----------------------");
            System.out.println("Program resumed!");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.getWheelRotation()<0){
            NUM_DOTS+=50;
        }else{
            NUM_DOTS-=50;
            if(NUM_DOTS<0)
                NUM_DOTS = 0;
        }
    }
}
