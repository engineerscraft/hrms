/**
 * 
 */
package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.hamdard.hua.model.Country;

/**
 * @author Biswajit
 *
 */
public class CountryRowMapper implements RowMapper<Country> {

    public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
        Country country = new Country();
        country.setCountryId(rs.getInt("COUNTRY_ID"));
        country.setCountryName(rs.getString("COUNTRY_NAME"));
        return country;
    }
}
