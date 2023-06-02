package Business;

import Control.Menu;
import Model.Nurse;
import Model.Patient;
import MyUses.useFiles;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map.Entry;

/**
 * Lớp quản lí 2 cái danh sách được truyền vô là NurseList + Patient list
 *
 * @author Hp
 */
public class Hospital {

//    public static HashMap<String, Nurse> nurses = new HashMap<>();
//    public static HashMap<String, Patient> patients = new HashMap<>();
    NurseList nList = new NurseList();
    PatientList pList = new PatientList();

    private final String NURSEDATPATH = "\\src\\Files\\nurses.dat";
    private final String PATERNDATPATH = "\\src\\Files\\patients.dat";

    public void createNurse() {
        nList.createNurse();
    }

    public void findNurse() {
        nList.findNurse();
    }

    public void updateNurse() {
        nList.updateNurse();
    }

    public void deleteNurse() {
        nList.deleteNurse();
    }

    public void addPatient() {
        if (nList.isEmpty()) {
            System.out.println("Can not add patient beacuse don't have nurses in list.");
        } else {
            pList.addPatient(nList);
        }
    }

    public void displayPatients() {
        pList.displayPatients();
    }

    public void sortPatients() {
        pList.sortPatients();
    }

    //8. Save data
    public void saveData() {
        ArrayList<String> data = new ArrayList<>();

        for (Entry<String, Nurse> item : nList.entrySet()) {
            data.add(item.getValue().toString());
        }

        useFiles.writeToFile(NURSEDATPATH, data);
        data.clear();

        for (Entry<String, Patient> item : pList.entrySet()) {
            data.add(item.getValue().toString());
        }
        useFiles.writeToFile(PATERNDATPATH, data);
        System.out.println("Data saved successfully!");
    }

    //9. Load data
    public void loadData() {
        nList.clear();
        pList.clear();

        ArrayList<String> data = new ArrayList<>();
        data.addAll(useFiles.readFromFile(NURSEDATPATH));
        data.addAll(useFiles.readFromFile(PATERNDATPATH));

        for (String item : data) {
            String lineSpl[] = item.trim().split(",");

            if (lineSpl[0].matches("^N\\d{4}$")) {
                nList.put(lineSpl[0],
                        new Nurse(lineSpl[1],
                                Integer.parseInt(lineSpl[2]),
                                lineSpl[3], lineSpl[4], lineSpl[5],
                                lineSpl[0], lineSpl[6], lineSpl[7],
                                Double.parseDouble(lineSpl[8])));

            } else if (lineSpl[0].matches("^P\\d{4}$")) {
                String[] nll = lineSpl[9].split("/");
                NurseList nl = new NurseList();

                for (String n : nll) {
                    nl.put(n, nList.find(n));
                }
                pList.put(lineSpl[0], new Patient(lineSpl[1],
                        Integer.parseInt(lineSpl[2]),
                        lineSpl[3], lineSpl[4], lineSpl[5],
                        lineSpl[0], lineSpl[6], lineSpl[7], lineSpl[8],
                        nl)
                );
            }
        }
        for (String nurseKey : nList.keySet()) {
            for (String patientKey : pList.keySet()) {
                Patient patient = pList.get(patientKey);
                for (String nuListKey : patient.getNl().keySet()) {
                    if (nuListKey.contains(nurseKey)) {
                        nList.get(nurseKey).incNumPatientAssign();
                    }
                }
            }
        }
        System.out.println("Load data successfully!");
    }

    public void quit() {
        if (Menu.getYesOrNo("Do you want to save before quit program?")) {
            saveData();
        }
        System.out.println("Quit program.");
        System.exit(0);
    }

//    //8. Save data
//    public void saveData() {
//        final String nursePath = "/files/nurses.dat" ;
//        ArrayList<String> nurseDta = new ArrayList<>() ;
//        for(Entry<String,Nurse> n : nurses.entrySet()){
//            nurseDta.add(n.getValue().toString()) ;
//        }
//        FileHandle.writeToFile(nursePath, nurseDta);
//        
//        final String patientPath = "/files/patient.dat" ;
//        ArrayList<String> patientDta = new ArrayList<>() ;
//        for(Entry<String,Patient> n : patients.entrySet()){
//            patientDta.add(n.getValue().toString()) ;
//        }
//        FileHandle.writeToFile(patientPath, patientDta);
//    }
//    //9. Load data
//    public void loadData() {
//        final String nursePath = "/Files/nurses.dat" ;
//        ArrayList<String> nurseDta = useFiles.readFromFile(nursePath) ;
//        nurses.clear();
//        for(String line : nurseDta){
//            String[] lineSpl = line.split("\\|");
//            nurses.put(lineSpl[0] , new Nurse()) ;
//        }
//        
//        final String patientPath = "/Files/patients.dat" ;
//        ArrayList<String> patientDta = useFiles.readFromFile(patientPath) ;
//        patients.clear();
//        for(String line : patientDta){
//            String[] lineSpl = line.split("\\|");
//            patients.put(lineSpl[0] , new Patient()) ;
//        }
//    }
}
