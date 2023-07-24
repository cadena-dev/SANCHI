package com.saanchi.hibernate.util;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.net.ssl.SSLContext;

//import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.JDBCException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.saanchi.commomUtility.ChargingStatementVO;
import com.saanchi.commomUtility.CommonHelper;
import com.saanchi.commomUtility.CommonOptionsNineVO;
import com.saanchi.commomUtility.CommonOptionsVO;
import com.saanchi.commomUtility.FPNStatementVO;
import com.saanchi.commomUtility.UserMasterVO;
import com.saanchi.hibernate.model.ChangeRequestProvisional;
import com.saanchi.hibernate.model.ChangeRequestStage;
import com.saanchi.hibernate.model.ChangeRequestValidated;
import com.saanchi.hibernate.model.FPNExportProvisional;
import com.saanchi.hibernate.model.FPNExportStage;
import com.saanchi.hibernate.model.FPNExportValidated;
import com.saanchi.hibernate.model.FPNExport_Infringements_Validated;
import com.saanchi.hibernate.model.FeeExportProvisional;
import com.saanchi.hibernate.model.FeeExportStage;
import com.saanchi.hibernate.model.FeeExportValidate;
import com.saanchi.hibernate.model.FileUploadDetail;
import com.saanchi.hibernate.model.HolidayMasterModel;
import com.saanchi.hibernate.model.InfringementsModel;
import com.saanchi.hibernate.model.InvoiceDetail;
import com.saanchi.hibernate.model.KPI1Provisional;
import com.saanchi.hibernate.model.KPI2Provisional;
import com.saanchi.hibernate.model.KPI3Provisional;
import com.saanchi.hibernate.model.KPI4Provisional;
import com.saanchi.hibernate.model.OfficeCreateModel;
import com.saanchi.hibernate.model.PAExportProvisional;
import com.saanchi.hibernate.model.PAExportStage;
import com.saanchi.hibernate.model.PAExportValidated;
import com.saanchi.hibernate.model.PermitFeeMasterModel;
import com.saanchi.hibernate.model.PromoterMasterModel;
import com.saanchi.hibernate.model.RegulationMasterModel;
import com.saanchi.hibernate.model.UserCreateModel;
import com.saanchi.hibernate.model.chargingDetail;
import com.saanchi.optionVO.ChangeRequestVO;
import com.saanchi.optionVO.FPNExportInfringementsVO;
import com.saanchi.optionVO.FeeExportVO;
import java.time.temporal.ChronoUnit;

public class HibernateManager extends HibernateUtil {

	private static final Logger log = Logger.getLogger(HibernateManager.class);
	private static CommonHelper helper = new CommonHelper();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
	SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
	SimpleDateFormat sdf3 = new SimpleDateFormat("dd-MMM-yyyy");
	SimpleDateFormat sdf4 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	SimpleDateFormat sdf5 = new SimpleDateFormat("dd/MM/yyyy");

	public String[] cheekUserValidity(String userName, String password) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		UserCreateModel userModel = new UserCreateModel();
		String[] retrunMap = new String[4];
		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(UserCreateModel.class)
						.add(Restrictions.eq("user_name", userName)).add(Restrictions.eq("user_password", password))
						.add(Restrictions.eq("isactive", true));

				userModel = (UserCreateModel) criteria.uniqueResult();

