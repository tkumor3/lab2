package pl.edu.agh.kis.soa.resources;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.imageio.ImageIO;
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
	private static final String PNG_PATH = "/Users/Guest/Desktop/Unknown.png";

	private static final Logger logger = Logger.getLogger("StudentResource");
	
	@GET
	@Path("/json")
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
	@GET
	@Path("/image")
	@Produces("image/png")
	public Response getFullImage() {

		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(PNG_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "png", baos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] imageData = baos.toByteArray();

		// uncomment line below to send non-streamed
		 return Response.ok(imageData).build();

		//return Response.ok(new ByteArrayInputStream(imageData)).build();
	}
	@GET
	@Path("/text")
	@Produces("text/plain")
	public String getText(){
		return "Tekst zwracam";
	}

}
