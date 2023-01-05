package rs.ac.uns.ftn.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.dataAccess.UserDataAccess;
import rs.ac.uns.ftn.model.User;

@Repository
public class UserRepository {

	@Autowired
	private UserDataAccess userDataAccess;
	
	public User getUserByEmail(String email) {
		return userDataAccess.retrive(email);
	}
	
	public void saveNewUser(User user) {
		userDataAccess.create(user);
	}
}
