package com.revature.controller;

import com.revature.model.Request;
import com.revature.model.enums.RequestType;
import com.revature.service.RequestService;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/requests")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    @CrossOrigin// POST /requests
    public Request createRequest(@RequestBody Request request) {
        return requestService.createRequest(request);
    }

    @GetMapping
    @CrossOrigin// GET /requests
    public List<Request> getAllRequests() {
        return requestService.getAllRequests();
    }

    @GetMapping("/{id}") // GET /requests/{ID}
    public Request getRequestById(@PathVariable Integer id) {
        return requestService.getRequestById(id);
    }

    @PutMapping
    public Request updateRequest(@RequestBody Request request) {
        return requestService.updateRequest(request);
    }

    @DeleteMapping
    public void deleteRequest(@RequestBody Request request) {
        requestService.deleteRequest(request);
    }

    @GetMapping("/type/{requestType}")
    public List<Request> getAllByRequestType(@PathVariable("requestType") String requestType) {
        RequestType requestTypeId = RequestType.valueOf(requestType.toUpperCase(Locale.ROOT));
        return requestService.getAllByRequestType(requestTypeId);
    }

    @GetMapping("/user/{id}/type/{requestType}")
    public List<Request> getAllByUserAndType(@PathVariable("requestType") String requestType, @PathVariable("id") Integer id) {
        RequestType requestTypeId = RequestType.valueOf(requestType.toUpperCase(Locale.ROOT));
        return requestService.getAllRequestByUserAndType(requestTypeId, id);
    }

    @GetMapping("/user/{id}")
    public List<Request> getAllRequestByUser(@PathVariable("id") Integer id) {
        return requestService.getAllByRequestByUser(id);
    }

    @PostMapping("/approve/{id}")
    public Request approveRequest(@RequestBody Request request , @PathVariable("id") Integer id) {
        return requestService.approveRequest(request, id);
    }

    @PostMapping("/deny/{id}")
    public Request denyRequest(@RequestBody Request request , @PathVariable("id") Integer id) {
        return requestService.denyRequest(request, id);
    }

}
