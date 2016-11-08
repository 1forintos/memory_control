import java.lang.management.ManagementFactory;

public class Main {

	public static void main(String[] args) {
		System.out.println("Arch: " + System.getProperty("os.arch"));
		System.out.println("PID@user: " + ManagementFactory.getRuntimeMXBean().getName());
		
		System.out.println();
		System.out.println("JVM MEMORY:");
		JVM_MBeans_Monitor.printMemoryMXBean();
		System.out.println((Runtime.getRuntime().maxMemory() / (1024 * 1024)) + " MB max Heap");

		System.out.println();		
		int size = 512 * 1024 * 1024;
        byte b[] = new byte[size];
        
	}
}
