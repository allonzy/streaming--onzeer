package fr.univavignon.onzeer.streaming_server;

import java.io.File;
import java.io.FilenameFilter;

public class FileIndexer {
	public static final String BASE_DIR = "ressources";
	public static File getFileByName(final String fileName) throws NonUniqueFileException{
		File baseDir = new File(BASE_DIR);
		System.out.println(baseDir.getAbsolutePath());
		File[] filesMatching = baseDir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				if(name.startsWith(fileName)){
					return true;
				}else{
					return false;
				}
			}
		});
		//System.out.println(fileName);
		if(filesMatching.length == 1){
			return filesMatching[0];
		}else if(filesMatching.length == 0){
			return null;
		}else{
			throw new NonUniqueFileException();
		}
	}
}
