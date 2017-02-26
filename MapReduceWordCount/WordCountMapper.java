import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
 
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
 
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
private final static IntWritable one = new IntWritable(1);
private final static IntWritable zero = new IntWritable(0);   
private Text word = new Text();
 
 
public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
 
String line = value.toString();
 
StringTokenizer tokenizer1 = new StringTokenizer(line, ",");
 
while (tokenizer1.hasMoreTokens()) {
 
 String token1 = tokenizer1.nextToken();
 
 if (token1.contains(" ")) {
 
 StringTokenizer tokenizer2 = new StringTokenizer(token1, " ");
 
 while (tokenizer2.hasMoreTokens()) {
 
 String token2 = tokenizer2.nextToken();
 
 parseToken(token2, context);   
 
 }
 
 } else {
 
 parseToken(token1, context);    
 
 }
 
	}
}
   
public void parseToken(String token, Context context) throws IOException, InterruptedException {
 
 
 token = token.toLowerCase();
 
 String pattern = "dec|hackathon|chicago|java"; //hard coded
 
 Pattern r = Pattern.compile(pattern);
 
 Matcher m = r.matcher(token);
 
 if (m.find()) {
 
 System.out.println("parsetoken contains " + token);   
 
 System.out.println(m.group(0));
 
 
 
 word.set(m.group(0));
 
 context.write(word, one);
 
 } else {
 
 context.write(new Text("dec"), zero);
 
 context.write(new Text("hackathon"), zero);
 
 context.write(new Text("chicago"), zero);
 
 context.write(new Text("java"), zero);
 
 }
}
}
