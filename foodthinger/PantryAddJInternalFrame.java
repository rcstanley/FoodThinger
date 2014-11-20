/*
 * PantryAddJInternalFrame.java
 *
 * Created on June 21, 2006, 9:16 PM
 */

package foodthinger;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Currency;
/**
 *
 * @author  RC Stanley
 */
public class PantryAddJInternalFrame extends javax.swing.JInternalFrame implements ActionListener
{
    FoodModel fm;
    boolean update;
    int foodId;
    /**
     * Creates new form PantryAddJInternalFrame 
     */
    public PantryAddJInternalFrame(FoodModel fm)
    {
	this.fm=fm;
	initComponents();
	resetValues();
	
    }
    
    /**
     * Listen for actions then update the hint
     */
    public void actionPerformed(ActionEvent e)
    {
	if(e.getSource() instanceof javax.swing.JComboBox)
	{
	    //set the value in the real combo box to match
	    if(e.getSource()==this.jComboBoxShortFoodList)
	    {
		syncFood(jComboBoxShortFoodList, this.jComboBoxFoodType);
	    }
	    else if(e.getSource()==this.jComboBoxFoodType)
	    {
		syncFood(this.jComboBoxFoodType, jComboBoxShortFoodList);
	    }
	    updateHint();
		
	}
    }
    
    private void syncFood(javax.swing.JComboBox box1,javax.swing.JComboBox box2)
    {
	FoodTreeNode selected = (FoodTreeNode)box1.getSelectedItem();
	if(selected==null) return;
	int id1 = selected.getId();
	int id2 = ((FoodTreeNode)box2.getSelectedItem()).getId();
	//if less than zero then nothing real is selected so syncing is irrelivant
	//if the ids are equal than it is allready synced
	if(id1 >= 0 && id1!=id2) 
	{
	    selectComboById(box2, id1);
	 }
    }
    
    /**
     * look up the hint in the db for the current selection
     * change the hint to reflect new combo box values
     */
    private void updateHint()
    {
	//look up the hint in the db for the current selection
	//change the hint to reflect new combo box values
	//get the ids from the combo boxes
	String hint="";
	if(this.jComboBoxFoodType.getSelectedItem() == null)return;
	String ndb_noStr = String.valueOf(((FoodTreeNode)this.jComboBoxFoodType.getSelectedItem()).getId());
	FoodTreeNode sel = ((FoodTreeNode)this.jComboBoxSize.getSelectedItem());
	String containerIdStr = "0";
	String results[] = null;
	String queryValues[] = new String[2];
	if(sel!=null)  //an item is selected, so look up it's info
	{
	    containerIdStr = String.valueOf(sel.getId());
	   
	    queryValues[0]=ndb_noStr;
	    queryValues[1]=containerIdStr;
	    //System.out.println(queryValues[0]+","+queryValues[1]);
	    //query db to see if there is an exp. date
	    results = fm.getStringsFromQuery(Constants.PANTRY_ADDR_EXP_DATE_QUERY,queryValues,0);
	
	    //if there is no exact date, query and get exp. date for anything similar
	    if(results != null) //so there is an experation date
	    {

		    hint = "This product should be good for "+results[0]+" years.";
	    }
	    else  
	    {
		queryValues = new String[1];
		queryValues[0] = ndb_noStr;
		results = fm.getStringsFromQuery(Constants.PANTRY_ADDR_EXP_DATE_ANY_CONTAINER_QUERY,queryValues,0);
		if(results != null)
		{
		    hint = "This product, in different packaging, can be good for ";
		    for(int i = 0; i<results.length; i++)
		    {
			hint = hint + results[i];
			if(i+1<results.length) //if there is a next one add a comma
			{
			    hint = hint + ", ";
			}
		    }
		}
	    }

	}
	//couldn't find any info, use default hint
	if(results==null)
	{
	    hint = Constants.DEFAULT_HINT;
	}
	hint = "<html><i>Hint: </i>"+hint+"</html>";
	jLabelHint.setText(hint);
    }
    
