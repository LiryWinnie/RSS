package rss;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;


import org.gnu.stealthp.rsslib.RSSChannel;
import org.gnu.stealthp.rsslib.RSSHandler;
import org.gnu.stealthp.rsslib.RSSItem;
import org.gnu.stealthp.rsslib.RSSParser;

import rss.bean;

public class FeedServlet {
	public static final String remoteRSS1="https://www.douban.com/feed/people/148706954/notes";
	public static final String remoteRSS="https://www.douban.com/feed/people/148706954/interests";
	private static final FeedServlet Rsslib4jReadRss = null; 
	private static int a=0;
	public static String m;
	public static String Rssremote(int a) {
		if(a==1)
			m=remoteRSS1;
		if(a==2)
			m=remoteRSS;
		return m;		
	}
	public static FeedBean test1() throws Exception {   
        RSSHandler remoteRSSHandler = new RSSHandler();   
        URL url = new URL(m);  
        URLConnection feedUrl = url.openConnection();  
        
        RSSParser.parseXmlFile(feedUrl.getURL(), remoteRSSHandler, false);   
        FeedBean rssInfo=Rsslib4jReadRss.getRSSInfo(remoteRSSHandler);    
        return rssInfo;
    }   
	public static bean test2(int num) throws Exception {   
		   
        RSSHandler remoteRSSHandler = new RSSHandler();   
        URL url = new URL(m);  
        URLConnection feedUrl = url.openConnection();  
        feedUrl.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
        RSSParser.parseXmlFile(feedUrl.getURL(), remoteRSSHandler, false);   
        bean n=Rsslib4jReadRss.getItems(remoteRSSHandler,num); 
        return n;
    }  
	public static int test3() throws Exception {   
		     
        RSSHandler remoteRSSHandler = new RSSHandler();   
        URL url = new URL(m);  
        URLConnection feedUrl = url.openConnection();  
        feedUrl.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)"); 
        RSSParser.parseXmlFile(feedUrl.getURL(), remoteRSSHandler, false);   
        int n=Rsslib4jReadRss.getNum(remoteRSSHandler); 
        return n;
    } 
    public static FeedBean getRSSInfo(RSSHandler handler) {  
    	FeedBean rssInfo= new FeedBean();
    	bean n[]=null;
  
    
        RSSChannel channel = handler.getRSSChannel();  
  
        // Part1: 分离出rss频道的元信息  
        // (1)频道的标题  
        String titleInfo = channel.getTitle();  
        // (2)频道的链接信息  
        String linkInfo = channel.getLink();  
        // (3)频道的描述信息  
        String descriptionInfo = channel.getDescription();  
        // (4)频道使用的语言  
        String languageInfo = channel.getLanguage();  
        // (5)频道版权信息  
        String copyrightInfo = channel.getCopyright();  
        // (6)频道的generator的信息  
        String pubDate = channel.getPubDate();    
  
        rssInfo.setTitleInfo( titleInfo );  
        rssInfo.setLinkInfo(linkInfo);  
        rssInfo.setDescriptionInfo(descriptionInfo);  
        rssInfo.setLanguageInfo(languageInfo);  
        rssInfo.setCopyrightInfo(copyrightInfo);  
        rssInfo.setPubDate(pubDate);   
        
        return rssInfo;  
  
    }  
    public static int getNum(RSSHandler handler) {
    	RSSChannel channel = handler.getRSSChannel();
        List channelItems = channel.getItems();  
        int itemSize = channelItems.size();  
        a=itemSize;
        return a;
    }
    public static bean getItems(RSSHandler handler, int itemnum) {
    	RSSChannel channel = handler.getRSSChannel();
        List channelItems = channel.getItems();  
        bean n= new bean();
        int itemSize = channelItems.size();  
        if (itemSize >= itemnum) {   
                int itemNo = itemnum + 1;  
                RSSItem item = (RSSItem) channelItems.get(itemnum);  
  
                // (1)摘要的标题  
                String itemTitle = item.getTitle();  
                // (2)摘要的链接  
                String itemLink = item.getLink();  
                // (3)摘要的描述  
                String itemDescription = item.getDescription();  
           
                // (5)摘要的发布日期  
                String itemPubDate = item.getPubDate();  
    
                n.setItemTitle(itemTitle); 
                n.setItemDescription(itemDescription);  
                n.setItemLink(itemLink);  
                n.setItemPubDate(itemPubDate);     
        }  
        return n;
    }
}
