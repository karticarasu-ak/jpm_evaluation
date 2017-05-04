package com.jpm.evaluation.util;

public class ErrorMessage {

	private static String errorMessage=null;
	
	public static void  writeErrorMessage(String msg){
		 errorMessage= (errorMessage==null) ? msg: errorMessage +"\n"+msg;
	}
	
	public static String  getErrorMessage(){
		return errorMessage;
	}
	
	public static void  clearErrorMessage(){
		 errorMessage=null;
	}
}
