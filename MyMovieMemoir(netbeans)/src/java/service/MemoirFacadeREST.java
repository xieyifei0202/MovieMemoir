/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import memoir2rest.Memoir;
import javax.persistence.Query;
import java.sql.Date;
import java.sql.Time;
import java.math.BigDecimal;
import javax.persistence.TypedQuery;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

/**
 *
 * @author 73768
 */
@Stateless
@Path("memoir2rest.memoir")
public class MemoirFacadeREST extends AbstractFacade<Memoir> {

    @PersistenceContext(unitName = "MyMovieMemoir2PU")
    private EntityManager em;

    public MemoirFacadeREST() {
        super(Memoir.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Memoir entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Memoir entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Memoir find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("findByMoviename/{moviename}")
    @Produces({"application/json"})
    public List<Memoir> findByMoviename(@PathParam("moviename") String moviename) {
        Query query = em.createNamedQuery("Memoir.findByMoviename");
        query.setParameter("moviename", moviename);
        return query.getResultList();
    }
    
    @GET
    @Path("findByReleasedate/{releasedate}")
    @Produces({"application/json"})
    public List<Memoir> findByReleasedate(@PathParam("releasedate") Date releasedate) {
        Query query = em.createNamedQuery("Memoir.findByReleasedate");
        query.setParameter("releasedate", releasedate);
        return query.getResultList();
    }
    
    @GET
    @Path("findByWatchdate/{watchdate}")
    @Produces({"application/json"})
    public List<Memoir> findByWatchdate(@PathParam("watchdate") Date watchdate) {
        Query query = em.createNamedQuery("Memoir.findByWatchdate");
        query.setParameter("watchdate", watchdate);
        return query.getResultList();
    }
    
    @GET
    @Path("findByWatchtime/{watchtime}")
    @Produces({"application/json"})
    public List<Memoir> findByWatchtime(@PathParam("watchtime") Time watchtime) {
        Query query = em.createNamedQuery("Memoir.findByWatchtime");
        query.setParameter("watchtime", watchtime);
        return query.getResultList();
    }
    
    @GET
    @Path("findByComment/{comment}")
    @Produces({"application/json"})
    public List<Memoir> findByComment(@PathParam("comment") String comment) {
        Query query = em.createNamedQuery("Memoir.findBycomment");
        query.setParameter("comment", comment);
        return query.getResultList();
    }
    
    @GET
    @Path("findByRatingscore/{ratingscore}")
    @Produces({"application/json"})
    public List<Memoir> findByRatingscore(@PathParam("ratingscore") BigDecimal ratingscore) {
        Query query = em.createNamedQuery("Memoir.findByRatingscore");
        query.setParameter("ratingscore", ratingscore);
        return query.getResultList();
    }
    
    @GET
    @Path("findByPerid/{perid}")
    @Produces({"application/json"})
    public List<Memoir> findByPerid(@PathParam("perid") Integer perid) {
        Query query = em.createNamedQuery("Memoir.findByPerid");
        query.setParameter("perid", perid);
        return query.getResultList();
    }
    
    @GET
    @Path("findByCinid/{cinid}")
    @Produces({"application/json"})
    public List<Memoir> findByCinid(@PathParam("cinid") Integer cinid) {
        Query query = em.createNamedQuery("Memoir.findByCinid");
        query.setParameter("cinid", cinid);
        return query.getResultList();
    }
        
    @GET
    @Path("findByMovienameANDCinemaname/{moviename}/{cinemaname}")
    @Produces({"application/json"})
    public List<Memoir> findByMovienameANDCinemaname(@PathParam("moviename") String moviename, @PathParam("cinemaname") String cinemaname) {
        TypedQuery<Memoir> q = em.createQuery("SELECT m FROM Memoir m WHERE m.moviename = :moviename AND m.cinid.cinemaname = :cinemaname", Memoir.class);
        q.setParameter("moviename", moviename);
        q.setParameter("cinemaname", cinemaname);
        return q.getResultList();
    }
    
    @GET
    @Path("findByMovienameANDCinemaname2/{moviename}/{cinemaname}")
    @Produces({"application/json"})
    public List<Memoir> findByMovienameANDCinemaname2(@PathParam("moviename") String moviename, @PathParam("cinemaname") String cinemaname) {
        Query query = em.createNamedQuery("Memoir.findByMovienameANDCinemaname2");
        query.setParameter("moviename", moviename);
        query.setParameter("cinemaname", cinemaname);
        return query.getResultList();
    }
    
    @GET
    @Path("task4a/{perid}/{startdate}/{enddate}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object task4a(@PathParam("perid") Integer perid, @PathParam("startdate") Date startdate, @PathParam("enddate") Date enddate) {
        TypedQuery<Object[]> q = em.createQuery("SELECT m.cinid.cinemalocation, COUNT (m.memid) FROM Memoir m WHERE m.perid.perid = :perid AND m.watchdate BETWEEN :startdate AND :enddate GROUP BY m.cinid.cinemalocation", Object[].class);
        q.setParameter("perid", perid);
        q.setParameter("startdate", startdate);
        q.setParameter("enddate", enddate);
        List<Object[]> queryList = q.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Object[] row : queryList) {
            JsonObject recordObject = Json.createObjectBuilder().
                     add("cinemalocation", (String)row[0])
                    .add("total", (Long)row[1]).build();
            arrayBuilder.add(recordObject);
        }
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }
    
    @GET
    @Path("task4b/{perid}/{year}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object task4b(@PathParam("perid") Integer perid, @PathParam("year") String year) {
        TypedQuery<Object[]> q = em.createQuery("SELECT EXTRACT(MONTH FROM m.watchdate), COUNT (m.memid) FROM Memoir m WHERE m.perid.perid = :perid AND EXTRACT(YEAR FROM m.watchdate) = :year GROUP BY EXTRACT(MONTH FROM m.watchdate)", Object[].class);
        q.setParameter("perid", perid);
        q.setParameter("year", year);
        List<Object[]> queryList = q.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Object[] row : queryList) {
            String month;
            if ((Integer)row[0] == 1) {month = "Jan";}
            else if ((Integer)row[0] == 2) {month = "Feb";}
            else if ((Integer)row[0] == 3) {month = "March";}
            else if ((Integer)row[0] == 4) {month = "Apr";}
            else if ((Integer)row[0] == 5) {month = "May";}
            else if ((Integer)row[0] == 6) {month = "Jun";}
            else if ((Integer)row[0] == 7) {month = "Jul";}
            else if ((Integer)row[0] == 8) {month = "Aug";}
            else if ((Integer)row[0] == 9) {month = "Sep";}
            else if ((Integer)row[0] == 10) {month = "Oct";}
            else if ((Integer)row[0] == 11) {month = "Nov";}
            else month = "Dec";
            JsonObject recordObject = Json.createObjectBuilder().
                     add("monthname", month)
                    .add("total", (Long)row[1]).build();
            arrayBuilder.add(recordObject);
        }
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }
    
