package com.jpm.evaluation.data;

import java.util.Date;
import java.util.HashMap;

/**
 * This hashmap has 
 * key: The date which the instruction would be settled.
 * value: The list of instructions for that date.
 * This class is made singleton so that there is always only one instance in the memory to have all the dates
 * 
 * @author Kartic
 *
 */
public class InstructionDateListMap extends HashMap<Date, InstructionDataList> {

	
	private static final long serialVersionUID = 1L;
	private static InstructionDateListMap mapInstance = null;

	private InstructionDateListMap() {

	}

	public static InstructionDateListMap getInstance() {

		if (mapInstance == null) {
			mapInstance= new InstructionDateListMap();
		} 
			
		return mapInstance;
	}

}
