/*
 * FoodModel.java
 *
 * Created on June 16, 2006, 1:17 PM
 *
  */

package foodthinger;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.DefaultComboBoxModel;
import SQLite.TableResult;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Vector;
import java.util.GregorianCalendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Properties;


/**
 * Data Model for the FoodThinger project.  Contains the DB, etc.
 * @author RC Stanley
 */
public class FoodModel
{
    FoodDatabase db = null;
    DefaultTreeModel tree = null;
    HashMap queryToComboModel = null;
    HashMap tableToModel = null;
    int maxExpDate;
    HashMap idToContainer;
    HashMap idToLocation;
    HashMap idToStore;
    HashMap idToAmount;
    HashMap idToCommonName;
    HashMap idToFoodGroup;
    HashMap idToCanneryCat;
    HashMap idToSubCategory;
    TableFormatter maps[];
    Properties props;
    
    
    /** Creates a new instance of FoodModel */
    public FoodModel(String url, String dbFile, Properties props) throws SQLite.Exception
    {
	this.props = props;
	tableToModel = new HashMap();
	db = new FoodDatabase(url, dbFile);
	queryToComboModel = new HashMap();
	String maxExp = props.getProperty(foodthinger.Constants.MAX_EXP_DATE, "30");
	maxExpDate = Integer.parseInt(maxExp);
	idToContainer = new HashMap();
	this.createHashMap(Constants.PANTRY_ADDER_CONTAINER_QUERY,idToContainer,1,0);
	idToLocation = new HashMap();
	this.createHashMap(Constants.PANTRY_ADDR_LOC_QUERY, idToLocation, 1, 0);
	idToStore = new HashMap();
	this.createHashMap(Constants.PANTRY_ADDER_STORE_QUERY, idToStore, 1, 0);
	idToAmount = new HashMap();
	this.createHashMap(Constants.PANTRY_ADDR_AMOUNTS_QUERY, idToAmount, 1, 0);
	idToCommonName = new HashMap();
	this.createHashMap(Constants.PANTRY_ADDR_COMMON_FOODS_QUERY, idToCommonName, 1, 0);
	idToFoodGroup = new HashMap();
	this.createHashMap(Constants.PANTRY_ADDR_FD_GROUP_QUERY, idToFoodGroup, 1, 0); 
	idToCanneryCat = new HashMap();
	this.createHashMap(Constants.PANTRY_ADDR_CANNERY_CAT_QUERY, idToCanneryCat, 1, 0); 
	idToSubCategory = new HashMap();
	this.createHashMap(Constants.SUB_CATEGORY_QUERY, idToSubCategory, 1, 0); 
	maps = new TableFormatter[Constants.COL_NAMES.length];
	
	//"Id","Expired","Food Type","Container","Experation Date","Purchased Date","Location",amount, "Store","Price","Weight"};
	/*DateTableFormatter dateFormatter = new DateTableFormatter();
	maps[0]= null;
	maps[1]= null;
	maps[2]= null;
	maps[3]= new LookupTableFormatter(idToContainer);
	maps[4]= dateFormatter;
	maps[5]= dateFormatter;
	maps[6] = new LookupTableFormatter(idToLocation);
	maps[7] = new LookupTableFormatter(idToAmount);
	maps[8] = new LookupTableFormatter(idToStore);
	*/
    }
    
    public String getIconUrl()
    {
	return props.getProperty("user.dir")+"/"+props.getProperty(Constants.PROP_ICON_URL);
    }
    
    public String getHelpUrl()
    {
	String helpurl = props.getProperty("user.dir")+"/"+props.getProperty(Constants.HELP_URL);
	return helpurl;
    }
    
    public int getMaxExp()
    {
	return maxExpDate;
    }
    
    /**
     * returns the count of a sql query.  assumes the sql has count(*)
     */
    
    protected int getCount(String sql)
    {
	String[]results = null;
	int count = 0;
	try
	{
	    TableResult tr = db.getTable(sql);
	    if(tr.nrows > 0)
	    {
		Iterator itr = tr.rows.iterator();
		results = new String[tr.nrows];
		String s[];
		int i = 0;
		while(itr.hasNext()) //
		{
		   s = (String[])itr.next(); 
		   if(s[0]!=null)
		   {
		    count += Integer.parseInt(s[0]);
		   }
		   i++;
		}
	    }
	}
	catch(SQLite.Exception e)
	{
	    System.out.println(e);
	    e.printStackTrace(System.out);
	}
	return count;
    }
    
