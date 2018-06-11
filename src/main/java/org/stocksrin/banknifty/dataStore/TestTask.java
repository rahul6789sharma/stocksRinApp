package org.stocksrin.banknifty.dataStore;

import java.util.TimerTask;

import org.stocksrin.email.SendEmail;
import org.stocksrin.utils.LoggerSysOut;

public class TestTask extends TimerTask {

	private String name;

	public TestTask(String name) {

		this.name = name;
	}

	@Override
	public void run() {
		LoggerSysOut.print("TestTask started " +name);
		// clear data in morning
	    SendEmail.sentMail("Morning Market started", "name :" + name);

	}

}
