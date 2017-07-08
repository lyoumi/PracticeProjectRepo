package Training.GreenHouse;

/**
 * Created by pikachu on 07.07.17.
 */
public class GreenHouseControls extends Controller{

    private boolean light = false;
    private boolean water = false;
    private String thesrmostat = "day";

    public class LightOn extends Event{

        protected LightOn(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            light = true;
        }

        public String toString(){
            return "Light on....";
        }
    }

    public class LightOff extends Event{

        protected LightOff(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            light = false;
        }

        public String toString(){
            return "Light off....";
        }
    }

    public class WaterOn extends Event{

        protected WaterOn(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            water = true;
        }

        public String toString(){
            return "Water on....";
        }
    }

    public class WaterOff extends Event{

        protected WaterOff(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            water = false;
        }

        public String toString(){
            return "Water off....";
        }
    }

    public class ThermostatNight extends Event{

        protected ThermostatNight(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            thesrmostat = "night";
        }

        public String toString(){
            return "Thermostat working by night....";
        }
    }

    public class ThermostatDay extends Event{

        protected ThermostatDay(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            water = false;
        }

        public String toString(){
            return "Thermostat working by day....";
        }
    }

    public class Bell extends Event{

        protected Bell(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            addEvent(new Bell(delayTime));
        }

        public String toString(){
            return "Bam!";
        }
    }

    public class Restart extends Event{

        private Event[] eventList;

        protected Restart(long delayTime, Event[] eventList) {
            super(delayTime);
            this.eventList = eventList;
            for (Event e :
                    eventList) {
                addEvent(e);
            }
        }

        @Override
        public void action() {
            for (Event e :
                    eventList) {
                e.start();
                addEvent(e);
            }
            start();
            addEvent(this);
        }

        public String toString(){
            return "System reboot....";
        }
    }

    public static class Terminate extends Event{

        protected Terminate(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            System.exit(0);
        }

        public String toString(){
            return "System shut down....";
        }
    }
}
