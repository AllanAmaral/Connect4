package RMI;

import java.awt.HeadlessException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JOptionPane;

//Responsável pela interação entre usuário e processo­servidor
/**
 *
 * @author Allan.Amaral
 */
public class Connect4Client {

    public static void main(String[] args) throws Exception {
        try {
            Scanner entrada = new Scanner(System.in);
            String nomeJogador;
            Integer jogador = -1;
            Integer ordemJogada;
            Integer tamanhoTabuleiro;
            Connect4Interface connect4 = (Connect4Interface) Naming.lookup("//localhost/Connect4");
            
            //Obtém o nome do jogador e envia este nome para o servidor (isto corresponde ao registro do jogador);
            System.out.print("Digite seu nome: ");
            nomeJogador = entrada.next();
            
            if (nomeJogador != null && !nomeJogador.isEmpty())
                jogador = connect4.registraJogador(nomeJogador);
            
            while(nomeJogador == null || nomeJogador.isEmpty() || jogador < 0) {
                System.out.print("Nome de jogador já existe, Digite outro nome: ");
                nomeJogador = entrada.next();
                jogador = connect4.registraJogador(nomeJogador);
            }
            //Fim

            //A seguir, o cliente entra em um ciclo de teste em que ele espera que outro jogador se registre;
            ordemJogada = connect4.temPartida(jogador);
            switch(ordemJogada) {
                case -1:
                    throw new Exception("Ocorreu um erro na requisição por partida.");
                    
                case 0:                
                    System.out.print("Quantas colunas terá o tabuleiro (mínimo 7 colunas)? ");
                    String tt = entrada.next();
                    tamanhoTabuleiro = -1;
                    
                    while(tamanhoTabuleiro < 7) {
                        try {
                            tamanhoTabuleiro = Integer.valueOf(tt);
                            
                            if (tamanhoTabuleiro < 7) {
                                System.out.print("Número de coluna é inválido, digite outro valor (mínimo 7 colunas): ");
                                tt = entrada.next();
                            }
                            
                        } catch(Exception e) {
                            tamanhoTabuleiro = -1;
                        }
                    }
                    
                    if (connect4.criaPartida(jogador, tamanhoTabuleiro) < 0)
                        throw new Exception("Ocorreu um erro na criação de uma partida.");
                
                    ordemJogada++;
                break;
            }
            
            System.out.print("Você será o " + ordemJogada + " a jogar, deseja continuar (S/N)? ");
            String continuar = entrada.next();
            
            while(!continuar.equalsIgnoreCase("S") && !continuar.equalsIgnoreCase("N")) {
                System.out.print("Você será o " + ordemJogada + " a jogar, deseja continuar (S/N)? ");
                continuar = entrada.next();
                
                if (continuar.equalsIgnoreCase("N")) {
                    connect4.encerraPartida(jogador);
                    JOptionPane.showMessageDialog(null, "Partida Encerrada", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            //Fim
            
            //Após o registro do segundo jogador, passa­se para um ciclo de jogadas até o fim da partida.
            jogadas(connect4, jogador);
            //Fim

        } catch (NotBoundException | MalformedURLException | RemoteException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",JOptionPane.ERROR);
        }
    }

    private static void jogadas(Connect4Interface connect4, Integer jogador) throws RemoteException {
        System.out.println(connect4.obtemGrade(jogador));
    }
}