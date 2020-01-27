package com.mafia.api;

//import com.mafia.persistence.entity.User;
//import com.mafia.persistence.util.HibernateUtil;
//import org.hibernate.Session;
import com.mafia.persistence.mysql.util.DBOperations;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * created by girija-4135 on 03/01/20
 */
@Path("users")
public class UserAPI {
    private static final Logger LOGGER = Logger.getLogger(UserAPI.class.getName());

    /**
     *  public static void main( String[] args )
     *     {
     *         System.out.println("Maven + Hibernate + MySQL");
     *         Session session = HibernateUtil.getSessionFactory().openSession();
     *
     *         session.beginTransaction();
     *         Stock stock = new Stock();
     *
     *         stock.setStockCode("4715");
     *         stock.setStockName("GENM");
     *
     *         session.save(stock);
     *         session.getTransaction().commit();
     *     }
     */

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createGame(String content)
    {
        LOGGER.log(Level.INFO,"********* Inside Create User ************************");
        LOGGER.log(Level.INFO,"********* Content ***********************" + content);

       /* ObjectMapper objectMapper = new ObjectMapper();
        try {
            User user = objectMapper.readValue(content, User.class);
            LOGGER.log(Level.INFO,"User Name = " + user.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }*/


       JSONObject userData = new JSONObject(content).optJSONObject("users");
        try {
            long id =DBOperations.addUser(userData.optString("name"),userData.optString("email"));

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "User created successfully with id - " + id);
            return Response.ok().entity(jsonObject.toString()).build();

        } catch (SQLException e) {
            e.printStackTrace();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "User already exists!!!!");
            return Response.serverError().entity(jsonObject.toString()).build();
        } catch (Exception e){
            return Response.serverError().entity("Internal server error"+e.toString()).build();
        }

    }
}
