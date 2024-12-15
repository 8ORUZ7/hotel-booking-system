package osiristape.hotelbooking;

import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JRException;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.SpinnerValueFactory;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class DashboardController implements Initializable {

    @FXML
    private Button GuessCheckIn_CheckInbtn;

    @FXML
    private Button GuessCheckIn_Clearbtn;

    @FXML
    private Spinner<Integer> GuessCheckIn_HoursStaySpinner;

    @FXML
    private Spinner<Integer> GuessCheckIn_RoomTypeSpinner;

    @FXML
    private Label GuessCheckIn_HoursStayValue;

    @FXML
    private Button GuessCheckIn_Receiptbtn;

    @FXML
    private Label GuessCheckIn_RoomNumber;

    @FXML
    private Label GuessCheckIn_RoomType;

    @FXML
    private Label GuessCheckIn_RoomTypeValue;

    @FXML
    private Button GuessCheckIn_SelectRoombtn;

    @FXML
    private Label GuessCheckIn_TotalValue;

    @FXML
    private TableColumn<HotelInfos, Date> GuessCheckIn_addcol_Added;

    @FXML
    private TableColumn<HotelInfos, String> GuessCheckIn_addcol_Number;

    @FXML
    private TableColumn<HotelInfos, String> GuessCheckIn_addcol_Type;

    @FXML
    private TextField GuessCheckIn_added;

    @FXML
    private Button GuessCheckIn_btn;

    @FXML
    private AnchorPane GuessCheckIn_form;

    @FXML
    private ImageView GuessCheckIn_imgview;

    @FXML
    private TextField GuessCheckIn_number;

    @FXML
    private TableView<HotelInfos> GuessCheckIn_tableView;

    @FXML
    private TextField GuessCheckIn_type;

    @FXML
    private Button LogBook_Clearbtn;

    @FXML
    private Label LogBook_DateCheckIn;

    @FXML
    private Button LogBook_Deletebtn;

    @FXML
    private Label LogBook_GuessName;

    @FXML
    private Label LogBook_RoomNumber;

    @FXML
    private Label LogBook_RoomType;

    @FXML
    private TableView<customerData> LogBook_TableView;

    @FXML
    private Label LogBook_TimeCheckIn;

    @FXML
    private TableColumn<customerData, String> LogBook_addcol_DateCheckIn;

    @FXML
    private TableColumn<customerData, String> LogBook_addcol_GuessCheckIn;

    @FXML
    private TableColumn<customerData, String> LogBook_addcol_RoomNumber;

    @FXML
    private TableColumn<customerData, String> LogBook_addcol_RoomType;

    @FXML
    private TableColumn<customerData, String> LogBook_addcol_TimeCheckIn;

    @FXML
    private Button LogBook_btn;

    @FXML
    private AnchorPane LogBook_form;

    @FXML
    private TextField LogBook_search;

    @FXML
    private ComboBox<?> RoomAvail_Current;

    @FXML
    private Button RoomAvail_Deletebtn;

    @FXML
    private ImageView RoomAvail_ImgView;

    @FXML
    private Label RoomAvail_RoomNumber;

    @FXML
    private Label RoomAvail_RoomType;

    @FXML
    private TableView<HotelInfos> RoomAvail_TableView;

    @FXML
    private Button RoomAvail_Updatebtn;

    @FXML
    private TableColumn<HotelInfos, String> RoomAvail_addcol_CurrentStatus;

    @FXML
    private TableColumn<HotelInfos, String> RoomAvail_addcol_RoomNumber;

    @FXML
    private TableColumn<HotelInfos, String> RoomAvail_addcol_RoomType;

    @FXML
    private TableColumn<HotelInfos, String> RoomAvail_addcol_Stay;

    @FXML
    private Button RoomAvail_btn;

    @FXML
    private AnchorPane RoomAvail_form;

    @FXML
    private TextField RoomAvail_search;

    @FXML
    private TableColumn<HotelInfos, Date> RoomListing_addcol_roomAdded;

    @FXML
    private TableColumn<HotelInfos, String> RoomListing_addcol_roomNumber;

    @FXML
    private TableColumn<HotelInfos, String> RoomListing_addcol_roomStay;

    @FXML
    private TableColumn<HotelInfos, String> RoomListing_addcol_roomType;

    @FXML
    private DatePicker RoomListing_added;

    @FXML
    private Button RoomListing_btn;

    @FXML
    private Button RoomListing_clearbtn;

    @FXML
    private Button RoomListing_deletebtn;

    @FXML
    private TextField RoomListing_duration;

    @FXML
    private AnchorPane RoomListing_form;

    @FXML
    private ImageView RoomListing_imgview;

    @FXML
    private Button RoomListing_importimg;

    @FXML
    private Button RoomListing_insertbtn;

    @FXML
    private TextField RoomListing_number;

    @FXML
    private TextField RoomListing_search;

    @FXML
    private TableView<HotelInfos> RoomListing_tableview;

    @FXML
    private TextField RoomListing_type;

    @FXML
    private Button RoomListing_updatebtn;

    @FXML
    private Label dashboard_AvailRooms;

    @FXML
    private Label dashboard_HotelGuess;

    @FXML
    private Label dashboard_TotalIncome;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Button signout;

    @FXML
    private Label email;

    private Image image;
    private double x = 0;
    private double y = 0;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private SpinnerValueFactory<Integer> spinner1;
    private SpinnerValueFactory<Integer> spinner2;
    private float price1 = 0;
    private float price2 = 0;
    private float total = 0;
    private int qty1 = 0;
    private int qty2 = 0;
    private Object num;
    private int soldCheckIn;
    private float totalIncome;
    private int totalAvailRooms;

    public void totalAvailRoom() {
        String sql = "SELECT COUNT(id) FROM roomlisting WHERE current = 'Vacant'";
        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            if (result.next()) {
                totalAvailRooms = result.getInt(1);  
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displaytotalAvailRooms() {
        dashboard_AvailRooms.setText(String.valueOf(totalAvailRooms)); 
    }

    public void countCheckIn() {
        Date date = new Date(System.currentTimeMillis()); 
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());  

        String sql = "SELECT count(id) FROM customer WHERE date = ?";  

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setDate(1, sqlDate); 
            result = prepare.executeQuery();

            if (result.next()) {
                soldCheckIn = result.getInt(1); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayTotalCheckIn() {
        dashboard_HotelGuess.setText(String.valueOf(soldCheckIn)); 
    }

    public void totalIncomeToday() {
        Date date = new Date(System.currentTimeMillis()); 
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());  

        String sql = "SELECT SUM(total) FROM customer WHERE date = ?"; 

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setDate(1, sqlDate);  // Set today's date as parameter
            result = prepare.executeQuery();

            if (result.next()) {
                totalIncome = (float) result.getDouble(1);  
                if (result.wasNull()) {
                    totalIncome = (float) 0.0;  
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayTotalIncome() {
        dashboard_TotalIncome.setText(String.valueOf(totalIncome));  
    }

    public void searchCustomer() {
        
        FilteredList<customerData> filter = new FilteredList<>(custList, e -> true);
        
        LogBook_search.setOnKeyTyped(event -> {
            
            String newValue = LogBook_search.getText();
    
            filter.setPredicate(predicateCustomer -> {
                
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                
                if (String.valueOf(predicateCustomer.getId()).contains(searchKey)) {
                    return true;
                } else if (predicateCustomer.getRoomNumber().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCustomer.getRoomType().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCustomer.getDate().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCustomer.getTime().toString().toLowerCase().contains(searchKey)) {
                    return true;
                }
                return false;
            });

            SortedList<customerData> sort = new SortedList<>(filter);
            sort.comparatorProperty().bind(LogBook_TableView.comparatorProperty());
            
            LogBook_TableView.setItems(sort);
        });
    }

    public ObservableList<customerData> customerList() {
        ObservableList<customerData> customerL = FXCollections.observableArrayList();

        String sql = "SELECT * FROM customer c LEFT JOIN roomlisting r ON c.roomNumber = r.roomNumber WHERE r.current = 'Vacant'";

        connect = database.connectDb();

        try {
            customerData customerD;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                customerD = new customerData(
                        result.getInt("id"),
                        result.getString("roomType"),
                        result.getString("roomNumber"),
                        result.getInt("quantity"),
                        result.getDouble("total"),
                        result.getDate("date"),
                        result.getTime("time")
                );

                customerL.add(customerD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerL;
    }
    
    private ObservableList<customerData> custList;

    public void showCustomerList() {
        custList = customerList();
        if (custList.isEmpty()) {
            System.out.println("No data found in the customer table.");
            LogBook_TableView.setPlaceholder(new Label("No records found in the database."));
        } else {
            System.out.println("Loaded " + custList.size() + " records.");
        }
        LogBook_addcol_GuessCheckIn.setCellValueFactory(new PropertyValueFactory<>("id"));
        LogBook_addcol_RoomType.setCellValueFactory(new PropertyValueFactory<>("roomType")); 
        LogBook_addcol_RoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber")); 
        LogBook_addcol_TimeCheckIn.setCellValueFactory(new PropertyValueFactory<>("time"));
        LogBook_addcol_DateCheckIn.setCellValueFactory(new PropertyValueFactory<>("date"));
        LogBook_TableView.setItems(custList);
    }

   public void selectCustomerList() {
        customerData cusD = LogBook_TableView.getSelectionModel().getSelectedItem();
        int num = LogBook_TableView.getSelectionModel().getSelectedIndex();

        if (cusD == null || num < 0) { 
            return;
        }  
        LogBook_GuessName.setText(String.valueOf(cusD.getId()));
        LogBook_RoomNumber.setText(cusD.getRoomNumber()); 
        LogBook_RoomType.setText(cusD.getRoomType()); 
        LogBook_TimeCheckIn.setText(String.valueOf(cusD.getTime()));
        LogBook_DateCheckIn.setText(String.valueOf(cusD.getDate()));
    }

    public void deleteCustomer() {
        if (LogBook_GuessName.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the customer first.");
            alert.showAndWait();
            return;
        }
        String customerId = LogBook_GuessName.getText();
        String sqlDeleteCustomer = "DELETE FROM customer WHERE id = " + customerId;
        String sqlUpdateRoomStatus = "UPDATE roomlisting SET current = 'Vacant' WHERE roomNumber = (SELECT roomNumber FROM customer WHERE id = ?)";
        connect = database.connectDb();
        try {
            Alert alert;
            statement = connect.createStatement();
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete customer with ID: "
                    + customerId + "?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.isPresent() && option.get() == ButtonType.OK) {
                connect.setAutoCommit(false);

                try (PreparedStatement updateStmt = connect.prepareStatement(sqlUpdateRoomStatus)) {
                    updateStmt.setInt(1, Integer.parseInt(customerId));
                    updateStmt.executeUpdate();
                }

                try (PreparedStatement deleteStmt = connect.prepareStatement(sqlDeleteCustomer)) {
                    deleteStmt.executeUpdate();
                }
                connect.commit();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Customer successfully removed and room marked as Vacant!");
                alert.showAndWait();
                clearCustomer();
                showCustomerList();
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connect.rollback(); 
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connect.setAutoCommit(true); 
                if (statement != null) {
                    statement.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void clearCustomer() {
        LogBook_GuessName.setText("");
        LogBook_RoomNumber.setText("");
        LogBook_RoomType.setText("");
        LogBook_TimeCheckIn.setText("");
        LogBook_DateCheckIn.setText("");
    }

    public void receipt() {
        if (total > 0) {
            HashMap<String, Object> hash = new HashMap<>();
            hash.put("receipt", num);

            try {   
                File file = new File("C:/Users/shift/Documents/NetBeansProjects/hotelbooking/src/main/resources/osiristape/hotelbooking/receipts.jrxml"); 
                JasperDesign jDesign = JRXmlLoader.load(file);
                JasperReport jReport = JasperCompileManager.compileReport(jDesign);
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/hotelbooking", "root", "");
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("receipt", num);  
                JasperPrint jPrint = JasperFillManager.fillReport(jReport, parameters, connect);
                JasperViewer.viewReport(jPrint, true);
            } catch (JRException | SQLException ex) {
                ex.printStackTrace(); 
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid");
            alert.showAndWait();
        }
    }

    public void buy() {
        String fetchSql = "SELECT roomType FROM roomlisting WHERE roomNumber = ?";
        String insertSql = "INSERT INTO customer (roomType, roomNumber, quantity, total, date, time) VALUES (?, ?, ?, ?, ?, ?)";
        connect = database.connectDb();
        java.util.Date date = new java.util.Date(); 
        java.sql.Date setDate = new java.sql.Date(date.getTime()); 
        try {
            String roomNumber = GuessCheckIn_RoomNumber.getText();
            if (roomNumber.isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the room first.");
                alert.showAndWait();
                return;
            }
            prepare = connect.prepareStatement(fetchSql);
            prepare.setString(1, roomNumber);
            result = prepare.executeQuery();

            String roomType = null;
            if (result.next()) {
                roomType = result.getString("roomType");
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Room number not found in the database.");
                alert.showAndWait();
                return;
            }
            if (total == 0) { // If no value is calculated
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please indicate the quantity.");
                alert.showAndWait();
                return;
            }

            int quantity = GuessCheckIn_HoursStaySpinner.getValue(); 

            LocalTime localTime = LocalTime.now();
            Time time = Time.valueOf(localTime);

            prepare = connect.prepareStatement(insertSql);
            prepare.setString(1, roomType);  // roomType from roomlisting
            prepare.setString(2, roomNumber); // roomNumber from input
            prepare.setInt(3, quantity);  // quantity from spinner
            prepare.setDouble(4, total);  // total from calculation
            prepare.setDate(5, setDate);  // date (current date)
            prepare.setTime(6, time);  // time (current time)
            prepare.executeUpdate();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully purchased!");
            alert.showAndWait();

            clearPurchaseRoomInfo();// reset
            totalAvailRoom();
            countCheckIn();
            totalIncomeToday();
            displayTotalCheckIn();
            displayTotalIncome();
            displaytotalAvailRooms();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearPurchaseRoomInfo() {
        spinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        spinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1000, 100000, 0);
        GuessCheckIn_HoursStaySpinner.setValueFactory(spinner1);
        GuessCheckIn_RoomTypeSpinner.setValueFactory(spinner2);
        GuessCheckIn_HoursStayValue.setText("₱0.00");
        GuessCheckIn_RoomTypeValue.setText("₱0.00");
        GuessCheckIn_TotalValue.setText("₱0.00");
    }
    public void showSpinnerValue() {
        spinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        spinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1000, 100000, 0);
        GuessCheckIn_HoursStaySpinner.setValueFactory(spinner1);
        GuessCheckIn_RoomTypeSpinner.setValueFactory(spinner2);
    }

    public void getSpinnerValue(MouseEvent event) {
        qty1 = GuessCheckIn_HoursStaySpinner.getValue();
        qty2 = GuessCheckIn_RoomTypeSpinner.getValue();
        price1 = (qty1 * price2);
        price2 = qty2;
        total = price1;
        GuessCheckIn_HoursStayValue.setText("₱" + String.valueOf(price1));
        GuessCheckIn_RoomTypeValue.setText("₱" + String.valueOf(price2));
        GuessCheckIn_TotalValue.setText("₱" + String.valueOf(total));
    }

    public ObservableList<HotelInfos> availableRoomList() {
        ObservableList<HotelInfos> listAvRoom = FXCollections.observableArrayList();
        String sql = "SELECT * FROM roomlisting WHERE current = 'Vacant'";
        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                HotelInfos HotI = new HotelInfos(
                        result.getInt("id"),
                        result.getString("roomNumber"),
                        result.getString("roomType"),
                        result.getString("duration"),
                        result.getString("image"),
                        result.getDate("date"),
                        result.getString("current")
                );
                listAvRoom.add(HotI);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (prepare != null) {
                    prepare.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listAvRoom;
    }

    private ObservableList<HotelInfos> availableRoomL;
    
    public void showAvailableRooms() {
        availableRoomL = availableRoomList();
        GuessCheckIn_addcol_Number.setCellValueFactory(new PropertyValueFactory<>("number"));
        GuessCheckIn_addcol_Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        GuessCheckIn_addcol_Added.setCellValueFactory(new PropertyValueFactory<>("date"));
        GuessCheckIn_tableView.setItems(availableRoomL);
    }

    public void selectAvailableRooms() {
        HotelInfos HotI = GuessCheckIn_tableView.getSelectionModel().getSelectedItem();
        int num = GuessCheckIn_tableView.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        GuessCheckIn_number.setText(HotI.getNumber());
        GuessCheckIn_type.setText(HotI.getType());
        GuessCheckIn_added.setText(String.valueOf(HotI.getDate()));

        getData.path = HotI.getImage();
        getData.Type = HotI.getType();
        getData.Number = HotI.getNumber();
        selectRoom();
    }

    public void selectRoom() {
        Alert alert;
        if (GuessCheckIn_number.getText().isEmpty()
                || GuessCheckIn_type.getText().isEmpty()
                || GuessCheckIn_added.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the room first.");
            alert.showAndWait();
        } else {
            String uri = "file:" + getData.path;
            image = new Image(uri, 200, 204, false, true);
            GuessCheckIn_imgview.setImage(image);
            GuessCheckIn_RoomNumber.setText(getData.Number);
            GuessCheckIn_RoomType.setText(getData.Type);
        }
        // GuessCheckIn_number.setText("");
        // GuessCheckIn_type.setText("");
        // GuessCheckIn_added.setText("");
    }

    private String[] currentList = {"Vacant", "Occupied"};

    public void listStatusBox() {
        List<String> listCurrent = new ArrayList<>();
        for (String data : currentList) {
            listCurrent.add(data);
        }
        ObservableList listC = FXCollections.observableArrayList(listCurrent);
        RoomAvail_Current.setItems(listC);
    }

    public void selectEditRoomAvail() {
        HotelInfos HotI = RoomAvail_TableView.getSelectionModel().getSelectedItem();
        int num = RoomAvail_TableView.getSelectionModel().getFocusedIndex();
        if ((num - 1) < -1) {
            return;
        }
        String uri = "file:" + HotI.getImage();
        image = new Image(uri, 200, 204, false, true);
        RoomAvail_ImgView.setImage(image);
        RoomAvail_RoomNumber.setText(HotI.getNumber());
        RoomAvail_RoomType.setText(HotI.getType());

    }

    public void updateRoomAvail() {
        String sql = "UPDATE roomlisting SET current = ? WHERE roomNumber = ?";
        connect = database.connectDb();
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, RoomAvail_Current.getSelectionModel().getSelectedItem().toString());
            preparedStatement.setString(2, RoomAvail_RoomNumber.getText());

            Alert alert;
            if (RoomAvail_RoomNumber.getText().isEmpty()
                    || RoomAvail_ImgView.getImage() == null
                    || RoomAvail_Current.getSelectionModel().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the room first");
                alert.showAndWait();
            } else {
                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();
                    
                    showEditAvailList();
                    clearEditRoomAvail();
                } else {
                    alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Warning Message");
                    alert.setHeaderText(null);
                    alert.setContentText("No matching room found to update.");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearEditRoomAvail() {
        RoomAvail_RoomNumber.setText("");
        RoomAvail_ImgView.setImage(null);
        //RoomAvail_Current.setSelectionModel(null);
    }

    public void searchEditRoomAvail() {
        FilteredList<HotelInfos> filter = new FilteredList(roomAvailL, e -> true);
        RoomAvail_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(predicateHotelInfos -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateHotelInfos.getNumber().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateHotelInfos.getType().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateHotelInfos.getDuration().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateHotelInfos.getCurrent().toLowerCase().contains(searchKey)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<HotelInfos> sortData = new SortedList<>(filter);
        sortData.comparatorProperty().bind(RoomAvail_TableView.comparatorProperty());
        RoomAvail_TableView.setItems(sortData);
    }

    public ObservableList<HotelInfos> roomAvailList() {
        ObservableList<HotelInfos> roomAvail = FXCollections.observableArrayList();
        String sql = "SELECT * FROM roomlisting";
        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            HotelInfos HotI;
            while (result.next()) {
                HotI = new HotelInfos(result.getInt("id"),
                        result.getString("roomNumber"),
                        result.getString("roomType"),
                        result.getString("duration"),
                        result.getString("image"),
                        result.getDate("date"),
                        result.getString("current"));
                roomAvail.add(HotI);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomAvail;
    }

    private ObservableList<HotelInfos> roomAvailL;
    public void showEditAvailList() {
        roomAvailL = roomAvailList();
        RoomAvail_addcol_RoomNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        RoomAvail_addcol_RoomType.setCellValueFactory(new PropertyValueFactory<>("type"));
        RoomAvail_addcol_Stay.setCellValueFactory(new PropertyValueFactory<>("duration"));
        RoomAvail_addcol_CurrentStatus.setCellValueFactory(new PropertyValueFactory<>("current"));
        RoomAvail_TableView.setItems(roomAvailL);
    }

    public void searchAddHotelInfos() {
        FilteredList<HotelInfos> filter = new FilteredList<>(addHotelInfo(), e -> true);
        RoomListing_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(predicateHotelInfos -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;  
                }
                String keySearch = newValue.toLowerCase();
                return predicateHotelInfos.getNumber().toLowerCase().contains(keySearch)
                        || predicateHotelInfos.getType().toLowerCase().contains(keySearch)
                        || predicateHotelInfos.getDuration().toLowerCase().contains(keySearch)
                        || (predicateHotelInfos.getDate() != null && predicateHotelInfos.getDate().toString().contains(keySearch));
            });
            SortedList<HotelInfos> sortedData = new SortedList<>(filter);
            sortedData.comparatorProperty().bind(RoomListing_tableview.comparatorProperty());
            RoomListing_tableview.setItems(sortedData);
        });
    }

    public void importImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            getData.path = file.getAbsolutePath(); 
            System.out.println("Selected Image Path: " + getData.path); 
            try {
                String uri = file.toURI().toString();
                Image image = new Image(uri);
                RoomListing_imgview.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to load the image.");
                alert.showAndWait();
            }
        } else {
            System.out.println("No file selected.");
        }
    }

    public void onRoomSelection() {
        HotelInfos selectedHotel = RoomListing_tableview.getSelectionModel().getSelectedItem();
        if (selectedHotel != null) {
            Date selectedDate = selectedHotel.getDate();
            if (selectedDate != null) {
                LocalDate localDate = selectedDate.toLocalDate();  
                RoomListing_added.setValue(localDate);
            } else {
                RoomListing_added.setValue(null);
            }
            
            String imagePath = selectedHotel.getImage();
            if (imagePath != null && !imagePath.isEmpty()) {
                File imageFile = new File(imagePath);
                if (imageFile.exists()) {
                    String uri = imageFile.toURI().toString();
                    RoomListing_imgview.setImage(new Image(uri));
                } else {
                    RoomListing_imgview.setImage(new Image("file:default-image.jpg"));
                }
            } else {
                RoomListing_imgview.setImage(new Image("file:default-image.jpg"));
            }
            RoomListing_number.setText(selectedHotel.getNumber());
            RoomListing_type.setText(selectedHotel.getType());
            RoomListing_duration.setText(selectedHotel.getDuration());
        }
    }

    public ObservableList<HotelInfos> addHotelInfo() {
        ObservableList<HotelInfos> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM roomlisting";
        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery(); 
            HotelInfos HotI;
            while (result.next()) {
                HotI = new HotelInfos(result.getInt("id"),
                        result.getString("roomNumber"),
                        result.getString("roomType"),
                        result.getString("duration"),
                        result.getString("image"),
                        result.getDate("date"),
                        result.getString("current"));
                listData.add(HotI);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private Integer countId;

    public void HotelId() {
        String sql = "SELECT COUNT(id) AS countId FROM roomlisting";
        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                countId = result.getInt("countId");  
            } else {
                countId = 0; 
            }
        } catch (Exception e) {
            e.printStackTrace();
            countId = 0; 
        }
    }

    public void selectAddHotelInfos(HotelInfos HotI) {
        HotI = RoomListing_tableview.getSelectionModel().getSelectedItem();
        int num = RoomListing_tableview.getSelectionModel().getSelectedIndex();

        if (num < 0) {
            return; 
        }
        getData.path = HotI != null ? HotI.getImage() : null;
        if (HotI != null) {
            RoomListing_number.setText(HotI.getNumber());
            RoomListing_type.setText(HotI.getType());
            RoomListing_duration.setText(HotI.getDuration());
            String imagePath = HotI.getImage();
            if (imagePath != null && !imagePath.isEmpty()) {
                String uri = "file:" + imagePath;
                Image image = new Image(uri, 200, 204, false, true); 
                RoomListing_imgview.setImage(image); 
            } else {
                RoomListing_imgview.setImage(new Image("file:default-image.jpg"));
            }
            Date date = HotI.getDate();
            if (date != null) {
                System.out.println("SQL Date: " + date);
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                System.out.println("Converted LocalDate: " + localDate);
                RoomListing_added.setValue(localDate); 
            } else {
                RoomListing_added.setValue(null); 
            }
        }
    }

    public void insertAddHotelInfos() {
        String sqlCheck = "SELECT * FROM roomlisting WHERE roomNumber = ?";
        String sqlInsert = "INSERT INTO roomlisting(roomNumber, roomType, duration, image, date) VALUES (?, ?, ?, ?, ?)";
        connect = database.connectDb();
        Alert alert;

        try {
            prepare = connect.prepareStatement(sqlCheck);
            prepare.setString(1, RoomListing_number.getText().trim());
            result = prepare.executeQuery();

            if (result.next()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Room number '" + RoomListing_number.getText() + "' already exists!");
                alert.showAndWait();
                return;
            }

            if (RoomListing_number.getText().trim().isEmpty()
                    || RoomListing_type.getText().trim().isEmpty()
                    || RoomListing_duration.getText().trim().isEmpty()
                    || RoomListing_imgview.getImage() == null
                    || RoomListing_added.getValue() == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all fields.");
                alert.showAndWait();
                return;
            }

            prepare = connect.prepareStatement(sqlInsert);

            String uri = getData.path != null ? getData.path.replace("\\", "\\\\") : "default-image.jpg";

            prepare.setString(1, RoomListing_number.getText().trim());
            prepare.setString(2, RoomListing_type.getText().trim());
            prepare.setString(3, RoomListing_duration.getText().trim());
            prepare.setString(4, uri);

            LocalDate selectedDate = RoomListing_added.getValue();
            prepare.setDate(5, selectedDate != null ? Date.valueOf(selectedDate) : null);

            int rowsInserted = prepare.executeUpdate();

            if (rowsInserted > 0) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Successfully added a new room.");
                alert.showAndWait();

                clearAddHotelInfos();
                showHotelInfos();
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Failed to add the new room.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void updateAddHotelInfos() {
        String sqlUpdate = "UPDATE roomlisting SET roomNumber = ?, roomType = ?, duration = ?, image = ?, date = ?, current = ? WHERE roomNumber = ?";
        connect = database.connectDb();

        try {
            HotelInfos selectedHotel = RoomListing_tableview.getSelectionModel().getSelectedItem();
            if (selectedHotel == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select a room to update.");
                alert.showAndWait();
                return;
            }
            String selectedRoomNumber = selectedHotel.getNumber(); 
            if (selectedRoomNumber == null || selectedRoomNumber.trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Selected room number is invalid or null.");
                alert.showAndWait();
                return;
            }
            
            prepare = connect.prepareStatement(sqlUpdate);

            String uri = getData.path != null ? getData.path.replace("\\", "\\\\") : "default-image.jpg";

            prepare.setString(1, RoomListing_number.getText().trim()); 
            prepare.setString(2, RoomListing_type.getText().trim());  
            prepare.setString(3, RoomListing_duration.getText().trim()); 
            prepare.setString(4, uri); 
            LocalDate selectedDate = RoomListing_added.getValue();
            prepare.setDate(5, selectedDate != null ? Date.valueOf(selectedDate) : null); 
            prepare.setString(6, "Occupied");
            prepare.setString(7, selectedRoomNumber); 
            
            int rowsUpdated = prepare.executeUpdate();

            if (rowsUpdated > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Room information updated successfully.");
                alert.showAndWait();

                clearAddHotelInfos();
                showHotelInfos();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Failed to update room information.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void deleteAddHotelInfos() {
        String roomNumber = RoomListing_number.getText().trim();
        if (roomNumber.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select a room first.");
            alert.showAndWait();
            return;
        }
        if (RoomListing_number.getText().isEmpty()
                || RoomListing_type.getText().isEmpty()
                || RoomListing_duration.getText().isEmpty()
                || RoomListing_imgview.getImage() == null 
                || RoomListing_added.getValue() == null) { 
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please ensure all room details are filled before deletion.");
            alert.showAndWait();
            return;
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Room");
            alert.setHeaderText("Are you sure you want to delete the room?");
            Optional<ButtonType> option = alert.showAndWait();
            if (ButtonType.OK.equals(option.get())) {
                try {
                    connect = database.connectDb();
                    String sql = "DELETE FROM roomlisting WHERE roomNumber = ?";
                    PreparedStatement prepare = connect.prepareStatement(sql);
                    prepare.setString(1, roomNumber);
                    int rowsAffected = prepare.executeUpdate();
                    if (rowsAffected > 0) {
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Information Message");
                        successAlert.setHeaderText(null);
                        successAlert.setContentText("Room successfully deleted!");
                        successAlert.showAndWait();
                        clearAddHotelInfos();
                        showHotelInfos();
                    } else {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Error Message");
                        errorAlert.setHeaderText(null);
                        errorAlert.setContentText("Failed to delete room, room not found.");
                        errorAlert.showAndWait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error Message");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("An error occurred while deleting the room: " + e.getMessage());
                    errorAlert.showAndWait();
                }
            }
        }
    }

    public void clearAddHotelInfos() {
        RoomListing_number.setText("");
        RoomListing_type.setText("");
        RoomListing_duration.setText("");
        RoomListing_imgview.setImage(null);
        RoomListing_added.setValue(null);
    }

    public void showHotelInfos() {
        ObservableList<HotelInfos> listHotelInfos = addHotelInfo();
        HotelInfos HotI = RoomListing_tableview.getSelectionModel().getSelectedItem();
        if (HotI != null) {
            String imagePath = HotI.getImage();
            if (imagePath != null && !imagePath.isEmpty()) {
                Image image = new Image("file:" + imagePath);
                RoomListing_imgview.setImage(image);
            } else {
                RoomListing_imgview.setImage(new Image("file:default-image.jpg"));
            }
        }
        RoomListing_addcol_roomNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        RoomListing_addcol_roomType.setCellValueFactory(new PropertyValueFactory<>("type"));
        RoomListing_addcol_roomStay.setCellValueFactory(new PropertyValueFactory<>("duration"));
        RoomListing_addcol_roomAdded.setCellValueFactory(new PropertyValueFactory<>("date"));
        RoomListing_addcol_roomAdded.setCellFactory(column -> new TableCell<HotelInfos, Date>() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            protected void updateItem(Date item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toLocalDate().format(formatter));
                }
            }
        });
        RoomListing_tableview.setItems(listHotelInfos);
    }

    public void logout() {
        signout.getScene().getWindow().hide();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Signin.fxml")); // Adjust path if needed
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            root.setOnMousePressed((MouseEvent event) -> {
                x = event.getSceneX();
                y = event.getSceneY();

            });
            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            });
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayEmail() {
        email.setText(getData.email);
    }

    public void close(ActionEvent event) {
        System.exit(0);
    }

    public void minimize(ActionEvent event) {
        Stage stage = (Stage) dashboard_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            RoomListing_form.setVisible(false);
            GuessCheckIn_form.setVisible(false);
            RoomAvail_form.setVisible(false);
            LogBook_form.setVisible(false);

            //#1c304e
            dashboard_btn.setStyle("-fx-background-color:#1c3046;");
            RoomListing_btn.setStyle("-fx-background-color:transparent;");
            GuessCheckIn_btn.setStyle("-fx-background-color:transparent;");
            RoomAvail_btn.setStyle("-fx-background-color:transparent;");
            LogBook_btn.setStyle("-fx-background-color:transparent;");

            displaytotalAvailRooms();
            displayTotalCheckIn();
            displayTotalIncome();

        } else if (event.getSource() == RoomListing_btn) {
            dashboard_form.setVisible(false);
            RoomListing_form.setVisible(true);
            GuessCheckIn_form.setVisible(false);
            RoomAvail_form.setVisible(false);
            LogBook_form.setVisible(false);

            dashboard_btn.setStyle("-fx-background-color:transparent;");
            RoomListing_btn.setStyle("-fx-background-color:#1c3046;");
            GuessCheckIn_btn.setStyle("-fx-background-color:transparent;");
            RoomAvail_btn.setStyle("-fx-background-color:transparent;");
            LogBook_btn.setStyle("-fx-background-color:transparent;");

            showEditAvailList();
        } else if (event.getSource() == GuessCheckIn_btn) {
            dashboard_form.setVisible(false);
            RoomListing_form.setVisible(false);
            GuessCheckIn_form.setVisible(true);
            RoomAvail_form.setVisible(false);
            LogBook_form.setVisible(false);

            dashboard_btn.setStyle("-fx-background-color:transparent;");
            RoomListing_btn.setStyle("-fx-background-color:transparent;");
            GuessCheckIn_btn.setStyle("-fx-background-color:#1c3046;");
            RoomAvail_btn.setStyle("-fx-background-color:transparent;");
            LogBook_btn.setStyle("-fx-background-color:transparent;");

            showAvailableRooms();
        } else if (event.getSource() == RoomAvail_btn) {
            dashboard_form.setVisible(false);
            RoomListing_form.setVisible(false);
            GuessCheckIn_form.setVisible(false);
            RoomAvail_form.setVisible(true);
            LogBook_form.setVisible(false);

            dashboard_btn.setStyle("-fx-background-color:transparent;");
            RoomListing_btn.setStyle("-fx-background-color:transparent;");
            GuessCheckIn_btn.setStyle("-fx-background-color:transparent;");
            RoomAvail_btn.setStyle("-fx-background-color:#1c3046;");
            LogBook_btn.setStyle("-fx-background-color:transparent;");

            selectAvailableRooms();
        } else if (event.getSource() == LogBook_btn) {
            dashboard_form.setVisible(false);
            RoomListing_form.setVisible(false);
            GuessCheckIn_form.setVisible(false);
            RoomAvail_form.setVisible(false);
            LogBook_form.setVisible(true);

            dashboard_btn.setStyle("-fx-background-color:transparent;");
            RoomListing_btn.setStyle("-fx-background-color:transparent;");
            GuessCheckIn_btn.setStyle("-fx-background-color:transparent;");
            RoomAvail_btn.setStyle("-fx-background-color:transparent;");
            LogBook_btn.setStyle("-fx-background-color:#1c3046;");

            showCustomerList();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RoomListing_tableview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectAddHotelInfos(newValue);
            }
        });
        displayEmail();
        showHotelInfos();
        showEditAvailList();
        listStatusBox();

        showAvailableRooms();
        selectAvailableRooms();
        showSpinnerValue();
        showCustomerList();

        totalAvailRoom();
        countCheckIn();
        totalIncomeToday();
        displayTotalCheckIn();
        displayTotalIncome();
        displaytotalAvailRooms();
    }
    private ResultSet prepareQuery() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
