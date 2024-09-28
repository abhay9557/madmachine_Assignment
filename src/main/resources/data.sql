-- Inserting Rooms
INSERT INTO Room (name, capacity) VALUES ('Conference Room 101', 10);
INSERT INTO Room (name, capacity) VALUES ('Board Room 202', 20);
INSERT INTO Room (name, capacity) VALUES ('Training Room 303', 15);

-- Inserting Participants
INSERT INTO Participant (name, email) VALUES ('John Doe', 'johndoe@example.com');
INSERT INTO Participant (name, email) VALUES ('Jane Smith', 'janesmith@example.com');
INSERT INTO Participant (name, email) VALUES ('Michael Brown', 'michaelbrown@example.com');
INSERT INTO Participant (name, email) VALUES ('Emily Clark', 'emilyclark@example.com');

-- Inserting Meetings
INSERT INTO Meeting (start_time, end_time, room_id) VALUES
('2024-09-28T09:00:00', '2024-09-28T10:00:00', (SELECT id FROM Room WHERE name = 'Conference Room 101')),
('2024-09-28T11:00:00', '2024-09-28T12:00:00', (SELECT id FROM Room WHERE name = 'Board Room 202')),
('2024-09-29T14:00:00', '2024-09-29T15:00:00', (SELECT id FROM Room WHERE name = 'Training Room 303'));

-- Inserting Meeting Participants (linking Participants to Meetings)
INSERT INTO Meeting_Participants (meeting_id, participants_id) VALUES
((SELECT id FROM Meeting WHERE start_time = '2024-09-28T09:00:00'), (SELECT id FROM Participant WHERE name = 'John Doe')),
((SELECT id FROM Meeting WHERE start_time = '2024-09-28T09:00:00'), (SELECT id FROM Participant WHERE name = 'Jane Smith')),
((SELECT id FROM Meeting WHERE start_time = '2024-09-28T11:00:00'), (SELECT id FROM Participant WHERE name = 'Jane Smith')),
((SELECT id FROM Meeting WHERE start_time = '2024-09-28T11:00:00'), (SELECT id FROM Participant WHERE name = 'Michael Brown')),
((SELECT id FROM Meeting WHERE start_time = '2024-09-29T14:00:00'), (SELECT id FROM Participant WHERE name = 'John Doe')),
((SELECT id FROM Meeting WHERE start_time = '2024-09-29T14:00:00'), (SELECT id FROM Participant WHERE name = 'Emily Clark'));
