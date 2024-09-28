package com.Assignment.demoAssignment.Controller;

import com.Assignment.demoAssignment.Service.MeetingService;
import com.Assignment.demoAssignment.dto.ScheduleMeetingRequest;
import com.Assignment.demoAssignment.model.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class MeetingController {


    @Autowired
    private MeetingService meetingService;
    @GetMapping("/meetings")
    public ResponseEntity<List<Meeting>> getAllMeetings() {
        List<Meeting> meetings = meetingService.getAllMeetings();
        return ResponseEntity.ok(meetings);
    }


    @PostMapping("/schedule")
    public ResponseEntity<Meeting> scheduleMeeting(@RequestBody ScheduleMeetingRequest request) {
        Meeting meeting = meetingService.scheduleMeeting(
                request.getStartTime(),
                request.getEndTime(),
                request.getRoomId(),
                request.getParticipantIds()
        );
        return ResponseEntity.ok(meeting);
    }
}
