/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kevinconan.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin
 */
public class RecordBO {

	private String errInfo;

	public String getErrInfo() {
		return errInfo;
	}
	private String recordSQL;
	private String countSQL;
	private int rowCount;

	public int getRowCount() {
		return rowCount;
	}
	private ArrayList<RecordBean> al;

	public ArrayList<RecordBean> getOutRecordListByBCID(String BCID, int pageNow, int pageSize) {
		return getRecordList(BCID, "bcid", "out", (pageNow - 1) * pageSize, pageSize);

	}

	public ArrayList<RecordBean> getInRecordListByBCID(String BCID, int pageNow, int pageSize) {
		return getRecordList(BCID, "bcid", "in", (pageNow - 1) * pageSize, pageSize);

	}

	public ArrayList<RecordBean> getAllRecordListByBCID(String BCID, int pageNow, int pageSize) {
		return getRecordList(BCID, "bcid", "all", (pageNow - 1) * pageSize, pageSize);

	}

	public ArrayList<RecordBean> getAllOutRecordList(int pageNow, int pageSize) {
		return getRecordList(null, "all", "out", (pageNow - 1) * pageSize, pageSize);

	}

	public ArrayList<RecordBean> getAllInRecordList(int pageNow, int pageSize) {
		return getRecordList(null, "all", "in", (pageNow - 1) * pageSize, pageSize);

	}

	public ArrayList<RecordBean> getAllRecordList(int pageNow, int pageSize) {
		return getRecordList(null, "all", "all", (pageNow - 1) * pageSize, pageSize);

	}

	/**
	 * 查询借书记录的主要方法
	 * <p/>
	 * @param id     一个ID串，可以是SN，ISBN，BCID，ADID任意一个，null表示全部分类
	 * @param idType 指明前面ID参数的类别，null&all表示全部类型
	 * @param fliter 对结果的过滤，"out"和"in"，"out"表示正在借出的，"in"表示已归还的，null表示全部
	 * <p/>
	 * @return
	 */
	public ArrayList<RecordBean> getRecordList(String id, String idType, String fliter, int lim1, int lim2) {
		al = new ArrayList<RecordBean>();
		if (id == null) {
			id = "all";
			idType = "all";
		}
		if (idType == null) {
			idType = "all";
		}

		//忽略大小写
		idType = idType.toUpperCase();
		fliter = fliter.toLowerCase();
		if (idType.equals("SN") | idType.equals("ISBN") | idType.equals("BCID") | idType.equals("ADID")) {
			//分别查询各种ID类型对应的的结果,in表示已归还，out已经借出，其它表示全部
			if (fliter.equals("in")) {
				recordSQL = "select * from record where RDATE is not NULL and " + idType + "='" + id + "' limit " + lim1 + "," + lim2;
				countSQL = "select count(*) from record where RDATE is not NULL and " + idType + "='" + id + "'";

			} else if (fliter.equals("out")) {
				recordSQL = "select * from record where RDATE is NULL and " + idType + "='" + id + "' limit " + lim1 + "," + lim2;
				countSQL = "select count(*) from record where RDATE is NULL and " + idType + "='" + id + "'";
			} else {
				recordSQL = "select * from record where " + idType + "='" + id + "' limit " + lim1 + "," + lim2;
				countSQL = "select count(*) from record where " + idType + "='" + id + "'";
			}
		} else {
			if (fliter.equals("in")) {
				//查询所有类型对应的归还与否分类结果，in表示已归还，out已经借出，其它表示全部
				recordSQL = "select * from record where RDATE is not NULL limit " + lim1 + "," + lim2;
				countSQL = "select count(*) from record where RDATE is not NULL limit " + lim1 + "," + lim2;

			} else if (fliter.equals("out")) {
				recordSQL = "select * from record where RDATE is NULL limit " + lim1 + "," + lim2;
				countSQL = "select count(*) from record where RDATE is NULL limit " + lim1 + "," + lim2;
			} else {
				recordSQL = "select * from record limit " + lim1 + "," + lim2;
				countSQL = "select count(*) from record limit " + lim1 + "," + lim2;
			}
		}


		System.out.println(recordSQL);
		System.out.println(countSQL);
		showRecord();
		calcRowCount();
		//System.out.println(al);
		return al;

	}

