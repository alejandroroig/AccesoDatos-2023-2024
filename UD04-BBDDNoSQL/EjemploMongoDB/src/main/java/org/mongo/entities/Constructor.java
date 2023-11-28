package org.mongo.entities;

public class Constructor {
    String constructorref;
    String name;
    String nationality;
    String url;

    public Constructor() {
    }

    public Constructor(String constructorref, String name, String nationality, String url) {
        this.constructorref = constructorref;
        this.name = name;
        this.nationality = nationality;
        this.url = url;
    }

    public String getConstructorref() {
        return constructorref;
    }

    public void setConstructorref(String constructorref) {
        this.constructorref = constructorref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Constructor{" +
                "constructorref='" + constructorref + '\'' +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}



