package com.company;

import com.google.gson.Gson;

import java.beans.XMLEncoder;
import java.io.*;
import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "persona")
public class Persona {
    public String nombre;
    public int edad;
    public String genero;
    public boolean estatus;

    public Persona(){}

    public Persona(String nombre, int edad, String genero) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.estatus = true;
    }

    public Persona(String nombre, int edad, String genero, boolean estatus) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.estatus = estatus;
    }

    public String toString(){
        return "Nombre: "+nombre+". Edad: " + edad + ". Género: " + genero + ". Estatus: " + estatus+"\n";
    }

    public void toXml() throws FileNotFoundException {
        XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("persona.xml")));
        encoder.writeObject(this);
        encoder.close();
        System.out.println("Desde toXML: Se ha creado/actualizado el archivo\n");
    }

    public void fromXml(String xml){
        JAXBContext jaxbContext;
        try
        {
            jaxbContext = JAXBContext.newInstance(Persona.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Persona persona = (Persona) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            System.out.println(persona.toString());

            nombre = persona.nombre;
            edad = persona.edad;
            genero = persona.genero;
            estatus = persona.estatus;
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }

    }

    public void fromJson(String json){
        Gson gson = new Gson();
        Persona persona = gson.fromJson(json, Persona.class);

        nombre = persona.nombre;
        edad = persona.edad;
        genero = persona.genero;
        estatus = persona.estatus;

    }

    public void toJson(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        System.out.println("Desde toJSON: "+ json+"\n");
    }

}