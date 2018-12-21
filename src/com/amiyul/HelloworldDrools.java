package com.amiyul;

import java.io.IOException;

import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloworldDrools {

    private static Logger log = LoggerFactory.getLogger(HelloworldDrools.class);

    private KieServices kieServices = KieServices.Factory.get();

    public static void main(String[] args) throws IOException {
	KieSession kieSession = new HelloworldDrools().getKieContainer().newKieSession();
	// kieSession.addEventListener( new DebugAgendaEventListener());
	// kieSession.addEventListener( new DebugRuleRuntimeEventListener() );
	kieSession.insert(new HelloworldImpl());
	log.info("Applied " + kieSession.fireAllRules() + " rules");

    }

    public KieFileSystem getKieFileSystem() throws IOException {
	KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
	String[] ruleFilenames = new String[] { "helloworld.drl" };
	for (String ruleFilename : ruleFilenames) {
	    String resource = "com/amiyul/" + ruleFilename;
	    kieFileSystem.write(ResourceFactory.newClassPathResource(resource, "UTF-8"));
	}
	return kieFileSystem;
    }

    public KieContainer getKieContainer() throws IOException {
	KieRepository kieRepository = kieServices.getRepository();

	kieRepository.addKieModule(new KieModule() {
	    public ReleaseId getReleaseId() {
		return kieRepository.getDefaultReleaseId();
	    }
	});

	kieServices.newKieBuilder(getKieFileSystem()).buildAll();

	return kieServices.newKieContainer(kieRepository.getDefaultReleaseId());
    }

}
