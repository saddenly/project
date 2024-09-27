package org.example.presentation.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.presentation.dtos.AttendanceLogDto;
import org.example.presentation.dtos.AttendanceLogRequest;

import java.io.IOException;
import java.sql.SQLException;

public class AttendanceLogPostCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            AttendanceLogRequest attendanceLogRequest = mapper.readValue(request.getInputStream(), AttendanceLogRequest.class);
            Integer attendanceLogId = getService().createAttendanceLog(
                    attendanceLogRequest.getStudentId(),
                    attendanceLogRequest.getLessonId(),
                    attendanceLogRequest.getAttended(),
                    attendanceLogRequest.getGrade());

            mapper.writeValue(response.getOutputStream(), new AttendanceLogDto(
                    attendanceLogId,
                    attendanceLogRequest.getStudentId(),
                    attendanceLogRequest.getLessonId(),
                    attendanceLogRequest.getAttended(),
                    attendanceLogRequest.getGrade()));
        } catch (SQLException e) {
            throw new ServletException("Error creating attendance log", e);
        }
    }
}
