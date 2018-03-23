/**
 * 
 */
package com.iam_vip.sync.rs.u;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Colin
 */
public class PathUtil {

	/**
	 * 
	 */
	public PathUtil() {
	}

	private static Invocable invocable;

	public static String getPath(HttpServletRequest request, String path) throws Exception {

		

		if (System.getProperty("os.name").contains("Windows")) {

			String root = path.substring(0, 1);
			String folder = path.substring(2).replace("\\", "/");
			
			{ /// 动态创建 Tomcat 目录映射 ///
				
				String sRealPath = request.getSession().getServletContext().getRealPath("/");
				File fPath1 = new File(sRealPath);
				
				File fRootXml = new File(fPath1.getParentFile().getParentFile(), "conf\\Catalina\\localhost\\" + root + root + root + ".xml");
				
				if (fRootXml.exists() == false) {
					fRootXml.createNewFile();
					FileWriter fw = new FileWriter(fRootXml);
					fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?> \r\n");
					fw.write("<Context docBase=\"" + root + ":\\\" /> \r\n");
					fw.close();
				}
			}

			int port = request.getServerPort();
			String http = request.getScheme() + "://" + request.getServerName() + (port == 80 ? "" : ":" + port);
			String prefix = "/" + root + root + root;

			String to = http + prefix.toLowerCase() + getInvocable(request).invokeFunction("enc", folder).toString().replace("%2F", "/");

			return to;

		} else {

			int port = request.getServerPort();
			String http = request.getScheme() + "://" + request.getServerName() + (port == 80 ? "" : ":" + port);
			String prefix = "/sync-rs";
			
			{ /// 动态创建 Tomcat 目录映射 ///
				
				String sRealPath = request.getSession().getServletContext().getRealPath("/");
				File fPath1 = new File(sRealPath);
				
				File fRootXml = new File(fPath1.getParentFile().getParentFile(), "conf/Catalina/localhost/" + prefix + ".xml");
				
				if (fRootXml.exists() == false) {
					fRootXml.createNewFile();
					FileWriter fw = new FileWriter(fRootXml);
					fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?> \r\n");
					fw.write("<Context docBase=\"/\" /> \r\n");
					fw.close();
				}
			}

			String to = http + prefix + getInvocable(request).invokeFunction("enc", path).toString().replace("%2F", "/");

			return to;

		}

	}
	
	public static Invocable getInvocable(HttpServletRequest request) {
		
		if (invocable == null) {

			String realPath = request.getServletContext().getRealPath("encodeURIComponent.js");
			System.out.println(realPath);

			try {
				ScriptEngineManager m = new ScriptEngineManager();
				ScriptEngine e = m.getEngineByName("JavaScript");
				e.eval(new FileReader(realPath));
				invocable = (Invocable) e;
			} catch (FileNotFoundException | ScriptException e) {
				e.printStackTrace();
			}
		}
		return invocable;
	}

}
