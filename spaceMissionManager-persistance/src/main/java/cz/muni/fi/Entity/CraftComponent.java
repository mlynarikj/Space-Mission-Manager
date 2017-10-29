package cz.muni.fi.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
public class CraftComponent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NotNull
	private boolean readyToUse;

	@Column
	@NotNull
	private String name;

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


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CraftComponent that = (CraftComponent) o;

		if (isReadyToUse() != that.isReadyToUse()) return false;
		if (!getName().equals(that.getName())) return false;
		return getReadyDate() != null ? getReadyDate().equals(that.getReadyDate()) : that.getReadyDate() == null;
	}

	@Override
	public int hashCode() {
		int result = (isReadyToUse() ? 1 : 0);
		result = 31 * result + getName().hashCode();
		result = 31 * result + (getReadyDate() != null ? getReadyDate().hashCode() : 0);
		return result;
	}
}