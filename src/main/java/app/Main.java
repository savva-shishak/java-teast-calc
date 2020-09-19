package app;

import app.exceptions.UnknownOperator;
import app.traslators.JustTranslator;
import app.traslators.row2.RomTranslator2;
import app.traslators.Translator;

import java.util.Scanner;

/**
 * Самое сложное в программе - это сделать переводчиков.
 *
 * Можно было бы сделать несколько калькуляторов,
 * которые определяли бы валидные для них значения и вычисляли бы,
 * но это было бы очень хлопотно.
 * К тому же, значения чисел везде одни и теже,
 * поэтому, оптимальнее будет сделать один калькулятор,
 * который по понятным ему числам и строковому оперетору вычислить значение,
 * а переводы чисел из одного в другой поручить переводчикам.
 */
public class Main {
    /**
     * Переводчики
     */
    public static Translator[] translators = {
            new JustTranslator(),
            // new RomTranslator() // - класс рабочий, но не рекомендуется, сделан как первая версия, Оставил его на всякий случай.
            new RomTranslator2()
    };

    /**
     * Запрашивает у пользователя выражение,
     *
     * Из полученного выражения вытаскивает чифры и оператор
     * и по цифрам ищет (через метод isValid) конвертер,
     * который сможет работать с этими цифрами.
     *
     * Затем переводит цифры из выражения в числа понятные программе
     * и делает вычисления с этими числами.
     *
     * После переводит результат через переводчик, которым конвертировал входные числа,
     * и выводит на консоль
     */
    public static void main(String[] args) {
        System.out.println("Enter expression:");
        String expression = (new Scanner(System.in)).nextLine();

        String[] components = expression.split(" ");

        for (Translator translator : translators) {
            if (translator.isValid(components[0]) && translator.isValid(components[2])) {
                int    num1     = translator.toInt(components[0]);
                int    num2     = translator.toInt(components[2]);
                String operator = components[1];

                int result = calc(num1, num2, operator);
                System.out.println(translator.toString(result));
                return;
            }
        }

        throw new UnknownOperator();
    }

    /**
     * Можно было бы и сделать всё через ООП,
     * но это довольно простые действия, поэтому я не вижу смысла создавать лишние классы
     * @param num1 - первое число
     * @param num2 - второе число
     * @param operator - оператор
     * @return - результат
     */
    public static int calc(int num1, int num2, String operator) {
        if (operator.equals("+")) {
            return num1 + num2;
        }

        if (operator.equals("-")) {
            return num1 - num2;
        }

        if (operator.equals("/")) {
            return num1 / num2;
        }

        if (operator.equals("*")) {
            return num1 * num2;
        }

        throw new UnknownOperator();
    }
}
