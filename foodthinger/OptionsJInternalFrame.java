/*
 * OptionsJInternalFrame.java
 *
 * Created on July 29, 2006, 9:24 PM
 */

package foodthinger;

/**
 *
 * @author  RC Stanley
 */
public class OptionsJInternalFrame extends javax.swing.JInternalFrame
{
    FoodModel fm;
    /** Creates new form OptionsJInternalFrame */
    public OptionsJInternalFrame(FoodModel fm)
    {
	this.fm = fm;
	initComponents();
	this.jTabbedPanel.setSelectedComponent(this.jPanelFamily);
	this.jCheckBoxUseExpired.setSelected(fm.getBooleanProperty(Constants.PROP_USE_EXPIRED));
    }
    
    //clears out the weight table
    public void clearWeightTable()
    {
	javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel)this.jTableWeights.getModel();
	model.setRowCount(0);
	this.jTableWeights.setPreferredSize(new java.awt.Dimension((int)jTableWeights.getPreferredSize().getWidth(),10));

    }
    
    //clear out the info in the add new food dialogue
    void clearAddNewFood()
    {
	this.jTextFieldAmount.setText("");
	this.jTextFieldCommon.setText("");
	this.jTextFieldAddFoodName.setText("");
	this.jSpinnerYears.setValue(new Double(1));

	//get rid of all the rows
	clearWeightTable();
    }
    
    OptionJPanel storePanel;
    OptionJPanel locationPanel;
    OptionJPanel containerPanel;
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        jTabbedPanel = new javax.swing.JTabbedPane();
        jPanelFamily = new javax.swing.JPanel();
        jLabelName = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jLabelGender = new javax.swing.JLabel();
        jComboBoxGender = new javax.swing.JComboBox();
        jLabelBirthday = new javax.swing.JLabel();
        jTextFieldBirthday = new javax.swing.JTextField();
        jButtonAdd = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTable1 = new javax.swing.JTable();
        jButtonCreateFoodPlan = new javax.swing.JButton();
        jLabelDeleteFamily = new javax.swing.JLabel();
        jTextFieldDeleteFamily = new javax.swing.JTextField();
        jButtonDeleteFamily = new javax.swing.JButton();
        jPanelGeneral = new javax.swing.JPanel();
        jCheckBoxUseExpired = new javax.swing.JCheckBox();
        jPanelCannery = new javax.swing.JPanel();
        jPanelWeight = new javax.swing.JPanel();
        jPanelFood = new javax.swing.JPanel();
        jLabelFoodName = new javax.swing.JLabel();
        jTextFieldAddFoodName = new javax.swing.JTextField();
        jLabelCommon = new javax.swing.JLabel();
        jTextFieldCommon = new javax.swing.JTextField();
        jButtonAddNewFoodOK = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jComboBoxContainer = new javax.swing.JComboBox();
        jTextFieldAmount = new javax.swing.JTextField();
        jComboBoxAmount = new javax.swing.JComboBox();
        jLabelExpires = new javax.swing.JLabel();
        jSpinnerYears = new javax.swing.JSpinner();
        jButtonAddContainer2Weight = new javax.swing.JButton();
        jButtonAddNewFoodClear = new javax.swing.JButton();
        jTableWeights = new javax.swing.JTable();
        jComboBoxAddNewFoodCategory = new javax.swing.JComboBox();
        jLabelAddNewFoodCategory = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Properties");
        jTabbedPanel.setName("General");
        jTabbedPanel.setNextFocusableComponent(jPanelFamily);
        storePanel = new OptionJPanel(fm, Constants.TABLE_STORE,Constants.CHECK_STORE,
            Constants.DELETE_STORE, Constants.INSERT_STORE, Constants.UPDATE_STORE, Constants.CHECK_STORE_IN_FOOD_STORED, Constants.PANTRY_ADDER_STORE_QUERY, 0, 1);
        jTabbedPanel.addTab("Store",storePanel);

        locationPanel = new OptionJPanel(fm, Constants.TABLE_LOCATIONS,Constants.CHECK_LOCATION,
            Constants.DELETE_LOCATION, Constants.INSERT_LOCATION, Constants.UPDATE_LOCATION, Constants.CHECK_LOCATION_IN_FOOD_STORED, Constants.PANTRY_ADDR_LOC_QUERY, 0, 1);
        jTabbedPanel.addTab("Locations",locationPanel);

        containerPanel = new OptionJPanel(fm, Constants.TABLE_CONTAINERS,Constants.CHECK_CONTAINER,
            Constants.DELETE_CONTAINER, Constants.INSERT_CONTAINER, Constants.UPDATE_CONTAINER, Constants.CHECK_CONTAINER_IN_FOOD_STORED, Constants.PANTRY_ADDER_CONTAINER_QUERY, 0, 1);
        jTabbedPanel.addTab("Containers",containerPanel);

        jPanelFamily.setLayout(new java.awt.GridBagLayout());

        jLabelName.setLabelFor(jTextFieldName);
        jLabelName.setText("Name:");
        jLabelName.setToolTipText("Name of the Person (ex. Dad)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelFamily.add(jLabelName, gridBagConstraints);

        jTextFieldName.setMinimumSize(new java.awt.Dimension(50, 20));
        jTextFieldName.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        jPanelFamily.add(jTextFieldName, gridBagConstraints);

        jLabelGender.setText("Gender:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        jPanelFamily.add(jLabelGender, gridBagConstraints);

        jComboBoxGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female" }));
        jComboBoxGender.setPreferredSize(new java.awt.Dimension(60, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        jPanelFamily.add(jComboBoxGender, gridBagConstraints);

        jLabelBirthday.setLabelFor(jTextFieldBirthday);
        jLabelBirthday.setText("Birthday:");
        jLabelBirthday.setToolTipText("When the Person was born (ex. 7/17/1977)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        jPanelFamily.add(jLabelBirthday, gridBagConstraints);

        jTextFieldBirthday.setMinimumSize(new java.awt.Dimension(50, 20));
        jTextFieldBirthday.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        jPanelFamily.add(jTextFieldBirthday, gridBagConstraints);

        jButtonAdd.setText("Add");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonAddActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        jPanelFamily.add(jButtonAdd, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(new javax.swing.border.TitledBorder("Family Members"));
        jPanel1.setToolTipText("Who is currently listed as being in your family");
        jTable1.setModel(new DatabaseTableModel(fm,foodthinger.Constants.SELECT_FAMILY,foodthinger.Constants.TABLE_FAMILY, new int[]{0,1,2,3}));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(jTable1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelFamily.add(jPanel1, gridBagConstraints);

        jButtonCreateFoodPlan.setText("Create Food Plan");
        jButtonCreateFoodPlan.setToolTipText("Creates a basic food storage plan based on who is entered in your family.");
        jButtonCreateFoodPlan.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonCreateFoodPlanActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.insets = new java.awt.Insets(13, 0, 0, 0);
        jPanelFamily.add(jButtonCreateFoodPlan, gridBagConstraints);

        jLabelDeleteFamily.setText("Delete Person #:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        jPanelFamily.add(jLabelDeleteFamily, gridBagConstraints);

        jTextFieldDeleteFamily.setMinimumSize(new java.awt.Dimension(20, 20));
        jTextFieldDeleteFamily.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        jPanelFamily.add(jTextFieldDeleteFamily, gridBagConstraints);

        jButtonDeleteFamily.setText("Delete");
        jButtonDeleteFamily.setToolTipText("Deletes the person's whose ID is in the box");
        jButtonDeleteFamily.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonDeleteFamilyActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        jPanelFamily.add(jButtonDeleteFamily, gridBagConstraints);

        jTabbedPanel.addTab("Family", jPanelFamily);

        jCheckBoxUseExpired.setText("Treat expired items as still usuable");
        jCheckBoxUseExpired.setToolTipText("If checked, expired foods will be allowed to count towards food plan totals.  If not checked, they are not counted and treated like they don't exist.");
        jCheckBoxUseExpired.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jCheckBoxUseExpiredActionPerformed(evt);
            }
        });

        jPanelGeneral.add(jCheckBoxUseExpired);

        jTabbedPanel.addTab("General", jPanelGeneral);

        jTabbedPanel.addTab("Cannery Categories", jPanelCannery);

        jTabbedPanel.addTab("Weight", jPanelWeight);

        jPanelFood.setLayout(new java.awt.GridBagLayout());

        jLabelFoodName.setText("Food/Item Name");
        jLabelFoodName.setToolTipText("The name of the food or item you are adding(ex. Beans, Black, Dry)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelFood.add(jLabelFoodName, gridBagConstraints);

        jTextFieldAddFoodName.setMinimumSize(new java.awt.Dimension(50, 20));
        jTextFieldAddFoodName.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelFood.add(jTextFieldAddFoodName, gridBagConstraints);

        jLabelCommon.setText("Common Name (Optional)");
        jLabelCommon.setToolTipText("A shortcut name for the food or item (ex. Black Beans)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelFood.add(jLabelCommon, gridBagConstraints);

        jTextFieldCommon.setMinimumSize(new java.awt.Dimension(50, 20));
        jTextFieldCommon.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelFood.add(jTextFieldCommon, gridBagConstraints);

        jButtonAddNewFoodOK.setText("Add");
        jButtonAddNewFoodOK.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonAddNewFoodOKActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 7);
        jPanelFood.add(jButtonAddNewFoodOK, gridBagConstraints);

        jButtonCancel.setText("Clear");
        jButtonCancel.setToolTipText("Clears the form");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonCancelActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 7, 0, 0);
        jPanelFood.add(jButtonCancel, gridBagConstraints);

        jPanel2.setBorder(new javax.swing.border.TitledBorder("Weights"));
        jPanel2.setToolTipText("Sets up containers and weights so  the \"autofill\" will work for this new food. (Note: if it doesn't show up in the table, it won't be added)");
        jPanel2.setMinimumSize(new java.awt.Dimension(312, 100));
        jPanel2.setPreferredSize(new java.awt.Dimension(560, 100));
        jComboBoxContainer.setModel(fm.getComboModelForQuery(foodthinger.Constants.PANTRY_ADDER_CONTAINER_QUERY,0,1));
        jComboBoxContainer.setToolTipText("The container");
        jPanel2.add(jComboBoxContainer);

        jTextFieldAmount.setToolTipText("Numeric value of how much it weights");
        jTextFieldAmount.setMinimumSize(new java.awt.Dimension(30, 20));
        jTextFieldAmount.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel2.add(jTextFieldAmount);

        jComboBoxAmount.setModel(fm.getComboModelForQuery(foodthinger.Constants.PANTRY_ADDR_AMOUNTS_QUERY,0,1));
        jComboBoxAmount.setToolTipText("The measurement the number is in");
        jPanel2.add(jComboBoxAmount);

        jLabelExpires.setText("Years until Expires");
        jLabelExpires.setToolTipText("How many years until the product expires (max 20 years)");
        jPanel2.add(jLabelExpires);

        jSpinnerYears.setModel(new javax.swing.SpinnerNumberModel(1,1,100,.5));
        jSpinnerYears.setEditor(new javax.swing.JSpinner.NumberEditor(jSpinnerYears));
        jSpinnerYears.setMinimumSize(new java.awt.Dimension(30, 20));
        jSpinnerYears.setPreferredSize(new java.awt.Dimension(40, 20));
        jPanel2.add(jSpinnerYears);

        jButtonAddContainer2Weight.setText("Add Weight");
        jButtonAddContainer2Weight.setToolTipText("Adds the information to the table");
        jButtonAddContainer2Weight.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonAddContainer2WeightActionPerformed(evt);
            }
        });

        jPanel2.add(jButtonAddContainer2Weight);

        jButtonAddNewFoodClear.setText("Clear All");
        jButtonAddNewFoodClear.setToolTipText("Clears all the weights from the table");
        jButtonAddNewFoodClear.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonAddNewFoodClearActionPerformed(evt);
            }
        });

        jPanel2.add(jButtonAddNewFoodClear);

        jTableWeights.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Weight", "Amount", "Amount Type", "Expires"
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean []
            {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        jTableWeights.setMinimumSize(new java.awt.Dimension(60, 10));
        jTableWeights.setPreferredSize(new java.awt.Dimension(300, 10));
        jPanel2.add(jTableWeights);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanelFood.add(jPanel2, gridBagConstraints);

        jComboBoxAddNewFoodCategory.setModel(fm.getComboModelForQuery(foodthinger.Constants.FD_GROUP_QUERY,0,1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        jPanelFood.add(jComboBoxAddNewFoodCategory, gridBagConstraints);

        jLabelAddNewFoodCategory.setLabelFor(jComboBoxAddNewFoodCategory);
        jLabelAddNewFoodCategory.setText("Category");
        jLabelAddNewFoodCategory.setToolTipText("What category the food belongs in ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelFood.add(jLabelAddNewFoodCategory, gridBagConstraints);

        jTabbedPanel.addTab("Add New Food/Item", jPanelFood);

        getContentPane().add(jTabbedPanel, java.awt.BorderLayout.CENTER);

        pack();
    }
    // </editor-fold>//GEN-END:initComponents

    private void jCheckBoxUseExpiredActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jCheckBoxUseExpiredActionPerformed
    {//GEN-HEADEREND:event_jCheckBoxUseExpiredActionPerformed
	boolean sel = this.jCheckBoxUseExpired.isSelected();
	fm.setProperty(Constants.PROP_USE_EXPIRED, String.valueOf(sel));
    }//GEN-LAST:event_jCheckBoxUseExpiredActionPerformed

    private void jButtonCreateFoodPlanActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCreateFoodPlanActionPerformed
    {//GEN-HEADEREND:event_jButtonCreateFoodPlanActionPerformed
	//find out how many months, dump old food plan (y/n), show changes, auto update
	//dump old food plan
	new FoodPlanInitJDialog(new javax.swing.JFrame(), true,fm).setVisible(true);
    }//GEN-LAST:event_jButtonCreateFoodPlanActionPerformed
    /**
     * Add a new food or item to the database, it's common name, and it's weight/amounts
     */
    private void jButtonAddNewFoodOKActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAddNewFoodOKActionPerformed
    {//GEN-HEADEREND:event_jButtonAddNewFoodOKActionPerformed
	//validate food name
	String food = this.jTextFieldAddFoodName.getText();
	if(food == null || food.trim().length()==0)
	{
	    javax.swing.JOptionPane.showMessageDialog(this,Constants.ERROR_ADD_NEW_FOOD+Constants.ERROR_ADDING_NEW_FOOD_NO_NAME,Constants.ERROR_ADD_NEW_FOOD,javax.swing.JOptionPane.ERROR_MESSAGE);
	    return;
	}
	food = food.trim();
	//validate common name
	String common = this.jTextFieldCommon.getText();
	if(common!=null)
	{
	    common = common.trim();
	}
	//make sure food and common name don't already exist
	if(fm.exists(Constants.FOOD_EXISTS, new String[]{food}, 0)!=0)
	{
	    //error the food does exist, don't add again
	    javax.swing.JOptionPane.showMessageDialog(this,Constants.ERROR_ADD_NEW_FOOD+Constants.ERROR_ADD_NEW_FOOD_FOOD_EXISTS,Constants.ERROR_ADD_NEW_FOOD,javax.swing.JOptionPane.ERROR_MESSAGE);
	    return;
	}
	if(common!=null && common.length()>0 && fm.exists(Constants.COMMON_FOOD_EXISTS, new String[]{common}, 0)!=0)
	{
	    //error the common name does exist, don't add again
	    javax.swing.JOptionPane.showMessageDialog(this,Constants.ERROR_ADD_NEW_FOOD+Constants.ERROR_ADD_NEW_FOOD_COMMON_EXISTS,Constants.ERROR_ADD_NEW_FOOD,javax.swing.JOptionPane.ERROR_MESSAGE);
	    return;
	}
	FoodTreeNode category = (FoodTreeNode)this.jComboBoxAddNewFoodCategory.getSelectedItem();
	if(category==null)
	{
	    javax.swing.JOptionPane.showMessageDialog(this,Constants.ERROR_ADD_NEW_FOOD+"Unexpected Error: please select a food category",Constants.ERROR_ADD_NEW_FOOD,javax.swing.JOptionPane.ERROR_MESSAGE);
	    return;

	}

	//add food into db
	    //get the max ndb_no so I can increment it
	    String maxId = getCurrentNdb_No();
	    if(maxId == null) return;
	    String error = fm.exec(Constants.INSERT_FOOD_DES,new String[]{maxId,String.valueOf(category.getId()),food,food},Constants.TABLE_FOOD_DES);
	    //get the category
	    if(error !=null)
	    {
		javax.swing.JOptionPane.showMessageDialog(this,Constants.ERROR_ADD_NEW_FOOD+"Unforseen error addint food:"+error,Constants.ERROR_ADD_NEW_FOOD,javax.swing.JOptionPane.ERROR_MESSAGE);
		return;
	    }
	//add common name into db
	    if(common!=null && common.length()>0)
	    {
		error = fm.exec(Constants.INSERT_COMMON_NAME,new String[]{maxId,common},Constants.TABLE_COMMON_FOODS);
		if(error !=null)
		{
		    javax.swing.JOptionPane.showMessageDialog(this,Constants.ERROR_ADD_NEW_FOOD+"Unforseen error adding common name:"+error,Constants.ERROR_ADD_NEW_FOOD,javax.swing.JOptionPane.ERROR_MESSAGE);
		    return;
		}
	    }
	//add weights/amounts mapping into db
	if(addContainer2AmountToDB((javax.swing.table.DefaultTableModel)this.jTableWeights.getModel(), maxId))
	{
		javax.swing.JOptionPane.showMessageDialog(this,Constants.SUCCESS_ADD_NEW_FOOD+food,Constants.SUCCESS_ADD_NEW_FOOD,javax.swing.JOptionPane.INFORMATION_MESSAGE);
		this.clearAddNewFood();
	}
	
    }//GEN-LAST:event_jButtonAddNewFoodOKActionPerformed
    
    /**
     * adds the container to amounts mapping to the db
     */
    boolean addContainer2AmountToDB(javax.swing.table.DefaultTableModel model, String ndb_no)
    {
	String error="";
	String error1="";
	for(int i = 0; i<model.getRowCount();i++)
	{
	    //get the container id
	    FoodTreeNode container = (FoodTreeNode)model.getValueAt(i,0);
	    //get the amount
	    String amt = (String)model.getValueAt(i, 1);
	    //get the amount id
	    FoodTreeNode amount = (FoodTreeNode)model.getValueAt(i,2);
	    //get expires
	    String expires = model.getValueAt(i, 3).toString();
	    //add them to the table
	    error1 = fm.exec(Constants.INSERT_FOOD_DES_CONTAINER, new String[]{ndb_no,
		    String.valueOf(container.getId()),
		    amt,
		    String.valueOf(amount.getId()),
		    expires,expires}, Constants.TABLE_FOOD_DES_CONTAINERS);
	    if(error1!=null && error1.length()>0) error+=error1;
	}
	if(error.length()>0)
	{
	    javax.swing.JOptionPane.showMessageDialog(this,Constants.ERROR_ADD_NEW_FOOD+"Unforseen error:"+error,Constants.ERROR_ADD_NEW_FOOD,javax.swing.JOptionPane.ERROR_MESSAGE);
	    return false;
	}
	return true;
    }
    /**
     * finds the max ndb_no in the db
     */
    String getCurrentNdb_No()
    {
	String maxId;
	    String[] maxStrs = fm.getStringsFromQuery(Constants.MAX_NDB_NO,null,0);
	    if(maxStrs!=null)
	    {
		maxId = String.valueOf(Integer.parseInt(maxStrs[0])+1);
	    }
	    else
	    {
		javax.swing.JOptionPane.showMessageDialog(this,Constants.ERROR_ADD_NEW_FOOD+"Unforseen error, no max ndb_no",Constants.ERROR_ADD_NEW_FOOD,javax.swing.JOptionPane.ERROR_MESSAGE);
		return null;
	    }
	    return maxId;
    }
    private void jButtonAddNewFoodClearActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAddNewFoodClearActionPerformed
    {//GEN-HEADEREND:event_jButtonAddNewFoodClearActionPerformed
	clearWeightTable();
    }//GEN-LAST:event_jButtonAddNewFoodClearActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCancelActionPerformed
    {//GEN-HEADEREND:event_jButtonCancelActionPerformed
	//clear out the text boxes, get rid of any info entered
	this.clearAddNewFood();
	
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonAddContainer2WeightActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAddContainer2WeightActionPerformed
    {//GEN-HEADEREND:event_jButtonAddContainer2WeightActionPerformed
	//get the data
	FoodTreeNode ftnContainer = (FoodTreeNode)this.jComboBoxContainer.getSelectedItem();
	FoodTreeNode ftnAmount = (FoodTreeNode)this.jComboBoxAmount.getSelectedItem();
	String number = this.jTextFieldAmount.getText();
	Object expires = this.jSpinnerYears.getValue();
	//no number, so error
	if(ftnContainer == null || ftnAmount==null || number == null || number.trim().length()==0)
	{
	    javax.swing.JOptionPane.showMessageDialog(this,Constants.ERROR_LOCAL_ADD_CONTAINER,Constants.ERROR,javax.swing.JOptionPane.ERROR_MESSAGE);
	    return;
	}
	//make sure it's an int
	try
	{
	    int i = Integer.parseInt(number);
	    number = String.valueOf(i);
	}
	catch(Exception e)
	{
	    javax.swing.JOptionPane.showMessageDialog(this,Constants.ERROR_LOCAL_ADD_CONTAINER,Constants.ERROR,javax.swing.JOptionPane.ERROR_MESSAGE);
	    return;
	}
	//make sure there isn't already a value for this combination in the table
	javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel)this.jTableWeights.getModel();
	if(containerComboExists(model, ftnContainer))
	{
	    javax.swing.JOptionPane.showMessageDialog(this,Constants.ERROR_LOCAL_ADD_CONTAINER_EXISTS,Constants.ERROR,javax.swing.JOptionPane.ERROR_MESSAGE);
	    return;
	}
	//add it to the table

	model.addRow(new Object[]{ftnContainer,number,ftnAmount,expires});
	java.awt.Dimension old = this.jTableWeights.getPreferredSize();
	this.jTableWeights.setPreferredSize(new java.awt.Dimension((int)old.getWidth(),(int)(20*model.getRowCount())));
	
    }//GEN-LAST:event_jButtonAddContainer2WeightActionPerformed
    
    /**
     * figure out if the container already has an amount associated with it
     */
    protected boolean containerComboExists(javax.swing.table.DefaultTableModel model, FoodTreeNode container)
    {
	for(int i = 0; i < model.getRowCount(); i++)
	{
	    //o is magic number, that's where the container is
	    if(container == model.getValueAt(i,0))
	    {
		return true;
	    }
	}
	return false;
    }
    
    private void jButtonDeleteFamilyActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonDeleteFamilyActionPerformed
    {//GEN-HEADEREND:event_jButtonDeleteFamilyActionPerformed
	String id1 = this.jTextFieldDeleteFamily.getText();
	//set text to empty
	
	if(id1==null || id1.trim().length()==0)
	{
	    //error out
	    javax.swing.JOptionPane.showMessageDialog(this,Constants.ERROR_DELETING_FAMILY+" id "+id1+" is not valid.",Constants.ERROR,javax.swing.JOptionPane.ERROR_MESSAGE);
	   // this.jTextFieldDeleteFamily.setText("");
	    return;
	}
	//check and make sure it is an int
	try
	{
	    Integer.parseInt(id1);
	}
	catch(Exception e)
	{
	    javax.swing.JOptionPane.showMessageDialog(this,Constants.ERROR_DELETING_FAMILY+" id "+id1+" is not valid.",Constants.ERROR,javax.swing.JOptionPane.ERROR_MESSAGE);
	   // this.jTextFieldDeleteFamily.setText("");
	    return;
	}
	String error = fm.exec(Constants.DELETE_FAMILY, new String[]{id1}, Constants.TABLE_FAMILY);
	if(error!=null && error.length()>0)
	{
	    javax.swing.JOptionPane.showMessageDialog(this,Constants.ERROR_DELETING_FAMILY+" "+error,Constants.ERROR,javax.swing.JOptionPane.ERROR_MESSAGE);
	}
	//this.jTextFieldDeleteFamily.setText("");
    }//GEN-LAST:event_jButtonDeleteFamilyActionPerformed
/**
 * Add a new member of the family.  Add a new row to the family table, verifies birthday
 */
    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAddActionPerformed
    {//GEN-HEADEREND:event_jButtonAddActionPerformed
	//validate birthday
	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("MM/dd/yyyy");
	java.util.Date bday = formatter.parse(jTextFieldBirthday.getText(), new java.text.ParsePosition(0));
	if(bday == null)
	{
	    javax.swing.JOptionPane.showMessageDialog(this,Constants.ERROR_ADDING_FAMILY,Constants.ERROR_ADDING_FAMILY,javax.swing.JOptionPane.ERROR_MESSAGE);
	    return;
	}
	//convert birthday to long
	long birthday = bday.getTime();
	//insert into db
	String []args = new String[3];
	args[0] = this.jTextFieldName.getText();
	args[1] = (String)(this.jComboBoxGender.getSelectedItem());
	args[2] = String.valueOf(birthday);
	
	String error = fm.exec(Constants.INSERT_FAMILY, args, Constants.TABLE_FAMILY);
	if(error != null && error.length()>0)
	{
	    javax.swing.JOptionPane.showMessageDialog(this,Constants.ERROR_ADDING_FAMILY,Constants.ERROR_ADDING_FAMILY+" "+error,javax.swing.JOptionPane.ERROR_MESSAGE);
	}
	//update dislay table
    }//GEN-LAST:event_jButtonAddActionPerformed
       
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonAddContainer2Weight;
    private javax.swing.JButton jButtonAddNewFoodClear;
    private javax.swing.JButton jButtonAddNewFoodOK;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonCreateFoodPlan;
    private javax.swing.JButton jButtonDeleteFamily;
    private javax.swing.JCheckBox jCheckBoxUseExpired;
    private javax.swing.JComboBox jComboBoxAddNewFoodCategory;
    private javax.swing.JComboBox jComboBoxAmount;
    private javax.swing.JComboBox jComboBoxContainer;
    private javax.swing.JComboBox jComboBoxGender;
    private javax.swing.JLabel jLabelAddNewFoodCategory;
    private javax.swing.JLabel jLabelBirthday;
    private javax.swing.JLabel jLabelCommon;
    private javax.swing.JLabel jLabelDeleteFamily;
    private javax.swing.JLabel jLabelExpires;
    private javax.swing.JLabel jLabelFoodName;
    private javax.swing.JLabel jLabelGender;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelCannery;
    private javax.swing.JPanel jPanelFamily;
    private javax.swing.JPanel jPanelFood;
    private javax.swing.JPanel jPanelGeneral;
    private javax.swing.JPanel jPanelWeight;
    private javax.swing.JSpinner jSpinnerYears;
    private javax.swing.JTabbedPane jTabbedPanel;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableWeights;
    private javax.swing.JTextField jTextFieldAddFoodName;
    private javax.swing.JTextField jTextFieldAmount;
    private javax.swing.JTextField jTextFieldBirthday;
    private javax.swing.JTextField jTextFieldCommon;
    private javax.swing.JTextField jTextFieldDeleteFamily;
    private javax.swing.JTextField jTextFieldName;
    // End of variables declaration//GEN-END:variables
    
}
