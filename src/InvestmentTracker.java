import java.util.Map;

public class InvestmentTracker {
	static int status=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		status=1;
		startApp();

	}
	
	public static void startApp() {
		obtainData test=new obtainData();
		Map enbridge=test.grabStock("ENB.TO");
		Map apple=test.grabStock("AAPL");
		Map apple2=test.grabStock("KKHHFF");
		test.retrieveData();
	}
	
	

}
