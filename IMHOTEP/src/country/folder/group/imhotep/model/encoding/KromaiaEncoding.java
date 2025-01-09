package country.folder.group.imhotep.model.encoding;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

import country.folder.group.imhotep.model.KromaiaModel;

import static country.folder.group.imhotep.approach.simulation.IndividualBossConfig.GetCharacterFromNumber;

public final class KromaiaEncoding extends Encoding {

    private final Document xml;

    public String vi;
    public String me;
    public String bu;
    public String ho;
    public String la;
    public String hu;
    public String li;
    public String be;

    public String tr;

    public KromaiaEncoding(final Document xml) {
        this.xml = xml;
        this.tr = getTransplantRootHullIndex();
    }

    @SuppressWarnings("unused")
    public KromaiaEncoding(final String configuration) {
        this((Document) null);

        this.vi = configuration.substring(VI_BEGIN_INDEX, VI_END_INDEX);
        this.me = configuration.substring(ME_BEGIN_INDEX, ME_END_INDEX);
        this.bu = configuration.substring(BU_BEGIN_INDEX, BU_END_INDEX);
        this.ho = configuration.substring(HO_BEGIN_INDEX, HO_END_INDEX);
        this.la = configuration.substring(LA_BEGIN_INDEX, LA_END_INDEX);
        this.hu = configuration.substring(HU_BEGIN_INDEX, HU_END_INDEX);
        this.li = configuration.substring(LI_BEGIN_INDEX, LI_END_INDEX);
        this.be = configuration.substring(BE_BEGIN_INDEX, BE_END_INDEX);
        this.tr = configuration.substring(TR_BEGIN_INDEX, TR_END_INDEX);

        this.configuration = configuration;
    }

    @Override
    public Encoding apply() {
        this.vi = getWeakPoints();
        this.me = getWeapons(WeaponType.MELEE);
        this.bu = getWeapons(WeaponType.BULLET);
        this.ho = getHomingProjectiles();
        this.la = getWeapons(WeaponType.LASER);
        this.hu = getHulls();
        this.li = getLinks();
        this.be = getBehavioralHulls();

        buildConfiguration();

        return this;
    }

    public static final int CONFIGURATION_ROW_LENGTH = 150;

    private static final int VI_BEGIN_INDEX = 0;
    private static final int VI_END_INDEX = CONFIGURATION_ROW_LENGTH;

    private static final int ME_BEGIN_INDEX = CONFIGURATION_ROW_LENGTH;
    private static final int ME_END_INDEX = 2 * CONFIGURATION_ROW_LENGTH;

    private static final int BU_BEGIN_INDEX = 2 * CONFIGURATION_ROW_LENGTH;
    private static final int BU_END_INDEX = 3 * CONFIGURATION_ROW_LENGTH;

    private static final int HO_BEGIN_INDEX = 3 * CONFIGURATION_ROW_LENGTH;
    private static final int HO_END_INDEX = 4 * CONFIGURATION_ROW_LENGTH;

    private static final int LA_BEGIN_INDEX = 4 * CONFIGURATION_ROW_LENGTH;
    private static final int LA_END_INDEX = 5 * CONFIGURATION_ROW_LENGTH;

    private static final int HU_BEGIN_INDEX = 5 * CONFIGURATION_ROW_LENGTH;
    private static final int HU_END_INDEX = 6 * CONFIGURATION_ROW_LENGTH;

    private static final int LI_BEGIN_INDEX = 6 * CONFIGURATION_ROW_LENGTH;
    private static final int LI_END_INDEX = 7 * CONFIGURATION_ROW_LENGTH;

    private static final int BE_BEGIN_INDEX = 7 * CONFIGURATION_ROW_LENGTH;
    private static final int BE_END_INDEX = 8 * CONFIGURATION_ROW_LENGTH;

    private static final int TR_BEGIN_INDEX = 8 * CONFIGURATION_ROW_LENGTH;
    private static final int TR_END_INDEX = 9 * CONFIGURATION_ROW_LENGTH;

    public enum WeaponType {
        MELEE, BULLET, HOMING, LASER
    }

    private static final Map<WeaponType, List<Integer>> WeaponTypes = Map.ofEntries(
            entry(WeaponType.MELEE, List.of(3)),
            entry(WeaponType.BULLET, List.of(0)),
            entry(WeaponType.HOMING, List.of(6, 102)),
            entry(WeaponType.LASER, List.of(6))
    );

    private String getWeakPoints() {
        NodeList hulls = xml.getElementsByTagName(KromaiaModel.ElementType.HULL.childs);

        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < hulls.getLength(); ++i) {
            Node hull = hulls.item(i);
            Node hullVitalData = ((Element) hull).getElementsByTagName("HullVitalData").item(0);

            if (hullVitalData != null) {
                positions.add(i);
            }
        }

