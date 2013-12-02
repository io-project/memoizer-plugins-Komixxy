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
class KomixxyMemeDownloader {
	
	/*
	 * Get a page source, parse it,
	 * extract memes and return
	 */
	static List<Meme> downloadMemesFromPage(URL url, EViewType viewType, IPluginFactory pluginFactory){
		return extractMemesFromNodes(
				extractMemeNodes(
				downloadPageSource(url)), viewType, pluginFactory);
	}
	
	/*
	 * Download page source from specific url
	 * If success returns downloaded page
	 * otherwise returns null
	 */
	private static Document downloadPageSource(URL url){
		try{
			return Jsoup
					.connect(url.toString())
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
	private static List<Meme> extractMemesFromNodes(Elements memeNodes, EViewType viewType, IPluginFactory pluginFactory){
		List<Meme> lst = new ArrayList<Meme>();
		
		for(Element meme : memeNodes){
			try{
				String desc = "";
				Element pic_image = meme.select("div.pic_image").first();
				
				//<input type="hidden" class="pic_id" name="pic_id" value="4247512"/>
				Integer idInput = Integer.parseInt(meme.select("input[name=pic_id]").first().attr("value"));
				
				String imageLink = null, title = null, description = null;
				
				Element pic = pic_image.select("img[alt]").first();
				imageLink = pic.attr("src");
				
				String fullTitle = pic.attr("alt");
				int split = fullTitle.indexOf('–');
				if(split<0)
					title = fullTitle;
				else if(split==0)
					title = fullTitle.substring(1, fullTitle.length()-1).trim();
				else if(split==fullTitle.length()-1)
					title = fullTitle.substring(0, fullTitle.length()-2).trim();
				else{
					title = fullTitle.substring(0, split-1).trim();
					description = fullTitle.substring(split+1, fullTitle.length()-1).trim();
				}
				
				URI pageLinkBad = new URI(meme.select("a[href$=comments]").first().attr("href"));//remove #comments from end
				URI pageLink = new URI(pageLinkBad.getScheme(), pageLinkBad.getHost(), pageLinkBad.getPath(),"");
				
				//TODO implement
				//int width = extractWidthFromImgTag(image);
				//int heigth = extractHeightFromImgTag(image);
				
				if(imageLink != null){
					Meme newMeme = new Meme(new URL(imageLink), pageLink.toURL(), title, description, 0, 0, null, pluginFactory);
					newMeme.setId(idInput);
					lst.add(newMeme);
				}
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return lst;
	}
	
	private static URL extractPageLinkFromATag(Element aTagElement){
		try{
			return new URL("http://www.komixxy.pl" + aTagElement.attr("href"));
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