package loginapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import admin.AdminController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import students.StudentsController;

public class LoginController implements Initializable {
	
	LoginModel loginModel = new LoginModel();
	
	@FXML
	private Label dbstatus;
	@FXML
	private TextField txtusern;
	@FXML
	private PasswordField txtpass;
	@FXML
	private ComboBox<option> cmbadminstudent;
	@FXML
	private Button btnlogin;
	@FXML
	private Label loginstatus;
	

	public void initialize(URL url, ResourceBundle rb) {
		if (this.loginModel.isDatabaseConnected()) {
			this.dbstatus.setText("Connected");
		} else {
			this.dbstatus.setText("Not Connected");
		}
		
		this.cmbadminstudent.setItems(FXCollections.observableArrayList(option.values()));
	}

	@FXML
	public void Login(ActionEvent event) {
		try {
			
			if (this.loginModel.isLogin(this.txtusern.getText(), this.txtpass.getText(), ((option)this.cmbadminstudent.getValue()).toString())) {
				Stage stage = (Stage)this.btnlogin.getScene().getWindow();
				stage.close();
				System.out.println("Login");
				
				switch (((option) this.cmbadminstudent.getValue()).toString()) {
				case "Admin":
					adminLogin();
					break;
				case "Student":
					studentLogin();
					break;
				} 
			} else {
				this.loginstatus.setText("Username/password incorrect.");
			}
			
		} catch (Exception localEx) {
			// TODO: handle exception
			localEx.printStackTrace();
			
		}
	}
	
	public void studentLogin() {
		try {
			Stage userStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = (Pane)loader.load(getClass().getResource("/students/studentsFXML.fxml").openStream());
			System.out.println("Student");
			
			StudentsController studentsController = (StudentsController)loader.getController();
			
			Scene scene = new Scene(root);
			
			userStage.setScene(scene);
			userStage.setTitle("Student Dashboard");
			userStage.setResizable(false);
			userStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void adminLogin() {
		try {
			Stage adminStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = (Pane)loader.load(getClass().getResource("/admin/adminFXML.fxml").openStream());
			System.out.println("Admin");
			
			AdminController adminController = (AdminController)loader.getController();
			
			Scene scene = new Scene(root);
			adminStage.setScene(scene);
			adminStage.setTitle("Admin Dashboard");
			adminStage.setResizable(false);
			adminStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
