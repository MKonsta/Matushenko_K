package ru.job4j;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.paintshape.Paint;
import ru.job4j.paintshape.Square;
import ru.job4j.paintshape.Triangle;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/*РЕФАКТОРИНГ   REFACTORING*/

public class PaintTest {
    private final PrintStream stdout = System.out; /*Получили ссылку на стандартный вывод на консоль*/
    private final ByteArrayOutputStream out = new ByteArrayOutputStream(); /*Создали буфер для хранения вывода*/

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    @Test
    public void paintTestWhenDrawSquare() {
        Square sq = new Square();
        Paint paint = new Paint();
        paint.draw(sq); /*Выполнили действие пишущее в консоль*/

        StringBuilder str = new StringBuilder();
        str.append("88888");
        str.append(System.lineSeparator());
        str.append("8   8");
        str.append(System.lineSeparator());
        str.append("8   8");
        str.append(System.lineSeparator());
        str.append("8   8");
        str.append(System.lineSeparator());
        str.append("88888");
        str.append(System.lineSeparator());
        assertThat(new String(out.toString()), is(str.toString()));
    }

    @Test
    public void paintTestWhenDrawTriangle() {
        Triangle tr = new Triangle();
        Paint paint = new Paint();
        paint.draw(tr); /*Выполнили действие пишущее в консоль*/

        StringBuilder str = new StringBuilder();
        str.append("    *    ");
        str.append(System.lineSeparator());
        str.append("   ***   ");
        str.append(System.lineSeparator());
        str.append("  *****  ");
        str.append(System.lineSeparator());
        str.append(" ******* ");
        str.append(System.lineSeparator());
        str.append("*********");
        str.append(System.lineSeparator());
        assertThat(new String(out.toString()), is(str.toString()));
    }
}
