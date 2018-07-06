package OMT.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Карта для моделирования
 */
public class Map {
    /** Высота карты */
    int height;
    /** Ширина карты */
    int width;
    /** Список объектов на карте */
    private final ArrayList<ObjectOnMap> objects = new ArrayList<>();
    
    /**
     * Конструктор
     * @param height высота карты
     * @param width ширина карты
     */
    public Map(int height, int width)
    {
        this.height = height;
        this.width = width;
    }
    
    /**
     * Генерация животных для карты
     * @param shelterCount количество поселений
     * @param gramonivorosCount количество травоядных
     * @param predatorCount количество хищников
     */
    public void randomGenerate(int shelterCount, int gramonivorosCount, int predatorCount, int shai_huludCount)
    {
        Random r = new Random();
        for (int i=0;i<shelterCount;i++)
        {
            int _x = r.nextInt(width),_y = r.nextInt(height);
            Shelter s = new Shelter(_x, _y, this);
            Caravan c = new Caravan(_x, _y, this);
            objects.add(s);
            for(int j = 0; j < 1 + (int) (Math.random() * 2); j++)
                objects.add(c);
        }
      /*  for (int i=0;i<gramonivorosCount;i++)
        {
            ObjectOnMap g = new Graminivorous(r.nextInt(width), r.nextInt(height), this);
            objects.add(g);
        }
        for (int i=0;i<predatorCount;i++)
        {
            ObjectOnMap p = new Predator(r.nextInt(), r.nextInt(), this);
            objects.add(p);
        }*/
        for (int i=0;i<shai_huludCount;i++)
        {
            ObjectOnMap sh = new Shai_Hulud(r.nextInt(), r.nextInt(), this);
            objects.add(sh);
        }
    }
    
    /**
     * Один шаг жизни экосистемы
     */
    public void liveStep()
    {
        for (ObjectOnMap obj : objects)
        {
            obj.liveStep();
        }
    }
    
    /**
     * Находит объекты в радиусе видимости
     * @param o объект, для которого ищем
     * @param r радиус зрения объекта
     * @return список видимых объектов
     */
    public ArrayList<ObjectOnMap> getVisibleObjects(ObjectOnMap o, int r)
    {
        ArrayList<ObjectOnMap> visibleObjects = new ArrayList<>();
        for (ObjectOnMap obj : objects)
        {
            if (destination(o,obj)<r*r)
                visibleObjects.add(obj);
        }
        return visibleObjects;
    }
    
    /**
     * Вычисление расстояния между объектами
     * @param o1 первый объект
     * @param o2 вротой объект
     * @return квадрат расстояния
     */
    private int destination(ObjectOnMap o1,ObjectOnMap o2)
    {
        return (o1.getX() - o2.getX())*(o1.getX() - o2.getX()) + (o1.getY() - o2.getY())*(o1.getY() - o2.getY());
    }

    /**
     * 
     * @return ширина карты 
     */
    public int getWidth() {
        return width;
    }

    /**
     * 
     * @return высота карты 
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * @return копися списка объектов на карте 
     */
    public ArrayList<ObjectOnMap> getAllObjects()
    {
        ArrayList<ObjectOnMap> list = new ArrayList<>();
        list.addAll(objects);
        return list;
    }
}
