package pl.edu.uj.tcs.memoizer.plugins.impl.komixxy;

import pl.edu.uj.tcs.memoizer.plugins.EViewType;
import pl.edu.uj.tcs.memoizer.plugins.Meme;
import pl.edu.uj.tcs.memoizer.plugins.impl.MemeBuffer;
import pl.edu.uj.tcs.memoizer.serialization.StateObject;

import java.util.Map;

public class KomixxyMemeBufferTop extends MemeBuffer {
	
	private static EViewType viewType = EViewType.FAVOURITE;
	
	public KomixxyMemeBufferTop(StateObject state){
		super(state);
	}
	
	@Override
	protected Iterable<Meme> downloadMemes(){
		return KomixxyDownloader.downloadMemesFromPage(
				KomixxyUrlFactory.getTopByPercentPageUrl(1),
				viewType);
	}
}