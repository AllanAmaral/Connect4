package Objetos;

/**
 *
 * @author Allan.Amaral
 */
public class Jogador {
    
    private Integer idJogador;
    private String nomeJogador;
    private Integer ordemJogada;
    private Integer status;

    public Jogador(Integer idJogador, String nomeJogador) {
        this.idJogador = idJogador;
        this.nomeJogador = nomeJogador;
        this.ordemJogada = 0;
        this.status = 0;
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

    public Integer getOrdemJogada() {
        return ordemJogada;
    }

    public void setOrdemJogada(Integer ordemJogada) {
        this.ordemJogada = ordemJogada;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}