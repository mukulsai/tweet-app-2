package com.tweetapp.Dao.Impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tweetapp.DB.DBconnection;
import com.tweetapp.Dao.UsersDao;
import com.tweetapp.Model.UsersModel;

public class UsersDaoImpl implements UsersDao {

	@Override
	public List<UsersModel> getAllUsers() {
		List<UsersModel> usersList = new ArrayList<UsersModel>();
		// TODO Auto-generated method stub
		String sql_select = "Select * From tweetapp.users";// query String
		try (Connection conn = DBconnection.createNewDBconnection()) {

			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql_select);// executing query
			while (results.next()) {

				UsersModel users = new UsersModel();

				users.setFirst_name(results.getString("first_name"));

				users.setLast_name(results.getString("last_name"));
				users.setGender(results.getString("gender"));
				users.setDob(results.getDate("dob"));
				users.setEmail(results.getString("email"));
				users.setPassword(results.getString("password"));
				users.setIsloggedin(results.getBoolean("isloggedin"));

				usersList.add(users);

			}
			return usersList;

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public void createUser(UsersModel usersModel) {
		// TODO Auto-generated method stub

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(usersModel.getDob());

		String sql_insert = "Insert into tweetapp.users (first_name,last_name,gender,dob,email,password,isloggedin) values("
				+ "'" + usersModel.getFirst_name() + "'" + "," + "'" + usersModel.getLast_name() + "'" + "," + "'"
				+ usersModel.getGender() + "'" + "," + "'" + strDate + "'" + "," + "'" + usersModel.getEmail() + "'"
				+ "," + "'" + usersModel.getPassword() + "'" + "," + "false" + ")";
		// System.out.println(sql_insert);
		try (Connection conn = DBconnection.createNewDBconnection()) {

			Statement stmt = conn.createStatement();
			boolean results = stmt.execute(sql_insert);// executing query

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	@Override
	public boolean createLogin(String userName, String password) {
		// TODO Auto-generated method stub
		String sql_select_login = "select * from tweetapp.users where email=" + "'" + userName + "'"
				+ " and password = " + "'" + password + "'";
		// System.out.println(sql_select_login);
		try (Connection conn = DBconnection.createNewDBconnection()) {

			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql_select_login);// executing query

			return results.next();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return false;
	}

	@Override
	public void changePassword(String email, String newpassword, String oldPassword) {
		// TODO Auto-generated method stub
		String sql_update = "update tweetapp.users set password = '" + newpassword + "' where email = '" + email
				+ "' and password = '" + oldPassword + "'";

		// System.out.println(sql_update);
		try (Connection conn = DBconnection.createNewDBconnection()) {

			Statement stmt = conn.createStatement();
			boolean results = stmt.execute(sql_update);// executing query

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	@Override
	public void forgotPassword(String email, String newPassword) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String sql_update = "update tweetapp.users set password = '" + newPassword + "' where email = '" + email + "'";

		// System.out.println(sql_update);
		try (Connection conn = DBconnection.createNewDBconnection()) {

			Statement stmt = conn.createStatement();
			boolean results = stmt.execute(sql_update);// executing query

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	@Override
	public void changelogInStatus(String email, boolean status) {
		// TODO Auto-generated method stub
		String sql_update = "update tweetapp.users set isLoggedIn = " + status + " where email = '" + email + "'";

		// System.out.println(sql_update);
		try (Connection conn = DBconnection.createNewDBconnection()) {

			Statement stmt = conn.createStatement();
			boolean results = stmt.execute(sql_update);// executing query

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	@Override
	public String getLoggedInUser() {
		String sql_select_login_user = "select email from tweetapp.users where isLoggedIn = true LIMIT 1";
		// System.out.println(sql_select_login_user);
		try (Connection conn = DBconnection.createNewDBconnection()) {

			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql_select_login_user);// executing query

			if (results.next()) {
				return results.getString("email");
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return null;
	}

	@Override
	public boolean checkIfUserExists(String email) {
		String sql_select_validation = "select email from tweetapp.users where email = '"+email+"'";
		// System.out.println(sql_select_login_user);
		try (Connection conn = DBconnection.createNewDBconnection()) {

			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql_select_validation);// executing query
return results.next();
		
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return false;
		
	}
	
	

}
