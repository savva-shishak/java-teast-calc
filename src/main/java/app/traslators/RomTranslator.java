package app.traslators;

import java.util.HashMap;
import java.util.Map;

/**
 * Первая версия переводчика.
 *
 * Работает с конкретными числами.
 * Сам по себе, устроен, просто.
 * С данной задачей вполне справляется.
 *
 * Её плюс в том, что она очень простая и маленькая.
 * Минус - она может работать только с числами от 0 до 100.
 * Чтобы добавить ещё разряды, нужно практически видоизменить все методы и добавить ещё свойств.
 *
 * Оставил его, т.к. он тоже является рабочей версией переводчика римских чисел для данной задачи.
 */
public class RomTranslator implements Translator {
    /**
     * Буквы из которых составляются римские числа от 1 до 100
     */
    public Map<Character, Integer> components = new HashMap() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
    }};

    /**
     * Сложные числа, которые записываются двумя буквами,
     * но во время перевода
     */
    final String four = "IV";
    final String nine = "IX";
    final String fourteen = "XL";
    final String nineteen = "XC";

    /**
     * Проверяет, является ли строка римским числом, проверяя каждый символ строки
     * @param num - Число в виде строки
     * @return - true, если может преобразовать в число, иначе false
     */
    public boolean isValid(String num) {
        char[] chars = num.toCharArray();

        for (char c : chars) {
            if(!components.containsKey(c))
                return false;
        }

        return true;
    }

    /**
     * Преобразует римское число в целое число.
     * Используется рекурсия
     * @param num - Римское число
     * @return
     */
    public int toInt(String num) {
        if (num.length() == 0) return 0;

        if(num.startsWith(four)) {
            return 4 + toInt(num.substring(2));
        }

        if (num.startsWith(nine)) {
            return 9 + toInt(num.substring(2));
        }

        if (num.startsWith(fourteen)) {
            return 40 + toInt(num.substring(2));
        }

        if (num.startsWith(nineteen)) {
            return 90 + toInt(num.substring(2));
        }

        return components.get(num.toCharArray()[0]) + toInt(num.substring(1));
    }

    /**
     * Преобразует целое число в римское.
     * Используется рекурсия
     * @param num - исходное число
     * @return - римское число в виде строки
     */
    public String toString(int num) {
        if (num == 100) return "C";
        if (num <= 0) return "";

        if (num >= 90) {
            return "XC" + toString(num - 90);
        }

        if (num >= 50) {
            return "L" + toString(num - 50);
        }

        if (num >= 40) {
            return "XL" + toString(num - 40);
        }

        if (num >= 10) {
            return "X" + toString(num - 10);
        }

        if (num >= 9) {
            return "IX" + toString(num - 9);
        }

        if (num >= 5) {
            return "V" + toString(num - 5);
        }

        if (num >= 4) {
            return "IV" + toString(num - 4);
        }

        return "I" + toString(num - 1);
    }
}
