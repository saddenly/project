package org.example.presentation.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.presentation.dtos.JournalDto;
import org.example.presentation.mappers.Mapper;

import java.io.IOException;
import java.sql.SQLException;

public class JournalGetCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            JournalDto journal = Mapper.toJournalDto(getService().getJournal());

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), journal);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving journal", e);
        }
    }
}