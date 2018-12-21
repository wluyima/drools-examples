package com.amiyul;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LicenceApplication {

    private static Logger log = LoggerFactory.getLogger(LicenceApplication.class);

    public static void main(String[] args) throws Exception {
	log.info("Starting..");
	KieServices kieServices = KieServices.Factory.get();
	KieContainer kContainer = kieServices.getKieClasspathContainer();
	StatelessKieSession kSession = kContainer.newStatelessKieSession();
	Applicant applicant1 = new Applicant("John Doe", 16);
	kSession.execute(applicant1);
	log.info("\n\n" + applicant1.getName() + " is of age: " + applicant1.isValid() + "\n");

	Applicant applicant2 = new Applicant("Jimmy Senyomo", 19);
	kSession.execute(applicant2);
	log.info("\n\n" + applicant2.getName() + " is of age: " + applicant2.isValid() + "\n");

	log.info("Done..");
    }
}
