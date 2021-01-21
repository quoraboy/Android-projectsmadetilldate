package com.example.lenovo.testandroid1;

/**
 * Created by Lenovo on 6/23/2018.
 */

public class Animal extends Object {

    private final String name;
    private final String color;
    private int amountofSpeed;
    private int amountofPower;

       public Animal(String name, String color, int amountofspeed, int amountofpower) {
                   this.name = name;
                   this.color = color;
                   this.amountofSpeed = amountofspeed;
                   this.amountofPower = amountofpower;
                 }
                  public String getName()
{
 return name;
}
                  public String getColor()
{
    return color;
}
                  public void setAmountofSpeed(int amountofSpeed)
                    {
                  this.amountofSpeed=amountofSpeed;
                    }
                public void setAmountofPower(int amountofPower)
                   {
                   this.amountofPower=amountofPower;
                       }
                public int getAmountofSpeed()
                        {
                            return amountofSpeed;
                        }
                public int getAmountofPower()

                     {
                         return amountofPower;
                      }

    @Override
    public String toString() {
        return String.format("Name: %s Color: %s Amountofspeed: %d Amountofpower: %d ",name,color,amountofSpeed,amountofPower);
            }
}
