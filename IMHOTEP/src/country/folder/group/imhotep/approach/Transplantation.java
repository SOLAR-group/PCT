package country.folder.group.imhotep.approach;

import java.lang.reflect.Constructor;
import java.util.Random;

import country.folder.group.imhotep.model.Model;
import country.folder.group.imhotep.model.KromaiaModel;

import country.folder.group.imhotep.util.IO;

public final class Transplantation<M extends Model> {

    public enum PatientType {
        DONOR,
        HOST
    }

    private final Class<M> model;

    private M donor;
    private M host;
    
    static private Random random = new Random(System.nanoTime());
    

    public Transplantation(final Class<M> model) {
        this.model = model;
    }

    public Transplantation<M> load(final PatientType type, final String filename) {
        try {
            Constructor<M> ctor = model.getConstructor(String.class);
            switch (type) {
                case DONOR -> donor = ctor.newInstance(filename);
                case HOST -> host = ctor.newInstance(filename);
            }
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    public M perform() {
        assert model == KromaiaModel.class;
        KromaiaModel D = (KromaiaModel) donor;
        KromaiaModel H = (KromaiaModel) host;

        KromaiaModel HP = new KromaiaModel(String.format("%s-%s", H.getURI(), D.getURI()), H);
        HP.setTransplantRootHullIndex(random.nextInt(H.getNumberOfHulls()));

        HP.addElementsFrom(KromaiaModel.ElementType.HULL, D);
        HP.addElementsFrom(KromaiaModel.ElementType.LINK, D);
        HP.addElementsFrom(KromaiaModel.ElementType.WEAPON, D);

        System.out.println("Host:  " + H.getEncoding().getConfiguration());
        System.out.println("Donor: " + D.getEncoding().getConfiguration());
        System.out.println("Host': " + HP.getEncoding().getConfiguration());

        IO.getInstance().deserializeXML(String.format("out/%s.xml", HP.getURI()), HP.getXML());

        return model.cast(HP);
    }
    
    public M performWithoutDonor() {
    	assert model == KromaiaModel.class;
    	
    	System.out.println("Host:  " + ((KromaiaModel) host).getEncoding().getConfiguration());
    	
    	return host;
    }

}
