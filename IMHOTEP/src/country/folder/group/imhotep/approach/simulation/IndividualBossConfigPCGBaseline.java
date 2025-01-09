package country.folder.group.imhotep.approach.simulation;

import java.util.Map;

import static java.util.Map.entry;

public class IndividualBossConfigPCGBaseline extends IndividualBossConfig {
	
	/////////////////////////////////////////////////////////////////////////////

	public IndividualBossConfigPCGBaseline(String configuration) {
		super(configuration);
	}

	/////////////////////////////////////////////////////////////////////////////

	public IndividualBossConfigPCGBaseline(int hullSize, double oneProbability) {
		super(hullSize, oneProbability);
	}
	
	/////////////////////////////////////////////////////////////////////////////

	public Individual CrossOver(Individual mate) {
		return new IndividualBossConfigPCGBaseline
		(
			( ( IndividualBossConfig ) super.CrossOver(mate) ).mConfiguration
		);
	}

	/////////////////////////////////////////////////////////////////////////////

	/**
	 * Kromaia's L-system
	 * variables: H W M B P L
	 */
	private interface Rule {
		void apply(StringBuilder axiom, int index);
	}

	private Map<Integer, Rule> hullGrammar = Map.ofEntries(
			/**
			 * One hull can produce two hulls.
			 * rule: (H -> HH)
			 */
			entry(0, new Rule() {
				public void apply(StringBuilder axiom, int index) {
					final int HU_END_INDEX = 6 * CONFIGURATION_ROW_LENGTH;

					int lastHull = axiom.substring(index, HU_END_INDEX).lastIndexOf('1');
					if ((index = lastHull + 1) == HU_END_INDEX) return;

					axiom.setCharAt(index, '1');
				}
			}),
			/**
			 * Enable weak point.
			 * rule: (H -> HW)
			 */
			entry(1, new Rule() {
				public void apply(StringBuilder axiom, int index) {
					axiom.setCharAt(index - 5 * CONFIGURATION_ROW_LENGTH, '1');
				}
			}),
			/**
			 * Enable melee weapon.
			 * rule: (H -> HM)
			 */
			entry(2, new Rule() {
				public void apply(StringBuilder axiom, int index) {
					axiom.setCharAt(index - 4 * CONFIGURATION_ROW_LENGTH, '1');
				}
			}),
			/**
			 * Enable bullet weapon.
			 * rule: (H -> HB)
			 */
			entry(3, new Rule() {
				public void apply(StringBuilder axiom, int index) {
					axiom.setCharAt(index - 3 * CONFIGURATION_ROW_LENGTH, '1');
				}
			}),
			/**
			 * Enable homing projectile.
			 * rule: (H -> HP)
			 */
			entry(4, new Rule() {
				public void apply(StringBuilder axiom, int index) {
					axiom.setCharAt(index - 2 * CONFIGURATION_ROW_LENGTH, '1');
				}
			}),
			/**
			 * Enable laser weapon.
			 * rule: (H -> HL)
			 */
			entry(5, new Rule() {
				public void apply(StringBuilder axiom, int index) {
					axiom.setCharAt(index - 1 * CONFIGURATION_ROW_LENGTH, '1');
				}
			})
	);

