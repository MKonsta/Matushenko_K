package threads.pingpong;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * 1. Пин-понг. [#1016]
 * В этой задаче вам необходимо сделать анимацию пинг-понг.

 Квадратик должен отталкиваться от краев окна и менять направление.

 Движение квадратика должно задаваться через Thread.
 */
public class PingPong extends Application {
    private static final String JOB4J = "Пинг-понг www.job4j.ru";

    @Override
    public void start(Stage stage) {
        int limitX = 300;
        int limitY = 300;
        Group group = new Group();
        Rectangle rect = new Rectangle(50, 100, 10, 10);
        group.getChildren().add(rect);
        Thread thread = new Thread(new RectangleMove(rect));
        thread.start();
        stage.setScene(new Scene(group, limitX, limitY));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(
                event -> thread.interrupt()
        );
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
