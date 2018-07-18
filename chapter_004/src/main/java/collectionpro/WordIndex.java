package collectionpro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Задачи ПИТЕР-СЕРВИС [#50316]
 *
 * Задача: Есть большой текстовый файл, в котором хранятся различные слова, некоторые из которых – по многу раз.
 Нужно сделать класс WordIndex (можно создавать и другие сопутствующие классы, если это необходимо), который по сути будет являться индексом. Он должен позволять по заданному слову находить все вхождения (позиции) его в файле.
 Данный класс должен компилироваться (исполняться) и иметь следующие методы:
 public void loadFile(String filename); Загрузка данных из файла и построение индекса.
 public  Set<Integer> getIndexes4Word(String searchWord); Возвращает список позиций слова в файле. Если данного слова в файле нет, то возвращается null.
 Для хранения данных в памяти предлагается использовать Trie (префиксное дерево).
На выходе должен получиться Java проект, в котором будет возможность запустить Unit-тест и проверить его работоспособность.
 Проверьте работу этого приложения с String.indexOf
 */
public class WordIndex {
    private List<String> words = new LinkedList<>();

    public WordIndex(String filename) throws IOException {
        loadFile(filename);
    }

    /**
     * Метод преобразует текстовый файл в АррейЛист стрингов
     * @param filename
     * @throws IOException
     */
    private void loadFile(String filename) throws IOException {
        File file = new File(filename);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while (bufferedReader.ready()) {
            words.addAll(Arrays.asList(bufferedReader.readLine().split(" ")));
        }
    }

    /**
     * Метод принимает искомое слово, и возвращает TreeSet<Integer> с номерами позиций, где вмтречается слово в файле
     * @param searchWord
     * @return
     */
    public Set<Integer> getIndex4Word(String searchWord) {
        if (!words.contains(searchWord)) {
            return null;
        }
        Set<Integer> result = new TreeSet<>();
        int index = 0;
        for (String str : words) {
            if (str.equals(searchWord)) {
                result.add(index);
            }
            index++;
        }
        return result;
    }

}
