package com.example.web_service.feature.admin.delivery.mapper;

import com.example.web_service.feature.admin.delivery.dto.req.*;
import com.example.web_service.feature.admin.delivery.dto.res.*;
import com.example.web_service.feature.admin.delivery.model.Delivery;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DeliveryMapper {

    private final jakarta.persistence.EntityManager entityManager;

    public Delivery fromRequest(DeliveryRequest req) {
        if (req == null) return null;
        Delivery entity = new Delivery();
        entity.setName(req.name());
        entity.setEmail(req.email());
        entity.setPhone(req.phone());
        entity.setAddress(req.address());
        entity.setStatus(req.status());
        entity.setImageUrl(req.imageUrl());
        entity.setDriverLicense(req.driverLicense());
        entity.setNationalIdUrl(req.nationalIdUrl());
        entity.setDriverLicenseUrl(req.driverLicenseUrl());
        entity.setRating(req.rating());
        entity.setCity(req.city());
        entity.setState(req.state());
        entity.setZip(req.zip());
        entity.setCountry(req.country());
        if (req.usersId() != null) {
            entity.setUsers(entityManager.find(com.example.web_service.feature.admin.user.model.User.class, req.usersId()));
        }
        return entity;
    }

    public DeliveryResponse toResponse(Delivery entity) {
        if (entity == null) return null;
        DeliveryResponse res = new DeliveryResponse();
        res.setId(entity.getId());
        res.setName(entity.getName());
        res.setEmail(entity.getEmail());
        res.setPhone(entity.getPhone());
        res.setAddress(entity.getAddress());
        res.setStatus(entity.getStatus());
        res.setImageUrl(entity.getImageUrl());
        res.setNationalId(entity.getNationalId());
        res.setDriverLicense(entity.getDriverLicense());
        res.setNationalIdUrl(entity.getNationalIdUrl());
        res.setDriverLicenseUrl(entity.getDriverLicenseUrl());
        res.setRating(entity.getRating());
        res.setCity(entity.getCity());
        res.setState(entity.getState());
        res.setZip(entity.getZip());
        res.setCountry(entity.getCountry());
        res.setUsers(entity.getUsers());
        return res;
    }

    public List<DeliveryResponse> toResponseList(List<Delivery> entities) {
        if (entities == null) return null;
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public void updateFromRequest(Delivery entity, DeliveryRequestUpdate req) {
        if (req == null) return;
        if (req.name() != null) {
            entity.setName(req.name());
        }
        if (req.email() != null) {
            entity.setEmail(req.email());
        }
        if (req.phone() != null) {
            entity.setPhone(req.phone());
        }
        if (req.address() != null) {
            entity.setAddress(req.address());
        }
        if (req.status() != null) {
            entity.setStatus(req.status());
        }
        if (req.imageUrl() != null) {
            entity.setImageUrl(req.imageUrl());
        }
        if (req.driverLicense() != null) {
            entity.setDriverLicense(req.driverLicense());
        }
        if (req.nationalIdUrl() != null) {
            entity.setNationalIdUrl(req.nationalIdUrl());
        }
        if (req.driverLicenseUrl() != null) {
            entity.setDriverLicenseUrl(req.driverLicenseUrl());
        }
        if (req.rating() != null) {
            entity.setRating(req.rating());
        }
        if (req.city() != null) {
            entity.setCity(req.city());
        }
        if (req.state() != null) {
            entity.setState(req.state());
        }
        if (req.zip() != null) {
            entity.setZip(req.zip());
        }
        if (req.country() != null) {
            entity.setCountry(req.country());
        }
        if (req.usersId() != null) {
            entity.setUsers(entityManager.find(com.example.web_service.feature.admin.user.model.User.class, req.usersId()));
        }
    }
}
