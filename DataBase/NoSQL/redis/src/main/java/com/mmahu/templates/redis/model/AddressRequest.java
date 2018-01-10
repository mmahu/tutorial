package com.mmahu.templates.redis.model;

public class AddressRequest {

    private String key;
    private Address address;

    public AddressRequest() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
   }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
