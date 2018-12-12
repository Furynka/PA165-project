package com.blackteam.pipboy.rest.controllers;

import com.blackteam.pipboy.rest.mixin.ApiUris;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, String> getResources() {
        Map<String,String> resourcesMap = new HashMap<>();
        
        resourcesMap.put("areas_uri", ApiUris.ROOT_URI_AREAS);
        resourcesMap.put("monsters_uri", ApiUris.ROOT_URI_MONSTERS);
        resourcesMap.put("persons_uri", ApiUris.ROOT_URI_PERSONS);
        resourcesMap.put("weapons_uri", ApiUris.ROOT_URI_WEAPONS);
        
        return Collections.unmodifiableMap(resourcesMap);
        
    }
}
