package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//Implementação da interface, estruturas de dados e métodos locais necessários para a resolução do problema
public class Connect4Impl extends UnicastRemoteObject implements Connect4Interface {

    //TODO private static final long serialVersionUID = 1234L;
    //TODO static private Integer nextPID = 1;

    protected Connect4Impl() throws RemoteException {
    }

    //TODO 
    /*
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
    }*/

    @Override
    public int registraJogador(String nomeJogador) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int temPartida(int idJogador) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int ehMinhaVez(int idJogador) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtemGrade(int idJogador) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtemOponente(int idJogador) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int enviaJogada(int idJogador, int numColuna) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int encerraPartida(int idJogador) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}