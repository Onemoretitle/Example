package OMT.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Хищник
 */


public class Predator extends Animal {

    /**
     * Признак, живое или тушка
     */
    private boolean alive = true;

    /**
     * Количество мяса
     */
    private int meatVolume;

    /** Скорость еды, для всех одинаковая */
    private static final int EAT_STEP = 50; 

    /**
     * Конструктор
     */
    public Predator(int x, int y, Map map)
    {
        super(x,y,map);
        meatVolume = new Random().nextInt(100)+10;
    }    

    @Override
    public void liveStep() {
        if (!isAlive())
            return;
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
    /**
     * Потыкать палочкой
     * @return живое или нет
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Сдохнуть
     */
    public void die() {
        alive = false;
    }

    /**
     * Наличие мяса
     * @return истина, если еще есть, что сожрать
     */
    public boolean hasMeat() {
        return meatVolume > 0;
    }

    /**
     * Отдать мяса
     * @param foodVolume количество, которое пытаются откусить
     * @return сколько удалось откусить
     */
    public int giveFood(int foodVolume) {
        if (isAlive())
            return 0;
        if (meatVolume>foodVolume)
        {
            meatVolume-=foodVolume;
            return foodVolume;
        }
        else
        {
            int realVolume = meatVolume;
            meatVolume = 0;
            return realVolume;
        }
    }
}
