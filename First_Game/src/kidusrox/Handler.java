package kidusrox;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> list = new LinkedList<GameObject>();

    public void tick() {
        for (int i = 0; i < list.size(); i++) {
            GameObject temp = list.get(i);
            temp.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < list.size(); i++) {
            GameObject temp = list.get(i);
            temp.render(g);
        }
    }

    public void addObject(GameObject object) {
        if (object.getClass().equals(BasicEnemy.class)
                || object.getClass().equals(Player.class))
            list.add(object);
    }

    public void removeObject(GameObject object) {
        list.remove(object);
    }

}
