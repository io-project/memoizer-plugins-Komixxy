package pl.edu.uj.tcs.memoizer.plugins.impl.komixxy;

import pl.edu.uj.tcs.memoizer.plugins.EViewType;
import pl.edu.uj.tcs.memoizer.plugins.Meme;
import pl.edu.uj.tcs.memoizer.plugins.impl.MemeBuffer;

import java.util.Map;

public class KomixxyMemeBufferTop extends MemeBuffer {
	
	private static EViewType viewType = EViewType.FAVOURITE;
	
	public KomixxyMemeBufferTop(Map<String, byte[]> state){
		super(state);
	}
	
	@Override
	protected Iterable<Meme> downloadMemes(){
		return null; 
		/*DemotyDownloader.downloadMemesFromPage(
				DemotyUrlFactory.getTopByPercentPageUrl(1),
				viewType);*/
	}
}