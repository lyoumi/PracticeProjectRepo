package Training.GreenHouse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pikachu on 07.07.17.
 */
public class Controller {
    private List<Event> eventList = new ArrayList<>();
    public void addEvent (Event c){eventList.add(c);}
    public void run(){
        while(eventList.size()>0){
            for (Event e :
                    new ArrayList<>(eventList)) {
                if (e.ready()){
                    System.out.println(e);
                    e.action();
                    eventList.remove(e);
                }
            }
        }
    }
}
