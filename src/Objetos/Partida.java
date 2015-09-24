package Objetos;

import java.util.List;

/**
 *
 * @author Allan.Amaral
 */
public class Partida {
    
    private List<Integer> jogadores;
    private Tabuleiro tabuleiro;

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
    
}