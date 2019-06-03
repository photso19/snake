import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gameplay extends JPanel implements KeyListener,ActionListener{
    private int[] snakeXpos = new int[750];
    private int[] snakeYpos = new int[750];
    private boolean left= false;
    private boolean right= false;
    private boolean up= false;
    private boolean down= false;


    private int numDots = 3;
    int keystrokes = 0;
    private int points;

    private int[] newdotXpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int[] newdotYpos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
    private ImageIcon availableDots;
    private Random rand = new Random();
    private int xpos = rand.nextInt(34);
    private int ypos = rand.nextInt(23);


    private Timer timing;
    private int stop =150;

    private ImageIcon snakeimage;

    private ImageIcon titleImage;

    public Gameplay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timing = new Timer(stop,this);
        timing.start();

    }
    public void paint(Graphics p){
        if (keystrokes == 0) {
            snakeXpos[2] =50;
            snakeXpos[1] =75;
            snakeXpos[0] = 100;
            snakeYpos[2] =100;
            snakeYpos[1] =100;
            snakeYpos[0] = 100;
        }

        //draw title image border
        p.setColor(Color.BLACK);
        p.drawRect(24,10,851,55);

       
        //draw border for palying area
        p.setColor(Color.white);
        p.drawRect(24,74,851,577);

        p.setColor(Color.MAGENTA);
        p.fillRect(24,75,850,575);

        //draw points
        p.setColor(Color.BLACK);
        p.setFont(new Font("arial",Font.PLAIN, 36));
        p.drawString("Snakey Game",330,50);

        // draw length of snake
        p.setColor(Color.BLUE);
        p.setFont(new Font("arial",Font.PLAIN, 30));
        p.drawString("Length: " +numDots,700,50);

       
        int a = 0;
        while (a<numDots){
         
            if (a!=0){
                snakeimage = new ImageIcon("snakeimage.png");
                snakeimage.paintIcon(this,p,snakeXpos[a],snakeYpos[a]);
            }
            a++;
        }
        availableDots = new ImageIcon("enemy.png");
        if (newdotXpos[xpos] == snakeXpos[0] && newdotYpos[ypos] == snakeYpos[0]){
            points++;
            numDots++;
            xpos = rand.nextInt(34);
            ypos = rand.nextInt(23);

        }
        availableDots.paintIcon(this,p,newdotXpos[xpos],newdotYpos[ypos]);

       

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timing.start();
        if (right){
            for (int r = numDots-1;r>=0;r--){
                    snakeYpos[r+1] = snakeYpos[r];
            }
            for (int r = numDots;r>=0;r--){
                if (r ==0) {
                    snakeXpos[r] = snakeXpos[r] + 25;
                }else{
                    snakeXpos[r] = snakeXpos[r-1];
                }
                if (snakeXpos[r] >850){
                    snakeXpos[r] =25;
                }
            }
            repaint();

        }
        if (left){
            for (int r = numDots-1;r>=0;r--){
                snakeYpos[r+1] = snakeYpos[r];
            }
            for (int r = numDots;r>=0;r--){
                if (r ==0) {
                    snakeXpos[r] = snakeXpos[r] - 25;
                }else{
                    snakeXpos[r] = snakeXpos[r-1];
                }
                if (snakeXpos[r] <25){
                    snakeXpos[r] =850;
                }
            }
            repaint();

        }
        if (up){
            for (int r = numDots-1;r>=0;r--){
                snakeXpos[r+1] = snakeXpos[r];
            }
            for (int r = numDots;r>=0;r--){
                if (r ==0) {
                    snakeYpos[r] = snakeYpos[r] - 25;
                }else{
                    snakeYpos[r] = snakeYpos[r-1];
                }
                if (snakeYpos[r] <75){
                    snakeYpos[r] =625;
                }
            }
            repaint();

        }
        if (down){
            for (int r = numDots-1;r>=0;r--){
                snakeXpos[r+1] = snakeXpos[r];
            }
            for (int r = numDots;r>=0;r--){
                if (r ==0) {
                    snakeYpos[r] = snakeYpos[r] + 25;
                }else{
                    snakeYpos[r] = snakeYpos[r-1];
                }
                if (snakeYpos[r] >625){
                    snakeYpos[r] =75;
                }
            }
            repaint();

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            keystrokes =0;
            numDots=3;
            points=0;
        }
        if (e.getKeyCode() == KeyEvent.VK_D){
            keystrokes++;
            right = true;
            if (!left){
                right = true;
            }else{
                right = false;
                left= true;
            }

            up= false;
            down= false;

        }if (e.getKeyCode() == KeyEvent.VK_A){
            keystrokes++;
            left= true;
            if (!right){
                left = true;
            }else{
                left = false;
                right = true;
            }

            up= false;
            down= false;

        }if (e.getKeyCode() == KeyEvent.VK_W){
            keystrokes++;
            up = true;
            if (!down){
                up = true;
            }else{
                up = false;
                down= true;
            }

            left= false;
            right= false;

        }if (e.getKeyCode() == KeyEvent.VK_S){
            keystrokes++;
            down = true;
            if (!up){
                down = true;
            }else{
                down = false;
                up= true;
            }

            left= false;
            right= false;

        }

    }
}