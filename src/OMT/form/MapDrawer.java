
package OMT.form;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import OMT.model.*;

/**
 * Класс для отрисовки карты на форме
 */
public class MapDrawer {
    
    /**
     * Объект, на котором рисуем
     */
    private Graphics g;
    
    /**
     * Карта, на которой рисуем
     */
    private Map map;
    
    /**
     * Размер клетки
     */
    private static final int CELL_SIZE=10;

    /**
     * Размер кружочка для животного
     */
    private static final int ANIMAL_SIZE=5;
    private static final int MONSTER_SIZE=20;
    /**
     * Конструктор
     * @param map карта для отрисовки
     */
    public MapDrawer( Map map)
    {
        this.map = map;
    }
    
    /**
     * Функция отрисовывает карту на заданном объекте
     * @param graphics объект для отрисовки
     * @param width ширина объекта
     * @param height высота объекта
     */
    public void draw(Graphics graphics,int width, int height)
    {
        g = graphics;
        g.clearRect(0, 0, width, height);
        drawGrid();
        drawObjects();        
    }

    /**
     * Функция рисует сетку
     */
    private void drawGrid() {
        g.setColor(Color.GRAY);
        int x1 = 0;
        int x2 = CELL_SIZE*map.getWidth();
        int y1 = CELL_SIZE;
        int y2 = CELL_SIZE;
        
        for (int i=1;i<map.getHeight();i++)
        {
            g.drawLine(x1, y1, x2, y2);
            y1+=CELL_SIZE;
            y2+=CELL_SIZE;
        }
        y1 = 0;
        y2 = CELL_SIZE*map.getHeight();
        x1 = CELL_SIZE;
        x2 = CELL_SIZE;
        for (int i=1;i<map.getWidth();i++)
        {
            g.drawLine(x1, y1, x2, y2);
            x1+=CELL_SIZE;
            x2+=CELL_SIZE;
        }
    }

    /**
     * Функция рисует объекты на карте
     */
    private void drawObjects() {
        ArrayList<ObjectOnMap> objects = map.getAllObjects();
        for (ObjectOnMap obj : objects)
        {

            if (obj instanceof Shai_Hulud){
                Shai_Hulud sh = (Shai_Hulud) obj;
                g.setColor(Color.white);
                g.fillOval(obj.getX()*CELL_SIZE, obj.getY()*CELL_SIZE, MONSTER_SIZE, MONSTER_SIZE);
                g.drawOval((obj.getX()-sh.getSight())*CELL_SIZE,
                        (obj.getY()-sh.getSight())*CELL_SIZE, 2*sh.getSight()*CELL_SIZE, 2*sh.getSight()*CELL_SIZE);
                g.setColor(Color.BLACK);
            }
            else if (obj instanceof Shelter)
            {
                Shelter S = (Shelter)obj;
                float volume = S.getVolumePrecentage();
                g.setColor(new Color(1,1 - (volume / 2),1 - volume));
                g.fillRect(obj.getX()*CELL_SIZE+1, obj.getY()*CELL_SIZE+1, CELL_SIZE-1, CELL_SIZE-1);
                g.setColor(Color.BLACK);
            } else if (obj instanceof Caravan)
            {
                Caravan c = (Caravan) obj;
                if (c.isAlive())
                    g.setColor(Color.black);
                else
                    g.setColor(Color.GRAY);
                g.fillOval(obj.getX()*CELL_SIZE, obj.getY()*CELL_SIZE, ANIMAL_SIZE, ANIMAL_SIZE);
                g.drawOval((obj.getX()-c.getSight())*CELL_SIZE,
                        (obj.getY()-c.getSight())*CELL_SIZE, 2*c.getSight()*CELL_SIZE, 2*c.getSight()*CELL_SIZE);
                g.setColor(Color.BLACK);
            }


          /*  if (obj instanceof Graminivorous)
            {
                Graminivorous gr = (Graminivorous)obj;
                if (gr.isAlive())
                    g.setColor(Color.black);
                else
                    g.setColor(Color.GRAY);
                g.fillOval(obj.getX()*CELL_SIZE, obj.getY()*CELL_SIZE, ANIMAL_SIZE, ANIMAL_SIZE);
                g.drawOval((obj.getX()-gr.getSight())*CELL_SIZE, 
                           (obj.getY()-gr.getSight())*CELL_SIZE, 2*gr.getSight()*CELL_SIZE, 2*gr.getSight()*CELL_SIZE);
                g.setColor(Color.BLACK);
                
            }
            else if (obj instanceof Predator)
            {
                Predator pr = (Predator)obj;
                if (pr.isAlive())
                    g.setColor(Color.RED);
                else
                    g.setColor(Color.GRAY);
                g.fillOval(obj.getX()*CELL_SIZE+ANIMAL_SIZE, obj.getY()*CELL_SIZE+ANIMAL_SIZE, ANIMAL_SIZE, ANIMAL_SIZE);

                g.drawOval((obj.getX()-pr.getSight())*CELL_SIZE, 
                           (obj.getY()-pr.getSight())*CELL_SIZE, 2*pr.getSight()*CELL_SIZE, 2*pr.getSight()*CELL_SIZE);
                g.setColor(Color.BLACK);
                
            }
            else if (obj instanceof Grass)
            {
                Grass grass = (Grass)obj;
                float volume = grass.getVolumePrecentage();
                g.setColor(new Color(1-volume,1,1-volume));
                g.fillRect(obj.getX()*CELL_SIZE+1, obj.getY()*CELL_SIZE+1, CELL_SIZE-1, CELL_SIZE-1);
                g.setColor(Color.BLACK);
            }*/
        }    
    }
}
