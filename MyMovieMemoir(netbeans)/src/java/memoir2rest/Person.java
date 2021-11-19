/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoir2rest;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 73768
 */
@Entity
@Table(name = "PERSON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
    , @NamedQuery(name = "Person.findByPerid", query = "SELECT p FROM Person p WHERE p.perid = :perid")
    , @NamedQuery(name = "Person.findByFname", query = "SELECT p FROM Person p WHERE p.fname = :fname")
    , @NamedQuery(name = "Person.findBySname", query = "SELECT p FROM Person p WHERE p.sname = :sname")
    , @NamedQuery(name = "Person.findByGender", query = "SELECT p FROM Person p WHERE p.gender = :gender")
    , @NamedQuery(name = "Person.findByDob", query = "SELECT p FROM Person p WHERE p.dob = :dob")
    , @NamedQuery(name = "Person.findByAddress", query = "SELECT p FROM Person p WHERE p.address = :address")
    , @NamedQuery(name = "Person.findByPstate", query = "SELECT p FROM Person p WHERE p.pstate = :pstate")
    , @NamedQuery(name = "Person.findByPostcode", query = "SELECT p FROM Person p WHERE p.postcode = :postcode")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERID")
    private Integer perid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "FNAME")
    private String fname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SNAME")
    private String sname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "GENDER")
    private String gender;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ADDRESS")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PSTATE")
    private String pstate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POSTCODE")
    private short postcode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perid")
    private Collection<Memoir> memoirCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perid")
    private Collection<Credential> credentialCollection;

    public Person() {
    }

    public Person(Integer perid) {
        this.perid = perid;
    }

    public Person(Integer perid, String fname, String sname, String gender, Date dob, String address, String pstate, short postcode) {
        this.perid = perid;
        this.fname = fname;
        this.sname = sname;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.pstate = pstate;
        this.postcode = postcode;
    }

    public Integer getPerid() {
        return perid;
    }

    public void setPerid(Integer perid) {
        this.perid = perid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPstate() {
        return pstate;
    }

    public void setPstate(String pstate) {
        this.pstate = pstate;
    }

    public short getPostcode() {
        return postcode;
    }

    public void setPostcode(short postcode) {
        this.postcode = postcode;
    }

    @XmlTransient
    public Collection<Memoir> getMemoirCollection() {
        return memoirCollection;
    }

    public void setMemoirCollection(Collection<Memoir> memoirCollection) {
        this.memoirCollection = memoirCollection;
    }

    @XmlTransient
    public Collection<Credential> getCredentialCollection() {
        return credentialCollection;
    }

    public void setCredentialCollection(Collection<Credential> credentialCollection) {
        this.credentialCollection = credentialCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perid != null ? perid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.perid == null && other.perid != null) || (this.perid != null && !this.perid.equals(other.perid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "memoir2rest.Person[ perid=" + perid + " ]";
    }
    
}