    protected void notifyModelListener(String table)
    {
	if(table==null) return;
	if(tableToModel.containsKey(table)) //someone else is watching this table
	{
	    Vector v = (Vector)(tableToModel.get(table));
	    boolean found =false;
	    for(int j = 0; j<v.size();j++)
	    {
		Object model = v.elementAt(j);
		if ( model instanceof DefaultTreeModel)
		{
		    DefaultTreeModel tm = (DefaultTreeModel)(model);
		    DefaultMutableTreeNode node = getTreeNodesForModel();
		    tm.setRoot(node);
		    tm.reload(node);
		    //tm.fire
		 
		}
		else if (model instanceof PantryTableModel)
		{
		    PantryTableModel ptm = (PantryTableModel)model;
		    ptm.reloadData();
		   // ptm.
		}
		else if(model instanceof javax.swing.DefaultComboBoxModel) //combo box, rebuild
		{
		    //get the query back
		    javax.swing.DefaultComboBoxModel cmodel = (javax.swing.DefaultComboBoxModel)model;
		    notifyDefaultComboModel(cmodel);
		}
		else if(model instanceof DatabaseTableModel)
		{
		    //TODO figure out what should happen here
		    DatabaseTableModel hmh = (DatabaseTableModel)model;
		    hmh.reloadData();
		}
		else if(model instanceof HashMapHolder)
		{
		    //TODO figure out what should happen here
		    HashMapHolder hmh = (HashMapHolder)model;
		    hmh.rebuildMap(this);
		}
	    }
	}
    }
    /**
     * looks through a model and sees if there are any items added later
     */
    java.util.Vector getItemsAddedToCombo(javax.swing.DefaultComboBoxModel model)
    {
	java.util.Vector addOns = new Vector(3);
	FoodTreeNode fn;
	for(int i = 0; i < model.getSize();i++)
	{
	    fn = (FoodTreeNode)model.getElementAt(i);
	    if(fn.getId()<0)
	    {
		addOns.add(fn);
	    }
	}
	
	
	return addOns;
    }
    
    /**
     * lets the combo box model know it has been changed so it can refresh its contents
     */   
    protected void notifyDefaultComboModel(javax.swing.DefaultComboBoxModel model)
    {
	java.util.Set set = queryToComboModel.entrySet();
	//find id of selected
	FoodTreeNode sel = ((FoodTreeNode)model.getSelectedItem());
	int idSelected = 0;
	if(sel!=null)
	{
	    idSelected = sel.getId();
	}
	//find id of added (like [none])
	java.util.Vector addOns = getItemsAddedToCombo(model);
	java.util.Iterator iter = set.iterator();
	while(iter.hasNext())
	{
	    //clear the box
	    java.util.Map.Entry entry = (java.util.Map.Entry)iter.next(); 
	    if(entry!=null)
	    {
		//System.out.println(entry.getKey());
		//System.out.println(entry.getValue());
		if((javax.swing.DefaultComboBoxModel)(entry.getKey())==model)
		{
		    //get what should be in the item
		    Object results[] = getNodesFromQuery((String)entry.getValue(), 0,1);
		    //clear the current model
		    model.removeAllElements();
		    //add in the new stuff
		    for(int i = 0; i<results.length;i++)
		    {
			model.addElement(results[i]);
		    }

		}
	    }
	}
	//add in the extra items
	for(int i = 0; i<addOns.size();i++)
	{
	    model.addElement(addOns.get(i));
	}
	//set selected to previous value
	selectComboById(idSelected,model);
	
    }
    
    void selectComboById(int id, javax.swing.DefaultComboBoxModel model)
    {
	FoodTreeNode ftn;
	//if model is empty bail
	if(model.getSize()<=0) return;
	for(int i = 0; i< model.getSize();i++)
	{
	    ftn = (FoodTreeNode)model.getElementAt(i);
	    if(ftn.getId()==id)
	    {
		model.setSelectedItem(ftn);
		return;
	    }
	}
	//didn't find it, check if one less than 0
	for(int i = 0; i< model.getSize();i++)
	{
	    ftn = (FoodTreeNode)model.getElementAt(i);
	    if(ftn.getId()<0)
	    {
		model.setSelectedItem(ftn);
		return;
	    }
	}
	//still no dice, just pick something
	model.setSelectedItem(model.getElementAt(0));
    }
    
