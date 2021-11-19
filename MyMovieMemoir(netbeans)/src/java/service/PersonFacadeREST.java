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
import memoir2rest.Person;
import javax.persistence.Query;
import java.sql.Date;
import javax.persistence.TypedQuery;

/**
 *
 * @author 73768
 */
@Stateless
@Path("memoir2rest.person")
public class PersonFacadeREST extends AbstractFacade<Person> {

    @PersistenceContext(unitName = "MyMovieMemoir2PU")
    private EntityManager em;

    public PersonFacadeREST() {
        super(Person.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Person entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Person entity) {
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
    public Person find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("findByFname/{fname}")
    @Produces({"application/json"})
    public List<Person> findByFname(@PathParam("fname") String fname) {
        Query query = em.createNamedQuery("Person.findByFname");
        query.setParameter("fname", fname);
        return query.getResultList();
    }
    
    @GET
    @Path("findBySname/{sname}")
    @Produces({"application/json"})
    public List<Person> findBySname(@PathParam("sname") String sname) {
        Query query = em.createNamedQuery("Person.findBySname");
        query.setParameter("sname", sname);
        return query.getResultList();
    }
    
    @GET
    @Path("findByGender/{gender}")
    @Produces({"application/json"})
    public List<Person> findByGender(@PathParam("gender") String gender) {
        Query query = em.createNamedQuery("Person.findByGender");
        query.setParameter("gender", gender);
        return query.getResultList();
    }
    
    @GET
    @Path("findByDob/{dob}")
    @Produces({"application/json"})
    public List<Person> findByDob(@PathParam("dob") Date dob) {
        Query query = em.createNamedQuery("Person.findByDob");
        query.setParameter("dob", dob);
        return query.getResultList();
    }
    
    @GET
    @Path("findByAddress/{address}")
    @Produces({"application/json"})
    public List<Person> findByAddress(@PathParam("address") String address) {
        Query query = em.createNamedQuery("Person.findByAddress");
        query.setParameter("address", address);
        return query.getResultList();
    }
    
    @GET
    @Path("findByPstate/{pstate}")
    @Produces({"application/json"})
    public List<Person> findByPstate(@PathParam("pstate") String pstate) {
        Query query = em.createNamedQuery("Person.findByPstate");
        query.setParameter("pstate", pstate);
        return query.getResultList();
    }
    
    @GET
    @Path("findByPostcode/{postcode}")
    @Produces({"application/json"})
    public List<Person> findByPostcode(@PathParam("postcode") Integer postcode) {
        Query query = em.createNamedQuery("Person.findByPostcode");
        query.setParameter("postcode", postcode);
        return query.getResultList();
    }
    
    @GET
    @Path("findByFnameANDSnameANDGender/{fname}/{sname}/{gender}")
    @Produces({"application/json"})
    public List<Person> findByFnameANDSnameANDGender(@PathParam("fname") String fname, @PathParam("sname") String sname, @PathParam("gender") String gender) {
        TypedQuery<Person> q = em.createQuery("SELECT P FROM Person p WHERE p.fname = :fname AND p.sname = :sname AND p.gender = :gender", Person.class);
        q.setParameter("fname", fname);
        q.setParameter("sname", sname);
        q.setParameter("gender", gender);
        return q.getResultList();
    }
    
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Person> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Person> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
