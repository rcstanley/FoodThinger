/*
 * Constants.java
 *
 * Created on June 12, 2006, 10:05 PM
 */

package foodthinger;

/**
 *  Constants for the foodthinger package
 * Includes constants for the properties files
 *
 * @author RC Stanley
 */
public class Constants
{
    //
    public static String DEFALT_PROP_FILE = "defaultProps.txt";
    public static String APP_PROP_FILE="appProps.txt";
    public static String PROP_APP_FILE="appProps";
    public static String PROP_FOOD_PLAN_YEARS = "fpNumMonths";
    public static String PROP_OPENED = "openedBefore";
    public static String PROP_USE_EXPIRED = "useExpired";
    public static String PROP_ICON_URL = "icon";
    //Pantry constants
    public static String[] COL_NAMES= new String[]{"Id","Expired","Food Type","Container","Expiration Date","Purchased Date","Location","Amount","Store","Price","Weight"};
    //keys into properties
    public static String PROP_DIR = "usr";
    public static String DB_JNI_LOC = "dbJNILoc";
    public static String PROP_DB_NAME = "dbName";
    public static String HELP_URL = "helpUrl";
    public static String MAX_EXP_DATE = "MAX_EXP_DATE";
    public static int NUM_FOOD_PLAN_COL = 7;
    public static int AMOUNT_ITEM = 5;
    public static String NUMBER_TEN_CAN_ID="1";
    
    
    //queries
    public static String TREE_MODEL_CATEGORY_QUERY = "select id, category from cannery_category order by category";
    public static String TREE_MODEL_SUB_CATEGORY_QUERY = "select fdgrp_cd,fdgrp_desc from fd_group where cannery_cat=%q order by fdgrp_desc";
    public static String PANTRY_ADDER_FOODTYPE_QUERY = "select shrt_desc, ndb_no from food_des order by shrt_desc";
    public static String PANTRY_ADDER_CONTAINER_QUERY = "select type, id from containers order by type";
    public static String PANTRY_ADDER_STORE_QUERY = "select name, id from store order by name";
    public static String PANTRY_ADDR_LOC_QUERY = "select desc, id from locations order by desc";
    public static String PANTRY_ADDR_EXP_DATE_QUERY= "select num_years_min from food_des_containers where ndb_no=%q and container=%q";
    public static String FD_GROUP_QUERY = "select fdgrp_desc,fdgrp_cd from fd_group order by fdgrp_desc";
    public static String INSERT_FOOD_STORED = "insert into food_stored (food_type_id , container_id , location_id , number_purchased"
						+ " , purchase_date , experation_date , price , store , amount , amount_id ) values(%q,%q,%q,%q,%q,%q,%q,%q,%q,%q)";
    
    public static int PANTRY_ADDR_ADD_VALUE = -1;
    public static String UPDATE_FOOD_STORED = "update food_stored set food_type_id = %q , container_id = %q , location_id = %q , number_purchased = %q"
						+ " , purchase_date = %q , experation_date = %q , price = %q , store = %q , amount = %q , amount_id = %q where id = %q";
    public static String PANTRY_ADDR_FD_GROUP_QUERY= "select fdgrp_desc,fdgrp_cd from fd_group";
    public static String PANTRY_ADDR_CANNERY_CAT_QUERY = "select category, id from cannery_category";
    //0 is exp date
	//1 is weight
	//2 is weight type
	//3 is container type
    public static String PANTRY_ADDR_EXP_DATE_ANY_CONTAINER_QUERY = "select num_years_min,amount,amount_type,container from food_des_containers where ndb_no=%q order by num_years_min";
    public static String PANTRY_ADDR_COMMON_FOODS_QUERY = "select long_desc,ndb_no from common_foods order by long_desc";
    public static String PANTRY_ADDR_AMOUNTS_QUERY = "Select amount,id from amounts order by amount";
    public static String PANTRY_ADDR_EDIT_QUERY = "select food_type_id, container_id, location_id, number_purchased, purchase_date, experation_date, price, store, amount, amount_id from food_stored where id=%q";
    
