package com.virtualpairprogrammers.roombooking.services;

import com.virtualpairprogrammers.roombooking.data.BookingRepository;
import com.virtualpairprogrammers.roombooking.data.RoomRepository;
import com.virtualpairprogrammers.roombooking.data.UserRepository;
import com.virtualpairprogrammers.roombooking.model.*;
import com.virtualpairprogrammers.roombooking.model.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DataInitialization {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookingRepository bookingRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        List<Room> rooms = roomRepository.findAll();
        if (rooms.size() == 0) {
            Room blueRoom = new Room("Salle 64","DMI 1st Floor",38,new State(true,""));
            blueRoom.setCapacity(new LayoutCapacity(Layout.BOARD,1,blueRoom));
            blueRoom.setCapacity(new LayoutCapacity(Layout.Projector,2,blueRoom));
            blueRoom.setCapacity(new LayoutCapacity(Layout.PCs,16,blueRoom));
            roomRepository.save(blueRoom);

            Room r2 = new Room("Salle 65","DMI 1st Floor",38,new State(true,""));
            r2.setCapacity(new LayoutCapacity(Layout.BOARD,1,r2));
            r2.setCapacity(new LayoutCapacity(Layout.Projector,2,r2));
            r2.setCapacity(new LayoutCapacity(Layout.PCs,19,r2));
            roomRepository.save(r2);

            Room r3 = new Room("Salle 74","DMI 2st Floor",38,new State(true,""));
            r3.setCapacity(new LayoutCapacity(Layout.BOARD,2,r3));
            r3.setCapacity(new LayoutCapacity(Layout.Projector,2,r3));
            r3.setCapacity(new LayoutCapacity(Layout.PCs,20,r3));
            roomRepository.save(r3);
            Room r4 = new Room("Salle 75","DMI 2st Floor",38,new State(true,""));
            r4.setCapacity(new LayoutCapacity(Layout.BOARD,1,r4));
            r4.setCapacity(new LayoutCapacity(Layout.Projector,1,r4));
            r4.setCapacity(new LayoutCapacity(Layout.PCs,12,r4));
            roomRepository.save(r4);
            Room r5 = new Room("Laboratoir","DMI 2st Floor",38,new State(true,""));
            r5.setCapacity(new LayoutCapacity(Layout.BOARD,2,r5));
            r5.setCapacity(new LayoutCapacity(Layout.Projector,2,r5));
            r5.setCapacity(new LayoutCapacity(Layout.PCs,2,r5));
            roomRepository.save(r5);


            User user = new User("BOURAS","Yassir","JK77777","400a/5f", "secret");
            userRepository.save(user);
            User user1 = new User("MESTARI","Med","JX11177","900a/5f", "secret");
            userRepository.save(user1);

            Booking booking1 = new Booking();
            booking1.setDate(new java.sql.Date(new java.util.Date().getTime()));
            booking1.setStartTime(java.sql.Time.valueOf("11:00:00"));
            booking1.setEndTime(java.sql.Time.valueOf("12:30:00"));
            booking1.setTitle("Exam IA");
            booking1.setRoom(blueRoom);
            booking1.setUser(user);
            bookingRepository.save(booking1);

            Booking booking2 = new Booking();
            booking2.setDate(new java.sql.Date(new java.util.Date().getTime()));
            booking2.setStartTime(java.sql.Time.valueOf("09:00:00"));
            booking2.setEndTime(java.sql.Time.valueOf("11:30:00"));
            booking2.setTitle("Exam JEE");
            booking2.setRoom(r2);
            booking2.setUser(user);
            bookingRepository.save(booking2);

            Booking booking3 = new Booking();
            booking3.setDate(new java.sql.Date(new java.util.Date().getTime()));
            booking3.setStartTime(java.sql.Time.valueOf("14:00:00"));
            booking3.setEndTime(java.sql.Time.valueOf("16:00:00"));
            booking3.setTitle("Exam IOT");
            booking3.setRoom(r4);
            booking3.setUser(user);
            bookingRepository.save(booking3);
        }
    }
}
