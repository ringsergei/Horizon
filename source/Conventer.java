import java.io.*;

class Conventer{

    private char bracket;
    private ObjectStyle[] objStyles = new ObjectStyle[2];
    private ObjectStyle current_objStyle;
    public Obstacles[] obstacles;
    public MachineGun[] machineGuns;

    public Conventer(Obstacles obstacles[], MachineGun machineGuns[]){
        this.obstacles=obstacles;
        this.machineGuns=machineGuns;
        String [] values = {"width", "height", "x", "y"};
        objStyles[0] = new ObjectStyle("Obstacle", values, this);
        String [] values2 = {"x", "y", "damageRadius", "orientation"};
        objStyles[1] = new ObjectStyle("MachineGun", values2, this);
        try{
            FileInputStream fstream = new FileInputStream("map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null){
                for( ObjectStyle objStyle : objStyles ){
                    if( strLine.contains(objStyle.name) ){
                        current_objStyle=objStyle;
                    }
                }
                if( strLine.contains("}") ){
                    bracket = '}';
                    current_objStyle.ObjectNumber+=1;
                }
                if( strLine.contains("{")){
                   bracket = '{';
                   current_objStyle.CreateNewGlobalObject();
                }
                else if( bracket=='{' ){
                    for( String value : current_objStyle.ObjectValues ){
                        if( strLine.contains(value) ){
                            strLine = strLine.replaceAll("\\s", "");
                            current_objStyle.setValue(value, strLine);
                        }
                    }
                }
            }
        }catch (IOException e){
          System.out.println("File or syntax error");
        }
    }
}

class ObjectStyle{
    public String name;
    public String[] ObjectValues;
    public int ObjectNumber=0;
    public Conventer conv;

    public ObjectStyle(String name, String values[], Conventer conv){
        this.name = name;
        this.ObjectValues = values;
        this.conv = conv;
    }

    public void CreateNewGlobalObject(){
        if( name=="Obstacle" ){
            conv.obstacles[ObjectNumber] = new Obstacles();
        }
        else if( name=="MachineGun" ){
            conv.machineGuns[ObjectNumber] = new MachineGun();
        }
    }

    public void setValue(String value, String strLine){
        if( name=="Obstacle" ){
            conv.obstacles[ObjectNumber].setValue(value, strLine.substring(strLine.indexOf(":")+1, strLine.length()));
        }
        else if( name=="MachineGun" ){
            conv.machineGuns[ObjectNumber].setValue(value, strLine.substring(strLine.indexOf(":")+1, strLine.length()));
        }
    }

}