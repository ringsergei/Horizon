import java.awt.event.WindowEvent;
public class Main{
      public static View view;
    	public static void main(String[] args) {
          view = new View();
          view.setVisible(true);
         
      }
      public static void CreateGameWindow(String mapName){
            Window window = new Window(mapName);
            view.dispose();
      }
}