	private Map<Integer, Rule> weakPointGrammar = Map.ofEntries(
			/**
			 * A weak point can produce additional weapons.
			 * rules: (W -> WM), (W -> WB), (W -> WP), (W -> WL)
			 */
			entry(0, new Rule() {
				public void apply(StringBuilder axiom, int index) {
					switch (random.nextInt(4)) {
						case 0 -> axiom.setCharAt(index + 1 * CONFIGURATION_ROW_LENGTH, '1');
						case 1 -> axiom.setCharAt(index + 2 * CONFIGURATION_ROW_LENGTH, '1');
						case 2 -> axiom.setCharAt(index + 3 * CONFIGURATION_ROW_LENGTH, '1');
						case 3 -> axiom.setCharAt(index + 4 * CONFIGURATION_ROW_LENGTH, '1');
					}
				}
			}),
			/**
			 * A weak point can be transformed into one or more long-range weapons.
			 * rules: (W -> L), (W -> P), (W -> PL), (W -> B), (W -> BL), (W -> BP), (W -> BPL)
			 */
			entry(1, new Rule() {
				public void apply(StringBuilder axiom, int index) {
					axiom.setCharAt(index, '0');

					switch (random.nextInt(7)) {
						case 0 -> axiom.setCharAt(index + 4 * CONFIGURATION_ROW_LENGTH, '1');
						case 1 -> axiom.setCharAt(index + 3 * CONFIGURATION_ROW_LENGTH, '1');
						case 2 -> {
							axiom.setCharAt(index + 3 * CONFIGURATION_ROW_LENGTH, '1');
							axiom.setCharAt(index + 4 * CONFIGURATION_ROW_LENGTH, '1');
						}
						case 3 -> axiom.setCharAt(index + 2 * CONFIGURATION_ROW_LENGTH, '1');
						case 4 -> {
							axiom.setCharAt(index + 2 * CONFIGURATION_ROW_LENGTH, '1');
							axiom.setCharAt(index + 4 * CONFIGURATION_ROW_LENGTH, '1');
						}
						case 5 -> {
							axiom.setCharAt(index + 2 * CONFIGURATION_ROW_LENGTH, '1');
							axiom.setCharAt(index + 3 * CONFIGURATION_ROW_LENGTH, '1');
						}
						case 6 -> {
							axiom.setCharAt(index + 2 * CONFIGURATION_ROW_LENGTH, '1');
							axiom.setCharAt(index + 3 * CONFIGURATION_ROW_LENGTH, '1');
							axiom.setCharAt(index + 4 * CONFIGURATION_ROW_LENGTH, '1');
						}
					}
				}
			})
	);

	private Map<Integer, Rule> meleeWeaponGrammar = Map.ofEntries(
			/**
			 * A melee weapon can transform into any other weapon.
			 * rule: (M -> B), (M -> P), (M -> L)
			 */
			entry(0, new Rule() {
				public void apply(StringBuilder axiom, int index) {
					axiom.setCharAt(index, '0');

					switch (random.nextInt(3)) {
						case 0 -> axiom.setCharAt(index + 1 * CONFIGURATION_ROW_LENGTH, '1');
						case 1 -> axiom.setCharAt(index + 2 * CONFIGURATION_ROW_LENGTH, '1');
						case 2 -> axiom.setCharAt(index + 3 * CONFIGURATION_ROW_LENGTH, '1');
					}
				}
			}),
			/**
			 * A melee weapon can become a weak point.
			 * rule: (M -> W)
			 */
			entry(1, new Rule() {
				public void apply(StringBuilder axiom, int index) {
					axiom.setCharAt(index, '0');
					axiom.setCharAt(index - 1 * CONFIGURATION_ROW_LENGTH, '1');
				}
			})
	);

	private Map<Integer, Rule> bulletWeaponGrammar = Map.ofEntries(
			/**
			 * A bullet weapon can be transformed into another long-range weapon.
			 * rules: (B -> P), (B -> L)
			 */
			entry(0, new Rule() {
				public void apply(StringBuilder axiom, int index) {
					axiom.setCharAt(index, '0');

					switch (random.nextInt(2)) {
						case 0 -> axiom.setCharAt(index + 1 * CONFIGURATION_ROW_LENGTH, '1');
						case 1 -> axiom.setCharAt(index + 2 * CONFIGURATION_ROW_LENGTH, '1');
					}
				}
			}),
			/**
			 * A bullet weapon can become a weak point.
			 * rule: (B -> W)
			 */
			entry(1, new Rule() {
				public void apply(StringBuilder axiom, int index) {
					axiom.setCharAt(index, '0');
					axiom.setCharAt(index - 2 * CONFIGURATION_ROW_LENGTH, '1');
				}
			})
	);

