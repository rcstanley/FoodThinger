/*
 * FoodPlanInitJDialog.java
 *
 * Created on September 11, 2006, 9:20 PM
 */

package foodthinger;

import javax.swing.table.*;
import javax.swing.JTable;

/**
 *
 * @author  RC Stanley
 */
public class FoodPlanInitJDialog extends javax.swing.JDialog
{
    FoodModel fm;
    FoodPlanTableModel foodPlanTableModel;
    FoodPlanItemSummaryJPanel summary;
    FoodPlanSubCatFoodsTableModel foodPlanSubCatFoodsTableModel;
    java.awt.CardLayout cardLayout;
    /** Creates new form FoodPlanInitJDialog */
    public FoodPlanInitJDialog(java.awt.Frame parent, boolean modal, FoodModel fm)
    {
	super(parent, modal);
	this.fm = fm;
	initComponents();
	//System.out.println(getContentPane().getLayout());
	cardLayout = (java.awt.CardLayout)getContentPane().getLayout();
    }
    
   /*protected DefaultTableColumnModel createHeader()
    {
	//DefaultTableColumnModel header = new DefalutTableColumnModel();
	/*for(int i = 0; i<5;i ++)
	{
	    DefaultTableColumn dtc = new DefaultTableColumn()
	    header.addColumn(dtc));
	}
	
    }*/
   
