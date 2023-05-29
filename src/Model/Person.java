package Model;

public class Person {

    String name;
    int age;
    String gender;
    String address;
    String phone;

    public Person() {
    }

    public Person(String name, int age, String gender, String address, String phone) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void showInfo() {
        String str;
        str = String.format("|%25s|%-4d|%-10s|%-50s|%10s|", this.name,
                this.age, this.gender, this.address, this.phone);
        System.out.println(str);
    }
}
