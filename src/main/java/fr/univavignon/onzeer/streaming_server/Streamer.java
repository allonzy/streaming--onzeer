package fr.univavignon.onzeer.streaming_server;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;

import uk.co.caprica.vlcj.medialist.MediaList;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.list.MediaListPlayer;

public class Streamer{
	private final static String SERVER_BASE_URL = "localhost/";
	private final static MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
	private MediaListPlayer mediaPlayer ;
	private String streamingUrl;
	private final MediaList playList; 
	private ServerSocket s;
	public Streamer(){
		mediaPlayer = mediaPlayerFactory.newMediaListPlayer();
		playList = mediaPlayerFactory.newMediaList();
		try {
		s = new ServerSocket(0);
		streamingUrl = SERVER_BASE_URL+":"+s.getLocalPort();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getStreamingURL(){
		return this.streamingUrl;
	}
	public void addFiles(List<File> files){
		for(File file : files) { 	
			playList.addMedia(file.getAbsolutePath());
		}
	}
	public void play(List<File> files){
		 try {

			 this.addFiles(files);
			 mediaPlayer.setMediaList(playList);
			 mediaPlayer.play();
			 Thread.currentThread().join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			try {
				s.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
