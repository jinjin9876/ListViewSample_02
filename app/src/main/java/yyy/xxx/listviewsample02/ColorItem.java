package yyy.xxx.listviewsample02;

import java.io.Serializable;

/**
 * Created by Jinwoo on 2016. 9. 24..
 */

public class ColorItem implements Serializable {

	private static final long serialVersionUID = 2220825036692751474L;

	private int bgColor = -1;
	private String colorName = "";


	public int getBgColor() {
		return bgColor;
	}

	public void setBgColor(int bgColor) {
		this.bgColor = bgColor;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
}
