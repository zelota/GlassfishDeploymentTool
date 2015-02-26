package com.seacon.gdt.runtime.domain;

import com.seacon.gdt.runtime.GdtCommand;
import com.seacon.gdt.utility.PasswordFileHandler;
import com.seacon.gdt.xml.objects.data.Domain;
import com.seacon.gdt.xml.objects.servers.Target;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * http://docs.oracle.com/cd/E19798-01/821-1751/ggoei/index.html
 *
 * @author varsanyi.peter
 */
public class Create extends GdtCommand {

    private String domainName;
    private String password;

    public Create(String asadminPath, Target targetServer) {
        super(asadminPath, targetServer);
        setProcessInfo("Create domain");
        setCommandExecuteIndex(com.seacon.gdt.xml.Constants.CI_DOMAIN_CREATE);
    }

    @Override
    public void executeBeforeCommand() throws Exception {
        PasswordFileHandler.createDomainPasswordFile(this.domainName, this.password);
    }

    @Override
    public void executeAfterCommand() throws Exception {
        PasswordFileHandler.deleteDomainPasswordFile(this.domainName);
    }

    public void setParameters(Domain domainData) throws URISyntaxException {
        this.domainName = domainData.getName();
        this.password = domainData.getPassword();
        
        getParameters().add("-H");
        getParameters().add(getTargetServer().getHost());
        getParameters().add("-p");
        getParameters().add(getTargetServer().getPort());
        getParameters().add("-u");
        getParameters().add(getTargetServer().getUser());
        if (domainData.getPassword() == null || domainData.getPassword().isEmpty()) {
            getParameters().add("--passwordfile");
            getParameters().add("\"" + PasswordFileHandler.getPasswordFilePath() + "\"");
        } else {
            getParameters().add("--passwordfile");
            getParameters().add("\"" + PasswordFileHandler.getDomainPasswordFilePath(this.domainName)+ "\"");
        }

        getParameters().add("create-domain");

        if (domainData.getAdminport() != null && !domainData.getAdminport().isEmpty()) {
            getParameters().add("--adminport");
            getParameters().add(domainData.getAdminport());
        }

        if (domainData.getInstanceport() != null && !domainData.getInstanceport().isEmpty()) {
            getParameters().add("--instanceport");
            getParameters().add(domainData.getInstanceport());
        }

        if (domainData.getPassword() == null || domainData.getPassword().isEmpty()) {
            getParameters().add("--nopassword");
        }

        getParameters().add(domainData.getName());
    }

}