    /**
     * resets the values in the boxes to their defaults 
     */
    private void resetValues()
    {
	//set the number purchased to be 1
	update = false;
	this.foodId = -1;
	this.setTitle(Constants.ADD_FOOD_ADD_TITLE); 
	this.jFormattedTextNumber.setText("1");
	//set the purchase date to this month
	Date today;
	String dateOut;
	String monthOut;
	SimpleDateFormat dateFormatter;
	
	dateFormatter = new SimpleDateFormat("yyyy",Locale.US);
	today = new Date();
	dateOut = dateFormatter.format(today);
	jComboBoxPurYear.setSelectedItem(dateOut);
	//set the month
	SimpleDateFormat monthFormatter = new SimpleDateFormat("MMM",Locale.US);
	monthOut = monthFormatter.format(today);
	this.jComboBoxPurMon.setSelectedItem(monthOut);
	//set the exp date to be 1 year from selected date
	/*GregorianCalendar greg = new GregorianCalendar();
	greg.add(greg.YEAR,1);
	dateOut = dateFormatter.format(greg.getTime());
	this.jComboBoxExpYear.setSelectedItem(dateOut);
	this.jComboBoxExpMon.setSelectedItem(monthOut);*/
	setMonthYearCombo(this.jComboBoxExpMon,this.jComboBoxExpYear, today, 1);
	//reset the price
	this.jFormattedTextFieldPrice.setText("0.00");
	
    }
    
    private void setMonthYearCombo(javax.swing.JComboBox monthBox, javax.swing.JComboBox yearBox,
	    Date startDate, double yearOffset)
    {
	String yearOut;
	String monthOut;
	SimpleDateFormat yearFormatter = new SimpleDateFormat("yyyy",Locale.US);
	SimpleDateFormat monthFormatter = new SimpleDateFormat("MMM",Locale.US);
	//set the exp date to be yearOffset years from selected date
	GregorianCalendar greg = new GregorianCalendar();
	greg.setTime(startDate);
	if(Math.floor(yearOffset) == yearOffset)
	{
	    greg.add(greg.YEAR,(int)yearOffset);
	}
	else
	{
	    //TODO test the months sometime
	    int yearCount = (int)Math.floor(yearOffset);
	    //compute the fraction part in months
	    int monthCount = (int)Math.ceil(Math.abs(12*(yearOffset-yearCount)));
	    greg.add(greg.YEAR, yearCount);
	    greg.add(greg.MONTH, monthCount);
	}
	yearOut = yearFormatter.format(greg.getTime());
	monthOut = monthFormatter.format(greg.getTime());
	monthBox.setSelectedItem(monthOut);
	yearBox.setSelectedItem(yearOut);
    }
   
    private void autoFiller(Date currDate, int exp, double weight, int weightType)
    {
	
    }
    
    /**
     * sets the value of the combo box to the database id entered
     * returns true if found a match, false if used a default value
     */
    private boolean selectComboById(javax.swing.JComboBox combo,int id)
    {
	    javax.swing.ComboBoxModel model = combo.getModel();
	    FoodTreeNode curr;
	    int size = model.getSize();
	    boolean found = false;
	    boolean reallyFound = false;
	    //check through the list and find the real one
	    for(int i = 0; i < size; i++)
	    {
		curr = (FoodTreeNode)model.getElementAt(i);
		if(curr.getId()==id)
		{
		    combo.setSelectedIndex(i);
		    found = true;
		    reallyFound = true;
		    break;
		}
	    }
	    //couldn't find a match, so set other box to the no selection id
	    if(!found)
	    {
		//check through the list and set it to the default value
		for(int i = 0; i < size; i++)
		{
		    curr = (FoodTreeNode)model.getElementAt(i);
		    if(curr.getId()< 0)
		    {
			combo.setSelectedIndex(i);
			found = true;
			break;
		    }
		}
	    }
	    if(!found)//still couldn't find anything, just pick the first one
	    {
		combo.setSelectedIndex(0);
	    }
	    return reallyFound;
    }
    
