package com.tweetapp.Service.Impl;

import java.util.List;
import java.util.Scanner;

import com.tweetapp.Dao.Impl.TweetDaoImpl;
import com.tweetapp.Dao.Impl.UsersDaoImpl;
import com.tweetapp.Model.TweetModel;
import com.tweetapp.Model.UsersModel;
import com.tweetapp.Service.TweetService;

public class TweetServiceImpl implements TweetService {

	@Override
	public void postTweet(String email) {
		TweetDaoImpl tweetDaoImpl = new TweetDaoImpl();
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		String tweets = sc.nextLine();
		tweetDaoImpl.createTweet(email, tweets);
		System.out.println("Tweet Posted Succsfully!! :) ");

	}

	@Override
	public void viewMyTweets(String email) {
		TweetDaoImpl tweetDaoImpl = new TweetDaoImpl();

		List<String> myTweets = tweetDaoImpl.viewMyTweets(email);

		for (String myTweet : myTweets) {
			System.out.println(myTweet);
		}

	}

	@Override
	public void viewAllTweets(String email) {
		// TODO Auto-generated method stub
		TweetDaoImpl tweetDaoImpl = new TweetDaoImpl();

		List<TweetModel> allTweets = tweetDaoImpl.viewAllTweets(email);

		for (TweetModel allTweet : allTweets) {
			System.out.println(allTweet.getEmail() + ":" + allTweet.getTweets());
		}

	}

	@Override
	public void viewAllUsers() {
		// TODO Auto-generated method stub
		UsersDaoImpl usersDaoImpl = new UsersDaoImpl();

		List<UsersModel> allUsers = usersDaoImpl.getAllUsers();

		System.out.println("Below Are the List of Registered Users : ");
		for (UsersModel allUser : allUsers) {

			System.out.println(allUser.getFirst_name() + " " + allUser.getLast_name() + "<" + allUser.getEmail() + ">");
		}

	}

}
