package Business;

import Control.Menu;
import Model.Nurse;
import Model.Patient;
import MyUses.Uses;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Danh sách bệnh nhân
 *
 * @author Hp
 */
public class PatientList extends HashMap<String, Patient> {

    //Add patient
    public void addPatient(NurseList nList) {
        String name;
        int age;
        String gender;
        String address;
        String phone;
        String id;
        String diagnosis;
        String admissionDate;
        String dischargeDate;

        boolean check = true;
        do {
            //Check mã code được yêu cầu nhập vô
            id = Uses.getStringreg("Enter the ID (PXXXX): ", ("^P\\d{4}$"), "Code is not null", "Code is not format (PXXXX)");
            if (this.containsKey(id)) {
                System.out.println("ID already exists. Please enter a unique ID.");
            } else {
                check = false; // nếu chưa có mã code thì sẽ thoát khỏi vòng lặp và được add thêm vào chương trình
            }
        } while (check);

        name = Uses.getStringNonBlank("Enter your name: ", "Name is not blank");
        age = Uses.getInt("Enter your age: ", 0);
        gender = Uses.getStringreg("Enter your gender [Male/Female]: ", "^(Male|Female)$", "Gender is not blank", "Gender is not format!");
        address = Uses.getStringNonBlank("Enter your address: ", "Address is not blank");
        phone = Uses.getStringreg("Enter your phone number: ", "0\\d{9}", "Phone number is not null", "Phone number is not format");
        diagnosis = Uses.getString("Enter diagnosis: ");
        admissionDate = Uses.getDate("Enter admission date (dd/MM/yyyy): ", "dd/MM/yyyy");
        do {
            dischargeDate = Uses.getDate("Enter discharge date (dd/MM/yyyy): ", "dd/MM/yyyy");
            if (Uses.toDate(dischargeDate, "dd/MM/yyyy").compareTo(Uses.toDate(admissionDate, "dd/MM/yyyy")) < 0) {
                System.out.println("Warning: The discharge date must be on the same day or after the admissionDate. Please enter the discharge date again: ");
            }
        } while (Uses.toDate(dischargeDate, "dd/MM/yyyy").compareTo(Uses.toDate(admissionDate, "dd/MM/yyyy")) < 0);

        NurseList nl = new NurseList();
        int count = 0;
        while (true) {
            Nurse nurse = null;
            String nId = Uses.getStringreg("Enter the nurse assigned ID (NXXXX): ", "^N\\d{4}$", "Code is not null", "Code is not format (NXXXX)");
            if ((nurse = nList.find(nId)) != null) {
                if (nurse.getNumPatientAssign() < 2) {
                    nl.put(nId, nurse);
                    nurse.incNumPatientAssign();
                    count++;
                } else {
                    System.out.println("This nurse already assigned 2 patients!");
                }
            } else {
                System.out.println("This nurse does not exist");
            }
            if (count >= 2 && !Menu.getYesOrNo("Add more nurse assigned?")) {
                break;
            }
        }

        //tạo đối tượng patient
        Patient newPatient = new Patient(name, age, gender, address, phone, id, diagnosis, admissionDate, dischargeDate, nl);

        this.put(id, newPatient);
        System.out.println("Patient added successfully.");

    }

    //Hiển thị ds bệnh nhân ra
    public void displayPatients() {
        String startDate;
        String endDate;

        startDate = Uses.getDate("Enter startDate [dd/mm/yyyy]: ", "dd/mm/yyyy");
        endDate = Uses.getDate("Enter endDate [dd/mm/yyyy]: ", "dd/mm/yyyy");

        System.out.println("------------------");
        System.out.println("LIST OF PATIENTS");
        System.out.println("Start date: " + startDate);
        System.out.println("End date: " + endDate);
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("|  No.| Patient Id | Admission Date |     Full name      |  Phone    | Diagnosis |");
        System.out.println("----------------------------------------------------------------------------------");

        int index = 1; //đánh dấu vị trí
        for (Patient patient : this.values()) {
            if (Uses.toDate(patient.getAdmissionDate(), "dd/MM/yyyy").compareTo(Uses.toDate(startDate, "dd/MM/yyyy")) >= 0
                    && Uses.toDate(patient.getAdmissionDate(), "dd/MM/yyyy").compareTo(Uses.toDate(endDate, "dd/MM/yyyy")) <= 0) {
                String str = String.format("|%5d", index);
                System.out.print(str);
                patient.showInfo();
                System.out.println("----------------------------------------------------------------------------------");
                index++;
            }
        }

    }

    //Sort list bệnh nhân theo 2 hướng cùng 1 lúc
    public void sortPatients() {
        String field;
        String pt;

        int choice;

        Menu sField = new Menu("Sorted by (patient's id|patient's name):");
        sField.addOption("Patient's id");
        sField.addOption("Patient's name");
        sField.printMenu();
        choice = sField.getUserChoice();
        field = choice == 1 ? "id" : "name";

        Menu sPT = new Menu("Sort order (ASC|DESC):");
        sPT.addOption("ASC");
        sPT.addOption("DESC");
        sPT.printMenu();
        choice = sPT.getUserChoice();
        pt = choice == 1 ? "ASC" : "DESC";
        
        Comparator<Patient> comparator;
        if (field.equalsIgnoreCase("id")) {
            comparator = Comparator.comparing(Patient::getId);
        } else if (field.equalsIgnoreCase("name")) {
            comparator = Comparator.comparing(Patient::getName);
        } else {
            System.out.println("Invalid sorted field.");
            return;
        }

        if (pt.equalsIgnoreCase("DESC")) {
            comparator = comparator.reversed(); //in ngược lại của ASC
        } else if (!pt.equalsIgnoreCase("ASC")) {
            System.out.println("Invalid sort order.");
            return;
        }

        List<Patient> sortedPatients = new ArrayList<>(this.values());
        sortedPatients.sort(comparator);

        System.out.println("LIST OF PATIENTS");
        System.out.println("Sorted by (patient's name|patient's id): " + field);
        System.out.println("Sort order (ASC|DESC): " + pt);
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("|  No.| Patient Id | Admission Date |     Full name      |  Phone    | Diagnosis |");
        System.out.println("----------------------------------------------------------------------------------");

        int index = 1;
        for (Patient patient : sortedPatients) {
            String str = String.format("|%5d", index);
            System.out.print(str);
            patient.showInfo();
            System.out.println("----------------------------------------------------------------------------------");
            index++;
        }
    }

}
