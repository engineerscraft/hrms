package com.hamdard.hua.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hamdard.hua.model.Account;
import com.hamdard.hua.privileges.Privilege;
import com.hamdard.hua.repository.AccountRepository;
import com.hamdard.hua.security.Secured;

@Component
@Path("/account")
public class AccountResource {

    @Autowired
    private AccountRepository accountRepository;

    @Context
    SecurityContext securityContext;

    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Secured(Privilege.ACCOUNT_ALL_READ_CMD)
    public List<Account> getAllAccounts() {
        return accountRepository.getAllAccounts();
    }

    @PUT
    @Path("/{accountCode}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Secured(Privilege.ACCOUNT_UPDATE_CMD)
    @Transactional
    public Response updateAccount(Account account, @PathParam("accountCode") String accountCode) {
        accountRepository.updateAccountDetails(account, accountCode, securityContext.getUserPrincipal().getName());
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @DELETE
    @Path("/{accountCode}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Secured(Privilege.ACCOUNT_DELETE_CMD)
    @Transactional
    public Response deleteAccount(@PathParam("accountCode") String accountCode) {
        accountRepository.deleteAccount(accountCode, securityContext.getUserPrincipal().getName());
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @POST
    @Path("/")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Secured(Privilege.ACCOUNT_CREATE_CMD)
    @Transactional
    public Response createAccount(Account account) {
        accountRepository.createAccount(account, securityContext.getUserPrincipal().getName());
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }
}
