/*
 * FoodPlanSummaryItem.java
 *
 * Created on September 18, 2006, 1:33 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package foodthinger;

/**
 * contains all the items, plus the summary and totals
 * @author RC Stanley
 */
public class FoodPlanSummaryItem
{
    int cannery_cat;
    float recommendedAmount;
    int recommendedUnits;
    float userAmount;
    String canneryCatName;
    javax.swing.JLabel text;
    String unitName;
    
    java.util.TreeSet items;
    /** Creates a new instance of FoodPlanSummaryItem */
    public FoodPlanSummaryItem(int cannery_cat,String canneryCatName,javax.swing.JLabel info, int recommendedUnits, String unitName)
    {
	this.cannery_cat = cannery_cat;
	this.canneryCatName = canneryCatName;
	items = new java.util.TreeSet();
	this.recommendedUnits = recommendedUnits;
	this.text = info;
	this.unitName = unitName;
    }
    
    boolean addFoodItem(FoodPlanItem item)
    {
	if(items.contains(item))
	{
	    return true;
	}
	if((item.cannery_cat == cannery_cat) && (recommendedUnits == item.recommendedAmountUnits))
	{
	    items.add(item);
	    item.setSummaryItem(this);
	    //only amounts if they are already in the db
	    //this allows the user to add weird stuff without
	    //me having to do a bunch of conversions
	    //TODO figure out if we want to ignore the stuff the user adds or not
	    if(item.recommendedAmount > 0)
	    {
		recommendedAmount += item.recommendedAmount;
		userAmount += item.userAmount;
		updateText();
	    }
	    return true;
	}
	return false;
    }
    
    public void amountChanged(float oldAmount, float newAmount)
    {
	userAmount -= oldAmount;
	userAmount += newAmount;
	updateText();
    }
    
    /**
     * data has been changed, update the totals
     */
    /*void updateUserAmount(FoodPlanItem item, float oldAmount)
    {
	if(item == null) return;
	if(items.contains(item))
	{
	    float uamount = item.userAmount;
	    userAmount -=uamount;
	    userAmount+=item.userAmount;
	}
	updateText();
    }*/
    
    void updateText()
    {
	String str = this.canneryCatName+":"+String.valueOf(recommendedAmount)+" / "+ String.valueOf(userAmount)+" "+unitName;
	if(recommendedAmount > userAmount)
	{
	    str = "<html><font color=#ff1111>* "+str+"</font></html>";
	}
	else
	{
	    str = "<html>"+str+"</html>";
	}
	text.setText(str);
    }
    
   /* float convert(float amount, int units, int desiredUnits)
    {
	if(units == desiredUnits) return amount;
	//TODO: look this up in the db and convert it
	return 0;
    }*/
    
    
    
}
