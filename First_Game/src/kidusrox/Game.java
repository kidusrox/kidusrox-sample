package kidusrox;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    /**
     * 
     */
    private static final long serialVersionUID = -8202810272777762206L;
    public static final int WIDTH = 640, HEIGHT = 640 / 12 * 9;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private HUD hud;
    private Random random;

    public Game() {
        handler = new Handler();
        hud = new HUD(handler);
        random = new Random();

        handler.addObject(new Player(50, 50, ID.Player));
        handler.addObject(new BasicEnemy(random.nextInt(WIDTH), random.nextInt(
            HEIGHT), ID.BasicEnemy));
        addKeyListener(new KeyInput(handler));

        new Window(WIDTH, HEIGHT, "First Game", this);
    }


    public static void main(String[] args) {
        new Game();
    }


    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }


    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick();
                delta--;
            }

            if (running) {
                render();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }

            if (HUD.HEALTH <= 0) {
                for (int i = 0; i < handler.list.size(); i++) {
                    if (handler.list.get(i).getId() == ID.Player) {
                        handler.removeObject(handler.list.get(i));
                    }
                    else {
                        handler.list.get(i).setVelX(0);
                        handler.list.get(i).setVelY(0);
                    }
                }

            }
        }

        stop();
    }


    private void tick() {
        handler.tick();
        hud.tick();
    }


    private void render() {
        BufferStrategy strat = getBufferStrategy();
        if (strat == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = strat.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        hud.render(g);

        g.dispose();
        strat.show();
    }


    public static int clamp(int value, int min, int max) {
        if (value >= max) {
            return value = max;
        }
        if (value <= min) {
            return value = min;
        }
        else {
            return value;
        }
    }
}
