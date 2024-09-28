package com.Assignment.demoAssignment.Service;

import com.Assignment.demoAssignment.model.Meeting;
import com.Assignment.demoAssignment.model.Participant;
import com.Assignment.demoAssignment.model.Room;
import com.Assignment.demoAssignment.repository.MeetingRepository;
import com.Assignment.demoAssignment.repository.ParticipantRepository;
import com.Assignment.demoAssignment.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }


    public boolean checkRoomAvailability(Room room, LocalDateTime startTime, LocalDateTime endTime) {
        List<Meeting> meetings = meetingRepository.findByRoomAndStartTimeBetween(room, startTime, endTime);
        return meetings.isEmpty();
    }

    public boolean checkParticipantAvailability(List<Participant> participants, LocalDateTime startTime, LocalDateTime endTime) {
        List<Meeting> meetings = meetingRepository.findByParticipantsInAndStartTimeBetween(participants, startTime, endTime);
        return meetings.isEmpty();
    }

    public Meeting scheduleMeeting(LocalDateTime startTime, LocalDateTime endTime, Long roomId, List<Long> participantIds) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
        List<Participant> participants = participantRepository.findAllById(participantIds);

        if (!checkRoomAvailability(room, startTime, endTime)) {
            throw new RuntimeException("Room not available");
        }

        if (!checkParticipantAvailability(participants, startTime, endTime)) {
            throw new RuntimeException("One or more participants are not available");
        }

        Meeting meeting = new Meeting();
        meeting.setStartTime(startTime);
        meeting.setEndTime(endTime);
        meeting.setRoom(room);
        meeting.setParticipants(participants);

        return meetingRepository.save(meeting);
    }

}
