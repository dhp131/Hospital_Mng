package Business;

import Model.Nurse;
import MyUses.Uses;
import java.util.HashMap;

/**
 *Danh sách y tá
 * @author Hp
 */

public class NurseList extends HashMap<String, Nurse> {

    // create nurse
    public void createNurse() {
        String staffID;
        String name;
        int age;
        String gender;
        String address;
        String phone;
        String department;
        String shift;
        double salary;

        boolean check = true;
        do {
            //Check mã code được yêu cầu nhập vô
            staffID = Uses.getStringreg("Enter the staff ID (NXXXX): ", "^N\\d{4}$", "Code is not null", "Code is not format (N0000)");
            if (this.containsKey(staffID)) {
                System.out.println("StaffID already exists. Please enter a unique ID.");
            } else {
                check = false; // nếu chưa có mã code thì sẽ thoát khỏi vòng lặp và được add thêm vào chương trình
            }
        } while (check);

        name = Uses.getStringNonBlank("Enter your name: ", "Name is not blank");
        age = Uses.getInt("Enter your age: ", 0);
        gender = Uses.getStringreg("Enter your gender [Male/Female]: ", "^(Male|Female)$", "Gender is not blank", "Gender is not format!");
        address = Uses.getStringNonBlank("Enter your address: ", "Address is not blank");
        phone = Uses.getStringreg("Enter your phone number: ", "0\\d{9}", "Phone number is not null", "Phone number is not format");
        department = Uses.getStringlength("Enter department: ", "Invalid!", 3, 50);
        shift = Uses.getString("Enter shift: ");
        salary = Uses.getFloat("Enter salary: ", 0);

        //tạo đối tượng nurse
        Nurse nurse = new Nurse(name, age, gender, address, phone, staffID, department, shift, salary);
        this.put(staffID, nurse);
        System.out.println("Nurse added successfully.");

    }

    // find a nurse
    public void findNurse() {
        String name = Uses.getStringNonBlank("Enter the nurse's name or part of the name: ", "Name is not blank");
        boolean found = false;
        for (Nurse nurse : this.values()) {
            if (nurse.getName().contains(name)) {
                System.out.println(nurse);
                found = true;
            }
        }
        if (!found) {
            System.out.println("The nurse does not exist.");
        }
    }

    // check id của nurse có tồn tại hay không?
    public Nurse find(String id) {
        if (this.containsKey(id)) {
            return this.get(id);
        } else {
            return null;
        }
    }

