/*
 * FoodPlanSubCatFoodsTableModel.java
 *
 * Created on September 19, 2006, 2:59 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package foodthinger;
import javax.swing.table.*;

/**
 *
 * @author RC Stanley
 */
public class FoodPlanSubCatFoodsTableModel extends AbstractTableModel implements javax.swing.event.ListSelectionListener
{
    String [][] values;
    FoodModel fm;
    javax.swing.JTable table ;
    /** Creates a new instance of FoodPlanSubCatFoodsTableModel */
    public FoodPlanSubCatFoodsTableModel(FoodModel fm,  javax.swing.JTable table)
    {
	this.fm = fm;
	this.table = table;
    }
    
    public int getColumnCount()
    {
	if(values==null)return 0;
	return values[0].length;
    }
    
    public Object getValueAt(int row, int col)
    {
	return values[row][col];
    }
    
    public int getRowCount()
    {
	if(values==null)return 0;
	return values.length;
    }
    
    public void valueChanged(javax.swing.event.ListSelectionEvent e)
    {
	//javax.swing.JTable table = ((javax.swing.JTable)e.getSource());
	int row = table.getSelectedRow();
	TableSorter sorter = (TableSorter)table.getModel();
	int realRow = 0;
	try
	{
	    realRow = sorter.modelIndex(row);
	}
	catch(Exception a)
	{
	    System.out.println(a);
	    return;
	}
		 
	FoodPlanTableModel model = (FoodPlanTableModel)sorter.getTableModel();
	Object val = model.getData().get(realRow);
	if(val instanceof NewFoodPlanItem)
	{
	    String ids = ((NewFoodPlanItem)val).idsAsString();
	    values = fm.getStringsFromQuery(Constants.FOOD_DES_DESC_ONLY_QUERY,new String[]{ids},new int[]{0});
	}
	else if(val instanceof FoodPlanItem)
	{
	    String id = String.valueOf(((FoodPlanItem)val).subCategory);
	    values = fm.getStringsFromQuery(Constants.FOOD_DESC_FOR_SUB_CAT_QUERY, new String[]{id},new int[]{0}); 
	}
	this.fireTableStructureChanged();
	this.fireTableDataChanged();
    }
    
}