    Date stringToDate(String mon, String year)
    {
	SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat("MMM/dd/yyyy");
	Date d = null;
	try
	{
	 d = dateFormatter.parse(mon+"/1/"+year);
	 //System.out.println(d);
	}
	catch(Exception e)
	{
	    System.out.println(e);
	}
	return d;
	 
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabelFoodItem = new javax.swing.JLabel();
        jComboBoxShortFoodList = new javax.swing.JComboBox();
        jComboBoxFoodType = new javax.swing.JComboBox();
        jLabelSize = new javax.swing.JLabel();
        jComboBoxSize = new javax.swing.JComboBox();
        jLabelNumber = new javax.swing.JLabel();
        jFormattedTextNumber = new javax.swing.JFormattedTextField();
        jPanelPurDate = new javax.swing.JPanel();
        jLabelPurDate = new javax.swing.JLabel();
        jComboBoxPurMon = new javax.swing.JComboBox();
        jLabelPurSlash = new javax.swing.JLabel();
        jComboBoxPurYear = new javax.swing.JComboBox();
        jPanelAutoFill = new javax.swing.JPanel();
        jPanelExpDate = new javax.swing.JPanel();
        jLabelExpDate = new javax.swing.JLabel();
        jComboBoxExpMon = new javax.swing.JComboBox();
        jLabelExpSlash = new javax.swing.JLabel();
        jComboBoxExpYear = new javax.swing.JComboBox();
        jLabelHint = new javax.swing.JLabel();
        jLabelWeight = new javax.swing.JLabel();
        jFormattedTextFieldWeight = new javax.swing.JFormattedTextField();
        jComboBoxWeight = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxStoreName = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jFormattedTextFieldPrice = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxLocation = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jButtonAutoFill = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();
        jToggleButtonCancel = new javax.swing.JToggleButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setResizable(true);
        setTitle("Add Food/Item to Pantry");
        setFrameIcon(null);
        setMinimumSize(new java.awt.Dimension(400, 200));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel4.setPreferredSize(new java.awt.Dimension(600, 400));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabelFoodItem.setLabelFor(jComboBoxFoodType);
        jLabelFoodItem.setText("Food / Item:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel5.add(jLabelFoodItem, gridBagConstraints);

        jComboBoxShortFoodList.setModel(fm.getComboModelForQuery(foodthinger.Constants.PANTRY_ADDR_COMMON_FOODS_QUERY,0,1, new FoodTreeNode("[None]", -1)));
        jComboBoxShortFoodList.addActionListener(this);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jComboBoxShortFoodList, gridBagConstraints);

        jComboBoxFoodType.setModel(fm.getComboModelForQuery(foodthinger.Constants.PANTRY_ADDER_FOODTYPE_QUERY,0,1));
        jComboBoxFoodType.setToolTipText("The type of food or item you bought. (Wheat, Oats, Tent)");
        jComboBoxFoodType.addActionListener(this);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jComboBoxFoodType, gridBagConstraints);

        jLabelSize.setLabelFor(jComboBoxSize);
        jLabelSize.setText("Container:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel5.add(jLabelSize, gridBagConstraints);

        jComboBoxSize.setModel(fm.getComboModelForQuery(foodthinger.Constants.PANTRY_ADDER_CONTAINER_QUERY,0,1));
        jComboBoxSize.setToolTipText("The Size of the item you bought.  (#10 can, Superpail,etc.)");
        jComboBoxSize.addActionListener(this);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jComboBoxSize, gridBagConstraints);

        jLabelNumber.setText("Amount Stored:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel5.add(jLabelNumber, gridBagConstraints);

        jFormattedTextNumber.setText("1");
        jFormattedTextNumber.setToolTipText("How many food items you bought");
        jFormattedTextNumber.setMinimumSize(new java.awt.Dimension(50, 22));
        jFormattedTextNumber.setPreferredSize(new java.awt.Dimension(50, 22));
        jFormattedTextNumber.setValue(new Integer(1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jFormattedTextNumber, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jPanel5, gridBagConstraints);

        jPanelPurDate.setLayout(new java.awt.GridBagLayout());

        jLabelPurDate.setLabelFor(jComboBoxPurMon);
        jLabelPurDate.setText("Purchase Date:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanelPurDate.add(jLabelPurDate, gridBagConstraints);

        jComboBoxPurMon.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));
        jComboBoxPurMon.setToolTipText("The month you bought the food.  ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelPurDate.add(jComboBoxPurMon, gridBagConstraints);

        jLabelPurSlash.setText("/");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelPurDate.add(jLabelPurSlash, gridBagConstraints);

        jComboBoxPurYear.setModel(new DefaultComboBoxModel(fm.getYearList(fm.getMaxExp(),0)));
        jComboBoxPurYear.setToolTipText("The year you bought the food");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelPurDate.add(jComboBoxPurYear, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jPanelPurDate, gridBagConstraints);

        jPanelAutoFill.setLayout(new java.awt.GridBagLayout());

        jPanelAutoFill.setBorder(new javax.swing.border.TitledBorder("AutoFill"));
        jPanelExpDate.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanelExpDate.setMinimumSize(new java.awt.Dimension(300, 30));
        jPanelExpDate.setPreferredSize(new java.awt.Dimension(250, 32));
        jLabelExpDate.setLabelFor(jComboBoxExpMon);
        jLabelExpDate.setText("Expiration Date:");
        jPanelExpDate.add(jLabelExpDate);

        jComboBoxExpMon.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));
        jComboBoxExpMon.setToolTipText("The month the item expires");
        jPanelExpDate.add(jComboBoxExpMon);

