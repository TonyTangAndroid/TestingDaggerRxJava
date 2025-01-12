package it.droidcon.testingdaggerrxjava;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;
import org.junit.After;
import org.junit.Before;

/**
 * JUnit Tests from <a href="https://gist.github.com/jaredsburrows/addcb1cee85d313992255dc22bcf16c9">url</a>
 *
 * @author <a href="mailto:jaredsburrows@gmail.com">Jared Burrows</a>
 */
public abstract class Rx2TestBase {

  @Before
  public void setUp() {
    RxAndroidPlugins.setInitMainThreadSchedulerHandler(ignored -> Schedulers.trampoline());
  }

  @After
  public void tearDown() {
    RxAndroidPlugins.reset();
  }
}