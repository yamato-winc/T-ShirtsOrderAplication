package com.winc.kensyu.DTO;

import java.sql.Date;

public class FontColorDTO {
	private String fontColorId;
	private String fontColorR;
	private String fontColorG;
	private String fontColorB;
	private Date fontColorUseStart;
	private Date fontColorUserEnd;
	
	
	public String getFontColorId() {
		return fontColorId;
	}
	public void setFontColorId(String fontColorId) {
		this.fontColorId = fontColorId;
	}
	public String getFontColorR() {
		return fontColorR;
	}
	public void setFontColorR(String fontColorR) {
		this.fontColorR = fontColorR;
	}
	public String getFontColorG() {
		return fontColorG;
	}
	public void setFontColorG(String fontColorG) {
		this.fontColorG = fontColorG;
	}
	public String getFontColorB() {
		return fontColorB;
	}
	public void setFontColorB(String fontColorB) {
		this.fontColorB = fontColorB;
	}
	public Date getFontColorUseStart() {
		return fontColorUseStart;
	}
	public void setFontColorUseStart(Date fontColorUseStart) {
		this.fontColorUseStart = fontColorUseStart;
	}
	public Date getFontColorUserEnd() {
		return fontColorUserEnd;
	}
	public void setFontColorUserEnd(Date fontColorUserEnd) {
		this.fontColorUserEnd = fontColorUserEnd;
	}
	
}