    public static String SUB_CATEGORY_QUERY = "select category,id from cannery_sub_category";
    public static String SUB_CATEGORY_EXISTS_QUERY = "select category,id from cannery_sub_category where category=\"%q\"";
    public static String FOOD_DES_DESC_ONLY_QUERY = "select long_desc from food_des where ndb_no in (%q)";
    public static String FOOD_DESC_FOR_SUB_CAT_QUERY = "select long_desc from food_des where ndb_no in (select ndb_no from sub_category_to_food_des where sub_cat_id=%q )";
   /* public static String TABLE_QUERY = "Select fs.id,fd.shrt_desc,fs.expired, c.type,l.desc,s.name,a.amount,fs.amount,fs.number_purchased, " +
	    " fs.purchase_date, fs.experation_date, fs.price from food_stored as fs " +
	    " inner join (select ndb_no,shrt_desc from food_des) as fd on fs.food_type_id=fd.ndb_no " +
	    " inner join (select  id, type from containers) as c on fs.container_id=c.id "+
	    " inner join locations as l on fs.location_id=l.id "+
	    " inner join store as s on fs.store=s.id "+
	    " inner join amounts as a on fs.amount_id=a.id "+
	    " where food_type_id in (select ndb_no from food_des where fdgrp_cd in (%q))" +
	    " order by fs.expired, fd.shrt_desc, fs.experation_date";*/
   // public static String[] COL_NAMES= new String[]{"Id","Expired","Food Type","Container","Experation Date","Purchased Date","Location",num purchased, "Store","Price","Weight2"};
    public static String FOOD_STORED_TABLE_QUERY = "Select fs.id,fs.expired, fd.shrt_desc, container_id,fs.experation_date,fs.purchase_date,fs.location_id,fs.number_purchased,fs.store, " +
	    "  fs.price,fs.amount,fs.amount_id,fs.food_type_id from food_stored as fs " +
	    " inner join (select ndb_no,shrt_desc from food_des) as fd on fs.food_type_id=fd.ndb_no " ;
	    //" inner join (select  id, type from containers) as c on fs.container_id=c.id "+
	    //" inner join locations as l on fs.location_id=l.id "+
	    //" inner join store as s on fs.store=s.id "+
	   // " inner join amounts as a on fs.amount_id=a.id "+
	   // " where food_type_id in (select ndb_no from food_des where fdgrp_cd in (%q))" +
    public static String FOOD_STORED_TABLE_QUERY_WHERE =" where food_type_id in (select ndb_no from food_des where fdgrp_cd in ";
    public static String FOOD_STORED_COUNTER_QUERY = " select sum(number_purchased) from food_stored ";

