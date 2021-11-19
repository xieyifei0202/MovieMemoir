/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoir2rest;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 73768
 */
@Entity
@Table(name = "CREDENTIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Credential.findAll", query = "SELECT c FROM Credential c")
    , @NamedQuery(name = "Credential.findByCreid", query = "SELECT c FROM Credential c WHERE c.creid = :creid")
    , @NamedQuery(name = "Credential.findByUsername", query = "SELECT c FROM Credential c WHERE c.username = :username")
    , @NamedQuery(name = "Credential.findByPasswordhash", query = "SELECT c FROM Credential c WHERE c.passwordhash = :passwordhash")
    , @NamedQuery(name = "Credential.findByPerid", query = "SELECT c FROM Credential c WHERE c.perid.perid = :perid")       
    , @NamedQuery(name = "Credential.findBySignupdate", query = "SELECT c FROM Credential c WHERE c.signupdate = :signupdate")})
public class Credential implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREID")
    private Integer creid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PASSWORDHASH")
    private String passwordhash;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SIGNUPDATE")
    @Temporal(TemporalType.DATE)
    private Date signupdate;
    @JoinColumn(name = "PERID", referencedColumnName = "PERID")
    @ManyToOne(optional = false)
    private Person perid;

    public Credential() {
    }

    public Credential(Integer creid) {
        this.creid = creid;
    }

    public Credential(Integer creid, String username, String passwordhash, Date signupdate) {
        this.creid = creid;
        this.username = username;
        this.passwordhash = passwordhash;
        this.signupdate = signupdate;
    }

    public Integer getCreid() {
        return creid;
    }

    public void setCreid(Integer creid) {
        this.creid = creid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash;
    }

    public Date getSignupdate() {
        return signupdate;
    }

    public void setSignupdate(Date signupdate) {
        this.signupdate = signupdate;
    }

    public Person getPerid() {
        return perid;
    }

    public void setPerid(Person perid) {
        this.perid = perid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (creid != null ? creid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Credential)) {
            return false;
        }
        Credential other = (Credential) object;
        if ((this.creid == null && other.creid != null) || (this.creid != null && !this.creid.equals(other.creid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "memoir2rest.Credential[ creid=" + creid + " ]";
    }
    
}
