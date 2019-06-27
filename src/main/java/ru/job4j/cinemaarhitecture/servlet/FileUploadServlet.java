package ru.job4j.cinemaarhitecture.servlet;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    private String filePath, tempPath;
    private int maxFileSize = 5242880;
    private int maxMemSize = 5 * 1024;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    public void init() throws ServletException {
        filePath = getServletContext().getInitParameter("file-upload");
        tempPath = getServletContext().getInitParameter("temp-upload");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String UPLOAD_DIRECTORY = "d:/uploads";

        System.out.println(req.getHeaderNames());
        if (ServletFileUpload.isMultipartContent(req)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(req);
                System.out.println(multiparts.size());
                for (FileItem item : multiparts) {
                    System.out.println(item);
                    if (!item.isFormField()) {
//                        File fileSaveDir = new File(UPLOAD_DIRECTORY);
//                        if (!fileSaveDir.exists()) {
//                            fileSaveDir.mkdir();
//                        }
                        String name = new File(item.getName()).getName();
                        System.out.println(name);
//                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
//                        Photo photo = ServiceAddObjects.getInstance().addPhoto(new File(item.getName()));
//                        System.out.println(photo);
                    }
                }
            } catch (Exception e) {
                // exception handling
            }

            PrintWriter out = resp.getWriter();
            out.print("{\"status\":1}");
        }

    }
}
