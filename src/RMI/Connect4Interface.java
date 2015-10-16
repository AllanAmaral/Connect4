package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

//Declara os métodos que serão invocados remotamente
/**
 *
 * @author Allan.Amaral
 */
public interface Connect4Interface extends Remote {

    //id -> número de identificação único para este jogador durante uma partida
    //­1 -> usuário já está cadastrado
    //­2 -> número máximo de jogadores atingido(2 vezes o número máximo de partidas)
    public int registraJogador(String nomeJogador) throws RemoteException;

    //­1 -> erro
    // 0 -> ainda não há partida
    // ordem de jogada do jogador
    public int temPartida(Integer idJogador) throws RemoteException;
    
    //-1 -> erro
    // 1 -> tudo certo
    public int criaPartida(Integer idJogador, Integer tamanhoTabuleiro) throws RemoteException;
    
    //­1 -> erro
    // 0 -> não
    // 1 -> sim
    // 2 -> é o vencedor
    // 3 -> é o perdedor
    // 4 -> houve empate
    // 5 -> vencedor por WO
    // 6 -> perdedor por WO
    //Observação: se ainda não houver 2 jogadores registrados na partida, esta chamada retorna 
    //  o código de erro ­1
    public int ehMinhaVez(Integer idJogador) throws RemoteException;

    //String vazia -> erro
    //Sring com a grade
    public String obtemGrade(Integer idJogador) throws RemoteException;

    //String vazia -> erro
    //String com o nome do oponente
    public String obtemOponente(Integer idJogador) throws RemoteException;

    //-1 -> erro
    // 1 -> tudo certo
    // 0 -> movimento inválido, por exemplo: coluna completamente cheia
    public int enviaJogada(Integer idJogador, Integer numColuna) throws RemoteException;

    //-1 -> erro
    // 0 -> sucesso
    //Observação: caso um dos jogadores chame encerraPartida antes de se determinar um vencedor para
    //  a partida ou de se determinar que houve empate, o outro jogador será vencedor por WO
    //  ou seja, receberá o código 5 quando chamar ehMinhaVez)
    public int encerraPartida(Integer idJogador) throws RemoteException;

    public String getNomeJogador(Integer idJogador) throws RemoteException;
}