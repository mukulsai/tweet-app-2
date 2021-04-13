package com.tweetapp.Dao.Impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tweetapp.DB.DBconnection;
import com.tweetapp.Dao.TweetDao;
import com.tweetapp.Model.TweetModel;

public class TweetDaoImpl implements TweetDao {

	@Override
	public void createTweet(String email, String tweets) {
		// TODO Auto-generated method stub
		String sql_insert = "Insert into tweets(email,tweets)values(" + "'" + email + "'" + "," + "'" + tweets + "')";// query
																														// String
		//System.out.println(sql_insert);
		try (Connection conn = DBconnection.createNewDBconnection()) {

			Statement stmt = conn.createStatement();
			boolean results = stmt.execute(sql_insert);// executing query

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<String> viewMyTweets(String email) {
		// TODO Auto-generated method stub

		String sql_select_mytweets = "select tweets from tweetapp.tweets where email =" + "'" + email + "'";// query
																											// String
		//System.out.println(sql_select_mytweets);
		try (Connection conn = DBconnection.createNewDBconnection()) {

			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql_select_mytweets);// executing query
			List<String> mytweets = new ArrayList<>();

			while (results.next()) {
				mytweets.add(results.getString("tweets"));
			}
			return mytweets;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<TweetModel> viewAllTweets(String email) {
		String sql_select_alltweets = "select * from tweetapp.tweets where email !=" + "'" + email + "'"
				+ " order by email";// query String
	//	System.out.println(sql_select_alltweets);
		try (Connection conn = DBconnection.createNewDBconnection()) {

			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql_select_alltweets);// executing query
			List<TweetModel> alltweets = new ArrayList<>();

			while (results.next()) {
				TweetModel modelObj = new TweetModel();
				modelObj.setEmail(results.getString("email"));
				modelObj.setTweets(results.getString("tweets"));
				alltweets.add(modelObj);

			}
			return alltweets;

		} catch (SQLException e) {
			e.printStackTrace();
		} // TODO Auto-generated method stub

		return null;
	}
}
