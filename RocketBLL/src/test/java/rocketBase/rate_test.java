package rocketBase;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import exceptions.RateException;

public class rate_test {

	@AfterClass
	public static void afterstuff() throws RateException{
		System.out.println(RateBLL.getRate(625));
	}
	
	@Test
	public void rate_test1() throws RateException{
		assertEquals(RateBLL.getRate(625),7.0,0.01);
		assertEquals(RateBLL.getRate(675),6.5,0.01);
		assertEquals(RateBLL.getRate(725),6.0,0.01);
		assertEquals(RateBLL.getRate(775),5.75,0.01);
		assertEquals(RateBLL.getRate(900),5.5,0.01);
	}
	
	@Test(expected=RateException.class)
	public void rate_test2() throws RateException{
		RateBLL.getRate(200);
	}
	
	@Test
	public void pmt_test(){
		assertEquals(RateBLL.getPayment(0.04/12, 360, 300000, 0, false)*-1,1432.25,0.01);
	}
}
