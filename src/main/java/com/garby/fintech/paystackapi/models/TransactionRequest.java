package com.garby.fintech.paystackapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.garby.fintech.paystackapi.models.MetaData;
import jakarta.xml.bind.ValidationException;
import com.garby.fintech.paystackapi.models.PaystackBearer;

//import javax.validation.ValidationException;
//import javax.validation.constraints.Digits;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class TransactionRequest {

    // Amount in kobo
    // @Digits(integer = 9, fraction = 0)
    private Integer amount;

    // User email
    private String email;

    // Recurring fees
    private String plan;

    // Transaction idenfication. Must be unique & alphanumeric (no spaces/newlines).
    private String reference;

    // generated subaccount code
    private String subaccount;

    // user bearing Paystack charges
    private PaystackBearer bearer = PaystackBearer.ACCOUNT;


    // Fully qualified url/used to override the callback url provided for this transaction
    private String callback_url;

    // Applies a multiple to the amount returned by the plan code
    private Float quantity;

    // Number of invoices to raise / Overrides invoice_limit set on plan.
    private Integer invoice_limit;

    // Extra info
    private MetaData metadata;

    // flat fee charged per transaction
    private Integer transaction_charge;

    private List<String> channel;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getSubaccount() {
        return subaccount;
    }

    public void setSubaccount(String subaccount) {
        this.subaccount = subaccount;
    }

    public PaystackBearer getBearer() {
        return bearer;
    }

    public void setBearer(PaystackBearer bearer) {
        this.bearer = bearer;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Integer getInvoice_limit() {
        return invoice_limit;
    }

    public void setInvoice_limit(Integer invoice_limit) {
        this.invoice_limit = invoice_limit;
    }

    public MetaData getMetadata() {
        return metadata;
    }

    public void setMetadata(MetaData metadata) {
        this.metadata = metadata;
    }

    public Integer getTransaction_charge() {
        return transaction_charge;
    }

    public void setTransaction_charge(Integer transaction_charge) {
        this.transaction_charge = transaction_charge;
    }

    public List<String> getChannel() {
        return channel;
    }

    public void setChannel(List<String> channel) throws ValidationException {
        if (!channel.contains("card") || !channel.contains("bank")) {
            throw new ValidationException("Invalid Channel, channel can only be 'bank' or `card`");
        }
        this.channel = channel;
    }

}
