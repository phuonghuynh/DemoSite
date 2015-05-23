package com.mycompany.sample.payment.service.gateway;

import com.mycompany.sample.vendor.braintree.service.payment.BraintreePaymentGatewayType;
import com.mycompany.sample.vendor.nullPaymentGateway.service.payment.NullPaymentGatewayConstants;
import org.broadleafcommerce.common.payment.PaymentType;
import org.broadleafcommerce.common.payment.dto.PaymentRequestDTO;
import org.broadleafcommerce.common.payment.dto.PaymentResponseDTO;
import org.broadleafcommerce.common.payment.service.PaymentGatewayHostedService;
import org.broadleafcommerce.common.vendor.service.exception.PaymentException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by phuonghqh on 5/20/15.
 */
@Service("blBraintreePaymentGatewayHostedService")
public class BraintreePaymentGatewayHostedServiceImpl implements PaymentGatewayHostedService {

  @Resource(name = "blBraintreePaymentGatewayHostedConfiguration")
  protected BraintreePaymentGatewayHostedConfigurationImpl configuration;

  @Override
  public PaymentResponseDTO requestHostedEndpoint(PaymentRequestDTO requestDTO) throws PaymentException {
    PaymentResponseDTO responseDTO = new PaymentResponseDTO(PaymentType.THIRD_PARTY_ACCOUNT,
      BraintreePaymentGatewayType.BRAINTREE_HOSTED_GATEWAY)
      .completeCheckoutOnCallback(requestDTO.isCompleteCheckoutOnCallback())
      .responseMap(NullPaymentGatewayConstants.ORDER_ID, requestDTO.getOrderId())
      .responseMap(NullPaymentGatewayConstants.TRANSACTION_AMT, requestDTO.getTransactionTotal())
      .responseMap(NullPaymentGatewayConstants.HOSTED_REDIRECT_URL,
        configuration.getHostedRedirectUrl());
    return responseDTO;
  }
}
