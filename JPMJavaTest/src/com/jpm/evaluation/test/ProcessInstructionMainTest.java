package com.jpm.evaluation.test;

import java.text.ParseException;

import com.jpm.evaluation.ProcessInstruction;
import com.jpm.evaluation.ProcessInstructionImpl;
import com.jpm.evaluation.exception.BaseCustomException;
import com.jpm.evaluation.util.StringConstants;

public class ProcessInstructionMainTest extends BaseTestClass {

	
	
	/**
	 * The main method which is used to test the data Ingestion and report
	 * 
	 * @param args
	 * @throws ParseException 
	 * @throws BaseCustomException 
	 * 
	 * Test Data 
	 * Test 1:
	 * SettlementDate: 07-Jan-2016: Thursday
	 * 1-instruction from abc,xyz,lmn,cde,jkl,bar
	 * Total: Incoming amount: 10000
	 * Total: Outgoing amount: 19899.5
	 * Highest Rank Buy: abc
	 * Highest Rank Buy: bar
	 * 
	 * Test 2:
	 * SettlementDate: 11-Jan-2016: Monday
	 * 1-ppc --On 10th should be moved to 11th
	 * 1-plp - On 9th should be moved to 11th
	 * Total: Incoming amount: 20050
	 * Total: Outgoing amount: 0
	 * Highest Rank Sell: ppc
	 * Highest Rank Buy: None
	 *  
	 * Test 3:
	 * 
	 * SettlementDate: 10-Jan-2016: Sunday
	 * 1 -aed
	 * 1 -aec should be moved to 10th
	 * Total: Incoming amount: 20050
	 * Total: Outgoing amount: 0
	 * Highest Rank Sell: aed
	 * Highest Rank Buy: None
	 */
	
	/*
	 * InstructionData(EntityName,buyOrSell,AgreedFx,currency, Date
	 * instructionDate, Date settlementDate, No of units, pricePeUnit)
	 */
	
	
	public static void main(String args[]) throws ParseException, BaseCustomException{
		
		System.setProperty(StringConstants.ENV_VAR,StringConstants.ENV_VAR_PROP);
		
		ProcessInstruction processInstruction  = new ProcessInstructionImpl();
				
		processInstruction.addNewIntruction("foo", "B", 0.50, "SGP", "01-JAN-2016", "02-JAN-2016", 200, 100.25);
		processInstruction.addNewIntruction("foo", "B", 0.50, "SGP", "01-JAN-2016", "02-JAN-2016", 200, 100.25);
		processInstruction.addNewIntruction("bar", "S", 0.22, "AED", "05-JAN-2016", "07-JAN-2016", 450, 150.5 );
		processInstruction.addNewIntruction("abc", "B", 1   , "SGD", "01-JAN-2016", "07-JAN-2016", 500, 10    );
		processInstruction.addNewIntruction("xyz", "B", 2   , "SGD", "01-JAN-2016", "07-JAN-2016", 50,  20    );
		processInstruction.addNewIntruction("pqr", "B", 0.50, "SGD", "01-JAN-2016", "07-JAN-2016", 200, 30    );
		processInstruction.addNewIntruction("lmn", "S", 0.50, "SGD", "01-JAN-2016", "07-JAN-2016", 200, 10    );
		processInstruction.addNewIntruction("cde", "S", 0.50, "SGD", "01-JAN-2016", "07-JAN-2016", 100, 30    );
		processInstruction.addNewIntruction("jkl", "S", 0.25, "SGD", "01-JAN-2016", "07-JAN-2016", 200, 50    );
		processInstruction.addNewIntruction("ppc", "B", 0.50, "SGD", "01-JAN-2016", "10-JAN-2016", 200, 100.25);
		processInstruction.addNewIntruction("aed", "B", 0.50, "AED", "01-JAN-2016", "10-JAN-2016", 200, 100.25);
		processInstruction.addNewIntruction("aec", "B", 0.50, "AED", "01-JAN-2016", "09-JAN-2016", 200, 100.25);
		processInstruction.addNewIntruction("plp", "B", 0.50, "SGP", "01-JAN-2016", "09-JAN-2016", 200, 100.25);
		
		processInstruction.printReportforAllDays();
	}
}
