/**
 * 
 */
package com.iam_vip.sync.action.sync;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.iam_vip.sync.action.ActionBase;
import com.iam_vip.sync.rs.u.ConfigUtil;

/**
 * @author Colin
 */
@Controller
@RequestMapping( value = { "file-sync" } )
public class FileSyncAction extends ActionBase {
	
	/**
	 * 
	 */
	public FileSyncAction() {}
	
	@RequestMapping( method = RequestMethod.POST, value = { "upload" } )
	public ModelAndView upload( MultipartHttpServletRequest req, HttpSession session, HttpServletResponse response, ModelMap model, MultipartFile f ) throws Exception {
		
		if ( f != null && !f.isEmpty() ) {
			
			BufferedInputStream input = new BufferedInputStream( f.getInputStream() );
			
			String folder = ConfigUtil.getFolder();
			if ( !folder.contains( ":" ) ) {
				
				File fj = new File( "." );
				File[] roots = File.listRoots();
				for ( File root : roots ) {
					if ( fj.getAbsolutePath().startsWith( root.getAbsolutePath() ) ) {
						folder = root.getAbsolutePath() + folder;
						break;
					}
				}
				
				File ff = new File( folder );
				if ( !ff.exists() ) ff.mkdirs();
			}
			
			File newFile = new File( folder, f.getOriginalFilename() );
			BufferedOutputStream output = new BufferedOutputStream( new FileOutputStream( newFile ) );
			
			byte[] buf = new byte[ 1024 * 10 ];
			int len = 0;
			while ( ( len = input.read( buf ) ) > 0 ) {
				output.write( buf, 0, len );
				len = 0;
			}
			
			input.close();
			output.close();
		}
		
		return new ModelAndView( REDIRECT + "/", model );
	}
	
}
