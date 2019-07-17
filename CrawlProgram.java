package finalAssignment;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.htmlparser.util.ParserException;

public class CrawlProgram {
	
    private DownLoadHtml download_Html = new DownLoadHtml();
    private TopicRegex topic_Regex = new TopicRegex();
    
    public int startCrawl(String url) 
    {
        try {
        	//配置一下
            Configuration conf = new Configuration();
            URI uri = new URI("hdfs://localhost:9000");
            FileSystem hdfs = FileSystem.get(uri, conf);
            //爬html
            String html = download_Html.downLoadUrl(url);
            //分析html中的数据装进set<>
            Set<String> allData = topic_Regex.getAllData(html);
            //set<>转成String数组
            String[] out = allData.toArray(new String[allData.size()]);
            //统一存入hdfs的result.txt
            Path p =  new Path("/input/" + "result.txt");
            FSDataOutputStream toHdfs = hdfs.create(p);//创建文件
            String realOut = StringUtils.join(out,"\n");//字符串数组转字符串，真正的输出
            toHdfs.write(realOut.getBytes());
//            for (String addr : allneed) {
//                Path p1 = new Path("/myspider/" + System.currentTimeMillis());
//                FSDataOutputStream dos = hdfs.create(p1);
//                String a = addr + "\n";
//                dos.write(a.getBytes());
//            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
