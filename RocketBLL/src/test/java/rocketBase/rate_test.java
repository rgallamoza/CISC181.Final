package rocketBase;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import exceptions.RateException;

public class rate_test {

	//TODO - RocketBLL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
	//TODO - RocketBLL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	@AfterClass
	public static void afterstuff() throws RateException{
		System.out.println(RateBLL.getRate(625));
	}
	
	@Test
	public void rate_test1() throws RateException{
		assertEquals(RateBLL.getRate(625),7.0,0.0001);
		assertEquals(RateBLL.getRate(675),6.5,0.0001);
		assertEquals(RateBLL.getRate(725),6.0,0.0001);
		assertEquals(RateBLL.getRate(775),5.75,0.0001);
		assertEquals(RateBLL.getRate(900),5.5,0.0001);
	}
	
	@Test(expected=RateException.class)
	public void rate_test2() throws RateException{
		RateBLL.getRate(200);
	}
}
