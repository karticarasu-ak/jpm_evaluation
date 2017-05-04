package com.jpm.evaluation.test;

import java.text.ParseException;

import org.junit.Before;

import com.jpm.evaluation.data.InstructionData;
import com.jpm.evaluation.data.InstructionDataList;
import com.jpm.evaluation.exception.BaseCustomException;
import com.jpm.evaluation.util.StringConstants;

/**
 * @author AK
 *
 *Doesnt run any test.All the steps common to all tests can be added here
 */
public class BaseTestClass {

	protected InstructionDataList iList = null;
	protected InstructionDataList iListOnSeventh = null;
  
	
	
	
	
	
	
	@Before
	public void buildTestData() throws ParseException, BaseCustomException {
		 System.setProperty(StringConstants.ENV_VAR,StringConstants.ENV_VAR_PROP);

		 /*This is test data
		 * Test 1:
		 * SettlementDate: 07-Jan-2016: Thurday
		 * 1-instruction from abc,xyz,lmn,cde,jkl
		 * Total: Incoming amount: 
		 * Total: Outgoing amount: 
		 * Highest Rank:
		 * 
		 * Test 2:
		 * SettlementDate: 11-Jan-2016: Monday
		 * 1-ppc --On 10th should be moved to 11th
		 * 1-plp - On 9th should be moved to 11th
		 * 1 aac --On 11th itself
		 * 
		 * Test 3:
		 * 
		 * SettlementDate: 10-Jan-2016: Sunday
		 * 1 -aed
		 * 1 -aec should be moved to 10th
		 */
		
		/*
		 * InstructionData(EntityName,buyOrSell,AgreedFx,currency, Date
		 * instructionDate, Date settlementDate, No of units, pricePeUnit)
		 */
		
		
		InstructionData id1 = InstructionData.getInstructDataObject("foo", "B", 0.50, "SGP","01-JAN-2016","02-JAN-2016", 200, 100.25);
		InstructionData id2 = InstructionData.getInstructDataObject("bar", "S", 0.22, "AED","05-JAN-2016","07-JAN-2016", 450, 150.5);
		InstructionData id3 = InstructionData.getInstructDataObject("abc", "B", 1, "SGD","01-JAN-2016","07-JAN-2016", 500, 10);
		InstructionData id4 = InstructionData.getInstructDataObject("xyz", "B", 2, "SGD","01-JAN-2016","07-JAN-2016", 50, 20);
		InstructionData id5 = InstructionData.getInstructDataObject("pqr", "B", 0.50, "SGD","01-JAN-2016","07-JAN-2016", 200, 30);
		InstructionData id6 = InstructionData.getInstructDataObject("lmn", "S", 0.50, "SGD","01-JAN-2016","07-JAN-2016", 200, 10);
		InstructionData id7 = InstructionData.getInstructDataObject("cde", "S", 0.50, "SGD","01-JAN-2016","07-JAN-2016", 100, 30);
		InstructionData id8 = InstructionData.getInstructDataObject("jkl", "S", 0.25, "SGD","01-JAN-2016","07-JAN-2016", 200, 50);
		InstructionData id9 = InstructionData.getInstructDataObject("ppc", "B", 0.50, "SGD","01-JAN-2016",	"10-JAN-2016", 200, 100.25);
		InstructionData id10 = InstructionData.getInstructDataObject("aed", "B", 0.50, "AED","01-JAN-2016",	"10-JAN-2016", 200, 100.25);
		InstructionData id11 = InstructionData.getInstructDataObject("aec", "B", 0.50, "AED","01-JAN-2016",	"09-JAN-2016", 200, 100.25);
		

	    iList = new InstructionDataList();
		iList.add(id1);
		iList.add(id2);
		iList.add(id3);
		iList.add(id4);
		iList.add(id5);
		iList.add(id6);
		iList.add(id7);
		iList.add(id8);
		iList.add(id9);
		iList.add(id10);
		iList.add(id11);
		
		iListOnSeventh = new InstructionDataList();
		iListOnSeventh.add(id3);
		iListOnSeventh.add(id4);
		iListOnSeventh.add(id5);
		iListOnSeventh.add(id6);
		iListOnSeventh.add(id7);
		iListOnSeventh.add(id8);
	}

}
