package com.example.PaymentService.PaymentGatewayStrategy;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements IpaymentGateway{

    @Value("${stripeApiKey}")
    private String stripeApikey;


    @Override
    public String generatePaymentLink(Long amount,String orderId,String phoneNumber,String name,String email) {

        try {
            Stripe.apiKey = stripeApikey;

            Price price=getPrice(amount);
            PaymentLinkCreateParams params =
                    PaymentLinkCreateParams.builder()
                            .addLineItem(
                                    PaymentLinkCreateParams.LineItem.builder()
                                            //we nned to set the price...for that we have method in stripe doc
                                            //.setPrice("price_1MoC3TLkdIwHu7ixcIbKelAC") //this we need to change..
                                            .setPrice(price.getId())
                                            .setQuantity(1L)
                                            .build()
                            ).setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder() //after payment compltoin
                                    .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT) //redirect url need to provide
                                    .setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                    .setUrl("https://scaler.com").build()).build())
                            .build();

            PaymentLink paymentLink = PaymentLink.create(params); //we will get the payment object
            return paymentLink.getUrl(); //returning payment url

        }catch (StripeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Price getPrice(Long amount){
        try {
            PriceCreateParams params =
                    PriceCreateParams.builder()
                            .setCurrency("usd")
                            .setUnitAmount(amount)
                            .setRecurring(
                                    PriceCreateParams.Recurring.builder()
                                            .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                            .build()
                            )
                            .setProductData(
                                    PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                            )
                            .build();

            Price price = Price.create(params);
            return  price;

        }catch (StripeException ex){
            throw  new RuntimeException(ex.getMessage());
        }

    }
}
