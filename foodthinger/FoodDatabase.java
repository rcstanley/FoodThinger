/*
 * database.java
 *
 * Created on June 11, 2006, 9:12 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */


package foodthinger;

import SQLite.*;

import java.sql.* ;

/**
 * Initializes and controls the database access to SQLite
 * @author Rachel
 */
public class FoodDatabase
{
    //create table w/ java classes then add using sqlite3
    private SQLite.Database db;
    /** Creates a new instance of database */
    public FoodDatabase(String dbUrl, String dbName) throws SQLite.Exception
    {
	/*try{
	    // Load the Driver
		 Class.forName ("SQLite.JDBCDriver");
	    // Make Connection
		 Connection c = DriverManager.getConnection("_jdbc:sqlite://dbfile");
	      } catch (java.lang.Exception e) {
		 e.printStackTrace();
		 System.exit(1);
	 }*/

	db = new SQLite.Database(dbUrl);//"C:/Documents and Settings/Rachel/My Documents/project/sqlitejar/dist/sqlite_jni.dll");
	//db = new SQLite.Database();//"C:/Documents and Settings/Rachel/My Documents/project/sqlitejar/dist/sqlite_jni.dll");
	db.open(dbName, 0666);
	System.out.println("dbname="+dbName);
	//db.get_table("Select * from weight");
	//System.out.println("LIB version: " + SQLite.Database.version());
	//db.exec("create table fd_group (fdgrp_cd integer,fdgrp_desc text)",null);
	//TableResult ts = db.get_table("Select * from weight");
	//System.out.println(ts.toString());
	//db.close();
	/*try {
	    db.open("db", 0666);
	    System.out.println("DB version: " + db.dbversion());
	    db.interrupt();
	    db.busy_timeout(1000);
	    db.busy_handler(null);
	   /* db.create_function("myregfunc", -1, new test());
	    db.function_type("myregfunc", Constants.SQLITE_TEXT);
	    db.create_aggregate("myaggfunc", 1, new test());
	    db.function_type("myaggfunc", Constants.SQLITE_TEXT);
	    db.exec("PRAGMA show_datatypes = on", null);
	    System.out.println("==== local callback ====");
	    db.exec("select * from sqlite_master", new test());
	    System.out.println("==== get_table ====");
	    System.out.print(db.get_table("select * from TEST"));
	    System.out.println("==== get_table w/ args ====");
	    String qargs[] = new String[1];
	    qargs[0] = "tab%";
	    System.out.print(db.get_table("select * from sqlite_master where type like '%q'", qargs));
	    System.out.println("==== call regular function ====");
	    db.exec("select myregfunc(TEST.firstname) from TEST", new test());
	    System.out.println("==== call aggregate function ====");
	    db.exec("select myaggfunc(TEST.firstname) from TEST", new test());
	    System.out.println("==== compile/step interface ====");
	    test cb = new test();
	    db.set_authorizer(cb);
	    db.trace(cb);
	    Vm vm = db.compile("select * from TEST; " +
			       "delete from TEST where id = 5; " +
			       "insert into TEST values(5, 'Speedy', 'Gonzales'); " +
			       "select * from TEST");
	    int stmt = 0;
	    do {
		++stmt;
		if (stmt > 3) {
		    System.out.println("setting progress handler");
		    db.progress_handler(3, cb);
		}
		System.out.println("---- STMT #" + stmt + " ----");
		while (vm.step(cb)) {
		}
	    } while (vm.compile());
	    db.close();
	    System.out.println("An exception is expected from now on.");
	    System.out.println("==== local callback ====");
	   // db.exec("select * from sqlite_master", new test());
	} catch (java.lang.Exception e) {
	    System.err.println("error: " + e);
	} finally {
	    try {
		System.err.println("cleaning up ...");
		db.close();
	    } catch(java.lang.Exception e) {
		System.err.println("error: " + e);
	    } finally {
		System.err.println("done.");
	    }
	}*/
	
	
    }
    /**
     *Returns a list of results for a sql statement
     *@param sql an sql statement that you expect a result from
     */
    public TableResult getTable(String sql) throws SQLite.Exception
    {
	return db.get_table(sql);
    }
    
    /**
     *Returns a list of results for a sql statement
     *@param sql an sql statement that you expect a result from
     */
    public TableResult getTable(String sql, String[]args) throws SQLite.Exception
    {
	return db.get_table(sql,args);
    }
    
    public String insertQuery(String sql, String[] args)
    {
	try
	{
	    db.exec(sql,null,args);
	}
	catch(SQLite.Exception e)
	{
	    return e.toString();
	}
	return null;
    }
    
     public String execute(String sql)
    {
	try
	{
	    db.exec(sql,null);
	}
	catch(SQLite.Exception e)
	{
	    return e.toString();
	}
	return null;
    }
    
    public String execute(String sql, String[] args)
    {
	try
	{
	    db.exec(sql,null,args);
	}
	catch(SQLite.Exception e)
	{
	    return e.toString();
	}
	return null;
    }
    
        
    protected void finalize()
    {
	try
	{
	   db.close(); 
	}
	catch(SQLite.Exception e)
	{
	    System.out.println(e);
	}
	
    }
    
}
