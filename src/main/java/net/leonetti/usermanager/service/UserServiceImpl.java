package net.leonetti.usermanager.service;

import net.leonetti.usermanager.dao.UserDao;
import net.leonetti.usermanager.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	@Transactional
	public void addUser(User user) {
		this.userDao.addUser(user);
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		this.userDao.updateUser(user);

	}

	@Override
	@Transactional
	public void deleteUser(int id) {
		this.userDao.deleteUser(id);

	}

	@Override
	@Transactional
	public List<User> listUsers() {
		return this.userDao.listUsers();
	}

	@Override
	@Transactional
	public User getUser(int id) {
		return this.userDao.getUser(id);
	}
	/*
	 * @Override public List<User> getAllUsers(String userName) { return
	 * this.userDao.getAllUsers(userName); }
	 */

	@Override
	@Transactional
	public List<User> searchByName(String name) {
		return this.userDao.searchByName(name);
	}
}
