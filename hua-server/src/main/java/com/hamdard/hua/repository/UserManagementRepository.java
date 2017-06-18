package com.hamdard.hua.repository;

import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.stereotype.Component;

import com.hamdard.hua.model.PasswordChangeDetails;
import com.hamdard.hua.model.User;

/**
 * @author Jyotirmoy Banerjee
 * 
 */

@Component
public class UserManagementRepository {

    private static final Logger logger          = LogManager.getLogger(AuthenticationRepository.class);

    @Autowired
    private LdapContextSource                   contextSource;
    
    @Value("${ldap.base}")
    private String                              baseDn;


    public void changePassword(String userName, PasswordChangeDetails newPassDetails) throws Exception {
        try{
            DirContext dirContext               = contextSource.getContext(
                    "cn=" + userName + ",ou=users," + "XX", 
                    new String(newPassDetails.getCurrentPassword()));
            if(dirContext == null)
                throw new Exception("Invalid password provided!");
            Attribute  password                = new BasicAttribute("userPassword"     , newPassDetails.getNewPassword());
        }catch(Exception ex){
            
        }
    }

    public void createUser(User newUser) throws Exception {
        try {
            //add individual attributes to the employee
            Attribute  sn                      = new BasicAttribute("sn"               , newUser.getFullName());
            Attribute  password                = new BasicAttribute("userPassword"     , newUser.getInitialPassword());
            Attribute  mobile                  = new BasicAttribute("mobile"           , newUser.getMobile());
            Attribute  description             = new BasicAttribute("description"      , newUser.getDescription());
            Attribute  employeeNumber          = new BasicAttribute("employeeNumber"   , newUser.getEmployeeNumber());
            Attribute  homePostalAddress       = new BasicAttribute("homePostalAddress", newUser.getEmployeeNumber());
            //add the required classes
            Attribute oc                       = new BasicAttribute("objectClass");  
            oc.add("top");  
            oc.add("person");  
            oc.add("organizationalPerson");  
            oc.add("inetOrgPerson");

            BasicAttributes personEntry        = new BasicAttributes();    
            personEntry.put(sn);  
            personEntry.put(password);
            personEntry.put(mobile);
            personEntry.put(description);
            personEntry.put(employeeNumber);
            personEntry.put(homePostalAddress);

            personEntry.put(oc);      
            String relativeDn                  = String.format("cn=%s,ou=users",    newUser.getUserName());
            contextSource.getReadWriteContext().createSubcontext(relativeDn ,       personEntry);

        } catch (Exception e) {
            logger.error("User creation failed", e);
            throw e;
        }
    }
}


