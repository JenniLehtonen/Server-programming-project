package data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 
 * @author Jenni
 *
 */
public class LoginData {
	/**
	 * This method checks if the password provided by the user is correct
	 * @param password1
	 * @param password2
	 * @return
	 */
	public static Boolean CheckPasswords (String password1, String password2) {
		boolean passwordCorrect = false;
		
		if(password1.equals(password2)) {
			System.out.println("Password correct");
			passwordCorrect = true;
		} else {
			System.out.println("Password incorrect");
			passwordCorrect = false;
		}
		
		return passwordCorrect;
		
	}
	
	/**
	 * This method crypts the admin's password and the password that the user provides
	 * @param str
	 * @return
	 */
	public static String crypt(String str) {
	      if (str == null || str.length() == 0) {
	          throw new IllegalArgumentException("String to encript cannot be null or zero length");
	      }

	      MessageDigest digester;
	      try {
	          digester = MessageDigest.getInstance("MD5");

	          digester.update(str.getBytes());
	          byte[] hash = digester.digest();
	          StringBuffer hexString = new StringBuffer();
	          for (int i = 0; i < hash.length; i++) {
	              if ((0xff & hash[i]) < 0x10) {
	                  hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
	              } else {
	                  hexString.append(Integer.toHexString(0xFF & hash[i]));
	              }
	          }
	          return hexString.toString();
	      } catch (NoSuchAlgorithmException e) {
	          e.printStackTrace();
	      }
	      return "";
	  }

}
