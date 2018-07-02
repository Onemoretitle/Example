package OMT.model;

import java.util.Random;

/**
 * Трава
 */
public class Grass extends ObjectOnMap {
    /**
     * Максимальное количество травы, одинаково для всех
     */
    private static final int MAX_VOLUME = 2000; 
    
    /**
     * Текущее количество травы на участке
     */
    private int volume;
    
    /**
     * Скорость роста травы на участке
     */
    private int growSpeed; 
    
    /**
     * Конструктор
     */
    public Grass(int x, int y, Map map)
    {
        super(x,y,map);
        Random r = new Random();
        volume = r.nextInt(100)%100+100;
        growSpeed = r.nextInt(20)%20 + 1;
        
    }
    

    @Override
    public void liveStep()
    {
        grow();
    }
    
    /**
     * Рост травы
     */
    private void grow()
    {
        if (volume<MAX_VOLUME)
            volume+=growSpeed;
        volume = MAX_VOLUME>volume?volume:MAX_VOLUME;
    }
    
    /**
     * Кормить травоядное
     * @param foodVolume сколько хочет сожрать
     * @return сколько удалось сожрать
     */
    public int giveFood(int foodVolume)
    {
        if (volume>foodVolume)
        {
            volume-=foodVolume;
            return foodVolume;
        }
        else
        {
            int realVolume = volume;
            volume = 0;
            return realVolume;
        }
    }
    
    /**
     * Для генерации цвета клетки
     * @return процент имеющегося объема травы от максимального, с точностью до 10%
     */
    public float getVolumePrecentage()
    {
        return (float) ((volume*10)/MAX_VOLUME)/10;
    }
    
    /**
     * Возвращает текущий объем травы
     * @return количество травы на участке
     */
    int getVolume() {
        return volume;
    }
}
