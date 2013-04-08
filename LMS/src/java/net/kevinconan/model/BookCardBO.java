/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kevinconan.model;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.kevinconan.tools.ToSHA_1;

/**
 * 借书卡管理类
 * <p/>
 * @author Kevin
 */
public class BookCardBO {

	//登陆验证
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
			//  cdb.close();
		}

		ToSHA_1 ts = new ToSHA_1(p);
		// System.out.println(p);
		//  System.out.println(ts.getSHA_1String());
		if (ts.getSHA_1String().equals(pwdb)) {
			b = true;
		}

		return b;
	}

	//返回借书卡信息
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
	//修改借书卡

	public boolean updateBookCard(BookCardBean bcb) {
		boolean b = false;
		ToSHA_1 psw = new ToSHA_1(bcb.getBPASSWD());
		bcb.setBPASSWD(psw.getSHA_1String());
		String sql = "update bookcard set BAPSSWD='" + bcb.getBPASSWD() + "',BSEX='" + bcb.getBSEX() + "',BAUTH='" + bcb.getBAUTH() + "',BDATE='" + bcb.getBDATE() + "',BAVATAR='" + bcb.getBAVATAR() + "' where BCID='" + bcb.getBCID() + "'";
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

	//注销借书卡
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

	//注册借书卡
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
			//转换密码为SHA_1
			ToSHA_1 psw = new ToSHA_1(bcb.getBPASSWD());
			bcb.setBPASSWD(psw.getSHA_1String());


			//写入数据库
			String sql2 = "INSERT into user (BCID,BAPSSWD,BSEX,BAUTH,BDATE,BAVATAR) VALUES ('" + bcb.getBCID() + "','" + bcb.getBPASSWD() + "','" + bcb.getBSEX() + "','" + bcb.getBAUTH() + "','" + bcb.getBDATE() + "','" + bcb.getBAVATAR() + "')";

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
}
