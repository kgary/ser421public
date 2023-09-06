/**
 * 
 */
package edu.asu.ser421.grocerydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.asu.ser421.grocerydemo.dto.PayInfo;


public interface PaymentInfoRepository extends JpaRepository<PayInfo, Long> {

}
