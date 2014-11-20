/*
 * FoodTreeNode.java
 *
 * Created on June 16, 2006, 1:43 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package foodthinger;

/**
 * a node in a tree
 * @author RC Stanley
 */
public class FoodTreeNode
{
    String category;
    int id;
    /** Creates a new instance of FoodTreeNode */
    public FoodTreeNode(String category, int id)
    {
	this.category = category;
	this.id = id;
    }
    
    public String getCategory()
    {
	return category;
    }
    
    public int getId()
    {
	return id;
    }
    
    public String toString()
    {
	return category;
    }
    
}
