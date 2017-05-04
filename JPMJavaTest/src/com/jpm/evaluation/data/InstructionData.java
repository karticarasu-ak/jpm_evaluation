package com.jpm.evaluation.data;

import java.text.ParseException;
import java.util.Date;

import com.jpm.evaluation.exception.BaseCustomException;
import com.jpm.evaluation.util.ErrorMessage;
import com.jpm.evaluation.util.PerformDataValidation;
import com.jpm.evaluation.util.PerformDataValidationImpl;

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

	public double getUsdAmount() {
		return usdAmount;
	}

	public void calculateUsdAmount() {
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

	public String getEntityName() {
		return entityName;
	}

	public InstructionType getBuyOrSell() {
		if (buyOrSell.equals("B")) {
			return InstructionType.Buy;
		} else
			return InstructionType.Sell;

	}

	public double getAgreedFx() {
		return agreedFx;
	}

	public String getCurrency() {
		return currency;
	}

	public Date getInstructionDate() {
		return instructionDate;
	}

	public Date getSettlementDate() {
		return settlementDate;
	}

	public long getUnits() {
		return units;
	}

	public double getPricePeUnit() {
		return pricePeUnit;
	}

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
