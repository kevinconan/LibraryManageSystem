/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kevinconan.model;

/**
 * 借书卡javaBean类
 * <p/>
 * @author Kevinconan
 */
public class BookCardBean {

	private String BCID;
	private String BNAME;
	private String BPASSWD;
	private String BSEX;
	private int BAUTH;
	private String BDATE;
	private String BAVATAR = "0";

	public BookCardBean() {
	}

	public BookCardBean(boolean b) {
		BCID = "";
		BNAME = "";
		BPASSWD = "";
		BSEX = "";
		BAUTH = 0;
		BDATE = "";
		BAVATAR = "0";


	}

	public String getBAVATAR() {
		return BAVATAR;
	}

	public void setBAVATAR(String BAVATAR) {
		this.BAVATAR = BAVATAR;
	}

	public String getBCID() {
		return BCID;
	}

	public void setBCID(String BCID) {
		this.BCID = BCID;
	}

	public String getBNAME() {
		return BNAME;
	}

	public void setBNAME(String BNAME) {
		this.BNAME = BNAME;
	}

	public String getBPASSWD() {
		return BPASSWD;
	}

	public void setBPASSWD(String BPASSWD) {
		this.BPASSWD = BPASSWD;
	}

	public String getBSEX() {
		return BSEX;
	}

	public void setBSEX(String BSEX) {
		this.BSEX = BSEX;
	}

	public int getBAUTH() {
		return BAUTH;
	}

	public void setBAUTH(int BAUTH) {
		this.BAUTH = BAUTH;
	}

	public String getBDATE() {
		return BDATE;
	}

	public void setBDATE(String BDATE) {
		this.BDATE = BDATE;
	}
}
