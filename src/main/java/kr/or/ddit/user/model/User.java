package kr.or.ddit.user.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private String userid;
    private String usernm;
    private String pass;
    private Date reg_dt;
    private String alias;
    private String addr1;
    private String addr2;
    private String zipcode;
    private String filename;
    private String realfilename;
    private String realfilename2;

    public User() {
    }

    public User(String userid, String usernm, String pass, Date reg_dt, String alias, String addr1, String addr2, String zipcode, String filename, String realfilename, String realfilename2) {
        this.userid = userid;
        this.usernm = usernm;
        this.pass = pass;
        this.reg_dt = reg_dt;
        this.alias = alias;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.zipcode = zipcode;
        this.filename = filename;
        this.realfilename = realfilename;
        this.realfilename2 = realfilename2;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsernm() {
        return usernm;
    }

    public void setUsernm(String usernm) {
        this.usernm = usernm;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Date getReg_dt() {
        return reg_dt;
    }

    public void setReg_dt(Date reg_dt) {
        this.reg_dt = reg_dt;
    }

    public String getReg_dt_fmt() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return  sf.format(reg_dt);
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getRealfilename() {
        return realfilename;
    }

    public void setRealfilename(String realfilename) {
        this.realfilename = realfilename;
    }

    public String getRealfilename2() {
        return realfilename2;
    }

    public void setRealfilename2(String realfilename2) {
        this.realfilename2 = realfilename2;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", usernm='" + usernm + '\'' +
                ", pass='" + pass + '\'' +
                ", reg_dt=" + reg_dt +
                ", alias='" + alias + '\'' +
                ", addr1='" + addr1 + '\'' +
                ", addr2='" + addr2 + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", filename='" + filename + '\'' +
                ", realfilename='" + realfilename + '\'' +
                ", realfilename2='" + realfilename2 + '\'' +
                '}';
    }
}
