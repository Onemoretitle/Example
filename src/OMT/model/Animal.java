
package OMT.model;

import java.util.Random;

/**
 * Класс животного
 */
public abstract class Animal extends ObjectOnMap{
    /**
     * Радиус зрения
     */
    private final int sight;
    
    /**
     * Конструктор
     * @param x абсцисса на карте
     * @param y ордината на карте
     * @param map ссылка на карту
     */
    public Animal(int x, int y, Map map)
    {
        super(x,y,map);
        Random r = new Random();
        sight = r.nextInt(10)%10 + 1;
    }
    
    /**
     * @return радиус видимости 
     */
    public int getSight()
    {
        return sight;
    }
    
    /**
     * Перемещение животного по направлению к объекту
     * @param o целевой объект
     */
    protected void goToObject(ObjectOnMap o)
    {
        int dx = o.getX()-getX();
        int dy = o.getY()-getY();
        dx = dx > 0 ? 1 : dx < 0 ? -1 : 0;
        dy = dy > 0 ? 1 : dy < 0 ? -1 : 0;
        setX(getX()+dx);
        setY(getY()+dy);
    }
    
    /**
     * Функция для случайного перемещения животного, если он не видит еду
     */
    protected void goRandom()
    {
        Random r = new Random();
        int dx = 0;
        int dy = 0;
        while (dy==0 && dx==0)
        {
            dx = r.nextInt(3)-1;
            dy = r.nextInt(3)-1;            
        }
        setX(getX()+dx);
        setY(getY()+dy);        
    }
}
