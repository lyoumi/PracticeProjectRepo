package Ant;

import java.util.List;

/**
 * Created by lyoumi on 06.11.2016.
 */
public interface Executor<T> {

    void addTask(Task<? extends T> task);

    void  addTask(Task<? extends T> task, Validator<? super T> validator);

    void execute();

    List<T> getValidResult();

    List<T> getInvalidResult();
}
