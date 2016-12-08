package jsf.util;

import java.security.MessageDigest;




/**
 * Use this class to change the password stored in the databae from one that is
 * visible as plain text (a security threat) to one that is "hashed". Hashing is
 * a one-way encryption system. Hashes can be generated but cannot be reverse
 * engineered, which is why they are called one-way hashes. Use this class to
 * generate a hashed password, based on the original plain text version, using
 * SHA-256, which is a superior hashing algorithm. Then copy the output from the
 * console and use it to replace what you have in your database.
 *
 * @author jlombardo
 */
public class HashedPasswordGenerator {
        
    public HashedPasswordGenerator() {
    }

    public String sha256(String base) {
    try{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(base.getBytes("UTF-8"));
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    } catch(Exception ex){
       throw new RuntimeException(ex);
    }
}
}
