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
                (p.getJogadores() != null && p.getJogadores().size() < 2))
                .collect(Collectors.toList());
        
        if (partidasAux.isEmpty()) return 0;
            
        Partida partida = partidasAux.get(0);
        if (partida == null || partida.getJogadores().isEmpty())
            return 0;
                    
        partida.getJogadores().add(idJogador);
        partida.setJogadorDaVez(1);
        
        Jogador jogador = jogadores.get(idJogador);
        jogador.setOrdemJogada(partida.getJogadores().size());
        
        return partida.getJogadores().size();
    }
    
    @Override
    public synchronized int criaPartida(Integer idJogador, Integer tamanhoTabuleiro) throws RemoteException{
        try {           
            List<Integer> listaJogador = new ArrayList<>();
            listaJogador.add(idJogador);
            
            Tabuleiro tabuleiro = new Tabuleiro(tamanhoTabuleiro);
            Partida partida = new Partida(listaJogador, tabuleiro);
            Jogador jogador = jogadores.get(idJogador);
            jogador.setOrdemJogada(partida.getJogadores().size());
        
            this.partidas.add(partida);

            return 1;
        
        } catch(Exception e) {
            return -1;
        }
    }

    @Override
    public int ehMinhaVez(Integer idJogador) throws RemoteException {
        Partida partida = getMinhaPartida(idJogador);
        Jogador jogador = jogadores.get(idJogador);
        Integer vencedor = 0;
        
        if (partida == null || jogador == null) return -1;
        
        if (partida.getJogadores().size() > 1)
            vencedor = verificarVencedor(partida, jogador);
        
        if (vencedor > 0)
            return vencedor;
            
        if (partida.getJogadorDaVez().equals(jogador.getOrdemJogada())) return 1;
        else return 0;
    }

    @Override
    public String obtemGrade(Integer idJogador) throws RemoteException {
        Partida partida = getMinhaPartida(idJogador);
        String grade = "";
        
        if (partida == null) return "";
        
        Tabuleiro tabuleiro = partida.getTabuleiro();
        
        for (int i=0; i < tabuleiro.getNumColuna(); i++) {
            for (int j=0; j < tabuleiro.getNumColuna(); j++) {
                grade += "[" + tabuleiro.getGrade()[i][j] + "]";
            }
            grade += "\n";
        }
        return grade;
    }

    @Override
    public String obtemOponente(Integer idJogador) throws RemoteException {
        Partida partida = getMinhaPartida(idJogador);
        
        if (partida == null || partida.getJogadores().size() < 2) return "";
        
        return this.jogadores.get(partida.getJogadores().stream().filter(j -> !j.equals(idJogador))
                .collect(Collectors.toList()).get(0)).getNomeJogador();
    }

    @Override
    public int enviaJogada(Integer idJogador, Integer numColuna) throws RemoteException {
        Partida partida = getMinhaPartida(idJogador);
        Jogador jogador = jogadores.get(idJogador);
        
        if (partida == null) return -1;
        
        Tabuleiro tabuleiro = partida.getTabuleiro();
        Integer[][] grade = tabuleiro.getGrade();
        
        if (colunaCheia(tabuleiro, numColuna)) return 0;
        
        for (int i=tabuleiro.getNumColuna(); i > 0; i--) {
            if (null == grade[numColuna][i]) {
                grade[numColuna][i] = jogador.getOrdemJogada();
                return 1;
            }
        }
        return -1;        
    }

    @Override
    public synchronized int encerraPartida(Integer idJogador) throws RemoteException {
        Partida partida = getMinhaPartida(idJogador);
        
        if (partida == null) return -1;
        
        Jogador jogador = jogadores.get(idJogador);
        Jogador oponente = this.jogadores.get(partida.getJogadores().stream().filter(j -> !j.equals(idJogador))
                .collect(Collectors.toList()).get(0));

        jogador.setStatus(6);   // 6 -> perdedor por WO
        oponente.setStatus(5);  // 5 -> vencedor por WO
        
        return 0;
    }
    
    private Partida getMinhaPartida(Integer idJogador){
        List<Partida> partidasAux = this.partidas.stream().filter(p -> 
                (p.getJogadores() != null && p.getJogadores().contains(idJogador)))
                .collect(Collectors.toList());
        
        if (partidasAux.isEmpty()) return null;
            
        return partidasAux.get(0);
    }

    private boolean colunaCheia(Tabuleiro tabuleiro, Integer numColuna) {
        for (int i=0; i < tabuleiro.getNumColuna(); i++) {
            if (null == tabuleiro.getGrade()[numColuna][i])
                return false;
        }
        
        return true;
    }
    
    private int verificarVencedor(Partida partida, Jogador jogador) {
        Jogador oponente = this.jogadores.get(partida.getJogadores().stream().filter(j -> !j.equals(jogador.getIdJogador()))
                .collect(Collectors.toList()).get(0));
        Tabuleiro tabuleiro = partida.getTabuleiro();
        boolean vencedor;
        
        vencedor = verificarVertical(tabuleiro, jogador.getOrdemJogada());
        if (!vencedor)
            vencedor = verificarHorizontal(tabuleiro, jogador.getOrdemJogada());
        if (!vencedor)
            vencedor = verificarDiagonal(tabuleiro, jogador.getOrdemJogada());
        
        if (oponente.getStatus() == 2 && vencedor) {    // 4 -> houve empate
            jogador.setStatus(4);
            oponente.setStatus(4);
            return 4;
        }
        if (vencedor) {                                 // 2 -> é o vencedor
            jogador.setStatus(2);
            oponente.setStatus(3);
            return 2;
        }
        if (oponente.getStatus() == 2) {                // 3 -> é o perdedor
            jogador.setStatus(3);
            return 3;
        }
        if (oponente.getStatus() == 5) {                // 6 -> perdedor por WO
            jogador.setStatus(6);
            return 6;
        }
        
        return -1;        
    }

    private boolean verificarVertical(Tabuleiro tabuleiro, Integer jogador) {
        Integer[][] grade = tabuleiro.getGrade();
        Integer size = tabuleiro.getNumColuna();
        for (int i=0; i < size; i++) {
            for (int j=0; j < size; j++) {
                if (null != grade[i][j]) {
                    if (grade[i][j].equals(jogador)) {
                        if (i+1 <= size && grade[i+1][j].equals(jogador)) {
                            if (i+2 <= size && grade[i+2][j].equals(jogador)) {
                                if (i+3 <= size && grade[i+3][j].equals(jogador)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean verificarHorizontal(Tabuleiro tabuleiro, Integer jogador) {
        Integer[][] grade = tabuleiro.getGrade();
        Integer size = tabuleiro.getNumColuna();
        for (int i=0; i < size; i++) {
            for (int j=0; j < size; j++) {
                if (null != grade[i][j]) {
                    if (grade[i][j].equals(jogador)) {
                        if (j+1 <= size && grade[i][j+1].equals(jogador)) {
                            if (j+2 <= size && grade[i][j+2].equals(jogador)) {
                                if (j+3 <= size && grade[i][j+3].equals(jogador)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean verificarDiagonal(Tabuleiro tabuleiro, Integer jogador) {
        Integer[][] grade = tabuleiro.getGrade();
        Integer size = tabuleiro.getNumColuna();
        //Diagonal maior
        for (int i=0; i < size; i++) {
            for (int j=0; j < size; j++) {
                if (null != grade[i][j]) {
                    if (grade[i][j].equals(jogador)) {
                        if (i+1 <= size && j+1 <= size && grade[i+1][j+1].equals(jogador)) {
                            if (i+2 <= size && j+2 <= size && grade[i+2][j+2].equals(jogador)) {
                                if (i+3 <= size && j+3 <= size && grade[i+3][j+3].equals(jogador)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        //Diagonal menor
        for (int i=0; i < size; i++) {
            for (int j=0; j < size; j++) {
                if (null != grade[i][j]) {
                    if (grade[i][j].equals(jogador)) {
                        if (i-1 > -1 && j-1 > -1 && grade[i-1][j-1].equals(jogador)) {
                            if (i-2 > -1 && j-2 > -1 && grade[i-2][j-2].equals(jogador)) {
                                if (i-3 > -1 && j-3 > -1 && grade[i-2][j-3].equals(jogador)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    @Override
    public String getNomeJogador(Integer idJogador) {
        return jogadores.get(idJogador).getNomeJogador();
    }
}