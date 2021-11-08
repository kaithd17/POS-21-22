package at.kaindorf.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Teacher {
    @XmlElement(name = "Kürzel")
    private String initials;
    @XmlElement(name = "Vorname")
    private String firstname;
    @XmlElement(name = "Familienname")
    private String lastname;
    @XmlElement(name = "Titel")
    private String title;
    @XmlElement(name = "Klasse")
    private String className;
    @XmlElement(name = "Schüler")
    private int size;
    @XmlElement(name = "Raum")
    private String room;
}
