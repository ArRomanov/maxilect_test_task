package models;

public class AddressShippingModel {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String email;
    private String phone;
    private String postcode;

    public String getPostcode() {
        return postcode;
    }

    public AddressShippingModel setPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }


    public String getFirstName() {
        return firstName;
    }

    public AddressShippingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AddressShippingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public AddressShippingModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressShippingModel setCity(String city) {
        this.city = city;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AddressShippingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public AddressShippingModel setPhone(String phone) {
        this.phone = phone;
        return this;
    }

}