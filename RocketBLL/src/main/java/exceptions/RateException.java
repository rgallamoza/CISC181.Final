package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {

	//	TODO - RocketBLL RateException - RateDomainModel should be an attribute of RateException
	//	* Add RateRomainModel as an attribute
	//	* Create a constructor, passing in RateDomainModel
	//	* Create a getter (no setter, set value only in Constructor)
	
	private RateDomainModel rdm;
	
	public RateException(RateDomainModel ratedm){
		this.rdm = ratedm;
	}
	
	public RateDomainModel getRdm() {
		return rdm;
	}

	public String getMessage(){
		String emessage = "Your credit score " + rdm.getiMinCreditScore() + " is too low to find an interest rate.";
		return emessage;
	}
}
