
package ru.technoserv.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for changeGrade complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="changeGrade">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="employeeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="GradeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "changeGrade1", propOrder = {
    "employeeId",
    "gradeId"
})
public class ChangeGrade {

    protected int employeeId;
    @XmlElement(name = "GradeId")
    protected int gradeId;

    /**
     * Gets the value of the employeeId property.
     * 
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * Sets the value of the employeeId property.
     * 
     */
    public void setEmployeeId(int value) {
        this.employeeId = value;
    }

    /**
     * Gets the value of the gradeId property.
     * 
     */
    public int getGradeId() {
        return gradeId;
    }

    /**
     * Sets the value of the gradeId property.
     * 
     */
    public void setGradeId(int value) {
        this.gradeId = value;
    }

}
