package com.seacon.gdt.runtime.pool;

import com.seacon.gdt.runtime.GdtCommand;
import com.seacon.gdt.utility.PasswordFileHandler;
import com.seacon.gdt.xml.objects.data.Pool;
import com.seacon.gdt.xml.objects.servers.Target;
import java.net.URISyntaxException;

/**
 * http://docs.oracle.com/cd/E19798-01/821-1751/gharo/index.html
 * 
 * @author varsanyi.peter
 */
public class Drop extends GdtCommand {
    
    public Drop(String asadminPath, Target targetServer) {
        super(asadminPath, targetServer);
        setProcessInfo("Drop pool");
        setCommandExecuteIndex(com.seacon.gdt.xml.Constants.CI_POOL_DROP);
    }

    public void setParameters(Pool poolData) throws URISyntaxException {
        getParameters().add("-H");
        getParameters().add(getTargetServer().getHost());
        getParameters().add("-p");
        getParameters().add(getTargetServer().getPort());
        getParameters().add("-u");
        getParameters().add(getTargetServer().getUser());
        getParameters().add("--passwordfile");
        getParameters().add("\"" + PasswordFileHandler.getPasswordFilePath() + "\"");
        getParameters().add("delete-jdbc-connection-pool");
        
        getParameters().add(poolData.getJndiName());
    }
    
}
