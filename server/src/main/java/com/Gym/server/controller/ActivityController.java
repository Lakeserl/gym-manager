package com.Gym.server.controller;

import com.Gym.server.dto.ActivityDTO;
import com.Gym.server.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping("/activity")
    public ResponseEntity<?> postActivity(@RequestBody ActivityDTO activityDTO) {
        ActivityDTO createActivity = activityService.postActivity(activityDTO);

        if(createActivity != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createActivity);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Activity");
        }
    }

    @GetMapping("/activities")
    public ResponseEntity<?> getActivities() {
        try{
            return ResponseEntity.ok(activityService.getActivities());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
