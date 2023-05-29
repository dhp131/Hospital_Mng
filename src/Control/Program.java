package Control;

import Business.Hospital;

public class Program {

    public static void main(String[] args) {

        Menu menu = new Menu("\n-------------------HOSPITAL MANAGEMENT------------------\n");
        menu.addOption("Add nurse");
        menu.addOption("Find nurse");
        menu.addOption("Update nurse");
        menu.addOption("Delete nurse");
        menu.addOption("Add patient");
        menu.addOption("Display patient");
        menu.addOption("Sort patient");
        menu.addOption("Save data");
        menu.addOption("Load data");
        menu.addOption("Quit");

        boolean check = true;

        Hospital hospital_mng = new Hospital();
        // hospital_mng.loadData();

        do {
            menu.printMenu();
            int choice = menu.getUserChoice();
            switch (choice) {
                case 1:
                    while (true) {
                        hospital_mng.createNurse();
                        if (!Menu.getYesOrNo("Do you want to countinue to add more nurse?")) {
                            break;
                        }
                    }
                    check = false;
                    break;
                case 2:
                    hospital_mng.findNurse();
                    break;
                case 3:
                    hospital_mng.updateNurse();
                    check = false;
                    break;
                case 4:
                    hospital_mng.deleteNurse();
                    check = false;
                    break;
                case 5:
                    while (true) {
                        hospital_mng.addPatient();
                        if (!Menu.getYesOrNo("Do you want to countinue to add more patient?")) {
                            break;
                        }
                    }
                    check = false;
                    break;
                case 6:
                    hospital_mng.displayPatients();
                    break;
                case 7:
                    hospital_mng.sortPatients();
                    break;
                case 8:
                    hospital_mng.saveData();
                    check = true;
                    break;
                case 9:
                    hospital_mng.loadData();
                    break;
                case 10:
                    hospital_mng.quit();
                    break;
            }
        } while (true);
    }
}
