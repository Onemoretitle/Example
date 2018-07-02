package OMT.model;

import java.util.ArrayList;

/**
 * Хищник
 */
public class Predator extends Animal {
    /** Скорость еды, для всех одинаковая */
    private static final int EAT_STEP = 50; 

    /**
     * Конструктор
     */
    public Predator(int x, int y, Map map)
    {
        super(x,y,map);
    }    

    @Override
    public void liveStep() {
        ArrayList<ObjectOnMap> objects = getMap().getVisibleObjects(this, getSight());
        for (ObjectOnMap obj : objects)
        {
            if (obj instanceof Graminivorous)
            {
                if (obj.getX()== getX() && obj.getY()== getY())
                {   
                    Graminivorous g = (Graminivorous)obj;
                    if (g.isAlive())
                    {
                        g.die();
                    }
                    if (g.hasMeat())
                    {
                        g.giveFood(EAT_STEP);
                        return;
                    }
                }
            }
        }    
        for (ObjectOnMap obj : objects)
        {
            if (obj instanceof Graminivorous)
            {
                if (((Graminivorous)obj).hasMeat())
                {
                    goToObject(obj);
                    return;
                }
            }
        }
        goRandom();
    }
}
