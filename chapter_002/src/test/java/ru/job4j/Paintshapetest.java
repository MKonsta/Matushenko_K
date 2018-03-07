package ru.job4j;

import org.junit.Test;
import ru.job4j.paintshape.Paint;
import ru.job4j.paintshape.Square;
import ru.job4j.paintshape.Triangle;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Paintshapetest {
    @Test
    public void triangleTest() {
        Triangle tr = new Triangle();
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
        assertThat(tr.draw(), is(str.toString()));
    }
    @Test
    public void squareTest() {
        Square sq = new Square();
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
        assertThat(sq.draw(), is(str.toString()));
    }

    @Test
    public void paintTestWhenDrawSquare() {
        PrintStream stdout = System.out; /*Получили ссылку на стандартный вывод на консоль*/
        ByteArrayOutputStream out = new ByteArrayOutputStream(); /*Создали буфер для хранения вывода*/
        System.setOut(new PrintStream(out)); /*Заменили стандартный вывод на вывод в память для тестирования*/
        Paint paint = new Paint();
        Square sq = new Square();
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
        assertThat(new String(out.toByteArray()), is(str.toString()));
        System.setOut(stdout); /*Возвратили обратно стандартный вывод на консоль*/
    }
}
