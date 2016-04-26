package pl.edu.agh.kis.soa.resources;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import pl.edu.agh.kis.soa.resources.model.Student;

/**
 * Klasa wystawiająca interfejs REST.
 * @author teacher
 *
 */
@Path("/rest")
@Stateless
public class StudentResource {
	private static final String FILE_PATH = "/Users/Guest/Desktop/REST.pdf";

	private static final Logger logger = Logger.getLogger("StudentResource");
	
	@GET
	@Path("/getStudent")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(@QueryParam("id") String albumNo) {
		Student s = new Student();
		s.setAlbumNo(albumNo);
		s.setFirstName("Jan");
		s.setLastName("Nowak");
		List<String> subjects = new ArrayList<String>();
		subjects.add("Bazy danych");
		subjects.add("SOA");
		s.setSubjects(subjects);
		return s;
	}

	@GET
	@Path("/pdf")
	@Produces("application/pdf")
	public Response getFile() {

		File file = new File(FILE_PATH);

		javax.ws.rs.core.Response.ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
				"attachment; filename=new-android-book.pdf");
		return response.build();

	}
}
