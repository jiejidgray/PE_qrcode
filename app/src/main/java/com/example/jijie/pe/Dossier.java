package com.example.jijie.pe;

public class Dossier {
    private int idDoc;
    private String nom;
    private String prenom;
    private String tel;
    private String email;

    public int getIdDoc() {
        return idDoc;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public void setIdDoc(int idDoc) {
        this.idDoc = idDoc;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Dossier() {

    }

}
