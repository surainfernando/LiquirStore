package hfad.com.liquirstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SellBatch extends AppCompatActivity {
int batchId;
String name;
String productId;
TextView IDView;
TextView productIDView;
TextView nameView;
    Spinner buyer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_batch);
        Intent intent=getIntent();
        batchId=intent.getIntExtra("batchId",0);
        name=intent.getStringExtra("name");
        productId=intent.getStringExtra("productId");
        IDView=(TextView)findViewById(R.id.IDView);
        productIDView=(TextView)findViewById(R.id.productIDView);
        nameView=(TextView)findViewById(R.id.nameView);
        buyer=(Spinner)findViewById(R.id.buyername);

        IDView.setText(String.valueOf(batchId));
        productIDView.setText(productId);
        nameView.setText(name);
    }
    public void onClick(View view)
    {  JSONObject postData = new JSONObject();

        String x=buyer.getSelectedItem().toString();
        int indexselected=buyer.getSelectedItemPosition();
        String permit="";
        if(indexselected==0)
        {
            permit="LS0001";
        }
        else
        {permit="LS0002";}

        try {
            postData.put("batchID", batchId);
            postData.put("newOwner",x);
            postData.put("permit",permit);

            volleyPost(postData); ///calls the volley method to make request. Email, password values are passed to volleyPost() method

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void volleyPost(JSONObject postData){
        ConnectionString obj=new ConnectionString();

        String postUrl = obj.connectioncode+"manufacturer/sellBatch";
        RequestQueue requestQueue = Volley.newRequestQueue(this);



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.i("MyActivity", "MyClass.getView() — get item number "+response);
                goToMain();
               // handleRecycler(response);// After the trespons e is recieved handleResponse() method will be called to read json response and take next steps.
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);

    }
    public void goToMain()
    {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}