    //Update a nurse - c1
    public void updateNurse() {

        String upID = Uses.getStringreg("Enter the staff ID (NXXXX): ", "^N\\d{4}$", "Code is not null", "Code is not format (N0000)");
        if (this.get(upID) != null) {
            System.out.println("Update the nurse (enter blank to keep the old infomation)");

            //Nhập lại thông tin cần update - nếu ko cần update thì gõ enter
            String name = Uses.getString("Enter new name: ");
            String age = Uses.getString("Enter new age: ");
            String gender = Uses.getString("Enter new gender: ");
            String address = Uses.getString("Enter new address: ");
            String phone = Uses.getString("Enter new phone: ");
            String department = Uses.getString("Enter new department: ");
            String shift = Uses.getString("Enter new shift: ");
            String salary = Uses.getString("Enter new salary: ");

            try {
                if (!age.isEmpty() && Integer.parseInt(age) <= 0) {
                    throw new Exception("Age must be a positive integer number:");
                }
                if (!gender.isEmpty() && !gender.matches("^(Male|Female)$")) {
                    throw new Exception("Gender must be Male or Female");
                }
                if (!phone.isEmpty() && !phone.matches("0\\d{9}")) {
                    throw new Exception("Invalid phone number");
                }
                if (!department.isEmpty() && (department.length() < 3 || department.length() > 50)) {
                    throw new Exception(" Department length must be in [3,50]");
                }
                if (!salary.isEmpty() && Double.parseDouble(salary) < 0) {
                    throw new Exception("Salary must be a positive real number");
                }
            } catch (Exception e) {
                System.out.println("Update failed!");
                return;
            }
            if (!name.isEmpty()) {
                this.get(upID).setName(name);
            }
            if (!age.isEmpty()) {
                this.get(upID).setAge(Integer.parseInt(age));
            }
            if (!gender.isEmpty()) {
                this.get(upID).setGender(gender);
            }
            if (!address.isEmpty()) {
                this.get(upID).setAddress(address);
            }
            if (!phone.isEmpty()) {
                this.get(upID).setPhone(phone);
            }
            if (!department.isEmpty()) {
                this.get(upID).setDepartment(department);
            }
            if (!shift.isEmpty()) {
                this.get(upID).setShift(shift);
            }
            if (!salary.isEmpty()) {
                this.get(upID).setSalary(Double.parseDouble(salary));
            }
            System.out.println("Nurse updated successfully!!");
        } else {
            System.out.println("The nurse does not exist!");
        }
    }

//    //update nurse -- c2
//    public void updateNurse() {
//        HashMap<String, Nurse> nursesID = new HashMap<>();
//        String staffID = Uses.getStringreg("Enter the staff ID (N000): ", "^N\\d{3}$", "Code is not null", "Code is not format (N000)");
//
//        Nurse nurse = nursesID.get(staffID);
//        if (nurse == null) {
//            System.out.println("The nurse does not exist.");
//            return;
//        }
//
//        boolean check = false;
//        while (!check) {
//            System.out.println("Which field do you want to update?");
//            System.out.println("1. Name");
//            System.out.println("2. Age");
//            System.out.println("3. Gender");
//            System.out.println("4. Address");
//            System.out.println("5. Phone");
//            System.out.println("6. Department");
//            System.out.println("7. Shift");
//            System.out.println("8. Salary");
//            System.out.println("0. Done");
//
//            int choice = Uses.getInt("Enter your choice: ", 8, 1);
//            switch (choice) {
//                case 1:
//                    String name = name = Uses.getStringNonBlank("Enter your name: ", "Name is not blank");
//                    nurse.setName(name);
//                    break;
//                case 2:
//                    int age = Uses.getInt("Enter your age: ", 0);
//                    nurse.setAge(age);
//                    break;
//                case 3:
//                    String gender = Uses.getStringNonBlank("Enter your gender: ", "Gender is not blank");
//                    nurse.setGender(gender);
//                    break;
//                case 4:
//                    String address = Uses.getStringNonBlank("Enter your address: ", "Address is not blank");
//                    nurse.setAddress(address);
//                    break;
//                case 5:
//                    String phone = Uses.getStringreg("Enter your phone number: ", "0\\d{9}", "Phone number is not null", "Phone number is not format");
//                    nurse.setPhone(phone);
//                    break;
//                case 6:
//                    String department = Uses.getStringlength("Enter department: ", "Not exist!", 3, 50);
//                    nurse.setDepartment(department);
//                    break;
//                case 7:
//                    String shift = Uses.getString("Enter shift: ");
//                    nurse.setShift(shift);
//                    break;
//                case 8:
//                    double salary = Uses.getFloat("Enter salary: ", 100000000, 0);
//                    nurse.setSalary(salary);
//                    break;
//                default:
//                    check = true;
//                    System.out.println("Invalid choice.");
//                    break;
//            }
//        }
//        System.out.println("Nurse updated successfully.");
//    }

    //4. Xóa y tá
    public void deleteNurse() {
        String deleteStaffID = Uses.getStringreg("Enter the staff ID (NXXXX): ", "^N\\d{4}$", "Code is not null", "Code is not format (N0000)");
        for (String key : this.keySet()) {
            Nurse nurse = this.get(key);
            if (nurse.getStaffID().equalsIgnoreCase(deleteStaffID)) {
                if (nurse.getNumPatientAssign() != 0) {
                    System.out.println("Can not delete this nurse because he/she still has task!");
                } else {
                    String check = Uses.getString("Are you want to delete [Y/N]? ");
                    if (check.equals("Y")) {
                        this.remove(nurse.getStaffID());
                        System.out.println("Delete nurse successfully!");
                        return;
                    } else {
                        return;
                    }
                }
            }
        }
        System.out.println("The nurse does not exist");
    }

}
