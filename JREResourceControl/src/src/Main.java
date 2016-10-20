import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.management.ManagementFactory;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
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
		System.out.println("INITIAL MEMORY STATS OS:");
		JVM_MBeans_Monitor.printOperatingSystemMXBean();
		System.out.println("JVM MEMORY:");
		JVM_MBeans_Monitor.printMemoryMXBean();
		System.out.println();
		if(filePath != null) {
			FileChannel rwCh = null;
			try {
				rwCh  = new RandomAccessFile(filePath, "rw").getChannel();
				long fileSize = rwCh.size();
				MappedByteBuffer mapFile = rwCh.map(FileChannel.MapMode.READ_WRITE, 0, fileSize);
		        //rwCh.close();
		        mapFile.load();
				System.out.println("Successfully loaded file: " + filePath + " , File size: " + fileSize / 1024 + "K");
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
		}
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
