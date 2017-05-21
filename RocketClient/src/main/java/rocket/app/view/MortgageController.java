package rocket.app.view;

import org.apache.poi.ss.formula.functions.FinanceLib;

import eNums.eAction;
import exceptions.RateException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	@FXML
	private TextField txtIncome;
	@FXML 
	private TextField txtExpenses;
	@FXML
	private TextField txtCreditScore;
	@FXML 
	private TextField txtHouseCost;
	@FXML 
	private TextField txtDownPayment;
	
	@FXML 
	private Label lblIncome;
	@FXML 
	private Label lblExpenses;
	@FXML 
	private Label lblCreditScore;
	@FXML 
	private Label lblHouseCost;
	@FXML 
	private Label lblMortgageTerm;
	@FXML 
	private Label lblCalcPayment;
	@FXML 
	private Label lblCalculation;
	@FXML 
	private Label lblDownPayment;
	
	@FXML 
	private ComboBox<Integer> cmbTerm;
	
	@FXML
	private TextField txtNew;
	
	private MainApp mainApp;
	

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		cmbTerm.getItems().addAll(15,30);
	}
	
	
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
//		Alert alert = new Alert(AlertType.INFORMATION);
//		alert.setTitle("Message Here...");
//		alert.setHeaderText("Look, an Information Dialog");
//		alert.setContentText(txtNew.getText());
//		alert.showAndWait().ifPresent(rs -> {
//		    if (rs == ButtonType.OK) {
//		        System.out.println("Pressed OK.");
//		    }
//		});
		
		LoanRequest lrequest = new LoanRequest();
		
		lrequest.setdAmount(Double.parseDouble(txtHouseCost.getCharacters().toString()));
	
		lrequest.setiCreditScore(Integer.parseInt(txtCreditScore.getCharacters().toString()));

		lrequest.setiDownPayment(Integer.parseInt(txtDownPayment.getCharacters().toString()));

		lrequest.setiTerm(cmbTerm.getValue()*12);

		lrequest.setIncome(Double.parseDouble(txtIncome.getCharacters().toString())/12);

		lrequest.setExpenses(Double.parseDouble(txtExpenses.getCharacters().toString()));
		
		mainApp.messageSend(lrequest);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{

		double piti28 = (lRequest.getIncome())*0.28;
		double piti36 = ((lRequest.getIncome())*0.36)-lRequest.getExpenses();
		
		double piti = 0;
		if(piti28<piti36){
			piti = piti28;
		}
		else{
			piti = piti36;
		}
		
		if(lRequest.getdRate()==0){
			lblCalculation.setText("Your Credit Score is too low.");
		}
		else if(lRequest.getdPayment()>piti){
			lblCalculation.setText("House Cost too high.");
		}
		else{
			lblCalculation.setText("Your monthly payment: " + String.format("%.2f",lRequest.getdPayment()));
		}
	}
}
