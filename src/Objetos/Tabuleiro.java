package Objetos;

/**
 *
 * @author Allan.Amaral
 */
public class Tabuleiro {
    
    private Integer numColuna;
    private Integer[][] grade;

    public Tabuleiro(Integer numColuna) {
        this.numColuna = numColuna;
        this.grade = new Integer[numColuna][numColuna];
    }
    
    public Integer getNumColuna() {
        return numColuna;
    }

    public void setNumColuna(Integer numColuna) {
        this.numColuna = numColuna;
    }

    public Integer[][] getGrade() {
        return grade;
    }

    public void setGrade(Integer[][] grade) {
        this.grade = grade;
    }
    
}