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
 *
 * @author Diluka
 */
public class ToSHA_1 {

	private String in, out;

	public ToSHA_1(String in) {
		this.in = in;
	}

	public String getSHA_1String() {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(in.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : md.digest()) {
				sb.append(String.format("%02x", b));
			}
			out = sb.toString();
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(ToSHA_1.class.getName()).log(Level.SEVERE, null, ex);
		}

		return out;
	}
}
