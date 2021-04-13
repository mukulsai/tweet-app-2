package com.tweetapp.Service;

public interface TweetService {

	public void postTweet(String email);

	public void viewMyTweets(String email);

	public void viewAllTweets(String email);

	public void viewAllUsers();

}
