package models;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;




import play.Logger;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import plugins.S3Plugin;

/**
 * This class is used to save the table for users and s3File
 * @author xingweiyang
 *
 */
@Entity
public class Users extends Model {
	@Id
	public String email;
	@Constraints.Required
	public String password;
	
	public static Finder<String,Users> find = new Finder<String,Users>(
		    String.class, Users.class);
	
	@Override
    public void save() {
            super.save(); // assigns an id
    }
	
	
	public Users find(String email) {
        return find.byId(email); // assigns an id
}
}
