package com.hamdard.hua.repository;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.hamdard.hua.exception.ResourceNotFound;
import com.hamdard.hua.model.Parameter;
import com.hamdard.hua.rowmapper.ParameterRowMapper;

@Component
public class ParameterRepository {

    private static final Logger logger = LogManager.getLogger(ParameterRepository.class);
    private static final Marker sqlMarker = MarkerManager.getMarker("SQL");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CommonRepository commonRepository;

    @Value("${sql.getParameterDetails}")
    private String parameterDetailsFetchingSql;

    @Value("${sql.setParameterValue}")
    private String parameterValueSettingSql;

    @Value("${sql.getAllParameterDetails}")
    private String allParameterDetailsFetchingSql;

    public List<Parameter> getAllParameters() {
        try {
            logger.info(sqlMarker, allParameterDetailsFetchingSql);
            List<Parameter> parameterList = (List<Parameter>) jdbcTemplate.query(allParameterDetailsFetchingSql, new ParameterRowMapper());
            logger.debug("Retrieved parameters: {}", () -> parameterList);
            return parameterList;
        } catch (Exception e) {
            logger.error("No parameter found", e);
            throw new ResourceNotFound("No parameter found");
        }

    }

    public Parameter getParameterDetails(String key) {

        try {
            logger.info(sqlMarker, parameterDetailsFetchingSql);
            logger.info(sqlMarker, "Params {}", () -> key);
            Parameter parameter = (Parameter) jdbcTemplate.queryForObject(parameterDetailsFetchingSql, new Object[] { key }, new ParameterRowMapper());
            logger.debug("Retrieved parameter {}", () -> parameter);
            return parameter;
        } catch (Exception e) {
            logger.error("No parameter with key {} found", () -> key);
            logger.error(e);
            throw new ResourceNotFound("No parameter with key: " + key + " found");
        }

    }

    public void updateParameterValue(Parameter parameter, String key, String username) {
        try {
            logger.info(sqlMarker, parameterValueSettingSql);
            logger.info(sqlMarker, "Params {}, {}, {}", () -> parameter.getValue(), () -> parameter.getClobValue(), () -> key);
            int updated_rows = this.jdbcTemplate.update(parameterValueSettingSql, parameter.getValue(), parameter.getClobValue(), key);
            if (updated_rows == 0) {
                logger.error("No parameter found with key {}", () -> key);
                throw new ResourceNotFound("No parameter with key: " + key + " found");
            }
            commonRepository.saveActivityHistory("Value of parameter " + key + "changed to '" + parameter.getValue() + "' and '" + parameter.getClobValue(), username);
        } catch (Exception e) {
            logger.error("Parameter with key {} could not be updated", () -> key);
            logger.error(e);
            throw new ResourceNotFound("Parameter with key: " + key + " could not be updated");
        }
    }
}
