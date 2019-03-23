import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;


class Window extends JFrame{


    public Window(){
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Field gameField = new Field();
		    Container container = getContentPane();
		    container.add(gameField);
        setVisible(true);
    }
}

class Field extends JPanel{

    public Sound audio = new Sound();

    public Obstacles[] obstacles = new Obstacles[30];
    public MachineGun[] machineGuns = new MachineGun[2];
    public Player player;
    Image background;


    public class mouseEventsHandler implements MouseListener {

        public void mousePressed(MouseEvent e) {
            player.gun.strikeTimer.start();
        }

        public void mouseReleased(MouseEvent e) {
            player.gun.strikeTimer.stop();
        }

        public void mouseEntered(MouseEvent e) {
            Toolkit toolkit = Toolkit.getDefaultToolkit();  
            Image image = toolkit.getImage("images//cursor.png");  
            Cursor cursor = toolkit.createCustomCursor(image, new Point(0, 0), "");  
            setCursor(cursor);  
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
            //player.gun.Strike();
        }
    }

    public class mouseMotionHandler implements MouseMotionListener{

        public void mouseDragged(MouseEvent e){

          mouseMoved(e);

        }

        public void mouseMoved(MouseEvent e){
            player.mouseX = e.getX();
            player.mouseY = e.getY();
        }

    }

    public class myKeyListener implements KeyListener {
        public void keyPressed(KeyEvent e){
            int key_ = e.getKeyCode();
            if( key_==68 ){
                player.move_right = true;
            }
            if( key_==65 ){
                player.move_left = true;
            }
            if( key_==87 ){
                player.move_top = true;
            }
            if( key_==83 ){
                player.move_down = true;
            }
        }
        public void keyReleased(KeyEvent e) {
            int key_ = e.getKeyCode();
            if( key_==68 ){
                player.move_right = false;
            }
            if( key_==65 ){
                player.move_left = false;
            }
            if( key_==87 ){
                player.move_top = false;
            }
            if( key_==83 ){
                player.move_down = false;
            }
        }
        public void keyTyped(KeyEvent e) {}
    }

    Timer repaint_ = new Timer(1, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
            repaint();
        }
    });

    public Field(){
       // audio.mainTheme();
        createMap();
        player = new Player(obstacles, machineGuns);
        for( MachineGun gun : machineGuns ){
            if( gun!=null ){
                gun.obstacles = obstacles;
                gun.player = player;
                gun.snooping.start();
                gun.checkHits.start();
            }
        }
        try{
          background = ImageIO.read(new File("images//background2.png"));
        }
        catch(IOException ex){}
        repaint_.start();
        addKeyListener(new myKeyListener());
        addMouseListener(new mouseEventsHandler());
        addMouseMotionListener(new mouseMotionHandler());
        setFocusable(true);
    }

    public void createMap(){
        Conventer MapsConventer = new Conventer(obstacles, machineGuns);
    }

    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        gr.drawImage(background, 0, 0, 1920, 1080, null);
        for( MachineGun machineGun : machineGuns ){
            if( machineGun!=null )machineGun.draw(gr);
        }
        for( Obstacles obstacle : obstacles ){
            if( obstacle!=null )obstacle.draw(gr);
        }
        for( MachineGun machineGun : machineGuns ){
            if( machineGun!=null )machineGun.drawPanel(gr);
        }
        if( player.heart!=null ){
            player.heart.draw(gr);
        }
        if( player.ammunition!=null ){
            player.ammunition.draw(gr);
        }
        player.drawPanel(gr);
        player.draw(gr);
        player.timerUpdate.start();

    }
}
