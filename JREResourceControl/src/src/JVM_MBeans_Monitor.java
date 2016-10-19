import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryMXBean;

public class JVM_MBeans_Monitor {

	public static void printOperatingSystemMXBean() {
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

	/**
	 * 
	 */
	public static void printGarbageCollectorMXBean() {
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

	public static void printMemoryPoolMXBean() {
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

	public static void printMemoryMXBean() {
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
}