package cz.muni.fi.Entity;

import javax.persistence.*;
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
	private List<CraftComponent> allComponents;

	@ManyToOne(fetch = FetchType.LAZY)
	private Mission mission;



	//METHODS

	//TODO managing components - add, remove...

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CraftComponent> getAllComponents() {
		return allComponents;
	}


	public int unfinishedComponentsCount(){
		int count = allComponents.size();
		for (CraftComponent c : allComponents){
			if(c.isReadyToUse()){
				count--;
			}
		}
		return count;
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