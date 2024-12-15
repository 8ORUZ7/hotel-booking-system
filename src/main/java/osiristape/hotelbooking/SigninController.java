package osiristape.hotelbooking; 

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

// Import Database
import osiristape.hotelbooking.database;

public class SigninController implements Initializable {

    @FXML
    private Button signin_minimize;

    @FXML
    private Button signin_close;

    @FXML
    private Hyperlink signin_createAccount;

    @FXML
    private TextField signin_email;

    @FXML
    private AnchorPane signin_form;

    @FXML
    private Button signin_loginBtn;

    @FXML
    private PasswordField signin_password;

    @FXML
    private Hyperlink signup_alreadyhaveaccount;

    @FXML
    private Button signup_btn;

    @FXML
    private Button signup_close;

    @FXML
    private TextField signup_email;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private Button signup_minimize;

    @FXML
    private PasswordField signup_password;

    @FXML
    private TextField signup_username;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public boolean validEmail(){
        Pattern pattern = Pattern.compile("[a-zA-z0-9][a-zA-z0-9._]*@[a-zA-z0-9]+([.][a-zA-Z]+)+");
        
        Matcher match = pattern.matcher(signup_email.getText());
        
        Alert alert;
        
        if(match.find() && match.group().matches(signup_email.getText())){
            return true;
        } else {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid email");
            alert.showAndWait();
            
            return false;
        }
    }
    
    public void signup() {
        String sql ="INSERT INTO admin (email,username,password) VALUES (?,?,?)";
        
        String sql1 = "SELECT * FROM admin";
        
        connect = database.connectDb();
        
        try{
            
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, signup_email.getText());
            prepare.setString(2, signup_username.getText());
            prepare.setString(3, signup_password.getText());
            
            Alert alert;
            
            if(signup_email.getText().isEmpty() || signup_username.getText().isEmpty() || signup_password.getText().isEmpty()){      
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all Blank fields");
                alert.showAndWait();              
            }else if(signup_password.getText().length() < 8){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait(); 
            }else{
                if (validEmail()){
                    prepare = connect.prepareStatement(sql1);
                    result = prepare.executeQuery();
                    
                    if(result.next()){
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText(signup_username.getText() + " was already exist!");
                        alert.showAndWait();
                    }else{
                        prepare.execute();
                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully create a new account!");
                        alert.showAndWait();
                
                        signup_email.setText("");
                        signup_username.setText("");
                        signup_password.setText("");
                    }
                }
            }
        }catch (Exception e) { e.printStackTrace();}
    }
    
    private double x = 0;
    private double y = 0;
    
    public void signin() throws IOException {
    String sql = "SELECT * FROM admin WHERE email = ? and password = ?";

        try {
            connect = database.connectDb();
        
            if (connect != null) {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, signin_email.getText());
                prepare.setString(2, signin_password.getText());
            
                result = prepare.executeQuery();
            
                Alert alert;
            
                if (signin_email.getText().isEmpty() || signin_password.getText().isEmpty()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all blank fields");
                    alert.showAndWait();
                 
                } else {
                    if (result.next()) {
                        
                        getData.email = signin_email.getText();
                        
                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully Logged In!");
                        alert.showAndWait();
                        
                        signin_loginBtn.getScene().getWindow().hide();
                    
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml")); // Adjust path if needed
                            Stage stage = new Stage();
                            Scene scene = new Scene(root);
                            
                            root.setOnMousePressed((MouseEvent event) ->{
                                x = event.getSceneX();
                                y = event.getSceneY();
                            });
                            
                            root.setOnMouseDragged((MouseEvent event) ->{
                                stage.setX(event.getScreenX() - x);
                                stage.setY(event.getScreenY() - y);
                            });
                            
                            stage.initStyle(StageStyle.TRANSPARENT);
                        
                            stage.setScene(scene);
                            stage.show();
                          
                        } catch (IOException e) {
                            e.printStackTrace();
                            Alert errorAlert = new Alert(AlertType.ERROR);
                            errorAlert.setTitle("FXML Load Error");
                            errorAlert.setHeaderText(null);
                            errorAlert.setContentText("An error occurred while loading the dashboard.");
                            errorAlert.showAndWait();
                        }
                  
                    } else {
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Wrong Email or Password");
                        alert.showAndWait(); 
                        }
                    }
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Database Error");
                alert.setHeaderText(null);
                alert.setContentText("Unable to connect to the database.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while querying the database.");
            alert.showAndWait();
        } finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
    public void switchForm(ActionEvent event) {
        if (event.getSource() == signin_createAccount) {
            signin_form.setVisible(false);
            signup_form.setVisible(true);

        } else if (event.getSource() == signup_alreadyhaveaccount) {
            signin_form.setVisible(true);
            signup_form.setVisible(false);
        }
    }

    public void signin_close() {
        System.exit(0);
    }

    public void signin_minimize() {
        Stage stage = (Stage) signin_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void signup_close() {
        System.exit(0);

    }

    public void signup_minimize() {
        Stage stage = (Stage) signup_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connect = database.connectDb(); 
        if (connect != null) {
            System.out.println("Database connection is ready to use.");
        } else {
            System.out.println("Failed to establish database connection.");
        }
    }

}
