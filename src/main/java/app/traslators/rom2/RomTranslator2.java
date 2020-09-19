package app.traslators.rom2;

import app.traslators.Translator;

/**
 * Вторая реализация переводчика римских чисел.
 *
 * Сам переводчик не работает с конкретными числами,
 * вместо этого он работает с разрядами из перечисления RowRank,
 * причём, без жёской привязки к числам или к разрядам.
 * Активно используется рекурсия.
 *
 * Здесь строк кода в 2 раза меньше, но они менее читабельны и он тесно работает с перечислением RomRank.
 * Плюсы - очень легко добавлять новые разряды (поддерживать).
 */
public class RomTranslator2 implements Translator {
    /**
     * Через рекурсию, пробегается по всей строке и проверяет каждый символ,
     * если он есть в каком-то из разрядов, значит он валидный,
     * иначе строка не валидна.
     *
     * @param num - число в виде строки
     * @return - валидный или нет
     */
    public boolean isValid(String num) {
        if (num.length() == 0) {
            return true;
        }

        for (RomRank rank : RomRank.values()) {
            if (rank.inRank(num)) {
                return isValid(num.substring(1));
            }
        }

        return false;
    }

    /**
     * Переводит римское число в виде строки в целочисленное значение.
     * Через рекурсию и разряды, распознаёт по одному числу в строке
     * и суммирует все что нашёл.
     *
     * @param num - исходное римское число-строка
     * @return - целочисленное значение этой строки
     */
    public int toInt(String num) {
        if (num.length() == 0) return 0;

        RomRank.Pair pair = null;

        for (RomRank rank : RomRank.values()) {
            if (rank.inRank(num)) {
                pair = rank.getFirstPair(num);
            }
        }

        if (pair == null) return 0;

        return pair.arab + toInt(num.substring(pair.rom.length()));
    }

    /**
     * Переводит целочисленное значение в виде строки в римское число.
     * Через рекурсию и разряды, переводит каждый разряд числа в римскую цифру
     * и склеивает результат.
     *
     * @param num - исходное целое число
     * @return - римское число-строка
     */
    public String toString(int num) {
        if (num == 0) return "";

        RomRank.Pair pair = null;

        for (RomRank rank : RomRank.values()) {
            if (rank.inRank(num)) {
                pair = rank.getFirstPair(num);
            }
        }

        if (pair == null) return "";

        return pair.rom + toString(num - pair.arab);
    }
}
