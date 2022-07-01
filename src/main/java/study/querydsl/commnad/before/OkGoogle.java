package study.querydsl.commnad.before;

public class OkGoogle {

    private static String[] modes = {"heater", "lamp"};
    private Heater heater;
    private Lamp lamp;
    private String mode;

    public OkGoogle(Heater heater, Lamp lamp) {
        this.heater = heater;
        this.lamp = lamp;
    }

    public void setModes(int idx) {
        this.mode = modes[idx];
    }

    public void talk() {
        switch (this.mode) {
            case "heater":
                this.heater.powerOn();
                break;
            case "lamp":
                this.lamp.turnOn();
                break;
        }
    }
}
