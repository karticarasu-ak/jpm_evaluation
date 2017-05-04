/**
 * 
 */
package com.jpm.evaluation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import com.jpm.evaluation.data.InstructionData;
import com.jpm.evaluation.data.InstructionDataList;
import com.jpm.evaluation.data.InstructionDateListMap;
import com.jpm.evaluation.exception.BaseCustomException;
import com.jpm.evaluation.util.StringConstants;

/**
 * This class is implementations of ProcessInstruction
 * 
 * @author AK
 *
 */
public class ProcessInstructionImpl implements ProcessInstruction {

	@Override
	public void addNewIntruction(String entityName, String buyOrSell, double agreedFx, String currency,
			String instructionDate, String settlementDate, long units, double pricePeUnit) throws BaseCustomException {
          
				
		InstructionData idata = InstructionData.getInstructDataObject(entityName, buyOrSell, agreedFx, currency,
				instructionDate, settlementDate, units, pricePeUnit);

		// Add data to instructionList
		InstructionDataList instructionDataList;
		Date key = idata.getSettlementDate();

		//Generate the Map which has data for all settlement days
		//key: The settlement date
		//values: Array list containing all the instruction data for the date
		
		Map<Date, InstructionDataList> map = InstructionDateListMap.getInstance();

		// Check if list is present for a settlement day: if yes get that list and append data 
		// if not : create a new list and add data to it.
		
			if (map.containsKey(key)) {
				 instructionDataList = map.get(key);
			} else {
				instructionDataList = new InstructionDataList();
			}
			instructionDataList.add(idata);
			map.put(idata.getSettlementDate(), instructionDataList);
	}

	@Override
	public InstructionDataList getDataForThisDay(Date date) {
		return InstructionDateListMap.getInstance().get(date);
	}

	@Override
	public void printReportforAllDays() {

		InstructionDateListMap.getInstance().keySet();

		Iterator<Date> it = InstructionDateListMap.getInstance().keySet().iterator();

		System.out.printf("%75s%n", StringConstants.REP_HEADER);

		System.out.printf("%-15s%-25s%-25s%-25s%-25s", StringConstants.DATE, StringConstants.AMT_INC, StringConstants.AMT_OUT,
				StringConstants.TOP_RANK_INC, StringConstants.TOP_RANK_OUT);

		while (it.hasNext()) {
			Date key = (Date) it.next();
			InstructionDataList list = InstructionDateListMap.getInstance().get(key);
			System.out.println("\n");
			System.out.format("%-15s%-25.2f%-25.2f%-25s%-25s", new SimpleDateFormat(StringConstants.DATE_FORMAT).format(key),
					list.getTotalOutgoing(), list.getTotalIncoming(), getEntityName(list.getTopBuyRankEntity()),
					getEntityName(list.getTopSellRankEntity()));
		}
	}

	
	/* Returns entityname from InstructionData
	 * if InstructionData object is null returns String "None"
	 * 
	 */
	private String getEntityName(InstructionData id) {

		String entityName;

		if (id == null) {
			entityName = "None";
		} else {
			entityName = id.getEntityName();
		}
		return entityName;
	}

}
