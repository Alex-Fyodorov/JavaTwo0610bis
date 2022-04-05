package lesson14;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CurrentClass {
    private static final Logger logger = LogManager.getLogger(CurrentClass.class);

    // log levels
    // Fatal > Error > Warn > Info > Debug > Trace
    //   6   >  5    >   4  >  3   >   2   >   1

    public static void main(String[] args) {
        int logLevel = 2; //Info

        int a = 50;
        if (logLevel <= 2) { //Debug
            System.out.println(System.currentTimeMillis() + " our method: a = " + a);
        }
        logger.info("a = {}", a);
        int b = 10;
        if (logLevel <= 2) { //Debug
            System.out.println(System.currentTimeMillis() + " our method: b = " + b);
        }
        logger.info("b = {}", b);
        int c = a + b;
        if (logLevel <= 3) { //Info
            System.out.println(System.currentTimeMillis() + " our method: с = " + c);
        }
        logger.info("c = {} + {} = {}", a, b, c);

        // Logger, Appender, Layont

        /*

        logger.trace("Trace");
        logger.debug("Debug");
        logger.info("Info");
        logger.warn("Warn");
        logger.error("Error");
        logger.fatal("Fatal"); */
    }
}
