package finalAssignment;

import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DefaultStringifier;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
 
//MR操作
public class StartMRProgram {
 
	private static String kv;
    public static class MapClass extends Mapper<LongWritable, Text, IntWritable, Text> 
    {

        public void map(LongWritable key, Text value, Context context) throws IOException,InterruptedException 
        {
            String[] kv = value.toString().split(",");
            context.write(new IntWritable(0), new Text(kv[0].trim() + "," + kv[1].trim()));
        }
    }
 
    public static class Reduce extends Reducer<IntWritable, Text, Text, Text> 
    {
//    	String kv;
//    	public void setup(Context context) throws IOException, InterruptedException {
//    	        Configuration conf = context.getConfiguration();
//    	        kv = conf.get("key");
//    	    }
        public void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException 
        {
            String out1,out2;
            Configuration conf = context.getConfiguration();
//            Text out = DefaultStringifier.load(conf, "keywords", Text.class);
            for(Text v : values)
            {
            	out1 = v.toString().split(",")[0];//网址
            	out2 = v.toString().split(",")[1];//标题
            	if(out2.indexOf(kv) != -1)
            		context.write(new Text(out1), new Text(out2));
            }
        }
    }
    public static void run(String[] args) throws Exception 
    {
//	    	MyCrawler mycrawler = new MyCrawler();
//	        mycrawler.startCrawl("https://www.zhihu.com/explore/recommendations");
    	// 获取配置信息
    	Configuration conf = new Configuration();
        // 创建一个 Job
        Job job = Job.getInstance(conf, "");      // 设置 job name 为 word count
        job.setJarByClass(StartMRProgram.class);
        job.setMapperClass(MapClass.class);
        job.setReducerClass(Reduce.class);
        job.setMapOutputKeyClass(IntWritable.class); 
        job.setMapOutputValueClass(Text.class);
        // 设置输入格式类
        job.setInputFormatClass(TextInputFormat.class);
        // 设置输出格式类
        job.setOutputKeyClass(Text.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setOutputValueClass(Text.class);
        
        String[] otherArgs = new GenericOptionsParser(job.getConfiguration(),args).getRemainingArgs();
        Path path = new Path(otherArgs[1]);// 取第1个表示输出目录参数（第0个参数是输入目录）
		FileSystem fileSystem = path.getFileSystem(conf);// 根据path找到这个文件
		if (fileSystem.exists(path)) {
			fileSystem.delete(path, true);
		}
        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
        job.waitForCompletion(true);
    }
    
    public static void main(String[] args) throws Exception 
    {
//    	MyCrawler mycrawler = new MyCrawler();
//        mycrawler.startCrawl("https://www.zhihu.com/explore/recommendations");
    	// 获取配置信息
        Configuration conf = new Configuration();
        // 创建一个 Job
        Job job = Job.getInstance(conf, "");      // 设置 job name 为 word count
        job.setJarByClass(StartMRProgram.class);
        job.setMapperClass(MapClass.class);
        job.setReducerClass(Reduce.class);
        job.setMapOutputKeyClass(IntWritable.class); 
        job.setMapOutputValueClass(Text.class);
        // 设置输入格式类
        job.setInputFormatClass(TextInputFormat.class);
        // 设置输出格式类
        job.setOutputKeyClass(Text.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setOutputValueClass(Text.class);
        //处理一个传参
        System.out.println("请输入要查询的关键字：");
        Scanner scanner = new Scanner(System.in);
        kv = scanner.nextLine();
//        Text keywords = new Text(kw);
//        DefaultStringifier.store(conf, keywords, "keywords");
        
        
        String[] otherArgs = new GenericOptionsParser(job.getConfiguration(),args).getRemainingArgs();
        Path path = new Path(otherArgs[1]);// 取第1个表示输出目录参数（第0个参数是输入目录）
		FileSystem fileSystem = path.getFileSystem(conf);// 根据path找到这个文件
		if (fileSystem.exists(path)) 
			fileSystem.delete(path, true);
        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
        job.waitForCompletion(true);
    }
}
