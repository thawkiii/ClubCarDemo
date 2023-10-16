package clubcardemo;

public class Cars {
    private int carID, carReqMiles;
    private String carType, projectName, startDate, needDate, status, requestor;
    private double maxHours;
    
    public Cars(int carID, String carType, String projectName, int carReqMiles, String startDate, String needDate, double maxHours, String status, String requestor) {
            this.carID = carID;
            this.carType = carType;
            this.projectName = projectName;
            this.carReqMiles = carReqMiles;
            this.startDate = startDate;
            this.needDate = needDate;
            this.maxHours = maxHours;
            this.status = status;
            this.requestor = requestor;
    }
    
    public int getCarID() {
        return carID;
    }
    
    public String getCarType() {
        return carType;
    }
    
    public String getProjectName() {
        return projectName;
    }
    
    public int getCarReqMiles() {
        return carReqMiles;
    }

    public String getStartDate() {
        return startDate;
    }
    
    public String getNeedDate() {
        return needDate;
    }
            
    public double getMaxHours() {
        return maxHours;
    }

    public String getStatus() {
        return status;
    }
    
    public String getRequestor() {
        return requestor;
    }
}
