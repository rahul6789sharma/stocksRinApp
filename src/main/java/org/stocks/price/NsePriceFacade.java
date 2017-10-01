package org.stocks.price;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.option.db.HibernateUtil;

public class NsePriceFacade {

	public static List<NsePrice> getAllDataBySymbole(String nseSymbol){
		List<NsePrice> results = new ArrayList<NsePrice>();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(NsePrice.class);
			cr.add(Restrictions.eq("id.nseSymbol", nseSymbol));
			results = cr.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return results;
	}
	
	public static List<NsePrice> getAllData(){
		List<NsePrice> results = new ArrayList<NsePrice>();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(NsePrice.class);
			cr.addOrder(Order.asc("id.nseDate"));
			results = cr.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return results;
	}
	
	public static void saveNsePrice(GoogleData googleData) {
		NsePrice nsePrice = GoogleDataToNsePrice(googleData);
		save(nsePrice);
	}

	private static void save(NsePrice nsePrice) {
		Session session = null;
		Transaction t = null;
		try {
			System.out.println(nsePrice);
			session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			session.save(nsePrice);
			t.commit();// transaction is commited
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	private static NsePrice GoogleDataToNsePrice(GoogleData googleData) {
		NsePrice nsePrice = new NsePrice();
		NsePriceId id = new NsePriceId();

		id.setNseSymbol(googleData.getT());
		id.setNseDate(new Date());

		nsePrice.setId(id);
		nsePrice.setNseChange(googleData.getC());
		nsePrice.setNseHigh(googleData.getHi());
		nsePrice.setNseLow(googleData.getLo());
		nsePrice.setNseOpen(googleData.getOp());
		nsePrice.setPercantageChange(googleData.getCp());
		nsePrice.setNseClose(googleData.getL());
		return nsePrice;
	}
}