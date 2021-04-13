package com.tweetapp.Service;

public interface UsersService {

	public void getRegister();

	public void getLogin();

	public void resetPassword(String email);

	public void forgetPassword();

	public void logOut(String email);

	public void welcomeMenu();

	public void loggedInMenu(String email);
	
	
}
