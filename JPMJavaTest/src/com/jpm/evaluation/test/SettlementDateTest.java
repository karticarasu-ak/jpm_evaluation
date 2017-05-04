package com.jpm.evaluation.test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.jpm.evaluation.util.PerformDataValidation;
import com.jpm.evaluation.util.PerformDataValidationImpl;
import com.jpm.evaluation.util.StringConstants;

/**
 * @author ak
 *
 */
public class SettlementDateTest {

	/* 
	 * Tests for Settlement Date from Monday to Thursday
	 * Expected Result: The settlement day should not change for any currency
	 *                : The settlement day should not change for AED and SAR currency
	*/
	@Test
	public void testNormalDay() throws ParseException {

		String settlementDate = "08-MAY-2017" ;  // Monday
		Date mondayDate = new SimpleDateFormat(StringConstants.DATE_FORMAT).parse("08-MAY-2017" );
		
		//newSettlmentDate : Should be Monday(Same as original settlement date)
		PerformDataValidation p = new PerformDataValidationImpl();
		Date newSettlmentDate = p.getSettlementDate(settlementDate, "SGP");
		assertEquals(newSettlmentDate, mondayDate);
		
		//newSettlmentDate : Should be Monday(Same as original settlement date) for AED
		newSettlmentDate = p.getSettlementDate(settlementDate, "AED");
		assertEquals(newSettlmentDate, mondayDate);
		
		/*Another sample date Thurday*/
		settlementDate= "11-MAY-2017"; //Thurday 
		Date thursDay = new SimpleDateFormat(StringConstants.DATE_FORMAT).parse("11-MAY-2017" );
		//newSettlmentDate : Should be Monday(Same as original settlement date)
        newSettlmentDate = p.getSettlementDate(settlementDate, "SGP");
		assertEquals(newSettlmentDate, thursDay);
		
	}
	
	
	
	/* 
	 * Tests for Settlement Date Friday
	 * Expected Result: The settlement day should be moved to Sunday for AED and SAR
	 *                : The settlement day should same for normal currency
	*/
	@Test
	public void testFriday() throws ParseException {

		String settlementDate = "05-MAY-2017"; // Friday
				
		Date fridayDate= new SimpleDateFormat(StringConstants.DATE_FORMAT).parse("05-MAY-2017"); 	
		
		Date sundayDate= new SimpleDateFormat(StringConstants.DATE_FORMAT).parse("07-MAY-2017"); 	
				
		PerformDataValidation p = new PerformDataValidationImpl();
		Date newSettlmentDate = p.getSettlementDate(settlementDate, "SGP");
		
		//newSettlmentDate: Should be Friday normal currency	
		assertEquals(newSettlmentDate,fridayDate );
		
		//newSettlmentDate: Should be Sunday: AED currency	
        newSettlmentDate = p.getSettlementDate(settlementDate, "AED");
		assertEquals(newSettlmentDate,sundayDate );

		//newSettlmentDate: Should be Sunday: SAR currency	
		newSettlmentDate = p.getSettlementDate(settlementDate, "SAR");
		assertEquals(newSettlmentDate,sundayDate );
	}

	
	/* 
	 * Tests for Settlement Date Saturday
	 * Expected Result: The settlement day should be moved to Sunday for AED and SAR
	 *                : The settlement day should be moved to Monday for normal currency
	*/
	@Test
	public void testSaturday() throws ParseException {

		String settlementDate = "06-MAY-2017"; // Saturday
		
		Date sundayDate= new SimpleDateFormat(StringConstants.DATE_FORMAT).parse("07-MAY-2017"); 	
		
		Date mondayDate= new SimpleDateFormat(StringConstants.DATE_FORMAT).parse("08-MAY-2017"); 	
				
		PerformDataValidation p = new PerformDataValidationImpl();
				
		//newSettlmentDate: Should be Monday for normal currency	
		Date newSettlmentDate = p.getSettlementDate(settlementDate, "SGP");
		assertEquals(newSettlmentDate,mondayDate );
		
		//newSettlmentDate: Should be Sunday: AED currency	
        newSettlmentDate = p.getSettlementDate(settlementDate, "AED");
		assertEquals(newSettlmentDate,sundayDate );

		//newSettlmentDate: Should be Sunday: SAR currency	
		newSettlmentDate = p.getSettlementDate(settlementDate, "SAR");
		assertEquals(newSettlmentDate,sundayDate );
		
	}
	
	
	/* 
	 * Tests for Settlement Date Sunday
	 * Expected Result: The settlement day should be moved to  Monday for normal currency 
	 *                : The settlement day should be same for AED and SAR
	*/
	@Test
	public void testSunday() throws ParseException {

		String settlementDate = "07-MAY-2017"; // Saturday
		
		Date sundayDate= new SimpleDateFormat(StringConstants.DATE_FORMAT).parse("07-MAY-2017"); 	
		
		Date mondayDate= new SimpleDateFormat(StringConstants.DATE_FORMAT).parse("08-MAY-2017"); 	
				
		PerformDataValidation p = new PerformDataValidationImpl();
				
		//newSettlmentDate: Should be Monday for normal currency	
		Date newSettlmentDate = p.getSettlementDate(settlementDate, "SGP");
		assertEquals(newSettlmentDate,mondayDate );
		
		//newSettlmentDate: Should be Sunday: AED currency	
        newSettlmentDate = p.getSettlementDate(settlementDate, "AED");
		assertEquals(newSettlmentDate,sundayDate );

		//newSettlmentDate: Should be Sunday: SAR currency	
		newSettlmentDate = p.getSettlementDate(settlementDate, "SAR");
		assertEquals(newSettlmentDate,sundayDate );
		
	}

}
