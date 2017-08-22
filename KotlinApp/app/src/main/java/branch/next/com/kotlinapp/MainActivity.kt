package branch.next.com.kotlinapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import org.json.JSONObject

import java.util.ArrayList

import io.branch.indexing.BranchUniversalObject
import io.branch.referral.Branch
import io.branch.referral.BranchError
import io.branch.referral.util.CommerceEvent
import io.branch.referral.util.CurrencyType
import io.branch.referral.util.LinkProperties
import io.branch.referral.util.Product
import io.branch.referral.util.ProductCategory

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var linkData: String? = null
    private var branch_link: Boolean = false
    private var btnFb: Button? = null
    private var mATxtView: TextView? = null

    override fun onStart() {
        super.onStart()

        Branch.getInstance().initSession({ referringParams, error ->
            if (error == null) {
                Log.v("data", referringParams.toString())
//                Log.v("identity", branch!!.isUserIdentified.toString())
                linkData = referringParams.toString()

                try {
                    //
                    branch_link = referringParams.getBoolean("+clicked_branch_link")

                } catch (e: Exception) {
                    //
                }


            } else {
                Log.i("MyApp", error.message)
            }
        }, this.intent.data, this)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnFb = findViewById(R.id.fb_button) as Button
        mATxtView = findViewById(R.id.main_activity_textview) as TextView
        //        mATxtView.setText(linkData);
        //
        btnFb!!.setOnClickListener(this)
    }


    override fun onNewIntent(intent: Intent) {
        this.intent = intent
    }

    override fun onClick(v: View) {


        //        //Add details about each product associated with the purchase (optional)
        //        Product product1 = new Product();
        //        product1.setSku("u123");
        //        product1.setName("cactus");
        //        product1.setPrice(45.00);
        //        product1.setQuantity(2);
        //        product1.setBrand("brand1");
        //        product1.setCategory(ProductCategory.ELECTRONICS);
        //        product1.setVariant("variant1");
        //
        //        Product product2 = new Product();
        //        product2.setSku("u456");
        //        product2.setName("grass");
        //        product2.setPrice(9.00);
        //        product2.setQuantity(1);
        //        product2.setBrand("brand2");
        //        product2.setCategory(ProductCategory.CAMERA_AND_OPTICS);
        //        product2.setVariant("variant2");
        //
        //
        //        //Create a list of products associated with the particular purchase (optional)
        //        List<Product> productList = new ArrayList<Product>();
        //        productList.add(product1);
        //        productList.add(product2);
        //
        //
        //        //Create the commerce event (only revenue is required)
        //        CommerceEvent commerceEvent = new CommerceEvent();
        //        commerceEvent.setRevenue(50.29);
        //        commerceEvent.setCurrencyType(CurrencyType.INR);
        //        commerceEvent.setTransactionID("TRANS-1111");
        //        commerceEvent.setShipping(4.50);
        //        commerceEvent.setTax(110.90);
        //        commerceEvent.setAffiliation("AFF-ID-101");
        //        commerceEvent.setProducts(productList);
        //
        //
        //        //Add metadata (optional)
        //        JSONObject metadata = new JSONObject();
        //
        //        try {
        //            metadata.put("custom_dictionary", 123);
        //            metadata.put("testVar", "abc");
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }
        //
        //
        //        //Fire the commerce event
        //        Branch.getInstance().sendCommerceEvent(commerceEvent, metadata, null);


        val branchUniversalObject = BranchUniversalObject()
                .setCanonicalIdentifier("movie")
                .setTitle("Test for movie")
                .setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC)
                .setContentDescription("Your friend has invited you to check out my app!")
                .setContentImageUrl("https://image.freepik.com/free-vector/bird-icon_23-2147507196.jpg")
                .addContentMetadata("test", "abc")
                .addContentMetadata("data", "Red Activity")

        val linkProperties = LinkProperties()
                .setChannel("Facebook")
                .setFeature("Sharing")
                .addControlParameter("\$android_deepview", "branch_default")


        branchUniversalObject.generateShortUrl(this, linkProperties) { url, error ->
            if (error == null) {

                Log.v("url", url)

                Toast.makeText(this@MainActivity, url, Toast.LENGTH_LONG).show()
            } else {
                Log.v("url", url)
            }
        }

    }
}
