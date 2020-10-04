package com.cars24.auction.repository;

import com.cars24.auction.dto.RunningAuction;
import com.cars24.auction.entity.Auction;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends JpaRepository<Auction,Long> {
    @Query(value = "select max(u.bid_amount) from auction a,user u where a.id=u.auction_id group by a.item_code" , nativeQuery = true)
    List<Integer> findHighestBidOfEachItem();

    @Query(value = "select max(u.bid_amount) from auction a,user u where a.id=u.auction_id and a.item_code=:itemCode" , nativeQuery = true)
    int findHighestBidOfItem(@Param("itemCode" ) String itemCode);

    @Query(value = "select a.item_code itemCode,max(u.bid_amount) bidAmount,a.step_rate stepRate from auction a,user u where  a.id=u.auction_id  and a.status=:status group by a.item_code,a.step_rate" , nativeQuery = true)
    List<RunningAuction> fetchRunningAuctions(@Param("status" ) String status, Pageable pageable );
    @Query(value="select * from auction a where a.item_code=:code and a.status='RUNNING'",nativeQuery = true)
    Auction fetchAuction(@Param("code")  String itemCode);


}
