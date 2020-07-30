package View;

import Model.Moeda;
import java.net.URL;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PrincipalController implements Initializable {

    private final char separadorDecimal
            = new DecimalFormatSymbols(Locale.getDefault(
                    Locale.Category.FORMAT)).getDecimalSeparator();

    private final NumberFormat nf
            = NumberFormat.getInstance(Locale.getDefault());

    private boolean ehDe = true;
    private double Cotacao = 1;

    private List<Moeda> moedas = new ArrayList<>();

    @FXML
    private TextField txtFldValorDe;

    @FXML
    private TextField txtFldValorPara;

    @FXML
    private Label lblUnDe;

    @FXML
    private Label lblUnPara;

    @FXML
    private ImageView imgSetas;

    @FXML
    private ComboBox<Moeda> cmbMoedas;

    @FXML
    private void btnFecharClick() {
        System.exit(0);
    }

    private String convertido(double val) {
        double v;
        if (ehDe) {
            v = val * Cotacao;
        } else {
            v = val / Cotacao;
        }
        return nf.format(v);
    }

    private void atualizaTxtFld() {
        if (ehDe) {
            try {
                if(!txtFldValorDe.getText().isEmpty()){
                txtFldValorPara.setText(
                        convertido(nf.parse(
                                txtFldValorDe.getText()).doubleValue()));
            }else {txtFldValorPara.setText("0");
                }
            } catch (ParseException ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                if(!txtFldValorPara.getText().isEmpty()){
                txtFldValorDe.setText(
                        convertido(nf.parse(
                                txtFldValorPara.getText()).doubleValue()));
            }else {txtFldValorDe.setText("0");
                }
            }catch (ParseException ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private final ChangeListener<? super String> listenerDe
            = (observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*(\\" + separadorDecimal
                        + "\\d*)?") && !newValue.isEmpty()) {
                    txtFldValorDe.setText(oldValue);
                } else {
                    atualizaTxtFld();
                }
            };
    private final ChangeListener<? super String> listenerPara
            = (observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*(\\" + separadorDecimal
                        + "\\d*)?") && !newValue.isEmpty()) {
                    txtFldValorPara.setText(oldValue);
                } else {
                    atualizaTxtFld();
                }
            };

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        moedas.add(new Moeda("Real Brasil", "R$", "Dolar EUA", "US$", 0.26));
        moedas.add(new Moeda("Dolar EUA", "US$", "Iuan Renmimb China", "元", 6.73));
        moedas.add(new Moeda("Libra Esterlina Reino Unido", "£", "Ienes Japão", "¥",  144.39));
        moedas.add(new Moeda("Euro", "€", "Peso Mexicano", "MXN$", 21.74));
        
        cmbMoedas.setItems(FXCollections.observableList(moedas));
        cmbMoedas.valueProperty().addListener(
                new ChangeListener<Moeda>() {
            @Override
            public void changed(ObservableValue<? extends Moeda> observable,
                    Moeda oldValue, Moeda newValue) {
                Cotacao = newValue.getCotacao();
                lblUnDe.setText(newValue.getSiglaDe());
                lblUnPara.setText(newValue.getSiglaPara());
                atualizaTxtFld();
            }
        });
        cmbMoedas.getSelectionModel().selectFirst();

        // Listener para foco no textfield      
        txtFldValorDe.focusedProperty().addListener(
                (ObservableValue<? extends Boolean> observable,
                        Boolean oldValue, Boolean newValue) -> {
                    if (newValue) { // objeto recebeu foco
                        ehDe = true;
                       imgSetas.setImage(new Image("/image/SetasDireita.png"));
                        txtFldValorDe.textProperty().addListener(listenerDe);
                    } else {
                        txtFldValorDe.textProperty().removeListener(listenerDe);
                    }
                });
        
        txtFldValorPara.focusedProperty().addListener(
                (ObservableValue<? extends Boolean> observable,
                        Boolean oldValue, Boolean newValue) -> {
                    if (newValue) { // objeto recebeu foco
                        ehDe = false;
                       imgSetas.setImage(new Image("/image/SetasEsquerda.png"));
                        txtFldValorPara.textProperty().addListener(listenerPara);
                    } else {
                        txtFldValorPara.textProperty().removeListener(listenerPara);
                    }
                });
        // TODO
    }
}
