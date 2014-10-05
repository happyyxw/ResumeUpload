package models;

import java.util.UUID;

import javax.persistence.Id;

import play.db.ebean.Model;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

/**
 * This class is used to save the table for users and s3File
 * @author xingweiyang
 *
 */
public class Users extends Model {
	@Id
	public String email;
	@Constraints.Required
	public UUID id;
	
	public String fileName;
	public String bucket;
}
