package country.folder.group.imhotep.model;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import country.folder.group.imhotep.model.encoding.KromaiaEncoding;

public final class KromaiaModel extends Model {

    public enum ElementType {
        HULL("HULL", "Hull"),
        LINK("LINKS", "Link"),
        WEAPON("WEAPONRYWEAPONSAI", "Weapon");

        public final String parent;
        public final String childs;

        ElementType(final String parent, final String childs) {
            this.parent = parent;
            this.childs = childs;
        }
    }

    private final int hullsNumber;
    private final NodeList hulls;

    private final int linksNumber;
    private final NodeList links;

    private final int weaponsNumber;
    private final NodeList weapons;

    @SuppressWarnings("unused")
    public KromaiaModel(final String uri) {
        super(uri);

        this.hullsNumber = getNumberOf(ElementType.HULL.parent);
        this.hulls = getElementsBy(ElementType.HULL.childs, hullsNumber);

        this.linksNumber = getNumberOf(ElementType.LINK.parent);
        this.links = getElementsBy(ElementType.LINK.childs, linksNumber);

        this.weaponsNumber = getNumberOf(ElementType.WEAPON.parent);
        this.weapons = getElementsBy(ElementType.WEAPON.childs, weaponsNumber);

        this.encoding = new KromaiaEncoding(xml).apply();
    }

    public KromaiaModel(final String uri, final KromaiaModel model) {
        super(uri, model.xml);

        this.hullsNumber = model.hullsNumber;
        this.hulls = model.hulls;

        this.linksNumber = model.linksNumber;
        this.links = model.links;

        this.weaponsNumber = model.weaponsNumber;
        this.weapons = model.weapons;

        this.encoding = new KromaiaEncoding(model.xml).apply();
    }

    private int getNumberOf(final String elements) {
        Node node = xml.getElementsByTagName(elements).item(0);
        return node != null ? Integer.parseInt(node.getAttributes().getNamedItem("Number").getTextContent()) : 0;
    }

    private void setNumberOf(final String elements, int to) {
        xml.getElementsByTagName(elements).item(0).getAttributes().getNamedItem("Number").setTextContent(
                String.valueOf(to)
        );
    }

    private NodeList getElementsBy(final String tagName, final int bound) {
        NodeList elements = xml.getElementsByTagName(tagName);

        for (int i = elements.getLength() - 1; i >= 0; --i) {
            Element e = (Element) elements.item(i);

            if (i >= bound) {
                e.getParentNode().removeChild(e);
            }
        }

        return elements;
    }

    public void addElementsFrom(final ElementType element, final KromaiaModel donor) {
        NodeList elements;
        int to = -1;

        switch (element) {
            case HULL -> {
                elements = donor.hulls;
                to = hullsNumber + donor.hullsNumber;

                appendHulls(elements);
                encoding.apply();

                ((KromaiaEncoding) encoding).setTransplantRootHullIndex(hullsNumber, to);
            }
            case LINK -> {
                elements = donor.links;
                to = linksNumber + donor.linksNumber + 1;

                appendLinks(elements);
                encoding.apply();
            }
            case WEAPON -> {
                elements = donor.weapons;
                to = weaponsNumber + donor.weaponsNumber;

                appendWeapons(elements);
                encoding.apply();
            }
        }

        setNumberOf(element.parent, to);
    }

    private void appendHulls(final NodeList elements) {
        for (int i = 0; i < elements.getLength(); ++i) {
            Node node = xml.importNode(elements.item(i), true);
            Node physicalObjectData = ((Element) node).getElementsByTagName("PhysicalObjectData").item(0);

            physicalObjectData.getAttributes().getNamedItem("ObjectMass").setTextContent("0.1");

            xml.getElementsByTagName(ElementType.HULL.parent).item(0).appendChild(node);
        }
    }

    private void appendLinks(final NodeList elements) {
        for (int i = 0; i < elements.getLength(); ++i) {
            Node node = xml.importNode(elements.item(i), true);
            Node linkData = ((Element) node).getElementsByTagName("LinkData").item(0);

            int hullIndexFirst = Integer.parseInt(
                    linkData.getAttributes().getNamedItem("HullIndexFirst").getTextContent()
            );
            linkData.getAttributes().getNamedItem("HullIndexFirst").setTextContent(
                    String.valueOf(hullsNumber + hullIndexFirst)
            );

            int hullIndexSecond = Integer.parseInt(
                    linkData.getAttributes().getNamedItem("HullIndexSecond").getTextContent()
            );
            linkData.getAttributes().getNamedItem("HullIndexSecond").setTextContent(
                    String.valueOf(hullsNumber + hullIndexSecond)
            );

            xml.getElementsByTagName(ElementType.LINK.parent).item(0).appendChild(node);
        }

        Element link = xml.createElement("Link");
        link.setAttribute("LinkType", "0");

        Element linkData = xml.createElement("LinkData");
        linkData.setAttribute("HullIndexFirst", String.valueOf(encoding.getTransplantRootIndex()));
        linkData.setAttribute("HullIndexSecond", String.valueOf(hullsNumber));
        linkData.setAttribute("Destructible", "0");
        linkData.setAttribute("DestructionDelay", "0");
        linkData.setAttribute("EffectMultiplierKill", "1");
        linkData.setAttribute("EffectMultiplierDestroy", "1");
        linkData.setAttribute("EffectsMaskKill", "00000000000000000000000000000101");
        linkData.setAttribute("EffectsMaskDestroy", "00000000000000000000000000000000");
        link.appendChild(linkData);

        xml.getElementsByTagName(ElementType.LINK.parent).item(0).appendChild(link);
    }

    private void appendWeapons(final NodeList elements) {
        for (int i = 0; i < elements.getLength(); ++i) {
            Node node = xml.importNode(elements.item(i), true);
            Node componentData = ((Element) node).getElementsByTagName("ComponentData").item(0);

            int hullIndexParent = Integer.parseInt(
                    componentData.getAttributes().getNamedItem("HullIndexParent").getTextContent()
            );
            componentData.getAttributes().getNamedItem("HullIndexParent").setTextContent(
                    String.valueOf(hullsNumber + hullIndexParent)
            );

            xml.getElementsByTagName(ElementType.WEAPON.parent).item(0).appendChild(node);
        }
    }

    public void setTransplantRootHullIndex(int transplantRootHullIndex) {
        encoding.setTransplantRootIndex(transplantRootHullIndex);
    }
    
    public int getNumberOfHulls() {
    	return hullsNumber;
    }

}
