package `in`.indilabz.ssf_exam.app

import `in`.indilabz.review_application.rest.RetrofitInstance
import `in`.indilabz.ssf_exam.INDIMaster
import `in`.indilabz.ssf_exam.utils.Toaster
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity/*
import com.paytm.pgsdk.PaytmOrder
import com.paytm.pgsdk.PaytmPGService
import com.paytm.pgsdk.PaytmPaymentTransactionCallback*/


abstract class PaymentActivity : AppCompatActivity() {

    var checkSum: String = ""
    var random : String = ""

    private var payTmParams : HashMap<String, String> = HashMap()

    override fun onCreate(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {

        payTmParams = prepareParams(random)

        super.onCreate(savedInstanceState, persistentState)
    }

    fun prepareOrder() {

        random = INDIMaster.getRandomString(22)!!

        val params:HashMap<String, String> = HashMap()

        params.put("CUST_ID", INDIPreferences.user()!!.mobile_number)
        params.put("CALLBACK_URL", "https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID=$random")
        params.put("ORDER_ID", random)
        params.put("INDUSTRY_TYPE_ID", "Retail")
        params.put("TXN_AMOUNT", "1000.00")
        params.put("MID", "UjNGiF62763194542476")
        params.put("WEBSITE","WEBSTAGING")
        params.put("CHANNEL_ID", "WAP")

       /* RetrofitInstance.getRetrofit(INDIMaster.api().generateChuckSum(
            params
        ), chucksum)*/
    }


    private val chucksum = {_: Int, bool:Boolean, value: String ->

        checkSum = value
        verifyOrder(checkSum)
    }

    private fun verifyOrder(value : String){

       /* RetrofitInstance.getRetrofit(INDIMaster.api().verifyChuckSum(
            value
        ), verify)*/
    }

    private val verify = { _: Int, bool:Boolean, value: String ->

        if(value == "true"){

            val params:HashMap<String, String> = HashMap()

            params.put("CUST_ID", INDIPreferences.user()!!.mobile_number)
            params.put("CALLBACK_URL", "https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID=$random")
            params.put("ORDER_ID", random)
            params.put("INDUSTRY_TYPE_ID", "Retail")
            params.put("TXN_AMOUNT", "1000.00")
            params.put("MID", "UjNGiF62763194542476")
            params.put("WEBSITE","WEBSTAGING")
            params.put("CHANNEL_ID", "WAP")
            params.put("CHECKSUMHASH", checkSum)

            //executeOrder(params)
        }
        else{
            Toaster.longt("HHHUUUU")
        }
        //Toaster.longt(value)
    }


    /*private fun executeOrder(params: HashMap<String, String>) {

        // choosing between PayTM staging and production
        val pgService = PaytmPGService.getStagingService()

        val Order = PaytmOrder(params)

        pgService.initialize(Order, null)

        pgService.startPaymentTransaction(this@PaymentActivity, true, true, object :
            PaytmPaymentTransactionCallback{
            override fun onTransactionResponse(inResponse: Bundle?) {

                val orderId = inResponse!!.getString("ORDERID")

               *//* RetrofitInstance.getRetrofit(
                    INDIMaster.api().status(random)
                , paymentStatus)*//*
            }

            override fun clientAuthenticationFailed(inErrorMessage: String?) {
                Toaster.longt(INDIMaster.gson.toJson(inErrorMessage))
            }

            override fun someUIErrorOccurred(inErrorMessage: String?) {
                Toaster.longt(INDIMaster.gson.toJson(inErrorMessage))
            }

            override fun onTransactionCancel(inErrorMessage: String?, inResponse: Bundle?) {
                Toaster.longt(INDIMaster.gson.toJson(inErrorMessage))
            }

            override fun networkNotAvailable() {
                Toaster.longt("EEYG")
            }

            override fun onErrorLoadingWebPage(
                iniErrorCode: Int,
                inErrorMessage: String?,
                inFailingUrl: String?
            ) {
                Toaster.longt(INDIMaster.gson.toJson(inErrorMessage))
            }

            override fun onBackPressedCancelTransaction() {
                Toaster.longt(INDIMaster.gson.toJson("BACK"))
            }

        })

        Toaster.longt("HERE")
    }*/

    private fun prepareParams(random: String): HashMap<String, String>{

        val params:HashMap<String, String> = HashMap()

        params.put("CUST_ID", INDIPreferences.user()!!.mobile_number)
        params.put("CALLBACK_URL", "https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID=$random")
        params.put("ORDER_ID", random)
        params.put("INDUSTRY_TYPE_ID", "Retail")
        params.put("TXN_AMOUNT", "1000.00")
        params.put("MID", "UjNGiF62763194542476")
        params.put("WEBSITE","WEBSTAGING")
        params.put("CHANNEL_ID", "WAP")

        return params
    }

    private val paymentStatus = {_: Int, bool:Boolean, value: String ->

        Toaster.longt(value)
    }
}