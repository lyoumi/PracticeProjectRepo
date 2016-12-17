package Ant;

import java.util.List;

/**
 * Created by lyoumi on 06.11.2016.
 */
public class SerialExecutor<T> implements Executor<T> {

    @Override
    public void addTask(Task<? extends T> task) {

    }

    @Override
    public void addTask(Task<? extends T> task, Validator<? super T> validator) {

    }

    @Override
    public void execute() {

    }

    @Override
    public List<T> getValidResult() {
        return null;
    }

    @Override
    public List<T> getInvalidResult() {
        return null;
    }
}
