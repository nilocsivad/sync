/**
 * 
 */
package com.iam_vip.sync.rs.u;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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

	public static String getPath(HttpServletRequest request, String path) throws Exception {

		if (System.getProperty("os.name").contains("Windows")) {

			int idx = path.indexOf(":");
			if (idx == 1) {

				String root = path.substring(0, 1);
				String folder = path.substring(2).replace("\\", "/");

				int port = request.getServerPort();
				String http = request.getScheme() + "://" + request.getServerName() + (port == 80 ? "" : ":" + port);
				String prefix = "/" + root + root + root;

				String to = http + prefix.toLowerCase() + URLEncoder.encode(folder, "UTF-8").replace("%2F", "/");
				/// System.out.println(to); ///

				/// response.sendRedirect(to); ///
				return to;

			}
			else {
				/// downloadNext(path, response); ///
				return next(request, path);
			}

		}
//		else if (System.getProperty("os.name").contains("Mac")) {
//		}
		else {

			int port = request.getServerPort();
			String http = request.getScheme() + "://" + request.getServerName() + (port == 80 ? "" : ":" + port);
			String prefix = ConfigUtil.getLinuxMacResource();

			String to = http + prefix + URLEncoder.encode(path, "UTF-8").replace("%2F", "/");
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
