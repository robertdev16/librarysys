package UI;

import java.util.ArrayList;
import java.util.List;

import Application.AccountEntry;
import Application.Address;
import Application.Author;
import Application.Book;
import Application.Member;
import Application.Periodical;
import DataAccess.DataAccess;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainWindowController {
	@FXML
	private Label accountNameLabel;

	@FXML
	private Label adminRoleLabel;

	@FXML
	private Label libRoleLabel;

	@FXML
	private ChoiceBox<String> isbnChoicebox;

	@FXML
	private TextField itemIdSearchField;

	@FXML
	private Button searchItemButton;

	@FXML
	private Button beginCheckoutButton;

	@FXML
	private Button addBookButton;

	@FXML
	private Button addPeriodicalButton;

	@FXML
	private Button addCopyButton;

	@FXML
	private Button searchMemberButton;

	@FXML
	private Button showRecordButton;

	@FXML
	private Button editMemberButton;

	@FXML
	private Button addMemberButton;

	@FXML
	private BorderPane authorBorderPane;

	@FXML
	private TextArea itemTitle;
	@FXML
	private TextField itemIdShowField;
	@FXML
	private TextField pubDateField;
	@FXML
	private TextField maxDaysField;
	@FXML
	private TextField copiesNumField;	
	
	
	@FXML
	private Label releaseLabel;
	
	@FXML
	private Label isbnLabel;

	@FXML
	private ChoiceBox<String> releaseChoiceBox;

	@FXML
	private ListView<Author> authorListview;
	@FXML
	private Label authorFirstnameLabel;
	@FXML
	private Label authorLastnameLabel;
	@FXML
	private TextField authorStreetField;
	@FXML
	private TextField authorCityField;
	@FXML
	private TextField authorStateField;
	@FXML
	private TextField authorZipField;
	@FXML
	private TextField authorPhoneField;
	@FXML
	private TextArea credentialsTextArea;
	@FXML
	private TextArea biographyTextArea;
	
	@FXML
	private TextField memberIdSearchField;
	@FXML
	private TextField memberFirstnameField;
	@FXML
	private TextField memberLastnameField;
	@FXML
	private TextField memberIdShowField;
	@FXML
	private TextField memberStreetField;
	@FXML
	private TextField memberCityField;
	@FXML
	private TextField memberStateField;
	@FXML
	private TextField memberZipField;
	@FXML
	private TextField memberPhoneField;

	private Main mainApp;
	private Stage loginStage;
	private Stage mainStage;
	private Alert alert;
	private ObservableList<Author> obvList = FXCollections.observableArrayList();
	private SystemController systemController = new SystemController();

	public void initAuthorListview(){
		authorListview.setItems(obvList);
		authorListview.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Author>() {
					public void changed(ObservableValue<? extends Author> ov,
							Author old_val, Author new_val) {
						clearAuthorField();
						if (new_val != null)
							updateAuthorField(new_val);
					}
				}

		);
	}
	
	public void addListenerToIsbnChoicebox() {
		isbnChoicebox.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<String>() {
					public void changed(ObservableValue<? extends String> ov,
							String old_val, String new_val) {
						
						if ("ISBN".compareTo(new_val) == 0){
							isbnLabel.setText("ISBN");
							authorBorderPane.setDisable(false);
							clearItemField();
							clearAuthorField();
							obvList.clear();
							releaseLabel.setDisable(true);
							releaseChoiceBox.setDisable(true);
						}
						else{
							isbnLabel.setText("ISSN");
							authorBorderPane.setDisable(true);
							clearItemField();
							clearAuthorField();
							obvList.clear();
							releaseLabel.setDisable(false);
							releaseChoiceBox.setDisable(false);
						}
					}
				}

		);
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

	public void setLoginStage(Stage loginStage) {
		this.loginStage = loginStage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	public void initRole(AccountEntry account) {
		accountNameLabel.setText(account.getUsername());
		if (!account.isAdmin())
			disableAdminButtons();
		if (!account.isLibrarian())
			disableLibrarianButtons();
	}

	private void disableLibrarianButtons() {
		libRoleLabel.setDisable(true);
		beginCheckoutButton.setDisable(true);
		showRecordButton.setDisable(true);
	}

	private void disableAdminButtons() {
		adminRoleLabel.setDisable(true);
		addBookButton.setDisable(true);
		addPeriodicalButton.setDisable(true);
		addCopyButton.setDisable(true);
		editMemberButton.setDisable(true);
		addMemberButton.setDisable(true);
	}

	private void enableButtons() {
		libRoleLabel.setDisable(false);
		beginCheckoutButton.setDisable(false);
		showRecordButton.setDisable(false);
		adminRoleLabel.setDisable(false);
		addBookButton.setDisable(false);
		addPeriodicalButton.setDisable(false);
		addCopyButton.setDisable(false);
		editMemberButton.setDisable(false);
		addMemberButton.setDisable(false);
	}

	private boolean isDigitRule(String numStr) {
		for (int i = 0; i < numStr.length(); i++) {
			if (!Character.isDigit(numStr.charAt(i)))
				return false;
		}
		return true;
	}

	@FXML
	public void handleLogoutButton(ActionEvent event) {
		mainStage.hide();
		loginStage.show();
		enableButtons();
		clearItemField();
		clearAuthorField();
		obvList.clear();
		clearMemberField();
		isbnChoicebox.getSelectionModel().select(0);
	}
	
	@FXML
	public void handleAddCopyButton(ActionEvent event) {
		String copiesNumStr = copiesNumField.getText();
		if (copiesNumStr.length() == 0){
			showErrorAlert("Error", "Invalid copies number",
					"Please search a valid publication first!");
		}
		else{
			copiesNumField.setText(Integer.toString(Integer.parseInt(copiesNumStr) + 1));
			showInfoAlert("Info", "Add copy successfully!",
					"A new copy has been added to this publication!");
		}
		
	}
	
	@FXML
	public void handleSearchItem(ActionEvent event) {
		Book book = null;
		Periodical periodical = null;

		String idText = itemIdSearchField.getText();
		if ((idText.length() == 0) || (!isDigitRule(idText))) {
			showErrorAlert("Error", "Invalid ISBN/ISSN",
					"ISBN/ISSN should be digits and cannot be empty!");
			return;
		}
		if ("ISBN".compareTo(isbnChoicebox.getValue()) == 0){
			book = systemController.searchBook("B" + idText);
			if (book == null) {
				showErrorAlert("Error", "Invalid ISBN",
						"The book of this ISBN does not exist!");
				return;
			} else {
				itemIdSearchField.clear();
				updateBookField(book);
			}
		}
		else{
			periodical = systemController.searchPeriodical("P" + idText);
			if (periodical == null) {
				showErrorAlert("Error", "Invalid ISSN",
						"The periodical of this ISSN does not exist!");
				return;
			} else {
				itemIdSearchField.clear();
				updatePeriodicalField(periodical);
			}
		}
	}
	
	private void updateBookField(Book book){
		itemTitle.setText(book.getTitle());
		itemIdShowField.setText(book.getISBN());
		pubDateField.setText(book.getPublicationdate().toString());
		maxDaysField.setText(Integer.toString(book.getMax_day_borrow()));
		
		SystemController sys = null;
		copiesNumField.setText(Integer.toString(sys.countBook(book)));
		
		List<Author> authorList = book.getAuthors();
		obvList.setAll(authorList);
		authorListview.getSelectionModel().select(0);
	}
	
	private void updateAuthorField(Author author){
		authorFirstnameLabel.setText(author.getFirstname());
		authorLastnameLabel.setText(author.getLastname());
		authorStreetField.setText(author.getAddress().getStreet());
		authorCityField.setText(author.getAddress().getCity());
		authorStateField.setText(author.getAddress().getState());
		authorZipField.setText(Integer.toString(author.getAddress().getZip()));
		authorPhoneField.setText(author.getPhone());
		credentialsTextArea.setText(author.getCredential());
		biographyTextArea.setText(author.getBio());
	}	
	
	private void updatePeriodicalField(Periodical periodical){
		itemTitle.setText(periodical.getTitle());
		itemIdShowField.setText(periodical.getISSN());
		pubDateField.setText(periodical.getPublicationdate().toString());
		maxDaysField.setText(Integer.toString(periodical.getMax_day_borrow()));
		SystemController sys = null;
		copiesNumField.setText(Integer.toString(sys.countperiodical(periodical)));
		releaseChoiceBox.getSelectionModel().select(2);
	}
	ArrayList<String> a=new ArrayList<String>();
	private void clearItemField(){
		itemIdSearchField.clear();
		itemTitle.clear();
		itemIdShowField.clear();
		pubDateField.clear();
		maxDaysField.clear();
		copiesNumField.clear();
		releaseChoiceBox.getSelectionModel().select(0);
	}
	
	private void clearAuthorField(){
		authorFirstnameLabel.setText("(Firstname)");
		authorLastnameLabel.setText("(Lastname)");
		authorStreetField.clear();
		authorCityField.clear();
		authorStateField.clear();
		authorZipField.clear();
		authorPhoneField.clear();
		credentialsTextArea.clear();
		biographyTextArea.clear();
	}

	@FXML
	public void handleSearchMember(ActionEvent event) {
		Member member = null;
		String idText = memberIdSearchField.getText();
		if ((idText.length() == 0) || (!isDigitRule(idText))) {
			showErrorAlert("Error", "Invalid member ID",
					"Member ID should be digits and cannot be empty!");
			return;
		}
		member = systemController.searchMember(Integer.parseInt(idText));
		if (member == null) {
			showErrorAlert("Error", "Invalid member ID",
					"This member ID does not exist!");
			return;
		} else {
			memberIdSearchField.clear();
			updateMemberField(member);
		}
	}

	private void updateMemberField(Member member) {
		memberFirstnameField.setText(member.getFirstname());
		memberLastnameField.setText(member.getLastname());
		memberIdShowField.setText(Integer.toString(member.getMemberID()));
		memberStreetField.setText(member.getAddress().getStreet());
		memberCityField.setText(member.getAddress().getCity());
		memberStateField.setText(member.getAddress().getState());
		memberZipField.setText(Integer.toString(member.getAddress().getZip()));
		memberPhoneField.setText(member.getPhone());
	}
	
	private void clearMemberField() {
		memberIdSearchField.clear();
		memberFirstnameField.clear();
		memberLastnameField.clear();
		memberIdShowField.clear();
		memberStreetField.clear();
		memberCityField.clear();
		memberStateField.clear();
		memberZipField.clear();
		memberPhoneField.clear();
	}

	@FXML
	public void handleBeginCheckoutButton(ActionEvent event) {
		Member member = null;
		Book book = null;
		Periodical periodical = null;
		String memberIdText = memberIdShowField.getText();
		String itemIdText = itemIdShowField.getText();

		if (memberIdText.length() == 0) {
			showErrorAlert("Error", "Invalid member ID",
					"Please search a valid member first!");
			return;
		}
		if (itemIdText.length() == 0) {
			showErrorAlert("Error", "Invalid ISBN/ISSN",
					"Please search a publication first!");
			return;
		}
		
		member = systemController.searchMember(Integer.parseInt(memberIdText));
		if ("ISBN".compareTo(isbnChoicebox.getValue()) == 0){
			book = systemController.searchBook("B" + itemIdText);
			if (book == null) {
				showErrorAlert("Error", "Invalid ISBN",
						"The book of this ISBN does not exist!");
				return;
			}
			else {
				mainApp.showShowRecordStage(member, book);
			}
		}
		else{
			periodical = systemController.searchPeriodical("P" + itemIdText);
			if (periodical == null) {
				showErrorAlert("Error", "Invalid ISSN",
						"The periodical of this ISSN does not exist!");
				return;
			}
			else {
				mainApp.showShowRecordStage(member, periodical);
			}
		}
		
	}

	@FXML
	public void handleShowRecordButton(ActionEvent event) {
		Member member = null;
		String idText = memberIdShowField.getText();

		if (idText.length() == 0) {
			showErrorAlert("Error", "Invalid member ID",
					"Please search a valid member first!");
			return;
		}
		member = systemController.searchMember(Integer.parseInt(idText));
		mainApp.showShowRecordStage(member, null);
	}

	@FXML
	public void handleAddMemberButton() {
		// System.out.println("call Add_Member");
		boolean SubmitClicked = mainApp.showEditMemberDialog(null);
		if (SubmitClicked)
			showInfoAlert("Info", "Add member successfully!",
					"A new member has been saved!");
	}

	@FXML
	public void handleEditMemberButton() {
		// System.out.println("call Edit_Member");
		// test code
		// Address address = new Address("4th","fairfeld","iowa",52557);
		// Member tempPerson = new
		// Member(1001,"abd","def","10086",address,null);
		// test code
		Member member = null;
		String idText = memberIdShowField.getText();

		if (idText.length() == 0) {
			showErrorAlert("Error", "Invalid member ID",
					"Please search a valid member first!");
			return;
		}
		member = systemController.searchMember(Integer.parseInt(idText));

		boolean SubmitClicked = mainApp.showEditMemberDialog(member);
		if (SubmitClicked) {
			member = systemController.searchMember(Integer.parseInt(idText));
			updateMemberField(member);
			showInfoAlert("Info", "Edit member successfully!",
					"Member information has been updated!");
		}
	}

	@FXML
	public void handleAddBookButton() {
		// System.out.println("call Add_Book");
		boolean SubmitClicked = mainApp.showAddBookDialog(/* tempBook */);
		if (SubmitClicked)
			showInfoAlert("Info", "Add book successfully!",
					"A book has been saved!");
	}

	@FXML
	public void handleAddPeriodicalButton() {
		// System.out.println("call Add_Periodical");
		boolean SubmitClicked = mainApp.showAddPeriodicalDialog(/* tempBook */);
		if (SubmitClicked)
			showInfoAlert("Info", "Add periodical successfully!",
					"A periodical has been saved!");
	}

	private void showErrorAlert(String title, String header, String content) {
		alert = new Alert(AlertType.ERROR);
		alert.initOwner(mainStage);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	private void showInfoAlert(String title, String header, String content) {
		alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(mainStage);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

}
