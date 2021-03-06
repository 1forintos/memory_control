import java.io.File;


public class JREResourceControl {

	public static void main(String[] args) {
		// System.out.println(System.getProperty("sun.arch.data.model") );
		 /* Total number of processors or cores available to the JVM */
		  System.out.println("Available processors (cores): " + 
		  Runtime.getRuntime().availableProcessors());

		  /* Total amount of free memory available to the JVM */
		  System.out.println("Free memory (MB): " + 
		  Runtime.getRuntime().freeMemory() / (1024 * 1024));

		  /* This will return Long.MAX_VALUE if there is no preset limit */
		  long maxMemory = Runtime.getRuntime().maxMemory() / (1024 * 1024);
		  /* Maximum amount of memory the JVM will attempt to use */
		  System.out.println("Maximum memory (MB): " + 
		  (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));

		  /* Get a list of all filesystem roots on this system */
		  File[] roots = File.listRoots();

		  /* For each filesystem root, print some info */
		  for (File root : roots) {
		    System.out.println("File system root: " + root.getAbsolutePath());
		    System.out.println("Total space (MB): " + root.getTotalSpace() / (1024 * 1024));
		    System.out.println("Free space (MB): " + root.getFreeSpace() / (1024 * 1024));
		    System.out.println("Usable space (MB): " + root.getUsableSpace() / (1024 * 1024));
		    System.out.println();
		    System.out.println("Runtime - totalMemory (MB): " + Runtime.getRuntime().totalMemory() / (1024 * 1024));
		    System.out.println("Runtime - maxMemory (MB): " + Runtime.getRuntime().maxMemory() / (1024 * 1024));
		    System.out.println("Runtime - freeMemory (MB): " + Runtime.getRuntime().freeMemory() / (1024 * 1024));
		    
		  }
	}

}
