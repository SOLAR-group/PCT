package country.folder.group.imhotep.util;

import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

import org.xml.sax.SAXException;

public final class IO {

    private static final ClassLoader context = Thread.currentThread().getContextClassLoader();

    private final DocumentBuilder db;

    private static IO INSTANCE;

    private IO() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            this.db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static IO getInstance() {
        return (INSTANCE == null) ? INSTANCE = new IO() : INSTANCE;
    }

    public static File getResourceAsFile(final String pathname) {
        URL resource = context.getResource(pathname);
        return (resource != null) ? new File(resource.getFile()) : null;
    }

    public Document parseXML(final String pathname) {
        try {
            Document xml = db.parse(getResourceAsFile(pathname));
            xml.getDocumentElement().normalize();
            return xml;
        } catch (IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public void deserializeXML(final String pathname, final Document xml) {
        File file = new File(pathname);

        File parent = file.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }

        try (OutputStream stream = new FileOutputStream(file)) {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(xml);
            StreamResult result = new StreamResult(stream);

            transformer.transform(source, result);
        } catch (IOException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }

}
