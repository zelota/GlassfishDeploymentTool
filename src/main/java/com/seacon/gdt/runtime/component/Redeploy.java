package com.seacon.gdt.runtime.component;

import com.seacon.gdt.runtime.AsadminCommandExecuter;
import com.seacon.gdt.utility.PasswordFileHandler;
import com.seacon.gdt.xml.objects.servers.Target;
import java.net.URISyntaxException;

/**
 * http://docs.oracle.com/cd/E19798-01/821-1750/giulr/index.html
 *
 * @author varsanyi.peter
 */
public class Redeploy extends AsadminCommandExecuter {

    public Redeploy(String asadminPath, Target targetServer) throws URISyntaxException {
        super(asadminPath, targetServer);
        setProcessInfo("Redeploy component");
    }

    public void setParameters(com.seacon.gdt.xml.objects.data.Component componentData, com.seacon.gdt.xml.objects.data.Component parentAppData) throws URISyntaxException, Exception {
        getParameters().add("-H");
        getParameters().add(getTargetServer().getHost());
        getParameters().add("-p");
        getParameters().add(getTargetServer().getPort());
        getParameters().add("-u");
        getParameters().add(getTargetServer().getUser());
        getParameters().add("--passwordfile");
        getParameters().add("\"" + PasswordFileHandler.getPasswordFilePath() + "\"");

        getParameters().add("redeploy");
        
        getParameters().add(componentData.getName());
    }

}