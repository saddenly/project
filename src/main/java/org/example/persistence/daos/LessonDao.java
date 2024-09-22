package org.example.persistence.daos;

import org.example.persistence.entities.LessonEntity;

import java.sql.SQLException;
import java.util.List;

public class LessonDao extends DaoBase {

    private static final String LESSON_ID_COLUMN_NAME = "id";
    private static final String DATE_COLUMN_NAME = "lesson_date";
    private static final String TABLE_NAME = "lesson";

    private static final String SELECT_FROM_LESSON_SCRIPT = "SELECT * FROM " + TABLE_NAME;

    public LessonDao(String url, String userName, String password) {
        super(url, userName, password);
    }

    public List<LessonEntity> getLessons() throws SQLException {
        return getList(SELECT_FROM_LESSON_SCRIPT, result -> {
            int lessonId = result.getInt(LESSON_ID_COLUMN_NAME);
            Long date = result.getLong(DATE_COLUMN_NAME);
            return new LessonEntity(lessonId, date);
        });
    }
}