	private Map<Integer, Rule> homingProjectileGrammar = Map.ofEntries(
			/**
			 * A homing projectile can be transformed into another long-range weapon.
			 * rules: (P -> B), (P -> L)
			 */
			entry(0, new Rule() {
				public void apply(StringBuilder axiom, int index) {
					axiom.setCharAt(index, '0');

					switch (random.nextInt(2)) {
						case 0 -> axiom.setCharAt(index - 1 * CONFIGURATION_ROW_LENGTH, '1');
						case 1 -> axiom.setCharAt(index + 1 * CONFIGURATION_ROW_LENGTH, '1');
					}
				}
			}),
			/**
			 * A homing projectile can become a weak point.
			 * rule: (P -> W)
			 */
			entry(1, new Rule() {
				public void apply(StringBuilder axiom, int index) {
					axiom.setCharAt(index, '0');
					axiom.setCharAt(index - 3 * CONFIGURATION_ROW_LENGTH, '1');
				}
			})
	);

	private Map<Integer, Rule> laserWeaponGrammar = Map.ofEntries(
			/**
			 * A laser weapon can be transformed into another long-range weapon.
			 * rules: (L -> B), (L -> P)
			 */
			entry(0, new Rule() {
				public void apply(StringBuilder axiom, int index) {
					axiom.setCharAt(index, '0');

					switch (random.nextInt(2)) {
						case 0 -> axiom.setCharAt(index - 2 * CONFIGURATION_ROW_LENGTH, '1');
						case 1 -> axiom.setCharAt(index - 1 * CONFIGURATION_ROW_LENGTH, '1');
					}
				}
			}),
			/**
			 * A laser weapon can become a weak point.
			 * rule: (L -> W)
			 */
			entry(1, new Rule() {
				public void apply(StringBuilder axiom, int index) {
					axiom.setCharAt(index, '0');
					axiom.setCharAt(index - 4 * CONFIGURATION_ROW_LENGTH, '1');
				}
			})
	);

	public void Mutate() {
		// Create a new StringBuilder object to store the mutated configuration.
		StringBuilder newConfiguration = new StringBuilder(mConfiguration);

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
			boolean isHullPresent = (newConfiguration.charAt(hullIndex) - '0') == 1;
			if (!isHullPresent) break;

			// Randomly determine whether to continue or skip the mutation for the current column.
			if (random.nextInt(CONFIGURATION_ROW_LENGTH) == 0) continue;

			// Mutate the hulls first. If new ones are added, they serve as anchor points for generating other new elements as well.
			hullGrammar.get(random.nextInt(hullGrammar.size())).apply(newConfiguration, hullIndex);

			// Apply random mutations to other elements in the configuration.
			weakPointGrammar.get(random.nextInt(weakPointGrammar.size())).apply(newConfiguration, weakPointIndex);
			meleeWeaponGrammar.get(random.nextInt(meleeWeaponGrammar.size())).apply(newConfiguration, meleeWeaponIndex);
			bulletWeaponGrammar.get(random.nextInt(bulletWeaponGrammar.size())).apply(newConfiguration, bulletWeaponIndex);
			homingProjectileGrammar.get(random.nextInt(homingProjectileGrammar.size())).apply(newConfiguration, homingProjectileIndex);
			laserWeaponGrammar.get(random.nextInt(laserWeaponGrammar.size())).apply(newConfiguration, laserWeaponIndex);

			// Total number of rules: 6 (H) + 11 (W) + 4 (M) + 3 (B) + 3 (P) + 3 (L) = 30.

			// TODO: Define transformation for the Link and Behavioral hull indices?
		}

		// Update the original configuration with the mutated configuration.
		mConfiguration = newConfiguration.toString();
	}

	/////////////////////////////////////////////////////////////////////////////

	public double CalculateFitnessAlternative() {
		// Overall fitness value calculation.
		double F_Overall = 0.2 * this.GetS1() + 0.2 * this.GetS2() + 0.2 * this.GetS3() + 0.2 * this.GetS4() + 0.2 * this.GetSimmetry();
		return IsValid() ? F_Overall : 0.0;
	}

	/////////////////////////////////////////////////////////////////////////////
}
