package OMT.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Травоядное животное
 */
public class Graminivorous extends Animal{
    /**
     * Признак, живое или тушка
     */
    private boolean alive = true;
    
    /**
     * Количество мяса
     */
    private int meatVolume;

    /**
     * Количество воды
     */
    private int waterVolume;

    /**
     * Количество груза
     */
    private int cargoVolume;
    /**
     * Скорость поедания травы, для всех одинаковая
     */
    private static final int EAT_STEP = 30;
    /**
     * Скорость поедания травы, для всех одинаковая
     */
    private static final int WATER_CONSUMPTION = 10;

    /**
     * Скорость поедания травы, для всех одинаковая
     */
    private static final int CARGO_DAMAGE = 10;

    /**
     * Конструктор
     */
    public Graminivorous(int x, int y, Map map)
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
            if (obj instanceof Grass)
            {
                if (obj.getX()== getX() && obj.getY()== getY())
                {   
                    if (((Grass)obj).getVolume() > EAT_STEP )
                    {
                        ((Grass)obj).giveFood(EAT_STEP);
                        return;
                    }
                }
            }
        }  
        
        for (ObjectOnMap obj : objects)
        {
            if (obj instanceof Grass)
            {
                if (((Grass)obj).getVolume() > EAT_STEP)
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
