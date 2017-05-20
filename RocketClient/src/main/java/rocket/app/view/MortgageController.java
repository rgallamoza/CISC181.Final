package rocket.app.view;

import org.apache.poi.ss.formula.functions.FinanceLib;

import eNums.eAction;
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

	@FXML TextField txtIncome;
	@FXML TextField txtExpenses;
	@FXML TextField txtCreditScore;
	@FXML TextField txtHouseCost;
	@FXML TextField txtDownPayment;
	
	@FXML Label lblIncome;
	@FXML Label lblExpenses;
	@FXML Label lblCreditScore;
	@FXML Label lblHouseCost;
	@FXML Label lblMortgageTerm;
	@FXML Label lblCalcPayment;
	@FXML Label lblCalculation;
	@FXML Label lblDownPayment;
	
	@FXML ComboBox<Integer> cmbTerm;
	
	private TextField txtNew;
	
	private MainApp mainApp;
	

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		cmbTerm.getItems().addAll(15,30);
	}
	
	
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message Here...");
		alert.setHeaderText("Look, an Information Dialog");
		alert.setContentText(txtNew.getText());
		alert.showAndWait().ifPresent(rs -> {
		    if (rs == ButtonType.OK) {
		        System.out.println("Pressed OK.");
		    }
		});
		
		double houseCost = Double.parseDouble(txtHouseCost.getCharacters().toString());
		int creditScore = Integer.parseInt(txtCreditScore.getCharacters().toString());
		double downPayment = Double.parseDouble(txtDownPayment.getCharacters().toString());
		double mortgageTerm = cmbTerm.getButtonCell().getItem();
		
		double rate = RateBLL.getRate(creditScore);
		
//		double pv = FinanceLib.pv(rate, mortgageTerm, y, 0, false);
//		
//		double payment = RateBLL.getPayment(rate, mortgageTerm, p, 0, false);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		//	TODO - RocketClient.HandleLoanRequestDetails
		//			lRequest is an instance of LoanRequest.
		//			after it's returned back from the server, the payment (dPayment)
		//			should be calculated.
		//			Display dPayment on the form, rounded to two decimal places
		
		
	}
}
