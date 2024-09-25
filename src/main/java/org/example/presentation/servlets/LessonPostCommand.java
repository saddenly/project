package org.example.presentation.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.presentation.dtos.LessonDto;
import org.example.presentation.dtos.LessonRequest;

import java.io.IOException;
import java.sql.SQLException;

public class LessonPostCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            LessonRequest lessonRequest = mapper.readValue(request.getInputStream(), LessonRequest.class);
            Integer lessonId = getService().createLesson(lessonRequest.getLessonDate());

            mapper.writeValue(response.getOutputStream(), new LessonDto(lessonId, lessonRequest.getLessonDate()));
        } catch (SQLException e) {
            throw new ServletException("Error creating lesson", e);
        }
    }
}

