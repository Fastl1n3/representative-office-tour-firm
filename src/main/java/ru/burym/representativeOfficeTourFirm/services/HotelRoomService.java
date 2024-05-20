package ru.burym.representativeOfficeTourFirm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.burym.representativeOfficeTourFirm.models.entities.HotelRoom;
import ru.burym.representativeOfficeTourFirm.models.queries.HotelCountInfo;
import ru.burym.representativeOfficeTourFirm.repositories.HotelRoomRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HotelRoomService {
    private final HotelRoomRepository hotelRoomRepository;

    @Autowired
    public HotelRoomService(HotelRoomRepository hotelRoomRepository) {
        this.hotelRoomRepository = hotelRoomRepository;
    }

    @Transactional(readOnly = true)
    public List<HotelRoom> findAll() {
        return (List<HotelRoom>) hotelRoomRepository.findAll();
    }

    @Transactional
    public void save(HotelRoom hotelRoom) {
        hotelRoomRepository.save(hotelRoom);
    }

    @Transactional(readOnly = true)
    public List<HotelCountInfo> getCountHotelsByDate(LocalDateTime start_date, LocalDateTime end_date) {
        return hotelRoomRepository.getCountHotelsByDate(start_date, end_date);
    }
}
