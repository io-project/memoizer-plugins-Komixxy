package pl.edu.uj.tcs.memoizer.plugins.impl.komixxy;

class KomixxyUrlFactory {
	
	private static String _workingUrl = "http://www.komixxy.pl";
	
	static String getBaseUrl(){
		return _workingUrl;
	}
	
	static String getMainPageUrl(int pageNum){
		return _workingUrl + "/page/" + pageNum;
	}
	
	static String getQueuePageUrl(int pageNum){
		return _workingUrl + "/poczekalnia/page/" + pageNum;
	}
	
	static String getTopByPercentPageUrl(int pageNum){
		return _workingUrl + "/topka/procenty/page/" + pageNum;
	}
}