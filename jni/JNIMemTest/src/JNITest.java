import java.lang.management.ManagementFactory;
import java.util.Scanner;

public class JNITest {    
    public native int allocateAndFillMemory(int numMB);
    public native void freeMemory(); 

    static {
        System.loadLibrary("foo");
    } 

    public static void main(String[] args) {
		System.out.println("PID: " + ManagementFactory.getRuntimeMXBean().getName());
    	printMemory();
    	Scanner in = new Scanner(System.in);
    	while(true) {
    		printOptions();
    		int num = in.nextInt();
    		if(num == -1) {
    			System.exit(0);
    		} else if(num == 0) {
    			(new JNITest()).freeMemory();
    		} else if(num > 0){
    			if((new JNITest()).allocateAndFillMemory(num) == 1) {
    				printMemory();
    			}
    		}
    	}
    }

	private static void printMemory() {
		int mb = 1024*1024;
		
		//Getting the runtime reference from system
		Runtime runtime = Runtime.getRuntime();
		//Print used memory
		System.out.println("Used Memory: " 
			+ ((runtime.totalMemory() - runtime.freeMemory()) / mb) + " MB");

		//Print free memory
		System.out.println("Free Memory: " 
			+ (runtime.freeMemory() / mb) + " MB");
		
		//Print total available memory
		System.out.println("Total Memory: " + (runtime.totalMemory() / mb) + " MB");

		//Print Maximum available memory
		System.out.println("Max Memory: " + (runtime.maxMemory() / mb) + " MB");
		
	}
	private static void printOptions() {
		System.out.println("[JAVA]: Enter a number");
		System.out.println("[JAVA]: [-1]: exit");
		System.out.println("[JAVA]: [0]: free memory");
		System.out.println("[JAVA]: [value > 0]: allocate and fill [value] MB");
	}
	
	
	
//	private static void printMemory() {
//		JVM_MBeans_Monitor.printMemoryMXBean();
//		System.out.println(Integer.MAX_VALUE);
//		System.out.println("JVM MEMORY:");
//		System.out.println((Runtime.getRuntime().maxMemory() / (1024 * 1024)) + " MB max Heap");
//		System.out.println();
//	}
}