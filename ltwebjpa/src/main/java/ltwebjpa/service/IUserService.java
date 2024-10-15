package ltwebjpa.service;

import java.util.List;
import java.util.Random;

import ltwebjpa.entity.User;

public interface IUserService {
	User login(String username, String password);

	void insert(User user);

	void update(User user);

	List<User> findAll();

	User findByPhone(String phone);

	User findByUsername(String username);

	boolean checkExistEmail(String email);

	boolean checkExistPhone(String phone);
	
	public String RandomPassword();
}
