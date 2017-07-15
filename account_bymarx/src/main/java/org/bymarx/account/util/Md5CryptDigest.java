package org.bymarx.account.util;

import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 对字符串进行加密解密的类
 * 
 * @Company:Dalian Wise Information Technology Co.LTD
 * @author xxl
 * @version 1.0
 */
public class Md5CryptDigest {

	/**
	 * 对字符串进行加密
	 * 
	 * @param strEncode
	 * @return String
	 * @throws DigestException
	 */
	@SuppressWarnings("finally")
	public static String encodeMd5(String strEncode)
			throws NoSuchAlgorithmException, DigestException {
		byte[] digest;
		String strCrypt = "";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(strEncode.getBytes());
			digest = messageDigest.digest();
			strCrypt = byte2Hex(digest);
		} catch (Exception cnse) {
			throw new DigestException("数据加密失败！");
		} finally {
			return strCrypt;
		}

	}

	/**
	 * 对byte数组进行十六进制转换
	 * 
	 * @param byte[] bytes
	 * @return String strHex
	 */
	public static String byte2Hex(byte[] bytes) {
		StringBuilder stringHexBuilder = new StringBuilder("");
		String strTemp = "";
		for (int i = 0; i < bytes.length; i++) {
			strTemp = (Integer.toHexString(bytes[i] & 0XFF));
			if (strTemp.length() == 1) {
				stringHexBuilder.append("0").append(strTemp);
			} else {
				stringHexBuilder.append(strTemp);
			}
		}
		return stringHexBuilder.toString();
	}

	/**
	 * 把加密后的字符串进行解密
	 * @param strDecode 待解密的字符串
	 * @return digest 解密后的十六进制byte数组
	 */
	public static byte[] decodeMd5(String strDecode) {
		byte[] digest= new byte[16];
		int iHexLength = strDecode.length();
		for (int i = 0, j = 0; i < iHexLength; i = i + 2, j++) {
			digest[j] = Integer.valueOf(strDecode.substring(i, i + 2), 16)
					.byteValue();
		}
		return digest;
	}

	/**
	 * 判断输入的字符串和加密的字符串是否相同
	 * @param strCrypted 加密后的字符串
	 * @param strEncode 待验证的字符串
	 * @return bResult 相同返回true 不相同返回false
	 * @throws DigestException
	 */
	@SuppressWarnings("finally")
	public boolean isValidate(String strCrypted, String strEncode)
			throws DigestException {
		byte[] digest;
		boolean bResult = false;
		try {
			digest = decodeMd5(strCrypted);
			MessageDigest alga = MessageDigest.getInstance("MD5");
			alga.update(strEncode.getBytes());
            bResult = MessageDigest.isEqual(digest, alga.digest());
		} catch (Exception ex) {
			bResult = false;
			System.out.println("算法出错。");
			throw new DigestException("数据加密失败！");

		} finally {
			return bResult;
		}
	}
}
