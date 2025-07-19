package cn.gok.hotel.config;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@MappedTypes(java.util.Date.class)
public class DateTypeHandler extends BaseTypeHandler<java.util.Date> {
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, java.util.Date parameter, JdbcType jdbcType)
            throws SQLException {
        if (parameter != null) {
            ps.setDate(i, new java.sql.Date(parameter.getTime()));
        }
    }
    
    @Override
    public java.util.Date getNullableResult(ResultSet rs, String columnName) throws SQLException {
        java.sql.Date sqlDate = rs.getDate(columnName);
        if (sqlDate != null) {
            // 直接返回，不进行时区转换
            return new java.util.Date(sqlDate.getTime());
        }
        return null;
    }
    
    @Override
    public java.util.Date getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        java.sql.Date sqlDate = rs.getDate(columnIndex);
        if (sqlDate != null) {
            // 直接返回，不进行时区转换
            return new java.util.Date(sqlDate.getTime());
        }
        return null;
    }
    
    @Override
    public java.util.Date getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        java.sql.Date sqlDate = cs.getDate(columnIndex);
        if (sqlDate != null) {
            // 直接返回，不进行时区转换
            return new java.util.Date(sqlDate.getTime());
        }
        return null;
    }
} 