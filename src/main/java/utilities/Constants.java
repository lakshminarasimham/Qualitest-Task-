package utilities;

public class Constants {

    public static final String PROPERTYFILE="src/main/resources/constants.properties";

    //Setting up the URLs
    public static final String URL = JavaHelpers.getPropertyValue(PROPERTYFILE,"url");
    public static final String FirstProduct = JavaHelpers.getPropertyValue(PROPERTYFILE,"firstProduct");
    public static final String SecondProduct = JavaHelpers.getPropertyValue(PROPERTYFILE,"secondProduct");
    public static final String ThirdProduct = JavaHelpers.getPropertyValue(PROPERTYFILE,"thirdProduct");
    public static final String FourthProduct = JavaHelpers.getPropertyValue(PROPERTYFILE,"fourthProduct");

    //Selenium constants
    public static final int WEBDRIVER_WAIT_DURATION= Integer.parseInt(JavaHelpers.getPropertyValue(PROPERTYFILE,"WebDriverWaitDuration"));
    public static final int MINIMUM_WEBDRIVER_WAIT_DURATION= Integer.parseInt(JavaHelpers.getPropertyValue(PROPERTYFILE,"MinimumWebDriverWaitDuration"));
    public static final int PAGEFACTORY_WAIT_DURATION= Integer.parseInt(JavaHelpers.getPropertyValue(PROPERTYFILE,"PageFactoryWaitDuration"));
    public static final String SCREENSHOT_LOCATION= JavaHelpers.getPropertyValue(PROPERTYFILE,"ScreenshotLocation");
    public static final String TESTOUTPUT_LOCATION= JavaHelpers.getPropertyValue(PROPERTYFILE,"TestOutputLocation");


    //Set Env variable
    private static String setEnvVariable()
    {
        //Reading from system properties.
        String environment = System.getProperty("Env");

        //if not specified via command line, take it from constants.properties file
        if (environment == null || environment.isEmpty())
        {
            environment = JavaHelpers.getPropertyValue(PROPERTYFILE,"Env");
        }
        return environment;
    }
}
