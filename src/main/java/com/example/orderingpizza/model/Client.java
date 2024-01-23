package com.example.orderingpizza.model;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class Client {
    private String name;
    private String phone;
    private String email;
    private String address;

    public Client(String name, String phone, String email, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Client(HttpServletRequest request) {
        this.name = request.getParameter("name");
        this.phone = request.getParameter("phone");
        this.email = request.getParameter("email");
        this.address = getAddress(request);
    }

    private String getAddress(HttpServletRequest request) {
        String address = String.format("%s %s %s %s", request.getParameter("town"), request.getParameter("street"),
                request.getParameter("building"), request.getParameter("flat"));
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name) && Objects.equals(phone, client.phone) && Objects.equals(email, client.email) && Objects.equals(address, client.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, email, address);
    }
}
