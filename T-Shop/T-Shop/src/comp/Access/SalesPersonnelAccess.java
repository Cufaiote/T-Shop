package comp.Access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import comp.users.SalesPersonnel;

public class SalesPersonnelAccess {
	final static Logger logger = Logger.getLogger(SalesPersonnelAccess.class);

	public static List<SalesPersonnel> getAdmins() throws SQLException {
		List<SalesPersonnel> admins = new ArrayList<>();
		String url = "jdbc:sqlite:D:\\info\\T-Shop\\T-Shop\\TShopDatabase.db";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Users WHERE rank = ?");
			ps.setString(1, "admin1");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SalesPersonnel adminAux = new SalesPersonnel();
				adminAux.setUserId(rs.getInt(1));
				adminAux.setUsername(rs.getString(2));
				adminAux.setPassword(rs.getString(3));
				adminAux.setStatus(rs.getString(4));
				admins.add(adminAux);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			conn.close();
			logger.info("Admins retuned");
		}
		return admins;
	}

}

