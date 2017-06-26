/**
 * 
 */
package com.hamdard.hua.repository;

import java.util.List;

import javax.ws.rs.InternalServerErrorException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.hamdard.hua.model.Unit;
import com.hamdard.hua.rowmapper.UnitRowMapper;

/**
 * @author Somdeb
 *
 */
@Component
public class UnitRepository {
	
	private static final Logger logger = LogManager.getLogger(UnitRepository.class);
    private static final Marker sqlMarker = MarkerManager.getMarker("SQL");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${sql.unit.list}")
    private String unitListSql;

	public List<Unit> getUnits(int orgId) {
		try {
			Object[] args={orgId};
			logger.info(sqlMarker, unitListSql);
            List<Unit> units = (List<Unit>) jdbcTemplate.query(unitListSql, args, new UnitRowMapper());
            logger.debug("Retrieved units: {}", () -> units);
            return units;
        } catch (Exception e) {
            logger.error("No parameter found", e);
            throw new InternalServerErrorException();
        }
	}

}
