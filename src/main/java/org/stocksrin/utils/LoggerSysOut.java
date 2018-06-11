package org.stocksrin.utils;

import java.util.Date;

public class LoggerSysOut {
/*
	public static void print(Set<String> msg) {

		try {
			String str = DateUtils.dateToString(new Date(), APPConstant.DATEFORMATE_Logger);
			System.out.println(str + " " + msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void print(List<String> msg) {

		try {
			String str = DateUtils.dateToString(new Date(), APPConstant.DATEFORMATE_Logger);
			System.out.println(str + " " + msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void print(Date msg) {

		try {
			String str = DateUtils.dateToString(new Date(), APPConstant.DATEFORMATE_Logger);
			System.out.println(str + " " + msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void print(long msg) {

		try {
			String str = DateUtils.dateToString(new Date(), APPConstant.DATEFORMATE_Logger);
			System.out.println(str + " " + msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void print(int msg) {

		try {
			String str = DateUtils.dateToString(new Date(), APPConstant.DATEFORMATE_Logger);
			System.out.println(str + " " + msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void print(StringBuilder msg) {

		try {
			String str = DateUtils.dateToString(new Date(), APPConstant.DATEFORMATE_Logger);
			System.out.println(str + " " + msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void print(String msg) {

		try {
			String str = DateUtils.dateToString(new Date(), APPConstant.DATEFORMATE_Logger);
			System.out.println(str + " " + msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/
	public static void print(Object msg) {

		try {
			String str = DateUtils.dateToString(new Date(), APPConstant.DATEFORMATE_Logger);
			System.out.println(str + " - " + msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

	public static void main(String[] args) {
		LoggerSysOut.print("Pring here ");
	}

}
