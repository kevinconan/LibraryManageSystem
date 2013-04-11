/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kevinconan.model;

import java.sql.Date;

/**
 * 借书记录表javabean
 * <p/>
 * @author Kevinconan
 */
public class RecordBean {

	private long SN;
	private String ISBN;
	private String ADID;
	private String BCID;
	private Date ODATE;
	private Date RDATE;
	private String EXTBK;
	private String RNOTE;
	private String BNAME;

	public String getBNAME() {
		return BNAME;
	}

	public void setBNAME(String BNAME) {
		this.BNAME = BNAME;
	}

	public RecordBean() {
	}

	public long getSN() {
		return SN;
	}

	public void setSN(long SN) {
		this.SN = SN;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public String getADID() {
		return ADID;
	}

	public void setADID(String ADID) {
		this.ADID = ADID;
	}

	public String getBCID() {
		return BCID;
	}

	public void setBCID(String BCID) {
		this.BCID = BCID;
	}

	public Date getODATE() {
		return ODATE;
	}

	public void setODATE(Date ODATE) {
		this.ODATE = ODATE;
	}

	public Date getRDATE() {
		return RDATE;
	}

	public void setRDATE(Date RDATE) {
		this.RDATE = RDATE;
	}

	public String getEXTBK() {
		return EXTBK;
	}

	public void setEXTBK(String EXTBK) {
		this.EXTBK = EXTBK;
	}

	public String getRNOTE() {
		return RNOTE;
	}

	public void setRNOTE(String RNOTE) {
		this.RNOTE = RNOTE;
	}
}
