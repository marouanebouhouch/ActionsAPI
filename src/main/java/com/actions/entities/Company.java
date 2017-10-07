package com.actions.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Company implements Serializable{
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private long turnover;
    private String ceo;
    private String catchphrase;
    private String logo;
    private String industry;
    private String url;
    private double latitude;
    private double longitude;
    @OneToMany(mappedBy="company",fetch= FetchType.EAGER)
    List<Operation> operations;
    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "company_tag",
            joinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    List<Tag> tags;

    public Company() {
    }

    public Company(String name, String address, long turnover, String ceo, String catchphrase, String logo, String industry, String url, double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.turnover = turnover;
        this.ceo = ceo;
        this.catchphrase = catchphrase;
        this.logo = logo;
        this.industry = industry;
        this.url = url;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Company(String name, String address, long turnover, String ceo, String catchphrase, String logo, String industry, String url, double latitude, double longitude, List<Operation> operations, List<Tag> tags) {
        this.name = name;
        this.address = address;
        this.turnover = turnover;
        this.ceo = ceo;
        this.catchphrase = catchphrase;
        this.logo = logo;
        this.industry = industry;
        this.url = url;
        this.latitude = latitude;
        this.longitude = longitude;
        this.operations = operations;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getTurnover() {
        return turnover;
    }

    public void setTurnover(long turnover) {
        this.turnover = turnover;
    }

    public String getCeo() { return ceo; }

    public void setCeo(String ceo) { this.ceo = ceo; }

    public String getCatchphrase() {
        return catchphrase;
    }

    public void setCatchphrase(String catchphrase) {
        this.catchphrase = catchphrase;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
