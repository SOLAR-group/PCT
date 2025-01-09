package country.folder.group.imhotep.approach.simulation;

import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;
import java.util.Set;

import country.folder.group.imhotep.model.encoding.KromaiaEncoding;

import java.util.HashSet;

public class IndividualBossConfig extends Individual {

	/////////////////////////////////////////////////////////////////////////////

	public enum VersionType {
		VERSION_TYPE_1, VERSION_TYPE_2, VERSION_TYPE_3
	}

	public enum AddOnType {
		ADDON_TYPE_VITAL_DEFAULT, ADDON_TYPE_WEAPON_MELEE, ADDON_TYPE_WEAPON_BULLET, ADDON_TYPE_WEAPON_HOMING,
		ADDON_TYPE_WEAPON_LASER,
		//
		ADDON_TYPE_HULL_ENABLED, ADDON_TYPE_LINK_PARENT, ADDON_TYPE_MOVEMENT_LEAD
	}

	public enum DuelResultType {
		DUEL_RESULT_TYPE_DRAW, DUEL_RESULT_TYPE_PLAYER_WINS, DUEL_RESULT_TYPE_BOSS_WINS
	}

	public static class Quality {
		public Quality() {
		}
		// Objective indicators
		double Q_Completion = 0;
		double Q_Duration = 0;
		double Q_Uncertainty = 0;
		double Q_KillerMoves = 0;
		double Q_Permanence = 0;
		double Q_LeadChange = 0;
		double Q_Overall = 0;
		// Subjective indicators
		double S_Subjective1 = 0;
		double S_Overall = 0;
	};

	/////////////////////////////////////////////////////////////////////////////

	static protected final boolean IS_REPARATION_ENABLED = false;

	static protected final VersionType VERSION_TYPE = VersionType.VERSION_TYPE_3;

	static protected final char INVALID_CHARACTER = '-';

	// private static final int mNumberOfWeaponTypes =
	// GetNumberOfRowsInEncoding();

	protected ArrayList<Integer> mHullsEnabledIndices; // Number
														// of
														// enabled
														// hulls

	protected String mConfiguration; // Configuration
									// value
	
	protected String mTreeSequence; // Tree sequence (actually, this is an excerpt of mConfiguration)

	protected String mDescription; // Description

	protected Quality mQuality; // Quality

	static protected Random random = new Random(System.nanoTime());

	static protected final int CONFIGURATION_ROW_LENGTH = KromaiaEncoding.CONFIGURATION_ROW_LENGTH;

	/////////////////////////////////////////////////////////////////////////////

	static public int GetNumberOfRowsInEncoding() {
		int numberOfRowsInEncoding = 0;
		switch (VERSION_TYPE) {
		case VERSION_TYPE_1:
			numberOfRowsInEncoding = 5;
			break;
		case VERSION_TYPE_2:
			numberOfRowsInEncoding = 8;
			break;
		case VERSION_TYPE_3:
			numberOfRowsInEncoding = 9;
			break;
		default:
			System.out.println("Version type out of range.");
			break;
		}
		return numberOfRowsInEncoding;
	}

	/////////////////////////////////////////////////////////////////////////////

	static public char GetCharacterFromNumber(int number) {
		char text = INVALID_CHARACTER;

		switch (number) {
		case -1:
			text = INVALID_CHARACTER;
			break;

		case 0:
			text = '0';
			break;
		case 1:
			text = '1';
			break;
		case 2:
			text = '2';
			break;
		case 3:
			text = '3';
			break;
		case 4:
			text = '4';
			break;
		case 5:
			text = '5';
			break;
		case 6:
			text = '6';
			break;
		case 7:
			text = '7';
			break;
		case 8:
			text = '8';
			break;
		case 9:
			text = '9';
			break;

		case 10:
			text = 'a';
			break;
		case 11:
			text = 'b';
			break;
		case 12:
			text = 'c';
			break;
		case 13:
			text = 'd';
			break;
		case 14:
			text = 'e';
			break;
		case 15:
			text = 'f';
			break;
		case 16:
			text = 'g';
			break;
		case 17:
			text = 'h';
			break;
		case 18:
			text = 'i';
			break;
		case 19:
			text = 'j';
			break;

		case 20:
			text = 'k';
			break;
		case 21:
			text = 'l';
			break;
		case 22:
			text = 'm';
			break;
		case 23:
			text = 'n';
			break;
		case 24:
			text = 'o';
			break;
		case 25:
			text = 'p';
			break;
		case 26:
			text = 'q';
			break;
		case 27:
			text = 'r';
			break;
		case 28:
			text = 's';
			break;
		case 29:
			text = 't';
			break;

		case 30:
			text = 'u';
			break;
		case 31:
			text = 'v';
			break;
		case 32:
			text = 'w';
			break;
		case 33:
			text = 'x';
			break;
		case 34:
			text = 'y';
			break;
		case 35:
			text = 'z';
			break;
		case 36:
			text = 'A';
			break;
		case 37:
			text = 'B';
			break;
		case 38:
			text = 'C';
			break;
		case 39:
			text = 'D';
			break;

		case 40:
			text = 'E';
			break;
		case 41:
			text = 'F';
			break;
		case 42:
			text = 'G';
			break;
		case 43:
			text = 'H';
			break;
		case 44:
			text = 'I';
			break;
		case 45:
			text = 'J';
			break;
		case 46:
			text = 'K';
			break;
		case 47:
			text = 'L';
			break;
		case 48:
			text = 'M';
			break;
		case 49:
			text = 'N';
			break;

		case 50:
			text = 'O';
			break;
		case 51:
			text = 'P';
			break;
		case 52:
			text = 'Q';
			break;
		case 53:
			text = 'R';
			break;
		case 54:
			text = 'S';
			break;
		case 55:
			text = 'T';
			break;
		case 56:
			text = 'U';
			break;
		case 57:
			text = 'V';
			break;
		case 58:
			text = 'W';
			break;
		case 59:
			text = 'X';
			break;

		case 60:
			text = 'Y';
			break;
		case 61:
			text = 'Z';
			break;
		case 62:
			text = '[';
			break;
		case 63:
			text = ']';
			break;
		case 64:
			text = '!';
			break;
		case 65:
			text = '†';
			break;
		case 66:
			text = '#';
			break;
		case 67:
			text = '$';
			break;
		case 68:
			text = '%';
			break;
		case 69:
			text = '&';
			break;

		case 70:
			text = '*';
			break;
		case 71:
			text = '‡';
			break;
		case 72:
			text = '¹';
			break;
		case 73:
			text = '+';
			break;
		case 74:
			text = '²';
			break;
		case 75:
			text = '³';
			break;
		case 76:
			text = '<';
			break;
		case 77:
			text = '=';
			break;
		case 78:
			text = '>';
			break;
		case 79:
			text = '?';
			break;

		case 80:
			text = '@';
			break;
		case 81:
			text = '•';
			break;
		case 82:
			text = '^';
			break;
		case 83:
			text = '_';
			break;
		case 84:
			text = '`';
			break;
		case 85:
			text = '{';
			break;
		case 86:
			text = '|';
			break;
		case 87:
			text = '}';
			break;
		case 88:
			text = '~';
			break;
		case 89:
			text = '€';
			break;

		case 90:
			text = 'á';
			break;
		case 91:
			text = 'é';
			break;
		case 92:
			text = 'í';
			break;
		case 93:
			text = 'ó';
			break;
		case 94:
			text = 'ú';
			break;
		case 95:
			text = 'Á';
			break;
		case 96:
			text = 'É';
			break;
		case 97:
			text = 'Í';
			break;
		case 98:
			text = 'Ó';
			break;
		case 99:
			text = 'Ú';
			break;

		case 100:
			text = 'à';
			break;
		case 101:
			text = 'è';
			break;
		case 102:
			text = 'ì';
			break;
		case 103:
			text = 'ò';
			break;
		case 104:
			text = 'ù';
			break;
		case 105:
			text = 'À';
			break;
		case 106:
			text = 'È';
			break;
		case 107:
			text = 'Ì';
			break;
		case 108:
			text = 'Ò';
			break;
		case 109:
			text = 'Ù';
			break;

		case 110:
			text = 'â';
			break;
		case 111:
			text = 'ê';
			break;
		case 112:
			text = 'î';
			break;
		case 113:
			text = 'ô';
			break;
		case 114:
			text = 'û';
			break;
		case 115:
			text = 'Â';
			break;
		case 116:
			text = 'Ê';
			break;
		case 117:
			text = 'Î';
			break;
		case 118:
			text = 'Ô';
			break;
		case 119:
			text = 'Û';
			break;

		case 120:
			text = 'ä';
			break;
		case 121:
			text = 'ë';
			break;
		case 122:
			text = 'ï';
			break;
		case 123:
			text = 'ö';
			break;
		case 124:
			text = 'ü';
			break;
		case 125:
			text = 'Ä';
			break;
		case 126:
			text = 'Ë';
			break;
		case 127:
			text = 'Ï';
			break;
		case 128:
			text = 'Ö';
			break;
		case 129:
			text = 'Ü';
			break;

		case 130:
			text = 'å';
			break;
		case 131:
			text = 'æ';
			break;
		case 132:
			text = 'ç';
			break;
		case 133:
			text = 'ð';
			break;
		case 134:
			text = 'ñ';
			break;
		case 135:
			text = 'Å';
			break;
		case 136:
			text = 'Æ';
			break;
		case 137:
			text = 'Ç';
			break;
		case 138:
			text = 'Ð';
			break;
		case 139:
			text = 'Ñ';
			break;

		case 140:
			text = 'š';
			break;
		case 141:
			text = '‹';
			break;
		case 142:
			text = 'œ';
			break;
		case 143:
			text = 'ž';
			break;
		case 144:
			text = 'ÿ';
			break;
		case 145:
			text = 'Š';
			break;
		case 146:
			text = '›';
			break;
		case 147:
			text = 'Œ';
			break;
		case 148:
			text = 'Ž';
			break;
		case 149:
			text = 'Ÿ';
			break;

		default:
			System.out.println("Number out of range");
			break;
		}

		return text;
	}

	/////////////////////////////////////////////////////////////////////////////

	static public int GetNumberFromCharacter(char character) {
		int number = -1;

		switch (character) {
		case INVALID_CHARACTER:
			number = -1;
			break;

		case '0':
			number = 0;
			break;
		case '1':
			number = 1;
			break;
		case '2':
			number = 2;
			break;
		case '3':
			number = 3;
			break;
		case '4':
			number = 4;
			break;
		case '5':
			number = 5;
			break;
		case '6':
			number = 6;
			break;
		case '7':
			number = 7;
			break;
		case '8':
			number = 8;
			break;
		case '9':
			number = 9;
			break;

		case 'a':
			number = 10;
			break;
		case 'b':
			number = 11;
			break;
		case 'c':
			number = 12;
			break;
		case 'd':
			number = 13;
			break;
		case 'e':
			number = 14;
			break;
		case 'f':
			number = 15;
			break;
		case 'g':
			number = 16;
			break;
		case 'h':
			number = 17;
			break;
		case 'i':
			number = 18;
			break;
		case 'j':
			number = 19;
			break;

		case 'k':
			number = 20;
			break;
		case 'l':
			number = 21;
			break;
		case 'm':
			number = 22;
			break;
		case 'n':
			number = 23;
			break;
		case 'o':
			number = 24;
			break;
		case 'p':
			number = 25;
			break;
		case 'q':
			number = 26;
			break;
		case 'r':
			number = 27;
			break;
		case 's':
			number = 28;
			break;
		case 't':
			number = 29;
			break;

		case 'u':
			number = 30;
			break;
		case 'v':
			number = 31;
			break;
		case 'w':
			number = 32;
			break;
		case 'x':
			number = 33;
			break;
		case 'y':
			number = 34;
			break;
		case 'z':
			number = 35;
			break;
		case 'A':
			number = 36;
			break;
		case 'B':
			number = 37;
			break;
		case 'C':
			number = 38;
			break;
		case 'D':
			number = 39;
			break;

		case 'E':
			number = 40;
			break;
		case 'F':
			number = 41;
			break;
		case 'G':
			number = 42;
			break;
		case 'H':
			number = 43;
			break;
		case 'I':
			number = 44;
			break;
		case 'J':
			number = 45;
			break;
		case 'K':
			number = 46;
			break;
		case 'L':
			number = 47;
			break;
		case 'M':
			number = 48;
			break;
		case 'N':
			number = 49;
			break;

		case 'O':
			number = 50;
			break;
		case 'P':
			number = 51;
			break;
		case 'Q':
			number = 52;
			break;
		case 'R':
			number = 53;
			break;
		case 'S':
			number = 54;
			break;
		case 'T':
			number = 55;
			break;
		case 'U':
			number = 56;
			break;
		case 'V':
			number = 57;
			break;
		case 'W':
			number = 58;
			break;
		case 'X':
			number = 59;
			break;

		case 'Y':
			number = 60;
			break;
		case 'Z':
			number = 61;
			break;
		case '[':
			number = 62;
			break;
		case ']':
			number = 63;
			break;
		case '!':
			number = 64;
			break;
		case '†':
			number = 65;
			break;
		case '#':
			number = 66;
			break;
		case '$':
			number = 67;
			break;
		case '%':
			number = 68;
			break;
		case '&':
			number = 69;
			break;

		case '*':
			number = 70;
			break;
		case '‡':
			number = 71;
			break;
		case '¹':
			number = 72;
			break;
		case '+':
			number = 73;
			break;
		case '²':
			number = 74;
			break;
		case '³':
			number = 75;
			break;
		case '<':
			number = 76;
			break;
		case '=':
			number = 77;
			break;
		case '>':
			number = 78;
			break;
		case '?':
			number = 79;
			break;

		case '@':
			number = 80;
			break;
		case '•':
			number = 81;
			break;
		case '^':
			number = 82;
			break;
		case '_':
			number = 83;
			break;
		case '`':
			number = 84;
			break;
		case '{':
			number = 85;
			break;
		case '|':
			number = 86;
			break;
		case '}':
			number = 87;
			break;
		case '~':
			number = 88;
			break;
		case '€':
			number = 89;
			break;

		case 'á':
			number = 90;
			break;
		case 'é':
			number = 91;
			break;
		case 'í':
			number = 92;
			break;
		case 'ó':
			number = 93;
			break;
		case 'ú':
			number = 94;
			break;
		case 'Á':
			number = 95;
			break;
		case 'É':
			number = 96;
			break;
		case 'Í':
			number = 97;
			break;
		case 'Ó':
			number = 98;
			break;
		case 'Ú':
			number = 99;
			break;

		case 'à':
			number = 100;
			break;
		case 'è':
			number = 101;
			break;
		case 'ì':
			number = 102;
			break;
		case 'ò':
			number = 103;
			break;
		case 'ù':
			number = 104;
			break;
		case 'À':
			number = 105;
			break;
		case 'È':
			number = 106;
			break;
		case 'Ì':
			number = 107;
			break;
		case 'Ò':
			number = 108;
			break;
		case 'Ù':
			number = 109;
			break;

		case 'â':
			number = 110;
			break;
		case 'ê':
			number = 111;
			break;
		case 'î':
			number = 112;
			break;
		case 'ô':
			number = 113;
			break;
		case 'û':
			number = 114;
			break;
		case 'Â':
			number = 115;
			break;
		case 'Ê':
			number = 116;
			break;
		case 'Î':
			number = 117;
			break;
		case 'Ô':
			number = 118;
			break;
		case 'Û':
			number = 119;
			break;

		case 'ä':
			number = 120;
			break;
		case 'ë':
			number = 121;
			break;
		case 'ï':
			number = 122;
			break;
		case 'ö':
			number = 123;
			break;
		case 'ü':
			number = 124;
			break;
		case 'Ä':
			number = 125;
			break;
		case 'Ë':
			number = 126;
			break;
		case 'Ï':
			number = 127;
			break;
		case 'Ö':
			number = 128;
			break;
		case 'Ü':
			number = 129;
			break;

		case 'å':
			number = 130;
			break;
		case 'æ':
			number = 131;
			break;
		case 'ç':
			number = 132;
			break;
		case 'ð':
			number = 133;
			break;
		case 'ñ':
			number = 134;
			break;
		case 'Å':
			number = 135;
			break;
		case 'Æ':
			number = 136;
			break;
		case 'Ç':
			number = 137;
			break;
		case 'Ð':
			number = 138;
			break;
		case 'Ñ':
			number = 139;
			break;

		case 'š':
			number = 140;
			break;
		case '‹':
			number = 141;
			break;
		case 'œ':
			number = 142;
			break;
		case 'ž':
			number = 143;
			break;
		case 'ÿ':
			number = 144;
			break;
		case 'Š':
			number = 145;
			break;
		case '›':
			number = 146;
			break;
		case 'Œ':
			number = 147;
			break;
		case 'Ž':
			number = 148;
			break;
		case 'Ÿ':
			number = 149;
			break;

		default:
			System.out.println("Number out of range");
			break;
		}

		return number;
	}

