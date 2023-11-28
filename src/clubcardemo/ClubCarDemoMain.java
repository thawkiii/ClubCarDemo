package clubcardemo;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;

public class ClubCarDemoMain extends javax.swing.JFrame {
    String dbURL = "jdbc:mysql://localhost:3306/clubcar";
    String username = "root";
    String password = "root";

    
    public ClubCarDemoMain() {
        initComponents();
        show_cars();
        show_drivers();
        show_holidays();
        show_track();
        show_track2();
        show_activecars();
        show_historicalcars();
        show_driverHours();
        show_driverHoursRemaining();
    }
    
    public ArrayList<Cars> carList() {
        ArrayList<Cars> carList = new ArrayList<>();
        if(jComboBoxCarsStatus.getSelectedItem().equals("Inactive")) {
            try {
                Connection connection = DriverManager.getConnection(dbURL, username, password);
                String query1 = "SELECT * FROM clubcar.car " +
                                "WHERE status = 'I';";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query1);
                Cars cars;
                while(rs.next()) {
                    cars = new Cars(rs.getInt("carID"), rs.getString("TRNo"), rs.getString("projectName"), rs.getString("requestor"), 
                    rs.getString("testEng"), rs.getString("testTech"), rs.getString("carType"), rs.getString("fuelType"), rs.getInt("carRequiredLaps"), 
                    rs.getInt("carLapsLeft"), rs.getInt("lapsHr"), rs.getInt("dailyHours"), rs.getInt("maxLaps"), rs.getString("startDate"), 
                    rs.getString("needDate"), rs.getString("Status"), rs.getInt("totalDays"), rs.getInt("workDays"), rs.getString("forecastedDate"), rs.getString("forecastDone"));
                    carList.add(cars);
                }
            }
            catch(SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }    
        } if(jComboBoxCarsStatus.getSelectedItem().equals("All")) {
            try {
                Connection connection = DriverManager.getConnection(dbURL, username, password);
                String query1 = "SELECT * FROM clubcar.car;";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query1);
                Cars cars;
                while(rs.next()) {
                    cars = new Cars(rs.getInt("carID"), rs.getString("TRNo"), rs.getString("projectName"), rs.getString("requestor"), 
                    rs.getString("testEng"), rs.getString("testTech"), rs.getString("carType"), rs.getString("fuelType"), rs.getInt("carRequiredLaps"), 
                    rs.getInt("carLapsLeft"), rs.getInt("lapsHr"), rs.getInt("dailyHours"), rs.getInt("maxLaps"), rs.getString("startDate"), 
                    rs.getString("needDate"), rs.getString("Status"), rs.getInt("totalDays"), rs.getInt("workDays"), rs.getString("forecastedDate"), rs.getString("forecastDone"));
                    carList.add(cars);
                }
            }
            catch(SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            } 
        } if(jComboBoxCarsStatus.getSelectedItem().equals("Active")) {
            try {
                Connection connection = DriverManager.getConnection(dbURL, username, password);
                String query1 = "SELECT * FROM clubcar.car " +
                                "WHERE status = 'A';";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query1);
                Cars cars;
                while(rs.next()) {
                    cars = new Cars(rs.getInt("carID"), rs.getString("TRNo"), rs.getString("projectName"), rs.getString("requestor"), 
                    rs.getString("testEng"), rs.getString("testTech"), rs.getString("carType"), rs.getString("fuelType"), rs.getInt("carRequiredLaps"), 
                    rs.getInt("carLapsLeft"), rs.getInt("lapsHr"), rs.getInt("dailyHours"), rs.getInt("maxLaps"), rs.getString("startDate"), 
                    rs.getString("needDate"), rs.getString("Status"), rs.getInt("totalDays"), rs.getInt("workDays"), rs.getString("forecastedDate"), rs.getString("forecastDone"));
                    carList.add(cars);
                }
            } 
            catch(SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            } 
        } else {}
        return carList; 
    }
        
    public void show_cars() {
        ArrayList<Cars> list = carList();
        DefaultTableModel model = (DefaultTableModel)jTableCars.getModel();
        model.setRowCount(0);
        Object[] row = new Object[16];
        for(int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getCarID();
            row[1] = list.get(i).getTRNo();
            row[2] = list.get(i).getProjectName();
            row[3] = list.get(i).getRequestor();
            row[4] = list.get(i).getTestEng();
            row[5] = list.get(i).getTestTech();
            row[6] = list.get(i).getCarType();
            row[7] = list.get(i).getFuelType();
            row[8] = list.get(i).getCarReqLaps();
            row[9] = list.get(i).getCarActLaps();
            row[10] = list.get(i).getLapsLeft();
            row[11] = list.get(i).getLapsHr();
            row[12] = list.get(i).getDailyHours();
            row[13] = list.get(i).getForecastDate(); 
            row[14] = list.get(i).getNeedDate();
            row[15] = list.get(i).getStatus();
            model.addRow(row);
        }
    }     
   
    public ArrayList<Drivers> driverList() {
        ArrayList<Drivers> driverList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "SELECT * FROM clubcar.employeehours;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Drivers drivers;
            while(rs.next()) {
                drivers = new Drivers(rs.getString("employeeName"), rs.getInt("driverHours"));
                driverList.add(drivers);
            }
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return driverList;
    }
   
    public void show_drivers() {
        ArrayList<Drivers> list = driverList();
        DefaultTableModel model = (DefaultTableModel)jTableDrivers.getModel();
        model.setRowCount(0);
        Object[] row = new Object[2];
        for(int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getDriverName();
            row[1] = list.get(i).getDriverHours();
            model.addRow(row);
        }
    } 

    public ArrayList<Holidays> holidayList() {
        ArrayList<Holidays> holidayList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "SELECT * FROM holidays;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Holidays holidays;
            while(rs.next()) {
                holidays = new Holidays(rs.getString("holiday"));
                holidayList.add(holidays);
            }
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return holidayList;
    }
   
    public void show_holidays() {
        ArrayList<Holidays> list = holidayList();
        DefaultTableModel model = (DefaultTableModel)jTableHolidays.getModel();
        model.setRowCount(0);
        Object[] row = new Object[1];
        for(int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getHolidayDate();
//            row[1] = list.get(i).getHolidayName();
            model.addRow(row);
        }
    } 
   
    public ArrayList<Track> trackList() {
        ArrayList<Track> trackList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "SELECT * FROM clubcar.track;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Track track;
            while(rs.next()) {
                track = new Track(rs.getString("trackDate"), rs.getInt("carID"), rs.getString("testTechnician"), rs.getInt("priority"), 
                rs.getInt("requestedMilesToday"), rs.getInt("actualMiles"), rs.getInt("startTime"), rs.getInt("endTime"), rs.getString("trackComments"));
                trackList.add(track);
            }
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return trackList;
    }
        
    public void show_track() {
        ArrayList<Track> list = trackList();
        DefaultTableModel model = (DefaultTableModel)jTableRecordsTrack.getModel();
        model.setRowCount(0);
        Object[] row = new Object[9];
        for(int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getDate();
            row[1] = list.get(i).getCarID();
            row[2] = list.get(i).getTestTech();
            row[3] = list.get(i).getPrio();
            row[4] = list.get(i).getReqMiles();
            row[5] = list.get(i).getActMiles();
            row[6] = list.get(i).getStart();
            row[7] = list.get(i).getEnd();
            row[8] = list.get(i).getComment();
            model.addRow(row);
        }
    }  
   
    public void show_track2() {
       ArrayList<Track> list = trackList();
       DefaultTableModel model = (DefaultTableModel)jTableScheduleTrack.getModel();
       model.setRowCount(0);
       Object[] row = new Object[9];
       for(int i = 0; i < list.size(); i++) {
           row[0] = list.get(i).getDate();
           row[1] = list.get(i).getCarID();
           row[2] = list.get(i).getTestTech();
           row[3] = list.get(i).getPrio();
           row[4] = list.get(i).getReqMiles();
           row[5] = list.get(i).getActMiles();
           row[6] = list.get(i).getStart();
           row[7] = list.get(i).getEnd();
           row[8] = list.get(i).getComment();
           model.addRow(row);
       }
   }
     
    public ArrayList<ActiveCars> activeCarsList() {
        ArrayList<ActiveCars> activeCarsList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "SELECT * FROM clubcar.car " +
                            "WHERE status = 'A';";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            ActiveCars activeCars;
            while(rs.next()) { //rewrite this to add the date the last forecast was done table join on id? or just add the date done to the car table
                activeCars = new ActiveCars(rs.getInt("carID"), rs.getString("TRNo"), rs.getString("projectName"), rs.getString("requestor"), 
                    rs.getString("testEng"), rs.getString("testTech"), rs.getString("carType"), rs.getString("fuelType"), rs.getInt("carRequiredLaps"), 
                    rs.getInt("carLapsLeft"), rs.getInt("lapsHr"), rs.getInt("dailyHours"), rs.getInt("maxLaps"), rs.getString("startDate"), 
                    rs.getString("needDate"), rs.getString("Status"), rs.getInt("totalDays"), rs.getInt("workDays"), rs.getString("forecastedDate"), rs.getString("forecastDone"));
                    activeCarsList.add(activeCars);
            }
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return activeCarsList;
    }
   
    public void show_activecars() {
        ArrayList<ActiveCars> list = activeCarsList();
        DefaultTableModel model = (DefaultTableModel)jTableForecastActiveCars.getModel();
        model.setRowCount(0);
        Object[] row = new Object[15];
        for(int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getTRNo();
            row[1] = list.get(i).getProjectName();
            row[2] = list.get(i).getRequestor();
            row[3] = list.get(i).getTestEng();
            row[4] = list.get(i).getTestTech();
            row[5] = list.get(i).getCarID();
            row[6] = list.get(i).getCarType();
            row[7] = list.get(i).getFuelType();
            row[8] = list.get(i).getCarReqLaps();
            row[9] = list.get(i).getCarActLaps();
            row[10] = list.get(i).getLapsLeft();
            row[11] = list.get(i).getStartDate();
            row[12] = list.get(i).getForecastDate(); 
            row[13] = list.get(i).getNeedDate();
            row[14] = list.get(i).getDateDone();
            model.addRow(row);
        }
    } 
    
    public ArrayList<HistoricalCars> historicalCarsList() {
        ArrayList<HistoricalCars> historicalCarsList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            java.util.Date date = jDateChooserForecastForecastDate.getDate();
            if(jTextForecastCarID.getText().isBlank() && date == null) { 
                String query1 = "SELECT * FROM clubcar.car " + 
                                "WHERE status = ?;";
                PreparedStatement pst = connection.prepareStatement(query1);
                pst.setString(1, "A");
                ResultSet rs = pst.executeQuery();
                HistoricalCars historicalCars;
                while(rs.next()) {
                    historicalCars = new HistoricalCars(rs.getInt("carID"), rs.getString("TRNo"), rs.getString("projectName"), rs.getString("requestor"), 
                        rs.getString("testEng"), rs.getString("testTech"), rs.getString("carType"), rs.getString("fuelType"), rs.getInt("carRequiredLaps"), 
                        rs.getInt("carLapsLeft"), rs.getInt("lapsHr"), rs.getInt("dailyHours"), rs.getInt("maxLaps"), rs.getString("startDate"), 
                        rs.getString("needDate"), rs.getString("Status"), rs.getInt("totalDays"), rs.getInt("workDays"), rs.getString("forecastedDate"), rs.getString("forecastDone"));
                        historicalCarsList.add(historicalCars); //fix these up? (add date forecasted on)
                }
            } else if(!jTextForecastCarID.getText().isBlank() && date != null) { 
                SimpleDateFormat formatYMD = new SimpleDateFormat("yyyy-MM-dd");
                String checkDate = formatYMD.format(jDateChooserForecastForecastDate.getDate());
                String query1 = "CALL clubcar.date_check(?, ?);";
                PreparedStatement pst = connection.prepareStatement(query1);
                pst.setString(1, jTextForecastCarID.getText());
                pst.setString(2, checkDate);
                ResultSet rs = pst.executeQuery();
                HistoricalCars historicalCars;
                while(rs.next()) {
                    historicalCars = new HistoricalCars(rs.getInt("carID"), rs.getString("TRNo"), rs.getString("projectName"), rs.getString("requestor"), 
                        rs.getString("testEng"), rs.getString("testTech"), rs.getString("carType"), rs.getString("fuelType"), rs.getInt("carRequiredLaps"), 
                        rs.getInt("carLapsLeft"), rs.getInt("lapsHr"), rs.getInt("dailyHours"), rs.getInt("maxLaps"), rs.getString("startDate"), 
                        rs.getString("needDate"), rs.getString("Status"), rs.getInt("totalDays"), rs.getInt("workDays"), rs.getString("forecastedDate"), rs.getString("forecastDone"));
                        historicalCarsList.add(historicalCars);
                }    
            } else if(!jTextForecastCarID.getText().isBlank() || date != null) { 
                if(!jTextForecastCarID.getText().isBlank()) {
                String query1 = "SELECT c.TRNo, c.projectName, c.requestor, c.testEng, c.testTech, fh.carID, c.carType, c.fuelType, " +
                                "fh.carRequiredLaps, fh.carLapsLeft, c.lapsHr, fh.dailyHours, fh.maxLaps, fh.startDate, fh.forecastDate AS forecastedDate, " +
                                "fh.needDate, fh.forecastDateDone, fh.status, c.totalDays, c.workDays, fh.forecastDateDone AS forecastDone " +
                                "FROM car AS c JOIN forecasthistory AS fh ON c.carID = fh.carID " +
                                "WHERE fh.carID = ?;";
                PreparedStatement pst = connection.prepareStatement(query1);
                pst.setString(1, jTextForecastCarID.getText());
                ResultSet rs = pst.executeQuery();
                HistoricalCars historicalCars;
                while(rs.next()) {
                    historicalCars = new HistoricalCars(rs.getInt("carID"), rs.getString("TRNo"), rs.getString("projectName"), rs.getString("requestor"), 
                        rs.getString("testEng"), rs.getString("testTech"), rs.getString("carType"), rs.getString("fuelType"), rs.getInt("carRequiredLaps"), 
                        rs.getInt("carLapsLeft"), rs.getInt("lapsHr"), rs.getInt("dailyHours"), rs.getInt("maxLaps"), rs.getString("startDate"), 
                        rs.getString("needDate"), rs.getString("Status"), rs.getInt("totalDays"), rs.getInt("workDays"), rs.getString("forecastedDate"), rs.getString("forecastDone"));
                        historicalCarsList.add(historicalCars);
                }
                } else if(date != null) { 
                SimpleDateFormat formatYMD = new SimpleDateFormat("yyyy-MM-dd");
                String checkDate = formatYMD.format(jDateChooserForecastForecastDate.getDate());
                String query1 = "CALL clubcar.date_check(0, ?);";
                PreparedStatement pst = connection.prepareStatement(query1);
                pst.setString(1, checkDate);
                ResultSet rs = pst.executeQuery();
                HistoricalCars historicalCars;
                while(rs.next()) {
                    historicalCars = new HistoricalCars(rs.getInt("carID"), rs.getString("TRNo"), rs.getString("projectName"), rs.getString("requestor"), 
                        rs.getString("testEng"), rs.getString("testTech"), rs.getString("carType"), rs.getString("fuelType"), rs.getInt("carRequiredLaps"), 
                        rs.getInt("carLapsLeft"), rs.getInt("lapsHr"), rs.getInt("dailyHours"), rs.getInt("maxLaps"), rs.getString("startDate"), 
                        rs.getString("needDate"), rs.getString("Status"), rs.getInt("totalDays"), rs.getInt("workDays"), rs.getString("forecastedDate"), rs.getString("forecastDone"));
                        historicalCarsList.add(historicalCars);
                }    
                } 
            } else {}
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return historicalCarsList;
    }
     
    public void show_historicalcars() {
        ArrayList<HistoricalCars> list = historicalCarsList();
        DefaultTableModel model = (DefaultTableModel)jTableForecastHistoricalCars.getModel();
        model.setRowCount(0);
        Object[] row = new Object[15];
        for(int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getTRNo();
            row[1] = list.get(i).getProjectName();
            row[2] = list.get(i).getRequestor();
            row[3] = list.get(i).getTestEng();
            row[4] = list.get(i).getTestTech();
            row[5] = list.get(i).getCarID();
            row[6] = list.get(i).getCarType();
            row[7] = list.get(i).getFuelType();
            row[8] = list.get(i).getCarReqLaps();
            row[9] = list.get(i).getCarActLaps();
            row[10] = list.get(i).getLapsLeft();
            row[11] = list.get(i).getStartDate();
            row[12] = list.get(i).getForecastDate(); 
            row[13] = list.get(i).getNeedDate();
            row[14] = list.get(i).getDateDone();
            model.addRow(row);
        }
    }
    
    public ArrayList<Hours> driverHours() {
        ArrayList<Hours> driverHours = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "SELECT SUM(driverHours) AS totalHours FROM clubcar.employeehours;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Hours hours;
            while(rs.next()) {
                hours = new Hours(rs.getInt("totalHours"));
                driverHours.add(hours);
            }
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return driverHours;
    }
   
    public void show_driverHours() {
        ArrayList<Hours> list = driverHours();
        DefaultTableModel model = (DefaultTableModel)jTableCarsTotalHours.getModel();
        model.setRowCount(0);
        Object[] row = new Object[1];
        for(int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getHours();
            model.addRow(row);
        }
        jTextDriversTotalHours.setText(String.valueOf(list.get(0).getHours()));
    }
         
    public ArrayList<Hours> driverHoursRemaining() {
        ArrayList<Hours> driverHours = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "SELECT SUM(dailyHours) AS remainingHours FROM clubcar.car " +
                            "WHERE status = 'A';";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Hours hours;
            while(rs.next()) {
                hours = new Hours(rs.getInt("remainingHours"));
                driverHours.add(hours);
            }
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return driverHours;
    }
   
    public void show_driverHoursRemaining() {
        ArrayList<Hours> list = driverHoursRemaining();
        ArrayList<Hours> list2 = driverHours();
        DefaultTableModel model = (DefaultTableModel)jTableCarsHoursRemaining.getModel();
        model.setRowCount(0);
        Object[] row = new Object[1];
        for(int i = 0; i < list.size(); i++) {
            row[0] = list2.get(i).getHours() - list.get(i).getHours();
            model.addRow(row);
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupCarsFuelType = new javax.swing.ButtonGroup();
        buttonGroupCarsStatus = new javax.swing.ButtonGroup();
        Parent = new javax.swing.JPanel();
        jPanelHome = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonCarsPage = new javax.swing.JButton();
        jButtonDriversPage = new javax.swing.JButton();
        jButtonHolidaysPage = new javax.swing.JButton();
        jButtonForecastPage = new javax.swing.JButton();
        jButtonRecordsPage = new javax.swing.JButton();
        jButtonSearchPage = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanelCars = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCars = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextCarsCarID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextCarsTRNo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextCarsReqLaps = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextCarsMaxLaps = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextCarsRequestor = new javax.swing.JTextField();
        jButtonCarsHome = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextCarsProject = new javax.swing.JTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 32767));
        jButtonCarsClear = new javax.swing.JButton();
        jButtonCarsInsert = new javax.swing.JButton();
        jButtonCarsUpdate = new javax.swing.JButton();
        jButtonCarsDelete = new javax.swing.JButton();
        jDateChooserCarsNeedDate = new com.toedter.calendar.JDateChooser();
        jDateChooserCarsStartDate = new com.toedter.calendar.JDateChooser();
        jTextCarsTestEng = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextCarsTestTech = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jTextCarsCarType = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jTextCarsActLaps = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jTextCarsLapsLeft = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jTextCarsLapsHr = new javax.swing.JTextField();
        jTextCarsTotalDailyHours = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jTextCarsStatus2 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jRadioButtonCarsFuelGas = new javax.swing.JRadioButton();
        jRadioButtonCarsFuelElectric = new javax.swing.JRadioButton();
        jRadioButtonCarsStatusActive = new javax.swing.JRadioButton();
        jRadioButtonCarsStatusInactive = new javax.swing.JRadioButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableCarsTotalHours = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTableCarsHoursRemaining = new javax.swing.JTable();
        jComboBoxCarsStatus = new javax.swing.JComboBox<>();
        jButtonCarsForecast = new javax.swing.JButton();
        jPanelDrivers = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableDrivers = new javax.swing.JTable();
        jButtonDriversHome = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextDriversGoalHours = new javax.swing.JTextField();
        jTextDriversTotalHours = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextDriversDriver = new javax.swing.JTextField();
        jTextDriversHours = new javax.swing.JTextField();
        jButtonDriversInsert = new javax.swing.JButton();
        jButtonDriversUpdate = new javax.swing.JButton();
        jButtonDriversDelete = new javax.swing.JButton();
        jButtonDriversClear = new javax.swing.JButton();
        jLabelDriversHourCheck = new javax.swing.JLabel();
        jPanelHolidays = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableHolidays = new javax.swing.JTable();
        jButtonHolidaysHome = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jDateChooserHolidaysDate = new com.toedter.calendar.JDateChooser();
        jButtonHolidaysInsert = new javax.swing.JButton();
        jButtonHolidaysUpdate = new javax.swing.JButton();
        jButtonHolidaysDelete = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanelForecast = new javax.swing.JPanel();
        jButtonForecastHome = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableForecastActiveCars = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jDateChooserForecastForecastDate = new com.toedter.calendar.JDateChooser();
        jButton7 = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTableForecastHistoricalCars = new javax.swing.JTable();
        jTextForecastCarID = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jPanelSchedule = new javax.swing.JPanel();
        jButtonScheduleHome = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableScheduleTrack = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        jTextScheduleStart = new javax.swing.JTextField();
        jTextScheduleGoalMiles = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jButtonScheduleDelete = new javax.swing.JButton();
        jButtonScheduleClear = new javax.swing.JButton();
        jButtonScheduleUpdate = new javax.swing.JButton();
        jButtonScheduleInsert = new javax.swing.JButton();
        jTextSchedulePrio = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jTextScheduleTestTech = new javax.swing.JTextField();
        jTextScheduleCarID = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jDateChooserScheduleDate = new com.toedter.calendar.JDateChooser();
        jPanelRecords = new javax.swing.JPanel();
        jButtonRecordsHome = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableRecordsTrack = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jTextRecordsActLaps = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jTextRecordsEnd = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextAreaRecordsComment = new javax.swing.JTextArea();
        jButtonRecordsUpdate = new javax.swing.JButton();
        jButtonRecordsDelete = new javax.swing.JButton();
        jButtonRecordsClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Parent.setBackground(new java.awt.Color(255, 255, 255));
        Parent.setPreferredSize(new java.awt.Dimension(950, 650));
        Parent.setLayout(new java.awt.CardLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/car.png"))); // NOI18N

        jButtonCarsPage.setText("Cars");
        jButtonCarsPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCarsPageActionPerformed(evt);
            }
        });

        jButtonDriversPage.setText("Drivers");
        jButtonDriversPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDriversPageActionPerformed(evt);
            }
        });

        jButtonHolidaysPage.setText("Holidays");
        jButtonHolidaysPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHolidaysPageActionPerformed(evt);
            }
        });

        jButtonForecastPage.setText("Forecast");
        jButtonForecastPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonForecastPageActionPerformed(evt);
            }
        });

        jButtonRecordsPage.setText("Records");
        jButtonRecordsPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRecordsPageActionPerformed(evt);
            }
        });

        jButtonSearchPage.setText("Schedule");
        jButtonSearchPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchPageActionPerformed(evt);
            }
        });

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/person.png"))); // NOI18N

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calander.png"))); // NOI18N

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/forecast.png"))); // NOI18N

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/document.png"))); // NOI18N

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/planning.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel7.setText("Club Car Forecast Tool");

        javax.swing.GroupLayout jPanelHomeLayout = new javax.swing.GroupLayout(jPanelHome);
        jPanelHome.setLayout(jPanelHomeLayout);
        jPanelHomeLayout.setHorizontalGroup(
            jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHomeLayout.createSequentialGroup()
                .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelHomeLayout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jButtonForecastPage)))
                    .addGroup(jPanelHomeLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonCarsPage, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(1, 1, 1)))
                .addGap(136, 136, 136)
                .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSearchPage)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButtonDriversPage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(139, 139, 139)
                .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonHolidaysPage)
                    .addComponent(jLabel22)
                    .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButtonRecordsPage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(204, 204, 204))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHomeLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(220, 220, 220))
        );
        jPanelHomeLayout.setVerticalGroup(
            jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHomeLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel7)
                .addGap(50, 50, 50)
                .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCarsPage)
                    .addComponent(jButtonDriversPage)
                    .addComponent(jButtonHolidaysPage))
                .addGap(72, 72, 72)
                .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonForecastPage)
                    .addComponent(jButtonRecordsPage)
                    .addComponent(jButtonSearchPage))
                .addGap(168, 168, 168))
        );

        Parent.add(jPanelHome, "card2");

        jPanelCars.setPreferredSize(new java.awt.Dimension(950, 650));

        jTableCars.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Car ID", "TR #", "Project", "Requestor", "Test Eng.", "Test Tech", "Car Type", "Fuel Type", "Req. Laps", "Act. Laps", "Laps Left", "Laps/hr", "Total Daily Hours", "Est. Completion", "Need Date", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableCars.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCarsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableCars);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Cars");

        jLabel3.setText("Car ID");

        jLabel4.setText("TR #");

        jLabel5.setText("Req. Laps");

        jLabel6.setText("Start Date");

        jLabel10.setText("Max Laps");

        jLabel11.setText("Status");

        jLabel12.setText("Requestor");

        jButtonCarsHome.setText("Home");
        jButtonCarsHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCarsHomeActionPerformed(evt);
            }
        });

        jLabel17.setText("Need Date");

        jLabel18.setText("Project");

        jButtonCarsClear.setText("Clear");
        jButtonCarsClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCarsClearActionPerformed(evt);
            }
        });

        jButtonCarsInsert.setText("Insert");
        jButtonCarsInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCarsInsertActionPerformed(evt);
            }
        });

        jButtonCarsUpdate.setText("Update");
        jButtonCarsUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCarsUpdateActionPerformed(evt);
            }
        });

        jButtonCarsDelete.setText("Delete");
        jButtonCarsDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCarsDeleteActionPerformed(evt);
            }
        });

        jDateChooserCarsNeedDate.setDateFormatString("yyyy-MM-dd");

        jDateChooserCarsStartDate.setDateFormatString("yyyy-MM-dd");

        jLabel32.setText("Test Eng.");

        jLabel33.setText("Test Tech");

        jLabel34.setText("Car Type");

        jLabel35.setText("Fuel Type");

        jLabel37.setText("Act. Laps");

        jLabel41.setText("Laps Left");

        jLabel43.setText("Laps/hr");

        jLabel48.setText("Total Daily Hours");

        jTextCarsStatus2.setEditable(false);

        jLabel49.setText("Est. Completion");

        buttonGroupCarsFuelType.add(jRadioButtonCarsFuelGas);
        jRadioButtonCarsFuelGas.setText("Gas");
        jRadioButtonCarsFuelGas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCarsFuelGasActionPerformed(evt);
            }
        });

        buttonGroupCarsFuelType.add(jRadioButtonCarsFuelElectric);
        jRadioButtonCarsFuelElectric.setText("Electric");
        jRadioButtonCarsFuelElectric.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCarsFuelElectricActionPerformed(evt);
            }
        });

        buttonGroupCarsStatus.add(jRadioButtonCarsStatusActive);
        jRadioButtonCarsStatusActive.setText("Active");

        buttonGroupCarsStatus.add(jRadioButtonCarsStatusInactive);
        jRadioButtonCarsStatusInactive.setText("Inactive");
        jRadioButtonCarsStatusInactive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCarsStatusInactiveActionPerformed(evt);
            }
        });

        jTableCarsTotalHours.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Total Hours"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(jTableCarsTotalHours);

        jTableCarsHoursRemaining.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Hours Remaining"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(jTableCarsHoursRemaining);

        jComboBoxCarsStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive", "All" }));
        jComboBoxCarsStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCarsStatusActionPerformed(evt);
            }
        });

        jButtonCarsForecast.setText("Forecast Update");
        jButtonCarsForecast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCarsForecastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCarsLayout = new javax.swing.GroupLayout(jPanelCars);
        jPanelCars.setLayout(jPanelCarsLayout);
        jPanelCarsLayout.setHorizontalGroup(
            jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCarsLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addComponent(jButtonCarsInsert)
                                .addGap(70, 70, 70)
                                .addComponent(jButtonCarsUpdate))
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextCarsRequestor, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextCarsTRNo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextCarsCarID, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextCarsProject, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextCarsTestEng, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextCarsTestTech, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                                        .addComponent(jLabel43)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextCarsLapsHr, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                                        .addComponent(jLabel41)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextCarsLapsLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                                        .addComponent(jLabel37)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextCarsActLaps, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextCarsReqLaps, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel35)
                                            .addComponent(jLabel34))
                                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCarsLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextCarsCarType, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(jRadioButtonCarsFuelGas)
                                                .addGap(18, 18, 18)
                                                .addComponent(jRadioButtonCarsFuelElectric))))))
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jButtonCarsDelete)
                                .addGap(60, 60, 60)
                                .addComponent(jButtonCarsClear)))
                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                                        .addComponent(jLabel48)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextCarsTotalDailyHours, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextCarsMaxLaps, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(17, 17, 17))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCarsLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCarsLayout.createSequentialGroup()
                                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                                .addComponent(jLabel17)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jDateChooserCarsNeedDate, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jDateChooserCarsStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                                .addComponent(jLabel49)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextCarsStatus2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCarsLayout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButtonCarsStatusActive)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jRadioButtonCarsStatusInactive)
                                        .addGap(30, 30, 30))
                                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                                        .addGap(62, 62, 62)
                                        .addComponent(jButtonCarsForecast)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonCarsHome, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelCarsLayout.createSequentialGroup()
                            .addGap(425, 425, 425)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxCarsStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 868, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(filler1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(49, 49, 49))
        );
        jPanelCarsLayout.setVerticalGroup(
            jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCarsLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxCarsStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jTextCarsCarID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel34)
                                    .addComponent(jTextCarsCarType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel48)
                                    .addComponent(jTextCarsTotalDailyHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jTextCarsTRNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel35)
                                    .addComponent(jRadioButtonCarsFuelGas)
                                    .addComponent(jRadioButtonCarsFuelElectric)
                                    .addComponent(jLabel10)
                                    .addComponent(jTextCarsMaxLaps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel18)
                                        .addComponent(jTextCarsProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5)
                                        .addComponent(jTextCarsReqLaps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooserCarsStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel12)
                                        .addComponent(jTextCarsRequestor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel37)
                                        .addComponent(jTextCarsActLaps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jDateChooserCarsNeedDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel32)
                                    .addComponent(jTextCarsTestEng, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel41)
                                    .addComponent(jTextCarsLapsLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel49)
                                    .addComponent(jTextCarsStatus2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jRadioButtonCarsStatusInactive)
                                            .addComponent(jRadioButtonCarsStatusActive)))
                                    .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel33)
                                        .addComponent(jTextCarsTestTech, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel43)
                                        .addComponent(jTextCarsLapsHr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCarsHome)
                    .addComponent(jButtonCarsClear)
                    .addComponent(jButtonCarsInsert)
                    .addComponent(jButtonCarsUpdate)
                    .addComponent(jButtonCarsDelete)
                    .addComponent(jButtonCarsForecast))
                .addGap(50, 50, 50))
        );

        Parent.add(jPanelCars, "card3");

        jTableDrivers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Driver", "Hours"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableDrivers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDriversMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableDrivers);

        jButtonDriversHome.setText("Home");
        jButtonDriversHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDriversHomeActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel13.setText("Drivers");

        jLabel27.setText("Goal Hours");

        jLabel28.setText("Total Hours");

        jTextDriversGoalHours.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextDriversGoalHoursKeyReleased(evt);
            }
        });

        jTextDriversTotalHours.setEditable(false);

        jLabel14.setText("Driver");

        jLabel15.setText("Hours");

        jButtonDriversInsert.setText("Insert");
        jButtonDriversInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDriversInsertActionPerformed(evt);
            }
        });

        jButtonDriversUpdate.setText("Update");
        jButtonDriversUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDriversUpdateActionPerformed(evt);
            }
        });

        jButtonDriversDelete.setText("Delete");
        jButtonDriversDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDriversDeleteActionPerformed(evt);
            }
        });

        jButtonDriversClear.setText("Clear");
        jButtonDriversClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDriversClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDriversLayout = new javax.swing.GroupLayout(jPanelDrivers);
        jPanelDrivers.setLayout(jPanelDriversLayout);
        jPanelDriversLayout.setHorizontalGroup(
            jPanelDriversLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDriversLayout.createSequentialGroup()
                .addGroup(jPanelDriversLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDriversLayout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addGroup(jPanelDriversLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel27)
                            .addGroup(jPanelDriversLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14)
                                .addComponent(jLabel15))
                            .addComponent(jButtonDriversInsert))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDriversLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDriversLayout.createSequentialGroup()
                                .addGroup(jPanelDriversLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextDriversGoalHours)
                                    .addComponent(jTextDriversTotalHours)
                                    .addComponent(jTextDriversDriver)
                                    .addComponent(jTextDriversHours, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelDriversHourCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(136, 136, 136))
                            .addGroup(jPanelDriversLayout.createSequentialGroup()
                                .addComponent(jButtonDriversUpdate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonDriversDelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonDriversClear)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelDriversLayout.createSequentialGroup()
                        .addGap(413, 413, 413)
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(86, 86, 86))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDriversLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonDriversHome)
                .addGap(39, 39, 39))
        );
        jPanelDriversLayout.setVerticalGroup(
            jPanelDriversLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDriversLayout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(jPanelDriversLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextDriversDriver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDriversLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextDriversHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanelDriversLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDriversInsert)
                    .addComponent(jButtonDriversUpdate)
                    .addComponent(jButtonDriversDelete)
                    .addComponent(jButtonDriversClear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelDriversLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jTextDriversGoalHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDriversLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDriversLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel28)
                        .addComponent(jTextDriversTotalHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelDriversHourCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addComponent(jButtonDriversHome)
                .addGap(35, 35, 35))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDriversLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel13)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 103, Short.MAX_VALUE))
        );

        Parent.add(jPanelDrivers, "card4");

        jTableHolidays.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableHolidays.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableHolidaysMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableHolidays);

        jButtonHolidaysHome.setText("Home");
        jButtonHolidaysHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHolidaysHomeActionPerformed(evt);
            }
        });

        jLabel8.setText("Date:");

        jDateChooserHolidaysDate.setDateFormatString("yyyy-MM-dd");

        jButtonHolidaysInsert.setText("Insert");
        jButtonHolidaysInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHolidaysInsertActionPerformed(evt);
            }
        });

        jButtonHolidaysUpdate.setText("Update");
        jButtonHolidaysUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHolidaysUpdateActionPerformed(evt);
            }
        });

        jButtonHolidaysDelete.setText("Delete");
        jButtonHolidaysDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHolidaysDeleteActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel9.setText("Holidays");

        javax.swing.GroupLayout jPanelHolidaysLayout = new javax.swing.GroupLayout(jPanelHolidays);
        jPanelHolidays.setLayout(jPanelHolidaysLayout);
        jPanelHolidaysLayout.setHorizontalGroup(
            jPanelHolidaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHolidaysLayout.createSequentialGroup()
                .addGap(405, 405, 405)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHolidaysLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanelHolidaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelHolidaysLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooserHolidaysDate, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelHolidaysLayout.createSequentialGroup()
                        .addComponent(jButtonHolidaysInsert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonHolidaysUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonHolidaysDelete)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 236, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHolidaysLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonHolidaysHome)
                .addGap(44, 44, 44))
        );
        jPanelHolidaysLayout.setVerticalGroup(
            jPanelHolidaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHolidaysLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel9)
                .addGroup(jPanelHolidaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelHolidaysLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(jPanelHolidaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooserHolidaysDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71)
                        .addGroup(jPanelHolidaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonHolidaysInsert)
                            .addComponent(jButtonHolidaysUpdate)
                            .addComponent(jButtonHolidaysDelete)))
                    .addGroup(jPanelHolidaysLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addComponent(jButtonHolidaysHome)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        Parent.add(jPanelHolidays, "card5");

        jButtonForecastHome.setText("Home");
        jButtonForecastHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonForecastHomeActionPerformed(evt);
            }
        });

        jTableForecastActiveCars.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "TR #", "Project", "Requestor", "Test Eng.", "Test Tech", "Car ID", "Car Type", "Fuel Type", "Req. Laps", "Act. Laps", "Laps Left", "Start Date", "Est. Completion", "Need Date", "Date Done"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTableForecastActiveCars);

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel26.setText("Forecasts");

        jLabel50.setText("Date");

        jDateChooserForecastForecastDate.setDateFormatString("yyyy-MM-dd");

        jButton7.setText("Look up");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel51.setText("Historical");

        jTableForecastHistoricalCars.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "TR #", "Project", "Requestor", "Test Eng.", "Test Tech", "Car ID", "Car Type", "Fuel Type", "Req. Laps", "Act. Laps", "Laps Left", "Start Date", "Est. Completion", "Need Date", "Date Done"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane11.setViewportView(jTableForecastHistoricalCars);

        jLabel16.setText("Car ID");

        jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel52.setText("Active");

        javax.swing.GroupLayout jPanelForecastLayout = new javax.swing.GroupLayout(jPanelForecast);
        jPanelForecast.setLayout(jPanelForecastLayout);
        jPanelForecastLayout.setHorizontalGroup(
            jPanelForecastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelForecastLayout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(jPanelForecastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelForecastLayout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addGap(426, 426, 426))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelForecastLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextForecastCarID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooserForecastForecastDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)
                        .addGap(119, 119, 119)
                        .addComponent(jButtonForecastHome)
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelForecastLayout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addGap(435, 435, 435))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelForecastLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(410, 410, 410))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelForecastLayout.createSequentialGroup()
                        .addGroup(jPanelForecastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68))))
        );
        jPanelForecastLayout.setVerticalGroup(
            jPanelForecastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelForecastLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanelForecastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelForecastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel50)
                        .addComponent(jTextForecastCarID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16))
                    .addComponent(jDateChooserForecastForecastDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelForecastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton7)
                        .addComponent(jButtonForecastHome)))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        Parent.add(jPanelForecast, "card6");

        jButtonScheduleHome.setText("Home");
        jButtonScheduleHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonScheduleHomeActionPerformed(evt);
            }
        });

        jTableScheduleTrack.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Date", "Car ID", "Test Tech", "Priority", "Goal Miles", "Act. Miles", "Start", "End", "Comment"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableScheduleTrack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableScheduleTrackMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTableScheduleTrack);

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel29.setText("Schedule");

        jLabel40.setText("Goal Miles");

        jLabel42.setText("Start");

        jButtonScheduleDelete.setText("Delete");
        jButtonScheduleDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonScheduleDeleteActionPerformed(evt);
            }
        });

        jButtonScheduleClear.setText("Clear");
        jButtonScheduleClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonScheduleClearActionPerformed(evt);
            }
        });

        jButtonScheduleUpdate.setText("Update");
        jButtonScheduleUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonScheduleUpdateActionPerformed(evt);
            }
        });

        jButtonScheduleInsert.setText("Insert");
        jButtonScheduleInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonScheduleInsertActionPerformed(evt);
            }
        });

        jLabel44.setText("Priority");

        jLabel45.setText("Test Tech");

        jLabel46.setText("Car ID");

        jLabel47.setText("Date");

        jDateChooserScheduleDate.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanelScheduleLayout = new javax.swing.GroupLayout(jPanelSchedule);
        jPanelSchedule.setLayout(jPanelScheduleLayout);
        jPanelScheduleLayout.setHorizontalGroup(
            jPanelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelScheduleLayout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addGroup(jPanelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelScheduleLayout.createSequentialGroup()
                        .addGap(282, 282, 282)
                        .addComponent(jButtonScheduleDelete)
                        .addGap(53, 53, 53)
                        .addComponent(jButtonScheduleClear))
                    .addGroup(jPanelScheduleLayout.createSequentialGroup()
                        .addComponent(jButtonScheduleInsert)
                        .addGap(50, 50, 50)
                        .addComponent(jButtonScheduleUpdate)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonScheduleHome)
                .addGap(36, 36, 36))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelScheduleLayout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addGroup(jPanelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelScheduleLayout.createSequentialGroup()
                        .addGroup(jPanelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelScheduleLayout.createSequentialGroup()
                                .addGroup(jPanelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel46)
                                    .addComponent(jLabel45))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextScheduleCarID)
                                    .addComponent(jTextScheduleTestTech, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelScheduleLayout.createSequentialGroup()
                                .addComponent(jLabel47)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooserScheduleDate, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelScheduleLayout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextScheduleStart, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelScheduleLayout.createSequentialGroup()
                                .addGroup(jPanelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel44)
                                    .addComponent(jLabel40))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextScheduleGoalMiles, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextSchedulePrio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelScheduleLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29)
                            .addGap(371, 371, 371))
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 846, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46))
        );
        jPanelScheduleLayout.setVerticalGroup(
            jPanelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelScheduleLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelScheduleLayout.createSequentialGroup()
                        .addGroup(jPanelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextSchedulePrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel44))
                            .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelScheduleLayout.createSequentialGroup()
                                .addGroup(jPanelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextScheduleCarID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel46))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextScheduleTestTech, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel45)))
                            .addGroup(jPanelScheduleLayout.createSequentialGroup()
                                .addGroup(jPanelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel40)
                                    .addComponent(jTextScheduleGoalMiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel42)
                                    .addComponent(jTextScheduleStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jDateChooserScheduleDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addGroup(jPanelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonScheduleHome)
                    .addComponent(jButtonScheduleInsert)
                    .addComponent(jButtonScheduleUpdate)
                    .addComponent(jButtonScheduleDelete)
                    .addComponent(jButtonScheduleClear))
                .addGap(29, 29, 29))
        );

        Parent.add(jPanelSchedule, "card8");

        jPanelRecords.setPreferredSize(new java.awt.Dimension(950, 650));

        jButtonRecordsHome.setText("Home");
        jButtonRecordsHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRecordsHomeActionPerformed(evt);
            }
        });

        jTableRecordsTrack.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Date", "Car ID", "Test Tech", "Priority", "Goal Miles", "Act. Laps", "Start", "End", "Comment"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableRecordsTrack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableRecordsTrackMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTableRecordsTrack);

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel30.setText("Records");

        jLabel36.setText("Act. Laps");

        jLabel38.setText("End time");

        jLabel39.setText("Comment");

        jTextAreaRecordsComment.setColumns(20);
        jTextAreaRecordsComment.setRows(5);
        jScrollPane7.setViewportView(jTextAreaRecordsComment);

        jButtonRecordsUpdate.setText("Update");
        jButtonRecordsUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRecordsUpdateActionPerformed(evt);
            }
        });

        jButtonRecordsDelete.setText("Delete");
        jButtonRecordsDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRecordsDeleteActionPerformed(evt);
            }
        });

        jButtonRecordsClear.setText("Clear");
        jButtonRecordsClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRecordsClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelRecordsLayout = new javax.swing.GroupLayout(jPanelRecords);
        jPanelRecords.setLayout(jPanelRecordsLayout);
        jPanelRecordsLayout.setHorizontalGroup(
            jPanelRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRecordsLayout.createSequentialGroup()
                .addGap(443, 443, 443)
                .addComponent(jLabel30)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelRecordsLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanelRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRecordsLayout.createSequentialGroup()
                        .addGroup(jPanelRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelRecordsLayout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addComponent(jButtonRecordsUpdate)
                                .addGap(63, 63, 63)
                                .addComponent(jButtonRecordsDelete)
                                .addGap(84, 84, 84))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRecordsLayout.createSequentialGroup()
                                .addGroup(jPanelRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanelRecordsLayout.createSequentialGroup()
                                        .addComponent(jLabel38)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextRecordsEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelRecordsLayout.createSequentialGroup()
                                        .addComponent(jLabel36)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextRecordsActLaps, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(104, 104, 104)))
                        .addComponent(jLabel39)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 845, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 47, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRecordsLayout.createSequentialGroup()
                .addGap(477, 477, 477)
                .addComponent(jButtonRecordsClear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonRecordsHome)
                .addGap(38, 38, 38))
        );
        jPanelRecordsLayout.setVerticalGroup(
            jPanelRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRecordsLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel30)
                .addGroup(jPanelRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRecordsLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(jPanelRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelRecordsLayout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addGap(37, 37, 37))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                        .addGroup(jPanelRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonRecordsHome)
                            .addComponent(jButtonRecordsUpdate)
                            .addComponent(jButtonRecordsDelete)
                            .addComponent(jButtonRecordsClear))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRecordsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(jTextRecordsActLaps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(jTextRecordsEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(153, 153, 153))))
        );

        Parent.add(jPanelRecords, "card7");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Parent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Parent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCarsPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCarsPageActionPerformed
        // TODO add your handling code here:
        Parent.removeAll();
        Parent.add(jPanelCars);
        Parent.repaint();
        Parent.revalidate();
    }//GEN-LAST:event_jButtonCarsPageActionPerformed

    private void jButtonDriversHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDriversHomeActionPerformed
        // TODO add your handling code here:
        Parent.removeAll();
        Parent.add(jPanelHome);
        Parent.repaint();
        Parent.revalidate();
    }//GEN-LAST:event_jButtonDriversHomeActionPerformed

    private void jButtonDriversPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDriversPageActionPerformed
        // TODO add your handling code here:
        Parent.removeAll();
        Parent.add(jPanelDrivers);
        Parent.repaint();
        Parent.revalidate();
    }//GEN-LAST:event_jButtonDriversPageActionPerformed

    private void jButtonForecastPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonForecastPageActionPerformed
        // TODO add your handling code here:
        Parent.removeAll();
        Parent.add(jPanelForecast);
        Parent.repaint();
        Parent.revalidate();
    }//GEN-LAST:event_jButtonForecastPageActionPerformed

    public void carsClear() {
        jTextCarsCarID.setText("");
        jTextCarsTRNo.setText("");
        jTextCarsProject.setText("");
        jTextCarsRequestor.setText("");
        jTextCarsTestEng.setText("");
        jTextCarsTestTech.setText("");
        jTextCarsCarType.setText("");
        buttonGroupCarsFuelType.clearSelection();
        jTextCarsReqLaps.setText("");
        jTextCarsActLaps.setText("");
        jTextCarsLapsLeft.setText("");
        jTextCarsLapsHr.setText("");
        jTextCarsTotalDailyHours.setText("");
        jTextCarsMaxLaps.setText("");
        jDateChooserCarsStartDate.setDate(null);
        jDateChooserCarsNeedDate.setDate(null);
        buttonGroupCarsStatus.clearSelection();
        jTableCars.clearSelection();
    }
    
    private void jButtonCarsDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCarsDeleteActionPerformed
        // TODO add your handling code here:
        try {    
            int i = jTableCars.getSelectedRow();
            TableModel model = jTableCars.getModel();
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "DELETE FROM `clubcar`.`car` " +
                            "WHERE `carID` = ? AND `TRNo` = ? AND `projectName` = ? AND `requestor` = ? AND " + 
                            "`testEng` = ? AND `testTech` = ? AND `carType` = ? AND `fuelType` = ? AND " + 
                            "`carRequiredLaps` = ? AND `carLapsLeft` = ? AND `lapsHr` = ? AND " + 
                            "`dailyHours` = ? AND `needDate` = ? AND `status` = ?;";
            PreparedStatement pst = connection.prepareStatement(query1);
            pst.setString(1, model.getValueAt(i, 0).toString());
            pst.setString(2, model.getValueAt(i, 1).toString());
            pst.setString(3, model.getValueAt(i, 2).toString());
            pst.setString(4, model.getValueAt(i, 3).toString());
            pst.setString(5, model.getValueAt(i, 4).toString());
            pst.setString(6, model.getValueAt(i, 5).toString());
            pst.setString(7, model.getValueAt(i, 6).toString());
            pst.setString(8, model.getValueAt(i, 7).toString());
            pst.setString(9, model.getValueAt(i, 8).toString());
            pst.setString(10, model.getValueAt(i, 9).toString());
            pst.setString(11, model.getValueAt(i, 11).toString());
            pst.setString(12, model.getValueAt(i, 12).toString());
            pst.setString(13, model.getValueAt(i, 14).toString());
            pst.setString(14, model.getValueAt(i, 15).toString());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Car deleted.");
            carsClear();
            show_cars();
            show_activecars();
            show_historicalcars();
            show_driverHoursRemaining();
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonCarsDeleteActionPerformed

    private void jButtonCarsUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCarsUpdateActionPerformed
        // TODO add your handling code here:
        try {     
            int i = jTableCars.getSelectedRow();
            TableModel model = jTableCars.getModel();     
            SimpleDateFormat formatYMD = new SimpleDateFormat("yyyy-MM-dd");
            String startDate = formatYMD.format(jDateChooserCarsStartDate.getDate());
            String needDate = formatYMD.format(jDateChooserCarsNeedDate.getDate());
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "UPDATE `clubcar`.`car` " +
                            "SET `carID` = ?, `TRNo` = ?, `projectName` = ?, `requestor` = ?, " + 
                            "`testEng` = ?, `testTech` = ?, `carType` = ?, `fuelType` = ?, " + 
                            "`carRequiredLaps` = ?, `carLapsLeft` = ?, `lapsHr` = ?, `dailyHours` = ?, " + 
                            "`maxLaps` = ?, `startDate` = ?, `needDate` = ?, `status` = ? " + //16
                            "WHERE `carID` = ? AND `TRNo` = ? AND `projectName` = ? AND `requestor` = ? AND " + 
                            "`testEng` = ? AND `testTech` = ? AND `carType` = ? AND `fuelType` = ? AND " + 
                            "`carRequiredLaps` = ? AND `carLapsLeft` = ? AND `lapsHr` = ? AND " + 
                            "`dailyHours` = ? AND `needDate` = ? AND `status` = ?;"; //30
            PreparedStatement pst = connection.prepareStatement(query1);
            pst.setString(1, jTextCarsCarID.getText());
            pst.setString(2, jTextCarsTRNo.getText());
            pst.setString(3, jTextCarsProject.getText());
            pst.setString(4, jTextCarsRequestor.getText());
            pst.setString(5, jTextCarsTestEng.getText());
            pst.setString(6, jTextCarsTestTech.getText());
            pst.setString(7, jTextCarsCarType.getText());
            if(jRadioButtonCarsFuelElectric.isSelected()) {
                pst.setString(8, "E");
            } else {
                pst.setString(8, "G");
            }
            pst.setString(9, jTextCarsReqLaps.getText());
            pst.setString(10, jTextCarsLapsLeft.getText());
            pst.setString(11, jTextCarsLapsHr.getText());
            pst.setString(12, jTextCarsTotalDailyHours.getText());
            pst.setString(13, jTextCarsMaxLaps.getText());
            pst.setString(14, startDate);
            pst.setString(15, needDate);
            if(jRadioButtonCarsStatusInactive.isSelected()) {
                pst.setString(16, "I");
            } else {
                pst.setString(16, "A");
            }
            pst.setString(17, model.getValueAt(i, 0).toString());
            pst.setString(18, model.getValueAt(i, 1).toString());
            pst.setString(19, model.getValueAt(i, 2).toString());
            pst.setString(20, model.getValueAt(i, 3).toString());
            pst.setString(21, model.getValueAt(i, 4).toString());
            pst.setString(22, model.getValueAt(i, 5).toString());
            pst.setString(23, model.getValueAt(i, 6).toString());
            pst.setString(24, model.getValueAt(i, 7).toString());
            pst.setString(25, model.getValueAt(i, 8).toString());
            pst.setString(26, model.getValueAt(i, 10).toString());
            pst.setString(27, model.getValueAt(i, 11).toString());
            pst.setString(28, model.getValueAt(i, 12).toString());
            pst.setString(29, model.getValueAt(i, 14).toString());
            pst.setString(30, model.getValueAt(i, 15).toString());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Car updated.");
            carsClear();
            show_cars();
            show_activecars();
            show_historicalcars();
            show_driverHoursRemaining();
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonCarsUpdateActionPerformed

    private void jButtonCarsInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCarsInsertActionPerformed
        // TODO add your handling code here:
        try {          
            SimpleDateFormat formatYMD = new SimpleDateFormat("yyyy-MM-dd");
            String startDate = formatYMD.format(jDateChooserCarsStartDate.getDate());
            String needDate = formatYMD.format(jDateChooserCarsNeedDate.getDate());
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "INSERT INTO `clubcar`.`car` " +
                            "(`carID`, `TRNo`, `projectName`, `requestor`, `testEng`, `testTech`, `carType`, " + 
                            "`fuelType`, `carRequiredLaps`, `carLapsLeft`, `lapsHr`, `dailyHours`, `maxLaps`, " + 
                            "`startDate`, `needDate`, `status`) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
            PreparedStatement pst = connection.prepareStatement(query1);
            pst.setString(1, jTextCarsCarID.getText());
            pst.setString(2, jTextCarsTRNo.getText());
            pst.setString(3, jTextCarsProject.getText());
            pst.setString(4, jTextCarsRequestor.getText());
            pst.setString(5, jTextCarsTestEng.getText());
            pst.setString(6, jTextCarsTestTech.getText());
            pst.setString(7, jTextCarsCarType.getText());
            if(jRadioButtonCarsFuelElectric.isSelected()) {
                pst.setString(8, "E");
            } else {
                pst.setString(8, "G");
            }
            pst.setString(9, jTextCarsReqLaps.getText());
            pst.setString(10, jTextCarsLapsLeft.getText());
            pst.setString(11, jTextCarsLapsHr.getText());
            pst.setString(12, jTextCarsTotalDailyHours.getText());
            pst.setString(13, jTextCarsMaxLaps.getText());
            pst.setString(14, startDate);
            pst.setString(15, needDate);
            if(jRadioButtonCarsStatusInactive.isSelected()) {
                pst.setString(16, "I");
            } else {
                pst.setString(16, "A");
            }
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Car added.");
            carsClear();
            show_cars();
            show_activecars();
            show_historicalcars();
            show_driverHoursRemaining();
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonCarsInsertActionPerformed

    private void jButtonCarsClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCarsClearActionPerformed
        // TODO add your handling code here:
        carsClear();
    }//GEN-LAST:event_jButtonCarsClearActionPerformed

    private void jButtonCarsHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCarsHomeActionPerformed
        // TODO add your handling code here:
        Parent.removeAll();
        Parent.add(jPanelHome);
        Parent.repaint();
        Parent.revalidate();
    }//GEN-LAST:event_jButtonCarsHomeActionPerformed

    private void jTableCarsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCarsMouseClicked
        // TODO add your handling code here:
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date1;
        java.util.Date date2;
        int i = jTableCars.getSelectedRow();
        TableModel model = jTableCars.getModel();
        jTextCarsCarID.setText(model.getValueAt(i, 0).toString());
        jTextCarsTRNo.setText(model.getValueAt(i, 1).toString());
        jTextCarsProject.setText(model.getValueAt(i, 2).toString());
        jTextCarsRequestor.setText(model.getValueAt(i, 3).toString());
        jTextCarsTestEng.setText(model.getValueAt(i, 4).toString());
        jTextCarsTestTech.setText(model.getValueAt(i, 5).toString());
        jTextCarsCarType.setText(model.getValueAt(i, 6).toString());
        if(model.getValueAt(i, 7).toString().equals("E")) {
            jRadioButtonCarsFuelElectric.setSelected(true);
        } else {
            jRadioButtonCarsFuelGas.setSelected(true);
        }
        jTextCarsReqLaps.setText(model.getValueAt(i, 8).toString());
        jTextCarsActLaps.setText(model.getValueAt(i, 9).toString());
        jTextCarsLapsLeft.setText(model.getValueAt(i, 10).toString());
        jTextCarsLapsHr.setText(model.getValueAt(i, 11).toString());
        jTextCarsTotalDailyHours.setText(model.getValueAt(i, 12).toString());
        jTextCarsMaxLaps.setText("");
        try {
            date1 = formatter.parse(model.getValueAt(i, 14).toString());
            java.sql.Date startDate = new java.sql.Date(date1.getTime());
            jDateChooserCarsStartDate.setDate(startDate);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }try {
            date2 = formatter.parse(model.getValueAt(i, 14).toString());
            java.sql.Date needDate = new java.sql.Date(date2.getTime());
            jDateChooserCarsNeedDate.setDate(needDate);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        if(model.getValueAt(i, 15).toString().equals("I")) {
            jRadioButtonCarsStatusInactive.setSelected(true);
        } else {
            jRadioButtonCarsStatusActive.setSelected(true);
        }
    }//GEN-LAST:event_jTableCarsMouseClicked

    private void jButtonHolidaysPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHolidaysPageActionPerformed
        // TODO add your handling code here:
        Parent.removeAll();
        Parent.add(jPanelHolidays);
        Parent.repaint();
        Parent.revalidate();
    }//GEN-LAST:event_jButtonHolidaysPageActionPerformed

    private void jButtonHolidaysHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHolidaysHomeActionPerformed
        // TODO add your handling code here:
        Parent.removeAll();
        Parent.add(jPanelHome);
        Parent.repaint();
        Parent.revalidate();
    }//GEN-LAST:event_jButtonHolidaysHomeActionPerformed

    private void jButtonForecastHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonForecastHomeActionPerformed
        // TODO add your handling code here:
        Parent.removeAll();
        Parent.add(jPanelHome);
        Parent.repaint();
        Parent.revalidate();
    }//GEN-LAST:event_jButtonForecastHomeActionPerformed

    private void jButtonRecordsPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecordsPageActionPerformed
        // TODO add your handling code here:
        Parent.removeAll();
        Parent.add(jPanelRecords);
        Parent.repaint();
        Parent.revalidate();
    }//GEN-LAST:event_jButtonRecordsPageActionPerformed

    private void jButtonSearchPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchPageActionPerformed
        // TODO add your handling code here:
        Parent.removeAll();
        Parent.add(jPanelSchedule);
        Parent.repaint();
        Parent.revalidate();
    }//GEN-LAST:event_jButtonSearchPageActionPerformed

    private void jButtonScheduleHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonScheduleHomeActionPerformed
        // TODO add your handling code here:
        Parent.removeAll();
        Parent.add(jPanelHome);
        Parent.repaint();
        Parent.revalidate();
    }//GEN-LAST:event_jButtonScheduleHomeActionPerformed

    private void jButtonRecordsClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecordsClearActionPerformed
        // TODO add your handling code here:
        recordsClear();
    }//GEN-LAST:event_jButtonRecordsClearActionPerformed

    private void jButtonRecordsHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecordsHomeActionPerformed
        // TODO add your handling code here:
        Parent.removeAll();
        Parent.add(jPanelHome);
        Parent.repaint();
        Parent.revalidate();
    }//GEN-LAST:event_jButtonRecordsHomeActionPerformed

    private void jButtonScheduleClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonScheduleClearActionPerformed
        // TODO add your handling code here:
        jDateChooserScheduleDate.setDate(null);
        jTextScheduleCarID.setText("");
        jTextScheduleTestTech.setText("");
        jTextSchedulePrio.setText("");
        jTextScheduleGoalMiles.setText("");
        jTextScheduleStart.setText("");
    }//GEN-LAST:event_jButtonScheduleClearActionPerformed

    private void jButtonScheduleInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonScheduleInsertActionPerformed
        // TODO add your handling code here:
        try {          
            SimpleDateFormat formatYMD = new SimpleDateFormat("yyyy-MM-dd");
            String date = formatYMD.format(jDateChooserScheduleDate.getDate());
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "INSERT INTO `clubcar`.`track` " +
            "(`trackDate`, `carID`, `testTechnician`, `priority`, `requestedMilesToday`, `startTime`) " +
            "VALUES (?, ?, ?, ?, ?, ?); ";
            PreparedStatement pst = connection.prepareStatement(query1);
            pst.setString(1, date);
            pst.setString(2, jTextScheduleCarID.getText());
            pst.setString(3, jTextScheduleTestTech.getText());
            pst.setString(4, jTextSchedulePrio.getText());
            pst.setString(5, jTextScheduleGoalMiles.getText());
            pst.setString(6, jTextScheduleStart.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Schedule added.");
            scheduleClear();
            show_track();
            show_track2();
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonScheduleInsertActionPerformed

    private void jRadioButtonCarsFuelGasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCarsFuelGasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonCarsFuelGasActionPerformed

    private void jRadioButtonCarsFuelElectricActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCarsFuelElectricActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonCarsFuelElectricActionPerformed

    private void jRadioButtonCarsStatusInactiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCarsStatusInactiveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonCarsStatusInactiveActionPerformed

    private void jButtonHolidaysInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHolidaysInsertActionPerformed
        // TODO add your handling code here:
        try {          
            SimpleDateFormat formatYMD = new SimpleDateFormat("yyyy-MM-dd");
            String holiday = formatYMD.format(jDateChooserHolidaysDate.getDate());
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "INSERT INTO `clubcar`.`holidays` " +
                            "(`holiday`) VALUES(?); ";
            PreparedStatement pst = connection.prepareStatement(query1);
            pst.setString(1, holiday);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Holiday added.");
            show_holidays();
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonHolidaysInsertActionPerformed

    private void jTableHolidaysMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableHolidaysMouseClicked
        // TODO add your handling code here:
        int i = jTableHolidays.getSelectedRow();
        TableModel model = jTableHolidays.getModel();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date;
        try {
            date = formatter.parse(model.getValueAt(i, 0).toString());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            jDateChooserHolidaysDate.setDate(sqlDate);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_jTableHolidaysMouseClicked

    private void jButtonHolidaysUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHolidaysUpdateActionPerformed
        // TODO add your handling code here:
        try {
            int i = jTableHolidays.getSelectedRow();
            TableModel model = jTableHolidays.getModel();
            SimpleDateFormat formatYMD = new SimpleDateFormat("yyyy-MM-dd");
            String holiday = formatYMD.format(jDateChooserHolidaysDate.getDate());
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "UPDATE `clubcar`.`holidays` " +
                            "SET `holiday` = ? WHERE `holiday` = ?";
            PreparedStatement pst = connection.prepareStatement(query1);
            pst.setString(1, holiday);
            pst.setString(2, model.getValueAt(i, 0).toString());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Holiday updated.");
            show_holidays();
            jTableHolidays.clearSelection();
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonHolidaysUpdateActionPerformed

    private void jButtonHolidaysDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHolidaysDeleteActionPerformed
        // TODO add your handling code here:
        try {
            SimpleDateFormat formatYMD = new SimpleDateFormat("yyyy-MM-dd");
            String holiday = formatYMD.format(jDateChooserHolidaysDate.getDate());
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "DELETE FROM `clubcar`.`holidays` " +
                            "WHERE `holiday` = ?";
            PreparedStatement pst = connection.prepareStatement(query1);
            pst.setString(1, holiday);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Holiday deleted.");
            show_holidays();
            jTableHolidays.clearSelection();
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonHolidaysDeleteActionPerformed

    public void recordsClear() {
        jTextRecordsActLaps.setText("");
        jTextRecordsEnd.setText("");
        jTextAreaRecordsComment.setText("");
        jTableRecordsTrack.clearSelection();
    }
    
    private void jButtonRecordsUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecordsUpdateActionPerformed
        // TODO add your handling code here:
        try {    
            int i = jTableRecordsTrack.getSelectedRow();
            TableModel model = jTableRecordsTrack.getModel();
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "UPDATE `clubcar`.`track` " +
                            "SET `actualMiles` = ?, `endTime` = ?, `trackComments` = ? " + 
                            "WHERE `trackDate` = ? AND `carID` = ? AND `startTime` = ?; ";
            PreparedStatement pst = connection.prepareStatement(query1);
            pst.setString(1, jTextRecordsActLaps.getText());
            pst.setString(2, jTextRecordsEnd.getText());
            pst.setString(3, jTextAreaRecordsComment.getText());
            pst.setString(4, model.getValueAt(i, 0).toString());
            pst.setString(5, model.getValueAt(i, 1).toString());
            pst.setString(6, model.getValueAt(i, 6).toString());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record updated.");
            recordsClear();
            show_track();
            show_track2();
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonRecordsUpdateActionPerformed

    private void jButtonRecordsDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecordsDeleteActionPerformed
        // TODO add your handling code here:
        try {
            int i = jTableRecordsTrack.getSelectedRow();
            TableModel model = jTableRecordsTrack.getModel();
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "DELETE FROM `clubcar`.`track` " +
                            "WHERE `trackDate` = ? AND `carID` = ? AND `testTechnician` = ? " + 
                            "AND `priority` = ? AND `requestedMilesToday` = ? AND `actualMiles` = ? " +
                            "AND `startTime` = ? AND `endTime` = ? AND `trackComments` = ?;";
            PreparedStatement pst = connection.prepareStatement(query1);
            pst.setString(1, model.getValueAt(i, 0).toString());
            pst.setString(2, model.getValueAt(i, 1).toString());
            pst.setString(3, model.getValueAt(i, 2).toString());
            pst.setString(4, model.getValueAt(i, 3).toString());
            pst.setString(5, model.getValueAt(i, 4).toString());
            pst.setString(6, model.getValueAt(i, 5).toString());
            pst.setString(7, model.getValueAt(i, 6).toString());
            pst.setString(8, model.getValueAt(i, 7).toString());
            pst.setString(9, model.getValueAt(i, 8).toString());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record deleted.");
            recordsClear();
            show_track();
            show_track2();
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonRecordsDeleteActionPerformed

    private void jTableRecordsTrackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableRecordsTrackMouseClicked
        // TODO add your handling code here:
        int i = jTableRecordsTrack.getSelectedRow();
        TableModel model = jTableRecordsTrack.getModel();
        jTextRecordsActLaps.setText(model.getValueAt(i, 5).toString());
        jTextRecordsEnd.setText(model.getValueAt(i, 7).toString());
        try {
            jTextAreaRecordsComment.setText(model.getValueAt(i, 8).toString());
        } catch(NullPointerException e) {
            jTextAreaRecordsComment.setText("");
        }
    }//GEN-LAST:event_jTableRecordsTrackMouseClicked

    public void scheduleClear() {jDateChooserScheduleDate.setDate(null);
        jTextScheduleCarID.setText("");
        jTextScheduleTestTech.setText("");
        jTextSchedulePrio.setText("");
        jTextScheduleGoalMiles.setText("");
        jTextScheduleStart.setText("");
        jTableScheduleTrack.clearSelection();
    }
    
    private void jButtonScheduleUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonScheduleUpdateActionPerformed
        // TODO add your handling code here:
        try {    
            SimpleDateFormat formatYMD = new SimpleDateFormat("yyyy-MM-dd");
            String trackDate = formatYMD.format(jDateChooserScheduleDate.getDate());
            int i = jTableScheduleTrack.getSelectedRow();
            TableModel model = jTableScheduleTrack.getModel();
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "UPDATE `clubcar`.`track` " +
                            "SET `trackDate` = ?, `carID` = ?, `testTechnician` = ?, " + 
                            "`priority` = ?, `requestedMilesToday` = ?, `startTime` = ?" +
                            "WHERE `trackDate` = ? AND `carID` = ? AND `testTechnician` = ? " + 
                            "AND `priority` = ? AND `requestedMilesToday` = ? AND `startTime` = ?;";
            PreparedStatement pst = connection.prepareStatement(query1);
            pst.setString(1, trackDate);
            pst.setString(2, jTextScheduleCarID.getText());
            pst.setString(3, jTextScheduleTestTech.getText());
            pst.setString(4, jTextSchedulePrio.getText());
            pst.setString(5, jTextScheduleGoalMiles.getText());
            pst.setString(6, jTextScheduleStart.getText());
            pst.setString(7, model.getValueAt(i, 0).toString());
            pst.setString(8, model.getValueAt(i, 1).toString());
            pst.setString(9, model.getValueAt(i, 2).toString());
            pst.setString(10, model.getValueAt(i, 3).toString());
            pst.setString(11, model.getValueAt(i, 4).toString());
            pst.setString(12, model.getValueAt(i, 6).toString());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Schedule updated.");
            scheduleClear();
            show_track();
            show_track2();
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonScheduleUpdateActionPerformed

    private void jButtonScheduleDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonScheduleDeleteActionPerformed
        // TODO add your handling code here:
        try {    
            int i = jTableScheduleTrack.getSelectedRow();
            TableModel model = jTableScheduleTrack.getModel();
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "DELETE FROM `clubcar`.`track` " +
                            "WHERE `trackDate` = ? AND `carID` = ? AND `testTechnician` = ? " + 
                            "AND `priority` = ? AND `requestedMilesToday` = ? AND `startTime` = ?;";
            PreparedStatement pst = connection.prepareStatement(query1);
            pst.setString(1, model.getValueAt(i, 0).toString());
            pst.setString(2, model.getValueAt(i, 1).toString());
            pst.setString(3, model.getValueAt(i, 2).toString());
            pst.setString(4, model.getValueAt(i, 3).toString());
            pst.setString(5, model.getValueAt(i, 4).toString());
            pst.setString(6, model.getValueAt(i, 6).toString());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Schedule deleted.");
            scheduleClear();
            show_track();
            show_track2();
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonScheduleDeleteActionPerformed

    private void jTableScheduleTrackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableScheduleTrackMouseClicked
        // TODO add your handling code here:
        int i = jTableScheduleTrack.getSelectedRow();
        TableModel model = jTableScheduleTrack.getModel();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date;
        try {
            date = formatter.parse(model.getValueAt(i, 0).toString());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            jDateChooserScheduleDate.setDate(sqlDate);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        jTextScheduleCarID.setText(model.getValueAt(i, 1).toString());
        jTextScheduleTestTech.setText(model.getValueAt(i, 2).toString());
        jTextSchedulePrio.setText(model.getValueAt(i, 3).toString());
        jTextScheduleGoalMiles.setText(model.getValueAt(i, 4).toString());
        jTextScheduleStart.setText(model.getValueAt(i, 6).toString());
    }//GEN-LAST:event_jTableScheduleTrackMouseClicked
    
    public void hourChecker() {
        if(!jTextDriversGoalHours.getText().isEmpty() && (Integer.parseInt(jTextDriversTotalHours.getText()) - Integer.parseInt(jTextDriversGoalHours.getText())) >= 0) {
            jLabelDriversHourCheck.setText("Good");
        } else if(!jTextDriversGoalHours.getText().isEmpty() && (Integer.parseInt(jTextDriversTotalHours.getText()) - Integer.parseInt(jTextDriversGoalHours.getText())) < 0) {
            jLabelDriversHourCheck.setText("Over");
        } else {
            jLabelDriversHourCheck.setText("");
        }
   }
    
    public void driversClear() {
        jTextDriversDriver.setText("");
        jTextDriversHours.setText("");
        jTableDrivers.clearSelection();
    }
    
    private void jButtonDriversInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDriversInsertActionPerformed
        // TODO add your handling code here:
        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "INSERT INTO `clubcar`.`employeehours` " +
                            "(`employeeName`, `driverHours`) VALUES(?, ?); ";
            PreparedStatement pst = connection.prepareStatement(query1);
            pst.setString(1, jTextDriversDriver.getText());
            if (jTextDriversHours.getText().isBlank()) {
                pst.setString(2, "0");
            } else {
                pst.setString(2, jTextDriversHours.getText());
            }
            pst.executeUpdate();
            String query2 = "INSERT INTO `clubcar`.`employee` " +
                            "(`employeeName`, `employeeType`) VALUES(?, Driver);";
            PreparedStatement pst2 = connection.prepareStatement(query2);
            pst2.setString(1, jTextDriversDriver.getText());
            JOptionPane.showMessageDialog(null, "Driver added.");
            driversClear();
            hourChecker();
            show_drivers();
            show_driverHours();
            show_driverHoursRemaining();
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonDriversInsertActionPerformed

    private void jButtonDriversUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDriversUpdateActionPerformed
        // TODO add your handling code here:
        try {
            int i = jTableDrivers.getSelectedRow();
            TableModel model = jTableDrivers.getModel();
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "UPDATE `clubcar`.`employeehours` " +
                            "SET `employeeName` = ?, `driverHours` = ? " + 
                            "WHERE `employeeName` = ? AND `driverHours` = ?; ";
            PreparedStatement pst = connection.prepareStatement(query1);
            pst.setString(1, jTextDriversDriver.getText());
            pst.setString(2, jTextDriversHours.getText());
            pst.setString(3, model.getValueAt(i, 0).toString());
            pst.setString(4, model.getValueAt(i, 1).toString());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Hours Updated.");
            driversClear();
            hourChecker();
            show_drivers();
            show_driverHours();
            show_driverHoursRemaining();
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonDriversUpdateActionPerformed

    private void jButtonDriversDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDriversDeleteActionPerformed
        // TODO add your handling code here:
        try {
            int i = jTableDrivers.getSelectedRow();
            TableModel model = jTableDrivers.getModel();
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "DELETE FROM `clubcar`.`employeehours` " +
                            "WHERE `employeeName` = ? AND `driverHours` = ?; ";
            PreparedStatement pst = connection.prepareStatement(query1);
            pst.setString(1, model.getValueAt(i, 0).toString());
            pst.setString(2, model.getValueAt(i, 1).toString());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Hours Updated.");
            driversClear();
            hourChecker();
            show_drivers();
            show_driverHours();
            show_driverHoursRemaining();
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonDriversDeleteActionPerformed

    private void jButtonDriversClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDriversClearActionPerformed
        // TODO add your handling code here:
        driversClear();
    }//GEN-LAST:event_jButtonDriversClearActionPerformed

    private void jTableDriversMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDriversMouseClicked
        // TODO add your handling code here:
        int i = jTableDrivers.getSelectedRow();
        TableModel model = jTableDrivers.getModel();
        jTextDriversDriver.setText(model.getValueAt(i, 0).toString());
        jTextDriversHours.setText(model.getValueAt(i, 1).toString());
    }//GEN-LAST:event_jTableDriversMouseClicked

    private void jTextDriversGoalHoursKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextDriversGoalHoursKeyReleased
        // TODO add your handling code here:
        hourChecker();
    }//GEN-LAST:event_jTextDriversGoalHoursKeyReleased

    private void jButtonCarsForecastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCarsForecastActionPerformed
        // TODO add your handling code here:
        //
        try {
            int i = jTableCars.getSelectedRow();
            TableModel model = jTableCars.getModel();
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "CALL clubcar.forecast_date(?, 1);";
            PreparedStatement pst = connection.prepareStatement(query1);
            if(!model.getValueAt(i, 0).toString().isBlank()) {
                pst.setString(1, model.getValueAt(i, 0).toString());
            } else {
                pst.setString(1, jTextCarsCarID.getText());
            }
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Forecast updated.");
            carsClear();
            show_cars();
            show_activecars();
            show_historicalcars();
            show_driverHoursRemaining();
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonCarsForecastActionPerformed

    private void jComboBoxCarsStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCarsStatusActionPerformed
        // TODO add your handling code here:
        show_cars();
    }//GEN-LAST:event_jComboBoxCarsStatusActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        show_historicalcars();
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClubCarDemoMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClubCarDemoMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClubCarDemoMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClubCarDemoMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClubCarDemoMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Parent;
    private javax.swing.ButtonGroup buttonGroupCarsFuelType;
    private javax.swing.ButtonGroup buttonGroupCarsStatus;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButtonCarsClear;
    private javax.swing.JButton jButtonCarsDelete;
    private javax.swing.JButton jButtonCarsForecast;
    private javax.swing.JButton jButtonCarsHome;
    private javax.swing.JButton jButtonCarsInsert;
    private javax.swing.JButton jButtonCarsPage;
    private javax.swing.JButton jButtonCarsUpdate;
    private javax.swing.JButton jButtonDriversClear;
    private javax.swing.JButton jButtonDriversDelete;
    private javax.swing.JButton jButtonDriversHome;
    private javax.swing.JButton jButtonDriversInsert;
    private javax.swing.JButton jButtonDriversPage;
    private javax.swing.JButton jButtonDriversUpdate;
    private javax.swing.JButton jButtonForecastHome;
    private javax.swing.JButton jButtonForecastPage;
    private javax.swing.JButton jButtonHolidaysDelete;
    private javax.swing.JButton jButtonHolidaysHome;
    private javax.swing.JButton jButtonHolidaysInsert;
    private javax.swing.JButton jButtonHolidaysPage;
    private javax.swing.JButton jButtonHolidaysUpdate;
    private javax.swing.JButton jButtonRecordsClear;
    private javax.swing.JButton jButtonRecordsDelete;
    private javax.swing.JButton jButtonRecordsHome;
    private javax.swing.JButton jButtonRecordsPage;
    private javax.swing.JButton jButtonRecordsUpdate;
    private javax.swing.JButton jButtonScheduleClear;
    private javax.swing.JButton jButtonScheduleDelete;
    private javax.swing.JButton jButtonScheduleHome;
    private javax.swing.JButton jButtonScheduleInsert;
    private javax.swing.JButton jButtonScheduleUpdate;
    private javax.swing.JButton jButtonSearchPage;
    private javax.swing.JComboBox<String> jComboBoxCarsStatus;
    private com.toedter.calendar.JDateChooser jDateChooserCarsNeedDate;
    private com.toedter.calendar.JDateChooser jDateChooserCarsStartDate;
    private com.toedter.calendar.JDateChooser jDateChooserForecastForecastDate;
    private com.toedter.calendar.JDateChooser jDateChooserHolidaysDate;
    private com.toedter.calendar.JDateChooser jDateChooserScheduleDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelDriversHourCheck;
    private javax.swing.JPanel jPanelCars;
    private javax.swing.JPanel jPanelDrivers;
    private javax.swing.JPanel jPanelForecast;
    private javax.swing.JPanel jPanelHolidays;
    private javax.swing.JPanel jPanelHome;
    private javax.swing.JPanel jPanelRecords;
    private javax.swing.JPanel jPanelSchedule;
    private javax.swing.JRadioButton jRadioButtonCarsFuelElectric;
    private javax.swing.JRadioButton jRadioButtonCarsFuelGas;
    private javax.swing.JRadioButton jRadioButtonCarsStatusActive;
    private javax.swing.JRadioButton jRadioButtonCarsStatusInactive;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTableCars;
    private javax.swing.JTable jTableCarsHoursRemaining;
    private javax.swing.JTable jTableCarsTotalHours;
    private javax.swing.JTable jTableDrivers;
    private javax.swing.JTable jTableForecastActiveCars;
    private javax.swing.JTable jTableForecastHistoricalCars;
    private javax.swing.JTable jTableHolidays;
    private javax.swing.JTable jTableRecordsTrack;
    private javax.swing.JTable jTableScheduleTrack;
    private javax.swing.JTextArea jTextAreaRecordsComment;
    private javax.swing.JTextField jTextCarsActLaps;
    private javax.swing.JTextField jTextCarsCarID;
    private javax.swing.JTextField jTextCarsCarType;
    private javax.swing.JTextField jTextCarsLapsHr;
    private javax.swing.JTextField jTextCarsLapsLeft;
    private javax.swing.JTextField jTextCarsMaxLaps;
    private javax.swing.JTextField jTextCarsProject;
    private javax.swing.JTextField jTextCarsReqLaps;
    private javax.swing.JTextField jTextCarsRequestor;
    private javax.swing.JTextField jTextCarsStatus2;
    private javax.swing.JTextField jTextCarsTRNo;
    private javax.swing.JTextField jTextCarsTestEng;
    private javax.swing.JTextField jTextCarsTestTech;
    private javax.swing.JTextField jTextCarsTotalDailyHours;
    private javax.swing.JTextField jTextDriversDriver;
    private javax.swing.JTextField jTextDriversGoalHours;
    private javax.swing.JTextField jTextDriversHours;
    private javax.swing.JTextField jTextDriversTotalHours;
    private javax.swing.JTextField jTextForecastCarID;
    private javax.swing.JTextField jTextRecordsActLaps;
    private javax.swing.JTextField jTextRecordsEnd;
    private javax.swing.JTextField jTextScheduleCarID;
    private javax.swing.JTextField jTextScheduleGoalMiles;
    private javax.swing.JTextField jTextSchedulePrio;
    private javax.swing.JTextField jTextScheduleStart;
    private javax.swing.JTextField jTextScheduleTestTech;
    // End of variables declaration//GEN-END:variables
}
