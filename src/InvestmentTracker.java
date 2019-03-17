
public class InvestmentTracker {
	static int status=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		status=1;
		startApp();

	}
	
	public static void startApp() {
		obtainData test=new obtainData();
		test.retrieveData();
	}
	
	

}
