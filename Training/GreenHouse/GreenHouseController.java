package Training.GreenHouse;

/**
 * Created by pikachu on 07.07.17.
 */
public class GreenHouseController {
    public static void main(String... args){
        GreenHouseControls greenHouseControls = new GreenHouseControls();
        greenHouseControls.addEvent(greenHouseControls.new Bell(900));
        Event[] events = {
                greenHouseControls.new ThermostatNight(0),
                greenHouseControls.new LightOn(200),
                greenHouseControls.new LightOff(400),
                greenHouseControls.new WaterOn(600),
                greenHouseControls.new WaterOff(800),
                greenHouseControls.new ThermostatDay(1400)
        };

        greenHouseControls.addEvent(greenHouseControls.new Restart(2000, events));
        if (args.length == 1)
            greenHouseControls.addEvent(new GreenHouseControls.Terminate(new Integer(args[0])));
        greenHouseControls.run();
    }
}
