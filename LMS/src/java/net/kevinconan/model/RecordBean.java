/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kevinconan.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 借书记录表javabean
 * <p/>
 * @author Kevinconan
 */
public class RecordBean {

	private String SN;
	private String ISBN;
	private String ADID;
	private String BCID;
	private String ODATE;
	private String RDATE;
	private String EXTBK;
	private String RNOTE;

	public RecordBean(boolean flag) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ODATE = sdf.format(new Date());
		RDATE = sdf.format(new Date());
	}

	public RecordBean() {
	}

	public String getSN() {
		return SN;
	}

	public void setSN(String SN) {
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

	public String getODATE() {
		return ODATE;
	}

	public void setODATE(String ODATE) {
		this.ODATE = ODATE;
	}

	public String getRDATE() {
		return RDATE;
	}

	public void setRDATE(String RDATE) {
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
