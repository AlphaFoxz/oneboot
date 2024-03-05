package com.github.alphafoxz.oneboot.domain.app.aggregate;

import com.github.alphafoxz.oneboot.domain.app.command.CheckInCommand;
import com.github.alphafoxz.oneboot.domain.app.command.CheckOutCommand;
import com.github.alphafoxz.oneboot.domain.app.command.NotifyPayCommand;
import com.github.alphafoxz.oneboot.domain.app.event.CheckedInEvent;
import com.github.alphafoxz.oneboot.domain.app.event.CheckedOutEvent;
import com.github.alphafoxz.oneboot.domain.app.standard.DomainEventQueueExample;
import com.github.alphafoxz.oneboot.domain.app.standard.Plate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class ParkingAggImplTest {
    @Test
    public void test_case0() {
        try {
            new ParkingAggImpl(new Plate("p1"), null, null, 0).calculateFeeNow(LocalDateTime.now());
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals("车辆尚未入场", e.getMessage());
        }
    }

    @Test
    public void test_case1() {
        var result = new ParkingAggImpl(
                new Plate("p1"),
                LocalDateTime.of(2023, 11, 26, 10, 0, 0),
                null, 0
        ).calculateFeeNow(LocalDateTime.of(2023, 11, 26, 10, 59, 59));
        Assertions.assertEquals(1, result);
    }

    @Test
    public void test_case2() {
        var result = new ParkingAggImpl(
                new Plate("p1"),
                LocalDateTime.of(2023, 11, 26, 10, 0, 0),
                null, 0
        ).calculateFeeNow(LocalDateTime.of(2023, 11, 26, 11, 59, 59));
        Assertions.assertEquals(2, result);
    }

    @Test
    public void test_case3() {
        var result = new ParkingAggImpl(
                new Plate("p1"),
                LocalDateTime.of(2023, 11, 26, 10, 0, 0),
                LocalDateTime.of(2023, 11, 26, 10, 30, 0),
                1
        ).calculateFeeNow(LocalDateTime.of(2023, 11, 26, 10, 44, 59));
        Assertions.assertEquals(0, result);
    }

    @Test
    public void test_case4() {
        var result = new ParkingAggImpl(
                new Plate("p1"),
                LocalDateTime.of(2023, 11, 26, 10, 0, 0),
                LocalDateTime.of(2023, 11, 26, 10, 30, 0),
                1
        ).calculateFeeNow(LocalDateTime.of(2023, 11, 26, 10, 46, 59));
        Assertions.assertEquals(0, result);
    }

    @Test
    public void test_case5() {
        var result = new ParkingAggImpl(
                new Plate("p1"),
                LocalDateTime.of(2023, 11, 26, 10, 0, 0),
                LocalDateTime.of(2023, 11, 26, 10, 30, 0),
                1
        ).calculateFeeNow(LocalDateTime.of(2023, 11, 26, 11, 31, 59));
        Assertions.assertEquals(1, result);
    }

    @Test
    public void 复杂场景_付款超时后再付款() {
        var plate = new Plate("p1");
        //未入场
        var target = new ParkingAggImpl(
                plate,
                null,
                null,
                0
        );
        Assertions.assertFalse(target.inPark());

        // 入场
        var eventQueue = new DomainEventQueueExample();
        target.handle(eventQueue, new CheckInCommand(plate, LocalDateTime.of(2023, 11, 26, 10, 0, 0)));
        Assertions.assertTrue(target.inPark());
        Assertions.assertEquals(1, eventQueue.getList().size());
        Assertions.assertTrue(eventQueue.getList().get(0) instanceof CheckedInEvent);

        // 半小时后，查询费用 1 块
        Assertions.assertEquals(1, target.calculateFeeNow(LocalDateTime.of(2023, 11, 26, 10, 30, 0)));

        // 付钱 1块
        var eventQueue1 = new DomainEventQueueExample();
        target.handle(eventQueue1, new NotifyPayCommand(
                plate,
                1,
                LocalDateTime.of(2023, 11, 26, 10, 30, 0)
        ));

        // 超时15分钟，离场失败
        var eventQueue2 = new DomainEventQueueExample();
        Assertions.assertFalse(target.handle(eventQueue2, new CheckOutCommand(plate, LocalDateTime.of(2023, 11, 26, 11, 1, 0))));

        // 查询应付1块
        Assertions.assertEquals(1, target.calculateFeeNow(LocalDateTime.of(2023, 11, 26, 11, 1, 0)));

        // 付款1块
        var eventQueue3 = new DomainEventQueueExample();
        target.handle(eventQueue3, new NotifyPayCommand(
                plate,
                1,
                LocalDateTime.of(2023, 11, 26, 11, 2, 0)
        ));

        // 出场成功
        var eventQueue4 = new DomainEventQueueExample();
        Assertions.assertTrue(target.handle(eventQueue4, new CheckOutCommand(plate, LocalDateTime.of(2023, 11, 26, 11, 3, 0))));
    }

    @Test
    public void 车辆入场付款出场再进场() {

        var plate = new Plate("p1");
        //未入场
        var target = new ParkingAggImpl(
                plate,
                null,
                null,
                0
        );
        Assertions.assertFalse(target.inPark());

        // 入场
        var eventQueue = new DomainEventQueueExample();
        target.handle(eventQueue, new CheckInCommand(plate, LocalDateTime.of(2023, 11, 26, 10, 0, 0)));
        Assertions.assertTrue(target.inPark());
        Assertions.assertEquals(1, eventQueue.getList().size());
        Assertions.assertTrue(eventQueue.getList().get(0) instanceof CheckedInEvent);

        // 付钱 1块
        var eventQueue1 = new DomainEventQueueExample();
        target.handle(eventQueue1, new NotifyPayCommand(
                plate,
                1,
                LocalDateTime.of(2023, 11, 26, 10, 30, 0)
        ));

        // 出场成功
        var eventQueue2 = new DomainEventQueueExample();
        Assertions.assertTrue(target.handle(eventQueue2, new CheckOutCommand(plate, LocalDateTime.of(2023, 11, 26, 10, 31, 0))));
        Assertions.assertFalse(target.inPark());
        Assertions.assertEquals(1, eventQueue2.getList().size());
        Assertions.assertTrue(eventQueue2.getList().get(0) instanceof CheckedOutEvent);

        // 再进场
        var eventQueue3 = new DomainEventQueueExample();
        target.handle(eventQueue3, new CheckInCommand(plate, LocalDateTime.of(2023, 11, 26, 11, 0, 0)));
        Assertions.assertTrue(target.inPark());
        Assertions.assertEquals(1, eventQueue3.getList().size());
        Assertions.assertTrue(eventQueue3.getList().get(0) instanceof CheckedInEvent);
    }
}