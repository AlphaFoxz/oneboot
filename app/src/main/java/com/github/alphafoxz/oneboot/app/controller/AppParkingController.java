package com.github.alphafoxz.oneboot.app.controller;

import com.github.alphafoxz.oneboot.app.gen.restl.apis.AppParkingCommandApi;
import com.github.alphafoxz.oneboot.app.gen.restl.apis.AppParkingQueryApi;
import com.github.alphafoxz.oneboot.app.gen.restl.dtos.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppParkingController implements AppParkingCommandApi, AppParkingQueryApi {
    @Override
    public ResponseEntity<Boolean> checkIn(String plate, String time) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> checkOut(String plate, String time) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> notifyPay(String plate, String time, Integer amount) {
        return null;
    }

    @Override
    public ResponseEntity<AppParkingHistoryVO> parkingHistory() {
        return null;
    }

    @Override
    public ResponseEntity<Integer> totalInPark() {
        return null;
    }

    @Override
    public ResponseEntity<AppParkingDailyRevenueVO> dailyRevenue() {
        return null;
    }
}
