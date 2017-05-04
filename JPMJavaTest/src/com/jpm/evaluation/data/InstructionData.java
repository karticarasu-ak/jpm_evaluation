package com.jpm.evaluation.data;

import java.text.ParseException;
import java.util.Date;

import com.jpm.evaluation.exception.BaseCustomException;
import com.jpm.evaluation.util.ErrorMessage;
import com.jpm.evaluation.util.PerformDataValidation;
import com.jpm.evaluation.util.PerformDataValidationImpl;

/**This class holds data for an Instruction and performs basic operations like calculating the
 * US Amount
 * 
 * @author AK
 *
 */
public class InstructionData {

	private String entityName;
	private String buyOrSell;
	private double agreedFx;
	private String currency;
	private Date instructionDate;
	private Date settlementDate;
	private long units;
	private double pricePeUnit;
	private double usdAmount;

	/**
	 * Method to get the USD amount
	 * @return usd amount
	 */
	public double getUsdAmount() {
		return usdAmount;
	}

	/**
	 * Method calculates the US Amount
	 * 
	 */
	private void calculateUsdAmount() {
		double usdAmount = pricePeUnit * units * agreedFx;

		this.usdAmount = usdAmount;
	}

	private InstructionData(String entityName, String buyOrSell, double agreedFx, String currency, Date instructionDate,
			Date settlementDate, long units, double pricePeUnit) {

		this.entityName = entityName;
		this.buyOrSell = buyOrSell;
		this.agreedFx = agreedFx;
		this.currency = currency;
		this.instructionDate = instructionDate;
		this.settlementDate = settlementDate;
		this.units = units;
		this.pricePeUnit = pricePeUnit;

		this.calculateUsdAmount();
	}

	/**
	 * Method returns Entity Name
	 * @return Entity Name
	 */
	public String getEntityName() {
		return entityName;
	}

	/**
	 * Method returns buy or sell
	 * @return buy or sell
	 */
	public InstructionType getBuyOrSell() {
		if (buyOrSell.equals("B")) {
			return InstructionType.Buy;
		} else
			return InstructionType.Sell;

	}

	/**
	 * Method returns agreed fx
	 * @return agreed fx
	 */
	public double getAgreedFx() {
		return agreedFx;
	}

	/**
	 * Method returns currency
	 * @return currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Method returns instruction Date
	 * @return
	 */
	public Date getInstructionDate() {
		return instructionDate;
	}

	/**
	 * Method returns settlement date
	 * @return settlement date
	 */
	public Date getSettlementDate() {
		return settlementDate;
	}

	/**
	 * Method returns units
	 * @return units
	 */
	public long getUnits() {
		return units;
	}

	/**
	 * Method returns price per unit
	 * @return price per unit
	 */
	public double getPricePeUnit() {
		return pricePeUnit;
	}

	/**
	 * Method builds the object for instruction data using all the parameters provided.
	 * 
	 * @param entityName
	 * @param buyOrSell
	 * @param agreedFx
	 * @param currency
	 * @param instructionDate
	 * @param settlementDate
	 * @param units
	 * @param pricePeUnit
	 * @return Instruction Data object
	 * @throws BaseCustomException
	 */
	public static InstructionData getInstructDataObject(String entityName, String buyOrSell, double agreedFx,
			String currency, String instructionDate, String settlementDate, long units, double pricePeUnit)
			throws BaseCustomException {

		Date settlementDateFormat = null;
		Date instructionDateFormat = null;

		// Perform validation on data
		PerformDataValidation pf = new PerformDataValidationImpl();

		try {
			settlementDateFormat = pf.getSettlementDate(settlementDate, currency);
			instructionDateFormat = pf.getInstructionDate(settlementDate);
		} catch (ParseException p) {
			throw new BaseCustomException(ErrorMessage.getErrorMessage());
		}

		boolean noError = pf.verifyUnits(buyOrSell, agreedFx, currency, instructionDateFormat, settlementDateFormat,
				units, pricePeUnit);

		if (!noError) {
			throw new BaseCustomException(ErrorMessage.getErrorMessage());
		}

		InstructionData InstructionData = new InstructionData(entityName, buyOrSell, agreedFx, currency,
				instructionDateFormat, settlementDateFormat, units, pricePeUnit);

		return InstructionData;
	}

}
