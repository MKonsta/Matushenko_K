package iterators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 5.1.4. Создать convert(Iterator<Iterator>) [#152]
 * Реализовать класс с методом Iterator<Integer> convert(Iterator<Iterator<Integer>> it).

 Что из себя представляет запись Iterator<Iterator<Integer>?.

 Каждый итератор это последовательность.

 Итератор 1 – 4 2 0 4 6 4 9

 Итератор 2 – 0 9 8 7 5

 Итератор 3 – 1 3 5 6 7 0 9 8 4

 Если мы говорим о записи Итератор Итераторов. Значит итератор содержит не конечные значения, а сложенные итераторы.

 Итератор - Итератор 1, Итератор 2, Итератор 3.

 Метод convert должен принимать объект итератор итератор и возвращать Итератор чисел.

 Iterator<Iterator<Integer> - ((4 2 0 4 6 4 9), (0 9 8 7 5), (1 3 5 6 7 0 9 8 4))

 Метод должен возвращать

 Iterator<Integer> - (4 2 0 4 6 4 9 0 9 8 7 5 1 3 5 6 7 0 9 8 4)

 Метод не должен копировать данные. Нужно реализовать итератор, который будет пробегать по вложенными итераторам без копирования данных.
 */

public class Converter {

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {

            Iterator<Integer> itIn = it.next();

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (itIn.hasNext()) {
                    result = true;
                } else while (it.hasNext()) {
                    itIn = it.next();
                    if (itIn.hasNext()) {
                        result = true;
                        break;
                    }
                }

                //==============Старый рабочий вариант===========
//                if (itIn.hasNext()) {
//                    result = true;
//                } else if (it.hasNext()) {
//                    itIn = it.next();
//                    if (itIn.hasNext()) {
//                        result = true;
//                    }
//                }
                return result;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return itIn.next();
            }
        };
    }

    public static void main(String[] args) {
        Iterator<Integer> its;

        Iterator<Integer> it1 = Arrays.asList(1, 2, 3).iterator();
        Iterator<Integer> it2 = Arrays.asList(4, 5, 6).iterator();
        Iterator<Integer> it3 = Arrays.asList(7, 8, 9).iterator();

        Iterator<Iterator<Integer>> it = Arrays.asList(it1, it2, it3).iterator();
        Converter con = new Converter();
        its = con.convert(it);
        while (its.hasNext()) {
            System.out.println(its.next());
        }

    }
}
