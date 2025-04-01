package lesson25.homework.TaskOne;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

@WebServlet("/download-book")
public class DownloadBookServlet extends HttpServlet {

    private String getBooksPath() {
        return getServletContext().getRealPath("/books");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("1".equals(req.getParameter("list"))) {
            sendBooksList(resp);
            return;
        }

        String requestedFile = req.getParameter("file");
        if (requestedFile != null) {
            handleFileDownload(req, resp, requestedFile);
            return;
        }
        req.getRequestDispatcher("/WEB-INF/download-book.html").forward(req, resp);
    }

    private void handleFileDownload(HttpServletRequest req, HttpServletResponse resp, String fileName)
            throws IOException {
        String booksPath = getBooksPath();
        File bookFile = new File(booksPath, fileName);

        if (bookFile.exists()) {
            String mimeType = getServletContext().getMimeType(bookFile.getAbsolutePath());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }

            resp.setContentType(mimeType);
            resp.setHeader("Content-Disposition",
                    "attachment; filename=\"" + fileName + "\"");
            resp.setContentLength((int) bookFile.length());

            try (InputStream in = new FileInputStream(bookFile);
                 OutputStream out = resp.getOutputStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
        }
    }

    private void sendBooksList(HttpServletResponse resp) throws IOException {
        String booksPath = getBooksPath();
        File booksDir = new File(booksPath);

        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        if (booksDir.exists() && booksDir.isDirectory()) {
            File[] books = booksDir.listFiles();
            if (books != null && books.length > 0) {
                String json = Arrays.stream(books)
                        .filter(File::isFile)
                        .map(File::getName)
                        .collect(Collectors.joining("\", \"", "[\"", "\"]"));
                out.print(json);
                return;
            }
        }
        out.print("[]");
    }
}