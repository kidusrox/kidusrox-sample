package kidusrox;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Handler handler;
    private boolean uP = false;
    private boolean dP = false;
    private boolean lP = false;
    private boolean rP = false;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.list.size(); i++) {
            GameObject temp = handler.list.get(i);

            if (temp.getId() == ID.Player) {
                if (key == KeyEvent.VK_W) {
                    uP = true;
                    temp.setVelY(-3);
                }

                if (key == KeyEvent.VK_A) {
                    lP = true;
                    temp.setVelX(-3);
                }

                if (key == KeyEvent.VK_S) {
                    dP = true;
                    temp.setVelY(3);
                }

                if (key == KeyEvent.VK_D) {
                    rP = true;
                    temp.setVelX(3);
                }
            }
        }
        
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }
    }


    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.list.size(); i++) {
            GameObject temp = handler.list.get(i);

            if (temp.getId() == ID.Player) {
                if (key == KeyEvent.VK_W) {
                    uP = false;
                    if (dP) {
                        temp.setVelY(3);
                    }
                    else {
                        temp.setVelY(0);
                    }
                }

                if (key == KeyEvent.VK_A) {
                    lP = false;
                    if (rP) {
                        temp.setVelX(3);
                    }
                    else {
                        temp.setVelX(0);
                    }
                }

                if (key == KeyEvent.VK_S) {
                    dP = false;
                    if (uP) {
                        temp.setVelY(-3);
                    }
                    else {
                        temp.setVelY(0);
                    }
                }

                if (key == KeyEvent.VK_D) {
                    rP = false;
                    if (lP) {
                        temp.setVelX(-3);
                    }
                    else {
                        temp.setVelX(0);
                    }
                }
            }
        }
    }

}
