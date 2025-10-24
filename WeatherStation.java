// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Sami Amtout (samiamtout)
//-------------------------------------------------------------------------
/**
 *  Represents a weather service that keeps track of all the weather
 *  stations.
 *
 *  @author Sami Amtout (samiamtout)
 *  @version 2025.05.03
 */
public class WeatherStation
{
    //~ Fields ................................................................
    private String stationId;
    private double[] monthlyTotals;
    private int[] monthlyCounts;


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Initializes a newly created WeatherStation object.
     * @param identifier ID for weather station
     */
    public WeatherStation(String identifier)
    {
        super();
        /*# Do any work to initialize your class here. */
        this.stationId = identifier;
        this.monthlyTotals = new double[12];
        this.monthlyCounts = new int[12];
    }


    //~ Methods ...............................................................
    /**
     * The ID for specific weather station
     * @return Returns the station's ID
     */
    public String getId()
    {
        return stationId;
    }
    /**
     * Records single daily rainfall record for specific month
     * @param month The specific month(1-12)
     * @param rainfall Daily rainfall amount to record for specific month
     */
    public void recordDailyRain(int month, double rainfall)
    {
        if (month >= 1 && month <= 12)
        {
            int index = month - 1;
            monthlyTotals[index] += rainfall;
            monthlyCounts[index]++;
        }
    }
    /**
     * Daily rainfall records for a specific month
     * @param month The specific month(1-12)
     * @return 0 Returns daily rainfall records for
     * specific month or just 0 if is invalid
     */
    public int getCountForMonth(int month)
    {
        if (month >= 1 && month <= 12)
        {
            int index = month - 1;
            return monthlyCounts[index];
        }
        return 0;
    }
    /**
     * Average daily rainfall for a specific month
     * @param month The specific month(1-12)
     * @return -1 Returns daily rainfall for month, or just
     * -1 if no specific data exists
     */
    public double getAvgForMonth(int month)
    {
        if (month >= 1 && month <= 12)
        {
            int index = month - 1;
            if (monthlyCounts[index] == 0)
            {
                return -1;
            }
            return monthlyTotals[index] / monthlyCounts[index];
        }
        return -1;
    }
    /**
     * The month with lowest average recorded rainfall at a specific weather
     * station
     * @return lowestMonth Returns the month with the lowest average
     * daily rainfall recorded at a given weather station
     */
    public int getLowestMonth()
    {
        int lowestMonth = 1;
        double lowestAvg = -1;
        
        for (int i = 0; i < 12; i++)
        {
            if (monthlyCounts[i] > 0)
            {
                double avg = monthlyTotals[i] / monthlyCounts[i];
                if (lowestAvg < 0 || avg < lowestAvg - 0.000001)
                {
                    lowestAvg = avg;
                    lowestMonth = i + 1;
                }
            }
        }
        return lowestMonth;
    }
    
}
