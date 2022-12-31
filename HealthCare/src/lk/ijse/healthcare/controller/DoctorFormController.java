package lk.ijse.healthcare.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.healthcare.dao.custom.impl.DoctorDaoImpl;
import lk.ijse.healthcare.entity.Doctor;
import lk.ijse.healthcare.view.tm.DoctorTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class DoctorFormController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContact;
    public TableView<DoctorTm> tblDoctors;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colOption;
    public JFXButton btnSaveDoctor;
    public TextField txtSearch;
    private String sText="";

    public void initialize(){
       colId.setCellValueFactory(new PropertyValueFactory<>("id"));
       colName.setCellValueFactory(new PropertyValueFactory<>("name"));
       colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
       colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
       colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        searchData(sText);

        tblDoctors.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if(newValue!=null){
                        setData(newValue);
                    }
                });


        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            sText=newValue;
            searchData(sText);
        });
    }

    private void setData(DoctorTm tm) {
        btnSaveDoctor.setText("Update Doctor");
        txtId.setText(tm.getId());
        txtName.setText(tm.getName());
        txtAddress.setText(tm.getAddress());
        txtContact.setText(tm.getContact());
    }

    private void searchData(String sText) {
        try {
            ArrayList<Doctor> list = new DoctorDaoImpl().searchDoctors(sText);
            ObservableList<DoctorTm> tmList= FXCollections.observableArrayList();

            for (Doctor d: list) {
                Button btn=new Button("Delete");
                tmList.add((new DoctorTm(d.getdId(),d.getName(),d.getAddress(),d.getContact(),btn)));

                btn.setOnAction(event ->{
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure?",ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType =alert.showAndWait();
                    if(buttonType.get()==ButtonType.YES){
                        try{

                            if (new DoctorDaoImpl().delete(d.getdId())){
                                new Alert(Alert.AlertType.CONFIRMATION,"Deleted!").show();
                                searchData(sText);
                            }else {
                                new Alert(Alert.AlertType.CONFIRMATION,"Try Again!").show();
                            }
                        } catch (SQLException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } );


            }
            tblDoctors.setItems(tmList);
        }catch (SQLException |ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }

    public void saveDoctorOnAction(ActionEvent actionEvent) {
        Doctor d1=new Doctor(txtId.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText());
        if(btnSaveDoctor.getText().equals("Save Doctor")){
            try{
                boolean isSaved=new DoctorDaoImpl().save(d1);
                if (isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION,"Saved!").show();
                    searchData(sText);
                }else {
                    new Alert(Alert.AlertType.CONFIRMATION,"Try Again!").show();
                }
            }catch(ClassNotFoundException | SQLException ex){
                new Alert(Alert.AlertType.CONFIRMATION,"Try Again!").show();
                ex.printStackTrace();
            }
        }else {
            try{
                boolean isUpdated=new DoctorDaoImpl().update(d1);
                if (isUpdated){
                    new Alert(Alert.AlertType.CONFIRMATION,"Updated!").show();
                    searchData(sText);
                }else {
                    new Alert(Alert.AlertType.CONFIRMATION,"Try Again!").show();
                }
            }catch(ClassNotFoundException | SQLException ex){
                new Alert(Alert.AlertType.CONFIRMATION,"Try Again!").show();
                ex.printStackTrace();
            }
        }


    }

    public void btnNewDoctorOnAction(ActionEvent actionEvent) {
        btnSaveDoctor.setText("Save Doctor");

    }
}
