package com.solvd.online.store.model;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Categories")
public class Categories {
    @XmlElement(name = "Category")
    private List<Category> categories;

    public Categories() {
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}