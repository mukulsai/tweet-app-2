package com.tweetapp.Dao;

import java.util.List;

import com.tweetapp.Model.UsersModel;

public interface UsersDao {

	public List<UsersModel> getAllUsers();

	public void createUser(UsersModel usersModel);

	public boolean createLogin(String userName, String password);

	public void changePassword(String email, String newpassword, String oldPassword);

	public void forgotPassword(String email, String newPassword);

	public void changelogInStatus(String email, boolean status);

	public String getLoggedInUser();
	
	public boolean checkIfUserExists(String email);
}
