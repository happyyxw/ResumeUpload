package controllers;

import models.S3File;
import play.db.ebean.Model;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http;
import play.mvc.*;
import views.html.index;
import views.html.login;
import views.html.signon;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import models.Users;
import play.api.data.*;
//import static play.api.data.Forms.*;
//import static play.api.data.Form.*;


public class Application extends Controller {

	
	public static Result login(String input){
		return ok(login.render(input));
	}
	
	public static Result signon(){
		return ok(signon.render());
	}
	
	public static Result signOnEnter(){
		if(request().body().isMaxSizeExceeded()) {
			
		    return badRequest("Too much data!");
		  }
		Map<String, String[]> params = request().body().asFormUrlEncoded();
		System.out.println(params);
		String email = params.get("email")[0];
		String password = params.get("password")[0];
		
		Users user = new Users();
		Users existUser = user.find(email);
		if(existUser!=null)
		{
			return redirect(routes.Application.login("Already Registerd; Please Login"));
		}
		user.email = email;
		user.password = password;
		user.save();
		
		return redirect(routes.Application.login(null));
	}
	
	public static Result enter(){
		if(request().body().isMaxSizeExceeded()) {
			
		    return badRequest("Too much data!");
		  }
		Map<String, String[]> params = request().body().asFormUrlEncoded();
		System.out.println(params);
		String email = params.get("email")[0];
		
		
		Users user = new Users();
		Users existUser = user.find(email);
		if(existUser==null)
		{
			return redirect(routes.Application.signon());
		}
		String password = params.get("password")[0];
		System.out.println(password);
		System.out.println(existUser.password);
		if(!existUser.password.equals(password))
		{
			return ok("Wrong password;");
		}
		
		// add authentication
		session().clear();
        session("email", email);
		return redirect(routes.Application.index());
	}
	@Security.Authenticated(Secured.class)
    public static Result index() {
    	
        //List<S3File> uploads = new Model.Finder(UUID.class, S3File.class).all();
        return ok(index.render());
    }
    
    public static Result jobInterests() {
    	session().clear();
        return ok("Resume Uploaded Successfully.");
    }
    public static Result upload() {
    	String email = session().get("email");
    	System.out.println("User Name " + email);
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart uploadFilePart = body.getFile("upload");
        if (uploadFilePart != null) {
            S3File s3File = new S3File();
            s3File.name = uploadFilePart.getFilename();
            s3File.file = uploadFilePart.getFile();
            s3File.emailId = email;
            s3File.save();
            return redirect(routes.Application.jobInterests());
        }
        else {
            return badRequest("File upload error");
        }
    }

}