package com.hamdard.hua.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author Jyotirmoy Banerjee
 * 
 */

public class DateAdapter extends XmlAdapter<String, Date> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public String marshal(Date v) throws Exception {
        synchronized (dateFormat) {
            if(v == null)
                return null;
            return dateFormat.format(v);
        }
    }

    @Override
    public Date unmarshal(String v) throws Exception {
        synchronized (dateFormat) {
            if(v == null || v.trim().length() == 0)
                return null;
            return dateFormat.parse(v);
        }
    }

}


