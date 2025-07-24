package com.winc.kensyu.DTO;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FontColorDTO {
	@JsonProperty("FontColor_Id")
	private int fontColorId;
	@JsonProperty("FontColor_R")
	private int fontColorR;
	@JsonProperty("FontColor_G")
	private int fontColorG;
	@JsonProperty("FontColor_B")
	private int fontColorB;
	@JsonIgnore
	private Date fontColorUseStart;
	@JsonIgnore
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
