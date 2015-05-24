package com.mycompany.sample.payment.service.gateway;

import com.braintreegateway.*;
import com.mycompany.sample.vendor.braintree.service.payment.BraintreePaymentGatewayType;
import com.mycompany.sample.vendor.nullPaymentGateway.service.payment.NullPaymentGatewayConstants;
import org.apache.commons.lang.ArrayUtils;
import org.broadleafcommerce.common.money.Money;
import org.broadleafcommerce.common.payment.PaymentTransactionType;
import org.broadleafcommerce.common.payment.PaymentType;
import org.broadleafcommerce.common.payment.dto.PaymentResponseDTO;
import org.broadleafcommerce.common.payment.service.PaymentGatewayWebResponsePrintService;
import org.broadleafcommerce.common.payment.service.PaymentGatewayWebResponseService;
import org.broadleafcommerce.common.vendor.service.exception.PaymentException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by phuonghqh on 5/20/15.
 */
@Service("blBraintreePaymentGatewayWebResponseService")
public class BraintreePaymentGatewayWebResponseServiceImpl  implements PaymentGatewayWebResponseService {

  @Resource(name = "blPaymentGatewayWebResponsePrintService")
  protected PaymentGatewayWebResponsePrintService webResponsePrintService;

  @Resource(name = "blBraintreePaymentGatewayConfiguration")
  protected BraintreePaymentGatewayConfigurationImpl configuration;

  @Override
  public PaymentResponseDTO translateWebResponse(HttpServletRequest request) throws PaymentException {
    PaymentResponseDTO responseDTO = new PaymentResponseDTO(PaymentType.CREDIT_CARD,
      BraintreePaymentGatewayType.BRAINTREE_GATEWAY)
      .rawResponse(webResponsePrintService.printRequest(request));

    Map<String,String[]> paramMap = request.getParameterMap();

    Money amount = Money.ZERO;
    if (paramMap.containsKey(NullPaymentGatewayConstants.TRANSACTION_AMT)) {
      String amt = paramMap.get(NullPaymentGatewayConstants.TRANSACTION_AMT)[0];
      amount = new Money(amt);
    }

    boolean approved = false;
    if (paramMap.containsKey(NullPaymentGatewayConstants.RESULT_SUCCESS)) {
      String[] msg = paramMap.get(NullPaymentGatewayConstants.RESULT_SUCCESS);
      if (ArrayUtils.contains(msg, "true")) {
        approved = true;
      }
    }

    PaymentTransactionType type = PaymentTransactionType.AUTHORIZE_AND_CAPTURE;
    if (!configuration.isPerformAuthorizeAndCapture()) {
      type = PaymentTransactionType.AUTHORIZE;
    }

    responseDTO.successful(approved)
      .amount(amount)
      .paymentTransactionType(type)
      .orderId(paramMap.get(NullPaymentGatewayConstants.ORDER_ID)[0])
      .responseMap(NullPaymentGatewayConstants.GATEWAY_TRANSACTION_ID,
        paramMap.get(NullPaymentGatewayConstants.GATEWAY_TRANSACTION_ID)[0])
      .responseMap(NullPaymentGatewayConstants.RESULT_MESSAGE,
        paramMap.get(NullPaymentGatewayConstants.RESULT_MESSAGE)[0])
      .billTo()
      .addressFirstName(paramMap.get(NullPaymentGatewayConstants.BILLING_FIRST_NAME)[0])
      .addressLastName(paramMap.get(NullPaymentGatewayConstants.BILLING_LAST_NAME)[0])
      .addressLine1(paramMap.get(NullPaymentGatewayConstants.BILLING_ADDRESS_LINE1)[0])
      .addressLine2(paramMap.get(NullPaymentGatewayConstants.BILLING_ADDRESS_LINE2)[0])
      .addressCityLocality(paramMap.get(NullPaymentGatewayConstants.BILLING_CITY)[0])
      .addressStateRegion(paramMap.get(NullPaymentGatewayConstants.BILLING_STATE)[0])
      .addressPostalCode(paramMap.get(NullPaymentGatewayConstants.BILLING_ZIP)[0])
      .addressCountryCode(paramMap.get(NullPaymentGatewayConstants.BILLING_COUNTRY)[0])
      .done()
      .creditCard()
      .creditCardHolderName(paramMap.get(NullPaymentGatewayConstants.CREDIT_CARD_NAME)[0])
      .creditCardLastFour(paramMap.get(NullPaymentGatewayConstants.CREDIT_CARD_LAST_FOUR)[0])
      .creditCardType(paramMap.get(NullPaymentGatewayConstants.CREDIT_CARD_TYPE)[0])
      .creditCardExpDate(paramMap.get(NullPaymentGatewayConstants.CREDIT_CARD_EXP_DATE)[0])
      .done();

    return responseDTO;

  }
}
