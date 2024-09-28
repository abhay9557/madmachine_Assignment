package com.Assignment.demoAssignment.repository;

import com.Assignment.demoAssignment.model.Meeting;
import com.Assignment.demoAssignment.model.Participant;
import com.Assignment.demoAssignment.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findByRoomAndStartTimeBetween(Room room, LocalDateTime startTime, LocalDateTime endTime);
    List<Meeting> findByParticipantsInAndStartTimeBetween(List<Participant> participants, LocalDateTime startTime, LocalDateTime endTime);
}
