package com.hamdard.hua.reqmap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;

@Controller
public class Routing {

    @RequestMapping({"/resources/**"}) 
    public String api(final HttpServletRequest request){
        String path = (String) request.getAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        return path;
    }
    
    @RequestMapping({ "", "/home", "/employeeHierarchySearch", "/employeeHierarchySearchResult", "/employeeDetails/**", "/employeeCreation", "/forbidden", "/404" })
    public String gui() {
        return "forward:/index.html";
    }
}