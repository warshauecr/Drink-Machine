/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Casey Warshauer
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrinkMachine extends JApplet
{
private JPanel titlePanel;
private JLabel LableTitle;

private JPanel displayPanel;
private JTextArea displayText;

private JPanel buttonPanel;
private DrinkMachineButtons[] inventoryButtons;

private JPanel changePanel;
private JButton changeButton;
private JLabel enterChangeField;
private JTextField changeField;
private double changeEntered = 0;

private final int INVENTORY = 20; 
private final double COST = 0.75;

/**
init method

*/
@Override
public void init()
{

//Create layout manager
setLayout(new BorderLayout());

//Build title JPanel and Components method
buildTitlePanel();

//Build Display Pannel
buildDisplay();

//Build buttons and panel
buildButtonPanel();

//Build Enter Change Panel
buildChangePanel();

}

private void buildTitlePanel()
{
//Build title panel
titlePanel = new JPanel();

//Build title text box
LableTitle = new JLabel("Drink Machine Applet");

//Add text field and and panel to component pane
titlePanel.add(LableTitle);
add(titlePanel, BorderLayout.NORTH);

}
/*
buildDisplay method builds the output display panel and text box
*/

private void buildDisplay()
{
displayPanel = new JPanel();

//Create text box and make it non editable
displayText = new JTextArea(50, 15);
displayText.setEditable(false);

//Add displayPanel to content pane
displayPanel.add(displayText);
add(displayPanel, BorderLayout.CENTER);
}


 /* Creates array and fills it with drinks. Drinks and buttons can be update here.
    To add or remove drinks to vending machine simply type the drink name into the drinks away.
    */

private void buildButtonPanel()
{
   
//Set drinks to be included    
String[] drinks = {"Coke", "Pepsi", "Sprite", "A&W Root Beer", "Dr.Pepper",
    "Mountain Dew", "Big Red"}; 

//Build panel
buttonPanel = new JPanel();

//Set Panel Layout Manager
buttonPanel.setLayout(new GridLayout(drinks.length, 1));

//Creats an array of DrinkMachineButtons
inventoryButtons = new DrinkMachineButtons[drinks.length];

//Fills DrinkMachineButtons with buttons and adds actions listener
for (int i = 0; i < drinks.length; i++)
{
 inventoryButtons[i] = new DrinkMachineButtons(drinks[i], drinks[i], INVENTORY);
 
 //Adds buttons to buttonPanel
 buttonPanel.add(inventoryButtons[i]);
 
 //Adds ActionListener form the inner class EventHandler
 inventoryButtons[i].addActionListener(new EventListener());
     
//Add Buttons panel to content panel
add(buttonPanel, BorderLayout.EAST);
}
}

private void buildChangePanel()
{
//Create change panel
JPanel changePanel = new JPanel();

//Set panel layout manager
changePanel.setLayout(new FlowLayout());

//Add JLable
enterChangeField = new JLabel("Enter change");

//Create enter change text field
JTextField changeField = new JTextField(10);

//Create change enter button
JButton changeButton = new JButton("Enter");

//Add action Listener and anonymous class
changeButton.addActionListener(new ActionListener(){
@Override
public void actionPerformed(ActionEvent e)
{
 Double changeResult;
 
//Convert String to double    
changeEntered = Double.parseDouble(changeField.getText()) + changeEntered;

//Checks to see if user has entered enough change and calcuates how much change is due.
if(changeEntered >= COST)
{
 changeResult = changeEntered - COST;
 displayText.setText("You entered: " + changeEntered + "\n"
 + "Drink cost: " + COST + "\n"
 + "Change due back: " + changeResult + "\n");
}
//If not enough change has been entered tells user how much more is needed
else 
{
displayText.setText("You entered: " + changeEntered + "\n"
+ "Please enter " + Math.abs(COST-changeEntered) + " more");    
}    
}
});

//Add text field, button and panel
changePanel.add(enterChangeField);
changePanel.add(changeField);
changePanel.add(changeButton);
add(changePanel, BorderLayout.SOUTH);
}

//Makes use of inner classes for this example and implements ActionListener. Executes when one of the drink buttons is pushed

private class EventListener implements ActionListener
{
 @Override   
    public void actionPerformed(ActionEvent e)
    {
     //Check to see if user has entered enough money.   
     if(changeEntered >= COST)
     {
        
     //Find which button was pushed
     DrinkMachineButtons button = (DrinkMachineButtons) e.getSource(); 
    
     //Finds button pushed in the inventoryButtons array. 
      for (int j = 0; j < inventoryButtons.length; j++)
      {
      if (inventoryButtons[j].getDrinkType().compareTo(button.getDrinkType()) == 0)
      {
          //If there are no more drinks, then it is of stock 
          if (inventoryButtons[j].getInventory() < 1)
                  {
                    displayText.setText("Out of " + inventoryButtons[j].getDrinkType());  
                  }
          //otherwise it deducts the cost of the drink and decreases the inventory.
          else
          {
          changeEntered = changeEntered-COST;    
          inventoryButtons[j].setInventory(inventoryButtons[j].getInventory() -1);
          displayText.setText(inventoryButtons[j].getDrinkType() + "(s)" + " left: " + inventoryButtons[j].getInventory() + "\n"
                + "Change owed: " + Math.abs(changeEntered));
      }
      }
    }   
}
     //If user has not input enough money, tells them how much more they need
     else
     {
      displayText.setText("You have: " + changeEntered + "\n" 
              + "Please enter: " + (COST-changeEntered));   
     }
}
}
}


    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    // End of variables declaration                   
}
