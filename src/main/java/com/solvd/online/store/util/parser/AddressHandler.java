package com.solvd.online.store.util.parser;
import com.solvd.online.store.model.Address;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class AddressHandler extends DefaultHandler {
    private Address address;
    private String currentElement = "";

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;
        address = new Address(); // Initialize the address object
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length).trim();
        if (value.isEmpty()) {
            return;
        }

        switch (currentElement) {
            case "addressId":
                address.setAddressId(Integer.parseInt(value));
                break;
            case "address":
                address.setAddress(value);
                break;
            case "city":
                address.setCity(value);
                break;
            case "state":
                address.setState(value);
                break;
            case "postalCode":
                address.setPostalCode(value);
                break;
            case "country":
                address.setCountry(value);
                break;
            default:
                break;
        }
    }
}