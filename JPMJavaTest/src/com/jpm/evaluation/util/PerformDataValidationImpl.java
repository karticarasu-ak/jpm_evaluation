package com.jpm.evaluation.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**Implementation of the Interface PerformDataValidation
 * 
 * @author AK
 *
 */
public class PerformDataValidationImpl implements PerformDataValidation {

	@Override
	public boolean verifyUnits(String buyOrSell, double agreedFx, String currency, Date instructionDate,
			Date settlementDate, long units, double pricePeUnit) {

		boolean noError = true;
		
		Date todayDate = todaysDate();

		if (!(buyOrSell == "S" || buyOrSell == "B")) {
			ErrorMessage.writeErrorMessage(StringConstants.BUYSELLERROR);
			noError = false;
		}
		if (agreedFx == 0) {
			ErrorMessage.writeErrorMessage(StringConstants.AGREED_FX_ERROR);
			noError = false;
		}
		if (currency == "") {
			ErrorMessage.writeErrorMessage(StringConstants.CURRENCY_ERROR);
			noError = false;
		}
		if (pricePeUnit == 0) {
			ErrorMessage.writeErrorMessage(StringConstants.PRICE_ERROR);
			noError = false;
		}
		if (units == 0) {
			ErrorMessage.writeErrorMessage(StringConstants.UNIT_ERROR);
			noError = false;
		}
		if (instructionDate != null && instructionDate.before(todayDate)) {
			ErrorMessage.writeErrorMessage(StringConstants.INSTRUCTION_DATE_ERROR);
			noError = false;
		}
		if (settlementDate != null && settlementDate.before(todayDate)) {
			ErrorMessage.writeErrorMessage(StringConstants.SETTELMENT_DATE_ERROR);
			noError = false;
		}
		return noError;
	}

	@Override
	public Date getSettlementDate(String date, String currency) throws ParseException {

		Date settlementDate = null;

		try {
			settlementDate = new SimpleDateFormat(StringConstants.DATE_FORMAT).parse(date);
		} catch (ParseException parseException) {
			ErrorMessage.writeErrorMessage(StringConstants.SETTELMENT_DATE_FORMAT_ERROR);
			throw parseException;
		}

		Calendar caledar = new GregorianCalendar();
		caledar.setTime(settlementDate);

		boolean isNormalCurrency = true; //True for normal currency 
		
		if ( currency.equals("AED") || currency.equals("SAR") ) {
			isNormalCurrency = false;
		}
		
		int dayOfTheWeekInt = caledar.get(Calendar.DAY_OF_WEEK);
	
		// Check which day the date falls
		switch ( dayOfTheWeekInt ) {

		case Calendar.SUNDAY:
			if ( isNormalCurrency ) {
				caledar.add(Calendar.DATE, 1);
			}
			break;

		case Calendar.SATURDAY:
			if ( isNormalCurrency ) {
				caledar.add(Calendar.DATE, 2);
			} else {
				caledar.add(Calendar.DATE, 1);			
			}
			break;

		case Calendar.FRIDAY:
			if ( !isNormalCurrency ) {
				caledar.add(Calendar.DATE, 2);
			}
			break;

		}
		return caledar.getTime();
	}

	@Override
	public Date getInstructionDate(String date) throws ParseException {

		Date instructionDate = null;
		try {
			instructionDate = new SimpleDateFormat(StringConstants.DATE_FORMAT).parse(date);
		} catch (ParseException parseException) {
			ErrorMessage.writeErrorMessage(StringConstants.INSTRUCTION_DATE_FORMAT_ERROR);
			throw parseException;
		}
		return instructionDate;
	}
	
	private Date todaysDate(){
		
		Date todayDate = new Date(); // SystemDate
		
		String env= System.getProperty(StringConstants.ENV_VAR);
		
		if (  env != null && env.equals(StringConstants.ENV_VAR_PROP)) {
			
			try {
				todayDate= new SimpleDateFormat(StringConstants.DATE_FORMAT).parse(StringConstants.TEST_DATE);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return todayDate;
	}
}
