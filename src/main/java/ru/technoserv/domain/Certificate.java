package ru.technoserv.domain;

import java.sql.Date;
import java.util.Map;

public class Certificate {
    private Integer number;
    private Integer ownerId;
    private String name;
    private String issuerName;
    private Date issueDate;
    private Map<Integer, byte[]> pages;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Map<Integer, byte[]> getPages() {
        return pages;
    }

    public void setPages(Map<Integer, byte[]> pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "number=" + number +
                ", ownerId=" + ownerId +
                ", name='" + name + '\'' +
                ", issuerName='" + issuerName + '\'' +
                ", issueDate=" + issueDate +
                ", pages=" + pages +
                '}';
    }
}
