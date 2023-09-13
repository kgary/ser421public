package edu.asu.ser421.grocerydemo.services;

import org.springframework.stereotype.Service;

import edu.asu.ser421.grocerydemo.dto.PayInfo;
import edu.asu.ser421.grocerydemo.repository.PaymentInfoRepository;

@Service
public class PayInfoServices {
	
	private final PaymentInfoRepository payInfoRepository;
	public PayInfoServices(PaymentInfoRepository payInfoRepository) {
		this.payInfoRepository = payInfoRepository;
	}
	
	public void savePaymentInfo(PayInfo payInfo) {
		payInfoRepository.save(new PayInfo(payInfo.getId(),
					payInfo.getName(),
					payInfo.getCreditCardNum(),
					payInfo.getCvv()));
	}
}
