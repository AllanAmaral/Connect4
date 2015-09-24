package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

//Responsável pela interação entre usuário e processo­servidor
/**
 *
 * @author Allan.Amaral
 */
public class Connect4Client {

    public static void main(String[] args) {
        try {
                Connect4Interface connect4 = (Connect4Interface) Naming.lookup("//localhost/Connect4");
                System.out.println("Jogador = " + connect4.registraJogador("Allan"));

                //obtém o nome do jogador e envia este nome para o servidor (isto corresponde ao registro do jogador);
                //a seguir, o cliente entra em um ciclo de teste em que ele espera que outro jogador se registre;
                //após o registro do segundo jogador, passa­se para um ciclo de jogadas até o fim da partida.

        } catch (MalformedURLException e) {
                e.printStackTrace();
        } catch (RemoteException e) {
                e.printStackTrace();
        } catch (NotBoundException e) {
                e.printStackTrace();
        }
    }
}