package OMT.model;

/**
 * Объект на карте
 */
public abstract class ObjectOnMap {
    /** абсцисса */
    private int x;
    /** ордината */
    private int y;
    /** ссылка на объект карты */
    private Map map;
    
    /**
     * Конструктор
     * @param x абсцисса
     * @param y ордината
     * @param map ссылка на объект карты
     */
    public ObjectOnMap (int x, int y, Map map)
    {
        this.map = map;
        setX(x);
        setY(y);
    }
    
    /**
     * Шаг жизни объекта
     */
    public abstract void liveStep();

    /**
     * Установка абсциссы
     * @param x новое значение абсциссы
     */
    protected void setX(int x) {
       if (x >= map.getWidth())
           this.x = map.getWidth()-1;
       else if (x < 0)
           this.x = 0;
       else
           this.x = x;
    }

    /**
     * Установка ординаты
     * @param y новое значение ординаты
     */    
    protected void setY(int y) {
       if (y >= map.getHeight())
           this.y = map.getHeight()-1;
       else if (y < 0)
           this.y = 0;
       else
           this.y = y;
    }
    
    /**
     * 
     * @return абсцисса 
     */
    public int getX()
    {
        return x;
    }

    /**
     * 
     * @return ордината 
     */    
    public int getY()
    {
        return y;
    }
    
    /**
     * 
     * @return ссылка на карту 
     */
    protected Map getMap()
    {
        return map;
    }


    
}
