package Training.GreenHouse;

/**
 * Created by pikachu on 07.07.17.
 */
public abstract class Event {
    private long eventTime;
    protected final long delayTime;
    protected Event(long delayTime) {
        this.delayTime = delayTime;
    }
    public void start(){
        eventTime = System.nanoTime() + delayTime;
    }
    public boolean ready(){
        return System.nanoTime() >= eventTime;
    }
    public abstract void action();
}
