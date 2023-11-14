package clubcardemo;

class HistoricalCars {
    private int carID, carReqLaps, carLapsLeft, lapsHr, dailyHours, maxLaps, totalDays, workDays;
    private String trNo, projectName, requestor, testEng, testTech, carType, fuelType, startDate, needDate, status, forecastDate, dateDone;
    //possibly add date done to this
    public HistoricalCars(int carID, String trNo, String projectName, String requestor, String testEng, String testTech, String carType, String fuelType, int carReqLaps, int carLapsLeft,
        int lapsHr, int dailyHours, int maxLaps, String startDate, String needDate, String status, int totalDays, int workDays, String forecastDate, String dateDone) {
            this.carID = carID;
            this.trNo = trNo;
            this.projectName = projectName;
            this.requestor = requestor;
            this.testEng = testEng;
            this.testTech = testTech;
            this.carType = carType;
            this.fuelType = fuelType;
            this.carReqLaps = carReqLaps;
            this.carLapsLeft = carLapsLeft;
            this.lapsHr = lapsHr;
            this.dailyHours = dailyHours;
            this.maxLaps = maxLaps;
            this.startDate = startDate;
            this.needDate = needDate;
            this.status = status;
            this.totalDays = totalDays;
            this.workDays = workDays;
            this.forecastDate = forecastDate;
            this.dateDone = dateDone;
    }
    
    public int getCarID() {
        return carID;
    }
    
    public String getTRNo() {
        return trNo;
    }
    
    public String getProjectName() {
        return projectName;
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
    
    public String getCarType() {
        return carType;
    }
    
    public String getFuelType() {
        return fuelType;
    }
    
    public int getCarReqLaps() {
        return carReqLaps;
    }

    public int getCarActLaps() {
        return (carReqLaps - carLapsLeft);
    }
    
    public int getLapsLeft() {
        return carLapsLeft;
    }
    
    public int getLapsHr() {
        return lapsHr;
    }
    
    public int getDailyHours() {
        return dailyHours;
    }
    
    public int getMaxLaps() {
        return maxLaps;
    }
    
    public String getStartDate() {
        return startDate;
    }
    
    public String getNeedDate() {
        return needDate;
    }

    public String getStatus() {
        return status;
    }
    
    public int getTotalDays() {
        return totalDays;
    }
    
    public int getWorkDays() {
        return workDays;
    }
    
    public String getForecastDate() {
        return forecastDate;
    }
    
    public String getDateDone() {
        return dateDone;
    }
}