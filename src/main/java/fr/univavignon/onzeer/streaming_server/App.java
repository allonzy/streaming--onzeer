package fr.univavignon.onzeer.streaming_server;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Properties;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;

/**
 * Hello world!
 *
 */
public class App 
{
	private static Queue queue ;
	private static QueueConnection connection;
	private static QueueReceiver receiver;
	private static QueueSession session;
	public static final int refreshTime = 500;
	public static void main(String[] args) throws JMSException, NamingException, InterruptedException, NonUniqueFileException, FileNotFoundException{
		/*File fileToStream = FileIndexer.getFileByName("hotelCalifornia");
		if(fileToStream != null){
			Streamer streamer = new Streamer();
			streamer.play(Collections.singleton(fileToStream));
			System.out.println("streaming en cours sur "+streamer.getStreamingURL());
			while(true){
			}/**/
			//streamer.end();/**/
		/*}
		/**/
		/*initializeConnection("jms/myConnectionFactory","jms/myQueue");
		receiver = session.createReceiver(queue);
		System.out.println("Serveur en attente de message");/**/
		Streamer streamer = new Streamer();

		/*while(true){
            Thread.sleep(refreshTime);
            TextMessage message =  catchTextMessage();
            if(message != null){
            	String fileName = message.getText();
    			if(fileName != null && fileName.equals("") == false){
    				File fileToStream = FileIndexer.getFileByName(fileName);
    				System.out.println(fileToStream);
    				if(fileToStream != null){
    					callBack(message,streamer.getStreamingURL());
    					streamer.play(Collections.singleton(fileToStream));
    					System.out.println("streaming en cours sur "+streamer.getStreamingURL());
    				}
    			}
			}
		}/**/
	}
	public static void initializeConnection(String connectionFactoryName,String queueName) throws JMSException, NamingException{

		//Context context = new InitialContext(env);
		Properties props = new Properties();
		//props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost"); // default!
		//props.setProperty("org.omg.CORBA.ORBInitialPort", "4848"); // default!

        InitialContext context = new InitialContext(props);
        context.addToEnvironment("port","4848");
		QueueConnectionFactory connectionFactory = (QueueConnectionFactory)
		context.lookup(connectionFactoryName);
		queue = (Queue) context.lookup(queueName);
		connection = connectionFactory.createQueueConnection();
		session = connection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
		connection.start();
	}
	public static TextMessage catchTextMessage() throws JMSException{
		TextMessage msg = (TextMessage) receiver.receive();
		System.out.println("message recus");
		return msg;
	}
	private static void callBack(Message msg,String messageContent) throws JMSException{
		QueueSender sender = session.createSender(null);
		TextMessage callBackMessage = session.createTextMessage(messageContent);
		if (msg.getJMSReplyTo() != null){
			sender.send(msg.getJMSReplyTo(),callBackMessage);
		}else{
		}

	} 
}
