package com.saanchi.hibernate.util;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import com.saanchi.commomUtility.CommonHelper;
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

public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	private static final Logger log = Logger.getLogger(HibernateUtil.class);

	static {
		try {

			Properties prop = new Properties();

			prop.setProperty("hibernate.connection.url",
					"jdbc:postgresql://13.42.106.45:5432/uatsanchidb");
			prop.setProperty("hibernate.connection.username", "uatdbadmin");
			prop.setProperty("hibernate.connection.password", "calcutta#106");

			prop.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
			prop.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
			prop.setProperty("hibernate.current_session_context_class", "thread");
			prop.setProperty("hibernate.jdbc.batch_size", "1000");
			prop.setProperty("show_sql", "true");

			sessionFactory = new AnnotationConfiguration().addAnnotatedClass(UserCreateModel.class)
					.addAnnotatedClass(FileUploadDetail.class).addAnnotatedClass(FPNExportProvisional.class)
					.addAnnotatedClass(FeeExportProvisional.class).addAnnotatedClass(ChangeRequestProvisional.class)
					.addAnnotatedClass(PAExportProvisional.class).addAnnotatedClass(FPNExportStage.class)
					.addAnnotatedClass(FPNExportValidated.class).addAnnotatedClass(PAExportStage.class)
					.addAnnotatedClass(PAExportValidated.class).addAnnotatedClass(ChangeRequestStage.class)
					.addAnnotatedClass(ChangeRequestValidated.class).addAnnotatedClass(FeeExportStage.class)
					.addAnnotatedClass(FeeExportValidate.class).addAnnotatedClass(OfficeCreateModel.class)
					.addAnnotatedClass(UserCreateModel.class).addAnnotatedClass(PromoterMasterModel.class)
					.addAnnotatedClass(PermitFeeMasterModel.class).addAnnotatedClass(RegulationMasterModel.class)
					.addAnnotatedClass(HolidayMasterModel.class).addAnnotatedClass(InvoiceDetail.class)
					.addAnnotatedClass(InfringementsModel.class)
					.addAnnotatedClass(FPNExport_Infringements_Validated.class).addAnnotatedClass(KPI1Provisional.class)
					.addAnnotatedClass(KPI2Provisional.class).addAnnotatedClass(KPI3Provisional.class)
					.addAnnotatedClass(KPI4Provisional.class)
					.addAnnotatedClass(chargingDetail.class).addProperties(prop).buildSessionFactory();
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}