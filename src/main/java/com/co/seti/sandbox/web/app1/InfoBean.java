/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.seti.sandbox.web.app1;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author andreslavado
 */
@Named(value = "infoBean")
@SessionScoped
public class InfoBean implements Serializable {

    private static final long serialVersionUID = 57867789989L;
    List<EnvironmentVariable> variables = new ArrayList<>();

    @PostConstruct
    private void init() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            String hostname = ip.getHostName();
            variables.add(new EnvironmentVariable("IP_ADDRESS", ip.toString()));
            variables.add(new EnvironmentVariable("HOSTNAME", hostname));
        } catch (UnknownHostException ex) {
            Logger.getLogger(InfoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        Map<String, String> environment = System.getenv();
        for (Map.Entry<String, String> entry : environment.entrySet()) {
            variables.add(new EnvironmentVariable(entry.getKey(), entry.getValue()));
        }
    }

    public List<EnvironmentVariable> getVariables() {
        return variables;
    }

}
