package OMT.model;

import java.util.ArrayList;
import java.util.Random;

public class Caravan extends Animal{

    /**
     * Координаты дома
     */
    private int xHome;
    private int yHome;
    /**
     * Признак, живое или тушка
     */
    private boolean alive = true;

    /**
     * Количесто жизни
     */
    private int lifeVolume;
    private int lifeTank;
    /**
     * Количество воды
     */
    private int waterVolume;
    private int waterTank;

    /**
     * Количество груза
     */
    private int cargoVolume;

    /**
     * Скорость потребления воды, для всех одинаковая
     */
    private static final int WATER_CONSUMPTION = 10;
    /**
     * Скорость потребления воды, для всех одинаковая
     */
    private static final int LIFE_STEP = 10;
    /**
     * Конструктор
     */
    public Caravan(int x, int y, Map map)
    {
        super(x,y,map);
        lifeVolume = new Random().nextInt(100)+10;
        waterVolume = new Random().nextInt(50)+15;
        cargoVolume = new Random().nextInt(1000)+15;
        lifeTank = lifeVolume;
        waterTank = waterVolume;
        xHome = x;
        yHome = y;
    }

    @Override
    public void liveStep() {
        if (!isAlive())
            return;
        ArrayList<ObjectOnMap> objects = getMap().getVisibleObjects(this, getSight());
        for (ObjectOnMap obj : objects)
        {
            if (obj instanceof Shelter)
            {
                if (obj.getX()== getX() && obj.getY()== getY())
                {
                    getRest(lifeTank, waterTank);
                }
            }
            else {
                lifeVolume -= LIFE_STEP;
            }
        }

        for (ObjectOnMap obj : objects)
        {
            if (obj instanceof Shelter && (xHome != getX() && yHome != getY()))
            {
                    goToObject(obj);
                    return;
            }
        }
       // goRandom();
    }

    private void getRest(int lifeTank, int waterTank) {

    }


    /**
     * Ещё живой?
     * @return живое или нет
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Уже нет...
     */
    public void die() {
        alive = false;
    }

    /**
     * Наличие груза
     * @return истина, если еще есть груз
     */
    public boolean hasCargo() {
        return cargoVolume > 0;
    }

    /**
     * Нлёт бандитов
     * @param cargoDamage урон грузу
     * @return урона нанесено
     */
    public int raid(int cargoDamage) {
        if (isAlive())
            return 0;
        if (cargoVolume > cargoDamage)
        {
            cargoVolume -= cargoDamage;
            return cargoDamage;
        }
        else
        {
            int realVolume = cargoVolume;
            cargoVolume = 0;
            return realVolume;
        }
    }

    public int getLifeVolume(){
        return lifeVolume;
    }
    public int getWaterVolume(){
        return waterVolume;
    }

    /**
     * Начло похода каравана
     */
    public boolean getStart(ObjectOnMap obj){
        if (obj instanceof Shelter)
        {
            return true;
        }
        else
            {
                return false;
            }

    }}