	/////////////////////////////////////////////////////////////////////////////

	public IndividualBossConfig(String configuration) {
		mDescription = "";
		mQuality = new Quality();
		mConfiguration = configuration;
		mHullsEnabledIndices = new ArrayList<Integer>();

		int hullSize = mConfiguration.length() / GetNumberOfRowsInEncoding();
		for (int i = 0; i < mConfiguration.length(); ++i) {
			if (i >= (5 * hullSize) && i < (6 * hullSize)) {
				// Enabled
				if (mConfiguration.charAt(i) == '1') {
					mHullsEnabledIndices.add(i % hullSize);
				}
			}
		}
		
		mTreeSequence = GetUpdatedTreeSequence();
	}

	/////////////////////////////////////////////////////////////////////////////

	public IndividualBossConfig(int hullSize, double oneProbability) {
		mDescription = "";
		mQuality = new Quality();
		// Random random = new Random( System.nanoTime() );
		mConfiguration = "";
		mHullsEnabledIndices = new ArrayList<Integer>();
		int size = hullSize * GetNumberOfRowsInEncoding();
		ArrayList<Integer> hullsEnabled = new ArrayList<Integer>();
		hullsEnabled.add(-1);

		for (int i = 0; i < size; ++i) {
			if (i >= 8 * hullSize) {
				// Transplant root hull index
				mConfiguration += INVALID_CHARACTER;
			} else if (i >= 7 * hullSize) {
				// Movement lead
				mConfiguration += random.nextDouble() <= oneProbability ? "1" : "0";
			} else if (i >= 6 * hullSize) {
				// Link parent
				if (IS_REPARATION_ENABLED) {
					mConfiguration += (mConfiguration.charAt(i - hullSize) == '0') ? INVALID_CHARACTER
							: GetCharacterFromNumber(hullsEnabled.get(random.nextInt(hullsEnabled.size())));
				} else {
					int linkParentHullIndex = random.nextInt(hullSize) - 1;
					mConfiguration += GetCharacterFromNumber(linkParentHullIndex);
					if (IsValid() && !mHullsEnabledIndices.contains(linkParentHullIndex)) {
						SetAsInvalid();
					}
				}
			} else if (i >= 5 * hullSize) {
				// Enabled
				if (random.nextDouble() <= oneProbability) {
					mConfiguration += "1";
					mHullsEnabledIndices.add(i % hullSize);
					hullsEnabled.add(i % hullSize);
				} else {
					mConfiguration += "0";
				}
			} else {
				mConfiguration += random.nextDouble() <= oneProbability ? "1" : "0";
			}
		}
		
		mTreeSequence = GetUpdatedTreeSequence();
	}

	/////////////////////////////////////////////////////////////////////////////

	public String GetConfiguration() {
		return mConfiguration;
	}

	/////////////////////////////////////////////////////////////////////////////

	public void SetConfiguration(String configuration) {
		mConfiguration = configuration;
	}

	/////////////////////////////////////////////////////////////////////////////

	public Quality GetQuality() {
		return mQuality;
	}

	/////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////

	public <T> T GetCustomValue(String valueName, Class<T> classReturned) {
		if (valueName == "TransplantRootHullIndex") {
			int transplantRootHullIndex = -1;
			int hullSize = mConfiguration.length() / GetNumberOfRowsInEncoding();
			for (int i = 0; transplantRootHullIndex == -1 && i < hullSize; ++i) {
				if ( /* Transplant root */mConfiguration.charAt(i + 8 * hullSize) != INVALID_CHARACTER &&
				/* Enabled */mConfiguration.charAt(i + 5 * hullSize) == '1') {
					transplantRootHullIndex = GetNumberFromCharacter(mConfiguration.charAt(i + 8 * hullSize));
				}
			}
			return classReturned.cast(transplantRootHullIndex);
		}
		else if (valueName == "Configuration") {
			return classReturned.cast(GetConfiguration());
		}
		else if (valueName == "SubjectiveQuality") {
			return classReturned.cast(mQuality.S_Overall);
		}
		else {
			return null;
		}
	}

	/////////////////////////////////////////////////////////////////////////////

	public String GetContentAsString() {
		return mConfiguration;
	}

	/////////////////////////////////////////////////////////////////////////////

	public String GetDescriptionString() {
		return mDescription;
	}

	/////////////////////////////////////////////////////////////////////////////

	public Individual CrossOver(Individual mate) {
		String newConfiguration = "";
		String mateConfiguration = ((IndividualBossConfig) mate).GetConfiguration();
		int minimumLength = Math.min(mConfiguration.length(), mateConfiguration.length());
		int maximumLength = Math.max(mConfiguration.length(), mateConfiguration.length());
		int crossCutIndex = random.nextInt(minimumLength);

		for (int i = 0; i < minimumLength; ++i) {
			newConfiguration += (i <= crossCutIndex) ? mConfiguration.charAt(i) : mateConfiguration.charAt(i);
		}
		for (int j = 0; j < (maximumLength - minimumLength); ++j) {
			newConfiguration += '0';
		}
		// System.out.println( ">>>crossCutIndex: " + crossCutIndex );
		// System.out.println( ">>>Configuration1: " + mConfiguration );
		// System.out.println( ">>>Configuration2: " + mateConfiguration );
		// System.out.println( ">>>New: " + newConfiguration );
		return new IndividualBossConfig(newConfiguration);
	}

	/////////////////////////////////////////////////////////////////////////////

	public void Mutate() {
		// Random random = new Random( System.nanoTime() );
		StringBuilder newConfiguration = new StringBuilder(mConfiguration);
		int hullSize = newConfiguration.length() / GetNumberOfRowsInEncoding();
		char potentialNewTransplantRootHullIndexCharacter = INVALID_CHARACTER;
		for (int i = 0; i < newConfiguration.length(); ++i) {

			// Not individually random
			if (i >= (8 * hullSize) && i < (9 * hullSize)) {
				// Transplant root change
				if (newConfiguration.charAt(i) != INVALID_CHARACTER
						&& potentialNewTransplantRootHullIndexCharacter != INVALID_CHARACTER) {
					newConfiguration.setCharAt(i, potentialNewTransplantRootHullIndexCharacter);
				}
			}

			// if ( random.nextInt( newConfiguration.length() ) == 0 ) {
			if (random.nextInt(hullSize) == 0) {
				if (i >= (8 * hullSize) && i < (9 * hullSize)) {
					// Transplant root change
					// Empty: This prevents random independent changes in this region
				} else if (i >= (6 * hullSize) && i < (7 * hullSize)) {
					// Link parent
					if (random.nextInt(mHullsEnabledIndices.size() + 1) == 0) {
						newConfiguration.setCharAt(i, INVALID_CHARACTER);
					} else {
						newConfiguration.setCharAt(i, GetCharacterFromNumber(
								mHullsEnabledIndices.get(random.nextInt(mHullsEnabledIndices.size()))));
					}
				} else if (i >= (5 * hullSize) && i < (6 * hullSize)) {
					// Enabled
					newConfiguration.setCharAt(i, (newConfiguration.charAt(i) == '0') ? '1' : '0');
					if (newConfiguration.charAt(i) == '1') {
						mHullsEnabledIndices.add(i % hullSize);
					} else {
						boolean b = mHullsEnabledIndices.remove(Integer.valueOf(i % hullSize));
					}
				} else {
					newConfiguration.setCharAt(i, (newConfiguration.charAt(i) == '0') ? '1' : '0');
				}
			}

			if ((i == (6 * hullSize - 1))) {
				// Enabled/disabled region done
				potentialNewTransplantRootHullIndexCharacter =
						// ( random.nextInt( newConfiguration.length() ) == 0 ) ?
						(random.nextInt(hullSize / 10) == 0)
								? GetCharacterFromNumber(
										mHullsEnabledIndices.get(random.nextInt(mHullsEnabledIndices.size())))
								: INVALID_CHARACTER;
			}
		}
		mConfiguration = newConfiguration.toString();
		
		mTreeSequence = GetUpdatedTreeSequence();
	}
	

	/////////////////////////////////////////////////////////////////////////////

