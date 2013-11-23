package pl.edu.uj.tcs.memoizer.plugins.impl.komixxy;

import java.util.Map;

import pl.edu.uj.tcs.memoizer.plugins.EViewType;
import pl.edu.uj.tcs.memoizer.plugins.Meme;
import pl.edu.uj.tcs.memoizer.plugins.impl.MemeBuffer;
import pl.edu.uj.tcs.memoizer.serialization.StateObject;

public class KomixxyMemeBufferQueue extends MemeBuffer {
	
	private static EViewType viewType = EViewType.QUEUE;

	public KomixxyMemeBufferQueue(StateObject state){
		super(state);
	}
	
	@Override
	protected Iterable<Meme> downloadMemes(){
		return KomixxyDownloader.downloadMemesFromPage(
				KomixxyUrlFactory.getQueuePageUrl(1),
				viewType);
	}
}