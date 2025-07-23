package com.winc.kensyu.DTO;

import java.sql.Date;

public class FontColorDTO {
	private int fontColorId;
	private int fontColorR;
	private int fontColorG;
	private int fontColorB;
	private Date fontColorUseStart;
	private Date fontColorUserEnd;
	
	
	public int getFontColorId() {
		return fontColorId;
	}
	public void setFontColorId(int fontColorId) {
		this.fontColorId = fontColorId;
	}
	public int getFontColorR() {
		return fontColorR;
	}
	public void setFontColorR(int fontColorR) {
		this.fontColorR = fontColorR;
	}
	public int getFontColorG() {
		return fontColorG;
	}
	public void setFontColorG(int fontColorG) {
		this.fontColorG = fontColorG;
	}
	public int getFontColorB() {
		return fontColorB;
	}
	public void setFontColorB(int fontColorB) {
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
