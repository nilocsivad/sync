/**
 * 
 */
package com.iam_vip.sync.rs.u;

import java.beans.ConstructorProperties;

/**
 * @author Colin
 * 		
 */
public class ConfigUtil {
	
	private static String folder;
	
	
	/**
	 * Keep in mind that to make this work out of the box your code must be compiled with the debug flag enabled so that Spring can look up the parameter name from the constructor. If you can’t compile your code with debug flag (or don’t want to) you can use @ConstructorProperties JDK annotation to explicitly name your constructor arguments.
	 */
	@ConstructorProperties( { "folder" } )
	public ConfigUtil( String key ) {
		ConfigUtil.folder = key;
	}
	
	/**
	 * @return the key
	 */
	public static String getFolder() {
		
		return folder;
	}
	
}
