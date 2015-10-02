package Objetos;

import java.util.List;

/**
 *
 * @author Allan.Amaral
 */
public class Partida {
    
    private List<Integer> jogadores;
    private Tabuleiro tabuleiro;
    private Integer numJogadores;

    public Partida(List<Integer> jogadores, Tabuleiro tabuleiro, Integer numJogadores) {
        this.jogadores = jogadores;
        this.tabuleiro = tabuleiro;
        this.numJogadores = numJogadores;
    }
    
    public List<Integer> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Integer> jogadores) {
        this.jogadores = jogadores;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public Integer getNumJogadores() {
        return numJogadores;
    }

    public void setNumJogadores(Integer numJogadores) {
        this.numJogadores = numJogadores;
    }
    
}