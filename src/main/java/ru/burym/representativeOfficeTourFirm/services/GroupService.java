package ru.burym.representativeOfficeTourFirm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.burym.representativeOfficeTourFirm.models.entities.*;
import ru.burym.representativeOfficeTourFirm.models.outputs.TouristAccomOutput;
import ru.burym.representativeOfficeTourFirm.models.outputs.TouristWithTypeOutput;
import ru.burym.representativeOfficeTourFirm.repositories.GroupRepository;
import ru.burym.representativeOfficeTourFirm.repositories.SpendingRepository;
import ru.burym.representativeOfficeTourFirm.repositories.TouristGroupRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    private final TouristGroupRepository touristGroupRepository;

    private final SpendingRepository spendingRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository, TouristGroupRepository touristGroupRepository, SpendingRepository spendingRepository) {
        this.groupRepository = groupRepository;
        this.touristGroupRepository = touristGroupRepository;
        this.spendingRepository = spendingRepository;
    }

    @Transactional(readOnly = true)
    public List<Group> findAll() {
        return (List<Group>) groupRepository.findAll();
    }

    @Transactional
    public void save(Group group) {
        groupRepository.save(group);
    }

    @Transactional(readOnly = true)
    public Group findById(int id) {
        return groupRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Group not found!"));
    }

    @Transactional(readOnly = true)
    public List<Flight> findFlightsByGroupId(int id) {
        return groupRepository.findFlightsByGroupId(id);
    }

    @Transactional(readOnly = true)
    public List<TouristWithTypeOutput> findTouristsByGroupId(int id) {
        return touristGroupRepository.findTouristsByGroupId(id);
    }

    @Transactional(readOnly = true)
    public List<Tourist> findTouristsNotInGroup(int id) {
        return touristGroupRepository.findTouristsNotInGroup(id);
    }

    @Transactional
    public void saveTouristToGroup(int groupId, TouristGroup touristGroup) {
        touristGroup.setGroupId(groupId);
        touristGroupRepository.save(touristGroup);
        spendingRepository.save(new Spending(touristGroup.getTouristId(), "flight", touristGroup.getCost(), LocalDateTime.now()));
    }

    @Transactional(readOnly = true)
    public List<TouristAccomOutput> findTouristsForAccommodationByGroupId(int groupId) {
        return touristGroupRepository.findTouristsForAccommodationByGroupId(groupId);
    }

    @Transactional(readOnly = true)
    public double getRatioCampersToForCargo() {
        return touristGroupRepository.getRatioCampersToForCargo().orElseThrow(() -> new IllegalArgumentException("Tourists is not found!"));
    }

    @Transactional(readOnly = true)
    public double getRatioCampersToForCargoByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return touristGroupRepository.getRatioCampersToForCargoByDate(startDate, endDate).orElseThrow(() -> new IllegalArgumentException("Tourists is not found on these dates!"));
    }

    @Transactional(readOnly = true)
    public List<TouristWithTypeOutput> getTouristInfoByGroupId(int groupId) {
        return groupRepository.getTouristInfoByGroupId(groupId);
    }

    @Transactional(readOnly = true)
    public List<TouristWithTypeOutput> getTouristInfoByGroupIdAndType(int groupId, String touristType) {
        return groupRepository.getTouristInfoByGroupIdAndType(groupId, touristType);
    }
}

