package country.folder.group.imhotep;

import country.folder.group.imhotep.approach.Transplantation;
import country.folder.group.approach.simulation.TinyEvo;
import country.folder.group.model.KromaiaModel;

import static country.folder.group.imhotep.model.encoding.KromaiaEncoding.CONFIGURATION_ROW_LENGTH;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {
	static private final String ROOT_FILE_COMPILATION = "./bin/res/";
	static private final String ROOT_FILE_LOAD = "./res/";
	static private final String SUBPATH_DONORS = "Donors/Totems/";
	static private final String SUBPATH_HOSTS = "Hosts/";
	static private final String FILE_EXTENSION = ".xml";
	
	static private final int HOST_NUMBER_OF_REPETITIONS = 129; // WARNING:  By default, set this to 1. Special optional configuration: Hosts could be repeated.

	public static void main(String[] args) {
		// File collections
		File[] hostFiles = (new File(ROOT_FILE_COMPILATION + SUBPATH_HOSTS)).listFiles();
		File[] donorFiles = (new File(ROOT_FILE_COMPILATION + SUBPATH_DONORS)).listFiles();

		// For each host, try every possible source
		for (int h = 0; h < hostFiles.length; ++h) {
			if (!hostFiles[h].isDirectory()) {
				String jointFileContent = ";SQ_SIMULATION;SQ_TEST;BASELINE;\n";
				int simulationScore = 0;
				int testSetScore = 0;
				
				// If there are no donors, The transplantation will be unique and innocuous/empty
				boolean noDonors = true;
				for (int t = 0; noDonors && t < donorFiles.length; ++t) {
					if (!donorFiles[t].isDirectory())
					{
						noDonors = false;
					}
				}
				
				// Test host with every donor available or without donors if there are no donors
				for (int d = 0; noDonors ? (d < HOST_NUMBER_OF_REPETITIONS) : (d < donorFiles.length); ++d) {
					// Donor file name
					boolean isValidDonorFileName = !noDonors;
					String donorFileName = isValidDonorFileName ? donorFiles[d].getName() : "";
					
					// Skip files with valid name but directory nature
					if (isValidDonorFileName && donorFiles[d].isDirectory()) {
						continue;
					}
					
					// Transplant selected pair: hostFiles[h] and donorFiles[d]
					System.out.println(
							"Transplantation: " + hostFiles[h].getName() + " <- " + donorFileName);
					Transplantation<KromaiaModel> transplantation = new Transplantation<>(KromaiaModel.class)
							.load(Transplantation.PatientType.HOST,
									ROOT_FILE_LOAD + SUBPATH_HOSTS + hostFiles[h].getName());
					if (isValidDonorFileName)
					{
						transplantation = transplantation
								.load(Transplantation.PatientType.DONOR, ROOT_FILE_LOAD + SUBPATH_DONORS + donorFileName);
					}
					KromaiaModel hostPrime =
							isValidDonorFileName ?
							transplantation.perform() :
							transplantation.performWithoutDonor();

					// Evolutionary algorithm executions
					String timeLimit = "2m30s";
					String tagPrefix = "[H-" + hostFiles[h].getName().replaceAll(FILE_EXTENSION, "") + "]_[D-"
							+ donorFileName.replaceAll(FILE_EXTENSION, "") + "]_";

					
					
					String leader1Encoding = "";
					String runTypeTag1 = tagPrefix + "[SIMULATION]";
					TinyEvo tinyEvo1 = new TinyEvo();
//					tinyEvo1.mSettings.SetCustomPrefixForOutputFileNames(runTypeTag1);
//					tinyEvo1.mSettings.SetAlternativeFitnessUsed(false);
//					tinyEvo1.mSettings.SetPCGBaselineUsed(false);
//					tinyEvo1.mSettings.SetInitialPopulationCompletedWithSeedClones(true);
//					tinyEvo1.mSettings.SetMaximumPopulationSize(100);
//					tinyEvo1.mSettings.SetTimeLimit(timeLimit);
//					tinyEvo1.RunExample(
//							TinyEvo.TAG____SEEDS_ENCODED_IN_TEST_NAME + hostPrime.getEncoding().getConfiguration(),
//							CONFIGURATION_ROW_LENGTH);
//					leader1Encoding = tinyEvo1.GetEncodedPopulationLeader();

			
			
					String leader2Encoding = "";
					String runTypeTag2 = tagPrefix + "[TESTSET]";
					TinyEvo tinyEvo2 = new TinyEvo();
//					tinyEvo2.mSettings.SetCustomPrefixForOutputFileNames(runTypeTag2);
//					tinyEvo2.mSettings.SetAlternativeFitnessUsed(true);
//					tinyEvo2.mSettings.SetPCGBaselineUsed(false);
//					tinyEvo2.mSettings.SetInitialPopulationCompletedWithSeedClones(true);
//					tinyEvo2.mSettings.SetMaximumPopulationSize(100);
//					tinyEvo2.mSettings.SetTimeLimit(timeLimit);
//					tinyEvo2.RunExample(
//							TinyEvo.TAG____SEEDS_ENCODED_IN_TEST_NAME + hostPrime.getEncoding().getConfiguration(),
//							CONFIGURATION_ROW_LENGTH);
//					leader2Encoding = tinyEvo2.GetEncodedPopulationLeader();
					
					
					
					String leader3Encoding = "";
					String runTypeTag3 = tagPrefix + "[PCGBASELINE]";
					TinyEvo tinyEvo3 = new TinyEvo();
					tinyEvo3.mSettings.SetCustomPrefixForOutputFileNames(runTypeTag3);
					tinyEvo3.mSettings.SetAlternativeFitnessUsed(true);
					tinyEvo3.mSettings.SetPCGBaselineUsed(true);
					tinyEvo3.mSettings.SetInitialPopulationCompletedWithSeedClones(true);
					tinyEvo3.mSettings.SetMaximumPopulationSize(100);
					tinyEvo3.mSettings.SetTimeLimit(timeLimit);
					tinyEvo3.RunExample(
							TinyEvo.TAG____SEEDS_ENCODED_IN_TEST_NAME + hostPrime.getEncoding().getConfiguration(),
							CONFIGURATION_ROW_LENGTH);
					leader3Encoding = tinyEvo3.GetEncodedPopulationLeader();
					
					
					
					String resultPresentation = "Solution proposal";
					System.out.println(resultPresentation + ": " + runTypeTag1 + ": " + leader1Encoding);
					System.out.println(resultPresentation + ": " + runTypeTag2 + ": " + leader2Encoding);
					System.out.println(resultPresentation + ": " + runTypeTag3 + ": " + leader3Encoding);


					// Joint file content
					jointFileContent +=
						donorFileName + ";" +
						(leader1Encoding == "" ? leader1Encoding : tinyEvo1.GetSubjectiveQualityFromPopulationLeader()) + ";" +
						(leader2Encoding == "" ? leader2Encoding : tinyEvo2.GetSubjectiveQualityFromPopulationLeader()) + ";" +
						(leader3Encoding == "" ? leader3Encoding : tinyEvo3.GetSubjectiveQualityFromPopulationLeader()) + "\n";
					
					// Preliminary review:
					// Comment this block below if tinyEvo1 and tinyEvo2 stuff is commented
//					if ( tinyEvo1.GetSubjectiveQualityFromPopulationLeader() > tinyEvo2.GetSubjectiveQualityFromPopulationLeader() ) {
//						++simulationScore;
//					}
//					else {
//						++testSetScore;
//					}
//					System.out.println( "<<<Preliminary versus result>>>\n" + simulationScore + " SIMULATION - TESTSET " + testSetScore );
					
					// Job done
				    tinyEvo1 = null;
					tinyEvo2 = null;
					tinyEvo3 = null;
					
				}
				
				
				// Subjective quality stored in a file (Fixed Host + every possible donor in  two columns: Simulation & Tests)
			    StreamOutputFile
			    (
			        "[H-" + hostFiles[h].getName().replaceAll(FILE_EXTENSION, "") + "]_" + "[VERSUS]_"
				    +
				    new SimpleDateFormat( "[dd-MM-yyyy]").format( new Date() ) + "_" + new SimpleDateFormat("[HH·mm·ss]").format( Calendar.getInstance().getTime() )
				    +
				    ".csv",
				    jointFileContent
			    );
			}
		}
	}
	
	
	private static void StreamOutputFile(String name, String content)
	{
	    PrintStream csvPrintStream = null;
	    try {
	        csvPrintStream =  new PrintStream(new FileOutputStream(name));
	    }
	    catch (FileNotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    System.setOut(csvPrintStream);
	    System.out.println(content);
	    if ( csvPrintStream != null ) {
	        csvPrintStream.close();
	        System.setOut( System.out ); 
	    }
	}
}
