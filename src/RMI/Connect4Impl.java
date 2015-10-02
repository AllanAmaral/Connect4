package RMI;

import Objetos.Jogador;
import Objetos.Partida;
import Objetos.Tabuleiro;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

//Implementação da interface, estruturas de dados e métodos locais necessários para a resolução do problema
/**
 *
 * @author Allan.Amaral
 */
public class Connect4Impl extends UnicastRemoteObject implements Connect4Interface {

    private static final long serialVersionUID = 1234L;
    private int nextPID = 1;
    private final HashMap<Integer, Jogador> jogadores = new HashMap<>();
    private final List<Partida> partidas = new ArrayList<>();

    protected Connect4Impl() throws RemoteException {
    }
    
    private int getPID() {
            int pid;

            pid = this.nextPID;
            ++this.nextPID;
            
            return pid;
    }

    @Override
    public synchronized int registraJogador(String nomeJogador) throws RemoteException {
        Collection<Jogador> jogadoresAux = this.jogadores.values();
        
        if (jogadoresAux.stream().filter(j -> (nomeJogador.equals(j.getNomeJogador()))).count() > 0) 
            return -1;
        
        Jogador j = new Jogador(getPID(), nomeJogador);        
        this.jogadores.put(j.getIdJogador(), j);
        
        return j.getIdJogador();
    }

    @Override
    public synchronized int temPartida(Integer idJogador) throws RemoteException {
        if (partidas.isEmpty()) return 0;
        
        List<Partida> partidasAux = partidas.stream().filter(p -> 
                (p.getJogadores() != null && p.getJogadores().size() < p.getNumJogadores()))
                .collect(Collectors.toList());
        
        if (partidasAux.isEmpty()) return 0;
            
        Partida partida = partidasAux.get(0);
        if (partida == null || partida.getJogadores().isEmpty())
            return 0;
                    
        partida.getJogadores().add(idJogador);
        
        //this.partidas.replace(partida.getIdPartida(), this.partidas.get(partida.getIdPartida()), partida);
        
        return partida.getJogadores().size();
    }
    
    @Override
    public synchronized int criaPartida(Integer idJogador, Integer tamanhoTabuleiro, Integer numJogadores) throws RemoteException{
        try {           
            List<Integer> listaJogador = new ArrayList<>();
            listaJogador.add(idJogador);
            
            Tabuleiro tabuleiro = new Tabuleiro(tamanhoTabuleiro);
            Partida partida = new Partida(listaJogador, tabuleiro, numJogadores);

            this.partidas.add(partida);

            return 1;
        
        } catch(Exception e) {
            return -1;
        }
    }

    @Override
    public int ehMinhaVez(Integer idJogador) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer[][] obtemGrade(Integer idJogador) throws RemoteException {
        List<Partida> partidasAux = this.partidas.stream().filter(p -> p.getJogadores().contains(idJogador))
                .collect(Collectors.toList());
        
        if (partidasAux.isEmpty()) return null;
            
        Partida partida = partidasAux.get(0);
        return partida.getTabuleiro().getGrade();
    }

    @Override
    public String obtemOponente(Integer idJogador) throws RemoteException {
        List<Partida> partidasAux = this.partidas.stream().filter(p -> 
                (p.getJogadores() != null && p.getJogadores().contains(idJogador)))
                .collect(Collectors.toList());
        
        if (partidasAux.isEmpty()) return "";
            
        Partida partida = partidasAux.get(0);
        if (partida.getJogadores().isEmpty()) return "";
        
        return this.jogadores.get(partida.getJogadores().stream().filter(j -> !j.equals(idJogador))
                .collect(Collectors.toList()).get(0)).getNomeJogador();
    }

    @Override
    public int enviaJogada(Integer idJogador, Integer numColuna) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized int encerraPartida(Integer idJogador) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}