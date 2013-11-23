package pl.edu.uj.tcs.memoizer.plugins.impl.komixxy;

import java.io.IOException;
import java.net.*;
import java.util.*;
import pl.edu.uj.tcs.memoizer.plugins.*;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * Plugin submodule designed for downloading 
 * and extracting memes from page sources
 */
class KomixxyDownloader {
	
	/*
	 * Get a page source, parse it,
	 * extract memes and return
	 */
	static List<Meme> downloadMemesFromPage(String url, EViewType viewType){
		return extractMemesFromNodes(
				extractMemeNodes(
				downloadPageSource(url)), viewType);
	}
	
	/*
	 * Download page source from specific url
	 * If success returns downloaded page
	 * otherwise returns null
	 */
	private static Document downloadPageSource(String url){
		try{
			return Jsoup
					.connect(url)
					.userAgent("Mozilla")
					.get();
		} catch(IOException e){}
		return null;
	}
	
	/*
	 * Extract html meme-linked elements 
	 * from given page source
	 */
	private static Elements extractMemeNodes(Document komixxyPageSource){
		Elements result = new Elements();
		
		if(komixxyPageSource == null)
			return result;
		
		Elements komixxy = komixxyPageSource.select("div.pic[id*=pic]");
		result.addAll(komixxy);
		
		return result;
	}
	
	/*
	 * Parse html meme-linked element and extract meme info
	 * returns list of parsed memes
	 */
	private static List<Meme> extractMemesFromNodes(Elements memeNodes, EViewType viewType){
		List<Meme> lst = new ArrayList<Meme>();
		
		for(Element meme : memeNodes){
			try{
				String desc = "";
				
				Element picLink = meme.select("a.picwrapper[href]").first();
				URL pageLink = extractPageLinkFromATag(picLink);
				
				Element image = picLink.select("img.pic[src]").first();
				URL imageLink = extractImageLinkFromImgTag(image);
				String title = extractTitleFromImgTag(image);
				int width = extractWidthFromImgTag(image);
				int heigth = extractHeightFromImgTag(image);
				
				if(imageLink != null)
					lst.add(new Meme(imageLink, pageLink, title, desc, width, heigth, null, viewType, null));
			} catch(Exception e){}
		}
		
		return lst;
	}
	
	private static URL extractPageLinkFromATag(Element aTagElement){
		try{
			return new URL(KomixxyUrlFactory.getBaseUrl() + aTagElement.attr("href"));
		}catch(Exception e){}
		return null;
	}
	
	private static URL extractImageLinkFromImgTag(Element imgTagElement){
		try{
			return new URL(imgTagElement.attr("src"));
		} catch(Exception e){}
		return null;
	}
	
	private static String extractTitleFromImgTag(Element imgTagElement){
		try{
			return imgTagElement.attr("alt");
		} catch(Exception e){}
		return "";
	}
	
	private static int extractWidthFromImgTag(Element imgTagElement){
		try{
			String width = imgTagElement.attr("width");
			if(width.endsWith("px"))
				width = width.substring(0, width.length()-2);
			return Integer.parseInt(width);
		} catch(Exception e){}
		return 0;
	}
	
	private static int extractHeightFromImgTag(Element imgTagElement){
		try{
			String height = imgTagElement.attr("height");
			if(height.endsWith("px"))
				height = height.substring(0, height.length()-2);
			return Integer.parseInt(height);
		} catch(Exception e){}
		return 0;
	}
}