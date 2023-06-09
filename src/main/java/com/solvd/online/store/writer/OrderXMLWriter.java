package com.solvd.online.store.writer;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import com.solvd.online.store.model.Order;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
public class OrderXMLWriter {

    public static void writeOrderToXML(Order order) {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element root = document.createElement("Order");
            document.appendChild(root);

            Element orderId = document.createElement("orderId");
            orderId.appendChild(document.createTextNode(String.valueOf(order.getOrderId())));
            root.appendChild(orderId);

            Element userId = document.createElement("userId");
            userId.appendChild(document.createTextNode(String.valueOf(order.getUserId())));
            root.appendChild(userId);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("Order.xml"));

            transformer.transform(domSource, streamResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
