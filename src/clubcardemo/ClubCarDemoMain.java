package clubcardemo;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;

public class ClubCarDemoMain extends javax.swing.JFrame {
    String dbURL = "jdbc:mysql://localhost:3306/clubcar";
    String username = "root";
    String password = "root";
    /**
     * Creates new form ClubCarDemoMain
     */
    public ClubCarDemoMain() {
        initComponents();
        show_cars();
        show_drivers();
        show_holidays();
    }
        public ArrayList<Cars> carList() {
        ArrayList<Cars> carList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "SELECT * FROM clubcar.carsview;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Cars cars;
            while(rs.next()) {
                cars = new Cars(rs.getInt("carID"), rs.getString("carProject"), rs.getString("carRequestor"), rs.getString("carTestEng"), rs.getString("carTestTech"), rs.getInt("carPrio"), 
                rs.getString("carType"), rs.getString("carFuel"), rs.getInt("carMaxHours"), rs.getString("carTestType"), rs.getDouble("carReqHours"), rs.getDouble("carActHours"), rs.getInt("carReqLaps"), 
                rs.getInt("carActLaps"), rs.getInt("carLapsDay"), rs.getString("carStart"), rs.getString("carNeed"));
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
        Object[] row = new Object[17];
        for(int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getCarID();
            row[1] = list.get(i).getProject();
            row[2] = list.get(i).getRequestor();
            row[3] = list.get(i).getTestEng();
            row[4] = list.get(i).getTestTech();
            row[5] = list.get(i).getCarPrio();
            row[6] = list.get(i).getCarType();
            row[7] = list.get(i).getFuelType();
            row[8] = list.get(i).getMaxHours();
            row[9] = list.get(i).getTestType();
            row[10] = list.get(i).getReqHours();
            row[11] = list.get(i).getActHours();
            row[12] = list.get(i).getReqLaps();
            row[13] = list.get(i).getActLaps();
            row[14] = list.get(i).getLapsDay();
            row[15] = list.get(i).getStartDate();
            row[16] = list.get(i).getNeedDate();
            model.addRow(row);
        }
    }     
   
   public ArrayList<Drivers> driverList() {
        ArrayList<Drivers> driverList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "SELECT * FROM drivers;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Drivers drivers;
            while(rs.next()) {
                drivers = new Drivers(rs.getString("DriverName"), rs.getDouble("DriverHours"));
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
                holidays = new Holidays(rs.getString("HolidaysDate"), rs.getString("HolidayName"));
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
        Object[] row = new Object[2];
        for(int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getHolidayDate();
            row[1] = list.get(i).getHolidayName();
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

        buttonGroupFuelType = new javax.swing.ButtonGroup();
        buttonGroupCarMeasureType = new javax.swing.ButtonGroup();
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
        jPanelCars = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCars = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextCarsCarID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextCarsProject = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextCarsTestEng = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextCarsTestTech = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextCarsCarPrio = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextCarsReqHours = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextCarsActHours = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextCarsReqLaps = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextCarsActLaps = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextCarsLapsDay = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextCarsStartDate = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextCarsNeedDate = new javax.swing.JTextField();
        jButtonCarsHome = new javax.swing.JButton();
        jComboBoxCarsCarType = new javax.swing.JComboBox<>();
        jRadioButtonCarsGas = new javax.swing.JRadioButton();
        jRadioButtonCarsElectric = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        jTextCarsMaxHours = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextCarsRequestor = new javax.swing.JTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 32767));
        jButtonCarsClear = new javax.swing.JButton();
        jButtonCarsInsert = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 25), new java.awt.Dimension(0, 25), new java.awt.Dimension(32767, 25));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(25, 0), new java.awt.Dimension(25, 0), new java.awt.Dimension(25, 32767));
        jPanelDrivers = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableDrivers = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jPanelHolidays = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableHolidays = new javax.swing.JTable();
        jButtonHolidaysHome = new javax.swing.JButton();
        jPanelForecast = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jButtonForecastHome = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabelReqAmount = new javax.swing.JLabel();
        jLabelDailyDone = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jRadioButtonForecastHours = new javax.swing.JRadioButton();
        jRadioButtonForecastLaps = new javax.swing.JRadioButton();
        jLabel20 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanelRecords = new javax.swing.JPanel();
        jPanelSearch = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Parent.setBackground(new java.awt.Color(255, 255, 255));
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

        jButtonSearchPage.setText("Search");

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/person.png"))); // NOI18N

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calander.png"))); // NOI18N

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/forecast.png"))); // NOI18N

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/document.png"))); // NOI18N

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search.png"))); // NOI18N

        javax.swing.GroupLayout jPanelHomeLayout = new javax.swing.GroupLayout(jPanelHome);
        jPanelHome.setLayout(jPanelHomeLayout);
        jPanelHomeLayout.setHorizontalGroup(
            jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHomeLayout.createSequentialGroup()
                .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap(209, Short.MAX_VALUE))
        );
        jPanelHomeLayout.setVerticalGroup(
            jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHomeLayout.createSequentialGroup()
                .addGap(156, 156, 156)
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
                .addContainerGap(220, Short.MAX_VALUE))
        );

        Parent.add(jPanelHome, "card2");

        jTableCars.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Car ID", "Project", "Requestor", "Test Eng", "Test Tech", "Priority", "Type", "Fuel Type", "Max Hours", "Test Type", "Req. Hours", "Act. Hours", "Req. Laps", "Act. Laps", "Laps/Day", "Start Date", "Need Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
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

        jLabel4.setText("Project");

        jLabel5.setText("Test Eng");

        jLabel6.setText("Test Tech");

        jLabel7.setText("Priority");

        jLabel8.setText("Car Type");

        jLabel9.setText("Fuel Type");

        jLabel10.setText("Req. Hours");

        jLabel11.setText("Act. Hours");

        jLabel12.setText("Req. Laps");

        jLabel13.setText("Act. Laps");

        jLabel14.setText("Laps/Day");

        jLabel15.setText("Start Date");

        jLabel16.setText("Need Date");

        jButtonCarsHome.setText("Home");
        jButtonCarsHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCarsHomeActionPerformed(evt);
            }
        });

        jComboBoxCarsCarType.setEditable(true);
        jComboBoxCarsCarType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        buttonGroupFuelType.add(jRadioButtonCarsGas);
        jRadioButtonCarsGas.setText("Gas");
        jRadioButtonCarsGas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonCarsGasMouseClicked(evt);
            }
        });
        jRadioButtonCarsGas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCarsGasActionPerformed(evt);
            }
        });

        buttonGroupFuelType.add(jRadioButtonCarsElectric);
        jRadioButtonCarsElectric.setText("Electric");
        jRadioButtonCarsElectric.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonCarsElectricMouseClicked(evt);
            }
        });
        jRadioButtonCarsElectric.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCarsElectricActionPerformed(evt);
            }
        });

        jLabel17.setText("Max Hours");

        jTextCarsMaxHours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextCarsMaxHoursActionPerformed(evt);
            }
        });

        jLabel18.setText("Requestor");

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
                .addGap(25, 25, 25)
                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                        .addComponent(jButtonCarsInsert)
                        .addGap(59, 59, 59)
                        .addComponent(jButton9))
                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextCarsProject, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextCarsCarID, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextCarsTestEng, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextCarsTestTech, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextCarsCarPrio, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextCarsRequestor, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxCarsCarType, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(60, 60, 60)
                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButtonCarsGas)
                                .addGap(28, 28, 28)
                                .addComponent(jRadioButtonCarsElectric))
                            .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanelCarsLayout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextCarsReqHours, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelCarsLayout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextCarsReqLaps, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelCarsLayout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextCarsActLaps, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelCarsLayout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextCarsActHours, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelCarsLayout.createSequentialGroup()
                                    .addComponent(jLabel17)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextCarsMaxHours, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton10)
                                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextCarsLapsDay, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCarsLayout.createSequentialGroup()
                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextCarsStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextCarsNeedDate, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(47, 47, 47))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCarsLayout.createSequentialGroup()
                        .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))))
            .addGroup(jPanelCarsLayout.createSequentialGroup()
                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCarsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(filler1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonCarsClear)
                                .addGap(270, 270, 270)
                                .addComponent(jButtonCarsHome)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                        .addGap(418, 418, 418)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCarsLayout.createSequentialGroup()
                .addGap(0, 19, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 907, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanelCarsLayout.setVerticalGroup(
            jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCarsLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextCarsCarID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextCarsProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jTextCarsRequestor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextCarsTestEng, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextCarsTestTech, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextCarsCarPrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jComboBoxCarsCarType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(119, Short.MAX_VALUE))
                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jTextCarsNeedDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(77, 77, 77)
                        .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCarsLayout.createSequentialGroup()
                        .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(jTextCarsStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCarsLayout.createSequentialGroup()
                                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jRadioButtonCarsGas)
                                    .addComponent(jRadioButtonCarsElectric))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(jTextCarsMaxHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(jTextCarsReqHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(jTextCarsActHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(jTextCarsReqLaps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(jTextCarsActLaps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(jTextCarsLapsDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jPanelDriversLayout = new javax.swing.GroupLayout(jPanelDrivers);
        jPanelDrivers.setLayout(jPanelDriversLayout);
        jPanelDriversLayout.setHorizontalGroup(
            jPanelDriversLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDriversLayout.createSequentialGroup()
                .addContainerGap(841, Short.MAX_VALUE)
                .addComponent(jButton8)
                .addGap(37, 37, 37))
            .addGroup(jPanelDriversLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDriversLayout.createSequentialGroup()
                    .addContainerGap(102, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(110, Short.MAX_VALUE)))
        );
        jPanelDriversLayout.setVerticalGroup(
            jPanelDriversLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDriversLayout.createSequentialGroup()
                .addContainerGap(605, Short.MAX_VALUE)
                .addComponent(jButton8)
                .addGap(27, 27, 27))
            .addGroup(jPanelDriversLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDriversLayout.createSequentialGroup()
                    .addContainerGap(116, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(341, Short.MAX_VALUE)))
        );

        Parent.add(jPanelDrivers, "card4");

        jTableHolidays.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Holiday"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
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

        javax.swing.GroupLayout jPanelHolidaysLayout = new javax.swing.GroupLayout(jPanelHolidays);
        jPanelHolidays.setLayout(jPanelHolidaysLayout);
        jPanelHolidaysLayout.setHorizontalGroup(
            jPanelHolidaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHolidaysLayout.createSequentialGroup()
                .addContainerGap(546, Short.MAX_VALUE)
                .addGroup(jPanelHolidaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHolidaysLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHolidaysLayout.createSequentialGroup()
                        .addComponent(jButtonHolidaysHome)
                        .addGap(31, 31, 31))))
        );
        jPanelHolidaysLayout.setVerticalGroup(
            jPanelHolidaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHolidaysLayout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButtonHolidaysHome)
                .addGap(17, 17, 17))
        );

        Parent.add(jPanelHolidays, "card5");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel19.setText("Car");

        jButtonForecastHome.setText("Home");
        jButtonForecastHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonForecastHomeActionPerformed(evt);
            }
        });

        jLabelReqAmount.setText("Req. Laps");

        jLabelDailyDone.setText("Laps/Day");

        buttonGroupCarMeasureType.add(jRadioButtonForecastHours);
        jRadioButtonForecastHours.setText("Hours");
        jRadioButtonForecastHours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonForecastHoursActionPerformed(evt);
            }
        });

        buttonGroupCarMeasureType.add(jRadioButtonForecastLaps);
        jRadioButtonForecastLaps.setText("Laps");
        jRadioButtonForecastLaps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonForecastLapsActionPerformed(evt);
            }
        });

        jLabel20.setText("Forecasted Date");

        jTextField2.setEditable(false);

        javax.swing.GroupLayout jPanelForecastLayout = new javax.swing.GroupLayout(jPanelForecast);
        jPanelForecast.setLayout(jPanelForecastLayout);
        jPanelForecastLayout.setHorizontalGroup(
            jPanelForecastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelForecastLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonForecastHome)
                .addGap(21, 21, 21))
            .addGroup(jPanelForecastLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanelForecastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20)
                    .addComponent(jLabelDailyDone)
                    .addComponent(jLabel19)
                    .addComponent(jLabelReqAmount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelForecastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1)
                    .addComponent(jComboBox1, 0, 148, Short.MAX_VALUE)
                    .addComponent(jTextField3)
                    .addComponent(jTextField2))
                .addGap(89, 89, 89)
                .addComponent(jRadioButtonForecastHours)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonForecastLaps)
                .addContainerGap(454, Short.MAX_VALUE))
        );
        jPanelForecastLayout.setVerticalGroup(
            jPanelForecastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelForecastLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(jPanelForecastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jRadioButtonForecastHours)
                    .addComponent(jRadioButtonForecastLaps))
                .addGap(54, 54, 54)
                .addGroup(jPanelForecastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelReqAmount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelForecastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDailyDone)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanelForecastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 342, Short.MAX_VALUE)
                .addComponent(jButtonForecastHome)
                .addGap(26, 26, 26))
        );

        Parent.add(jPanelForecast, "card6");

        javax.swing.GroupLayout jPanelRecordsLayout = new javax.swing.GroupLayout(jPanelRecords);
        jPanelRecords.setLayout(jPanelRecordsLayout);
        jPanelRecordsLayout.setHorizontalGroup(
            jPanelRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 950, Short.MAX_VALUE)
        );
        jPanelRecordsLayout.setVerticalGroup(
            jPanelRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 654, Short.MAX_VALUE)
        );

        Parent.add(jPanelRecords, "card7");

        javax.swing.GroupLayout jPanelSearchLayout = new javax.swing.GroupLayout(jPanelSearch);
        jPanelSearch.setLayout(jPanelSearchLayout);
        jPanelSearchLayout.setHorizontalGroup(
            jPanelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 950, Short.MAX_VALUE)
        );
        jPanelSearchLayout.setVerticalGroup(
            jPanelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 654, Short.MAX_VALUE)
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
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query1 = "INSERT INTO cars" +
            "(CarID, CarProject, CarRequestor, CarTestEng, CarTestTech, CarPrio, CarType, CarFuel, CarMaxHours, CarReqHours, CarActHours," +
            "CarReqLaps, CarActLaps, CarLapsDay, CarStart, CarNeed)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement pst = connection.prepareStatement(query1);
            pst.setString(1, jTextCarsCarID.getText());
            pst.setString(2, jTextCarsProject.getText());
            pst.setString(3, jTextCarsRequestor.getText());
            pst.setString(4, jTextCarsTestEng.getText());
            pst.setString(5, jTextCarsTestTech.getText());
            if(jTextCarsCarPrio.getText().isBlank() || jTextCarsCarPrio.getText().equals(0)) {
                pst.setNull(6, Types.NULL);
            }
            else {
                pst.setString(6, jTextCarsCarPrio.getText());
            }
            pst.setString(7, jComboBoxCarsCarType.getSelectedItem().toString());
            if (jRadioButtonCarsGas.isSelected()) {
                pst.setString(8, jRadioButtonCarsGas.getText());
                pst.setNull(9, Types.NULL);
            }
            else {
                pst.setString(8, jRadioButtonCarsElectric.getText());
                pst.setString(9, jTextCarsMaxHours.getText());
            }
            if(jTextCarsReqHours.getText().isBlank()) {
                pst.setNull(10, Types.NULL);
            }
            else {
                pst.setString(10, jTextCarsReqHours.getText());
            }
            if(jTextCarsActHours.getText().isBlank()) {
                pst.setNull(11, Types.NULL);
            }
            else {
                pst.setString(11, jTextCarsActHours.getText());
            }
            if(jTextCarsReqLaps.getText().isBlank()) {
                pst.setNull(12, Types.NULL);
            }
            else {
                pst.setString(12, jTextCarsReqLaps.getText());
            }
            if(jTextCarsActLaps.getText().isBlank()) {
                pst.setNull(13, Types.NULL);
            }
            else {
                pst.setString(13, jTextCarsActLaps.getText());
            }
            if(jTextCarsLapsDay.getText().isBlank()) {
                pst.setNull(14, Types.NULL);
            }
            else {
                pst.setString(14, jTextCarsLapsDay.getText());
            }
            pst.setString(15, jTextCarsStartDate.getText());
            pst.setString(16, jTextCarsNeedDate.getText());
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
        jTextCarsProject.setText("");
        jTextCarsRequestor.setText("");
        jTextCarsTestEng.setText("");
        jTextCarsTestTech.setText("");
        jTextCarsCarPrio.setText("");
        jComboBoxCarsCarType.getEditor().setItem("");
        buttonGroupFuelType.clearSelection();
        jTextCarsMaxHours.setText("");
        jTextCarsReqHours.setText("");
        jTextCarsActHours.setText("");
        jTextCarsReqLaps.setText("");
        jTextCarsActLaps.setText("");
        jTextCarsLapsDay.setText("");
        jTextCarsStartDate.setText("");
        jTextCarsNeedDate.setText("");
    }//GEN-LAST:event_jButtonCarsClearActionPerformed

    private void jTextCarsMaxHoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextCarsMaxHoursActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextCarsMaxHoursActionPerformed

    private void jRadioButtonCarsElectricActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCarsElectricActionPerformed
        // TODO add your handling code here:
        jTextCarsMaxHours.setEditable(true);
    }//GEN-LAST:event_jRadioButtonCarsElectricActionPerformed

    private void jRadioButtonCarsElectricMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonCarsElectricMouseClicked
        // TODO add your handling code here:
        //        jTextCarsMaxHours.setEditable(true);
    }//GEN-LAST:event_jRadioButtonCarsElectricMouseClicked

    private void jRadioButtonCarsGasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCarsGasActionPerformed
        // TODO add your handling code here:
        jTextCarsMaxHours.setText("");
        jTextCarsMaxHours.setEditable(false);
    }//GEN-LAST:event_jRadioButtonCarsGasActionPerformed

    private void jRadioButtonCarsGasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonCarsGasMouseClicked
        // TODO add your handling code here:
        //            jTextCarsMaxHours.setText("");
        //            jTextCarsMaxHours.setEditable(false);
    }//GEN-LAST:event_jRadioButtonCarsGasMouseClicked

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
        jTextCarsProject.setText(model.getValueAt(i, 1).toString());
        jTextCarsRequestor.setText(model.getValueAt(i, 2).toString());
        jTextCarsTestEng.setText(model.getValueAt(i, 3).toString());
        jTextCarsTestTech.setText(model.getValueAt(i, 4).toString());
        jTextCarsCarPrio.setText(model.getValueAt(i, 5).toString());
        jComboBoxCarsCarType.getEditor().setItem(model.getValueAt(i, 6).toString());
        String fuelType = model.getValueAt(i, 7).toString();
        if(fuelType.equals("Gas")) {
            jRadioButtonCarsGas.setSelected(true);
            jTextCarsMaxHours.setText("");
            jTextCarsMaxHours.setEditable(false);
        }
        else {
            jRadioButtonCarsElectric.setSelected(true);
            jTextCarsMaxHours.setEditable(true);
            jTextCarsMaxHours.setText(model.getValueAt(i, 8).toString());
        }
        jTextCarsReqHours.setText(model.getValueAt(i, 10).toString());
        jTextCarsActHours.setText(model.getValueAt(i, 11).toString());
        jTextCarsReqLaps.setText(model.getValueAt(i, 12).toString());
        jTextCarsActLaps.setText(model.getValueAt(i, 13).toString());
        jTextCarsLapsDay.setText(model.getValueAt(i, 14).toString());
        jTextCarsStartDate.setText(model.getValueAt(i, 15).toString());
        jTextCarsNeedDate.setText(model.getValueAt(i, 16).toString());
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

    private void jRadioButtonForecastHoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonForecastHoursActionPerformed
        // TODO add your handling code here:
        jLabelReqAmount.setText("Req. Hours");
        jLabelDailyDone.setText("Hours/Day");
    }//GEN-LAST:event_jRadioButtonForecastHoursActionPerformed

    private void jRadioButtonForecastLapsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonForecastLapsActionPerformed
        // TODO add your handling code here:
        jLabelReqAmount.setText("Req. Laps");
        jLabelDailyDone.setText("Laps/Day");
    }//GEN-LAST:event_jRadioButtonForecastLapsActionPerformed

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
    private javax.swing.ButtonGroup buttonGroupCarMeasureType;
    private javax.swing.ButtonGroup buttonGroupFuelType;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JButton jButton10;
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
    private javax.swing.JButton jButtonRecordsPage;
    private javax.swing.JButton jButtonSearchPage;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBoxCarsCarType;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JLabel jLabelDailyDone;
    private javax.swing.JLabel jLabelReqAmount;
    private javax.swing.JPanel jPanelCars;
    private javax.swing.JPanel jPanelDrivers;
    private javax.swing.JPanel jPanelForecast;
    private javax.swing.JPanel jPanelHolidays;
    private javax.swing.JPanel jPanelHome;
    private javax.swing.JPanel jPanelRecords;
    private javax.swing.JPanel jPanelSearch;
    private javax.swing.JRadioButton jRadioButtonCarsElectric;
    private javax.swing.JRadioButton jRadioButtonCarsGas;
    private javax.swing.JRadioButton jRadioButtonForecastHours;
    private javax.swing.JRadioButton jRadioButtonForecastLaps;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableCars;
    private javax.swing.JTable jTableDrivers;
    private javax.swing.JTable jTableHolidays;
    private javax.swing.JTextField jTextCarsActHours;
    private javax.swing.JTextField jTextCarsActLaps;
    private javax.swing.JTextField jTextCarsCarID;
    private javax.swing.JTextField jTextCarsCarPrio;
    private javax.swing.JTextField jTextCarsLapsDay;
    private javax.swing.JTextField jTextCarsMaxHours;
    private javax.swing.JTextField jTextCarsNeedDate;
    private javax.swing.JTextField jTextCarsProject;
    private javax.swing.JTextField jTextCarsReqHours;
    private javax.swing.JTextField jTextCarsReqLaps;
    private javax.swing.JTextField jTextCarsRequestor;
    private javax.swing.JTextField jTextCarsStartDate;
    private javax.swing.JTextField jTextCarsTestEng;
    private javax.swing.JTextField jTextCarsTestTech;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
