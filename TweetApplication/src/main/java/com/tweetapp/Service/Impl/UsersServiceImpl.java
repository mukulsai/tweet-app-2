package com.tweetapp.Service.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.tweetapp.Dao.Impl.UsersDaoImpl;
import com.tweetapp.Model.UsersModel;
import com.tweetapp.Service.UsersService;

public class UsersServiceImpl implements UsersService {

	UsersDaoImpl usersDaoImpl = new UsersDaoImpl();
	TweetServiceImpl tweetServiceImpl = new TweetServiceImpl();

	@Override
	public void getRegister() {
		// TODO Auto-generated method stub
		try {

			Scanner sc = new Scanner(System.in);
			UsersModel userDetails = new UsersModel();
			System.out.println("Enter your First Name");
			String firstName = this.takeRequiredInput("First Name");
			
			userDetails.setFirst_name(firstName);
		
			System.out.println("Enter your Last Name");
			String lastName = sc.nextLine();
			userDetails.setLast_name(lastName);
			
			System.out.println("Enter your  Gender 'M' for Male or 'F' for Female");
			String gender="";
			boolean isGenderNotValid = false;
			do
			{
				gender = this.takeRequiredInput("Gender");
				
				if(gender.equals("M") || gender.equals("F") || gender.equals("m")||gender.equals("f"))
				{
					isGenderNotValid = false;
					
				}
				else
				{
					isGenderNotValid = true;
					System.out.println("Entered Wrong Entry :( please Enter 'M' for Male or 'F' for Female");
				}
				
			}while(isGenderNotValid);
			
				userDetails.setGender(gender);
				
			System.out.println("Enter your email");
			String email = "";
			boolean isEmailNotValid = false;
			
			do
			{
				email  =this.takeRequiredInput("Email");
				
				String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{​​​​​​​​|}​​​​​​​​~^.-]+@[a-zA-Z0-9.-]+$";

				if(email.matches(regex))
				{
				
					isEmailNotValid = false;
				}
				else
				{
					isEmailNotValid = true;
					System.out.println("Entered Wrong Entry :( please Enter Valid Email");
				}
				
				
			}while(isEmailNotValid);
			 
				userDetails.setEmail(email);
				
			System.out.println("Enter your Password");
			String password  =this.takeRequiredInput("Password");
			userDetails.setPassword(password);
			
			System.out.println("Enter your Date Of Birth in yyyy-MM-dd format");
			String sDate1 = sc.nextLine();

			Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
			userDetails.setDob(dob);

			System.out.println("Enter Y to Submit or Any Other key to GoBack");
			String submit = sc.nextLine();

			if (submit.equals("Y") || submit.equals("y")) {
				boolean isEmailRegistered = false;

				do {
					isEmailRegistered = usersDaoImpl.checkIfUserExists(email);
					if (isEmailRegistered) {
						System.out.println("User Already Registered :( ");
						System.out.println("Please Enter New email below");
						email = sc.nextLine();
						userDetails.setEmail(email);

					} else {
						usersDaoImpl.createUser(userDetails);
						System.out.println("Registered Succesfully!!");
					}
				} while (isEmailRegistered);

			} else {

				this.welcomeMenu();

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	@Override
	public void getLogin() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter your Login Email");
		String email = "";
		boolean isEmailNotValid = false;
		
		do
		{
			email  =this.takeRequiredInput("Email");
			
			String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{​​​​​​​​|}​​​​​​​​~^.-]+@[a-zA-Z0-9.-]+$";

			if(email.matches(regex))
			{
			
				isEmailNotValid = false;
			}
			else
			{
				isEmailNotValid = true;
				System.out.println("Entered Wrong Entry :( please Enter Valid Email");
			}
			
			
		}while(isEmailNotValid);
		 
		
		System.out.println("Enter you Password");
		String password = this.takeRequiredInput("Password");

		boolean isLoggedIn = usersDaoImpl.createLogin(email, password);

		if (isLoggedIn) {

			loggedInMenu(email);

		} else {
			System.out.println("Invalid Credentials");
		}

	}

	@Override
	public void resetPassword(String email) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter Your Old Password");
		String oldPassword = sc.nextLine();
		System.out.println("Enter Your New Password");
		String newpassword = sc.nextLine();
		usersDaoImpl.changePassword(email, newpassword, oldPassword);
		System.out.println("Password Changed Successfully");

	}

	@Override
	public void forgetPassword() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Your Email");
		String email = sc.nextLine();

		System.out.println("Enter Your New Password");
		String newPassword = sc.nextLine();
		usersDaoImpl.forgotPassword(email, newPassword);
		System.out.println("Password Set Succesfully");
	}

	@Override
	public void logOut(String email) {
		// TODO Auto-generated method stub

		usersDaoImpl.changelogInStatus(email, false);
		this.welcomeMenu();
	}

	@Override
	public void welcomeMenu() {
		int menu;
		Scanner sc = new Scanner(System.in);
		do {
			String mainMenu = ("Select a choice from the menu: \n" + "1.Register \n" + "2.Login \n"
					+ "3.Forgot Password \n" + "4.Exit");
			System.out.println(mainMenu);

			menu = sc.nextInt();

			switch (menu) {

			case 1:
				System.out.println("Register");
				this.getRegister();

				break;

			case 2:
				System.out.println("Login");
				this.getLogin();
				break;
			case 3:
				System.out.println("Forgot Password");
				this.forgetPassword();
				break;
			case 4:
				System.exit(0);
			default:
				System.out.println("invalid entry");
				break;
			}
		} while (true);

	}

	@Override
	public void loggedInMenu(String email) {

		System.out.println("Welcome " + email + " :) ");
		usersDaoImpl.changelogInStatus(email, true);
		do {
			String loggedUser = ("Select a choice from the below: \n" + "1.Post a tweet \n" + "2.View my tweets \n"
					+ "3.View all tweets \n" + "4.View All Users \n" + "5.Reset Password \n" + "6.Logout \n"
					+ "7.Exit");

			System.out.println(loggedUser);
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();

			switch (choice) {

			case 1:
				System.out.println("Post a tweet");
				tweetServiceImpl.postTweet(email);
				break;

			case 2:
				System.out.println("View my tweets");
				tweetServiceImpl.viewMyTweets(email);
				break;
			case 3:
				System.out.println("View all tweets");
				tweetServiceImpl.viewAllTweets(email);
				break;
			case 4:
				System.out.println("View All Users");
				tweetServiceImpl.viewAllUsers();
				break;
			case 5:
				System.out.println("Reset Password");
				this.resetPassword(email);
				break;
			case 6:
				System.out.println("Logout");
				this.logOut(email);
				break;
			case 7:
				System.exit(0);
			default:
				System.out.println("invalid entry");
				break;
			}
		} while (true);
		
		
	}
	
	public String takeRequiredInput(String keyName)
	{
		Scanner sc = new Scanner(System.in);
		boolean isInputNotValid = false;
		String value = "";
		do {
			value = sc.nextLine();
			if(value.isEmpty())
			{
				isInputNotValid = true;
			System.out.println(keyName+" cannot be Blank :( ");
			System.out.println("Please Enter your "+keyName);
			}
			else
			{
				isInputNotValid = false;
			}
		} while (isInputNotValid);
		return value;
	}
	
}
