/**
 * 
 */
package com.iam_vip.sync.rs.u;

import java.beans.ConstructorProperties;
import java.util.Map;

/**
 * @author Colin
 * 
 */
public class ConfigUtil {

	private static String folder;
	private static String linuxMacResource_;

	/**
	 * Keep in mind that to make this work out of the box your code must be compiled with the debug flag enabled so that Spring can look up the parameter name from the constructor. If you can’t compile your code with debug flag (or don’t want to) you can use @ConstructorProperties JDK annotation to explicitly name your constructor arguments.
	 */
	@ConstructorProperties({ "fmap", "linuxMacResource" })
	public ConfigUtil(Map<String, String> fmap, String linuxMacResource) {
		folder = (System.getProperty("os.name").contains("Windows")) ? fmap.get("Windows") : fmap.get("Others");
		linuxMacResource_ = linuxMacResource;
	}

	/**
	 * @return the key
	 */
	public static String getFolder() {
		return folder;
	}

	/**
	 * @return the linuxMacResource
	 */
	public static String getLinuxMacResource() {
		return linuxMacResource_;
	}

}
