package finalAssignment;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;
import org.htmlparser.util.ParserException;

public class StartCrawling extends Thread{
//    public void run(String[] args) throws ParserException,Exception
//    {
//        MyCrawler mycrawler = new MyCrawler();
//        mycrawler.startCrawl("https://www.zhihu.com/explore/recommendations");
//        
//        MRProgram m1 = new MRProgram();
//        while(true)
//        	m1.run(args);
//    }
    
    public static void main(String[] args) throws ParserException,Exception
    {
        CrawlProgram mycrawler = new CrawlProgram();
        System.out.println("请输入要爬虫的网址：");
        //https://zhihu.com/explore/recommendations
        //https://sohu.com
        Scanner scanner = new Scanner(System.in);
        String url = scanner.nextLine();
        mycrawler.startCrawl(url);
//        MRProgram m = new MRProgram();
//        m.run(args);
//        Go go2 = new Go();
//        go1.start();
//        go2.start();
    }

//	@Override
//	public void run()
//	{
//		MRProgram m1 = new MRProgram();
//		String[] args = {"hdfs://localhost:9000/output/result.txt","hdfs://localhost:9000/analysisOut/"};
//			try {
//				m1.run(args);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	}
//    
    
}