package ltwebjpa.dao;

import java.util.List;

import ltwebjpa.entity.User;

public interface IUserDAO {
	
	void insert(User user);

	void update(User user);
	
	List<User> findAll();
	
	User findByPhone(String phone);

	User findByUsername(String username);

	boolean checkExistEmail(String email);

	boolean checkExistPhone(String phone);
}
