package com.mafia.api;

import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * created by girija-4135 on 24/12/19
 */

@Path("games")
public class GameAPI
{

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGames() {
        System.out.println("***** inside get all games *******");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello", "ajhd");
        return Response.ok().entity(jsonObject.toString()).build();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createGame(String content)
    {
        System.out.println("content = " + content);
        System.out.println("***** inside create game *******");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("post", "dummy");
        return Response.ok().entity(jsonObject.toString()).build();
    }

}
