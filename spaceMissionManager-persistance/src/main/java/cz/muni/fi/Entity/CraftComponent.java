package cz.muni.fi.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
public class CraftComponent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private boolean readyToUse;

	@Column(unique = true)
	@NotNull
	private String name;

    @ManyToOne(fetch = FetchType.LAZY)
	private Spacecraft spacecraft;

	private ZonedDateTime readyDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isReadyToUse() {
		return readyToUse;
	}

	public void setReadyToUse(boolean readyToUse) {
		this.readyToUse = readyToUse;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ZonedDateTime getReadyDate() {
		return readyDate;
	}

	public void setReadyDate(ZonedDateTime readyDate) {
		this.readyDate = readyDate;
	}

	public Spacecraft getSpacecraft() {
		return spacecraft;
	}

	public void setSpacecraft(Spacecraft spacecraft) {
		this.spacecraft = spacecraft;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CraftComponent that = (CraftComponent) o;

		return getName().equals(that.getName());
	}

	@Override
	public int hashCode() {
		return getName().hashCode();
	}
}