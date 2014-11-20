/*
 * FoodPlanTableModel.java
 *
 * Created on September 13, 2006, 2:21 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package foodthinger;
import javax.swing.table.AbstractTableModel;
import java.util.HashMap;

/**
 * Table model for the food
 * @author RC Stanley
 */
public class FoodPlanTableModel  extends AbstractTableModel
{
    java.util.Vector foods;
    java.util.Vector newFoods;
    FoodModel fm;
    boolean newPlan; 
    boolean keepUserValues; 
    boolean updateUserValues;
    int numMonths;
    
    
    /** Creates a new instance of FoodPlanTableModel */
    public FoodPlanTableModel(FoodModel fm)
    {
	foods = new java.util.Vector(40, 5);  //about 40 things in the default food storage plan
	newFoods = new java.util.Vector(40, 5);
	this.fm = fm;
	//getDefaultValues();
	//addItem(1, "Wheat",200,1,1,2,1,3,1);
	newPlan = true;
	keepUserValues = false;
	updateUserValues = false;
	numMonths = 0;
    }
    
    void getDefaultValues()
    {
	
    }
    
    public FoodPlanItem addItem(int sub_cat, String foodName, int cannery_cat, float recommendedAmount,
	    int AmountUnits, float dbAmount, int dbAmountUnits,
	    float userAmount,int userAmountUnits)
    {
	FoodPlanItem fpi = new FoodPlanItem( sub_cat, foodName, cannery_cat, /* fd_group_id,*/  recommendedAmount,
	     AmountUnits,  dbAmount,  dbAmountUnits,
	     userAmount, userAmountUnits);
	foods.add(fpi);
	return fpi;
    }
    
    /*public void addItem(int sub_cat, String foodName, int cannery_cat
	    float userAmount,int userAmountUnits)
    {
	FoodPlanItem fpi = new FoodPlanItem( sub_cat, foodName, cannery_cat);
	fpi.setUserAmount(userAmount);
	fpi.setUserAmountUnits(userAmountUnits);
	foods.add(fpi);
	this.fireTableDataChanged();
    }*/
    
    public Object getValueAt(int row, int col)
    {
	FoodPlanItem food = (FoodPlanItem)foods.get(row);
	Object o = "";
	switch (col)
	{
	    case 0: o= fm.idToCanneryCat.get(String.valueOf(food.cannery_cat)); 
		break;
	    case 1:  o= food.foodName;
		break;
	    //case 2:  o= fm.idToFoodGroup.get(String.valueOf(food.subCategory));
	//	break;
	    case 2:  o= new Float(food.recommendedAmount);
		break;
	    case 3:  o=  fm.idToAmount.get(String.valueOf(food.recommendedAmountUnits));
		break;
	    case 4:  o=  new Float(food.userAmount);
		break;
	    case 5:  o=  fm.idToAmount.get(String.valueOf(food.userAmountUnits)); 
		break;
	    
	}
	return o;
    }
    
    public java.util.Vector getData()
    {
	return this.foods;
    }
    
    public int getColumnCount()
    {
	return Constants.FOOD_PLAN_COL.length;
    }
    
    public int getRowCount()
    {
	return foods.size();
    }
    