    public static String FOOD_STORED_TABLE_QUERY_END = " order by fs.expired, fd.shrt_desc, fs.experation_date";
    public static int[] FOOD_STORED_TABLE_QUERY_IND = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12};
    public static String COUNT_FOOD_STORED_QUERY = "select sum(number_purchased) from food_stored";
    
    public static String UPDATE_FOOD_STORED_AMOUNT = "update food_stored set number_purchased=%q where id=%q";
    public static String DELETE_FOOD_STORED_QUERY = "delete from food_stored where id=%q";
    public static String COPY_FOOD_STORED_QUERY = "insert into food_stored (food_type_id , container_id , location_id , number_purchased"
						+ " , purchase_date , experation_date , price , store , amount , amount_id )"
						+" select food_type_id , container_id , location_id , number_purchased"
						+ " , purchase_date , experation_date , price , store , amount , amount_id from food_stored where id=%q";
    public static String INSERT_STORE = "insert into store (name) values(\"%q\")";
    public static String CHECK_STORE = "select id from store where name =\"%q\"";
    public static String CHECK_STORE_IN_FOOD_STORED = "select id from food_stored where store=%q";
    public static String DELETE_STORE = "delete from store where id =%q";
    public static String UPDATE_STORE = "update store set name=\"%q\" where id =%q";
    
    public static String INSERT_LOCATION = "insert into locations (desc) values(\"%q\")";
    public static String CHECK_LOCATION = "select id from locations where desc =\"%q\"";
    public static String CHECK_LOCATION_IN_FOOD_STORED = "select id from food_stored where location_id=%q";
    public static String DELETE_LOCATION = "delete from locations where id =%q";
    public static String UPDATE_LOCATION = "update locations set desc=\"%q\" where id =%q";
    
    public static String INSERT_CONTAINER = "insert into containers (type) values(\"%q\")";
    public static String CHECK_CONTAINER = "select id from containers where type =\"%q\"";
    public static String CHECK_CONTAINER_IN_FOOD_STORED = "select id from food_stored where container_id=%q";
    public static String DELETE_CONTAINER = "delete from containers where id =%q";
    public static String UPDATE_CONTAINER = "update containers set type=\"%q\" where id =%q";
    
    public static String INSERT_FAMILY = "insert into family (name,gender,birthday) values(\"%q\",\"%q\",%q)";
    public static String DELETE_FAMILY = "delete from family where id =%q";
    public static String SELECT_FAMILY = "select id, name, gender, birthday from family";
    
    public static String COMMON_FOOD_EXISTS = "select * from common_foods where long_desc=\"%q\"";
    public static String FOOD_EXISTS = "select * from food_des where long_desc='%q'";
    public static String CONTAINER_AMOUNT_EXISTS = "select * from food_des_containers where ndb_no=%q and container=%q";
    public static String MAX_NDB_NO = "select max(ndb_no) from food_des";
    public static String INSERT_FOOD_DES = "insert into food_des (ndb_no, fdgrp_cd, long_desc, shrt_desc) values(%q,%q,\"%q\",\"%q\")";
    public static String INSERT_COMMON_NAME = "insert into common_foods (ndb_no, long_desc) values(%q,\"%q\")";
    public static String INSERT_FOOD_DES_CONTAINER = "insert into food_des_containers (ndb_no,container,amount,amount_type,num_years_min,num_years_max) values(%q,%q,%q,%q,%q,%q)";
    
    public static String FOOD_PLAN_GET_FOOD_DES = "select fd.ndb_no,fd.long_desc,fd_group.cannery_cat,fd.fdgrp_cd from fd_group"
						   +"inner join (select ndb_no, fdgrp_cd, long_desc from food_des where ndb_no =%q)"
						   +" as fd on fd_group.fdgrp_cd = fd.fdgrp_cd";
    
    public static String FOOD_PLAN_DEFAULT_PLAN = "select cannery_category.id,d.sub_id, d.sub_category,d.amount,d.amount_id from cannery_category inner join"
    +" (select c.cannery_category_id as cannery_category_id,c.id as sub_id, "
    +" c.category as sub_category,a.amount as amount,a.amount_id as amount_id from cannery_sub_category as c inner join "
    +" (select id_no, amount, amount_id from "
    +" food_storage_amounts where type=%q) as a on c.id = a.id_no) "
    +"as d on cannery_category.id = d.cannery_category_id order "
    +" by cannery_category.id,d.cannery_category_id";
    
    public static String FOOD_IN_SUB_CAT_QUERY = "select ndb_no from sub_category_to_food_des where ndb_no in (%q)";

    //public static String SELECT_USER_FOOD_STORAGE_AMOUNTS_QUERY = "select id_no,amount,amount_id from user_food_storage_amounts";
    public static String FOOD_PLAN_USER_PLAN = "select cannery_category.id,d.sub_id, d.sub_category,d.amount,d.amount_id from cannery_category inner join"
    +" (select c.cannery_category_id as cannery_category_id,c.id as sub_id, "
    +" c.category as sub_category,a.amount as amount,a.amount_id as amount_id from cannery_sub_category as c inner join "
    +" (select id_no, amount, amount_id from "
    +" user_food_storage_amounts) as a on c.id = a.id_no) "
    +"as d on cannery_category.id = d.cannery_category_id order "
    +" by cannery_category.id,d.cannery_category_id";   
    
    public static String BEGIN_FOOD_PLAN_TRANSACTION = " begin transaction food_plan";
    public static String DELETE_USER_FOOD_STORAGE_AMOUNTS = "delete from user_food_storage_amounts"; //deletes all rows from user food storage amounts
    public static String DROP_USER_FOOD_STORAGE_AMOUNTS = "drop table user_food_storage_amounts";
    public static String CREATE_USER_FOOD_STORAGE_AMOUNTS = "create table user_food_storage_amounts(id integer primary key autoincrement, id_no integer, amount real, amount_id integer)";
    public static String INSERT_USER_FOOD_STORAGE_AMOUNTS = "insert into user_food_storage_amounts (id_no,amount,amount_id) values(%q,%q,%q)";
    public static String INSERT_CANNERY_SUB_CATEGORY = "insert into cannery_sub_category (category, cannery_category_id) values(\"%q\",%q)";
    public static String SELECT_CANNERY_SUB_CATEGORY_FOR_NAME = "select id from cannery_sub_category where category=\"%q\" and cannery_category_id=%q";
    public static String INSERT_SUB_CATEGORY_TO_FOOD_DES = "insert into sub_category_to_food_des (sub_cat_id, ndb_no) values(%q,%q)";
    public static String END_FOOD_PLAN_TRANSACTION = "end transaction food_plan; commit transaction food_plan;";
    