    public void addModelListener(String[]tables,Object model)
    {
	//bail out if there isn't any data
	if(tables == null || model == null)return;
	for(int i = 0; i<tables.length;i++)
	{
	    if(tableToModel.containsKey(tables[i])) //someone else is watching this table
	    {
		Vector v = (Vector)(tableToModel.get(tables[i]));
		boolean found =false;
		for(int j = 0; j<v.size();j++)
		{
		    if(v.elementAt(j).equals(model))  //object already being watched
		    {
			found = true;
		    }
		}
		if(!found)
		{
		    v.add(model);
		}
	    }
	    else
	    {
		Vector v = new Vector(4);
		v.add(model);
		tableToModel.put(tables[i],v);
	    }
	}
    }
    
    /**
     * returns a tree model for a table view
     */
    protected TreeModel getTreeModel()
    {
	if(tree!=null)
	{
	    return tree;
	}
	DefaultMutableTreeNode top = getTreeNodesForModel();
	tree = new DefaultTreeModel(top);
	this.addModelListener(new String[]{Constants.TABLE_FOOD_STORED,Constants.TABLE_CANNERY_CATEGOREY, Constants.TABLE_FD_GROUP},tree);
	return tree;
    }
    
    
    public DefaultMutableTreeNode getTreeNodesForModel()
    {
	
	int count = getCount(Constants.FOOD_STORED_COUNTER_QUERY);
	DefaultMutableTreeNode top = new DefaultMutableTreeNode(new FoodTreeNode("All ("+count+")", -1));
	try
	{
	    //tree hasn't been created yet
	    TableResult tr= db.getTable(Constants.TREE_MODEL_CATEGORY_QUERY);

	    Iterator itr = tr.rows.iterator();
	    String s[];
	    TableResult trSub;
	    Iterator itrSubCat;
	    String subs[];
	    DefaultMutableTreeNode catTop;
	    String args[] = new String[1];
	    int id;
	    //for each result, see if there are sub_categories
	    while(itr.hasNext())
	    {
		
		s = (String[])itr.next();
		//create category node
		id = Integer.parseInt(s[0]);
		count = getCount(Constants.FOOD_STORED_COUNTER_QUERY
			+Constants.FOOD_STORED_TABLE_QUERY_WHERE
			+ " (select fdgrp_cd from fd_group where cannery_cat in ("+s[0]+"))) ");
		catTop = new DefaultMutableTreeNode(new FoodTreeNode(s[1]+"("+count+")", id));
		//set up for queries into subgroups (USDA stuff)
		args[0]=s[0];
		trSub = db.getTable(Constants.TREE_MODEL_SUB_CATEGORY_QUERY,args);
		itrSubCat = trSub.rows.iterator();
		while(itrSubCat.hasNext())
		{
		    subs = (String[])itrSubCat.next();
		    count = getCount(Constants.FOOD_STORED_COUNTER_QUERY
			+ Constants.FOOD_STORED_TABLE_QUERY_WHERE
			+"("+subs[0]+"))");
		    catTop.add(new DefaultMutableTreeNode(new FoodTreeNode(subs[1]+" ("+count+")", Integer.parseInt(subs[0]))));
		}
		top.add(catTop);
	    }
	}
	catch(SQLite.Exception e)
	{
	    System.out.println(e);
	    e.printStackTrace(System.out);
	}
	return top;
    }
    
    
    
    public void createHashMap(String sql,HashMap map, int idIndex, int dataIndex)
    {
	createHashMap(sql,map,idIndex,dataIndex, true);
    }
    
    public void createHashMap(String sql,HashMap map, int idIndex, int dataIndex, boolean addListener)
    {
	//String results[];
	try
	{
	    TableResult tr = db.getTable(sql);
	    if(tr.nrows > 0)
	    {
		Iterator itr = tr.rows.iterator();
		if(idIndex > tr.ncolumns || dataIndex > tr.ncolumns)
		{
		    throw new SQLite.Exception("the column index doesn't exist");
		}
		//results = new String[tr.nrows];
		String s[];
		int i = 0;
		while(itr.hasNext())
		{
		   s = (String[])itr.next(); 
		   map.put(s[idIndex], s[dataIndex]);
		   //results[i]= s[indexOfInfo];
		   i++;
		}
	    }
	}
	catch(SQLite.Exception e)
	{
	    System.out.println(e);
	    e.printStackTrace(System.out);
	}
	if(addListener)
	{
	    this.addModelListenersForQuery(sql,new HashMapHolder(sql,map, idIndex, dataIndex));
	}
    }
    
