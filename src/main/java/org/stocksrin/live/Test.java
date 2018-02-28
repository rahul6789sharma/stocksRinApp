package org.stocksrin.live;

import java.util.Calendar;
import java.util.TimeZone;

public class Test {

	public static void main(String[] args) {
		isClearTime();
	}

	
	public static void isClearTime() {
	
		Calendar morningTime = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		morningTime.set(Calendar.HOUR_OF_DAY, 8);
		morningTime.set(Calendar.MINUTE, 0);
		morningTime.set(Calendar.SECOND, 0);

		Calendar eveningTime = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		eveningTime.set(Calendar.HOUR_OF_DAY, 8);
		eveningTime.set(Calendar.MINUTE, 30);
		eveningTime.set(Calendar.SECOND, 0);

		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("IST"));

		now.set(Calendar.HOUR_OF_DAY, 8);
		now.set(Calendar.MINUTE, 30);
		now.set(Calendar.SECOND, 0);

		System.out.println(now.getTime().after(morningTime.getTime()));

		if (now.getTime().after(morningTime.getTime()) && now.getTime().before(eveningTime.getTime())) {
			System.out.println("IN Between");
		}else{
			System.out.println("Not IN Betwee");
		}

	}

}
