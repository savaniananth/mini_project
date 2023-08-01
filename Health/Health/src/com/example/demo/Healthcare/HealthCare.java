package com.example.demo.Healthcare;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class HealthCare {

	public static void main(String[] args) throws SQLException {

		Scanner scan = new Scanner(System.in);

		int choice;

		do {
			System.out.println("Welcome Back!");
			System.out.println("************************");
			System.out.println(
					"1.Add Patient details\n2.Add Health Provider Details\n3.Add Appointment Details\n4.Add Prescription Details\n5.View Patient Details\n6.View Doctor Details\n7.View Patients Prescription\n8.View Appointment Details\n9.Check Follow-up \n10.Exit");
			System.out.println("************************");
			choice = Integer.parseInt(scan.nextLine());

			switch (choice) {

			case 1:
				System.out.print("Enter the patient ID : ");
				int patientId = Integer.parseInt(scan.nextLine());
				System.out.print("Enter the patient Name : ");
				String patientName = scan.nextLine();
				System.out.print("Enter the patient Age : ");
				int patientAge = Integer.parseInt(scan.nextLine());
				System.out.print("Enter the patient Phone Number : ");
				String phoneno = scan.nextLine();
				System.out.print("Enter the patient Medical History : ");
				String medicalHistory = scan.nextLine();

				Patient newPatient = new Patient(patientId, patientName, phoneno, patientAge, medicalHistory);
				PatientDao patientDao=new PatientDao();
				patientDao.saveToDatabase(DB.connect(), patientId, patientName, patientAge, phoneno, medicalHistory);
				System.out.println("=========================");
				System.out.println("Patient Details saved successfully!!!");
				System.out.println("=========================");
				break;

			case 2:
				System.out.print("Enter the doctor ID : ");
				int doctorID = Integer.parseInt(scan.nextLine());
				System.out.print("Enter the doctor Name : ");
				String doctorName = scan.nextLine();
				System.out.print("Enter doctor's speciality : ");
				String speciality = scan.nextLine();
				
				HealthCareProvider hp = new HealthCareProvider(doctorID,doctorName,speciality);
				HealthCareDao.saveToDatabase(DB.connect(), doctorID,doctorName,speciality);
				
				System.out.println("-------------------------");
				System.out.println("Doctor Details saved successfully!!!");
				System.out.println("-------------------------");
				break;
			case 3:
				System.out.print("Enter the Appointment ID : ");
				int appID = Integer.parseInt(scan.nextLine());
				System.out.print("Enter the start_time : ");
				String start_time = scan.nextLine();
				System.out.print("Enter the end_time: ");
				String end_time = scan.nextLine();
				System.out.print("Enter speciality: ");
				String speciality1 = scan.nextLine();
				
				Appointment ap = new Appointment(appID,start_time,end_time,speciality1);
				AppointmentDao.saveToDatabase(DB.connect(), appID,start_time,end_time,speciality1);
				
				System.out.println("-------------------------");
				System.out.println("Appointment Details saved successfully!!!");
				System.out.println("-------------------------");
				break;
			case 4:
				System.out.print("Enter the prescription ID : ");
				int presID = Integer.parseInt(scan.nextLine());
				System.out.print("Enter the cause : ");
				String healthcauses = scan.nextLine();
				System.out.print("Enter doctor's solution : ");
				String solution = scan.nextLine();
				System.out.print("Enter if reappoinment needed? : ");
				String reapp = scan.nextLine();
				System.out.print("Enter patient's name: ");
				String patientname = scan.nextLine();
				
				Prescription p = new Prescription(presID,healthcauses,solution,reapp,patientname);
				PrescriptionDao.saveToDatabase(DB.connect(),presID,healthcauses,solution,reapp,patientname);
				

				System.out.println("-------------------------");
				System.out.println("Prescription Details saved successfully!!!");
				System.out.println("-------------------------");
				break;
			case 5:
                 
				PatientDao ptDao=new PatientDao();
				List<Patient> res = ptDao.getAllPatients();

				if (res.size() != 0) {
					for (int i = 0; i < res.size(); i++) {
						System.out.println("***************************************");
						System.out.println("PATIENT ID              : " + res.get(i).getId());
						System.out.println("PATIENT NAME            : " + res.get(i).getName());
						System.out.println("PATIENT AGE             : " + res.get(i).getAge());
						System.out.println("PATIENT PHONE NUMBER    : " + res.get(i).getPhoneNumber());
						System.out.println("PATIENT MEDICAL HISTORY : " + res.get(i).getMedicalHistory());
						System.out.println("***************************************");
					}
				} else {
					System.out.println("No record found !!");
				}
				break;
			case 6:
				List<HealthCareProvider> lis = HealthCareDao.getAlldoctors();

				if (lis.size() != 0) {
					for (int i = 0; i < lis.size(); i++) {
						System.out.println("***************************************");
						System.out.println("DOCTOR ID              : " + lis.get(i).getId());
						System.out.println("DOCTOR NAME            : " + lis.get(i).getDoctorname());
						System.out.println("DOCTOR SPECIALITY   : " + lis.get(i).getSpecialization());
						System.out.println("***************************************");
					}
				} else {
					System.out.println("No record found !!");
				}
				break;
			case 7:
				PatientDao ptD=new PatientDao();
				List<Patient> l = ptD.getAllPatients();
				List<Prescription> pr = PrescriptionDao.getPresDetails();
                
				System.out.println("Get Your prescription details now!!");
				System.out.println("***************************************");
				System.out.println("PRESCRIPTION:");
				if (l.size() != 0 && pr.size()!=0) {
					for (int i = 0; i < l.size(); i++) {
						for(int j=0;j<pr.size();j++) {
						if(pr.get(j).getPresID()==l.get(i).getId()) {
						     System.out.println("***************************************");
						     System.out.println("PATIENT ID              : " + l.get(i).getId());
						     System.out.println("PATIENT NAME            : " + l.get(i).getName());
						     System.out.println("HEALTHCAUSES           : " + pr.get(j).getHealthcauses());
						     System.out.println("SOLUTIONS    : " + pr.get(j).getSolutions());
						     System.out.println("REAPPOINTMENT : " + pr.get(j).getReappointment());
						     System.out.println("***************************************");
						}
						}
					}
				} else {
					System.out.println("No record found !!");
				}
				break;
			case 8:
				PatientDao ptDO=new PatientDao();
				List<Patient> pa = ptDO.getAllPatients();
				List<Appointment> app = AppointmentDao.getAppointmentDetails();
				List<HealthCareProvider> hcp = HealthCareDao.getAlldoctors();
                
				System.out.println("***************************************");
				System.out.println("APPOINTMENT DETAILS:");
				if (pa.size() != 0 && app.size()!=0) {
					for (int i = 0; i < pa.size(); i++) {
						for(int j=0;j<app.size();j++) {
							for(int k=0;k<hcp.size();k++) {
						if((app.get(j).getAppID()==pa.get(i).getId())) {
						     System.out.println("***************************************");
						     System.out.println("APPOINTMENT ID              : " + app.get(j).getAppID());
						     System.out.println("PATIENT NAME            : " + pa.get(i).getName());
						     System.out.println("DOCTOR NAME             : " + hcp.get(k).getDoctorname());
						     System.out.println("SPECIALIZATION            : " + hcp.get(k).getSpecialization());
						     System.out.println("START TIME : " + app.get(j).getStart_time());
						     System.out.println("END TIME   : " + app.get(j).getEnd_time());
						     System.out.println("***************************************");
						}
						}
						}
					}
				} else {
					System.out.println("No record found !!");
				}
				break;
			case 9:
				PatientDao ptdo=new PatientDao();
				List<Patient> pat = ptdo.getAllPatients();
				List<Appointment> apt = AppointmentDao.getAppointmentDetails();
				List<Prescription> pre = PrescriptionDao.getPresDetails();
				for(int i=0;i<pre.size();i++) {
					for(int j=0;j<pat.size();j++) {
						for(int k=0;k<apt.size();k++) {
						if((pre.get(i).getPatientName().equals(pat.get(j).getName()))) {
						      if(pre.get(i).getReappointment().equals("No")) {
								    ptdo.deleteById(DB.connect(), pat.get(j).getId());
								    PrescriptionDao.deleteById(DB.connect(), pre.get(i).getPresID());
								    System.out.println("Your Time with us is over. Until next time "+pat.get(j).getName()+"!");
								    break;
						      }
					    }
					}
				}
			}
			break;
				
			case 10:
				System.out.println("Thank You using our platform");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid Choice!");
				break;
			}
		} while (choice != 10);

	}
}
