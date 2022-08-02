package kidusrox;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class HUD {
    public static int HEALTH = 100;
    private Handler handler;
    private int greenValue = 255;
    private int score = 0;
    private int level = 1;
    private Random r = new Random();

    public HUD(Handler handler) {
        this.handler = handler;
    }

    public void tick() {
        if (HEALTH > 0) {
            HEALTH = Game.clamp(HEALTH, 0, 100);
            greenValue = Game.clamp(greenValue, 0, 255);

            for (int i = 0; i < handler.list.size(); i++) {
                if (handler.list.get(i).getId() == ID.Player) {
                    GameObject tempPlayer = handler.list.get(i);
                    for (int j = 0; j < handler.list.size(); j++) {
                        if (handler.list.get(j).getId() == ID.BasicEnemy) {
                            GameObject tempEnemy = handler.list.get(j);
                            if (Math.abs(
                                    tempPlayer.getX() - tempEnemy.getX()) <= 30
                                    && Math.abs(tempPlayer.getY()
                                            - tempEnemy.getY()) <= 30) {
                                HEALTH -= 2;
                            }
                        }
                    }
                }
            }

            greenValue = HEALTH * 2;

            score++;

            if (score >= 1000) {
                score = 0;
                level++;
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH),
                        r.nextInt(Game.HEIGHT), ID.BasicEnemy));
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);

        g.setColor(new Color(100, greenValue, 0));
        g.fillRect(15, 15, HEALTH * 2, 32);

        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);

        if (HEALTH == 0) {
            g.setColor(Color.white);
            g.drawString("GAME OVER", 78, 35);
        }

        g.drawString("Score: " + score, 15, 64);
        g.drawString("Level: " + level, 15, 80);
        g.drawString("Play with W, A, S, and D!", 470, 35);
    }

    public void setLevel(int value) {
        level = value;
    }

    public int getLevel() {
        return level;
    }
}
