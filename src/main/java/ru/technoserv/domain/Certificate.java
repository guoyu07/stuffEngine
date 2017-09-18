package ru.technoserv.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "CERTIFICATE")
public class Certificate {

    public Certificate() {
    }

    @Id
    @Column(name = "CERT_NUMBER")
    private Integer number;

    @Column(name = "EMPLOYEE_ID")
    private Integer ownerId;

    @Column(name = "CERT_NAME")
    private String name;

    @Column(name = "ISSUER_NAME")
    private String issuerName;

    @Column(name = "ISSUE_DATE")
    private Date issueDate;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "CERT_NUMBER")
    private Set<Page> pages;

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

    public Set<Page> getPages() {
        return pages;
    }

    public void setPages(Set<Page> pages) {
        this.pages = pages;
    }
}

