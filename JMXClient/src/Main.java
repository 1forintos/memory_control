import java.lang.management.ManagementFactory;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String filePath = null;
		if(args.length > 0) {
			if(args[0].equals("-h")) {
				printHelp();
				System.exit(0);
			} else {
				filePath = args[0];
			}
		}
		filePath = "/home/mkamras/jre_mem_controll/dummy_file.txt";
		System.out.println("Arch: " + System.getProperty("os.arch"));
		System.out.println("PID: " + ManagementFactory.getRuntimeMXBean().getName());
		
		System.out.println();
		//System.out.println("INITIAL MEMORY STATS OS:");
		//JVM_MBeans_Monitor.printOperatingSystemMXBean();
		System.out.println("JVM MEMORY:");
		JVM_MBeans_Monitor.printMemoryMXBean();
		
		System.out.println();
		//int num = 0;
        //Vector v = new Vector();
		//int size = 512*1024*1024;
        byte b[] = new byte[512*1024*1024];
       // while (true)
        //{
           // byte b[] = new byte[1048576];
            /*for(int i = 0; i < 1048576; i++) {
	        	b[i] = (byte)0xFF;
	        }*/
            //v.add(b);
            Runtime rt = Runtime.getRuntime();
            // System.out.println( "free memory: " + rt.freeMemory() );
           // num++;
            //System.out.println(num + " MB allocated.");
            System.out.println((Runtime.getRuntime().maxMemory() / (1024 * 1024)) + " MB max Heap");
    		System.out.println();
    		Scanner reader = new Scanner(System.in);  // Reading from System.in
    		String n = reader.next(); // Scans the next token of the input as an int.
        //}
		/*
		if(filePath != null) {
			FileChannel rwCh = null;
			try {
				rwCh  = new RandomAccessFile(filePath, "rw").getChannel();
				long fileSize = rwCh.size();
				MappedByteBuffer mapFile = rwCh.map(FileChannel.MapMode.READ_WRITE, 0, fileSize);
		        //rwCh.close();
		        //mapFile.load();
				int size = 512*1024*1024;
		        byte b[] = new byte[512*1024*1024];
		        for(int i = 0; i < size; i++) {
		        	b[i] = (byte)0xFF;
		        }
				System.out.println("Successfully mapped file: " + filePath + " , File size: " + fileSize / 1024 + "K");
				System.out.println();

		        mapFile.put((byte) 0xff);
				
				Scanner reader = new Scanner(System.in);  // Reading from System.in
				while(true) {
					System.out.println("MEMORY STATS OS:");
					System.out.println();
					JVM_MBeans_Monitor.printOperatingSystemMXBean();
					System.out.println("JVM Memory");
					System.out.println();
					JVM_MBeans_Monitor.printMemoryMXBean();
					System.out.println();
					
					System.out.println("...");
					String n = reader.next(); // Scans the next token of the input as an int.
				}
			} catch (FileNotFoundException e) {			
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}*/
	}
	
	private static void printHelp() {
		System.out.println("Usage:");
		System.out.println("[1] No arguments: prints OS and JVM memory stats.");
		System.out.println("[2] 1 argument: <filePath>. Prints OS and JVM memory stats before and after loading the file at <filePath> using MMIO.");
		System.out.println("You can easily create a dummy file with a certain size on Linux by using the fallocate command.");
		System.out.println("Example of creating a 300MB file: \"fallocate -l 300M dummy_file.txt\"");
		System.out.println("[3] 1 argument: -h  - Prints this help message.");
	}
}
