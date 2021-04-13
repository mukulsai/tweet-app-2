package com.tweetapp.TweetApplication;

import java.sql.Connection;
import java.util.Scanner;

import com.tweetapp.DB.DBconnection;
import com.tweetapp.Dao.Impl.UsersDaoImpl;
import com.tweetapp.Service.Impl.UsersServiceImpl;

/**
 * Hello world!
 *
 */

public class TwitterApplication {
	static UsersServiceImpl userServiceImpl = new UsersServiceImpl();
	static UsersDaoImpl userDaoImpl = new UsersDaoImpl();

	public static void main(String[] args) {

		Connection conn = DBconnection.createNewDBconnection();
		System.out.println("WELCOME TO TWEET APP");

		String loggedInUserEmail = userDaoImpl.getLoggedInUser();
		if (loggedInUserEmail != null) {
			userServiceImpl.loggedInMenu(loggedInUserEmail);
		} else {
			userServiceImpl.welcomeMenu();
		}
	}

}
