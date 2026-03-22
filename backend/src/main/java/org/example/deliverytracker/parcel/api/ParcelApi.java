package org.example.deliverytracker.parcel.api;

import jakarta.validation.Valid;
import org.example.deliverytracker.parcel.dto.CreateParcelDto;
import org.example.deliverytracker.parcel.ParcelService;
import org.example.deliverytracker.parcel.dto.ParcelDto;
import org.example.deliverytracker.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/parcels")
public class ParcelApi {
    private final ParcelService parcelService;

    public ParcelApi(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody@Valid CreateParcelDto request) {
        ParcelDto dto = parcelService.createParcel(request);
        return ResponseEntity.ok(new ApiResponse(dto));
    }
}
