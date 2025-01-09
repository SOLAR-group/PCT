package country.folder.group.imhotep.approach.simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.AbstractMap;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TinyEvo {
  /////////////////////////////////////////////////////////////////////////////

  public class TinyEvoSettings {
    private int mMaximumPopulationSize = 100;

    private long mTimeLimitHours = 0;

    private long mTimeLimitMinutes = 20;

    private long mTimeLimitSeconds = 0;

    private long mMaximumNumberOfSeconds = mTimeLimitHours * 3600 + mTimeLimitMinutes * 60 + mTimeLimitSeconds;

    private boolean mIsInitialPopulationCompletedWithSeedClones = false;

    private boolean mIsAlternativeFitnessUsed = false;
    
    private boolean mIsPCGBaselineUsed = false;

    private boolean mIsRandomSearch = false;

    private String mCustomPrefixForOutputFileNames = "";

    private long mRandomSearchIndividualsPending = 0;

    public void SetMaximumPopulationSize(int maximumPopulationSize) {
      mMaximumPopulationSize = maximumPopulationSize;
    }

    public void SetTimeLimit(long hours, long minutes, long seconds) {
      mTimeLimitHours = hours;
      mTimeLimitMinutes = minutes;
      mTimeLimitSeconds = seconds;
      mMaximumNumberOfSeconds = mTimeLimitHours * 3600 + mTimeLimitMinutes * 60 + mTimeLimitSeconds;
    }

    public void SetTimeLimit(String duration) {
      duration = duration.toLowerCase();

      Matcher matcherHours = Pattern.compile("([0-9]+)h", Pattern.CASE_INSENSITIVE).matcher(duration);
      long hours = matcherHours.find() ? Long.parseLong(matcherHours.group(0).split("[h]")[0]) : 0;

      Matcher matcherMinutes = Pattern.compile("([0-9]+)m", Pattern.CASE_INSENSITIVE).matcher(duration);
      long minutes = matcherMinutes.find() ? Long.parseLong(matcherMinutes.group(0).split("[m]")[0]) : 0;

      Matcher matcherSeconds = Pattern.compile("([0-9]+)s", Pattern.CASE_INSENSITIVE).matcher(duration);
      long seconds = matcherSeconds.find() ? Long.parseLong(matcherSeconds.group(0).split("[s]")[0]) : 0;

      SetTimeLimit(hours, minutes, seconds);
    }

    public void EnableRandomSearch(long numberOfIndividualsToGenerate) {
      mIsRandomSearch = true;
      mRandomSearchIndividualsPending = numberOfIndividualsToGenerate;
    }

    public void SetAlternativeFitnessUsed(boolean isAlternativeFitnessUsed) {
      mIsAlternativeFitnessUsed = isAlternativeFitnessUsed;
    }
    
    public void SetPCGBaselineUsed(boolean isPCGBaselineUsed) {
      mIsPCGBaselineUsed = isPCGBaselineUsed;
    }

    public void SetCustomPrefixForOutputFileNames(String prefix) {
      mCustomPrefixForOutputFileNames = prefix;
    }

    public void SetInitialPopulationCompletedWithSeedClones(boolean isCompletedWithClones) {
      mIsInitialPopulationCompletedWithSeedClones = isCompletedWithClones;
    }
  }

  /////////////////////////////////////////////////////////////////////////////

  static public final String TAG____SEEDS_ENCODED_IN_TEST_NAME = "_SEEDS_"; // Tag found in running tests in which an
                                                                            // individual is actually encoded in the
                                                                            // test name

  /////////////////////////////////////////////////////////////////////////////

  public TinyEvoSettings mSettings; // Current settings

  private int mIndividualHistoryCount = 0; // Total number of individuals generated since this object was created

  static private int mPersistentIndividualCount = 0;

  static private int mPersistentInvalidCount = 0;

  private int mGenerationNumber = 1; // Number corresponding the last generation

  private long mStartTimeNanoseconds; // Start time in nanoseconds

  private PrintStream mDefaultOutput; // Default output

  private ArrayList<Map.Entry<Integer, Individual>> mPopulation; // Current population

  /////////////////////////////////////////////////////////////////////////////

  public TinyEvo() {
    mSettings = new TinyEvoSettings();
    mStartTimeNanoseconds = System.nanoTime();
    mDefaultOutput = System.out;
    // Initial population
    mPopulation = new ArrayList<Map.Entry<Integer, Individual>>();
  }

  /////////////////////////////////////////////////////////////////////////////

  static public void IncreasePersistentInvalidCount() {
    ++TinyEvo.mPersistentInvalidCount;
  }

  /////////////////////////////////////////////////////////////////////////////

  public String GetEncodedPopulationLeader() {
    return mPopulation.get(0).getValue().GetContentAsString();
  }
  
  /////////////////////////////////////////////////////////////////////////////

  public double GetSubjectiveQualityFromPopulationLeader() {
	  
    return mPopulation.get(0).getValue().GetCustomValue( "SubjectiveQuality", java.lang.Double.class );
  }

  /////////////////////////////////////////////////////////////////////////////

  void AddIndividual(Individual individual) {
    mPopulation.add(new AbstractMap.SimpleEntry<>(mIndividualHistoryCount, individual));
    mPopulation.get(mPopulation.size() - 1).getValue().SetAlternativeFitnessUsed(mSettings.mIsAlternativeFitnessUsed);
    ++mIndividualHistoryCount;
    ++mPersistentIndividualCount;
    if (mSettings.mIsRandomSearch && mSettings.mRandomSearchIndividualsPending > 0) {
      --mSettings.mRandomSearchIndividualsPending;
    }
  }

  /////////////////////////////////////////////////////////////////////////////

  int GetIndividualHistoryCount(Individual individual) {
    return mIndividualHistoryCount;
  }

  /////////////////////////////////////////////////////////////////////////////

  void SortPopulation() {
    Collections.sort(mPopulation, new Comparator<Map.Entry<Integer, Individual>>() {

      @Override
      public int compare(Map.Entry<Integer, Individual> first, Map.Entry<Integer, Individual> second) {
        return -Double.compare(first.getValue().GetFitnessValue(), second.getValue().GetFitnessValue());
      }

    });
  }

  /////////////////////////////////////////////////////////////////////////////

  void EvaluatePopulation() {
    for (int i = 0; i < mPopulation.size(); ++i) {
      mPopulation.get(i).getValue().UpdateFitnessValue();
    }
  }

  /////////////////////////////////////////////////////////////////////////////

  void CrossOverPopulation(float populationPercentage) {
    boolean areNewIndividualsCreated = false;
    int selectedPopulationSize = Math.max(2, (int) (populationPercentage * mPopulation.size()));
    for (int i = 0; i < selectedPopulationSize; ++i) {
      for (int j = i + 1; j < selectedPopulationSize; ++j) {
        AddIndividual(mPopulation.get(i).getValue().CrossOver(mPopulation.get(j).getValue()));
        areNewIndividualsCreated = true;
        mPopulation.get(mPopulation.size() - 1).getValue().Mutate();
      }
    }

    if (areNewIndividualsCreated) {
      ++mGenerationNumber;
    }
  }

  /////////////////////////////////////////////////////////////////////////////

  void ControlPopulation() {
    while (mPopulation.size() > mSettings.mMaximumPopulationSize) {
      mPopulation.remove(mPopulation.size() - 1);
    }
  }

  /////////////////////////////////////////////////////////////////////////////

  public void RunExample( String name, int encodingRowSize )
  {
    IndividualBossConfig.Quality averageSolutionQuality = new IndividualBossConfig.Quality();
    averageSolutionQuality.Q_Completion       = 0.0;
    averageSolutionQuality.Q_Duration         = 0.0;
    averageSolutionQuality.Q_Uncertainty      = 0.0;
    averageSolutionQuality.Q_KillerMoves      = 0.0;
    averageSolutionQuality.Q_Permanence       = 0.0;
    averageSolutionQuality.Q_LeadChange       = 0.0;
    averageSolutionQuality.Q_Overall          = 0.0;
    averageSolutionQuality.S_Subjective1      = 0.0;
    averageSolutionQuality.S_Overall          = 0.0;
   
    
    ArrayList< IndividualBossConfig.Quality > populationQuality = new ArrayList< IndividualBossConfig.Quality >();
    
    int numberOfRuns = 1;
    
    for ( int h = 0; h < numberOfRuns; ++h ) {

      // Reset
      populationQuality.clear();
      mPopulation.clear();
      mIndividualHistoryCount = 0;
      mGenerationNumber = 1;
      mStartTimeNanoseconds = System.nanoTime();
      
      // Certain names are shortcuts which let the algorithm specify certain initial individuals in the population
      
      if ( name.contains( TAG____SEEDS_ENCODED_IN_TEST_NAME ) ) {
    	// IMHOTEP
        // Case: Tag found: The name is actually a collection of encoded individual that are bounded by separators
        // Tag removal and individual compilation
        String[] encodedIndividuals = name.replaceAll( TAG____SEEDS_ENCODED_IN_TEST_NAME, "" ).split( "[ ]" );
        for ( int i = 0; i < encodedIndividuals.length; ++i ) {
          for ( int j = 0; j < mSettings.mMaximumPopulationSize / 2; ++j ) {
            AddIndividual
            (
              mSettings.mIsPCGBaselineUsed ?
              ( new IndividualBossConfigPCGBaseline( encodedIndividuals[i] ) ) :
              ( new IndividualBossConfig( encodedIndividuals[i] ) )
            );
          }
        }
      }
      else {
    	// NOT IMHOTEP
	    if ( name == "FullBosses" ) {
	      // Vermis
	      AddIndividual( new IndividualBossConfig( "011010101000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000001010100000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111111111111111111111111111111111111111111111111111111111----------------------------------------------------------------0000000000000000000000000000000000000000000000000000000000000000" ) );
	      // Teuthus
	      AddIndividual( new IndividualBossConfig( "011100000111000001110000010000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001000000000010000000100000000000000000000000000000000000000000000000000000010000000100000000000000000000000000000000000000000000001000000010000000100000001000001000000000000000000000000000000001111111111111111111111111111111100000000000000000000000000000000----------------------------------------------------------------0000000000000000000000000000000000000000000000000000000000000000" ) );
	      // Argos
	      AddIndividual( new IndividualBossConfig( "011111111001111111111111111111111111000000000000000000000000000000000000000111111111100000000000001100000000000000000000000000001000000000000000000000000000000011000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111000000000000000000000000000000000000000000000000000000000001111111111111111111111111111111111110000000000000000000000000000----------------------------------------------------------------0000000000000000000000000000000000000000000000000000000000000000" ) );
	      // Orion
	      AddIndividual( new IndividualBossConfig( "111111111111111111111111111111111111111111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000001010101010001100000000000000111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111111111111111111111111111111111111111111111111110000000----------------------------------------------------------------0000000000000000000000000000000000000000000000000000000000000000" ) );
	      // Maia
	      AddIndividual( new IndividualBossConfig( "111111111110000000000000000000000000000000000000000000000000000000000001111000000000000000000000000000000000000000000000000000000000011111100000000000000000000000000000000000000000000000000000000001111110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111100000000000000000000000000000000000000000000000000000----------------------------------------------------------------0000000000000000000000000000000000000000000000000000000000000000" ) );
	    }
	    else if ( name == "MiniBosses" ) {
	      // VermisChild
	      AddIndividual( new IndividualBossConfig( "111111111100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001000100001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111000000000000000000000000000000000000000000000000000000-012345678------------------------------------------------------0000000000000000000000000000000000000000000000000000000000000000" ) );
	      // DaimonDelta
	      AddIndividual( new IndividualBossConfig( "011111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000101010101000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111110000000000000000000000000000000000000000000000000000000-01030507-------------------------------------------------------0000000000000000000000000000000000000000000000000000000000000000" ) );
	      // DaimonTrial
	      AddIndividual( new IndividualBossConfig( "011111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010010000000110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000001111111111111111111110000000000000000000000000000000000000000000-00000000000000000000-------------------------------------------0000000000000000000000000000000000000000000000000000000000000000" ) );
	      // DaimonTurretCompound
	      AddIndividual( new IndividualBossConfig( "011111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001100100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111110000000000000000000000000000000000000000000000000000000-01110555-------------------------------------------------------0000000000000000000000000000000000000000000000000000000000000000" ) );
	      // DaimonZeta
	      AddIndividual( new IndividualBossConfig( "010101111000011110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000001111111111111111111110000000000000000000000000000000000000000000-0103000067580000efdg-------------------------------------------0000000000000000000000000000000000000000000000000000000000000000" ) );
	    }   
	    else if ( name == "FullBoss" ) {
	      // DaimonTrial
	      AddIndividual( new IndividualBossConfig( "011111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010010000000110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000001111111111111111111110000000000000000000000000000000000000000000-00000000000000000000-------------------------------------------0000000000000000000000000000000000000000000000000000000000000000" ) );
	    }
	    else if ( name == "MiniBoss" ) {
	      // DaimonDelta
	        AddIndividual( new IndividualBossConfig( "011111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000101010101000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111110000000000000000000000000000000000000000000000000000000-01030507-------------------------------------------------------0000000000000000000000000000000000000000000000000000000000000000" ) );
	    }
      }

      
      // Population completion with randomly generated individuals, if there is room left
      
      float oneProbability = 0.0f;
      for ( int i = mPopulation.size();
            !mSettings.mIsRandomSearch && mPopulation.size() < mSettings.mMaximumPopulationSize && i < mSettings.mMaximumPopulationSize;
            ++i ) {
        
        if ( this.mSettings.mIsInitialPopulationCompletedWithSeedClones ) {
          IndividualBossConfig seedClone =
            mSettings.mIsPCGBaselineUsed ?
            new IndividualBossConfigPCGBaseline(  mPopulation.get( 0 ).getValue().GetCustomValue( "Configuration", java.lang.String.class ) ) :
        	new IndividualBossConfig(  mPopulation.get( 0 ).getValue().GetCustomValue( "Configuration", java.lang.String.class ) );
          seedClone.Mutate();
          AddIndividual( seedClone );
        }
        else {
          if ( i < 33 ) { oneProbability = 0.1f * 0.5f; }
          if ( i < 66 ) { oneProbability = 0.1f; }
          else          { oneProbability = 0.1f * 2.0f; }
          
          if ( name != "MiniBosses" && name != "MiniBoss" && name != "FullBosses" && name != "FullBoss" ) {
            AddIndividual
            (
              mSettings.mIsPCGBaselineUsed ?
              new IndividualBossConfigPCGBaseline( encodingRowSize, oneProbability ) :
              new IndividualBossConfig( encodingRowSize, oneProbability )
            );
          }
        }
      }
      // Initial population ready
  
      // Evaluation
      EvaluatePopulation();
      // Ranking
      SortPopulation();
      
      // First step done
      
      // Evolutionary algorithm iterations
      if ( mSettings.mIsRandomSearch ) {
        while ( mSettings.mRandomSearchIndividualsPending > 0 ) {
          if ( ( mSettings.mRandomSearchIndividualsPending % 3 ) == 0 ) { oneProbability = 0.1f * 0.5f; }
          if ( ( mSettings.mRandomSearchIndividualsPending % 3 ) == 1 ) { oneProbability = 0.1f; }
          else          { oneProbability = 0.1f * 2.0f; }
          AddIndividual
          (
            mSettings.mIsPCGBaselineUsed ?
            new IndividualBossConfigPCGBaseline( encodingRowSize, oneProbability ):
            new IndividualBossConfig( encodingRowSize, oneProbability )
          );
          EvaluatePopulation();
          SortPopulation();
          ControlPopulation();
        }
      }
      else {
        while ( mPopulation.size() > 1 && /*mPopulation.get( 0 ).getValue().GetFitnessValue() < 0.9*/( TimeUnit.NANOSECONDS.toSeconds( System.nanoTime() - mStartTimeNanoseconds ) <= mSettings.mMaximumNumberOfSeconds ) ) {   
          CrossOverPopulation( 0.10f );
          EvaluatePopulation();
          SortPopulation();
          ControlPopulation();
        }
      }
      
      // Run finished
      System.out.println( "Population after run:" );
      for ( int c = 0; c < mPopulation.size(); ++c ) {
        System.out.println( mPopulation.get( c ).getValue().GetContentAsString() );
      }
      System.out.println( "Solution found in Generation: " + mGenerationNumber );
      System.out.println( mPopulation.get( 0 ).getValue().GetDescriptionString() );
    
      IndividualBossConfig.Quality solutionQuality = ( ( IndividualBossConfig ) ( mPopulation.get( 0 ).getValue() ) ).GetQuality();
      averageSolutionQuality.Q_Completion       += solutionQuality.Q_Completion;
      averageSolutionQuality.Q_Duration         += solutionQuality.Q_Duration;
      averageSolutionQuality.Q_Uncertainty      += solutionQuality.Q_Uncertainty;
      averageSolutionQuality.Q_KillerMoves      += solutionQuality.Q_KillerMoves;
      averageSolutionQuality.Q_Permanence       += solutionQuality.Q_Permanence;
      averageSolutionQuality.Q_LeadChange       += solutionQuality.Q_LeadChange;
      averageSolutionQuality.Q_Overall          += solutionQuality.Q_Overall;
      averageSolutionQuality.S_Subjective1      += solutionQuality.S_Subjective1;
      averageSolutionQuality.S_Overall          += solutionQuality.S_Overall;
      
      
      for ( int p = 0; p < mPopulation.size(); ++p ) {
        populationQuality.add( new IndividualBossConfig.Quality() );
        populationQuality.get( populationQuality.size() - 1 ).Q_Completion       = 0.0;
        populationQuality.get( populationQuality.size() - 1 ).Q_Duration         = 0.0;
        populationQuality.get( populationQuality.size() - 1 ).Q_Uncertainty      = 0.0;
        populationQuality.get( populationQuality.size() - 1 ).Q_KillerMoves      = 0.0;
        populationQuality.get( populationQuality.size() - 1 ).Q_Permanence       = 0.0;
        populationQuality.get( populationQuality.size() - 1 ).Q_LeadChange       = 0.0;
        populationQuality.get( populationQuality.size() - 1 ).Q_Overall          = 0.0;
        populationQuality.get( populationQuality.size() - 1 ).S_Subjective1      = 0.0;
        populationQuality.get( populationQuality.size() - 1 ).S_Overall          = 0.0;
      }      
      for ( int p = 0; p < populationQuality.size(); ++p ) {
        IndividualBossConfig.Quality quality = ( ( IndividualBossConfig ) ( mPopulation.get( p ).getValue() ) ).GetQuality();
        populationQuality.get( p ).Q_Completion       += quality.Q_Completion;
        populationQuality.get( p ).Q_Duration         += quality.Q_Duration;
        populationQuality.get( p ).Q_Uncertainty      += quality.Q_Uncertainty;
        populationQuality.get( p ).Q_KillerMoves      += quality.Q_KillerMoves;
        populationQuality.get( p ).Q_Permanence       += quality.Q_Permanence;
        populationQuality.get( p ).Q_LeadChange       += quality.Q_LeadChange;
        populationQuality.get( p ).Q_Overall          += quality.Q_Overall;
        populationQuality.get( p ).S_Subjective1      += quality.S_Subjective1;
        populationQuality.get( p ).S_Overall          += quality.S_Overall;
      }
      
      // Run finished and measured
    }
    
    
    // Every run done. Average results...
    
    for ( int p = 0; p < populationQuality.size(); ++p ) {
      populationQuality.get( p ).Q_Completion       /= ( double ) numberOfRuns;
      populationQuality.get( p ).Q_Duration         /= ( double ) numberOfRuns;
      populationQuality.get( p ).Q_Uncertainty      /= ( double ) numberOfRuns;
      populationQuality.get( p ).Q_KillerMoves      /= ( double ) numberOfRuns;
      populationQuality.get( p ).Q_Permanence       /= ( double ) numberOfRuns;
      populationQuality.get( p ).Q_LeadChange       /= ( double ) numberOfRuns;
      populationQuality.get( p ).Q_Overall          /= ( double ) numberOfRuns;
      populationQuality.get( p ).S_Subjective1      /= ( double ) numberOfRuns;
      populationQuality.get( p ).S_Overall          /= ( double ) numberOfRuns;
    }
    
    averageSolutionQuality.Q_Completion       /= ( double ) numberOfRuns;
    averageSolutionQuality.Q_Duration         /= ( double ) numberOfRuns;
    averageSolutionQuality.Q_Uncertainty      /= ( double ) numberOfRuns;
    averageSolutionQuality.Q_KillerMoves      /= ( double ) numberOfRuns;
    averageSolutionQuality.Q_Permanence       /= ( double ) numberOfRuns;
    averageSolutionQuality.Q_LeadChange       /= ( double ) numberOfRuns;
    averageSolutionQuality.Q_Overall          /= ( double ) numberOfRuns;
    averageSolutionQuality.S_Subjective1      /= ( double ) numberOfRuns;
    averageSolutionQuality.S_Overall          /= ( double ) numberOfRuns;
    
    System.out.println( "------------\nHulls: " + encodingRowSize + "\nQuality:" );
    System.out.println( "Q_Completion: " + averageSolutionQuality.Q_Completion );
    System.out.println( "Q_Duration: " + averageSolutionQuality.Q_Duration );
    System.out.println( "Q_Uncertainty: " + averageSolutionQuality.Q_Uncertainty );
    System.out.println( "Q_KillerMoves: " + averageSolutionQuality.Q_KillerMoves );
    System.out.println( "Q_Permanence: " + averageSolutionQuality.Q_Permanence );
    System.out.println( "Q_LeadChange: " + averageSolutionQuality.Q_LeadChange );
    System.out.println( "Q_Overall: " + averageSolutionQuality.Q_Overall );
    System.out.println( "S_Subjective1: " + averageSolutionQuality.S_Subjective1 );
    System.out.println( "S_Overall: " + averageSolutionQuality.S_Overall );
    System.out.println( "--------------------------------------------------\n" );
    System.out.println( "Valid Generation Ratio: " + ( 1.0 - ( double ) mPersistentInvalidCount / (double ) mPersistentIndividualCount ) );
    System.out.println( "--------------------------------------------------\n" );
    System.out.println( "--------------------------------------------------\n" );
    System.out.println( "--------------------------------------------------\n" );
    
    
    
    
    // File
    if ( name != "" ) {
      PrintStream csvPrintStream = null;
      try {
        csvPrintStream =
          new PrintStream
          (
            new FileOutputStream
            (
              mSettings.mCustomPrefixForOutputFileNames
              +
              ( mSettings.mCustomPrefixForOutputFileNames == "" ? "" : "_" )
              +
              (
                name.length() < 100 ?
                name :
                new SimpleDateFormat( "[dd-MM-yyyy]").format( new Date() ) + "_" + new SimpleDateFormat("[HH·mm·ss]").format( Calendar.getInstance().getTime() )
              )
              +
              ".csv"
            )
          );
      }
      catch ( FileNotFoundException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      System.setOut( csvPrintStream );
      
      System.out.println( ";Q_1;Q_2;Q_3;Q_4;Q_5;Q_6;Q_7;S1;S_2;TransplantRootHullIndex;Encoding;" );
      for ( int j = 0; j < populationQuality.size(); ++j ) {
        System.out.println(
          name + ";" +
          populationQuality.get( j ).Q_Completion + ";" +
          populationQuality.get( j ).Q_Duration + ";" +
          populationQuality.get( j ).Q_Uncertainty + ";" +
          populationQuality.get( j ).Q_KillerMoves + ";" +
          populationQuality.get( j ).Q_Permanence + ";" +
          populationQuality.get( j ).Q_LeadChange + ";" +
          populationQuality.get( j ).Q_Overall + ";" +
          populationQuality.get( j ).S_Subjective1 + ";" +
          populationQuality.get( j ).S_Overall + ";" +
          mPopulation.get( j ).getValue().GetCustomValue( "TransplantRootHullIndex", java.lang.Integer.class ) + ";" +
          mPopulation.get( j ).getValue().GetContentAsString()
        );
      }
      
      if ( csvPrintStream != null ) {
          csvPrintStream.close();
          System.setOut( mDefaultOutput ); 
      }
      
      
      
      
      System.out.println( "Population after run:" );
      for ( int c = 0; c < mPopulation.size(); ++c ) {
        System.out.println( mPopulation.get( c ).getValue().GetContentAsString() );
      }
      System.out.println( "Solution found in Generation: " + mGenerationNumber );
      System.out.println( mPopulation.get( 0 ).getValue().GetDescriptionString() );
      System.out.println( "------------\nHulls: " + encodingRowSize + "\nQuality:" );
      System.out.println( "Q_Completion: " + averageSolutionQuality.Q_Completion );
      System.out.println( "Q_Duration: " + averageSolutionQuality.Q_Duration );
      System.out.println( "Q_Uncertainty: " + averageSolutionQuality.Q_Uncertainty );
      System.out.println( "Q_KillerMoves: " + averageSolutionQuality.Q_KillerMoves );
      System.out.println( "Q_Permanence: " + averageSolutionQuality.Q_Permanence );
      System.out.println( "Q_LeadChange: " + averageSolutionQuality.Q_LeadChange );
      System.out.println( "Q_Overall: " + averageSolutionQuality.Q_Overall );
      System.out.println( "S_Subjective1: " + averageSolutionQuality.S_Subjective1 );
      System.out.println( "S_Overall: " + averageSolutionQuality.S_Overall );
      System.out.println( "--------------------------------------------------\n" );
      System.out.println( "Valid Generation Ratio: " + ( 1.0 - ( double ) mPersistentInvalidCount / (double ) mPersistentIndividualCount ) );
      System.out.println( "--------------------------------------------------\n" );
      System.out.println( "--------------------------------------------------\n" );
      System.out.println( "--------------------------------------------------\n" );
    }
    
    
    
    
  }

  /////////////////////////////////////////////////////////////////////////////
}
