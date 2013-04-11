/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kevinconan.model;

/**
 * 书籍表javabean
 * <p/>
 * @author Kevinconan
 */
public class BookBean {

	private String ISBN;
	private String BNAME;
	private String AUTHER;
	private String PRESS;
	private String PTIME;
	private String BTYPE;
	private double PRICE;
	private int BAUTH;
	private int SNUM;
	private int BNUM;

	public BookBean() {
	}

	public BookBean(boolean b) {
		ISBN = "";
		BNAME = "";
		AUTHER = "";
		PRESS = "";
		PRICE = 0;
		PTIME = "";
		BTYPE = "";
		BAUTH = 0;
		SNUM = 0;
		BNUM = 0;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public String getBNAME() {
		return BNAME;
	}

	public void setBNAME(String BNAME) {
		this.BNAME = BNAME;
	}

	public String getAUTHER() {
		return AUTHER;
	}

	public void setAUTHER(String AUTHER) {
		this.AUTHER = AUTHER;
	}

	public String getPRESS() {
		return PRESS;
	}

	public void setPRESS(String PRESS) {
		this.PRESS = PRESS;
	}

	public String getPTIME() {
		return PTIME;
	}

	public void setPTIME(String PTIME) {
		this.PTIME = PTIME;
	}

	public String getBTYPE() {
		return BTYPE;
	}

	public void setBTYPE(String BTYPE) {
		this.BTYPE = BTYPE;
	}

	public double getPRICE() {
		return PRICE;
	}

	public void setPRICE(double PRICE) {
		this.PRICE = PRICE;
	}

	public int getBAUTH() {
		return BAUTH;
	}

	public void setBAUTH(int BAUTH) {
		this.BAUTH = BAUTH;
	}

	public int getSNUM() {
		return SNUM;
	}

	public void setSNUM(int SNUM) {
		this.SNUM = SNUM;
	}

	public int getBNUM() {
		return BNUM;
	}

	public void setBNUM(int BNUM) {
		this.BNUM = BNUM;
	}
}
