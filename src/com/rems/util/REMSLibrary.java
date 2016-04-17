package com.rems.util;

import java.sql.Connection;
import org.apache.log4j.Logger;

import com.rems.connection.*;

public class REMSLibrary {
	static Logger logger = Logger.getLogger(REMSLibrary.class);

	  /** @method : getDBConnection
		* @inputs : None
		* @return : Connection Object
		* @detail : Takes one connection from the available pool and returns to the calling class.
		* */
	public static Connection getDBConnection() throws Exception {
		try {
			Connection con = null;
			ConnectionPool conPool = null;

			conPool = PoolBean.getPoolObject();
			con = conPool.getConnection();

			return con;
		} catch (Exception e) {
			logger.error("Exception in getAdminPoolConnection of REMS Library",
					e);
			return null;
		} finally {

		}
	}
	/** @method : checkNull
	  * @inputs : String
	  * @return : String
	  * @detail : This method validates a string for null.
	  * */
	public static String checkNull(String stringToCheck) throws Exception {

		if (stringToCheck == null || stringToCheck.equals("(NULL)"))
			return "";
		else {
			stringToCheck = stringToCheck.trim();
			return stringToCheck;
		}

	}

}
