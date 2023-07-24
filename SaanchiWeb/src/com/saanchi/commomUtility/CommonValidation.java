package com.saanchi.commomUtility;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


public class CommonValidation {
	private static final Logger log = Logger.getLogger(CommonValidation.class);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyy");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");

	public boolean requiredFiledValidate(String fieldName) {
		boolean returnValue = true;

		if ((fieldName == null) || (fieldName.length() == 0)) {
			System.out.println("In validator " + fieldName + " = " + fieldName.length());
			returnValue = false;
		}

		return returnValue;
	}

	public boolean futureDateFiledValidate(Date fieldName) {
		boolean returnValue = true;
		Date currentDate = new Date();
		try {
			if (fieldName.compareTo(currentDate) >= 0) {
				returnValue = false;
			}
		} catch (Exception e) {
			returnValue = false;
			log.fatal("ERROR ", e);
		}

		return returnValue;
	}

	public boolean neumericFiledLengthValidate(String fieldName, int digit) {
		Pattern mobNO = Pattern.compile("\\d{" + digit + "}");
		Matcher matcher = mobNO.matcher(fieldName);
		if (matcher.find()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean dependentFiledValidate(String fieldName1, String fieldName2) {
		boolean returnValue = true;

		if (((fieldName1 == null) || (fieldName1.length() == 0)) && (fieldName2 != null)) {
			returnValue = false;
		} else if (((fieldName2 == null) || (fieldName2.length() == 0)) && (fieldName1 != null)) {
			returnValue = false;
		} else if ((fieldName1 == null) && (fieldName2 == null)) {
			returnValue = true;
		}

		return returnValue;
	}

	public boolean panFiledValidate(String fieldName) {
		Pattern pan = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
		Matcher matcher = pan.matcher(fieldName);
		if (matcher.find()) {
			return true;
		} else if ((fieldName.equalsIgnoreCase("FORM 60")) || (fieldName.equalsIgnoreCase("FORM 61"))) {
			return true;
		}

		else {
			return false;
		}
	}

	public boolean validateNameField(String fieldValue, String language) {
		String regxEnglish = "[a-zA-Z]+\\.?";
		if (language.equalsIgnoreCase("1")) {
			Pattern pattern = Pattern.compile(regxEnglish);
			Matcher matcher = pattern.matcher(fieldValue);
			return matcher.find();
		} else
			return true;
	}

	public boolean validateIntegerField(String fieldValue) {
		String regxEnglish = "[0-9]";
		Pattern pattern = Pattern.compile(regxEnglish);
		Matcher matcher = pattern.matcher(fieldValue);
		return matcher.find();

	}

	public boolean languageValidate(String fieldValue, String language) {

		if (language.equalsIgnoreCase("1") && isEnglish(fieldValue))
			return true;
		else if (language.equalsIgnoreCase("2") && (!isEnglish(fieldValue)))
			return true;
		else
			return false;
	}

	private static boolean isEnglish(String text) {

		CharsetEncoder asciiEncoder = Charset.forName("US-ASCII").newEncoder();
		CharsetEncoder isoEncoder = Charset.forName("ISO-8859-1").newEncoder();
		return asciiEncoder.canEncode(text) || isoEncoder.canEncode(text);
	}

	public boolean neumericFiledValidate(String fieldName) {
		try {
			NumberFormat.getInstance().parse(fieldName);
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	public boolean neumericFiledValidate(String fieldName, int digit) {
		Pattern mobNO = Pattern.compile("\\d{" + digit + "}");
		Matcher matcher = mobNO.matcher(fieldName);
		if (matcher.find()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean khasraFiledValidate(String fieldName) {

		if (fieldName.matches("[a-zA-Z0-9]+"))
			return true;
		else
			return false;

	}

	public boolean ageFiledValidate(String fieldName, int minValue) {
		try {
			if (Integer.parseInt(fieldName) > minValue)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean mailFiledValidate(String fieldName) {
		Pattern mail = Pattern
				.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher matcher = mail.matcher(fieldName);
		if (matcher.find()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean genderFiledValidate(String gender, String occupation, String title) {
		boolean returnValue = true;

		if (gender.equalsIgnoreCase("MALE") && (occupation.equalsIgnoreCase("Housewife")))
			returnValue = false;

		else if (((title.equalsIgnoreCase("BABY") || title.equalsIgnoreCase("BIBI") || title.equalsIgnoreCase("KUMARI")
				|| title.equalsIgnoreCase("MISS") || title.equalsIgnoreCase("MRS") || title.equalsIgnoreCase("MS")
				|| title.equalsIgnoreCase("SMT"))) && (gender.equalsIgnoreCase("MALE")))
			returnValue = false;

		else if (((title.equalsIgnoreCase("MASTER") || title.equalsIgnoreCase("MD") || title.equalsIgnoreCase("MR")
				|| title.equalsIgnoreCase("SAH") || title.equalsIgnoreCase("SHEIKH") || title.equalsIgnoreCase("SHRI")))
				&& (gender.equalsIgnoreCase("FEMALE")))
			returnValue = false;
		else if (((title.equalsIgnoreCase("MASTER") || title.equalsIgnoreCase("MD") || title.equalsIgnoreCase("MR")
				|| title.equalsIgnoreCase("SAH") || title.equalsIgnoreCase("SHEIKH") || title.equalsIgnoreCase("SHRI")))
				&& (occupation.equalsIgnoreCase("Housewife")))
			returnValue = false;

		return returnValue;
	}

	public boolean amountFiledValidate(String fieldName) {
		boolean returnValue = false;
		try {

			Float.parseFloat(fieldName);
			if (Float.parseFloat(fieldName) >= 0)
				returnValue = true;
			else
				returnValue = false;
		} catch (Exception e) {
			returnValue = false;
			log.fatal("Exception---", e);
		}
		return returnValue;

	}

	public boolean DateDifferenceValidate(Date fieldName, int differenceValue) {
		boolean returnValue = true;

		long MILLISECS_PER_DAY = 24 * 60 * 60 * 1000;
		long days = 0l;
		Date currentDate = new Date();

		try {
			days = (currentDate.getTime() - fieldName.getTime()) / MILLISECS_PER_DAY;

			if (days > differenceValue)
				returnValue = false;
			else
				returnValue = true;

		} catch (Exception e) {
			log.fatal("Exception---", e);
		}

		return returnValue;

	}

	public boolean OldDateFiledValidate(Date fieldName) {
		boolean returnValue = true;
		Date currentDate = new Date();
		try {

			if (sdf.parse(sdf.format(fieldName)).compareTo(sdf.parse(sdf.format(currentDate))) < 0) {
				returnValue = false;
			}
		} catch (Exception e) {
			returnValue = false;
			log.fatal("Exception---", e);
		}

		return returnValue;
	}

	public boolean dateFormate(String dateValue) {

		Pattern mail = Pattern.compile("((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])");
		
		System.out.println(dateValue.trim());
		

		Matcher matcher = mail.matcher(dateValue.trim());
		if (matcher.find()) {			
			return true;
		} else {
			return false;
		}

	}
	
	
	public boolean dateFormateEdit(String dateValue) {

		Pattern mail = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])([-])(0?[1-9]|1[012])([-])((19|20)\\d\\d)");
		
		System.out.println(dateValue.trim());
		

		Matcher matcher = mail.matcher(dateValue.trim());
		if (matcher.find()) {			
			return true;
		} else {
			return false;
		}

	}

	public boolean dateCompair(String issueDate, String closeDate) {
		boolean returnValue = false;

		try {
			Date IssueDate = sdf.parse(issueDate);
			Date CloseDate = sdf.parse(closeDate);

			if (IssueDate.compareTo(CloseDate) > 0)
				returnValue = false;
			else
				returnValue = true;

		} catch (ParseException e) {
			log.fatal("ERROR ", e);
		}

		return returnValue;
	}

	
	public boolean checkSpecialCharacter(String stringValue) {
		boolean returnValue = false;
		 Pattern p = Pattern.compile(
		            "[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		 
		 Matcher m = p.matcher(stringValue);
		 returnValue = m.find();
		 
		
		return returnValue;

	}
	
}
