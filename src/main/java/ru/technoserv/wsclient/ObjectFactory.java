
package ru.technoserv.wsclient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.technoserv.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ChangeGradeResponse_QNAME = new QName("http://ws.technoserv.ru/", "changeGradeResponse");
    private final static QName _ChangeGrade_QNAME = new QName("http://ws.technoserv.ru/", "changeGrade");
    private final static QName _ChangeSalary_QNAME = new QName("http://ws.technoserv.ru/", "changeSalary");
    private final static QName _ChangePosition_QNAME = new QName("http://ws.technoserv.ru/", "changePosition");
    private final static QName _ChangePositionResponse_QNAME = new QName("http://ws.technoserv.ru/", "changePositionResponse");
    private final static QName _ChangeSalaryResponse_QNAME = new QName("http://ws.technoserv.ru/", "changeSalaryResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.technoserv.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ChangeSalaryResponse }
     * 
     */
    public ChangeSalaryResponse createChangeSalaryResponse() {
        return new ChangeSalaryResponse();
    }

    /**
     * Create an instance of {@link ChangePosition }
     * 
     */
    public ChangePosition createChangePosition() {
        return new ChangePosition();
    }

    /**
     * Create an instance of {@link ChangePositionResponse }
     * 
     */
    public ChangePositionResponse createChangePositionResponse() {
        return new ChangePositionResponse();
    }

    /**
     * Create an instance of {@link ChangeGrade }
     * 
     */
    public ChangeGrade createChangeGrade() {
        return new ChangeGrade();
    }

    /**
     * Create an instance of {@link ChangeSalary }
     * 
     */
    public ChangeSalary createChangeSalary() {
        return new ChangeSalary();
    }

    /**
     * Create an instance of {@link ChangeGradeResponse }
     * 
     */
    public ChangeGradeResponse createChangeGradeResponse() {
        return new ChangeGradeResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeGradeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.technoserv.ru/", name = "changeGradeResponse")
    public JAXBElement<ChangeGradeResponse> createChangeGradeResponse(ChangeGradeResponse value) {
        return new JAXBElement<ChangeGradeResponse>(_ChangeGradeResponse_QNAME, ChangeGradeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeGrade }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.technoserv.ru/", name = "changeGrade")
    public JAXBElement<ChangeGrade> createChangeGrade(ChangeGrade value) {
        return new JAXBElement<ChangeGrade>(_ChangeGrade_QNAME, ChangeGrade.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeSalary }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.technoserv.ru/", name = "changeSalary")
    public JAXBElement<ChangeSalary> createChangeSalary(ChangeSalary value) {
        return new JAXBElement<ChangeSalary>(_ChangeSalary_QNAME, ChangeSalary.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangePosition }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.technoserv.ru/", name = "changePosition")
    public JAXBElement<ChangePosition> createChangePosition(ChangePosition value) {
        return new JAXBElement<ChangePosition>(_ChangePosition_QNAME, ChangePosition.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangePositionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.technoserv.ru/", name = "changePositionResponse")
    public JAXBElement<ChangePositionResponse> createChangePositionResponse(ChangePositionResponse value) {
        return new JAXBElement<ChangePositionResponse>(_ChangePositionResponse_QNAME, ChangePositionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeSalaryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.technoserv.ru/", name = "changeSalaryResponse")
    public JAXBElement<ChangeSalaryResponse> createChangeSalaryResponse(ChangeSalaryResponse value) {
        return new JAXBElement<ChangeSalaryResponse>(_ChangeSalaryResponse_QNAME, ChangeSalaryResponse.class, null, value);
    }

}
