package com.solvd.online.store.model;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Addresses")
public class Addresses {
    @XmlElement(name = "Address")
    private List<Address> addresses;

    public Addresses() {
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}