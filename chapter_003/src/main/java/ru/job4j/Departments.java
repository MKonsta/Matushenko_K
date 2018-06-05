package ru.job4j;

import java.util.*;


/**
 * Отсортировать департаменты [#34352]
 */
public class Departments {

    /**
     * Метод разбивает каждую строку по иерархической структуре
     * @param str = “K1\SK1\SSK1”
     * @return List<String> = {"K1", "K1\SK1", "K1\SK1\SSK1"}
     */
    public List<String> splitter(String str) {
        List<String> list = new ArrayList<>();
        char[] ch = str.toCharArray();
        int index = 0;
        list.add("");
        for (char a : ch) {
            if (a != '\\') {
                list.set(index, list.get(index) + a);
            } else {
                list.add(list.get(index) + "\\");
                index++;
            }
        }
        return list;
    }

    /**
     * Метод для сортировки по возрастанию. Работает нормально
     * @param departments
     * @return
     */
    public List<String> upSort(String[] departments) {
        List<String> list = new ArrayList<>(Arrays.asList(departments));
        for (int i = 0; i < list.size(); i++) {
            for (String s : splitter(list.get(i))) {
                if (!list.contains(s)) {
                    list.add(s);
                }
            }
        }
        Collections.sort(list);
        return list;
    }

    /**
     * Метод для сортировки по-убыванию. Не работает, и я не знаю как его правильно реализовать.
     * @param departments
     * @return
     */
    public List<String> downSort(String[] departments) {
        List<String> list = new ArrayList<>(Arrays.asList(departments));
        for (int i = 0; i < list.size(); i++) {
            for (String s : splitter(list.get(i))) {
                if (!list.contains(s)) {
                    list.add(s);
                }
            }
        }

        List<String> list1 = new ArrayList<>(Arrays.asList(departments));
        for (int i = 0; i < list1.size(); i++) {
            for (String s : splitter(list.get(i))) {
                if (!list.contains(s)) {
                    list.add(s);
                }
            }
        }
        Collections.sort(list, downComp.thenComparing(lengthComp));
        return list;
    }

    Comparator<String> downComp = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o2.compareTo(o1);
        }
    };

    Comparator<String> upComp = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    };

    Comparator<String> lengthComp = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o1.length() - o2.length();
        }
    };

    public static void main(String[] args) {
        Departments departments = new Departments();
        String[] deps = {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};

        System.out.println(departments.upSort(deps));
    }
}
