/**
 * 
 */
package com.iam_vip.sync.rs.u;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
		
		if (invocable == null) {
			
			String realPath = request.getServletContext().getRealPath("encodeURIComponent.js");
			System.out.println(realPath);
			
			try {
				ScriptEngineManager m = new ScriptEngineManager();
				ScriptEngine e = m.getEngineByName("JavaScript");
				e.eval(new FileReader(realPath));
				invocable = (Invocable) e;
			}
			catch (FileNotFoundException | ScriptException e) {
				e.printStackTrace();
			}
		}

		if (System.getProperty("os.name").contains("Windows")) {

			int idx = path.indexOf(":");
			if (idx == 1) {

				String root = path.substring(0, 1);
				String folder = path.substring(2).replace("\\", "/");

				int port = request.getServerPort();
				String http = request.getScheme() + "://" + request.getServerName() + (port == 80 ? "" : ":" + port);
				String prefix = "/" + root + root + root;

				String to = http + prefix.toLowerCase() + invocable.invokeFunction("enc", folder).toString().replace("%2F", "/");
				/// System.out.println(to); ///

				/// response.sendRedirect(to); ///
				return to;

			}
			else {
				/// downloadNext(path, response); ///
				return next(request, path);
			}

		}
		// else if (System.getProperty("os.name").contains("Mac")) {
		// }
		else {

			int port = request.getServerPort();
			String http = request.getScheme() + "://" + request.getServerName() + (port == 80 ? "" : ":" + port);
			String prefix = ConfigUtil.getLinuxMacResource();

			String to = http + prefix + invocable.invokeFunction("enc", path).toString().replace("%2F", "/");
			/// System.out.println(to); ///

			/// response.sendRedirect(to); ///
			return to;

			/// downloadNext(path, response); ///
			/// return next(request, path);
		}

	}

	/**
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String next(HttpServletRequest request, String path) throws UnsupportedEncodingException {
		return request.getContextPath() + "/file-sync/download.html?path=" + URLEncoder.encode(path, request.getCharacterEncoding());
	}

}