    @GET
    @Path("task4c/{perid}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object task4c(@PathParam("perid") Integer perid) {
        TypedQuery<Object[]> q = em.createQuery("SELECT m.moviename, CAST(m.releasedate AS VARCHAR(20)), m.ratingscore FROM Memoir m WHERE m.perid.perid = :perid AND m.ratingscore = (SELECT MAX(n.ratingscore) FROM Memoir n)", Object[].class);
        q.setParameter("perid", perid);
        List<Object[]> queryList = q.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Object[] row : queryList) {
            JsonObject recordObject = Json.createObjectBuilder().
                     add("moviename", (String)row[0])
                    .add("releasedate", (String)row[1])
                    .add("highestRatingScore", (BigDecimal)row[2]).build();
            arrayBuilder.add(recordObject);
        }
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }
    
    @GET
    @Path("task4d/{perid}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object task4d(@PathParam("perid") Integer perid) {
        TypedQuery<Object[]> q = em.createQuery("SELECT m.moviename, EXTRACT(YEAR FROM m.releasedate) FROM Memoir m WHERE m.perid.perid = :perid AND EXTRACT(YEAR FROM m.releasedate) = EXTRACT(YEAR FROM m.watchdate)", Object[].class);
        q.setParameter("perid", perid);
        List<Object[]> queryList = q.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Object[] row : queryList) {
            JsonObject recordObject = Json.createObjectBuilder().
                     add("moviename", (String)row[0])
                    .add("releaseyear", (Integer)row[1]).build();
            arrayBuilder.add(recordObject);
        }
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }
    
    @GET
    @Path("task4e/{perid}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object task4e(@PathParam("perid") Integer perid) {
        TypedQuery<Object[]> q = em.createQuery("SELECT m.moviename, EXTRACT(YEAR FROM m.releasedate) FROM Memoir m WHERE m.perid.perid = :perid AND m.moviename IN (SELECT n.moviename FROM Memoir n GROUP BY n.moviename HAVING COUNT(n.moviename) > 1)", Object[].class);
        q.setParameter("perid", perid);
        List<Object[]> queryList = q.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Object[] row : queryList) {
            JsonObject recordObject = Json.createObjectBuilder().
                     add("moviename", (String)row[0])
                    .add("releaseyear", (Integer)row[1]).build();
            arrayBuilder.add(recordObject);
        }
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }
    
    @GET
    @Path("task4f/{perid}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object task4f(@PathParam("perid") Integer perid) {
        TypedQuery<Object[]> q = em.createQuery("SELECT n.moviename, CAST(n.releasedate AS VARCHAR(20)), n.ratingscore FROM Memoir n WHERE n.perid.perid = :perid ORDER BY n.ratingscore DESC, n.releasedate DESC", Object[].class);
        q.setParameter("perid", perid);
        List<Object[]> queryList = q.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (int i = 0 ; i < 5 ; i ++) {
            JsonObject recordObject = Json.createObjectBuilder().
                     add("moviename", (String)queryList.get(i)[0])
                    .add("releasedate", (String)queryList.get(i)[1])
                    .add("highestRatingScore", (BigDecimal)queryList.get(i)[2]).build();
            arrayBuilder.add(recordObject);
        }
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Memoir> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Memoir> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
