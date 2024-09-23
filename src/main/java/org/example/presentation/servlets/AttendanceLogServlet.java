package org.example.presentation.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.application.ServiceFactory;
import org.example.application.service.JournalService;
import org.example.presentation.dtos.AttendanceLogDto;
import org.example.presentation.dtos.AttendanceLogRequest;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/api/v1/attendancelog")
public class AttendanceLogServlet extends HttpServlet {

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
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) {
        setCorsHeaders(response);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);
        try {
            ObjectMapper mapper = new ObjectMapper();
            AttendanceLogRequest attendanceLogRequest = mapper.readValue(request.getInputStream(), AttendanceLogRequest.class);
            Integer attendanceLogId = getService().createAttendanceLog(
                    attendanceLogRequest.getStudentId(),
                    attendanceLogRequest.getLessonId(),
                    attendanceLogRequest.getAttended());

            setJsonResponseHeaders(response);

            mapper.writeValue(response.getOutputStream(), new AttendanceLogDto(
                    attendanceLogId,
                    attendanceLogRequest.getStudentId(),
                    attendanceLogRequest.getLessonId(),
                    attendanceLogRequest.getAttended()));
        } catch (SQLException e) {
            throw new ServletException("Error creating attendance log", e);
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