	public double CalculateFitnessAndQualityIndicatorsAndReturnFitness() {
		// Size evaluation
		if ((mConfiguration.length() % GetNumberOfRowsInEncoding()) != 0) {
			System.out.println("Configuration Error. Size: " + mConfiguration.length());
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Hull size
		int numberOfHulls = mConfiguration.length() / GetNumberOfRowsInEncoding();
		// Hull structure
		int numberOfVitals = 0;
		int numberOfMelees = 0;
		int numberOfBullets = 0;
		int numberOfHomings = 0;
		int numberOfLasers = 0;
		ArrayList<HashSet<AddOnType>> hulls = new ArrayList<HashSet<AddOnType>>();
		boolean isValid = true;
		for (int g = 0; isValid && g < numberOfHulls; ++g) {
			HashSet<AddOnType> addOns = new HashSet<AddOnType>();
			if (mHullsEnabledIndices.isEmpty() || mHullsEnabledIndices.contains(g)) {
				boolean isWeaponFree = true;
				if (mConfiguration.charAt(g + numberOfHulls * 0) == '1') {
					addOns.add(AddOnType.ADDON_TYPE_VITAL_DEFAULT);
					++numberOfVitals;
				}
				if (mConfiguration.charAt(g + numberOfHulls * 1) == '1') {
					addOns.add(AddOnType.ADDON_TYPE_WEAPON_MELEE);
					++numberOfMelees;
					isWeaponFree = false;
				}
				if (mConfiguration.charAt(g + numberOfHulls * 2) == '1') {
					addOns.add(AddOnType.ADDON_TYPE_WEAPON_BULLET);
					++numberOfBullets;
					isWeaponFree = false;
				}
				if (mConfiguration.charAt(g + numberOfHulls * 3) == '1') {
					addOns.add(AddOnType.ADDON_TYPE_WEAPON_HOMING);
					++numberOfHomings;
					isWeaponFree = false;
				}
				if (mConfiguration.charAt(g + numberOfHulls * 4) == '1') {
					addOns.add(AddOnType.ADDON_TYPE_WEAPON_LASER);
					++numberOfLasers;
					isWeaponFree = false;
				}
				if (isWeaponFree && GetNumberOfRowsInEncoding() > 5
						&& mConfiguration.charAt(g + numberOfHulls * 7) == '1') {
					// Motion lead hull found with no weapons
					isValid = false;
				}
			}
			hulls.add(addOns);
		}
		if (!isValid) {
			SetAsInvalid();
		}

		// Distance from last vital hull to end
		int distanceFromEndToLastVitalHullIndex = -1;
		int numberOfDisabledHulls = 0;
		// System.out.println( ">>>mHullEnabledindices " +
		// mHullsEnabledIndices.toString() );
		for (int h = hulls.size() - 1; distanceFromEndToLastVitalHullIndex < 0 && h >= 0; --h) {
			if (mHullsEnabledIndices.isEmpty() || mHullsEnabledIndices.contains(h)) {
				if (hulls.get(h).contains(AddOnType.ADDON_TYPE_VITAL_DEFAULT)) {
					distanceFromEndToLastVitalHullIndex = hulls.size() - 1 - h - numberOfDisabledHulls;
				}
			} else {
				++numberOfDisabledHulls;
			}
		}
		// System.out.println( ">>numberOfDisabledHulls " + numberOfDisabledHulls );
		// System.out.println( ">>distanceFromEndToLastVitalHullIndex " +
		// distanceFromEndToLastVitalHullIndex );

		// Duel settings
		// Random random = new Random( System.nanoTime() );
		final int numberOfDuels = 30;
		final int optimalDuelSteps = 60 * (10 + 5 * (numberOfHulls - 32) / 32);
		final int maximumDuelSteps = 60 * (20 + 10 * (numberOfHulls - 32) / 32);
		final int hullVisitSteps = 20;
		final int bossInitialShields = numberOfVitals + 1;
		final int playerInitialShields = 5;
		final int recoveryStepDistance = 10;
		final float killerLifePercentageGap = 0.3f;
		final double hitProbabilityMelee = 0.014;// 0.014;
		final double hitProbabilityBullet = 0.014;// 0.014;
		final double hitProbabilityHoming = 0.014;// 0.014;
		final double hitProbabilityLaser = 0.014;// 0.014;
		final double hitProbabilityPlayer = 0.25;// 0.2;

		// Duel stat variables
		int numberOfDuelsWonBySomeone = 0;
		int numberOfPlayerWins = 0;

		// Duel stats
		double Q_Completion = 0.0;
		double Q_Duration = 0.0;
		double Q_Uncertainty = 0.0;
		double Q_KillerMoves = 0.0;
		double Q_Permanence = 0.0;
		double Q_LeadChange = 0.0;
		double Q_Overall = 0.0;
		double S_Subjective1 = 0.0;
		double S_Overall = 0.0;

		double F_PlayerWinPercentage = 0.0;
		double F_PlayerWinLifePercentage = 0.0;
		double F_PlayerWinLifeGapPercentage = 0.0;
		double F_Overall = 0.0;

		double X_PlayerLifePercentage = 0.0;
		double X_BossLifePercentage = 0.0;

		// Fitness references
		final double goalPlayerWinPercentage = 0.33;
		final double goalPlayerWinLifePercentage = 0.35;
		final double goalPlayerWinLifeGapPercentage = 0.35;

		for (int d = 0; d < numberOfDuels; ++d) {

			// Settings before each duel
			DuelResultType duelResult = DuelResultType.DUEL_RESULT_TYPE_DRAW;
			DuelResultType provisionalLead = DuelResultType.DUEL_RESULT_TYPE_DRAW;
			HashSet<Integer> vitalPointsDestroyed = new HashSet<Integer>();
			boolean isForwardRoute = true;
			boolean isDuelInProgress = true;
			int bossShields = bossInitialShields;
			int playerShields = playerInitialShields;
			int hullStepCount = 0;
			int duelStepCount = 0;
			int bossStepsToCritical = 0;
			int playerStepsToCritical = 0;
			int highlightMoveCount = 0;
			int killerMoveCount = 0;
			int recoveryMoveCount = 0;
			int lastHighlightStepCount = 0;
			int leadChangeCount = 0;
			int bossSuccessfulAttackCount = 0;
			int bossSuccessfulAttackWithoutResponseCount = 0;
			float bossLifePercentage = 1.0f;
			float playerLifePercentage = 1.0f;

			// Duel loop
			for (int hullIndex = 0; isDuelInProgress && duelStepCount < maximumDuelSteps; ++duelStepCount) {

				boolean isHullStudied = true;
				if (!mHullsEnabledIndices.isEmpty() && !mHullsEnabledIndices.contains(hullIndex)) {
					--duelStepCount;
					isHullStudied = false;
				}

				// Variables used in player attack, later
				boolean isVitalHullAlreadyDestroyed = false;
				boolean isBossDamaged = false;

				if (isHullStudied) {

					// Boss attack

					// Index range
					ArrayList<Integer> indicesInRange = new ArrayList<Integer>();
					int indexInRange = hullIndex - 1;
					int indicesAdded = 0;
					while (indicesAdded < 3 && indexInRange >= 0) {
						if (mHullsEnabledIndices.isEmpty() || mHullsEnabledIndices.contains(indexInRange)) {
							indicesInRange.add(0, indexInRange);
							++indicesAdded;
						}
						--indexInRange;
					}
					indicesInRange.add(hullIndex);
					indexInRange = hullIndex + 1;
					indicesAdded = 0;
					while (indicesAdded < 3 && indexInRange < hulls.size()) {
						if (mHullsEnabledIndices.isEmpty() || mHullsEnabledIndices.contains(indexInRange)) {
							indicesInRange.add(indexInRange);
							++indicesAdded;
						}
						++indexInRange;
					}
					// int firstHullIndex = Math.max( hullIndex - 3, 0 );
					// int lastHullIndex = Math.min( hullIndex + 3, hulls.size() - 1 );
					// System.out.println( ">>>Indices in range:" + indicesInRange.toString() );

					boolean isPlayerDamaged = false;
					// for ( int j = firstHullIndex; !isPlayerDamaged && j <= lastHullIndex; ++j ) {
					for (int j = 0; !isPlayerDamaged && j < indicesInRange.size(); ++j) {
						int studiedIndex = indicesInRange.get(j);
						// int distanceToCurrentHull = Math.abs( j - hullIndex );
						int distanceToCurrentHull = Math.abs(j - indicesInRange.indexOf(hullIndex));
						// System.out.println( ">>>Distance:" + distanceToCurrentHull );
						isPlayerDamaged = // Current hull
								(distanceToCurrentHull < 1
										&& hulls.get(studiedIndex).contains(AddOnType.ADDON_TYPE_WEAPON_MELEE)
										&& random.nextDouble() <= (hitProbabilityMelee
												* (4.0 - (double) distanceToCurrentHull) / 4.0))
										|| (distanceToCurrentHull < 2
												&& hulls.get(studiedIndex).contains(AddOnType.ADDON_TYPE_WEAPON_BULLET)
												&& random.nextDouble() <= (hitProbabilityBullet
														* (4.0 - (double) distanceToCurrentHull) / 4.0))
										|| (distanceToCurrentHull < 3
												&& hulls.get(studiedIndex).contains(AddOnType.ADDON_TYPE_WEAPON_HOMING)
												&& random.nextDouble() <= (hitProbabilityHoming
														* (4.0 - (double) distanceToCurrentHull) / 4.0))
										|| (distanceToCurrentHull < 4
												&& hulls.get(studiedIndex).contains(AddOnType.ADDON_TYPE_WEAPON_LASER)
												&& random.nextDouble() <= (hitProbabilityLaser
														* (4.0 - (double) distanceToCurrentHull) / 4.0));
						// if ( isPlayerDamaged ) { System.out.println( ">>>Damaged" ); }
					}
					// Damage, again, this time transplanted content
					int transplantPresenceWeight = 0;
					for (int t = 0; !isPlayerDamaged && t < hulls.size(); ++t) {
						if (mHullsEnabledIndices.contains(t) && GetNumberOfRowsInEncoding() > 8
								&& GetNumberFromCharacter(mConfiguration.charAt(8 * numberOfHulls + t)) == t) {
							// This hull is enabled and it is rooted here as a result of a transplant
							++transplantPresenceWeight;
							int distanceToCurrentHull = 0;
							isPlayerDamaged = // Current hull
									(distanceToCurrentHull < 1
											&& hulls.get(t).contains(AddOnType.ADDON_TYPE_WEAPON_MELEE)
											&& random.nextDouble() <= (hitProbabilityMelee
													* (4.0 - (double) distanceToCurrentHull) / 4.0))
											|| (distanceToCurrentHull < 2
													&& hulls.get(t).contains(AddOnType.ADDON_TYPE_WEAPON_BULLET)
													&& random.nextDouble() <= (hitProbabilityBullet
															* (4.0 - (double) distanceToCurrentHull) / 4.0))
											|| (distanceToCurrentHull < 3
													&& hulls.get(t).contains(AddOnType.ADDON_TYPE_WEAPON_HOMING)
													&& random.nextDouble() <= (hitProbabilityHoming
															* (4.0 - (double) distanceToCurrentHull) / 4.0))
											|| (distanceToCurrentHull < 4
													&& hulls.get(t).contains(AddOnType.ADDON_TYPE_WEAPON_LASER)
													&& random.nextDouble() <= (hitProbabilityLaser
															* (4.0 - (double) distanceToCurrentHull) / 4.0));
						}

					}

					// Player health control
					if (isDuelInProgress && isPlayerDamaged) {
						--playerShields;
						++highlightMoveCount;
						lastHighlightStepCount = duelStepCount;
						if (playerShields == 1) {
							playerStepsToCritical = duelStepCount + 1;
						} else if (playerShields <= 0) {
							isDuelInProgress = false;
							duelResult = DuelResultType.DUEL_RESULT_TYPE_BOSS_WINS;
						}
					}
					// Player attack
					isVitalHullAlreadyDestroyed = vitalPointsDestroyed.contains(hullIndex) && bossShields > 1;
					double updatedHitProbabilityPlayer = hitProbabilityPlayer
							* ((transplantPresenceWeight == 0) ? 1.0 : Math.pow(0.9, transplantPresenceWeight));
					isBossDamaged = hulls.get(hullIndex).contains(AddOnType.ADDON_TYPE_VITAL_DEFAULT)
							&& !isVitalHullAlreadyDestroyed && random.nextDouble() <= updatedHitProbabilityPlayer;
					// Boss health control
					if (isDuelInProgress && isBossDamaged) {
						vitalPointsDestroyed.add(hullIndex);
						--bossShields;
						++highlightMoveCount;
						lastHighlightStepCount = duelStepCount;
						if (bossShields == 1) {
							bossStepsToCritical = duelStepCount + 1;
						}
						if (bossShields <= 0) {
							isDuelInProgress = false;
							duelResult = DuelResultType.DUEL_RESULT_TYPE_PLAYER_WINS;
						}
					}
					// Last clash evaluation
					boolean isKillerMovePossible = Math
							.abs(playerLifePercentage - bossLifePercentage) < killerLifePercentageGap;
					boolean isRecoveryMovePossible = Math
							.abs(lastHighlightStepCount - duelStepCount) <= recoveryStepDistance
							&& Math.abs(playerLifePercentage - bossLifePercentage) >= killerLifePercentageGap;
					DuelResultType provisionalLeadOld = provisionalLead;
					playerLifePercentage = (float) playerShields / (float) playerInitialShields;
					bossLifePercentage = (float) bossShields / (float) bossInitialShields;
					provisionalLead = (playerLifePercentage == bossLifePercentage)
							? DuelResultType.DUEL_RESULT_TYPE_DRAW
							: ((playerLifePercentage > bossLifePercentage) ? DuelResultType.DUEL_RESULT_TYPE_PLAYER_WINS
									: DuelResultType.DUEL_RESULT_TYPE_BOSS_WINS);
					if (provisionalLead != provisionalLeadOld) {
						++leadChangeCount;
					}
					if (isKillerMovePossible
							&& Math.abs(playerLifePercentage - bossLifePercentage) >= killerLifePercentageGap) {
						++killerMoveCount;
					}
					if (isRecoveryMovePossible
							&& Math.abs(playerLifePercentage - bossLifePercentage) < killerLifePercentageGap) {
						++recoveryMoveCount;
					}
					if ( isPlayerDamaged ) {
						++bossSuccessfulAttackCount;
						if ( !isBossDamaged ) {
							++bossSuccessfulAttackWithoutResponseCount;
						}
					}

				}

				// Route control
				++hullStepCount;
				if (!isHullStudied || isVitalHullAlreadyDestroyed || isBossDamaged
						|| hullStepCount >= (hulls.get(hullIndex).contains(AddOnType.ADDON_TYPE_VITAL_DEFAULT)
								? (2 * hullVisitSteps)
								: hullVisitSteps)) {
					hullStepCount = 0;
					// Direction control
					if (isForwardRoute && (hullIndex + 1) >= hulls.size()) {
						if (distanceFromEndToLastVitalHullIndex >= 10) {
							hullIndex = -1;
						} else {
							isForwardRoute = false;
						}
					} else if (!isForwardRoute && (hullIndex - 1) < 0) {
						isForwardRoute = true;
					}
					hullIndex += isForwardRoute ? 1 : -1;
					// Clamp: Example: no hulls enabled
					hullIndex = Math.max(0, Math.min(hulls.size() - 1, hullIndex));
				}
			}
			// End of duel loop

			// Duel finished
			if (duelResult != DuelResultType.DUEL_RESULT_TYPE_DRAW) {
				++numberOfDuelsWonBySomeone;
				if (duelResult == DuelResultType.DUEL_RESULT_TYPE_PLAYER_WINS) {
					++numberOfPlayerWins;
				}
			}

			// Duel quality data
			// Q_Completion;
			Q_Duration += (double) Math.abs(optimalDuelSteps - duelStepCount) / (double) optimalDuelSteps;
			Q_Uncertainty += (double) (duelStepCount - Math.min(playerStepsToCritical, bossStepsToCritical))
					/ (double) duelStepCount;
			Q_KillerMoves += (double) killerMoveCount / (double) highlightMoveCount;
			Q_Permanence += (double) recoveryMoveCount / (double) (highlightMoveCount + killerMoveCount);
			Q_LeadChange += (double) leadChangeCount / (double) (highlightMoveCount + killerMoveCount);
			S_Subjective1 += (double) Math.abs(optimalDuelSteps - duelStepCount) / (double) optimalDuelSteps;// Old version: Incontestability: (double) bossSuccessfulAttackWithoutResponseCount / (double) bossSuccessfulAttackCount;

			// Duel fitness data
			// F_PlayerWinPercentage;
			if (duelResult == DuelResultType.DUEL_RESULT_TYPE_PLAYER_WINS) {
				F_PlayerWinLifePercentage += Math.abs(goalPlayerWinLifePercentage - (double) playerLifePercentage)
						/ goalPlayerWinLifePercentage;
				F_PlayerWinLifeGapPercentage += Math.abs(
						goalPlayerWinLifeGapPercentage - (double) Math.abs(playerLifePercentage - bossLifePercentage))
						/ goalPlayerWinLifeGapPercentage;
				X_PlayerLifePercentage += (double) playerLifePercentage;
				X_BossLifePercentage += (double) bossLifePercentage;
				// System.out.println( "----Duel Data----" );
				// System.out.println( "F_PlayerWinLifePercentage " + Math.abs(
				// goalPlayerWinLifePercentage - ( double ) playerLifePercentage ) /
				// goalPlayerWinLifePercentage );
				// System.out.println( "playerLifePercentage " + playerLifePercentage );
				// System.out.println( "goalPlayerWinLifePercentage " +
				// goalPlayerWinLifePercentage );
				// System.out.println( "goalPlayerWinLifePercentage - ( double )
				// playerLifePercentage " + (goalPlayerWinLifePercentage - ( double )
				// playerLifePercentage) );
				// System.out.println( "playerLifePercentage - bossLifePercentage: " +
				// (playerLifePercentage - bossLifePercentage) );
				// System.exit( 0 );
			}

			//
//      System.out.println( "----Duel Finished----" );
//      System.out.println( "playerShields: " + playerShields );
//      System.out.println( "bossShields: " + bossShields );
//      System.out.println( "goalPlayerWinLifePercentage: " + goalPlayerWinLifePercentage );
//      System.out.println( "playerLifePercentage: " + playerLifePercentage );
//      System.out.println( "F_PlayerWinLifePercentage: " + F_PlayerWinLifePercentage );
//      System.exit( 0 );

		}
		// Every duel finished

		// Global duel quality stats
		int qualityStatCount = 0;
		Q_Completion = (double) numberOfDuelsWonBySomeone / (double) numberOfDuels;
		++qualityStatCount;
		Q_Duration = Math.max(0.0, 1.0 - Q_Duration / (double) numberOfDuels);
		++qualityStatCount;
		Q_Uncertainty = Math.max(0.0, 1.0 - Q_Uncertainty / (double) numberOfDuels);
		++qualityStatCount;
		Q_KillerMoves = Math.max(0.0, 1.0 - Q_KillerMoves / (double) numberOfDuels);
		++qualityStatCount;
		Q_Permanence = Math.max(0.0, 1.0 - Q_Permanence / (double) numberOfDuels);
		++qualityStatCount;
		Q_LeadChange = Q_LeadChange / (double) numberOfDuels;
		++qualityStatCount;
		Q_Overall = (Q_Completion + Q_Duration + Q_Uncertainty + Q_KillerMoves + Q_Permanence + Q_LeadChange)
				/ (double) qualityStatCount;
		int subjectiveStatCount = 0;
		S_Subjective1 = Math.max(0.0, 1.0 - S_Subjective1 / (double) numberOfDuels);// Old version: Incontestability: S_Subjective1 / (double) numberOfDuels;
		++subjectiveStatCount;
		S_Overall = (S_Subjective1) / (double) subjectiveStatCount;
		//
//    System.out.println( "----Quality----" );
//    System.out.println( "Q_Completion: " + Q_Completion );
//    System.out.println( "Q_Duration: " + Q_Duration );
//    System.out.println( "Q_Uncertainty: " + Q_Uncertainty );
//    System.out.println( "Q_KillerMoves: " + Q_KillerMoves );
//    System.out.println( "Q_Permanence: " + Q_Permanence );
//    System.out.println( "Q_LeadChange: " + Q_LeadChange );
//    System.out.println( "Q_Overall: " + Q_Overall );
//	  System.out.println( "S_Subjective1: " + S_Subjective1 );
//	  System.out.println( "S_Overall: " + S_Overall );

		// Fitness stats
		int fitnessStatCount = 0;
		F_PlayerWinPercentage = Math.max(0.0, 1.0
				- ((double) Math.abs(goalPlayerWinPercentage - ((double) numberOfPlayerWins / (double) numberOfDuels)))
						/ goalPlayerWinPercentage);
		++fitnessStatCount;
		F_PlayerWinLifePercentage = (numberOfPlayerWins > 0)
				? Math.max(0.0, 1.0 - F_PlayerWinLifePercentage / (double) numberOfPlayerWins)
				: 0.0;
		++fitnessStatCount;
		F_PlayerWinLifeGapPercentage = (numberOfPlayerWins > 0)
				? Math.max(0.0, 1.0 - F_PlayerWinLifeGapPercentage / (double) numberOfPlayerWins)
				: 0.0;
		++fitnessStatCount;
		F_Overall = (F_PlayerWinPercentage + F_PlayerWinLifePercentage + F_PlayerWinLifeGapPercentage)
				/ (double) fitnessStatCount;
		X_PlayerLifePercentage = (numberOfPlayerWins > 0) ? (X_PlayerLifePercentage / (double) numberOfPlayerWins)
				: 0.0;
		X_BossLifePercentage = (numberOfPlayerWins > 0) ? (X_BossLifePercentage / (double) numberOfPlayerWins) : 0.0;

		//
//    System.out.println( "----Fitness----" );
//    System.out.println( "numberOfDuels: " + numberOfDuels );
//    System.out.println( "numberOfPlayerWins: " + numberOfPlayerWins );
//    System.out.println( "F_PlayerWinPercentage: " + F_PlayerWinPercentage );
//    System.out.println( "F_PlayerWinLifePercentage: " + F_PlayerWinLifePercentage );
//    System.out.println( "F_PlayerWinLifeGapPercentage: " + F_PlayerWinLifeGapPercentage );
//    System.out.println( "F_Overall: " + F_Overall );
//    System.out.println( "X_PlayerLifePercentage: " + X_PlayerLifePercentage );
//    System.out.println( "X_BossLifePercentage: " + X_BossLifePercentage );

		mDescription = "";
		mDescription += "----Content----\n";
		mDescription += GetContentAsString() + "\n";
		mDescription += "----Fitness----\n";
		mDescription += "numberOfDuels: " + numberOfDuels + "\n";
		mDescription += "numberOfPlayerWins: " + numberOfPlayerWins + "\n";
		mDescription += "F_PlayerWinPercentage: " + F_PlayerWinPercentage + "\n";
		mDescription += "F_PlayerWinLifePercentage: " + F_PlayerWinLifePercentage + "\n";
		mDescription += "F_PlayerWinLifeGapPercentage: " + F_PlayerWinLifeGapPercentage + "\n";
		mDescription += "F_Overall: " + F_Overall + "\n";
		mDescription += "----Quality----\n";
		mDescription += "Q_Completion: " + Q_Completion + "\n";
		mDescription += "Q_Duration: " + Q_Duration + "\n";
		mDescription += "Q_Uncertainty: " + Q_Uncertainty + "\n";
		mDescription += "Q_KillerMoves: " + Q_KillerMoves + "\n";
		mDescription += "Q_Permanence: " + Q_Permanence + "\n";
		mDescription += "Q_LeadChange: " + Q_LeadChange + "\n";
		mDescription += "Q_Overall: " + Q_Overall + "\n";
		mDescription += "S_Subjective1: " + S_Subjective1 + "\n";
		mDescription += "S_Overall: " + S_Overall + "\n";

		//
		//
		// System.out.println( mDescription );
		// System.exit( 0 );
		//
		//

		mQuality.Q_Completion = IsValid() ? Q_Completion : 0.0;
		mQuality.Q_Duration = IsValid() ? Q_Duration : 0.0;
		mQuality.Q_Uncertainty = IsValid() ? Q_Uncertainty : 0.0;
		mQuality.Q_KillerMoves = IsValid() ? Q_KillerMoves : 0.0;
		mQuality.Q_Permanence = IsValid() ? Q_Permanence : 0.0;
		mQuality.Q_LeadChange = IsValid() ? Q_LeadChange : 0.0;
		mQuality.Q_Overall = IsValid() ? Q_Overall : 0.0;
		mQuality.S_Subjective1 = IsValid() ? S_Subjective1 : 0.0;
		mQuality.S_Overall = IsValid() ? S_Overall : 0.0;

		return IsValid() ? F_Overall : 0.0;
	}

	/////////////////////////////////////////////////////////////////////////////

	public double CalculateFitnessAlternative() {
		// Data retrieval
		int numberOfHulls = mConfiguration.length() / GetNumberOfRowsInEncoding();
		int numberOfVitals = 0;
		int numberOfMelees = 0;
		int numberOfBullets = 0;
		int numberOfHomings = 0;
		int numberOfLasers = 0;
		int numberOfArmedHulls = 0;
		int numberOfFullArmedHulls = 0;
		HashSet<AddOnType> weaponTypes = new HashSet<AddOnType>();
		for (int h = 0; h < numberOfHulls; ++h) {
			if (mHullsEnabledIndices.contains(h)) {
				boolean isWeaponFree = true;
				int hullWeaponCount = 0;
				if (mConfiguration.charAt(h + numberOfHulls * 0) == '1') {
					++numberOfVitals;
				}
				if (mConfiguration.charAt(h + numberOfHulls * 1) == '1') {
					weaponTypes.add(AddOnType.ADDON_TYPE_WEAPON_MELEE);
					++numberOfMelees;
					++hullWeaponCount;
					isWeaponFree = false;
				}
				if (mConfiguration.charAt(h + numberOfHulls * 2) == '1') {
					weaponTypes.add(AddOnType.ADDON_TYPE_WEAPON_BULLET);
					++numberOfBullets;
					++hullWeaponCount;
					isWeaponFree = false;
				}
				if (mConfiguration.charAt(h + numberOfHulls * 3) == '1') {
					weaponTypes.add(AddOnType.ADDON_TYPE_WEAPON_HOMING);
					++numberOfHomings;
					++hullWeaponCount;
					isWeaponFree = false;
				}
				if (mConfiguration.charAt(h + numberOfHulls * 4) == '1') {
					weaponTypes.add(AddOnType.ADDON_TYPE_WEAPON_LASER);
					++numberOfLasers;
					++hullWeaponCount;
					isWeaponFree = false;
				}
				numberOfArmedHulls += isWeaponFree ? 0 : 1;
				numberOfFullArmedHulls += (hullWeaponCount < 4) ? 0 : 1;
			}
		}
		int numberOfWeapons = numberOfMelees + numberOfBullets + numberOfHomings + numberOfLasers;
		int numberOfUnarmedHulls = numberOfHulls - numberOfArmedHulls;

		// Tests
		ArrayList<Double> testCompilation = new ArrayList<Double>();
		double testValue = 0.0;

		// 001: Test: Weapon density
		testValue = (numberOfWeapons > 0 && (float) numberOfWeapons < (2.0 * (float) numberOfHulls)) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 002: Test: Weapon diversity
		testValue = weaponTypes.size() > 0 ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 003: Test: Vital hull presence
		testValue = numberOfVitals > 4 ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 004: Test: Unarmed hull percentage minimum value
		testValue = ((float) (numberOfHulls - numberOfArmedHulls) > (0.1 * (float) numberOfHulls)) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 005: Test: Full armed hull count limit
		testValue = numberOfFullArmedHulls < 2 ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 006: Test: Melee weapon percentage limit
		testValue = ((float) numberOfMelees < (0.5 * (float) numberOfHulls)) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 007: Test: Projectile-based weapon presence
		testValue = (numberOfBullets + numberOfHomings) > 0 ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 008: Test: Bullet weapon count limit
		testValue = numberOfBullets < 10 ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 009: Test: Homing weapon count limit
		testValue = numberOfHomings < 10 ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 010: Test: Laser weapon count limit
		testValue = numberOfLasers < 10 ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// -- 20230529

		// 011: Test: Maximum number of weapons
		testValue = (numberOfWeapons <= 20) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 012: Test: Minimum number of weapons
		testValue = (numberOfWeapons >= 5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 013: Test: Number of weapon types matches the number of weapons
		testValue = (weaponTypes.size() == numberOfWeapons) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 014: Test: Presence of specific weapon type (e.g., "Melee")
		testValue = (weaponTypes.contains(AddOnType.ADDON_TYPE_WEAPON_MELEE)) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 015: Test: Presence of specific weapon type (e.g., "Bullet")
		testValue = (weaponTypes.contains(AddOnType.ADDON_TYPE_WEAPON_BULLET)) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 016: Test: Presence of specific weapon type (e.g., "Homing")
		testValue = (weaponTypes.contains(AddOnType.ADDON_TYPE_WEAPON_HOMING)) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 017: Test: Presence of specific weapon type (e.g., "Laser")
		testValue = (weaponTypes.contains(AddOnType.ADDON_TYPE_WEAPON_LASER)) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 018: Test: Maximum number of vitals
		testValue = (numberOfVitals <= 10) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 019: Test: Minimum number of vitals
		testValue = (numberOfVitals >= 5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 020: Test: Percentage of unarmed hulls
		double unarmedPercentage = (double) (numberOfHulls - numberOfArmedHulls) / numberOfHulls;
		testValue = (unarmedPercentage >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 021: Test: Percentage of full armed hulls
		double fullArmedPercentage = (double) numberOfFullArmedHulls / numberOfHulls;
		testValue = (fullArmedPercentage <= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 022: Test: Maximum number of melee weapons
		testValue = (numberOfMelees <= 5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 023: Test: Presence of specific weapon category (e.g., Projectile)
		testValue = ((numberOfBullets + numberOfHomings) > 0) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 024: Test: Maximum number of bullet weapons
		testValue = (numberOfBullets <= 15) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 025: Test: Maximum number of homing weapons
		testValue = (numberOfHomings <= 10) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 026: Test: Maximum number of laser weapons
		testValue = (numberOfLasers <= 5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 027: Test: Number of hulls is even
		testValue = (numberOfHulls % 2 == 0) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 028: Test: Number of vitals is odd
		testValue = (numberOfVitals % 2 != 0) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 029: Test: Percentage of armed hulls
		double armedPercentage = (double) numberOfArmedHulls / numberOfHulls;
		testValue = (armedPercentage <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 030: Test: Minimum number of bullet weapons
		testValue = (numberOfBullets >= 5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 031: Test: Minimum number of homing weapons
		testValue = (numberOfHomings >= 5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 032: Test: Minimum number of laser weapons
		testValue = (numberOfLasers >= 5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 033: Test: Maximum number of weapon types
		testValue = (weaponTypes.size() <= 15) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 034: Test: Minimum number of weapon types
		testValue = (weaponTypes.size() >= 3) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 035: Test: Maximum number of melee weapons compared to hulls
		double meleePercentage = (double) numberOfMelees / numberOfHulls;
		testValue = (meleePercentage <= 0.3) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 036: Test: Maximum number of vitals compared to hulls
		double vitalsPercentage = (double) numberOfVitals / numberOfHulls;
		testValue = (vitalsPercentage <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 037: Test: Maximum number of weapon types compared to hulls
		double weaponTypesPercentage = (double) weaponTypes.size() / numberOfHulls;
		testValue = (weaponTypesPercentage <= 0.4) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 038: Test: Maximum number of vital hulls
		testValue = (numberOfVitals <= 5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 039: Test: Minimum number of vital hulls
		testValue = (numberOfVitals >= 3) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 040: Test: Maximum number of full armed hulls
		testValue = (numberOfFullArmedHulls <= 3) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 041: Test: Minimum number of full armed hulls
		testValue = (numberOfFullArmedHulls >= 1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 042: Test: Maximum number of bullets compared to weapons
		double bulletPercentage = (double) numberOfBullets / numberOfWeapons;
		testValue = (bulletPercentage <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 043: Test: Maximum number of homings compared to weapons
		double homingPercentage = (double) numberOfHomings / numberOfWeapons;
		testValue = (homingPercentage <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 044: Test: Maximum number of lasers compared to weapons
		double laserPercentage = (double) numberOfLasers / numberOfWeapons;
		testValue = (laserPercentage <= 0.3) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 045: Test: Maximum number of armed hulls compared to hulls
		double armedHullsPercentage = (double) numberOfArmedHulls / numberOfHulls;
		testValue = (armedHullsPercentage <= 0.6) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 046: Test: Minimum number of armed hulls compared to hulls
		testValue = (armedHullsPercentage >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 047: Test: Maximum number of unarmed hulls compared to hulls
		double unarmedHullsPercentage = (double) (numberOfHulls - numberOfArmedHulls) / numberOfHulls;
		testValue = (unarmedHullsPercentage <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 048: Test: Minimum number of unarmed hulls compared to hulls
		testValue = (unarmedHullsPercentage >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 049: Test: Maximum number of vitals compared to armed hulls
		double vitalsArmedPercentage = (double) numberOfVitals / numberOfArmedHulls;
		testValue = (vitalsArmedPercentage <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 050: Test: Minimum number of vitals compared to armed hulls
		testValue = (vitalsArmedPercentage >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 051: Test: Maximum number of vitals compared to unarmed hulls
		double vitalsUnarmedPercentage = (double) numberOfVitals / (numberOfHulls - numberOfArmedHulls);
		testValue = (vitalsUnarmedPercentage <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 052: Test: Minimum number of vitals compared to unarmed hulls
		testValue = (vitalsUnarmedPercentage >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 053: Test: Maximum number of vitals compared to weapon types
		double vitalsWeaponTypesPercentage = (double) numberOfVitals / weaponTypes.size();
		testValue = (vitalsWeaponTypesPercentage <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 054: Test: Minimum number of vitals compared to weapon types
		testValue = (vitalsWeaponTypesPercentage >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 055: Test: Maximum number of vitals compared to full armed hulls
		double vitalsFullArmedPercentage = (double) numberOfVitals / numberOfFullArmedHulls;
		testValue = (vitalsFullArmedPercentage <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 056: Test: Minimum number of vitals compared to full armed hulls
		testValue = (vitalsFullArmedPercentage >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 057: Test: Maximum number of weapon types compared to vitals
		double weaponTypesVitalsPercentage = (double) weaponTypes.size() / numberOfVitals;
		testValue = (weaponTypesVitalsPercentage <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 058: Test: Minimum number of weapon types compared to vitals
		testValue = (weaponTypesVitalsPercentage >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 059: Test: Maximum number of weapon types compared to full armed hulls
		double weaponTypesFullArmedPercentage = (double) weaponTypes.size() / numberOfFullArmedHulls;
		testValue = (weaponTypesFullArmedPercentage <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 060: Test: Minimum number of weapon types compared to full armed hulls
		testValue = (weaponTypesFullArmedPercentage >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 063: Test: Maximum number of vitals compared to weapon types and hulls
		double vitalsWeaponTypesHullsPercentage = (double) numberOfVitals / (weaponTypes.size() + numberOfHulls);
		testValue = (vitalsWeaponTypesHullsPercentage <= 0.4) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 064: Test: Minimum number of vitals compared to weapon types and hulls
		testValue = (vitalsWeaponTypesHullsPercentage >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 065: Test: Maximum number of weapon types compared to vitals and hulls
		double weaponTypesVitalsHullsPercentage = (double) weaponTypes.size() / (numberOfVitals + numberOfHulls);
		testValue = (weaponTypesVitalsHullsPercentage <= 0.4) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 066: Test: Minimum number of weapon types compared to vitals and hulls
		testValue = (weaponTypesVitalsHullsPercentage >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 067: Test: Maximum number of vitals compared to weapon types and full armed
		// hulls
		double vitalsWeaponTypesFullArmedPercentage = (double) numberOfVitals
				/ (weaponTypes.size() + numberOfFullArmedHulls);
		testValue = (vitalsWeaponTypesFullArmedPercentage <= 0.4) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 068: Test: Minimum number of vitals compared to weapon types and full armed
		// hulls
		testValue = (vitalsWeaponTypesFullArmedPercentage >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 069: Test: Maximum number of weapon types compared to vitals and full armed
		// hulls
		double weaponTypesVitalsFullArmedPercentage = (double) weaponTypes.size()
				/ (numberOfVitals + numberOfFullArmedHulls);
		testValue = (weaponTypesVitalsFullArmedPercentage <= 0.4) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 070: Test: Minimum number of weapon types compared to vitals and full armed
		// hulls
		testValue = (weaponTypesVitalsFullArmedPercentage >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 071: Test: Maximum number of bullets compared to the total number of weapons
		// and hulls
		double bulletPercentageTotal = (double) numberOfBullets / (numberOfWeapons + numberOfHulls);
		testValue = (bulletPercentageTotal <= 0.3) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 072: Test: Minimum number of bullets compared to the total number of weapons
		// and hulls
		testValue = (bulletPercentageTotal >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 073: Test: Maximum number of homings compared to the total number of weapons
		// and hulls
		double homingPercentageTotal = (double) numberOfHomings / (numberOfWeapons + numberOfHulls);
		testValue = (homingPercentageTotal <= 0.3) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 074: Test: Minimum number of homings compared to the total number of weapons
		// and hulls
		testValue = (homingPercentageTotal >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 075: Test: Maximum number of lasers compared to the total number of weapons
		// and hulls
		double laserPercentageTotal = (double) numberOfLasers / (numberOfWeapons + numberOfHulls);
		testValue = (laserPercentageTotal <= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 076: Test: Minimum number of lasers compared to the total number of weapons
		// and hulls
		testValue = (laserPercentageTotal >= 0.05) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 077: Test: Maximum number of vitals compared to the total number of armed
		// hulls and unarmed hulls
		double vitalsPercentageTotal = (double) numberOfVitals
				/ (numberOfArmedHulls + (numberOfHulls - numberOfArmedHulls));
		testValue = (vitalsPercentageTotal <= 0.6) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 078: Test: Minimum number of vitals compared to the total number of armed
		// hulls and unarmed hulls
		testValue = (vitalsPercentageTotal >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 079: Test: Maximum number of armed hulls compared to the total number of
		// hulls and vitals
		double armedHullsPercentageTotal = (double) numberOfArmedHulls / (numberOfHulls + numberOfVitals);
		testValue = (armedHullsPercentageTotal <= 0.6) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 080: Test: Minimum number of armed hulls compared to the total number of
		// hulls and vitals
		testValue = (armedHullsPercentageTotal >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 081: Test: Maximum number of melees compared to the total number of weapons
		// and hulls
		double meleePercentageTotal = (double) numberOfMelees / (numberOfWeapons + numberOfHulls);
		testValue = (meleePercentageTotal <= 0.4) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 082: Test: Minimum number of melees compared to the total number of weapons
		// and hulls
		testValue = (meleePercentageTotal >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 083: Test: Maximum number of projectiles (bullets + homings) compared to the
		// total number of weapons and hulls
		double projectilesPercentageTotal = ((double) numberOfBullets + numberOfHomings)
				/ (numberOfWeapons + numberOfHulls);
		testValue = (projectilesPercentageTotal <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 084: Test: Minimum number of projectiles (bullets + homings) compared to the
		// total number of weapons and hulls
		testValue = (projectilesPercentageTotal >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 085: Test: Maximum number of weapon types compared to the total number of
		// weapons and hulls
		double weaponTypesPercentageTotal = (double) weaponTypes.size() / (numberOfWeapons + numberOfHulls);
		testValue = (weaponTypesPercentageTotal <= 0.4) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 086: Test: Minimum number of weapon types compared to the total number of
		// weapons and hulls
		testValue = (weaponTypesPercentageTotal >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 087: Test: Maximum number of hulls compared to the total number of weapons
		// and vitals
		double hullsPercentageTotal = (double) numberOfHulls / (numberOfWeapons + numberOfVitals);
		testValue = (hullsPercentageTotal <= 0.6) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 088: Test: Minimum number of hulls compared to the total number of weapons
		// and vitals
		testValue = (hullsPercentageTotal >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 089: Test: Maximum number of full armed hulls compared to the total number of
		// armed hulls
		fullArmedPercentage = (double) numberOfFullArmedHulls / numberOfArmedHulls;
		testValue = (fullArmedPercentage <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 090: Test: Minimum number of full armed hulls compared to the total number of
		// armed hulls
		testValue = (fullArmedPercentage >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 091: Test: Maximum number of weapons compared to the total number of hulls
		double weaponsPercentageHulls = (double) numberOfWeapons / numberOfHulls;
		testValue = (weaponsPercentageHulls <= 2.0) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 092: Test: Minimum number of weapons compared to the total number of hulls
		testValue = (weaponsPercentageHulls >= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 093: Test: Maximum number of armed hulls compared to the total number of
		// hulls
		double armedHullsPercentageHulls = (double) numberOfArmedHulls / numberOfHulls;
		testValue = (armedHullsPercentageHulls <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 094: Test: Minimum number of armed hulls compared to the total number of
		// hulls
		testValue = (armedHullsPercentageHulls >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 095: Test: Maximum number of unarmed hulls compared to the total number of
		// hulls
		double unarmedHullsPercentageHulls = 1.0 - armedHullsPercentageHulls;
		testValue = (unarmedHullsPercentageHulls <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 096: Test: Minimum number of unarmed hulls compared to the total number of
		// hulls
		testValue = (unarmedHullsPercentageHulls >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 097: Test: Maximum number of vitals compared to the total number of hulls
		double vitalsPercentageHulls = (double) numberOfVitals / numberOfHulls;
		testValue = (vitalsPercentageHulls <= 0.6) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 098: Test: Minimum number of vitals compared to the total number of hulls
		testValue = (vitalsPercentageHulls >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 099: Test: Maximum number of bullets compared to the total number of weapons
		double bulletsPercentageWeapons = (double) numberOfBullets / numberOfWeapons;
		testValue = (bulletsPercentageWeapons <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 100: Test: Minimum number of bullets compared to the total number of weapons
		testValue = (bulletsPercentageWeapons >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 101: Test: Maximum number of homings compared to the total number of weapons
		double homingsPercentageWeapons = (double) numberOfHomings / numberOfWeapons;
		testValue = (homingsPercentageWeapons <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 102: Test: Minimum number of homings compared to the total number of weapons
		testValue = (homingsPercentageWeapons >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 103: Test: Maximum number of lasers compared to the total number of weapons
		double lasersPercentageWeapons = (double) numberOfLasers / numberOfWeapons;
		testValue = (lasersPercentageWeapons <= 0.3) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 104: Test: Minimum number of lasers compared to the total number of weapons
		testValue = (lasersPercentageWeapons >= 0.05) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 105: Test: Maximum number of weapon types compared to the total number of
		// weapons
		double weaponTypesPercentageWeapons = (double) weaponTypes.size() / numberOfWeapons;
		testValue = (weaponTypesPercentageWeapons <= 0.4) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 106: Test: Minimum number of weapon types compared to the total number of
		// weapons
		testValue = (weaponTypesPercentageWeapons >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 107: Test: Maximum number of melees compared to the total number of weapons
		double meleesPercentageWeapons = (double) numberOfMelees / numberOfWeapons;
		testValue = (meleesPercentageWeapons <= 0.4) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 108: Test: Minimum number of melees compared to the total number of weapons
		testValue = (meleesPercentageWeapons >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 109: Test: Maximum number of projectiles (bullets + homings) compared to the
		// total number of weapons
		double projectilesPercentageWeapons = ((double) numberOfBullets + numberOfHomings) / numberOfWeapons;
		testValue = (projectilesPercentageWeapons <= 0.6) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 110: Test: Minimum number of projectiles (bullets + homings) compared to the
		// total number of weapons
		testValue = (projectilesPercentageWeapons >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 111: Test: Maximum number of bullets compared to the total number of
		// projectiles (bullets + homings)
		double bulletsPercentageProjectiles = (double) numberOfBullets / (numberOfBullets + numberOfHomings);
		testValue = (bulletsPercentageProjectiles <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 112: Test: Minimum number of bullets compared to the total number of
		// projectiles (bullets + homings)
		testValue = (bulletsPercentageProjectiles >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 113: Test: Maximum number of homings compared to the total number of
		// projectiles (bullets + homings)
		double homingsPercentageProjectiles = (double) numberOfHomings / (numberOfBullets + numberOfHomings);
		testValue = (homingsPercentageProjectiles <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 114: Test: Minimum number of homings compared to the total number of
		// projectiles (bullets + homings)
		testValue = (homingsPercentageProjectiles >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 115: Test: Maximum number of lasers compared to the total number of
		// projectiles (bullets + homings)
		double lasersPercentageProjectiles = (double) numberOfLasers / (numberOfBullets + numberOfHomings);
		testValue = (lasersPercentageProjectiles <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 116: Test: Minimum number of lasers compared to the total number of
		// projectiles (bullets + homings)
		testValue = (lasersPercentageProjectiles >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 117: Test: Maximum number of vitals compared to the total number of
		// projectiles (bullets + homings)
		double vitalsPercentageProjectiles = (double) numberOfVitals / (numberOfBullets + numberOfHomings);
		testValue = (vitalsPercentageProjectiles <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 118: Test: Minimum number of vitals compared to the total number of
		// projectiles (bullets + homings)
		testValue = (vitalsPercentageProjectiles >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 119: Test: Maximum number of vitals compared to the total number of bullets
		double vitalsPercentageBullets = (double) numberOfVitals / numberOfBullets;
		testValue = (vitalsPercentageBullets <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 120: Test: Minimum number of vitals compared to the total number of bullets
		testValue = (vitalsPercentageBullets >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 121: Test: Maximum number of vitals compared to the total number of homings
		double vitalsPercentageHomings = (double) numberOfVitals / numberOfHomings;
		testValue = (vitalsPercentageHomings <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 122: Test: Minimum number of vitals compared to the total number of homings
		testValue = (vitalsPercentageHomings >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 123: Test: Maximum number of armed hulls compared to the total number of
		// vitals
		double armedHullsPercentageVitals = (double) numberOfArmedHulls / numberOfVitals;
		testValue = (armedHullsPercentageVitals <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 124: Test: Minimum number of armed hulls compared to the total number of
		// vitals
		testValue = (armedHullsPercentageVitals >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 125: Test: Maximum number of unarmed hulls compared to the total number of
		// vitals
		double unarmedHullsPercentageVitals = 1.0 - armedHullsPercentageVitals;
		testValue = (unarmedHullsPercentageVitals <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 126: Test: Minimum number of unarmed hulls compared to the total number of
		// vitals
		testValue = (unarmedHullsPercentageVitals >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 127: Test: Maximum number of unarmed hulls compared to the total number of
		// bullets
		double unarmedHullsPercentageBullets = (double) (numberOfHulls - numberOfArmedHulls) / numberOfBullets;
		testValue = (unarmedHullsPercentageBullets <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 128: Test: Minimum number of unarmed hulls compared to the total number of
		// bullets
		testValue = (unarmedHullsPercentageBullets >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 129: Test: Maximum number of unarmed hulls compared to the total number of
		// homings
		double unarmedHullsPercentageHomings = (double) (numberOfHulls - numberOfArmedHulls) / numberOfHomings;
		testValue = (unarmedHullsPercentageHomings <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 130: Test: Minimum number of unarmed hulls compared to the total number of
		// homings
		testValue = (unarmedHullsPercentageHomings >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 131: Test: Maximum number of vitals compared to the total number of hulls
		vitalsPercentageHulls = (double) numberOfVitals / numberOfHulls;
		testValue = (vitalsPercentageHulls <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 132: Test: Minimum number of vitals compared to the total number of hulls
		testValue = (vitalsPercentageHulls >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 133: Test: Maximum number of vitals compared to the total number of armed
		// hulls
		double vitalsPercentageArmedHulls = (double) numberOfVitals / numberOfArmedHulls;
		testValue = (vitalsPercentageArmedHulls <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 134: Test: Minimum number of vitals compared to the total number of armed
		// hulls
		testValue = (vitalsPercentageArmedHulls >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 135: Test: Maximum number of vitals compared to the total number of unarmed
		// hulls
		double vitalsPercentageUnarmedHulls = (double) numberOfVitals / (numberOfHulls - numberOfArmedHulls);
		testValue = (vitalsPercentageUnarmedHulls <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 136: Test: Minimum number of vitals compared to the total number of unarmed
		// hulls
		testValue = (vitalsPercentageUnarmedHulls >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 137: Test: Maximum number of full-armed hulls compared to the total number of
		// hulls
		double fullArmedHullsPercentageHulls = (double) numberOfFullArmedHulls / numberOfHulls;
		testValue = (fullArmedHullsPercentageHulls <= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 138: Test: Minimum number of full-armed hulls compared to the total number of
		// hulls
		testValue = (fullArmedHullsPercentageHulls >= 0.01) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 139: Test: Maximum number of full-armed hulls compared to the total number of
		// armed hulls
		double fullArmedHullsPercentageArmedHulls = (double) numberOfFullArmedHulls / numberOfArmedHulls;
		testValue = (fullArmedHullsPercentageArmedHulls <= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 140: Test: Minimum number of full-armed hulls compared to the total number of
		// armed hulls
		testValue = (fullArmedHullsPercentageArmedHulls >= 0.01) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 141: Test: Maximum number of full-armed hulls compared to the total number of
		// unarmed hulls
		double fullArmedHullsPercentageUnarmedHulls = (double) numberOfFullArmedHulls
				/ (numberOfHulls - numberOfArmedHulls);
		testValue = (fullArmedHullsPercentageUnarmedHulls <= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 142: Test: Minimum number of full-armed hulls compared to the total number of
		// unarmed hulls
		testValue = (fullArmedHullsPercentageUnarmedHulls >= 0.01) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 143: Test: Maximum number of vitals compared to the total number of
		// full-armed hulls
		double vitalsPercentageFullArmedHulls = (double) numberOfVitals / numberOfFullArmedHulls;
		testValue = (vitalsPercentageFullArmedHulls <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 144: Test: Minimum number of vitals compared to the total number of
		// full-armed hulls
		testValue = (vitalsPercentageFullArmedHulls >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 145: Test: Maximum number of vitals compared to the total number of unarmed
		// hulls with weapons
		double vitalsPercentageUnarmedHullsWithWeapons = (double) numberOfVitals
				/ (numberOfHulls - numberOfArmedHulls - numberOfUnarmedHulls);
		testValue = (vitalsPercentageUnarmedHullsWithWeapons <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 146: Test: Minimum number of vitals compared to the total number of unarmed
		// hulls with weapons
		testValue = (vitalsPercentageUnarmedHullsWithWeapons >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 147: Test: Maximum number of unarmed hulls without weapons compared to the
		// total number of unarmed hulls
		double unarmedHullsWithoutWeaponsPercentageUnarmedHulls = (double) numberOfUnarmedHulls
				/ (numberOfHulls - numberOfArmedHulls);
		testValue = (unarmedHullsWithoutWeaponsPercentageUnarmedHulls <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 148: Test: Minimum number of unarmed hulls without weapons compared to the
		// total number of unarmed hulls
		testValue = (unarmedHullsWithoutWeaponsPercentageUnarmedHulls >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 149: Test: Maximum number of armed hulls compared to the total number of
		// unarmed hulls without weapons
		double armedHullsPercentageUnarmedHullsWithoutWeapons = (double) numberOfArmedHulls / numberOfUnarmedHulls;
		testValue = (armedHullsPercentageUnarmedHullsWithoutWeapons <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 150: Test: Minimum number of armed hulls compared to the total number of
		// unarmed hulls without weapons
		testValue = (armedHullsPercentageUnarmedHullsWithoutWeapons >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 151: Test: Maximum number of unarmed hulls without weapons compared to the
		// total number of vitals
		double unarmedHullsWithoutWeaponsPercentageVitals = (double) numberOfUnarmedHulls / numberOfVitals;
		testValue = (unarmedHullsWithoutWeaponsPercentageVitals <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 152: Test: Minimum number of unarmed hulls without weapons compared to the
		// total number of vitals
		testValue = (unarmedHullsWithoutWeaponsPercentageVitals >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 153: Test: Maximum number of unarmed hulls without weapons compared to the
		// total number of bullets
		double unarmedHullsWithoutWeaponsPercentageBullets = (double) numberOfUnarmedHulls / numberOfBullets;
		testValue = (unarmedHullsWithoutWeaponsPercentageBullets <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 154: Test: Minimum number of unarmed hulls without weapons compared to the
		// total number of bullets
		testValue = (unarmedHullsWithoutWeaponsPercentageBullets >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 155: Test: Maximum number of unarmed hulls without weapons compared to the
		// total number of homings
		double unarmedHullsWithoutWeaponsPercentageHomings = (double) numberOfUnarmedHulls / numberOfHomings;
		testValue = (unarmedHullsWithoutWeaponsPercentageHomings <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 156: Test: Minimum number of unarmed hulls without weapons compared to the
		// total number of homings
		testValue = (unarmedHullsWithoutWeaponsPercentageHomings >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 157: Test: Maximum number of unarmed hulls without weapons compared to the
		// total number of lasers
		double unarmedHullsWithoutWeaponsPercentageLasers = (double) numberOfUnarmedHulls / numberOfLasers;
		testValue = (unarmedHullsWithoutWeaponsPercentageLasers <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 158: Test: Minimum number of unarmed hulls without weapons compared to the
		// total number of lasers
		testValue = (unarmedHullsWithoutWeaponsPercentageLasers >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 159: Test: Maximum number of bullets compared to the total number of homings
		double bulletsPercentageHomings = (double) numberOfBullets / numberOfHomings;
		testValue = (bulletsPercentageHomings <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 160: Test: Minimum number of bullets compared to the total number of homings
		testValue = (bulletsPercentageHomings >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 161: Test: Maximum number of bullets compared to the total number of lasers
		double bulletsPercentageLasers = (double) numberOfBullets / numberOfLasers;
		testValue = (bulletsPercentageLasers <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 162: Test: Minimum number of bullets compared to the total number of lasers
		testValue = (bulletsPercentageLasers >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 163: Test: Maximum number of homings compared to the total number of lasers
		double homingsPercentageLasers = (double) numberOfHomings / numberOfLasers;
		testValue = (homingsPercentageLasers <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 164: Test: Minimum number of homings compared to the total number of lasers
		testValue = (homingsPercentageLasers >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 165: Test: Maximum number of bullets compared to the total number of vitals
		double bulletsPercentageVitals = (double) numberOfBullets / numberOfVitals;
		testValue = (bulletsPercentageVitals <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 166: Test: Minimum number of bullets compared to the total number of vitals
		testValue = (bulletsPercentageVitals >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 167: Test: Maximum number of homings compared to the total number of vitals
		double homingsPercentageVitals = (double) numberOfHomings / numberOfVitals;
		testValue = (homingsPercentageVitals <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 168: Test: Minimum number of homings compared to the total number of vitals
		testValue = (homingsPercentageVitals >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 169: Test: Maximum number of lasers compared to the total number of vitals
		double lasersPercentageVitals = (double) numberOfLasers / numberOfVitals;
		testValue = (lasersPercentageVitals <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 170: Test: Minimum number of lasers compared to the total number of vitals
		testValue = (lasersPercentageVitals >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 171: Test: Maximum number of lasers compared to the total number of bullets
		double lasersPercentageBullets = (double) numberOfLasers / numberOfBullets;
		testValue = (lasersPercentageBullets <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 172: Test: Minimum number of lasers compared to the total number of bullets
		testValue = (lasersPercentageBullets >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 173: Test: Maximum number of lasers compared to the total number of homings
		double lasersPercentageHomings = (double) numberOfLasers / numberOfHomings;
		testValue = (lasersPercentageHomings <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 174: Test: Minimum number of lasers compared to the total number of homings
		testValue = (lasersPercentageHomings >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 175: Test: Maximum number of armed hulls compared to the total number of
		// vitals
		armedHullsPercentageVitals = (double) numberOfArmedHulls / numberOfVitals;
		testValue = (armedHullsPercentageVitals <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 176: Test: Minimum number of armed hulls compared to the total number of
		// vitals
		testValue = (armedHullsPercentageVitals >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 177: Test: Maximum number of armed hulls compared to the total number of
		// bullets
		double armedHullsPercentageBullets = (double) numberOfArmedHulls / numberOfBullets;
		testValue = (armedHullsPercentageBullets <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 178: Test: Minimum number of armed hulls compared to the total number of
		// bullets
		testValue = (armedHullsPercentageBullets >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 179: Test: Maximum number of armed hulls compared to the total number of
		// homings
		double armedHullsPercentageHomings = (double) numberOfArmedHulls / numberOfHomings;
		testValue = (armedHullsPercentageHomings <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 180: Test: Minimum number of armed hulls compared to the total number of
		// homings
		testValue = (armedHullsPercentageHomings >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 181: Test: Maximum number of armed hulls compared to the total number of
		// lasers
		double armedHullsPercentageLasers = (double) numberOfArmedHulls / numberOfLasers;
		testValue = (armedHullsPercentageLasers <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 182: Test: Minimum number of armed hulls compared to the total number of
		// lasers
		testValue = (armedHullsPercentageLasers >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 183: Test: Maximum number of unarmed hulls compared to the total number of
		// vitals
		unarmedHullsPercentageVitals = (double) (numberOfHulls - numberOfArmedHulls) / numberOfVitals;
		testValue = (unarmedHullsPercentageVitals <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 184: Test: Minimum number of unarmed hulls compared to the total number of
		// vitals
		testValue = (unarmedHullsPercentageVitals >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 185: Test: Maximum number of unarmed hulls compared to the total number of
		// bullets
		unarmedHullsPercentageBullets = (double) (numberOfHulls - numberOfArmedHulls) / numberOfBullets;
		testValue = (unarmedHullsPercentageBullets <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 186: Test: Minimum number of unarmed hulls compared to the total number of
		// bullets
		testValue = (unarmedHullsPercentageBullets >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 187: Test: Maximum number of unarmed hulls compared to the total number of
		// homings
		unarmedHullsPercentageHomings = (double) (numberOfHulls - numberOfArmedHulls) / numberOfHomings;
		testValue = (unarmedHullsPercentageHomings <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 188: Test: Minimum number of unarmed hulls compared to the total number of
		// homings
		testValue = (unarmedHullsPercentageHomings >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 189: Test: Maximum number of unarmed hulls compared to the total number of
		// lasers
		double unarmedHullsPercentageLasers = (double) (numberOfHulls - numberOfArmedHulls) / numberOfLasers;
		testValue = (unarmedHullsPercentageLasers <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 190: Test: Minimum number of unarmed hulls compared to the total number of
		// lasers
		testValue = (unarmedHullsPercentageLasers >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 191: Test: Maximum number of unarmed hulls without weapons compared to the
		// total number of vitals
		unarmedHullsWithoutWeaponsPercentageVitals = (double) numberOfUnarmedHulls / numberOfVitals;
		testValue = (unarmedHullsWithoutWeaponsPercentageVitals <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 192: Test: Minimum number of unarmed hulls without weapons compared to the
		// total number of vitals
		testValue = (unarmedHullsWithoutWeaponsPercentageVitals >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 193: Test: Maximum number of unarmed hulls without weapons compared to the
		// total number of bullets
		unarmedHullsWithoutWeaponsPercentageBullets = (double) numberOfUnarmedHulls / numberOfBullets;
		testValue = (unarmedHullsWithoutWeaponsPercentageBullets <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 194: Test: Minimum number of unarmed hulls without weapons compared to the
		// total number of bullets
		testValue = (unarmedHullsWithoutWeaponsPercentageBullets >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 195: Test: Maximum number of unarmed hulls without weapons compared to the
		// total number of homings
		unarmedHullsWithoutWeaponsPercentageHomings = (double) numberOfUnarmedHulls / numberOfHomings;
		testValue = (unarmedHullsWithoutWeaponsPercentageHomings <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 196: Test: Minimum number of unarmed hulls without weapons compared to the
		// total number of homings
		testValue = (unarmedHullsWithoutWeaponsPercentageHomings >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 197: Test: Maximum number of unarmed hulls without weapons compared to the
		// total number of lasers
		unarmedHullsWithoutWeaponsPercentageLasers = (double) numberOfUnarmedHulls / numberOfLasers;
		testValue = (unarmedHullsWithoutWeaponsPercentageLasers <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 198: Test: Minimum number of unarmed hulls without weapons compared to the
		// total number of lasers
		testValue = (unarmedHullsWithoutWeaponsPercentageLasers >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 199: Test: Maximum number of bullets compared to the total number of homings
		bulletsPercentageHomings = (double) numberOfBullets / numberOfHomings;
		testValue = (bulletsPercentageHomings <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 200: Test: Minimum number of bullets compared to the total number of homings
		testValue = (bulletsPercentageHomings >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 201: Test: Minimum number of unarmed hulls without weapons compared to the
		// total number of lasers
		unarmedHullsWithoutWeaponsPercentageLasers = (double) (numberOfHulls - numberOfArmedHulls
				- numberOfUnarmedHulls) / numberOfLasers;
		testValue = (unarmedHullsWithoutWeaponsPercentageLasers >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 202: Test: Maximum number of unarmed hulls without weapons compared to the
		// total number of vitals
		unarmedHullsWithoutWeaponsPercentageVitals = (double) (numberOfHulls - numberOfArmedHulls
				- numberOfUnarmedHulls) / numberOfVitals;
		testValue = (unarmedHullsWithoutWeaponsPercentageVitals <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 203: Test: Minimum number of unarmed hulls without weapons compared to the
		// total number of vitals
		testValue = (unarmedHullsWithoutWeaponsPercentageVitals >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 204: Test: Maximum number of unarmed hulls without weapons compared to the
		// total number of bullets
		unarmedHullsWithoutWeaponsPercentageBullets = (double) (numberOfHulls - numberOfArmedHulls
				- numberOfUnarmedHulls) / numberOfBullets;
		testValue = (unarmedHullsWithoutWeaponsPercentageBullets <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 205: Test: Minimum number of unarmed hulls without weapons compared to the
		// total number of bullets
		testValue = (unarmedHullsWithoutWeaponsPercentageBullets >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 206: Test: Maximum number of unarmed hulls without weapons compared to the
		// total number of homings
		unarmedHullsWithoutWeaponsPercentageHomings = (double) (numberOfHulls - numberOfArmedHulls
				- numberOfUnarmedHulls) / numberOfHomings;
		testValue = (unarmedHullsWithoutWeaponsPercentageHomings <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 207: Test: Minimum number of unarmed hulls without weapons compared to the
		// total number of homings
		testValue = (unarmedHullsWithoutWeaponsPercentageHomings >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 208: Test: Maximum number of unarmed hulls without weapons compared to the
		// total number of lasers
		unarmedHullsWithoutWeaponsPercentageLasers = (double) (numberOfHulls - numberOfArmedHulls
				- numberOfUnarmedHulls) / numberOfLasers;
		testValue = (unarmedHullsWithoutWeaponsPercentageLasers <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 209: Test: Minimum number of unarmed hulls without weapons compared to the
		// total number of lasers
		testValue = (unarmedHullsWithoutWeaponsPercentageLasers >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 210: Test: Maximum number of bullets compared to the total number of homings
		bulletsPercentageHomings = (double) numberOfBullets / numberOfHomings;
		testValue = (bulletsPercentageHomings <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 211: Test: Minimum number of bullets compared to the total number of homings
		testValue = (bulletsPercentageHomings >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 212: Test: Maximum number of bullets compared to the total number of lasers
		bulletsPercentageLasers = (double) numberOfBullets / numberOfLasers;
		testValue = (bulletsPercentageLasers <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 213: Test: Minimum number of bullets compared to the total number of lasers
		testValue = (bulletsPercentageLasers >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 214: Test: Maximum number of homings compared to the total number of lasers
		homingsPercentageLasers = (double) numberOfHomings / numberOfLasers;
		testValue = (homingsPercentageLasers <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 215: Test: Minimum number of homings compared to the total number of lasers
		testValue = (homingsPercentageLasers >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 216: Test: Maximum number of unarmed hulls compared to the total number of
		// vitals and weapons
		double unarmedHullsPercentageVitalsWeapons = (double) (numberOfHulls - numberOfArmedHulls)
				/ (numberOfVitals + numberOfWeapons);
		testValue = (unarmedHullsPercentageVitalsWeapons <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 217: Test: Minimum number of unarmed hulls compared to the total number of
		// vitals and weapons
		testValue = (unarmedHullsPercentageVitalsWeapons >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 218: Test: Maximum number of unarmed hulls without weapons compared to the
		// total number of vitals and weapons
		double unarmedHullsWithoutWeaponsPercentageVitalsWeapons = (double) (numberOfHulls - numberOfArmedHulls
				- numberOfUnarmedHulls) / (numberOfVitals + numberOfWeapons);
		testValue = (unarmedHullsWithoutWeaponsPercentageVitalsWeapons <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 219: Test: Minimum number of unarmed hulls without weapons compared to the
		// total number of vitals and weapons
		testValue = (unarmedHullsWithoutWeaponsPercentageVitalsWeapons >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 220: Test: Maximum number of unarmed hulls without weapons compared to the
		// total number of bullets and homings
		double unarmedHullsWithoutWeaponsPercentageBulletsHomings = (double) (numberOfHulls - numberOfArmedHulls
				- numberOfUnarmedHulls) / (numberOfBullets + numberOfHomings);
		testValue = (unarmedHullsWithoutWeaponsPercentageBulletsHomings <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 221: Test: Minimum number of unarmed hulls without weapons compared to the
		// total number of bullets and homings
		testValue = (unarmedHullsWithoutWeaponsPercentageBulletsHomings >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 222: Test: Maximum number of unarmed hulls without weapons compared to the
		// total number of bullets and lasers
		double unarmedHullsWithoutWeaponsPercentageBulletsLasers = (double) (numberOfHulls - numberOfArmedHulls
				- numberOfUnarmedHulls) / (numberOfBullets + numberOfLasers);
		testValue = (unarmedHullsWithoutWeaponsPercentageBulletsLasers <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 223: Test: Minimum number of unarmed hulls without weapons compared to the
		// total number of bullets and lasers
		testValue = (unarmedHullsWithoutWeaponsPercentageBulletsLasers >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 224: Test: Maximum number of unarmed hulls without weapons compared to the
		// total number of homings and lasers
		double unarmedHullsWithoutWeaponsPercentageHomingsLasers = (double) (numberOfHulls - numberOfArmedHulls
				- numberOfUnarmedHulls) / (numberOfHomings + numberOfLasers);
		testValue = (unarmedHullsWithoutWeaponsPercentageHomingsLasers <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 225: Test: Minimum number of unarmed hulls without weapons compared to the
		// total number of homings and lasers
		testValue = (unarmedHullsWithoutWeaponsPercentageHomingsLasers >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 226: Test: Maximum number of unarmed hulls without weapons compared to the
		// total number of vitals, bullets, and homings
		double unarmedHullsWithoutWeaponsPercentageVitalsBulletsHomings = (double) (numberOfHulls - numberOfArmedHulls
				- numberOfUnarmedHulls) / (numberOfVitals + numberOfBullets + numberOfHomings);
		testValue = (unarmedHullsWithoutWeaponsPercentageVitalsBulletsHomings <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 227: Test: Minimum number of unarmed hulls without weapons compared to the
		// total number of vitals, bullets, and homings
		testValue = (unarmedHullsWithoutWeaponsPercentageVitalsBulletsHomings >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 228: Test: Maximum number of unarmed hulls without weapons compared to the
		// total number of vitals, bullets, and lasers
		double unarmedHullsWithoutWeaponsPercentageVitalsBulletsLasers = (double) (numberOfHulls - numberOfArmedHulls
				- numberOfUnarmedHulls) / (numberOfVitals + numberOfBullets + numberOfLasers);
		testValue = (unarmedHullsWithoutWeaponsPercentageVitalsBulletsLasers <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 229: Test: Minimum number of unarmed hulls without weapons compared to the
		// total number of vitals, bullets, and lasers
		testValue = (unarmedHullsWithoutWeaponsPercentageVitalsBulletsLasers >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 230: Test: Maximum number of unarmed hulls without weapons compared to the
		// total number of vitals, homings, and lasers
		double unarmedHullsWithoutWeaponsPercentageVitalsHomingsLasers = (double) (numberOfHulls - numberOfArmedHulls
				- numberOfUnarmedHulls) / (numberOfVitals + numberOfHomings + numberOfLasers);
		testValue = (unarmedHullsWithoutWeaponsPercentageVitalsHomingsLasers <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 231: Test: Minimum number of unarmed hulls without weapons compared to the
		// total number of vitals, homings, and lasers
		testValue = (unarmedHullsWithoutWeaponsPercentageVitalsHomingsLasers >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 232: Test: Maximum number of bullets compared to the total number of vitals,
		// homings, and lasers
		double bulletsPercentageVitalsHomingsLasers = (double) numberOfBullets
				/ (numberOfVitals + numberOfHomings + numberOfLasers);
		testValue = (bulletsPercentageVitalsHomingsLasers <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 233: Test: Minimum number of bullets compared to the total number of vitals,
		// homings, and lasers
		testValue = (bulletsPercentageVitalsHomingsLasers >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 234: Test: Maximum number of homings compared to the total number of vitals,
		// bullets, and lasers
		double homingsPercentageVitalsBulletsLasers = (double) numberOfHomings
				/ (numberOfVitals + numberOfBullets + numberOfLasers);
		testValue = (homingsPercentageVitalsBulletsLasers <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 235: Test: Minimum number of homings compared to the total number of vitals,
		// bullets, and lasers
		testValue = (homingsPercentageVitalsBulletsLasers >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 236: Test: Maximum number of lasers compared to the total number of vitals,
		// bullets, and homings
		double lasersPercentageVitalsBulletsHomings = (double) numberOfLasers
				/ (numberOfVitals + numberOfBullets + numberOfHomings);
		testValue = (lasersPercentageVitalsBulletsHomings <= 0.5) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 237: Test: Minimum number of lasers compared to the total number of vitals,
		// bullets, and homings
		testValue = (lasersPercentageVitalsBulletsHomings >= 0.1) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 238: Test: Maximum number of unarmed hulls compared to the total number of
		// vitals, bullets, homings, and lasers
		double unarmedHullsPercentageVitalsBulletsHomingsLasers = (double) (numberOfHulls - numberOfArmedHulls)
				/ (numberOfVitals + numberOfBullets + numberOfHomings + numberOfLasers);
		testValue = (unarmedHullsPercentageVitalsBulletsHomingsLasers <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 239: Test: Minimum number of unarmed hulls compared to the total number of
		// vitals, bullets, homings, and lasers
		testValue = (unarmedHullsPercentageVitalsBulletsHomingsLasers >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 240: Test: Maximum number of unarmed hulls without weapons compared to the
		// total number of vitals, bullets, homings, and lasers
		double unarmedHullsWithoutWeaponsPercentageVitalsBulletsHomingsLasers = (double) (numberOfHulls
				- numberOfArmedHulls - numberOfUnarmedHulls)
				/ (numberOfVitals + numberOfBullets + numberOfHomings + numberOfLasers);
		testValue = (unarmedHullsWithoutWeaponsPercentageVitalsBulletsHomingsLasers <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 241: Test: Minimum number of unarmed hulls without weapons compared to the
		// total number of vitals, bullets, homings, and lasers
		testValue = (unarmedHullsWithoutWeaponsPercentageVitalsBulletsHomingsLasers >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 242: Test: Maximum number of unarmed hulls without weapons compared to the
		// total number of all parts
		double unarmedHullsWithoutWeaponsPercentageAllParts = (double) (numberOfHulls - numberOfArmedHulls
				- numberOfUnarmedHulls)
				/ (numberOfVitals + numberOfBullets + numberOfHomings + numberOfLasers + numberOfWeapons);
		testValue = (unarmedHullsWithoutWeaponsPercentageAllParts <= 0.8) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// 243: Test: Minimum number of unarmed hulls without weapons compared to the
		// total number of all parts
		testValue = (unarmedHullsWithoutWeaponsPercentageAllParts >= 0.2) ? 1.0 : 0.0;
		testCompilation.add(testValue);

		// Overall fitness value calculation
		double F_Overall = 0.0;
		for (int i = 0; i < testCompilation.size(); ++i) {
			F_Overall += testCompilation.get(i);
		}
		if (!testCompilation.isEmpty()) {
			F_Overall /= (double) testCompilation.size();
		}

		// Description addendum
		mDescription += "----Appendix: Fitness Alternative Value----\n";
		mDescription += "F_Overall: " + F_Overall + "\n";

		// Done
		return IsValid() ? F_Overall : 0.0;
	}
	
	  /////////////////////////////////////////////////////////////////////////////
	  
	  private String GetUpdatedTreeSequence()
	  {
	    int hullSize = GetConfiguration().length() / GetNumberOfRowsInEncoding();
	    int indexStart = 6 * hullSize;
	    //int indexLength = 7 * hullSize;
	    return GetConfiguration().substring( indexStart, indexStart + hullSize );
	  }
	  
	  /////////////////////////////////////////////////////////////////////////////
	  
	  private String GetEnabledHullSequence()
	  {
	    int hullSize = GetConfiguration().length() / GetNumberOfRowsInEncoding();
	    int indexStart = 5 * hullSize;
	    //int indexLength = 6 * hullSize;
	    return GetConfiguration().substring( indexStart, indexStart + hullSize );
	  }
	
	  /////////////////////////////////////////////////////////////////////////////
	
	  private ArrayList<Integer> GetChildren( int nodeIndex )
	  {
	    ArrayList<Integer> children = new ArrayList<Integer>();
	    for ( int i = 0; i < mTreeSequence.length(); ++i )
	    {
	      if ( mTreeSequence.charAt( i ) == GetCharacterFromNumber( nodeIndex ) )
	      {
	        children.add( i );
	      }
	    }
	    return children;
	  }
	
	  /////////////////////////////////////////////////////////////////////////////
	
	  private int GetChildCount( int nodeIndex )
	  {
	    //System.out.println( "GetChildCount: " + nodeIndex + " " + mTreeSequence );
	    int childCount = 0;
	    for ( int i = 0; i < mTreeSequence.length(); ++i )
	    {
	      if ( mTreeSequence.charAt( i ) == GetCharacterFromNumber( nodeIndex ) )
	      {
	        ++childCount;
	      }
	    }
	    return childCount;
	  }
	
	  /////////////////////////////////////////////////////////////////////////////
	
	  private int GetChild( int nodeIndex, int childIndex )
	  {
	    
	    int childSearched = -2;
	
	    int childCounter = -1;
	    for ( int i = 0; childSearched == -2 && i < mTreeSequence.length(); ++i )
	    {
	      if ( mTreeSequence.charAt( i ) == GetCharacterFromNumber( nodeIndex ) )
	      {
	        ++childCounter;
	        if ( childCounter == childIndex )
	        {
	          childSearched = i;
	        }
	      }
	    }
	    
	    return childSearched;
	  }
	  
	  /////////////////////////////////////////////////////////////////////////////
	
	  private int GetNumberOfDescendants( int nodeIndex )
	  {
	    int childCount = GetChildCount( nodeIndex );
	    int numberOfDescendants = childCount;
	    for ( int i = 0; i < childCount; ++i )
	    {
	      numberOfDescendants += GetNumberOfDescendants( GetChild( nodeIndex, i ) );
	    }
	    return numberOfDescendants;
	  }
	  
	  /////////////////////////////////////////////////////////////////////////////
	
	  static private double GetDistance( IndividualBossConfig boss1,
	                                     Integer currentNodeBoss1,
	                                     IndividualBossConfig boss2,
	                                     Integer currentNodeBoss2 )
	  {
	    if ( VERSION_TYPE == VersionType.VERSION_TYPE_2 || VERSION_TYPE == VersionType.VERSION_TYPE_3 )
	    {     
	      int childCount1 = currentNodeBoss1 < 0 ? 0 : boss1.GetChildCount( currentNodeBoss1 );
	      int indexHeaviestChild1 = 0;
	      for ( int i = 0; i < childCount1; ++i )
	      {
	        if ( boss1.GetNumberOfDescendants( boss1.GetChild( currentNodeBoss1, i ) ) > 
	             boss1.GetNumberOfDescendants( boss1.GetChild( currentNodeBoss1, indexHeaviestChild1 ) )
	           )
	        {
	          indexHeaviestChild1 = i;
	        }
	      }
	      currentNodeBoss1 = ( childCount1 <= 0 ) ? -1 : boss1.GetChild( currentNodeBoss1, indexHeaviestChild1 );
	      
	     
	      int childCount2 = currentNodeBoss2 < 0 ? 0 : boss2.GetChildCount( currentNodeBoss2 );
	      int indexHeaviestChild2 = 0;
	      for ( int i = 0; i < childCount2; ++i )
	      {
	        if ( boss2.GetNumberOfDescendants( boss2.GetChild( currentNodeBoss2, i ) ) > 
	             boss2.GetNumberOfDescendants( boss2.GetChild( currentNodeBoss2, indexHeaviestChild2 ) )
	           )
	        {
	          indexHeaviestChild2 = i;
	        }
	      }
	      currentNodeBoss2 = ( childCount2 <= 0 ) ? -1 : boss2.GetChild( currentNodeBoss2, indexHeaviestChild2 );
	      
	      
	      
	      
	      int localDivergence = Math.abs( childCount1 - childCount2 );
	      return
	          localDivergence +
	          ( ( currentNodeBoss1 == -1 && currentNodeBoss2 == -1 ) ? 0 : GetDistance( boss1, currentNodeBoss1, boss2, currentNodeBoss2 ) );
	    }
	    else
	    {
	      return 1.0;
	    }
	  }
	  
	  
	  /////////////////////////////////////////////////////////////////////////////
	
	  static public double GetDissimilarity( Individual individual1, Individual individual2 )
	  {
	    IndividualBossConfig boss1 = ( IndividualBossConfig ) individual1;
	    IndividualBossConfig boss2 = ( IndividualBossConfig ) individual2;
	    double dissimilarity = 0.0;
	    double distanceMaximum = /*1.0;*/ (float) ( boss1.GetNumberOfDescendants( 0 ) + boss2.GetNumberOfDescendants( 0 ) );
	    if ( distanceMaximum > 0.0 )
	    {
	      dissimilarity = ( (double) GetDistance( boss1, 0, boss2, 0 ) ) / distanceMaximum;
	    }
	    return dissimilarity;
	  }

	  /////////////////////////////////////////////////////////////////////////////
	  
	  public double GetSimmetryExperimental()
	  {
		  ArrayList<Double> simmetries = new ArrayList<Double>();
		  ArrayList<Integer> childrenOfRoot = this.GetChildren(0);
		  for ( int i = 0; i < childrenOfRoot.size(); ++i )
		  {
			  if ((i + 1) < childrenOfRoot.size())
			  {
				  double dissimilarity = 0.0;
				  double pairSimmetry = 1.0;
				  double distanceMaximum = (float) ( this.GetNumberOfDescendants( childrenOfRoot.get(i) ) + this.GetNumberOfDescendants( childrenOfRoot.get(i + 1) ) );
				  if ( distanceMaximum > 0.0 )
				  {
					  dissimilarity = ( (double) GetDistance( this, childrenOfRoot.get(i), this, childrenOfRoot.get(i + 1) ) ) / distanceMaximum;
				  }
				  pairSimmetry = 1.0 - dissimilarity;
				  pairSimmetry = Math.max(0.0, Math.min(1.0, pairSimmetry));
				  simmetries.add(pairSimmetry);
			  }
		  }
		  
		  
		  double simmetry = 0.0;
		  for (int i = 0; i < simmetries.size(); ++i)
		  {
			  simmetry += simmetries.get(i);
		  }
		  if (!simmetries.isEmpty())
		  {
			  simmetry /= (double) simmetries.size();
		  }
		  return simmetry;
	  }

	  /////////////////////////////////////////////////////////////////////////////
	  
	  public double GetSimmetry()
	  {
		  int numberOfHullsWithEvenChildCount = 0;
		  String enabledHullSequence = this.GetEnabledHullSequence();
		  for (int i = 0; i < enabledHullSequence.length(); ++i)
		  {
			  if (enabledHullSequence.charAt(i) == '1' && (GetChildCount(i) % 2) == 0)
			  {
				  ++numberOfHullsWithEvenChildCount;
			  }
		  }
		  
		  double simmetry = 1.0;
		  if ( enabledHullSequence.length() > 0)
		  {
			  simmetry = (double)numberOfHullsWithEvenChildCount / (double)enabledHullSequence.length();
		  }
		  return simmetry;
	  }
	  
	  /////////////////////////////////////////////////////////////////////////////
	  
	  	/**
	  	 * @return the amount of functional blocks over the total number of blocks (s1).
	  	 */
	  	public double GetS1() {
			// Variables to track the number of functional blocks and total blocks.
			int functionalBlocks, totalBlocks;
			functionalBlocks = totalBlocks = 0;
	
			// Iterate over each column of the configuration.
			for (int i = 0; i < CONFIGURATION_ROW_LENGTH; ++i) {
				// Calculate the indices for different elements in the configuration.
				int weakPointIndex        = 0 * CONFIGURATION_ROW_LENGTH + i;
				int meleeWeaponIndex      = 1 * CONFIGURATION_ROW_LENGTH + i;
				int bulletWeaponIndex     = 2 * CONFIGURATION_ROW_LENGTH + i;
				int homingProjectileIndex = 3 * CONFIGURATION_ROW_LENGTH + i;
				int laserWeaponIndex      = 4 * CONFIGURATION_ROW_LENGTH + i;
				int hullIndex             = 5 * CONFIGURATION_ROW_LENGTH + i;
	
				// Check if a hull is present in the current column. If no hull is present, exit the loop.
				boolean isHullPresent = (mConfiguration.charAt(hullIndex) - '0') == 1;
				if (!isHullPresent) break;
	
				// Check if each type of element (weak point, melee weapon, bullet weapon, homing projectile, laser weapon) is present in the current column.
				boolean isWeakPointPresent = (mConfiguration.charAt(weakPointIndex) - '0') == 1;
				boolean isMeleeWeaponPresent = (mConfiguration.charAt(meleeWeaponIndex) - '0') == 1;
				boolean isBulletWeaponPresent = (mConfiguration.charAt(bulletWeaponIndex) - '0') == 1;
				boolean isHomingProjectilePresent = (mConfiguration.charAt(homingProjectileIndex) - '0') == 1;
				boolean isLaserWeaponPresent = (mConfiguration.charAt(laserWeaponIndex) - '0') == 1;
	
				// If any functional element is present in the current column, increment the functionalBlocks counter.
				if (isWeakPointPresent || isMeleeWeaponPresent || isBulletWeaponPresent || isHomingProjectilePresent || isLaserWeaponPresent) {
					++functionalBlocks;
				}
	
				// Increment the totalBlocks counter for each column.
				++totalBlocks;
			}
	
			// Return the  the amount of functional blocks over the total number of blocks (s1).
			return functionalBlocks / totalBlocks;
		}
	  
	  /////////////////////////////////////////////////////////////////////////////
	  
	  	/**
	  	 * @return the filled volume over the total (bounding box) volume (s2).
	  	 */
	  	public double GetS2() {
	  		// Variables to track the filled volume and total (bounding box) volume.
	  		int filledVolume, totalVolume;
	  		filledVolume = totalVolume = 0;

	  		// Iterate over each column of the configuration.
	  		for (int i = 0; i < CONFIGURATION_ROW_LENGTH; ++i) {
	  			// Calculate the hull indices in the configuration.
	  			int hullIndex             = 5 * CONFIGURATION_ROW_LENGTH + i;

	  			// Check if a hull is present in the current column. If positive, increment the filledVolume counter.
	  			boolean isHullPresent = (mConfiguration.charAt(hullIndex) - '0') == 1;
	  			if (isHullPresent) ++filledVolume;

	  			// Increment the totalVolume counter for each column.
	  			++totalVolume;
	  		}

	  		// Return the filled volume over the total (bounding box) volume (s2).
	  		return filledVolume / totalVolume;
	  	}
	  
	  /////////////////////////////////////////////////////////////////////////////
	  
	  	/**
	  	 * @return the major axis over the medium axis (s3).
	  	 */
	  	public double GetS3() {
	  		// Variables to track the major axis and medium axis.
	  		int majorAxis, mediumAxis;
	  		majorAxis = mediumAxis = 0;

	  		// Iterate over each column of the configuration.
	  		for (int i = 0; i < CONFIGURATION_ROW_LENGTH; ++i) {
	  			// Calculate the indices for different elements in the configuration.
	  			int weakPointIndex        = 0 * CONFIGURATION_ROW_LENGTH + i;
	  			int meleeWeaponIndex      = 1 * CONFIGURATION_ROW_LENGTH + i;
	  			int bulletWeaponIndex     = 2 * CONFIGURATION_ROW_LENGTH + i;
	  			int homingProjectileIndex = 3 * CONFIGURATION_ROW_LENGTH + i;
	  			int laserWeaponIndex      = 4 * CONFIGURATION_ROW_LENGTH + i;
	  			int hullIndex             = 5 * CONFIGURATION_ROW_LENGTH + i;

	  			// Check if a hull is present in the current column. If no hull is present, exit the loop.
	  			boolean isHullPresent = (mConfiguration.charAt(hullIndex) - '0') == 1;
	  			if (!isHullPresent) break;

	  			// Variable to track the number of elements present in the current column.
	  			int axis = 0;

	  			// Check if a weak point is present in the current column.
	  			boolean isWeakPointPresent = (mConfiguration.charAt(weakPointIndex) - '0') == 1;
	  			if (isWeakPointPresent) ++axis;

	  			// Check if a melee weapon is present in the current column.
	  			boolean isMeleeWeaponPresent = (mConfiguration.charAt(meleeWeaponIndex) - '0') == 1;
	  			if (isMeleeWeaponPresent) ++axis;

	  			// Check if a bullet weapon is present in the current column.
	  			boolean isBulletWeaponPresent = (mConfiguration.charAt(bulletWeaponIndex) - '0') == 1;
	  			if (isBulletWeaponPresent) ++axis;

	  			// Check if a homing projectile is present in the current column.
	  			boolean isHomingProjectilePresent = (mConfiguration.charAt(homingProjectileIndex) - '0') == 1;
	  			if (isHomingProjectilePresent) ++axis;

	  			// Check if a laser weapon is present in the current column.
	  			boolean isLaserWeaponPresent = (mConfiguration.charAt(laserWeaponIndex) - '0') == 1;
	  			if (isLaserWeaponPresent) ++axis;

	  			// Update the major and medium axes.
	  			if (axis > majorAxis) {
	  				mediumAxis = majorAxis;
	  				majorAxis = axis;
	  			} else if (axis > mediumAxis) {
	  				mediumAxis = axis;
	  			}
	  		}

	  		// Return the major axis over the medium axis (s3).
	  		return (majorAxis - mediumAxis) / 5;
	  	}
	  
	  /////////////////////////////////////////////////////////////////////////////
	  
	  	/**
	  	 * @return the major axis over the smallest axis (s4).
	  	 */
	  	public double GetS4() {
	  		// Variables to track the major axis and smallest axis.
	  		int majorAxis, smallestAxis;
	  		majorAxis = smallestAxis = 0;

	  		// Iterate over each column of the configuration.
	  		for (int i = 0; i < CONFIGURATION_ROW_LENGTH; ++i) {
	  			// Calculate the indices for different elements in the configuration.
	  			int weakPointIndex        = 0 * CONFIGURATION_ROW_LENGTH + i;
	  			int meleeWeaponIndex      = 1 * CONFIGURATION_ROW_LENGTH + i;
	  			int bulletWeaponIndex     = 2 * CONFIGURATION_ROW_LENGTH + i;
	  			int homingProjectileIndex = 3 * CONFIGURATION_ROW_LENGTH + i;
	  			int laserWeaponIndex      = 4 * CONFIGURATION_ROW_LENGTH + i;
	  			int hullIndex             = 5 * CONFIGURATION_ROW_LENGTH + i;

	  			// Check if a hull is present in the current column. If no hull is present, exit the loop.
	  			boolean isHullPresent = (mConfiguration.charAt(hullIndex) - '0') == 1;
	  			if (!isHullPresent) break;

	  			// Variable to track the number of elements present in the current column.
	  			int axis = 0;

	  			// Check if a weak point is present in the current column.
	  			boolean isWeakPointPresent = (mConfiguration.charAt(weakPointIndex) - '0') == 1;
	  			if (isWeakPointPresent) ++axis;

	  			// Check if a melee weapon is present in the current column.
	  			boolean isMeleeWeaponPresent = (mConfiguration.charAt(meleeWeaponIndex) - '0') == 1;
	  			if (isMeleeWeaponPresent) ++axis;

	  			// Check if a bullet weapon is present in the current column.
	  			boolean isBulletWeaponPresent = (mConfiguration.charAt(bulletWeaponIndex) - '0') == 1;
	  			if (isBulletWeaponPresent) ++axis;

	  			// Check if a homing projectile is present in the current column.
	  			boolean isHomingProjectilePresent = (mConfiguration.charAt(homingProjectileIndex) - '0') == 1;
	  			if (isHomingProjectilePresent) ++axis;

	  			// Check if a laser weapon is present in the current column.
	  			boolean isLaserWeaponPresent = (mConfiguration.charAt(laserWeaponIndex) - '0') == 1;
	  			if (isLaserWeaponPresent) ++axis;

	  			// Update the major and smallest axes.
	  			majorAxis = Math.max(majorAxis, axis);
	  			smallestAxis = Math.min(smallestAxis, axis);
	  		}

	  		// Return the major axis over the smallest axis (s4).
	  		return (majorAxis - smallestAxis) / 5;
	  	}
	  
	  /////////////////////////////////////////////////////////////////////////////


}
