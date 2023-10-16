package clubcardemo;

public class ActiveCars {
    private int carID;
    private String carType, project, requestor;
    
    public ActiveCars(int carID, String carType, String project, String requestor) {
        this.carID = carID;
        this.carType = carType;
        this.project = project;
        this.requestor = requestor;
    }
    
    public int getCarID() {
        return carID;
    }
    
    public String getCarType() {
        return carType;
    }
    
    public String getProject() {
        return project;
    }
    
    public String getRequestor() {
        return requestor;
    }
}
