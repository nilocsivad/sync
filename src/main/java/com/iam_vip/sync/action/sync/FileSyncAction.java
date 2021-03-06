/**
 * 
 */
package com.iam_vip.sync.action.sync;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iam_vip.sync.action.ActionBase;

/**
 * @author Colin
 */
@Controller
@RequestMapping(value = { "file-sync" })
public class FileSyncAction extends ActionBase {

	/**
	 * 
	 */
	public FileSyncAction() {
	}

	@RequestMapping(method = RequestMethod.POST, value = { "upload.html" })
	public ModelAndView upload(@RequestParam("f") CommonsMultipartFile[] farr, ModelMap model) throws Exception {

		if (farr != null && farr.length > 0 /* !f.isEmpty() */) {

			File ff = new File("/tmp");
			if (!ff.exists())
				ff.mkdirs();

			for (MultipartFile f : farr) {

				if (f == null || f.isEmpty())
					continue;

				BufferedInputStream input = new BufferedInputStream(f.getInputStream());

				File newFile = new File(ff, f.getOriginalFilename());
				System.out.println("==> " + newFile.getAbsolutePath());

				BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(newFile));

				byte[] buf = new byte[1024 * 10];
				int len = 0;
				while ((len = input.read(buf)) > 0) {
					output.write(buf, 0, len);
					len = 0;
				}

				input.close();
				output.close();
			} // MultipartFile f : farr

		}

		return new ModelAndView(REDIRECT + "/", model);
	}

	@RequestMapping(value = { "download.html" })
	public void download(String path, @RequestParam(defaultValue = "0") Integer f, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		File file = new File(path);

		response.reset();
		response.setContentType("application/x-download");
		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), request.getCharacterEncoding()));
		response.addHeader("Content-Length", file.length() + "");

		BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream());

		byte[] buf = new byte[1024 * 1024 * 36];
		int len = 0;
		while ((len = input.read(buf)) > 0) {
			output.write(buf, 0, len);
		}

		input.close();
		output.flush();
		output.close();
	}

}
