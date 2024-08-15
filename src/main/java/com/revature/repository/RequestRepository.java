package com.revature.repository;

import com.revature.model.Request;
import com.revature.model.enums.RequestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    List<Request> getAllByRequestType(RequestType requestType);
    List<Request> getAllByCreator_UserId(Integer id);
    List<Request> getAllByRequestTypeAndCreator_UserId(RequestType requestType, Integer id);
    Optional<Request> findByCreator_UserIdAndMed_MedIdAndDosageCountAndDosageFreqAndRequestType(Integer creatorId, Integer medId, Integer dosageCount, Integer dosageFreq, RequestType requestType);
    List<Request> findByCreator_UserIdAndRequestTypeAndMed_NameStartingWith(Integer creatorId, RequestType requestType, String letter);
    List<Request> findByCreator_UserIdAndRequestTypeAndMed_NameContainingIgnoreCase(Integer creatorId, RequestType requestType, String query);
    List<Request> findByMed_NameStartingWith(String letter);
    List<Request> findByMed_NameContainingIgnoreCase(String query);
}
