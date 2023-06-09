package com.solvd.online.store.parser;
import com.solvd.online.store.model.Order;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class OrderHandler extends DefaultHandler {
    private Order order;
    private String currentElement = "";

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length).trim();
        if (value.isEmpty()) {
            return;
        }

        switch (currentElement) {
            case "orderId":
                order.setOrderId(Integer.parseInt(value));
                break;
            case "userId":
                order.setUserId(Integer.parseInt(value));
                break;
            default:
                break;
        }
    }
}
