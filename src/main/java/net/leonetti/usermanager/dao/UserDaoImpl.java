package net.leonetti.usermanager.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import net.leonetti.usermanager.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();

		user.setCreatedDate(new Date());
		session.persist(user);
		logger.info("User successfully saved. User details: " + user);
	}

	@Override
	public void updateUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(user);
		logger.info("User successfully update. User details: " + user);
	}

	@Override
	public void deleteUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = (User) session.load(User.class, new Integer(id));

		if (user != null) {
			session.delete(user);
		}
		logger.info("User successfully removed. User details: " + user);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> listUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> userList = session.createQuery("from User").list();

		for (User user : userList) {
			logger.info("User list: " + user);
		}

		return userList;
	}

	@Override
	public User getUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = (User) session.load(User.class, new Integer(id));
		logger.info("User successfully loaded. User details: " + user);

		return user;
	}

	@Override
	public List<User> searchByName(String name) {
		Query query= this.sessionFactory.getCurrentSession().createQuery("from User where name like :name");
		query.setParameter("name", "%" + name + "%");
		return query.list();
	}

	/*
	 * @Override public List<User> getAllUsers(String userName) {
	 * 
	 * Session session = sessionFactory.getCurrentSession(); Query q =
	 * session.createQuery("SELECT e FROM user e WHERE e.name = :userName");
	 * q.setParameter("userName", userName); List<User> result = ; return
	 * result; }
	 */

}
