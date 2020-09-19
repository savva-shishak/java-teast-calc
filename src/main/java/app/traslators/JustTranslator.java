package app.traslators;

/**
 * Простой переводчик
 *
 * Работает только со строками в виде чисел
 */
public class JustTranslator implements Translator {

    public boolean isValid(String num) {
        try {
            new Integer(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int toInt(String num) {
        return new Integer(num);
    }

    public String toString(int num) {
        return num + "";
    }
}
