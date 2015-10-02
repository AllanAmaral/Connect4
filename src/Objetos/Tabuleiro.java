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
        
        populaGrade();
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

    private void populaGrade() {
        for (Integer[] grade1 : this.grade) {
            for (int j = 0; j<this.grade.length; j++) {
                grade1[j] = 0;
            }
        }
    }
    
}