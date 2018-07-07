package OMT.model;

import java.util.ArrayList;
import java.util.Random;

public class Shelter extends ObjectOnMap{
    /**
     * Количество воды
     */
    private int WATER;
    /**
     * Товары
     */
    private int CARGO;
    /**
     * Живая сила
     */
    private int LIFE;

    /**
     * Максимальное количество воды и товаров, одинаково для всех
     */
    private static final int MAX_VOLUME = 2000;

    /**
     * Скорость появления товаров
     */
    private int produceSpeed;
    /**
     * Скорость пополнения воды
     */
    private int waterRefill;
    /**
     * Скорость восстановления жизни
     */
    private int lifeRecovery;


    private boolean visited;

    /**
     * Конструктор
     *
     * @param x   абсцисса
     * @param y   ордината
     * @param map ссылка на объект карты
     */
    public Shelter(int x, int y, Map map) {
        super(x, y, map);
        Random r = new Random(0);
        WATER = r.nextInt(100)%100 + 10;
        CARGO = r.nextInt(50)%50 + 5;
        LIFE = r.nextInt(100)%100 + 10;
        produceSpeed = r.nextInt(20)%20 + 1;
        waterRefill = r.nextInt(10)% 10 + 1;
        lifeRecovery = r.nextInt(30)% 10 + 1;

    }
    /**
     * Пополнить воду
     * @param _water сколько выды требуется
     * @return сколько пополнилось
     */
    public int giveWater(int _water)
        {
            if (WATER>_water)
            {
                WATER-=_water;
                return _water;
            }
            else
            {
                int realVolume = WATER;
                WATER = 0;
                return realVolume;
            }
        }
    /**
     * Пополнить жизни
     * @param _life сколько выды требуется
     * @return сколько пополнилось
     */
    public int restoreLife(int _life)
    {
        if (LIFE>_life)
        {
            LIFE-=_life;
            return _life;
        }
        else
        {
            int realVolume = LIFE;
            LIFE = 0;
            return realVolume;
        }
    }
    /**
     * Торговать
     * @param cargoVolume количество товаров
     * @return сколько удалось продать
     */
    public int trade(int cargoVolume)
    {
        if (CARGO > cargoVolume)
        {
            CARGO -= cargoVolume;
            return cargoVolume;
        }
        else
        {
            int realVolume = CARGO;
            CARGO = 0;
            return realVolume;
        }
    }


    @Override
    public void liveStep()
    {
        build();
        produceGoods();
        recoverLife();
    }

    /**
     * Пополнение воды
     */
    private void build()
    {
        if (WATER <  MAX_VOLUME)
            WATER += waterRefill;
        WATER = MAX_VOLUME > WATER ? WATER:MAX_VOLUME;
    }

    /**
     * Производство товаров
     */
    private void produceGoods()
    {
        if (CARGO <  MAX_VOLUME)
            CARGO += produceSpeed;
        CARGO = MAX_VOLUME > CARGO ? CARGO :MAX_VOLUME;
    }

    /**
     * Производство товаров
     */
    private void recoverLife()
    {
        if (LIFE <  MAX_VOLUME)
            LIFE += lifeRecovery;
        LIFE = MAX_VOLUME > LIFE ? LIFE :MAX_VOLUME;
    }

    /**
     * Для генерации цвета клетки
     * @return процент имеющегося объема травы от максимального, с точностью до 10%
     */
    public float getVolumePrecentage()
    {
        return (float) (((CARGO + WATER + LIFE)/3)*10/MAX_VOLUME) /10;
    }

    /**
     * Возвращает текущий объем воды
     * @return количество воды в поселении
     */
    public  int getWATER() {
        return WATER;
    }

    /**
     * Возвращает текущий объем товаров
     * @return количество товаров в поселении
     */
    public int getCARGO() {
        return CARGO;
    }

    public void getVisited(){
        visited = true;
    }
    public void getUnvisited(){
        visited = false;
    }
    }

