package comp.Access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import comp.users.User;

public class UserAccess {
	final static Logger logger = Logger.getLogger(UserAccess.class);

	public static int verificareDate(User user) throws SQLException {
		String url = "jdbc:sqlite:D:\\info\\T-Shop\\T-Shop\\TShopDatabase.db";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM Users WHERE username = ? AND password = ? AND rank=?");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getStatus());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}
			logger.info("Datele personale sunt corecte!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Eroare la verificarea datelor personale!");
			e.printStackTrace();
		} finally {
			conn.close();

		}
		return 0;
	}

	public static boolean adaugareDate(User user) throws SQLException {
		String url = "jdbc:sqlite:D:\\info\\T-Shop\\T-Shop\\TShopDatabase.db";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Users VALUES (?, ?, ?, ?)");
			ps.setString(1, null);
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getStatus());
			ps.execute();
			logger.info("Utilizator adaugat cu succes!");
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Eroare la verificarea datelor personale!");
			e.printStackTrace();
		} finally {
			conn.close();

		}
		return false;

	}

}
