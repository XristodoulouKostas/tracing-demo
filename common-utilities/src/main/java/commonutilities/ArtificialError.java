package commonutilities;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ArtificialError {

  private static final Random random = new Random();

  public void randomlyAddDelay(double probability, long value, TemporalUnit unit) {
    if (probability <= 0.0 || probability >= 1.0) {
      throw new IllegalArgumentException("Probability must be greater than 0 and less than 1.");
    }

    if (random.nextDouble() < probability) {
      try {
        Thread.sleep(Duration.of(value, unit));
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public void randomlyFailWith(double probability, String message) {
    if (probability <= 0.0 || probability >= 1.0) {
      throw new IllegalArgumentException("Probability must be greater than 0 and less than 1.");
    }

    if (random.nextDouble() < probability) {
      log.error(message);
      throw new RuntimeException(message);
    }
  }
}
