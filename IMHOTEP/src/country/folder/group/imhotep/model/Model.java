package country.folder.group.imhotep.model;

import org.w3c.dom.Document;

import country.folder.group.imhotep.model.encoding.Encoding;

import country.folder.group.imhotep.util.IO;

public abstract class Model {

    protected final String uri;
    protected final Document xml;
    protected Encoding encoding;

    public Model(final String uri) {
        this(uri.replaceAll(".*[\\\\/]|\\.[^.]*$", ""), IO.getInstance().parseXML(uri));
    }

    protected Model(final String uri, final Document xml) {
        this.uri = uri;
        this.xml = xml;
    }

    public String getURI() {
        return uri;
    }

    public Document getXML() {
        return xml;
    }

    public Encoding getEncoding() {
        return encoding;
    }

}
