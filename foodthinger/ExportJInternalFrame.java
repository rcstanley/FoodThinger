/*
 * ExportJInternalFrame.java
 *
 * Created on July 24, 2006, 9:10 PM
 */

package foodthinger;

import javax.swing.JCheckBox;
import java.io.File;

/**
 *
 * @author  RC Stanley
 */
public class ExportJInternalFrame extends javax.swing.JInternalFrame
{
    File exportFile=null;
    FoodModel fm;
    /** Creates new form ExportJInternalFrame */
    public ExportJInternalFrame(FoodModel fm)
    {
	this.fm = fm;
	initComponents();
    }
    
    javax.swing.JCheckBox boxes[] = new javax.swing.JCheckBox[Constants.COL_NAMES.length];
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        jPanelOptions = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabelDelim = new javax.swing.JLabel();
        jTextFieldDelim = new javax.swing.JTextField();
        jLabelFile = new javax.swing.JLabel();
        jCheckBoxCommon = new javax.swing.JCheckBox();
        jCheckBoxHeaders = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridLayout(3, 1));

        setClosable(true);
        setResizable(true);
        setTitle("Export Pantry Information");
        jPanelOptions.setLayout(new java.awt.GridLayout(5, 2));

        jPanelOptions.setBorder(new javax.swing.border.TitledBorder("Export"));
        jPanelOptions.setName("Export");

        for(int i = 0; i < Constants.COL_NAMES.length;i++)
        {
            boxes[i] = new JCheckBox(Constants.COL_NAMES[i],true);
            this.jPanelOptions.add(boxes[i],i);
        }
        getContentPane().add(jPanelOptions);

        jLabelDelim.setLabelFor(jTextFieldDelim);
        jLabelDelim.setText("Delimiter:");
        jLabelDelim.setToolTipText("Character to Sepparate the fields in the file");
        jPanel1.add(jLabelDelim);

        jTextFieldDelim.setText("^");
        jPanel1.add(jTextFieldDelim);

        jPanel1.add(jLabelFile);

        jCheckBoxCommon.setSelected(true);
        jCheckBoxCommon.setText("Use Common Name");
        jCheckBoxCommon.setToolTipText("Use a name like \"Beans, Black\" instead of \"BEANS,BLACK,MATURE SEEDS,RAW\"");
        jPanel1.add(jCheckBoxCommon);

        jCheckBoxHeaders.setSelected(true);
        jCheckBoxHeaders.setText("Include Column Names");
        jCheckBoxHeaders.setToolTipText("Include the column headers");
        jPanel1.add(jCheckBoxHeaders);

        getContentPane().add(jPanel1);

        jButton1.setText("Export");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                browseForExportFile(evt);
            }
        });

        jPanel2.add(jButton1);

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cancelButton(evt);
            }
        });

        jPanel2.add(jButton2);

        getContentPane().add(jPanel2);

        pack();
    }
    // </editor-fold>//GEN-END:initComponents

    private void cancelButton(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cancelButton
    {//GEN-HEADEREND:event_cancelButton
	try
	{
	    this.setClosed(true);
	}
	catch (Exception e)
	{
	    System.out.println(e);
	}
    }//GEN-LAST:event_cancelButton

    public void writeLine(String[] data, java.io.FileWriter out) throws java.io.IOException
    {
	    boolean first = true;
	    for(int j=0; j<Constants.COL_NAMES.length;j++)
	    {
		//if the user wants it written out
		if(this.boxes[j].isSelected())
		{
		    if(first) //if this is the first item in the row, mark that it is
		    {
			first = false;
		    }
		    else //put the delimiter in front
		    {
			out.write(this.jTextFieldDelim.getText());
		    }
		    //if the data is not null write it out
		    if(data[j] != null)
		    {
			out.write(data[j]);
		    }
		    
		}
		
	    }
	 //put carriage return
	out.write("\r\n");
    }
    /**
     * writes the strings to the export file
     */
 public void writeFile(File file, String[][]data) throws java.io.IOException
 {
     //open the file
	//write the file
	java.io.FileWriter out = new java.io.FileWriter(exportFile);
	//write the headers
	if(this.jCheckBoxHeaders.isSelected())
	{
	    writeLine(Constants.COL_NAMES, out);
	}
	boolean first = true;
	//for each row
	for(int i=0; i<data.length;i++)
	{
	    writeLine(data[i], out);
	    
	}

    //close the file
	out.flush();
	out.close();
 }
    
 /**
 * allows the user to browse for the file to export to
 * then does the export.  Closes window when finished.
 */
    private void browseForExportFile(java.awt.event.ActionEvent evt)//GEN-FIRST:event_browseForExportFile
    {//GEN-HEADEREND:event_browseForExportFile
	//get the new file
	javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
	String error="";
	if(exportFile!=null)
	{
	    fc.setSelectedFile(exportFile);
	}
	int returnval = fc.showSaveDialog(this);
	if(returnval == fc.APPROVE_OPTION)
	{
	    exportFile = fc.getSelectedFile();
	    if(exportFile == null) return;
	    //TODO open the file, get the info, and save it
	    //get the info from the db
	    String[][] table = fm.getFoodTable(Constants.FOOD_STORED_TABLE_QUERY, this.jCheckBoxCommon.isSelected());
	    if(table==null || table.length==0)
	    {
		javax.swing.JOptionPane.showMessageDialog(this,"Could not export information:"+error,"Could not export information",javax.swing.JOptionPane.ERROR_MESSAGE);
	    }
	    try
	    {
		if(exportFile.exists())
		{
		    int overwrite = javax.swing.JOptionPane.showConfirmDialog(this,"File "+exportFile + " already exists.  Do you want to Overwrite?");
		    if(overwrite != javax.swing.JOptionPane.YES_OPTION) return;
		}
    		writeFile(exportFile,table);
	   	
	    }
	    catch(Exception e)
	    {
		javax.swing.JOptionPane.showMessageDialog(this,"Could not export information:"+e.toString(),"Could not export information",javax.swing.JOptionPane.ERROR_MESSAGE);
	    }
	    if(error.length() > 0) //error, warn couldn't save
	    {
		javax.swing.JOptionPane.showMessageDialog(this,"Could not export information:"+error,"Could not export information",javax.swing.JOptionPane.ERROR_MESSAGE);
	    }
	    else //no errors, close window
	    {
		try
		{
		    this.setClosed(true);
		}
		catch (Exception e)
		{
		    System.out.println(e);
		}
	    }
	}
	
	
    }//GEN-LAST:event_browseForExportFile
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBoxCommon;
    private javax.swing.JCheckBox jCheckBoxHeaders;
    private javax.swing.JLabel jLabelDelim;
    private javax.swing.JLabel jLabelFile;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelOptions;
    private javax.swing.JTextField jTextFieldDelim;
    // End of variables declaration//GEN-END:variables
    
}