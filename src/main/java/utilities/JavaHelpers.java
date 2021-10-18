package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class JavaHelpers {

    //Time-stamps
    /**
     *
     * Get current time-stamp in given format
     *
     * @param String format e.g. "yyyy MMM dd", 'yyyyMMdd_HHmmss' etc.
     *
     * @return String timestamp
     *
     */
    public String getTimeStamp(String format)
    {
        /*
         * Example format are :
         *
         * "yyyy MMM dd" for "2013 Nov 28"
         *
         * "yyyyMMdd_HHmmss" for "20130131000000"
         *
         * "yyyy MMM dd HH:mm:ss" for "2013 Jan 31 00:00:00"
         *
         * "dd MMM yyyy" for "28 Nov 2017"
         */
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }


    //Data Reader
    /**
     *
     * Get Property value
     *
     * @param String propertyfile property file name
     *
     * @param String propertyname property name
     *
     * @return String property value
     *
     */
    public static String getPropertyValue(String propertyfile, String propertyname)
    {
        Properties prop = accessPropertiesFile(propertyfile);
        return prop.getProperty(propertyname);
    }

    /**
     *
     * Access property file
     *
     * @param String propertyfile property file name
     *
     * @return Properties object
     *
     */
    public static Properties accessPropertiesFile(String propertyfile)
    {
        Properties prop = new Properties();
        // try retrieve data from file
        try
        {
            prop.load(new FileInputStream(propertyfile));
        }
        // catch exception in case properties file does not exist
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return prop;
    }

}
