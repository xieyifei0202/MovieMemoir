/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoir2rest;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 73768
 */
@Entity
@Table(name = "CINEMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cinema.findAll", query = "SELECT c FROM Cinema c")
    , @NamedQuery(name = "Cinema.findByCinid", query = "SELECT c FROM Cinema c WHERE c.cinid = :cinid")
    , @NamedQuery(name = "Cinema.findByCinemaname", query = "SELECT c FROM Cinema c WHERE c.cinemaname = :cinemaname")
    , @NamedQuery(name = "Cinema.findByCinemalocation", query = "SELECT c FROM Cinema c WHERE c.cinemalocation = :cinemalocation")})
public class Cinema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CINID")
    private Integer cinid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CINEMANAME")
    private String cinemaname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CINEMALOCATION")
    private String cinemalocation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cinid")
    private Collection<Memoir> memoirCollection;

    public Cinema() {
    }

    public Cinema(Integer cinid) {
        this.cinid = cinid;
    }

    public Cinema(Integer cinid, String cinemaname, String cinemalocation) {
        this.cinid = cinid;
        this.cinemaname = cinemaname;
        this.cinemalocation = cinemalocation;
    }

    public Integer getCinid() {
        return cinid;
    }

    public void setCinid(Integer cinid) {
        this.cinid = cinid;
    }

    public String getCinemaname() {
        return cinemaname;
    }

    public void setCinemaname(String cinemaname) {
        this.cinemaname = cinemaname;
    }

    public String getCinemalocation() {
        return cinemalocation;
    }

    public void setCinemalocation(String cinemalocation) {
        this.cinemalocation = cinemalocation;
    }

    @XmlTransient
    public Collection<Memoir> getMemoirCollection() {
        return memoirCollection;
    }

    public void setMemoirCollection(Collection<Memoir> memoirCollection) {
        this.memoirCollection = memoirCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cinid != null ? cinid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cinema)) {
            return false;
        }
        Cinema other = (Cinema) object;
        if ((this.cinid == null && other.cinid != null) || (this.cinid != null && !this.cinid.equals(other.cinid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "memoir2rest.Cinema[ cinid=" + cinid + " ]";
    }
    
}
