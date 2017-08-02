package live;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.util.concurrent.atomic.AtomicBoolean;

public class KeepRunningThread implements Runnable
{
  private AtomicBoolean keepRunning;
  
  public KeepRunningThread()
  {
    keepRunning = new AtomicBoolean(true);
  }
  
  public void shutdown() {
    keepRunning.set(false);
  }
  

  public void run()
  {
    try
    {
      Robot hal = new Robot();
      
      while (keepRunning.get()) {
        hal.delay(60000);
        Point pObj = java.awt.MouseInfo.getPointerInfo().getLocation();
        System.out.println(pObj.toString() + "x>>" + pObj.x + "  y>>" + pObj.y);
        hal.mouseMove(pObj.x + 1, pObj.y + 1);
        hal.mouseMove(pObj.x - 1, pObj.y - 1);
        pObj = java.awt.MouseInfo.getPointerInfo().getLocation();
        System.out.println(pObj.toString() + "x>>" + pObj.x + "  y>>" + pObj.y);
      }
    }
    catch (AWTException e) {
      e.printStackTrace();
    }
  }
}

