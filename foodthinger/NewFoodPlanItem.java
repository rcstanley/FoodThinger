/*
 * NewFoodPlanItem.java
 *
 * Created on September 19, 2006, 2:33 PM
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
public class NewFoodPlanItem extends FoodPlanItem
{
    int[] foodIds;
    /** Creates a new instance of NewFoodPlanItem */
    public NewFoodPlanItem(int[]foodIds,int cannery_cat,String subCategory,float userAmount,int userAmountUnits)
    {
	super(-1, subCategory, cannery_cat);
	this.userAmount = userAmount;
	this.userAmountUnits = userAmountUnits;
	this.recommendedAmount = 0;
	this.recommendedAmountUnits = userAmountUnits;
	this.foodIds = foodIds;
    }
    
     public int compareTo(Object o)
    {
	if(o instanceof FoodPlanItem)
	{
	    FoodPlanItem fpi = (FoodPlanItem)o;
	    if(fpi!=null)
	    {
		if((fpi.foodName == this.foodName) && (this.recommendedAmountUnits == fpi.recommendedAmountUnits))
		{
		    return 0;
		}
		if(fpi.subCategory > this.subCategory) return 1;
		
	    }
	    return -1;
	}
	return 1;
    }
    
    public boolean equals(Object o)
    {
	if(o instanceof FoodPlanItem)
	{
	    if(o!=null)
	    {
		FoodPlanItem fpi = (FoodPlanItem)o;
		return ((fpi.foodName == this.foodName) && (this.recommendedAmountUnits == fpi.recommendedAmountUnits));
	    }
	}
	return false;
    }
    
    boolean containsNdbNo(int qids[])
    {
	if(qids==null || this.foodIds == null) return false;
	for(int i = 0; i<this.foodIds.length;i++)
	{
	    for(int j = 0; j < qids.length;j++)
	    {
		if(this.foodIds[i]==qids[j]) return true;
	    }
	}
	return false;
    }
    
    String idsAsString()
    {
	String queryEnd = "";
	if(foodIds == null || foodIds.length==0) return "";
	queryEnd = String.valueOf(foodIds[0]);
	for(int i = 1; i<foodIds.length;i++)
	{
	    queryEnd = queryEnd+","+String.valueOf(foodIds[i]);
	}
	return queryEnd;
    }
    
}
