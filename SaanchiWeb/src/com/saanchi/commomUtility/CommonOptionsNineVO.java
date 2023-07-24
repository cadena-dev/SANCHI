/**
 * Class Name  : OptionsVO
 * Description : Can Store the Options in a Drop down List
 * Created by  : TCS/Sabyasachi Bhattacharya
 * Created on  : 7-SEP-04
 * Modified    : [Who , When , What]
 */
package com.saanchi.commomUtility;

import java.io.Serializable;
import java.math.BigDecimal;

public class CommonOptionsNineVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type_code_1;
	private String type_code_2;
	private String type_code_3;
	private String type_code_4;
	private String type_code_5;
	private String type_code_6;
	private String type_code_7;
	private String type_code_8;
	private String type_code_9;

	public CommonOptionsNineVO(String type_code_1, String type_code_2, String type_code_3, String type_code_4,
			String type_code_5, String type_code_6, String type_code_7, String type_code_8, String type_code_9) {

		this.type_code_1 = type_code_1;
		this.type_code_2 = type_code_2;
		this.type_code_3 = type_code_3;
		this.type_code_4 = type_code_4;
		this.type_code_5 = type_code_5;
		this.type_code_6 = type_code_6;
		this.type_code_7 = type_code_7;
		this.type_code_8 = type_code_8;
		this.type_code_9 = type_code_9;

	}

	public String getType_code_1() {
		return type_code_1;
	}

	public void setType_code_1(String type_code_1) {
		this.type_code_1 = type_code_1;
	}

	public String getType_code_2() {
		return type_code_2;
	}

	public void setType_code_2(String type_code_2) {
		this.type_code_2 = type_code_2;
	}

	public String getType_code_3() {
		return type_code_3;
	}

	public void setType_code_3(String type_code_3) {
		this.type_code_3 = type_code_3;
	}

	public String getType_code_4() {
		return type_code_4;
	}

	public void setType_code_4(String type_code_4) {
		this.type_code_4 = type_code_4;
	}

	public String getType_code_5() {
		return type_code_5;
	}

	public void setType_code_5(String type_code_5) {
		this.type_code_5 = type_code_5;
	}

	public String getType_code_6() {
		return type_code_6;
	}

	public void setType_code_6(String type_code_6) {
		this.type_code_6 = type_code_6;
	}

	public String getType_code_7() {
		return type_code_7;
	}

	public void setType_code_7(String type_code_7) {
		this.type_code_7 = type_code_7;
	}

	public String getType_code_8() {
		return type_code_8;
	}

	public void setType_code_8(String type_code_8) {
		this.type_code_8 = type_code_8;
	}

	public String getType_code_9() {
		return type_code_9;
	}

	public void setType_code_9(String type_code_9) {
		this.type_code_9 = type_code_9;
	}

}
