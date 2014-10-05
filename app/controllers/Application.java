package controllers;

import models.S3File;
import play.db.ebean.Model;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http;
import views.html.index;
import views.html.login;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import models.Users;
import play.api.data.*;
//import static play.api.data.Forms.*;
//import static play.api.data.Form.*;


public class Application extends Controller {

	
	public static Result login(){
		return ok(login.render());
	}
	
	public static Result enter(){
		System.out.println(request().body());
		if(request().body().isMaxSizeExceeded()) {
			
		    return badRequest("Too much data!");
		  }
		Map<String, String[]> params = request().body().asFormUrlEncoded();
		System.out.println(params);
		String email = params.get("email")[0];
		return redirect(routes.Application.index());
	}
    public static Result index() {
        List<S3File> uploads = new Model.Finder(UUID.class, S3File.class).all();
        return ok(index.render(uploads));
    }
    
    public static Result jobInterests() {
        return ok("Under Construction");
    }
    public static Result upload() {
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart uploadFilePart = body.getFile("upload");
        if (uploadFilePart != null) {
            S3File s3File = new S3File();
            s3File.name = uploadFilePart.getFilename();
            s3File.file = uploadFilePart.getFile();
            s3File.save();
            return redirect(routes.Application.jobInterests());
        }
        else {
            return badRequest("File upload error");
        }
    }

}