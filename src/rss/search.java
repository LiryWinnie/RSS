package rss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;

import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;


public class search {
	private static Log log = LogFactory.getLog(log.class);
    //Http Get
    public static String doGet(String url, String queryString, String charset, boolean pretty) {  
            StringBuffer response = new StringBuffer();  
            HttpClient client = new HttpClient();  
            HttpMethod method = new GetMethod(url);  
            try {  
                    if (queryString!=null)
                            method.setQueryString(URIUtil.encodeQuery(queryString));  
                    client.executeMethod(method);  
                    if (method.getStatusCode() == HttpStatus.SC_OK) {  
                            BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), charset));  
                            String line;  
                            while ((line = reader.readLine()) != null) {  
                                    if (pretty)  
                                            response.append(line).append(System.getProperty("line.separator"));  
                                    else  
                                            response.append(line);  
                            }  
                            reader.close();  
                    }  
            } catch (URIException e) {  
                log.error("HTTP Get " + queryString + " 失败", e);
            } catch (IOException e) {  
                log.error("HTTP Get " + queryString + " 失败", e);
            } finally {  
                    method.releaseConnection();  
            }  
            return response.toString();  
    }

    //解析json数据
    public static Map<String, String> toMap(String jsonString) throws JSONException  {  

        JSONObject jsonObject = new JSONObject(jsonString);  
          
        Map<String, String> result = new HashMap<String, String>();  
        Iterator<?> iterator = jsonObject.keys();  
        String key = null;  
        String value = null;  
          
        while (iterator.hasNext()) {  

            key = (String) iterator.next();  
            value = jsonObject.getString(key);  
            result.put(key, value);  

        }  
        return result;  

    }  
    
    public static bookbean bookinfo(String uri)  {  
        String y = doGet(uri, null, "UTF-8", true);
        y=y.replace("\"", "'");
        y=y.replaceAll("\\\\","/");
        y=y.replaceAll("//","/");
        Map<String, String> map=null;  
        try {  
            map = toMap(y);  
        } catch (JSONException e) {
            System.out.println("失败");
            e.printStackTrace();  
        }
        bookbean m=printBook(map);  
        return m;
   }  
 
    //根据需要，输出书籍信息
    public static bookbean printBook(Map<String, String> map){ 
    	bookbean book=new bookbean();
    	book.setId(map.get("id"));
    	book.setTitle(map.get("title"));
        String author=map.get("author").toString();  
        author=author.replace("[", "");  
        author=author.replace("]", "");  
        author=author.replace("\"", "");  
        book.setAuthor(author);
        book.setPrice(map.get("price"));
        book.setPress(map.get("publisher"));
        book.setPubdate(map.get("pubdate"));
        book.setIsbn(map.get("isbn13"));
        book.setSummary(map.get("summary"));
        Map<String, String> map2=null;  
        try {  
            map2=toMap(map.get("images").toString().replace("\"", "'"));  
        } catch (JSONException e) {
            e.printStackTrace();  
        }          
        book.setImg1(map2.get("small"));  
        book.setImg2(map2.get("medium"));  
        book.setImg3(map2.get("large"));
        return book;
    } 
    public static List<String> sea(String q,String name) {
    	
    	String url="https://api.douban.com/v2/book/search?q="+q+"&fields=isbn13,title";
    	String y=doGet(url, null, "UTF-8", true);
    	Map<String, String> map=null;  
        try {  
            map = toMap(y);  
        } catch (JSONException e) {
            System.out.println("失败");
            e.printStackTrace();  
        }
        String y1=map.get("books").toString();
        y1=y1.replace("]", "");
        y1=y1.replace("[", "");
        y1=y1.replace("},", "}\n");
        String[] str1 = y1.split("\n");
        List<String> title= new ArrayList<String>();
        List<String> isbn= new ArrayList<String>();
        Map<String, String> map2=null;  
        try {  
        	for(int i=0;i<str1.length;i++) {
              map2=toMap(str1[i]); 
              title.add(map2.get("title"));
              isbn.add(map2.get("isbn13"));
            } 
        } catch (JSONException e) {
            e.printStackTrace();  
        }
        if(name=="title")
        {
		   return title;
        }
        if(name=="isbn")
        	return isbn;
        else
        	return null;
    }
}
