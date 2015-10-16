package Objetos;

import java.util.List;

/**
 *
 * @author Allan.Amaral
 */
public class Partida {
    
    private List<Integer> jogadores;
    private Tabuleiro tabuleiro;
    private Integer jogadorDaVez;

    public Partida(List<Integer> jogadores, Tabuleiro tabuleiro) {
        this.jogadores = jogadores;
        this.tabuleiro = tabuleiro;
        this.jogadorDaVez = 0;
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

    public Integer getJogadorDaVez() {
        return jogadorDaVez;
    }

    public void setJogadorDaVez(Integer jogadorDaVez) {
        this.jogadorDaVez = jogadorDaVez;
    }

}