package net.sf.gaboto.test;

import java.util.Random;
import java.util.UUID;

import net.sf.gaboto.GabotoFactory;
import net.sf.gaboto.time.TimeInstant;
import net.sf.gaboto.time.TimeSpan;


public class TimeUtils {
  
  
  public static TimeSpan getRandomTimespan() {
    TimeInstant t1 = getRandomTimeinstant();
    TimeInstant t2 = getRandomTimeinstant();
    if (t1.compareTo(t2) == -1)
      return TimeSpan.createFromInstants(t1, t2);
    else 
      return TimeSpan.createFromInstants(t2, t1);
      
//    return getRandomTimespan(0.5, 0.5, 0.85, 0.95, 0.5, 0.5);
  }

  /**
   * 
   * @return a random timespan
   */
  public static TimeSpan getRandomTimespan(double prob1, double prob2,
      double prob3, double prob4, double prob5, double prob6) {
    Random r = new Random();
    TimeSpan timeSpan = new TimeSpan();
    timeSpan.setStartYear(r.nextInt(2009));
    boolean month = false, day = false;
    if (r.nextDouble() < prob1) {
      month = true;
      timeSpan.setStartMonth(r.nextInt(12));
      if (r.nextDouble() < prob2) {
        day = true;
        timeSpan.setStartDay(r.nextInt(27) + 1);
      }
    }

    if (r.nextDouble() < prob3) {
      if (r.nextDouble() < prob4)
        timeSpan.setDurationYear(r.nextInt(2009 - timeSpan.getStartYear()));
      if (month && r.nextDouble() < prob5)
        timeSpan.setDurationMonth(r.nextInt(12));
      if (day && r.nextDouble() < prob6)
        timeSpan.setDurationDay(r.nextInt(27));
    }

    return timeSpan;
  }

  /**
   * 
   * @return a random time instant
   */
  public static TimeInstant getRandomTimeinstant() {
    Random r = new Random();
    TimeInstant ti = new TimeInstant();
    ti.setStartYear(r.nextInt(2009));
    if (r.nextDouble() < 0.5) {
      ti.setStartMonth(r.nextInt(12));
      if (r.nextDouble() < 0.5) {
        ti.setStartDay(r.nextInt(27) + 1);
      }
    }

    return ti;
  }

  public static String generateRandomURI() {
    String uri = GabotoFactory.getConfig().getNSData();

    uri += UUID.randomUUID().toString().substring(0, 10);

    return uri;
  }
  

}
