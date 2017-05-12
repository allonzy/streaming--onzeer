package fr.univavignon.onzeer.streaming_server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;


import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;

public class Streamer{
	private final static String SERVER_URL = "localhost";
	private final static String LOCAL_URL = "localhost";
	private MediaPlayer mediaPlayer;
	private static final MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
	private String streamingUrl;
	private Set<File> playList;
	private int port = 8090;
	private String option;
	//private ServerSocket sock;
	public Streamer(){
		/*try {
			sock = new ServerSocket(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		port = sock.getLocalPort();/**/
		mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();
	}
	public String getStreamingURL(){
		return this.streamingUrl;
	}
	public void addFiles(Set<File> set){
		playList = set;
	}

	public void play(Set<File> files) throws FileNotFoundException{	
		//option = "sout=#transcode{access=udp,mux=ts,dst="+LOCAL_URL+",port=" + port + "}";
		for (File file : files){
			option =/*"#transcode{acodec=mp3,ab=128,mux=ogg}:"
					+ "http{dst=:"+port+"/"+file.getName()+"}";/**/
			streamingUrl = SERVER_URL+":"+port+"/"+file.getName();
			mediaPlayer.prepareMedia(file.getAbsolutePath());
			mediaPlayer.addMediaOptions(option);
			mediaPlayer.play();
			}
	}
	public void end(){
		mediaPlayer.stop();
		mediaPlayer.release();

	}
	public Boolean isRunning(){
		return mediaPlayer.isPlaying();
	}
}
