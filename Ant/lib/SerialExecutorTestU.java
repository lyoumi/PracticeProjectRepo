import Ant.SerialExecutor;
import Ant.Task;

//import static org.junit.Assert.*;

/**
 * Created by lyoumi on 06.11.2016.
 */
/*
public class SerialExecutorTestU {
    @org.junit.Test
    public void testExecuteWithoutValidator() throws Exception {
        SerialExecutor<Integer> executor = new SerialExecutor<Integer>();
        executor.addTask(new AddTask(1, 2));
        executor.execute();
        assertEquals(executor.getValidResult().get(0), Integer.valueOf(3));
    }

    private static class AddTask implements Task<Integer> {


        private int value1;
        private int value2;
        private int result;

        public AddTask(int value1, int value2) {
            this.value1 = value1;
            this.value2 = value2;
        }

        @Override
        public void execute() {
            result = value1 + value2;
        }

        @Override
        public Integer getResult() {
            return null;
        }
    }
}*/