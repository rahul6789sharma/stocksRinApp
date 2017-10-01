package org.option.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.smarttrade.options.utils.DateUtils;

public class USDINRDbFacade {

	private static USDINRDbFacade instance = new USDINRDbFacade();

	private USDINRDbFacade() {
	}

	public static USDINRDbFacade getInstance() {
		return instance;
	}

	public void save(Date expiry, float strickPrice, int ceOIValue, int peOIValue) {
		Usdinr usdinr = new Usdinr();
		UsdinrId usdinrId = new UsdinrId();
		usdinrId.setDate(new Date());
		usdinrId.setExpiry(expiry);
		usdinrId.setStrick(strickPrice);

		usdinr.setId(usdinrId);
		usdinr.setCeOiValue(ceOIValue);
		usdinr.setPeOiValue(peOIValue);
		Session session = null;
		Transaction t = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			
			System.out.println("SessionFactory isClosed:" + sessionFactory.isClosed());
			session = HibernateUtil.getSessionFactory().openSession();
			
			System.out.println("Session connection state  isConnected:" + session.isConnected());
			System.out.println("Session connection state :" + session.isOpen());
			t = session.beginTransaction();
			session.save(usdinr);
			t.commit();// transaction is commited
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	public List<Usdinr> getDataByExpiry(String expiryDate) {
		List<Usdinr> results = new ArrayList<Usdinr>();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(Usdinr.class);
			Date expiry = DateUtils.getDateFromString(expiryDate);
			cr.add(Restrictions.eq("id.expiry", expiry));
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

	public List<Usdinr> getDataByExpiryAndStrick(String expiryDate, float strick ) {
		List<Usdinr> results = new ArrayList<Usdinr>();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(Usdinr.class);
			cr.add(Restrictions.eq("id.strick", strick));
			Date expiry = DateUtils.getDateFromString(expiryDate);
			cr.add(Restrictions.eq("id.expiry", expiry));
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

}
