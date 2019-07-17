package finalAssignment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownLoadHtml {
	public String downLoadUrl(final String addr) {
    	StringBuffer stringbuffer = new StringBuffer();
        try {
        	URL url;
            if(addr.startsWith("https://")==false)
            {
            	String urladdr=addr+"https://";
                url = new URL(urladdr);
            }
            else
            {
            	System.out.println(addr);
            	url = new URL(addr);
            }
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5000);
            con.connect();
            if (con.getResponseCode() == 200) 
            {
            	BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
                Scanner scanner = new Scanner(bis,"utf-8");
                while (scanner.hasNextLine()) 
                    stringbuffer.append(scanner.nextLine());
            }
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return stringbuffer.toString();
    }

}

