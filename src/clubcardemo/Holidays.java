package clubcardemo;

public class Holidays {
    private String holidayDate, holidayName;
    
    public Holidays(String holidayDate, String holidayName) {
        this.holidayDate = holidayDate;
        this.holidayName = holidayName;
    }
    
    public String getHolidayDate() {
        return holidayDate;
    }
    
    public String getHolidayName() {
        return holidayName;
    }
}
