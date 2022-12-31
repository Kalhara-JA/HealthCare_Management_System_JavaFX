package lk.ijse.healthcare.view.tm;

import javafx.scene.control.Button;

public class DoctorTm {
    private String id;
    private String name;
    private String address;
    private String Contact;
    private Button btn;

    public DoctorTm() {
    }

    public DoctorTm(String id, String name, String address, String contact, Button btn) {
        this.id = id;
        this.name = name;
        this.address = address;
        Contact = contact;
        this.btn = btn;
    }



    public String getId() {
        return id;
    }

    public void setId(String dId) {
        this.id = dId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
