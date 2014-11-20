/*
 * PantryTableModel.java
 *
 * Created on June 21, 2006, 8:26 PM
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
public class PantryTableModel extends AbstractTableModel implements javax.swing.event.TreeSelectionListener
{
    FoodModel fm;
    Object[][] data;
    String canneryId="";
    String grp_cd="";
    
    /** Creates a new instance of PantryTableModel */
    public PantryTableModel(FoodModel fm)
    {
	this.fm = fm;
	reloadData();
	
	
    }
    
    
    
    public int getRowCount()
    {
	if(data == null) return 0;
	return data.length;
    }
    
    public int getColumnCount()
    {
	return Constants.COL_NAMES.length;
    }
    
    public Object getValueAt(int row, int column)
    {
	if(data == null) return "";
	if(row >= data[0].length && column >= data.length) return "";
	return data[row][column];
    }
    
    public String getColumnName(int column)
    {
	if(column<Constants.COL_NAMES.length)
	{
	    //System.out.println(Constants.COL_NAMES[column]);
	    return Constants.COL_NAMES[column];
	}
	else return "";
    }
    
    public Class getColumnClass(int c) {
        if(data!=null)
	{
	    if(getValueAt(0,c)==null) return "".getClass();
	    //System.out.println(getValueAt(0, c).getClass());
	    return getValueAt(0, c).getClass();
	}
	return "".getClass();
    }
    
        
    public void reloadData()
    {
	String[][] dataStr = fm.getFoodTable(canneryId,grp_cd);
	if(dataStr == null)
	{
	    data = null;
	}
	else
	{
	    Object[][] dataObj = new Object[dataStr.length][dataStr[0].length];
	   
	    for(int i = 0; i < dataStr.length; i++)
	    {
		for(int k = 0; k < dataStr[0].length; k++)
		{
		    dataObj[i][k] = dataStr[i][k];
		    switch(k)
		    {
			case 0: dataObj[i][k] = new Integer(dataStr[i][k]);
			case 7: dataObj[i][k] = new Integer(dataStr[i][k]);
			//case 9: dataObj[i][k] = new Double(dataStr[i][k]);
				
		    }
		}
	    }
	    data = dataObj;
	}
	this.fireTableDataChanged();
    }
    
    public void valueChanged(javax.swing.event.TreeSelectionEvent e) 
    {
	javax.swing.JTree tree = (javax.swing.JTree)e.getSource();//javax.swing.tree.TreePath
	javax.swing.tree.TreePath[] paths= tree.getSelectionPaths();
	canneryId = "";
	grp_cd = "";
	//figure out the Ids in the table
	if(paths != null) //nothing selected, so act like "all" is selected
	{
	    //create query
	    
	    for(int i = 0; i < paths.length; i++)
	    {
		if(paths[i].getPathCount()==1)
		{
		    //same as all selected
		    canneryId="";
		    grp_cd="";
		    break;
		}
		else if(paths[i].getPathCount()==2)
		{
		    //cannery cd, need to do complex query to find grp_cds
		    javax.swing.tree.DefaultMutableTreeNode node = (javax.swing.tree.DefaultMutableTreeNode)(paths[i].getLastPathComponent());
		    String id = String.valueOf(((FoodTreeNode)node.getUserObject()).getId());
		    if(id.length()>0)
		    {
			canneryId = (canneryId.length()>0)?canneryId+","+id:id;
		    }
		}
		else//must be more than 1
		{
		    //assume was groupcd, so add it to the query
		    javax.swing.tree.DefaultMutableTreeNode node = (javax.swing.tree.DefaultMutableTreeNode)(paths[i].getLastPathComponent());
		    String id = String.valueOf(((FoodTreeNode)node.getUserObject()).getId());
		    if(id.length()>0)
		    {
			grp_cd = (grp_cd.length()>0)?grp_cd+","+id:id;
		    }
		}
		
	    }
	}
	//do the query and reset the model
	reloadData();
    }
    
    
}
