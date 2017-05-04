package com.jpm.evaluation.data;

import java.util.ArrayList;

/**
 * This class holds all the instruction data for a particular day 
 * When a new instruction is added total buy sum and total sell Sum is calculated along 
 * with ranks for that day
 * 
 * @author AK
 *
 */
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
	 * Add total value for Sum and Buy instructions
	 * 
	 * @param id
	 */
	protected void addToTotalValue(InstructionData id) {

		if ( id.getBuyOrSell().equals(InstructionType.Buy) ) {
			totalBuySum = totalBuySum + id.getUsdAmount();
		}
		else if ( id.getBuyOrSell().equals(InstructionType.Sell) ) {
			totalSellSum = totalSellSum + id.getUsdAmount();
		}
	}

	/**
	 * Find the top rank for an Instruction for BUY and SELL
	 * 
	 * @param id
	 */
	protected void findTopRank(InstructionData id) {

		if ( id.getBuyOrSell().equals(InstructionType.Buy) ) {

			if ( topRankBuyEntity == null || topRankBuyEntity.getUsdAmount() < id.getUsdAmount() ) {
				topRankBuyEntity = id;
			}
		}
		else if ( id.getBuyOrSell().equals(InstructionType.Sell) ) {
			if ( topRankSellEntity == null || topRankSellEntity.getUsdAmount() < id.getUsdAmount() ) {
				topRankSellEntity = id;
			}
		}
	}

	/**
	 * Method returns top buy rank
	 * @return
	 */
	public InstructionData getTopBuyRankEntity() {
		return topRankBuyEntity;
	}

	/**
	 * Method returns top sell rank
	 * @return
	 */
	public InstructionData getTopSellRankEntity() {
		return topRankSellEntity;
	}

	/**
	 * Method returns total sum of incoming values
	 * @return
	 */
	public double getTotalIncoming() {
		return totalSellSum;
	}

	/**
	 * Method returns total sum of outgoing values
	 * @return
	 */
	public double getTotalOutgoing() {
		return totalBuySum;
	}
}
