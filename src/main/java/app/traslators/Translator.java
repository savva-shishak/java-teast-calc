package app.traslators;

public interface Translator {
    boolean isValid(String num);

    int toInt(String num);

    String toString(int num);
}
