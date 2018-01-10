package com.mmahu.templates.redis.model;

public class Address {
    private String cite;
    private String zip;

    public Address() {
    }

    public Address(String cite, String zip) {
        this.cite = cite;
        this.zip = zip;
    }

    public String getCite() {
        return cite;
    }

    public void setCite(String cite) {
        this.cite = cite;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