				if ((userModel != null) && (userModel.getUser_name() != null)) {
					retrunMap[0] = String.valueOf(userModel.getEmp_no());
					retrunMap[1] = String.valueOf(userModel.getOffice_location());
					retrunMap[2] = String.valueOf(userModel.getUser_type());
					retrunMap[3] = userModel.getEmp_name();

				} else
					retrunMap[0] = String.valueOf("99999XXXXXX");

			}

		} catch (Exception e) {
			retrunMap[0] = String.valueOf("99999XXXXXX");
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");

		} finally {
			if (session.isOpen())
				session.close();
		}
		return retrunMap;

	}

	public List<CommonOptionsVO> searchOfficeList() throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<CommonOptionsVO> officeList = new ArrayList();
		try {
			if (session != null) {
				session.beginTransaction();

				StringBuffer sql = new StringBuffer(
						" select office_master_id,office_desc from office_master where isactive=true and office_type in('Highway Authority') ORDER BY office_desc ");
				SQLQuery query = session.createSQLQuery(sql.toString());
				List<Object[]> offeices = (List<Object[]>) query.list();
				for (Object[] office : offeices) {
					CommonOptionsVO optionVO = new CommonOptionsVO(String.valueOf(office[0]), (String) office[1]);
					officeList.add(optionVO);

				}

			}

		} catch (Exception e) {
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");

		} finally {
			if (session.isOpen())
				session.close();
		}
		return officeList;

	}

	public int getFileUploadID(String fileName) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		int returnValue = 0;
		try {
			if (session != null) {
				session.beginTransaction();

				System.out.println("fileName " + fileName);
				Criteria criteria = session.createCriteria(FileUploadDetail.class)
						.add(Restrictions.eq("filename", fileName)).add(Restrictions.eq("error_records", 0))
						.add(Restrictions.gt("successful_records", 0));

				FileUploadDetail fileMode = (FileUploadDetail) criteria.uniqueResult();
				if (fileMode != null)
					returnValue = fileMode.getUpload_id();
				else
					returnValue = 0;

			}

			else {
				returnValue = 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			log.fatal(e);
			throw new Exception("System Error Occured");
		} finally {
			if (session.isOpen())
				session.close();
		}
		return returnValue;

	}

	public int uploadFile(String fileName, String fileType) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		int returnValue = 0;
		Date currentDate = new Date();
		try {
			if (session != null) {
				session.beginTransaction();

				FileUploadDetail fileModel = new FileUploadDetail();
				fileModel.setFilename(fileName);
				fileModel.setCreated_at(currentDate);
				fileModel.setUpload_type(fileType);
				fileModel.setCreated_by(1);
				session.save(fileModel);

				Criteria criteria = session.createCriteria(FileUploadDetail.class)
						.add(Restrictions.eq("filename", fileName)).add(Restrictions.eq("error_records", 0))
						.add(Restrictions.eq("successful_records", 0)).add(Restrictions.eq("no_of_records", 0));

				fileModel = (FileUploadDetail) criteria.uniqueResult();

				returnValue = fileModel.getUpload_id();
				session.getTransaction().commit();
			} else
				returnValue = 0;

		} catch (Exception e) {
			returnValue = 0;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}
		return returnValue;

	}

	public FileUploadDetail getUploadFileDetails(int fileID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		FileUploadDetail fileModel = new FileUploadDetail();

		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(FileUploadDetail.class)
						.add(Restrictions.eq("upload_id", fileID));
				fileModel = (FileUploadDetail) criteria.uniqueResult();

			}

		} catch (Exception e) {

			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return fileModel;

	}

	public boolean uploadFPNExportProvisional(List<FPNExportProvisional> fpnModelListlList,
			List<FPNExportStage> fpnStagelList, int uploadFileId, String empID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		int rowcount = 0;
		int totalRecords = 0;
		int successfullRecords = 0;
		int errorRecords = 0;
		boolean returnValue = false;

		try {
			if (session != null) {
				session.beginTransaction();

				totalRecords = fpnModelListlList.size();
				ListIterator<FPNExportProvisional> listItr = fpnModelListlList.listIterator();
				while (listItr.hasNext()) {
					FPNExportProvisional FPNExportProvisionalModel = new FPNExportProvisional();
					FPNExportProvisionalModel = (FPNExportProvisional) listItr.next();
					session.save(FPNExportProvisionalModel);
					rowcount += 1;

					if (rowcount % 30 == 0) {
						session.flush();
						session.clear();
					}

				}

				rowcount = 0;
				ListIterator<FPNExportStage> listItrStage = fpnStagelList.listIterator();
				while (listItrStage.hasNext()) {
					FPNExportStage FPNExportStageModel = new FPNExportStage();
					FPNExportStageModel = (FPNExportStage) listItrStage.next();

					if ((FPNExportStageModel.getFpn_status().trim().equalsIgnoreCase("Issued"))
							|| (FPNExportStageModel.getFpn_status().trim().equalsIgnoreCase("Disputed"))
							|| (FPNExportStageModel.getFpn_status().trim().equalsIgnoreCase("Withdrawn"))
							|| (FPNExportStageModel.getFpn_status().trim().equalsIgnoreCase("Accepted"))) {
						FPNExportStageModel.setAmount_paid(new BigDecimal(0));

					} else if (FPNExportStageModel.getFpn_status().trim().equalsIgnoreCase("Paid")) {

						if ((FPNExportStageModel.getOffence_code().trim().equalsIgnoreCase("05 - Section 70"))
								|| (FPNExportStageModel.getOffence_code().trim()
										.equalsIgnoreCase("06 - Section 74 (7B)"))
								|| (FPNExportStageModel.getOffence_code().trim().equalsIgnoreCase("09 - Regulation 20"))
								|| (FPNExportStageModel.getOffence_code().trim().equalsIgnoreCase("06 - Section 74"))) {
							FPNExportStageModel.setAmount_paid(new BigDecimal(120));
						}

						else if (FPNExportStageModel.getOffence_code().trim().equalsIgnoreCase("08 - Regulation 19")) {
							FPNExportStageModel.setAmount_paid(new BigDecimal(500));
						}

					}

					else if (FPNExportStageModel.getFpn_status().trim().equalsIgnoreCase("Paid (Discounted)")) {

						if ((FPNExportStageModel.getOffence_code().trim().equalsIgnoreCase("05 - Section 70"))
								|| (FPNExportStageModel.getOffence_code().trim().equalsIgnoreCase("06 - Section 74"))
								|| (FPNExportStageModel.getOffence_code().trim()
										.equalsIgnoreCase("09 - Regulation 20"))) {
							FPNExportStageModel.setAmount_paid(new BigDecimal(80));
						}

						else if (FPNExportStageModel.getOffence_code().trim().equalsIgnoreCase("08 - Regulation 19")) {
							FPNExportStageModel.setAmount_paid(new BigDecimal(300));
						}

						else
							FPNExportStageModel.setAmount_paid(new BigDecimal(0));
					}

					session.save(FPNExportStageModel);
					rowcount += 1;

					if (rowcount % 30 == 0) {
						session.flush();
						session.clear();
					}

				}

				successfullRecords = totalRecords;
				session.getTransaction().commit();
				returnValue = true;
			} else {
				returnValue = false;
				errorRecords = totalRecords;
				successfullRecords = 0;
			}

		} catch (Exception e) {
			errorRecords = totalRecords;
			successfullRecords = 0;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}
		updateUploadFile(uploadFileId, totalRecords, successfullRecords, errorRecords);

		return returnValue;

	}

	public void updateUploadFile(int fileID, int totalRecords, int successfullRecords, int errorRecords) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			if (session != null) {
				session.beginTransaction();

				System.out.println(fileID + " " + totalRecords + " " + successfullRecords + " " + errorRecords);

				FileUploadDetail fileModel = new FileUploadDetail();

				Criteria criteria = session.createCriteria(FileUploadDetail.class)
						.add(Restrictions.eq("upload_id", fileID));
				fileModel = (FileUploadDetail) criteria.uniqueResult();

				fileModel.setNo_of_records(totalRecords);
				fileModel.setSuccessful_records(successfullRecords);
				fileModel.setError_records(errorRecords);
				session.update(fileModel);
				session.getTransaction().commit();

			}

		} catch (Exception e) {

			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

	}

	public boolean uploadFeeExportProvisional(List<FeeExportProvisional> feeModelListlList,
			List<FeeExportStage> feeStagelList, int uploadFileId, String empID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		int rowcount = 0;
		int totalRecords = 0;
		int successfullRecords = 0;
		int errorRecords = 0;
		boolean returnValue = false;

		try {
			if (session != null) {
				session.beginTransaction();

				totalRecords = feeModelListlList.size();
				ListIterator<FeeExportProvisional> listItr = feeModelListlList.listIterator();
				while (listItr.hasNext()) {
					FeeExportProvisional FeexportProvisionalModel = new FeeExportProvisional();
					FeexportProvisionalModel = (FeeExportProvisional) listItr.next();
					session.save(FeexportProvisionalModel);
					rowcount += 1;

					if (rowcount % 30 == 0) {
						session.flush();
						session.clear();
					}

				}

				rowcount = 0;
				ListIterator<FeeExportStage> listItrStage = feeStagelList.listIterator();
				while (listItrStage.hasNext()) {
					FeeExportStage feeExportStage = new FeeExportStage();
					feeExportStage = (FeeExportStage) listItrStage.next();
					session.save(feeExportStage);
					rowcount += 1;

					if (rowcount % 30 == 0) {
						session.flush();
						session.clear();
					}

				}

				successfullRecords = totalRecords;
				session.getTransaction().commit();
				returnValue = true;
			} else {
				returnValue = false;
				successfullRecords = 0;
				errorRecords = totalRecords;

			}

		} catch (Exception e) {
			errorRecords = totalRecords;
			successfullRecords = 0;
			session.getTransaction().rollback();
			e.printStackTrace();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}
		updateUploadFile(uploadFileId, totalRecords, successfullRecords, errorRecords);

		return returnValue;

	}

	public boolean uploadChangeRequestProvisional(List<ChangeRequestProvisional> changeModelListlList,
			List<ChangeRequestStage> changeStagelList, int uploadFileId, String empID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		int rowcount = 0;
		int totalRecords = 0;
		int successfullRecords = 0;
		int errorRecords = 0;
		boolean returnValue = false;

		try {
			if (session != null) {
				session.beginTransaction();

				totalRecords = changeModelListlList.size();
				ListIterator<ChangeRequestProvisional> listItr = changeModelListlList.listIterator();
				while (listItr.hasNext()) {
					ChangeRequestProvisional changeRequestProvisionalModel = new ChangeRequestProvisional();
					changeRequestProvisionalModel = (ChangeRequestProvisional) listItr.next();
					session.save(changeRequestProvisionalModel);
					rowcount += 1;

					if (rowcount % 30 == 0) {
						session.flush();
						session.clear();
					}

				}

				rowcount = 0;
				ListIterator<ChangeRequestStage> listItrStage = changeStagelList.listIterator();
				while (listItrStage.hasNext()) {
					ChangeRequestStage changeStageModel = new ChangeRequestStage();
					changeStageModel = (ChangeRequestStage) listItrStage.next();
					session.save(changeStageModel);
					rowcount += 1;

					if (rowcount % 30 == 0) {
						session.flush();
						session.clear();
					}

				}

				successfullRecords = totalRecords;
				session.getTransaction().commit();
				returnValue = true;
			} else {
				returnValue = false;
				successfullRecords = 0;
				errorRecords = totalRecords;

			}

		} catch (Exception e) {
			errorRecords = totalRecords;
			successfullRecords = 0;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}
		updateUploadFile(uploadFileId, totalRecords, successfullRecords, errorRecords);

		return returnValue;

	}

	public boolean uploadPAExportProvisional(List<PAExportProvisional> PAExportModelListlList,
			List<PAExportStage> paStagelList, int uploadFileId, String empID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		int rowcount = 0;
		int totalRecords = 0;
		int successfullRecords = 0;
		int errorRecords = 0;
		boolean returnValue = false;

		try {
			if (session != null) {
				session.beginTransaction();

				totalRecords = PAExportModelListlList.size();
				ListIterator<PAExportProvisional> listItr = PAExportModelListlList.listIterator();
				while (listItr.hasNext()) {
					PAExportProvisional paExportProvisionalModel = new PAExportProvisional();
					paExportProvisionalModel = (PAExportProvisional) listItr.next();
					session.save(paExportProvisionalModel);
					rowcount += 1;

					if (rowcount % 30 == 0) {
						session.flush();
						session.clear();
					}

				}

				rowcount = 0;
				ListIterator<PAExportStage> listItrStage = paStagelList.listIterator();
				while (listItrStage.hasNext()) {
					PAExportStage PAExportStageModel = new PAExportStage();
					PAExportStageModel = (PAExportStage) listItrStage.next();
					session.save(PAExportStageModel);
					rowcount += 1;

					if (rowcount % 30 == 0) {
						session.flush();
						session.clear();
					}

				}

				successfullRecords = totalRecords;
				session.getTransaction().commit();
				returnValue = true;
			} else {
				returnValue = false;
				errorRecords = totalRecords;
				successfullRecords = 0;

			}

		} catch (Exception e) {
			e.printStackTrace();
			errorRecords = totalRecords;
			successfullRecords = 0;
			session.getTransaction().rollback();
			;
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}
		updateUploadFile(uploadFileId, totalRecords, successfullRecords, errorRecords);

		return returnValue;

	}

	public List<CommonOptionsNineVO> SearchFileListForValidation(int officeCode, String fileType) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<CommonOptionsNineVO> searchFileList = new ArrayList<CommonOptionsNineVO>();
		StringBuffer sql = new StringBuffer("");
		try {
			if (session != null) {

				session.beginTransaction();

				if (fileType.equalsIgnoreCase("PA_Export")) {

					sql = new StringBuffer("	select distinct a.file_name,b.no_of_records,b.successful_records, ")
							.append(" to_char(b.created_at, 'dd/MM/yyyy') upload_date ")
							.append(" from pa_export_stage   a, ").append(" file_upload_detail b where status is null ")
							.append(" and a.file_name=b.filename ").append(" and a.office_code= " + officeCode)
							.append(" and upload_type='PA_Export' order by to_char(b.created_at, 'dd/MM/yyyy')");
				} else if (fileType.equalsIgnoreCase("Change_Request")) {

					sql = new StringBuffer("	select distinct a.file_name,b.no_of_records,b.successful_records, ")
							.append(" to_char(b.created_at, 'dd/MM/yyyy') upload_date ")
							.append(" from change_request_stage   a, ")
							.append(" file_upload_detail b where status is null ")
							.append(" and a.file_name=b.filename ").append(" and a.office_code= " + officeCode)
							.append(" and upload_type='Change_Request' order by to_char(b.created_at, 'dd/MM/yyyy')");
				}

				else if (fileType.equalsIgnoreCase("Fee_Export")) {

					sql = new StringBuffer("	select distinct a.file_name,b.no_of_records,b.successful_records, ")
							.append(" to_char(b.created_at, 'dd/MM/yyyy') upload_date ")
							.append(" from fee_export_stage   a, ")
							.append(" file_upload_detail b where status is null ")
							.append(" and a.file_name=b.filename ").append(" and a.office_code= " + officeCode)
							.append(" and upload_type='Fee_Export' order by to_char(b.created_at, 'dd/MM/yyyy')");
				}

				else if (fileType.equalsIgnoreCase("FPN_Export")) {

					sql = new StringBuffer("	select distinct a.file_name,b.no_of_records,b.successful_records, ")
							.append(" to_char(b.created_at, 'dd/MM/yyyy') upload_date ")
							.append(" from fpn_export_stage   a, ")
							.append(" file_upload_detail b where status is null ")
							.append(" and a.file_name=b.filename ").append(" and a.office_code= " + officeCode)
							.append(" and upload_type='FPN_Export' order by to_char(b.created_at, 'dd/MM/yyyy')");
				}

				log.fatal(" SearchFileListForValidation = " + sql);

				SQLQuery query = session.createSQLQuery(sql.toString());
				List<Object[]> partyTypes = (List<Object[]>) query.list();
				for (Object[] partyType : partyTypes) {
					CommonOptionsNineVO optionVO = new CommonOptionsNineVO((String) partyType[0],
							String.valueOf(partyType[1]), String.valueOf(partyType[2]), (String) partyType[3], null,
							null, null, null, null);
					searchFileList.add(optionVO);
				}
				System.out.println("Size is " + searchFileList.size());

			}

		} catch (Exception e) {

			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");
		} finally {
			if (session.isOpen())
				session.close();
		}
		return searchFileList;

	}

	public List<FPNExportStage> searchFPNFileDataFromStage(String fileName, List<FPNExportStage> fpnStageList) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(FPNExportStage.class)
						.add(Restrictions.eq("file_name", fileName)).add(Restrictions.isNull("status"));
				fpnStageList = criteria.list();

			}

		} catch (Exception e) {

			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return fpnStageList;

	}

	public List<PAExportStage> searchPAFileDataFromStage(String fileName, List<PAExportStage> paStageList) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(PAExportStage.class)
						.add(Restrictions.eq("file_name", fileName)).add(Restrictions.isNull("status"));
				paStageList = criteria.list();

			}

		} catch (Exception e) {

			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return paStageList;

	}

	public List<ChangeRequestStage> searchChangeRequestDataFromStage(String fileName,
			List<ChangeRequestStage> changeStageList) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(ChangeRequestStage.class)
						.add(Restrictions.eq("file_name", fileName)).add(Restrictions.isNull("status"));
				changeStageList = criteria.list();

			}

		} catch (Exception e) {

			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return changeStageList;

	}

	public List<FeeExportStage> searchFeeFileDataFromStage(String fileName, List<FeeExportStage> feeStageList) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(FeeExportStage.class)
						.add(Restrictions.eq("file_name", fileName)).add(Restrictions.isNull("status"));
				feeStageList = criteria.list();

			}

		} catch (Exception e) {

			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return feeStageList;

	}

	public boolean checkPermitNoinPAExport(int yearMonth, int officeCode, String permi_reference_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;
		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(PAExportValidated.class)
						.add(Restrictions.eq("office_code", officeCode)).add(Restrictions.eq("year_month", yearMonth))
						.add(Restrictions.eq("permit_reference_number", permi_reference_no.trim()));

				if (criteria.list().size() > 0)
					returnValue = true;
				else
					returnValue = false;

			}

		} catch (Exception e) {
			returnValue = false;
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return returnValue;

	}

	public boolean checkChangeRequestNoinChangeRequestExport(int yearMonth, int officeCode,
			String change_request_reference_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;
		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(ChangeRequestValidated.class)
						.add(Restrictions.eq("office_code", officeCode)).add(Restrictions.eq("year_month", yearMonth))
						.add(Restrictions.eq("change_request_reference_number", change_request_reference_no.trim()));

				if (criteria.list().size() > 0)
					returnValue = true;
				else
					returnValue = false;

			}

		} catch (Exception e) {
			returnValue = false;
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return returnValue;

	}

	public void updatePermiStatusinPAExport(int yearMonth, int officeCode, String permi_reference_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(PAExportValidated.class)
						.add(Restrictions.eq("office_code", officeCode)).add(Restrictions.eq("year_month", yearMonth))
						.add(Restrictions.eq("permit_reference_number", permi_reference_no.trim()));

				List<PAExportValidated> paExportList = criteria.list();

				ListIterator<PAExportValidated> listItrValidate = paExportList.listIterator();
				while (listItrValidate.hasNext()) {
					PAExportValidated validateeModel = new PAExportValidated();
					validateeModel = (PAExportValidated) listItrValidate.next();
					validateeModel.setPermit_status("superseded");
					session.update(validateeModel);

				}

				session.getTransaction().commit();
			}

		} catch (Exception e) {

			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

	}

	public void updateTrafficSensitiveinPAExport(int yearMonth, int officeCode, String permi_reference_no,
			String trafficSensitive) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		String coordinator = "";
		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(PAExportValidated.class)
						.add(Restrictions.eq("office_code", officeCode)).add(Restrictions.eq("year_month", yearMonth))
						.add(Restrictions.eq("permit_reference_number", permi_reference_no.trim()));

				List<PAExportValidated> paExportList = criteria.list();

				ListIterator<PAExportValidated> listItrValidate = paExportList.listIterator();
				while (listItrValidate.hasNext()) {
					PAExportValidated validateeModel = new PAExportValidated();
					validateeModel = (PAExportValidated) listItrValidate.next();
					validateeModel.setTraffic_sensitive(trafficSensitive);

					if ((validateeModel.getReinstatement_category().startsWith("0"))
							|| (validateeModel.getReinstatement_category().startsWith("1"))
							|| (validateeModel.getReinstatement_category().startsWith("2"))) {
						validateeModel.setCoordinator("CAT 0,1,2 or TS");
						coordinator = "CAT 0,1,2 or TS";
					} else if ((validateeModel.getReinstatement_category().startsWith("3"))
							|| (validateeModel.getReinstatement_category().startsWith("4"))) {
						if (trafficSensitive.equalsIgnoreCase("Yes")) {
							validateeModel.setCoordinator("CAT 0,1,2 or TS");
							coordinator = "CAT 0,1,2 or TS";

						} else if (trafficSensitive.equalsIgnoreCase("No")) {
							validateeModel.setCoordinator("CAT 3,4 and Non TS");
							coordinator = "CAT 3,4 and Non TS";
						}
					}

					else if ((validateeModel.getReinstatement_category().startsWith("6"))
							|| (validateeModel.getReinstatement_category().startsWith("7"))
							|| (validateeModel.getReinstatement_category().startsWith("8"))) {
						if (trafficSensitive.equalsIgnoreCase("Yes")) {
							validateeModel.setCoordinator("CAT 0,1,2 or TS");
							coordinator = "CAT 0,1,2 or TS";

						} else if (trafficSensitive.equalsIgnoreCase("No")) {
							validateeModel.setCoordinator("CAT 3,4 and Non TS");

							coordinator = "CAT 3,4 and Non TS";
						}
					} else {
						validateeModel.setCoordinator("");
						coordinator = "";
					}

					session.update(validateeModel);

				}

				updateCoordinatorChangeRequest(yearMonth, officeCode, permi_reference_no, coordinator);

				session.getTransaction().commit();
			}

		} catch (Exception e) {

			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

	}

	public void updateCoordinatorChangeRequest(int yearMonth, int officeCode, String permi_reference_no,
			String coordinnator) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(ChangeRequestValidated.class)
						.add(Restrictions.eq("office_code", officeCode)).add(Restrictions.eq("year_month", yearMonth))
						.add(Restrictions.eq("permit_reference_number", permi_reference_no.trim()));

				List<ChangeRequestValidated> ChanngeRequestList = criteria.list();

				ListIterator<ChangeRequestValidated> listItrValidate = ChanngeRequestList.listIterator();
				while (listItrValidate.hasNext()) {

					System.out.println("I am Updating ...... " + permi_reference_no + "  " + coordinnator);
					ChangeRequestValidated validateeModel = new ChangeRequestValidated();
					validateeModel = (ChangeRequestValidated) listItrValidate.next();
					validateeModel.setCoordinator(coordinnator);
					session.update(validateeModel);

				}

				session.getTransaction().commit();
			}

		} catch (Exception e) {

			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

	}

	public void updateOutOfWorkPAExport(int yearMonth, int officeCode, String permi_reference_no,
			String streetSensitive) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(PAExportValidated.class)
						.add(Restrictions.eq("office_code", officeCode)).add(Restrictions.eq("year_month", yearMonth))
						.add(Restrictions.eq("permit_reference_number", permi_reference_no.trim()));

				List<PAExportValidated> paExportList = criteria.list();

				ListIterator<PAExportValidated> listItrValidate = paExportList.listIterator();
				while (listItrValidate.hasNext()) {
					PAExportValidated validateeModel = new PAExportValidated();
					validateeModel = (PAExportValidated) listItrValidate.next();
					validateeModel.setOut_of_working_hours(streetSensitive);
					session.update(validateeModel);

				}

				session.getTransaction().commit();
			}

		} catch (Exception e) {

			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

	}

	public void updateGrantPermitDatePAExport(int yearMonth, int officeCode, String permi_reference_no,
			Date grantPermitDate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(PAExportValidated.class)
						.add(Restrictions.eq("office_code", officeCode)).add(Restrictions.eq("year_month", yearMonth))
						.add(Restrictions.eq("permit_reference_number", permi_reference_no.trim()));

				List<PAExportValidated> paExportList = criteria.list();

				ListIterator<PAExportValidated> listItrValidate = paExportList.listIterator();
				while (listItrValidate.hasNext()) {
					PAExportValidated validateeModel = new PAExportValidated();
					validateeModel = (PAExportValidated) listItrValidate.next();
					validateeModel.setGrant_permit_date(grantPermitDate);
					session.update(validateeModel);

				}

				session.getTransaction().commit();
			}

		} catch (Exception e) {

			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

	}

	public boolean UpdateFPNExportStage(List<FPNExportStage> alledited, String file_name) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		boolean returnValue = false;

		try {
			if (session != null) {
				session.beginTransaction();

				ListIterator<FPNExportStage> listItr = alledited.listIterator();
				FPNExportStage FPNExportStageModel = new FPNExportStage();
				while (listItr.hasNext()) {

					FPNExportStage stageModel = new FPNExportStage();
					stageModel = (FPNExportStage) listItr.next();

					Criteria criteria = session.createCriteria(FPNExportStage.class)
							.add(Restrictions.eq("file_name", file_name))
							.add(Restrictions.eq("fpn_export_stage_id", stageModel.getFpn_export_stage_id()));

					FPNExportStageModel = (FPNExportStage) criteria.uniqueResult();

					if (stageModel.getWorks_reference_number() != null)
						FPNExportStageModel.setWorks_reference_number(stageModel.getWorks_reference_number());

					if (stageModel.getFpn_number() != null)
						FPNExportStageModel.setFpn_number(stageModel.getFpn_status());

					if (stageModel.getFpn_status() != null)
						FPNExportStageModel.setFpn_status(stageModel.getFpn_status());

					if (stageModel.getOffence_code() != null)
						FPNExportStageModel.setOffence_code(stageModel.getOffence_code());

					if (stageModel.getOffence_date() != null)
						FPNExportStageModel.setOffence_date(stageModel.getOffence_date());

					if (stageModel.getOffence_details() != null)
						FPNExportStageModel.setOffence_details(stageModel.getOffence_details());

					if (stageModel.getIssue_date_time() != null)
						FPNExportStageModel.setIssue_date_time(stageModel.getIssue_date_time());

					if (stageModel.getStreet_name() != null)
						FPNExportStageModel.setStreet_name(stageModel.getStreet_name());

					if (stageModel.getUsrn() != null)
						FPNExportStageModel.setUsrn(stageModel.getUsrn());

					if (stageModel.getWorks_promoter() != null)
						FPNExportStageModel.setWorks_promoter(stageModel.getWorks_promoter());

					if (stageModel.getIssuing_authority() != null)
						FPNExportStageModel.setIssuing_authority(stageModel.getIssuing_authority());

					if (stageModel.getStatus_changed_date() != null)
						FPNExportStageModel.setStatus_changed_date(stageModel.getStatus_changed_date());

					if (stageModel.getAmount_paid() != null)
						FPNExportStageModel.setAmount_paid(stageModel.getAmount_paid());

					session.update(FPNExportStageModel);

				}

				session.getTransaction().commit();
				returnValue = true;
			} else {
				returnValue = false;

			}

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return returnValue;

	}

	public boolean UpdatePAExportStage(List<PAExportStage> alledited, String file_name) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		boolean returnValue = false;

		try {
			if (session != null) {
				session.beginTransaction();

				ListIterator<PAExportStage> listItr = alledited.listIterator();
				PAExportStage PAExportStageModel = new PAExportStage();
				while (listItr.hasNext()) {

					PAExportStage stageModel = new PAExportStage();
					stageModel = (PAExportStage) listItr.next();

					Criteria criteria = session.createCriteria(PAExportStage.class)
							.add(Restrictions.eq("file_name", file_name))
							.add(Restrictions.eq("pa_export_stage_id", stageModel.getPa_export_stage_id()));

					PAExportStageModel = (PAExportStage) criteria.uniqueResult();

					if (stageModel.getDate_of_permits() != null)
						PAExportStageModel.setDate_of_permits(stageModel.getDate_of_permits());

					if (stageModel.getPromoter_name() != null)
						PAExportStageModel.setPromoter_name(stageModel.getPromoter_name());

					if (stageModel.getWork_reference_number() != null)
						PAExportStageModel.setWork_reference_number(stageModel.getWork_reference_number());

					if (stageModel.getPermit_reference_number() != null)
						PAExportStageModel.setPermit_reference_number(stageModel.getPermit_reference_number());

					if (stageModel.getApplication_type() != null)
						PAExportStageModel.setApplication_type(stageModel.getApplication_type());

					if (stageModel.getType_of_work() != null)
						PAExportStageModel.setType_of_work(stageModel.getType_of_work());

					if (stageModel.getLocation() != null)
						PAExportStageModel.setLocation(stageModel.getLocation());

					if (stageModel.getStreet() != null)
						PAExportStageModel.setStreet(stageModel.getStreet());

					if (stageModel.getTown() != null)
						PAExportStageModel.setTown(stageModel.getTown());

					if (stageModel.getUsrn() != null)
						PAExportStageModel.setUsrn(stageModel.getUsrn());

					if (stageModel.getActivity_type() != null)
						PAExportStageModel.setActivity_type(stageModel.getActivity_type());

					if (stageModel.getWork_description() != null)
						PAExportStageModel.setWork_description(stageModel.getWork_description());

					if (stageModel.getWorks_with_excavation() != null)
						PAExportStageModel.setWorks_with_excavation(stageModel.getWorks_with_excavation());

					if (stageModel.getReinstatement_category() != null)
						PAExportStageModel.setReinstatement_category(stageModel.getReinstatement_category());

					if (stageModel.getTraffic_management_method() != null)
						PAExportStageModel.setTraffic_management_method(stageModel.getTraffic_management_method());

					if (stageModel.getCollaborative_working() != null)
						PAExportStageModel.setCollaborative_working(stageModel.getCollaborative_working());

					if (stageModel.getEastimated_start_date() != null)
						PAExportStageModel.setEastimated_start_date(stageModel.getEastimated_start_date());

					if (stageModel.getProposed_start_time() != null)
						PAExportStageModel.setProposed_start_time(stageModel.getProposed_start_time());

					if (stageModel.getProposed_end_date() != null)
						PAExportStageModel.setProposed_end_date(stageModel.getProposed_end_date());

					if (stageModel.getProposed_end_time() != null)
						PAExportStageModel.setProposed_end_time(stageModel.getProposed_end_time());

					if (stageModel.getDuration_of_work() != null)
						PAExportStageModel.setDuration_of_work(stageModel.getDuration_of_work());

					if (stageModel.getPermit_status() != null)
						PAExportStageModel.setPermit_status(stageModel.getPermit_status());

					if (stageModel.getPermit_deeming_date() != null)
						PAExportStageModel.setPermit_deeming_date(stageModel.getPermit_deeming_date());

					if (stageModel.getDiscount() != null)
						PAExportStageModel.setDiscount(stageModel.getDiscount());

					session.update(PAExportStageModel);

				}

				session.getTransaction().commit();
				returnValue = true;
			} else {
				returnValue = false;

			}

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return returnValue;

	}

	public boolean saveToValidate(String file_name, String empNo, String comment) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		boolean returnValue = false;
		Date currentDate = new Date();
		try {
			if (session != null) {
				session.beginTransaction();

				List<FPNExportStage> stageModelList = new ArrayList();

				Criteria criteria = session.createCriteria(FPNExportStage.class)
						.add(Restrictions.eq("file_name", file_name)).add(Restrictions.isNull("status"));

				stageModelList = criteria.list();
				ListIterator<FPNExportStage> listItr = stageModelList.listIterator();

				while (listItr.hasNext()) {
					FPNExportStage stageModel = new FPNExportStage();
					stageModel = (FPNExportStage) listItr.next();
					FPNExportValidated validatedModel = new FPNExportValidated();

					Criteria criteriaValidated = session.createCriteria(FPNExportValidated.class)
							.add(Restrictions.eq("fpn_number", stageModel.getFpn_number()));

					validatedModel = (FPNExportValidated) criteriaValidated.uniqueResult();

					if (validatedModel == null) {

						FPNExportValidated insertValidatedModel = new FPNExportValidated();

						insertValidatedModel.setWorks_reference_number(stageModel.getWorks_reference_number());
						insertValidatedModel.setFpn_number(stageModel.getFpn_number());
						insertValidatedModel.setFpn_status(stageModel.getFpn_status());
						insertValidatedModel.setOffence_code(stageModel.getOffence_code());
						insertValidatedModel.setOffence_date(stageModel.getOffence_date());
						insertValidatedModel.setOffence_details(stageModel.getOffence_details());
						insertValidatedModel.setIssue_date_time(stageModel.getIssue_date_time());
						insertValidatedModel.setStreet_name(stageModel.getStreet_name());
						insertValidatedModel.setUsrn(stageModel.getUsrn());
						insertValidatedModel.setWorks_promoter(stageModel.getWorks_promoter());
						insertValidatedModel.setIssuing_authority(stageModel.getIssuing_authority());
						insertValidatedModel.setStatus_changed_date(stageModel.getStatus_changed_date());
						insertValidatedModel.setAmount_paid(stageModel.getAmount_paid());
						insertValidatedModel.setComments_txt(comment.trim());
						insertValidatedModel.setOffice_code(stageModel.getOffice_code());
						insertValidatedModel.setYear_month(stageModel.getYear_month());
						insertValidatedModel.setStatus("INSERTED");
						insertValidatedModel.setUpload_date(stageModel.getUpload_date());
						insertValidatedModel.setCreated_by(Integer.parseInt(empNo));
						insertValidatedModel.setFile_name(file_name);
						insertValidatedModel.setCreated_at(currentDate);
						insertValidatedModel.setFirst_update_status("Yes");
						insertValidatedModel.setSecond_update_status("Yes");
						session.save(insertValidatedModel);
					}

					else if (validatedModel != null && validatedModel.getFpn_number() != null
							&& validatedModel.getFpn_number().length() > 0) {

						validatedModel.setWorks_reference_number(stageModel.getWorks_reference_number());
						validatedModel.setFpn_status(stageModel.getFpn_status());
						validatedModel.setOffence_code(stageModel.getOffence_code());
						validatedModel.setOffence_date(stageModel.getOffence_date());
						validatedModel.setOffence_details(stageModel.getOffence_details());
						validatedModel.setIssue_date_time(stageModel.getIssue_date_time());
						validatedModel.setStreet_name(stageModel.getStreet_name());
						validatedModel.setUsrn(stageModel.getUsrn());
						validatedModel.setWorks_promoter(stageModel.getWorks_promoter());
						validatedModel.setIssuing_authority(stageModel.getIssuing_authority());
						validatedModel.setStatus_changed_date(stageModel.getStatus_changed_date());
						validatedModel.setAmount_paid(stageModel.getAmount_paid());
						validatedModel.setComments_txt(comment.trim());
						validatedModel.setOffice_code(stageModel.getOffice_code());
						validatedModel.setYear_month(stageModel.getYear_month());
						validatedModel.setStatus("UPDATED");
						validatedModel.setCreated_by(Integer.parseInt(empNo));
						validatedModel.setUpload_date(currentDate);
						validatedModel.setCreated_at(currentDate);
						validatedModel.setFile_name(file_name);
						validatedModel.setFirst_update_status("Yes");
						validatedModel.setSecond_update_status("Yes");
						session.update(validatedModel);
					}

				}

				String hql = "delete from FPNExportStage where file_name= :file_name and status is null";
				session.createQuery(hql).setString("file_name", file_name).executeUpdate();

				hql = "update FPNExportProvisional set status='VALIDATED' where file_name= :file_name and status is null";
				session.createQuery(hql).setString("file_name", file_name).executeUpdate();

				session.getTransaction().commit();
				returnValue = true;
			} else {
				returnValue = false;

			}

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return returnValue;

	}

	public boolean savePAToValidate(String file_name, String empNo, String comment) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		boolean returnValue = false;
		Date currentDate = new Date();
		try {
			if (session != null) {
				session.beginTransaction();

				List<PAExportStage> stageModelList = new ArrayList();

				Criteria criteria = session.createCriteria(PAExportStage.class)
						.add(Restrictions.eq("file_name", file_name)).add(Restrictions.isNull("status"));

				stageModelList = criteria.list();
				ListIterator<PAExportStage> listItr = stageModelList.listIterator();

				while (listItr.hasNext()) {
					PAExportStage stageModel = new PAExportStage();
					stageModel = (PAExportStage) listItr.next();
					PAExportValidated validatedModel = new PAExportValidated();

					Criteria criteriaValidated = session.createCriteria(PAExportValidated.class)
							.add(Restrictions.eq("permit_reference_number", stageModel.getPermit_reference_number()));

					validatedModel = (PAExportValidated) criteriaValidated.uniqueResult();

					if (validatedModel == null) {

						PAExportValidated insertValidatedModel = new PAExportValidated();

						insertValidatedModel.setDate_of_permits(stageModel.getDate_of_permits());
						insertValidatedModel.setPromoter_name(stageModel.getPromoter_name());
						insertValidatedModel.setWork_reference_number(stageModel.getWork_reference_number());
						insertValidatedModel.setPermit_reference_number(stageModel.getPermit_reference_number());
						insertValidatedModel.setApplication_type(stageModel.getApplication_type());
						insertValidatedModel.setType_of_work(stageModel.getType_of_work());
						insertValidatedModel.setLocation(stageModel.getLocation());
						insertValidatedModel.setStreet(stageModel.getStreet());
						insertValidatedModel.setTown(stageModel.getTown());
						insertValidatedModel.setUsrn(stageModel.getUsrn());
						insertValidatedModel.setActivity_type(stageModel.getActivity_type());
						insertValidatedModel.setWork_description(stageModel.getWork_description());
						insertValidatedModel.setWorks_with_excavation(stageModel.getWorks_with_excavation());
						insertValidatedModel.setReinstatement_category(stageModel.getReinstatement_category());
						insertValidatedModel.setTraffic_management_method(stageModel.getTraffic_management_method());
						insertValidatedModel.setCollaborative_working(stageModel.getCollaborative_working());
						insertValidatedModel.setEastimated_start_date(stageModel.getEastimated_start_date());
						insertValidatedModel.setProposed_start_time(stageModel.getProposed_start_time());
						insertValidatedModel.setProposed_end_date(stageModel.getProposed_end_date());
						insertValidatedModel.setProposed_end_time(stageModel.getProposed_end_time());
						insertValidatedModel.setDuration_of_work(stageModel.getDuration_of_work());
						insertValidatedModel.setPermit_status(stageModel.getPermit_status());
						insertValidatedModel.setPermit_deeming_date(stageModel.getPermit_deeming_date());
						insertValidatedModel.setDiscount(stageModel.getDiscount());
						insertValidatedModel.setComments_txt(comment.trim());
						insertValidatedModel.setOffice_code(stageModel.getOffice_code());
						insertValidatedModel.setYear_month(stageModel.getYear_month());
						insertValidatedModel.setStatus("INSERTED");
						insertValidatedModel.setUpload_date(stageModel.getUpload_date());
						insertValidatedModel.setCreated_by(Integer.parseInt(empNo));
						insertValidatedModel.setFile_name(file_name);
						insertValidatedModel.setCreated_at(currentDate);
						session.save(insertValidatedModel);
					}

					else if (validatedModel != null && validatedModel.getPermit_reference_number() != null
							&& validatedModel.getPermit_reference_number().length() > 0) {

						validatedModel.setDate_of_permits(stageModel.getDate_of_permits());
						validatedModel.setPromoter_name(stageModel.getPromoter_name());
						validatedModel.setWork_reference_number(stageModel.getWork_reference_number());
						validatedModel.setApplication_type(stageModel.getApplication_type());
						validatedModel.setType_of_work(stageModel.getType_of_work());
						validatedModel.setLocation(stageModel.getLocation());
						validatedModel.setStreet(stageModel.getStreet());
						validatedModel.setTown(stageModel.getTown());
						validatedModel.setUsrn(stageModel.getUsrn());
						validatedModel.setActivity_type(stageModel.getActivity_type());
						validatedModel.setWork_description(stageModel.getWork_description());
						validatedModel.setWorks_with_excavation(stageModel.getWorks_with_excavation());
						validatedModel.setReinstatement_category(stageModel.getReinstatement_category());
						validatedModel.setTraffic_management_method(stageModel.getTraffic_management_method());
						validatedModel.setCollaborative_working(stageModel.getCollaborative_working());
						validatedModel.setEastimated_start_date(stageModel.getEastimated_start_date());
						validatedModel.setProposed_start_time(stageModel.getProposed_start_time());
						validatedModel.setProposed_end_date(stageModel.getProposed_end_date());
						validatedModel.setProposed_end_time(stageModel.getProposed_end_time());
						validatedModel.setDuration_of_work(stageModel.getDuration_of_work());
						validatedModel.setPermit_status(stageModel.getPermit_status());
						validatedModel.setPermit_deeming_date(stageModel.getPermit_deeming_date());
						validatedModel.setDiscount(stageModel.getDiscount());
						validatedModel.setComments_txt(comment.trim());
						validatedModel.setOffice_code(stageModel.getOffice_code());
						validatedModel.setYear_month(stageModel.getYear_month());
						validatedModel.setStatus("UPDATED");
						validatedModel.setUpload_date(stageModel.getUpload_date());
						validatedModel.setCreated_by(Integer.parseInt(empNo));
						validatedModel.setFile_name(file_name);
						validatedModel.setCreated_at(currentDate);
					}

				}

				String hql = "delete from PAExportStage where file_name= :file_name and status is null";
				session.createQuery(hql).setString("file_name", file_name).executeUpdate();

				hql = "update PAExportProvisional set status='VALIDATED' where file_name= :file_name and status is null";
				session.createQuery(hql).setString("file_name", file_name).executeUpdate();

				session.getTransaction().commit();
				returnValue = true;
			} else {
				returnValue = false;

			}

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return returnValue;

	}

	public boolean saveChangeRequestToValidate(String file_name, String empNo, String comment) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		boolean returnValue = false;
		Date currentDate = new Date();
		try {
			if (session != null) {
				session.beginTransaction();

				List<ChangeRequestStage> stageModelList = new ArrayList();

				Criteria criteria = session.createCriteria(ChangeRequestStage.class)
						.add(Restrictions.eq("file_name", file_name)).add(Restrictions.isNull("status"));

				stageModelList = criteria.list();
				ListIterator<ChangeRequestStage> listItr = stageModelList.listIterator();

				while (listItr.hasNext()) {
					ChangeRequestStage stageModel = new ChangeRequestStage();
					stageModel = (ChangeRequestStage) listItr.next();
					ChangeRequestValidated validatedModel = new ChangeRequestValidated();

					Criteria criteriaValidated = session.createCriteria(ChangeRequestValidated.class).add(Restrictions
							.eq("change_request_reference_number", stageModel.getChange_request_reference_number()));

					validatedModel = (ChangeRequestValidated) criteriaValidated.uniqueResult();

					if (validatedModel == null) {

						ChangeRequestValidated insertValidatedModel = new ChangeRequestValidated();

						insertValidatedModel
								.setChange_request_reference_number(stageModel.getChange_request_reference_number());
						insertValidatedModel.setPermit_reference_number(stageModel.getPermit_reference_number());
						insertValidatedModel.setChange_request_type(stageModel.getChange_request_type());
						insertValidatedModel.setApplication_type("Variation");
						insertValidatedModel.setType_of_work(stageModel.getType_of_work());
						insertValidatedModel.setChange_status(stageModel.getChange_status());
						insertValidatedModel.setAssessment_discount(stageModel.getAssessment_discount());
						insertValidatedModel.setChange_submission_date((stageModel.getChange_submission_date()));
						insertValidatedModel.setPa_proposed_start_date(stageModel.getPa_proposed_start_date());
						insertValidatedModel.setPa_proposed_end_date(stageModel.getPa_proposed_end_date());
						insertValidatedModel.setPromoter_name(stageModel.getPromoter_name());

						if (stageModel.getPa_proposed_end_date() != null
								&& stageModel.getPa_proposed_start_date() != null) {

							insertValidatedModel.setDuration(new BigDecimal(helper.numberOfDays(
									stageModel.getPa_proposed_start_date(), stageModel.getPa_proposed_end_date())));
						}

						insertValidatedModel.setComments_txt(comment.trim());
						insertValidatedModel.setOffice_code(stageModel.getOffice_code());
						insertValidatedModel.setYear_month(stageModel.getYear_month());
						insertValidatedModel.setStatus("INSERTED");
						insertValidatedModel.setUpload_date(stageModel.getUpload_date());
						insertValidatedModel.setCreated_by(Integer.parseInt(empNo));
						insertValidatedModel.setFile_name(file_name);
						insertValidatedModel.setCreated_at(currentDate);
						session.save(insertValidatedModel);
					}

					else if (validatedModel != null && validatedModel.getChange_request_reference_number() != null
							&& validatedModel.getChange_request_reference_number().length() > 0) {

						validatedModel
								.setChange_request_reference_number(stageModel.getChange_request_reference_number());
						validatedModel.setPermit_reference_number(stageModel.getPermit_reference_number());
						validatedModel.setChange_request_type(stageModel.getChange_request_type());
						validatedModel.setApplication_type("Variation");
						validatedModel.setType_of_work(stageModel.getType_of_work());
						validatedModel.setChange_status(stageModel.getChange_status());
						validatedModel.setAssessment_discount(stageModel.getAssessment_discount());
						validatedModel.setChange_submission_date((stageModel.getChange_submission_date()));
						validatedModel.setPa_proposed_start_date(stageModel.getPa_proposed_start_date());
						validatedModel.setPa_proposed_end_date(stageModel.getPa_proposed_end_date());
						validatedModel.setPromoter_name(stageModel.getPromoter_name());

						if (stageModel.getPa_proposed_end_date() != null
								&& stageModel.getPa_proposed_start_date() != null) {

							validatedModel.setDuration(new BigDecimal(helper.numberOfDays(
									stageModel.getPa_proposed_start_date(), stageModel.getPa_proposed_end_date())));
						}

						validatedModel.setComments_txt(comment.trim());
						validatedModel.setOffice_code(stageModel.getOffice_code());
						validatedModel.setYear_month(stageModel.getYear_month());
						validatedModel.setStatus("UPDATED");
						validatedModel.setUpload_date(stageModel.getUpload_date());
						validatedModel.setCreated_by(Integer.parseInt(empNo));
						validatedModel.setFile_name(file_name);
						validatedModel.setCreated_at(currentDate);
					}

				}

				String hql = "delete from ChangeRequestStage where file_name= :file_name and status is null";
				session.createQuery(hql).setString("file_name", file_name).executeUpdate();

				hql = "update ChangeRequestProvisional set status='VALIDATED' where file_name= :file_name and status is null";
				session.createQuery(hql).setString("file_name", file_name).executeUpdate();

				session.getTransaction().commit();
				returnValue = true;
			} else {
				returnValue = false;

			}

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return returnValue;

	}

	public List<ChangeRequestVO> ChangeRequestValidation(List<ChangeRequestStage> changeExportStageList)
			throws ParseException {
		List<ChangeRequestVO> changeRequestValidationLogicList = new ArrayList<ChangeRequestVO>();

		ListIterator<ChangeRequestStage> listItrStage = changeExportStageList.listIterator();
		while (listItrStage.hasNext()) {
			ChangeRequestStage changeRequestStageModel = new ChangeRequestStage();
			ChangeRequestVO changeVO = new ChangeRequestVO();

			changeRequestStageModel = (ChangeRequestStage) listItrStage.next();

			if (changeRequestStageModel.getChange_request_reference_number() == null
					|| changeRequestStageModel.getChange_request_reference_number().trim().length() == 0) {
				changeVO.setChange_request_reference_number_validated(false);

			} else {
				changeVO.setChange_request_reference_number_validated(true);
			}

			if (changeRequestStageModel.getPermit_reference_number() == null
					|| changeRequestStageModel.getPermit_reference_number().trim().length() == 0) {
				changeVO.setPermit_reference_number_validated(false);

			}
			/*
			 * THIS POINT IGNORED AS PER KR on 06/01/2022 else
			 * if(!checkPermitNoinPAExport(changeRequestStageModel.getYear_month(),
			 * changeRequestStageModel.getOffice_code(),
			 * changeRequestStageModel.getPermit_reference_number().trim())) {
			 * 
			 * changeVO.setPermit_reference_number_validated(false); }
			 */
			else {
				changeVO.setPermit_reference_number_validated(true);
			}

			if (changeRequestStageModel.getChange_request_type() == null
					|| changeRequestStageModel.getChange_request_type().trim().length() == 0) {
				changeVO.setChange_request_type_validated(false);

			} else {
				changeVO.setChange_request_type_validated(true);
			}

			if (changeRequestStageModel.getType_of_work() == null
					|| changeRequestStageModel.getType_of_work().trim().length() == 0) {
				changeVO.setType_of_work_validated(false);

			}

			else if ((!changeRequestStageModel.getType_of_work().trim().equals("Major"))
					&& (!changeRequestStageModel.getType_of_work().trim().equals("Standard"))
					&& (!changeRequestStageModel.getType_of_work().trim().equals("Minor"))
					&& (!changeRequestStageModel.getType_of_work().trim().equals("Immediate - urgent"))
					&& (!changeRequestStageModel.getType_of_work().trim().equals("Immediate - emergency"))) {

				changeVO.setType_of_work_validated(false);
			} else {
				changeVO.setType_of_work_validated(true);
			}

			if (changeRequestStageModel.getChange_status() == null
					|| changeRequestStageModel.getChange_status().trim().length() == 0) {
				changeVO.setChange_status_validated(false);

			}

			else {
				changeVO.setChange_status_validated(true);
			}

			if (changeRequestStageModel.getChange_submission_date() == null) {
				changeVO.setChange_submission_date_validated(false);

			}

			else {
				changeVO.setChange_submission_date_validated(true);
			}

			if (changeRequestStageModel.getAssessment_discount() == null) {
				changeVO.setAssessment_discount_validated(false);

			}

			else {
				changeVO.setAssessment_discount_validated(true);
			}

			if (changeRequestStageModel.getPa_proposed_start_date() == null) {
				changeVO.setPa_proposed_start_date_validated(false);

			}

			else {
				changeVO.setPa_proposed_start_date_validated(true);
			}

			if (changeRequestStageModel.getPa_proposed_end_date() == null) {
				changeVO.setPa_proposed_end_date_validated(false);

			}

			else {
				changeVO.setPa_proposed_end_date_validated(true);
			}

			if (changeRequestStageModel.getDuration() == null) {
				changeVO.setDuration_validated(false);

			}

			else {
				changeVO.setDuration_validated(true);
			}

			if (changeRequestStageModel.getChange_request_type() != null
					&& changeRequestStageModel.getChange_request_type().trim().length() == 0
					&& changeRequestStageModel.getChange_request_type().equalsIgnoreCase("HA imposed change")) {

				changeRequestStageModel.setChange_status("AIV");
				changeVO.setChange_status_validated(true);

			}

			if (changeRequestStageModel.getChange_request_type() != null
					&& changeRequestStageModel.getChange_request_type().trim().length() == 0
					&& changeRequestStageModel.getChange_request_type().equalsIgnoreCase("Promoter imposed change")) {

				updatePermiStatusinPAExport(changeRequestStageModel.getYear_month(),
						changeRequestStageModel.getOffice_code(),
						changeRequestStageModel.getPermit_reference_number().trim());

			}

			changeVO.setApplication_type_validated(true);

			if ((!changeVO.isType_of_work_validated()) || (!changeVO.isApplication_type_validated())
					|| (!changeVO.isAssessment_discount_validated())
					|| (!changeVO.isChange_request_reference_number_validated())
					|| (!changeVO.isChange_request_type_validated()) || (!changeVO.isChange_status_validated())
					|| (!changeVO.isChange_submission_date_validated()) || (!changeVO.isDuration_validated())
					|| (!changeVO.isPa_proposed_end_date_validated())
					|| (!changeVO.isPa_proposed_start_date_validated())
					|| (!changeVO.isPermit_reference_number_validated())) {

				changeVO.setChange_request_stage_id(changeRequestStageModel.getChange_request_stage_id());
				changeVO.setChange_request_reference_number(
						changeRequestStageModel.getChange_request_reference_number());
				changeVO.setPermit_reference_number(changeRequestStageModel.getPermit_reference_number());
				changeVO.setChange_request_type(changeRequestStageModel.getChange_request_type());
				changeVO.setApplication_type("Variation");
				changeVO.setType_of_work(changeRequestStageModel.getType_of_work());
				changeVO.setChange_status(changeRequestStageModel.getChange_status());
				changeVO.setChange_submission_date(sdf.format(changeRequestStageModel.getChange_submission_date()));
				changeVO.setAssessment_discount(changeRequestStageModel.getAssessment_discount());
				changeVO.setPa_proposed_start_date(sdf1.format(changeRequestStageModel.getPa_proposed_start_date()));
				changeVO.setPa_proposed_end_date(sdf1.format(changeRequestStageModel.getPa_proposed_end_date()));
				changeVO.setDuration(changeRequestStageModel.getDuration());

				changeRequestValidationLogicList.add(changeVO);

			}
		}

		System.out.println("Error Record Count is " + changeRequestValidationLogicList.size());
		return changeRequestValidationLogicList;
	}

	public boolean UpdateChangeRequestStage(List<ChangeRequestStage> alledited, String file_name) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		boolean returnValue = false;

		try {
			if (session != null) {
				session.beginTransaction();

				ListIterator<ChangeRequestStage> listItr = alledited.listIterator();
				ChangeRequestStage changeStageModel = new ChangeRequestStage();
				while (listItr.hasNext()) {

					ChangeRequestStage stageModel = new ChangeRequestStage();
					stageModel = (ChangeRequestStage) listItr.next();

					Criteria criteria = session.createCriteria(ChangeRequestStage.class)
							.add(Restrictions.eq("file_name", file_name))
							.add(Restrictions.eq("change_request_stage_id", stageModel.getChange_request_stage_id()));

					changeStageModel = (ChangeRequestStage) criteria.uniqueResult();

					if (stageModel.getChange_request_reference_number() != null)
						changeStageModel
								.setChange_request_reference_number(stageModel.getChange_request_reference_number());

					if (stageModel.getPermit_reference_number() != null)
						changeStageModel.setPermit_reference_number(stageModel.getPermit_reference_number());

					if (stageModel.getChange_request_type() != null)
						changeStageModel.setChange_request_type(stageModel.getChange_request_type());

					if (stageModel.getApplication_type() != null)
						changeStageModel.setApplication_type("Variation");

					if (stageModel.getType_of_work() != null)
						changeStageModel.setType_of_work(stageModel.getType_of_work());

					if (stageModel.getChange_status() != null)
						changeStageModel.setChange_status(stageModel.getChange_status());

					if (stageModel.getAssessment_discount() != null)
						changeStageModel.setAssessment_discount(stageModel.getAssessment_discount());

					if (stageModel.getPromoter_name() != null)
						changeStageModel.setPromoter_name(stageModel.getPromoter_name());

					if (stageModel.getChange_submission_date() != null)
						changeStageModel.setChange_submission_date((stageModel.getChange_submission_date()));

					if (stageModel.getPa_proposed_start_date() != null)
						changeStageModel.setPa_proposed_start_date(stageModel.getPa_proposed_start_date());

					if (stageModel.getPa_proposed_end_date() != null)
						changeStageModel.setPa_proposed_end_date(stageModel.getPa_proposed_end_date());

					if (stageModel.getPa_proposed_end_date() != null
							&& stageModel.getPa_proposed_start_date() != null) {

						changeStageModel
								.setDuration(new BigDecimal(helper.numberOfDays(stageModel.getPa_proposed_start_date(),
										stageModel.getPa_proposed_end_date())));
					}

					session.update(changeStageModel);

				}

				session.getTransaction().commit();
				returnValue = true;
			} else {
				returnValue = false;

			}

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return returnValue;

	}

	public List<FeeExportVO> feeExportValidation(List<FeeExportStage> feeExportStageList) throws ParseException {
		List<FeeExportVO> feeExportValidationLogicList = new ArrayList<FeeExportVO>();

		ListIterator<FeeExportStage> listItrStage = feeExportStageList.listIterator();
		while (listItrStage.hasNext()) {
			FeeExportStage feeExportStageModel = new FeeExportStage();
			FeeExportVO feeVO = new FeeExportVO();

			feeExportStageModel = (FeeExportStage) listItrStage.next();

			if (feeExportStageModel.getOrg_code() == null || feeExportStageModel.getOrg_code().trim().length() == 0) {
				feeVO.setOrg_code_validate(false);

			} else {
				feeVO.setOrg_code_validate(true);
			}

			if (feeExportStageModel.getOrg_name() == null || feeExportStageModel.getOrg_name().trim().length() == 0) {
				feeVO.setOrg_name_validate(false);

			} else {
				feeVO.setOrg_name_validate(true);
			}

			if (feeExportStageModel.getPromoter_workstream() == null
					|| feeExportStageModel.getPromoter_workstream().trim().length() == 0) {
				feeVO.setPromoter_workstream_validate(false);

			} else {
				feeVO.setPromoter_workstream_validate(true);
			}

			if (feeExportStageModel.getWork_category() == null
					|| feeExportStageModel.getWork_category().trim().length() == 0) {
				feeVO.setWork_category_validate(false);

			}

			else if ((!feeExportStageModel.getWork_category().trim().equals("Major"))
					&& (!feeExportStageModel.getWork_category().trim().equals("Standard"))
					&& (!feeExportStageModel.getWork_category().trim().equals("Minor"))
					&& (!feeExportStageModel.getWork_category().trim().equals("Immediate - urgent"))
					&& (!feeExportStageModel.getWork_category().trim().equals("Immediate - emergency"))) {

				feeVO.setWork_category_validate(false);
			} else {
				feeVO.setWork_category_validate(true);
			}

			if (feeExportStageModel.getTransaction_type() == null
					|| feeExportStageModel.getTransaction_type().trim().length() == 0) {
				feeVO.setTransaction_type_validate(false);

			} else {
				feeVO.setTransaction_type_validate(true);
			}

			if (feeExportStageModel.getDiscount_percentage() == null) {
				feeVO.setDiscount_percentage_validate(false);

			} else {
				feeVO.setDiscount_percentage_validate(true);
			}

			if (feeExportStageModel.getPermit_reference() == null
					|| feeExportStageModel.getPermit_reference().trim().length() == 0) {
				feeVO.setPermit_reference_validate(false);

			}
			/*
			 * else if(!checkPermitNoinPAExport(feeExportStageModel.getYear_month(),
			 * feeExportStageModel.getOffice_code(),
			 * feeExportStageModel.getPermit_reference().trim())) {
			 * 
			 * feeVO.setPermit_reference_validate(false); }
			 */
			else {
				feeVO.setPermit_reference_validate(true);
			}

			if (feeExportStageModel.getGranted_date() == null) {
				feeVO.setGranted_date_validate(false);

			}

			else {
				feeVO.setGranted_date_validate(true);

			}

			if (feeExportStageModel.getUsrn() == null || feeExportStageModel.getUsrn().toString().length() != 8) {
				feeVO.setUsrn_validate(false);

			} else {
				feeVO.setUsrn_validate(true);
			}

			if (feeExportStageModel.getStreet() == null || feeExportStageModel.getStreet().trim().length() == 0) {
				feeVO.setStreet_validate(false);

			} else {
				feeVO.setStreet_validate(true);
			}

			if (feeExportStageModel.getTown() == null || feeExportStageModel.getTown().trim().length() == 0) {
				feeVO.setTown_validate(false);

			} else {
				feeVO.setTown_validate(true);
			}

			if (feeExportStageModel.getRoad_category() == null
					|| feeExportStageModel.getRoad_category().trim().length() == 0) {
				feeVO.setRoad_category_validate(false);

			} else {
				feeVO.setRoad_category_validate(true);
			}

			if (feeExportStageModel.getIs_works_traffic_sensitive() == null
					|| feeExportStageModel.getIs_works_traffic_sensitive().trim().length() == 0) {
				feeVO.setIs_works_traffic_sensitive_validate(false);

			} else if ((!feeExportStageModel.getIs_works_traffic_sensitive().contains("Yes"))
					&& (!feeExportStageModel.getIs_works_traffic_sensitive().contains("No"))) {

				feeVO.setIs_works_traffic_sensitive_validate(false);
			} else {
				feeVO.setIs_works_traffic_sensitive_validate(true);

			}

			if (feeExportStageModel.getIs_street_traffic_sensitive() == null
					|| feeExportStageModel.getIs_street_traffic_sensitive().trim().length() == 0) {
				feeVO.setIs_street_traffic_sensitive_validate(false);

			} else if ((!feeExportStageModel.getIs_street_traffic_sensitive().contains("Yes"))
					&& (!feeExportStageModel.getIs_street_traffic_sensitive().contains("No"))) {

				feeVO.setIs_street_traffic_sensitive_validate(false);
			} else {
				feeVO.setIs_street_traffic_sensitive_validate(true);

			}

			if (feeExportStageModel.getTraffic_management_type() == null
					|| feeExportStageModel.getTraffic_management_type().trim().length() == 0) {
				feeVO.setTraffic_management_type_validate(false);

			} else {
				feeVO.setTraffic_management_type_validate(true);
			}

			if (feeExportStageModel.getProposed_start_date() == null) {
				feeVO.setProposed_start_date_validate(false);

			}

			else {
				feeVO.setProposed_start_date_validate(true);
			}

			if (feeExportStageModel.getProposed_end_date() == null) {
				feeVO.setProposed_end_date_validate(false);

			}

			else {
				feeVO.setProposed_end_date_validate(true);
			}

			/*
			 * if(feeExportStageModel.getChange_request_reference()==null ||
			 * feeExportStageModel.getChange_request_reference().trim().length()==0) {
			 * feeVO.setChange_request_reference_validate(false);
			 * 
			 * }
			 */
			/*
			 * else if(!checkChangeRequestNoinChangeRequestExport(feeExportStageModel.
			 * getYear_month(),feeExportStageModel.getOffice_code(),
			 * feeExportStageModel.getChange_request_reference().trim())) {
			 * 
			 * feeVO.setPermit_reference_validate(false); }
			 */
			/*
			 * else {
			 */
			feeVO.setChange_request_reference_validate(true);
			/* } */

			if ((!feeVO.isOrg_code_validate()) || (!feeVO.isOrg_name_validate())
					|| (!feeVO.isPromoter_workstream_validate()) || (!feeVO.isWork_category_validate())
					|| (!feeVO.isTransaction_type_validate()) || (!feeVO.isDiscount_percentage_validate())
					|| (!feeVO.isPermit_reference_validate()) || (!feeVO.isGranted_date_validate())
					|| (!feeVO.isUsrn_validate()) || (!feeVO.isStreet_validate()) || (!feeVO.isTown_validate())
					|| (!feeVO.isRoad_category_validate()) || (!feeVO.isIs_works_traffic_sensitive_validate())
					|| (!feeVO.isIs_street_traffic_sensitive_validate())
					|| (!feeVO.isTraffic_management_type_validate()) || (!feeVO.isProposed_start_date_validate())
					|| (!feeVO.isProposed_end_date_validate()) || (!feeVO.isChange_request_reference_validate())) {

				feeVO.setFee_export_stage_id(feeExportStageModel.getFee_export_stage_id());
				feeVO.setOrg_code(feeExportStageModel.getOrg_code());
				feeVO.setOrg_name(feeExportStageModel.getOrg_name());
				feeVO.setPromoter_workstream(feeExportStageModel.getPromoter_workstream());
				feeVO.setWork_category(feeExportStageModel.getWork_category());
				feeVO.setTransaction_type(feeExportStageModel.getTransaction_type());
				feeVO.setDiscount_percentage(feeExportStageModel.getDiscount_percentage());
				feeVO.setPermit_reference(feeExportStageModel.getPermit_reference());
				feeVO.setGranted_date(sdf.format(feeExportStageModel.getGranted_date()));
				feeVO.setUsrn(feeExportStageModel.getUsrn());
				feeVO.setStreet(feeExportStageModel.getStreet());
				feeVO.setTown(feeExportStageModel.getTown());
				feeVO.setRoad_category(feeExportStageModel.getRoad_category());
				feeVO.setIs_works_traffic_sensitive(feeExportStageModel.getIs_works_traffic_sensitive());
				feeVO.setIs_street_traffic_sensitive(feeExportStageModel.getIs_street_traffic_sensitive());
				feeVO.setTraffic_management_type(feeExportStageModel.getTraffic_management_type());
				feeVO.setProposed_start_date(sdf1.format(feeExportStageModel.getProposed_start_date()));
				feeVO.setProposed_end_date(sdf1.format(feeExportStageModel.getProposed_end_date()));
				feeVO.setChange_request_reference(feeExportStageModel.getChange_request_reference());

				feeExportValidationLogicList.add(feeVO);

			}
		}

		System.out.println("Error Record Count is FEE EXPORT " + feeExportValidationLogicList.size());
		return feeExportValidationLogicList;
	}

	public boolean UpdateFEEExportStage(List<FeeExportStage> alledited, String file_name) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		boolean returnValue = false;

		try {
			if (session != null) {
				session.beginTransaction();

				ListIterator<FeeExportStage> listItr = alledited.listIterator();
				FeeExportStage FeeExportStageModel = new FeeExportStage();
				while (listItr.hasNext()) {

					FeeExportStage stageModel = new FeeExportStage();
					stageModel = (FeeExportStage) listItr.next();

					Criteria criteria = session.createCriteria(FeeExportStage.class)
							.add(Restrictions.eq("file_name", file_name))
							.add(Restrictions.eq("fee_export_stage_id", stageModel.getFee_export_stage_id()));

					FeeExportStageModel = (FeeExportStage) criteria.uniqueResult();

					if (stageModel.getOrg_code() != null)
						FeeExportStageModel.setOrg_code(stageModel.getOrg_code());
					if (stageModel.getOrg_name() != null)
						FeeExportStageModel.setOrg_name(stageModel.getOrg_name());
					if (stageModel.getPromoter_workstream() != null)
						FeeExportStageModel.setPromoter_workstream(stageModel.getPromoter_workstream());
					if (stageModel.getWork_category() != null)
						FeeExportStageModel.setWork_category(stageModel.getWork_category());
					if (stageModel.getTransaction_type() != null)
						FeeExportStageModel.setTransaction_type(stageModel.getTransaction_type());
					if (stageModel.getDiscount_percentage() != null)
						FeeExportStageModel.setDiscount_percentage(stageModel.getDiscount_percentage());
					if (stageModel.getPermit_reference() != null)
						FeeExportStageModel.setPermit_reference(stageModel.getPermit_reference());
					if (stageModel.getGranted_date() != null)
						FeeExportStageModel.setGranted_date(stageModel.getGranted_date());
					if (stageModel.getUsrn() != null)
						FeeExportStageModel.setUsrn(stageModel.getUsrn());
					if (stageModel.getStreet() != null)
						FeeExportStageModel.setStreet(stageModel.getStreet());
					if (stageModel.getTown() != null)
						FeeExportStageModel.setTown(stageModel.getTown());
					if (stageModel.getRoad_category() != null)
						FeeExportStageModel.setRoad_category(stageModel.getRoad_category());
					if (stageModel.getIs_works_traffic_sensitive() != null)
						FeeExportStageModel.setIs_works_traffic_sensitive(stageModel.getIs_works_traffic_sensitive());
					if (stageModel.getIs_street_traffic_sensitive() != null)
						FeeExportStageModel.setIs_street_traffic_sensitive(stageModel.getIs_street_traffic_sensitive());
					if (stageModel.getTraffic_management_type() != null)
						FeeExportStageModel.setTraffic_management_type(stageModel.getTraffic_management_type());
					if (stageModel.getProposed_start_date() != null)
						FeeExportStageModel.setProposed_start_date(stageModel.getProposed_start_date());
					if (stageModel.getProposed_end_date() != null)
						FeeExportStageModel.setProposed_end_date(stageModel.getProposed_end_date());
					if (stageModel.getChange_request_reference() != null)
						FeeExportStageModel.setChange_request_reference(stageModel.getChange_request_reference());

					session.update(FeeExportStageModel);

				}

				session.getTransaction().commit();
				returnValue = true;
			} else {
				returnValue = false;

			}

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return returnValue;

	}

	public boolean saveToFeeExportValidate(String file_name, String empNo, String comment) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		boolean returnValue = false;
		Date currentDate = new Date();
		String coodinator = "";
		BigDecimal permitFee = new BigDecimal(0);
		String workType = "";
		String sentCoodinator = "";
		int numberOfDays = 0;
		try {
			if (session != null) {
				session.beginTransaction();

				List<FeeExportStage> stageModelList = new ArrayList();
				List<PermitFeeMasterModel> feeMasterList = new ArrayList();

				Criteria criteria = session.createCriteria(FeeExportStage.class)
						.add(Restrictions.eq("file_name", file_name)).add(Restrictions.isNull("status"));

				stageModelList = criteria.list();

				Criteria feeMasterCriteria = session.createCriteria(PermitFeeMasterModel.class)
						.add(Restrictions.eq("isactive", true));
				feeMasterList = feeMasterCriteria.list();

				ListIterator<FeeExportStage> listItr = stageModelList.listIterator();

				while (listItr.hasNext()) {
					FeeExportStage stageModel = new FeeExportStage();
					stageModel = (FeeExportStage) listItr.next();

					FeeExportValidate validatedModel = new FeeExportValidate();

					Criteria criteriaValidated = session.createCriteria(FeeExportValidate.class)
							.add(Restrictions.eq("fee_export_validate_id", stageModel.getFee_export_stage_id()));

					validatedModel = (FeeExportValidate) criteriaValidated.uniqueResult();

					if (validatedModel == null) {

						FeeExportValidate insertValidatedModel = new FeeExportValidate();
						insertValidatedModel.setFee_export_validate_id(stageModel.getFee_export_stage_id());
						insertValidatedModel.setOrg_code(stageModel.getOrg_code());
						insertValidatedModel.setOrg_name(stageModel.getOrg_name());
						insertValidatedModel.setPromoter_workstream(stageModel.getPromoter_workstream());
						insertValidatedModel.setWork_category(stageModel.getWork_category());
						insertValidatedModel.setTransaction_type(stageModel.getTransaction_type());
						insertValidatedModel.setDiscount_percentage(stageModel.getDiscount_percentage());
						insertValidatedModel.setPermit_reference(stageModel.getPermit_reference());
						insertValidatedModel.setGranted_date(stageModel.getGranted_date());
						insertValidatedModel.setUsrn(stageModel.getUsrn());
						insertValidatedModel.setStreet(stageModel.getStreet());
						insertValidatedModel.setTown(stageModel.getTown());
						insertValidatedModel.setRoad_category(stageModel.getRoad_category());
						insertValidatedModel.setIs_works_traffic_sensitive(stageModel.getIs_works_traffic_sensitive());
						insertValidatedModel
								.setIs_street_traffic_sensitive(stageModel.getIs_street_traffic_sensitive());
						insertValidatedModel.setTraffic_management_type(stageModel.getTraffic_management_type());
						insertValidatedModel.setProposed_start_date(stageModel.getProposed_start_date());
						insertValidatedModel.setProposed_end_date(stageModel.getProposed_end_date());
						insertValidatedModel.setChange_request_reference(stageModel.getChange_request_reference());
						insertValidatedModel.setComments_txt(comment.trim());
						insertValidatedModel.setOffice_code(stageModel.getOffice_code());
						insertValidatedModel.setYear_month(stageModel.getYear_month());
						insertValidatedModel.setStatus("INSERTED");
						insertValidatedModel.setUpload_date(stageModel.getUpload_date());
						insertValidatedModel.setCreated_by(Integer.parseInt(empNo));
						insertValidatedModel.setFile_name(file_name);
						insertValidatedModel.setCreated_at(currentDate);
						session.save(insertValidatedModel);
					}

					else if (validatedModel != null
							&& String.valueOf(validatedModel.getFee_export_validate_id()) != null
							&& String.valueOf(validatedModel.getFee_export_validate_id()).trim().length() > 0) {

						validatedModel.setOrg_code(stageModel.getOrg_code());
						validatedModel.setOrg_name(stageModel.getOrg_name());
						validatedModel.setPromoter_workstream(stageModel.getPromoter_workstream());
						validatedModel.setWork_category(stageModel.getWork_category());
						validatedModel.setTransaction_type(stageModel.getTransaction_type());
						validatedModel.setDiscount_percentage(stageModel.getDiscount_percentage());
						validatedModel.setPermit_reference(stageModel.getPermit_reference());
						validatedModel.setGranted_date(stageModel.getGranted_date());
						validatedModel.setUsrn(stageModel.getUsrn());
						validatedModel.setStreet(stageModel.getStreet());
						validatedModel.setTown(stageModel.getTown());
						validatedModel.setRoad_category(stageModel.getRoad_category());
						validatedModel.setIs_works_traffic_sensitive(stageModel.getIs_works_traffic_sensitive());
						validatedModel.setIs_street_traffic_sensitive(stageModel.getIs_street_traffic_sensitive());
						validatedModel.setTraffic_management_type(stageModel.getTraffic_management_type());
						validatedModel.setProposed_start_date(stageModel.getProposed_start_date());
						validatedModel.setProposed_end_date(stageModel.getProposed_end_date());
						validatedModel.setChange_request_reference(stageModel.getChange_request_reference());
						validatedModel.setComments_txt(comment.trim());
						validatedModel.setOffice_code(stageModel.getOffice_code());
						validatedModel.setYear_month(stageModel.getYear_month());

						validatedModel.setStatus("UPDATED");
						validatedModel.setCreated_by(Integer.parseInt(empNo));
						validatedModel.setUpload_date(currentDate);
						validatedModel.setCreated_at(currentDate);
						validatedModel.setFile_name(file_name);
						session.update(validatedModel);
					}

					criteria = session.createCriteria(PAExportValidated.class)
							.add(Restrictions.eq("office_code", stageModel.getOffice_code()))
							.add(Restrictions.eq("year_month", stageModel.getYear_month()))
							.add(Restrictions.eq("permit_reference_number", stageModel.getPermit_reference()));

					List<PAExportValidated> paExportList = criteria.list();

					ListIterator<PAExportValidated> listItrValidate = paExportList.listIterator();
					while (listItrValidate.hasNext()) {
						PAExportValidated validateeModel = new PAExportValidated();
						validateeModel = (PAExportValidated) listItrValidate.next();
						validateeModel.setGrant_permit_date(stageModel.getGranted_date());
						validateeModel.setOut_of_working_hours(stageModel.getIs_works_traffic_sensitive());

						if ((validateeModel.getReinstatement_category().startsWith("0"))
								|| (validateeModel.getReinstatement_category().startsWith("1"))
								|| (validateeModel.getReinstatement_category().startsWith("2"))) {
							validateeModel.setCoordinator("CAT 0,1,2 or TS");
							coodinator = "CAT 0,1,2 or TS";
						} else if ((validateeModel.getReinstatement_category().startsWith("3"))
								|| (validateeModel.getReinstatement_category().startsWith("4"))) {
							if (stageModel.getIs_street_traffic_sensitive().equalsIgnoreCase("Yes")) {
								validateeModel.setCoordinator("CAT 0,1,2 or TS");
								coodinator = "CAT 0,1,2 or TS";

							} else if (stageModel.getIs_street_traffic_sensitive().equalsIgnoreCase("No")) {
								validateeModel.setCoordinator("CAT 3,4 and Non TS");
								coodinator = "CAT 3,4 and Non TS";
							}
						}

						else if ((validateeModel.getReinstatement_category().startsWith("6"))
								|| (validateeModel.getReinstatement_category().startsWith("7"))
								|| (validateeModel.getReinstatement_category().startsWith("8"))) {
							if (stageModel.getIs_street_traffic_sensitive().equalsIgnoreCase("Yes")) {
								validateeModel.setCoordinator("CAT 0,1,2 or TS");
								coodinator = "CAT 0,1,2 or TS";

							} else if (stageModel.getIs_street_traffic_sensitive().equalsIgnoreCase("No")) {
								validateeModel.setCoordinator("CAT 3,4 and Non TS");

								coodinator = "CAT 3,4 and Non TS";
							}
						} else {
							validateeModel.setCoordinator("");
							coodinator = "";
						}

						if (coodinator.equalsIgnoreCase("CAT 3,4 and Non TS")
								|| coodinator.equalsIgnoreCase("CAT 0,1,2 or TS")) {

							if (validateeModel.getType_of_work().startsWith("Immediate"))
								workType = "Immediate";
							else if (validateeModel.getType_of_work().startsWith("Major"))
								workType = "Major";
							else
								workType = validateeModel.getType_of_work().trim();

							if (coodinator.equalsIgnoreCase("CAT 3,4 and Non TS"))
								sentCoodinator = "Cat 3-4";
							else if (coodinator.equalsIgnoreCase("CAT 0,1,2 or TS"))
								sentCoodinator = "Cat 0-2";

							if (validateeModel.getEastimated_start_date() != null
									&& validateeModel.getProposed_end_date() != null)
								numberOfDays = helper.numberOfDays(validateeModel.getEastimated_start_date(),
										validateeModel.getProposed_end_date());

							permitFee = calculatePermitFee(validateeModel.getOffice_code(),
									validateeModel.getApplication_type(), workType, sentCoodinator, numberOfDays,
									feeMasterList);

							log.fatal(permitFee);

						}

						validateeModel.setPermit_fee(permitFee);
						session.update(validateeModel);

					}

					criteria = session.createCriteria(ChangeRequestValidated.class)
							.add(Restrictions.eq("office_code", stageModel.getOffice_code()))
							.add(Restrictions.eq("year_month", stageModel.getYear_month()))
							.add(Restrictions.eq("permit_reference_number", stageModel.getPermit_reference().trim()));

					List<ChangeRequestValidated> ChanngeRequestList = criteria.list();

					ListIterator<ChangeRequestValidated> listItrChange = ChanngeRequestList.listIterator();
					while (listItrChange.hasNext()) {

						log.fatal("I am Updating ...... " + stageModel.getPermit_reference() + "  " + coodinator);
						ChangeRequestValidated changeModel = new ChangeRequestValidated();
						changeModel = (ChangeRequestValidated) listItrChange.next();

						permitFee = calculatePermitFeeForChangeRequest(stageModel.getOffice_code(), sentCoodinator,
								feeMasterList);

						changeModel.setCoordinator(coodinator);
						changeModel.setPermit_fee(permitFee);
						session.update(changeModel);

					}

					/*
					 * updateGrantPermitDatePAExport(stageModel.getYear_month(),
					 * stageModel.getOffice_code(), stageModel.getPermit_reference().trim(),
					 * stageModel.getGranted_date());
					 */

					/*
					 * updateOutOfWorkPAExport(stageModel.getYear_month(),
					 * stageModel.getOffice_code(), stageModel.getPermit_reference().trim(),
					 * stageModel.getIs_works_traffic_sensitive());
					 */

					/*
					 * updateTrafficSensitiveinPAExport(stageModel.getYear_month(),
					 * stageModel.getOffice_code(), stageModel.getPermit_reference().trim(),
					 * stageModel.getIs_street_traffic_sensitive());
					 */
				}

				String hql = "delete from FeeExportStage where file_name= :file_name and status is null";
				session.createQuery(hql).setString("file_name", file_name).executeUpdate();

				hql = "update FeeExportProvisional set status='VALIDATED' where file_name= :file_name and status is null";
				session.createQuery(hql).setString("file_name", file_name).executeUpdate();

				session.getTransaction().commit();
				returnValue = true;
			} else {
				returnValue = false;

			}

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return returnValue;

	}

	public boolean insertOfficeMaster(OfficeCreateModel officeModel) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;
		try {
			if (session != null) {
				session.beginTransaction();
				session.save(officeModel);
				session.getTransaction().commit();
				returnValue = true;
			} else
				returnValue = false;

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");
		} finally {
			if (session.isOpen())
				session.close();
		}
		return returnValue;

	}

	public List<OfficeCreateModel> searchOfficeMaster(List<OfficeCreateModel> officeList) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(OfficeCreateModel.class)
						.add(Restrictions.isNotNull("isactive"));
				criteria.addOrder(Order.desc("isactive"));
				criteria.addOrder(Order.asc("office_master_id"));
				officeList = criteria.list();

				log.fatal("Office List Size " + officeList.size());

			}

		} catch (Exception e) {

			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return officeList;

	}

	public boolean updateOfficeMaster(OfficeCreateModel officeModel, String event) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;
		OfficeCreateModel model = new OfficeCreateModel();
		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(OfficeCreateModel.class)
						.add(Restrictions.eq("office_master_id", officeModel.getOffice_master_id()));

				model = (OfficeCreateModel) criteria.uniqueResult();

				if (event.equalsIgnoreCase("UPDATE")) {
					model.setUpdated_by(officeModel.getUpdated_by());
					model.setUpdated_datetime(officeModel.getUpdated_datetime());
					model.setOffice_desc(officeModel.getOffice_desc());
					model.setOffice_address(officeModel.getOffice_address());
					model.setOffice_type(officeModel.getOffice_type());
					model.setIsactive(officeModel.isIsactive());
				} else if (event.equalsIgnoreCase("DELETE")) {
					model.setUpdated_by(officeModel.getUpdated_by());
					model.setUpdated_datetime(officeModel.getUpdated_datetime());
					model.setIsactive(false);

				}

				session.update(model);

				StringBuffer permitFeeDelete = new StringBuffer(
						" update permit_fee_master set isactive=null where highway_authority_id = "
								+ officeModel.getOffice_master_id() + " and isactive=true ");
				StringBuffer userDelete = new StringBuffer(
						" update user_info set isactive=null where office_location = "
								+ officeModel.getOffice_master_id() + " and isactive=true ");

				StringBuffer permitFeeRetrive = new StringBuffer(
						" update permit_fee_master set isactive=true where highway_authority_id = "
								+ officeModel.getOffice_master_id());
				StringBuffer userRetrive = new StringBuffer(
						" update user_info set isactive=true where office_location = "
								+ officeModel.getOffice_master_id());

				if (event.equalsIgnoreCase("DELETE")) {
					SQLQuery sqlquery = session.createSQLQuery(permitFeeDelete.toString());
					sqlquery.executeUpdate();
					sqlquery = session.createSQLQuery(userDelete.toString());
					sqlquery.executeUpdate();

				}
				if (event.equalsIgnoreCase("UPDATE")) {

					if (!officeModel.isIsactive()) {
						SQLQuery sqlquery = session.createSQLQuery(permitFeeDelete.toString());
						sqlquery.executeUpdate();
						sqlquery = session.createSQLQuery(userDelete.toString());
						sqlquery.executeUpdate();
					} else if (officeModel.isIsactive()) {
						SQLQuery sqlquery = session.createSQLQuery(permitFeeRetrive.toString());
						sqlquery.executeUpdate();
						sqlquery = session.createSQLQuery(userRetrive.toString());
						sqlquery.executeUpdate();
					}

				}

				session.getTransaction().commit();
				returnValue = true;

			} else
				returnValue = false;

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");
		} finally {
			if (session.isOpen())
				session.close();
		}
		return returnValue;

	}

	public boolean insertUserMaster(UserCreateModel userModel) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;
		try {
			if (session != null) {
				session.beginTransaction();
				session.save(userModel);
				session.getTransaction().commit();
				returnValue = true;
			} else
				returnValue = false;

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");
		} finally {
			if (session.isOpen())
				session.close();
		}
		return returnValue;

	}

	public List<UserMasterVO> searchUserMasterList() throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<UserMasterVO> userMasterList = new ArrayList();
		try {
			if (session != null) {
				session.beginTransaction();

				StringBuffer sql = new StringBuffer(
						" select emp_no,office_location,user_name,user_password,user_mail,user_contact_no,").append(
								" user_address,isactive,user_activity, user_type,emp_name,OFFICE_DESC, user_type_value ")
								.append(" from(SELECT emp_no,office_location,user_name,user_password,user_mail,user_contact_no, ")
								.append(" user_address,A.isactive, (case when A.isactive is null then 'ZZ'      when A.isactive=true then 'XX' ")
								.append(" when A.isactive=false then 'YY' end)user_activity, user_type,emp_name,B.OFFICE_DESC OFFICE_DESC, ")
								.append(" (CASE WHEN user_type=1 THEN 'Super user'      WHEN user_type=2 THEN 'Admin user' ")
								.append(" WHEN user_type=3 THEN 'Normal user' end) user_type_value FROM USER_INFO A, OFFICE_MASTER B ")
								.append(" WHERE A.office_location=B.OFFICE_MASTER_ID and user_type!=1)P ")
								.append(" order by user_activity asc ,OFFICE_DESC, user_name ");

				log.fatal(sql.toString());

				SQLQuery query = session.createSQLQuery(sql.toString());
				List<Object[]> users = (List<Object[]>) query.list();
				for (Object[] user : users) {

					UserMasterVO optionVO = new UserMasterVO(String.valueOf(user[0]), String.valueOf(user[1]),
							String.valueOf(user[2]), String.valueOf(user[3]), String.valueOf(user[4]),
							String.valueOf(user[5]), String.valueOf(user[6]), String.valueOf(user[7]),
							String.valueOf(user[8]), String.valueOf(user[9]), String.valueOf(user[10]),
							String.valueOf(user[11]), String.valueOf(user[12]));
					userMasterList.add(optionVO);

				}

			}

		} catch (Exception e) {
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");

		} finally {
			if (session.isOpen())
				session.close();
		}

		log.fatal("userMasterList " + userMasterList.size());
		return userMasterList;

	}

	public boolean updateUserMaster(UserCreateModel userModel, String event) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;
		UserCreateModel model = new UserCreateModel();
		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(UserCreateModel.class)
						.add(Restrictions.eq("emp_no", userModel.getEmp_no()));

				model = (UserCreateModel) criteria.uniqueResult();

				if (event.equalsIgnoreCase("UPDATE")) {
					model.setUpdated_by(userModel.getUpdated_by());

					model.setIsactive(userModel.getIsactive());
					model.setUser_name(userModel.getUser_name());
					model.setEmp_name(userModel.getEmp_name());
					model.setUser_mail(userModel.getUser_mail());
					// userModel.setUser_password(helper.passwordEncoder(user_password));
					model.setUser_contact_no(userModel.getUser_contact_no());
					model.setUser_address(userModel.getUser_address());
					model.setOffice_location(userModel.getOffice_location());
					model.setUser_type(userModel.getUser_type());
					model.setUpdated_by(userModel.getUpdated_by());
					model.setUpdated_datetime(userModel.getUpdated_datetime());

				} else if (event.equalsIgnoreCase("DELETE")) {
					model.setUpdated_by(userModel.getUpdated_by());
					model.setUpdated_datetime(userModel.getUpdated_datetime());
					model.setIsactive(false);

				}

				session.update(model);
				session.getTransaction().commit();
				returnValue = true;
			} else
				returnValue = false;

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");
		} finally {
			if (session.isOpen())
				session.close();
		}
		return returnValue;

	}

	public boolean insertPromoterMaster(PromoterMasterModel promoterModel) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;
		try {
			if (session != null) {
				session.beginTransaction();
				session.save(promoterModel);
				session.getTransaction().commit();
				returnValue = true;
			} else
				returnValue = false;

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");
		} finally {
			if (session.isOpen())
				session.close();
		}
		return returnValue;

	}

	public List<PromoterMasterModel> searchPromoterMaster(List<PromoterMasterModel> promoterList) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(PromoterMasterModel.class)
						.add(Restrictions.eq("isactive", true));
				criteria.addOrder(Order.asc("promoter_master_id"));
				promoterList = criteria.list();

				log.fatal("Office List Size " + promoterList.size());

			}

		} catch (Exception e) {

			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return promoterList;

	}

	public boolean updatePromoterMaster(PromoterMasterModel promoterModel, String event) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;
		PromoterMasterModel model = new PromoterMasterModel();
		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(PromoterMasterModel.class)
						.add(Restrictions.eq("promoter_master_id", promoterModel.getPromoter_master_id()));

				model = (PromoterMasterModel) criteria.uniqueResult();

				if (event.equalsIgnoreCase("UPDATE")) {
					model.setUpdated_by(promoterModel.getUpdated_by());

					model.setIsactive(promoterModel.getIsactive());
					model.setPromoter_name(promoterModel.getPromoter_name());
					model.setPromoter_short_name(promoterModel.getPromoter_short_name());
					model.setOrg_reference_number(promoterModel.getOrg_reference_number());
					model.setPrefix(promoterModel.getPrefix());
					model.setPromoter_mail(promoterModel.getPromoter_mail());
					model.setPromoter_contact_no(promoterModel.getPromoter_contact_no());
					model.setPromoter_mobile_no(promoterModel.getPromoter_mobile_no());
					model.setUpdated_by(promoterModel.getUpdated_by());
					model.setUpdated_datetime(promoterModel.getUpdated_datetime());

				} else if (event.equalsIgnoreCase("DELETE")) {
					model.setUpdated_by(promoterModel.getUpdated_by());
					model.setUpdated_datetime(promoterModel.getUpdated_datetime());
					model.setIsactive(false);

				}

				session.update(model);
				session.getTransaction().commit();
				returnValue = true;
			} else
				returnValue = false;

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");
		} finally {
			if (session.isOpen())
				session.close();
		}
		return returnValue;

	}

	public boolean insertRegulationMaster(RegulationMasterModel regulationModel) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;
		try {
			if (session != null) {
				session.beginTransaction();
				session.save(regulationModel);
				session.getTransaction().commit();
				returnValue = true;
			} else
				returnValue = false;

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");
		} finally {
			if (session.isOpen())
				session.close();
		}
		return returnValue;

	}

	public List<RegulationMasterModel> searchRegulationMaster(List<RegulationMasterModel> regulationList) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(RegulationMasterModel.class)
						.add(Restrictions.eq("isactive", true));
				criteria.addOrder(Order.asc("regulation_master_id"));
				regulationList = criteria.list();

				log.fatal("Regulation List Size " + regulationList.size());

			}

		} catch (Exception e) {

			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return regulationList;

	}

	public boolean updateRegulationMaster(RegulationMasterModel regulationModel, String event) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;
		RegulationMasterModel model = new RegulationMasterModel();
		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(RegulationMasterModel.class)
						.add(Restrictions.eq("regulation_master_id", regulationModel.getRegulation_master_id()));

				model = (RegulationMasterModel) criteria.uniqueResult();

				if (event.equalsIgnoreCase("UPDATE")) {
					model.setUpdated_by(regulationModel.getUpdated_by());

					model.setIsactive(regulationModel.getIsactive());

					model.setRegulation_id(regulationModel.getRegulation_id());
					model.setRegulation_code(regulationModel.getRegulation_code());
					model.setDescription(regulationModel.getDescription());

					model.setUpdated_by(regulationModel.getUpdated_by());
					model.setUpdated_datetime(regulationModel.getUpdated_datetime());

				} else if (event.equalsIgnoreCase("DELETE")) {
					model.setUpdated_by(regulationModel.getUpdated_by());
					model.setUpdated_datetime(regulationModel.getUpdated_datetime());
					model.setIsactive(false);

				}

				session.update(model);
				session.getTransaction().commit();
				returnValue = true;
			} else
				returnValue = false;

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");
		} finally {
			if (session.isOpen())
				session.close();
		}
		return returnValue;

	}

	public boolean insertHolidayMaster(HolidayMasterModel holidayModel) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;
		try {
			if (session != null) {
				session.beginTransaction();
				session.save(holidayModel);
				session.getTransaction().commit();
				returnValue = true;
			} else
				returnValue = false;

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");
		} finally {
			if (session.isOpen())
				session.close();
		}
		return returnValue;

	}

	public List<HolidayMasterModel> searchHolidayMaster(List<HolidayMasterModel> holidayList) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(HolidayMasterModel.class)
						.add(Restrictions.isNotNull("isactive"));
				criteria.addOrder(Order.desc("isactive"));
				criteria.addOrder(Order.asc("holidaye_master_id"));
				holidayList = criteria.list();

				log.fatal("Holiday List Size " + holidayList.size());

			}

		} catch (Exception e) {

			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return holidayList;

	}

	public boolean updateHolidayMaster(HolidayMasterModel holidayModel, String event) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;
		HolidayMasterModel model = new HolidayMasterModel();
		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(HolidayMasterModel.class)
						.add(Restrictions.eq("holidaye_master_id", holidayModel.getHolidaye_master_id()));

				model = (HolidayMasterModel) criteria.uniqueResult();

				if (event.equalsIgnoreCase("UPDATE")) {
					model.setUpdated_by(holidayModel.getUpdated_by());

					model.setIsactive(holidayModel.getIsactive());

					model.setHolidate_date(holidayModel.getHolidate_date());
					model.setDescription(holidayModel.getDescription());

					model.setUpdated_by(holidayModel.getUpdated_by());
					model.setUpdated_datetime(holidayModel.getUpdated_datetime());

				} else if (event.equalsIgnoreCase("DELETE")) {
					model.setUpdated_by(holidayModel.getUpdated_by());
					model.setUpdated_datetime(holidayModel.getUpdated_datetime());
					model.setIsactive(false);

				}

				session.update(model);
				session.getTransaction().commit();
				returnValue = true;
			} else
				returnValue = false;

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");
		} finally {
			if (session.isOpen())
				session.close();
		}
		return returnValue;

	}

	public boolean insertPermitFeeMaster(PermitFeeMasterModel permitFeeModel) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;
		try {
			if (session != null) {
				session.beginTransaction();
				session.save(permitFeeModel);
				session.getTransaction().commit();
				returnValue = true;
			} else
				returnValue = false;

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");
		} finally {
			if (session.isOpen())
				session.close();
		}
		return returnValue;

	}

	public List<PermitFeeMasterModel> searchPermitFeeMaster(List<PermitFeeMasterModel> permitFeeList) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			if (session != null) {
				session.beginTransaction();

				permitFeeList = session.createQuery(
						" FROM PermitFeeMasterModel order by  (case when isactive is null then 'ZZ' when isactive =true then 'XX' when isactive =false then 'YY' end), highway_authority_name   ")
						.list();
				/*
				 * Criteria criteria = session.createCriteria(PermitFeeMasterModel.class);
				 * criteria.addOrder(Order.asc("isactive"));
				 * criteria.addOrder(Order.asc("permit_fee_master_id")); permitFeeList =
				 * criteria.list();
				 */

				log.fatal("Permit Fee List Size " + permitFeeList.size());

			}

		} catch (Exception e) {

			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return permitFeeList;

	}

	public boolean updatePermitFeeMaster(PermitFeeMasterModel permitFeeModel, String event) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;
		PermitFeeMasterModel model = new PermitFeeMasterModel();
		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(PermitFeeMasterModel.class)
						.add(Restrictions.eq("permit_fee_master_id", permitFeeModel.getPermit_fee_master_id()));

				model = (PermitFeeMasterModel) criteria.uniqueResult();

				if (event.equalsIgnoreCase("UPDATE")) {
					model.setUpdated_by(permitFeeModel.getUpdated_by());

					model.setIsactive(permitFeeModel.getIsactive());

					model.setHighway_authority_name(permitFeeModel.getHighway_authority_name());
					model.setApplication_type(permitFeeModel.getApplication_type());
					model.setWork_category(permitFeeModel.getWork_category());
					model.setRoad_category(permitFeeModel.getRoad_category());
					model.setTraffic_sensitivity(permitFeeModel.getTraffic_sensitivity());
					model.setFee_amount(permitFeeModel.getFee_amount());

					model.setUpdated_by(permitFeeModel.getUpdated_by());
					model.setUpdated_datetime(permitFeeModel.getUpdated_datetime());

				} else if (event.equalsIgnoreCase("DELETE")) {
					System.out.println("I am at delete " + permitFeeModel.getPermit_fee_master_id());
					model.setUpdated_by(permitFeeModel.getUpdated_by());
					model.setUpdated_datetime(permitFeeModel.getUpdated_datetime());
					model.setIsactive(false);

				}

				session.update(model);
				session.getTransaction().commit();
				returnValue = true;
			} else
				returnValue = false;

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");
		} finally {
			if (session.isOpen())
				session.close();
		}
		return returnValue;

	}

	public List<CommonOptionsVO> promoterNameList(int office_location, int yearMonth) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<CommonOptionsVO> promoterList = new ArrayList();
		try {
			if (session != null) {
				session.beginTransaction();

				StringBuffer sql = new StringBuffer("	select distinct promoter_name from pa_export_validated ")
						.append("	where office_code=" + office_location).append("	and year_month=" + yearMonth)
						.append("	union  ").append("	select distinct promoter_name from change_request_validated  ")
						.append("	where office_code=" + office_location).append("	and year_month=" + yearMonth)
						.append("	ORDER BY promoter_name ");
				SQLQuery query = session.createSQLQuery(sql.toString());
				List<String> offeices = (List<String>) query.list();
				for (String office : offeices) {
					CommonOptionsVO optionVO = new CommonOptionsVO(office, office);
					promoterList.add(optionVO);

				}

			}

		} catch (Exception e) {
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");

		} finally {
			if (session.isOpen())
				session.close();
		}
		return promoterList;

	}

	public List<ChargingStatementVO> chargingStatementList(String promoter_name, int office_location, int yearMonth)
			throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<ChargingStatementVO> promoterList = new ArrayList();
		try {
			if (session != null) {
				session.beginTransaction();

				// As on mail dated 30 Mar 2023 the the invoice amount change from
				// round(cast(permit_fee- ((permit_fee*coalesce(discount,0))/100) as numeric),
				// 2)
				// to round(cast(permit_fee- ((permit_fee*coalesce(discount,0))) as numeric), 2)
				// In change Request the change_status Refused and Cancelled ignored

				StringBuffer sql = new StringBuffer(" select  work_reference_number  Works_Reference, ")
						.append("	permit_reference_number Permit_Reference, ")
						.append("	to_char(date_of_permits,'dd/mm/yyyy') date_of_permits, ")
						.append("	application_type Works_Type, ").append("	type_of_work Permit_Type, ")
						.append("	coalesce(coordinator,'Others') Applicable_Charge, ")
						.append("	permit_fee standard_charges , ")
						.append("	 round(cast(((permit_fee*coalesce(discount,0))) as numeric), 2) Adjustment_Charge  , ")
						.append("	round(cast((permit_fee- ((permit_fee*coalesce(discount,0)))) as numeric), 2) Invoice_Amount, ")
						.append("	location work_location, ").append("	street ").append("	from pa_export_validated ")
						.append("	where year_month =  " + yearMonth)
						.append("	and promoter_name = '" + promoter_name + "' ")
						.append("	and office_code =  " + office_location)
						.append(" and coordinator is not null and permit_status  in(select distinct status from charging_statement_status)	union all  ")
						.append("	select change_request_reference_number Works_Reference,  ")
						.append("	permit_reference_number Permit_Reference,  ")
						.append("	to_char(cast(change_submission_date as date),'dd/mm/yyyy') date_of_permits, ")
						.append("	application_type Works_Type, ").append("	type_of_work Permit_Type, ")
						.append("	coalesce(coordinator,'Others') Applicable_Charge, ")
						.append("	permit_fee standard_charges , ")
						.append("	round(cast(((permit_fee*coalesce(discount,0))) as numeric), 2) Adjustment_Charge , ")
						.append("	round(cast(permit_fee- ((permit_fee*coalesce(discount,0))) as numeric), 2) Invoice_Amount, ")
						.append("	'*' work_location , ").append("	'*' street ")
						.append("	from Change_request_validated ").append("	where year_month = " + yearMonth)
						.append("	and promoter_name =   '" + promoter_name + "'")
						.append("	and office_code = " + office_location
								+ "  and coordinator is not null and change_status in (select distinct status from charging_statement_status)  ");

				// log.fatal(sql.toString());

				SQLQuery query = session.createSQLQuery(sql.toString());
				List<Object[]> offeices = (List<Object[]>) query.list();
				for (Object[] office : offeices) {
					ChargingStatementVO optionVO = new ChargingStatementVO();

					optionVO.setWorks_reference(String.valueOf(office[0]));
					optionVO.setPermit_reference(String.valueOf(office[1]));
					optionVO.setDate_of_permits(String.valueOf(office[2]));
					optionVO.setWorks_type(String.valueOf(office[3]));
					optionVO.setPermit_type(String.valueOf(office[4]));
					optionVO.setApplicable_charge(String.valueOf(office[5]));
					optionVO.setStandard_charges(String.valueOf(office[6]));
					optionVO.setAdjustment_charges(String.valueOf(office[7]));
					optionVO.setInvoice_amount(String.valueOf(office[8]));
					optionVO.setWork_location(String.valueOf(office[9]));
					optionVO.setStreet(String.valueOf(office[10]));

					promoterList.add(optionVO);

				}

			}

		} catch (Exception e) {
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");

		} finally {
			if (session.isOpen())
				session.close();
		}
		return promoterList;

	}

	public BigDecimal calculatePermitFee(int office_code, String application_type, String type_of_work,
			String coodinator, int noOfDays, List<PermitFeeMasterModel> feeMasterList) {

		ListIterator<PermitFeeMasterModel> listItr = feeMasterList.listIterator();
		BigDecimal returnValue = new BigDecimal(0);

		while (listItr.hasNext()) {
			PermitFeeMasterModel feeModel = new PermitFeeMasterModel();
			feeModel = (PermitFeeMasterModel) listItr.next();

			if (application_type.equalsIgnoreCase("PAA"))
				type_of_work = "All Major Works";

			else if (application_type.equalsIgnoreCase("Variation"))
				type_of_work = "All works categories";

			if ((feeModel.getHighway_authority_id() == office_code)
					&& (feeModel.getApplication_type().trim().equalsIgnoreCase(application_type))
					&& (feeModel.getRoad_category().trim().equalsIgnoreCase(coodinator))) {
				if ((!type_of_work.startsWith("Major"))
						&& (feeModel.getWork_category().equalsIgnoreCase(type_of_work))) {
					returnValue = feeModel.getFee_amount();
					log.fatal("Fee " + returnValue);
				} else if (type_of_work.startsWith("Major")) {

					if ((noOfDays <= 3) && (feeModel.getWork_category().equalsIgnoreCase("Major works up to 3 days")))
						returnValue = feeModel.getFee_amount();
					else if ((noOfDays >= 4) && (noOfDays < 10)
							&& (feeModel.getWork_category().equalsIgnoreCase("Major 4-10 days")))
						returnValue = feeModel.getFee_amount();
					else if ((noOfDays > 10)
							&& (feeModel.getWork_category().equalsIgnoreCase("Major more than 10 days")))
						returnValue = feeModel.getFee_amount();
					else if (feeModel.getWork_category().equalsIgnoreCase("Major works with TTRO"))
						returnValue = feeModel.getFee_amount();
				}

			}

		}

		return returnValue;
	}

	public BigDecimal calculatePermitFeeForChangeRequest(int office_code, String coodinator,
			List<PermitFeeMasterModel> feeMasterList) {

		ListIterator<PermitFeeMasterModel> listItr = feeMasterList.listIterator();
		BigDecimal returnValue = new BigDecimal(0);

		while (listItr.hasNext()) {
			PermitFeeMasterModel feeModel = new PermitFeeMasterModel();
			feeModel = (PermitFeeMasterModel) listItr.next();

			if ((feeModel.getHighway_authority_id() == office_code)
					&& (feeModel.getApplication_type().equalsIgnoreCase("Variation"))
					&& (feeModel.getRoad_category().trim().equalsIgnoreCase(coodinator))) {
				returnValue = feeModel.getFee_amount();
			}
		}

		return returnValue;
	}

	public String getLocationAndStreetForChangeRequest(String permit_reference_number) {
		String returnString = "*~*";
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(PAExportValidated.class)
						.add(Restrictions.eq("permit_reference_number", permit_reference_number.trim()));
				criteria.addOrder(Order.desc("year_month"));
				criteria.setMaxResults(1);

				PAExportValidated paValidated = (PAExportValidated) criteria.uniqueResult();

				if (paValidated != null) {
					returnString = paValidated.getLocation() + "~" + paValidated.getStreet();
				}

			}

		} catch (Exception e) {
			returnString = "*~*";
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return returnString;

	}

	public boolean saveInvoiceList(List<InvoiceDetail> invoiceDetailList, int office_code, int year_month,
			List<chargingDetail> chargingDetailList) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;

		try {
			if (session != null) {
				session.beginTransaction();

				String hql = "delete from InvoiceDetail where office_code= :office_code and year_month = :year_month ";
				session.createQuery(hql).setInteger("office_code", office_code).setInteger("year_month", year_month)
						.executeUpdate();

				String hqlcharging = "delete from chargingDetail where office_code= :office_code and year_month = :year_month ";

				session.createQuery(hqlcharging).setInteger("office_code", office_code)
						.setInteger("year_month", year_month).executeUpdate();

				ListIterator<InvoiceDetail> listItr = invoiceDetailList.listIterator();
				while (listItr.hasNext()) {
					InvoiceDetail invoiceDetail = new InvoiceDetail();
					invoiceDetail = (InvoiceDetail) listItr.next();
					session.save(invoiceDetail);
				}

				ListIterator<chargingDetail> listItrCharge = chargingDetailList.listIterator();
				while (listItrCharge.hasNext()) {
					chargingDetail chargingDtl = new chargingDetail();
					chargingDtl = (chargingDetail) listItrCharge.next();
					session.save(chargingDtl);
				}

				session.getTransaction().commit();
				returnValue = true;
			}
		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			e.printStackTrace();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return returnValue;
	}

	public boolean checkInvoiceDetails(int yearMonth, int officeCode) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;
		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(InvoiceDetail.class)
						.add(Restrictions.eq("office_code", officeCode)).add(Restrictions.eq("year_month", yearMonth))
						.add(Restrictions.isNotNull("status"));

				if (criteria.list().size() > 0)
					returnValue = false;
				else
					returnValue = true;

			}

		} catch (Exception e) {
			returnValue = false;
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return returnValue;

	}

	public List<CommonOptionsVO> searchPromoterForInvoiceUpdateList(int office_location, int year_month)
			throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<CommonOptionsVO> officeList = new ArrayList();
		try {
			if (session != null) {
				session.beginTransaction();

				StringBuffer sql = new StringBuffer(" select promoter_name from invoice_detail where office_code="
						+ office_location + " and year_month=" + year_month
						+ " and (status is null or status_final is null)  order by promoter_name ");
				SQLQuery query = session.createSQLQuery(sql.toString());
				List<String> offeices = (List<String>) query.list();
				for (String office : offeices) {
					CommonOptionsVO optionVO = new CommonOptionsVO(office, office);
					officeList.add(optionVO);

				}

			}

		} catch (Exception e) {
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");

		} finally {
			if (session.isOpen())
				session.close();
		}
		return officeList;

	}

	public InvoiceDetail getInvoiceDetailToUpdate(int office_code, int year_month, String promoter_name) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		InvoiceDetail invoiceModel = new InvoiceDetail();

		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(InvoiceDetail.class)
						.add(Restrictions.eq("office_code", office_code)).add(Restrictions.eq("year_month", year_month))
						.add(Restrictions.eq("promoter_name", promoter_name)).add(Restrictions.disjunction()
								.add(Restrictions.isNull("status")).add(Restrictions.isNull("status_final")));

				invoiceModel = (InvoiceDetail) criteria.uniqueResult();

			}

		} catch (Exception e) {

			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return invoiceModel;

	}

	public boolean updateInvoiceDetail(InvoiceDetail invoiceDetail, String event) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;
		InvoiceDetail model = new InvoiceDetail();
		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(InvoiceDetail.class)
						.add(Restrictions.eq("invoice_detail_id", invoiceDetail.getInvoice_detail_id()));

				model = (InvoiceDetail) criteria.uniqueResult();

				if (event.equalsIgnoreCase("First")) {

					model.setStatement_issued(invoiceDetail.getStatement_issued());
					model.setStatement_issued_date(invoiceDetail.getStatement_issued_date());
					model.setPromoter_response(invoiceDetail.getPromoter_response());
					model.setChallenged_amount(invoiceDetail.getChallenged_amount());
					model.setInvoiced_amount(invoiceDetail.getInvoiced_amount());
					model.setInvoiced_date(invoiceDetail.getInvoiced_date());
					model.setOrder_number(invoiceDetail.getOrder_number());
					model.setInvoice_no(invoiceDetail.getInvoice_no());
					model.setStatus("Yes");

				} else if (event.equalsIgnoreCase("Second")) {

					model.setPaid_amount(invoiceDetail.getPaid_amount());
					model.setPaid_date(invoiceDetail.getPaid_date());
					model.setComments(invoiceDetail.getComments());
					model.setStatus_final("Yes");

				}

				session.update(model);
				session.getTransaction().commit();
				returnValue = true;
			} else
				returnValue = false;

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");
		} finally {
			if (session.isOpen())
				session.close();
		}
		return returnValue;

	}

	public boolean uploadInfringementsList(List<InfringementsModel> infringementModelList, int uploadFileId,
			String empID, List<FPNExport_Infringements_Validated> fpnExportValidatedModelList) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		int rowcount = 0;
		int totalRecords = 0;
		int successfullRecords = 0;
		int errorRecords = 0;
		boolean returnValue = false;

		try {
			if (session != null) {
				session.beginTransaction();

				totalRecords = infringementModelList.size();
				ListIterator<InfringementsModel> listItr = infringementModelList.listIterator();
				while (listItr.hasNext()) {
					InfringementsModel infringementsModel = new InfringementsModel();
					infringementsModel = (InfringementsModel) listItr.next();
					session.save(infringementsModel);
					rowcount += 1;

					if (rowcount % 30 == 0) {
						session.flush();
						session.clear();
					}

				}

				System.out.println("Size " + fpnExportValidatedModelList.size());
				ListIterator<FPNExport_Infringements_Validated> listItrFPN = fpnExportValidatedModelList.listIterator();
				while (listItrFPN.hasNext()) {
					FPNExport_Infringements_Validated fpnModel = new FPNExport_Infringements_Validated();
					fpnModel = (FPNExport_Infringements_Validated) listItrFPN.next();
					session.save(fpnModel);
					rowcount += 1;

					if (rowcount % 30 == 0) {
						session.flush();
						session.clear();
					}

				}

				successfullRecords = totalRecords;
				session.getTransaction().commit();
				returnValue = true;
			} else {
				returnValue = false;
				errorRecords = totalRecords;
				successfullRecords = 0;
			}

		} catch (Exception e) {
			errorRecords = totalRecords;
			successfullRecords = 0;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}
		updateUploadFile(uploadFileId, totalRecords, successfullRecords, errorRecords);

		return returnValue;

	}

	public int searchHolidayDate(Date holidayDate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		int returnValue = 0;

		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(HolidayMasterModel.class)
						.add(Restrictions.eq("isactive", true)).add(Restrictions.eq("holidate_date", holidayDate));
				List<HolidayMasterModel> holidayList = criteria.list();
				returnValue = holidayList.size();
				log.fatal("Holiday List Size " + holidayList.size());

			}

		} catch (Exception e) {

			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return returnValue;

	}

	public String calculateFPN(Date actual_start_date, Date notice_record_date, Date actual_time, Date notice_time,
			boolean weekEndFlag, boolean holidayFlag, String file_type, String work_ref_no) throws ParseException {
		String returnValue = "No FPN.~0~00:00";

		Date start_date = sdf4.parse(sdf1.format(actual_start_date) + " " + sdf2.format(actual_time));

		Date notice_date = sdf4.parse(sdf1.format(notice_record_date) + " " + sdf2.format(notice_time));
		
		System.out.println("notice_date "+notice_date+" start_date "+start_date);

		long time_difference = notice_date.getTime() - start_date.getTime();

		long minutes_difference = (time_difference / (1000 * 60));

		long days_difference = (time_difference / (1000 * 60 * 60 * 24));

		int hour = (int) (minutes_difference / 60);
		int min = (int) (minutes_difference % 60);

		long minutes_difference_display = ((time_difference / (1000 * 60)) - 120);
		
		System.out.println("time_difference "+time_difference +" minutes_difference_display "+minutes_difference_display);
		int hour_display = (int) (minutes_difference_display / 60);
		int min_display = (int) (minutes_difference_display % 60);
		
		System.out.println("hour_display "+hour_display +" min_display "+min_display);

		if (file_type.equalsIgnoreCase("Registration")) {
			
			days_difference = Math.round(time_difference / (1000 * 60 * 60 * 24));
			
			    BigDecimal bd_time= new BigDecimal(time_difference) ;
		        BigDecimal bd_day= new BigDecimal((1000 * 60 * 60 * 24)) ; 
		        BigDecimal result=(bd_time.divide(bd_day, 0, RoundingMode.HALF_UP));
		        days_difference=Long.parseLong(String.valueOf(result));
			
			System.out.println("days_difference "+days_difference);
			int holidayCount = getNoOfHolidays(sdf1.parse(sdf1.format(actual_start_date)),
					sdf1.parse(sdf1.format(notice_record_date)), days_difference);
			System.out.println("holidayCount "+holidayCount);

			if (days_difference > 10) {
				

				if ((days_difference - holidayCount) > 10) {
					returnValue = "Registration application submitted late by "
							+ ((days_difference - holidayCount) - 10) + " days.  Application submitted on "
							+ sdf3.format(notice_record_date) + " at " + sdf2.format(notice_time)
							+ " with actual start date as " + sdf3.format(actual_start_date) + " at "
							+ sdf2.format(actual_time) + "." + "~" + (days_difference - holidayCount) + "~"
							+ (hour + ":" + min);
				} else
					returnValue = "No FPN." + "~" + (days_difference - holidayCount) + "~" + (hour + ":" + min);
			} else
				returnValue = "No FPN." + "~" + (days_difference - holidayCount) + "~" + (hour + ":" + min);

		}

		else if (!file_type.equalsIgnoreCase("Registration")) {

			if (!weekEndFlag && !holidayFlag) {

				if ((sdf2.parse(sdf2.format(actual_time)).after(sdf2.parse("07:59")))
						&& (sdf2.parse(sdf2.format(actual_time)).before(sdf2.parse("16:31")))) {

					
					System.out.println("11111");
					int holidayCount = getNoOfHolidays(sdf1.parse(sdf1.format(actual_start_date)),
							sdf1.parse(sdf1.format(notice_record_date)), days_difference);
					
					if(holidayCount>0) {
						hour=hour-(holidayCount*24);
						minutes_difference=minutes_difference-(holidayCount*1440);
						if(hour_display>=(holidayCount*24))
						hour_display=hour;
						if(min_display>=(holidayCount*1440))
						min_display=min_display-(holidayCount*1440);
						days_difference=days_difference-holidayCount;
					}
					
					System.out.println("hour "+hour+" minutes_difference "+minutes_difference+" hour_display "+hour_display+" min_display "+min_display);
					
					if (hour > 24) {
						returnValue = file_type + " application submitted late by " + days_difference
								+ " day/s.  Application submitted on " + sdf3.format(notice_record_date) + " at "
								+ sdf2.format(notice_time) + " with actual start date as "
								+ sdf3.format(actual_start_date) + " at " + sdf2.format(actual_time) + "." + "~"
								+ days_difference + "~" + (hour + ":" + min);

					}

					else if (minutes_difference > 120 && hour <= 24 & hour > 0) {

						returnValue = file_type + " application submitted late by " + hour_display + " Hour/s and "
								+ min_display + " minute/s.  Application submitted on "
								+ sdf3.format(notice_record_date) + " at " + sdf2.format(notice_time)
								+ " with actual start date as " + sdf3.format(actual_start_date) + " at "
								+ sdf2.format(actual_time) + "." + "~" + days_difference + "~" + (hour_display + ":" + min_display);

					}
					
					else if (minutes_difference > 0 && hour == 0) {

						if(hour_display>=0 && min_display>=0) {
						returnValue = file_type + " application submitted late by " + hour_display + " Hour/s and "
								+ min_display + " minute/s.  Application submitted on "
								+ sdf3.format(notice_record_date) + " at " + sdf2.format(notice_time)
								+ " with actual start date as " + sdf3.format(actual_start_date) + " at "
								+ sdf2.format(actual_time) + "." + "~" + days_difference + "~" + (hour_display + ":" + min_display);
						}
						else
							returnValue = "No FPN." + "~" + days_difference + "~" + (hour_display + ":" + min_display);
					}

					else
						returnValue = "No FPN." + "~" + days_difference + "~" + (hour_display + ":" + min_display);
				}

				else if (sdf2.parse(sdf2.format(actual_time)).after(sdf2.parse("16:31"))) {
					
					System.out.println("2222");

					String nextWorkingDate = getNextWorkingDate(sdf1.parse(sdf1.format(actual_start_date)),
							work_ref_no);
					Date next_working_date_time = sdf.parse(nextWorkingDate + " " + "10:00");
					
					if(actual_start_date.compareTo(notice_record_date)==0)
					{
						
						next_working_date_time = sdf.parse(sdf1.format(notice_record_date) + " " + "10:00");
					}

					log.fatal("AT NEXT " + work_ref_no + " - " + notice_date + " - " + next_working_date_time);

					time_difference = notice_date.getTime() - next_working_date_time.getTime();

					int holidayCount = getNoOfHolidays(sdf1.parse(sdf1.format(actual_start_date)),
							sdf1.parse(sdf1.format(notice_record_date)), days_difference);

					System.out.println("holidayCount " + holidayCount);

					minutes_difference = (time_difference / (1000 * 60));
					days_difference = (time_difference / (1000 * 60 * 60 * 24));

					

					hour = (int) (minutes_difference / 60);
					min = (int) (minutes_difference % 60);
					
					
				
					
					if(holidayCount>0) {
						hour=hour-(holidayCount*24);
						minutes_difference=minutes_difference-(holidayCount*1440);
						if(hour_display>=(holidayCount*24))
						hour_display=hour;
						if(min_display>=(holidayCount*1440))
						min_display=min_display-(holidayCount*1440);
						days_difference=days_difference-holidayCount;
					}
                   
					if(minutes_difference_display >0) {
					
					if (hour > 24) {
						returnValue = file_type + " application submitted late by " + days_difference
								+ " day/s.  Application submitted on " + sdf3.format(notice_record_date) + " at "
								+ sdf2.format(notice_time) + " with actual start date as "
								+ sdf3.format(actual_start_date) + " at " + sdf2.format(actual_time) + "." + "~"
								+ days_difference + "~" + (hour + ":" + min);

					}

					else if (hour <= 24 && hour >= 0 && min > 0) {
						returnValue = file_type + " application submitted late by " + hour + " Hour/s and "
								+ min + " minute/s.  Application submitted on "
								+ sdf3.format(notice_record_date) + " at " + sdf2.format(notice_time)
								+ " with actual start date as " + sdf3.format(actual_start_date) + " at "
								+ sdf2.format(actual_time) + "." + "~" + days_difference + "~" + (hour + ":" + min);

					}
					
					else if (minutes_difference > 0 && hour == 0) {

						if(hour_display>=0 && min_display>=0) {
						returnValue = file_type + " application submitted late by " + hour + " Hour/s and "
								+ min + " minute/s.  Application submitted on "
								+ sdf3.format(notice_record_date) + " at " + sdf2.format(notice_time)
								+ " with actual start date as " + sdf3.format(actual_start_date) + " at "
								+ sdf2.format(actual_time) + "." + "~" + days_difference + "~" + (hour + ":" + min);
						}
						else
							returnValue = "No FPN." + "~" + days_difference + "~" + (hour_display + ":" + min_display);
					}

					else
						returnValue = "No FPN." + "~" + days_difference + "~" + (hour + ":" + min);

				}
					
					else
						returnValue = "No FPN." + "~" + days_difference + "~" + (hour + ":" + min);	
					
				}

				else {
					
					System.out.println("333");
					
					
					String nextWorkingDate = getNextWorkingDate(sdf1.parse(sdf1.format(actual_start_date)),
							work_ref_no);
					Date next_working_date_time = sdf.parse(nextWorkingDate + " " + "10:00");
					
					if(actual_start_date.compareTo(notice_record_date)==0)
					{
						
						next_working_date_time = sdf.parse(sdf1.format(notice_record_date) + " " + "10:00");
					}

					log.fatal("AT NEXT " + work_ref_no + " - " + notice_date + " - " + next_working_date_time);

					time_difference = notice_date.getTime() - next_working_date_time.getTime();

					int holidayCount = getNoOfHolidays(sdf1.parse(sdf1.format(actual_start_date)),
							sdf1.parse(sdf1.format(notice_record_date)), days_difference);

					System.out.println("holidayCount " + holidayCount);

					minutes_difference = (time_difference / (1000 * 60));
					days_difference = (time_difference / (1000 * 60 * 60 * 24));

					System.out.println("minutes_difference " + minutes_difference);

					hour = (int) (minutes_difference / 60);
					min = (int) (minutes_difference % 60);
					
					System.out.println("hour " + hour+" min "+min);
				
					
					if(holidayCount>0) {
						hour=hour-(holidayCount*24);
						minutes_difference=minutes_difference-(holidayCount*1440);
						if(hour_display>=(holidayCount*24))
						hour_display=hour;
						if(min_display>=(holidayCount*1440))
						min_display=min_display-(holidayCount*1440);
						days_difference=days_difference-holidayCount;
					}


					if (hour > 24) {
						returnValue = file_type + " application submitted late by " + days_difference
								+ " day/s.  Application submitted on " + sdf3.format(notice_record_date) + " at "
								+ sdf2.format(notice_time) + " with actual start date as "
								+ sdf3.format(actual_start_date) + " at " + sdf2.format(actual_time) + "." + "~"
								+ days_difference + "~" + (hour + ":" + min);

					}

					else if (minutes_difference > 120 && hour <= 24 & hour > 0) {

						returnValue = file_type + " application submitted late by " + hour + " Hour/s and "
								+ min + " minute/s.  Application submitted on "
								+ sdf3.format(notice_record_date) + " at " + sdf2.format(notice_time)
								+ " with actual start date as " + sdf3.format(actual_start_date) + " at "
								+ sdf2.format(actual_time) + "." + "~" + days_difference + "~" + (hour + ":" + min);

					}
					
					else if (minutes_difference > 0 && hour == 0) {
						
						if(hour_display>=0 && min_display>=0) {

						returnValue = file_type + " application submitted late by " + hour + " Hour/s and "
								+ min + " minute/s.  Application submitted on "
								+ sdf3.format(notice_record_date) + " at " + sdf2.format(notice_time)
								+ " with actual start date as " + sdf3.format(actual_start_date) + " at "
								+ sdf2.format(actual_time) + "." + "~" + days_difference + "~" + (hour + ":" + min);

                       }
						else
							returnValue = "No FPN." + "~" + days_difference + "~" + (hour_display + ":" + min_display);
					}

					else
						returnValue = "No FPN." + "~" + days_difference + "~" + (hour + ":" + min);
				}

			}

			if ((weekEndFlag) || (holidayFlag)) {
				
				System.out.println("5555");

				String nextWorkingDate = getNextWorkingDate(sdf1.parse(sdf1.format(actual_start_date)), work_ref_no);
				Date next_working_date_time = sdf4.parse(nextWorkingDate + " " + "10:00");

				log.fatal("AT NEXT " + work_ref_no + " - " + notice_date + " - " + next_working_date_time);

				time_difference = notice_date.getTime() - next_working_date_time.getTime();

				minutes_difference = (time_difference / (1000 * 60));

				days_difference = (time_difference / (1000 * 60 * 60 * 24));

				hour = (int) (minutes_difference / 60);
				min = (int) (minutes_difference % 60);

				minutes_difference_display = ((time_difference / (1000 * 60)) - 120);
				hour_display = (int) (minutes_difference_display / 60);
				min_display = (int) (minutes_difference_display % 60);

				log.fatal("AT NEXT HOLIDAY" + work_ref_no + " - " + hour + " - " + hour_display + " - " + min + " - "
						+ min_display);

				if (hour > 24) {
					returnValue = file_type + " application submitted late by " + days_difference
							+ " day/s.  Application submitted on " + sdf3.format(notice_record_date) + " at "
							+ sdf2.format(notice_time) + " with actual start date as " + sdf3.format(actual_start_date)
							+ " at " + sdf2.format(actual_time) + "." + "~" + days_difference + "~"
							+ (hour + ":" + min);

				}

				else if (hour <= 24 && hour >= 0 && min > 0) {
					returnValue = file_type + " application submitted late by " + hour_display + " Hour/s and "
							+ min_display + " minute/s.  Application submitted on " + sdf3.format(notice_record_date)
							+ " at " + sdf2.format(notice_time) + " with actual start date as "
							+ sdf3.format(actual_start_date) + " at " + sdf2.format(actual_time) + "." + "~"
							+ days_difference + "~" + (hour + ":" + min);

				}

				else
					returnValue = "No FPN." + "~" + days_difference + "~" + (hour + ":" + min);

			}

			log.fatal(work_ref_no + " - " + returnValue);
		}

		return returnValue;
	}

	public String getNextWorkingDate(Date actualDate, String work_ref_no) {
		String returnDate = sdf1.format(new Date());
		log.fatal("at getNextWorkingDate =" + work_ref_no + " - " + actualDate);

		Calendar calendar = Calendar.getInstance();

		try {
			for (int i = 1; i <= 20; i++) {
				calendar.setTime(actualDate);
				calendar.add(Calendar.DAY_OF_YEAR, i);
				calendar.setTime(sdf1.parse(sdf1.format(calendar.getTime())));
				int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
				if ((dayOfWeek != 1 && dayOfWeek != 7)
						&& (searchHolidayDate(sdf1.parse(sdf1.format(calendar.getTime()))) <= 0)) {
					returnDate = sdf1.format(calendar.getTime());
					break;
				}

			}
		} catch (Exception e) {
			log.fatal(helper.writeLogInUpperCase(e));
			e.printStackTrace();
		}

		return returnDate;

	}

	public List<FPNExportInfringementsVO> getFPNInfringementsDetailToUpdate(int office_code, int year_month,
			String file_type) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<FPNExportInfringementsVO> FPNInfringementsList = new ArrayList<FPNExportInfringementsVO>();

		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(FPNExport_Infringements_Validated.class)
						.add(Restrictions.eq("office_code", office_code)).add(Restrictions.eq("year_month", year_month))
						.add(Restrictions.eq("file_type", file_type))
						.add(Restrictions.disjunction().add(Restrictions.isNull("first_update_status"))
								.add(Restrictions.isNull("second_update_status")));

				FPNInfringementsList = criteria.list();

			}

		} catch (Exception e) {

			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return FPNInfringementsList;

	}

	public boolean UpdateFPNInfringement(List<FPNExport_Infringements_Validated> alledited) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		boolean returnValue = false;

		try {
			if (session != null) {
				session.beginTransaction();

				ListIterator<FPNExport_Infringements_Validated> listItr = alledited.listIterator();

				while (listItr.hasNext()) {

					FPNExport_Infringements_Validated stageModel = new FPNExport_Infringements_Validated();
					FPNExport_Infringements_Validated updateModel = new FPNExport_Infringements_Validated();

					stageModel = (FPNExport_Infringements_Validated) listItr.next();

					if (stageModel.getFirst_update_status().equalsIgnoreCase("Yes")) {
						Criteria criteria = session.createCriteria(FPNExport_Infringements_Validated.class)
								.add(Restrictions.eq("fpn_export_infringements_id",
										stageModel.getFpn_export_infringements_id()));

						log.fatal(" AT HM " + stageModel.getFpn_export_infringements_id() + " "
								+ stageModel.getFpn_number() + " " + stageModel.getIssue_date_time() + " "
								+ stageModel.getAmount_paid() + " " + stageModel.getFirst_update_status());

						updateModel = (FPNExport_Infringements_Validated) criteria.uniqueResult();
						updateModel.setFpn_number(stageModel.getFpn_number());
						updateModel.setIssue_date_time(stageModel.getIssue_date_time());
						updateModel.setAmount_paid(stageModel.getAmount_paid());
						updateModel.setFirst_update_status(stageModel.getFirst_update_status());
						session.update(updateModel);

					}

					else if (stageModel.getSecond_update_status().equalsIgnoreCase("Yes")) {

						Criteria criteria = session.createCriteria(FPNExport_Infringements_Validated.class)
								.add(Restrictions.eq("fpn_export_infringements_id",
										stageModel.getFpn_export_infringements_id()));

						updateModel = (FPNExport_Infringements_Validated) criteria.uniqueResult();

						System.out.println("fpn_export_infringements_id " + stageModel.getFpn_export_infringements_id()
								+ " updateModel.getFpn_number() " + updateModel.getFpn_number());

						if (updateModel.getFpn_number() != null && updateModel.getFpn_number().trim().length() > 0) {

							updateModel.setReceived_date(stageModel.getReceived_date());
							updateModel.setSecond_update_status(stageModel.getSecond_update_status());
							session.update(updateModel);

							Date currentDate = new Date();

							FPNExportValidated insertValidatedModel = new FPNExportValidated();

							insertValidatedModel.setWorks_reference_number(updateModel.getWorks_reference_number());
							insertValidatedModel.setFpn_number(updateModel.getFpn_number());
							insertValidatedModel.setFpn_status("Paid");
							insertValidatedModel.setOffence_code(updateModel.getOffence_code());
							insertValidatedModel.setOffence_date(updateModel.getOffence_date());
							insertValidatedModel.setOffence_details(updateModel.getOffence_details());
							insertValidatedModel.setIssue_date_time(updateModel.getIssue_date_time());
							insertValidatedModel.setStreet_name("INFRINGEMET-DATA");
							insertValidatedModel.setUsrn(new BigDecimal(stageModel.getFpn_export_infringements_id()));
							insertValidatedModel.setWorks_promoter(updateModel.getWorks_promoter());
							insertValidatedModel.setIssuing_authority(updateModel.getIssuing_authority());
							insertValidatedModel.setStatus_changed_date(currentDate);
							insertValidatedModel.setAmount_paid(updateModel.getAmount_paid());
							insertValidatedModel.setComments_txt("INFRINGEMET");
							insertValidatedModel.setOffice_code(updateModel.getOffice_code());
							insertValidatedModel.setYear_month(updateModel.getYear_month());
							insertValidatedModel.setStatus("INFRINGEMET");
							insertValidatedModel.setUpload_date(updateModel.getReceived_date());
							insertValidatedModel.setCreated_by(1);
							insertValidatedModel.setFile_name("INSERTED-INFRINGEMETS");
							insertValidatedModel.setCreated_at(currentDate);
							insertValidatedModel.setFirst_update_status("Yes");
							insertValidatedModel.setSecond_update_status("Yes");

							session.persist(insertValidatedModel);

						}

					}
				}
				session.getTransaction().commit();
				returnValue = true;
			} else {
				returnValue = false;

			}

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		return returnValue;

	}

	public boolean uploadKPI1Provisional(List<KPI1Provisional> kpi1ProvisionalList, int uploadFileId, String empID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		int rowcount = 0;
		int totalRecords = 0;
		int successfullRecords = 0;
		int errorRecords = 0;
		boolean returnValue = false;

		try {
			if (session != null) {
				session.beginTransaction();

				totalRecords = kpi1ProvisionalList.size();
				ListIterator<KPI1Provisional> listItr = kpi1ProvisionalList.listIterator();
				while (listItr.hasNext()) {
					KPI1Provisional kpiProvisionalModel = new KPI1Provisional();
					kpiProvisionalModel = (KPI1Provisional) listItr.next();

					log.fatal(kpiProvisionalModel.getSite_code() + " " + kpiProvisionalModel.getNotice_issue_time()
							+ " " + kpiProvisionalModel.getPermit_date());
					session.save(kpiProvisionalModel);
					rowcount += 1;

					if (rowcount % 30 == 0) {
						session.flush();
						session.clear();
					}

				}

				successfullRecords = totalRecords;
				session.getTransaction().commit();
				returnValue = true;
			} else {
				returnValue = false;
				errorRecords = totalRecords;
				successfullRecords = 0;
			}

		} catch (Exception e) {
			returnValue = false;
			errorRecords = totalRecords;
			successfullRecords = 0;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}
		updateUploadFile(uploadFileId, totalRecords, successfullRecords, errorRecords);

		return returnValue;

	}

	public boolean uploadKPI2Provisional(List<KPI2Provisional> kpi2ProvisionalList, int uploadFileId, String empID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		int rowcount = 0;
		int totalRecords = 0;
		int successfullRecords = 0;
		int errorRecords = 0;
		boolean returnValue = false;

		try {
			if (session != null) {
				session.beginTransaction();

				totalRecords = kpi2ProvisionalList.size();
				ListIterator<KPI2Provisional> listItr = kpi2ProvisionalList.listIterator();
				while (listItr.hasNext()) {
					KPI2Provisional kpiProvisionalModel = new KPI2Provisional();
					kpiProvisionalModel = (KPI2Provisional) listItr.next();

					log.fatal(kpiProvisionalModel.getSite_code() + " " + kpiProvisionalModel.getNotice_issue_time()
							+ " " + kpiProvisionalModel.getPermit_date());
					session.save(kpiProvisionalModel);
					rowcount += 1;

					if (rowcount % 30 == 0) {
						session.flush();
						session.clear();
					}

				}

				successfullRecords = totalRecords;
				session.getTransaction().commit();
				returnValue = true;
			} else {
				returnValue = false;
				errorRecords = totalRecords;
				successfullRecords = 0;
			}

		} catch (Exception e) {
			returnValue = false;
			errorRecords = totalRecords;
			successfullRecords = 0;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}
		updateUploadFile(uploadFileId, totalRecords, successfullRecords, errorRecords);

		return returnValue;

	}

	public boolean uploadKPI3Provisional(List<KPI3Provisional> kpi3ProvisionalList, int uploadFileId, String empID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		int rowcount = 0;
		int totalRecords = 0;
		int successfullRecords = 0;
		int errorRecords = 0;
		boolean returnValue = false;

		try {
			if (session != null) {
				session.beginTransaction();

				totalRecords = kpi3ProvisionalList.size();
				ListIterator<KPI3Provisional> listItr = kpi3ProvisionalList.listIterator();
				while (listItr.hasNext()) {
					KPI3Provisional kpiProvisionalModel = new KPI3Provisional();
					kpiProvisionalModel = (KPI3Provisional) listItr.next();

					log.fatal(kpiProvisionalModel.getSite_code() + " " + kpiProvisionalModel.getNotice_issue_time()
							+ " " + kpiProvisionalModel.getPermit_date());
					session.save(kpiProvisionalModel);
					rowcount += 1;

					if (rowcount % 30 == 0) {
						session.flush();
						session.clear();
					}

				}

				successfullRecords = totalRecords;
				session.getTransaction().commit();
				returnValue = true;
			} else {
				returnValue = false;
				errorRecords = totalRecords;
				successfullRecords = 0;
			}

		} catch (Exception e) {
			returnValue = false;
			errorRecords = totalRecords;
			successfullRecords = 0;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		}

		/*
		 * }catch (JDBCException jdbce) {
		 * jdbce.getSQLException().getNextException().printStackTrace(); }
		 */

		finally {
			if (session.isOpen())
				session.close();
		}
		updateUploadFile(uploadFileId, totalRecords, successfullRecords, errorRecords);

		return returnValue;

	}

	public boolean uploadKPI4Provisional(List<KPI4Provisional> kpi4ProvisionalList, int uploadFileId, String empID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		int rowcount = 0;
		int totalRecords = 0;
		int successfullRecords = 0;
		int errorRecords = 0;
		boolean returnValue = false;

		try {
			if (session != null) {
				session.beginTransaction();

				totalRecords = kpi4ProvisionalList.size();
				ListIterator<KPI4Provisional> listItr = kpi4ProvisionalList.listIterator();
				while (listItr.hasNext()) {
					KPI4Provisional kpiProvisionalModel = new KPI4Provisional();
					kpiProvisionalModel = (KPI4Provisional) listItr.next();

					log.fatal(kpiProvisionalModel.getSite_code() + " " + kpiProvisionalModel.getNotice_issue_time()
							+ " " + kpiProvisionalModel.getPermit_date());
					session.save(kpiProvisionalModel);
					rowcount += 1;

					if (rowcount % 30 == 0) {
						session.flush();
						session.clear();
					}

				}

				successfullRecords = totalRecords;
				session.getTransaction().commit();
				returnValue = true;
			} else {
				returnValue = false;
				errorRecords = totalRecords;
				successfullRecords = 0;
			}

		} catch (Exception e) {
			returnValue = false;
			errorRecords = totalRecords;
			successfullRecords = 0;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));

		}

		/*
		 * }catch (JDBCException jdbce) {
		 * jdbce.getSQLException().getNextException().printStackTrace(); }
		 */

		finally {
			if (session.isOpen())
				session.close();
		}
		updateUploadFile(uploadFileId, totalRecords, successfullRecords, errorRecords);

		return returnValue;

	}

	public List<CommonOptionsVO> promoterNameListForFPN(int officeCode,int yearMonth) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<CommonOptionsVO> promoterList = new ArrayList();
		try {
			if (session != null) {
				session.beginTransaction();

				StringBuffer sql = new StringBuffer(
						"	select distinct promoter_name  from infringements_data where year_month =" + yearMonth
								+ " and office_code="+officeCode+" and status is null order by promoter_name  ");
				
				SQLQuery query = session.createSQLQuery(sql.toString());
				List<String> offeices = (List<String>) query.list();
				for (String office : offeices) {
					CommonOptionsVO optionVO = new CommonOptionsVO(office, office);
					promoterList.add(optionVO);

				}

			}

		} catch (Exception e) {
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");

		} finally {
			if (session.isOpen())
				session.close();
		}
		return promoterList;

	}

	public List<FPNStatementVO> FPNStatementList(String promoter_name, int yearMonth) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<FPNStatementVO> promoterList = new ArrayList();
		try {
			if (session != null) {
				session.beginTransaction();

				StringBuffer sql = new StringBuffer(" select promoter_name , work_reference_number, ")
						.append("	cast(stop_notice_recorded_date as varchar) notice_date, ")
						.append("	cast(stop_notice_recorded_time as varchar)notice_time, ")
						.append("	cast(actual_completed_date as varchar) actual_date, ")
						.append("	cast(actual_completed_time as varchar) actual_time,fpn_comments ")
						.append("	from infringements_data ").append("	where year_month =  " + yearMonth)
						.append("	and promoter_name = '" + promoter_name + "' ")
						.append("	and status  is null order by work_reference_number");

				log.fatal(sql.toString());

				SQLQuery query = session.createSQLQuery(sql.toString());
				List<Object[]> offeices = (List<Object[]>) query.list();
				for (Object[] office : offeices) {
					FPNStatementVO optionVO = new FPNStatementVO();
					optionVO.setPromoter_name(String.valueOf(office[0]));
					optionVO.setWork_reference_number(String.valueOf(office[1]));
					optionVO.setNotice_date(String.valueOf(office[2]));
					optionVO.setNotice_time(String.valueOf(office[3]));
					optionVO.setActual_date(String.valueOf(office[4]));
					optionVO.setActual_time(String.valueOf(office[5]));
					optionVO.setFpn_comments(String.valueOf(office[6]));
					promoterList.add(optionVO);

				}

			}

		} catch (Exception e) {
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");

		} finally {
			if (session.isOpen())
				session.close();
		}
		return promoterList;

	}

	public boolean checkExistingHolidayDate(Date holidayDate) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;
		List<HolidayMasterModel> model = new ArrayList<HolidayMasterModel>();
		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(HolidayMasterModel.class)
						.add(Restrictions.eq("holidate_date", holidayDate));

				model = criteria.list();

				if (model.size() > 0)
					returnValue = false;
				else
					returnValue = true;
			}

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");
		} finally {
			if (session.isOpen())
				session.close();
		}
		return returnValue;

	}

	public boolean checkExistingHolidayDateForUpdate(Date holidayDate, int holyday_master_id) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;
		List<HolidayMasterModel> model = new ArrayList<HolidayMasterModel>();
		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(HolidayMasterModel.class)
						.add(Restrictions.eq("holidate_date", holidayDate))
						.add(Restrictions.ne("holidaye_master_id", holyday_master_id));

				model = criteria.list();

				if (model.size() > 0)
					returnValue = false;
				else
					returnValue = true;
			}

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");
		} finally {
			if (session.isOpen())
				session.close();
		}
		return returnValue;

	}

	public boolean checkExistingOffice(int highway_authority_id) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;
		List<HolidayMasterModel> model = new ArrayList<HolidayMasterModel>();
		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(OfficeCreateModel.class)
						.add(Restrictions.eq("office_master_id", highway_authority_id))
						.add(Restrictions.eq("isactive", true));

				model = criteria.list();

				if (model.size() > 0)
					returnValue = false;
				else
					returnValue = true;
			}

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");
		} finally {
			if (session.isOpen())
				session.close();
		}
		return returnValue;

	}

	public String updateChangePassword(String empID, String oldPassword, String newPassword) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		String message = 0 + "Database Operation Failed.Contact IT.";
		UserCreateModel userModel = new UserCreateModel();

		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(UserCreateModel.class)
						.add(Restrictions.eq("emp_no", Integer.parseInt(empID)));

				userModel = (UserCreateModel) criteria.uniqueResult();

				if (!userModel.getUser_password().equalsIgnoreCase(oldPassword))
					message = 0 + "Wrong Old Password";
				else {
					userModel.setUser_password(newPassword);
					session.update(userModel);
					session.getTransaction().commit();
					message = 1 + "Data Saved Successfully";
				}

			} else
				message = 0 + "Database Operation Failed.Contact IT.";

		} catch (Exception e) {
			session.getTransaction().rollback();
			message = 0 + "Database Operation Failed.Contact IT.";
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");

		} finally {
			if (session.isOpen())
				session.close();
		}
		return message;

	}

	public boolean checkExistingUser(String user_name) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;
		List<UserCreateModel> model = new ArrayList<UserCreateModel>();
		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(UserCreateModel.class)
						.add(Restrictions.eq("user_name", user_name));

				model = criteria.list();

				if (model.size() > 0)
					returnValue = false;
				else
					returnValue = true;
			}

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");
		} finally {
			if (session.isOpen())
				session.close();
		}
		return returnValue;

	}

	public List<CommonOptionsVO> searchOfficeListForUser() throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<CommonOptionsVO> officeList = new ArrayList();
		try {
			if (session != null) {
				session.beginTransaction();

				StringBuffer sql = new StringBuffer(
						" select office_master_id,office_desc from office_master where isactive=true and office_type in('Highway Authority','Admin') ORDER BY office_desc ");
				SQLQuery query = session.createSQLQuery(sql.toString());
				List<Object[]> offeices = (List<Object[]>) query.list();
				for (Object[] office : offeices) {
					CommonOptionsVO optionVO = new CommonOptionsVO(String.valueOf(office[0]), (String) office[1]);
					officeList.add(optionVO);

				}

			}

		} catch (Exception e) {
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");

		} finally {
			if (session.isOpen())
				session.close();
		}
		return officeList;

	}

	public boolean checkExistingOffice(String office_desc) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;
		List<OfficeCreateModel> model = new ArrayList<OfficeCreateModel>();
		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(OfficeCreateModel.class)
						.add(Restrictions.eq("office_desc", office_desc));

				model = criteria.list();

				if (model.size() > 0)
					returnValue = false;
				else
					returnValue = true;
			}

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");
		} finally {
			if (session.isOpen())
				session.close();
		}
		return returnValue;

	}

	public boolean checkExistingOfficeForUpdate(String office_desc, int office_master_id) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;
		List<OfficeCreateModel> model = new ArrayList<OfficeCreateModel>();
		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(OfficeCreateModel.class)
						.add(Restrictions.eq("office_desc", office_desc))
						.add(Restrictions.ne("office_master_id", office_master_id));

				model = criteria.list();

				if (model.size() > 0)
					returnValue = false;
				else
					returnValue = true;
			}

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");
		} finally {
			if (session.isOpen())
				session.close();
		}
		return returnValue;

	}

	public List<CommonOptionsVO> searchOfficeListForCharge() throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<CommonOptionsVO> officeList = new ArrayList();
		try {
			if (session != null) {
				session.beginTransaction();

				StringBuffer sql = new StringBuffer(
						" select office_master_id,upper(office_desc) from office_master where isactive=true and office_type in('Highway Authority','Admin') ORDER BY office_desc ");
				SQLQuery query = session.createSQLQuery(sql.toString());
				List<Object[]> offeices = (List<Object[]>) query.list();
				for (Object[] office : offeices) {
					CommonOptionsVO optionVO = new CommonOptionsVO(String.valueOf(office[0]), (String) office[1]);
					officeList.add(optionVO);

				}

			}

		} catch (Exception e) {
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");

		} finally {
			if (session.isOpen())
				session.close();
		}
		return officeList;

	}

	public boolean checkExistingPermitFee(int highway_id, String application_type, String work_category,
			String road_category, String traffic_sensitivity) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;
		List<PermitFeeMasterModel> model = new ArrayList<PermitFeeMasterModel>();
		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(PermitFeeMasterModel.class)
						.add(Restrictions.eq("highway_authority_id", highway_id))
						.add(Restrictions.eq("application_type", application_type))
						.add(Restrictions.eq("work_category", work_category))
						.add(Restrictions.eq("road_category", road_category))
						.add(Restrictions.eq("traffic_sensitivity", traffic_sensitivity));

				model = criteria.list();

				if (model.size() > 0)
					returnValue = false;
				else
					returnValue = true;
			}

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");
		} finally {
			if (session.isOpen())
				session.close();
		}
		return returnValue;

	}

	public boolean checkExistingPermitFeeForEdit(int permit_master_id, int highway_id, String application_type,
			String work_category, String road_category, String traffic_sensitivity) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;
		List<PermitFeeMasterModel> model = new ArrayList<PermitFeeMasterModel>();
		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(PermitFeeMasterModel.class)
						.add(Restrictions.ne("permit_fee_master_id", permit_master_id))
						.add(Restrictions.eq("highway_authority_id", highway_id))
						.add(Restrictions.eq("application_type", application_type))
						.add(Restrictions.eq("work_category", work_category))
						.add(Restrictions.eq("road_category", road_category))
						.add(Restrictions.eq("traffic_sensitivity", traffic_sensitivity));

				model = criteria.list();

				if (model.size() > 0)
					returnValue = false;
				else
					returnValue = true;
			}

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");
		} finally {
			if (session.isOpen())
				session.close();
		}
		return returnValue;

	}

	public boolean checkExistingUserForEdit(int user_id, String user_name) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean returnValue = false;
		List<UserCreateModel> model = new ArrayList<UserCreateModel>();
		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteria = session.createCriteria(UserCreateModel.class)
						.add(Restrictions.ne("emp_no", user_id)).add(Restrictions.eq("user_name", user_name));

				model = criteria.list();

				if (model.size() > 0)
					returnValue = false;
				else
					returnValue = true;
			}

		} catch (Exception e) {
			returnValue = false;
			session.getTransaction().rollback();
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");
		} finally {
			if (session.isOpen())
				session.close();
		}
		return returnValue;

	}

	public List<CommonOptionsVO> searchWorkcategory(String applicationType) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<CommonOptionsVO> officeList = new ArrayList();
		try {
			if (session != null) {
				session.beginTransaction();

				StringBuffer sql = new StringBuffer(
						" select aw.work_category_value ,aw.work_category_name  from application_workcategory aw where aw.application_type ='"
								+ applicationType + "'  order by aw.work_category_name  ");
				SQLQuery query = session.createSQLQuery(sql.toString());
				List<Object[]> offeices = (List<Object[]>) query.list();
				for (Object[] office : offeices) {
					CommonOptionsVO optionVO = new CommonOptionsVO(String.valueOf(office[0]), (String) office[1]);
					officeList.add(optionVO);

				}

			}

		} catch (Exception e) {
			log.fatal(helper.writeLogInUpperCase(e));
			throw new Exception("System Error Occured");

		} finally {
			if (session.isOpen())
				session.close();
		}
		return officeList;

	}

	public ChargingStatementVO getPAA(int office_code, String work_ref_no, String coordinator) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		ChargingStatementVO paaVO = new ChargingStatementVO();
		String standardCharge = "0.00";

		String type_of_work = "All Major Works";
		String sentCoodinator = "";

		if (coordinator.equalsIgnoreCase("CAT 3,4 and Non TS"))
			sentCoodinator = "CAT 3-4";
		else if (coordinator.equalsIgnoreCase("CAT 0,1,2 or TS"))
			sentCoodinator = "CAT 0-2";

		try {
			if (session != null) {
				session.beginTransaction();

				Criteria criteriaCharging = session.createCriteria(chargingDetail.class)
						.add(Restrictions.eq("office_code", office_code))
						.add(Restrictions.eq("works_reference", work_ref_no)).add(Restrictions.eq("permit_type", "PAA"))
						.add(Restrictions.eq("works_type", "Major (PAA)"));

				criteriaCharging.addOrder(Order.asc("date_of_permits"));
				criteriaCharging.setFirstResult(0);
				criteriaCharging.setMaxResults(1);

				chargingDetail chargingDetailModel = (chargingDetail) criteriaCharging.uniqueResult();

				if (chargingDetailModel == null) {

					Criteria criteria = session.createCriteria(PAExportValidated.class)
							.add(Restrictions.eq("office_code", office_code))
							.add(Restrictions.eq("work_reference_number", work_ref_no))
							.add(Restrictions.eq("application_type", "PAA"))
							.add(Restrictions.eq("type_of_work", "Major (PAA)"));

					criteria.addOrder(Order.asc("date_of_permits"));
					criteria.setFirstResult(0);
					criteria.setMaxResults(1);

					PAExportValidated validateModel = (PAExportValidated) criteria.uniqueResult();

					if (validateModel != null && validateModel.getPermit_reference_number() != null) {
						System.out.println("My validateModel is not null " + validateModel.getWork_reference_number());

						criteria = session.createCriteria(PermitFeeMasterModel.class)
								.add(Restrictions.eq("highway_authority_id", office_code))
								.add(Restrictions.eq("road_category", sentCoodinator))
								.add(Restrictions.eq("application_type", "PAA"))
								.add(Restrictions.eq("work_category", type_of_work))
								.add(Restrictions.eq("isactive", true));

						System.out.println(office_code + " " + sentCoodinator + " " + "PAA" + " " + type_of_work);

						PermitFeeMasterModel feeMaster = (PermitFeeMasterModel) criteria.uniqueResult();
						if (feeMaster != null && feeMaster.getFee_amount() != null) {

							System.out.println("My feeMaster is not null " + feeMaster.getFee_amount());
							BigDecimal adjustmentAmount = feeMaster.getFee_amount()
									.multiply(validateModel.getDiscount()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
							BigDecimal invoiceAmount = feeMaster.getFee_amount().subtract(adjustmentAmount).setScale(2,
									BigDecimal.ROUND_HALF_EVEN);

							paaVO.setWorks_reference(work_ref_no);
							paaVO.setPermit_reference(validateModel.getPermit_reference_number());
							paaVO.setDate_of_permits(sdf5.format(validateModel.getDate_of_permits()));
							paaVO.setWorks_type(validateModel.getType_of_work());
							paaVO.setPermit_type(validateModel.getApplication_type());
							paaVO.setApplicable_charge(coordinator);
							paaVO.setStandard_charges(String.valueOf(feeMaster.getFee_amount()));
							paaVO.setAdjustment_charges(String.valueOf(adjustmentAmount));
							paaVO.setInvoice_amount(String.valueOf(invoiceAmount));
							paaVO.setWork_location(validateModel.getLocation());
							paaVO.setStreet(validateModel.getStreet());
						}

					}

				}

			}

		} catch (Exception e) {
			log.fatal(helper.writeLogInUpperCase(e));

		} finally {
			if (session.isOpen())
				session.close();
		}

		log.fatal("PAA- " + work_ref_no + " = " + paaVO == null ? "null"
				: "Not Null" + " Amount " + paaVO.getInvoice_amount());
		return paaVO;
	}

	public int getNoOfHolidays(Date StartDate, Date NoticeDate, long day_difference) {
		int returnValue = 0;
		Calendar cal = Calendar.getInstance();

		try {
			Date addedDate = StartDate;
			for (int i = 0; i < day_difference; i++) {
				if (addedDate.before(NoticeDate)) {
					cal.setTime(addedDate);
					cal.add(Calendar.DAY_OF_MONTH, 1);
					addedDate = sdf1.parse(sdf1.format(cal.getTime()));

					int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
					if (dayOfWeek == 1 || dayOfWeek == 7) {
						returnValue = returnValue + 1;
					} else if (searchHolidayDate(addedDate) > 0) {
						returnValue = returnValue + 1;
					}
				}

			}

		} catch (Exception e) {

			log.fatal(helper.writeLogInUpperCase(e));
		}

		return returnValue;
	}

}