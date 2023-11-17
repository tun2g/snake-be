package com.example.tram.modules.user;


import com.example.tram.config.redis.IRedisCacheService;
import com.example.tram.config.response.Response;
import com.example.tram.modules.user.interface_user.IAdminController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AdminControllerImpl implements IAdminController {

    @Autowired
    private IRedisCacheService redisCacheService;
    public ResponseEntity<?> testAuth(){
        redisCacheService.set("test","admin",1000*60*5);
        return Response.SuccessResponse(null,"admin");
    }
}
