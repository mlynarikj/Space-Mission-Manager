package cz.muni.fi.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author mlynarikj
 */

@Entity
public class Mission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@OneToMany(fetch = FetchType.LAZY)
	private Set<User> astronauts = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY)
	private Set<Spacecraft> spacecrafts = new HashSet<>();

	@NotNull
	@Column(nullable = false, unique = true)
	private String name;
	private String destination;
	private ZonedDateTime eta;
	private String missionDescription;


	private boolean active;
	private String result;
	private LocalDate endDate;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public ZonedDateTime getEta() {
		return eta;
	}

	public void setEta(ZonedDateTime eta) {
		this.eta = eta;
	}

	public String getMissionDescription() {
		return missionDescription;
	}

	public void setMissionDescription(String missionDescription) {
		this.missionDescription = missionDescription;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Set<User> getAstronauts() {
		return astronauts;
	}

	public void setAstronauts(Set<User> astronauts) {
		this.astronauts = astronauts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Spacecraft> getSpacecrafts() {
		return spacecrafts;
	}

	public void setSpacecrafts(Set<Spacecraft> spacecrafts) {
		this.spacecrafts = spacecrafts;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Mission)) return false;

		Mission mission = (Mission) o;

		return getName() != null ? getName().equals(mission.getName()) : mission.getName() == null;
	}

	@Override
	public int hashCode() {
		return getName() != null ? getName().hashCode() : 0;
	}
}