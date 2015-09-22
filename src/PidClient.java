import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class PidClient {

	public static void main(String[] args) {
		try {
			PidInterface p = (PidInterface)Naming.lookup("//localhost/PID");
			System.out.println("PID="+p.getPID());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

}
