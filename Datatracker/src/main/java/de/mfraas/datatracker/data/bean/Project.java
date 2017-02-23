package de.mfraas.datatracker.data.bean;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Created by marcelfraas on 21.01.17.
 */

@Entity
public class Project {

    @Id
    private String id = UUID.randomUUID().toString();
    
    private String protokollführer;

    private String speicherort;

    private String faserhalbzeug;

    private String harzsystem;

    private String trägermaterial;

    private String kaschiermaterial;

    private String spalthöhe;

    private String verfahren;

    private String bahnverlauf;

    private String fadenspannung;

    private String drehmoment;

    private String spaltA;

    private String spaltB;

    private String zugA;

    private String zugB;

    private String flächengewicht;

    private String harzgehalt;

    private String prepregbreite;

    public String getId() {
        return id;
    }

    public String getProtokollführer() {
        return protokollführer;
    }

    public void setProtokollführer(String protokollführer) {
        this.protokollführer = protokollführer;
    }

    public String getSpeicherort() {
        return speicherort;
    }

    public void setSpeicherort(String speicherort) {
        this.speicherort = speicherort;
    }

    public String getFaserhalbzeug() {
        return faserhalbzeug;
    }

    public void setFaserhalbzeug(String faserhalbzeug) {
        this.faserhalbzeug = faserhalbzeug;
    }

    public String getHarzsystem() {
        return harzsystem;
    }

    public void setHarzsystem(String harzsystem) {
        this.harzsystem = harzsystem;
    }

    public String getTrägermaterial() {
        return trägermaterial;
    }

    public void setTrägermaterial(String trägermaterial) {
        this.trägermaterial = trägermaterial;
    }

    public String getKaschiermaterial() {
        return kaschiermaterial;
    }

    public void setKaschiermaterial(String kaschiermaterial) {
        this.kaschiermaterial = kaschiermaterial;
    }

    public String getSpalthöhe() {
        return spalthöhe;
    }

    public void setSpalthöhe(String spalthöhe) {
        this.spalthöhe = spalthöhe;
    }

    public String getVerfahren() {
        return verfahren;
    }

    public void setVerfahren(String verfahren) {
        this.verfahren = verfahren;
    }

    public String getBahnverlauf() {
        return bahnverlauf;
    }

    public void setBahnverlauf(String bahnverlauf) {
        this.bahnverlauf = bahnverlauf;
    }

    public String getFadenspannung() {
        return fadenspannung;
    }

    public void setFadenspannung(String fadenspannung) {
        this.fadenspannung = fadenspannung;
    }

    public String getDrehmoment() {
        return drehmoment;
    }

    public void setDrehmoment(String drehmoment) {
        this.drehmoment = drehmoment;
    }

    public String getSpaltA() {
        return spaltA;
    }

    public void setSpaltA(String spaltA) {
        this.spaltA = spaltA;
    }

    public String getSpaltB() {
        return spaltB;
    }

    public void setSpaltB(String spaltB) {
        this.spaltB = spaltB;
    }

    public String getZugA() {
        return zugA;
    }

    public void setZugA(String zugA) {
        this.zugA = zugA;
    }

    public String getZugB() {
        return zugB;
    }

    public void setZugB(String zugB) {
        this.zugB = zugB;
    }

    public String getFlächengewicht() {
        return flächengewicht;
    }

    public void setFlächengewicht(String flächengewicht) {
        this.flächengewicht = flächengewicht;
    }

    public String getHarzgehalt() {
        return harzgehalt;
    }

    public void setHarzgehalt(String harzgehalt) {
        this.harzgehalt = harzgehalt;
    }

    public String getPrepregbreite() {
        return prepregbreite;
    }

    public void setPrepregbreite(String prepregbreite) {
        this.prepregbreite = prepregbreite;
    }
}
