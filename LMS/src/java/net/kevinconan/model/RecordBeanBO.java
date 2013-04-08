/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kevinconan.model;

/**
 * 借书记录管理
 * <p/>
 * @author Kevin
 */
public class RecordBeanBO {

	private RecordBean rb;

	/**
	 * 构造方法，初始化并传入ISBN，ADID,BCID
	 */
	public RecordBeanBO(String ISBN, String ADID, String BCID) {
		rb = new RecordBean(true);
		rb.setISBN(ISBN);
		rb.setADID(ADID);
		rb.setBCID(BCID);
	}

	/**
	 * 借书处理
	 * <p/>
	 * @return
	 */
	public boolean checkOutBook() {
		boolean b = false;
		ConnDB cdb = new ConnDB();
		String sql = "insert record (ISBN,ADID,BCID,ODATE) VALUES ('" + rb.getISBN() + "','" + rb.getADID() + "','" + rb.getBCID() + "','" + rb.getODATE() + "')";
		cdb.setSqlStatement(sql);
		cdb.execUpdate();
		if (cdb.getExecUpdateNum() == 1) {
			BookBO bbo = new BookBO();
			bbo.updateBookNum(rb.getISBN(), "in");
			b = true;
		}

		return b;

	}

	/**
	 * 还书处理
	 * <p/>
	 * @return
	 */
	public boolean checkInBook() {
		boolean b = false;
		ConnDB cdb = new ConnDB();
		String sql = "insert record (ISBN,ADID,BCID,RDATE) VALUES ('" + rb.getISBN() + "','" + rb.getADID() + "','" + rb.getBCID() + "','" + rb.getRDATE() + "')";
		cdb.setSqlStatement(sql);
		cdb.execUpdate();
		if (cdb.getExecUpdateNum() == 1) {
			BookBO bbo = new BookBO();
			bbo.updateBookNum(rb.getISBN(), "out");
			b = true;
		}

		return b;

	}

	public RecordBean showRecord() {
		RecordBean rb = new RecordBean();
		ConnDB cdb = new ConnDB();
		String sql = "select * ";


		return rb;
	}
}
