package lesson25.homework.TaskOne;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.nio.file.*;
import java.util.Set;

@WebServlet("/upload-book")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 15
)
public class UploadBookServlet extends HttpServlet {

    private static final Set<String> ALLOWED_EXTENSIONS = Set.of("txt", "pdf", "fb2");

    private String getBooksPath() {
        // Путь к папке books в webapp
        return getServletContext().getRealPath("/books");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/upload-book.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Part filePart = req.getPart("bookFile");
        if (filePart == null || filePart.getSize() == 0) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "File not found");
            return;
        }

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String fileExt = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();

        if (!ALLOWED_EXTENSIONS.contains(fileExt)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Only files are allowed: " + String.join(", ", ALLOWED_EXTENSIONS));
            return;
        }

        String uploadPath = getBooksPath();
        Path uploadDir = Paths.get(uploadPath);
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        Path filePath = uploadDir.resolve(fileName);
        try (InputStream in = filePart.getInputStream()) {
            Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);

            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Успешная загрузка</title></head><body>");
            out.println("<h1>Книга успешно загружена!</h1>");
            out.println("<p>Файл: " + fileName + "</p>");
            out.println("<a href=\"" + req.getContextPath() + "/download-book\">К списку книг</a>");
            out.println("<p><a href=\"" + req.getContextPath() + "/home-book\">На главную</a></p>");
            out.println("</body></html>");
        } catch (IOException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "File upload error: " + e.getMessage());
        }
    }
}