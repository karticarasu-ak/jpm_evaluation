package com.jpm.evaluation.test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Test;

import com.jpm.evaluation.data.InstructionData;

public class InstructionDataListTest extends BaseTestClass {

	@Test
	public void usCurrencyConvertiontest() throws ParseException {

		// Check the US conversation for couple of entities
		InstructionData iData = iList.get(0);
		assertEquals(iData.getEntityName(), "foo");
		assertEquals("UsdAmountCheckFailed1", iData.getUsdAmount(), 10025.0, 0);
		InstructionData iData1 = iList.get(1);
		assertEquals(iData1.getEntityName(), "bar");
		assertEquals("UsdAmountCheckFailed2", iData1.getUsdAmount(), 14899.5, 0);
	}

	@Test
	public void topRankingEntitiesTest() throws ParseException {

		// Check the top ranking entities for a day
		assertEquals(iListOnSeventh.getTopBuyRankEntity().getEntityName(), "abc");
		assertEquals(iListOnSeventh.getTopSellRankEntity().getEntityName(), "jkl");
	}

	@Test
	public void totalSumForDayTest() throws ParseException {

		// Check total outgoing and incoming for day
		assertEquals(iListOnSeventh.getTotalOutgoing(), 10000, 0);
		assertEquals(iListOnSeventh.getTotalIncoming(), 5000, 0);
	}
}
