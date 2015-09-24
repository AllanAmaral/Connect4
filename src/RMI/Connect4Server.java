package RMI;

import java.rmi.Naming;
import java.rmi.RemoteException;

//Cria a instância de um objeto que terá os métodos chamados remotamente
/**
 *
 * @author Allan.Amaral
 */
public class Connect4Server {

    public static void main(String[] args) {
        try {
                java.rmi.registry.LocateRegistry.createRegistry(1099);
                System.out.println("RMI registry ready.");

        } catch (RemoteException e) {
                System.out.println("RMI registry already running.");
        }

        try {
                Naming.rebind ("Connect4", new Connect4Impl ());
                System.out.println ("Connect4Server is ready.");

        } catch (Exception e) {
                System.out.println ("Connect4Server failed:");
                e.printStackTrace();
        }
        
        //inicializa a configuração necessária para executar até 50 partidas simultâneas;
        //aceita invocações remotas de métodos para, por exemplo: registrar jogador, verificar se o segundo
        //    jogador já se registrou, receber jogadas, informar nome do oponente, mostrar tabuleiro, informar
        //    estado dos jogadores, etc.
    }
}