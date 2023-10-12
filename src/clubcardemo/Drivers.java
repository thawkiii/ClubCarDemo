package clubcardemo;

public class Drivers {
    private String driverName;
    private double driverHours;
    
    public Drivers(String driverName, double driverHours) {
        this.driverName = driverName;
        this.driverHours = driverHours;
    }
    
    public String getDriverName() {
        return driverName;
    }
    
    public double getDriverHours() {
        return driverHours;
    }
}
