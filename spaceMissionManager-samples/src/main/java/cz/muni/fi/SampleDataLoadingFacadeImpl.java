package cz.muni.fi;


import cz.muni.fi.services.CraftComponentService;
import cz.muni.fi.services.MissionService;
import cz.muni.fi.services.SpacecraftService;
import cz.muni.fi.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;

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
		//TODO create sample data for in memory DB
	}
}