    public String getColumnName(int column)
    {
	if(column<Constants.FOOD_PLAN_COL.length)
	{
	    //System.out.println(Constants.COL_NAMES[column]);
	    return Constants.FOOD_PLAN_COL[column];
	}
	else return "";
    }
    
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 4) {
            return false;
        } else {
            return true;
        }
    }
    
    public Class getColumnClass(int c) {
        if(foods!=null && foods.size()>0)
	{
	    Object o = getValueAt(0,c);
	    if(o==null) return "".getClass();
	    //System.out.println(getValueAt(0, c).getClass());
	    return o.getClass();
	}
	return "".getClass();
    }
    
    public void setValueAt(Object value, int row, int col)
    {
	//get the right object
	FoodPlanItem fpi = (FoodPlanItem)foods.get(row);
	if(fpi==null) return;
	//note that user touched it
	if(col == 5)  //this is the units
	{
	    if(value instanceof FoodTreeNode)
	    {
		fpi.setUserAmountUnits(((FoodTreeNode)value).getId());
	    }
	}
	else if (col==4)  //this is the amount
	{
	    float amount;
	    amount = ((Float)value).floatValue();
	    if(amount < 0.0)  //negative values invalid
	    {    
		//pop up warning
		javax.swing.JOptionPane.showMessageDialog(null,Constants.ERROR_EDITING_FOODAMOUNT,Constants.ERROR,javax.swing.JOptionPane.ERROR_MESSAGE);
		//don't change anything
		return;
	    }
	    fpi.setUserAmount(amount);
	    
	}
    }
    
    public void clear()
    {
	this.foods.clear();
	this.newFoods.clear();
    }
   
    /**
     * see if a food with the id already exists
     * returns true if the food exists
     */
    boolean foodExists(int[] ids)
    {
	String queryEnd = "";
	if(ids == null || ids.length==0) return false;
	queryEnd = String.valueOf(ids[0]);
	for(int i = 1; i<ids.length;i++)
	{
	    queryEnd = queryEnd+","+String.valueOf(ids[i]);
	}
	return (fm.exists(Constants.FOOD_IN_SUB_CAT_QUERY,new String[]{queryEnd}, 0)>0); 
	
    }
    
    java.util.Vector getNewItems()
    {
	return this.newFoods;
    }
    
    FoodPlanItem addNewItem(int[]foodIds,String subCat,int canneryCat,float amt, int unitId)
    {
	NewFoodPlanItem nfpi = new NewFoodPlanItem(foodIds, canneryCat,subCat,amt,unitId);
	foods.add(nfpi);
	newFoods.add(nfpi);
	this.fireTableDataChanged();
	return nfpi;
    }
    
    /**
     * create the food plan based on the recommended amounts
     */
     void generateDefaultFoodPlan(boolean newPlan, boolean keepUserValues, boolean updateUserValues, int numMonths,float userScaleFactor)
    {
	this.newPlan = newPlan;
	this.keepUserValues = keepUserValues;
	this.updateUserValues = updateUserValues;
	//get the number of people, kids under 5, adults
	String[][] family = fm.getStringsFromQuery(Constants.SELECT_FAMILY,null,new int[]{1,2,3});
	if(family == null || family.length==0)
	{
	    //TODO error out here
	    return;
	}
	int male = 0;
	int female = 0;
	int youngChild = 0;
	//see how many people we need numbers for
	for(int i = 0; i<family.length;i++)
	{
	    if(personIsChild(family[i][2]))
	    {
		youngChild++;
	    }
	    else if(family[i][1].equals(Constants.FAMILY_MALE))
	    {
		male++;
	    }
	    else if(family[i][1].equals(Constants.FAMILY_FEMALE))
	    {
		female++;
	    }
	
	}
	//generate new base plan
	if(male > 0) generatePlan(Constants.FOOD_PLAN_MAN,male,numMonths);
	if(female > 0 )generatePlan(Constants.FOOD_PLAN_WOMAN,female,numMonths);
	if(youngChild > 0)generatePlan(Constants.FOOD_PLAN_CHILD,youngChild,numMonths);
	//get all the 1 per family stuff
	generatePlan(Constants.FOOD_PLAN_FAMILY,1);
	//get user info from the db from last time
	getUserOldPlan(keepUserValues,updateUserValues,userScaleFactor);
	this.numMonths = numMonths;
    }
     //TODO test get user oldplan
     /**
      * gets the old food storage plan out of the database
      */
     void getUserOldPlan(boolean keepUserValues,boolean updatUserValues, float userScaleFactor)
     {
	 String [][]results = fm.getStringsFromQuery(Constants.FOOD_PLAN_USER_PLAN,null,new int[]{0,1,2,3,4}); 
	 int subcat=0;
	 //int amountId=0;
	 float amount =0;
	 boolean innerFound = false;
	 if(results ==null || results.length==0) return;
	 for(int i =0; i<results.length;i++)
	 {
	    amount = Float.parseFloat(results[i][3]);
	    FoodPlanItem fpiNew = new FoodPlanItem(Integer.parseInt(results[i][1]), results[i][2], Integer.parseInt(results[i][0]));
	    fpiNew.recommendedAmountUnits = Integer.parseInt(results[i][4]);
	    fpiNew.recommendedAmount = 0;
	    fpiNew.userAmountUnits = fpiNew.recommendedAmountUnits;
	    //since items are unique, see if the user really wants to multiply
	    if(fpiNew.userAmountUnits == Constants.AMOUNT_ITEM && amount!=(amount*userScaleFactor)) 
	    {
		String message = new String(Constants.INCREMENT_AMOUNT);
		message = message.replaceFirst("%q", fpiNew.foodName);
		message = message.replaceFirst("%q", String.valueOf(amount));
		message = message.replaceFirst("%q", String.valueOf(amount*userScaleFactor));
		int ans = javax.swing.JOptionPane.showConfirmDialog(null,message);
		if(ans == javax.swing.JOptionPane.YES_OPTION)
		{
		    amount = amount*userScaleFactor;
		}
	    }
	    fpiNew.userAmount = amount;
	    //
	    innerFound = false;
	    //find it in the vector
	   for(int j = 0; j<this.foods.size() && !innerFound;j++)
	     {
		 FoodPlanItem fpi1 = (FoodPlanItem)this.foods.get(j);
		 if(fpi1.equals(fpiNew))
		 {
		     fpi1.dbAmount = amount;
		     fpi1.dbAmountUnits = fpiNew.recommendedAmountUnits;
		     if(keepUserValues) //if we keep the user values, set this to be the value
		     {
			 fpi1.userAmount = fpi1.dbAmount;
		     }
		     if(updateUserValues) //if we'return updating, then keep the rec value only if bigger
		     {
			 if(fpi1.recommendedAmount <fpi1.dbAmount)
			 {
			     fpi1.userAmount = fpi1.dbAmount;
			 }
		     }
		     innerFound = true;
		 }
		 
	     }
	    //food isn't a default food, so we'll need to add it, and find all the other values for it
	    if(keepUserValues && !innerFound)
	     {
		 foods.add(fpiNew);
	     }
	 }
	 
     }
    
     /**
      * assumes default number of months to generate the plan.  This is for 
      * items like tents that you only need 1.
      */
     void generatePlan(int id, int numPeople)
    {
	
	 generatePlan(id,numPeople,Constants.FOOD_PLAN_DEFAULT_NUM_MONTHS);
    } 
     
    
    /**
     * talks to the db and gets the info for the number of people
     */
    void generatePlan(int id, int numPeople, int numMonths)
    {
	float factor = (float)numMonths/(float)Constants.FOOD_PLAN_DEFAULT_NUM_MONTHS;
	String[][] results = fm.getStringsFromQuery(Constants.FOOD_PLAN_DEFAULT_PLAN,new String[]{String.valueOf(id)},new int[]{0,1,2,3,4});
	float amount = 0;
	if(results == null || results.length == 0) return;
	boolean found = false;
	for(int i = 0; i < results.length; i++)
	{
	    //get amounts
	    found = false;
	    amount = factor*numPeople * Float.parseFloat(results[i][3]);
	    FoodPlanItem fpi = new FoodPlanItem(Integer.parseInt(results[i][1]), results[i][2], Integer.parseInt(results[i][0]));
	    fpi.recommendedAmountUnits = Integer.parseInt(results[i][4]);
	    fpi.recommendedAmount = amount;
	    fpi.userAmountUnits = fpi.recommendedAmountUnits;
	    fpi.userAmount = fpi.recommendedAmount;
	    //see if already exists,
	    for(int j=0; j<foods.size() && !found;j++)
	    {
		FoodPlanItem temp = (FoodPlanItem)foods.get(j);
		//if exists, add to existing (this can happen if you have 1 male and 1 female, etc.
		if(temp.equals(fpi))
		{
		    temp.recommendedAmount += fpi.recommendedAmount;
		    temp.userAmount = temp.recommendedAmount;
		    found = true;
		}
	    }
	    //if not exist, create new
	    if(!found)foods.add(fpi);
	}
    }
    
    /**
     * returns true if the birthday is less than five years ago
     */
    boolean personIsChild(String day)
    {
	java.util.Date today = new java.util.Date();
	java.util.Date bday = new java.util.Date(Long.parseLong(day));
	java.util.GregorianCalendar cal = new java.util.GregorianCalendar();
	cal.setTime(bday);
	cal.add(cal.YEAR, 5);
	java.util.Date child = cal.getTime();
	if(child.after(today)) return true;
	return false;
    }
    
    void truncateFoodPlan()
    {
	//String err = fm.exec(Constants.DELETE_USER_FOOD_STORAGE_AMOUNTS, Constants.TABLE_USER_FOOD_STORAGE_AMOUNTS);
	//can't get delete to work so just drop and rebuild the table
	String err = fm.exec(Constants.DROP_USER_FOOD_STORAGE_AMOUNTS, Constants.TABLE_USER_FOOD_STORAGE_AMOUNTS);
	err += fm.exec(Constants.CREATE_USER_FOOD_STORAGE_AMOUNTS, Constants.TABLE_USER_FOOD_STORAGE_AMOUNTS);
	//err+= 
	System.out.println("error="+err);

    }
    
    /**
     * commits the current plan to the db
     */
    public boolean commit()
    {
	if(foods.size() == 0) return true;
	Object o;
	String error="";
	truncateFoodPlan();

	for(int i = 0; i<foods.size();i++)
	{
	    o = foods.get(i);
	    //if new item, add the sub category stuff to the appropriate tables
	    if(o instanceof NewFoodPlanItem)
	    {
		//set the id to be the id we just got
		error = commitNewItem((NewFoodPlanItem)o);
		
	    }
	    //
	    FoodPlanItem fpi = (FoodPlanItem)o;
	   if(o instanceof FoodPlanItem && error.length()==0)
	    {
		//add it to the user's food storage plan
		//fm.exec(Constants.INSERT_USER_FOOD_STORAGE_AMOUNTS, new String[]{String.valueOf(fpi.subCategory),String.valueOf(fpi.userAmount),String.valueOf(fpi.userAmountUnits)}, Constants.TABLE_USER_FOOD_STORAGE_AMOUNTS);
		error+=fpi.commit(fm);
	    }
	    if(error.length() > 0)
		{
		    //throw up dialog box
		    int e = javax.swing.JOptionPane.showConfirmDialog(null,Constants.ERROR_CHANGING_FOODPLAN,Constants.ERROR,javax.swing.JOptionPane.ERROR_MESSAGE);
		    if(e != javax.swing.JOptionPane.YES_OPTION)
		    {
			//delete the table
			truncateFoodPlan();
			return false;
		    }
		    else //the user didn't care, so ditch the error
		    {
			error="";
		    }
		}
	}//end for
	if(numMonths>0)
	{
	    fm.setProperty(Constants.PROP_FOOD_PLAN_YEARS, String.valueOf(numMonths));
	}
	return true;
    }
    
    /**
     * commits the sub category stuff to the appropriate tables
     * @return the error message if any
     */
    String commitNewItem(NewFoodPlanItem nfpi)
    {
	String error = "";
	String err = "";
	err = fm.exec(Constants.INSERT_CANNERY_SUB_CATEGORY, new String[]{nfpi.foodName,String.valueOf(nfpi.cannery_cat)}, Constants.TABLE_CANNERY_SUB_CATEGORY);
	if(err!=null) error = error+err;
	if(error!=null && error.length()>0) return error;
	String[] idStrs = fm.getStringsFromQuery(Constants.SELECT_CANNERY_SUB_CATEGORY_FOR_NAME,new String[]{nfpi.foodName,String.valueOf(nfpi.cannery_cat)},0);
	if(idStrs.length >0)
	{
	    nfpi.subCategory = Integer.parseInt(idStrs[0]); //shouldn't be more than one, but if is, just ignore it
	    //for each food associated with it
	    for(int i = 0; i < nfpi.foodIds.length;i++)
	    {
		err= fm.exec(Constants.INSERT_SUB_CATEGORY_TO_FOOD_DES, new String[]{String.valueOf(nfpi.subCategory),String.valueOf(nfpi.foodIds[i])}, Constants.TABLE_SUB_CATEGORY_TO_FOOD_DES);
		if(err!=null && err.length()>0) error = error + err;
		if(error.length()>0) return error;
	    }
	}
	else //insert failed silently somehow
	{
	    error = "could not find id in table cannery_sub_category";
	}
	return error;
    }
    
    
}




