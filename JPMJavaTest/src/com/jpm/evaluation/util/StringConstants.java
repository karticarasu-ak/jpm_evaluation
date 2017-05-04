package com.jpm.evaluation.util;

public class StringConstants {

	/* Date format */
	public static final String DATE_FORMAT = "dd-MMM-yyyy";
	
	public static final String ENV_VAR= "env";
	
	public static final String ENV_VAR_PROP= "test";
	
	
	//public static final String TEST_DATE= "01-JAN-2016";
	
	public static final String TEST_DATE= "31-DEC-2015";
	
	

	/* Error Messages */
	public static final String BUYSELLERROR = "The variable for buy or Sell should be either B or S ";
	public static final String AGREED_FX_ERROR = "The Agreed FX can never be 0 ";
	public static final String CURRENCY_ERROR = "The currency should be of a valid type";
	public static final String UNIT_ERROR = "The unit can never be 0 ";
	public static final String PRICE_ERROR = "The price per unit can never be 0 ";
	public static final String INSTRUCTION_DATE_ERROR = "The instruction date can never be in past";
	public static final String SETTELMENT_DATE_ERROR = "The setlement date can never be in past";
	public static final String INSTRUCTION_DATE_FORMAT_ERROR = "The instruction date format should be in " + DATE_FORMAT;
	public static final String SETTELMENT_DATE_FORMAT_ERROR = "The settlement date format should be in " + DATE_FORMAT;
	
	
	/* Report Messages */
	
	public static final String REP_HEADER = "****   THE REPORT FOR ALL DAYS     ***** ";
	public static final String DATE = "DATE" ;
	public static final String AMT_INC ="Amount Incoming" ;
	public static final String AMT_OUT ="Amount Outgoing" ;
	public static final String TOP_RANK_INC ="TOP RANK SELLING ENTITY" ;
	public static final String TOP_RANK_OUT ="TOP RANK BUYING ENTITY";
			
			

	
	
	

}
