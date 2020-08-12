package logic;

import java.util.Date;

public class SNSFile {
	private long fno, wno, wno2, wno3;
	private String fwhere, name, filename, fileurl;
	private Date regdate;
	public long getFno() {
		return fno;
	}
	public void setFno(long fno) {
		this.fno = fno;
	}
	public long getWno() {
		return wno;
	}
	public void setWno(long wno) {
		this.wno = wno;
	}
	public long getWno2() {
		return wno2;
	}
	public void setWno2(long wno2) {
		this.wno2 = wno2;
	}
	public long getWno3() {
		return wno3;
	}
	public void setWno3(long wno3) {
		this.wno3 = wno3;
	}
	public String getFwhere() {
		return fwhere;
	}
	public void setFwhere(String fwhere) {
		this.fwhere = fwhere;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getfileurl() {
		return fileurl;
	}
	public void setfileurl(String fileurl) {
		this.fileurl = fileurl;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "File [fno=" + fno + ", wno=" + wno + ", wno2=" + wno2 + ", wno3=" + wno3 + ", fwhere=" + fwhere
				+ ", name=" + name + ", filename=" + filename + ", fileurl=" + fileurl + ", regdate=" + regdate + "]";
	}
	
}
