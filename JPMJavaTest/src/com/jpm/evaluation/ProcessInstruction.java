package com.jpm.evaluation;

import java.util.Date;

import com.jpm.evaluation.data.InstructionDataList;
import com.jpm.evaluation.exception.BaseCustomException;

/**
 * This Interface is used to process new instructions for a day and also
 * Different methods available are
 * adding a new instruction
 * getting the data for a particular day
 * printing the report for all the days
 * 
 * @author AK
 *
 */
public interface ProcessInstruction {

	/**
	 * This method adds instruction data to the list
	 * 
	 * 
	 * @param idata Object of Instruction Data
	 * 
	 * @throws BaseCustomException
	 */
	void addNewIntruction(String entityName, String buyOrSell, double agreedFx, String currency,
			String instructionDate, String settlementDate, long units, double pricePeUnit) throws BaseCustomException;

	/**
	 * This method returns the list of instructions for a particular settlement date
	 * 
	 * @param date Date for which the 
	 * @return List of data for the give date
	 */
	InstructionDataList getDataForThisDay(Date date);

	/**
	 * This method prints the report for all the days based on the settlement date
	 *  
	 */
	void printReportforAllDays();
	
}
