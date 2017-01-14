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

public class DrinkMachineButtons extends JButton
{
private String drinkType;
private int inventory;


public DrinkMachineButtons(String text, String drinkType, int inventory)
{
 super(text);
 this.drinkType = drinkType;
 this.inventory = inventory;
}

//Set drink type method
public void setDrinkType(String drinkType)
{
 this.drinkType = drinkType;
}

//Get drink type method
public String getDrinkType()
{
 return drinkType;
}

//Set inventory method
public void setInventory(int inventory)
{
 this.inventory = inventory;
}

//Get inventory method
public int getInventory()
{
 return inventory;
}
}
