//java -classpath .;"C:\Program Files\Java\jdk1.8.0_181\lib\mysql-connector-java-8.0.16\mysql-connector-java-8.0.16.jar" Main

import java.sql.*;
import java.util.ArrayList;
import java.util.List; 

public class DataBaseConnector{
    Connection connect;

    public DataBaseConnector(){
		try{
			String serverName = "localhost";
			String mybase = "Horizon";
			String url = "jdbc:mysql://"+serverName+"/"+mybase+"?autoReconnect=true&serverTimezone=UTC";
			String username = "mysql";
			String password = "mysql";
            connect = DriverManager.getConnection(url, username, password);

		}catch(Exception exp){System.out.println(exp);}  
    }

    public String[] getAllMaps(){

        ArrayList<String> subjects = new ArrayList<String>();
        String[] subjectArr=null;

        try{
            String query = "SELECT * FROM `Maps`";
            Statement state = connect.createStatement();
            ResultSet result = state.executeQuery(query);

            while( result.next() ){
                subjects.add(result.getString(2));
            }

            subjectArr = new String[subjects.size()];
            subjectArr = subjects.toArray(subjectArr);
            
        }catch(Exception exp){System.out.println(exp);}

        return subjectArr;

    }

    public void getAllMusic(){

    }

    public void getAllImages(){

    }

    public MapModel getMap(String mapName){
        MapModel mapModel = null;
        
        try{
            String query = "SELECT * FROM `Maps` WHERE Name=" + "\"" + mapName + "\"";
            Statement state = connect.createStatement();
            ResultSet result = state.executeQuery(query);

            if( result.next() ){
                mapModel = new MapModel(Integer.parseInt(result.getString("ID")), result.getString("Name"), result.getString("Author"), Integer.parseInt(result.getString("Image_ID")), result.getString("Code"));
            }
        }catch(Exception exp){System.out.println(exp);}

        return mapModel;

    }

    public void getMusic(String musicName){

    }

    public ImageModel getImage(String imageName){
        ImageModel imageModel = null;
        
        try{
            String query = "SELECT * FROM `Images` WHERE Name=" + "\"" + imageName + "\"";
            Statement state = connect.createStatement();
            ResultSet result = state.executeQuery(query);

            if( result.next() ){
                imageModel = new ImageModel(result.getInt("ID"), result.getString("Type"), result.getBlob("Image"), result.getString("Size"), result.getString("Name"));
            }
        }catch(Exception exp){System.out.println(exp);}

        return imageModel;
    }

    public void connectClose(){
        try{
            connect.close();
            System.out.println("Close");
        }catch(Exception exp){System.out.println(exp);}
    }

}

class MapModel{
    public int id;
    public String name;
    public String author;
    public int image_id;
    public String code;

    public MapModel(int id, String name, String author, int image_id, String code){
        this.id = id;
        this.name = name;
        this.author = author;
        this.image_id = image_id;
        this.code = code;
    }
}

class ImageModel{
    public int id;
    public String type;
    public java.sql.Blob image;
    public String size;
    public String name;

    public ImageModel(int id, String type, java.sql.Blob image, String size, String name){
        this.id = id;
        this.type = type;
        this.image = image;
        this.size = size;
        this.name = name;
    }
}