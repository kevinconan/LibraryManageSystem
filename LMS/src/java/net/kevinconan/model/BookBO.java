/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kevinconan.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 进行与Book表相关的数据库操作，添加，删除，更改，查找等
 * <p/>
 * @author Diluka
 */
public class BookBO {

	private int searchCount;
	private String searchPattern = null;

	/**
	 * 通过ISBN查询书名
	 * <p/>
	 * @return
	 */
	public String getBookNameByISBN(String ISBN) {
		String bookName = null;
		ConnDB cdb = new ConnDB();
		String sql = "select BNAME from BOOK where ISBN='" + ISBN + "'";
		cdb.connect();
		cdb.setSqlStatement(sql);
		cdb.execQuery();
		ResultSet rs = cdb.getResultSet();

		try {
			if (rs.next()) {
				bookName = rs.getString(1);
			}
		} catch (SQLException ex) {
			Logger.getLogger(BookBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			cdb.close();
		}
		return bookName;
	}

	/**
	 * 通过ISBN查询书库存
	 * <p/>
	 * @return
	 */
	public int getBookNumByISBN(String ISBN) {
		int bookNum = 0;
		ConnDB cdb = new ConnDB();
		String sql = "select BNUM from BOOK where ISBN='" + ISBN + "'";
		cdb.connect();
		cdb.setSqlStatement(sql);
		cdb.execQuery();
		ResultSet rs = cdb.getResultSet();

		try {
			if (rs.next()) {
				bookNum = rs.getInt(1);
			}
		} catch (SQLException ex) {
			Logger.getLogger(BookBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			cdb.close();
		}
		return bookNum;
	}

	/**
	 * 更新书籍库存数据
	 * <p/>
	 * @param ISBN 书号
	 * @param Type 更新方式，借出'out',还书'in'
	 * <p/>
	 * @return boolean
	 */
	public boolean updateBookNum(String ISBN, String Type) {
		boolean b = false;
		ConnDB cdb = new ConnDB();

		int BNUM = getBookNumByISBN(ISBN);
		String sql;
		if (BNUM < 1) {
			return false;
		}
		if (Type.equals("in")) {
			sql = "update book set BNUM=" + (BNUM + 1) + " where ISBN='" + ISBN + "'";

		} else if (Type.equals("out")) {
			sql = "update book set BNUM=" + (BNUM - 1) + " where ISBN='" + ISBN + "'";
		} else {
			return b;
		}
		//	System.out.println(sql);
		cdb.setSqlStatement(sql);
		cdb.execUpdate();
		if (cdb.getExecUpdateNum() == 1) {
			b = true;
		}

		return b;
	}

	/**
	 * 用isbn精确查找书籍信息
	 * <p/>
	 * @param isbn
	 *                <p/>
	 * @return BookBean
	 */
	public BookBean searchBookByISBN(String isbn) {
		BookBean bb = new BookBean();
		ConnDB cdb = new ConnDB();
		String sql = "select * from BOOK where ISBN='" + isbn + "'";
		cdb.connect();
		cdb.setSqlStatement(sql);
		cdb.execQuery();
		ResultSet rs = cdb.getResultSet();

		try {
			if (rs.next()) {
				bb.setISBN(rs.getString(1));
				bb.setBNAME(rs.getString(2));
				bb.setAUTHER(rs.getString(3));
				bb.setPRESS(rs.getString(4));
				bb.setPTIME(rs.getString(5));
				bb.setBTYPE(rs.getString(6));
				bb.setPRICE(rs.getDouble(7));
				bb.setBAUTH(rs.getInt(8));
				bb.setSNUM(rs.getInt(9));
				bb.setBNUM(rs.getInt(10));
			}
		} catch (SQLException ex) {
			Logger.getLogger(BookBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			cdb.close();
		}

		return bb;
	}

	/**
	 * 修改一条书籍记录
	 * <p/>
	 * @param bb
	 *              <p/>
	 * @return boolean
	 */
	public boolean updateBook(BookBean bb) {
		boolean b = false;
		String sql = "update book set BNAME='" + bb.getBNAME() + "',AUTHER='" + bb.getAUTHER() + "',PRESS='" + bb.getPRESS() + "',PTIME='" + bb.getPTIME() + "',BTYPE='" + bb.getBTYPE() + "',PRICE='" + bb.getPRICE() + "',BAUTH='" + bb.getBAUTH() + "',SNUM='" + bb.getSNUM() + "',BNUM='" + bb.getBNUM() + "' where ISBN='" + bb.getISBN() + "'";
		System.out.println("updatesql=" + sql);
		ConnDB cdb = new ConnDB();
		cdb.connect();
		cdb.setSqlStatement(sql);
		cdb.execUpdate();
		if (cdb.getExecUpdateNum() != 0) {
			b = true;
		}
		cdb.close();
		return b;
	}

	/**
	 * 添加一条书籍记录
	 * <p/>
	 * @param bb
	 *              <p/>
	 * @return boolean
	 */
	public boolean addBook(BookBean bb) {
		boolean b = false;
		ConnDB cdb = new ConnDB();
		String data = "'" + bb.getISBN() + "','" + bb.getBNAME() + "','" + bb.getAUTHER() + "','" + bb.getPRESS() + "','" + bb.getPTIME() + "','" + bb.getBTYPE() + "','" + bb.getPRICE() + "','" + bb.getBAUTH() + "','" + bb.getSNUM() + "','" + bb.getBNUM() + "'";
		String sql = "insert into BOOK (ISBN,BNAME,AUTHER,PRESS,PTIME,BTYPE,PRICE,BAUTH,SNUM,BNUM) values(" + data + ")";
		System.out.println("addsql=" + sql);
		cdb.connect();
		System.out.println(sql);
		cdb.setSqlStatement(sql);
		cdb.execUpdate();
		if (cdb.getExecUpdateNum() != 0) {
			b = true;
		}
		cdb.close();
		return b;
	}

	/**
	 * 删除一条书籍记录
	 * <p/>
	 * @param isbn
	 *                <p/>
	 * @return boolean
	 */
	public boolean delBookByISBN(String isbn) {
		boolean b = false;
		String sql = "delete from BOOK where ISBN='" + isbn + "'";
		ConnDB cdb = new ConnDB();
		cdb.connect();
		cdb.setSqlStatement(sql);
		cdb.execUpdate();
		if (cdb.getExecUpdateNum() != 0) {
			b = true;
		}
		cdb.close();
		return b;
	}

	/**
	 * 用已设置的字符串进行模糊查找，返回一页的结果
	 * <p/>
	 * @param pageNow  当前页
	 * @param pageSize 每页结果数量
	 * <p/>
	 * @return ArrayList 结果表
	 */
	public ArrayList searchBook(int pageNow, int pageSize) {
		ArrayList<BookBean> al = new ArrayList<BookBean>();
		String sql = "select * from BOOK WHERE CONCAT(ISBN,AUTHER,PRESS,BNAME) LIKE '%" + searchPattern + "%' AND CONCAT(ISBN,AUTHER,PRESS,BNAME) IS NOT NULL limit " + (pageNow - 1) * pageSize + "," + pageSize;
		ConnDB cdb = new ConnDB();
		cdb.connect();
		cdb.setSqlStatement(sql);
		cdb.execQuery();
		ResultSet rs = cdb.getResultSet();
		try {
			while (rs.next()) {
				BookBean bb = new BookBean();

				bb.setISBN(rs.getString(1));
				bb.setBNAME(rs.getString(2));
				bb.setAUTHER(rs.getString(3));
				bb.setPRESS(rs.getString(4));
				bb.setPTIME(rs.getString(5));
				bb.setBTYPE(rs.getString(6));
				bb.setPRICE(rs.getDouble(7));
				bb.setBAUTH(rs.getInt(8));
				bb.setSNUM(rs.getInt(9));
				bb.setBNUM(rs.getInt(10));

				al.add(bb);
			}
		} catch (SQLException ex) {
			Logger.getLogger(BookBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			cdb.close();
		}
		return al;
	}

	/**
	 * 设置模糊查找字符串
	 * <p/>
	 * @param searchPattern
	 */
	public void setSearchPattern(String searchPattern) {
		this.searchPattern = searchPattern;
	}

	/**
	 * 得到模糊查找数量
	 * <p/>
	 * @return int
	 */
	public int getSearchCount() {
		calcSearchCount();
		return searchCount;
	}

	/**
	 * 执行模糊查找数量统计
	 */
	private void calcSearchCount() {
		String sql = "select count(*) from BOOK WHERE CONCAT(AUTHER,PRESS,BNAME) LIKE '%" + searchPattern + "%' AND CONCAT(AUTHER,PRESS,BNAME) IS NOT NULL";
		ConnDB cdb = new ConnDB();
		cdb.connect();
		cdb.setSqlStatement(sql);
		cdb.execQuery();
		try {
			if (cdb.getResultSet().next()) {
				searchCount = cdb.getResultSet().getInt(1);
			}
		} catch (SQLException ex) {
			Logger.getLogger(BookBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			cdb.close();
		}
	}
}
