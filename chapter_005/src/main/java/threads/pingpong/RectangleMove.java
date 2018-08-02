package threads.pingpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        int deltaX = 1;
        int deltaY = 1;
        Thread current = Thread.currentThread();
        while (!current.isInterrupted()) {

            if (this.rect.getX() == 300) {
                deltaX = -1;
            } else if (this.rect.getX() == 0) {
                deltaX = 1;
            }

            if (this.rect.getY() == 300) {
                deltaY = -1;
            } else if (this.rect.getY() == 0) {
                deltaY = 1;
            }

            this.rect.setX(this.rect.getX() + deltaX);
            this.rect.setY(this.rect.getY() + deltaY);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
