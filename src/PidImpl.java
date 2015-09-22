import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PidImpl extends UnicastRemoteObject implements PidInterface {

	private static final long serialVersionUID = 1234L;
	static private Integer nextPID = 1;

	protected PidImpl() throws RemoteException {
	}

	@Override
	public int getPID() throws RemoteException {
		int pid;

		System.out.println("PidServer> Entrada");
		pid = nextPID;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		++nextPID;
		System.out.println("PidServer> Saida");
		return pid;
	}

}
