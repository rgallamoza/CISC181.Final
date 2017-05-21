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
		
		double houseCost = Double.parseDouble(txtHouseCost.getCharacters().toString());
		lrequest.setdAmount(houseCost);
		
		int creditScore = Integer.parseInt(txtCreditScore.getCharacters().toString());
		lrequest.setiCreditScore(creditScore);
		
		int downPayment = Integer.parseInt(txtDownPayment.getCharacters().toString());
		lrequest.setiDownPayment(downPayment);
		
		int mortgageTerm = cmbTerm.getValue();
		lrequest.setiTerm(mortgageTerm*12);
		
		double Income = Double.parseDouble(txtIncome.getCharacters().toString());
		lrequest.setIncome(Income/12);
		
		double Expenses = Double.parseDouble(txtExpenses.getCharacters().toString());
		lrequest.setExpenses(Expenses);
		
		HandleLoanRequestDetails(lrequest);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		//	TODO - RocketClient.HandleLoanRequestDetails
		//			lRequest is an instance of LoanRequest.
		//			after it's returned back from the server, the payment (dPayment)
		//			should be calculated.
		//			Display dPayment on the form, rounded to two decimal places
		
		double rate = 0;
		
		try{
			rate = RateBLL.getRate(lRequest.getiCreditScore());
		}
		catch(RateException re){
			System.out.println("RateException: " + re.getMessage());
			re.printStackTrace();
		}
		
		double payment = RateBLL.getPayment(rate/1200, lRequest.getiTerm(), lRequest.getdAmount()-lRequest.getiDownPayment(), 0, false)*-1;
		
		double piti28 = (lRequest.getIncome())*0.28;
		double piti36 = ((lRequest.getIncome())*0.36)-lRequest.getExpenses();
		
		double piti = 0;
		if(piti28<piti36){
			piti = piti28;
		}
		else{
			piti = piti36;
		}
		if(payment>piti){
			lblCalculation.setText("House Cost too high");
		}
		else{
			lblCalculation.setText("Your monthly payment: " + String.format("%.2f",payment));
		}
	}
}
