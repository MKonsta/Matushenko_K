package ru.job4j.peerreview;

import java.util.*;
// Нет описаняя класса, что он делает, и для чего нужен
public class Sorter {

    //Пустой конструктор незачем создавать, он и так создан по-умолчанию
    public Sorter(){

    }

    Set<User> sort (List<User> list) {
        TreeSet<User> sortedList = new TreeSet<>(); //Если делаем treeset, то подразумевается какой-то компаратор, метод сортировки. Тут непонятна логика использования treeset
        sortedList.addAll(list);
        return sortedList;
    }

    List<User> sortnamelength (List<User> list) {
        Comparator<User> compar = new Comparator<User>() {
            @Override
            public int compare (User o1, User o2) {
                return o1.getName().length() - o2.getName().length();
            }
        };
        list.sort(compar);
        return list;
    }

    List<User> sortboth (List<User> list) {
        Comparator<User> compar1 = new Comparator<User>() { //тут компаратор надо назызывать не compar1, 2, а compareByName, и compareByAge
            @Override
            public int compare (User o1, User o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        Comparator<User> compar2 = new Comparator<User>() {
            @Override
            public int compare (User o1, User o2) {
                return o1.getAge() - o2.getAge();
            }
        };
        list.sort(compar1.thenComparing(compar2));
        return list;
    }
}