        jLabelExpSlash.setText("/");
        jPanelExpDate.add(jLabelExpSlash);

        jComboBoxExpYear.setModel(new DefaultComboBoxModel(fm.getYearList(3,fm.getMaxExp())));
        jComboBoxExpYear.setToolTipText("The year the food expires");
        jPanelExpDate.add(jComboBoxExpYear);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanelAutoFill.add(jPanelExpDate, gridBagConstraints);

        jLabelHint.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabelHint.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelHint.setText("<html><i>Hint:</i></html>");
        jLabelHint.setMaximumSize(new java.awt.Dimension(500, 14));
        jLabelHint.setMinimumSize(new java.awt.Dimension(500, 14));
        jLabelHint.setPreferredSize(new java.awt.Dimension(500, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanelAutoFill.add(jLabelHint, gridBagConstraints);

        jLabelWeight.setLabelFor(jComboBoxWeight);
        jLabelWeight.setText("Weight of 1 container");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAutoFill.add(jLabelWeight, gridBagConstraints);

        jFormattedTextFieldWeight.setText("1");
        jFormattedTextFieldWeight.setMinimumSize(new java.awt.Dimension(50, 22));
        jFormattedTextFieldWeight.setPreferredSize(new java.awt.Dimension(50, 22));
        jFormattedTextFieldWeight.setValue(new Double(0.00));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 0);
        jPanelAutoFill.add(jFormattedTextFieldWeight, gridBagConstraints);

        jComboBoxWeight.setModel(fm.getComboModelForQuery(foodthinger.Constants.PANTRY_ADDR_AMOUNTS_QUERY,0,1));
        jComboBoxWeight.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jComboBoxWeightActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 0);
        jPanelAutoFill.add(jComboBoxWeight, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jPanelAutoFill, gridBagConstraints);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel3.setBorder(new javax.swing.border.TitledBorder("Optional"));
        jLabel2.setLabelFor(jComboBoxStoreName);
        jLabel2.setText("Store");
        jPanel3.add(jLabel2);

        jComboBoxStoreName.setModel(fm.getComboModelForQuery(foodthinger.Constants.PANTRY_ADDER_STORE_QUERY,0,1));
        jComboBoxStoreName.setToolTipText("The store you purchased it from");
        jPanel3.add(jComboBoxStoreName);

        jLabel6.setLabelFor(jFormattedTextFieldPrice);
        jLabel6.setText("Price:  $");
        jPanel3.add(jLabel6);

        jFormattedTextFieldPrice.setText("0.00");
        jFormattedTextFieldPrice.setMinimumSize(new java.awt.Dimension(12, 22));
        jFormattedTextFieldPrice.setValue(new Double(0.00)/*Currency.getInstance(Locale.US)*/);
        jPanel3.add(jFormattedTextFieldPrice);

        jLabel1.setLabelFor(jComboBoxLocation);
        jLabel1.setText("Location");
        jPanel3.add(jLabel1);

        jComboBoxLocation.setModel(fm.getComboModelForQuery(foodthinger.Constants.PANTRY_ADDR_LOC_QUERY,0,1));
        jComboBoxLocation.setToolTipText("Where the item is stored.  (Garage, in the pantry, etc.)");
        jPanel3.add(jComboBoxLocation);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel4.add(jPanel1, gridBagConstraints);

