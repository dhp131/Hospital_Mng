package Model;

import Business.NurseList;
import java.io.Serializable;
import java.util.Map.Entry;

public class Patient extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    String id;
    String diagnosis;
    String admissionDate;
    String dischargeDate;
    NurseList nl;
//  String nurseAssigned; 

    public Patient() {
    }

   public Patient(String name, int age, String gender, String address, String phone, String id, String diagnosis, String admissionDate, String dischargeDate, NurseList nl) {
        super(name, age, gender, address, phone);
        this.id = id;
        this.diagnosis = diagnosis;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.nl = nl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public NurseList getNl() {
        return nl;
    }

    public void setNl(NurseList nl) {
        this.nl = nl;
    }

    //chuyển NurstList --> String --> load
    private String toStringNursesAssigned(){
        String str = "";
        for (Entry<String,Nurse> item : nl.entrySet()) {
            str += String.format("%s/",item.getKey());
        }
        return str.substring(0, str.length() - 1);
    }

    @Override
    public void showInfo() {
        String str = String.format("|%12s|%16s|%-20s|%11s|%11s|", this.id, this.admissionDate, this.name,
                this.phone, this.diagnosis);
        System.out.println(str);
    }

    @Override
    public String toString() {
        String str = String.format("%s,%s,%d,%s,%s,%s,%s,%s,%s,%s", this.id, this.name, this.age,
                this.gender, this.address, this.phone, this.diagnosis,
                this.admissionDate, this.dischargeDate,
                toStringNursesAssigned());
        return str;
    }
}
