package collectionpro;

import java.util.HashMap;
import java.util.Map;

/**
 * Тестовое задание. Дано два одинаковых слова. Но в словах перемешаны буквы.
 * Если набор букв одинаков. Метод comp должен вернуть true
 */
public class TwoWords {

    public boolean comp(String a, String b) {
        char[] chA = a.toCharArray();

        Map<Character, Integer> bMap = new HashMap<>();
        char[] chB = b.toCharArray();
        for (char cha : chB) {
            bMap.put(cha, null);
        }

        for (char ch : chA) {
            if (bMap.containsKey(ch)) {
                bMap.remove(ch);
            } else {
                return false;
            }
        }
        return (bMap.size() == 0);
    }

}
