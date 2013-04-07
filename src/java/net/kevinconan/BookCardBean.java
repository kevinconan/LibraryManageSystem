/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kevinconan;

/**
 *借书卡javaBean类
 * @author Kevinconan
 */
public class BookCardBean {
    private String BCID;
    private String BPASSWD;
    private String BSEX;
    private int BAUTH;
    private String BDATE;

    public String getBCID() {
        return BCID;
    }

    public void setBCID(String BCID) {
        this.BCID = BCID;
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
