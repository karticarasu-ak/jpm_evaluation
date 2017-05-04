package com.jpm.evaluation.data;

import java.util.ArrayList;

import com.jpm.evaluation.exception.NullEntityException;

public class InstructionDataList extends ArrayList<InstructionData> {

	private static final long serialVersionUID = 1L;
	private double totalBuySum = 0;
	private double totalSellSum = 0;
	private InstructionData topRankSellEntity;
	private InstructionData topRankBuyEntity;

	/*
	 * Override the add methods to find the top rank and sum
	 * 
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	@Override
	public boolean add(InstructionData id) {
		addToTotalValue(id);
		findTopRank(id);
		return super.add(id);

	}

	/**
	 * @param id
	 */
	protected void addToTotalValue(InstructionData id) {

		if (id.getBuyOrSell().equals(InstructionType.Buy)) {
			totalBuySum = totalBuySum + id.getUsdAmount();
		}
		else if (id.getBuyOrSell().equals(InstructionType.Sell)) {
			totalSellSum = totalSellSum + id.getUsdAmount();
		}

	}

	protected void findTopRank(InstructionData id) {

		if (id.getBuyOrSell().equals(InstructionType.Buy)) {

			if (topRankBuyEntity == null || topRankBuyEntity.getUsdAmount() < id.getUsdAmount()) {
				topRankBuyEntity = id;
			}

		}
		else if (id.getBuyOrSell().equals(InstructionType.Sell)) {
			if (topRankSellEntity == null || topRankSellEntity.getUsdAmount() < id.getUsdAmount()) {
				topRankSellEntity = id;
			}
		}
	}

	public InstructionData getTopBuyRankEntity() /*throws NullEntityException*/ {
		
	/*	if (topRankBuyEntity == null) {
			throw new NullEntityException("NO BUY RANK");
		}*/
		
		return topRankBuyEntity;
	}

	public InstructionData getTopSellRankEntity() /*throws NullEntityException*/ {
	/*	if (topRankSellEntity == null) {
			throw new NullEntityException("NO SELL RANK");
		}
		*/
		return topRankSellEntity;
	}

	public double getTotalIncoming() {
		return totalSellSum;
	}

	public double getTotalOutgoing() {
		return totalBuySum;
	}

}
