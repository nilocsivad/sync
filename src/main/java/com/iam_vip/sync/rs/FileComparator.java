package com.iam_vip.sync.rs;

import java.io.File;
import java.util.Comparator;

public class FileComparator implements Comparator< File > {
	
	public FileComparator() {}
	
	public int compare( File o1, File o2 ) {
		
		return o1.getName().charAt( 0 ) - o2.getName().charAt( 0 );
	}
	
}
