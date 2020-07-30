/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author idomar
 */
public class Moeda {

    private String descricaoDe;
    private String siglaDe;
    private String descricaoPara;
    private String siglaPara;
    private double Cotacao;

    public Moeda(String descricaoDe, String siglaDe, String descricaoPara,
            String siglaPara, double Cotacao) {
        this.descricaoDe = descricaoDe;
        this.siglaDe = siglaDe;
        this.descricaoPara = descricaoPara;
        this.siglaPara = siglaPara;
        setCotacao(Cotacao);
    }

    public Moeda() {
    }

    public String getDescricaoDe() {
        return descricaoDe;
    }

    public void setDescricaoDe(String descricaoDe) {
        this.descricaoDe = descricaoDe;
    }

    public String getSiglaDe() {
        return siglaDe;
    }

    public void setSiglaDe(String siglaDe) {
        this.siglaDe = siglaDe;
    }

    public String getDescricaoPara() {
        return descricaoPara;
    }

    public void setDescricaoPara(String descricaoPara) {
        this.descricaoPara = descricaoPara;
    }

    public String getSiglaPara() {
        return siglaPara;
    }

    public void setSiglaPara(String siglaPara) {
        this.siglaPara = siglaPara;
    }

    public double getCotacao() {
        return Cotacao;
    }

    public void setCotacao(double Cotacao) {
        if (Cotacao > 0) {
            this.Cotacao = Cotacao;
        } else {
            this.Cotacao = 1;
        }
    }

    @Override
    public String toString() {
        return descricaoDe + " - " + descricaoPara;
    }

}
