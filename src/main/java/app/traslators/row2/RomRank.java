package app.traslators.row2;

/**
 * Римские разряды чисел.
 *
 *  Работать с числами у которых система счисления опирается на разряды
 *  очень не удобно.
 *  Оптимальнее будет, для каждого разряда создать объект,
 *  который будет работать с числами в рамках этого разряда,
 *  и с него уже собирать данные.
 *
 *  Перечисление RowRank имеет всего 2 константы, но их легко можно дополнить.
 *
 *  Этот класс берёт на себя заботы о том,
 *  как правильно должно выглядеть число, на пример VIIII или IX.
 */
public enum RomRank {
    FIRST("X", "V", "I", 1),
    SECOND("C", "L", "X", 2);

    public final int rank;
    public final Pair[] numbers;

    /**
     * Сам экземпляр перечисления принимает в себя данные,
     * необходимые для работы с определённым разрядом римских чисел,
     * и создаёт массив римских чисел, исходя из полученных данных.
     *
     * @param decade - единица следующего разряда
     * @param fiver - пять единиц разряда
     * @param unit - единица разряда
     * @param rank -сам разряд по порядку, то есть число, в степень которого нужно возвести число 10,
     *             чтобы получить минимальное значение следующего разряда.
     */
    RomRank(String decade, String fiver, String unit, int rank) {
        this.rank = rank;

        numbers = new Pair[] {
                new Pair((decade),                     withRank(10)),
                new Pair((unit + decade),              withRank(9)),
                new Pair((fiver + unit + unit + unit), withRank(8)),
                new Pair((fiver + unit + unit),        withRank(7)),
                new Pair((fiver + unit),               withRank(6)),
                new Pair((fiver),                      withRank(5)),
                new Pair((unit + fiver),               withRank(4)),
                new Pair((unit + unit + unit),         withRank(3)),
                new Pair((unit + unit),                withRank(2)),
                new Pair((unit),                       withRank(1)),
        };
    }

    /**
     * Умножает число на разряд
     * @param num - исходное число
     * @return - минимальное число разряда умноженное на исходное
     */
    int withRank(int num) {
        return num * (int) Math.pow(10, rank - 1);
    }

    /**
     * Подбирает пару числа арабское-римское
     * для исходного числа в рамках текущего разряда
     *
     * @param num - исходное число
     * @return - пара число арабское-римское данного разряда
     */
    public Pair getFirstPair(int num) {
        for (Pair pair : numbers) {
            if (pair.arab <= num) return pair;
        }

        return null;
    }

    /**
     * Подбирает пару числа арабское-римское
     * для исходной строки в рамках текущего разряда
     *
     * @param num - исходная строка
     * @return - пара число арабское-римское данного разряда
     */
    public Pair getFirstPair(String num) {
        for (Pair pair : numbers) {
            if (num.startsWith(pair.rom)) return pair;
        }

        return null;
    }

    /**
     * Проверяет, может ли римское число содержать текущий разряд
     */
    public boolean inRank(String num) {
        return num.startsWith(numbers[0].rom) ||
                num.startsWith(numbers[4].rom) ||
                num.startsWith(numbers[9].rom);
    }

    /**
     * Проверяет, может ли число содержать текущий разряд
     */
    public boolean inRank(int num) {
        return Math.pow(10, rank) >= num && Math.pow(10, rank - 1) <= num;
    }

    /**
     * Пара арабское число - римское число.
     *
     * Удобно обрабатывать числа, разных форматов, но одного значения, вместе
     */
    public class Pair {
        public final String rom;
        public final int arab;

        public Pair(String rom, int arab) {
            this.rom = rom;
            this.arab = arab;
        }
    }
}
