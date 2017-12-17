package cz.muni.fi;


import cz.muni.fi.entity.CraftComponent;
import cz.muni.fi.entity.Spacecraft;
import cz.muni.fi.entity.User;
import cz.muni.fi.services.CraftComponentService;
import cz.muni.fi.services.MissionService;
import cz.muni.fi.services.SpacecraftService;
import cz.muni.fi.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZonedDateTime;

@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

	final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

	@Autowired
	private UserService userService;
	@Autowired
	private CraftComponentService craftComponentService;
	@Autowired
	private MissionService missionService;
	@Autowired
	private SpacecraftService spacecraftService;


	@Override
	public void loadData() throws IOException {
		loadCC();
		loadUsers();
	}


	private void loadUsers() {

		User user = new User();
		user.setName("ADMIN");
		user.setBirthDate(LocalDate.now().minusYears(20));
		user.setEmail("admin@admin.com");
		user.setPassword("password");
		user.setManager(true);
		userService.addUser(user);

		User gagarin = new User();
		gagarin.setName("Gagarin");
		gagarin.setBirthDate(LocalDate.of(1934, Month.MARCH, 9));
		gagarin.setEmail("gagarin@gmail.com");
		gagarin.setPassword("gagarin");
		gagarin.setManager(false);
		gagarin.setExperienceLevel(10);
		userService.addUser(gagarin);

	}


	private void loadCC() {
		CraftComponent craftComponent = new CraftComponent();
		craftComponent.setName("Wing");
		craftComponent.setReadyToUse(false);
		craftComponent.setReadyDate(ZonedDateTime.now().plusDays(5));
		craftComponentService.addComponent(craftComponent);

		craftComponent = new CraftComponent();
		craftComponent.setName("Fuel tank");
		craftComponent.setReadyToUse(true);
		//craftComponent.setReadyDate(ZonedDateTime.now().plusDays(5));
		craftComponentService.addComponent(craftComponent);

		craftComponent = new CraftComponent();
		craftComponent.setName("Engine");
		craftComponent.setReadyToUse(false);
		craftComponent.setReadyDate(ZonedDateTime.now().plusDays(40));
		craftComponentService.addComponent(craftComponent);

		craftComponent = new CraftComponent();
		craftComponent.setName("Cockpit");
		craftComponent.setReadyToUse(true);
//		craftComponent.setReadyDate(ZonedDateTime.now().plusDays(5));
		craftComponentService.addComponent(craftComponent);

	}

	private void loadSpacecrafts() {
		Spacecraft spacecraft = new Spacecraft();
		spacecraft.setName("Apollo 11");
		spacecraft.setType("Manned spacecraft");
	}

}
