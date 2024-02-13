package security;

import org.mindrot.jbcrypt.BCrypt;

/**
 * The {@link SecUtil} class contains methods
 * for encrypting and de-crypting operations.
 */
public class SecUtil {
	
	private SecUtil() {}
	
	public static boolean checkPassword(String inputPasswd, String storedHashedPasswd) {
		return BCrypt.checkpw(inputPasswd, storedHashedPasswd);
	}
	
	public static String hashPassword(String inputPasswd) {
		int workload = 12;
		String salt = BCrypt.gensalt(workload);
		return BCrypt.hashpw(inputPasswd, salt);
	}
}