package Ant;

/**
 * Created by lyoumi on 06.11.2016.
 */
public interface Task<T> {

    void execute();

    T getResult();
}
