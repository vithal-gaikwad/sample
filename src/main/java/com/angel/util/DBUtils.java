package com.angel.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.angel.beans.UserBean;
import com.angel.database.DBConnection;
import com.angel.services.beans.UserServiceBean;

public class DBUtils {
	private Logger logger = Logger.getLogger(DBUtils.class);

	/** ===================getData=============================== */

	public UserServiceBean getData(String userid) {

		Session session = DBConnection.getSessionFactory().openSession();

		Transaction tx = null;
		UserBean user = new UserBean();
		UserServiceBean userSer = null;
		try {
			tx = session.beginTransaction();
			List<?> users = session.createQuery(
					"FROM UserBean where userid='" + userid + "' and status=1")
					.list();
			for (Iterator<?> iterator = users.iterator(); iterator.hasNext();) {
				user = (UserBean) iterator.next();
				userSer.setId(user.getId());
				userSer.setUserid(userid);
				userSer.setLat(user.getLat());
				userSer.setLon(user.getLon());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.error("getData exception:", e);
		} finally {
			session.close();
		}
		return userSer;
	}

	/** ===================deleteUser=============================== */

	public boolean deleteUser(String userid) {

		Session session = DBConnection.getSessionFactory().openSession();
		try {
			Transaction transaction = null;
			transaction = session.beginTransaction();
			Query q = session
					.createQuery("FROM UserBean where userid = :userid ");
			q.setParameter("userid", userid);

			UserBean fb = (UserBean) q.list().get(0);
			fb.setStatus(0);
			session.update(fb);
			transaction.commit();
			return true;
		} catch (Exception e) {
			logger.error("ERROR @ deleteUser:");
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}

	/**
	 * ===================getAllUsers===============================
	 *
	 * 
	 * */

	public List<UserServiceBean> getAllUsers() {

		Session session = DBConnection.getSessionFactory().openSession();
		List<UserServiceBean> userSerList = new LinkedList<UserServiceBean>();
		Transaction tx = null;
		UserBean user = new UserBean();		
		try {

			tx = session.beginTransaction();
			List<?> users = session.createQuery("FROM UserBean where status=1")
					.list();
			for (Iterator<?> iterator = users.iterator(); iterator.hasNext();) {
				user = (UserBean) iterator.next();
				UserServiceBean userSer = new UserServiceBean();
				userSer.setId(user.getId());
				userSer.setUserid(user.getUserid());
				userSer.setLat(user.getLat());
				userSer.setLon(user.getLon());
				userSerList.add(userSer);
			}
			tx.commit();

		} catch (Exception e) {
			logger.info("Exception @ isUserExist");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return userSerList;

	}

	/** ===================postData=============================== */
	public int postData(String userid, String lat, String lon) {

		Session session = DBConnection.getSessionFactory().openSession();
		Transaction tx = null;
		int result = 0;

		try {
			tx = session.beginTransaction();
			Query query = session
					.createQuery("UPDATE UserBean set lat = :lat , lon= :lon where userid = :userid and status = :status");

			query.setParameter("lat", lat);
			query.setParameter("lon", lon);
			query.setParameter("userid", userid);
			query.setParameter("status", 1);

			result = query.executeUpdate();
			logger.info("Rows affected: " + result);

			tx.commit();

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			logger.error("setSessionData exception:", e);
		} finally {
			session.close();
		}
		return result;
	}

	/** ===================registerUser=============================== */
	public String registerUser(String userid) {

		// check already user exist
		String isExistStatus = isUserExist(userid);

		if (isExistStatus.equalsIgnoreCase("false")) {
			Session session = DBConnection.getSessionFactory().openSession();
			Transaction tx = null;
			try {

				tx = session.beginTransaction();

				UserBean user = new UserBean();
				user.setUserid(userid);
				user.setLat("");
				user.setLon("");
				user.setStatus(1);
				session.save(user);
				tx.commit();
				isExistStatus = "true";

			} catch (Exception e) {
				if (tx != null)
					tx.rollback();
				logger.error("registerUser exception:");
				e.printStackTrace();
				isExistStatus = "false";

			} finally {
				session.close();
			}
		}

		return isExistStatus;
	}

	/** ===================isUserExist=============================== */
	public String isUserExist(String userid) {
		Session session = DBConnection.getSessionFactory().openSession();
		String status = "false";
		try {
			Query qry = session.createQuery("FROM UserBean where userid='"
					+ userid + "' ");

			List list = qry.list();
			Iterator<UserBean> it = list.iterator();
			if (it.hasNext()) {
				UserBean userBean = it.next();
				String userName = userBean.getUserid();
				if (userName.equalsIgnoreCase(userid))
					status = "true";
			}
		} catch (Exception e) {
			logger.info("Exception @ isUserExist");
			e.printStackTrace();
			status = "false";
		} finally {
			session.close();
		}
		return status;
	}
}
