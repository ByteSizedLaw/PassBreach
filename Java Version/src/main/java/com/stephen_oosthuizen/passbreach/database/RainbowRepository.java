/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stephen_oosthuizen.passbreach.database;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Stephen Oosthuizen
 */
@Repository
public interface RainbowRepository extends JpaRepository<RainbowEntity, Long>{
    public RainbowEntity findByHash(String hash);
    @Query("SELECT r FROM RainbowEntity r WHERE r.length = (SELECT MAX(r2.length) FROM RainbowEntity r2)")
    List<RainbowEntity> findAllWithMaxLength();
}