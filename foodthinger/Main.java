/*
 * Main.java
 *
 * Created on June 11, 2006, 12:54 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package foodthinger;
import java.io.FileInputStream;
import java.io.File;
import java.util.Properties;

/**
 *
 * @author Rachel
 */
public class Main {
    private FoodModel fm = null;
    private Properties props = null;
    /** Creates a new instance of Main */
    public Main() {
	//load the config files
	loadProperties(); 
        //open and verify the db
	try
	{
	    //db = new FoodDatabase(props.getProperty(Constants.DB_JNI_LOC),props.getProperty("user.dir")+"/"+props.getProperty(Constants.PROP_DB_NAME));
	    fm = new FoodModel(props.getProperty("user.dir")+"/"+props.getProperty(Constants.DB_JNI_LOC),props.getProperty("user.dir")+"/"+props.getProperty(Constants.PROP_DB_NAME), props);
	}
	catch(Exception e)
	{
	   System.out.println( e.toString());
	   e.printStackTrace(System.out);
	   
	}

    }
    
    public FoodModel getFoodModel()
    {
	return fm;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
       //load the properties 
	Main main = new Main();
       	//hack to launch the gui
	String[] fred=new String[1]; 
        //FoodThingerMainWindow.main(fred);
	final FoodModel temp = main.getFoodModel();
	java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
		javax.swing.JFrame.setDefaultLookAndFeelDecorated(true);
                new FoodThingerMainWindow(temp).setVisible(true);
            }
        });
	
      
    }
    
    
    
    /**
     * Load the System properties and the two default configuration files
     */
    protected void loadProperties()
    {
	try
	{
	    //load the system props
	    props = new Properties(System.getProperties());
	    //load the default config file
	    File f = new File(Constants.PROP_DIR+"/"+Constants.DEFALT_PROP_FILE);
	    if(f.exists())
	    {
		FileInputStream propFile = new FileInputStream(f);
		props.load(propFile);
		propFile.close();
	    }
	    //load the user config file
	   File f2 = new File(Constants.PROP_DIR+"/"+Constants.APP_PROP_FILE);
	   props.setProperty(Constants.PROP_APP_FILE, Constants.PROP_DIR+"/"+Constants.APP_PROP_FILE);
	    // create and load default properties
	   if(f2.exists())
	   {
	    FileInputStream in = new FileInputStream(f2);
	    props.load(in);
	    in.close();
	   }
	    //props.list(System.out);
	}
	catch(Exception e)
	{
	    System.out.println(e);
	}
    }
     
    
}