//table names
    public static String TABLE_FOOD_STORED = "food_stored";
    public static String TABLE_FOOD_DES = "food_des";
    public static String TABLE_FD_GROUP = "fd_group";
    public static String TABLE_WEIGHT = "weight";
    public static String TABLE_CONTAINERS = "containers";
    public static String TABLE_LOCATIONS = "locations";
    public static String TABLE_STORE = "store";
    public static String TABLE_CANNERY_CATEGOREY = "cannery_category";
    public static String TABLE_FOOD_DES_CONTAINERS = "food_des_containers";
    public static String TABLE_AMOUNTS = "amounts";
    public static String TABLE_COMMON_FOODS = "common_foods";
    public static String TABLE_FAMILY = "family";
    public static String TABLE_USER_FOOD_STORAGE_AMOUNTS = "user_food_storage_amounts";
    public static String TABLE_CANNERY_SUB_CATEGORY = "cannery_sub_category";
    public static String TABLE_SUB_CATEGORY_TO_FOOD_DES = "sub_category_to_food_des";
    
    
    public static String []TABLES ={TABLE_FOOD_STORED, TABLE_FOOD_DES,TABLE_FD_GROUP,
	TABLE_WEIGHT, TABLE_CONTAINERS, TABLE_LOCATIONS, TABLE_STORE, TABLE_CANNERY_CATEGOREY,
	TABLE_FOOD_DES_CONTAINERS, TABLE_AMOUNTS, TABLE_COMMON_FOODS, TABLE_FAMILY};
    
    public static String ACTCOM_PANTRY_ADD="pantryadd";
    public static String ACTCOM_PANTRY_COPY="pantrycopy";
    public static String ACTCOM_PANTRY_EDIT="pantryedit";
    public static String ACTCOM_PANTRY_DELETE="pantrydelete";
    public static String ACTCOM_PANTRY_DEC="pantrydecrement";
    
    public static String ERROR_DELETING = "Error Deleting ";
    public static String ERROR_DELETING_INUSE = " items(s) use this item.  Rename it instead";
    public static String SUCCESS_DELETING = "Deleted Item(s) ";
    public static String ERROR_INSERTING = "Error Copying ";
    public static String SUCCESS_INSERTING = "Copied Item(s) ";
    public static String ERROR_DECREMENTING = "Error Decrementing ";
    public static String SUCCESS_DECREMENTING = "Decremented Item(s) ";
    public static String ERROR_INSERTING_REAL = "Error Inserting ";
    public static String SUCCESS_INSERTING_REAL = "Inserted Item(s) ";
    public static String ERROR_UPDATE = "Error Renaming ";
    public static String SUCCESS_UPDATE = "Renamed Item(s) ";
    public static String ERROR = "Error";
    public static String ERROR_ADDING = "Error: You need to enter some text before adding a new item.";
    public static String ERROR_ADDING_EXISTS = " already exists.  Please choose another name." ;
    public static String ERROR_ADDING_FAMILY = "Error: Adding Family Member.  The format is mm/dd/yyyy";
    public static String ERROR_DELETING_FAMILY = "Error: could not delete family member.";
    public static String DEFAULT_HINT = "Most foods are good for about 1 year. Check package for date.";
    public static String ERROR_LOCAL_ADD_CONTAINER = "Error: Please enter an amount.";
    public static String ERROR_LOCAL_ADD_CONTAINER_EXISTS = "Error adding container/amount: the container is already in the table.";
    public static String SUCCESS_ADD_NEW_FOOD = "New food added:";
    public static String ERROR_ADD_NEW_FOOD = "Error adding new food";
    public static String ERROR_ADDING_NEW_FOOD_NO_NAME = ": make sure there is a name for the food or item.";
    public static String ERROR_ADD_NEW_FOOD_FOOD_EXISTS = ": the food name already exists.";
    public static String ERROR_ADD_NEW_FOOD_COMMON_EXISTS = ": The common name already exists.";
    
    public static String ERROR_EDITING_FOODAMOUNT = "Error: the amount you entered is not correct.";
    public static String ERROR_ADDING_NEW_ITEM_FOOD_PLAN = "Error adding the new item to the food plan.";
    public static String ERROR_ADDING_NEW_ITEM_FOOD_PLAN_BAD_NUMBER = "The amount must be a number greater than 0.";
    public static String ERROR_ADDING_NEW_ITEM_FOOD_PLAN_NOT_ADDED = "The item was not added.";
    
    public static String SUCCESS_CREATING_FOOD_PLAN = "The food plan has been created.";
    
    public static String ERROR_CHANGING_FOODPLAN = "Error adding this food item.  Continue or quit?";
    
    public static String REPORT_QUERY_ERROR = "Unable to process query: ";
    public static String REPORT_QUERY_SUCCESS = "Query processed. ";
    public static String INCREMENT_AMOUNT = "Do you want to change the amount for %q from %q to %q? ";
    public static String MONTHS_CHANGED = "The number of months for your plan has been changed from %q to %q. ";
    public static String CHANGE_IGNORED = "The change will be ignored.";
    public static String MONTHS_DO = "Do you want to change the number of months? ";
    
    public static String FOOD_PLAN_COL[] = {"Category","Sub Category","Recommended Amount","","User Amount",""};
    public static int FOOD_PLAN_MAN=1;
    public static int FOOD_PLAN_WOMAN=2;
    public static int FOOD_PLAN_CHILD=3;
    public static int FOOD_PLAN_FAMILY=4;
    public static int FOOD_PLAN_DEFAULT_NUM_MONTHS = 12;
    public static String FAMILY_MALE = "Male";
    public static String FAMILY_FEMALE = "Female";
    
    public static int REPORT_GENERAL_SIZE = 5;
    public static String REPORT_GENERAL_USER_AMOUNTS = "select id_no,amount,amount_id from user_food_storage_amounts where id_no in"+
	    " (select id from cannery_sub_category where cannery_category_id=%q) ";
    //select id_no,amount,amount_id from user_food_storage_amounts where id_no in (select id from cannery_sub_category where cannery_category_id=1) ";
    public static String REPORT_GENERAL_FOODS = "select number_purchased,amount, amount_id from food_stored where  food_type_id in" //amount_id=%q and
	    +" (select ndb_no from sub_category_to_food_des where sub_cat_id=%q)";
    public static String REPORT_GENERAL_FOODS_WHERE = " and (expired!=1)";
    //select number_purchased,amount, amount_id from food_stored where amount_id=2 and food_type_id in(select ndb_no from sub_category_to_food_des where sub_cat_id=1);
    public static Object[] REPORT_TOTAL_COL_NAMES = {"Sub category","Goal Amount","Amount","Percent Complete"};
    public static Object[] REPORT_REMAINING_COL_NAMES = {"Category","Sub category","Amount Remaining","#10 cans (approx)"};
    public static String START_BOLD = "<html><b>";
    public static String END_BOLD = "</b></html>";
    public static String UPDATE_EXPIRED_QUERY="update food_stored set expired=1 where experation_date<%q";
    public static String SELECT_EXPIRED_WHERE_QUERY = " where experation_date<%q ";
    public static String IGNORE_EXPIRED_QUERY = " and expired!=1";
    public static String NUMBER_TEN_CAN_WEIGHT_QUERY = "select amount, container from food_des_containers where amount_type=%q and container=%q"+
							" and ndb_no in (select ndb_no from sub_category_to_food_des where sub_cat_id=%q)";
    public static String CONVERSION_QUERY = "select id_start,id_end, conversion_factor from amount_conversions";
    
    public static String GRAPH_TITLE="Goals";
    public static String GRAPH_NUMBER_TITLE = "Number of Months";
    public static String GRAPH_LABELS_TITLE = "SubCategory";
    public static String ABOUT_TITLE="About FoodThinger";
    public static String ABOUT_MESSAGE="<html><h2>FoodThinger</h2><br>Version .91<Br>Written by R.C. Stanley<Br><i>(c) 2006/2008</i></html>";
    
    public static String ADD_FOOD_ADD_TITLE = "Add an Item to the Pantry";
    public static String ADD_FOOD_UPDATE_TITLE = "Update an Item in the Pantry";
    /** Creates a new instance of Constants */
    public Constants()
    {
    }
    
}
