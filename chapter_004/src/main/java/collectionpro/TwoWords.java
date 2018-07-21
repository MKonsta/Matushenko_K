package collectionpro;

import java.util.HashMap;
import java.util.Map;

/**
 * Тестовое задание. Дано два одинаковых слова. Но в словах перемешаны буквы.
 * Если набор букв одинаков. Метод comp должен вернуть true
 */
public class TwoWords {

    public boolean comp(String a, String b) {
        boolean result = false;
        if (a.length() == b.length()) {
            Map<Character, Integer> aMap = new HashMap<>();
            char[] aChar = a.toCharArray();
            for (char ch : aChar) {
                Integer absent = aMap.putIfAbsent(ch, 1);
                if (absent != null) {
                    aMap.put(ch, aMap.get(ch) + 1);
                }
            }

            char[] bChar = b.toCharArray();
            for (char ch : bChar) {
                if (aMap.get(ch) == null) {
                    break;
                }
                if (aMap.get(ch) > 1) {
                    aMap.put(ch, aMap.get(ch) - 1);
                } else {
                    aMap.remove(ch);
                }
            }
            result = aMap.size() == 0;
        }
        return result;
    }
}
