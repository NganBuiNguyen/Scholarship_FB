package com.example.project.model;

import java.io.Serializable;
import java.util.Date;

public class InfoMsg implements Serializable {
    private int idInfoMsg;
    private String nameProfessor;
    private String nameUniversity;
    private String laboratory;
    private String salary;
    private String major;
    private String languge;
    private String programmingSkills;
    private int phone;
    private String email;
    private Date dateline;

    public InfoMsg() {
    }

    public InfoMsg(int idInfoMsg, String nameProfessor, String nameUniversity, String laboratory, String salary, String major, String languge, String programmingSkills, int phone, String email, Date dateline) {
        this.idInfoMsg = idInfoMsg;
        this.nameProfessor = nameProfessor;
        this.nameUniversity = nameUniversity;
        this.laboratory = laboratory;
        this.salary = salary;
        this.major = major;
        this.languge = languge;
        this.programmingSkills = programmingSkills;
        this.phone = phone;
        this.email = email;
        this.dateline = dateline;
    }

    public int getIdInfoMsg() {
        return idInfoMsg;
    }

    public String getNameProfessor() {
        return nameProfessor;
    }

    public String getNameUniversity() {
        return nameUniversity;
    }

    public String getLaboratory() {
        return laboratory;
    }

    public String getSalary() {
        return salary;
    }

    public String getMajor() {
        return major;
    }

    public String getLanguge() {
        return languge;
    }

    public String getProgrammingSkills() {
        return programmingSkills;
    }

    public int getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Date getDateline() {
        return dateline;
    }

    public void setIdInfoMsg(int idInfoMsg) {
        this.idInfoMsg = idInfoMsg;
    }

    public void setNameProfessor(String nameProfessor) {
        this.nameProfessor = nameProfessor;
    }

    public void setNameUniversity(String nameUniversity) {
        this.nameUniversity = nameUniversity;
    }

    public void setLaboratory(String laboratory) {
        this.laboratory = laboratory;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setLanguge(String languge) {
        this.languge = languge;
    }

    public void setProgrammingSkills(String programmingSkills) {
        this.programmingSkills = programmingSkills;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateline(Date dateline) {
        this.dateline = dateline;
    }

    @Override
    public String toString() {
        return "InfoMsg{" +
                "idInfoMsg=" + idInfoMsg +
                ", nameProfessor='" + nameProfessor + '\'' +
                ", nameUniversity='" + nameUniversity + '\'' +
                ", laboratory='" + laboratory + '\'' +
                ", salary='" + salary + '\'' +
                ", major='" + major + '\'' +
                ", languge='" + languge + '\'' +
                ", programmingSkills='" + programmingSkills + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", dateline=" + dateline +
                '}';
    }
}
