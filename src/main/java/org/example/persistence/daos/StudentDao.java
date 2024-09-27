package org.example.persistence.daos;

import org.example.persistence.entities.StudentEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StudentDao extends DaoBase {

    private static final String STUDENT_ID_COLUMN_NAME = "id";
    private static final String NAME_COLUMN_NAME = "name";
    private static final String TEAM_ID_COLUMN_NAME = "team_id";
    private static final String ACTIVE_COLUMN_NAME = "active";
    private static final String TABLE_NAME = "Student";

    private static final String SELECT_FROM_STUDENT_SCRIPT = "SELECT * FROM " + TABLE_NAME;
    private static final String SOFT_DELETE_SCRIPT = "UPDATE " + TABLE_NAME + " SET active = 0 WHERE id = ?";

    public StudentDao(String url, String userName, String password) {
        super(url, userName, password);
    }

    public List<StudentEntity> getStudents() throws SQLException {
        return getList(SELECT_FROM_STUDENT_SCRIPT, result -> {
            int studentId = result.getInt(STUDENT_ID_COLUMN_NAME);
            String studentName = result.getString(NAME_COLUMN_NAME);
            int teamId = result.getInt(TEAM_ID_COLUMN_NAME);
            boolean active = result.getBoolean(ACTIVE_COLUMN_NAME);
            return new StudentEntity(studentId, studentName, teamId, active);
        });
    }

    public void deleteStudent(Integer studentId) throws SQLException {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement stmt = connection.prepareStatement(SOFT_DELETE_SCRIPT)) {
                stmt.setInt(1, studentId);
                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Deleting student failed, no rows affected.");
                }

                connection.commit();
            }
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e.addSuppressed(e1);
                }
            }
            throw e;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
