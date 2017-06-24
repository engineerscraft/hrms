package com.hamdard.hua.repository;

import java.util.HashMap;
import java.util.Map;

import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

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

    //    @Autowired
    //    private AuthenticationRepository            authenticationRepository;


    public Map<String,String> changePassword(String userName, PasswordChangeDetails newPassDetails) {
        Map<String, String> retMap                = new HashMap<>();
        try{
            if(newPassDetails.getConfirmedPassword().indexOf(userName) >0 )
                throw new Exception("Password should not contain the username within it");
            DirContext dirContext                   = null;
            try{
                 dirContext                         = contextSource.getContext(
                        "cn=" + userName + ",ou=users," + baseDn, newPassDetails.getCurrentPassword());
            }catch(Exception ex){
                retMap.put("STATUS","ERROR");
                retMap.put("MESSAGE","Provided password does not match with present one");
                return retMap;
            }
            ModificationItem[] modifiables      = new ModificationItem[1];
            Attribute  password                 = new BasicAttribute(
                    "userpassword"     , newPassDetails.getNewPassword());
            modifiables[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, password);

            dirContext.modifyAttributes("cn=" + userName + ",ou=users", modifiables);
            retMap.put("STATUS","SUCCESS");
            retMap.put("MESSAGE","Password modified successfully");
        }catch(Exception ex){
            logger.error(ex.getMessage());
            retMap.put("STATUS","ERROR");
            retMap.put("MESSAGE",ex.getMessage());
        }
        System.out.println("MAP :" + retMap);
        return retMap;
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


