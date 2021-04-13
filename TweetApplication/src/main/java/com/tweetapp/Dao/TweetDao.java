package com.tweetapp.Dao;

import java.util.List;

import com.tweetapp.Model.TweetModel;

public interface TweetDao {

	public void createTweet(String email, String tweets);

	public List<String> viewMyTweets(String email);

	public List<TweetModel> viewAllTweets(String email);

}
