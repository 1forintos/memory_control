import hello.HelloMBean;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;


public class Main {

	public static void main(String[] args) {
		System.out.println("\nCreate an RMI connector client and " +
			    "connect it to the RMI connector server");
			JMXServiceURL url;
			try {
				url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://:9999/jmxrmi");
				JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
				MBeanServerConnection mbsc = 
					    jmxc.getMBeanServerConnection();
				System.out.println("\nDomains:");
				String domains[] = mbsc.getDomains();
				Arrays.sort(domains);
				for (String domain : domains) {
				    System.out.println("\tDomain = " + domain);
				}
				System.out.println("\nMBeanServer default domain = " + mbsc.getDefaultDomain());

				System.out.println("\nMBean count = " +  mbsc.getMBeanCount());
				System.out.println("\nQuery MBeanServer MBeans:");
				Set<ObjectName> names = 
				    new TreeSet<ObjectName>(mbsc.queryNames(null, null));
				for (ObjectName name : names) {
					System.out.println("\tObjectName = " + name);
				}
				ObjectName mbeanName = new ObjectName("com.example:type=Hello");
				HelloMBean mbeanProxy = JMX.newMBeanProxy(mbsc, mbeanName, 
				                                          HelloMBean.class, true);

				System.out.println("\nAdd notification listener...");
				//mbsc.addNotificationListener(mbeanName, listener, null, null);

				System.out.println("\nCacheSize = " + mbeanProxy.getCacheSize());

				mbeanProxy.setCacheSize(150);

				System.out.println("\nWaiting for notification...");
				//sleep(2000);
				System.out.println("\nCacheSize = " + mbeanProxy.getCacheSize());
				System.out.println("\nInvoke sayHello() in Hello MBean...");
				mbeanProxy.sayHello();

				System.out.println("\nInvoke add(2, 3) in Hello MBean...");
				System.out.println("\nadd(2, 3) = " + mbeanProxy.add(2, 3));

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedObjectNameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

	}

}
