package service;

public class DateTime {

    public static final long milliseconds = System.currentTimeMillis();
    private static int seconds;
    private static int minutes;
    private static int hours;
    private static int years;
    private static int currentDaysCount;
    private static int dayOfYear;
    private static int begin = 1970;

    /**
     * Calculate time components using system time
     * <p>
     * All values are based on system time
     */
    public static void sysTime() {
        System.out.println(System.currentTimeMillis());
        seconds = ((int) (milliseconds / 1000) % 60);
        minutes = ((int) ((milliseconds / (1000 * 60)) % 60));
        hours = ((int) ((milliseconds / (1000 * 60 * 60)) % 24));
        currentDaysCount = ((int) (milliseconds / (1000 * 60 * 60 * 24)));
        years = ((int) ((milliseconds / (1000 * 60 * 60 * 24)) / (365.2422)));
        dayOfYear = ((int) ((milliseconds / (1000 * 60 * 60 * 24)) % 365.2422));

    }


    /**
     * Return Current Seconds
     *
     * @return
     */
    public static int getSeconds() {
        sysTime();
        return seconds;
    }

    /**
     * Return Current Minutes
     *
     * @return
     */
    public static int getMinutes() {
        sysTime();
        return minutes;
    }

    /**
     * Return current Hours
     *
     * @return
     */
    public static int getHours() {
        sysTime();
        return hours;
    }

    /**
     * Return current Years
     *
     * @return
     */
    public static int getYears() {
        sysTime();
        return years + begin;
    }

    /**
     * get total days up to today
     *
     * @return
     */
    public static int getCurrentDaysCount() {
        sysTime();
        return currentDaysCount;
    }

    /**
     * Get date of the year from jan 1
     *
     * @return
     */
    public static int getDayOfYear() {
        sysTime();
        if (dayOfYear == 365) {
            return 1;
        } else {
            return dayOfYear + 1;
        }

    }

    /**
     * get current Gmt time
     *
     * @return
     */
    public static String getGMTTime() {
        sysTime();
        return hours + ":" + minutes + ":" + seconds;
    }

}
