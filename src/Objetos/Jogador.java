package Objetos;

/**
 *
 * @author Allan.Amaral
 */
public class Jogador {
    
    private Integer idJogador;
    private String nomeJogador;

    public Jogador(Integer idJogador, String nomeJogador) {
        this.idJogador = idJogador;
        this.nomeJogador = nomeJogador;
    }
    
    public Integer getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(Integer idJogador) {
        this.idJogador = idJogador;
    }
    
    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

}