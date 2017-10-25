package Entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mlynarikj
 */

@Entity
public class Mission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@OneToMany
	private List<User> astronauts = new ArrayList<User>();

	private String destination;
	private ZonedDateTime eta;
	private String missionDescription;

	private Boolean active;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
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

	public List<User> getAstronauts() {
		return astronauts;
	}

	public void setAstronauts(List<User> astronauts) {
		this.astronauts = astronauts;
	}
}