package com.hairteen.hung.web.form;

public class EmployeeForm {

	private Integer idEmployee;
    private String nameEmployee;
    public Integer getIdEmployee() {
		return idEmployee;
	}
	public void setIdEmployee(Integer idEmployee) {
		this.idEmployee = idEmployee;
	}

	private Integer sex;
    private String birthday;
    private String address;
    private String phone;
    private String email;
    private String basicSalary;

    public String getNameEmployee() {
        return nameEmployee;
    }
    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }
    public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getBasicSalary() {
        return basicSalary;
    }
    public void setBasicSalary(String basicSalary) {
        this.basicSalary = basicSalary;
    }

    public EmployeeForm() {}

    public EmployeeForm(Integer idEmployee, String nameEmployee, Integer sex, String birthday, String address, String phone, String email,
            String basicSalary) {
        super();
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.sex = sex;
        this.birthday = birthday;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.basicSalary = basicSalary;
    }
}