    /**
     * Determins if a query returns any rows
     * @return 0 if query returns no rows, the number of rows otherwise
     */
    public int exists(String sql, String[] values, int indexOfInfo)
    {
	String[] results = getStringsFromQuery(sql,values,indexOfInfo);
	if(results== null) return 0;
	return results.length;
	
    }
    
    public String[][] getStringsFromQuery(String sql, String[] values, boolean header)
    {
	String[][]results = null;
	try
	{
	    TableResult tr = db.getTable(sql,values);
	    if(tr.nrows > 0)
	    {
		int i = 0;
		results = new String[header?tr.nrows+1:tr.nrows][tr.ncolumns];
		if(header)
		{
		    for(int j = 0;j<tr.column.length;j++)
		    {
			results[i][j] = tr.column[j];
		    }
		    i++;
		}
		Iterator itr = tr.rows.iterator();
		
		String s[];
		
		while(itr.hasNext())
		{
		   s = (String[])itr.next(); 
		   for(int j = 0; j<tr.ncolumns;j++)
		   {
		    results[i][j]= s[j];
		   }
		   i++;
		}
	    }
	}
	catch(SQLite.Exception e)
	{
	    System.out.println(e);
	    e.printStackTrace(System.out);
	}
	return results;
    }
   
    /**
     * get the results from a query and return the values as a string
     */
    public String[] getStringsFromQuery(String sql,String[] values,int indexOfInfo)
    {
	String[]results = null;
	try
	{
	    TableResult tr = db.getTable(sql,values);
	    if(tr.nrows > 0)
	    {
		Iterator itr = tr.rows.iterator();
		results = new String[tr.nrows];
		String s[];
		int i = 0;
		while(itr.hasNext())
		{
		   s = (String[])itr.next(); 
		   results[i]= s[indexOfInfo];
		   i++;
		}
	    }
	}
	catch(SQLite.Exception e)
	{
	    System.out.println(e);
	    e.printStackTrace(System.out);
	}
	return results;
    }
    
    public String exec (String sql)
    {
	String error = db.execute(sql);
	java.util.Vector v = this.findTableName(sql);
	for(int i = 0; i<v.size();i++)
	{
	    notifyModelListener((String)v.get(i));
	}
	return error;
    }
    
       
    public String exec(String sql, String[]args, String tableName)
    {
	   String error = db.execute(sql,args);
	   notifyModelListener(tableName);
	   return error;
    }
    
    public String exec(String sql, String tableName)
    {
	   String error = db.execute(sql);
	   notifyModelListener(tableName);
	   return error;
    }
    
     /**
     * get the results from a query and return the values as a string
     */
    public String[][] getStringsFromQuery(String sql,String[] values,int[] infoIds)
    {
	String[][]results = null;
	try
	{
	    TableResult tr = db.getTable(sql,values);
	    if(tr.nrows > 0)
	    {
		Iterator itr = tr.rows.iterator();
		//query isn't right, so barf and return null;
		if(infoIds.length > tr.ncolumns)
		{
		    throw new SQLite.Exception("Got back fewer columns than you expected - RCS");
		}
		results = new String[tr.nrows][infoIds.length];
		String s[];
		int i = 0;
		while(itr.hasNext())
		{
		   s = (String[])itr.next(); 
		   for(int j=0; j<infoIds.length;j++)
		   {
			results[i][j]= s[infoIds[j]];
		   }
		   i++;
		}
	    }
	}
	catch(SQLite.Exception e)
	{
	    System.out.println(e);
	    e.printStackTrace(System.out);
	}
	return results;
    }
    
