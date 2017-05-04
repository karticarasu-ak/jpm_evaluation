package com.jpm.evaluation.util;

import java.text.ParseException;
import java.util.Date;

/**
 * This Interface performs validation on the Instruction Data entered.
 * The main purpose is
 * verify the different units on basic checks
 * verify the settlement date.
 * verify the instruction date.
 * 
 * @author AK
 *
 */
public interface PerformDataValidation {
		
	/**
	 * Verifies each and every unit given in the parameter
	 * 
	 * @param buyOrSell
	 * @param agreedFx
	 * @param currency
	 * @param instructionDate
	 * @param settlementDate
	 * @param units
	 * @param pricePeUnit
	 * @return true if succesfull : false if there are any exceptions during adding
	 */
	boolean verifyUnits(String buyOrSell, double agreedFx, String currency,
			Date instructionDate, Date settlementDate, long units, double pricePeUnit);
	
	
	/**
	 * Gets the settlement date based on predefined rules
	 * 
	 * @param date : The settlement date given in the instruction
	 * @param currency : The currency value given in the instruction
	 * @return : The new settlement date based on the pre defined rules
	 * @throws ParseException
	 */
	Date getSettlementDate(String date,String currency) throws ParseException;
	
	/**
	 * Gets the instruction date based on predefined rules
	 * @param date The instruction date in String format
	 * @return The instruction date in Date format
	 * @throws ParseException
	 */
	Date getInstructionDate(String date) throws ParseException;
}
