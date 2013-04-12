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
import net.kevinconan.tools.SimpleMessageDigest;

/**
 * 借书卡管理类
 * <p/>
 * @author Kevin
 */
public class BookCardBO {

	/**
	 * 登陆验证
	 * <p/>
	 * @param u 用户名
	 * @param p 密码
	 * <p/>
	 * @return
	 */
	public boolean checkBookCard(String u, String p) {
		boolean b = false;
		ConnDB cdb = new ConnDB();
		String sql = "select BPASSWD from bookcard where BCID='" + u + "'";
		String pwdb = null;
		cdb.connect();
		cdb.setSqlStatement(sql);
		cdb.execQuery();
		try {
			if (cdb.getResultSet().next()) {
				//从数据库直接读取byte数组，这里要确定数据库里面保存的是20个字节的数据
				//SHA-1算法的结果是20字节
				//这里没有选择保存20字节的二进制数据，太不优雅了，把它变成40个字节的字符串保存的，内容是十六进制数字
				pwdb = cdb.getResultSet().getString(1);
			} else {
				return false;//既然用户都不存在，就没必要继续了
			}
		} catch (SQLException ex) {
			Logger.getLogger(AdminBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			cdb.close();
		}

		SimpleMessageDigest md = new SimpleMessageDigest();
		// System.out.println(p);
		//  System.out.println(ts.getSHA_1String());
		if (md.isStringMatchSHA1(p, pwdb)) {
			b = true;
		}

		return b;
	}

	/**
	 * 返回借书卡信息
	 * <p/>
	 * @param String BCID
	 * <p/>
	 * @return BookCardBean
	 */
	public BookCardBean showBookCard(String BCID) {
		BookCardBean bb = new BookCardBean();
		String sql = "select * from bookcard where BCID='" + BCID + "'";
		ConnDB cdb = new ConnDB();
		cdb.connect();
		cdb.setSqlStatement(sql);
		cdb.execQuery();
		try {
			if (cdb.getResultSet().next()) {
				bb.setBCID(cdb.getResultSet().getString(1));
				bb.setBPASSWD(cdb.getResultSet().getString(2));
				bb.setBNAME(cdb.getResultSet().getString(3));
				bb.setBSEX(cdb.getResultSet().getString(4));
				bb.setBAUTH(cdb.getResultSet().getInt(5));
				bb.setBDATE(cdb.getResultSet().getString(6));
				bb.setBAVATAR(cdb.getResultSet().getString(7));
			}
		} catch (Exception ex) {
			Logger.getLogger(AdminBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			cdb.close();
		}
		return bb;
	}

	/**
	 * 修改借书卡
	 * <p/>
	 * @param bcb
	 *               <p/>
	 * @return
	 */
	public boolean updateBookCard(BookCardBean bcb) {
		boolean b = false;
		SimpleMessageDigest psw = new SimpleMessageDigest();

		bcb.setBPASSWD(psw.getSHA1String(bcb.getBPASSWD()));
		String sql = "update bookcard set BNAME='" + bcb.getBNAME() + "',BPASSWD='" + bcb.getBPASSWD() + "',BSEX='" + bcb.getBSEX() + "',BAUTH='" + bcb.getBAUTH() + "',BDATE='" + bcb.getBDATE() + "',BAVATAR='" + bcb.getBAVATAR() + "' where BCID='" + bcb.getBCID() + "'";
		System.out.println(sql);
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
	 * 注销借书卡
	 * <p/>
	 * @param BCID
	 *                <p/>
	 * @return
	 */
	public boolean delBookCard(String BCID) {
		boolean b = false;
		String sql = "delete from bookcard where BCID='" + BCID + "'";
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
	 * 注册借书卡
	 * <p/>
	 * @param bcb
	 *               <p/>
	 * @return
	 */
	public boolean addBookCard(BookCardBean bcb) {
		boolean b = false;
		ConnDB cdb = new ConnDB();
		String sql = "select * from bookcard where BCID='" + bcb.getBCID() + "'";

		cdb.connect();
		cdb.setSqlStatement(sql);
		cdb.execQuery();

		try {
			//如果用户名存在，直接返回false
			if (cdb.getResultSet().next()) {
				return false;
			}
			//转换密码为SHA_
			SimpleMessageDigest psw = new SimpleMessageDigest();

			bcb.setBPASSWD(psw.getSHA1String(bcb.getBPASSWD()));

			Date bdate = new Date(new java.util.Date().getTime());
			//写入数据库
			String sql2 = "INSERT into bookcard (BCID,BNAME,BPASSWD,BSEX,BAUTH,BDATE,BAVATAR) VALUES ('" + bcb.getBCID() + "','" + bcb.getBNAME() + "','" + bcb.getBPASSWD() + "','" + bcb.getBSEX() + "','" + bcb.getBAUTH() + "','" + bdate + "','" + bcb.getBAVATAR() + "')";

			cdb.setSqlStatement(sql2);
			cdb.execUpdate();
			//检查创建成功了没有
			if (cdb.getExecUpdateNum() != 0) {
				b = true;
			}

		} catch (SQLException ex) {
			Logger.getLogger(AdminBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			cdb.close();
		}
		return b;
	}

	/**
	 * 搜索借书卡
	 * <p/>
	 * @param s        搜索字符串
	 * @param pageNow  当前页
	 * @param pageSize 每页条目数
	 * <p/>
	 * @return ArrayList<BookCardBean>
	 */
	public ArrayList<BookCardBean> searchBookCard(String s, int pageNow, int pageSize) {
		ArrayList<BookCardBean> al = new ArrayList<BookCardBean>();
		String sql = "select * from BOOKCARD WHERE CONCAT(BCID,BNAME) LIKE '%" + s + "%' AND CONCAT(BCID,BNAME) IS NOT NULL limit " + (pageNow - 1) * pageSize + "," + pageSize;
		ConnDB cdb = new ConnDB();
		cdb.connect();
		cdb.setSqlStatement(sql);
		cdb.execQuery();
		ResultSet rs = cdb.getResultSet();
		try {
			while (rs.next()) {
				BookCardBean bcb = new BookCardBean();
				bcb.setBCID(rs.getString(1));
				bcb.setBPASSWD(rs.getString(2));
				bcb.setBNAME(rs.getString(3));
				bcb.setBSEX(rs.getString(4));
				bcb.setBAUTH(rs.getInt(5));
				bcb.setBDATE(rs.getString(6));
				bcb.setBAVATAR(rs.getString(7));

				al.add(bcb);

			}
		} catch (SQLException ex) {
			Logger.getLogger(BookCardBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			cdb.close();
		}
		return al;
	}

	/**
	 * 返回搜索结果数
	 * <p/>
	 * @param s 搜索字符串
	 * <p/>
	 * @return 返回这个字符串搜索的结果数
	 */
	public int getSearchCount(String s) {
		String sql = "select count(*) from BOOKCARD WHERE CONCAT(BCID,BNAME) LIKE '%" + s + "%' AND CONCAT(BCID,BNAME) IS NOT NULL";
		ConnDB cdb = new ConnDB();
		cdb.connect();
		System.out.println(sql);
		cdb.setSqlStatement(sql);
		cdb.execQuery();
		try {
			if (cdb.getResultSet().next()) {
				return cdb.getResultSet().getInt(1);

			}
		} catch (SQLException ex) {
			Logger.getLogger(BookCardBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			cdb.close();
		}
		return 0;
	}
}
