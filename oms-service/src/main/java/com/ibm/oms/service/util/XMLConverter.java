package com.ibm.oms.service.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
 
public class XMLConverter {
 
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;
 
    public Marshaller getMarshaller() {
        return marshaller;
    }
 
    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }
 
    public Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }
 
    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }
 
    public void convertFromObjectToXML(Object object, String filepath)
        throws IOException {
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(filepath);
            getMarshaller().marshal(object, new StreamResult(os));
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }

    public String convertFromObjectToXMLString(Object object) {
        StringWriter st = new StringWriter();
        Marshaller marshaller = getMarshaller();
        try {
            marshaller.marshal(object, new StreamResult(st));
        } catch (XmlMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return st.toString();

    }
    public Object convertFromXMLToObject(String xmlfile) throws IOException {
 
        FileInputStream is = null;
        try {
            is = new FileInputStream(xmlfile);
            return getUnmarshaller().unmarshal(new StreamSource(is));
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
    public Object convertFromXMLStringToObject(String xmlStr) {
        Object ret = null;
            try {
                ret = getUnmarshaller().unmarshal(new StreamSource(new StringReader(xmlStr)));
            } catch (XmlMappingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return ret;
    }
}
