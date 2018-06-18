package ru.job4j.departamentsort;

import java.util.*;

/**
 *Отсортировать департаменты [#34352]
 */
public class Org {
    /**
     * Добавляю недостающие строки (если есть) в массив
     * Данный метод используют методы сортировки по возрастанию и по убыванию
     * @param list - исходный массив
     * @return ArrayList с недостающими строками
     */
    private List<String> addAllDeps(String[] list) {
        List<String> result = new ArrayList<>();
        for (String s : list) {
            String[] temp = s.split("\\\\");
            List<String> tempList = new ArrayList<>();
            tempList.add(temp[0]);
            for (int i = 1; i < temp.length; i++) {
                tempList.add(tempList.get(tempList.size() - 1) + "\\" + temp[i]);
            }
            for (String s1 : tempList) {
                if (!result.contains(s1)) {
                    result.add(s1);
                }
            }
        }
        return result;
    }

    /**
     * Сортирую по возрастанию
     * @param list
     * @return
     */
    public List<String> sortByIncreasing(String[] list) {
        List<String> result = addAllDeps(list);
        result.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        return result;
    }

    /**
     * Сортирую по убыванию
     * @param list
     * @return
     */
    public List<String>  sortByDecreasing(String[] list) {
        List<String> result = addAllDeps(list);
        Collections.sort(result, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                List<String> s1 = Arrays.asList(o1.split("\\\\"));
                List<String> s2 = Arrays.asList(o2.split("\\\\"));
                int temp = s2.get(0).compareTo(s1.get(0));
                if (temp == 0) {
                    temp = s1.size() - s2.size();
                    if (temp == 0) {
                        temp = o2.compareTo(o1);
                    }
                }
                return temp;
            }
        });
        return result;
    }

    public static void main(String[] args) {
        Org org = new Org();
        String[] str = {"K2\\SK1\\SSK2", "K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1"};
        //System.out.println(org.sortByIncreasing(str));
        for (String s : org.sortByIncreasing(str)) {
            System.out.println(s);
        }

        System.out.println("=======================");

        for (String s : org.sortByDecreasing(str)) {
            System.out.println(s);
        }
    }
}
