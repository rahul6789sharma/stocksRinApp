package com.stocksrin.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.stocksrin.banknifty.option.alog2.OptionDataCollectorTask2;
import org.stocksrin.option.model.OptionModle;

public class AllTestCases {

	@BeforeClass
	public static void oneTimeSetUp() {
		// System.setProperty("AOR_CONFIG_DIR", "C:\\conf");
		System.out.println("@IacRcaAlgoTest - Started");

	}

	@AfterClass
	public static void oneTimeTearDown() {

		System.out.println("@IacRcaAlgoTest - Completed");
	}

	@Test
	public void Test1() {
		OptionDataCollectorTask2 optionDataCollectorTask2 = new OptionDataCollectorTask2();
		optionDataCollectorTask2.run();
	}


	public static List<OptionModle> getData() {
		List<OptionModle> lst = new ArrayList<>();
		OptionModle optionModle = new OptionModle();
		optionModle.setStrike_price("7700");
		optionModle.setC_oi("1823400");
		optionModle.setP_oi("5783025");
		lst.add(optionModle);

		OptionModle optionModle2 = new OptionModle();
		optionModle2.setStrike_price("7800");
		optionModle2.setC_oi("3448575");
		optionModle2.setP_oi("4864125");
		lst.add(optionModle2);

		OptionModle optionModle3 = new OptionModle();
		optionModle3.setStrike_price("7900");
		optionModle3.setC_oi("5367450");
		optionModle3.setP_oi("2559375");
		lst.add(optionModle3);
		return lst;

	}
	
	public static List<OptionModle> getData2() {
		List<OptionModle> lst = new ArrayList<>();
		OptionModle optionModle = new OptionModle();
		optionModle.setStrike_price("7000");
		optionModle.setC_oi("1404300");
		optionModle.setP_oi("4087050");
		lst.add(optionModle);

		OptionModle optionModle2 = new OptionModle();
		optionModle2.setStrike_price("7100");
		optionModle2.setC_oi("335700");
		optionModle2.setP_oi("1029150");
		lst.add(optionModle2);

		OptionModle optionModle3 = new OptionModle();
		optionModle3.setStrike_price("7200");
		optionModle3.setC_oi("482100");
		optionModle3.setP_oi("2977875");
		lst.add(optionModle3);

		OptionModle optionModle4 = new OptionModle();
		optionModle4.setStrike_price("7300");
		optionModle4.setC_oi("422475");
		optionModle4.setP_oi("1975650");
		lst.add(optionModle4);

		OptionModle optionModle5 = new OptionModle();
		optionModle5.setStrike_price("7400");
		optionModle5.setC_oi("963900");
		optionModle5.setP_oi("2336700");
		lst.add(optionModle5);

		OptionModle optionModle6 = new OptionModle();
		optionModle6.setStrike_price("7500");
		optionModle6.setC_oi("999975");
		optionModle6.setP_oi("4548450");
		lst.add(optionModle6);

		OptionModle optionModle7 = new OptionModle();
		optionModle7.setStrike_price("7600");
		optionModle7.setC_oi("785550");
		optionModle7.setP_oi("3690900");
		lst.add(optionModle7);

		OptionModle optionModle8 = new OptionModle();
		optionModle8.setStrike_price("7700");
		optionModle8.setC_oi("1823400");
		optionModle8.setP_oi("5783025");
		lst.add(optionModle8);

		OptionModle optionModle9 = new OptionModle();
		optionModle9.setStrike_price("7800");
		optionModle9.setC_oi("3448575");
		optionModle9.setP_oi("4864125");
		lst.add(optionModle9);

		OptionModle optionModle10 = new OptionModle();
		optionModle10.setStrike_price("7900");
		optionModle10.setC_oi("5367450");
		optionModle10.setP_oi("2559375");
		lst.add(optionModle10);

		OptionModle optionModle11 = new OptionModle();
		optionModle11.setStrike_price("8000");
		optionModle11.setC_oi("6510975");
		optionModle11.setP_oi("1447125");
		lst.add(optionModle11);

		OptionModle optionModle12 = new OptionModle();
		optionModle12.setStrike_price("8100");
		optionModle12.setC_oi("5900325");
		optionModle12.setP_oi("310500");
		lst.add(optionModle12);

		OptionModle optionModle13 = new OptionModle();
		optionModle13.setStrike_price("8200");
		optionModle13.setC_oi("5113350");
		optionModle13.setP_oi("248775");
		lst.add(optionModle13);

		OptionModle optionModle14 = new OptionModle();
		optionModle14.setStrike_price("8300");
		optionModle14.setC_oi("3844500");
		optionModle14.setP_oi("355725");
		lst.add(optionModle14);

		OptionModle optionModle15 = new OptionModle();
		optionModle15.setStrike_price("8400");
		optionModle15.setC_oi("2135625");
		optionModle15.setP_oi("255525");
		lst.add(optionModle15);

		OptionModle optionModle16 = new OptionModle();
		optionModle16.setStrike_price("8500");
		optionModle16.setC_oi("2252250");
		optionModle16.setP_oi("488475");
		lst.add(optionModle16);

		OptionModle optionModle17 = new OptionModle();
		optionModle17.setStrike_price("8600");
		optionModle17.setC_oi("1083750");
		optionModle17.setP_oi("58500");
		lst.add(optionModle17);

		return lst;

	}
}