    /**
     * get the results from a query and return the values as FoodTreeNodes
     */
    public Object[] getNodesFromQuery(String sql, int indexOfString, int indexOfId)
    {
	FoodTreeNode results[];
	try
	{
	    //query the table
	    TableResult tr= db.getTable(sql);
	    //if there are results...
	    if(tr.nrows > 0)
	    {
		Iterator itr = tr.rows.iterator();
		results = new FoodTreeNode[tr.nrows];
		//System.out.println("tr.numrows="+tr.nrows);
		String s[];
		int i = 0;
		//for each row in resultset
		while(itr.hasNext())
		{
		    //go to the next row
		    s = (String[])itr.next();
		    
		    //assign desired index from result table to the string to return;
		    results[i]=new FoodTreeNode(s[indexOfString],Integer.parseInt(s[indexOfId]));
		    //increment counter
		    i++;

		}
	    }
	    else //oops, no results
	    {
		results = new FoodTreeNode[1];
		results[0] = new FoodTreeNode("No Rows in DB",-1);
	    }
	}
	catch(SQLite.Exception e) //db exception
	{
	    System.out.println(e);
	    e.printStackTrace(System.out);
	    results = new FoodTreeNode[1];
	    results[0] = new FoodTreeNode("DB Error"+e.toString(),-1);
	}
	return results;
    }
    
    /**
     * Gets a combo box model, specifically, it makes sure that any box
     * that uses the same query gets the same model.
     */
    public DefaultComboBoxModel getComboModelForQuery(String sql, int item, int id)
    {
	/*if(this.queryToComboModel.containsKey(sql))
	{
	    return (DefaultComboBoxModel)(this.queryToComboModel.get(sql));
	}
	else*/
	{
	    
	    Object results[] = getNodesFromQuery(sql, item, id);
	    DefaultComboBoxModel model =  new DefaultComboBoxModel(results);
	    //this.queryToComboModel.put(sql,model);
	    this.queryToComboModel.put(model,sql);
	    addModelListenersForQuery(sql,model);
	    return model;
	}
    }
    
    protected void addModelListenersForQuery(String sql,Object model)
    {
	for(int i = 0; i<Constants.TABLES.length;i++)
	{
	    if(sql.contains(Constants.TABLES[i]))
	    {
		this.addModelListener(new String[]{Constants.TABLES[i]}, model);
	    }
	}
    }
    
    protected java.util.Vector findTableName(String sql)
    {
	java.util.Vector v = new java.util.Vector();
	for(int i = 0; i<Constants.TABLES.length;i++)
	{
	    if(sql.contains(Constants.TABLES[i]))
	    {
		v.add(Constants.TABLES[i]);
	    }
	}
	return v;
    }
    
    public DefaultComboBoxModel getComboModelForQuery(String sql, int item, int id,FoodTreeNode defaultNode)
    {
	DefaultComboBoxModel model = getComboModelForQuery(sql, item,id);
	model.addElement(defaultNode);
	model.setSelectedItem(defaultNode);
	return model;
    }

   
    /**
     * Creates a list of years before and after today's date
     */
    
    String[] getYearList(int yearsPast, int yearsFuture)
    {
	Date today = new Date();
	String[] years = new String[yearsPast+yearsFuture+1]; //the +1 is to add in the current year
	SimpleDateFormat format = new SimpleDateFormat("yyyy") ;
	GregorianCalendar gc = new GregorianCalendar(); 
	years[yearsPast] = format.format(gc.getTime());
	for(int i = 0; i < yearsPast; i++)
	{
	    gc.add(gc.YEAR,-1);
	    years[(yearsPast -1) - i]= format.format(gc.getTime());
	}
	gc.setTime(today); //Jan 1 is just to be lazy;
	for(int i = 0; i < yearsFuture; i++)
	{
	    gc.add(gc.YEAR,1);
	    years[(yearsPast +1) + i]= format.format(gc.getTime());  //+1 is to skip current year
	}
	
	
	return years;
     }
    
    String insertFoodStored(int food_type_id, int container_id, int location_id, 
	    int number_purchased, long purchase_date , long experation_date , double price, 
	    int store, double amount, int amount_id)
    {
	String args[] = new String[10];
	args[0]= String.valueOf(food_type_id);
	args[1]= String.valueOf(container_id);
	args[2]= String.valueOf(location_id);
	args[3]= String.valueOf(number_purchased);
	args[4]= String.valueOf(purchase_date);
	args[5]= String.valueOf(experation_date);
	args[6]= String.valueOf(price);
	args[7]= String.valueOf(store);
	args[8]= String.valueOf(amount);
	args[9]= String.valueOf(amount_id);
	String result =  db.insertQuery(Constants.INSERT_FOOD_STORED, args);
	if(result==null)
	{
	    this.notifyModelListener(Constants.TABLE_FOOD_STORED);
	}
	return result;
    }
    
