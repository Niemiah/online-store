package com.solvd.online.store.util.parser;
import com.solvd.online.store.model.Category;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CategoryHandler extends DefaultHandler {
    private Category category;
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
            case "categoryId":
                category.setCategoryId(Integer.parseInt(value));
                break;
            case "categoryName":
                category.setCategoryName(value);
                break;
            default:
                break;
        }
    }
}
