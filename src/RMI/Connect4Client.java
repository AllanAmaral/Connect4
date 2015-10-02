package RMI;

import java.awt.HeadlessException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;

//Responsável pela interação entre usuário e processo­servidor
/**
 *
 * @author Allan.Amaral
 */
public class Connect4Client {

    public static void main(String[] args) {
        try {
            String nomeJogador;
            Integer jogador = -1;
            Integer ordemJogada;
            String tamanhoTabuleiro;
            String numeroJogadores;
            Connect4Interface connect4 = (Connect4Interface) Naming.lookup("//localhost/Connect4");
            
            //Obtém o nome do jogador e envia este nome para o servidor (isto corresponde ao registro do jogador);
            nomeJogador = JOptionPane.showInputDialog("Digite seu nome: ");
            
            if (nomeJogador != null && !nomeJogador.isEmpty())
                jogador = connect4.registraJogador(nomeJogador);
            
            while(nomeJogador == null || nomeJogador.isEmpty() || jogador < 0) {
                nomeJogador = JOptionPane.showInputDialog("Nome de jogador já existe, Digite outro nome: ");
                jogador = connect4.registraJogador(nomeJogador);
            }
            //Fim

            //A seguir, o cliente entra em um ciclo de teste em que ele espera que outro jogador se registre;
            ordemJogada = connect4.temPartida(jogador);
            switch(ordemJogada) {
                case -1:
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro na requisição por partida.", "ERRO",JOptionPane.ERROR);
                break;
                    
                case 0:
                    numeroJogadores = JOptionPane.showInputDialog("Ainda não existe uma partida, Quantos jogadores terá a partida? (mínimo 2 jogadores)");
                    Integer nj = -1;
                    
                    while(nj < 2) {
                        try {
                            nj = Integer.valueOf(numeroJogadores);
                            
                            if (nj < 2)
                                numeroJogadores = JOptionPane.showInputDialog("Número de jogadores é inválido, digite outro valor: (mínimo 2 colunas)");
                            
                        } catch(Exception e) {
                            nj = -1;
                        }
                    }
                    
                    tamanhoTabuleiro = JOptionPane.showInputDialog("Quantas colunas terá o tabuleiro? (mínimo 7 colunas)");
                    Integer tt = -1;
                    
                    while(tt < 7) {
                        try {
                            tt = Integer.valueOf(tamanhoTabuleiro);
                            
                            if (tt < 7)
                                tamanhoTabuleiro = JOptionPane.showInputDialog("Número de coluna é inválido, digite outro valor: (mínimo 7 colunas)");
                            
                        } catch(Exception e) {
                            tt = -1;
                        }
                    }
                    
                    if (connect4.criaPartida(jogador, tt, nj) < 0)
                        JOptionPane.showMessageDialog(null, "Ocorreu um erro na criação de uma partida.", "ERRO",JOptionPane.ERROR);
                
                    ordemJogada++;
                break;
            }
            
            Integer continuar = JOptionPane.showConfirmDialog(null, "Você será o " + ordemJogada + " a jogar, deseja continuar?");
            if (continuar > 0) {
                connect4.encerraPartida(jogador);
                JOptionPane.showMessageDialog(null, "Partida Encerrada", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
            }
            //Fim
            
            //Após o registro do segundo jogador, passa­se para um ciclo de jogadas até o fim da partida.
            
            //Fim

        } catch (NotBoundException | MalformedURLException | RemoteException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",JOptionPane.ERROR);
        }
    }
}