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
    private static final int WATER_CONSUMPTION = 1;
    /**
     * Торговые навыки
     */
    private static final int TRADE_SKILLS = 10;
    /**
     * Скорость потребления воды, для всех одинаковая
     */
    private static final int LIFE_STEP = 1;
    /**
     * Скорость потребления воды, для всех одинаковая
     */
    private static final int LIFE_RECOVER = 10;
    /**
     * Скорость потребления воды, для всех одинаковая
     */
    private static final int WATER_RECOVER  = 10;
    /**
     * Отдохнул? Иди дальше
     */
    private boolean HasRest;

    /**
     * Текущая цель
     */
    private Shelter Target;
    private int range;
    private int coin;
    private ArrayList<ObjectOnMap> targets = new ArrayList<ObjectOnMap>();
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

        ArrayList<ObjectOnMap> objects = getMap().getVisibleObjects(this, getSight());
        for (ObjectOnMap obj : objects)
        {
            if (obj instanceof Shelter)
                targets.add(obj);
        }
        range = targets.size();
    }

    @Override
    public void liveStep() {

        if (!isAlive())
            return;
       // HasRest = false;
        if(!haveUnvisited())
            for(int i = 0; i < targets.size(); i++)
                targets.get(i).getUnvisited();
        ArrayList<ObjectOnMap> objects = getMap().getVisibleObjects(this, getSight());
        if (Target != null)
            for (ObjectOnMap obj : objects)
            {
                if (obj instanceof Shelter && !(obj.getX()== xHome && obj.getY()== yHome) && !obj.visited/* &&  !HasRest*/)
                {
                    if ((obj.getX()== getX() && obj.getY()== getY()))
                    {

                        obj.getVisited();
                       //getSomeRestAndKeepGoing(lifeTank, waterTank, obj);
                        ((Shelter)obj).giveWater(WATER_RECOVER);
                        ((Shelter)obj).trade(TRADE_SKILLS);
                        ((Shelter)obj).restoreLife(LIFE_RECOVER);
                        //lifeVolume = lifeTank;
                        xHome = obj.getX();
                        yHome = obj.getY();
                       // HasRest = true;
                        Target = null;
                        return;
                    }
                }
            }
        if(Target == null)
            Target = searchNearestShelter();
        //coin = 0 + (int) (Math.random() * range);
        if (lifeVolume > 0){
            for (ObjectOnMap shelters : targets)
            {
                if (!(shelters.getX()== xHome && shelters.getY()== yHome))
                {
                    if (waterVolume > 0)
                        waterVolume -= WATER_CONSUMPTION;
                    else
                        lifeVolume -= LIFE_STEP;
                    goToObject(Target);
                   // goToObject(targets.get(coin));
                   // HasRest = false;
                    return;

                   /* if (coin == 1)
                    {
                        if (waterVolume > 0)
                            waterVolume -= WATER_CONSUMPTION;
                        else
                            lifeVolume -= LIFE_STEP;
                        target = shelters;
                        goToObject(target);
                        HasRest = false;
                        return;
                    }
                    else if (target != null){
                        if (waterVolume > 0)
                            waterVolume -= WATER_CONSUMPTION;
                        else
                            lifeVolume -= LIFE_STEP;
                        goToObject(target);
                        HasRest = false;
                        return;
                    }*/
                }
            }
        } else
            die();

    }

    private void getSomeRestAndKeepGoing(int lifeTank, int waterTank, ObjectOnMap obj)
    {

    }
    private Shelter searchNearestShelter () {
        ArrayList<ObjectOnMap> objects = getMap().getVisibleObjects(this, getSight());

        Map map = getMap();
        int minDistance = Integer.MAX_VALUE;
        Shelter result = null;

        for (ObjectOnMap obj : objects)
            if (obj instanceof Shelter && !(obj.getX() == getX() && obj.getY() == getY())) {
                int curDistance = getMap().destination(this, obj);
                if (curDistance < minDistance) {
                    minDistance = curDistance;
                    result = (Shelter) obj;
                }
            }

        return result;
    }

    private boolean haveUnvisited() {
        for(int i = 0; i < targets.size(); i ++){
            if (!targets.get(i).visited){
                return true;
            }
        }
        return false;
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

    }
}

