// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Sami Amtout (samiamtout)
import java.util.HashMap;

import java.util.Map;
import java.util.Scanner;

//-------------------------------------------------------------------------
/**
 *  WeatherStation class where basic statistics are collected
 *  by one weather observation station.
 *
 *  @author Sami Amtout (samiamtout)
 *  @version 2025.05.03
 */
public class WeatherBureau
{
    //~ Fields ................................................................
    private Map<String, WeatherStation> stations;


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Initializes a newly created WeatherBureau object.
     */
    public WeatherBureau()
    {
        super();
        /*# Do any work to initialize your class here. */
        stations = new HashMap<>();
    }


    //~ Methods ...............................................................
    /**
     * Reads a single line of data by collecting ID, month, and rainfall
     * @param text Parameter for text given or read from the weatherstation
     */
    public void recordDailySummary(String text)
    {
        Scanner sc = new Scanner(text);
        String id = sc.next();
        int n = sc.nextInt();
        int month;
        double rainfall;
        if (n > 12)
        {
            sc.next();
            sc.next();
            int date = sc.nextInt();
            month = (date / 100)  % 100;
            rainfall = sc.nextDouble();
        }
        else
        {
            month = n;
            sc.nextInt();
            rainfall = sc.nextDouble();
        }
        sc.close();
        if (rainfall == -9999)
        {
            return;
        }
        WeatherStation station = stations.get(id);
        if (station == null)
        {
            station = new WeatherStation(id);
            stations.put(id, station);
        }
        station.recordDailyRain(month, rainfall);
    }
    /**
     * Multiple recorded daily summaries from the weatherstation
     * @param input Input from the weatherstation 
     */
    public void recordDailySummaries(Scanner input)
    {
        while (input.hasNextLine())
        {
            String line = input.nextLine();
            recordDailySummary(line);
        }
    }
    /**
     * Weather station with a given ID or identifier
     * @return stations.get(identifier) Returns
     * the weather station with the specific ID
     * @param identifier ID for weather station
     */
    public WeatherStation getStation(String identifier)
    {
        return stations.get(identifier);
    }
    /**
     * Lowest average daily rainfall for a specific month
     * @param month Specifies a given month for lowest average
     * @return lowestStation returns lowest average daily rainfall
     * for the specific month
     */
    public WeatherStation lowestStation(int month)
    {
        WeatherStation lowestStation = null;
        double lowestAvg = -1;
        
        for (WeatherStation station : stations.values())
        {
            double avg = station.getAvgForMonth(month);
            if (avg != -1 && (lowestAvg == -1 || avg < lowestAvg))
            {
                lowestAvg = avg;
                lowestStation = station;
            }
        }
        return lowestStation;
    }
    /**
     * Lowest average daily rainfall recorded for all months
     * @return lowestStation Returns lowest average daily
     * recorded rainfall
     */
    public WeatherStation lowestStation()
    {
        WeatherStation lowestStation = null;
        double lowestAvg = -1;
        
        for (WeatherStation station : stations.values())
        {
            for (int month = 1; month <= 12; month++)
            {
                double avg = station.getAvgForMonth(month);
                if (avg != -1 && (lowestAvg == -1 || avg < lowestAvg))
                {
                    lowestAvg = avg;
                    lowestStation = station;
                }
            }
        }
        return lowestStation;
    }
}
