package country.folder.group.imhotep.model.encoding;

public abstract class Encoding {

    protected String configuration;

    protected int transplantRootIndex;

    public abstract Encoding apply();

    public String getConfiguration() {
        return configuration;
    }

    public int getTransplantRootIndex() {
        return transplantRootIndex;
    }

    public void setTransplantRootIndex(int transplantRootIndex) {
        this.transplantRootIndex = transplantRootIndex;
    }

}
