/**
 * 
 */
package com.hamdard.hua.rest;

import java.util.List;

import javax.validation.constraints.Min;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hamdard.hua.model.Department;
import com.hamdard.hua.model.Message;
import com.hamdard.hua.privileges.Privilege;
import com.hamdard.hua.repository.DepartmentRepository;
import com.hamdard.hua.security.Secured;

/**
 * @author Somdeb
 *
 */
@Path("v1/department")
public class DepartmentResource {

    private static final Logger logger = LogManager.getLogger(DepartmentResource.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Secured(Privilege.READ_DEPTS_OF_A_UNIT)
    public Response getDepartments(@QueryParam("unitId") @Min(1) int unitId) {
        List<Department> departments;
        try {
            departments = departmentRepository.getDepartmentsByUnitId(unitId);
            /* If passed unitId doesn't return any values */
            if (departments.isEmpty()) {
                logger.error("No departments are found for unitId: {}", () -> unitId);
                return Response.status(Response.Status.NOT_FOUND).entity(new Message("No departments found for the given unit")).build();
            }
            /* If orgId return associated values */
            else
                return Response.status(Response.Status.OK).entity(departments).build();
        }
        /* Any other kind of exception */
        catch (Exception ex) {
            logger.error("The departments dould not be retrieved", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(ex.getMessage())).build();
        }
    }

}