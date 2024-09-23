package org.example.presentation.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.application.ServiceFactory;
import org.example.application.service.JournalService;
import org.example.presentation.dtos.JournalDto;
import org.example.presentation.mappers.Mapper;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/api/v1/journal")
public class JournalServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            throw new ServletException("MySQL driver not found", ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCorsHeaders(resp);
        try {
            JournalDto journal = Mapper.toJournalDto(getService().getJournal());

            setJsonResponseHeaders(resp);

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(resp.getOutputStream(), journal);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving journal", e);
        }
    }

    private void setJsonResponseHeaders(HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }
    private void setCorsHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }

    private JournalService getService() {
        return ServiceFactory.createJournalService();
    }
}