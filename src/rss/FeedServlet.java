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
  
        // Part1: �����rssƵ����Ԫ��Ϣ  
        // (1)Ƶ���ı���  
        String titleInfo = channel.getTitle();  
        // (2)Ƶ����������Ϣ  
        String linkInfo = channel.getLink();  
        // (3)Ƶ����������Ϣ  
        String descriptionInfo = channel.getDescription();  
        // (4)Ƶ��ʹ�õ�����  
        String languageInfo = channel.getLanguage();  
        // (5)Ƶ����Ȩ��Ϣ  
        String copyrightInfo = channel.getCopyright();  
        // (6)Ƶ����generator����Ϣ  
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
  
                // (1)ժҪ�ı���  
                String itemTitle = item.getTitle();  
                // (2)ժҪ������  
                String itemLink = item.getLink();  
                // (3)ժҪ������  
                String itemDescription = item.getDescription();  
           
                // (5)ժҪ�ķ�������  
                String itemPubDate = item.getPubDate();  
    
                n.setItemTitle(itemTitle); 
                n.setItemDescription(itemDescription);  
                n.setItemLink(itemLink);  
                n.setItemPubDate(itemPubDate);     
        }  
        return n;
    }
}
