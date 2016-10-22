package io.alicorn.data.models;

public class Client extends User {
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    private String ssn;
    private String dob;
    private String ethnicity;
    private String gender;
    private boolean veteran;
    private String veteranInfo;
    private String maritalStatus;
    private int numberOfChildren;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isVeteran() {
        return veteran;
    }

    public void setVeteran(boolean veteran) {
        this.veteran = veteran;
    }

    public String getVeteranInfo() {
        return veteranInfo;
    }

    public void setVeteranInfo(String veteranInfo) {
        this.veteranInfo = veteranInfo;
    }

    @Override
    public Kind getKind() {
        return Kind.CLIENT;

    }
}
