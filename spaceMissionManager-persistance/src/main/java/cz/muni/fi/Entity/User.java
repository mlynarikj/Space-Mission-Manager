package cz.muni.fi.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

/**
 * Created by jsmadis
 *
 * @author jsmadis
 */

@Entity
//In Derby, its forbiden to 'USER' is reserved keyword, we need to rename table
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private LocalDate birthDate;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = ".+@.+\\....?")
    @NotNull
    private String email;

    @NotNull
    private String password;

    private boolean isManager;

    private int experienceLevel;


    private boolean rejectedMission;

    private String explanation;

    @ManyToOne(fetch = FetchType.LAZY)
    private Mission mission;

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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public int getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(int experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public boolean isRejectedMission() {
        return rejectedMission;
    }

    public void setRejectedMission(boolean rejectedMission) {
        this.rejectedMission = rejectedMission;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        if(this.mission != null && this.mission.equals(mission)) return;
        this.mission = mission;
        mission.addAstronaut(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return getEmail() != null ? getEmail().equals(user.getEmail()) : user.getEmail() == null;
    }

    @Override
    public int hashCode() {
        return getEmail() != null ? getEmail().hashCode() : 0;
    }
}