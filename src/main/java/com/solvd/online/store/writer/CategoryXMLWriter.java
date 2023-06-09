package com.solvd.online.store.writer;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import com.solvd.online.store.model.Category;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
public class CategoryXMLWriter {

    public static void writeCategoryToXML(Category category) {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element root = document.createElement("Category");
            document.appendChild(root);

            Element categoryId = document.createElement("categoryId");
            categoryId.appendChild(document.createTextNode(String.valueOf(category.getCategoryId())));
            root.appendChild(categoryId);

            Element categoryName = document.createElement("categoryName");
            categoryName.appendChild(document.createTextNode(category.getCategoryName()));
            root.appendChild(categoryName);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("Category.xml"));

            transformer.transform(domSource, streamResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}