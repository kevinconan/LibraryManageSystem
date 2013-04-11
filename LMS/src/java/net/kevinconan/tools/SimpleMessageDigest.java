/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kevinconan.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 这样好用多了
 * <p/>
 * @author Diluka
 */
public class SimpleMessageDigest {

	/**
	 * 可以选算法，MD5，SHA-1，SHA-256
	 * <p/>
	 * @param message
	 * @param algorithm
	 *                     <p/>
	 * @return
	 */
	public String getDigestString(String message, String algorithm) {
		MessageDigest md;
		StringBuilder sb = new StringBuilder();
		try {
			md = MessageDigest.getInstance(algorithm);
			md.update(message.getBytes());

			for (byte b : md.digest()) {
				sb.append(String.format("%02x", b));
			}

		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(SimpleMessageDigest.class.getName()).log(Level.SEVERE, null, ex);
		}
		return sb.toString();
	}

	/**
	 * 直接计算SHA-1
	 * <p/>
	 * @param message
	 *                   <p/>
	 * @return
	 */
	public String getSHA1String(String message) {
		return getDigestString(message, "SHA-1");
	}

	/**
	 * 比较字符串和已有的摘要是否匹配，需要指定摘要算法
	 * <p/>
	 * @param message
	 * @param digest
	 * @param algorithm
	 *                     <p/>
	 * @return
	 */
	public boolean isStringMatchDigest(String message, String digest, String algorithm) {
		if (digest.toLowerCase().equals(getDigestString(message, algorithm))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 比较字符串和已有的摘要是否匹配，SHA-1算法
	 * <p/>
	 * @param message
	 * @param sha1
	 *                   <p/>
	 * @return
	 */
	public boolean isStringMatchSHA1(String message, String sha1) {
		if (isStringMatchDigest(message, sha1, "SHA-1")) {
			return true;
		} else {
			return false;
		}
	}
}