        return padding(
                '0',
                '1', positions.stream().mapToInt(Integer::intValue).toArray(),
                CONFIGURATION_ROW_LENGTH
        );
    }

    private String getWeapons(final WeaponType weaponType) {
        NodeList weapons = xml.getElementsByTagName(KromaiaModel.ElementType.WEAPON.childs);

        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < weapons.getLength(); ++i) {
            Node weapon = weapons.item(i);

            int weaponTypeValue = Integer.parseInt(weapon.getAttributes().getNamedItem("WeaponType").getTextContent());

            if (WeaponTypes.get(weaponType).contains(weaponTypeValue)) {
                Node componentData = ((Element) weapon).getElementsByTagName("ComponentData").item(0);

                int hullIndexParent = Integer.parseInt(
                        componentData.getAttributes().getNamedItem("HullIndexParent").getTextContent()
                );

                positions.add(hullIndexParent);
            }
        }

        return padding(
                '0',
                '1', positions.stream().mapToInt(Integer::intValue).toArray(),
                CONFIGURATION_ROW_LENGTH
        );
    }

    private String getHomingProjectiles() {
        NodeList weapons = xml.getElementsByTagName(KromaiaModel.ElementType.WEAPON.childs);

        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < weapons.getLength(); ++i) {
            Node weapon = weapons.item(i);

            Node weaponProjectileData = ((Element) weapon).getElementsByTagName("WeaponProjectileData").item(0);

            if (weaponProjectileData != null) {
                int projectileType = Integer.parseInt(
                        weaponProjectileData.getAttributes().getNamedItem("ProjectileType").getTextContent()
                );

                if (WeaponTypes.get(WeaponType.HOMING).contains(projectileType)) {
                    Node componentData = ((Element) weapon).getElementsByTagName("ComponentData").item(0);

                    int hullIndexParent = Integer.parseInt(
                            componentData.getAttributes().getNamedItem("HullIndexParent").getTextContent()
                    );

                    positions.add(hullIndexParent);
                }
            }
        }

        return padding(
                '0',
                '1', positions.stream().mapToInt(Integer::intValue).toArray(),
                CONFIGURATION_ROW_LENGTH
        );
    }

    private String getHulls() {
        return padding(
                '0',
                '1', xml.getElementsByTagName(KromaiaModel.ElementType.HULL.childs).getLength(),
                CONFIGURATION_ROW_LENGTH
        );
    }

    private String getLinks() {
        NodeList links = xml.getElementsByTagName(KromaiaModel.ElementType.LINK.childs);

        Map<Integer, Integer> replace = new HashMap<>();
        for (int i = 0; i < links.getLength(); ++i) {
            Node link = links.item(i);
            Node linkData = ((Element) link).getElementsByTagName("LinkData").item(0);

            int position = Integer.parseInt(linkData.getAttributes().getNamedItem("HullIndexSecond").getTextContent());
            int value = Integer.parseInt(linkData.getAttributes().getNamedItem("HullIndexFirst").getTextContent());
            
            if (position < 0) continue;

            replace.put(position, value);
        }

        return padding('-', replace, CONFIGURATION_ROW_LENGTH);
    }

    private String getBehavioralHulls() {
        return "0".repeat(CONFIGURATION_ROW_LENGTH);
    }

    private String getTransplantRootHullIndex() {
        return "-".repeat(CONFIGURATION_ROW_LENGTH);
    }

    public void setTransplantRootHullIndex(final int from, final int to) {
        this.tr = replace(tr.toCharArray(), from, to, GetCharacterFromNumber(transplantRootIndex));

        buildConfiguration();
    }

    private void buildConfiguration() {
        this.configuration = String.format("%s%s%s%s%s%s%s%s%s", vi, me, bu, ho, la, hu, li, be, tr);
    }

    @SuppressWarnings({"unused", "SameParameterValue"})
    private static String padding(final char initial, final char replacement, final int repeat, final int length) {
        char[] str = String.valueOf(initial).repeat(length).toCharArray();

        for (int i = 0; i < repeat; ++i) {
            str[i] = replacement;
        }

        return new String(str);
    }

    @SuppressWarnings({"unused", "SameParameterValue"})
    private static String padding(final char initial, final char replacement, final int[] positions, final int length) {
        char[] str = String.valueOf(initial).repeat(length).toCharArray();

        for (int i : positions) {
            str[i] = replacement;
        }

        return new String(str);
    }

    @SuppressWarnings({"unused", "SameParameterValue"})
    private static String padding(final char initial, final Map<Integer, Integer> replace, final int length) {
        char[] str = String.valueOf(initial).repeat(length).toCharArray();

        for (int i : replace.keySet()) {
            str[i] = GetCharacterFromNumber(replace.get(i));
        }

        return new String(str);
    }

    @SuppressWarnings({"unused", "SameParameterValue"})
    private static String replace(final char[] str, final int from, final int to, final char replacement) {
        for (int i = from; i < to; ++i) {
            str[i] = replacement;
        }

        return new String(str);
    }

    @SuppressWarnings({"unused", "SameParameterValue"})
    private static String replace(final char[] str, final int from, final char[] replacements) {
        for (int i = from, j = 0; j < replacements.length; ++i, ++j) {
            str[i] = replacements[j];
        }

        return new String(str);
    }

}
