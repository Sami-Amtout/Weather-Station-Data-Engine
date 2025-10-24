// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Sami Amtout (samiamtout)
import student.micro.*;
import static org.assertj.core.api.Assertions.*;

// -------------------------------------------------------------------------
/**
 *  Test class for my WeatherStation class
 *
 *  @author Sami Amtout (samiamtout)
 *  @version 2025.05.03
 */
public class WeatherStationTest
    extends TestCase
{
    //~ Fields ................................................................


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new WeatherStationTest test object.
     */
    public WeatherStationTest()
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
     * Tests daily rain and average
     */
    public void testRecordDailyRainAndAverage()
    {
        WeatherStation station = new WeatherStation("Station1");
        station.recordDailyRain(1, 5.0);
        station.recordDailyRain(1, 7.0);
        
        assertThat(station.getCountForMonth(1)).isEqualTo(2);
        assertThat(station.getAvgForMonth(1)).isEqualTo(6.0, within(0.001));
    }
    /**
     * Tests no rain fall recorded
     */
    public void testNoRainfallRecorded()
    {
        WeatherStation station = new WeatherStation("Station2");
        
        assertThat(station.getCountForMonth(2)).isEqualTo(0);
        assertThat(station.getAvgForMonth(2)).isEqualTo(-1);
    }
    /**
     * Tests lowest month
     */
    public void testLowestMonth()
    {
        WeatherStation station = new WeatherStation("Station3");
        station.recordDailyRain(3, 1.0);
        station.recordDailyRain(5, 5.0);
        station.recordDailyRain(5, 5.0);
        
        assertThat(station.getLowestMonth()).isEqualTo(3);
    }
    /**
     * Tests lowest month with no data
     */
    public void testLowestMonthWithNoData()
    {
        WeatherStation station = new WeatherStation("Station4");
        
        assertThat(station.getLowestMonth()).isEqualTo(1);
    }
    /**
     * Tests with zerio rain fall
     */
    public void testZeroRainfall()
    {
        WeatherStation station = new WeatherStation("Station5");
        station.recordDailyRain(12, 0.0);
        
        assertThat(station.getAvgForMonth(12)).isEqualTo(0.0, within(0.001));
        assertThat(station.getLowestMonth()).isEqualTo(12);
    }
    /**
     * Tests an invalid month
     */
    public void testInvalidMonth()
    {
        WeatherStation station =  new WeatherStation("X");
        station.recordDailyRain(0, 1.0);
        station.recordDailyRain(13, 2.0);
        
        assertThat(station.getCountForMonth(1)).isEqualTo(0);
        assertThat(station.getAvgForMonth(1)).isEqualTo(-1);
    }
    /**
     * Tests the lowest Station with No Data
     */
    public void testLowestStationWithNoData()
    {
        WeatherBureau bureau = new WeatherBureau();
        WeatherStation result = bureau.lowestStation(4);
        assertThat(result).isNull();
    }
    /**
     * Tests count invalid months(0 & 13)
     */
    public void testCountForInvalidMonth()
    {
        WeatherStation station = new WeatherStation("Station");
        
        assertThat(station.getCountForMonth(0)).isEqualTo(0); 
        assertThat(station.getCountForMonth(13)).isEqualTo(0);
    }
    /**
     * Tests average for invalid months(0 & 13)
     */
    public void testAvgForInvalidMonth()
    {
        WeatherStation station = new WeatherStation("Station");
        
        assertThat(station.getAvgForMonth(0)).isEqualTo(-1);
        assertThat(station.getAvgForMonth(13)).isEqualTo(-1);
    }
    /**
     * Test to keep the lowest month for weather station
     */
    public void testKeepingLowestMonth()
    {
        WeatherStation station = new WeatherStation("Station");
        station.recordDailyRain(3, 1.0);
        station.recordDailyRain(4, 5.0);
        assertThat(station.getLowestMonth()).isEqualTo(3);
    }
    /**
     * Test to not update if the average is higher
     */
    public void testAverageIsHigher()
    {
        WeatherStation station = new WeatherStation("Station");
        station.recordDailyRain(2, 1.0);
        station.recordDailyRain(5, 5.0);
        
        assertThat(station.getLowestMonth()).isEqualTo(2);
    }
    /**
     * Test to not update if the average is seen as higher
     * or equal to
     */
    public void testAverageHigherOrEqual()
    {
        WeatherStation station = new WeatherStation("station");
        station.recordDailyRain(1, 1.0);
        station.recordDailyRain(2, 3.0);
        station.recordDailyRain(3, 1.0);
        assertThat(station.getLowestMonth()).isEqualTo(1);
    }
    /**
     * Test for not updating the average if it's equal or higher
     */
    public void testAverageEqualOrHigher()
    {
        WeatherStation station = new WeatherStation("station");
        station.recordDailyRain(1, 2.0);
        station.recordDailyRain(2, 3.0);
        station.recordDailyRain(3, 2.0);
        assertThat(station.getLowestMonth()).isEqualTo(1);
    }
    /**
     * Test
     */
    public void testFirstValidMonth()
    {
        WeatherStation station = new WeatherStation("Station");
        station.recordDailyRain(4, 2.5);
        assertThat(station.getLowestMonth()).isEqualTo(4);
    }
    /**
     * Test
     */
    public void testAverageLessThanLowestAverage()
    {
        WeatherStation station = new WeatherStation("station");
        station.recordDailyRain(5, 4.0);
        station.recordDailyRain(7, 1.0);
        assertThat(station.getLowestMonth()).isEqualTo(7);
    }
    /**
     * Test
     */
    public void testAverageGreaterThanLowestAverage()
    {
        WeatherStation station = new WeatherStation("station");
        station.recordDailyRain(1, 2.0);
        station.recordDailyRain(2, 3.0);
        station.recordDailyRain(3, 2.0);
        assertThat(station.getLowestMonth()).isEqualTo(1);
    }
    
}
