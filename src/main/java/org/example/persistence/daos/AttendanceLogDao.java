package org.example.persistence.daos;

import org.example.persistence.entities.AttendanceLogEntity;

import java.sql.SQLException;
import java.util.List;

public class AttendanceLogDao extends DaoBase {
    private static final String ID_COLUMN_NAME = "id";
    private static final String STUDENT_ID_COLUMN_NAME = "student_id";
    private static final String LESSON_ID_COLUMN_NAME = "lesson_id";
    private static final String ATTENDED_COLUMN_NAME = "attended";
    private static final String TABLE_NAME = "attendancelog";

    private static final String SELECT_FROM_ATTENDANCE_LOG_SCRIPT = "SELECT * FROM " + TABLE_NAME;

    public AttendanceLogDao(String url, String userName, String password) {
        super(url, userName, password);
    }


    public List<AttendanceLogEntity> getAttendanceLogs() throws SQLException {

        return getList(SELECT_FROM_ATTENDANCE_LOG_SCRIPT, result -> {
            int id = result.getInt(ID_COLUMN_NAME);
            int studentId = result.getInt(STUDENT_ID_COLUMN_NAME);
            int lessonId = result.getInt(LESSON_ID_COLUMN_NAME);
            Boolean isAttended = result.getBoolean(ATTENDED_COLUMN_NAME);
            return new AttendanceLogEntity(id, studentId, lessonId, isAttended);
        });
    }
}
