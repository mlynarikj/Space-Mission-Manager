package cz.muni.fi.Entity;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
public class Spacecraft {

	//ATTRIBUTES
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String type; //just probes or astronauts...

    @Column(nullable = false, unique = true)
	private String name;

	@OneToMany(fetch = FetchType.LAZY)
	private List<CraftComponent> components;

	@ManyToOne(fetch = FetchType.LAZY)
	private Mission mission;


	//METHODS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addComponent(CraftComponent c){
		components.add(c);
	}

	public List<CraftComponent> getComponents() {
		return Collections.unmodifiableList(components);
	}

	public void setComponents(List<CraftComponent> components) {
		this.components = components;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Spacecraft)) return false;
		Spacecraft spacecraft = (Spacecraft) o;
		return getName() != null ? getName().equals(spacecraft.getName()) : spacecraft.getName() == null;
	}

	@Override
	public int hashCode() {
		return getName() != null ? getName().hashCode() : 0;
	}
}