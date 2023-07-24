package com.saanchi.commomUtility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.commons.codec.binary.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.jboss.security.Base64Encoder;

import com.saanchi.hibernate.model.ChangeRequestStage;
import com.saanchi.hibernate.model.FPNExportStage;
import com.saanchi.hibernate.model.FPNExport_Infringements_Validated;
import com.saanchi.hibernate.model.FeeExportStage;
import com.saanchi.hibernate.model.PAExportStage;
import com.saanchi.hibernate.util.HibernateManager;
import com.saanchi.optionVO.ChangeRequestVO;
import com.saanchi.optionVO.FPNValidationVO;
import com.saanchi.optionVO.PAValidateVO;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CommonHelper {
	private static final Logger log = Logger.getLogger(CommonHelper.class);
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
	SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm");
	SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy");

	public Date combineDateTime(Date date, Date time) {
		Calendar calendarA = Calendar.getInstance();
		calendarA.setTime(date);
		Calendar calendarB = Calendar.getInstance();
		calendarB.setTime(time);

		calendarA.set(Calendar.HOUR_OF_DAY, calendarB.get(Calendar.HOUR_OF_DAY));
		calendarA.set(Calendar.MINUTE, calendarB.get(Calendar.MINUTE));
		calendarA.set(Calendar.SECOND, calendarB.get(Calendar.SECOND));
		calendarA.set(Calendar.MILLISECOND, calendarB.get(Calendar.MILLISECOND));

		Date result = calendarA.getTime();
		return result;
	}

	/*
	 * public String passwordEncoder(String password) throws
	 * NoSuchAlgorithmException, IOException {
	 * 
	 * String algoritmo = "MD5"; String hashedPassword = null; // DEFAULT PASSWORD=
	 * 123456 4QrcOUm6Wau+VuBX8g+IPg==
	 * 
	 * byte[] hash =
	 * MessageDigest.getInstance(algoritmo).digest(password.getBytes());
	 * hashedPassword = Base64Encoder.encode(hash);
	 * 
	 * return hashedPassword; }
	 */

	public StringWriter writeLogInUpperCase(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw;
	}

	public List<FPNValidationVO> FPNExportValidation(List<FPNExportStage> fpnStageList) {
		List<FPNValidationVO> fpnValidationLogicList = new ArrayList<FPNValidationVO>();

		ListIterator<FPNExportStage> listItrStage = fpnStageList.listIterator();
		while (listItrStage.hasNext()) {
			FPNExportStage fpnStageModel = new FPNExportStage();
			FPNValidationVO fpnVO = new FPNValidationVO();

			fpnStageModel = (FPNExportStage) listItrStage.next();

			if (fpnStageModel.getWorks_reference_number() == null
					|| fpnStageModel.getWorks_reference_number().trim().length() == 0) {
				fpnVO.setWorks_reference_number_validate(false);

			} else {
				fpnVO.setWorks_reference_number_validate(true);
			}

			if (fpnStageModel.getFpn_number() == null || fpnStageModel.getFpn_number().trim().length() == 0) {
				fpnVO.setFpn_number_validate(false);

			} else {
				fpnVO.setFpn_number_validate(true);
			}

			if (fpnStageModel.getFpn_status() == null || fpnStageModel.getFpn_status().trim().length() == 0) {
				fpnVO.setFpn_status_validate(false);

			} else if ((!fpnStageModel.getFpn_status().equals("Issued"))
					&& (!fpnStageModel.getFpn_status().equals("Disputed"))
					&& (!fpnStageModel.getFpn_status().equals("Withdrawn"))
					&& (!fpnStageModel.getFpn_status().equals("Accepted"))
					&& (!fpnStageModel.getFpn_status().equals("Paid"))
					&& (!fpnStageModel.getFpn_status().equals("Paid (Discounted)")))
			// AS PER KR SUGGEEST ON 21/12/2021 WE ALLOW A SPACE BETWEEN Paid(Discount)
			{

				fpnVO.setFpn_status_validate(false);
			} else {
				fpnVO.setFpn_status_validate(true);
			}

			if (fpnStageModel.getOffence_code() == null || fpnStageModel.getOffence_code().trim().length() == 0) {
				fpnVO.setOffence_code_validate(false);

			} else if ((!fpnStageModel.getOffence_code().equals("05 - Section 70"))
					&& (!fpnStageModel.getOffence_code().equals("06 - Section 74"))
					&& (!fpnStageModel.getOffence_code().equals("09 - Regulation 20"))
					&& (!fpnStageModel.getOffence_code().equals("08 - Regulation 19"))) {

				fpnVO.setOffence_code_validate(false);
			} else {
				fpnVO.setOffence_code_validate(true);
			}

			if (fpnStageModel.getOffence_date() == null) {
				fpnVO.setOffence_date_validate(false);

			} else {
				fpnVO.setOffence_date_validate(true);
			}

			if (fpnStageModel.getOffence_details() == null || fpnStageModel.getOffence_details().trim().length() == 0) {
				fpnVO.setOffence_details_validate(false);

			} else {
				fpnVO.setOffence_details_validate(true);
			}

			if (fpnStageModel.getIssue_date_time() == null) {
				fpnVO.setIssue_date_time_validate(false);

			} else {
				fpnVO.setIssue_date_time_validate(true);
			}

			if (fpnStageModel.getStreet_name() == null || fpnStageModel.getStreet_name().trim().length() == 0) {
				fpnVO.setStreet_name_validate(false);

			} else {
				fpnVO.setStreet_name_validate(true);
			}

			if (fpnStageModel.getUsrn() == null || fpnStageModel.getUsrn().toString().length() != 8) {
				fpnVO.setUern_validate(false);

			} else {
				fpnVO.setUern_validate(true);
			}

			if (fpnStageModel.getStreet_name() == null || fpnStageModel.getStreet_name().trim().length() == 0) {
				fpnVO.setWorks_promoter_validate(false);

			} else {
				fpnVO.setWorks_promoter_validate(true);
			}

			if (fpnStageModel.getIssuing_authority() == null
					|| fpnStageModel.getIssuing_authority().trim().length() == 0) {
				fpnVO.setIssuing_authority_validate(false);

			} else {
				fpnVO.setIssuing_authority_validate(true);
			}

			if (fpnStageModel.getStatus_changed_date() == null) {
				fpnVO.setStatus_changed_date_validate(false);

			} else {
				fpnVO.setStatus_changed_date_validate(true);
			}

			if (((fpnStageModel.getFpn_status().trim().equalsIgnoreCase("Issued"))
					|| (fpnStageModel.getFpn_status().trim().equalsIgnoreCase("Disputed"))
					|| (fpnStageModel.getFpn_status().trim().equalsIgnoreCase("Withdrawn"))
					|| (fpnStageModel.getFpn_status().trim().equalsIgnoreCase("Accepted")))
					&& (!fpnStageModel.getAmount_paid().equals(new BigDecimal(0)))) {
				fpnVO.setAmount_paid_validate(false);

			} else if (fpnStageModel.getFpn_status().trim().equalsIgnoreCase("Paid")) {

				if (((fpnStageModel.getOffence_code().trim().equalsIgnoreCase("05 - Section 70"))
						|| (fpnStageModel.getOffence_code().trim().equalsIgnoreCase("06 - Section 74"))
						|| (fpnStageModel.getOffence_code().trim().equalsIgnoreCase("09 - Regulation 20")))
						&& (!fpnStageModel.getAmount_paid().equals(new BigDecimal(120)))) {
					fpnVO.setAmount_paid_validate(false);
				}

				else if (fpnStageModel.getOffence_code().trim().equalsIgnoreCase("08 - Regulation 19")
						&& (!fpnStageModel.getAmount_paid().equals(new BigDecimal(500)))) {
					fpnVO.setAmount_paid_validate(false);
				}

				else
					fpnVO.setAmount_paid_validate(true);

			}

			else if (fpnStageModel.getFpn_status().trim().equalsIgnoreCase("Paid (Discounted)")) {

				if (((fpnStageModel.getOffence_code().trim().equalsIgnoreCase("05 - Section 70"))
						|| (fpnStageModel.getOffence_code().trim().equalsIgnoreCase("06 - Section 74"))
						|| (fpnStageModel.getOffence_code().trim().equalsIgnoreCase("09 - Regulation 20")))
						&& (!fpnStageModel.getAmount_paid().equals(new BigDecimal(80))))

				{

					fpnVO.setAmount_paid_validate(false);
				}

				else if (fpnStageModel.getOffence_code().trim().equalsIgnoreCase("08 - Regulation 19")
						&& (!fpnStageModel.getAmount_paid().equals(new BigDecimal(300)))) {

					fpnVO.setAmount_paid_validate(false);
				} else
					fpnVO.setAmount_paid_validate(true);
			} else
				fpnVO.setAmount_paid_validate(true);

			if ((!fpnVO.isWorks_reference_number_validate()) || (!fpnVO.isFpn_number_validate())
					|| (!fpnVO.isFpn_status_validate()) || (!fpnVO.isOffence_code_validate())
					|| (!fpnVO.isOffence_date_validate()) || (!fpnVO.isOffence_details_validate())
					|| (!fpnVO.isIssue_date_time_validate()) || (!fpnVO.isStreet_name_validate())
					|| (!fpnVO.isUern_validate()) || (!fpnVO.isWorks_promoter_validate())
					|| (!fpnVO.isIssuing_authority_validate()) || (!fpnVO.isStatus_changed_date_validate())
					|| (!fpnVO.isAmount_paid_validate())) {

				fpnVO.setFpn_export_stage_id(fpnStageModel.getFpn_export_stage_id());
				fpnVO.setWorks_reference_number(fpnStageModel.getWorks_reference_number());
				fpnVO.setFpn_number(fpnStageModel.getFpn_number());
				fpnVO.setFpn_status(fpnStageModel.getFpn_status());
				fpnVO.setOffence_code(fpnStageModel.getOffence_code());
				fpnVO.setOffence_date(
						fpnStageModel.getOffence_date() == null ? null : sdf1.format(fpnStageModel.getOffence_date()));
				fpnVO.setOffence_details(fpnStageModel.getOffence_details());
				fpnVO.setIssue_date_time(fpnStageModel.getIssue_date_time() == null ? null
						: sdf.format(fpnStageModel.getIssue_date_time()));
				fpnVO.setStreet_name(fpnStageModel.getStreet_name());
				fpnVO.setUsrn(fpnStageModel.getUsrn());
				fpnVO.setWorks_promoter(fpnStageModel.getWorks_promoter());
				fpnVO.setIssuing_authority(fpnStageModel.getIssuing_authority());
				fpnVO.setStatus_changed_date(fpnStageModel.getStatus_changed_date() == null ? null
						: sdf.format(fpnStageModel.getStatus_changed_date()));
				fpnVO.setAmount_paid(fpnStageModel.getAmount_paid());

				fpnValidationLogicList.add(fpnVO);

			}
		}

		System.out.println("Error Record Count is " + fpnValidationLogicList.size());
		return fpnValidationLogicList;
	}

	public List<PAValidateVO> PAExportValidation(List<PAExportStage> paStageList) throws ParseException {
		List<PAValidateVO> paValidationLogicList = new ArrayList<PAValidateVO>();

		ListIterator<PAExportStage> listItrStage = paStageList.listIterator();
		while (listItrStage.hasNext()) {
			PAExportStage paStageModel = new PAExportStage();
			PAValidateVO paVO = new PAValidateVO();

			paStageModel = (PAExportStage) listItrStage.next();

			if (paStageModel.getPromoter_name() == null || paStageModel.getPromoter_name().trim().length() == 0) {
				paVO.setPromoter_name_validate(false);

			} else {
				paVO.setPromoter_name_validate(true);
			}

			if (paStageModel.getWork_reference_number() == null
					|| paStageModel.getWork_reference_number().trim().length() == 0) {
				paVO.setWork_reference_number_validate(false);

			} else {
				paVO.setWork_reference_number_validate(true);
			}

			if (paStageModel.getPermit_reference_number() == null
					|| paStageModel.getPermit_reference_number().trim().length() == 0) {
				paVO.setPermit_reference_number_validate(false);

			} else {
				paVO.setPermit_reference_number_validate(true);
			}

			if (paStageModel.getApplication_type() == null || paStageModel.getApplication_type().trim().length() == 0) {
				paVO.setApplication_type_validate(false);

			} else if ((!paStageModel.getApplication_type().trim().equals("PAA"))
					&& (!paStageModel.getApplication_type().trim().equals("Permit"))
					&& (!paStageModel.getApplication_type().trim().equals("Variation"))) {

				paVO.setApplication_type_validate(false);
			} else {
				paVO.setApplication_type_validate(true);
			}

			if (paStageModel.getType_of_work() == null || paStageModel.getType_of_work().trim().length() == 0) {
				paVO.setType_of_work_validate(false);

			} else if ((!paStageModel.getType_of_work().contains("Major"))
					&& (!paStageModel.getType_of_work().contains("Standard"))
					&& (!paStageModel.getType_of_work().contains("Minor"))
					&& (!paStageModel.getType_of_work().contains("Immediate"))) {

				paVO.setType_of_work_validate(false);
			} else {
				paVO.setType_of_work_validate(true);
			}

			if (paStageModel.getLocation() == null || paStageModel.getLocation().trim().length() == 0) {
				paVO.setLocation_validate(false);

			} else {
				paVO.setLocation_validate(true);
			}

			if (paStageModel.getStreet() == null || paStageModel.getStreet().trim().length() == 0) {
				paVO.setStreet_validate(false);

			} else {
				paVO.setStreet_validate(true);
			}

			if (paStageModel.getTown() == null || paStageModel.getTown().trim().length() == 0) {
				paVO.setTown_validate(false);

			} else {
				paVO.setTown_validate(true);
			}

			if (paStageModel.getUsrn() == null || paStageModel.getUsrn().toString().length() != 8) {
				paVO.setUsrn_validate(false);

			} else {
				paVO.setUsrn_validate(true);
			}

			if (paStageModel.getActivity_type() == null || paStageModel.getActivity_type().trim().length() == 0) {
				paVO.setActivity_type_validate(false);

			} else {
				paVO.setActivity_type_validate(true);
			}

			if (paStageModel.getWork_description() == null || paStageModel.getWork_description().trim().length() == 0) {
				paVO.setWork_description_validate(false);

			} else {
				paVO.setWork_description_validate(true);
			}

			if (paStageModel.getDuration_of_work() == null || paStageModel.getDuration_of_work().trim().length() == 0) {
				paVO.setDuration_of_work_validate(false);

			} else {
				paVO.setDuration_of_work_validate(true);
			}

			if (paStageModel.getWorks_with_excavation() == null
					|| paStageModel.getWorks_with_excavation().trim().length() == 0) {
				paVO.setWorks_with_excavation_validate(false);

			} else if ((!paStageModel.getWorks_with_excavation().contains("Yes"))
					&& (!paStageModel.getWorks_with_excavation().contains("No"))) {

				paVO.setWorks_with_excavation_validate(false);
			} else {
				paVO.setWorks_with_excavation_validate(true);
			}

			if (paStageModel.getReinstatement_category() == null
					|| paStageModel.getReinstatement_category().trim().length() == 0) {
				paVO.setReinstatement_category_validate(false);

			}

			else if ((new BigDecimal(paStageModel.getReinstatement_category()).compareTo(new BigDecimal(0)) != 0)
					&& (new BigDecimal(paStageModel.getReinstatement_category()).compareTo(new BigDecimal(1)) != 0)
					&& (new BigDecimal(paStageModel.getReinstatement_category()).compareTo(new BigDecimal(2)) != 0)
					&& (new BigDecimal(paStageModel.getReinstatement_category()).compareTo(new BigDecimal(3)) != 0)
					&& (new BigDecimal(paStageModel.getReinstatement_category()).compareTo(new BigDecimal(4)) != 0)
					&& (new BigDecimal(paStageModel.getReinstatement_category()).compareTo(new BigDecimal(5)) != 0)
					&& (new BigDecimal(paStageModel.getReinstatement_category()).compareTo(new BigDecimal(6)) != 0)
					&& (new BigDecimal(paStageModel.getReinstatement_category()).compareTo(new BigDecimal(7)) != 0)
					&& (new BigDecimal(paStageModel.getReinstatement_category()).compareTo(new BigDecimal(8)) != 0)
					&& (new BigDecimal(paStageModel.getReinstatement_category()).compareTo(new BigDecimal(9)) != 0)) {

				paVO.setReinstatement_category_validate(false);
			} else {
				paVO.setReinstatement_category_validate(true);
			}

			if (paStageModel.getTraffic_management_method() == null
					|| paStageModel.getTraffic_management_method().trim().length() == 0) {
				paVO.setTraffic_management_method_validate(false);

			} else if ((!paStageModel.getTraffic_management_method().contains("Some carriageway incursion"))
					&& (!paStageModel.getTraffic_management_method().contains("Give and take"))
					&& (!paStageModel.getTraffic_management_method().contains("Lane closure"))
					&& (!paStageModel.getTraffic_management_method().contains("No carriageway incursion"))
					&& (!paStageModel.getTraffic_management_method().contains("Two-way signals"))
					&& (!paStageModel.getTraffic_management_method().contains("Stop/go boards"))
					&& (!paStageModel.getTraffic_management_method().contains("Multi-way signals"))
					&& (!paStageModel.getTraffic_management_method().contains("Road closure"))
					&& (!paStageModel.getTraffic_management_method().contains("Priority working"))
					&& (!paStageModel.getTraffic_management_method().contains("Contra-flow"))
					&& (!paStageModel.getTraffic_management_method().contains("Convoy workings"))) {

				paVO.setTraffic_management_method_validate(false);
			} else {
				paVO.setTraffic_management_method_validate(true);
			}

			if (paStageModel.getCollaborative_working() == null
					|| paStageModel.getCollaborative_working().trim().length() == 0) {
				paVO.setCollaborative_working_validate(false);

			} else if ((!paStageModel.getCollaborative_working().contains("Yes"))
					&& (!paStageModel.getCollaborative_working().contains("No"))) {

				paVO.setCollaborative_working_validate(false);
			} else {
				paVO.setCollaborative_working_validate(true);
			}

			if (paStageModel.getEastimated_start_date() == null) {
				paVO.setEastimated_start_date_validate(false);

			} else {
				paVO.setEastimated_start_date_validate(true);
			}

			if (paStageModel.getProposed_start_time() == null) {
				paVO.setProposed_start_time_validate(false);

			} else {
				paVO.setProposed_start_time_validate(true);
			}

			if (paStageModel.getProposed_end_date() == null) {
				paVO.setProposed_end_date_validate(false);

			} else {
				paVO.setProposed_end_date_validate(true);
			}

			if (paStageModel.getProposed_end_time() == null) {
				paVO.setProposed_end_time_validate(false);

			} else {
				paVO.setProposed_end_time_validate(true);
			}

			if (paStageModel.getDuration_of_work() == null) {
				paVO.setDuration_of_work_validate(false);

			} else {
				paVO.setDuration_of_work_validate(true);
			}

			if (paStageModel.getPermit_status() == null || paStageModel.getPermit_status().trim().length() == 0) {
				paVO.setPermit_status_validate(false);

			} else if ((!paStageModel.getPermit_status().contains("Granted"))
					&& (!paStageModel.getPermit_status().contains("Refused"))
					&& (!paStageModel.getPermit_status().contains("Permit-Modification-Request"))
					&& (!paStageModel.getPermit_status().contains("Deemed"))
					&& (!paStageModel.getPermit_status().contains("Cancelled"))
					&& (!paStageModel.getPermit_status().contains("Pending"))
					&& (!paStageModel.getPermit_status().contains("Granted(Auto)"))) {

				paVO.setPermit_status_validate(false);
			} else {
				paVO.setPermit_status_validate(true);
			}

			if (paStageModel.getPermit_deeming_date() == null) {
				paVO.setPermit_deeming_date_validate(false);

			} else {
				paVO.setPermit_deeming_date_validate(true);
			}

			if (paStageModel.getDiscount() == null) {
				paVO.setDiscount_validate(false);

			} else {
				paVO.setDiscount_validate(true);
			}

			if (paStageModel.getDate_of_permits() == null) {
				paVO.setDate_of_permits_validate(false);

			} else {
				paVO.setDate_of_permits_validate(true);
			}

			if ((!paVO.isActivity_type_validate()) || (!paVO.isApplication_type_validate())
					|| (!paVO.isCollaborative_working_validate()) || (!paVO.isDate_of_permits_validate())
					|| (!paVO.isDiscount_validate()) || (!paVO.isDuration_of_work_validate())
					|| (!paVO.isEastimated_start_date_validate()) || (!paVO.isLocation_validate())
					|| (!paVO.isPermit_deeming_date_validate()) || (!paVO.isPermit_reference_number_validate())
					|| (!paVO.isPermit_status_validate()) || (!paVO.isProposed_end_date_validate())
					|| (!paVO.isProposed_end_time_validate()) || (!paVO.isProposed_start_time_validate())
					|| (!paVO.isReinstatement_category_validate()) || (!paVO.isStreet_validate())
					|| (!paVO.isTown_validate()) || (!paVO.isTraffic_management_method_validate())
					|| (!paVO.isType_of_work_validate()) || (!paVO.isUsrn_validate())
					|| (!paVO.isWork_description_validate()) || (!paVO.isWork_reference_number_validate())
					|| (!paVO.isWorks_with_excavation_validate())) {

				paVO.setDiscount(paStageModel.getDiscount());
				paVO.setUsrn(paStageModel.getUsrn());
				paVO.setDate_of_permits(paStageModel.getDate_of_permits() == null ? null
						: sdf1.format(paStageModel.getDate_of_permits()));
				paVO.setEastimated_start_date(paStageModel.getEastimated_start_date() == null ? null
						: sdf.format(paStageModel.getEastimated_start_date()));
				paVO.setPermit_deeming_date(paStageModel.getPermit_deeming_date() == null ? null
						: sdf.format(paStageModel.getPermit_deeming_date()));
				paVO.setProposed_end_date(paStageModel.getProposed_end_date() == null ? null
						: sdf.format(paStageModel.getProposed_end_date()));
				paVO.setProposed_end_time(paStageModel.getProposed_end_time() == null ? null
						: sdf2.format(paStageModel.getProposed_end_time()));
				paVO.setProposed_start_time(paStageModel.getProposed_start_time() == null ? null
						: sdf2.format(paStageModel.getProposed_start_time()));
				paVO.setPa_export_stage_id(paStageModel.getPa_export_stage_id());
				paVO.setActivity_type(paStageModel.getActivity_type());
				paVO.setApplication_type(paStageModel.getApplication_type());
				paVO.setCollaborative_working(paStageModel.getCollaborative_working());
				paVO.setDuration_of_work(paStageModel.getDuration_of_work());
				paVO.setLocation(paStageModel.getLocation());
				paVO.setPermit_reference_number(paStageModel.getPermit_reference_number());
				paVO.setPermit_status(paStageModel.getPermit_status());
				paVO.setPromoter_name(paStageModel.getPromoter_name());
				paVO.setReinstatement_category(paStageModel.getReinstatement_category());
				paVO.setStreet(paStageModel.getStreet());
				paVO.setTown(paStageModel.getTown());
				paVO.setTraffic_management_method(paStageModel.getTraffic_management_method());
				paVO.setType_of_work(paStageModel.getType_of_work());
				paVO.setWork_description(paStageModel.getWork_description());
				paVO.setWork_reference_number(paStageModel.getWork_reference_number());
				paVO.setWorks_with_excavation(paStageModel.getWorks_with_excavation());

				System.out.println(paVO.getProposed_start_time());
				paValidationLogicList.add(paVO);

			}
		}

		System.out.println("Error Record Count is " + paValidationLogicList.size());
		return paValidationLogicList;
	}

	public List<FPNExportStage> getAllDataForFPNStage(HttpServletRequest request, String rowNumber) {

		System.out.println("My Request " + request);
		List<FPNExportStage> alledited = new ArrayList<FPNExportStage>();
		try {
			if ((rowNumber != null) && (rowNumber.trim().length() > 0)) {
				String fpn_export_stage_id[] = request.getParameterValues("fpn_export_stage_id");
				String works_reference_number[] = request.getParameterValues("works_reference_number");
				String fpn_number[] = request.getParameterValues("fpn_number");
				String fpn_status[] = request.getParameterValues("fpn_status");
				String offence_code[] = request.getParameterValues("offence_code");
				String offence_date[] = request.getParameterValues("offence_date");
				String offence_details[] = request.getParameterValues("offence_details");
				String issue_date_time[] = request.getParameterValues("issue_date_time");
				String street_name[] = request.getParameterValues("street_name");
				String usrn[] = request.getParameterValues("usrn");
				String works_promoter[] = request.getParameterValues("works_promoter");
				String issuing_authority[] = request.getParameterValues("issuing_authority");
				String status_changed_date[] = request.getParameterValues("status_changed_date");
				String amount_paid[] = request.getParameterValues("amount_paid");

				log.fatal("My Row Number is " + rowNumber);

				for (int row = 0; row < Integer.parseInt(rowNumber); row++) {

					FPNExportStage stageModel = new FPNExportStage();
					stageModel.setFpn_export_stage_id(Integer.parseInt(fpn_export_stage_id[row]));
					if (works_reference_number != null)
						stageModel.setWorks_reference_number(works_reference_number[row]);

					if (works_reference_number != null)
						stageModel.setWorks_reference_number(works_reference_number[row]);

					if (fpn_number != null)
						stageModel.setFpn_number(fpn_number[row]);

					if (fpn_status != null)
						stageModel.setFpn_status(fpn_status[row]);

					if (offence_code != null)
						stageModel.setOffence_code(offence_code[row]);

					if (offence_date != null)
						stageModel.setOffence_date(sdf1.parse(offence_date[row]));

					if (offence_details != null)
						stageModel.setOffence_details(offence_details[row]);

					if (issue_date_time != null)
						stageModel.setIssue_date_time(sdf.parse(issue_date_time[row]));

					if (street_name != null)
						stageModel.setStreet_name(street_name[row]);

					if (usrn != null)
						stageModel.setUsrn(new BigDecimal(usrn[row]));

					if (works_promoter != null)
						stageModel.setWorks_promoter(works_promoter[row]);

					if (issuing_authority != null)
						stageModel.setWorks_reference_number(issuing_authority[row]);

					if (works_reference_number != null)
						stageModel.setIssuing_authority(works_reference_number[row]);

					if (status_changed_date != null)
						stageModel.setStatus_changed_date(sdf.parse(status_changed_date[row]));

					if (amount_paid != null)
						stageModel.setAmount_paid(new BigDecimal(amount_paid[row]));

					alledited.add(stageModel);

				}
			}
		} catch (Exception e) {
			log.fatal("ERROR ", e);
		}

		return alledited;
	}

	public List<PAExportStage> getAllDataForPAStage(HttpServletRequest request, String rowNumber) {

		System.out.println("My Request " + request);
		List<PAExportStage> alledited = new ArrayList<PAExportStage>();
		try {
			if ((rowNumber != null) && (rowNumber.trim().length() > 0)) {
				String pa_export_stage_id[] = request.getParameterValues("pa_export_stage_id");
				String date_of_permits[] = request.getParameterValues("date_of_permits");
				String promoter_name[] = request.getParameterValues("promoter_name");
				String work_reference_number[] = request.getParameterValues("work_reference_number");
				String permit_reference_number[] = request.getParameterValues("permit_reference_number");
				String application_type[] = request.getParameterValues("application_type");
				String type_of_work[] = request.getParameterValues("type_of_work");
				String location[] = request.getParameterValues("location");
				String street[] = request.getParameterValues("street");
				String town[] = request.getParameterValues("town");
				String usrn[] = request.getParameterValues("usrn");
				String activity_type[] = request.getParameterValues("activity_type");
				String work_description[] = request.getParameterValues("work_description");
				String works_with_excavation[] = request.getParameterValues("works_with_excavation");
				String reinstatement_category[] = request.getParameterValues("reinstatement_category");
				String traffic_management_method[] = request.getParameterValues("traffic_management_method");
				String collaborative_working[] = request.getParameterValues("collaborative_working");
				String eastimated_start_date[] = request.getParameterValues("eastimated_start_date");
				String proposed_start_time[] = request.getParameterValues("proposed_start_time");
				String proposed_end_date[] = request.getParameterValues("proposed_end_date");
				String proposed_end_time[] = request.getParameterValues("proposed_end_time");
				String duration_of_work[] = request.getParameterValues("duration_of_work");
				String permit_status[] = request.getParameterValues("permit_status");
				String permit_deeming_date[] = request.getParameterValues("permit_deeming_date");
				String discount[] = request.getParameterValues("discount");

				log.fatal("My Row Number is " + rowNumber);

				for (int row = 0; row < Integer.parseInt(rowNumber); row++) {

					PAExportStage stageModel = new PAExportStage();
					stageModel.setPa_export_stage_id(Integer.parseInt(pa_export_stage_id[row]));

					if (date_of_permits != null)
						stageModel.setDate_of_permits(sdf1.parse(date_of_permits[row]));

					if (promoter_name != null)
						stageModel.setPromoter_name(promoter_name[row]);

					if (work_reference_number != null)
						stageModel.setWork_reference_number(work_reference_number[row]);

					if (permit_reference_number != null)
						stageModel.setPermit_reference_number(permit_reference_number[row]);

					if (application_type != null)
						stageModel.setApplication_type(application_type[row]);

					if (type_of_work != null)
						stageModel.setType_of_work(type_of_work[row]);

					if (location != null)
						stageModel.setLocation(location[row]);

					if (street != null)
						stageModel.setStreet(street[row]);

					if (town != null)
						stageModel.setTown(town[row]);

					if (usrn != null)
						stageModel.setUsrn(new BigDecimal(usrn[row]));

					if (activity_type != null)
						stageModel.setActivity_type(activity_type[row]);

					if (work_description != null)
						stageModel.setWork_description(work_description[row]);

					if (works_with_excavation != null)
						stageModel.setWorks_with_excavation(works_with_excavation[row]);

					if (reinstatement_category != null)
						stageModel.setReinstatement_category(reinstatement_category[row]);

					if (traffic_management_method != null)
						stageModel.setTraffic_management_method(traffic_management_method[row]);

					if (collaborative_working != null)
						stageModel.setCollaborative_working(collaborative_working[row]);

					if (eastimated_start_date != null)
						stageModel.setEastimated_start_date(sdf.parse(eastimated_start_date[row]));

					if (proposed_start_time != null)
						stageModel.setProposed_start_time(sdf2.parse(proposed_start_time[row]));

					if (proposed_end_date != null)
						stageModel.setProposed_end_date(sdf.parse(proposed_end_date[row]));

					if (proposed_end_time != null)
						stageModel.setProposed_end_time(sdf2.parse(proposed_start_time[row]));

					if (duration_of_work != null)
						stageModel.setDuration_of_work(duration_of_work[row]);

					if (permit_status != null)
						stageModel.setPermit_status(permit_status[row]);

					if (permit_deeming_date != null)
						stageModel.setPermit_deeming_date(sdf.parse(permit_deeming_date[row]));

					if (discount != null)
						stageModel.setDiscount(new BigDecimal(discount[row]));

					alledited.add(stageModel);

				}
			}
		} catch (Exception e) {
			log.fatal("ERROR ", e);
		}

		return alledited;
	}

	public List<ChangeRequestStage> getAllDataForChangeRequestStage(HttpServletRequest request, String rowNumber) {

		System.out.println("My Request " + request);
		List<ChangeRequestStage> alledited = new ArrayList<ChangeRequestStage>();
		try {
			if ((rowNumber != null) && (rowNumber.trim().length() > 0)) {
				String change_request_stage_id[] = request.getParameterValues("change_request_stage_id");
				String change_request_reference_number[] = request
						.getParameterValues("change_request_reference_number");
				String permit_reference_number[] = request.getParameterValues("permit_reference_number");
				String change_request_type[] = request.getParameterValues("change_request_type");
				String application_type[] = request.getParameterValues("application_type");
				String type_of_work[] = request.getParameterValues("type_of_work");
				String change_status[] = request.getParameterValues("change_status");
				String change_submission_date[] = request.getParameterValues("change_submission_date");
				String assessment_discount[] = request.getParameterValues("assessment_discount");
				String pa_proposed_start_date[] = request.getParameterValues("pa_proposed_start_date");
				String pa_proposed_end_date[] = request.getParameterValues("pa_proposed_end_date");
				String duration[] = request.getParameterValues("duration");
				String promoter_name[] = request.getParameterValues("promoter_name");

				log.fatal("My Row Number is " + rowNumber);

				for (int row = 0; row < Integer.parseInt(rowNumber); row++) {

					ChangeRequestStage stageModel = new ChangeRequestStage();
					stageModel.setChange_request_stage_id(Integer.parseInt(change_request_stage_id[row]));
					stageModel.setPromoter_name(promoter_name[row]);

					if (change_request_reference_number != null)
						stageModel.setChange_request_reference_number(change_request_reference_number[row]);

					if (permit_reference_number != null)
						stageModel.setPermit_reference_number(permit_reference_number[row]);

					if (change_request_type != null)
						stageModel.setChange_request_type(change_request_type[row]);

					if (application_type != null)
						stageModel.setApplication_type("Variation");

					if (type_of_work != null)
						stageModel.setType_of_work(type_of_work[row]);

					if (change_status != null)
						stageModel.setChange_status(change_status[row]);

					if (assessment_discount != null)
						stageModel.setAssessment_discount(new BigDecimal(assessment_discount[row]));

					if (change_submission_date != null)
						stageModel.setChange_submission_date((sdf.parse(change_submission_date[row])));

					if (pa_proposed_start_date != null)
						stageModel.setPa_proposed_start_date(((sdf1.parse(pa_proposed_start_date[row]))));

					if (pa_proposed_end_date != null)
						stageModel.setPa_proposed_end_date((((sdf1.parse(pa_proposed_end_date[row])))));

					if (stageModel.getPa_proposed_end_date() != null
							&& stageModel.getPa_proposed_start_date() != null) {

						stageModel.setDuration(new BigDecimal(numberOfDays(stageModel.getPa_proposed_start_date(),
								stageModel.getPa_proposed_end_date())));
					}

					alledited.add(stageModel);

				}
			}
		} catch (Exception e) {
			log.fatal("ERROR ", e);
		}

		return alledited;
	}

	public List<FeeExportStage> getAllDataForFeeExportStage(HttpServletRequest request, String rowNumber) {

		System.out.println("My Request " + request);
		List<FeeExportStage> alledited = new ArrayList<FeeExportStage>();
		try {
			if ((rowNumber != null) && (rowNumber.trim().length() > 0)) {
				String fee_export_stage_id[] = request.getParameterValues("fee_export_stage_id");
				String org_code[] = request.getParameterValues("org_code");
				String org_name[] = request.getParameterValues("org_name");
				String promoter_workstream[] = request.getParameterValues("promoter_workstream");
				String work_category[] = request.getParameterValues("work_category");
				String transaction_type[] = request.getParameterValues("transaction_type");
				String discount_percentage[] = request.getParameterValues("discount_percentage");
				String permit_reference[] = request.getParameterValues("permit_reference");
				String granted_date[] = request.getParameterValues("granted_date");
				String usrn[] = request.getParameterValues("usrn");
				String street[] = request.getParameterValues("street");
				String town[] = request.getParameterValues("town");
				String road_category[] = request.getParameterValues("road_category");
				String is_works_traffic_sensitive[] = request.getParameterValues("is_works_traffic_sensitive");

				String is_street_traffic_sensitive[] = request.getParameterValues("is_street_traffic_sensitive");
				String traffic_management_type[] = request.getParameterValues("traffic_management_type");
				String proposed_start_date[] = request.getParameterValues("proposed_start_date");
				String proposed_end_date[] = request.getParameterValues("proposed_end_date");
				String change_request_reference[] = request.getParameterValues("change_request_reference");

				log.fatal("My Row Number is " + rowNumber);

				for (int row = 0; row < Integer.parseInt(rowNumber); row++) {

					FeeExportStage stageModel = new FeeExportStage();
					stageModel.setFee_export_stage_id(Integer.parseInt(fee_export_stage_id[row]));

					if (org_code != null)
						stageModel.setOrg_code(org_code[row]);

					if (org_name != null)
						stageModel.setOrg_name(org_name[row]);

					if (promoter_workstream != null)
						stageModel.setPromoter_workstream(promoter_workstream[row]);

					if (work_category != null)
						stageModel.setWork_category(work_category[row]);

					if (transaction_type != null)
						stageModel.setTransaction_type(transaction_type[row]);

					if (discount_percentage != null)
						stageModel.setDiscount_percentage(new BigDecimal(discount_percentage[row]));

					if (permit_reference != null)
						stageModel.setPermit_reference(permit_reference[row]);

					if (granted_date != null)
						stageModel.setGranted_date(sdf.parse(granted_date[row]));

					if (usrn != null)
						stageModel.setUsrn(new BigDecimal(usrn[row]));

					if (street != null)
						stageModel.setStreet(street[row]);

					if (town != null)
						stageModel.setTown(town[row]);

					if (road_category != null)
						stageModel.setRoad_category(road_category[row]);

					if (is_works_traffic_sensitive != null)
						stageModel.setIs_works_traffic_sensitive(is_works_traffic_sensitive[row]);
					if (is_street_traffic_sensitive != null)
						stageModel.setIs_street_traffic_sensitive(is_street_traffic_sensitive[row]);
					if (traffic_management_type != null)
						stageModel.setTraffic_management_type(road_category[row]);
					if (proposed_start_date != null)
						stageModel.setProposed_start_date(sdf1.parse(proposed_start_date[row]));
					if (road_category != null)
						stageModel.setRoad_category(road_category[row]);

					if (proposed_end_date != null)
						stageModel.setProposed_end_date(sdf1.parse(proposed_end_date[row]));

					if (change_request_reference != null)
						stageModel.setChange_request_reference(change_request_reference[row]);

					alledited.add(stageModel);

				}
			}
		} catch (Exception e) {
			log.fatal("ERROR ", e);
		}

		return alledited;
	}

	public Date dateMinus(Date mainDate, int numOfDays) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(mainDate);
		cal.add(Calendar.DATE, -numOfDays);
		Date dateBeforeDays = cal.getTime();
		return dateBeforeDays;
	}

	public int numberOfDays(Date fromDate, Date toDate) {
		long timeDiff = 0;
		long date1InMs = fromDate.getTime();
		long date2InMs = toDate.getTime();

		timeDiff = date2InMs - date1InMs;
		int daysDiff = (int) (timeDiff / (1000 * 60 * 60 * 24));

		return daysDiff;

	}

	public boolean zipDirectory(File dir, String zipDirName) {
		boolean returnValue = false;
		try {
			List<String> filesListInDir = new ArrayList<String>();
			filesListInDir = populateFilesList(dir);

			FileOutputStream fos = new FileOutputStream(zipDirName);
			ZipOutputStream zos = new ZipOutputStream(fos);
			for (String filePath : filesListInDir) {
				System.out.println("Zipping " + filePath);
				ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length() + 1, filePath.length()));
				zos.putNextEntry(ze);
				FileInputStream fis = new FileInputStream(filePath);
				byte[] buffer = new byte[1024];
				int len;
				while ((len = fis.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
				zos.closeEntry();
				fis.close();
			}
			zos.close();
			fos.close();
			returnValue = true;
		} catch (IOException e) {
			returnValue = false;
			e.printStackTrace();
		}
		return returnValue;
	}

	public List<String> populateFilesList(File dir) throws IOException {
		List<String> filesListInDir = new ArrayList<String>();
		System.out.println("DIR NAME " + dir.getAbsolutePath());
		File[] files = dir.listFiles();
		System.out.println("FILE COUNT " + files.length);
		for (File file : files) {
			if (file.isFile())
				filesListInDir.add(file.getAbsolutePath());
			else
				populateFilesList(file);
		}
		return filesListInDir;
	}

	public List<FPNExport_Infringements_Validated> getAllDataForFPNUpdate(HttpServletRequest request,
			String rowNumber) {

		System.out.println("My Request " + request);
		List<FPNExport_Infringements_Validated> alledited = new ArrayList<FPNExport_Infringements_Validated>();
		try {
			if ((rowNumber != null) && (rowNumber.trim().length() > 0)) {
				String fpn_export_infringements_id[] = request.getParameterValues("fpn_export_infringements_id");
				String first_update_status[] = request.getParameterValues("first_update_status");
				String second_update_status[] = request.getParameterValues("second_update_status");
				String fpn_number[] = request.getParameterValues("fpn_number");
				String issue_date_time[] = request.getParameterValues("issue_date_time");
				String amount_paid[] = request.getParameterValues("amount_paid");
				String received_date[] = request.getParameterValues("received_date");

				log.fatal("My Row Number is " + rowNumber);

				for (int row = 0; row < Integer.parseInt(rowNumber); row++) {

					if (first_update_status[row] == null || first_update_status[row].trim().length() == 0) {

						FPNExport_Infringements_Validated stageModel = new FPNExport_Infringements_Validated();

						stageModel.setFpn_export_infringements_id(Integer.parseInt(fpn_export_infringements_id[row]));

						if (fpn_number[row] != null && fpn_number[row].trim().length() > 0) {
							stageModel.setFpn_number(fpn_number[row]);
						}

						if (issue_date_time[row] != null && issue_date_time[row].trim().length() > 0) {
							stageModel.setIssue_date_time(sdf3.parse(issue_date_time[row]));
						}

						if (amount_paid[row] != null && amount_paid[row].trim().length() > 0) {
							stageModel.setAmount_paid(new BigDecimal(amount_paid[row]));
						}

						if (fpn_number[row] != null && fpn_number[row].trim().length() > 0
								&& issue_date_time[row] != null && issue_date_time[row].trim().length() > 0
								&& amount_paid[row] != null && amount_paid[row].trim().length() > 0) {
							stageModel.setSecond_update_status(null);
							stageModel.setFirst_update_status("Yes");
							alledited.add(stageModel);

						}

					}

					else if (second_update_status[row] == null || second_update_status[row].trim().length() == 0) {

						
						if(first_update_status[row] != null && first_update_status[row].trim().length() >0) {
						System.out.println(" first_update_status[row] " + first_update_status[row]+"--"+fpn_export_infringements_id[row]);
						
						FPNExport_Infringements_Validated stageModel = new FPNExport_Infringements_Validated();

						stageModel.setFpn_export_infringements_id(Integer.parseInt(fpn_export_infringements_id[row]));

						

						if (received_date[row] != null && received_date[row].trim().length() > 0) {
							stageModel.setIssue_date_time(sdf3.parse(received_date[row]));
						}


						if (received_date[row] != null && received_date[row].trim().length() > 0) {
							stageModel.setSecond_update_status("Yes");
							stageModel.setFirst_update_status("No");
							alledited.add(stageModel);

						}
						
						}

					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.fatal("ERROR ", e);
		}

		return alledited;
	}
}
