package clubcardemo;

public class Cars {
    private int carID, carPrio, maxHours, reqLaps, actLaps, lapsDay;
    private String project, requestor, testEng, testTech, carType, fuelType, testType, startDate, needDate;
    private double reqHours, actHours;
    
    public Cars(int carID, String project, String requestor, String testEng, String testTech, int carPrio, String carType, String fuelType, int maxHours, String testType, double reqHours, double actHours, int reqLaps, int actLaps, int lapsDay, String startDate, String needDate) {
            this.carID = carID;
            this.project = project;
            this.requestor = requestor;
            this.testEng = testEng;
            this.testTech = testTech;
            this.carPrio = carPrio;
            this.carType = carType;
            this.fuelType = fuelType;
            this.maxHours = maxHours;
            this.testType = testType;
            this.reqHours = reqHours;
            this.actHours = actHours;
            this.reqLaps = reqLaps;
            this.actLaps = actLaps;
            this.lapsDay = lapsDay;
            this.startDate = startDate;
            this.needDate = needDate;
    }
    
    public int getCarID() {
        return carID;
    }
    
    public String getProject() {
        return project;
    }
    
    public String getRequestor() {
        return requestor;
    }
    
    public String getTestEng() {
        return testEng;
    }
    
    public String getTestTech() {
        return testTech;
    }
    
    public int getCarPrio() {
        return carPrio;
    }
    
    public String getCarType() {
        return carType;
    }
    
    public String getFuelType() {
        return fuelType;
    }
    
    public int getMaxHours() {
        return maxHours;
    }
    
    public String getTestType() {
        return testType;
    }
    
    public double getReqHours() {
        return reqHours;
    }
    
    public double getActHours() {
        return actHours;
    }
    
    public int getReqLaps() {
        return reqLaps;
    }
    
    public int getActLaps() {
        return actLaps;
    }
    
    public int getLapsDay() {
        return lapsDay;
    }
    
    public String getStartDate() {
        return startDate;
    }
    
    public String getNeedDate() {
        return needDate;
    }
}
