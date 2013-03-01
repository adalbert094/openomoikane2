package omoikane.inventarios;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.util.converter.DefaultStringConverter;
import omoikane.sistema.TextFieldTableCell;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormatSymbols;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Octavio
 * Date: 27/02/13
 * Time: 05:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class TomaInventarioController implements Initializable {

    public Logger logger = Logger.getLogger(getClass());

    ObservableList<ItemTomaInventario> items;

    @FXML TableView<ItemTomaInventario> itemsTable;
    @FXML TableColumn<ItemTomaInventario, String> codigoCol;
    @FXML TableColumn<ItemTomaInventario, String> nombreProductoCol;
    @FXML TableColumn<ItemTomaInventario, BigDecimal> conteoCol;
    @FXML Label idLabel;
    @FXML TextField codigoTextField;
    Character decimalSeparator = getDecimalSeparator();

    private Character getDecimalSeparator() {
        DecimalFormatSymbols custom=new DecimalFormatSymbols();
        return custom.getDecimalSeparator();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        codigoCol        .setCellValueFactory(new PropertyValueFactory<ItemTomaInventario, String>("codigo"));
        codigoCol        .setCellFactory(new ImprovedCellFactory<ItemTomaInventario>());

        nombreProductoCol.setCellValueFactory(new PropertyValueFactory<ItemTomaInventario, String>("nombre"));
        nombreProductoCol.setCellFactory(new ImprovedCellFactory<ItemTomaInventario>());

        conteoCol        .setCellValueFactory(new PropertyValueFactory<ItemTomaInventario, BigDecimal>("conteo"));
        conteoCol        .setCellFactory(new NumericCellFactory<ItemTomaInventario>());

        codigoTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER) {
                    items.add(new ItemTomaInventario(codigoTextField.getText(), "", new BigDecimal("0")));
                }
            }
        });

        items = FXCollections.observableArrayList();
        ItemTomaInventario item = new ItemTomaInventario();
        item.setNombre(new SimpleStringProperty("prueba"));
        item.setCodigo(new SimpleStringProperty("0001"));
        items.add(item);
        itemsTable.setItems(items);

    }
    public class ImprovedCellFactory<S> implements Callback<TableColumn<S, String>, TableCell<S, String>> {

        @Override
        public TableCell<S, String> call(TableColumn<S, String> stTableColumn) {
            return new ImprovedTableCell<S, String>();
        }

    }

    public class NumericCellFactory<S> implements Callback<TableColumn<S, BigDecimal>, TableCell<S, BigDecimal>> {

        @Override
        public TableCell<S, BigDecimal> call(TableColumn<S, BigDecimal> stTableColumn) {
            return new NumericTableCell<S, BigDecimal>();
        }

    }

    public class NumericTableCell<S, T> extends ImprovedTableCell<S, BigDecimal> {

        public NumericTableCell() {
            super(new BigDecimalStringConverter());
        }

        @Override
        public void postCreateTextField(TextField t) {
            super.postCreateTextField(t);
            textField.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
                public void handle(KeyEvent t) {
                    Integer pos = textField.caretPositionProperty().get();
                    String newVal = textField.getText().substring(0, pos)
                            + t.getCharacter()
                            + textField.getText().substring(pos);


                    Pattern pattern = Pattern.compile("^\\d*(\\"+decimalSeparator+"?\\d{0,2})$");
                    Matcher matcher = pattern.matcher(newVal);
                    if (!matcher.find()) {
                        t.consume();
                    }

                }
            });

        }
    }

    public class ImprovedTableCell<S, T> extends TextFieldTableCell<S, T> {

        public ImprovedTableCell() {
            super((StringConverter<T>) new DefaultStringConverter());
        }
        public ImprovedTableCell(StringConverter<T> sc) {
            super(sc);
        }

        @Override
        public void postCreateTextField(TextField t) {

            t.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER || t.getCode() == KeyCode.TAB) {
                        t.consume();
                        if (getConverter() == null) {
                            throw new IllegalStateException(
                                    "Attempting to convert text input into Object, but provided "
                                            + "StringConverter is null. Be sure to set a StringConverter "
                                            + "in your cell factory.");
                        }
                        commitEdit(getConverter().fromString(textField.getText()));
                        getTableView().getFocusModel().focusRightCell();
                        getTableView().edit(getTableRow().getIndex(), getTableView().getFocusModel().getFocusedCell().getTableColumn());

                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });
        }

        @Override
        public void startEdit() {
            super.startEdit();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    textField.requestFocus();
                }
            });
        }
    }

}
