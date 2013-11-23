package pl.edu.uj.tcs.memoizer.plugins.impl.komixxy;

import pl.edu.uj.tcs.memoizer.plugins.EViewType;
import pl.edu.uj.tcs.memoizer.plugins.Meme;
import pl.edu.uj.tcs.memoizer.plugins.impl.MemeBuffer;
import pl.edu.uj.tcs.memoizer.serialization.StateObject;

import java.util.Map;

public class KomixxyMemeBufferChrono extends MemeBuffer {

	private static EViewType viewType = EViewType.CHRONOLOGICAL;
	
	public KomixxyMemeBufferChrono(StateObject state){
		super(state);
	}
	
	@Override
	protected Iterable<Meme> downloadMemes(){
		return KomixxyDownloader.downloadMemesFromPage(
				KomixxyUrlFactory.getMainPageUrl(1),
				viewType);
	}
}