/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoir2rest;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "MEMOIR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Memoir.findAll", query = "SELECT m FROM Memoir m")
    , @NamedQuery(name = "Memoir.findByMemid", query = "SELECT m FROM Memoir m WHERE m.memid = :memid")
    , @NamedQuery(name = "Memoir.findByMoviename", query = "SELECT m FROM Memoir m WHERE m.moviename = :moviename")
    , @NamedQuery(name = "Memoir.findByReleasedate", query = "SELECT m FROM Memoir m WHERE m.releasedate = :releasedate")
    , @NamedQuery(name = "Memoir.findByWatchdate", query = "SELECT m FROM Memoir m WHERE m.watchdate = :watchdate")
    , @NamedQuery(name = "Memoir.findByWatchtime", query = "SELECT m FROM Memoir m WHERE m.watchtime = :watchtime")
    , @NamedQuery(name = "Memoir.findByComment", query = "SELECT m FROM Memoir m WHERE m.comment = :comment")
    , @NamedQuery(name = "Memoir.findByPerid", query = "SELECT m FROM Memoir m WHERE m.perid.perid = :perid")
    , @NamedQuery(name = "Memoir.findByCinid", query = "SELECT m FROM Memoir m WHERE m.cinid.cinid = :cinid")
    , @NamedQuery(name = "Memoir.findByMovienameANDCinemaname2", query = "SELECT m FROM Memoir m WHERE m.moviename = :moviename AND m.cinid.cinemaname = :cinemaname")    
    , @NamedQuery(name = "Memoir.findByRatingscore", query = "SELECT m FROM Memoir m WHERE m.ratingscore = :ratingscore")})
public class Memoir implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEMID")
    private Integer memid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MOVIENAME")
    private String moviename;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RELEASEDATE")
    @Temporal(TemporalType.DATE)
    private Date releasedate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "WATCHDATE")
    @Temporal(TemporalType.DATE)
    private Date watchdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "WATCHTIME")
    @Temporal(TemporalType.TIME)
    private Date watchtime;
    @Size(max = 50)
    @Column(name = "COMMENT")
    private String comment;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "RATINGSCORE")
    private BigDecimal ratingscore;
    @JoinColumn(name = "CINID", referencedColumnName = "CINID")
    @ManyToOne(optional = false)
    private Cinema cinid;
    @JoinColumn(name = "PERID", referencedColumnName = "PERID")
    @ManyToOne(optional = false)
    private Person perid;

    public Memoir() {
    }

    public Memoir(Integer memid) {
        this.memid = memid;
    }

    public Memoir(Integer memid, String moviename, Date releasedate, Date watchdate, Date watchtime, BigDecimal ratingscore) {
        this.memid = memid;
        this.moviename = moviename;
        this.releasedate = releasedate;
        this.watchdate = watchdate;
        this.watchtime = watchtime;
        this.ratingscore = ratingscore;
    }

    public Integer getMemid() {
        return memid;
    }

    public void setMemid(Integer memid) {
        this.memid = memid;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public Date getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }

    public Date getWatchdate() {
        return watchdate;
    }

    public void setWatchdate(Date watchdate) {
        this.watchdate = watchdate;
    }

    public Date getWatchtime() {
        return watchtime;
    }

    public void setWatchtime(Date watchtime) {
        this.watchtime = watchtime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getRatingscore() {
        return ratingscore;
    }

    public void setRatingscore(BigDecimal ratingscore) {
        this.ratingscore = ratingscore;
    }

    public Cinema getCinid() {
        return cinid;
    }

    public void setCinid(Cinema cinid) {
        this.cinid = cinid;
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
        hash += (memid != null ? memid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Memoir)) {
            return false;
        }
        Memoir other = (Memoir) object;
        if ((this.memid == null && other.memid != null) || (this.memid != null && !this.memid.equals(other.memid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "memoir2rest.Memoir[ memid=" + memid + " ]";
    }
    
}
