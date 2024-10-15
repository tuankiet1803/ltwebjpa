package ltwebjpa.service.implement;

import java.util.List;
import java.util.Random;

import ltwebjpa.dao.IUserDAO;
import ltwebjpa.dao.implement.UserDAIOImp;
import ltwebjpa.entity.User;
import ltwebjpa.service.IUserService;

public class UserServiceImp implements IUserService{
	IUserDAO userDAO = new UserDAIOImp();
	@Override
	public User login(String username, String password) {
		try {
			User user = userDAO.findByUsername(username);
			if(user != null && password.equals(user.getPassword()))
				return user;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public void insert(User user) {
		userDAO.insert(user);
	}

	@Override
	public void update(User user) {
		userDAO.update(user);
	}

	@Override
	public List<User> findAll() {
		return userDAO.findAll();
	}

	@Override
	public User findByPhone(String phone) {
		return userDAO.findByPhone(phone);
	}

	@Override
	public User findByUsername(String username) {
		return userDAO.findByUsername(username);
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDAO.checkExistEmail(email);
	}

	@Override
	public boolean checkExistPhone(String phone) {
		return userDAO.checkExistPhone(phone);
	}

	@Override
	public String RandomPassword() {
		    int leftLimit = 48; // numeral '0'
		    int rightLimit = 122; // letter 'z'
		    int targetStringLength = 10;
		    Random random = new Random();

		    String generatedString = random.ints(leftLimit, rightLimit + 1)
		      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
		      .limit(targetStringLength)
		      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		      .toString();
		    return generatedString;
	}
}
