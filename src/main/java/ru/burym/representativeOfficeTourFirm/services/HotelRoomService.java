package ru.burym.representativeOfficeTourFirm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.burym.representativeOfficeTourFirm.models.entities.HotelRoom;
import ru.burym.representativeOfficeTourFirm.repositories.HotelRoomRepository;

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
}