	/**
	 * 计算查询结果总数
	 */
	private void calcRowCount() {
		ConnDB cdb = new ConnDB();
		cdb.connect();
		cdb.setSqlStatement(countSQL);
		cdb.execQuery();
		try {
			if (cdb.getResultSet().next()) {
				rowCount = cdb.getResultSet().getInt(1);
			}
		} catch (SQLException ex) {
			Logger.getLogger(RecordBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			cdb.close();
		}
	}

	/**
	 * 借书记录显示
	 */
	private void showRecord() {
		ConnDB cdb = new ConnDB();
		cdb.connect();
		cdb.setSqlStatement(recordSQL);
		//System.out.println(recordSQL);
		cdb.execQuery();
		ResultSet rs = cdb.getResultSet();
		BookBO bbo = new BookBO();
		try {
			while (rs.next()) {
				//	System.out.println("封装已经执行");
				RecordBean rb = new RecordBean();
				rb.setSN(rs.getLong(1));
				rb.setISBN(rs.getString(2));
				rb.setADID(rs.getString(3));
				rb.setBCID(rs.getString(4));
				rb.setODATE(rs.getDate(5));
				rb.setRDATE(rs.getDate(6));
				rb.setEXTBK(rs.getString(7));
				rb.setRNOTE(rs.getString(8));
				rb.setBNAME(bbo.getBookNameByISBN(rs.getString(2)));
				al.add(rb);
			}
		} catch (SQLException ex) {
			Logger.getLogger(RecordBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			cdb.close();

		}
	}

	/**
	 * 借书处理
	 * <p/>
	 * @return
	 */
	public boolean outBook(String ISBN, String BCID, String ADID) {
		boolean b = false;
		ConnDB cdb = new ConnDB();
		cdb.connect();
		if (cdb.getExecUpdateNum() == 1) {
			//修改数据库中书籍库存量
			BookBO bbo = new BookBO();
			if (!bbo.updateBookNum(ISBN, "out")) {
				return false;
			}
			b = true;
		}
		Date date = new Date(new java.util.Date().getTime());

		//	System.out.println(date);
		String sql = "insert record (ISBN,ADID,BCID,ODATE) VALUES ('" + ISBN + "','" + ADID + "','" + BCID + "','" + date + "')";
		cdb.setSqlStatement(sql);
		cdb.execUpdate();

		cdb.close();
		return b;

	}

	/**
	 * 还书处理
	 * <p/>
	 * @return
	 */
	public boolean inBook(String ISBN, String BCID, String ADID) {
		boolean b = false;
		//	Date sysDate=new Date();
//		Calendar cal=new Calendar.getinstance();
		//	java.sql.Date dbDate=new java.sql.Date(sysDate.getTime());
		ConnDB cdb = new ConnDB();
		cdb.connect();
		String sql = "select SN from record where ISBN='" + ISBN + "' and BCID='" + BCID + "' and RDATE is NULL";

		cdb.setSqlStatement(sql);
		cdb.execQuery();
		long sn;
		ResultSet rs = cdb.getResultSet();
		Date rdate = new Date(new java.util.Date().getTime());
		try {
			if (rs.next()) {
				sn = rs.getLong(1);
			} else {
				return false;
			}
			sql = "update record set RDATE='" + rdate + "' where SN=" + sn;

			cdb.setSqlStatement(sql);
			cdb.execUpdate();
			if (cdb.getExecUpdateNum() == 1) {
				//修改数据库中书籍库存量
				BookBO bbo = new BookBO();
				bbo.updateBookNum(ISBN, "in");
				b = true;
			}

		} catch (SQLException ex) {
			Logger.getLogger(RecordBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			cdb.close();
		}
		return b;

	}
}
