package com.rest.file;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.glassfish.hk2.utilities.reflection.Constants;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.glassfish.jersey.media.multipart.internal.MultiPartWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.nio.file.Paths;
import java.util.List;

@WebServlet(name = "FileUploadServlet")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    // upload settings
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    private final String SERVICE_URL = "http://localhost:8080/ProjetRest_war_exploded/rest/fileService/";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);
        String fileName = "";
        try {
            // parses the request's content to extract file data
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        fileName = new File(item.getName()).getAbsolutePath();
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message",
                    "There was an error: " + ex.getMessage());
        }

        Client client = ClientBuilder.newBuilder()
                .register(MultiPartFeature.class).build();

        FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("file",
                new File(fileName));
        MultiPart multiPartEntity = new FormDataMultiPart().bodyPart(fileDataBodyPart);

        String callResult = client.target(SERVICE_URL + "upload")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(multiPartEntity,
                        multiPartEntity.getMediaType()),
                        String.class);

        //Use response object to verify upload success

        multiPartEntity.close();


        if (callResult.contains("true")) {
            request.setAttribute("success", "Le fichier a bien été uploadé");
            request.setAttribute("message", "");
            RequestDispatcher dispatcher = request.getRequestDispatcher("upload.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("success", "");
            request.setAttribute("message", "Erreur lors de l'upload du fichier");
            RequestDispatcher dispatcher = request.getRequestDispatcher("upload.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message", "");
        request.setAttribute("success", "");
        RequestDispatcher dispatcher = request.getRequestDispatcher("upload.jsp");
        dispatcher.forward(request, response);
    }
}
