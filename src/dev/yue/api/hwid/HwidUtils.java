package dev.yue.api.hwid;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HwidUtils {

    public static String getHwid () {
        
        try {
            String unhashed = unhashed();

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            byte[] hash = messageDigest.digest(unhashed.getBytes());

            return byte2hex(hash);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String unhashed () {
        return System.getProperty("user.name") 
            + System.getProperty("os.name") 
            + System.getenv("NUMBER_OF_PROCESSORS")
            + System.getenv("PROCESSOR_ARCHITECTURE")
            + System.getenv("PROCESSOR_IDENTIFIER")
            + System.getenv("PROCESSOR_LEVEL")
            + System.getenv("PROCESSOR_REVISION");
    }

    private static String byte2hex (byte[] bytes) {

        String hexString = "";

        for (byte bits: bytes) {
            String hex = Integer.toHexString(bits & 0xff);

            if (hex.length() < 2) hex = "0" + hex;
            
            hexString += hex;
        }

        return hexString;
    }
    
}
