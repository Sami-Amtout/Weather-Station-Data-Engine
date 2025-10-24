// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Sami Amtout (samiamtout)
import student.micro.*;
import static org.assertj.core.api.Assertions.*;
import java.util.Scanner;

// -------------------------------------------------------------------------
/**
 *  Tests class for my WeatherBureau class
 *
 *  @author Sami Amtout (samiamtout)
 *  @version 2025.05.03
 */
public class WeatherBureauTest
    extends TestCase
{
    //~ Fields ................................................................


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new WeatherBureauTest test object.
     */
    public WeatherBureauTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
        /*# Insert your own setup code here */
    }


    // ----------------------------------------------------------
    /*# Insert your own test methods here */
    /**
     * Tests a single recorded daily summary
     */
    public void testRecordSingleDailySummary()
    {
        WeatherBureau bureau = new WeatherBureau();
        bureau.recordDailySummary("Station1 4 15 2.5");
        WeatherStation station = bureau.getStation("Station1");
        
        assertThat(station).isNotNull();
        assertThat(station.getId()).isEqualTo("Station1");
        assertThat(station.getCountForMonth(4)).isEqualTo(1);
        assertThat(station.getAvgForMonth(4)).isEqualTo(2.5, within(0.001));
    }
    /**
     * Test ignoring missing data
     */
    public void testIgnoreMissingData()
    {
        WeatherBureau bureau = new WeatherBureau();
        bureau.recordDailySummary("Station2 5 10 -9999");
        WeatherStation station = bureau.getStation("Station2");
        
        assertThat(station).isNull();
    }
    /**
     * Tests a record with multiple summaries
     */
    public void testRecordManySummaries()
    {
        WeatherBureau bureau = new WeatherBureau();
        Scanner input = new Scanner(
            "Station1 1 1 1.0\n" +
            "Station1 1 2 3.0\n" +
            "Station2 2 1 2.0\n"
        );
        bureau.recordDailySummaries(input);
        
        WeatherStation station1 = bureau.getStation("Station1");
        WeatherStation station2 = bureau.getStation("Station2");
        
        assertThat(station1.getCountForMonth(1)).isEqualTo(2);
        assertThat(station1.getAvgForMonth(1)).isEqualTo(2.0, within(0.001));
        assertThat(station2.getCountForMonth(2)).isEqualTo(1);
        assertThat(station2.getAvgForMonth(2)).isEqualTo(2.0, within(0.001));
    }
    /**
     * Tests lowest station for given month
     */
    public void testLowestStationForMonth()
    {
        WeatherBureau bureau = new WeatherBureau();
        Scanner input = new Scanner(
            "Station1 6 5 1.0\n" +
            "Station2 6 5 0.5\n" +
            "Station2 6 6 1.5\n"
        );
        bureau.recordDailySummaries(input);
        WeatherStation lowest = bureau.lowestStation(6);
        
        assertThat(lowest).isNotNull();
        assertThat(lowest.getId()).isEqualTo("Station2");
    }
    /**
     * Tests lowest station
     */
    public void testLowestStation()
    {
        WeatherBureau bureau = new WeatherBureau();
        Scanner input = new Scanner(
            "Station1 2 3 1.0\n" +
            "Station2 5 10 0.1\n"
        );
        bureau.recordDailySummaries(input);
        WeatherStation lowest = bureau.lowestStation();

        assertThat(lowest).isNotNull();
        assertThat(lowest.getId()).isEqualTo("Station2");
    }
    /**
     * Tests lowest station with no data
     */
    public void testLowestStationWithNoData()
    {
        WeatherBureau bureau = new WeatherBureau();
        WeatherStation lowest = bureau.lowestStation();
        
        assertThat(lowest).isNull();
    }
    /**
     * Test for a given month with no data
     */
    public void testLowestStationMonthWithNoData()
    {
        WeatherBureau bureau = new WeatherBureau();
        WeatherStation result = bureau.lowestStation(3);
        
        assertThat(result).isNull();
    }
    /**
     * Test for the first valid weather station
     */
    public void testFirstValidAverage()
    {
        WeatherBureau bureau = new WeatherBureau();
        bureau.recordDailySummary("Station1 3 1 2.0");
        WeatherStation result = bureau.lowestStation(3);
        
        assertThat(result.getId()).isEqualTo("Station1");
    }
    /**
     * Test to keep the lowest station average
     */
    public void testKeepLowestStation()
    {
        WeatherBureau bureau = new WeatherBureau();
        bureau.recordDailySummary("Station1 4 1 1.0");
        bureau.recordDailySummary("Station2 4 2 2.0");
        WeatherStation result = bureau.lowestStation(4);
        
        assertThat(result.getId()).isEqualTo("Station1");
    }
    /**
     * Tests no valid data
     */
    public void testNoValidData()
    {
        WeatherBureau bureau = new WeatherBureau();
        assertThat(bureau.lowestStation(5)).isNull();
    }
    /**
     * Test valid average
     */
    public void tesValidAverage()
    {
        WeatherBureau bureau = new WeatherBureau();
        bureau.recordDailySummary("Station1 6 1 2.0");
        WeatherStation result = bureau.lowestStation(6);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("Station1");
    }
    /**
     * Tests highest average
     */
    public void testHighestAverage()
    {
        WeatherBureau bureau = new WeatherBureau();
        bureau.recordDailySummary("Station1 7 1 1.0");
        bureau.recordDailySummary("Station2 7 2 3.0");
        WeatherStation result = bureau.lowestStation(7);
        
        assertThat(result.getId()).isEqualTo("Station1");
    }
    /**
     * Tests lowest station with no recorded rainfall
     */
    public void testLowestStationWithNoRainfall()
    {
        WeatherBureau bureau = new WeatherBureau();
        WeatherStation result = bureau.lowestStation();
        assertThat(result).isNull();
    }
    /**
     * Tests lowest station with the first valid month
     */
    public void testLowestStationWithTheFirstValidMonth()
    {
        WeatherBureau bureau = new WeatherBureau();
        bureau.recordDailySummary("Station1 4 1 2.0");
        WeatherStation result = bureau.lowestStation();
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("Station1");
    }
    /**
     * Test where lowest average is kept
     */
    public void testKeepLowestAverageStation()
    {
        WeatherBureau bureau = new WeatherBureau();
        bureau.recordDailySummary("Station1 2 1 1.0");
        bureau.recordDailySummary("Station2 3 1 5.0");
        WeatherStation result = bureau.lowestStation();
        assertThat(result.getId()).isEqualTo("Station1");
    }
    /**
     * Tests station with no data
     */
    public void testStationNoData()
    {
        WeatherBureau bureau = new WeatherBureau();
        WeatherStation result = bureau.lowestStation(3);
        assertThat(result).isNull();
    }
    /**
     * Tests the first valid month
     */
    public void testMonthWithValidAverage()
    {
        WeatherBureau bureau = new WeatherBureau();
        bureau.recordDailySummary("Station1 3 1 2.0");
        WeatherStation result = bureau.lowestStation(3);
        assertThat(result.getId()).isEqualTo("Station1");
    }
    /**
     * Test to keep the lowest station report
     */
    public void testKeepTheLowestStation()
    {
        WeatherBureau bureau = new WeatherBureau();
        bureau.recordDailySummary("Station1 4 1 2.0");
        bureau.recordDailySummary("Station2 4 1 1.0");
        WeatherStation result = bureau.lowestStation(4);
        assertThat(result.getId()).isEqualTo("Station2");
    }
    /**
     * Test where not replacing the lowest average
     */
    public void testNotReplacingLowestAverage()
    {
        WeatherBureau bureau = new WeatherBureau();
        bureau.recordDailySummary("Station1 4 1 1.0");
        bureau.recordDailySummary("Station2 4 1 3.0");
        WeatherStation result = bureau.lowestStation(4);
        assertThat(result.getId()).isEqualTo("Station1");
    }
    /**
     * 
     */
    public void testAverage()
    {
        WeatherBureau bureau = new WeatherBureau();
        bureau.recordDailySummary("Station1 4 1 2.0");
        bureau.recordDailySummary("Station2 4 1 2.0");
        WeatherStation result = bureau.lowestStation(4);
        assertThat(result.getId()).isEqualTo("Station2");
    }
    /**
     * Tests a station with no data
     */
    public void testAverageWithNoData()
    {
        WeatherBureau bureau = new WeatherBureau();
        bureau.recordDailySummary("Station1 4 1 2.0");
        WeatherStation result = bureau.lowestStation(5);
        assertThat(result).isNull();
    }
    /**
     * Tests recording a weather station
     */
    public void testRecord()
    {
        WeatherBureau bureau = new WeatherBureau();
        String str = "Station1 100 0.0 0.0 20230907 4.2 0 0 0";
        bureau.recordDailySummary(str);
        WeatherStation station = bureau.getStation("Station1");
        assertThat(station.getCountForMonth(9)).isEqualTo(1);
        assertThat(station.getAvgForMonth(9)).isEqualTo(4.2, within(0.001));
    }
}
