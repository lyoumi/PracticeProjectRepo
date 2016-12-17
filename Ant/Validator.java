package Ant;

/**
 * Created by lyoumi on 06.11.2016.
 */
public interface Validator<T> {

    boolean isValid(T result);
}
