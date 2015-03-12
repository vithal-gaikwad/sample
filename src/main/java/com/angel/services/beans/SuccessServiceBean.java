package com.angel.services.beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "success")
@XmlType(propOrder = { "code", "message", "timestamp" })
public class SuccessServiceBean {

    private String Message;
    private String Code;
    private Long Timestamp;

    @XmlElement(name = "Message")
    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    @XmlElement(name = "Code")
    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    @XmlElement(name = "Timestamp")
    public Long getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(Long timestamp) {
        Timestamp = timestamp;
    }

}
