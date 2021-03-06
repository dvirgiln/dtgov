package org.overlord.dtgov.demos.project;

import javax.jws.WebService;

@WebService(endpointInterface = "org.overlord.dtgov.demos.project.HelloWorld",
            serviceName = "HelloWorld")
public class HelloWorldImpl implements HelloWorld {
	
	public String sayHi(String text) {
        System.out.println("sayHi called"); //$NON-NLS-1$
        return "Hello " + text; //$NON-NLS-1$
    }
	
}
