package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.*;

import exceptions.RateException;
import rocketDomain.RateDomainModel;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws RateException 
	{
		double dInterestRate = 0;

		ArrayList<RateDomainModel> Rates = (ArrayList<RateDomainModel>) RateDAL.getAllRates().stream().filter(r -> r.getiMinCreditScore()<=GivenCreditScore).collect(Collectors.toList());

		if(Rates.isEmpty()){
			RateDomainModel rde = new RateDomainModel();
			rde.setiMinCreditScore(GivenCreditScore);
			throw new RateException(rde);
		}
		else{
			dInterestRate = Rates.get(0).getdInterestRate();
		}
		
		return dInterestRate;
		
		
	}
	
	public static double getPayment(double r, double n, double p, double f, boolean t)
	{		
		return FinanceLib.pmt(r, n, p, f, t);
	}
}
