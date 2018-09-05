package com.lshs.sbtemf.utils;

import com.lshs.sbtemf.entry.BiAn;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 爬取网页工具
 * @author: LuShao
 * @create: 2018-08-26 8:30
 **/
public class JsoupUtil {

	/**
	 * 得到url响应状态
	 * @param url
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public static int getStatus(String url) throws URISyntaxException, IOException {
		HttpGet get=new HttpGet(new URI(url));
		HttpResponse response=new DefaultHttpClient().execute(get);
		int statusCode = response.getStatusLine().getStatusCode();
		return statusCode;
	}

	/**
	 * 得到第一个页面的list内容
	 * @param url1
	 * @param biAn
	 * @param start 从这个页面的第几个开始
	 * @param end 到第几个结束  1-18
	 * @return
	 * @throws IOException
	 */
	public static List<BiAn> getUrl1List(String url1, BiAn biAn, Integer start, Integer end) throws IOException {
		List<BiAn> url1s=new ArrayList<>();
		Document document = Jsoup.connect(url1).timeout(300000).get();
		Element list = document.getElementsByClass("list").get(0);
		Elements lis = list.getElementsByTag("li");

		int num=0;
		int size;
		if(start!=null) num=start-1;
		if (end!=null) size=end;
		else size=lis.size();

		for (int i=num;i<size;i++){
			Element li = lis.get(i);
			Element a = li.getElementsByTag("a").get(0);
			String href1 = a.attr("href");
			if (!href1.equals("http://pic.netbian.com/")){
				BiAn biAn1=new BiAn();
				biAn1.setPage(biAn.getPage());
				biAn1.setScreen(biAn.getScreen());
				biAn1.setFromurl(biAn.getFromurl());
				String href = "http://www.netbian.com" + href1;
				biAn1.setUrl1(href);
				String title = a.attr("title");
				biAn1.setTitle(title);
				Element img = a.getElementsByTag("img").get(0);
				String src = img.attr("src");
				biAn1.setImg1(src);
				Element p = li.getElementsByTag("p").get(0);
//			更新时间：2017-01-07
				String replace = p.text().replace("更新时间：", "");
				biAn1.setUpdatetime(replace);
				url1s.add(biAn1);
			}
		}
		return url1s;
	}

	/**
	 * 根据传入的url得到第二个页面内容
	 * @param url1
	 * @param biAn
	 * @return
	 * @throws IOException
	 */
	public static BiAn getUrl2(String url1,BiAn biAn) throws IOException {
		Document document = Jsoup.connect(url1).timeout(300000).get();
		Element action = document.getElementsByClass("action").get(0);
//		风景壁纸
		String s = action.getElementsByTag("a").get(1).text().replaceAll("壁纸", "");
		biAn.setCategory(s);
		Element pic = document.getElementsByClass("pic").get(0);
		Element a = pic.getElementsByTag("a").get(0);
		String href = a.attr("href");
		href="http://www.netbian.com"+href;
		biAn.setUrl2(href);
		Element img = a.getElementsByTag("img").get(0);
		String src = img.attr("src");
		biAn.setImg2(src);
		return biAn;
	}

	/**
	 * 得到最后一页的内容
	 * @param url2
	 * @param biAn
	 * @return
	 * @throws IOException
	 */
	public static BiAn getUrl3(String url2,BiAn biAn) throws IOException {
		Document document = Jsoup.connect(url2).timeout(300000).get();
		String attr = document.getElementById("endimg").getElementsByTag("a").get(0).attr("href");
		biAn.setUrl3(attr);
		return biAn;
	}


}
