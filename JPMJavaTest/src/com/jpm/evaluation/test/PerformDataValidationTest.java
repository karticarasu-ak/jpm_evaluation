package com.jpm.evaluation.test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Test;

import com.jpm.evaluation.util.ErrorMessage;
import com.jpm.evaluation.util.PerformDataValidation;
import com.jpm.evaluation.util.PerformDataValidationImpl;
import com.jpm.evaluation.util.StringConstants;

public class PerformDataValidationTest {

	PerformDataValidation pdv;

	@Test
	public void testBuySellErrort() throws ParseException {

		pdv = new PerformDataValidationImpl();
		pdv.verifyUnits("k", 10, "SGP", new SimpleDateFormat("dd-MMM-yyyy").parse("07-JUNE-2017"),
				new SimpleDateFormat("dd-MMM-yyyy").parse("07-JUNE-2017"), 10, 30);

		assertEquals(ErrorMessage.getErrorMessage(), StringConstants.BUYSELLERROR);

	}

	@Test
	public void testPriceError() throws ParseException {

		pdv = new PerformDataValidationImpl();
		pdv.verifyUnits("B", 10, "SGP", new SimpleDateFormat("dd-MMM-yyyy").parse("07-JUNE-2017"),
				new SimpleDateFormat("dd-MMM-yyyy").parse("07-JUNE-2017"), 10, 0);
		assertEquals(ErrorMessage.getErrorMessage(), StringConstants.PRICE_ERROR);
	}

	@Test
	public void testSettlementDateError() throws ParseException {

		pdv = new PerformDataValidationImpl();
		pdv.verifyUnits("B", 10, "SGP", new SimpleDateFormat("dd-MMM-yyyy").parse("07-JUNE-2017"),
				new SimpleDateFormat("dd-MMM-yyyy").parse("07-JUNE-2016"), 10, 30);
		assertEquals(ErrorMessage.getErrorMessage(), StringConstants.SETTELMENT_DATE_ERROR);

	}

	@Test
	public void testInstructionDate() throws ParseException {

		pdv = new PerformDataValidationImpl();
		pdv.verifyUnits("B", 10, "SGP", new SimpleDateFormat("dd-MMM-yyyy").parse("01-JAN-2016"),
				new SimpleDateFormat("dd-MMM-yyyy").parse("07-JUNE-2017"), 10, 30);

		assertEquals(ErrorMessage.getErrorMessage(), StringConstants.INSTRUCTION_DATE_ERROR);

	}

	@Test
	public void testUnitError() throws ParseException {
		pdv = new PerformDataValidationImpl();
		pdv.verifyUnits("B", 10, "SGP", new SimpleDateFormat("dd-MMM-yyyy").parse("07-JUNE-2017"),
				new SimpleDateFormat("dd-MMM-yyyy").parse("07-JUNE-2017"), 0, 10);

		assertEquals(ErrorMessage.getErrorMessage(), StringConstants.UNIT_ERROR);

	}

	@Test
	public void testAgreedFxError() throws ParseException {
		pdv = new PerformDataValidationImpl();
		pdv.verifyUnits("B", 0, "SGP", new SimpleDateFormat("dd-MMM-yyyy").parse("07-JUNE-2017"),
				new SimpleDateFormat("dd-MMM-yyyy").parse("07-JUNE-2017"), 10, 30);

		assertEquals(ErrorMessage.getErrorMessage(), StringConstants.AGREED_FX_ERROR);

	}
	
	@Test
	public void testInstructionDateFormat() throws ParseException {

		pdv = new PerformDataValidationImpl();
		try{
		  pdv.getInstructionDate("07-01-2017");
		}catch (Exception e){
		assertEquals(ErrorMessage.getErrorMessage(), StringConstants.INSTRUCTION_DATE_FORMAT_ERROR);
		}
	}

	@After
	public void cleanUp() {
		ErrorMessage.clearErrorMessage();
		pdv = null;

	}

}
