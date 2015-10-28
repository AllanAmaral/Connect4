package RMI;

import java.awt.HeadlessException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
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
            Connect4Interface connect4;
            String novaPartida = "S";
            
            connect4 = (Connect4Interface) Naming.lookup("//localhost/Connect4_allan");
        
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
            
            while (novaPartida.equalsIgnoreCase("S")) {
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

                String continuar = "";

                while(!continuar.equalsIgnoreCase("S") && !continuar.equalsIgnoreCase("N")) {
                    System.out.print("Você será o " + ordemJogada + " a jogar, deseja continuar (S/N)? ");
                    continuar = entrada.next();

                    if (continuar.equalsIgnoreCase("N")) {
                        connect4.encerraPartida(jogador);
                        System.out.println("Partida Encerrada.");
                    }
                }
                //Fim

                //Após o registro do segundo jogador, passa­se para um ciclo de jogadas até o fim da partida.
                if (!continuar.equalsIgnoreCase("N"))
                    jogadas(connect4, jogador);
                //Fim
                
                System.out.print("Deseja iniciar uma nova partida? (S/N)? ");
                novaPartida = entrada.next();
                
                while(!novaPartida.equalsIgnoreCase("S") && !novaPartida.equalsIgnoreCase("N")) {
                    System.out.print("Deseja iniciar uma nova partida? (S/N)? ");
                    novaPartida = entrada.next();

                    if (novaPartida.equalsIgnoreCase("N")) {
                        connect4.encerraJogador(jogador);
                        System.out.print("Jogo Encerrado.");
                    }
                }
            }

        } catch (NotBoundException | MalformedURLException | RemoteException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",JOptionPane.ERROR);
        }
    }

    private static void jogadas(Connect4Interface connect4, Integer jogador) throws RemoteException, InterruptedException {
        Scanner entrada = new Scanner(System.in);
        Integer tamanhoTabuleiro = connect4.getTamanhoTabuleiro(jogador);
        String nomeJogador = connect4.getNomeJogador(jogador);
        Integer coluna;
        String cc;
        Integer vez = 0;
        
        while (vez < 3) {
            vez = connect4.ehMinhaVez(jogador);
            cc = "";
            coluna = -1;

            switch(vez) {
                case 0:
                    System.out.println("Aguardando oponente...");
                break;

                case 1:
                    System.out.println("Sua vez " + nomeJogador + "!");
                    System.out.println(connect4.obtemGrade(jogador));
                    
                    while (coluna < 0 || coluna > tamanhoTabuleiro) {
                        try {
                            if (coluna < tamanhoTabuleiro) {
                                System.out.print("Se quiser sair do jogo digite 'S', "
                                        + "ou digite o número da coluna que você jogará (Iniciando em '0'): ");
                                cc = entrada.next();
                            }
                            
                            if (cc.equalsIgnoreCase("S")) {
                                connect4.encerraPartida(jogador);
                                break;
                            }
                            
                            coluna = Integer.valueOf(cc);
                            
                        } catch(Exception e) {
                            coluna = -1;
                        }
                    }
                    
                    connect4.enviaJogada(jogador, coluna);
                    System.out.println(connect4.obtemGrade(jogador));
                break;

                case 2:
                    System.out.println("Parabéns " + nomeJogador + ", você é o vencedor!!!");
                    return;

                case 3:
                    System.out.println("Que pena " + nomeJogador + ", você perdeu.");
                    return; 

                case 4:
                    System.out.println("Bom jogo " + nomeJogador + ", acabou empatado.");
                    return;

                case 5:
                    System.out.println("Parabéns " + nomeJogador + ", você venceu por WO!!!");
                    return;

                case 6:
                    System.out.println("Que pena " + nomeJogador + ", você perdeu por WO.");
                    return;
            }
            Thread.sleep(5000);
        }
    }

}