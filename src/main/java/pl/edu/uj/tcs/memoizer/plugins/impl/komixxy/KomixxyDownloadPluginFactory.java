package pl.edu.uj.tcs.memoizer.plugins.impl.komixxy;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import pl.edu.uj.tcs.memoizer.plugins.EViewType;
import pl.edu.uj.tcs.memoizer.plugins.IDownloadPlugin;
import pl.edu.uj.tcs.memoizer.plugins.IPluginFactory;
import pl.edu.uj.tcs.memoizer.plugins.InvalidViewException;
import pl.edu.uj.tcs.memoizer.serialization.IStateObject;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Komixxy.pl download plugin factory implementation
 * @author pmikos (sokar92)
 */
public class KomixxyDownloadPluginFactory implements IPluginFactory {

	@Override
	public List<EViewType> getAvailableDownloadViews() {
		List<EViewType> list = new ArrayList<EViewType>();
		list.add(EViewType.CHRONOLOGICAL);
		list.add(EViewType.FAVOURITE);
		list.add(EViewType.UNSEEN);
		list.add(EViewType.QUEUE);
		list.add(EViewType.SEARCH);
		list.add(EViewType.RANDOM);
		return list;
	}

	/**
	 * @author pkubiak
	 */
	@Override
	public Image getIcon() {
		try{
			byte T[] = new byte[]{
				(byte)0x89,(byte)0x50,(byte)0x4e,(byte)0x47,(byte)0x0d,(byte)0x0a,(byte)0x1a,(byte)0x0a,
				(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x0d,(byte)0x49,(byte)0x48,(byte)0x44,(byte)0x52,
				(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x10,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x10,
				(byte)0x04,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xed,(byte)0xdd,(byte)0xe2,
				(byte)0x52,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x30,(byte)0x50,(byte)0x4c,(byte)0x54,
				(byte)0x45,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x11,(byte)0x11,(byte)0x11,(byte)0x29,
				(byte)0x29,(byte)0x29,(byte)0x3a,(byte)0x3a,(byte)0x3a,(byte)0x47,(byte)0x47,(byte)0x47,
				(byte)0x56,(byte)0x56,(byte)0x56,(byte)0x66,(byte)0x66,(byte)0x66,(byte)0x76,(byte)0x76,
				(byte)0x76,(byte)0x8c,(byte)0x8c,(byte)0x8c,(byte)0x99,(byte)0x99,(byte)0x99,(byte)0xaa,
				(byte)0xaa,(byte)0xaa,(byte)0xb8,(byte)0xb8,(byte)0xb8,(byte)0xc8,(byte)0xc8,(byte)0xc8,
				(byte)0xdb,(byte)0xdb,(byte)0xdb,(byte)0xe9,(byte)0xe9,(byte)0xe9,(byte)0xfe,(byte)0xfe,
				(byte)0xfe,(byte)0x1e,(byte)0xbc,(byte)0xda,(byte)0x7d,(byte)0x00,(byte)0x00,(byte)0x00,
				(byte)0x01,(byte)0x62,(byte)0x4b,(byte)0x47,(byte)0x44,(byte)0x00,(byte)0x88,(byte)0x05,
				(byte)0x1d,(byte)0x48,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x09,(byte)0x70,(byte)0x48,
				(byte)0x59,(byte)0x73,(byte)0x00,(byte)0x00,(byte)0x0b,(byte)0x13,(byte)0x00,(byte)0x00,
				(byte)0x0b,(byte)0x13,(byte)0x01,(byte)0x00,(byte)0x9a,(byte)0x9c,(byte)0x18,(byte)0x00,
				(byte)0x00,(byte)0x00,(byte)0x07,(byte)0x74,(byte)0x49,(byte)0x4d,(byte)0x45,(byte)0x07,
				(byte)0xdd,(byte)0x0c,(byte)0x01,(byte)0x00,(byte)0x25,(byte)0x3a,(byte)0x31,(byte)0xc7,
				(byte)0x26,(byte)0x60,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x75,(byte)0x49,(byte)0x44,
				(byte)0x41,(byte)0x54,(byte)0x08,(byte)0xd7,(byte)0x63,(byte)0x70,(byte)0x81,(byte)0x02,
				(byte)0x06,(byte)0x17,(byte)0x97,(byte)0x10,(byte)0x28,(byte)0xc3,(byte)0xe3,(byte)0xb9,
				(byte)0x8b,(byte)0x8b,(byte)0xc7,(byte)0x54,(byte)0x20,(byte)0xc3,(byte)0xeb,(byte)0xbb,
				(byte)0x8b,(byte)0xeb,(byte)0xfa,(byte)0x6b,(byte)0x20,(byte)0xc6,(byte)0x33,(byte)0xe7,
				(byte)0x9c,(byte)0xbf,(byte)0x25,(byte)0x40,(byte)0x86,(byte)0xf7,(byte)0x55,(byte)0xf3,
				(byte)0xfb,(byte)0xc7,(byte)0x40,(byte)0x6a,(byte)0xa2,(byte)0x8f,(byte)0xd4,(byte)0xfd,
				(byte)0x06,(byte)0x29,(byte)0x66,(byte)0x8a,(byte)0xb9,(byte)0xf9,(byte)0x7e,(byte)0x0a,
				(byte)0x88,(byte)0xc1,(byte)0xa0,(byte)0xf3,(byte)0xff,(byte)0x27,(byte)0x90,(byte)0x76,
				(byte)0x62,(byte)0x60,(byte)0xd0,(byte)0xfd,(byte)0xff,(byte)0x37,(byte)0xc5,(byte)0x48,
				(byte)0x91,(byte)0x01,(byte)0xc8,(byte)0x58,(byte)0x79,(byte)0xff,(byte)0x87,(byte)0x02,
				(byte)0x03,(byte)0x88,(byte)0x51,(byte)0x60,(byte)0x71,(byte)0xff,(byte)0xa5,(byte)0x23,
				(byte)0x90,(byte)0x61,(byte)0x5f,(byte)0xc0,(byte)0x60,(byte)0xf5,(byte)0xff,(byte)0xa5,
				(byte)0x00,(byte)0x03,(byte)0x43,(byte)0x7c,(byte)0x01,(byte)0x03,(byte)0x63,(byte)0xde,
				(byte)0x77,(byte)0xa0,(byte)0x88,(byte)0x10,(byte)0x90,(byte)0xc3,(byte)0x14,(byte)0xc8,
				(byte)0x00,(byte)0x07,(byte)0x00,(byte)0xb8,(byte)0x27,(byte)0x2b,(byte)0x5a,(byte)0x02,
				(byte)0x47,(byte)0x9a,(byte)0x70,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x49,
				(byte)0x45,(byte)0x4e,(byte)0x44,(byte)0xae,(byte)0x42,(byte)0x60,(byte)0x82
			};

			return ImageIO.read(new ByteArrayInputStream(T)); 
		}catch(IOException e){
			return null;
		}
	}

	@Override
	public String getServiceName() {
		return "Komixxy";
	}

	@Override
	public IDownloadPlugin newInstance(IStateObject state, EViewType view)
			throws InvalidViewException {
		try{		
			switch(view){
				case CHRONOLOGICAL:
				case UNSEEN:
					return new KomixxySequentialDownloader("KomixxyChrono", state, view, new URI("http://www.komixxy.pl/page"), this);
				case QUEUE:
					return new KomixxySequentialDownloader("KomixxyQueue", state, view, new URI("http://www.komixxy.pl/poczekalnia/page"), this);
				case FAVOURITE:
					return new KomixxySequentialDownloader("KomixxyTopPercent", state, view, new URI("http://www.komixxy.pl/topka/procenty/page"), this);
				case SEARCH:
					throw new RuntimeException("Missing keyword for Searching");
				case RANDOM:
					return new KomixxySingleDownloader("KomixxyRandom", state, view, new URI("http://komixxy.pl/losuj"), this);
				default:
					throw new InvalidViewException();	
			}
		}catch(URISyntaxException e){
			throw new InvalidViewException();
		}
	}

	/**
	 * Extended version of newInstance taking additional parameter for parametrised views.
	 * @author pkubiak
	 */
	@Override
	public IDownloadPlugin newInstance(IStateObject state, EViewType viewType, Object parameters) throws InvalidViewException {
		switch(viewType){
			case SEARCH:
				String searchKey = (String)parameters;
				try {
					String url = "http://komixxy.pl/szukaj?q=" + URLEncoder.encode(searchKey, "UTF-8");
					URI uri = new URI(url);
					return new KomixxySequentialDownloader("DemotywatorySearch", state, viewType, uri, this);
				} catch (UnsupportedEncodingException | URISyntaxException e) {
					e.printStackTrace();
					throw new InvalidViewException();
				}
			default:
				return newInstance(state, viewType);
		}
	}
		
}
