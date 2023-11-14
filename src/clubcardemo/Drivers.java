package clubcardemo;

public class Drivers {
    private String driverName;
    private int driverHours;
    
    public Drivers(String driverName, int driverHours) {
        this.driverName = driverName;
        this.driverHours = driverHours;
    }
    
    public String getDriverName() {
        return driverName;
    }
    
    public int getDriverHours() {
        return driverHours;
    }
}
