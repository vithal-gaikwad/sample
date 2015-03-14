package com.angel.webservices;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.angel.services.beans.ErrorEntityBean;
import com.angel.services.beans.UserServiceBean;
import com.angel.util.DBUtils;
import com.angel.util.ServiceUtil;

@Path("")
public class BasicService {
	private Logger logger = Logger.getLogger(BasicService.class);

	@Context
	private HttpServletRequest request;	

	@Path("/getUserData")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getUserData(@QueryParam("userid") String userid) {

		if (userid == null || userid.trim().equals("") || userid.equals("0")) {
			ErrorEntityBean errorBean = ServiceUtil.getError("FORM_ERROR", "Please provide userid.");
			return Response.status(412).entity(errorBean).build();
		} else {
			try {
				UserServiceBean userResp = null;
				DBUtils dt = new DBUtils();
				userResp = dt.getData(userid);
				if(userResp!=null)
					return Response.status(200).entity(userResp).build();
				else
					return Response.status(200).entity(ServiceUtil.getError("NO_DATA", "No data with given userid."))
							.build();

			} catch (Exception e) {
				logger.error("ERROR @ getUserData : " + e);
				return Response.status(500).entity(ServiceUtil.getError("ERROR", "Please try again")).build();
			}
		}
	}
	
	@Path("/deleteUser")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteUser(@QueryParam("userid") String userid) {

		if (userid == null || userid.trim().equals("") || userid.equals("0")) {
			ErrorEntityBean errorBean = ServiceUtil.getError("FORM_ERROR", "Please provide userid.");
			return Response.status(412).entity(errorBean).build();
		} else {
			try {
				DBUtils dt = new DBUtils();
				boolean flag = dt.deleteUser(userid);
				if(flag)
					return Response.status(200).entity(ServiceUtil.getSuccess("SUCCESS", "User deleted successfully."))
							.build();
				else
					return Response.status(200).entity(ServiceUtil.getError("NO_DATA", "No data with given userid."))
							.build();

			} catch (Exception e) {
				logger.error("ERROR @ getUserData : " + e);
				return Response.status(500).entity(ServiceUtil.getError("ERROR", "Please try again")).build();
			}
		}
	}
	
	
	@Path("/getAllUsers")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllUsers() {
		
		List<UserServiceBean> userSerList=null;
		DBUtils dt=new DBUtils();
		userSerList=dt.getAllUsers();
		
		return Response.ok().entity(new GenericEntity<List<UserServiceBean>>(userSerList) {}).build();
		
	}

	@POST
	@Path("/postUserData")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response postUserData(@FormParam("lat") String lat, @FormParam("lon") String lon,@FormParam("userid") String userid) {

		if (userid == null || userid.trim().equals("") || userid.equals("0")) {
			ErrorEntityBean errorBean = ServiceUtil.getError("FORM_ERROR", "Please provide userid.");
			return Response.status(412).entity(errorBean).build();
		} else { 
			try {

				DBUtils dt=new DBUtils();
				int result=dt.postData(userid, lat, lon);
				if(result>0)					 
					return Response.status(200).entity(ServiceUtil.getSuccess("SUCCESS", "Data posted successfully.")).build();
				else
					return Response.status(200).entity(ServiceUtil.getError("NO_DATA", "No data posted."))
							.build();


			} catch (Exception e) {
				logger.error("ERROR @ postUserData : " + e);
				return Response.status(500).entity(ServiceUtil.getError("ERROR", "Please try again")).build();
			}
		}
	}

	@POST
	@Path("/registerUser")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response postCoords(@FormParam("userid") String userid) {

		if (userid == null || userid.trim().equals("") || userid.equals("0")) {
			ErrorEntityBean errorBean = ServiceUtil.getError("FORM_ERROR", "Please provide userid.");
			return Response.status(412).entity(errorBean).build();
		} else { 
			try {

				DBUtils dt=new DBUtils();
				String status=dt.isUserExist(userid);
				if (status.equalsIgnoreCase("true"))					 
					return Response.status(200).entity(ServiceUtil.getSuccess("FAILURE", "User already exists please try with another userid.")).build();
				else {
					String isExistStatus=dt.registerUser(userid);
					if (isExistStatus.equalsIgnoreCase("true"))					 
						return Response.status(200).entity(ServiceUtil.getSuccess("SUCCESS", "User registered successfully.")).build();
					else
						return Response.status(200).entity(ServiceUtil.getError("FAILURE", "User not registered."))
								.build();
				}			

			} catch (Exception e) {
				logger.error("ERROR @ postCoords : " + e);				
				return Response.status(500).entity(ServiceUtil.getError("ERROR", "Please try again")).build();
			}
		}
	}
}
