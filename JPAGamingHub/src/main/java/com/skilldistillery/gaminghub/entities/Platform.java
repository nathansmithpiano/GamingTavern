package com.skilldistillery.gaminghub.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Platform {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private boolean enabled;
	private String name;
	private String type;
	private String description;
	private LocalDateTime created;
	private LocalDateTime updated;
	@ManyToMany
	@JoinTable(
	        name = "alias_platform", 
	        joinColumns = @JoinColumn(name = "alias_id") , 
	        inverseJoinColumns =  @JoinColumn(name = "platform_id") 
	    )
	private List<Alias> alias;
	
	public Platform() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	public List<Alias> getAlias() {
		return alias;
	}

	public void setAlias(List<Alias> alias) {
		this.alias = alias;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alias, created, description, enabled, id, name, type, updated);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Platform other = (Platform) obj;
		return Objects.equals(alias, other.alias) && Objects.equals(created, other.created)
				&& Objects.equals(description, other.description) && enabled == other.enabled && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(type, other.type)
				&& Objects.equals(updated, other.updated);
	}

	@Override
	public String toString() {
		return "Platform [id=" + id + ", enabled=" + enabled + ", name=" + name + ", type=" + type + ", description="
				+ description + ", created=" + created + ", updated=" + updated + ", alias=" + alias + "]";
	}

	
	
}