    String updateFoodStored(int food_type_id, int container_id, int location_id, 
	    int number_purchased, long purchase_date , long experation_date , double price, 
	    int store, double amount, int amount_id, int id)
    {
	String args[] = new String[11];
	args[0]= String.valueOf(food_type_id);
	args[1]= String.valueOf(container_id);
	args[2]= String.valueOf(location_id);
	args[3]= String.valueOf(number_purchased);
	args[4]= String.valueOf(purchase_date);
	args[5]= String.valueOf(experation_date);
	args[6]= String.valueOf(price);
	args[7]= String.valueOf(store);
	args[8]= String.valueOf(amount);
	args[9]= String.valueOf(amount_id);
	args[10] = String.valueOf(id);
	String result =  db.execute(Constants.UPDATE_FOOD_STORED, args);
	if(result==null)
	{
	    this.notifyModelListener(Constants.TABLE_FOOD_STORED);
	}
	return result;
    }
    
    /**
     *gets the model for the food table with a set of ids of either
     *cannery codes or the usda codes, depending on what has 
     *been selected by user from the tree
     */
    String[][] getFoodTable(String canneryCds,String grpcds)
    {
	if(canneryCds.length()==0 && grpcds.length()==0) //if both don't exists, this is the same as 
	{
	    return getFoodTable(Constants.FOOD_STORED_TABLE_QUERY, false);
	}
	else if(canneryCds.length()>0&& grpcds.length()==0)
	{
	    return getFoodTable(Constants.FOOD_STORED_TABLE_QUERY
		    + Constants.FOOD_STORED_TABLE_QUERY_WHERE
		    + " (select fdgrp_cd from fd_group where cannery_cat in ("+canneryCds+"))) "
		    + Constants.FOOD_STORED_TABLE_QUERY_END, false);
	}
	else if(canneryCds.length()==0&& grpcds.length()>0)
	{
	     return getFoodTable(Constants.FOOD_STORED_TABLE_QUERY
		    + Constants.FOOD_STORED_TABLE_QUERY_WHERE
		    + "("+grpcds+")) "
		    + Constants.FOOD_STORED_TABLE_QUERY_END, false);

	}
	else if(canneryCds.length()>0&& grpcds.length()>0)
	{
	     return getFoodTable(Constants.FOOD_STORED_TABLE_QUERY
		    + Constants.FOOD_STORED_TABLE_QUERY_WHERE
		    + " (select fdgrp_cd from fd_group where cannery_cat in ("+canneryCds+"))"
		     + " or fdgrp_cd in ("+grpcds+")) "
		    + Constants.FOOD_STORED_TABLE_QUERY_END, false);

	}
	//this statement shouldn't ever be reached
	return null;
    }
    
    /**
     * returns a table model of all the rows in food_stored in the database
     */
    String[][] getFoodTable()
    {
	return getFoodTable(Constants.FOOD_STORED_TABLE_QUERY + Constants.FOOD_STORED_TABLE_QUERY_END, false);
    }
    
    /**
     * Gets the model for the table in pantryjinternalframe
     */
      String[][] getFoodTable(String sql, boolean useCommonName)
    {
	System.out.println(sql);
	String initialResults [][] = getStringsFromQuery(sql,null,Constants.FOOD_STORED_TABLE_QUERY_IND);
	if(initialResults == null)
	{
	    return null;
	}
	String finalResults[][] = new String[initialResults.length][Constants.COL_NAMES.length];
	for(int i = 0; i<initialResults.length;i++)
	{
	//COL_NAMES= new String[]{"Id","Expired","Food Type","Container","Experation Date","Purchased Date","Location",num purchased, "Store","Price","Weight2"};	    
	//id num
	    finalResults[i][0] = initialResults[i][0];
	    finalResults[i][1] = initialResults[i][1];
	    if(useCommonName)
	    {
		//System.out.println(initialResults[i][12]);
		String name = (String)(this.idToCommonName.get(initialResults[i][12]));
		if(name == null) 
		{
		    finalResults[i][2] = initialResults[i][2];   
		}
		else
		{
		    finalResults[i][2] = name;
		}
	    }
	    else
	    {
		finalResults[i][2] = initialResults[i][2];
	    }
	    finalResults[i][3] = (String)(this.idToContainer.get(initialResults[i][3]));
	    TableDateFormatter df = new TableDateFormatter();
	    finalResults[i][4] = df.format(initialResults[i][4]); //exp. date
	    finalResults[i][5] = df.format(initialResults[i][5]);
	    finalResults[i][6] = (String)(this.idToLocation.get(initialResults[i][6]));//location
	    finalResults[i][7] = initialResults[i][7];//+" "+(String)(this.idToAmount.get(initialResults[i][8]));
	    finalResults[i][8] = (String)(this.idToStore.get(initialResults[i][8]));//store
	    finalResults[i][9] = "$" + initialResults[i][9];
	    finalResults[i][10] = initialResults[i][10]+" "+(String)(this.idToAmount.get(initialResults[i][11]));
	    
	}
     return finalResults;
	
    }
    
