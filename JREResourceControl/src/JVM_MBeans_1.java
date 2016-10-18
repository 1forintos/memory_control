import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryMXBean;

public class JVM_MBeans_1 {

	private static void printOperatingSystemMXBean() {
		OperatingSystemMXBean operatingSystemMXBean = ManagementFactory
				.getOperatingSystemMXBean();
		System.out.print("n");
		for (Method method : operatingSystemMXBean.getClass()
				.getDeclaredMethods()) {
			method.setAccessible(true);
			if (method.getName().startsWith("get")
					&& Modifier.isPublic(method.getModifiers())) {
				Object value;
				try {
					value = method.invoke(operatingSystemMXBean);
				} catch (Exception e) {
					value = e;
					e.printStackTrace();
				}
				System.out.println("t" + method.getName() + " = " + value);
			}
		}
	}

	private static void printGarbageCollectorMXBean() {
		java.util.List<java.lang.management.GarbageCollectorMXBean> list = (java.util.List<java.lang.management.GarbageCollectorMXBean>) ManagementFactory
				.getGarbageCollectorMXBeans();
		int count = 0;
		for (GarbageCollectorMXBean temp : list) {
			count++;
			GarbageCollectorMXBean garbageCollectorMXBean = temp;
			System.out.println("n********MemoryManager: "
					+ garbageCollectorMXBean.getName());
			String arr[] = garbageCollectorMXBean.getMemoryPoolNames();
			for (String name : arr) {
				System.out.println("nt GarbageCollectorMXBean-" + (count)
						+ "tMemory P"
						+ "ool names: " + name);
			}
		}
	}

	private static void printMemoryPoolMXBean() {
		java.util.List<java.lang.management.MemoryPoolMXBean> list = (java.util.List<java.lang.management.MemoryPoolMXBean>) ManagementFactory
				.getMemoryPoolMXBeans();
		int count = 0;
		for (MemoryPoolMXBean temp : list) {
			count++;
			MemoryPoolMXBean memoryPoolMXBean = temp;
			System.out.println("n********MemoryManager: "
					+ memoryPoolMXBean.getName());

			for (Method method : memoryPoolMXBean.getClass()
					.getDeclaredMethods()) {
				method.setAccessible(true);

				boolean collectionUsageThresholdSupported = memoryPoolMXBean
						.isCollectionUsageThresholdSupported();
				boolean usageThresholdSupported = memoryPoolMXBean
						.isUsageThresholdSupported();

				// if (collectionUsageThresholdSupported==true &
				// usageThresholdSupported==true)

				String methodName = method.getName(); // System.out.println("=========================> "+methodName);;

				if (method.getName().startsWith("get")
						&& Modifier.isPublic(method.getModifiers())) {
					Object value = new Object();
					;
					try {
						if (!usageThresholdSupported == false
								&& !collectionUsageThresholdSupported == false)
							value = method.invoke(memoryPoolMXBean);
					} catch (Exception e) {
						value = e;
						e.printStackTrace();
					}
					System.out.println("t" + method.getName() + " = " + value);
				}
			}
		}
	}

	private static void printMemoryMXBean() {
		MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
		System.out.print("n");
		for (Method method : memoryMXBean.getClass().getDeclaredMethods()) {
			method.setAccessible(true);
			if (method.getName().startsWith("get")
					&& Modifier.isPublic(method.getModifiers())) {
				Object value;
				try {
					value = method.invoke(memoryMXBean);
				} catch (Exception e) {
					value = e;
					e.printStackTrace();
				}
				System.out.println("t" + method.getName() + " = " + value);
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("nnt----------JVM Runtime Details---------");
		System.out.println("tAvailable processors (Cores): "
				+ Runtime.getRuntime().availableProcessors());
		System.out.println("tInitial Memory (-Xms)       : "
				+ (Runtime.getRuntime().freeMemory() / (1024 * 1024)) + " MB");
		long maxMemory = Runtime.getRuntime().maxMemory();
		System.out.println("tMaximum JVM Memory (-Xmx)   : "
				+ (maxMemory / (1024 * 1024)) + " MB");
		System.out.println("tTotal Used JVM Memory       : "
				+ (Runtime.getRuntime().totalMemory() / (1024 * 1024)) + " MB");

		File[] roots = File.listRoots();
		System.out.println("nnt----------FileSystem Details---------");
		for (File root : roots) {
			System.out.println("ntFileSystem Root Details: "
					+ root.getAbsolutePath());
			System.out.println("tTotal Space              : "
					+ (root.getTotalSpace() / (1024 * 1024)) + " MB");
			System.out.println("tFree Space               : "
					+ (root.getFreeSpace() / (1024 * 1024)) + " MB");
			System.out.println("tUsable Space             : "
					+ (root.getUsableSpace() / (1024 * 1024)) + " MB");
		}
		System.out.println("nnt----------CPU USAGES---------");
		printOperatingSystemMXBean();

		System.out.println("nnt----------GARBAGE COLLECTOR USAGES---------");
		printGarbageCollectorMXBean();

		System.out.println("nnt----------MemoryPoolMXBean USAGES---------");
		printMemoryPoolMXBean();

		System.out.println("nnt----------MemoryMXBean USAGES---------");
		printMemoryMXBean();
	}
}