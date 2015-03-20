package com.liujiaqi.connectionmanager;

import java.util.ArrayList;

public class XMPPConnManager {
	public static final int MAX_CONNECTIONS = 1;
	private ArrayList<Runnable> active = new ArrayList<Runnable>();
    private ArrayList<Runnable> queue = new ArrayList<Runnable>();
    private static XMPPConnManager instance;
    Thread currentRunThread = null;
    
    public static XMPPConnManager getInstance() {
        if (instance == null)
             instance = new XMPPConnManager();
        return instance;
   }
   public void push(Runnable runnable) {
        queue.add(runnable);
        if (active.size() < MAX_CONNECTIONS)
             startNext();
   }
   private void startNext() {
       if (!queue.isEmpty()) {
            Runnable next = queue.get(0);
            queue.remove(0);
            active.add(next);

            currentRunThread = new Thread(next);
            currentRunThread.start();              
       }
       currentRunThread = null;
  }
  public void didComplete(Runnable runnable) {
       active.remove(runnable);
       startNext();
  }
  public int getQueueSize()
  {
  	return queue.size();
  }
  public Thread getCurrentRunThread() {
  	return currentRunThread;
  }
}