    /**
     * fun functions for the foodstored table
     */
    
    int foodStoredRowCount()
    {
	
	return 1;
    }
    
    /**
     * write out properties to user file
     */
    public void writeProperties()
    {
	java.util.Properties writeProps = new java.util.Properties();
	//write the db name
	writeProps.setProperty(Constants.PROP_DB_NAME, props.getProperty(Constants.PROP_DB_NAME));
	//write the number of years
	String years = props.getProperty(Constants.PROP_FOOD_PLAN_YEARS);
	if(years!=null && years.length()>0)
	{
	    writeProps.setProperty(Constants.PROP_FOOD_PLAN_YEARS,years );
	}
	//write whether or not to use expired
	String exp = props.getProperty(Constants.PROP_USE_EXPIRED);
	if(exp==null || exp.length()==0) exp = "true";
	writeProps.setProperty(Constants.PROP_USE_EXPIRED, exp);
	writeProps.setProperty(Constants.PROP_OPENED,"true");
	
	try
	{
	    
	    String fi = props.getProperty(Constants.PROP_APP_FILE);
	    java.io.File f = new java.io.File(fi);
	    java.io.FileOutputStream propFile = new java.io.FileOutputStream(f,false);
	    writeProps.store(propFile, "user info");
	    propFile.close();
	    //props.

	}
	catch(Exception e)
	{
	    e.printStackTrace(System.out);
	}
    }
    
    public void setProperty(String key, String value)
    {
	props.setProperty(key,value);
    }
    
    public boolean getBooleanProperty(String key)
    {
	String s = getProperty(key);
	if(s==null) return false;
	return Boolean.parseBoolean(s);
    }
    
    public String getProperty(String key)
    {
	return props.getProperty(key);
    }
    
    public int getNumPlanMonths()
    {
	String amt = getProperty(Constants.PROP_FOOD_PLAN_YEARS);
	if(amt == null || amt.length()==0) return 0;
	return Integer.parseInt(amt);
    }
    
    public String getToday(int numMonths)
    {
	if(numMonths==0)
	{
	    java.util.Date d = new Date();
	    return String.valueOf(d.getTime());
	}
	else
	{
	    java.util.GregorianCalendar cal = new java.util.GregorianCalendar();
	    cal.add(cal.MONTH,numMonths);
	    
	    return String.valueOf(cal.getTime().getTime());
	}
	
    }
    
    
}

class HashMapHolder
{
    String sql;
    HashMap map;
    int idIndex;
    int nameIndex;
    public HashMapHolder(String sql, HashMap map, int idIndex, int nameIndex)
    {
	this.sql = sql;
	this.map = map;
	this.idIndex = idIndex;
	this.nameIndex = nameIndex;
    }
    
    String getQuery()
    {
	return sql;
    }
    
    public void rebuildMap(FoodModel fm)
    {
	
	map.clear();    
	fm.createHashMap(sql, map, idIndex,nameIndex,false);
	
    }
    
    HashMap getMap()
    {
	return map;
    }
}

abstract class TableFormatter
{
    String format(String input)
    {
	return "";
    }
}

class TableDateFormatter extends TableFormatter
{
    java.text.SimpleDateFormat format1;
    TableDateFormatter()
    {
	format1 = new java.text.SimpleDateFormat("yyyy - MMM");
    }
    
    String format(String input)
    {
	//convert integer as string to yyyy-MMM format
	long dateNum = Long.parseLong(input);
	java.util.Date date = new java.util.Date();
	date.setTime(dateNum);
	//System.out.println("Date="+date);
	return format1.format(date);
	
    }
}
 class LookupTableFormatter extends TableFormatter
{
    HashMap lookup;
    LookupTableFormatter(HashMap map)
    {
	lookup = map;
    }
    String format(String input)
    {
	return (String)lookup.get(input);
    }
}


