/**
 * Class Name  : OptionsVO
 * Description : Can Store the Options in a Drop down List
 * Created by  : TCS/Sabyasachi Bhattacharya
 * Created on  : 7-SEP-04
 * Modified    : [Who , When , What]
 */
package com.saanchi.commomUtility;
import java.io.Serializable;


public class CommonOptionsVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String typeCode;
	private String typeDesc;
    private String typeCodeDesc;

	public CommonOptionsVO(String code, String desc) {
		setTypeCode(code);
		setTypeDesc(desc);
		setTypeCodeDesc(code,desc);
	}

		/**
	 * @return
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * @return
	 */
	public String getTypeDesc() {
		return typeDesc;
	}

	/**
	 * @param string
	 */
	public void setTypeCode(String string) {
		typeCode = string;
	}

	/**
	 * @param string
	 */
	public void setTypeDesc(String string) {
		typeDesc = string;
	}

	
	

	/**
	 * @return
	 */
	public String getTypeCodeDesc() {
		return typeCodeDesc;
	}

	/**
	 * @param string
	 */
	public void setTypeCodeDesc(String string1,String string2 ) {
		typeCodeDesc = string1.trim()+" ~ "+string2.trim();
	}

}
