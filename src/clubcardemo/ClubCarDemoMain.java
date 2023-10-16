package clubcardemo;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        show_activecars();
    }
        public ArrayList<Cars> carList() {
        ArrayList<Cars> carList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "SELECT * FROM clubcar.car;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Cars cars;
            while(rs.next()) {
                cars = new Cars(rs.getInt("carID"), rs.getString("carType"), rs.getString("projectName"), rs.getInt("carRequestedMiles"), 
                rs.getString("startDate"), rs.getString("needDate"), rs.getDouble("maxHoursDay"), rs.getString("Status"), rs.getString("requestor"));
                carList.add(cars);
            }
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return carList;
    }
        
   public void show_cars() {
        ArrayList<Cars> list = carList();
        DefaultTableModel model = (DefaultTableModel)jTableCars.getModel();
        model.setRowCount(0);
        Object[] row = new Object[9];
        for(int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getCarID();
            row[1] = list.get(i).getCarType();
            row[2] = list.get(i).getProjectName();
            row[3] = list.get(i).getCarReqMiles();
            row[4] = list.get(i).getStartDate();
            row[5] = list.get(i).getNeedDate();
            row[6] = list.get(i).getMaxHours();
            row[7] = list.get(i).getStatus();
            row[8] = list.get(i).getRequestor();
            model.addRow(row);
        }
    }     
   
   public ArrayList<Drivers> driverList() {
        ArrayList<Drivers> driverList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "SELECT * FROM clubcar.drivers;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Drivers drivers;
            while(rs.next()) {
                drivers = new Drivers(rs.getString("employeeName"));
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
        Object[] row = new Object[1];
        for(int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getDriverName();
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
     
      public ArrayList<ActiveCars> activeCarsList() {
        ArrayList<ActiveCars> activeCarsList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "SELECT * FROM clubcar.active_cars;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            ActiveCars activeCars;
            while(rs.next()) {
                activeCars = new ActiveCars(rs.getInt("carID"), rs.getString("carType"), rs.getString("projectName"), rs.getString("requestor"));
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
        Object[] row = new Object[4];
        for(int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getCarID();
            row[1] = list.get(i).getCarType();
            row[2] = list.get(i).getProject();
            row[3] = list.get(i).getRequestor();
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
        jTextCarsType = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextCarsReqMiles = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextCarsMaxHours = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextCarsStatus = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextCarsRequestor = new javax.swing.JTextField();
        jButtonCarsHome = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextCarsProject = new javax.swing.JTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 32767));
        jButtonCarsClear = new javax.swing.JButton();
        jButtonCarsInsert = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 25), new java.awt.Dimension(0, 25), new java.awt.Dimension(32767, 25));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(25, 0), new java.awt.Dimension(25, 0), new java.awt.Dimension(25, 32767));
        jDateChooserNeedDate = new com.toedter.calendar.JDateChooser();
        jDateChooserStartDate = new com.toedter.calendar.JDateChooser();
        jPanelDrivers = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableDrivers = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanelHolidays = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableHolidays = new javax.swing.JTable();
        jButtonHolidaysHome = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jDateChooserHolidaysDate = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanelForecast = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jButtonForecastHome = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableForecastActiveCars = new javax.swing.JTable();
        jTextForecastCarID = new javax.swing.JTextField();
        jPanelRecords = new javax.swing.JPanel();
        jButtonRecordsHome = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableRecordsTrack = new javax.swing.JTable();
        jPanelSearch = new javax.swing.JPanel();
        jButtonSearchHome = new javax.swing.JButton();

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

        jButtonSearchPage.setText("Search");
        jButtonSearchPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchPageActionPerformed(evt);
            }
        });

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/person.png"))); // NOI18N

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calander.png"))); // NOI18N

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/forecast.png"))); // NOI18N

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/document.png"))); // NOI18N

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel7.setText("Club Car Forecast Tool");

        javax.swing.GroupLayout jPanelHomeLayout = new javax.swing.GroupLayout(jPanelHome);
        jPanelHome.setLayout(jPanelHomeLayout);
        jPanelHomeLayout.setHorizontalGroup(
            jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHomeLayout.createSequentialGroup()
                .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelHomeLayout.createSequentialGroup()
                        .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelHomeLayout.createSequentialGroup()
                                .addGap(242, 242, 242)
                                .addComponent(jButtonCarsPage))
                            .addGroup(jPanelHomeLayout.createSequentialGroup()
                                .addGap(241, 241, 241)
                                .addComponent(jLabel1)))
                        .addGap(126, 126, 126)
                        .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jButtonDriversPage))
                        .addGap(153, 153, 153)
                        .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonHolidaysPage)
                            .addComponent(jLabel22)))
                    .addGroup(jPanelHomeLayout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelHomeLayout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(122, 122, 122)
                                .addComponent(jLabel24))
                            .addGroup(jPanelHomeLayout.createSequentialGroup()
                                .addComponent(jButtonForecastPage)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonRecordsPage)))
                        .addGap(152, 152, 152)
                        .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jButtonSearchPage))))
                .addContainerGap(204, Short.MAX_VALUE))
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
                .addContainerGap(168, Short.MAX_VALUE))
        );

        Parent.add(jPanelHome, "card2");

        jTableCars.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Car ID", "Car Type", "Project", "Req. Miles", "Start Date", "Need Date", "Max Hours", "Status", "Requestor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
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
        jTableCars.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCarsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableCars);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Cars");

        jLabel3.setText("Car ID");

        jLabel4.setText("Car Type");

        jLabel5.setText("Req. Miles");

        jLabel6.setText("Start Date");

        jLabel10.setText("Max Hours");

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

        jButton9.setText("Update");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Delete");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCarsLayout = new javax.swing.GroupLayout(jPanelCars);
        jPanelCars.setLayout(jPanelCarsLayout);
        jPanelCarsLayout.setHorizontalGroup(
            jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCarsLayout.createSequentialGroup()
                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextCarsType, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextCarsCarID, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextCarsReqMiles, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooserStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextCarsProject, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jButtonCarsInsert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9)))
                .addGap(60, 60, 60)
                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextCarsMaxHours, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextCarsStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooserNeedDate, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanelCarsLayout.createSequentialGroup()
                            .addComponent(jButton10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonCarsClear))
                        .addGroup(jPanelCarsLayout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextCarsRequestor, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 370, Short.MAX_VALUE)
                .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
            .addGroup(jPanelCarsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(filler1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonCarsHome)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCarsLayout.createSequentialGroup()
                .addGap(0, 19, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 907, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(jPanelCarsLayout.createSequentialGroup()
                .addGap(452, 452, 452)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelCarsLayout.setVerticalGroup(
            jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCarsLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jTextCarsCarID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jTextCarsType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(jTextCarsProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jTextCarsReqMiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jDateChooserStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCarsLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooserNeedDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jTextCarsMaxHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jTextCarsStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTextCarsRequestor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonCarsHome)
                            .addComponent(jButtonCarsClear)
                            .addComponent(jButtonCarsInsert)
                            .addComponent(jButton9)
                            .addComponent(jButton10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableDrivers);

        jButton8.setText("Home");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel13.setText("Drivers");

        javax.swing.GroupLayout jPanelDriversLayout = new javax.swing.GroupLayout(jPanelDrivers);
        jPanelDrivers.setLayout(jPanelDriversLayout);
        jPanelDriversLayout.setHorizontalGroup(
            jPanelDriversLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDriversLayout.createSequentialGroup()
                .addGroup(jPanelDriversLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDriversLayout.createSequentialGroup()
                        .addGap(413, 413, 413)
                        .addComponent(jLabel13))
                    .addGroup(jPanelDriversLayout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(125, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDriversLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton8)
                .addGap(41, 41, 41))
        );
        jPanelDriversLayout.setVerticalGroup(
            jPanelDriversLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDriversLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel13)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 294, Short.MAX_VALUE)
                .addComponent(jButton8)
                .addGap(44, 44, 44))
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

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
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

        jButton1.setText("Insert");

        jButton2.setText("Update");

        jButton3.setText("Delete");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel9.setText("Holidays");

        javax.swing.GroupLayout jPanelHolidaysLayout = new javax.swing.GroupLayout(jPanelHolidays);
        jPanelHolidays.setLayout(jPanelHolidaysLayout);
        jPanelHolidaysLayout.setHorizontalGroup(
            jPanelHolidaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHolidaysLayout.createSequentialGroup()
                .addGap(408, 408, 408)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHolidaysLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelHolidaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelHolidaysLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 789, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonHolidaysHome))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHolidaysLayout.createSequentialGroup()
                        .addGroup(jPanelHolidaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelHolidaysLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooserHolidaysDate, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelHolidaysLayout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 290, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)))
                .addGap(35, 35, 35))
        );
        jPanelHolidaysLayout.setVerticalGroup(
            jPanelHolidaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHolidaysLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel9)
                .addGap(27, 27, 27)
                .addGroup(jPanelHolidaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelHolidaysLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanelHolidaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooserHolidaysDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71)
                        .addGroup(jPanelHolidaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelHolidaysLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)))
                .addComponent(jButtonHolidaysHome)
                .addGap(74, 74, 74))
        );

        Parent.add(jPanelHolidays, "card5");

        jLabel19.setText("Car ID");

        jButtonForecastHome.setText("Home");
        jButtonForecastHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonForecastHomeActionPerformed(evt);
            }
        });

        jTableForecastActiveCars.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Car ID", "Car Type", "Project", "Requestor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTableForecastActiveCars);

        javax.swing.GroupLayout jPanelForecastLayout = new javax.swing.GroupLayout(jPanelForecast);
        jPanelForecast.setLayout(jPanelForecastLayout);
        jPanelForecastLayout.setHorizontalGroup(
            jPanelForecastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelForecastLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonForecastHome)
                .addGap(21, 21, 21))
            .addGroup(jPanelForecastLayout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextForecastCarID, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 299, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );
        jPanelForecastLayout.setVerticalGroup(
            jPanelForecastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelForecastLayout.createSequentialGroup()
                .addGroup(jPanelForecastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelForecastLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addGroup(jPanelForecastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jTextForecastCarID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelForecastLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 260, Short.MAX_VALUE)
                .addComponent(jButtonForecastHome)
                .addGap(26, 26, 26))
        );

        Parent.add(jPanelForecast, "card6");

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
                "Date", "Car ID", "Test Tech", "Prio", "Goal Miles", "Act. Miles", "Start", "End", "Comment"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTableRecordsTrack);

        javax.swing.GroupLayout jPanelRecordsLayout = new javax.swing.GroupLayout(jPanelRecords);
        jPanelRecords.setLayout(jPanelRecordsLayout);
        jPanelRecordsLayout.setHorizontalGroup(
            jPanelRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRecordsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonRecordsHome)
                .addGap(26, 26, 26))
            .addGroup(jPanelRecordsLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 847, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanelRecordsLayout.setVerticalGroup(
            jPanelRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRecordsLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 219, Short.MAX_VALUE)
                .addComponent(jButtonRecordsHome)
                .addGap(22, 22, 22))
        );

        Parent.add(jPanelRecords, "card7");

        jButtonSearchHome.setText("Home");
        jButtonSearchHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchHomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSearchLayout = new javax.swing.GroupLayout(jPanelSearch);
        jPanelSearch.setLayout(jPanelSearchLayout);
        jPanelSearchLayout.setHorizontalGroup(
            jPanelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSearchLayout.createSequentialGroup()
                .addContainerGap(848, Short.MAX_VALUE)
                .addComponent(jButtonSearchHome)
                .addGap(30, 30, 30))
        );
        jPanelSearchLayout.setVerticalGroup(
            jPanelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSearchLayout.createSequentialGroup()
                .addContainerGap(608, Short.MAX_VALUE)
                .addComponent(jButtonSearchHome)
                .addGap(20, 20, 20))
        );

        Parent.add(jPanelSearch, "card8");

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

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        Parent.removeAll();
        Parent.add(jPanelHome);
        Parent.repaint();
        Parent.revalidate();
    }//GEN-LAST:event_jButton8ActionPerformed

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

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButtonCarsInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCarsInsertActionPerformed
        // TODO add your handling code here:
        try {          
            SimpleDateFormat formatYMD = new SimpleDateFormat("yyyy-MM-dd");
            String startDate = formatYMD.format(jDateChooserStartDate.getDate());
            String needDate = formatYMD.format(jDateChooserNeedDate.getDate());
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "INSERT INTO `clubcar`.`car` " +
            "(`carID`, `carType`, `projectName`, `carRequestedMiles`, `startDate`, `needDate`, `maxHoursDay`, `status`, `requestor`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?); ";
            PreparedStatement pst = connection.prepareStatement(query1);
            pst.setString(1, jTextCarsCarID.getText());
            pst.setString(2, jTextCarsType.getText());
            pst.setString(3, jTextCarsType.getText());
            pst.setString(4, jTextCarsReqMiles.getText());
            pst.setString(5, startDate);
            pst.setString(6, needDate);
            pst.setString(7, jTextCarsMaxHours.getText());
            pst.setString(8, jTextCarsStatus.getText());
            pst.setString(9, jTextCarsProject.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Car added.");
            show_cars();
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonCarsInsertActionPerformed

    private void jButtonCarsClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCarsClearActionPerformed
        // TODO add your handling code here:
        jTextCarsCarID.setText("");
        jTextCarsType.setText("");
        jTextCarsType.setText("");
        jTextCarsReqMiles.setText("");
        jDateChooserStartDate.setDate(null);
        jDateChooserNeedDate.setDate(null);
        jTextCarsMaxHours.setText("");
        jTextCarsStatus.setText("");
        jTextCarsProject.setText("");
        jTextCarsRequestor.setText("");
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
        int i = jTableCars.getSelectedRow();
        TableModel model = jTableCars.getModel();
        jTextCarsCarID.setText(model.getValueAt(i, 0).toString());
        jTextCarsType.setText(model.getValueAt(i, 1).toString());
        jTextCarsProject.setText(model.getValueAt(i, 2).toString());
        jTextCarsReqMiles.setText(model.getValueAt(i, 3).toString());
        jDateChooserStartDate.setDate(null); //temp to clear these
        jDateChooserNeedDate.setDate(null);
//        jDateChooserStartDate.setDateFormatString(model.getValueAt(i, 4).toString()); //these two need to be fixed or skipped
//        jDateChooserNeedDate.setDateFormatString(model.getValueAt(i, 5).toString());  //2nd
        jTextCarsMaxHours.setText(model.getValueAt(i, 6).toString());
        jTextCarsStatus.setText(model.getValueAt(i, 7).toString());
        jTextCarsRequestor.setText(model.getValueAt(i, 8).toString());
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
        Parent.add(jPanelSearch);
        Parent.repaint();
        Parent.revalidate();
    }//GEN-LAST:event_jButtonSearchPageActionPerformed

    private void jButtonRecordsHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecordsHomeActionPerformed
        // TODO add your handling code here:
        Parent.removeAll();
        Parent.add(jPanelHome);
        Parent.repaint();
        Parent.revalidate();
    }//GEN-LAST:event_jButtonRecordsHomeActionPerformed

    private void jButtonSearchHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchHomeActionPerformed
        // TODO add your handling code here:
        Parent.removeAll();
        Parent.add(jPanelHome);
        Parent.repaint();
        Parent.revalidate();
    }//GEN-LAST:event_jButtonSearchHomeActionPerformed

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
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonCarsClear;
    private javax.swing.JButton jButtonCarsHome;
    private javax.swing.JButton jButtonCarsInsert;
    private javax.swing.JButton jButtonCarsPage;
    private javax.swing.JButton jButtonDriversPage;
    private javax.swing.JButton jButtonForecastHome;
    private javax.swing.JButton jButtonForecastPage;
    private javax.swing.JButton jButtonHolidaysHome;
    private javax.swing.JButton jButtonHolidaysPage;
    private javax.swing.JButton jButtonRecordsHome;
    private javax.swing.JButton jButtonRecordsPage;
    private javax.swing.JButton jButtonSearchHome;
    private javax.swing.JButton jButtonSearchPage;
    private com.toedter.calendar.JDateChooser jDateChooserHolidaysDate;
    private com.toedter.calendar.JDateChooser jDateChooserNeedDate;
    private com.toedter.calendar.JDateChooser jDateChooserStartDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanelCars;
    private javax.swing.JPanel jPanelDrivers;
    private javax.swing.JPanel jPanelForecast;
    private javax.swing.JPanel jPanelHolidays;
    private javax.swing.JPanel jPanelHome;
    private javax.swing.JPanel jPanelRecords;
    private javax.swing.JPanel jPanelSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTableCars;
    private javax.swing.JTable jTableDrivers;
    private javax.swing.JTable jTableForecastActiveCars;
    private javax.swing.JTable jTableHolidays;
    private javax.swing.JTable jTableRecordsTrack;
    private javax.swing.JTextField jTextCarsCarID;
    private javax.swing.JTextField jTextCarsMaxHours;
    private javax.swing.JTextField jTextCarsProject;
    private javax.swing.JTextField jTextCarsReqMiles;
    private javax.swing.JTextField jTextCarsRequestor;
    private javax.swing.JTextField jTextCarsStatus;
    private javax.swing.JTextField jTextCarsType;
    private javax.swing.JTextField jTextForecastCarID;
    // End of variables declaration//GEN-END:variables
}
