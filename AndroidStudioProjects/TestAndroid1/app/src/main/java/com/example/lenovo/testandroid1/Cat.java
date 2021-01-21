package com.example.lenovo.testandroid1;

/**
 * Created by Lenovo on 6/23/2018.
 */

public class Cat extends Animal {

    private final int numberoflegs;
    private boolean canHuntOtherAnimals;

    public Cat(String name, String color, int amountofspeed, int amountofpower, int numberoflegs, boolean canHuntOtherAnimals)
    {
            super(name,color,amountofspeed,amountofpower);
      this.numberoflegs = numberoflegs;
        this.canHuntOtherAnimals = canHuntOtherAnimals;
    }


    public boolean getCanHuntOtherAnimals()
    {
                return canHuntOtherAnimals;
    }
    public void setCanHuntOtherAnimals(boolean canHuntOtherAnimals )
    {
      this.canHuntOtherAnimals=canHuntOtherAnimals;
    }
    public int getNumberoflegs()
    {
         return numberoflegs;
    }


    @Override
    public String toString() {
        return super.toString()+String.format(" Numberoflegs: %d CanHuntOtherAnimals: %b",numberoflegs, canHuntOtherAnimals);

    }
}
