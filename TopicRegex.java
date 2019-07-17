package finalAssignment;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TopicRegex {
    //正则表达式语法，分析<a></a>里的链接（即网址）以及元素（即标题内容）
    static String topicURL = "<a\\s+[^<>]*\\s+href=\"?(http[^<>\"]*)\"[^<>]*>([^<]*)</a>";
    
    static Set<String> getAllData(String html) 
    {
    	//分析已经下载的html，并装进set<>
        Set<String> result = new HashSet<String>();
        Pattern p = Pattern.compile(topicURL, Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(html);
        while (matcher.find()) //匹配一条加一条
        {
            System.out.println("======="+matcher.group(1)+","+matcher.group(2));
            result.add(matcher.group(1)+","+matcher.group(2));//匹配一条加一条
        }
        return result;
    }
}
