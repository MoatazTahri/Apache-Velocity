package com.example.springvelocity.Batch.Mapper;

import com.example.springvelocity.Email.Entity.Email;
import com.example.springvelocity.Notification.Enumeration.EmailType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

// utilise Selma mapper, une petite formation requise.
public class EmailRowMapper implements RowMapper<Email> {
    @Override
    public Email mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Email.builder()
                .id(rs.getInt("id"))
                .templateName(rs.getString("template_name"))
                .jsonParams(rs.getString("json_params"))
                .subject(rs.getString("subject"))
                .emailType(EmailType.valueOf(rs.getString("email_type")))
                .sent(rs.getBoolean("sent"))
                .build();
    }
}
