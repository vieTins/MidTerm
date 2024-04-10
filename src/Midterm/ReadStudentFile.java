package Midterm;

import java.io.File;
import java.util.concurrent.BlockingQueue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

// Đọc File Student.xml
public class ReadStudentFile implements Runnable {
    private String fileName;
    private BlockingQueue<Student> queue;

    public ReadStudentFile(String fileName, BlockingQueue<Student> queue) {
        this.fileName = fileName;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            File file = new File(fileName);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("Student");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element studentElement = (Element) nodeList.item(i);
                String id = studentElement.getAttribute("id");
                String name = getElementValue(studentElement, "name");
                String address = getElementValue(studentElement, "address");
                String dateOfBirth = getElementValue(studentElement, "dateOfBirth");

                Student student = new Student(id, name, address, dateOfBirth);
                queue.put(student); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Phương thức hỗ trợ lấy nội dung của một phần tử con
    private String getElementValue(Element parentElement, String tagName) {
        NodeList nodeList = parentElement.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }
}
