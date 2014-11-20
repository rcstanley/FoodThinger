/*
 * DatabaseTableModel.java
 *
 * Created on September 7, 2006, 3:33 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package foodthinger;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author RC Stanley
 */
public class DatabaseTableModel  extends AbstractTableModel
{
    String[][] data;
    FoodModel fm;
    String query;
    String tableName;
    int [] columnsWanted;
    java.text.SimpleDateFormat format;
    /**
     * Creates a new instance of DatabaseTableModel 
     * a model for a table backed by a database
     * Warning: some hard coded stuff(dates) since only one class uses this so far
     */
    public DatabaseTableModel(FoodModel fm, String refreshQuery, String tableName,int[] columnsWanted)
    {
	this.fm = fm;
	query = refreshQuery;
	this.tableName = tableName;
	this.columnsWanted = columnsWanted;
	format = new java.text.SimpleDateFormat("MM/dd/yyyy");
	//notify model when db changes
	fm.addModelListener(new String[]{tableName}, this);
	this.reloadData();
    }
    
     public int getRowCount()
    {
	if(data == null) return 0;
	return data.length;
    }
    
    public int getColumnCount()
    {
	if(data == null) return 4;
	return data[0].length;
    }
    
    public Object getValueAt(int row, int column)
    {
	if(data == null) return "";
	if(row >= data[0].length && column >= data.length) return "";
	return data[row][column];
    }
    
    /*
     * reload the data from the db 
     */
    public void reloadData()
    {
	String [][] values = fm.getStringsFromQuery(query,null,columnsWanted);
	
	if(values == null || values.length == 0)
	{
	    data = null;
	    this.fireTableDataChanged();
	    return;
	}
	String date;
	String[][] dataNew = new String[values.length][values[0].length];
	for(int i = 0; i < values.length;i++)
	{
	    for(int j = 0; j < values[0].length; j++)
	    {
		dataNew[i][j]=values[i][j];
		
	    }
	    //magic #, the date the 3rd one
	    date = format.format(new java.util.Date(Long.parseLong(values[i][3])));
	    dataNew[i][3] = date;
	}
	data = dataNew;
	this.fireTableDataChanged();
	 
    }
    
}