   protected void changeColumns(TableColumnModel cm)
    {
	TableColumn header = cm.getColumn(5);
	javax.swing.JComboBox jcombo = new javax.swing.JComboBox(fm.getComboModelForQuery(Constants.PANTRY_ADDR_AMOUNTS_QUERY,0,1));
	header.setCellEditor(new javax.swing.DefaultCellEditor(jcombo));
	
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanelBuildOptions = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jList1 = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jRadioButtonNewPlan = new javax.swing.JRadioButton();
        jRadioButtonEditOld = new javax.swing.JRadioButton();
        jRadioButtonUpdateIncrease = new javax.swing.JRadioButton();
        jButtonCreate = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanelUserSetChoices = new javax.swing.JPanel();
        jPanelAddDifferentFood = new javax.swing.JPanel();
        jScrollPaneItem = new javax.swing.JScrollPane();
        jListItem = new javax.swing.JList();
        jTextFieldSubCategoryName = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jTextFieldAmount = new javax.swing.JTextField();
        jComboBoxAmount = new javax.swing.JComboBox();
        jButtonAddToPlan = new javax.swing.JButton();
        jLabelSubCategoryName = new javax.swing.JLabel();
        jLabelAmount = new javax.swing.JLabel();
        jButtonUSCFinish = new javax.swing.JButton();
        jButtonUSCBack = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPaneFoods = new javax.swing.JScrollPane();
        jTableFoods = new javax.swing.JTable();
        jScrollPaneSummary = new javax.swing.JScrollPane();

        getContentPane().setLayout(new java.awt.CardLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Generate New Food Storage Goals");
        setFont(new java.awt.Font("Agency FB", 0, 10));
        setForeground(java.awt.Color.white);
        setModal(true);
        jPanelBuildOptions.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Number of Months to Plan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelBuildOptions.add(jLabel1, gridBagConstraints);

        jList1.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "3", "6", "9", "12" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.setSelectedIndex(0);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        jPanelBuildOptions.add(jList1, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        buttonGroup1.add(jRadioButtonNewPlan);
        jRadioButtonNewPlan.setSelected(true);
        jRadioButtonNewPlan.setText("New Plan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jRadioButtonNewPlan, gridBagConstraints);

        buttonGroup1.add(jRadioButtonEditOld);
        jRadioButtonEditOld.setText("Edit Old Plan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jRadioButtonEditOld, gridBagConstraints);

        buttonGroup1.add(jRadioButtonUpdateIncrease);
        jRadioButtonUpdateIncrease.setText("Update Plan, increase what ever needs to be increased");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jRadioButtonUpdateIncrease, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelBuildOptions.add(jPanel1, gridBagConstraints);

        jButtonCreate.setText("Next");
        jButtonCreate.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonCreateActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelBuildOptions.add(jButtonCreate, gridBagConstraints);

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonCancelActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelBuildOptions.add(jButtonCancel, gridBagConstraints);

        jLabel2.setText("Planning For:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelBuildOptions.add(jLabel2, gridBagConstraints);

        getContentPane().add(jPanelBuildOptions, "card2");

        jPanelUserSetChoices.setLayout(new java.awt.GridBagLayout());

        jPanelAddDifferentFood.setLayout(new java.awt.GridBagLayout());

        jPanelAddDifferentFood.setBorder(new javax.swing.border.TitledBorder("Add an Item"));
        jPanelAddDifferentFood.setMinimumSize(new java.awt.Dimension(101, 200));
        jPanelAddDifferentFood.setPreferredSize(new java.awt.Dimension(402, 200));
        jListItem.setModel(fm.getComboModelForQuery(foodthinger.Constants.PANTRY_ADDER_FOODTYPE_QUERY,0,1));
        jListItem.setVisibleRowCount(5);
        jScrollPaneItem.setViewportView(jListItem);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAddDifferentFood.add(jScrollPaneItem, gridBagConstraints);

        jTextFieldSubCategoryName.setMinimumSize(new java.awt.Dimension(30, 20));
        jTextFieldSubCategoryName.setPreferredSize(new java.awt.Dimension(30, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAddDifferentFood.add(jTextFieldSubCategoryName, gridBagConstraints);

        jComboBox1.setModel(fm.getComboModelForQuery(foodthinger.Constants.TREE_MODEL_CATEGORY_QUERY,1,0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 0);
        jPanelAddDifferentFood.add(jComboBox1, gridBagConstraints);

        jTextFieldAmount.setMinimumSize(new java.awt.Dimension(30, 20));
        jTextFieldAmount.setPreferredSize(new java.awt.Dimension(30, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAddDifferentFood.add(jTextFieldAmount, gridBagConstraints);

        jComboBoxAmount.setModel(fm.getComboModelForQuery(Constants.PANTRY_ADDR_AMOUNTS_QUERY,0,1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        jPanelAddDifferentFood.add(jComboBoxAmount, gridBagConstraints);

        jButtonAddToPlan.setText("Add to Plan");
        jButtonAddToPlan.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonAddToPlanActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAddDifferentFood.add(jButtonAddToPlan, gridBagConstraints);

        jLabelSubCategoryName.setText("Subcategory Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAddDifferentFood.add(jLabelSubCategoryName, gridBagConstraints);

        jLabelAmount.setText("Amount:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAddDifferentFood.add(jLabelAmount, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 0.5;
        jPanelUserSetChoices.add(jPanelAddDifferentFood, gridBagConstraints);

        jButtonUSCFinish.setText("Finish");
        jButtonUSCFinish.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonUSCFinishActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 3, 0);
        jPanelUserSetChoices.add(jButtonUSCFinish, gridBagConstraints);

        jButtonUSCBack.setText("Back");
        jButtonUSCBack.setEnabled(false);
        jButtonUSCBack.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonUSCBackActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 10, 3, 0);
        jPanelUserSetChoices.add(jButtonUSCBack, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(new javax.swing.border.TitledBorder("Plan Amounts"));
        jSplitPane1.setResizeWeight(0.5);
        foodPlanTableModel = new FoodPlanTableModel(fm);
        TableSorter sorter = new TableSorter(foodPlanTableModel);
        jTable1.setModel(sorter);
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        sorter.setTableHeader(jTable1.getTableHeader());
        changeColumns(this.jTable1.getColumnModel());
        summary = new FoodPlanItemSummaryJPanel(this.foodPlanTableModel.getData(), this.fm.idToCanneryCat, this.fm.idToAmount) ;
        //jPanelUserSetChoices.add(summary, new java.awt.GridBagConstraints());

        jScrollPane1.setViewportView(jTable1);

        jSplitPane1.setLeftComponent(jScrollPane1);

        foodPlanSubCatFoodsTableModel = new FoodPlanSubCatFoodsTableModel(fm, this.jTable1);
        jTableFoods.setModel(foodPlanSubCatFoodsTableModel);
        this.jTable1.getSelectionModel().addListSelectionListener(foodPlanSubCatFoodsTableModel);
        jScrollPaneFoods.setViewportView(jTableFoods);

        jSplitPane1.setRightComponent(jScrollPaneFoods);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jSplitPane1, gridBagConstraints);

        jScrollPaneSummary.setMinimumSize(new java.awt.Dimension(200, 100));
        jScrollPaneSummary.setPreferredSize(new java.awt.Dimension(200, 100));
        jScrollPaneSummary.setViewportView(summary);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        jPanel2.add(jScrollPaneSummary, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelUserSetChoices.add(jPanel2, gridBagConstraints);

        getContentPane().add(jPanelUserSetChoices, "card3");

        pack();
    }
    // </editor-fold>//GEN-END:initComponents
    /**
     * put the food items and their amounts in the db
     */
    private void jButtonUSCFinishActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonUSCFinishActionPerformed
    {//GEN-HEADEREND:event_jButtonUSCFinishActionPerformed
	//clear out the table
	if(foodPlanTableModel.commit()) 
	{
	    javax.swing.JOptionPane.showMessageDialog(this,Constants.SUCCESS_CREATING_FOOD_PLAN);
	    this.dispose();
	}
	
    }//GEN-LAST:event_jButtonUSCFinishActionPerformed

    int [] getFoodIds()
    {
	Object o[] = this.jListItem.getSelectedValues();
	if(o == null) return null;
	int[] ids = new int[o.length];
	for(int i = 0; i < o.length;i++)
	{
	    ids[i] = ((FoodTreeNode)o[i]).getId();
	}
	return ids;
    }
    
    /**
     * returns true if the ids or subcat already exist
     * returns false else
     */   
    boolean infoInNew(int ids[], String subCat)
    {
	java.util.Vector v = foodPlanTableModel.getNewItems();
	NewFoodPlanItem nfpi;
	for(int i = 0; i<v.size(); i++)
	{
	    nfpi = (NewFoodPlanItem)v.get(i);
	    if(nfpi.foodName.equals(subCat))
	    {
		return true;
	    }
	    if(nfpi.containsNdbNo(ids))
	    {
		return true;
	    }
	}
	return false;
    }
    /**
     * add the user added item to the food plan
     */
    private void jButtonAddToPlanActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAddToPlanActionPerformed
    {//GEN-HEADEREND:event_jButtonAddToPlanActionPerformed
	//add the selected food to the plan for the user
	int foodIds[] = getFoodIds();
	String subCat = this.jTextFieldSubCategoryName.getText();
	int canneryCat = ((FoodTreeNode)this.jComboBox1.getSelectedItem()).getId();
	int unitId =((FoodTreeNode)this.jComboBoxAmount.getSelectedItem()).getId(); 
	String amount = this.jTextFieldAmount.getText();
	String error = "";
	//see if foodId already exists
	if(foodPlanTableModel.foodExists(foodIds))
	{
	    error = "food exists";
	}
	if(foodIds == null || foodIds.length<0)
	{
	    error = Constants.ERROR_ADDING_NEW_ITEM_FOOD_PLAN_BAD_NUMBER + Constants.ERROR_ADDING_NEW_ITEM_FOOD_PLAN_NOT_ADDED;
	}
	subCat = subCat.trim();
	//make sure sub category doesn't already exist
	if(subCat.length()==0 || fm.exists(Constants.SUB_CATEGORY_EXISTS_QUERY, new String[]{subCat},0) > 0)
	{
	    error = "category exists";
	}
	if(infoInNew(foodIds, subCat))
	{
	    error = "category exists";
	}
	float amt=(float)0.0;
	try
	{
	    amt = Float.parseFloat(amount);
	    if(amt < 0.0)
	    {
		error = Constants.ERROR_ADDING_NEW_ITEM_FOOD_PLAN_BAD_NUMBER + Constants.ERROR_ADDING_NEW_ITEM_FOOD_PLAN_NOT_ADDED;
	    }
	}
	catch(Exception e)
	{
	    error = Constants.ERROR_ADDING_NEW_ITEM_FOOD_PLAN_BAD_NUMBER + Constants.ERROR_ADDING_NEW_ITEM_FOOD_PLAN_NOT_ADDED;
	}
	if(error.length()>0)
	{
	    javax.swing.JOptionPane.showMessageDialog(this,Constants.ERROR_ADDING_NEW_ITEM_FOOD_PLAN + error,Constants.ERROR,javax.swing.JOptionPane.ERROR_MESSAGE);
	    return;
	}
	else
	{
	    //get the info about the food from the db
	    /*String[][]results = fm.getStringsFromQuery(Constants.FOOD_PLAN_GET_FOOD_DES,new String[]{String.valueOf(foodId)},new int[]{0,1,2,3});
	     //create the foodplanitem
	    if(results.length==0)
	    {
		javax.swing.JOptionPane.showMessageDialog(this,Constants.ERROR_ADDING_NEW_ITEM_FOOD_PLAN + "Unexpected Error",Constants.ERROR,javax.swing.JOptionPane.ERROR_MESSAGE);
		return;
	    }*/
	     //add it to the list
	    FoodPlanItem f = this.foodPlanTableModel.addNewItem(foodIds,subCat,canneryCat,amt, unitId);
	    this.summary.addItem(f);
	    
	}
	
    }//GEN-LAST:event_jButtonAddToPlanActionPerformed

    private void jButtonUSCBackActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonUSCBackActionPerformed
    {//GEN-HEADEREND:event_jButtonUSCBackActionPerformed
	cardLayout.previous(this.getContentPane());
    }//GEN-LAST:event_jButtonUSCBackActionPerformed

    private String monthsChanged(String start,String end, int orig, int box)
    {
	start = start.replaceFirst("%q", String.valueOf(orig));
	start = start.replaceFirst("%q", String.valueOf(box));
	return start + end;
    }
    
    private void jButtonCreateActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCreateActionPerformed
    {//GEN-HEADEREND:event_jButtonCreateActionPerformed
	boolean newPlan = true;
	boolean keepUserValues = false;
	boolean updateUserValues = false;
	int numMonths =Integer.parseInt(((String)this.jList1.getSelectedValue()));
	float factor = 1;
	//see how they want to do the plan
	if(this.jRadioButtonNewPlan.isSelected())
	{
	    newPlan = true;
	    keepUserValues = false;
	    updateUserValues = false;
	}
	else if (this.jRadioButtonEditOld.isSelected())
	{
	    newPlan = false;
	    keepUserValues = true;
	    updateUserValues = false;
	    String mon = fm.getProperty(Constants.PROP_FOOD_PLAN_YEARS);
	    if(mon!=null && mon.length()>0)
	    {
		int num = Integer.parseInt(mon);
		javax.swing.JOptionPane.showMessageDialog(this,monthsChanged(new String(Constants.MONTHS_CHANGED),Constants.CHANGE_IGNORED,num, numMonths));
		numMonths = num;
	    }
	}
	else if(this.jRadioButtonUpdateIncrease.isSelected())
	{
	    newPlan = false;
	    keepUserValues = true;
	    updateUserValues = true;
	    String mon = fm.getProperty(Constants.PROP_FOOD_PLAN_YEARS);
	    //find out many months the original plan  was for and multiply 
	    //figure out the factor to multiply it by for the new number of months
	    if(mon!=null && mon.length()>0)
	    {
		int num = Integer.parseInt(mon);
		factor = (float)numMonths/(float)num;
		int ans = javax.swing.JOptionPane.showConfirmDialog(this,monthsChanged(new String(Constants.MONTHS_CHANGED),Constants.MONTHS_DO,num, numMonths));
		//if yes, then change the number of months, if no, go with old plan
		if(ans ==javax.swing.JOptionPane.YES_OPTION)
		{
		    numMonths = num;
		}
		
	    }
	}
	
	//generate the default plan
	foodPlanTableModel.generateDefaultFoodPlan(newPlan, keepUserValues, updateUserValues,numMonths,factor); 
	summary.createSummaries(foodPlanTableModel.getData(), numMonths);
	//generate the summaries
	
	cardLayout.next(this.getContentPane());
	
    }//GEN-LAST:event_jButtonCreateActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCancelActionPerformed
    {//GEN-HEADEREND:event_jButtonCancelActionPerformed
	foodPlanTableModel.clear();
	this.dispose();
	//java.awt.CardLayout card= (java.awt.CardLayout)this.getLayout();
	//card.previous(this);
    }//GEN-LAST:event_jButtonCancelActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
	java.awt.EventQueue.invokeLater(new Runnable()
	{
	    public void run()
	    {
		//new FoodPlanInitJDialog(new javax.swing.JFrame(), true).setVisible(true);
	    }
	});
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonAddToPlan;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonCreate;
    private javax.swing.JButton jButtonUSCBack;
    private javax.swing.JButton jButtonUSCFinish;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBoxAmount;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelAmount;
    private javax.swing.JLabel jLabelSubCategoryName;
    private javax.swing.JList jList1;
    private javax.swing.JList jListItem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelAddDifferentFood;
    private javax.swing.JPanel jPanelBuildOptions;
    private javax.swing.JPanel jPanelUserSetChoices;
    private javax.swing.JRadioButton jRadioButtonEditOld;
    private javax.swing.JRadioButton jRadioButtonNewPlan;
    private javax.swing.JRadioButton jRadioButtonUpdateIncrease;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneFoods;
    private javax.swing.JScrollPane jScrollPaneItem;
    private javax.swing.JScrollPane jScrollPaneSummary;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableFoods;
    private javax.swing.JTextField jTextFieldAmount;
    private javax.swing.JTextField jTextFieldSubCategoryName;
    // End of variables declaration//GEN-END:variables
    
}

