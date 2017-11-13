package cz.muni.fi.config;

import cz.muni.fi.dto.CraftComponentDTO;
import cz.muni.fi.dto.MissionDTO;
import cz.muni.fi.dto.SpacecraftDTO;
import cz.muni.fi.dto.UserDTO;
import cz.muni.fi.entity.CraftComponent;
import cz.muni.fi.entity.Mission;
import cz.muni.fi.entity.Spacecraft;
import cz.muni.fi.entity.User;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;

public class EntityMapping extends BeanMappingBuilder {
	@Override
	protected void configure() {
		mapping(User.class, UserDTO.class, TypeMappingOptions.mapNull(false));
		mapping(Mission.class, MissionDTO.class, TypeMappingOptions.mapNull(false));
		mapping(CraftComponent.class, CraftComponentDTO.class, TypeMappingOptions.mapNull(false));
		mapping(Spacecraft.class, SpacecraftDTO.class, TypeMappingOptions.mapNull(false));
		//TODO add other mapping options in future
	}
}
