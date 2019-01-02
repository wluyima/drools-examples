package com.amiyul;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

public class FireMonitor {

    public static void main(String[] args) {
	KieSession session = KieServices.Factory.get().getKieClasspathContainer().newKieSession();
	Room livingRoom = new Room("Living Room");
	Sprinkler livingRoomSprinkler = new Sprinkler(livingRoom);
	Room bedroom = new Room("Bed Room");
	Sprinkler bedroomSprinker = new Sprinkler(bedroom);
	// bedroomSprinker.setOn(true);
	session.insert(bedroom);
	session.insert(bedroomSprinker);
	session.insert(livingRoom);
	session.insert(livingRoomSprinkler);

	session.fireAllRules();

	FactHandle fireLivingRoom = session.insert(new Fire(livingRoom));

	FactHandle fireBedRoom = session.insert(new Fire(bedroom));

	session.fireAllRules();

	session.delete(fireLivingRoom);

	session.fireAllRules();

	session.delete(fireBedRoom);

	session.fireAllRules();

	Applicant applicant = new Applicant("Wyclif", 19);
	session.insert(applicant);

	session.fireAllRules();
	System.out.println(applicant.getName() + " is of age:" + applicant.isValid());

	applicant = new Applicant("Arnold", 17);
	session.insert(applicant);

	session.fireAllRules();
	System.out.println(applicant.getName() + " is of age:" + applicant.isValid());

    }

}