        jPanel2.setMinimumSize(new java.awt.Dimension(60, 33));
        jPanel2.setPreferredSize(new java.awt.Dimension(100, 33));
        jButtonAutoFill.setText("AutoFill");
        jButtonAutoFill.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                autoFillClicked(evt);
            }
        });

        jPanel2.add(jButtonAutoFill);

        jButtonAdd.setText("Add");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonAddActionPerformed(evt);
            }
        });
        jButtonAdd.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                addButtonPressed(evt);
            }
        });

        jPanel2.add(jButtonAdd);

        jToggleButtonCancel.setText("Cancel");
        jToggleButtonCancel.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                jToggleButtonCancelMouseReleased(evt);
            }
        });

        jPanel2.add(jToggleButtonCancel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel4.add(jPanel2, gridBagConstraints);

        jScrollPane1.setViewportView(jPanel4);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.SOUTH);

        pack();
    }
    // </editor-fold>//GEN-END:initComponents

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAddActionPerformed
    {//GEN-HEADEREND:event_jButtonAddActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jComboBoxWeightActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBoxWeightActionPerformed
    {//GEN-HEADEREND:event_jComboBoxWeightActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxWeightActionPerformed

    /**
     * same as show(), only with an id passed in
     */
    public void show(int id)
    {
	this.foodId = id;
	
	if(id>0) //this is an update
	{
	    //TODO set values
	    this.setTitle(Constants.ADD_FOOD_UPDATE_TITLE);
	    this.jButtonAdd.setText("Update");
	    this.update = true;
	    //get the values from the db
	    String[][] values = fm.getStringsFromQuery(Constants.PANTRY_ADDR_EDIT_QUERY,new String[]{String.valueOf(id)},new int[]{0,1,2,3,4,5,6,7,8,9});
	    if(values!=null && values.length>0)
	    {
		//set all the combo boxes
		//"select food_type_id, container_id, location_id, number_purchased, purchase_date, experation_date, price, store, amount, amount_id from food_stored where id=%q";
		fm.selectComboById(Integer.parseInt(values[0][0]),(javax.swing.DefaultComboBoxModel)jComboBoxFoodType.getModel());
		//container
		fm.selectComboById(Integer.parseInt(values[0][1]),(javax.swing.DefaultComboBoxModel)this.jComboBoxSize.getModel());
		//location
		fm.selectComboById(Integer.parseInt(values[0][2]),(javax.swing.DefaultComboBoxModel)this.jComboBoxLocation.getModel());
		//number purchased
		//System.out.println(values[0][3]);
		this.jFormattedTextNumber.setValue(Integer.valueOf(values[0][3]));
		//TODO purchase date
		this.setMonthYearCombo(this.jComboBoxPurMon, this.jComboBoxPurYear, new Date(Long.parseLong(values[0][4])), 0);
		//TODO experation date
		this.setMonthYearCombo(this.jComboBoxExpMon, this.jComboBoxExpYear, new Date(Long.parseLong(values[0][5])), 0);
		//price
		this.jFormattedTextFieldPrice.setValue(Double.valueOf(values[0][6]));
		//store
		fm.selectComboById(Integer.parseInt(values[0][7]),(javax.swing.DefaultComboBoxModel)this.jComboBoxStoreName.getModel());
		// amount
		this.jFormattedTextFieldWeight.setValue(Double.valueOf(values[0][8]));
		// amountid
		fm.selectComboById(Integer.parseInt(values[0][9]),(javax.swing.DefaultComboBoxModel)this.jComboBoxWeight.getModel());
		this.show();
	    }
	    else //oops, id doesn't exist throw an error and put up 
	    {
		id = -1;
		javax.swing.JOptionPane.showMessageDialog(this,"Error: id does not exist");
	    }
	    
	}
	if(id < 0)
	{
	    
	    this.setTitle(Constants.ADD_FOOD_ADD_TITLE);
	    this.jButtonAdd.setText("Add");
	    this.update = false;
	    this.show();
	}
    }
    
    private void addButtonPressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_addButtonPressed
    {//GEN-HEADEREND:event_addButtonPressed
	//get the values
	FieldValues fv = new FieldValues();
	String error = "";
	//validate
	if(fv.purDate.after(fv.expDate))
	{
	    error = "The experation date is before the purchase date.\r\n";
	}
	
	if(fv.weight < 0)
	{
	    error = error+" The weight cannot be less than 0.\r\n";
	}
	if(fv.numBought <= 0)
	{
	    error = error+" The number must be 1 or more.\r\n";
	}
	if(fv.price < 0)
	{
	    error = error+" The price cannot be less than 0.\r\n";
	}
	//throw up box if wrong
	if(error.length()>0)
	{
	    int result = javax.swing.JOptionPane.showConfirmDialog(this,error,"Error Adding",javax.swing.JOptionPane.YES_NO_CANCEL_OPTION,javax.swing.JOptionPane.ERROR_MESSAGE);
	    if(result == javax.swing.JOptionPane.CANCEL_OPTION || result == javax.swing.JOptionPane.NO_OPTION)
	    {
		return;
	    }
	}
	//otherwise add to db
	if(foodId<0)
	{
	    error = fm.insertFoodStored(fv.ndb_no,fv.containerId,fv.locationId, fv.numBought,
		fv.purDate.getTime(),fv.expDate.getTime(),fv.price,fv.storeId, 
		fv.weight, fv.weightTypeId);
	}
	else  //update existing row
	{
	    
	    error = fm.updateFoodStored(fv.ndb_no,fv.containerId,fv.locationId, fv.numBought,
		fv.purDate.getTime(),fv.expDate.getTime(),fv.price,fv.storeId, 
		fv.weight, fv.weightTypeId, this.foodId);
	    if(error!=null)
	    {
		int choice = javax.swing.JOptionPane.showConfirmDialog(this,"Update failed because"+error+", would you like to add instead?","Update Instead",javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
		if(choice==javax.swing.JOptionPane.YES_OPTION)
		{
		    error = fm.updateFoodStored(fv.ndb_no,fv.containerId,fv.locationId, fv.numBought,
		    fv.purDate.getTime(),fv.expDate.getTime(),fv.price,fv.storeId, 
		    fv.weight, fv.weightTypeId, this.foodId);
		}
	    }
	    
	}
	if(error == null)
	{
	    //tell the user item added
	    javax.swing.JOptionPane.showMessageDialog(this,(update?"Updated ":"Added ")+fv.foodName+" to your pantry");
	    if(update) //the item was updated, so hide it
	    {
		this.hide();
		resetValues();
	    }
	}
	else
	{
	    javax.swing.JOptionPane.showMessageDialog(this,"Unable to "+(update?"update ":"add ")+fv.foodName+" to your pantry because:"+error);
	}
    }//GEN-LAST:event_addButtonPressed

    private void autoFillClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_autoFillClicked
    {//GEN-HEADEREND:event_autoFillClicked
	//check what is in the food and container box
	FoodTreeNode selFood = (FoodTreeNode)(this.jComboBoxFoodType.getSelectedItem());
	int ndb_no = selFood.getId();
	FoodTreeNode containerNode = (FoodTreeNode)(this.jComboBoxSize.getSelectedItem());
	int containerId = containerNode.getId();
	//look up in db and see if we have any values
	int columns[] = new int[4];
	String results[][];
	columns[0] = 0;
	columns[1]=1;
	columns[2]=2;
	columns[3]=3;
	String[]queryValues = new String[1];
	queryValues[0] = String.valueOf(ndb_no);
	//queryValues[1] = String.valueOf(container);
	results = fm.getStringsFromQuery(Constants.PANTRY_ADDR_EXP_DATE_ANY_CONTAINER_QUERY,queryValues,columns);
	//if there are values, fill it in
	//0 is exp date
	//1 is weight
	//2 is weight type
	//3 is container type
	String message="";
	int containerCol = 3;
	int expDateCol = 0;
	int weightCol = 1;
	int weightTypeCol=2;
	boolean found = false;
	if(results != null)
	{
	    for(int i = 0; i<results.length; i++)
	    {
		if(Integer.parseInt(results[i][containerCol])==containerId)
		{
		    //found an exact match, rejoice
		    found = true;
		    //set the weight
		    this.jFormattedTextFieldWeight.setText(results[i][weightCol]);
		    selectComboById(this.jComboBoxWeight,Integer.parseInt(results[i][weightTypeCol]));
		    //don't forget to check the purchase date, might be different
		    Date expDate = stringToDate((String)(this.jComboBoxPurMon.getSelectedItem()), (String) (this.jComboBoxPurYear.getSelectedItem()));
		    double expyear = Double.parseDouble(results[i][expDateCol]);
		    this.setMonthYearCombo(this.jComboBoxExpMon, this.jComboBoxExpYear,expDate,expyear);
		    //autoFiller();
		}
	    }
	    if(!found) //pick 1st one for easiness and use it's info then warn
	    {
		javax.swing.JOptionPane.showMessageDialog(this, "Sorry, unable to find the exact information for this item. Please check to see if the weight and expiration date are correct.");
	    }
	}
	else //if no values pop jdialog and say you'll have to do it yourself, mention if extrapolated
	{
	    javax.swing.JOptionPane.showMessageDialog(this, "Sorry, unable to find any information on this item. Please fill in the weight and expiration date.");
	}

    }//GEN-LAST:event_autoFillClicked

    private void jToggleButtonCancelMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jToggleButtonCancelMouseReleased
    {//GEN-HEADEREND:event_jToggleButtonCancelMouseReleased
	this.setVisible(false);
	resetValues();
    }//GEN-LAST:event_jToggleButtonCancelMouseReleased
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonAutoFill;
    private javax.swing.JComboBox jComboBoxExpMon;
    private javax.swing.JComboBox jComboBoxExpYear;
    private javax.swing.JComboBox jComboBoxFoodType;
    private javax.swing.JComboBox jComboBoxLocation;
    private javax.swing.JComboBox jComboBoxPurMon;
    private javax.swing.JComboBox jComboBoxPurYear;
    private javax.swing.JComboBox jComboBoxShortFoodList;
    private javax.swing.JComboBox jComboBoxSize;
    private javax.swing.JComboBox jComboBoxStoreName;
    private javax.swing.JComboBox jComboBoxWeight;
    private javax.swing.JFormattedTextField jFormattedTextFieldPrice;
    private javax.swing.JFormattedTextField jFormattedTextFieldWeight;
    private javax.swing.JFormattedTextField jFormattedTextNumber;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelExpDate;
    private javax.swing.JLabel jLabelExpSlash;
    private javax.swing.JLabel jLabelFoodItem;
    private javax.swing.JLabel jLabelHint;
    private javax.swing.JLabel jLabelNumber;
    private javax.swing.JLabel jLabelPurDate;
    private javax.swing.JLabel jLabelPurSlash;
    private javax.swing.JLabel jLabelSize;
    private javax.swing.JLabel jLabelWeight;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelAutoFill;
    private javax.swing.JPanel jPanelExpDate;
    private javax.swing.JPanel jPanelPurDate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButtonCancel;
    // End of variables declaration//GEN-END:variables
  //figures out the values for the various items
   private class FieldValues
  {
	int ndb_no; 
	String foodName;
	int containerId;
	String containerStr;
	int weightTypeId;
	String weightTypeStr;
	double weight;
	String weightIdStr;
	int numBought;
	int storeId;
	String store;
	int locationId;
	double price;
	String location;
	String expMon;
	String expYear;
	String purMon;
	String purYear;
	Date purDate;
	Date expDate;
	
	/**
	 * Finds all the values from the fields so this only has to be done once
	 */
	FieldValues()
	{
	    FoodTreeNode node;
	    //get the food type
	    node = (FoodTreeNode)jComboBoxFoodType.getSelectedItem();
	    ndb_no = node.getId();
	    foodName = node.getCategory();
	    //get the container
	    node = (FoodTreeNode)jComboBoxSize.getSelectedItem();
	    containerId = node.getId();
	    containerStr = node.getCategory();
	    node = (FoodTreeNode)jComboBoxWeight.getSelectedItem();
	    weightTypeId = node.getId();
	    weightTypeStr = node.getCategory();
	    weight = Double.parseDouble(jFormattedTextFieldWeight.getText());
	    numBought = Integer.parseInt(jFormattedTextNumber.getText());
	    node = (FoodTreeNode)jComboBoxStoreName.getSelectedItem();
	    storeId = node.getId();
	    store = node.getCategory();
	    node = (FoodTreeNode)jComboBoxLocation.getSelectedItem();
	    locationId = node.getId();
	    location = node.getCategory();
	    expMon = (String)jComboBoxExpMon.getSelectedItem();
	    expYear = (String)jComboBoxExpYear.getSelectedItem();
	    purMon = (String)jComboBoxPurMon.getSelectedItem();
	    purYear = (String)jComboBoxPurYear.getSelectedItem();
	    purDate = stringToDate(purMon,purYear);
	    expDate = stringToDate(expMon, expYear);
	    price = ((Double)jFormattedTextFieldPrice.getValue()).doubleValue();
	}
	
  }
}



