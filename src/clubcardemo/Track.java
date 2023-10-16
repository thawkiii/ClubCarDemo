package clubcardemo;

public class Track {
    private int carID, prio, reqMiles, actMiles, start, end;
    private String date, testTech, comment;
    
    public Track(String date, int carID, String testTech, int prio, int reqMiles, int actMiles, int start, int end, String comment) {
        this.date = date;
        this.carID = carID;
        this.testTech = testTech;
        this.prio = prio;
        this.reqMiles = reqMiles;
        this.actMiles = actMiles;
        this.start = start;
        this.end = end;
        this.comment = comment;
    }
    
    public String getDate() {
        return date;
    }
    
    public int getCarID() {
        return carID;
    }
    
    public String getTestTech() {
        return testTech;
    }
    
    public int getPrio() {
        return prio;
    }
    
    public int getReqMiles() {
        return reqMiles;
    }
    
    public int getActMiles() {
        return actMiles;
    }
    
    public int getStart() {
        return start;
    }
    
    public int getEnd() {
        return end;
    }
    
    public String getComment() {
        return comment;
    }
}
