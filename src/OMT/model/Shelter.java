package OMT.model;

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
     * Скорость восстановления жизни
     */
    private static final int LIFE_RECOVERY = 10;

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
        produceSpeed = r.nextInt(20)%20 + 1;
        waterRefill = r.nextInt(10)% 10 + 1;

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
    }

    /**
     * Рост травы
     */
    private void build()
    {
        if (WATER <  MAX_VOLUME)
            WATER += waterRefill;
        WATER = MAX_VOLUME > WATER ? WATER:MAX_VOLUME;
    }

    /**
     * Рост травы
     */
    private void produceGoods()
    {
        if (CARGO <  MAX_VOLUME)
            CARGO += produceSpeed;
        CARGO = MAX_VOLUME > CARGO ? CARGO :MAX_VOLUME;
    }

    /**
     * Для генерации цвета клетки
     * @return процент имеющегося объема травы от максимального, с точностью до 10%
     */
    public float getVolumePrecentage()
    {
        return (float) (((CARGO + WATER)/2)*10/MAX_VOLUME) /10;
    }

    /**
     * Возвращает текущий объем воды
     * @return количество воды в поселении
     */
    int getWATER() {
        return WATER;
    }

    /**
     * Возвращает текущий объем товаров
     * @return количество товаров в поселении
     */
    int getCARGO() {
        return CARGO;
    }

    }

