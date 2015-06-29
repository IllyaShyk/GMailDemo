package utils;

import org.apache.log4j.Logger;
import org.testng.Reporter;

/**
 * Created by Illya
 */
public class Log4Test {
    private Log4Test() {}

    private static final Logger LOGGER = Logger.getLogger(Log4Test.class);

    // ****************** Default message content ********************
    private static final String INFO_LOG = "INFO: \"%s\"";
    private static final String ERROR_LOG = "ERROR: \"%s\" !";
    private static final String RESULT_LOG = "RESULT: \"%s\"";


    public static String error(String message)
    {
        LOGGER.error(String.format(ERROR_LOG, message));
        Reporter.log(String.format(ERROR_LOG, message));
        return String.format(ERROR_LOG, message);
    }

    public static String info(String message)
    {
        LOGGER.info(String.format(INFO_LOG, message));
        Reporter.log(String.format(INFO_LOG, message));
        return String.format(INFO_LOG, message);
    }

    public static String result(String message)
    {
        LOGGER.info(String.format(RESULT_LOG, message));
        Reporter.log(String.format(RESULT_LOG, message));
        return String.format(RESULT_LOG, message);
    }


}

