package qaguru.model;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer {
    private int id;
    private String name;
    private String email;
    @JsonProperty("isActive")
    private boolean active;
    private List<Address> addresses;
    private List<OrderItem> orders;

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public List<OrderItem> getOrders() {
        return orders;
    }
}