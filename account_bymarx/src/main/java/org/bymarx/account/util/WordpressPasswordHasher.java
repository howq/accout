package org.bymarx.account.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author howq
 * @create 2017-07-11 下午5:34
 **/
public class WordpressPasswordHasher {
    private static final String STRITOA64 = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static String cryptPrivate(String password, String setting)
            throws NoSuchAlgorithmException {
        String output = "*0";
        if (setting.startsWith(output)) {
            {
                output = "*1";
            }
        }

        String id = setting.substring(0, 3);

        if (!"$P$".equals(id) && !"$H$".equals(id)) {
            return output;
        }

        int count_log2 = STRITOA64.indexOf(setting.substring(3, 4));
        if (count_log2 < 7 || count_log2 > 30) {
            return output;
        }

        int count = 1 << count_log2;

        String salt = setting.substring(4, 12);
        if (salt.length() != 8) {
            return output;
        }

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update((salt + password).getBytes());
        byte hash[] = md.digest();
        do {
            byte newHash[] = new byte[hash.length + password.getBytes().length];
            System.arraycopy(hash, 0, newHash, 0, hash.length);
            System.arraycopy(password.getBytes(), 0, newHash, hash.length,
                    password.getBytes().length);
            md.update(newHash);
            hash = md.digest();
        } while ((--count) > 0);

        output = setting.substring(0, 12);
        output += encode64(hash, 16);
        return output;
    }

    private static String encode64(byte input[], int count) {
        String output = "";
        int i = 0;
        do {
            int value = input[i++] & 0xFF;
            output += STRITOA64.charAt(value & 0x3f);
            if (i < count) {
                value |= ((input[i] & 0xFF) << 8);
            }
            output += STRITOA64.charAt((value >> 6) & 0x3f);
            if (i++ >= count) {
                break;
            }
            if (i < count) {
                value |= ((input[i] & 0xFF) << 16);
            }
            output += STRITOA64.charAt((value >> 12) & 0x3f);
            if (i++ >= count) {
                break;
            }
            output += STRITOA64.charAt((value >> 18) & 0x3f);
        } while (i < count);
        return output;
    }


    /**
     * 加密
     *
     * @param password
     * @return  密文
     * @throws NoSuchAlgorithmException
     */
    public static String HashPassword (String password) throws NoSuchAlgorithmException{
        if (password.length() > 4096) {
            return "*";
        }

        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        sb.append("$P$B");
        for (int i = 0; i < 8; i++) {
            int number = random.nextInt(STRITOA64.length());
            sb.append(STRITOA64.charAt(number));
        }

        String hash = cryptPrivate(password, sb.toString());
        if(34==hash.length()){
            return hash;
        }
        return "*";
    }

    /**
     * Check password
     *
     * @param password Plain password
     * @param storedHash Stored password
     * @return If the password is correct, then returns true. Else it returns false.
     *
     * @throws NoSuchAlgorithmException
     */
    public static boolean checkPassword(String password, String storedHash)
            throws NoSuchAlgorithmException {
        String hash = cryptPrivate(password, storedHash);

        return hash.equals(storedHash);
    }
}