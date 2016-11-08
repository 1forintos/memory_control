import java.lang.management.ManagementFactory;
import java.util.Vector;

public class Main {

	public static void main(String[] args) {
		System.out.println("Arch: " + System.getProperty("os.arch"));
		System.out.println("PID: " + ManagementFactory.getRuntimeMXBean().getName());
		
		System.out.println();
		System.out.println("JVM MEMORY:");
		JVM_MBeans_Monitor.printMemoryMXBean();
		System.out.println((Runtime.getRuntime().maxMemory() / (1024 * 1024)) + " MB max Heap");
		System.out.println();

        Vector v = new Vector();
		int MB = 0;
		int maxSize = 512;
        for(int i = 0; i < maxSize; i++)
        {
	        byte b[] = new byte[1024*1024];
	        for(int i = 0; i < 1024*1024; i++) {
	        	b[i] = (byte)0xFF;
	        }
	        v.add(b);
	        MB++;
			System.out.println(MB + " MB data stored.");
			System.out.println((Runtime.getRuntime().maxMemory() / (1024 * 1024)) + " MB max Heap");
    		System.out.println();
        }
	}
}