/*
 * FoodPlanItem.java
 *
 * Created on September 13, 2006, 3:03 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package foodthinger;

/**
 *
 * @author RC Stanley
 */
public class FoodPlanItem implements java.lang.Comparable
{
   
    public float recommendedAmount;  //how much the calculated amount is
    public float userAmount; //how much the user wants
    public int subCategory; //what item it is
    public String foodName;
    public int cannery_cat; //what the cannery category for it is
    //public int fd_group_id; //what the subcategory is
    public int recommendedAmountUnits; //what the units 
    public int userAmountUnits;
    public float dbAmount;//what the user said previously in the database
    public int dbAmountUnits;//the units the user OKed previously in the database
     public boolean touched;
     public boolean inDb;
     FoodPlanSummaryItem summary;
    
  
    /** Creates a new instance of FoodPlanItem */
    public FoodPlanItem(int subCategory, String foodName, int cannery_cat, /*int fd_group_id,*/ float recommendedAmount,
	    int recommendedAmountUnits, float dbAmount, int dbAmountUnits,
	    float userAmount,int userAmountUnits)
    {
	this.subCategory = subCategory;
	this.foodName = foodName;
	this.cannery_cat = cannery_cat;
	//this.fd_group_id = fd_group_id;
	this.recommendedAmount = recommendedAmount;
	this.recommendedAmountUnits = recommendedAmountUnits;
	this.dbAmount = dbAmount;
	this.dbAmountUnits = dbAmountUnits;
	this.userAmount = userAmount;
	this.userAmountUnits = userAmountUnits;
	this.touched = false;
	this.inDb = false;
    }
    
    public FoodPlanItem(int ndb_no, String foodName, int cannery_cat/*, int fd_group_id*/, float recommendedAmount,
	    int recommendedAmountUnits, float dbAmount, int dbAmountUnits,
	    float userAmount,int userAmountUnits, boolean inDb, boolean userTouched)
    {
	this.subCategory = ndb_no;
	this.foodName = foodName;
	this.cannery_cat = cannery_cat;
	//this.fd_group_id = fd_group_id;
	this.recommendedAmount = recommendedAmount;
	this.recommendedAmountUnits = recommendedAmountUnits;
	this.dbAmount = dbAmount;
	this.dbAmountUnits = dbAmountUnits;
	this.userAmount = userAmount;
	this.userAmountUnits = userAmountUnits;
	this.touched = userTouched;
	this.inDb = inDb;
    }
    
    public FoodPlanItem(int subCat, String foodName, int cannery_cat)
    {
	this.subCategory = subCat;
	this.foodName = foodName;
	this.cannery_cat = cannery_cat;
	//this.fd_group_id = fd_group_id;
    }
    
    void setSummaryItem(FoodPlanSummaryItem summary)
    {
	this.summary = summary;
    }
    
    void setUserAmountUnits(int id)
    {
	this.userAmountUnits = id;
    }
    
    void setUserAmount(float amount)
    {
	summary.amountChanged(userAmount,amount);
	this.userAmount = amount;
    }
    
    public int compareTo(Object o)
    {
	if(o instanceof FoodPlanItem)
	{
	    FoodPlanItem fpi = (FoodPlanItem)o;
	    if(fpi!=null)
	    {
		if((fpi.subCategory == this.subCategory) && (this.recommendedAmountUnits == fpi.recommendedAmountUnits))
		{
		    return 0;
		}
		if(fpi.subCategory > this.subCategory) return 1;
		
	    }
	    return -1;
	}
	return 1;
    }
    
    public String commit(FoodModel fm)
    {
	String err = "";
	if(this.recommendedAmount!=0 || this.userAmount!=0) //basically if the recommend amount is 0 and the user amount is 0, don't put it in the db
	{
	     err =  fm.exec(Constants.INSERT_USER_FOOD_STORAGE_AMOUNTS, new String[]{String.valueOf(subCategory),String.valueOf(userAmount),String.valueOf(userAmountUnits)}, Constants.TABLE_USER_FOOD_STORAGE_AMOUNTS);
	}
	if(err==null)return "";
	return err;
    }
    
        
    public boolean equals(Object o)
    {
	if(o instanceof FoodPlanItem)
	{
	    if(o!=null)
	    {
		FoodPlanItem fpi = (FoodPlanItem)o;
		return ((fpi.subCategory == this.subCategory) && (this.recommendedAmountUnits == fpi.recommendedAmountUnits));
	    }
	}
	return false;
    }
}
