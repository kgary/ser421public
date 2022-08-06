class Worker425 implements Runnable {
    protected int id;
    protected int sleepDelay;
    
    public Worker425 (int assignedID, int sd) {
        id = assignedID;
        sleepDelay = sd;
    }

    public void run() {
        for (int loop=0; loop < 5; loop++) {
            System.out.println("Hello from " + id  + " loop=" + loop);
            try {
        		Thread.sleep(sleepDelay);
        	} catch (Throwable t) {
        		t.printStackTrace();
        	}
        }
    }
}

class FirstThread {
    public static void main(String args[]) {
        int  times= Integer.parseInt(args[0]);
        int sleepDelay = Integer.parseInt(args[1]);
        
        for (int loop=0; loop < times; loop++) {
        	
            Runnable worker = new Worker425(loop, sleepDelay*loop);
            Thread task = new Thread(worker, "Task#"+loop);
            task.start();
        }
    }
}
