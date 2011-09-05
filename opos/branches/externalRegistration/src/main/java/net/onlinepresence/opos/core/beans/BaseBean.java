package net.onlinepresence.opos.core.beans;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class BaseBean implements Identifiable, Serializable{

	private static final long serialVersionUID = 9185592784570536156L;
	private Long _id;
	private String _uuid = UUID.randomUUID().toString();
	
	@SuppressWarnings("unused")
	@Version
	@Column(name = "BASEBEAN_VERSION")
	private int _version;
	
	public BaseBean() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long get_id() {
		return _id;
	}
	
	public void set_id(Long id) {
		this._id = id;
	}

	public void set_id(Serializable id) {
		this.set_id((Long)id);
	}

	@Basic
	public String get_uuid() {
		return _uuid;
	}

	public void set_uuid(String _uuid) {
		this._uuid = _uuid;
	}
	
	@Override
	public int hashCode() {
        if (get_uuid() != null) {
            return get_uuid().hashCode();
        } else {
            return super.hashCode();
        }
    }
	
	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Identifiable)) {
            return false;
        }
        Identifiable other = (Identifiable)o;
        if (get_id() != null) {
        	return get_id().equals(other.get_id());
        }
        if (get_uuid() == null) return false;
        return get_uuid().equals(other.get_uuid());
    }
}
