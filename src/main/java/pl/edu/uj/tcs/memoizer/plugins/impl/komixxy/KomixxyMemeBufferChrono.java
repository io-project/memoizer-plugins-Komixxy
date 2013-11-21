package pl.edu.uj.tcs.memoizer.plugins.impl.komixxy;

import pl.edu.uj.tcs.memoizer.plugins.EViewType;
import pl.edu.uj.tcs.memoizer.plugins.Meme;
import pl.edu.uj.tcs.memoizer.plugins.impl.MemeBuffer;

import java.util.Map;

public class KomixxyMemeBufferChrono extends MemeBuffer {

	private static EViewType viewType = EViewType.CHRONOLOGICAL;
	
	public KomixxyMemeBufferChrono(Map<String, byte[]> state){
		super(state);
	}
	
	@Override
	protected Iterable<Meme> downloadMemes(){
		return null;
		/*DemotyDownloader.downloadMemesFromPage(
				DemotyUrlFactory.getMainPageUrl(1),
				viewType);*/
	}
}